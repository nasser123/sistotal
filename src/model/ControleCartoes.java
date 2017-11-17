/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nasser
 */
@Entity
@Table(name = "controle_cartoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControleCartoes.findAll", query = "SELECT c FROM ControleCartoes c"),
    @NamedQuery(name = "ControleCartoes.findByIdcontroleCartoes", query = "SELECT c FROM ControleCartoes c WHERE c.idcontroleCartoes = :idcontroleCartoes"),
    @NamedQuery(name = "ControleCartoes.findByNrPrestacao", query = "SELECT c FROM ControleCartoes c WHERE c.nrPrestacao = :nrPrestacao"),
    @NamedQuery(name = "ControleCartoes.findByDataVenda", query = "SELECT c FROM ControleCartoes c WHERE c.dataVenda = :dataVenda"),
    @NamedQuery(name = "ControleCartoes.findByValorTotal", query = "SELECT c FROM ControleCartoes c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "ControleCartoes.findByValorPrestacao", query = "SELECT c FROM ControleCartoes c WHERE c.valorPrestacao = :valorPrestacao"),
    @NamedQuery(name = "ControleCartoes.findByDataPrevista", query = "SELECT c FROM ControleCartoes c WHERE c.dataPrevista = :dataPrevista"),
    @NamedQuery(name = "ControleCartoes.findByDataEntrada", query = "SELECT c FROM ControleCartoes c WHERE c.dataEntrada = :dataEntrada")})
public class ControleCartoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrole_cartoes")
    private Integer idcontroleCartoes;
    @Basic(optional = false)
    @Column(name = "nr_prestacao")
    private int nrPrestacao;
    @Basic(optional = false)
    @Column(name = "data_venda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Basic(optional = false)
    @Column(name = "valor_prestacao")
    private BigDecimal valorPrestacao;
    @Basic(optional = false)
    @Column(name = "data_prevista")
    @Temporal(TemporalType.DATE)
    private Date dataPrevista;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @JoinColumn(name = "idcartao", referencedColumnName = "idcartao")
    @ManyToOne(optional = false)
    private Cartao idcartao;
    @JoinColumn(name = "idsituacao_conta", referencedColumnName = "idsituacao_conta")
    @ManyToOne(optional = false)
    private SituacaoConta idsituacaoConta;

    public ControleCartoes() {
    }

    public ControleCartoes(Integer idcontroleCartoes) {
        this.idcontroleCartoes = idcontroleCartoes;
    }

    public ControleCartoes(Integer idcontroleCartoes, int nrPrestacao, Date dataVenda, BigDecimal valorTotal, BigDecimal valorPrestacao, Date dataPrevista) {
        this.idcontroleCartoes = idcontroleCartoes;
        this.nrPrestacao = nrPrestacao;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.valorPrestacao = valorPrestacao;
        this.dataPrevista = dataPrevista;
    }

    public Integer getIdcontroleCartoes() {
        return idcontroleCartoes;
    }

    public void setIdcontroleCartoes(Integer idcontroleCartoes) {
        this.idcontroleCartoes = idcontroleCartoes;
    }

    public int getNrPrestacao() {
        return nrPrestacao;
    }

    public void setNrPrestacao(int nrPrestacao) {
        this.nrPrestacao = nrPrestacao;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorPrestacao() {
        return valorPrestacao;
    }

    public void setValorPrestacao(BigDecimal valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Cartao getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Cartao idcartao) {
        this.idcartao = idcartao;
    }

    public SituacaoConta getIdsituacaoConta() {
        return idsituacaoConta;
    }

    public void setIdsituacaoConta(SituacaoConta idsituacaoConta) {
        this.idsituacaoConta = idsituacaoConta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontroleCartoes != null ? idcontroleCartoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControleCartoes)) {
            return false;
        }
        ControleCartoes other = (ControleCartoes) object;
        if ((this.idcontroleCartoes == null && other.idcontroleCartoes != null) || (this.idcontroleCartoes != null && !this.idcontroleCartoes.equals(other.idcontroleCartoes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ControleCartoes[ idcontroleCartoes=" + idcontroleCartoes + " ]";
    }
    
}
