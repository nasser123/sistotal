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
@Table(name = "pdv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pdv.findAll", query = "SELECT p FROM Pdv p"),
    @NamedQuery(name = "Pdv.findByIdpdv", query = "SELECT p FROM Pdv p WHERE p.idpdv = :idpdv"),
    @NamedQuery(name = "Pdv.findByDataAbertura", query = "SELECT p FROM Pdv p WHERE p.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "Pdv.findByDataFechamento", query = "SELECT p FROM Pdv p WHERE p.dataFechamento = :dataFechamento"),
    @NamedQuery(name = "Pdv.findByValorInicial", query = "SELECT p FROM Pdv p WHERE p.valorInicial = :valorInicial"),
    @NamedQuery(name = "Pdv.findByValorDinheiro", query = "SELECT p FROM Pdv p WHERE p.valorDinheiro = :valorDinheiro"),
    @NamedQuery(name = "Pdv.findByValorCartao", query = "SELECT p FROM Pdv p WHERE p.valorCartao = :valorCartao"),
    @NamedQuery(name = "Pdv.findByValorCheque", query = "SELECT p FROM Pdv p WHERE p.valorCheque = :valorCheque"),
    @NamedQuery(name = "Pdv.findByValorPrazo", query = "SELECT p FROM Pdv p WHERE p.valorPrazo = :valorPrazo"),
    @NamedQuery(name = "Pdv.findByValorPrazoEntrada", query = "SELECT p FROM Pdv p WHERE p.valorPrazoEntrada = :valorPrazoEntrada")})
public class Pdv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpdv")
    private Integer idpdv;
    @Basic(optional = false)
    @Column(name = "data_abertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura;
    @Column(name = "data_fechamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_inicial")
    private BigDecimal valorInicial;
    @Basic(optional = false)
    @Column(name = "valor_dinheiro")
    private BigDecimal valorDinheiro;
    @Basic(optional = false)
    @Column(name = "valor_cartao")
    private BigDecimal valorCartao;
    @Basic(optional = false)
    @Column(name = "valor_cheque")
    private BigDecimal valorCheque;
    @Basic(optional = false)
    @Column(name = "valor_prazo")
    private BigDecimal valorPrazo;
    @Basic(optional = false)
    @Column(name = "valor_prazo_entrada")
    private BigDecimal valorPrazoEntrada;

    public Pdv() {
    }

    public Pdv(Integer idpdv) {
        this.idpdv = idpdv;
    }

    public Pdv(Integer idpdv, Date dataAbertura, BigDecimal valorInicial, BigDecimal valorDinheiro, BigDecimal valorCartao, BigDecimal valorCheque, BigDecimal valorPrazo, BigDecimal valorPrazoEntrada) {
        this.idpdv = idpdv;
        this.dataAbertura = dataAbertura;
        this.valorInicial = valorInicial;
        this.valorDinheiro = valorDinheiro;
        this.valorCartao = valorCartao;
        this.valorCheque = valorCheque;
        this.valorPrazo = valorPrazo;
        this.valorPrazoEntrada = valorPrazoEntrada;
    }

    public Integer getIdpdv() {
        return idpdv;
    }

    public void setIdpdv(Integer idpdv) {
        this.idpdv = idpdv;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorDinheiro() {
        return valorDinheiro;
    }

    public void setValorDinheiro(BigDecimal valorDinheiro) {
        this.valorDinheiro = valorDinheiro;
    }

    public BigDecimal getValorCartao() {
        return valorCartao;
    }

    public void setValorCartao(BigDecimal valorCartao) {
        this.valorCartao = valorCartao;
    }

    public BigDecimal getValorCheque() {
        return valorCheque;
    }

    public void setValorCheque(BigDecimal valorCheque) {
        this.valorCheque = valorCheque;
    }

    public BigDecimal getValorPrazo() {
        return valorPrazo;
    }

    public void setValorPrazo(BigDecimal valorPrazo) {
        this.valorPrazo = valorPrazo;
    }

    public BigDecimal getValorPrazoEntrada() {
        return valorPrazoEntrada;
    }

    public void setValorPrazoEntrada(BigDecimal valorPrazoEntrada) {
        this.valorPrazoEntrada = valorPrazoEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpdv != null ? idpdv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pdv)) {
            return false;
        }
        Pdv other = (Pdv) object;
        if ((this.idpdv == null && other.idpdv != null) || (this.idpdv != null && !this.idpdv.equals(other.idpdv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pdv[ idpdv=" + idpdv + " ]";
    }
    
}
