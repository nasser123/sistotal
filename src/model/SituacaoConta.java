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
@Table(name = "situacao_conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacaoConta.findAll", query = "SELECT s FROM SituacaoConta s"),
    @NamedQuery(name = "SituacaoConta.findByIdsituacaoConta", query = "SELECT s FROM SituacaoConta s WHERE s.idsituacaoConta = :idsituacaoConta"),
    @NamedQuery(name = "SituacaoConta.findByDescricao", query = "SELECT s FROM SituacaoConta s WHERE s.descricao = :descricao")})
public class SituacaoConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsituacao_conta")
    private Integer idsituacaoConta;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsituacaoConta")
    private List<ContasAPagar> contasAPagarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsituacaoConta")
    private List<ControleCartoes> controleCartoesList;

    public SituacaoConta() {
    }

    public SituacaoConta(Integer idsituacaoConta) {
        this.idsituacaoConta = idsituacaoConta;
    }

    public Integer getIdsituacaoConta() {
        return idsituacaoConta;
    }

    public void setIdsituacaoConta(Integer idsituacaoConta) {
        this.idsituacaoConta = idsituacaoConta;
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

    @XmlTransient
    public List<ControleCartoes> getControleCartoesList() {
        return controleCartoesList;
    }

    public void setControleCartoesList(List<ControleCartoes> controleCartoesList) {
        this.controleCartoesList = controleCartoesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsituacaoConta != null ? idsituacaoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoConta)) {
            return false;
        }
        SituacaoConta other = (SituacaoConta) object;
        if ((this.idsituacaoConta == null && other.idsituacaoConta != null) || (this.idsituacaoConta != null && !this.idsituacaoConta.equals(other.idsituacaoConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SituacaoConta[ idsituacaoConta=" + idsituacaoConta + " ]";
    }
    
}
