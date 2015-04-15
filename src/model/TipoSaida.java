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
@Table(name = "tipo_saida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSaida.findAll", query = "SELECT t FROM TipoSaida t"),
    @NamedQuery(name = "TipoSaida.findByIdtipoSaida", query = "SELECT t FROM TipoSaida t WHERE t.idtipoSaida = :idtipoSaida"),
    @NamedQuery(name = "TipoSaida.findByDescricao", query = "SELECT t FROM TipoSaida t WHERE t.descricao = :descricao")})
public class TipoSaida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_saida")
    private Integer idtipoSaida;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoSaida")
    private List<SaidaProduto> saidaProdutoList;

    public TipoSaida() {
    }

    public TipoSaida(Integer idtipoSaida) {
        this.idtipoSaida = idtipoSaida;
    }

    public Integer getIdtipoSaida() {
        return idtipoSaida;
    }

    public void setIdtipoSaida(Integer idtipoSaida) {
        this.idtipoSaida = idtipoSaida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<SaidaProduto> getSaidaProdutoList() {
        return saidaProdutoList;
    }

    public void setSaidaProdutoList(List<SaidaProduto> saidaProdutoList) {
        this.saidaProdutoList = saidaProdutoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoSaida != null ? idtipoSaida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSaida)) {
            return false;
        }
        TipoSaida other = (TipoSaida) object;
        if ((this.idtipoSaida == null && other.idtipoSaida != null) || (this.idtipoSaida != null && !this.idtipoSaida.equals(other.idtipoSaida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoSaida[ idtipoSaida=" + idtipoSaida + " ]";
    }
    
}
