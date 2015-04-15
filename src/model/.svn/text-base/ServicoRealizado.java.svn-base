/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Produção
 */
@Entity
@Table(name = "servico_realizado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicoRealizado.findAll", query = "SELECT s FROM ServicoRealizado s"),
    @NamedQuery(name = "ServicoRealizado.findByIdservicoRealizado", query = "SELECT s FROM ServicoRealizado s WHERE s.idservicoRealizado = :idservicoRealizado"),
    @NamedQuery(name = "ServicoRealizado.findByValor", query = "SELECT s FROM ServicoRealizado s WHERE s.valor = :valor")})
public class ServicoRealizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico_realizado")
    private Integer idservicoRealizado;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idsituacao_servico", referencedColumnName = "idsituacao_servico")
    @ManyToOne(optional = false)
    private SituacaoServico idsituacaoServico;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false)
    private Servico idservico;
    @JoinColumn(name = "idordem_servico", referencedColumnName = "idordem_servico")
    @ManyToOne(optional = false)
    private OrdemServico idordemServico;

    public ServicoRealizado() {
    }

    public ServicoRealizado(Integer idservicoRealizado) {
        this.idservicoRealizado = idservicoRealizado;
    }

    public Integer getIdservicoRealizado() {
        return idservicoRealizado;
    }

    public void setIdservicoRealizado(Integer idservicoRealizado) {
        this.idservicoRealizado = idservicoRealizado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public SituacaoServico getIdsituacaoServico() {
        return idsituacaoServico;
    }

    public void setIdsituacaoServico(SituacaoServico idsituacaoServico) {
        this.idsituacaoServico = idsituacaoServico;
    }

    public Servico getIdservico() {
        return idservico;
    }

    public void setIdservico(Servico idservico) {
        this.idservico = idservico;
    }

    public OrdemServico getIdordemServico() {
        return idordemServico;
    }

    public void setIdordemServico(OrdemServico idordemServico) {
        this.idordemServico = idordemServico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicoRealizado != null ? idservicoRealizado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicoRealizado)) {
            return false;
        }
        ServicoRealizado other = (ServicoRealizado) object;
        if ((this.idservicoRealizado == null && other.idservicoRealizado != null) || (this.idservicoRealizado != null && !this.idservicoRealizado.equals(other.idservicoRealizado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ServicoRealizado[ idservicoRealizado=" + idservicoRealizado + " ]";
    }
    
}
