/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name = "lista_atributos_produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaAtributosProduto.findAll", query = "SELECT l FROM ListaAtributosProduto l"),
    @NamedQuery(name = "ListaAtributosProduto.findByIdlistaAtributosProduto", query = "SELECT l FROM ListaAtributosProduto l WHERE l.idlistaAtributosProduto = :idlistaAtributosProduto")})
public class ListaAtributosProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista_atributos_produto")
    private Integer idlistaAtributosProduto;
    @JoinColumn(name = "idatributos", referencedColumnName = "idatributos")
    @ManyToOne(optional = false)
    private Atributos idatributos;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false)
    private Produto idproduto;

    public ListaAtributosProduto() {
    }

    public ListaAtributosProduto(Integer idlistaAtributosProduto) {
        this.idlistaAtributosProduto = idlistaAtributosProduto;
    }

    public Integer getIdlistaAtributosProduto() {
        return idlistaAtributosProduto;
    }

    public void setIdlistaAtributosProduto(Integer idlistaAtributosProduto) {
        this.idlistaAtributosProduto = idlistaAtributosProduto;
    }

    public Atributos getIdatributos() {
        return idatributos;
    }

    public void setIdatributos(Atributos idatributos) {
        this.idatributos = idatributos;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistaAtributosProduto != null ? idlistaAtributosProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaAtributosProduto)) {
            return false;
        }
        ListaAtributosProduto other = (ListaAtributosProduto) object;
        if ((this.idlistaAtributosProduto == null && other.idlistaAtributosProduto != null) || (this.idlistaAtributosProduto != null && !this.idlistaAtributosProduto.equals(other.idlistaAtributosProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ListaAtributosProduto[ idlistaAtributosProduto=" + idlistaAtributosProduto + " ]";
    }
    
}
