/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Conta;

/**
 *
 * @author linkf
 */
@Stateless
public class ContaDAO extends AbstractDAO<Conta> {

    public List<Conta> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Conta.findAll", Conta.class)
                .getResultList();
    }

    public Conta buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Conta.findById", Conta.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public Conta buscarPorContaAgencia(int numConta, int numAgencia) {
        return getEntityManager()
                .createNamedQuery("Conta.findByIdAndAgenciaId", Conta.class)
                .setParameter("id", numConta)
                .setParameter("agenciaId", numAgencia)
                .getSingleResult();
    }
}
