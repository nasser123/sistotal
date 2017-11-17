/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nasser
 */
@Entity
@Table(name = "centro_custo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroCusto.findAll", query = "SELECT c FROM CentroCusto c"),
    @NamedQuery(name = "CentroCusto.findByIdcentroCusto", query = "SELECT c FROM CentroCusto c WHERE c.idcentroCusto = :idcentroCusto"),
    @NamedQuery(name = "CentroCusto.findByDescricao", query = "SELECT c FROM CentroCusto c WHERE c.descricao = :descricao")})
public class CentroCusto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcentro_custo")
    private Integer idcentroCusto;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcentroCusto")
    private List<ContasAPagar> contasAPagarList;

    public CentroCusto() {
    }

    public CentroCusto(Integer idcentroCusto) {
        this.idcentroCusto = idcentroCusto;
    }

    public Integer getIdcentroCusto() {
        return idcentroCusto;
    }

    public void setIdcentroCusto(Integer idcentroCusto) {
        this.idcentroCusto = idcentroCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<ContasAPagar> getContasAPagarList() {
        return contasAPagarList;
    }

    public void setContasAPagarList(List<ContasAPagar> contasAPagarList) {
        this.contasAPagarList = contasAPagarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcentroCusto != null ? idcentroCusto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroCusto)) {
            return false;
        }
        CentroCusto other = (CentroCusto) object;
        if ((this.idcentroCusto == null && other.idcentroCusto != null) || (this.idcentroCusto != null && !this.idcentroCusto.equals(other.idcentroCusto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CentroCusto[ idcentroCusto=" + idcentroCusto + " ]";
    }
    
}
