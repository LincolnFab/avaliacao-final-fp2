/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author linkf
 */
@Entity
@Table(name = "operacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacao.findAll", query = "SELECT o FROM Operacao o"),
    @NamedQuery(name = "Operacao.findById", query = "SELECT o FROM Operacao o WHERE o.operacaoPK.id = :id"),
    @NamedQuery(name = "Operacao.findByContaId", query = "SELECT o FROM Operacao o WHERE o.operacaoPK.contaId = :contaId"),
    @NamedQuery(name = "Operacao.findByContaAgenciaId", query = "SELECT o FROM Operacao o WHERE o.operacaoPK.contaAgenciaId = :contaAgenciaId"),
    @NamedQuery(name = "Operacao.findByContaIdDestino", query = "SELECT o FROM Operacao o WHERE o.contaIdDestino = :contaIdDestino"),
    @NamedQuery(name = "Operacao.findByAgenciaIdDestino", query = "SELECT o FROM Operacao o WHERE o.agenciaIdDestino = :agenciaIdDestino"),
    @NamedQuery(name = "Operacao.findByData", query = "SELECT o FROM Operacao o WHERE o.data = :data"),
    @NamedQuery(name = "Operacao.findByValor", query = "SELECT o FROM Operacao o WHERE o.valor = :valor"),
    @NamedQuery(name = "Operacao.findByStatus", query = "SELECT o FROM Operacao o WHERE o.status = :status"),
    @NamedQuery(name = "Operacao.findByTipoOperacao", query = "SELECT o FROM Operacao o WHERE o.tipoOperacao = :tipoOperacao")})
public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OperacaoPK operacaoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conta_id_destino")
    private int contaIdDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agencia_id_destino")
    private int agenciaIdDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_operacao")
    private String tipoOperacao;
    @JoinColumns({
        @JoinColumn(name = "conta_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "conta_agencia_id", referencedColumnName = "agencia_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Conta conta;

    public Operacao() {
    }

    public Operacao(OperacaoPK operacaoPK) {
        this.operacaoPK = operacaoPK;
    }

    public Operacao(OperacaoPK operacaoPK, int contaIdDestino, int agenciaIdDestino, Date data, double valor, boolean status, String tipoOperacao) {
        this.operacaoPK = operacaoPK;
        this.contaIdDestino = contaIdDestino;
        this.agenciaIdDestino = agenciaIdDestino;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.tipoOperacao = tipoOperacao;
    }

    public Operacao(int id, int contaId, int contaAgenciaId) {
        this.operacaoPK = new OperacaoPK(id, contaId, contaAgenciaId);
    }

    public OperacaoPK getOperacaoPK() {
        return operacaoPK;
    }

    public void setOperacaoPK(OperacaoPK operacaoPK) {
        this.operacaoPK = operacaoPK;
    }

    public int getContaIdDestino() {
        return contaIdDestino;
    }

    public void setContaIdDestino(int contaIdDestino) {
        this.contaIdDestino = contaIdDestino;
    }

    public int getAgenciaIdDestino() {
        return agenciaIdDestino;
    }

    public void setAgenciaIdDestino(int agenciaIdDestino) {
        this.agenciaIdDestino = agenciaIdDestino;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operacaoPK != null ? operacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacao)) {
            return false;
        }
        Operacao other = (Operacao) object;
        if ((this.operacaoPK == null && other.operacaoPK != null) || (this.operacaoPK != null && !this.operacaoPK.equals(other.operacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Operacao[ operacaoPK=" + operacaoPK + " ]";
    }
    
}
