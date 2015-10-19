/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import Utilidades.ConnectionFactory;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Venda;

/**
 *
 * @author Produção
 */
public class VendaController implements IDao {

    private EntityManager entity;

    public VendaController() {
        this.entity = ConnectionFactory.getEntityManager();
    }


    public void alterar(EntityManager em, Venda v) {
        new Conexao(em).update(v);
    }

    public void excluir(EntityManager em, Venda v) {
        new Conexao(em).delete(v);
    }

    @Override
    public boolean inserir(Object objeto) throws SQLException {
        if (objeto instanceof Venda) {
            Venda vc = (Venda) objeto;
            entity.getTransaction().begin();
            entity.persist(vc);
            entity.getTransaction().commit();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel gravar a venda.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Venda gravada com sucesso.");
        return true;

    }

    @Override
    public boolean alterar(Object objeto, boolean mensagem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
