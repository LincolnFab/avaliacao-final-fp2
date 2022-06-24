/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AgenciaDAO;
import dao.ClienteDAO;
import dao.ContaDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Agencia;
import model.Cliente;
import model.Conta;
import model.ContaPK;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@ViewScoped
public class ContaController implements Serializable {

    @Inject
    private ContaDAO contaDAO;

    @Inject
    private AgenciaDAO agenciaDAO;

    @Inject
    private ClienteDAO clienteDAO;

    private Conta conta;
    private List<Conta> contas;
    private List<Conta> contasSelecionadas;

    public ContaController() {
        this.conta = new Conta();
        this.conta.setAgencia(new Agencia());
        this.conta.setClienteCpf(new Cliente());
    }

    @PostConstruct
    public void init() {
        this.contas = contaDAO.buscarTodas();
//        for (Conta c : this.contas) {
//            if (!c.getClienteCpf().getStatus()) {
//                this.contas.remove(c);
//                System.out.println(c.toString());
//            }
//        }
    }

    public void openNew() {
        this.conta = new Conta();
        this.conta.setAgencia(new Agencia());
        this.conta.setClienteCpf(new Cliente());
    }

    public void criarConta() {
        try {
            if (clienteDAO.buscarPorCpf(conta.getClienteCpf().getCpf()) != null) {
                Agencia agencia = agenciaDAO.buscarPorId(1);
                this.conta.setAgencia(agencia);
                this.conta.setContaPK(new ContaPK(contaDAO.buscarTodas().size() + 1, agencia.getId()));
                this.conta.setDataAbertura(new Date());
                this.conta.setSaldo(0);
                this.conta.setStatus(true);

                System.out.println(
                        "\n\n*********************************"
                        + "\nAgencia ID...: " + this.conta.getContaPK().getAgenciaId()
                        + "\nTipoConta ID.: " + this.conta.getTipoConta()
                        + "\nCliente CPF..: " + this.conta.getClienteCpf().getCpf()
                        + "\nSaldo........: " + this.conta.getSaldo()
                        + "\nLimite.......: " + this.conta.getLimite()
                        + "\nData Abertura: " + this.conta.getDataAbertura()
                        + "\nSenha........: " + this.conta.getSenha()
                        + "\n*********************************\n\n"
                );

                this.contaDAO.create(conta);
                this.contas.add(this.conta);
                Util.addMessageInformation("Conta Criada");
                this.openNew();
            }
        } catch (EJBException ex) {
            ex.printStackTrace();
            Util.addMessageError("Cliente não encontrado");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
            return;
        }

        PrimeFaces.current().executeScript("PF('createContaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
    }

    public void desativarConta() {
        if (conta.getStatus()) {
            Conta c = contaDAO.buscarPorId(conta.getContaPK().getId());

            if (c != null) {
                conta.setStatus(false);
                contas.set(contas.indexOf(c), conta);
                contaDAO.edit(conta);
            }

            this.conta = null;
        } else {
            Util.addMessageWarning("Conta já foi desativada");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
            return;
        }

        Util.addMessageInformation("Conta Desativada");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
    }

    public void reativarConta() {
        if (!conta.getStatus()) {
            Conta c = contaDAO.buscarPorId(conta.getContaPK().getId());

            if (c != null) {
                conta.setStatus(true);
                contas.set(contas.indexOf(c), conta);
                contaDAO.edit(conta);
            }

            this.conta = null;
        } else {
            Util.addMessageWarning("Conta já está ativa");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
            return;
        }

        Util.addMessageInformation("Conta Reativada");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-contas");
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Conta> getContassSelecionados() {
        return contasSelecionadas;
    }

    public void setContassSelecionados(List<Conta> contassSelecionados) {
        this.contasSelecionadas = contassSelecionados;
    }

}
