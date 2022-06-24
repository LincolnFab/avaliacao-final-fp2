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
public class ContaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agencia_id")
    private int agenciaId;

    public ContaPK() {
    }

    public ContaPK(int id, int agenciaId) {
        this.id = id;
        this.agenciaId = agenciaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) agenciaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaPK)) {
            return false;
        }
        ContaPK other = (ContaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.agenciaId != other.agenciaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ContaPK[ id=" + id + ", agenciaId=" + agenciaId + " ]";
    }
    
}
