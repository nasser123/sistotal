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
@Table(name = "situacao_servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacaoServico.findAll", query = "SELECT s FROM SituacaoServico s"),
    @NamedQuery(name = "SituacaoServico.findByIdsituacaoServico", query = "SELECT s FROM SituacaoServico s WHERE s.idsituacaoServico = :idsituacaoServico"),
    @NamedQuery(name = "SituacaoServico.findByDescricao", query = "SELECT s FROM SituacaoServico s WHERE s.descricao = :descricao")})
public class SituacaoServico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsituacao_servico")
    private Integer idsituacaoServico;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsituacaoServico")
    private List<ServicoRealizado> servicoRealizadoList;

    public SituacaoServico() {
    }

    public SituacaoServico(Integer idsituacaoServico) {
        this.idsituacaoServico = idsituacaoServico;
    }

    public Integer getIdsituacaoServico() {
        return idsituacaoServico;
    }

    public void setIdsituacaoServico(Integer idsituacaoServico) {
        this.idsituacaoServico = idsituacaoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<ServicoRealizado> getServicoRealizadoList() {
        return servicoRealizadoList;
    }

    public void setServicoRealizadoList(List<ServicoRealizado> servicoRealizadoList) {
        this.servicoRealizadoList = servicoRealizadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsituacaoServico != null ? idsituacaoServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoServico)) {
            return false;
        }
        SituacaoServico other = (SituacaoServico) object;
        if ((this.idsituacaoServico == null && other.idsituacaoServico != null) || (this.idsituacaoServico != null && !this.idsituacaoServico.equals(other.idsituacaoServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SituacaoServico[ idsituacaoServico=" + idsituacaoServico + " ]";
    }
    
}
