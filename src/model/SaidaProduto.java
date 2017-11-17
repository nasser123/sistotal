/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produção
 */
@Entity
@Table(name = "saida_produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaidaProduto.findAll", query = "SELECT s FROM SaidaProduto s"),
    @NamedQuery(name = "SaidaProduto.findByIdsaidaProduto", query = "SELECT s FROM SaidaProduto s WHERE s.idsaidaProduto = :idsaidaProduto"),
    @NamedQuery(name = "SaidaProduto.findByQtd", query = "SELECT s FROM SaidaProduto s WHERE s.qtd = :qtd"),
    @NamedQuery(name = "SaidaProduto.findByValorunitario", query = "SELECT s FROM SaidaProduto s WHERE s.valorunitario = :valorunitario"),
    @NamedQuery(name = "SaidaProduto.findByDataSaida", query = "SELECT s FROM SaidaProduto s WHERE s.dataSaida = :dataSaida"),
    @NamedQuery(name = "SaidaProduto.findByLucroBruto", query = "SELECT s FROM SaidaProduto s WHERE s.lucroBruto = :lucroBruto")})
public class SaidaProduto implements Serializable {

    @Column(name = "saida_produtocol")
    private String saidaProdutocol;
    @JoinTable(name = "saida_produto_has_serial_entrada", joinColumns = {
        @JoinColumn(name = "idsaida_produto", referencedColumnName = "idsaida_produto")}, inverseJoinColumns = {
        @JoinColumn(name = "idserial_entrada", referencedColumnName = "idserial_entrada")})
    @ManyToMany
    private List<SerialEntrada> serialEntradaList;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsaida_produto")
    private Integer idsaidaProduto;
    @Column(name = "qtd")
    private Integer qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorunitario")
    private BigDecimal valorunitario;
    @Column(name = "data_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;
    @Column(name = "lucro_bruto")
    private BigDecimal lucroBruto;
    @JoinColumn(name = "idordem_servico", referencedColumnName = "idordem_servico")
    @ManyToOne
    private OrdemServico idordemServico;
    @JoinColumn(name = "idvenda", referencedColumnName = "idvenda")
    @ManyToOne
    private Venda idvenda;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false)
    private Produto idproduto;
    @JoinColumn(name = "idtipo_saida", referencedColumnName = "idtipo_saida")
    @ManyToOne(optional = false)
    private TipoSaida idtipoSaida;

    public SaidaProduto() {
    }

    public SaidaProduto(Integer idsaidaProduto) {
        this.idsaidaProduto = idsaidaProduto;
    }

    public Integer getIdsaidaProduto() {
        return idsaidaProduto;
    }

    public void setIdsaidaProduto(Integer idsaidaProduto) {
        Integer oldIdsaidaProduto = this.idsaidaProduto;
        this.idsaidaProduto = idsaidaProduto;
        changeSupport.firePropertyChange("idsaidaProduto", oldIdsaidaProduto, idsaidaProduto);
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        Integer oldQtd = this.qtd;
        this.qtd = qtd;
        changeSupport.firePropertyChange("qtd", oldQtd, qtd);
    }

    public BigDecimal getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(BigDecimal valorunitario) {
        BigDecimal oldValorunitario = this.valorunitario;
        this.valorunitario = valorunitario;
        changeSupport.firePropertyChange("valorunitario", oldValorunitario, valorunitario);
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        Date oldDataSaida = this.dataSaida;
        this.dataSaida = dataSaida;
        changeSupport.firePropertyChange("dataSaida", oldDataSaida, dataSaida);
    }

    public BigDecimal getLucroBruto() {
        return lucroBruto;
    }

    public void setLucroBruto(BigDecimal lucroBruto) {
        BigDecimal oldLucroBruto = this.lucroBruto;
        this.lucroBruto = lucroBruto;
        changeSupport.firePropertyChange("lucroBruto", oldLucroBruto, lucroBruto);
    }

    public OrdemServico getIdordemServico() {
        return idordemServico;
    }

    public void setIdordemServico(OrdemServico idordemServico) {
        OrdemServico oldIdordemServico = this.idordemServico;
        this.idordemServico = idordemServico;
        changeSupport.firePropertyChange("idordemServico", oldIdordemServico, idordemServico);
    }

    public Venda getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Venda idvenda) {
        Venda oldIdvenda = this.idvenda;
        this.idvenda = idvenda;
        changeSupport.firePropertyChange("idvenda", oldIdvenda, idvenda);
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        Produto oldIdproduto = this.idproduto;
        this.idproduto = idproduto;
        changeSupport.firePropertyChange("idproduto", oldIdproduto, idproduto);
    }

    public TipoSaida getIdtipoSaida() {
        return idtipoSaida;
    }

    public void setIdtipoSaida(TipoSaida idtipoSaida) {
        TipoSaida oldIdtipoSaida = this.idtipoSaida;
        this.idtipoSaida = idtipoSaida;
        changeSupport.firePropertyChange("idtipoSaida", oldIdtipoSaida, idtipoSaida);
    }
    
    public Float getSubTotal(){
        Float subTotal = this.getValorunitario().floatValue() * this.getQtd();
        return subTotal;
    }
    
    public void setLucroBruto(BigDecimal valorAtual, BigDecimal valorVendido, int quantidade) {
        this.lucroBruto = valorVendido.subtract(valorAtual);
        this.lucroBruto = this.lucroBruto.multiply(BigDecimal.valueOf(quantidade));
    }
//    public Float getTotal(List<SaidaProduto> saidaProdutoList){
//        List<SaidaProduto> sp = new ArrayList<SaidaProduto>(); 
//        Float total = 0f;
//        for (int i = 0; i < sp.size(); i++){
//            total += sp.get(i).getSubTotal();
//        }
//        return total;
//    
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsaidaProduto != null ? idsaidaProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaidaProduto)) {
            return false;
        }
        SaidaProduto other = (SaidaProduto) object;
        if ((this.idsaidaProduto == null && other.idsaidaProduto != null) || (this.idsaidaProduto != null && !this.idsaidaProduto.equals(other.idsaidaProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SaidaProduto[ idsaidaProduto=" + idsaidaProduto + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public String getSaidaProdutocol() {
        return saidaProdutocol;
    }

    public void setSaidaProdutocol(String saidaProdutocol) {
        this.saidaProdutocol = saidaProdutocol;
    }

    @XmlTransient
    public List<SerialEntrada> getSerialEntradaList() {
        return serialEntradaList;
    }

    public void setSerialEntradaList(List<SerialEntrada> serialEntradaList) {
        this.serialEntradaList = serialEntradaList;
    }
    
}
