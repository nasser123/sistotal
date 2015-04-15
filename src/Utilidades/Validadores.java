/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Produção
 */
public class Validadores {

    public static boolean verificaNr(String valor) {
        if (valor != null) {
            try {
                Integer.parseInt(valor);
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean ehTelefone(String telefone) {
        if(telefone == null){
            return true;
        }
        try {
            Double nr = Double.parseDouble(telefone);
            Double limite = 9999999999.0;
            if (nr < limite) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No campo telefone/celular, informe até 10 digitos");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No campo telefone, digite apenas números");
        }
        return false;
    }
    
    public static boolean ehNome(String nome){
        if(nome == null){
            JOptionPane.showMessageDialog(null, "O campo nome não pode ser vazio");
            return false;
        }
        return true;
    
    }
    
    
}
