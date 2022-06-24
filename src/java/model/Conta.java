/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author linkf
 */
@Entity
@Table(name = "conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByIdAndAgenciaId", query = "SELECT c FROM Conta c WHERE c.contaPK.id = :id AND c.contaPK.agenciaId = :agenciaId"),
    @NamedQuery(name = "Conta.findById", query = "SELECT c FROM Conta c WHERE c.contaPK.id = :id"),
    @NamedQuery(name = "Conta.findByAgenciaId", query = "SELECT c FROM Conta c WHERE c.contaPK.agenciaId = :agenciaId"),
    @NamedQuery(name = "Conta.findByTipoConta", query = "SELECT c FROM Conta c WHERE c.tipoConta = :tipoConta"),
    @NamedQuery(name = "Conta.findBySaldo", query = "SELECT c FROM Conta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Conta.findByLimite", query = "SELECT c FROM Conta c WHERE c.limite = :limite"),
    @NamedQuery(name = "Conta.findByDataAbertura", query = "SELECT c FROM Conta c WHERE c.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "Conta.findBySenha", query = "SELECT c FROM Conta c WHERE c.senha = :senha"),
    @NamedQuery(name = "Conta.findByStatus", query = "SELECT c FROM Conta c WHERE c.status = :status")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContaPK contaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_conta")
    private String tipoConta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private double saldo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limite")
    private Double limite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_abertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private Collection<Operacao> operacaoCollection;
    @JoinColumn(name = "agencia_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Agencia agencia;
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Cliente clienteCpf;

    public Conta() {
    }

    public Conta(ContaPK contaPK) {
        this.contaPK = contaPK;
    }

    public Conta(ContaPK contaPK, String tipoConta, double saldo, Date dataAbertura, String senha, boolean status) {
        this.contaPK = contaPK;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
        this.senha = senha;
        this.status = status;
    }

    public Conta(int id, int agenciaId) {
        this.contaPK = new ContaPK(id, agenciaId);
    }

    public ContaPK getContaPK() {
        return contaPK;
    }

    public void setContaPK(ContaPK contaPK) {
        this.contaPK = contaPK;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Operacao> getOperacaoCollection() {
        return operacaoCollection;
    }

    public void setOperacaoCollection(Collection<Operacao> operacaoCollection) {
        this.operacaoCollection = operacaoCollection;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cliente getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(Cliente clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contaPK != null ? contaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.contaPK == null && other.contaPK != null) || (this.contaPK != null && !this.contaPK.equals(other.contaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conta{" + "contaPK=" + contaPK + ", tipoConta=" + tipoConta + ", saldo=" + saldo + ", limite=" + limite + ", dataAbertura=" + dataAbertura + ", senha=" + senha + ", status=" + status + ", operacaoCollection=" + operacaoCollection + ", agencia=" + agencia + ", clienteCpf=" + clienteCpf + '}';
    }

}
