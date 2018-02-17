/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Utilidades.ConfigTelas;
import Utilidades.ConnectionFactory;
import Utilidades.Datas;
import controller.OrdemServicoController;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import relatorios.ExecutaRelatorio;

/**
 *
 * @author Produção
 */
public class TelaCadastroOrdemServico extends javax.swing.JFrame {

    /**
     * Creates new form OrdemServicoCadastro
     */
    Cliente cliente;
    OrdemServico osCad;
    boolean outroSistema;
    boolean mensagem = true;

    public TelaCadastroOrdemServico() {
        this.osCad = new OrdemServico();
        initComponents();
        ConfigTelas ct = new ConfigTelas(this);
        ct.carregarConfig(this);
        jDateChooserAbertura.setDate(Datas.getCurrentTime());
        jLabelTitulo.setText("Ordem de Serviço");
        outroSistema = false;
        jComboBoxEquipamento.setSelectedIndex(0);
        jComboBoxSituacaoOS.setSelectedIndex(0);
        jComboBoxLocal.setSelectedIndex(0);
        //this.setAlwaysOnTop(true);
    }

    public TelaCadastroOrdemServico(Cliente c) {
        this.osCad = new OrdemServico();
        this.cliente = c;
        this.osCad.setIdcliente(this.cliente);
        initComponents();
        ConfigTelas ct = new ConfigTelas(this);
        ct.carregarConfig(this);
        jTextFieldCodigo.setText(this.cliente.getIdcliente().toString());
        jTextFieldNome.setText(this.cliente.getNome());
        jTextFieldCelular.setText(this.cliente.getCelular().toString());
        jTextFieldFone.setText(this.cliente.getFone().toString());
        jDateChooserAbertura.setDate(Datas.getCurrentTime());
        outroSistema = false;

        jComboBoxEquipamento.setSelectedIndex(0);
        jComboBoxSituacaoOS.setSelectedIndex(0);
        jComboBoxLocal.setSelectedIndex(0);

    }

    public TelaCadastroOrdemServico(OrdemServico os) {
        this.osCad = os;

        initComponents();
        SistotalPUEntityManager.refresh(this.osCad);
        SistotalPUEntityManager.refresh(this.osCad.getIdcliente());
        ConfigTelas ct = new ConfigTelas(this);
        ct.carregarConfig(this);
        this.ordemServico = os;

        preencheTodosDados();
        jLabelTitulo.setText("Ordem de Serviço N° " + os.getIdordemServico());
        //bloqueiaEdicao();

        jButtonNovo.setEnabled(false);
        jButtonPesquisar.setEnabled(false);

    }

