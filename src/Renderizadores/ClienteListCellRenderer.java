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
import model.Cliente;

public class ClienteListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Cliente) {
            Cliente cli = (Cliente) value;
            setText(cli.getCelular().toString());
            //setBackground(Color.red);
        }
        return this;
    }
}
