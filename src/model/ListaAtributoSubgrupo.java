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
@Table(name = "lista_atributo_subgrupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaAtributoSubgrupo.findAll", query = "SELECT l FROM ListaAtributoSubgrupo l"),
    @NamedQuery(name = "ListaAtributoSubgrupo.findByIdlistaAtributoSubgrupo", query = "SELECT l FROM ListaAtributoSubgrupo l WHERE l.idlistaAtributoSubgrupo = :idlistaAtributoSubgrupo")})
public class ListaAtributoSubgrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista_atributo_subgrupo")
    private Integer idlistaAtributoSubgrupo;
    @JoinColumn(name = "idsubgrupo", referencedColumnName = "idsubgrupo")
    @ManyToOne(optional = false)
    private Subgrupo idsubgrupo;
    @JoinColumn(name = "idtipo_atributo", referencedColumnName = "idtipo_atributo")
    @ManyToOne(optional = false)
    private TipoAtributo idtipoAtributo;

    public ListaAtributoSubgrupo() {
    }

    public ListaAtributoSubgrupo(Integer idlistaAtributoSubgrupo) {
        this.idlistaAtributoSubgrupo = idlistaAtributoSubgrupo;
    }

    public Integer getIdlistaAtributoSubgrupo() {
        return idlistaAtributoSubgrupo;
    }

    public void setIdlistaAtributoSubgrupo(Integer idlistaAtributoSubgrupo) {
        this.idlistaAtributoSubgrupo = idlistaAtributoSubgrupo;
    }

    public Subgrupo getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Subgrupo idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
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
        hash += (idlistaAtributoSubgrupo != null ? idlistaAtributoSubgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaAtributoSubgrupo)) {
            return false;
        }
        ListaAtributoSubgrupo other = (ListaAtributoSubgrupo) object;
        if ((this.idlistaAtributoSubgrupo == null && other.idlistaAtributoSubgrupo != null) || (this.idlistaAtributoSubgrupo != null && !this.idlistaAtributoSubgrupo.equals(other.idlistaAtributoSubgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ListaAtributoSubgrupo[ idlistaAtributoSubgrupo=" + idlistaAtributoSubgrupo + " ]";
    }
    
}