    private void selecionaHora(Date hora) {
        Date dHora = hora;
        int iHora = 0;
        int iMin = 0;
        iHora = Datas.getHour(dHora);
        iMin = Datas.getMinute(dHora);

        for (int i = 0; i < jComboBoxHora.getItemCount(); i++) {
            Integer sHora = Integer.parseInt(jComboBoxHora.getItemAt(i).toString());
            if (sHora.equals(iHora)) {
                jComboBoxHora.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < jComboBoxMin.getItemCount(); i++) {
            Integer sMin = Integer.parseInt(jComboBoxMin.getItemAt(i).toString());
            if (sMin.equals(iMin)) {
                jComboBoxMin.setSelectedIndex(i);
                break;
            }
        }

    }

    private void preecheComboMinutos() {
//        Vector<String> minutos = new Vector();
//        for (Integer i = 0; i < 60; i++) {
//            jComboBoxMin.addItem(i);
//            i = i + 9;
//        }
    }

    private void preencheDadosCliente() {
        outroSistema = false;

    }

    private void configuraTextArea() {
        jTextAreaEfeitoCliente.setLineWrap(true);

    }

    private void preencheTodosDados() {
        jDateChooserAbertura.setDate(this.ordemServico.getDataAbertura());
        
        jDateChooserDataAgendamento.setDate(this.ordemServico.getData_agendamento());

        if(this.ordemServico.getHora_agendamento() != null){
            selecionaHora(this.ordemServico.getHora_agendamento());
        }
        
        
        if (this.ordemServico.getPago()) {
            jComboBoxPago.setSelectedIndex(1);
        } else {
            jComboBoxPago.setSelectedIndex(0);
        }

        if (this.ordemServico.getDataTermino() != null) {
            jDateChooserEntrega.setDate(this.ordemServico.getDataTermino());
        }
        
        if (this.ordemServico.getIdusuario() != null){
            jComboBoxUsuario.setSelectedItem(this.ordemServico.getIdusuario());
        }

        boolean encontrou = false;
        for (int i = 0; i < jComboBoxAV.getItemCount(); i++) {
            if (jComboBoxAV.getModel().getElementAt(i).toString().equals(this.ordemServico.getAntivirus().toString())) {
                jComboBoxAV.setSelectedIndex(i);
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            jComboBoxAV.setSelectedIndex(0);
        }

        encontrou = false;
        for (int i = 0; i < jComboBoxOffice.getItemCount(); i++) {
            if (jComboBoxOffice.getModel().getElementAt(i).toString().equals(this.ordemServico.getOffice().toString())) {
                jComboBoxOffice.setSelectedIndex(i);
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            jComboBoxOffice.setSelectedIndex(0);
        }

        encontrou = false;
        for (int i = 0; i < jComboBoxSO.getItemCount(); i++) {
            if (jComboBoxSO.getModel().getElementAt(i).toString().equals(this.ordemServico.getSistemaOperacional().toString())) {
                jComboBoxSO.setSelectedIndex(i);
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            jComboBoxSO.setSelectedIndex(jComboBoxSO.getItemCount() - 1);
            jTextFieldOutro.setText(this.ordemServico.getSistemaOperacional().toString());
        }

    }

    private void bloqueiaEdicao() {
        jTextFieldCodigo.setEnabled(false);
        jTextFieldNome.setEnabled(false);
        jTextFieldFone.setEnabled(false);
        jTextFieldCelular.setEnabled(false);
        jTextFieldProcessador.setEnabled(false);
        jTextFieldMemoria.setEnabled(false);
        jTextFieldDiscoRigido.setEnabled(false);;
        jTextFieldFonte.setEnabled(false);
        jTextFieldPlacaMae.setEnabled(false);
        jTextFieldMarca.setEnabled(false);
        jComboBoxEquipamento.setEnabled(false);
        jComboBoxSituacaoOS.setEnabled(false);
        jTextAreaEfeitoCliente.setEnabled(false);
        jTextAreaObservacoes.setEnabled(false);
        jDateChooserAbertura.setEnabled(false);
        jTextAreaServicosRealizados.setEnabled(false);
        jTextAreaFeedBack.setEnabled(false);
        jComboBoxAV.setEnabled(false);
        jComboBoxOffice.setEnabled(false);
        jComboBoxSO.setEnabled(false);
        jDateChooserEntrega.setEnabled(false);
        jTextFieldOutro.setEnabled(false);
        jButtonGravar.setEnabled(false);
        jTextField_outro_hardware.setEnabled(false);
//        jComboBoxPago.setEnabled(false);
        jTextFieldValor.setEnabled(false);
        jComboBoxLocal.setEnabled(false);

    }

    private void liberaEdicao() {
        jTextFieldProcessador.setEnabled(true);
        jTextFieldMemoria.setEnabled(true);
        jTextFieldDiscoRigido.setEnabled(true);;
        jTextFieldFonte.setEnabled(true);
        jTextFieldPlacaMae.setEnabled(true);
        jTextFieldMarca.setEnabled(true);
        jComboBoxEquipamento.setEnabled(true);
        jComboBoxSituacaoOS.setEnabled(true);
        jTextAreaEfeitoCliente.setEnabled(true);
        jTextAreaObservacoes.setEnabled(true);
        jTextAreaServicosRealizados.setEnabled(true);
        jTextAreaFeedBack.setEnabled(true);
        jComboBoxAV.setEnabled(true);
        jComboBoxOffice.setEnabled(true);
        jComboBoxSO.setEnabled(true);
        jButtonGravar.setEnabled(true);
        jTextField_outro_hardware.setEnabled(true);
//        jComboBoxPago.setEnabled(true);
        jTextFieldValor.setEnabled(true);
        jComboBoxLocal.setEnabled(true);
        jDateChooserEntrega.setEnabled(true);

        jButtonPesquisar.setEnabled(true);

//        if (jComboBoxSituacaoOS.getSelectedIndex() != -1) {
//            if (jComboBoxSituacaoOS.getSelectedItem().toString().equals("Entregue")) {
//                jDateChooserEntrega.setEnabled(true);
//                if(jDateChooserEntrega.getDate() == null){
//                    jDateChooserEntrega.setDate(Datas.getCurrentTime());
//                }
//                
//            } else {
//                jDateChooserEntrega.setEnabled(false);
//            }
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        SistotalPUEntityManager = ConnectionFactory.getEntityManager();
        equipamentoQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT e FROM Equipamento e");
        equipamentoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(equipamentoQuery.getResultList());
        situacaoOsQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT s FROM SituacaoOs s");
        situacaoOsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(situacaoOsQuery.getResultList());
        tipoServicoQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT t FROM TipoServico t");
        tipoServicoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : tipoServicoQuery.getResultList();
        ordemServico = this.osCad;
        usuarioQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(usuarioQuery.getResultList());
        usuarioListCellRenderer1 = new Renderizadores.UsuarioListCellRenderer();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEfeitoCliente = new javax.swing.JTextArea();
        jDateChooserDataAgendamento = new com.toedter.calendar.JDateChooser();
        jTextFieldProcessador = new javax.swing.JTextField();
        jTextFieldMemoria = new javax.swing.JTextField();
        jTextFieldDiscoRigido = new javax.swing.JTextField();
        jComboBoxEquipamento = new javax.swing.JComboBox();
        jComboBoxSituacaoOS = new javax.swing.JComboBox();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonPesquisar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldFonte = new javax.swing.JTextField();
        jTextFieldPlacaMae = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jButtonGravar = new javax.swing.JButton();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldFone = new javax.swing.JTextField();
        jTextFieldCelular = new javax.swing.JTextField();
        jButtonNovo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaServicosRealizados = new javax.swing.JTextArea();
        jComboBoxSO = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxAV = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxOffice = new javax.swing.JComboBox();
        jTextFieldOutro = new javax.swing.JTextField();
        jLabelOutro = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaFeedBack = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jDateChooserEntrega = new com.toedter.calendar.JDateChooser();
        jButtonDetalhar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField_outro_hardware = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxLocal = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxPago = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jDateChooserAbertura = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBoxMin = new javax.swing.JComboBox();
        jComboBoxHora = new javax.swing.JComboBox();
        jComboBoxUsuario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldArmazenamento = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        usuarioListCellRenderer1.setText("usuarioListCellRenderer1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMinimumSize(new java.awt.Dimension(870, 740));
        setPreferredSize(new java.awt.Dimension(852, 740));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(870, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(875, 721));

        jTextAreaEfeitoCliente.setColumns(20);
        jTextAreaEfeitoCliente.setLineWrap(true);
        jTextAreaEfeitoCliente.setRows(7);
        jTextAreaEfeitoCliente.setWrapStyleWord(true);
        jTextAreaEfeitoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Problemas apresentados:"));
        jTextAreaEfeitoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${efeitoCliente}"), jTextAreaEfeitoCliente, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTextAreaEfeitoCliente);

        jTextFieldProcessador.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${processador}"), jTextFieldProcessador, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldProcessador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProcessadorActionPerformed(evt);
            }
        });

        jTextFieldMemoria.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${memoria}"), jTextFieldMemoria, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMemoriaActionPerformed(evt);
            }
        });

