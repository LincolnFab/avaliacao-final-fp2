/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import model.Agencia;

/**
 *
 * @author linkf
 */
@Stateless
public class AgenciaDAO extends AbstractDAO<Agencia> {

    public Agencia buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Agencia.findById", Agencia.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
