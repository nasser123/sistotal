/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Utilidades.Datas;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ordem_servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdemServico.findAll", query = "SELECT o FROM OrdemServico o"),
    @NamedQuery(name = "OrdemServico.findByIdordemServico", query = "SELECT o FROM OrdemServico o WHERE o.idordemServico = :idordemServico"),
    @NamedQuery(name = "OrdemServico.findByDataAbertura", query = "SELECT o FROM OrdemServico o WHERE o.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "OrdemServico.findByDataTermino", query = "SELECT o FROM OrdemServico o WHERE o.dataTermino = :dataTermino"),
    @NamedQuery(name = "OrdemServico.findByProcessador", query = "SELECT o FROM OrdemServico o WHERE o.processador = :processador"),
    @NamedQuery(name = "OrdemServico.findByMemoria", query = "SELECT o FROM OrdemServico o WHERE o.memoria = :memoria"),
    @NamedQuery(name = "OrdemServico.findByFonte", query = "SELECT o FROM OrdemServico o WHERE o.fonte = :fonte"),
    @NamedQuery(name = "OrdemServico.findByDiscoRigido", query = "SELECT o FROM OrdemServico o WHERE o.discoRigido = :discoRigido"),
    @NamedQuery(name = "OrdemServico.findByIdSituacaoOrdemServico", query = "SELECT o FROM OrdemServico o WHERE o.idsituacaoOs = :idsituacaoOs"),
    @NamedQuery(name = "OrdemServico.findByEmAbertoAndamento", query = "SELECT o FROM OrdemServico o WHERE o.idsituacaoOs = :situacao1 or o.idsituacaoOs = :situacao2"),
    @NamedQuery(name = "OrdemServico.findByFiltro", query = "SELECT o FROM OrdemServico o WHERE o.idsituacaoOs in (:parametroIn)"),
    @NamedQuery(name = "OrdemServico.findByMarca", query = "SELECT o FROM OrdemServico o WHERE o.marca = :marca")})
public class OrdemServico implements Serializable {

   

