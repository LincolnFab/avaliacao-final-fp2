/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aluno
 * @param <T>
 */
public abstract class AbstractDAO<T> {

    @PersistenceContext(unitName = "BancoIFSPPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public void remove(T entity) {
        em.remove(entity);
    }

    public void edit(T entity) {
        em.merge(entity);
    }
}
