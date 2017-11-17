/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nasser
 */
@Entity
@Table(name = "serial_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SerialEntrada.findAll", query = "SELECT s FROM SerialEntrada s"),
    @NamedQuery(name = "SerialEntrada.findByIdserialEntrada", query = "SELECT s FROM SerialEntrada s WHERE s.idserialEntrada = :idserialEntrada"),
    @NamedQuery(name = "SerialEntrada.findBySerial", query = "SELECT s FROM SerialEntrada s WHERE s.serial = :serial")})
public class SerialEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idserial_entrada")
    private Integer idserialEntrada;
    @Column(name = "serial")
    private String serial;
    @ManyToMany(mappedBy = "serialEntradaList")
    private List<SaidaProduto> saidaProdutoList;
    @JoinColumn(name = "identrada_produto", referencedColumnName = "identrada_produto")
    @ManyToOne(optional = false)
    private EntradaProduto identradaProduto;

    public SerialEntrada() {
    }

    public SerialEntrada(Integer idserialEntrada) {
        this.idserialEntrada = idserialEntrada;
    }

    public Integer getIdserialEntrada() {
        return idserialEntrada;
    }

    public void setIdserialEntrada(Integer idserialEntrada) {
        this.idserialEntrada = idserialEntrada;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @XmlTransient
    public List<SaidaProduto> getSaidaProdutoList() {
        return saidaProdutoList;
    }

    public void setSaidaProdutoList(List<SaidaProduto> saidaProdutoList) {
        this.saidaProdutoList = saidaProdutoList;
    }

    public EntradaProduto getIdentradaProduto() {
        return identradaProduto;
    }

    public void setIdentradaProduto(EntradaProduto identradaProduto) {
        this.identradaProduto = identradaProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idserialEntrada != null ? idserialEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SerialEntrada)) {
            return false;
        }
        SerialEntrada other = (SerialEntrada) object;
        if ((this.idserialEntrada == null && other.idserialEntrada != null) || (this.idserialEntrada != null && !this.idserialEntrada.equals(other.idserialEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SerialEntrada[ idserialEntrada=" + idserialEntrada + " ]";
    }
    
}
