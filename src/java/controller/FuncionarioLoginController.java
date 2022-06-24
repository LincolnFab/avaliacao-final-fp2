/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Funcionario;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@SessionScoped
public class FuncionarioLoginController implements Serializable {

    @Inject
    private FuncionarioDAO funcionarioDAO;

    private Funcionario funcionarioAutenticado;

    public FuncionarioLoginController() {
        this.funcionarioAutenticado = new Funcionario();
    }

    public String authenticator() {
        System.out.println(funcionarioAutenticado.toString());
        Funcionario funcionario
                = funcionarioDAO.buscarPorLoginSenha(
                        funcionarioAutenticado.getLogin(),
                        funcionarioAutenticado.getSenha());

        if (funcionario != null) {
            if (funcionario.getSenha().equals(funcionarioAutenticado.getSenha())) {
                this.funcionarioAutenticado = funcionario;
                return "funcionario/home?faces-redirect=true";
            } else {
                Util.addMessageError("Senha Inválida");
            }
        } else {
            Util.addMessageError("Login não encontrada");
        }
        return null;
    }

    public void alterarSenha() {
        funcionarioDAO.edit(funcionarioAutenticado);

        Util.addMessageInformation("Senha Alterada");

        PrimeFaces.current().executeScript("PF('alterPasswordDialog').hide()");
        PrimeFaces.current().ajax().update("messages");
    }

    public String logout() {
        this.funcionarioAutenticado = new Funcionario();
        return "/login_funcionario?faces-redirect=true";
    }

    public Funcionario getFuncionarioAutenticado() {
        return funcionarioAutenticado;
    }

    public void setFuncionarioAutenticado(Funcionario funcionarioAutenticado) {
        this.funcionarioAutenticado = funcionarioAutenticado;
    }

}
