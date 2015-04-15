/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Atendimento
 */
@Entity
@Table(name = "tipo_servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoServico.findAll", query = "SELECT t FROM TipoServico t"),
    @NamedQuery(name = "TipoServico.findByIdtipoServico", query = "SELECT t FROM TipoServico t WHERE t.idtipoServico = :idtipoServico"),
    @NamedQuery(name = "TipoServico.findByDescricao", query = "SELECT t FROM TipoServico t WHERE t.descricao = :descricao")})
public class TipoServico implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipo_servico")
    private Integer idtipoServico;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoServico")
    private List<OrdemServico> ordemServicoList;

    public TipoServico() {
    }

    public TipoServico(Integer idtipoServico) {
        this.idtipoServico = idtipoServico;
    }

    public Integer getIdtipoServico() {
        return idtipoServico;
    }

    public void setIdtipoServico(Integer idtipoServico) {
        Integer oldIdtipoServico = this.idtipoServico;
        this.idtipoServico = idtipoServico;
        changeSupport.firePropertyChange("idtipoServico", oldIdtipoServico, idtipoServico);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    @XmlTransient
    public List<OrdemServico> getOrdemServicoList() {
        return ordemServicoList;
    }

    public void setOrdemServicoList(List<OrdemServico> ordemServicoList) {
        this.ordemServicoList = ordemServicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoServico != null ? idtipoServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServico)) {
            return false;
        }
        TipoServico other = (TipoServico) object;
        if ((this.idtipoServico == null && other.idtipoServico != null) || (this.idtipoServico != null && !this.idtipoServico.equals(other.idtipoServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "model.TipoServico[ idtipoServico=" + idtipoServico + " ]";
        return descricao;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
