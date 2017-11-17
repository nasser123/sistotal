/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nasser
 */
@Entity
@Table(name = "venda_cartao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendaCartao.findAll", query = "SELECT v FROM VendaCartao v"),
    @NamedQuery(name = "VendaCartao.findByIdvendaCartao", query = "SELECT v FROM VendaCartao v WHERE v.idvendaCartao = :idvendaCartao"),
    @NamedQuery(name = "VendaCartao.findByValor", query = "SELECT v FROM VendaCartao v WHERE v.valor = :valor")})
public class VendaCartao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idvenda_cartao")
    private Integer idvendaCartao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idcartao", referencedColumnName = "idcartao")
    @ManyToOne(optional = false)
    private Cartao idcartao;
    @JoinColumn(name = "idvenda", referencedColumnName = "idvenda")
    @ManyToOne(optional = false)
    private Venda idvenda;

    public VendaCartao() {
    }

    public VendaCartao(Integer idvendaCartao) {
        this.idvendaCartao = idvendaCartao;
    }

    public Integer getIdvendaCartao() {
        return idvendaCartao;
    }

    public void setIdvendaCartao(Integer idvendaCartao) {
        this.idvendaCartao = idvendaCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cartao getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Cartao idcartao) {
        this.idcartao = idcartao;
    }

    public Venda getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Venda idvenda) {
        this.idvenda = idvenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendaCartao != null ? idvendaCartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaCartao)) {
            return false;
        }
        VendaCartao other = (VendaCartao) object;
        if ((this.idvendaCartao == null && other.idvendaCartao != null) || (this.idvendaCartao != null && !this.idvendaCartao.equals(other.idvendaCartao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VendaCartao[ idvendaCartao=" + idvendaCartao + " ]";
    }
    
}
