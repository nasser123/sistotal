/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produção
 */
@Entity
@Table(name = "subgrupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subgrupo.findAll", query = "SELECT s FROM Subgrupo s"),
    @NamedQuery(name = "Subgrupo.findByIdsubgrupo", query = "SELECT s FROM Subgrupo s WHERE s.idsubgrupo = :idsubgrupo"),
    @NamedQuery(name = "Subgrupo.findByDescricao", query = "SELECT s FROM Subgrupo s WHERE s.descricao = :descricao")})
public class Subgrupo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubgrupo")
    private List<ListaAtributoSubgrupo> listaAtributoSubgrupoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubgrupo")
    private Integer idsubgrupo;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubgrupo")
    private List<Produto> produtoList;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
    @ManyToOne(optional = false)
    private Grupo idgrupo;

    public Subgrupo() {
    }

    public Subgrupo(Integer idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public Integer getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Integer idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubgrupo != null ? idsubgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subgrupo)) {
            return false;
        }
        Subgrupo other = (Subgrupo) object;
        if ((this.idsubgrupo == null && other.idsubgrupo != null) || (this.idsubgrupo != null && !this.idsubgrupo.equals(other.idsubgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Subgrupo[ idsubgrupo=" + idsubgrupo + " ]";
    }

    @XmlTransient
    public List<ListaAtributoSubgrupo> getListaAtributoSubgrupoList() {
        return listaAtributoSubgrupoList;
    }

    public void setListaAtributoSubgrupoList(List<ListaAtributoSubgrupo> listaAtributoSubgrupoList) {
        this.listaAtributoSubgrupoList = listaAtributoSubgrupoList;
    }
    
}
