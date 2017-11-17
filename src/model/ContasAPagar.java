/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name = "contas_a_pagar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContasAPagar.findAll", query = "SELECT c FROM ContasAPagar c"),
    @NamedQuery(name = "ContasAPagar.findByIdcontasAPagar", query = "SELECT c FROM ContasAPagar c WHERE c.idcontasAPagar = :idcontasAPagar"),
    @NamedQuery(name = "ContasAPagar.findByDescricao", query = "SELECT c FROM ContasAPagar c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "ContasAPagar.findByDataVencimento", query = "SELECT c FROM ContasAPagar c WHERE c.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "ContasAPagar.findByDataPagamento", query = "SELECT c FROM ContasAPagar c WHERE c.dataPagamento = :dataPagamento")})
public class ContasAPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontas_a_pagar")
    private Integer idcontasAPagar;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @JoinColumn(name = "idsituacao_conta", referencedColumnName = "idsituacao_conta")
    @ManyToOne(optional = false)
    private SituacaoConta idsituacaoConta;
    @JoinColumn(name = "idcentro_custo", referencedColumnName = "idcentro_custo")
    @ManyToOne(optional = false)
    private CentroCusto idcentroCusto;

    public ContasAPagar() {
    }

    public ContasAPagar(Integer idcontasAPagar) {
        this.idcontasAPagar = idcontasAPagar;
    }

    public ContasAPagar(Integer idcontasAPagar, Date dataVencimento) {
        this.idcontasAPagar = idcontasAPagar;
        this.dataVencimento = dataVencimento;
    }

    public Integer getIdcontasAPagar() {
        return idcontasAPagar;
    }

    public void setIdcontasAPagar(Integer idcontasAPagar) {
        this.idcontasAPagar = idcontasAPagar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public SituacaoConta getIdsituacaoConta() {
        return idsituacaoConta;
    }

    public void setIdsituacaoConta(SituacaoConta idsituacaoConta) {
        this.idsituacaoConta = idsituacaoConta;
    }

    public CentroCusto getIdcentroCusto() {
        return idcentroCusto;
    }

    public void setIdcentroCusto(CentroCusto idcentroCusto) {
        this.idcentroCusto = idcentroCusto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontasAPagar != null ? idcontasAPagar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasAPagar)) {
            return false;
        }
        ContasAPagar other = (ContasAPagar) object;
        if ((this.idcontasAPagar == null && other.idcontasAPagar != null) || (this.idcontasAPagar != null && !this.idcontasAPagar.equals(other.idcontasAPagar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ContasAPagar[ idcontasAPagar=" + idcontasAPagar + " ]";
    }
    
}
