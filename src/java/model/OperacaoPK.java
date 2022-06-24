/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author linkf
 */
@Embeddable
public class OperacaoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conta_id")
    private int contaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conta_agencia_id")
    private int contaAgenciaId;

    public OperacaoPK() {
    }

    public OperacaoPK(int id, int contaId, int contaAgenciaId) {
        this.id = id;
        this.contaId = contaId;
        this.contaAgenciaId = contaAgenciaId;
    }

    public OperacaoPK(int contaId, int contaAgenciaId) {
        this.contaId = contaId;
        this.contaAgenciaId = contaAgenciaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getContaAgenciaId() {
        return contaAgenciaId;
    }

    public void setContaAgenciaId(int contaAgenciaId) {
        this.contaAgenciaId = contaAgenciaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) contaId;
        hash += (int) contaAgenciaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperacaoPK)) {
            return false;
        }
        OperacaoPK other = (OperacaoPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.contaId != other.contaId) {
            return false;
        }
        if (this.contaAgenciaId != other.contaAgenciaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OperacaoPK[ id=" + id + ", contaId=" + contaId + ", contaAgenciaId=" + contaAgenciaId + " ]";
    }

}