    @JoinColumn(name = "idtipo_servico", referencedColumnName = "idtipo_servico")
    @ManyToOne(optional = false)
    private TipoServico idtipoServico;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "pago")
    private Boolean pago;
    @Lob
    @Column(name = "outro_hardware")
    private String outroHardware;
    @Lob
    @Column(name = "descricao_servicos")
    private String descricaoServicos;
    @Column(name = "sistema_operacional")
    private String sistemaOperacional;
    @Column(name = "antivirus")
    private String antivirus;
    @Column(name = "office")
    private String office;
    @Column(name = "data_abertura")
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;
    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @OneToMany(mappedBy = "idordemServico")
    private List<SaidaProduto> saidaProdutoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idordemServico")
    private List<ServicoRealizado> servicoRealizadoList;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Column(name = "placa_mae")
    private String placaMae;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idordem_servico")
    private Integer idordemServico;
    @Lob
    @Column(name = "efeito_cliente")
    private String efeitoCliente;
    @Lob
    @Column(name = "feedback")
    private String feedback;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "processador")
    private String processador;
    @Column(name = "memoria")
    private String memoria;
    @Column(name = "fonte")
    private String fonte;
    @Column(name = "disco_rigido")
    private String discoRigido;
    @Column(name = "marca")
    private String marca;
    @JoinColumn(name = "idsituacao_os", referencedColumnName = "idsituacao_os")
    @ManyToOne(optional = false)
    private SituacaoOs idsituacaoOs;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(optional = false)
    private Equipamento idequipamento;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    @Column(name = "responsavel")
    private String responsavel;
    @Column(name = "local")
    private String local;
    @Column(name = "data_agendamento")
    @Temporal(TemporalType.DATE)
    private Date data_agendamento;
    @Column(name = "hora_agendamento")
    @Temporal(TemporalType.TIME)
    private Date hora_agendamento;
    
    
    
    
    
    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(Date data_agendamento) {
        this.data_agendamento = data_agendamento;
    }

    public Date getHora_agendamento() {
        return hora_agendamento;
    }

    public void setHora_agendamento(Date hora_agendamento) {
        this.hora_agendamento = hora_agendamento;
    }
    
    
    
    public OrdemServico() {
    }

    public OrdemServico(Integer idordemServico) {
        this.idordemServico = idordemServico;
    }

    public Integer getIdordemServico() {
        return idordemServico;
    }

    public void setIdordemServico(Integer idordemServico) {
        Integer oldIdordemServico = this.idordemServico;
        this.idordemServico = idordemServico;
        changeSupport.firePropertyChange("idordemServico", oldIdordemServico, idordemServico);
    }

    public String getEfeitoCliente() {
        if (this.efeitoCliente == null) {
            this.efeitoCliente = "";
            return this.efeitoCliente;
        } else {
            return efeitoCliente;
        }
    }

    public void setEfeitoCliente(String efeitoCliente) {
        String oldEfeitoCliente = this.efeitoCliente;
        if (efeitoCliente == null) {
            this.efeitoCliente = "";
        } else {
            this.efeitoCliente = efeitoCliente;
        }
        changeSupport.firePropertyChange("efeitoCliente", oldEfeitoCliente, efeitoCliente);
    }

    public String getFeedback() {
        if (this.feedback == null) {
            this.feedback = "";
            return this.feedback;
        } else {
            return feedback;
        }
    }

    public void setFeedback(String feedback) {
        String oldFeedback = this.feedback;
        this.feedback = feedback;
        changeSupport.firePropertyChange("feedback", oldFeedback, feedback);
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        Date oldDataAbertura = this.dataAbertura;
        this.dataAbertura = dataAbertura;
        changeSupport.firePropertyChange("dataAbertura", oldDataAbertura, dataAbertura);
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        Date oldDataTermino = this.dataTermino;
        this.dataTermino = dataTermino;
        if ((dataTermino == null) && this.getIdsituacaoOs().getDescricao().equals("Entregue")) {
            this.dataTermino = Datas.getCurrentTime();
        }
        changeSupport.firePropertyChange("dataTermino", oldDataTermino, dataTermino);
    }

    public String getObservacao() {
        if (this.observacao == null) {
            this.observacao = "";
            return this.observacao;
        } else {
            return observacao;
        }
    }

    public void setObservacao(String observacao) {

        String oldObservacao = this.observacao;
        if (observacao == null) {
            this.observacao = "";
        } else {
            this.observacao = observacao;
        }
        changeSupport.firePropertyChange("observacao", oldObservacao, observacao);
    }

    public String getProcessador() {
        if (this.processador == null) {
            this.processador = "";
            return this.processador;
        } else {
            return processador;
        }
    }

    public void setProcessador(String processador) {
        String oldProcessador = this.processador;
        if (processador == null) {
            this.processador = " ";
        } else {
            this.processador = processador.toUpperCase();
        }

        changeSupport.firePropertyChange("processador", oldProcessador, processador);
    }

    public String getMemoria() {
        if (this.memoria == null) {
            this.memoria = "";
            return this.memoria;
        } else {
            return memoria;
        }
    }

    public void setMemoria(String memoria) {
        String oldMemoria = this.memoria;
        if (memoria == null) {
            this.memoria = " ";
        } else {
            this.memoria = memoria.toUpperCase();
        }

        changeSupport.firePropertyChange("memoria", oldMemoria, memoria);
    }

    public String getFonte() {
        if (this.fonte == null) {
            this.fonte = "";
            return this.fonte;
        } else {
            return fonte;
        }
    }

    public void setFonte(String fonte) {
        String oldFonte = this.fonte;
        if (fonte == null) {
            this.fonte = "";
        } else {
            this.fonte = fonte.toUpperCase();
        }

        changeSupport.firePropertyChange("fonte", oldFonte, fonte);
    }

    public String getDiscoRigido() {
        if (this.discoRigido == null) {
            this.discoRigido = "";
            return this.discoRigido;
        } else {
            return discoRigido;
        }
    }

    public void setDiscoRigido(String discoRigido) {
        String oldDiscoRigido = this.discoRigido;
        if (discoRigido == null) {
            this.discoRigido = "";
        } else {
            this.discoRigido = discoRigido.toUpperCase();
        }
        changeSupport.firePropertyChange("discoRigido", oldDiscoRigido, discoRigido);
    }

    public String getMarca() {
        if (this.marca == null) {
            this.marca = "";
            return this.marca;
        } else {
            return marca;
        }
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        if (marca == null) {
            this.marca = "";
        } else {
            this.marca = marca.toUpperCase();
        }
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public SituacaoOs getIdsituacaoOs() {
        return idsituacaoOs;
    }

    public void setIdsituacaoOs(SituacaoOs idsituacaoOs) {
        SituacaoOs oldIdsituacaoOs = this.idsituacaoOs;
        this.idsituacaoOs = idsituacaoOs;
        changeSupport.firePropertyChange("idsituacaoOs", oldIdsituacaoOs, idsituacaoOs);
    }

    public Equipamento getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(Equipamento idequipamento) {
        Equipamento oldIdequipamento = this.idequipamento;
        this.idequipamento = idequipamento;
        changeSupport.firePropertyChange("idequipamento", oldIdequipamento, idequipamento);
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        Cliente oldIdcliente = this.idcliente;
        this.idcliente = idcliente;
        changeSupport.firePropertyChange("idcliente", oldIdcliente, idcliente);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordemServico != null ? idordemServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdemServico)) {
            return false;
        }
        OrdemServico other = (OrdemServico) object;
        if ((this.idordemServico == null && other.idordemServico != null) || (this.idordemServico != null && !this.idordemServico.equals(other.idordemServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrdemServico[ idordemServico=" + idordemServico + " ]";
    }

    public String getPlacaMae() {
        if (this.placaMae == null) {
            this.placaMae = "";
            return this.placaMae;
        } else {
            return placaMae;
        }
    }

    public void setPlacaMae(String placaMae) {
        String oldPlacaMae = this.placaMae;
        if (placaMae == null) {
            this.placaMae = "";
        } else {
            this.placaMae = placaMae.toUpperCase();
        }
        changeSupport.firePropertyChange("placaMae", oldPlacaMae, placaMae);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @XmlTransient
    public List<ServicoRealizado> getServicoRealizadoList() {
        return servicoRealizadoList;
    }

    public void setServicoRealizadoList(List<ServicoRealizado> servicoRealizadoList) {
        this.servicoRealizadoList = servicoRealizadoList;
    }

    @XmlTransient
    public List<SaidaProduto> getSaidaProdutoList() {
        return saidaProdutoList;
    }

    public void setSaidaProdutoList(List<SaidaProduto> saidaProdutoList) {
        this.saidaProdutoList = saidaProdutoList;
    }

    public String getDescricaoServicos() {
        if (this.descricaoServicos == null) {
            this.descricaoServicos = "";
            return this.descricaoServicos;
        } else {
            return descricaoServicos;
        }
    }

    public void setDescricaoServicos(String descricaoServicos) {
        String oldDescricaoServicos = this.descricaoServicos;
        this.descricaoServicos = descricaoServicos;
        changeSupport.firePropertyChange("descricaoServicos", oldDescricaoServicos, descricaoServicos);
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        String oldSistemaOperacional = this.sistemaOperacional;
        this.sistemaOperacional = sistemaOperacional;
        changeSupport.firePropertyChange("sistemaOperacional", oldSistemaOperacional, sistemaOperacional);
    }

    public String getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(String antivirus) {
        String oldAntivirus = this.antivirus;
        this.antivirus = antivirus;
        changeSupport.firePropertyChange("antivirus", oldAntivirus, antivirus);
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        String oldOffice = this.office;
        this.office = office;
        changeSupport.firePropertyChange("office", oldOffice, office);
    }

    public BigDecimal getValor() {
        if (valor != null) {
            return valor;
        }
        BigDecimal v = new BigDecimal("0.0");
        return v;
    }

    public void setValor(BigDecimal valor) {
        BigDecimal oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public void setValor(String valor) {
        BigDecimal oldValor = this.valor;
        if (valor != null) {
            try {
                this.valor = new BigDecimal(valor);
            } catch (java.lang.NumberFormatException nfe) {
                this.valor = oldValor;
            }
        } else {
            this.valor = new BigDecimal("0");
        }

    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        Boolean oldPago = this.pago;
        this.pago = pago;
        changeSupport.firePropertyChange("pago", oldPago, pago);
    }

    public String getOutroHardware() {
        if (this.outroHardware == null) {
            this.outroHardware = "";
            return this.outroHardware;
        } else {
            return outroHardware;
        }
    }

    public void setOutroHardware(String outroHardware) {
        this.outroHardware = outroHardware.toUpperCase();
    }

    public TipoServico getIdtipoServico() {
        return idtipoServico;
    }

    public void setIdtipoServico(TipoServico idtipoServico) {
        TipoServico oldIdtipoServico = this.idtipoServico;
        this.idtipoServico = idtipoServico;
        changeSupport.firePropertyChange("idtipoServico", oldIdtipoServico, idtipoServico);
    }

    
}
