/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nasser
 */
@Entity
@Table(name = "contas_a_receber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContasAReceber.findAll", query = "SELECT c FROM ContasAReceber c"),
    @NamedQuery(name = "ContasAReceber.findByIdcontasAReceber", query = "SELECT c FROM ContasAReceber c WHERE c.idcontasAReceber = :idcontasAReceber"),
    @NamedQuery(name = "ContasAReceber.findByValor", query = "SELECT c FROM ContasAReceber c WHERE c.valor = :valor")})
public class ContasAReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontas_a_receber")
    private Integer idcontasAReceber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idvenda", referencedColumnName = "idvenda")
    @ManyToOne(optional = false)
    private Venda idvenda;

    public ContasAReceber() {
    }

    public ContasAReceber(Integer idcontasAReceber) {
        this.idcontasAReceber = idcontasAReceber;
    }

    public ContasAReceber(Integer idcontasAReceber, BigDecimal valor) {
        this.idcontasAReceber = idcontasAReceber;
        this.valor = valor;
    }

    public Integer getIdcontasAReceber() {
        return idcontasAReceber;
    }

    public void setIdcontasAReceber(Integer idcontasAReceber) {
        this.idcontasAReceber = idcontasAReceber;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Venda getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Venda idvenda) {
        this.idvenda = idvenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontasAReceber != null ? idcontasAReceber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasAReceber)) {
            return false;
        }
        ContasAReceber other = (ContasAReceber) object;
        if ((this.idcontasAReceber == null && other.idcontasAReceber != null) || (this.idcontasAReceber != null && !this.idcontasAReceber.equals(other.idcontasAReceber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ContasAReceber[ idcontasAReceber=" + idcontasAReceber + " ]";
    }
    
}
