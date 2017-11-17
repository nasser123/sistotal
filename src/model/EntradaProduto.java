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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entrada_produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaProduto.findAll", query = "SELECT e FROM EntradaProduto e"),
    @NamedQuery(name = "EntradaProduto.findByIdentradaProduto", query = "SELECT e FROM EntradaProduto e WHERE e.identradaProduto = :identradaProduto"),
    @NamedQuery(name = "EntradaProduto.findByPrecoUnit", query = "SELECT e FROM EntradaProduto e WHERE e.precoUnit = :precoUnit"),
    @NamedQuery(name = "EntradaProduto.findByIpi", query = "SELECT e FROM EntradaProduto e WHERE e.ipi = :ipi"),
    @NamedQuery(name = "EntradaProduto.findBySt", query = "SELECT e FROM EntradaProduto e WHERE e.st = :st"),
    @NamedQuery(name = "EntradaProduto.findByFrete", query = "SELECT e FROM EntradaProduto e WHERE e.frete = :frete"),
    @NamedQuery(name = "EntradaProduto.findByQuantidade", query = "SELECT e FROM EntradaProduto e WHERE e.quantidade = :quantidade"),
    @NamedQuery(name = "EntradaProduto.findByNotafiscal", query = "SELECT e FROM EntradaProduto e WHERE e.notafiscal = :notafiscal")})
public class EntradaProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identrada_produto")
    private Integer identradaProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_unit")
    private BigDecimal precoUnit;
    @Column(name = "ipi")
    private BigDecimal ipi;
    @Column(name = "st")
    private BigDecimal st;
    @Column(name = "frete")
    private BigDecimal frete;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "notafiscal")
    private Integer notafiscal;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor idfornecedor;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false)
    private Produto idproduto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identradaProduto")
    private List<SerialEntrada> serialEntradaList;

    public EntradaProduto() {
    }

    public EntradaProduto(Integer identradaProduto) {
        this.identradaProduto = identradaProduto;
    }

    public Integer getIdentradaProduto() {
        return identradaProduto;
    }

    public void setIdentradaProduto(Integer identradaProduto) {
        this.identradaProduto = identradaProduto;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }

    public BigDecimal getIpi() {
        return ipi;
    }

    public void setIpi(BigDecimal ipi) {
        this.ipi = ipi;
    }

    public BigDecimal getSt() {
        return st;
    }

    public void setSt(BigDecimal st) {
        this.st = st;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getNotafiscal() {
        return notafiscal;
    }

    public void setNotafiscal(Integer notafiscal) {
        this.notafiscal = notafiscal;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

    @XmlTransient
    public List<SerialEntrada> getSerialEntradaList() {
        return serialEntradaList;
    }

    public void setSerialEntradaList(List<SerialEntrada> serialEntradaList) {
        this.serialEntradaList = serialEntradaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identradaProduto != null ? identradaProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaProduto)) {
            return false;
        }
        EntradaProduto other = (EntradaProduto) object;
        if ((this.identradaProduto == null && other.identradaProduto != null) || (this.identradaProduto != null && !this.identradaProduto.equals(other.identradaProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EntradaProduto[ identradaProduto=" + identradaProduto + " ]";
    }
    
}
