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
@Table(name = "atributos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atributos.findAll", query = "SELECT a FROM Atributos a"),
    @NamedQuery(name = "Atributos.findByIdatributos", query = "SELECT a FROM Atributos a WHERE a.idatributos = :idatributos"),
    @NamedQuery(name = "Atributos.findByDescricao", query = "SELECT a FROM Atributos a WHERE a.descricao = :descricao")})
public class Atributos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatributos")
    private Integer idatributos;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatributos")
    private List<ListaAtributosProduto> listaAtributosProdutoList;
    @JoinColumn(name = "idtipo_atributo", referencedColumnName = "idtipo_atributo")
    @ManyToOne(optional = false)
    private TipoAtributo idtipoAtributo;

    public Atributos() {
    }

    public Atributos(Integer idatributos) {
        this.idatributos = idatributos;
    }

    public Integer getIdatributos() {
        return idatributos;
    }

    public void setIdatributos(Integer idatributos) {
        this.idatributos = idatributos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<ListaAtributosProduto> getListaAtributosProdutoList() {
        return listaAtributosProdutoList;
    }

    public void setListaAtributosProdutoList(List<ListaAtributosProduto> listaAtributosProdutoList) {
        this.listaAtributosProdutoList = listaAtributosProdutoList;
    }

    public TipoAtributo getIdtipoAtributo() {
        return idtipoAtributo;
    }

    public void setIdtipoAtributo(TipoAtributo idtipoAtributo) {
        this.idtipoAtributo = idtipoAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatributos != null ? idatributos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atributos)) {
            return false;
        }
        Atributos other = (Atributos) object;
        if ((this.idatributos == null && other.idatributos != null) || (this.idatributos != null && !this.idatributos.equals(other.idatributos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Atributos[ idatributos=" + idatributos + " ]";
    }
    
}
