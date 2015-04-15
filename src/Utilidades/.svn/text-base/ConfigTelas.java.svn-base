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
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    }

    public void carregaIcone(JFrame jp) {
        BufferedImage ico = null;
        try {
            ico = ImageIO.read(jp.getClass().getResource("/imagens/agenda_tit.png"));
            jp.setIconImage(ico);
        } catch (IllegalArgumentException iae) {
            System.out.println("Erro ao carregar icone!");
        } catch (java.lang.NullPointerException npe) {
            System.out.println("Erro ao carregar icone!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar icone!");
        }

    }

    public void carregarConfig(JFrame jp) {
        
        //faz com que a tecla ENTER funcione conforme a tecla TAB
        HashSet conj = new HashSet(jp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        jp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);





        /*
         * this.janela.setBounds((tela.width - this.janela.getSize().width) / 2,
         * (tela.height - this.janela.getSize().height) / 2, larg, alt);
         *
         * this.janela.setLocation((tela.width - this.janela.getSize().width) /
         * 2, (tela.height - this.janela.getSize().height) / 2);
         */


        //  this.janela.setBounds((tela.width - this.janela.getSize().width) / 2, y, width, height);

        /*
         * System.out.println(tela.width + "\n" + tela.height + "\n" +
         * this.janela.getSize().width + "\n" +
                this.janela.getSize().height);
         */


        jp.setTitle("Sistema de Convênio Papa Tudo");

        jp.setResizable(false);


        //Altera o icone padrão do java
        BufferedImage ico = null;
        try {
            ico = ImageIO.read(jp.getClass().getResource("/imagens/vendas64.png"));
            jp.setIconImage(ico);
        } catch (IllegalArgumentException iae) {
            System.out.println("Erro ao carregar icone!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar icone!");
        }

    }

    
}
