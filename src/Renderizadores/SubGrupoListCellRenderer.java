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
import model.Subgrupo;

public class SubGrupoListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Subgrupo) {
            Subgrupo sg = (Subgrupo) value;
            setText(sg.getDescricao());
        }
        return this;
    }
}
