/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produção
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIdvenda", query = "SELECT v FROM Venda v WHERE v.idvenda = :idvenda"),
    @NamedQuery(name = "Venda.findByData", query = "SELECT v FROM Venda v WHERE v.data = :data"),
    @NamedQuery(name = "Venda.findByValortotal", query = "SELECT v FROM Venda v WHERE v.valortotal = :valortotal"),
    @NamedQuery(name = "Venda.findByDinheiro", query = "SELECT v FROM Venda v WHERE v.dinheiro = :dinheiro"),
    @NamedQuery(name = "Venda.findByCartao", query = "SELECT v FROM Venda v WHERE v.cartao = :cartao"),
    @NamedQuery(name = "Venda.findByCheque", query = "SELECT v FROM Venda v WHERE v.cheque = :cheque"),
    @NamedQuery(name = "Venda.findByPrazo", query = "SELECT v FROM Venda v WHERE v.prazo = :prazo")})
public class Venda implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvenda")
    private Integer idvenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valortotal")
    private Float valortotal;
    @Column(name = "dinheiro")
    private Float dinheiro;
    @Column(name = "cartao")
    private Float cartao;
    @Column(name = "cheque")
    private Float cheque;
    @Column(name = "prazo")
    private Float prazo;
    @OneToMany(mappedBy = "idvenda")
    private List<SaidaProduto> saidaProdutoList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;

    public Venda() {
    }

    public Venda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Integer getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Integer idvenda) {
        Integer oldIdvenda = this.idvenda;
        this.idvenda = idvenda;
        changeSupport.firePropertyChange("idvenda", oldIdvenda, idvenda);
    }

    public Float getValorTotal() {
        return valortotal;
    }

    public void setValorTotal(Float valortotal) {
        this.valortotal = valortotal;
    }

    public Float getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Float dinheiro) {
        Float oldDinheiro = this.dinheiro;
        this.dinheiro = dinheiro;
        changeSupport.firePropertyChange("dinheiro", oldDinheiro, dinheiro);
    }

    public Float getCartao() {
        return cartao;
    }

    public void setCartao(Float cartao) {
        Float oldCartao = this.cartao;
        this.cartao = cartao;
        changeSupport.firePropertyChange("cartao", oldCartao, cartao);
    }

    public Float getCheque() {
        return cheque;
    }

    public void setCheque(Float cheque) {
        Float oldCheque = this.cheque;
        this.cheque = cheque;
        changeSupport.firePropertyChange("cheque", oldCheque, cheque);
    }

    public Float getPrazo() {
        return prazo;
    }

    public void setPrazo(Float prazo) {
        Float oldPrazo = this.prazo;
        this.prazo = prazo;
        changeSupport.firePropertyChange("prazo", oldPrazo, prazo);
    }

    public boolean verificaPagamento() {
        
        if (this.getValorTotal().equals(this.getTotalPago())) {
            return true;
        } else {
            return false;
        }

    }
    
    public Float getTotalPago(){
        Float total = 0f;
        total = this.getDinheiro()+ this.getCartao() + this.getCheque() + this.getPrazo();
        return total;
    }
    
    public Float getPendente(){
        return getValorTotal() - getTotalPago();
    }

    @XmlTransient
    public List<SaidaProduto> getSaidaProdutoList() {
        return saidaProdutoList;
    }

    public void setSaidaProdutoList(List<SaidaProduto> saidaProdutoList) {
        this.saidaProdutoList = saidaProdutoList;
    }

    public Cliente getIdcliente() {
        return this.idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        Cliente oldIdcliente = this.idcliente;
        this.idcliente = idcliente;
        changeSupport.firePropertyChange("idcliente", oldIdcliente, idcliente);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvenda != null ? idvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idvenda == null && other.idvenda != null) || (this.idvenda != null && !this.idvenda.equals(other.idvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Venda[ idvenda=" + idvenda + " ]";
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