        jTextFieldDiscoRigido.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${discoRigido}"), jTextFieldDiscoRigido, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, equipamentoList, jComboBoxEquipamento);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idequipamento}"), jComboBoxEquipamento, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, situacaoOsList, jComboBoxSituacaoOS);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idsituacaoOs}"), jComboBoxSituacaoOS, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBoxSituacaoOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSituacaoOSActionPerformed(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Ordem de Serviço");

        jLabel3.setText("Data de Abertura:");

        jTextFieldCodigo.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.idcliente}"), jTextFieldCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoActionPerformed(evt);
            }
        });

        jTextFieldNome.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.nome}"), jTextFieldNome, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel4.setText("Código");

        jLabel5.setText("Nome");

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/pesquisar.png"))); // NOI18N
        jButtonPesquisar.setMnemonic('P');
        jButtonPesquisar.setText("Pesquisar (F2)");
        jButtonPesquisar.setToolTipText("");
        jButtonPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefone");

        jLabel7.setText("Celular");

        jLabel8.setText("Dados do Equipamento");

        jLabel9.setText("Processador");

        jLabel10.setText("Memória");

        jLabel11.setText("Disco Rígido");

        jLabel12.setText("Fonte");

        jTextFieldFonte.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${fonte}"), jTextFieldFonte, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFonteActionPerformed(evt);
            }
        });

        jTextFieldPlacaMae.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${placaMae}"), jTextFieldPlacaMae, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldPlacaMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPlacaMaeActionPerformed(evt);
            }
        });

        jLabel13.setText("Placa Mãe:");

        jLabel14.setText("Tipo de Equipamento:");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setLineWrap(true);
        jTextAreaObservacoes.setRows(7);
        jTextAreaObservacoes.setWrapStyleWord(true);
        jTextAreaObservacoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jTextAreaObservacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${observacao}"), jTextAreaObservacoes, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(jTextAreaObservacoes);

        jLabel16.setText("Situação");

        jButtonGravar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/gravar32.png"))); // NOI18N
        jButtonGravar.setMnemonic('G');
        jButtonGravar.setText("Gravar");
        jButtonGravar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonGravar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGravar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarActionPerformed(evt);
            }
        });

        jTextFieldMarca.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${marca}"), jTextFieldMarca, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel17.setText("Marca/Modelo:");

        jTextFieldFone.setBackground(new java.awt.Color(240, 240, 240));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.fone}"), jTextFieldFone, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldCelular.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.celular}"), jTextFieldCelular, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/add_user.png"))); // NOI18N
        jButtonNovo.setMnemonic('N');
        jButtonNovo.setText("Novo(F3)");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/edit.png"))); // NOI18N
        jButton1.setMnemonic('e');
        jButton1.setText("editar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/cancelar32.png"))); // NOI18N
        jButtonCancelar.setMnemonic('c');
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/relatorio.png"))); // NOI18N
        jButtonImprimir.setMnemonic('I');
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/sair2.png"))); // NOI18N
        jButtonSair.setMnemonic('S');
        jButtonSair.setText("Sair");
        jButtonSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBar(null);

        jTextAreaServicosRealizados.setColumns(20);
        jTextAreaServicosRealizados.setLineWrap(true);
        jTextAreaServicosRealizados.setRows(7);
        jTextAreaServicosRealizados.setWrapStyleWord(true);
        jTextAreaServicosRealizados.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição dos serviços"));
        jTextAreaServicosRealizados.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${descricaoServicos}"), jTextAreaServicosRealizados, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(jTextAreaServicosRealizados);

        jComboBoxSO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Windows 10 64", "Windows 10 32", "Windows 8.1 32", "Windows 8.1 64", "Windows 7 32", "Windows 7 64", "Windows XP", "Ubuntu", "Outro" }));
        jComboBoxSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSOActionPerformed(evt);
            }
        });

        jLabel18.setText("Sistema Operacional:");

        jLabel19.setText("Antivírus");

        jComboBoxAV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avast", "Norton", "Panda", "AVG", "McAfee", "Security Essentials" }));

        jLabel20.setText("Suite de Escritorio");

        jComboBoxOffice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Office 2016", "Office 2013", "Office 2010", "Office 2007", "Office 2003", "Office 365", "Br Office" }));

        jTextFieldOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOutroActionPerformed(evt);
            }
        });

        jLabelOutro.setText("Outro:");

        jTextAreaFeedBack.setColumns(20);
        jTextAreaFeedBack.setLineWrap(true);
        jTextAreaFeedBack.setRows(7);
        jTextAreaFeedBack.setWrapStyleWord(true);
        jTextAreaFeedBack.setBorder(javax.swing.BorderFactory.createTitledBorder("Feedback do cliente"));
        jTextAreaFeedBack.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${feedback}"), jTextAreaFeedBack, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane4.setViewportView(jTextAreaFeedBack);

        jLabel22.setText("Data de Entrega:");

        jButtonDetalhar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/edit_profile.png"))); // NOI18N
        jButtonDetalhar.setMnemonic('D');
        jButtonDetalhar.setText("Detalhar");
        jButtonDetalhar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDetalhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetalharActionPerformed(evt);
            }
        });

        jLabel23.setText("Demais equipamentos");

        jTextField_outro_hardware.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${outroHardware}"), jTextField_outro_hardware, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel24.setText("Pago:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${valor}"), jTextFieldValor, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldValorFocusGained(evt);
            }
        });

        jLabel25.setText("Valor Total:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tipoServicoList, jComboBoxLocal);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idtipoServico}"), jComboBoxLocal, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBoxLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLocalActionPerformed(evt);
            }
        });

        jLabel26.setText("Local:");

        jComboBoxPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));

        jLabel15.setText("Técnico Responsável");

        jLabel27.setText("Data Agendamento");

        jLabel21.setText("Hora");

        jComboBoxMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));

        jComboBoxHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jComboBoxUsuario.setRenderer(usuarioListCellRenderer1);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, jComboBoxUsuario);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idusuario}"), jComboBoxUsuario, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jLabel1.setText("Armazenamento");

        jTextFieldArmazenamento.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${local}"), jTextFieldArmazenamento, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldArmazenamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldArmazenamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel9)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel10)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel11)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel23)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(133, 133, 133)
                                                .addComponent(jLabelOutro)
                                                .addGap(168, 168, 168)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(477, 477, 477)
                                        .addComponent(jLabel20))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jComboBoxSO, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jComboBoxAV, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel22)
                                .addGap(97, 97, 97)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jComboBoxSituacaoOS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jDateChooserAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jDateChooserEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jComboBoxPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180)
                                .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(252, 252, 252)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButtonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDetalhar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldDiscoRigido, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_outro_hardware, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jDateChooserDataAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jComboBoxHora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jComboBoxMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(110, 110, 110)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel21)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxUsuario, 0, 190, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldArmazenamento, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jButtonNovo))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jButtonPesquisar))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addGap(6, 6, 6)
                            .addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonDetalhar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(9, 9, 9)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel23)
                    .addComponent(jLabel17))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDiscoRigido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_outro_hardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel15)
                        .addComponent(jLabel1)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserDataAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldArmazenamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelOutro)
                                    .addComponent(jLabel19)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel3)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSituacaoOS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jComboBoxPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Ordem de Serviço");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setText("Pesquisar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem1.setText("Novo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        bindingGroup.bind();

        setSize(new java.awt.Dimension(1027, 764));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMemoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMemoriaActionPerformed

    private void jTextFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void pesquisaCliente() {
        TelaListaClientesJDialog tlc = new TelaListaClientesJDialog(this, true);
        tlc.setVisible(true);
        this.ordemServico.setIdcliente(tlc.getCliente());
        this.preencheDadosCliente();

    }

    private void jTextFieldPlacaMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPlacaMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPlacaMaeActionPerformed

    private void jButtonGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarActionPerformed
        this.ordemServico.setDataAbertura(jDateChooserAbertura.getDate());

        if (jComboBoxPago.getSelectedIndex() == 0) {
            this.ordemServico.setPago(false);
        } else {
            this.ordemServico.setPago(true);
        }
        if (jComboBoxSO.getSelectedIndex() < (jComboBoxSO.getItemCount() - 1)) {
            this.ordemServico.setSistemaOperacional(jComboBoxSO.getSelectedItem().toString());
        } else {
            this.ordemServico.setSistemaOperacional(jTextFieldOutro.getText());
        }
        this.ordemServico.setAntivirus(jComboBoxAV.getSelectedItem().toString());
        this.ordemServico.setOffice(jComboBoxOffice.getSelectedItem().toString());
        
        
        if (jDateChooserEntrega != null) {
            this.ordemServico.setDataTermino(jDateChooserEntrega.getDate());
        }
        
        if (jComboBoxUsuario.getSelectedIndex() != -1){
            this.ordemServico.setIdusuario((Usuario)jComboBoxUsuario.getSelectedItem());
        }
        
        if (jDateChooserDataAgendamento != null){
            this.ordemServico.setData_agendamento(jDateChooserDataAgendamento.getDate());
        }
        
        this.ordemServico.setHora_agendamento(Datas.getTimeDataBase((String) jComboBoxHora.getSelectedItem(), jComboBoxMin.getSelectedItem().toString(), "00"));
        

        OrdemServicoController osc = new OrdemServicoController();
        if (this.ordemServico.getIdordemServico() == null) {
            try {
                osc.inserir(ordemServico);

            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                osc.alterar(ordemServico, this.mensagem);
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        this.mensagem = true;

    }//GEN-LAST:event_jButtonGravarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
//        TelaCadastroCliente tcc = new TelaCadastroCliente(true);
//        tcc.setVisible(true);
//        this.dispose();
        novoCliente();

    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void novoCliente() {
        Cliente c = new Cliente();
        TelaDetalharCliente tdc = new TelaDetalharCliente(this, true, c, true, false, null);
        tdc.setVisible(true);
        this.ordemServico.setIdcliente(tdc.getCliente());

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        liberaEdicao();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (this.osCad != null) {
//            TelaListaOS tlo = new TelaListaOS();
//            tlo.setVisible(true);
            this.dispose();
        } else {
            this.dispose();

        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        this.mensagem = false;
        this.jButtonGravarActionPerformed(evt);

        if (this.osCad.getIdordemServico() != null) {
            String sistemaOperacional = jComboBoxSO.getModel().getSelectedItem().toString();
            String antiVirus = jComboBoxAV.getModel().getSelectedItem().toString();
            String office = jComboBoxOffice.getModel().getSelectedItem().toString();
            ExecutaRelatorio er = new ExecutaRelatorio();
            er.abrirRelatorioOS(this.osCad, sistemaOperacional, antiVirus, office);

        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.gc();
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextFieldOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOutroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOutroActionPerformed

    private void jComboBoxSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSOActionPerformed
        if (jComboBoxSO.getModel().getSelectedItem().toString().equals("Outro")) {
            jTextFieldOutro.setEnabled(true);
            jTextFieldOutro.setEditable(true);
            outroSistema = true;
        } else {
            jTextFieldOutro.setEditable(false);
            outroSistema = false;
        }
    }//GEN-LAST:event_jComboBoxSOActionPerformed

    private void jComboBoxSituacaoOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSituacaoOSActionPerformed
        if (jComboBoxSituacaoOS.getSelectedIndex() != -1) {
            if (jComboBoxSituacaoOS.getSelectedItem().toString().equals("Entregue") || jComboBoxSituacaoOS.getSelectedItem().toString().equals("Fechada")) {
                jDateChooserEntrega.setEnabled(true);
                jDateChooserEntrega.setDate(Datas.getCurrentTime());
            } else {
                jDateChooserEntrega.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jComboBoxSituacaoOSActionPerformed

    private void jButtonDetalharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalharActionPerformed
        if (this.cliente != null) {
            new TelaDetalharCliente(this, true, this.cliente, false, false, null).setVisible(true);
        } else {
            if (this.osCad != null) {
                new TelaDetalharCliente(this, true, this.osCad.getIdcliente(), false, false, null).setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonDetalharActionPerformed

    private void jTextFieldProcessadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProcessadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProcessadorActionPerformed

    private void jTextFieldFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFonteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFonteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        novoCliente();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTextFieldValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldValorFocusGained
        jTextFieldValor.selectAll();
    }//GEN-LAST:event_jTextFieldValorFocusGained

    private void jComboBoxLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLocalActionPerformed

        if (jComboBoxLocal.getSelectedItem() != null) {
            TipoServico ts = (TipoServico) jComboBoxLocal.getSelectedItem();

            if (ts.getDescricao().equals("Externo")) {
                jDateChooserDataAgendamento.setEnabled(true);
                jComboBoxHora.setEnabled(true);
                jComboBoxMin.setEnabled(true);

            } else {
                jDateChooserDataAgendamento.setEnabled(false);
                jComboBoxMin.setEnabled(false);
                jComboBoxMin.setEnabled(false);

            }
        }
    }//GEN-LAST:event_jComboBoxLocalActionPerformed

    private void jTextFieldArmazenamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldArmazenamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldArmazenamentoActionPerformed

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TelaCadastroOrdemServico().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SistotalPUEntityManager;
    private java.util.List<model.Equipamento> equipamentoList;
    private javax.persistence.Query equipamentoQuery;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDetalhar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox jComboBoxAV;
    private javax.swing.JComboBox jComboBoxEquipamento;
    private javax.swing.JComboBox jComboBoxHora;
    private javax.swing.JComboBox jComboBoxLocal;
    private javax.swing.JComboBox jComboBoxMin;
    private javax.swing.JComboBox jComboBoxOffice;
    private javax.swing.JComboBox jComboBoxPago;
    private javax.swing.JComboBox jComboBoxSO;
    private javax.swing.JComboBox jComboBoxSituacaoOS;
    private javax.swing.JComboBox<String> jComboBoxUsuario;
    private com.toedter.calendar.JDateChooser jDateChooserAbertura;
    private com.toedter.calendar.JDateChooser jDateChooserDataAgendamento;
    private com.toedter.calendar.JDateChooser jDateChooserEntrega;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelOutro;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextAreaEfeitoCliente;
    private javax.swing.JTextArea jTextAreaFeedBack;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextArea jTextAreaServicosRealizados;
    private javax.swing.JTextField jTextFieldArmazenamento;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldDiscoRigido;
    private javax.swing.JTextField jTextFieldFone;
    private javax.swing.JTextField jTextFieldFonte;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldMemoria;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldOutro;
    private javax.swing.JTextField jTextFieldPlacaMae;
    private javax.swing.JTextField jTextFieldProcessador;
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTextField jTextField_outro_hardware;
    private model.OrdemServico ordemServico;
    private java.util.List<model.SituacaoOs> situacaoOsList;
    private javax.persistence.Query situacaoOsQuery;
    private java.util.List<model.TipoServico> tipoServicoList;
    private javax.persistence.Query tipoServicoQuery;
    private java.util.List<model.Usuario> usuarioList;
    private Renderizadores.UsuarioListCellRenderer usuarioListCellRenderer1;
    private javax.persistence.Query usuarioQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
