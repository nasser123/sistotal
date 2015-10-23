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
        this.setAlwaysOnTop(true);
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
        ConfigTelas ct = new ConfigTelas(this);
        ct.carregarConfig(this);
        this.ordemServico = os;

        preencheTodosDados();
        jLabelTitulo.setText("Ordem de Serviço N° " + os.getIdordemServico());
        bloqueiaEdicao();

        jButtonNovo.setEnabled(false);
        jButtonPesquisar.setEnabled(false);

    }

    private void preencheDadosCliente() {
        outroSistema = false;

    }

    private void configuraTextArea() {
        jTextAreaEfeitoCliente.setLineWrap(true);

    }

    private void preencheTodosDados() {
        jDateChooserAbertura.setDate(this.ordemServico.getDataAbertura());

        if (this.ordemServico.getPago()) {
            jComboBoxPago.setSelectedIndex(1);
        } else {
            jComboBoxPago.setSelectedIndex(0);
        }

        if (this.ordemServico.getDataTermino() != null) {
            jDateChooserEntrega.setDate(this.ordemServico.getDataTermino());
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
        jComboBoxPago.setEnabled(false);
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
        jComboBoxPago.setEnabled(true);
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
        ordemServico = this.osCad;
        equipamentoQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT e FROM Equipamento e");
        equipamentoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(equipamentoQuery.getResultList());
        situacaoOsQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT s FROM SituacaoOs s");
        situacaoOsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(situacaoOsQuery.getResultList());
        tipoServicoQuery = java.beans.Beans.isDesignTime() ? null : SistotalPUEntityManager.createQuery("SELECT t FROM TipoServico t");
        tipoServicoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : tipoServicoQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEfeitoCliente = new javax.swing.JTextArea();
        jDateChooserAbertura = new com.toedter.calendar.JDateChooser();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(870, 740));
        setPreferredSize(new java.awt.Dimension(852, 740));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(870, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(875, 721));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaEfeitoCliente.setColumns(20);
        jTextAreaEfeitoCliente.setLineWrap(true);
        jTextAreaEfeitoCliente.setRows(7);
        jTextAreaEfeitoCliente.setWrapStyleWord(true);
        jTextAreaEfeitoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Problemas apresentados:"));
        jTextAreaEfeitoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${efeitoCliente}"), jTextAreaEfeitoCliente, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTextAreaEfeitoCliente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 224, 407, 140));
        jPanel1.add(jDateChooserAbertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 585, 156, 25));

        jTextFieldProcessador.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${processador}"), jTextFieldProcessador, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldProcessador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProcessadorActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldProcessador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, 236, -1));

        jTextFieldMemoria.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${memoria}"), jTextFieldMemoria, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMemoriaActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 146, 130, -1));

        jTextFieldDiscoRigido.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${discoRigido}"), jTextFieldDiscoRigido, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextFieldDiscoRigido, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 146, 148, -1));

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, equipamentoList, jComboBoxEquipamento);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idequipamento}"), jComboBoxEquipamento, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jComboBoxEquipamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 190, 250, -1));

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, situacaoOsList, jComboBoxSituacaoOS);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idsituacaoOs}"), jComboBoxSituacaoOS, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBoxSituacaoOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSituacaoOSActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxSituacaoOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 585, 150, 25));

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Ordem de Serviço");
        jPanel1.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 30));

        jLabel3.setText("Data de Abertura:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 565, -1, -1));

        jTextFieldCodigo.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.idcliente}"), jTextFieldCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 70, 55, -1));

        jTextFieldNome.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.nome}"), jTextFieldNome, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 70, 246, -1));

        jLabel4.setText("Código");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 50, 55, -1));

        jLabel5.setText("Nome");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 52, -1, -1));

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/1408_16x16.png"))); // NOI18N
        jButtonPesquisar.setMnemonic('P');
        jButtonPesquisar.setText("Pesquisar (F2)");
        jButtonPesquisar.setToolTipText("");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 67, 130, -1));

        jLabel6.setText("Telefone");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 70, -1));

        jLabel7.setText("Celular");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 70, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 98, 870, -1));

        jLabel8.setText("Dados do Equipamento");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 106, -1, -1));

        jLabel9.setText("Processador");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 126, -1, -1));

        jLabel10.setText("Memória");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 126, -1, -1));

        jLabel11.setText("Disco Rígido");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 126, -1, -1));

        jLabel12.setText("Fonte");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 126, 70, -1));

        jTextFieldFonte.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${fonte}"), jTextFieldFonte, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue(" ");
        bindingGroup.addBinding(binding);

        jTextFieldFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFonteActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldFonte, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 146, 90, -1));

        jTextFieldPlacaMae.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${placaMae}"), jTextFieldPlacaMae, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldPlacaMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPlacaMaeActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldPlacaMae, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 146, 150, -1));

        jLabel13.setText("Placa Mãe:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 126, 111, -1));

        jLabel14.setText("Tipo de Equipamento:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 170, 140, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 218, 870, -1));

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setLineWrap(true);
        jTextAreaObservacoes.setRows(7);
        jTextAreaObservacoes.setWrapStyleWord(true);
        jTextAreaObservacoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jTextAreaObservacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${observacao}"), jTextAreaObservacoes, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(jTextAreaObservacoes);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 224, 407, 140));

        jLabel16.setText("Situação");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 565, 150, -1));

        jButtonGravar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/gravar32.png"))); // NOI18N
        jButtonGravar.setMnemonic('G');
        jButtonGravar.setText("Gravar");
        jButtonGravar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGravar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 620, 90, 80));

        jTextFieldMarca.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${marca}"), jTextFieldMarca, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextFieldMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 140, -1));

        jLabel17.setText("Marca/Modelo:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.fone}"), jTextFieldFone, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("!= null"), jTextFieldFone, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextFieldFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 80, -1));

        jTextFieldCelular.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idcliente.celular}"), jTextFieldCelular, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextFieldCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 70, -1));

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/add_user.png"))); // NOI18N
        jButtonNovo.setMnemonic('N');
        jButtonNovo.setText("Novo(F3)");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 100, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/8394_32x32.png"))); // NOI18N
        jButton1.setMnemonic('e');
        jButton1.setText("editar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 90, 80));

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/cancelar32.png"))); // NOI18N
        jButtonCancelar.setMnemonic('c');
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, 90, 80));

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/32x32/relatorio.png"))); // NOI18N
        jButtonImprimir.setMnemonic('I');
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 620, 90, 80));

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
        jPanel1.add(jButtonSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 620, 90, 80));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 373, 407, 130));

        jComboBoxSO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Windows XP", "Windows 7 32", "Windows 7 64", "Windows 8.1 32", "Windows 8.1 64", "Ubuntu", "Windows 10 32", "Windows 10 64", "Outro" }));
        jComboBoxSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSOActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 529, 127, 25));

        jLabel18.setText("Sistema Operacional:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 509, -1, -1));

        jLabel19.setText("Antivírus");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 509, 125, -1));

        jComboBoxAV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avast", "Norton", "Panda", "AVG", "McAfee", "Security Essentials" }));
        jPanel1.add(jComboBoxAV, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 529, 125, 25));

        jLabel20.setText("Suite de Escritorio");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 509, -1, -1));

        jComboBoxOffice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Office 2003", "Office 2007", "Office 2010", "Office 2013", "Office 365", "Br Office" }));
        jPanel1.add(jComboBoxOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 529, 165, 25));

        jTextFieldOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOutroActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldOutro, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 529, 188, 25));

        jLabelOutro.setText("Outro:");
        jPanel1.add(jLabelOutro, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 509, -1, -1));

        jTextAreaFeedBack.setColumns(20);
        jTextAreaFeedBack.setLineWrap(true);
        jTextAreaFeedBack.setRows(7);
        jTextAreaFeedBack.setWrapStyleWord(true);
        jTextAreaFeedBack.setBorder(javax.swing.BorderFactory.createTitledBorder("Feedback do cliente"));
        jTextAreaFeedBack.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${feedback}"), jTextAreaFeedBack, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane4.setViewportView(jTextAreaFeedBack);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 373, 407, 130));

        jLabel22.setText("Data de Entrega:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 565, -1, -1));
        jPanel1.add(jDateChooserEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 585, 156, 25));

        jButtonDetalhar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/edit_profile.png"))); // NOI18N
        jButtonDetalhar.setMnemonic('D');
        jButtonDetalhar.setText("Detalhar");
        jButtonDetalhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetalharActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDetalhar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 67, -1, -1));

        jLabel23.setText("Demais equipamentos");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jTextField_outro_hardware.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${outroHardware}"), jTextField_outro_hardware, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jTextField_outro_hardware, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 190, 400, -1));

        jLabel24.setText("Pago:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 565, 170, -1));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${valor}"), jTextFieldValor, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldValorFocusGained(evt);
            }
        });
        jPanel1.add(jTextFieldValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 585, 110, 25));

        jLabel25.setText("Valor Total:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 565, 110, -1));

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tipoServicoList, jComboBoxLocal);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ordemServico, org.jdesktop.beansbinding.ELProperty.create("${idtipoServico}"), jComboBoxLocal, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jComboBoxLocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 529, 180, 25));

        jLabel26.setText("Local:");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 509, 80, -1));

        jComboBoxPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));
        jPanel1.add(jComboBoxPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 590, 150, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Ordem de Serviço");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/1408_16x16.png"))); // NOI18N
        jMenuItem2.setText("Pesquisar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/16x16/add_user.png"))); // NOI18N
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

        setSize(new java.awt.Dimension(875, 764));
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
                if ("Nimbus".equals(info.getName())) {
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
    private javax.swing.JComboBox jComboBoxLocal;
    private javax.swing.JComboBox jComboBoxOffice;
    private javax.swing.JComboBox jComboBoxPago;
    private javax.swing.JComboBox jComboBoxSO;
    private javax.swing.JComboBox jComboBoxSituacaoOS;
    private com.toedter.calendar.JDateChooser jDateChooserAbertura;
    private com.toedter.calendar.JDateChooser jDateChooserEntrega;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
