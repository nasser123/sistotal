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
import java.math.BigInteger;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cliente.findByNumero", query = "SELECT c FROM Cliente c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cliente.findByComplemento", query = "SELECT c FROM Cliente c WHERE c.complemento = :complemento"),
    @NamedQuery(name = "Cliente.findByBairro", query = "SELECT c FROM Cliente c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Cliente.findByCep", query = "SELECT c FROM Cliente c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByCpfCnpj", query = "SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpfCnpj"),
    @NamedQuery(name = "Cliente.findByInscEstadual", query = "SELECT c FROM Cliente c WHERE c.inscEstadual = :inscEstadual"),
    @NamedQuery(name = "Cliente.findByFone", query = "SELECT c FROM Cliente c WHERE c.fone = :fone"),
    @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular")})
public class Cliente implements Serializable {

    @Column(name = "recebeu_brinde")
    private boolean recebeuBrinde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Questionario> questionarioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private List<EntradaPagamentos> entradaPagamentosList;

    @Column(name = "numero")
    private String numero;
    @Column(name = "fone")
    private BigInteger fone;
    @Column(name = "celular")
    private BigInteger celular;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "insc_municipal")
    private String inscMunicipal;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private List<Venda> vendaList;
    @OneToMany(mappedBy = "idcliente")
    private List<OrdemServico> ordemServicoList;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "nome")
    private String nome;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cep")
    private String cep;
    @Column(name = "email")
    private String email;
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    @Column(name = "insc_estadual")
    private String inscEstadual;
    
    @Column(name = "data_brinde")
    @Temporal(TemporalType.DATE)
    private Date data_brinde;
    
    
    
    @JoinColumn(name = "idsituacao_cliente", referencedColumnName = "idsituacao_cliente")
    @ManyToOne(optional = false)
    private SituacaoCliente idsituacaoCliente;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false)
    private Cidade idcidade;
    @Transient
    private BigDecimal totalServicos;
    @Transient
    private BigDecimal totalAberto;

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public Date getData_brinde() {
        return data_brinde;
    }

    public void setData_brinde(Date data_brinde) {
        this.data_brinde = data_brinde;
    }


    
    
    
    
    
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        Date oldDataCadastro = this.dataCadastro;
        this.dataCadastro = dataCadastro;
        if(this.dataCadastro == null){
            this.dataCadastro = Datas.getCurrentTime();
        }
        changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
    }

    public String getDataCadastroString() {
        if(dataCadastro == null){
            return "";
        }
        return Datas.getDate(dataCadastro);

    }

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        Integer oldIdcliente = this.idcliente;
        this.idcliente = idcliente;
        changeSupport.firePropertyChange("idcliente", oldIdcliente, idcliente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome.toUpperCase();
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public String getEndereco() {
        if (endereco == null) {
            endereco = "";
        }
        return endereco;
    }

    public void setEndereco(String endereco) {
        String oldEndereco = this.endereco;
        if (endereco == null) {
            this.endereco = "";
        } else {
            this.endereco = endereco.toUpperCase();
        }
        changeSupport.firePropertyChange("endereco", oldEndereco, endereco);
    }

    public String getComplemento() {
        if (complemento == null) {
            complemento = "";
        }
        return complemento;
    }

    public void setComplemento(String complemento) {
        String oldComplemento = this.complemento;
        if (complemento == null) {
            this.complemento = "";
        } else {
            this.complemento = complemento.toUpperCase();
        }
        changeSupport.firePropertyChange("complemento", oldComplemento, complemento);
    }

    public String getBairro() {
        if (bairro == null) {
            bairro = "";
        }
        return bairro;
    }

    public void setBairro(String bairro) {
        String oldBairro = this.bairro;
        if (bairro == null) {
            this.bairro = "";
        } else {
            this.bairro = bairro.toUpperCase();
        }
        changeSupport.firePropertyChange("bairro", oldBairro, bairro);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        String oldCep = this.cep;
        this.cep = cep;
        changeSupport.firePropertyChange("cep", oldCep, cep);
    }

    public String getEmail() {
        if (email == null) {
            email = "";
        }
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        if (email == null) {
            this.email = "";
        } else {
            this.email = email.toLowerCase();
        }
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getCpfCnpj() {
        if (cpfCnpj == null) {
            cpfCnpj = "";
        }
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        String oldCpfCnpj = this.cpfCnpj;
        this.cpfCnpj = cpfCnpj;
        changeSupport.firePropertyChange("cpfCnpj", oldCpfCnpj, cpfCnpj);
    }

    public String getInscEstadual() {
        if (inscEstadual == null)
            inscEstadual = "";
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        String oldInscEstadual = this.inscEstadual;
        this.inscEstadual = inscEstadual;
        changeSupport.firePropertyChange("inscEstadual", oldInscEstadual, inscEstadual);
    }

    public SituacaoCliente getIdsituacaoCliente() {
        return idsituacaoCliente;
    }

    public void setIdsituacaoCliente(SituacaoCliente idsituacaoCliente) {
        SituacaoCliente oldIdsituacaoCliente = this.idsituacaoCliente;
        this.idsituacaoCliente = idsituacaoCliente;
        changeSupport.firePropertyChange("idsituacaoCliente", oldIdsituacaoCliente, idsituacaoCliente);
    }

    public Cidade getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Cidade idcidade) {
        Cidade oldIdcidade = this.idcidade;
        this.idcidade = idcidade;
        changeSupport.firePropertyChange("idcidade", oldIdcidade, idcidade);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ idcliente=" + idcliente + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @XmlTransient
    public List<OrdemServico> getOrdemServicoList() {
        return ordemServicoList;
    }

    public void setOrdemServicoList(List<OrdemServico> ordemServicoList) {
        this.ordemServicoList = ordemServicoList;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    public String getNumero() {
        if (numero == null)
            numero = "";
        return numero;
    }

    public void setNumero(String numero) {
        if (numero != null) {
            this.numero = numero;
        } else {
            this.numero = "";
        }
    }

    public BigInteger getFone() {
        if (fone != null) {
            return fone;
        }
        fone = new BigInteger("0");
        return fone;
    }

    public void setFone(BigInteger fone) {
        BigInteger oldFone = this.fone;
        this.fone = fone;
        changeSupport.firePropertyChange("fone", oldFone, fone);
    }

    public void setFone(String fone) {
        BigInteger oldFone = this.fone;
        if (fone != null) {
            try {
                this.fone = new BigInteger(fone);
            } catch (java.lang.NumberFormatException nfe) {
                this.fone = oldFone;
            }
        } else {
            this.fone = new BigInteger("0");
        }

    }

    public BigInteger getCelular() {
        if (celular != null) {
            return celular;
        }
        celular  = new BigInteger("0");
        return celular;
    }

    public void setCelular(BigInteger celular) {
        BigInteger oldCelular = this.celular;
        if (celular != null) {
            try {
                this.celular = celular;
            } catch (java.lang.NumberFormatException nfe) {
                this.celular = oldCelular;
            }
        } else {
            this.celular = new BigInteger("0");
        }
    }

    public void setCelular(String celular) {
        if (celular != null) {
            try {
                this.celular = new BigInteger(celular);
            } catch (java.lang.NumberFormatException nfe) {
                this.celular = new BigInteger("0");
            }
        } else {
            this.celular = new BigInteger("0");
        }

    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        Date oldDataNascimento = this.dataNascimento;
        this.dataNascimento = dataNascimento;
        changeSupport.firePropertyChange("dataNascimento", oldDataNascimento, dataNascimento);
    }

    public String getInscMunicipal() {
        if(inscEstadual == null)
            inscMunicipal = "";
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        String oldInscMunicipal = this.inscMunicipal;
        this.inscMunicipal = inscMunicipal;
        changeSupport.firePropertyChange("inscMunicipal", oldInscMunicipal, inscMunicipal);
    }

    public BigDecimal getTotalAberto() {
        BigDecimal v = new BigDecimal("0.0");
        for (int i = 0; i < this.getOrdemServicoList().size(); i++) {
            if (!this.getOrdemServicoList().get(i).getPago()) {
                v = v.add(this.getOrdemServicoList().get(i).getValor());
            }
        }
        return v;
    }

    public BigDecimal getTotalServicos() {
        BigDecimal v = new BigDecimal("0.0");
        for (int i = 0; i < this.getOrdemServicoList().size(); i++) {
            v = v.add(this.getOrdemServicoList().get(i).getValor());
        }
        return v;
    }

    @XmlTransient
    public List<EntradaPagamentos> getEntradaPagamentosList() {
        return entradaPagamentosList;
    }

    public void setEntradaPagamentosList(List<EntradaPagamentos> entradaPagamentosList) {
        this.entradaPagamentosList = entradaPagamentosList;
    }

    public boolean getRecebeuBrinde() {
        return recebeuBrinde;
    }

    public void setRecebeuBrinde(boolean recebeuBrinde) {
        this.recebeuBrinde = recebeuBrinde;
    }

    @XmlTransient
    public List<Questionario> getQuestionarioList() {
        return questionarioList;
    }

    public void setQuestionarioList(List<Questionario> questionarioList) {
        this.questionarioList = questionarioList;
    }

}
