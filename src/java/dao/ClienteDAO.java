/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Cliente;

/**
 *
 * @author linkf
 */
@Stateless
public class ClienteDAO extends AbstractDAO<Cliente> {

    public Cliente buscarPorCpf(String cpf) {
        return getEntityManager()
                .createNamedQuery("Cliente.findByCpf", Cliente.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    public List<Cliente> buscarTodos() {
        return getEntityManager()
                .createNamedQuery("Cliente.findAll", Cliente.class)
                .getResultList();
    }
}
