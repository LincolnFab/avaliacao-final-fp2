/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import model.Operacao;

/**
 *
 * @author linkf
 */
@Stateless
public class OperacaoDAO extends AbstractDAO<Operacao> {

    public List<Operacao> buscarPorStatus(boolean status) {
        return getEntityManager()
                .createNamedQuery("Operacao.findByStatus", Operacao.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Operacao> buscarPorPeriodo(Date data_ini, Date data_fin) {
        return getEntityManager()
                .createQuery("SELECT o FROM Operacao o WHERE o.data >= :data_ini AND o.data <= :data_fin", Operacao.class)
                .setParameter("data_ini", data_ini)
                .setParameter("data_fin", data_fin)
                .getResultList();
    }
}
