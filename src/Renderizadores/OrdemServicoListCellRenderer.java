/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderizadores;

/**
 *
 * @author nasser
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.Fabricante;
import model.OrdemServico;

public class OrdemServicoListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof OrdemServico) {
            OrdemServico os = (OrdemServico) value;
            //setText(os.getDescricao());
            setBackground(Color.red);
        }
        return this;
    }
}
