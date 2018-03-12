/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "questionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questionario.findAll", query = "SELECT q FROM Questionario q"),
    @NamedQuery(name = "Questionario.findByIdquestionario", query = "SELECT q FROM Questionario q WHERE q.idquestionario = :idquestionario"),
    @NamedQuery(name = "Questionario.findByQtdComp", query = "SELECT q FROM Questionario q WHERE q.qtdComp = :qtdComp"),
    @NamedQuery(name = "Questionario.findByConhece", query = "SELECT q FROM Questionario q WHERE q.conhece = :conhece"),
    @NamedQuery(name = "Questionario.findByAvaliacao", query = "SELECT q FROM Questionario q WHERE q.avaliacao = :avaliacao")})
public class Questionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquestionario")
    private Integer idquestionario;
    @Column(name = "qtd_comp")
    private Integer qtdComp;
    @Lob
    @Column(name = "consideracao")
    private String consideracao;
    @Column(name = "conhece")
    private boolean conhece;
    @Lob
    @Column(name = "tipo_contato")
    private String tipoContato;
    @Column(name = "avaliacao")
    private Integer avaliacao;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Questionario() {
    }

    public Questionario(Integer idquestionario) {
        this.idquestionario = idquestionario;
    }

    public Integer getIdquestionario() {
        return idquestionario;
    }

    public void setIdquestionario(Integer idquestionario) {
        this.idquestionario = idquestionario;
    }

    public Integer getQtdComp() {
        return qtdComp;
    }

    public void setQtdComp(Integer qtdComp) {
        this.qtdComp = qtdComp;
    }

    public String getConsideracao() {
        return consideracao;
    }

    public void setConsideracao(String consideracao) {
        this.consideracao = consideracao;
    }

    public boolean getConhece() {
        return conhece;
    }

    public void setConhece(boolean conhece) {
        this.conhece = conhece;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquestionario != null ? idquestionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionario)) {
            return false;
        }
        Questionario other = (Questionario) object;
        if ((this.idquestionario == null && other.idquestionario != null) || (this.idquestionario != null && !this.idquestionario.equals(other.idquestionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Questionario[ idquestionario=" + idquestionario + " ]";
    }
    
}
