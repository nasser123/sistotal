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
@Table(name = "entrada_pagamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaPagamentos.findAll", query = "SELECT e FROM EntradaPagamentos e"),
    @NamedQuery(name = "EntradaPagamentos.findByIdentradaPagamentos", query = "SELECT e FROM EntradaPagamentos e WHERE e.identradaPagamentos = :identradaPagamentos"),
    @NamedQuery(name = "EntradaPagamentos.findByDataEntrada", query = "SELECT e FROM EntradaPagamentos e WHERE e.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "EntradaPagamentos.findByValor", query = "SELECT e FROM EntradaPagamentos e WHERE e.valor = :valor")})
public class EntradaPagamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identrada_pagamentos")
    private Integer identradaPagamentos;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;

    public EntradaPagamentos() {
    }

    public EntradaPagamentos(Integer identradaPagamentos) {
        this.identradaPagamentos = identradaPagamentos;
    }

    public Integer getIdentradaPagamentos() {
        return identradaPagamentos;
    }

    public void setIdentradaPagamentos(Integer identradaPagamentos) {
        this.identradaPagamentos = identradaPagamentos;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identradaPagamentos != null ? identradaPagamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaPagamentos)) {
            return false;
        }
        EntradaPagamentos other = (EntradaPagamentos) object;
        if ((this.identradaPagamentos == null && other.identradaPagamentos != null) || (this.identradaPagamentos != null && !this.identradaPagamentos.equals(other.identradaPagamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EntradaPagamentos[ identradaPagamentos=" + identradaPagamentos + " ]";
    }
    
}
