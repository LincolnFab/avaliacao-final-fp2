/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import model.Funcionario;

/**
 *
 * @author linkf
 */
@Stateless
public class FuncionarioDAO extends AbstractDAO<Funcionario> {

    public Funcionario buscarPorLoginSenha(String login, String senha) {
        return getEntityManager()
                .createNamedQuery("Funcionario.findByLoginSenha", Funcionario.class)
                .setParameter("login", login)
                .setParameter("senha", senha)
                .getSingleResult();
    }
}
