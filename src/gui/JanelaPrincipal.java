/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import excecoes.FilaVaziaException;
import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import modelo.Atendimento;
import modelo.NivelTriagem;
import modelo.Paciente;
import servicos.GestorAtendimento;

/**
 *
 * @author Edson
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName());
    private GestorAtendimento gestorAtendimento;
    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        initComponents();
        TableColumnModel columnModel = tbPacientes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(330);
        columnModel.getColumn(2).setPreferredWidth(100);
        
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        
        columnModel.getColumn(0).setCellRenderer(centro);
        columnModel.getColumn(2).setCellRenderer(centro);
        
        JTableHeader cabecalho = tbPacientes.getTableHeader();
        cabecalho.setFont(new Font("SansSerif", Font.BOLD,14));
        cabecalho.setForeground(Color.decode("#1E293B"));
        cabecalho.setBackground(Color.decode("#F1F5F9"));
        tbPacientes.setSelectionBackground(Color.decode("#DBEAFE"));
        tbPacientes.setSelectionForeground(Color.decode("#1E293B"));
        scTabelaPaciente.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        cabecalho.setPreferredSize(new Dimension(cabecalho.getPreferredSize().width, 34));
        
        TableColumnModel columnModel1 = tbPacienteTriagem.getColumnModel();
        columnModel1.setColumnSelectionAllowed(false);
        tbPacienteTriagem.setColumnSelectionAllowed(false);
        columnModel1.getColumn(0).setPreferredWidth(50);
            columnModel1.getColumn(1).setPreferredWidth(330);
        columnModel1.getColumn(2).setPreferredWidth(100);
        
        DefaultTableCellRenderer centro1 = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        
        columnModel.getColumn(0).setCellRenderer(centro);
        columnModel.getColumn(2).setCellRenderer(centro);
        
        JTableHeader cabecalho1 = tbPacienteTriagem.getTableHeader();
        cabecalho1.setFont(new Font("SansSerif", Font.BOLD,14));
        cabecalho1.setForeground(Color.decode("#1E293B"));
        cabecalho1.setBackground(Color.decode("#F1F5F9"));
        tbPacienteTriagem.setSelectionBackground(Color.decode("#DBEAFE"));
        tbPacienteTriagem.setSelectionForeground(Color.decode("#1E293B"));
        scTabelaPacienteTriagem.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        cabecalho1.setPreferredSize(new Dimension(cabecalho1.getPreferredSize().width, 34));
        
        TableColumnModel columnModel2 = tbFilaPrioritaria.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(100);
        columnModel2.getColumn(1).setPreferredWidth(330);
        columnModel2.getColumn(2).setPreferredWidth(200);
        JTableHeader cabecalho2 = tbFilaPrioritaria.getTableHeader();
        cabecalho2.setFont(new Font("SansSerif", Font.BOLD,14));
        cabecalho2.setForeground(Color.decode("#1E293B"));
        cabecalho2.setBackground(Color.decode("#F1F5F9"));
        tbFilaPrioritaria.setSelectionBackground(Color.decode("#DBEAFE"));
        tbFilaPrioritaria.setSelectionForeground(Color.decode("#1E293B"));
        scTabelaFilaPrioritaria.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        cabecalho2.setPreferredSize(new Dimension(cabecalho2.getPreferredSize().width, 34));
        
                TableColumnModel columnModel3= tbFilaNormal.getColumnModel();
        columnModel3.getColumn(0).setPreferredWidth(100);
        columnModel3.getColumn(1).setPreferredWidth(330);
        columnModel3.getColumn(2).setPreferredWidth(200);
        JTableHeader cabecalho3 = tbFilaNormal.getTableHeader();
        cabecalho3.setFont(new Font("SansSerif", Font.BOLD,14));
        cabecalho3.setForeground(Color.decode("#1E293B"));
        cabecalho3.setBackground(Color.decode("#F1F5F9"));
        tbFilaNormal.setSelectionBackground(Color.decode("#DBEAFE"));
        tbFilaNormal.setSelectionForeground(Color.decode("#1E293B"));
        scTabelaFilaNormal.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        cabecalho3.setPreferredSize(new Dimension(cabecalho3.getPreferredSize().width, 34));
        
        
                TableColumnModel cm = tbHistorico.getColumnModel();
        cm.getColumn(0).setPreferredWidth(20);
        cm.getColumn(1).setPreferredWidth(120);
        this.setTitle("Sistema de Gestao de Atendimento Hospitalar");
        
        comboNivelTriagem.removeAllItems();
        for(NivelTriagem nivel: NivelTriagem.values()){
            comboNivelTriagem.addItem(nivel);
        }
        
comboNivelTriagem.setRenderer(new DefaultListCellRenderer() {
    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        if (value instanceof NivelTriagem nivel) {

            setText(nivel.toString());

            switch (nivel) {
                case EMERGENCIA ->
                    setForeground(Color.RED);

                case MUITO_URGENTE ->
                    setForeground(Color.ORANGE);

                case URGENTE ->
                    setForeground(Color.YELLOW);

                case NORMAL ->
                    setForeground(Color.GREEN);

                case NAO_URGENTE ->
                    setForeground(Color.BLUE);
            }
        }

        return this;
    }
});
    configurarTabelasFilas();
selecionarBotao(btDashboard);
        
        this.gestorAtendimento = new GestorAtendimento();
            atualizarDashBoard();
           ImageIcon icson = new ImageIcon(
                   getClass().getResource("/gui/images/hospital.png")
           );
           ImageIcon icon = new ImageIcon(
    getClass().getResource("/gui/images/hospital.png")
);

setIconImage(icon.getImage());
           

    }
    
    
           private void selecionarBotao(JButton bt){
               btDashboard.setBackground(Color.white);
               btPacientes.setBackground(Color.white);
               btTriagem.setBackground(Color.white);
               btFilas.setBackground(Color.white);
               btAtendimento.setBackground(Color.white);
               btHistorico.setBackground(Color.white);
               
               bt.setBackground(new Color(0xDBEAFE));
           }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnPrincipal = new javax.swing.JPanel();
        pnSidebar = new javax.swing.JPanel();
        pnLogo = new javax.swing.JPanel();
        lbSah = new javax.swing.JLabel();
        lbSubtitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        btPacientes = new javax.swing.JButton();
        btDashboard = new javax.swing.JButton();
        btAtendimento = new javax.swing.JButton();
        btTriagem = new javax.swing.JButton();
        btFilas = new javax.swing.JButton();
        btHistorico = new javax.swing.JButton();
        pnConteudo = new javax.swing.JPanel();
        pnDashboard = new javax.swing.JPanel();
        pnCabecalho = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbTitulo1 = new javax.swing.JLabel();
        pnConteudoDashboard = new javax.swing.JPanel();
        pnResumoContainer = new javax.swing.JPanel();
        pnResumo = new javax.swing.JPanel();
        pnCardAguardando = new javax.swing.JPanel();
        lbTituloAguardando = new javax.swing.JLabel();
        lbNumeroAguardando = new javax.swing.JLabel();
        lbDescricaoAguardando = new javax.swing.JLabel();
        pnCardPrioridade = new javax.swing.JPanel();
        lbTituloPrioridae = new javax.swing.JLabel();
        lbNumeroPrioridade = new javax.swing.JLabel();
        lblDescricaoPrioridade = new javax.swing.JLabel();
        pnCardNormal = new javax.swing.JPanel();
        lbTituloNormal = new javax.swing.JLabel();
        lbNumeroNormal = new javax.swing.JLabel();
        lbDescricaoNormal = new javax.swing.JLabel();
        pnPaciente = new javax.swing.JPanel();
        pnCabecalhoPaciente = new javax.swing.JPanel();
        lbTitulo2 = new javax.swing.JLabel();
        lbTitulo3 = new javax.swing.JLabel();
        pnConteudoClientes = new javax.swing.JPanel();
        pnResumoContainer1 = new javax.swing.JPanel();
        pnBotoes = new javax.swing.JPanel();
        btNovoPaciente = new javax.swing.JButton();
        pnTabelaPacientes = new javax.swing.JPanel();
        scTabelaPaciente = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable();
        pnTriagem = new javax.swing.JPanel();
        pnCabecalhoTriagem = new javax.swing.JPanel();
        lbTitulo4 = new javax.swing.JLabel();
        lbTitulo5 = new javax.swing.JLabel();
        pnConteudoTriagem = new javax.swing.JPanel();
        pnSelecaoPacienteTriagem = new javax.swing.JPanel();
        pnPesquisaPaciente = new javax.swing.JPanel();
        lbPesquisarPacienteTriagem = new javax.swing.JLabel();
        txtPesquisarPaciente = new javax.swing.JTextField();
        pnTabelaPacientesTriagem = new javax.swing.JPanel();
        scTabelaPacienteTriagem = new javax.swing.JScrollPane();
        tbPacienteTriagem = new javax.swing.JTable();
        pnContainerTiagemForm = new javax.swing.JPanel();
        pnPacienteSelecionadoTriagem = new javax.swing.JPanel();
        lbTituloAtendimento1 = new javax.swing.JLabel();
        lbEspaco2 = new javax.swing.JLabel();
        lbPacienteTriagemID = new javax.swing.JLabel();
        lbPacienteTriagemNome = new javax.swing.JLabel();
        lbPacienteTriagemIdade = new javax.swing.JLabel();
        pnFormularioTriagem = new javax.swing.JPanel();
        lbTituloAtendimento2 = new javax.swing.JLabel();
        lbPacienteTriagemSintomas = new javax.swing.JLabel();
        lbPacienteTriagemIdade1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTxtSintomasTriagem = new javax.swing.JTextArea();
        comboNivelTriagem = new javax.swing.JComboBox<>();
        pnBotoesPacienteTriagem = new javax.swing.JPanel();
        btRealizarTriagem = new javax.swing.JButton();
        pnFilas = new javax.swing.JPanel();
        pnCabecalhoFilas = new javax.swing.JPanel();
        lbTitulo6 = new javax.swing.JLabel();
        lbTitulo7 = new javax.swing.JLabel();
        pnFilasContainerConteudo = new javax.swing.JPanel();
        pnConteudoFilas = new javax.swing.JPanel();
        pnFilaPrioridade = new javax.swing.JPanel();
        pnCabecalhoFilasPrioritaria = new javax.swing.JPanel();
        lbTituloFilaPrioridade = new javax.swing.JLabel();
        lbTotalPrioridade = new javax.swing.JLabel();
        scTabelaFilaPrioritaria = new javax.swing.JScrollPane();
        tbFilaPrioritaria = new javax.swing.JTable();
        pnFilaNormal = new javax.swing.JPanel();
        pnCabecalhoFilasPrioritaria1 = new javax.swing.JPanel();
        lbTituloFilaNormal = new javax.swing.JLabel();
        lbTotalNormal = new javax.swing.JLabel();
        scTabelaFilaNormal = new javax.swing.JScrollPane();
        tbFilaNormal = new javax.swing.JTable();
        pnAtendimentoMenu = new javax.swing.JPanel();
        pnCabecalhoAtendimento = new javax.swing.JPanel();
        lbTitulo10 = new javax.swing.JLabel();
        lbTitulo11 = new javax.swing.JLabel();
        pnFilasContainerConteudo2 = new javax.swing.JPanel();
        pnConteudoFilas2 = new javax.swing.JPanel();
        pnFilaPrioridade2 = new javax.swing.JPanel();
        pnProximoPaciente = new javax.swing.JPanel();
        lbTituloFilaPrioridade2 = new javax.swing.JLabel();
        lbNomePacienteProximo = new javax.swing.JLabel();
        lbIDPacienteProximo = new javax.swing.JLabel();
        lbNivelPacienteProximo = new javax.swing.JLabel();
        btChamarProximo = new javax.swing.JButton();
        lbNivelAtendimento3 = new javax.swing.JLabel();
        txtMedicoResponsavel = new javax.swing.JTextField();
        pnFilaNormal2 = new javax.swing.JPanel();
        pnCabecalhoFilasPrioritaria5 = new javax.swing.JPanel();
        lbTituloFilaNormal2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbNomePacienteAtendimento = new javax.swing.JLabel();
        lbNivelAtendimento = new javax.swing.JLabel();
        lbNivelAtendimento1 = new javax.swing.JLabel();
        lbNivelAtendimento2 = new javax.swing.JLabel();
        lbNivelAtendimento4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTxtObservacoes = new javax.swing.JTextArea();
        btFinalizarAtendimento = new javax.swing.JButton();
        pnHistorico = new javax.swing.JPanel();
        pnCaabecalhoHistorico = new javax.swing.JPanel();
        lbTitulo12 = new javax.swing.JLabel();
        lbTitulo13 = new javax.swing.JLabel();
        pnHistoricoContainer = new javax.swing.JPanel();
        pnTotalAtendimentos = new javax.swing.JPanel();
        lbTotalAtendimentos = new javax.swing.JLabel();
        pnConteudoFilas3 = new javax.swing.JPanel();
        scTbHistorico = new javax.swing.JScrollPane();
        tbHistorico = new javax.swing.JTable();
        pnPesquisaPacienteHisotrico = new javax.swing.JPanel();
        txtPesquisarHisorico = new javax.swing.JTextField();
        lbPesquisarPacienteTriagem1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnPrincipal.setMinimumSize(new java.awt.Dimension(1000, 600));
        pnPrincipal.setLayout(new java.awt.BorderLayout());

        pnSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnSidebar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnSidebar.setPreferredSize(new java.awt.Dimension(250, 700));

        pnLogo.setBackground(new java.awt.Color(255, 255, 255));
        pnLogo.setPreferredSize(new java.awt.Dimension(250, 100));

        lbSah.setBackground(new java.awt.Color(0, 0, 255));
        lbSah.setFont(new java.awt.Font("Ubuntu Light", 1, 20)); // NOI18N
        lbSah.setForeground(new java.awt.Color(0, 153, 204));
        lbSah.setText("SAH");

        lbSubtitulo.setBackground(new java.awt.Color(30, 41, 59));
        lbSubtitulo.setFont(new java.awt.Font("Ubuntu Light", 1, 12)); // NOI18N
        lbSubtitulo.setForeground(new java.awt.Color(30, 41, 59));
        lbSubtitulo.setText("Atendimento Hospitalar");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/hospital.png"))); // NOI18N
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout pnLogoLayout = new javax.swing.GroupLayout(pnLogo);
        pnLogo.setLayout(pnLogoLayout);
        pnLogoLayout.setHorizontalGroup(
            pnLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSubtitulo)
                    .addComponent(lbSah))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnLogoLayout.setVerticalGroup(
            pnLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLogoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbSah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSubtitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnMenu.setLayout(new javax.swing.BoxLayout(pnMenu, javax.swing.BoxLayout.LINE_AXIS));

        btPacientes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btPacientes.setForeground(new java.awt.Color(0, 0, 0));
        btPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/pacient.png"))); // NOI18N
        btPacientes.setText("Pacientes");
        btPacientes.setBorderPainted(false);
        btPacientes.setContentAreaFilled(false);
        btPacientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPacientes.setFocusPainted(false);
        btPacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btPacientes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPacientes.setOpaque(true);
        btPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPacientesActionPerformed(evt);
            }
        });

        btDashboard.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btDashboard.setForeground(new java.awt.Color(0, 0, 0));
        btDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/dashboard.png"))); // NOI18N
        btDashboard.setText("Dashboard");
        btDashboard.setBorderPainted(false);
        btDashboard.setContentAreaFilled(false);
        btDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDashboard.setFocusPainted(false);
        btDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btDashboard.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btDashboard.setOpaque(true);
        btDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDashboardActionPerformed(evt);
            }
        });

        btAtendimento.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btAtendimento.setForeground(new java.awt.Color(0, 0, 0));
        btAtendimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/atendimento2.png"))); // NOI18N
        btAtendimento.setText("Atendimento");
        btAtendimento.setBorderPainted(false);
        btAtendimento.setContentAreaFilled(false);
        btAtendimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAtendimento.setFocusPainted(false);
        btAtendimento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btAtendimento.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btAtendimento.setOpaque(true);
        btAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtendimentoActionPerformed(evt);
            }
        });

        btTriagem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btTriagem.setForeground(new java.awt.Color(0, 0, 0));
        btTriagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/triagem2.png"))); // NOI18N
        btTriagem.setText("Triagem");
        btTriagem.setBorderPainted(false);
        btTriagem.setContentAreaFilled(false);
        btTriagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTriagem.setFocusPainted(false);
        btTriagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btTriagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btTriagem.setOpaque(true);
        btTriagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTriagemActionPerformed(evt);
            }
        });

        btFilas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btFilas.setForeground(new java.awt.Color(0, 0, 0));
        btFilas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/filas3.png"))); // NOI18N
        btFilas.setText("Filas");
        btFilas.setBorderPainted(false);
        btFilas.setContentAreaFilled(false);
        btFilas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFilas.setFocusPainted(false);
        btFilas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btFilas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btFilas.setOpaque(true);
        btFilas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFilasActionPerformed(evt);
            }
        });

        btHistorico.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btHistorico.setForeground(new java.awt.Color(0, 0, 0));
        btHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/historico.png"))); // NOI18N
        btHistorico.setText("Historico");
        btHistorico.setBorderPainted(false);
        btHistorico.setContentAreaFilled(false);
        btHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHistorico.setFocusPainted(false);
        btHistorico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btHistorico.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btHistorico.setOpaque(true);
        btHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSidebarLayout = new javax.swing.GroupLayout(pnSidebar);
        pnSidebar.setLayout(pnSidebarLayout);
        pnSidebarLayout.setHorizontalGroup(
            pnSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSidebarLayout.createSequentialGroup()
                .addGroup(pnSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(btAtendimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnSidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btTriagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btFilas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btHistorico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnSidebarLayout.setVerticalGroup(
            pnSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSidebarLayout.createSequentialGroup()
                .addComponent(pnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btDashboard)
                .addGap(18, 18, 18)
                .addComponent(btPacientes)
                .addGap(18, 18, 18)
                .addComponent(btTriagem)
                .addGap(18, 18, 18)
                .addComponent(btFilas)
                .addGap(18, 18, 18)
                .addComponent(btAtendimento)
                .addGap(18, 18, 18)
                .addComponent(btHistorico)
                .addGap(0, 121, Short.MAX_VALUE))
        );

        pnPrincipal.add(pnSidebar, java.awt.BorderLayout.WEST);

        pnConteudo.setBackground(new java.awt.Color(255, 102, 102));
        pnConteudo.setPreferredSize(new java.awt.Dimension(950, 700));
        pnConteudo.setLayout(new java.awt.CardLayout());

        pnDashboard.setBackground(new java.awt.Color(248, 250, 252));
        pnDashboard.setLayout(new java.awt.BorderLayout());

        pnCabecalho.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo.setText("DASHBOARD");

        lbTitulo1.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo1.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo1.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo1.setText("Visão geral do atendimento hospitalar.");

        javax.swing.GroupLayout pnCabecalhoLayout = new javax.swing.GroupLayout(pnCabecalho);
        pnCabecalho.setLayout(pnCabecalhoLayout);
        pnCabecalhoLayout.setHorizontalGroup(
            pnCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo1)
                    .addComponent(lbTitulo))
                .addContainerGap(642, Short.MAX_VALUE))
        );
        pnCabecalhoLayout.setVerticalGroup(
            pnCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnDashboard.add(pnCabecalho, java.awt.BorderLayout.NORTH);

        pnConteudoDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnConteudoDashboard.setLayout(new java.awt.BorderLayout());

        pnResumoContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnResumoContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 100, 0));

        pnResumo.setBackground(new java.awt.Color(255, 255, 255));
        pnResumo.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        pnCardAguardando.setBackground(new java.awt.Color(255, 255, 255));
        pnCardAguardando.setForeground(new java.awt.Color(255, 255, 255));
        pnCardAguardando.setLayout(new javax.swing.BoxLayout(pnCardAguardando, javax.swing.BoxLayout.Y_AXIS));

        lbTituloAguardando.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbTituloAguardando.setForeground(new java.awt.Color(100, 116, 139));
        lbTituloAguardando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloAguardando.setText("AGUARDANDO");
        lbTituloAguardando.setAlignmentX(0.5F);
        lbTituloAguardando.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardAguardando.add(lbTituloAguardando);

        lbNumeroAguardando.setFont(new java.awt.Font("Ubuntu Condensed", 0, 28)); // NOI18N
        lbNumeroAguardando.setForeground(new java.awt.Color(30, 41, 59));
        lbNumeroAguardando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumeroAguardando.setText("12");
        lbNumeroAguardando.setAlignmentX(0.5F);
        lbNumeroAguardando.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardAguardando.add(lbNumeroAguardando);

        lbDescricaoAguardando.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbDescricaoAguardando.setForeground(new java.awt.Color(100, 116, 139));
        lbDescricaoAguardando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDescricaoAguardando.setText("Pacientes na fila");
        lbDescricaoAguardando.setAlignmentX(0.5F);
        lbDescricaoAguardando.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardAguardando.add(lbDescricaoAguardando);

        pnResumo.add(pnCardAguardando);

        pnCardPrioridade.setBackground(new java.awt.Color(255, 255, 255));
        pnCardPrioridade.setForeground(new java.awt.Color(255, 255, 255));
        pnCardPrioridade.setLayout(new javax.swing.BoxLayout(pnCardPrioridade, javax.swing.BoxLayout.Y_AXIS));

        lbTituloPrioridae.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbTituloPrioridae.setForeground(new java.awt.Color(100, 116, 139));
        lbTituloPrioridae.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloPrioridae.setText("PRIORIDADE");
        lbTituloPrioridae.setAlignmentX(0.5F);
        lbTituloPrioridae.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardPrioridade.add(lbTituloPrioridae);

        lbNumeroPrioridade.setFont(new java.awt.Font("Ubuntu Condensed", 0, 28)); // NOI18N
        lbNumeroPrioridade.setForeground(new java.awt.Color(30, 41, 59));
        lbNumeroPrioridade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumeroPrioridade.setText("4");
        lbNumeroPrioridade.setAlignmentX(0.5F);
        lbNumeroPrioridade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardPrioridade.add(lbNumeroPrioridade);

        lblDescricaoPrioridade.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblDescricaoPrioridade.setForeground(new java.awt.Color(100, 116, 139));
        lblDescricaoPrioridade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricaoPrioridade.setText("Pacientes na prioritarios");
        lblDescricaoPrioridade.setAlignmentX(0.5F);
        lblDescricaoPrioridade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardPrioridade.add(lblDescricaoPrioridade);

        pnResumo.add(pnCardPrioridade);

        pnCardNormal.setBackground(new java.awt.Color(255, 255, 255));
        pnCardNormal.setForeground(new java.awt.Color(255, 255, 255));
        pnCardNormal.setLayout(new javax.swing.BoxLayout(pnCardNormal, javax.swing.BoxLayout.Y_AXIS));

        lbTituloNormal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbTituloNormal.setForeground(new java.awt.Color(100, 116, 139));
        lbTituloNormal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloNormal.setText("NORMAL");
        lbTituloNormal.setAlignmentX(0.5F);
        lbTituloNormal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardNormal.add(lbTituloNormal);

        lbNumeroNormal.setFont(new java.awt.Font("Ubuntu Condensed", 0, 28)); // NOI18N
        lbNumeroNormal.setForeground(new java.awt.Color(30, 41, 59));
        lbNumeroNormal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumeroNormal.setText("8");
        lbNumeroNormal.setAlignmentX(0.5F);
        lbNumeroNormal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardNormal.add(lbNumeroNormal);

        lbDescricaoNormal.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbDescricaoNormal.setForeground(new java.awt.Color(100, 116, 139));
        lbDescricaoNormal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDescricaoNormal.setText("Pacientes na fila");
        lbDescricaoNormal.setAlignmentX(0.5F);
        lbDescricaoNormal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCardNormal.add(lbDescricaoNormal);

        pnResumo.add(pnCardNormal);

        javax.swing.GroupLayout pnResumoContainerLayout = new javax.swing.GroupLayout(pnResumoContainer);
        pnResumoContainer.setLayout(pnResumoContainerLayout);
        pnResumoContainerLayout.setHorizontalGroup(
            pnResumoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResumoContainerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnResumoContainerLayout.setVerticalGroup(
            pnResumoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResumoContainerLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(pnResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(525, 525, 525))
        );

        pnConteudoDashboard.add(pnResumoContainer, java.awt.BorderLayout.NORTH);

        pnDashboard.add(pnConteudoDashboard, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnDashboard, "pnDashboard");

        pnPaciente.setBackground(new java.awt.Color(248, 250, 252));
        pnPaciente.setLayout(new java.awt.BorderLayout());

        pnCabecalhoPaciente.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo2.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo2.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo2.setText("REGISTAR PACIENTE");

        lbTitulo3.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo3.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo3.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo3.setText("Cadastre os dados basicos do paciente antes da triagem.");

        javax.swing.GroupLayout pnCabecalhoPacienteLayout = new javax.swing.GroupLayout(pnCabecalhoPaciente);
        pnCabecalhoPaciente.setLayout(pnCabecalhoPacienteLayout);
        pnCabecalhoPacienteLayout.setHorizontalGroup(
            pnCabecalhoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoPacienteLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCabecalhoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo3)
                    .addComponent(lbTitulo2))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        pnCabecalhoPacienteLayout.setVerticalGroup(
            pnCabecalhoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoPacienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnPaciente.add(pnCabecalhoPaciente, java.awt.BorderLayout.NORTH);

        pnConteudoClientes.setBackground(new java.awt.Color(255, 255, 255));
        pnConteudoClientes.setLayout(new java.awt.BorderLayout());

        pnResumoContainer1.setBackground(new java.awt.Color(255, 255, 255));
        pnResumoContainer1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 30, 0));

        pnBotoes.setBackground(new java.awt.Color(255, 255, 255));
        pnBotoes.setLayout(new java.awt.BorderLayout());

        btNovoPaciente.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        btNovoPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btNovoPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/btNovoPaciente.png"))); // NOI18N
        btNovoPaciente.setText("Novo Paciente");
        btNovoPaciente.setToolTipText("Registar um novo paciente");
        btNovoPaciente.setAlignmentX(1.0F);
        btNovoPaciente.setBorderPainted(false);
        btNovoPaciente.setContentAreaFilled(false);
        btNovoPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btNovoPaciente.setFocusPainted(false);
        btNovoPaciente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btNovoPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btNovoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoPacienteActionPerformed(evt);
            }
        });
        pnBotoes.add(btNovoPaciente, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout pnResumoContainer1Layout = new javax.swing.GroupLayout(pnResumoContainer1);
        pnResumoContainer1.setLayout(pnResumoContainer1Layout);
        pnResumoContainer1Layout.setHorizontalGroup(
            pnResumoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResumoContainer1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnResumoContainer1Layout.setVerticalGroup(
            pnResumoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResumoContainer1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnConteudoClientes.add(pnResumoContainer1, java.awt.BorderLayout.NORTH);

        pnTabelaPacientes.setBackground(new java.awt.Color(255, 255, 255));
        pnTabelaPacientes.setLayout(new java.awt.GridLayout(1, 2, 12, 0));

        scTabelaPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tbPacientes.setBackground(new java.awt.Color(30, 41, 59));
        tbPacientes.setForeground(new java.awt.Color(241, 245, 249));
        tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Idade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPacientes.setRowHeight(32);
        tbPacientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPacientes.getTableHeader().setReorderingAllowed(false);
        scTabelaPaciente.setViewportView(tbPacientes);
        if (tbPacientes.getColumnModel().getColumnCount() > 0) {
            tbPacientes.getColumnModel().getColumn(0).setResizable(false);
            tbPacientes.getColumnModel().getColumn(1).setResizable(false);
            tbPacientes.getColumnModel().getColumn(2).setResizable(false);
        }

        pnTabelaPacientes.add(scTabelaPaciente);

        pnConteudoClientes.add(pnTabelaPacientes, java.awt.BorderLayout.CENTER);

        pnPaciente.add(pnConteudoClientes, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnPaciente, "pnPacientes");

        pnTriagem.setBackground(new java.awt.Color(248, 250, 252));
        pnTriagem.setLayout(new java.awt.BorderLayout());

        pnCabecalhoTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoTriagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo4.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo4.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo4.setText("TRIAGEM");

        lbTitulo5.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo5.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo5.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo5.setText("Avaliacao inicial e classificacao dos pacientes.");

        javax.swing.GroupLayout pnCabecalhoTriagemLayout = new javax.swing.GroupLayout(pnCabecalhoTriagem);
        pnCabecalhoTriagem.setLayout(pnCabecalhoTriagemLayout);
        pnCabecalhoTriagemLayout.setHorizontalGroup(
            pnCabecalhoTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoTriagemLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCabecalhoTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo5)
                    .addComponent(lbTitulo4))
                .addContainerGap(1202, Short.MAX_VALUE))
        );
        pnCabecalhoTriagemLayout.setVerticalGroup(
            pnCabecalhoTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoTriagemLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnTriagem.add(pnCabecalhoTriagem, java.awt.BorderLayout.NORTH);

        pnConteudoTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnConteudoTriagem.setLayout(new java.awt.BorderLayout());

        pnSelecaoPacienteTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnSelecaoPacienteTriagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 30, 0));

        pnPesquisaPaciente.setBackground(new java.awt.Color(255, 255, 255));

        lbPesquisarPacienteTriagem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbPesquisarPacienteTriagem.setForeground(new java.awt.Color(0, 0, 0));
        lbPesquisarPacienteTriagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/search.png"))); // NOI18N
        lbPesquisarPacienteTriagem.setText("Pesquisar Paciente:");
        lbPesquisarPacienteTriagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));

        txtPesquisarPaciente.setColumns(25);
        txtPesquisarPaciente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtPesquisarPaciente.setForeground(new java.awt.Color(30, 41, 59));
        txtPesquisarPaciente.setToolTipText("Pesquisar paciente pelo nome");
        txtPesquisarPaciente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPesquisarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarPacienteActionPerformed(evt);
            }
        });
        txtPesquisarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarPacienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnSelecaoPacienteTriagemLayout = new javax.swing.GroupLayout(pnSelecaoPacienteTriagem);
        pnSelecaoPacienteTriagem.setLayout(pnSelecaoPacienteTriagemLayout);
        pnSelecaoPacienteTriagemLayout.setHorizontalGroup(
            pnSelecaoPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSelecaoPacienteTriagemLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbPesquisarPacienteTriagem)
                .addGap(76, 76, 76)
                .addComponent(txtPesquisarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnPesquisaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnSelecaoPacienteTriagemLayout.setVerticalGroup(
            pnSelecaoPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSelecaoPacienteTriagemLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(pnSelecaoPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPesquisarPacienteTriagem)
                    .addComponent(pnPesquisaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSelecaoPacienteTriagemLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtPesquisarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnConteudoTriagem.add(pnSelecaoPacienteTriagem, java.awt.BorderLayout.NORTH);

        pnTabelaPacientesTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnTabelaPacientesTriagem.setLayout(new java.awt.GridLayout(1, 2, 12, 0));

        tbPacienteTriagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        tbPacienteTriagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Idade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPacienteTriagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbPacienteTriagem.setShowGrid(false);
        tbPacienteTriagem.getTableHeader().setReorderingAllowed(false);
        tbPacienteTriagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPacienteTriagemMouseClicked(evt);
            }
        });
        scTabelaPacienteTriagem.setViewportView(tbPacienteTriagem);
        tbPacienteTriagem.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbPacienteTriagem.getColumnModel().getColumnCount() > 0) {
            tbPacienteTriagem.getColumnModel().getColumn(0).setResizable(false);
            tbPacienteTriagem.getColumnModel().getColumn(1).setResizable(false);
            tbPacienteTriagem.getColumnModel().getColumn(2).setResizable(false);
        }

        pnTabelaPacientesTriagem.add(scTabelaPacienteTriagem);

        pnContainerTiagemForm.setLayout(new java.awt.BorderLayout());

        pnPacienteSelecionadoTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnPacienteSelecionadoTriagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 1, 5, 1));
        pnPacienteSelecionadoTriagem.setLayout(new javax.swing.BoxLayout(pnPacienteSelecionadoTriagem, javax.swing.BoxLayout.Y_AXIS));

        lbTituloAtendimento1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbTituloAtendimento1.setForeground(new java.awt.Color(100, 116, 139));
        lbTituloAtendimento1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTituloAtendimento1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/person.png"))); // NOI18N
        lbTituloAtendimento1.setText("PACIENTE SELECIONADO");
        lbTituloAtendimento1.setAlignmentX(0.5F);
        lbTituloAtendimento1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        pnPacienteSelecionadoTriagem.add(lbTituloAtendimento1);

        lbEspaco2.setBackground(new java.awt.Color(255, 255, 255));
        lbEspaco2.setForeground(new java.awt.Color(255, 255, 255));
        lbEspaco2.setText("A");
        pnPacienteSelecionadoTriagem.add(lbEspaco2);

        lbPacienteTriagemID.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbPacienteTriagemID.setForeground(new java.awt.Color(100, 116, 139));
        lbPacienteTriagemID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPacienteTriagemID.setAlignmentX(0.5F);
        lbPacienteTriagemID.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 1, 2, 1));
        lbPacienteTriagemID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnPacienteSelecionadoTriagem.add(lbPacienteTriagemID);

        lbPacienteTriagemNome.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lbPacienteTriagemNome.setForeground(new java.awt.Color(30, 41, 59));
        lbPacienteTriagemNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPacienteTriagemNome.setText("Nenhum Paciente Selecionado");
        lbPacienteTriagemNome.setAlignmentX(0.5F);
        lbPacienteTriagemNome.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 1, 2, 1));
        lbPacienteTriagemNome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnPacienteSelecionadoTriagem.add(lbPacienteTriagemNome);

        lbPacienteTriagemIdade.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbPacienteTriagemIdade.setForeground(new java.awt.Color(100, 116, 139));
        lbPacienteTriagemIdade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPacienteTriagemIdade.setAlignmentX(0.5F);
        lbPacienteTriagemIdade.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 1, 2, 1));
        lbPacienteTriagemIdade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnPacienteSelecionadoTriagem.add(lbPacienteTriagemIdade);

        pnContainerTiagemForm.add(pnPacienteSelecionadoTriagem, java.awt.BorderLayout.NORTH);

        pnFormularioTriagem.setBackground(new java.awt.Color(255, 255, 255));
        pnFormularioTriagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        pnFormularioTriagem.setLayout(new java.awt.GridBagLayout());

        lbTituloAtendimento2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbTituloAtendimento2.setForeground(new java.awt.Color(100, 116, 139));
        lbTituloAtendimento2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloAtendimento2.setText("Realizar Triagem");
        lbTituloAtendimento2.setAlignmentX(0.5F);
        lbTituloAtendimento2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        pnFormularioTriagem.add(lbTituloAtendimento2, gridBagConstraints);

        lbPacienteTriagemSintomas.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbPacienteTriagemSintomas.setForeground(new java.awt.Color(100, 116, 139));
        lbPacienteTriagemSintomas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPacienteTriagemSintomas.setText("Sintomas");
        lbPacienteTriagemSintomas.setAlignmentX(0.5F);
        lbPacienteTriagemSintomas.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 1, 2, 1));
        lbPacienteTriagemSintomas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        pnFormularioTriagem.add(lbPacienteTriagemSintomas, gridBagConstraints);

        lbPacienteTriagemIdade1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbPacienteTriagemIdade1.setForeground(new java.awt.Color(100, 116, 139));
        lbPacienteTriagemIdade1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPacienteTriagemIdade1.setText("Nivel de Triagem");
        lbPacienteTriagemIdade1.setAlignmentX(0.5F);
        lbPacienteTriagemIdade1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 1));
        lbPacienteTriagemIdade1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 3;
        pnFormularioTriagem.add(lbPacienteTriagemIdade1, gridBagConstraints);

        areaTxtSintomasTriagem.setColumns(20);
        areaTxtSintomasTriagem.setForeground(new java.awt.Color(30, 41, 59));
        areaTxtSintomasTriagem.setLineWrap(true);
        areaTxtSintomasTriagem.setRows(5);
        areaTxtSintomasTriagem.setWrapStyleWord(true);
        areaTxtSintomasTriagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(areaTxtSintomasTriagem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 293;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        pnFormularioTriagem.add(jScrollPane1, gridBagConstraints);

        comboNivelTriagem.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        comboNivelTriagem.setForeground(new java.awt.Color(0, 0, 0));
        comboNivelTriagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        pnFormularioTriagem.add(comboNivelTriagem, gridBagConstraints);

        pnContainerTiagemForm.add(pnFormularioTriagem, java.awt.BorderLayout.CENTER);

        pnBotoesPacienteTriagem.setBackground(new java.awt.Color(255, 255, 255));

        btRealizarTriagem.setBackground(new java.awt.Color(0, 51, 255));
        btRealizarTriagem.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btRealizarTriagem.setForeground(new java.awt.Color(255, 255, 255));
        btRealizarTriagem.setText("Realizar Triagem");
        btRealizarTriagem.setToolTipText("Realizar a triagem do paciente selecionado");
        btRealizarTriagem.setBorderPainted(false);
        btRealizarTriagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRealizarTriagem.setFocusPainted(false);
        btRealizarTriagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRealizarTriagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBotoesPacienteTriagemLayout = new javax.swing.GroupLayout(pnBotoesPacienteTriagem);
        pnBotoesPacienteTriagem.setLayout(pnBotoesPacienteTriagemLayout);
        pnBotoesPacienteTriagemLayout.setHorizontalGroup(
            pnBotoesPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
            .addGroup(pnBotoesPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBotoesPacienteTriagemLayout.createSequentialGroup()
                    .addContainerGap(324, Short.MAX_VALUE)
                    .addComponent(btRealizarTriagem)
                    .addContainerGap(325, Short.MAX_VALUE)))
        );
        pnBotoesPacienteTriagemLayout.setVerticalGroup(
            pnBotoesPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(pnBotoesPacienteTriagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBotoesPacienteTriagemLayout.createSequentialGroup()
                    .addContainerGap(23, Short.MAX_VALUE)
                    .addComponent(btRealizarTriagem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );

        pnContainerTiagemForm.add(pnBotoesPacienteTriagem, java.awt.BorderLayout.SOUTH);

        pnTabelaPacientesTriagem.add(pnContainerTiagemForm);

        pnConteudoTriagem.add(pnTabelaPacientesTriagem, java.awt.BorderLayout.CENTER);

        pnTriagem.add(pnConteudoTriagem, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnTriagem, "pnTriagem");
        pnTriagem.getAccessibleContext().setAccessibleName("pnTriagem");

        pnFilas.setBackground(new java.awt.Color(248, 250, 252));
        pnFilas.setLayout(new java.awt.BorderLayout());

        pnCabecalhoFilas.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoFilas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo6.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo6.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo6.setText("FILAS DE ATENDIMENTO");

        lbTitulo7.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo7.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo7.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo7.setText("Visulaizacao dos pacientes aguardando atendimento e sua ordem de atendimento");

        javax.swing.GroupLayout pnCabecalhoFilasLayout = new javax.swing.GroupLayout(pnCabecalhoFilas);
        pnCabecalhoFilas.setLayout(pnCabecalhoFilasLayout);
        pnCabecalhoFilasLayout.setHorizontalGroup(
            pnCabecalhoFilasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoFilasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCabecalhoFilasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo7)
                    .addComponent(lbTitulo6))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        pnCabecalhoFilasLayout.setVerticalGroup(
            pnCabecalhoFilasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoFilasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo7)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnFilas.add(pnCabecalhoFilas, java.awt.BorderLayout.NORTH);

        pnFilasContainerConteudo.setBackground(new java.awt.Color(255, 255, 255));
        pnFilasContainerConteudo.setLayout(new java.awt.BorderLayout());

        pnConteudoFilas.setBackground(new java.awt.Color(255, 255, 255));
        pnConteudoFilas.setLayout(new java.awt.GridLayout(1, 2, 12, 0));

        pnFilaPrioridade.setBackground(new java.awt.Color(255, 255, 255));
        pnFilaPrioridade.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnFilaPrioridade.setLayout(new java.awt.BorderLayout());

        pnCabecalhoFilasPrioritaria.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoFilasPrioritaria.setLayout(new java.awt.BorderLayout());

        lbTituloFilaPrioridade.setBackground(new java.awt.Color(255, 255, 255));
        lbTituloFilaPrioridade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbTituloFilaPrioridade.setForeground(new java.awt.Color(102, 102, 102));
        lbTituloFilaPrioridade.setText("FILA PRIORITARIA");
        lbTituloFilaPrioridade.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 5, 15));
        pnCabecalhoFilasPrioritaria.add(lbTituloFilaPrioridade, java.awt.BorderLayout.NORTH);

        lbTotalPrioridade.setBackground(new java.awt.Color(255, 255, 255));
        lbTotalPrioridade.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbTotalPrioridade.setText("5");
        lbTotalPrioridade.setAlignmentX(0.5F);
        lbTotalPrioridade.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbTotalPrioridade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCabecalhoFilasPrioritaria.add(lbTotalPrioridade, java.awt.BorderLayout.CENTER);

        pnFilaPrioridade.add(pnCabecalhoFilasPrioritaria, java.awt.BorderLayout.NORTH);

        scTabelaFilaPrioritaria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tbFilaPrioritaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posicao", "Paciente", "Nivel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFilaPrioritaria.getTableHeader().setReorderingAllowed(false);
        scTabelaFilaPrioritaria.setViewportView(tbFilaPrioritaria);
        if (tbFilaPrioritaria.getColumnModel().getColumnCount() > 0) {
            tbFilaPrioritaria.getColumnModel().getColumn(0).setResizable(false);
            tbFilaPrioritaria.getColumnModel().getColumn(1).setResizable(false);
            tbFilaPrioritaria.getColumnModel().getColumn(2).setResizable(false);
        }

        pnFilaPrioridade.add(scTabelaFilaPrioritaria, java.awt.BorderLayout.CENTER);

        pnConteudoFilas.add(pnFilaPrioridade);

        pnFilaNormal.setBackground(new java.awt.Color(255, 255, 255));
        pnFilaNormal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnFilaNormal.setLayout(new java.awt.BorderLayout());

        pnCabecalhoFilasPrioritaria1.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoFilasPrioritaria1.setLayout(new java.awt.BorderLayout());

        lbTituloFilaNormal.setBackground(new java.awt.Color(255, 255, 255));
        lbTituloFilaNormal.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        lbTituloFilaNormal.setForeground(new java.awt.Color(102, 102, 102));
        lbTituloFilaNormal.setText("FILA NORMAL");
        lbTituloFilaNormal.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 5, 10));
        pnCabecalhoFilasPrioritaria1.add(lbTituloFilaNormal, java.awt.BorderLayout.NORTH);

        lbTotalNormal.setBackground(new java.awt.Color(255, 255, 255));
        lbTotalNormal.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbTotalNormal.setText("3");
        lbTotalNormal.setAlignmentX(0.5F);
        lbTotalNormal.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbTotalNormal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnCabecalhoFilasPrioritaria1.add(lbTotalNormal, java.awt.BorderLayout.CENTER);

        pnFilaNormal.add(pnCabecalhoFilasPrioritaria1, java.awt.BorderLayout.NORTH);

        scTabelaFilaNormal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tbFilaNormal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posicao", "Paciente", "Nivel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFilaNormal.getTableHeader().setReorderingAllowed(false);
        scTabelaFilaNormal.setViewportView(tbFilaNormal);
        if (tbFilaNormal.getColumnModel().getColumnCount() > 0) {
            tbFilaNormal.getColumnModel().getColumn(0).setResizable(false);
            tbFilaNormal.getColumnModel().getColumn(1).setResizable(false);
            tbFilaNormal.getColumnModel().getColumn(2).setResizable(false);
        }

        pnFilaNormal.add(scTabelaFilaNormal, java.awt.BorderLayout.CENTER);

        pnConteudoFilas.add(pnFilaNormal);

        pnFilasContainerConteudo.add(pnConteudoFilas, java.awt.BorderLayout.CENTER);

        pnFilas.add(pnFilasContainerConteudo, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnFilas, "pnFilas");
        pnFilas.getAccessibleContext().setAccessibleParent(pnFilas);

        pnAtendimentoMenu.setBackground(new java.awt.Color(248, 250, 252));
        pnAtendimentoMenu.setLayout(new java.awt.BorderLayout());

        pnCabecalhoAtendimento.setBackground(new java.awt.Color(255, 255, 255));
        pnCabecalhoAtendimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo10.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo10.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo10.setText("ATENDIMENTO");

        lbTitulo11.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo11.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo11.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo11.setText("Gestao dos atendimentos em curso.");

        javax.swing.GroupLayout pnCabecalhoAtendimentoLayout = new javax.swing.GroupLayout(pnCabecalhoAtendimento);
        pnCabecalhoAtendimento.setLayout(pnCabecalhoAtendimentoLayout);
        pnCabecalhoAtendimentoLayout.setHorizontalGroup(
            pnCabecalhoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoAtendimentoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCabecalhoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo11)
                    .addComponent(lbTitulo10))
                .addContainerGap(893, Short.MAX_VALUE))
        );
        pnCabecalhoAtendimentoLayout.setVerticalGroup(
            pnCabecalhoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoAtendimentoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo11)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnAtendimentoMenu.add(pnCabecalhoAtendimento, java.awt.BorderLayout.NORTH);

        pnFilasContainerConteudo2.setBackground(new java.awt.Color(255, 255, 255));
        pnFilasContainerConteudo2.setLayout(new java.awt.BorderLayout());

        pnConteudoFilas2.setBackground(new java.awt.Color(255, 255, 255));

        pnFilaPrioridade2.setBackground(new java.awt.Color(255, 255, 255));
        pnFilaPrioridade2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnFilaPrioridade2.setLayout(new java.awt.BorderLayout());

        pnProximoPaciente.setBackground(new java.awt.Color(255, 255, 255));

        lbTituloFilaPrioridade2.setBackground(new java.awt.Color(255, 255, 255));
        lbTituloFilaPrioridade2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbTituloFilaPrioridade2.setForeground(new java.awt.Color(102, 102, 102));
        lbTituloFilaPrioridade2.setText("PROXIMO PACIENTE");
        lbTituloFilaPrioridade2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 5, 15));

        lbNomePacienteProximo.setBackground(new java.awt.Color(255, 255, 255));
        lbNomePacienteProximo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNomePacienteProximo.setText("Nao ha Pacientes aguardando atendimento");
        lbNomePacienteProximo.setAlignmentX(0.5F);
        lbNomePacienteProximo.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNomePacienteProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbIDPacienteProximo.setBackground(new java.awt.Color(255, 255, 255));
        lbIDPacienteProximo.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        lbIDPacienteProximo.setAlignmentX(0.5F);
        lbIDPacienteProximo.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 2, 50));
        lbIDPacienteProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbNivelPacienteProximo.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelPacienteProximo.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        lbNivelPacienteProximo.setAlignmentX(0.5F);
        lbNivelPacienteProximo.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 2, 50));
        lbNivelPacienteProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btChamarProximo.setBackground(new java.awt.Color(0, 51, 255));
        btChamarProximo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btChamarProximo.setForeground(new java.awt.Color(255, 255, 255));
        btChamarProximo.setText("Iniciar Atendimento");
        btChamarProximo.setToolTipText("Iniciar o atendimento do próximo paciente da fila");
        btChamarProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btChamarProximo.setFocusPainted(false);
        btChamarProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChamarProximoActionPerformed(evt);
            }
        });

        lbNivelAtendimento3.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAtendimento3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNivelAtendimento3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivelAtendimento3.setText("Medico");
        lbNivelAtendimento3.setAlignmentX(0.5F);
        lbNivelAtendimento3.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNivelAtendimento3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtMedicoResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicoResponsavelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnProximoPacienteLayout = new javax.swing.GroupLayout(pnProximoPaciente);
        pnProximoPaciente.setLayout(pnProximoPacienteLayout);
        pnProximoPacienteLayout.setHorizontalGroup(
            pnProximoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNomePacienteProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbIDPacienteProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbNivelPacienteProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnProximoPacienteLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btChamarProximo))
            .addGroup(pnProximoPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNivelAtendimento3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMedicoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnProximoPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTituloFilaPrioridade2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnProximoPacienteLayout.setVerticalGroup(
            pnProximoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnProximoPacienteLayout.createSequentialGroup()
                .addComponent(lbTituloFilaPrioridade2)
                .addGap(81, 81, 81)
                .addComponent(lbNomePacienteProximo)
                .addGap(6, 6, 6)
                .addComponent(lbIDPacienteProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbNivelPacienteProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnProximoPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNivelAtendimento3)
                    .addComponent(txtMedicoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btChamarProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnFilaPrioridade2.add(pnProximoPaciente, java.awt.BorderLayout.CENTER);

        pnFilaNormal2.setBackground(new java.awt.Color(255, 255, 255));
        pnFilaNormal2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));

        pnCabecalhoFilasPrioritaria5.setBackground(new java.awt.Color(255, 255, 255));

        lbTituloFilaNormal2.setBackground(new java.awt.Color(255, 255, 255));
        lbTituloFilaNormal2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        lbTituloFilaNormal2.setForeground(new java.awt.Color(102, 102, 102));
        lbTituloFilaNormal2.setText("ATENDIMENTO EM CURSO");
        lbTituloFilaNormal2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 5, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/pacient.png"))); // NOI18N

        javax.swing.GroupLayout pnCabecalhoFilasPrioritaria5Layout = new javax.swing.GroupLayout(pnCabecalhoFilasPrioritaria5);
        pnCabecalhoFilasPrioritaria5.setLayout(pnCabecalhoFilasPrioritaria5Layout);
        pnCabecalhoFilasPrioritaria5Layout.setHorizontalGroup(
            pnCabecalhoFilasPrioritaria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoFilasPrioritaria5Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(pnCabecalhoFilasPrioritaria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCabecalhoFilasPrioritaria5Layout.createSequentialGroup()
                        .addComponent(lbTituloFilaNormal2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCabecalhoFilasPrioritaria5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
        );
        pnCabecalhoFilasPrioritaria5Layout.setVerticalGroup(
            pnCabecalhoFilasPrioritaria5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCabecalhoFilasPrioritaria5Layout.createSequentialGroup()
                .addComponent(lbTituloFilaNormal2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        lbNomePacienteAtendimento.setBackground(new java.awt.Color(255, 255, 255));
        lbNomePacienteAtendimento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNomePacienteAtendimento.setText("Paciente : -");
        lbNomePacienteAtendimento.setAlignmentX(0.5F);
        lbNomePacienteAtendimento.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNomePacienteAtendimento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbNivelAtendimento.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAtendimento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNivelAtendimento.setText("Nivel : -");
        lbNivelAtendimento.setAlignmentX(0.5F);
        lbNivelAtendimento.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNivelAtendimento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbNivelAtendimento1.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAtendimento1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNivelAtendimento1.setText("Sintomas:");
        lbNivelAtendimento1.setAlignmentX(0.5F);
        lbNivelAtendimento1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNivelAtendimento1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbNivelAtendimento2.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAtendimento2.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        lbNivelAtendimento2.setAlignmentX(0.5F);
        lbNivelAtendimento2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lbNivelAtendimento2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbNivelAtendimento4.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAtendimento4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNivelAtendimento4.setText("Observacoes");
        lbNivelAtendimento4.setAlignmentX(0.5F);
        lbNivelAtendimento4.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 50, 10, 50));
        lbNivelAtendimento4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        areaTxtObservacoes.setColumns(20);
        areaTxtObservacoes.setRows(5);
        jScrollPane2.setViewportView(areaTxtObservacoes);

        btFinalizarAtendimento.setBackground(new java.awt.Color(0, 51, 255));
        btFinalizarAtendimento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btFinalizarAtendimento.setForeground(new java.awt.Color(255, 255, 255));
        btFinalizarAtendimento.setText("Finalizar Atendimento");
        btFinalizarAtendimento.setToolTipText("Finalizar o atendimento atual");
        btFinalizarAtendimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizarAtendimento.setFocusPainted(false);
        btFinalizarAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarAtendimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFilaNormal2Layout = new javax.swing.GroupLayout(pnFilaNormal2);
        pnFilaNormal2.setLayout(pnFilaNormal2Layout);
        pnFilaNormal2Layout.setHorizontalGroup(
            pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                .addGroup(pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnCabecalhoFilasPrioritaria5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbNomePacienteAtendimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNivelAtendimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbNivelAtendimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbNivelAtendimento2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                                    .addComponent(lbNivelAtendimento4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btFinalizarAtendimento)))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        pnFilaNormal2Layout.setVerticalGroup(
            pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                .addComponent(pnCabecalhoFilasPrioritaria5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lbNomePacienteAtendimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNivelAtendimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNivelAtendimento1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNivelAtendimento2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnFilaNormal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnFilaNormal2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lbNivelAtendimento4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(btFinalizarAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pnConteudoFilas2Layout = new javax.swing.GroupLayout(pnConteudoFilas2);
        pnConteudoFilas2.setLayout(pnConteudoFilas2Layout);
        pnConteudoFilas2Layout.setHorizontalGroup(
            pnConteudoFilas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConteudoFilas2Layout.createSequentialGroup()
                .addComponent(pnFilaPrioridade2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnFilaNormal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        pnConteudoFilas2Layout.setVerticalGroup(
            pnConteudoFilas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnFilaPrioridade2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnFilaNormal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnFilasContainerConteudo2.add(pnConteudoFilas2, java.awt.BorderLayout.CENTER);

        pnAtendimentoMenu.add(pnFilasContainerConteudo2, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnAtendimentoMenu, "pnAtendimento");

        pnHistorico.setBackground(new java.awt.Color(248, 250, 252));
        pnHistorico.setLayout(new java.awt.BorderLayout());

        pnCaabecalhoHistorico.setBackground(new java.awt.Color(255, 255, 255));
        pnCaabecalhoHistorico.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitulo12.setFont(new java.awt.Font("Ubuntu Mono", 1, 26)); // NOI18N
        lbTitulo12.setForeground(new java.awt.Color(30, 41, 59));
        lbTitulo12.setText("HISTORICO");

        lbTitulo13.setBackground(new java.awt.Color(0, 0, 0));
        lbTitulo13.setFont(new java.awt.Font("Ubuntu Mono", 0, 14)); // NOI18N
        lbTitulo13.setForeground(new java.awt.Color(100, 116, 139));
        lbTitulo13.setText("Registo dos atendimentos realizados");

        javax.swing.GroupLayout pnCaabecalhoHistoricoLayout = new javax.swing.GroupLayout(pnCaabecalhoHistorico);
        pnCaabecalhoHistorico.setLayout(pnCaabecalhoHistoricoLayout);
        pnCaabecalhoHistoricoLayout.setHorizontalGroup(
            pnCaabecalhoHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCaabecalhoHistoricoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnCaabecalhoHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo13)
                    .addComponent(lbTitulo12))
                .addContainerGap(663, Short.MAX_VALUE))
        );
        pnCaabecalhoHistoricoLayout.setVerticalGroup(
            pnCaabecalhoHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCaabecalhoHistoricoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbTitulo12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo13)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnHistorico.add(pnCaabecalhoHistorico, java.awt.BorderLayout.NORTH);

        pnHistoricoContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnHistoricoContainer.setLayout(new java.awt.BorderLayout());

        lbTotalAtendimentos.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbTotalAtendimentos.setText("TOTAL");

        javax.swing.GroupLayout pnTotalAtendimentosLayout = new javax.swing.GroupLayout(pnTotalAtendimentos);
        pnTotalAtendimentos.setLayout(pnTotalAtendimentosLayout);
        pnTotalAtendimentosLayout.setHorizontalGroup(
            pnTotalAtendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTotalAtendimentosLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lbTotalAtendimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        pnTotalAtendimentosLayout.setVerticalGroup(
            pnTotalAtendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTotalAtendimentosLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbTotalAtendimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pnHistoricoContainer.add(pnTotalAtendimentos, java.awt.BorderLayout.SOUTH);

        pnConteudoFilas3.setBackground(new java.awt.Color(255, 255, 255));
        pnConteudoFilas3.setLayout(new java.awt.BorderLayout());

        tbHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Paciente", "Medico", "Nivel", "Inicio", "Fim", "Duracao", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHistorico.setColumnSelectionAllowed(true);
        tbHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbHistorico.getTableHeader().setReorderingAllowed(false);
        scTbHistorico.setViewportView(tbHistorico);
        tbHistorico.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbHistorico.getColumnModel().getColumnCount() > 0) {
            tbHistorico.getColumnModel().getColumn(0).setResizable(false);
            tbHistorico.getColumnModel().getColumn(1).setResizable(false);
            tbHistorico.getColumnModel().getColumn(2).setResizable(false);
            tbHistorico.getColumnModel().getColumn(3).setResizable(false);
            tbHistorico.getColumnModel().getColumn(4).setResizable(false);
            tbHistorico.getColumnModel().getColumn(5).setResizable(false);
            tbHistorico.getColumnModel().getColumn(6).setResizable(false);
            tbHistorico.getColumnModel().getColumn(7).setResizable(false);
        }

        pnConteudoFilas3.add(scTbHistorico, java.awt.BorderLayout.CENTER);

        pnHistoricoContainer.add(pnConteudoFilas3, java.awt.BorderLayout.CENTER);

        pnPesquisaPacienteHisotrico.setBackground(new java.awt.Color(255, 255, 255));
        pnPesquisaPacienteHisotrico.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 1, 10, 1));

        txtPesquisarHisorico.setColumns(25);
        txtPesquisarHisorico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtPesquisarHisorico.setForeground(new java.awt.Color(30, 41, 59));
        txtPesquisarHisorico.setToolTipText("Pesquisar atendimento por paciente");
        txtPesquisarHisorico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPesquisarHisorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarHisoricoActionPerformed(evt);
            }
        });
        txtPesquisarHisorico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarHisoricoKeyReleased(evt);
            }
        });

        lbPesquisarPacienteTriagem1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbPesquisarPacienteTriagem1.setForeground(new java.awt.Color(0, 0, 0));
        lbPesquisarPacienteTriagem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/search.png"))); // NOI18N
        lbPesquisarPacienteTriagem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));

        javax.swing.GroupLayout pnPesquisaPacienteHisotricoLayout = new javax.swing.GroupLayout(pnPesquisaPacienteHisotrico);
        pnPesquisaPacienteHisotrico.setLayout(pnPesquisaPacienteHisotricoLayout);
        pnPesquisaPacienteHisotricoLayout.setHorizontalGroup(
            pnPesquisaPacienteHisotricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPesquisaPacienteHisotricoLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(lbPesquisarPacienteTriagem1)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisarHisorico, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnPesquisaPacienteHisotricoLayout.setVerticalGroup(
            pnPesquisaPacienteHisotricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPesquisaPacienteHisotricoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbPesquisarPacienteTriagem1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPesquisaPacienteHisotricoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisarHisorico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnHistoricoContainer.add(pnPesquisaPacienteHisotrico, java.awt.BorderLayout.NORTH);

        pnHistorico.add(pnHistoricoContainer, java.awt.BorderLayout.CENTER);

        pnConteudo.add(pnHistorico, "pnHistorico");
        pnHistorico.getAccessibleContext().setAccessibleParent(pnHistorico);

        pnPrincipal.add(pnConteudo, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPacientesActionPerformed
        // TODO add your handling code here:
        atualizarTabelaPacientes();
        selecionarBotao(btPacientes);
        CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnPacientes");
        
    }//GEN-LAST:event_btPacientesActionPerformed

    private void btDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboardActionPerformed
        // TODO add your handling code here:
        selecionarBotao(btDashboard);
atualizarDashBoard()
;        

        
    }//GEN-LAST:event_btDashboardActionPerformed

    private void atualizarDashBoard(){
            CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnDashboard");
        int totalPacientes = gestorAtendimento.listarPacientes().size();
        int emEspera = gestorAtendimento.listarFilaNormal().size() + gestorAtendimento.listarFilaPrioridade().size();
        lbNumeroAguardando.setText(String.valueOf(emEspera));
        lbNumeroPrioridade.setText(String.valueOf(gestorAtendimento.listarFilaPrioridade().size()));
        lbNumeroNormal.setText(String.valueOf(gestorAtendimento.listarFilaNormal().size()));
    }
    
    private void btAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtendimentoActionPerformed
        // TODO add your handling code here:
        selecionarBotao(btAtendimento);
        atualizarPainelProximoPacienteAtendimento();
                CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnAtendimento");
        Atendimento atendimentoAtual = gestorAtendimento.getAtendimentoAtual();
        if (atendimentoAtual == null) {
            btFinalizarAtendimento.setEnabled(false);
        }
        
    }//GEN-LAST:event_btAtendimentoActionPerformed

    private void atualizarPainelProximoPacienteAtendimento(){
        try {
           
            if (gestorAtendimento.proximoPaciente() != null) {
                btChamarProximo.setEnabled(true);
                lbNivelAtendimento3.setVisible(true);
                txtMedicoResponsavel.setVisible(true);
                Paciente proximoPaciente = gestorAtendimento.proximoPaciente();
            lbNomePacienteProximo.setText("Nome : " + proximoPaciente.getNome());
            lbIDPacienteProximo.setText("ID : " + proximoPaciente.getId());
            lbNivelPacienteProximo.setText("Nivel : " + proximoPaciente.getNivel());
            }else{
                lbNivelAtendimento3.setVisible(false);
                txtMedicoResponsavel.setVisible(false);
             
            }
            
        } catch (FilaVaziaException ex) {
            lbNomePacienteProximo.setText("Nao ha Pacientes aguardando atendimento." );
            lbIDPacienteProximo.setText("");
            lbNivelPacienteProximo.setText("");
            btChamarProximo.setEnabled(false);
             lbNivelAtendimento3.setVisible(false);
                txtMedicoResponsavel.setVisible(false);
        }
    }
    
    private void btTriagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTriagemActionPerformed
        // TODO add your handling code here:
        selecionarBotao(btTriagem);
        atualizarTabelaPacientesTriagem();
         CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnTriagem");
    }//GEN-LAST:event_btTriagemActionPerformed

    private void btFilasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFilasActionPerformed
        // TODO add your handling code here:
        selecionarBotao(btFilas);
        atualizarTabelaNormal();
        atualizarTabelaPrioritaria();
         CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnFilas");
    }//GEN-LAST:event_btFilasActionPerformed

    private void btHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHistoricoActionPerformed
        // TODO add your handling code here:
        selecionarBotao(btHistorico);
        prencherTabelaHistorico();
                 CardLayout cardLayout = (CardLayout) pnConteudo.getLayout();
        cardLayout.show(pnConteudo, "pnHistorico");
        
    }//GEN-LAST:event_btHistoricoActionPerformed

    private void prencherTabelaHistorico(){
        DefaultTableModel modelo = (DefaultTableModel) tbHistorico.getModel();
        modelo.setRowCount(0);
        
        for(Atendimento a : gestorAtendimento.getHistorico()){
            Paciente paciente = a.getPaciente();
            Object[] linha = {
                a.getId(),
                paciente.getNome(),
                a.getMedico(),
                paciente.getNivel(),
                formatarHora(a.getHoraInicio()),
                formatarHora(a.getHoraFim()),
                a.getDuracaoSegundos() + "s",
                a.getEstado()
            };
            modelo.addRow(linha);
            
        }
        lbTotalAtendimentos.setText("Total de atendimentos: " + modelo.getRowCount());
    }
    
    private String formatarHora(long tm){
        if(tm == 0){
            return "-";
        }
        return new SimpleDateFormat("HH:mm").format(new Date(tm));
    }
    
    private void btRealizarTriagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRealizarTriagemActionPerformed
        // TODO add your handling code here:
        int linha = tbPacienteTriagem.getSelectedRow();
        if(linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione um paciente.", "Triagem", JOptionPane.WARNING_MESSAGE);
        }else{
            int id = (int) tbPacienteTriagem.getValueAt(linha, 0);
            Paciente paciente = gestorAtendimento.buscarPaciente(id);
            NivelTriagem nivel = (NivelTriagem) comboNivelTriagem.getSelectedItem();
            if (nivel == null) {
                JOptionPane.showMessageDialog(this, "Selecione o nivel de triagem.", "Triagem", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            try {
                gestorAtendimento.realizarTriagem(paciente, areaTxtSintomasTriagem.getText().trim(), nivel);
            } catch (PacienteInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Triagem",JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Triagem realizada com sucesso.\n\nPaciente: " + paciente.getNome() + "\nNivel: " + paciente.getNivel(), "Triagem", JOptionPane.INFORMATION_MESSAGE);
            atualizarTabelaPacientesTriagem();
            areaTxtSintomasTriagem.setText("");
            comboNivelTriagem.setSelectedIndex(0);
            lbPacienteTriagemID.setText("");
            lbPacienteTriagemNome.setText("Nenhum Paciente Selecionado");
            lbPacienteTriagemIdade.setText("");
            txtPesquisarPaciente.setText("");
        }
    }//GEN-LAST:event_btRealizarTriagemActionPerformed

    private void tbPacienteTriagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteTriagemMouseClicked
        // TODO add your handling code here:
        int linha = tbPacienteTriagem.getSelectedRow();
        if (linha != -1) {
            int id = (int) tbPacienteTriagem.getValueAt(linha, 0);
            String nome = (String) tbPacienteTriagem.getValueAt(linha, 1);
            int  idade = (int) tbPacienteTriagem.getValueAt(linha, 2);
            lbPacienteTriagemID.setText("ID: " + id);
            lbPacienteTriagemNome.setText("Nome: " + nome);
            lbPacienteTriagemIdade.setText("Idade: " + idade + " anos");
        }
    }//GEN-LAST:event_tbPacienteTriagemMouseClicked

    private void txtPesquisarPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarPacienteKeyReleased
        // TODO add your handling code here:
        pesquisarPacientesTriagem();
    }//GEN-LAST:event_txtPesquisarPacienteKeyReleased

    private void txtPesquisarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarPacienteActionPerformed

    private void btNovoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoPacienteActionPerformed
        // TODO add your handling code here:
        DlgNovoPaciente dlgNovoPaciente = new DlgNovoPaciente(this, true, gestorAtendimento);
        dlgNovoPaciente.setLocationRelativeTo(this);
        atualizarTabelaPacientes();
    }//GEN-LAST:event_btNovoPacienteActionPerformed

    private void txtMedicoResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicoResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicoResponsavelActionPerformed

    private void btChamarProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChamarProximoActionPerformed
        if (gestorAtendimento.getAtendimentoAtual() != null) {
            JOptionPane.showMessageDialog(this, "Existe um Atendimento nao finalizado. Finalize o atendimento para\n chamar o proximo paciente.", "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // TODO add your handling code here:
            gestorAtendimento.chamarProximo(txtMedicoResponsavel.getText().trim());
            try {
           
            if (gestorAtendimento.proximoPaciente() != null) {
                btChamarProximo.setEnabled(true);
                lbNivelAtendimento3.setVisible(true);
                txtMedicoResponsavel.setVisible(true);
                Paciente proximoPaciente = gestorAtendimento.proximoPaciente();
            lbNomePacienteProximo.setText("Nome : " + proximoPaciente.getNome());
            lbIDPacienteProximo.setText("ID : " + proximoPaciente.getId());
            lbNivelPacienteProximo.setText("Nivel : " + proximoPaciente.getNivel());
            }else{
                lbNivelAtendimento3.setVisible(false);
                txtMedicoResponsavel.setVisible(false);
             
            }
            
        } catch (FilaVaziaException ex) {
            lbNomePacienteProximo.setText("Nao ha Pacientes aguardando atendimento." );
            lbIDPacienteProximo.setText("");
            lbNivelPacienteProximo.setText("");
            btChamarProximo.setEnabled(false);
             lbNivelAtendimento3.setVisible(false);
                txtMedicoResponsavel.setVisible(false);
        }
            atualizarAtendimentoEmCurso();
            if(gestorAtendimento.proximoPaciente() == null){
            btChamarProximo.setEnabled(false);
            lbNomePacienteProximo.setText("Nao ha Pacientes aguardando atendimento");
            }
        } catch (FilaVaziaException | TriagemPendenteException | PacienteInvalidoException ex) {
            lbNomePacienteProximo.setText("Nao ha Pacientes aguardando atendimento");
        }
        btFinalizarAtendimento.setEnabled(true);
        txtMedicoResponsavel.setText("");
    }//GEN-LAST:event_btChamarProximoActionPerformed

    private void btFinalizarAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarAtendimentoActionPerformed
        // TODO add your handling code here:
        String obser = areaTxtObservacoes.getText().trim();
        gestorAtendimento.finalizarAtendimento(obser);
        areaTxtObservacoes.setText("");
        try {
            if (gestorAtendimento.proximoPaciente() == null) {
                btChamarProximo.setEnabled(false);
            }
        } catch (FilaVaziaException ex) {
            btChamarProximo.setEnabled(false);
        }
  
        btFinalizarAtendimento.setEnabled(false);
        atualizarAtendimentoEmCurso();
    }//GEN-LAST:event_btFinalizarAtendimentoActionPerformed

    private void txtPesquisarHisoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarHisoricoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarHisoricoActionPerformed

    private void txtPesquisarHisoricoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarHisoricoKeyReleased
        // TODO add your handling code here:
        pesquisarHisorico();
    }//GEN-LAST:event_txtPesquisarHisoricoKeyReleased
    
     private void atualizarAtendimentoEmCurso(){
            Atendimento atendimentoAtual = gestorAtendimento.getAtendimentoAtual();
            if (atendimentoAtual == null) {
             lbNomePacienteAtendimento.setText("Paciente : -");
            lbNivelAtendimento.setText("Nivel : -");
            lbNivelAtendimento2.setText("");
          }else{
            lbNomePacienteAtendimento.setText("Paciente : " + atendimentoAtual.getPaciente().getNome());
            lbNivelAtendimento.setText("Nivel : " + atendimentoAtual.getPaciente().getNivel());
            lbNivelAtendimento2.setText(atendimentoAtual.getPaciente().getSintomas());
            }
     }
    
        private void atualizarTabelaPacientesTriagem(){
        DefaultTableModel model = (DefaultTableModel) tbPacienteTriagem.getModel();
        model.setRowCount(0);
        
        List<Paciente> pacientes = gestorAtendimento.listarNaoTriados();
        for(Paciente p: pacientes){
            Object [] linha = {p.getId(), p.getNome(), p.getIdade()};
            model.addRow(linha);
        }
    }
        
        private void pesquisarPacientesTriagem(){
            String texto = txtPesquisarPaciente.getText().trim().toLowerCase();
            DefaultTableModel modelo = (DefaultTableModel) tbPacienteTriagem.getModel();
            modelo.setRowCount(0);
            for(Paciente p: gestorAtendimento.listarNaoTriados()){
                if(texto.isEmpty() || p.getNome().toLowerCase().contains(texto)){
                
                modelo.addRow(new Object [] {p.getId(), p.getNome(), p.getIdade()});
            }
        }
        }
        
    private void atualizarTabelaPacientes(){
        DefaultTableModel model = (DefaultTableModel) tbPacientes.getModel();
        model.setRowCount(0);
        
        List<Paciente> pacientes = gestorAtendimento.listarPacientes();
        for(Paciente p: pacientes){
            Object [] linha = {p.getId(), p.getNome(), p.getIdade()};
            model.addRow(linha);
        }
    }
    
        private void atualizarTabelaPrioritaria(){
        DefaultTableModel model = (DefaultTableModel) tbFilaPrioritaria.getModel();
        model.setRowCount(0);
        
        List<Paciente> pacientes = gestorAtendimento.listarFilaPrioridade();
        int i=0;
        for(Paciente p: pacientes){
            i++;
            Object [] linha = {i, p.getNome(), p.getNivel()};
            model.addRow(linha);
        }
        lbTotalPrioridade.setText(String.valueOf(i));
    }
         private void atualizarTabelaNormal(){
        DefaultTableModel model = (DefaultTableModel) tbFilaNormal.getModel();
        model.setRowCount(0);
        
        List<Paciente> pacientes = gestorAtendimento.listarFilaNormal();
        int i=0;
        for(Paciente p: pacientes){
            i++;
            Object [] linha = {i, p.getNome(), p.getNivel()};
            model.addRow(linha);
        }
        lbTotalNormal.setText(String.valueOf(i));
    }
         
         private void configurarTabelasFilas(){
             DefaultTableCellRenderer rendererNivel =
        new DefaultTableCellRenderer() {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column
        );

        if (value instanceof NivelTriagem nivel) {

            switch (nivel) {
                case EMERGENCIA ->
                    setForeground(Color.RED);

                case MUITO_URGENTE ->
                    setForeground(Color.ORANGE);

                case URGENTE ->
                    setForeground(Color.YELLOW);

                case NORMAL ->
                    setForeground(Color.GREEN);

                case NAO_URGENTE ->
                    setForeground(Color.BLUE);
            }
        }
 

        return this;
    }
};
                    tbFilaPrioritaria.getColumnModel()
        .getColumn(2)
        .setCellRenderer(rendererNivel);

tbFilaNormal.getColumnModel()
        .getColumn(2)
        .setCellRenderer(rendererNivel);
         }
         
         private void pesquisarHisorico(){
          DefaultTableModel modelo = (DefaultTableModel) tbHistorico.getModel();
        modelo.setRowCount(0);
      String texto = txtPesquisarHisorico.getText().trim();
        for(Atendimento a : gestorAtendimento.getHistorico()){
            if(texto.isEmpty() || a.getPaciente().getNome().toLowerCase().contains(texto)){
            Paciente paciente = a.getPaciente();
            Object[] linha = {
                a.getId(),
                paciente.getNome(),
                a.getMedico(),
                paciente.getNivel(),
                formatarHora(a.getHoraInicio()),
                formatarHora(a.getHoraFim()),
                a.getDuracaoSegundos() + "s",
                a.getEstado()
            };
            modelo.addRow(linha);
            }
        }
                

        }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTxtObservacoes;
    private javax.swing.JTextArea areaTxtSintomasTriagem;
    private javax.swing.JButton btAtendimento;
    private javax.swing.JButton btChamarProximo;
    private javax.swing.JButton btDashboard;
    private javax.swing.JButton btFilas;
    private javax.swing.JButton btFinalizarAtendimento;
    private javax.swing.JButton btHistorico;
    private javax.swing.JButton btNovoPaciente;
    private javax.swing.JButton btPacientes;
    private javax.swing.JButton btRealizarTriagem;
    private javax.swing.JButton btTriagem;
    private javax.swing.JComboBox<NivelTriagem> comboNivelTriagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDescricaoAguardando;
    private javax.swing.JLabel lbDescricaoNormal;
    private javax.swing.JLabel lbEspaco2;
    private javax.swing.JLabel lbIDPacienteProximo;
    private javax.swing.JLabel lbNivelAtendimento;
    private javax.swing.JLabel lbNivelAtendimento1;
    private javax.swing.JLabel lbNivelAtendimento2;
    private javax.swing.JLabel lbNivelAtendimento3;
    private javax.swing.JLabel lbNivelAtendimento4;
    private javax.swing.JLabel lbNivelPacienteProximo;
    private javax.swing.JLabel lbNomePacienteAtendimento;
    private javax.swing.JLabel lbNomePacienteProximo;
    private javax.swing.JLabel lbNumeroAguardando;
    private javax.swing.JLabel lbNumeroNormal;
    private javax.swing.JLabel lbNumeroPrioridade;
    private javax.swing.JLabel lbPacienteTriagemID;
    private javax.swing.JLabel lbPacienteTriagemIdade;
    private javax.swing.JLabel lbPacienteTriagemIdade1;
    private javax.swing.JLabel lbPacienteTriagemNome;
    private javax.swing.JLabel lbPacienteTriagemSintomas;
    private javax.swing.JLabel lbPesquisarPacienteTriagem;
    private javax.swing.JLabel lbPesquisarPacienteTriagem1;
    private javax.swing.JLabel lbSah;
    private javax.swing.JLabel lbSubtitulo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTitulo1;
    private javax.swing.JLabel lbTitulo10;
    private javax.swing.JLabel lbTitulo11;
    private javax.swing.JLabel lbTitulo12;
    private javax.swing.JLabel lbTitulo13;
    private javax.swing.JLabel lbTitulo2;
    private javax.swing.JLabel lbTitulo3;
    private javax.swing.JLabel lbTitulo4;
    private javax.swing.JLabel lbTitulo5;
    private javax.swing.JLabel lbTitulo6;
    private javax.swing.JLabel lbTitulo7;
    private javax.swing.JLabel lbTituloAguardando;
    private javax.swing.JLabel lbTituloAtendimento1;
    private javax.swing.JLabel lbTituloAtendimento2;
    private javax.swing.JLabel lbTituloFilaNormal;
    private javax.swing.JLabel lbTituloFilaNormal2;
    private javax.swing.JLabel lbTituloFilaPrioridade;
    private javax.swing.JLabel lbTituloFilaPrioridade2;
    private javax.swing.JLabel lbTituloNormal;
    private javax.swing.JLabel lbTituloPrioridae;
    private javax.swing.JLabel lbTotalAtendimentos;
    private javax.swing.JLabel lbTotalNormal;
    private javax.swing.JLabel lbTotalPrioridade;
    private javax.swing.JLabel lblDescricaoPrioridade;
    private javax.swing.JPanel pnAtendimentoMenu;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnBotoesPacienteTriagem;
    private javax.swing.JPanel pnCaabecalhoHistorico;
    private javax.swing.JPanel pnCabecalho;
    private javax.swing.JPanel pnCabecalhoAtendimento;
    private javax.swing.JPanel pnCabecalhoFilas;
    private javax.swing.JPanel pnCabecalhoFilasPrioritaria;
    private javax.swing.JPanel pnCabecalhoFilasPrioritaria1;
    private javax.swing.JPanel pnCabecalhoFilasPrioritaria5;
    private javax.swing.JPanel pnCabecalhoPaciente;
    private javax.swing.JPanel pnCabecalhoTriagem;
    private javax.swing.JPanel pnCardAguardando;
    private javax.swing.JPanel pnCardNormal;
    private javax.swing.JPanel pnCardPrioridade;
    private javax.swing.JPanel pnContainerTiagemForm;
    private javax.swing.JPanel pnConteudo;
    private javax.swing.JPanel pnConteudoClientes;
    private javax.swing.JPanel pnConteudoDashboard;
    private javax.swing.JPanel pnConteudoFilas;
    private javax.swing.JPanel pnConteudoFilas2;
    private javax.swing.JPanel pnConteudoFilas3;
    private javax.swing.JPanel pnConteudoTriagem;
    private javax.swing.JPanel pnDashboard;
    private javax.swing.JPanel pnFilaNormal;
    private javax.swing.JPanel pnFilaNormal2;
    private javax.swing.JPanel pnFilaPrioridade;
    private javax.swing.JPanel pnFilaPrioridade2;
    private javax.swing.JPanel pnFilas;
    private javax.swing.JPanel pnFilasContainerConteudo;
    private javax.swing.JPanel pnFilasContainerConteudo2;
    private javax.swing.JPanel pnFormularioTriagem;
    private javax.swing.JPanel pnHistorico;
    private javax.swing.JPanel pnHistoricoContainer;
    private javax.swing.JPanel pnLogo;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnPaciente;
    private javax.swing.JPanel pnPacienteSelecionadoTriagem;
    private javax.swing.JPanel pnPesquisaPaciente;
    private javax.swing.JPanel pnPesquisaPacienteHisotrico;
    private javax.swing.JPanel pnPrincipal;
    private javax.swing.JPanel pnProximoPaciente;
    private javax.swing.JPanel pnResumo;
    private javax.swing.JPanel pnResumoContainer;
    private javax.swing.JPanel pnResumoContainer1;
    private javax.swing.JPanel pnSelecaoPacienteTriagem;
    private javax.swing.JPanel pnSidebar;
    private javax.swing.JPanel pnTabelaPacientes;
    private javax.swing.JPanel pnTabelaPacientesTriagem;
    private javax.swing.JPanel pnTotalAtendimentos;
    private javax.swing.JPanel pnTriagem;
    private javax.swing.JScrollPane scTabelaFilaNormal;
    private javax.swing.JScrollPane scTabelaFilaPrioritaria;
    private javax.swing.JScrollPane scTabelaPaciente;
    private javax.swing.JScrollPane scTabelaPacienteTriagem;
    private javax.swing.JScrollPane scTbHistorico;
    private javax.swing.JTable tbFilaNormal;
    private javax.swing.JTable tbFilaPrioritaria;
    private javax.swing.JTable tbHistorico;
    private javax.swing.JTable tbPacienteTriagem;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTextField txtMedicoResponsavel;
    private javax.swing.JTextField txtPesquisarHisorico;
    private javax.swing.JTextField txtPesquisarPaciente;
    // End of variables declaration//GEN-END:variables
}
