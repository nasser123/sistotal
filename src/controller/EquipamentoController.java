/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ConnectionFactory;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Equipamento;

/**
 *
 * @author Nasser
 */
public class EquipamentoController implements IDao {

    private EntityManager entity;

    public EquipamentoController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    @Override
    public boolean inserir(Object equipamento) throws SQLException {
        if (equipamento instanceof Equipamento) {
            Equipamento e = (Equipamento) equipamento;
//            if (!existeUsuario(c.getUsuario()) && dadosValidos(c)) {
            if (ehValido(e)) {
                entity.getTransaction().begin();
                entity.persist(e);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Equipamento salvo com sucesso.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean alterar(Object equipamento) throws SQLException {
        if (equipamento instanceof Equipamento) {
            Equipamento e = (Equipamento) equipamento;
//            if (!existeUsuario(c.getUsuario()) && dadosValidos(c)) {
            if (ehValido(e)) {
                entity.getTransaction().begin();
                entity.merge(e);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Equipamento salvo com sucesso.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean excluir(Object objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object pesquisarPorId(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> pesquisarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> pesquisarTodosOrdenadoPor(String criterioOrdenamento) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean ehValido(Equipamento equipamento) {
        boolean validado = true;
        return true;
    }

    

}
