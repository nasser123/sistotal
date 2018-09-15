package Utilidades;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
//import view.TelaProgresso;

/**
 * Classe com métodos utilitários para executar e abrir relatórios.
 *
 * @author David Buzatto
 */
public class ReportUtils {

    /**
     * Abre um relatório usando uma conexão como datasource.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param conexao Conexão utilizada para a execução da query.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    //private static TelaProgresso telaProgresso;
    private static JasperPrint print;
    private static String tituloRelatorio;
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao) throws JRException {

        
        tituloRelatorio = titulo;

       
        print = JasperFillManager.fillReport(
                inputStream, parametros, conexao);
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando uma conexão.
         */
        
        
        
        try {
            // abre o JasperPrint em um JFrame
            viewReportFrame(titulo, print);
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ReportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource) throws JRException {

//        telaProgresso = new TelaProgresso();
//        telaProgresso.setVisible(true);
        

        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource);

        try {
            // abre o JasperPrint em um JFrame
            viewReportFrame(titulo, print);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Cria um JFrame para exibir o relatório representado pelo JasperPrint.
     *
     * @param titulo Título do JFrame.
     * @param print JasperPrint do relatório.
     */
    private static void viewReportFrame(String titulo, JasperPrint print) throws InterruptedException {

        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        
        //JRViewer viewer = new JRViewer(print);
//
//        // cria o JFrame
//        JFrame frameRelatorio = new JFrame(titulo);
//        
//        // adiciona o JRViewer no JFrame
//        frameRelatorio.add(viewer, BorderLayout.CENTER);
//
//        // configura o tamanho padrão do JFrame
//        frameRelatorio.setSize(700, 500);
//
//        // maximiza o JFrame para ocupar a tela toda.
//       // frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//        // configura a operação padrão quando o JFrame for fechado.
//        frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        //frameRelatorio.setAlwaysOnTop(true);
//
//        // exibe o JFrame
//        
//        frameRelatorio.setLocationRelativeTo(null);
//        //frameRelatorio.setAlwaysOnTop(true);
//        
//        frameRelatorio.setVisible(true);
//        frameRelatorio.requestFocusInWindow();
        
//        telaProgresso.dispose();

//        JasperViewer viewer = new JasperViewer(print, false);
          JRViewer viewer = new JRViewer(print);
//        viewer.toFront();
 //       viewer.setVisible(true);

  

        JDialog dialogRelatorio = new JDialog();
        
        // adiciona o JRViewer no JFrame
        dialogRelatorio.add(viewer, BorderLayout.CENTER);

        // configura o tamanho padrão do JFrame
        dialogRelatorio.setSize(1024, 700);
        dialogRelatorio.toFront();
        

        // maximiza o JFrame para ocupar a tela toda.
       // frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // configura a operação padrão quando o JFrame for fechado.
        dialogRelatorio.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //frameRelatorio.setAlwaysOnTop(true);

        // exibe o JFrame
        
        dialogRelatorio.setLocationRelativeTo(null);
        //frameRelatorio.setAlwaysOnTop(true);
        
        dialogRelatorio.setVisible(true);
        dialogRelatorio.requestFocusInWindow();
//        
////        telaProgresso.dispose();






    }



}
