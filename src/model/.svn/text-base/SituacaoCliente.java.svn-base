/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
@Table(name = "situacao_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacaoCliente.findAll", query = "SELECT s FROM SituacaoCliente s"),
    @NamedQuery(name = "SituacaoCliente.findByIdsituacaoCliente", query = "SELECT s FROM SituacaoCliente s WHERE s.idsituacaoCliente = :idsituacaoCliente"),
    @NamedQuery(name = "SituacaoCliente.findByDescricao", query = "SELECT s FROM SituacaoCliente s WHERE s.descricao = :descricao")})
public class SituacaoCliente implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idsituacao_cliente")
    private Integer idsituacaoCliente;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsituacaoCliente")
    private List<Cliente> clienteList;

    public SituacaoCliente() {
    }

    public SituacaoCliente(Integer idsituacaoCliente) {
        this.idsituacaoCliente = idsituacaoCliente;
    }

    public Integer getIdsituacaoCliente() {
        return idsituacaoCliente;
    }

    public void setIdsituacaoCliente(Integer idsituacaoCliente) {
        Integer oldIdsituacaoCliente = this.idsituacaoCliente;
        this.idsituacaoCliente = idsituacaoCliente;
        changeSupport.firePropertyChange("idsituacaoCliente", oldIdsituacaoCliente, idsituacaoCliente);
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
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsituacaoCliente != null ? idsituacaoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoCliente)) {
            return false;
        }
        SituacaoCliente other = (SituacaoCliente) object;
        if ((this.idsituacaoCliente == null && other.idsituacaoCliente != null) || (this.idsituacaoCliente != null && !this.idsituacaoCliente.equals(other.idsituacaoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       // return "model.SituacaoCliente[ idsituacaoCliente=" + idsituacaoCliente + " ]";
        return descricao;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
