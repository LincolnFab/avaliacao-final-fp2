/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContaDAO;
import java.io.Serializable;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Conta;
import model.ContaPK;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@SessionScoped
public class ContaLoginController implements Serializable {

    @Inject
    private ContaDAO contaDAO;

    private Conta contaAutenticada;

    public ContaLoginController() {
        this.contaAutenticada = new Conta();
        this.contaAutenticada.setContaPK(new ContaPK());
    }

    public String authenticator() {
        Conta conta = null;
        try {
            conta = contaDAO.buscarPorContaAgencia(
                    this.contaAutenticada.getContaPK().getId(),
                    this.contaAutenticada.getContaPK().getAgenciaId());
        } catch (EJBException ex) {
            ex.printStackTrace();
        }

        if (conta != null) {
            if (conta.getSenha().equals(contaAutenticada.getSenha())) {
                this.contaAutenticada = conta;
                return "cliente/home?faces-redirect=true";
            } else {
                Util.addMessageError("Senha Inválida");
            }
        } else {
            Util.addMessageError("Conta não encontrada");
        }
        return null;
    }

    public void alterarSenha() {
        contaDAO.edit(contaAutenticada);

        Util.addMessageInformation("Senha Alterada");

        PrimeFaces.current().executeScript("PF('alterPasswordDialog').hide()");
        PrimeFaces.current().ajax().update("messages");
    }

    public String logout() {
        this.contaAutenticada = new Conta();
        return "/login_conta?faces-redirect=true";
    }

    public Conta getContaAutenticada() {
        return contaAutenticada;
    }

    public void setContaAutenticada(Conta contaAutenticada) {
        this.contaAutenticada = contaAutenticada;
    }
}
