/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cartao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartao.findAll", query = "SELECT c FROM Cartao c"),
    @NamedQuery(name = "Cartao.findByIdcartao", query = "SELECT c FROM Cartao c WHERE c.idcartao = :idcartao"),
    @NamedQuery(name = "Cartao.findByDescricao", query = "SELECT c FROM Cartao c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Cartao.findByDesconto", query = "SELECT c FROM Cartao c WHERE c.desconto = :desconto"),
    @NamedQuery(name = "Cartao.findByPrazo", query = "SELECT c FROM Cartao c WHERE c.prazo = :prazo"),
    @NamedQuery(name = "Cartao.findByNrPrest", query = "SELECT c FROM Cartao c WHERE c.nrPrest = :nrPrest")})
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcartao")
    private Integer idcartao;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Basic(optional = false)
    @Column(name = "prazo")
    private int prazo;
    @Column(name = "nr_prest")
    private Integer nrPrest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcartao")
    private List<ControleCartoes> controleCartoesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcartao")
    private List<VendaCartao> vendaCartaoList;

    public Cartao() {
    }

    public Cartao(Integer idcartao) {
        this.idcartao = idcartao;
    }

    public Cartao(Integer idcartao, String descricao, BigDecimal desconto, int prazo) {
        this.idcartao = idcartao;
        this.descricao = descricao;
        this.desconto = desconto;
        this.prazo = prazo;
    }

    public Integer getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Integer idcartao) {
        this.idcartao = idcartao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public Integer getNrPrest() {
        return nrPrest;
    }

    public void setNrPrest(Integer nrPrest) {
        this.nrPrest = nrPrest;
    }

    @XmlTransient
    public List<ControleCartoes> getControleCartoesList() {
        return controleCartoesList;
    }

    public void setControleCartoesList(List<ControleCartoes> controleCartoesList) {
        this.controleCartoesList = controleCartoesList;
    }

    @XmlTransient
    public List<VendaCartao> getVendaCartaoList() {
        return vendaCartaoList;
    }

    public void setVendaCartaoList(List<VendaCartao> vendaCartaoList) {
        this.vendaCartaoList = vendaCartaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcartao != null ? idcartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.idcartao == null && other.idcartao != null) || (this.idcartao != null && !this.idcartao.equals(other.idcartao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cartao[ idcartao=" + idcartao + " ]";
    }
    
}
