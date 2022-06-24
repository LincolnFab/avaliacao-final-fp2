/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContaDAO;
import dao.OperacaoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Conta;
import model.Operacao;
import model.OperacaoPK;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@ViewScoped
public class OperacaoController implements Serializable {

    @Inject
    private OperacaoDAO operacaoDAO;

    @Inject
    private ContaDAO contaDAO;

    @Inject
    private ContaLoginController contaLoginController;

    private Operacao operacao;
    private Conta conta;

    private List<Date> periodo;
    private List<Operacao> extrato;

    private List<Operacao> depositosPendentes;
    private List<Operacao> depositosSelecionado;

    @PostConstruct
    public void setConta() {
        this.conta = contaLoginController.getContaAutenticada();
        depositosPendentes = operacaoDAO.buscarPorStatus(false);
    }

    public void openNew() {
        depositosPendentes = operacaoDAO.buscarPorStatus(false);
        operacao = new Operacao();
    }

    public OperacaoController() {
        periodo = new ArrayList<Date>();
        operacao = new Operacao();
    }

    public void saque() {
        if (operacao.getValor() > 0) {
            operacao.setData(new Date());
            operacao.setOperacaoPK(new OperacaoPK(this.conta.getContaPK().getId(), this.conta.getAgencia().getId()));
            operacao.setConta(this.conta);
            operacao.setStatus(true);
            operacao.setTipoOperacao("saque");

            if (this.conta.getTipoConta().equals("especial")) {
                if (operacao.getValor() > (this.conta.getSaldo() + this.conta.getLimite())) {
                    Util.addMessageError("Limite Atingido");
                    return;
                } else {
                    this.operacaoDAO.create(operacao);
                    conta.setSaldo(conta.getSaldo() - operacao.getValor());

                    this.contaDAO.edit(conta);
                }
            } else {
                if (operacao.getValor() > this.conta.getSaldo()) {

                    Util.addMessageError("Saldo Insuficiente");
                    return;
                } else {
                    this.operacaoDAO.create(operacao);
                    conta.setSaldo(conta.getSaldo() - operacao.getValor());

                    this.contaDAO.edit(conta);
                }
            }

            openNew();
            Util.addMessageInformation("Saque Realizado");
            PrimeFaces.current().ajax().update("form:saldo");
        } else {
            Util.addMessageWarning("Informe o Valor");
        }
    }

    public void deposito_caixa() {
        if (operacao.getValor() > 0) {
            operacao.setData(new Date());
            operacao.setOperacaoPK(new OperacaoPK(this.conta.getContaPK().getId(), this.conta.getAgencia().getId()));
            operacao.setConta(this.conta);
            operacao.setStatus(false);
            operacao.setTipoOperacao("deposito_caixa");

            this.operacaoDAO.create(operacao);

            openNew();
            Util.addMessageInformation("Depósito enviado para análise");
            PrimeFaces.current().ajax().update("form:saldo");
        } else {
            Util.addMessageWarning("Informe o Valor");
        }
    }

    public void deposito_atendente() {
        if (operacao.getValor() > 0) {
            operacao.setData(new Date());
            operacao.setOperacaoPK(new OperacaoPK(this.conta.getContaPK().getId(), this.conta.getAgencia().getId()));
            operacao.setConta(this.conta);
            operacao.setStatus(true);
            operacao.setTipoOperacao("deposito_atendente");

            this.operacaoDAO.create(operacao);
            conta.setSaldo(conta.getSaldo() + operacao.getValor());

            this.contaDAO.edit(conta);

            openNew();
            Util.addMessageInformation("Depósito Realizado");
            PrimeFaces.current().ajax().update("form:saldo");
        } else {
            Util.addMessageWarning("Informe o Valor");
        }
    }

    public void transferencia() {
        Conta contaDestino;
        try {
            contaDestino = contaDAO.buscarPorContaAgencia(operacao.getContaIdDestino(), operacao.getAgenciaIdDestino());
        } catch (EJBException ex) {
            Util.addMessageError("Conta não encontrada");
            return;
        }

        operacao.setData(new Date());
        operacao.setOperacaoPK(new OperacaoPK(this.conta.getContaPK().getId(), this.conta.getAgencia().getId()));
        operacao.setConta(this.conta);
        operacao.setStatus(true);
        operacao.setTipoOperacao("transferencia");

        if (operacao.getValor() > 0) {
            if (conta.getSaldo() >= operacao.getValor()) {
                this.operacaoDAO.create(operacao);

                conta.setSaldo(conta.getSaldo() - operacao.getValor());
                contaDestino.setSaldo(contaDestino.getSaldo() + operacao.getValor());

                this.contaDAO.edit(conta);
                this.contaDAO.edit(contaDestino);

                openNew();
                Util.addMessageInformation("Transferência Realizada");
                PrimeFaces.current().ajax().update("form:saldo");
            } else {
                Util.addMessageWarning("Saldo Insuficiente");
            }
        } else {
            Util.addMessageWarning("Informe o Valor");
        }
    }

    public void autorizarDeposito() {
        Conta conta = operacao.getConta();
        operacao.setStatus(true);

        operacaoDAO.edit(operacao);

        conta.setSaldo(conta.getSaldo() + operacao.getValor());
        contaDAO.edit(conta);

        depositosPendentes.remove(operacao);

        openNew();

        PrimeFaces.current().ajax().update("form:messages", "form:dt-depositos");
        Util.addMessageInformation("Depósito Autorizado");
    }

    public void extratoPeriodo() {
        this.extrato = operacaoDAO.buscarPorPeriodo(conta.getContaPK().getId(), conta.getContaPK().getAgenciaId(), periodo.get(0), periodo.get(1));
        if (this.extrato.size() == 0) {
            Util.addMessageWarning("Não foram realizadas operações no período selecionado");
        } else {
            Util.addMessageInformation("O extrato foi listado");
        }
        this.periodo = new ArrayList<Date>();
    }

    public List<Date> getPeriodo() {
        return periodo;
    }

    public void setPeriodo(List<Date> periodo) {
        this.periodo = periodo;
    }

    public List<Operacao> getExtrato() {
        return extrato;
    }

    public void setExtrato(List<Operacao> extrato) {
        this.extrato = extrato;
    }

    public List<Operacao> getDepositosSelecionado() {
        return depositosSelecionado;
    }

    public void setDepositosSelecionado(List<Operacao> depositosSelecionado) {
        this.depositosSelecionado = depositosSelecionado;
    }

    public List<Operacao> getDepositosPendentes() {
        return depositosPendentes;
    }

    public void setDepositosPendentes(List<Operacao> depositosPendentes) {
        this.depositosPendentes = depositosPendentes;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
