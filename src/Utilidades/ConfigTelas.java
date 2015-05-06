/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.AWTKeyStroke;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Define configurações padrão das telas
 *
 * @author nasser
 */
public class ConfigTelas {

    private JFrame janela;
    private Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
    private int larg;
    private int alt;
    private JPanel panel;
    public String titulo;

    /**
     * @param janela informa a janela que está sendo configurada
     * @param larg informa a largura da tela em pixels (padrão: 1024)
     * @param alt informa a altura da tela em pixels (padrão: 680)
     */
    public ConfigTelas(JFrame janela, int larg, int alt) {
        this.janela = janela;
        this.larg = larg;
        this.alt = alt;

    }

    public ConfigTelas(JPanel jp, int larg, int alt) {
        this.larg = larg;
        this.alt = alt;
        this.panel = jp;

    }

    public ConfigTelas(JFrame jf) {
        this.larg = jf.getWidth();
        this.alt = jf.getHeight();
        this.titulo = getTitulo();

    }

    public ConfigTelas(JPanel jp) {
//        this.larg = jp.getWidth();
//        this.alt = jf.getHeight();
        this.titulo = getTitulo();

    }

    public ConfigTelas(JDialog jd) {
//        this.larg = jp.getWidth();
//        this.alt = jf.getHeight();
        this.titulo = getTitulo();
    }

    public void carregaIcone(JFrame jf) {
        BufferedImage ico = null;
        try {
            ico = ImageIO.read(jf.getClass().getResource("/sistotal/icone_totaltech.png"));
            jf.setIconImage(ico);
        } catch (IllegalArgumentException iae) {
            System.out.println("Erro ao carregar icone!");
        } catch (java.lang.NullPointerException npe) {
            System.out.println("Erro ao carregar icone!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar icone!");
        }

    }

    public void carregarConfig(JFrame jf) {
        this.carregaIcone(jf);

        //faz com que a tecla ENTER funcione conforme a tecla TAB
        HashSet conj = new HashSet(jf.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        jf.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        jf.setResizable(false);
        jf.setTitle(getTitulo());
        this.setLookAndFeel();

    }

    public void setLookAndFeel(){
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ConfigTelas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ConfigTelas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ConfigTelas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(ConfigTelas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
    
    }
    public static String getTitulo() {
        try {
            //Section config = ConfigurationFactory.getConfiguration();
            ConfigurationFactory cf = new ConfigurationFactory("main");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigTelas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ("Sistotal em " + ConfigurationFactory.DBHOST + "/" + ConfigurationFactory.DATABASE);

    }

    public void configuraTabulacao(JPanel jp) {
        HashSet conj = new HashSet(jp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        jp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

    }
    private void configuraTabulacao(JFrame jf) {
        HashSet conj = new HashSet(jf.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        jf.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

    }

}
