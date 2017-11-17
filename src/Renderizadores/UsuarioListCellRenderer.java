/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderizadores;

/**
 *
 * @author nasser
 */
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.Usuario;

public class UsuarioListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Usuario) {
            Usuario usu = (Usuario) value;
            setText(usu.getNome().toString());
            //setBackground(Color.red);
        }
        return this;
    }
}
