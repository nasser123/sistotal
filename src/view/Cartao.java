/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Atendimento
 */
@Entity
@Table(name = "cartao", catalog = "sistotal", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cartao.findAll", query = "SELECT c FROM Cartao c"),
    @NamedQuery(name = "Cartao.findByIdcartao", query = "SELECT c FROM Cartao c WHERE c.idcartao = :idcartao"),
    @NamedQuery(name = "Cartao.findByDescricao", query = "SELECT c FROM Cartao c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Cartao.findByDesconto", query = "SELECT c FROM Cartao c WHERE c.desconto = :desconto"),
    @NamedQuery(name = "Cartao.findByPrazo", query = "SELECT c FROM Cartao c WHERE c.prazo = :prazo"),
    @NamedQuery(name = "Cartao.findByNrPrest", query = "SELECT c FROM Cartao c WHERE c.nrPrest = :nrPrest")})
public class Cartao implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcartao")
    private Integer idcartao;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Basic(optional = false)
    @Column(name = "prazo")
    private int prazo;
    @Column(name = "nr_prest")
    private Integer nrPrest;

    public Cartao() {
    }

    public Cartao(Integer idcartao) {
        this.idcartao = idcartao;
    }

    public Cartao(Integer idcartao, String descricao, BigDecimal desconto, int prazo) {
        this.idcartao = idcartao;
        this.descricao = descricao;
        this.desconto = desconto;
        this.prazo = prazo;
    }

    public Integer getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Integer idcartao) {
        Integer oldIdcartao = this.idcartao;
        this.idcartao = idcartao;
        changeSupport.firePropertyChange("idcartao", oldIdcartao, idcartao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        BigDecimal oldDesconto = this.desconto;
        this.desconto = desconto;
        changeSupport.firePropertyChange("desconto", oldDesconto, desconto);
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        int oldPrazo = this.prazo;
        this.prazo = prazo;
        changeSupport.firePropertyChange("prazo", oldPrazo, prazo);
    }

    public Integer getNrPrest() {
        return nrPrest;
    }

    public void setNrPrest(Integer nrPrest) {
        Integer oldNrPrest = this.nrPrest;
        this.nrPrest = nrPrest;
        changeSupport.firePropertyChange("nrPrest", oldNrPrest, nrPrest);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcartao != null ? idcartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.idcartao == null && other.idcartao != null) || (this.idcartao != null && !this.idcartao.equals(other.idcartao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "view.Cartao[ idcartao=" + idcartao + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
