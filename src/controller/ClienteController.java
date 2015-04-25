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

/**
 *
 * @author Nasser
 */
public class ClienteController implements IDao {

    private EntityManager entity;

    public ClienteController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    @Override
    public boolean inserir(Object cliente) throws SQLException {
        if (cliente instanceof Cliente) {
            Cliente c = (Cliente) cliente;
            if (ehValido(c)) {
                entity.getTransaction().begin();
                entity.persist(c);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean alterar(Object cliente) throws SQLException {
        if (cliente instanceof Cliente) {
            Cliente c = (Cliente) cliente;
//            if (!existeUsuario(c.getUsuario()) && dadosValidos(c)) {
            if (ehValido(c)) {
                entity.getTransaction().begin();
                entity.merge(c);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso.");
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

    private boolean ehValido(Cliente cliente) {
        boolean validado = true;
        if (cliente.getNome() == null || cliente.getNome() == "") {
            JOptionPane.showMessageDialog(null, "Campo Nome vazio");
            return false;
        }
        return true;
    }

    public Cliente getClienteByIdCliente(javax.persistence.Query query, EntityManager em, Integer idCodigo) {
        Cliente c = null;
        Integer codigo = idCodigo;
        List<Cliente> resultado;

        query = em.createNamedQuery("Cliente.findByIdcliente");
        query.setParameter("idcliente", codigo);
        resultado = query.getResultList();

        try {
            c = resultado.get(0);
        } catch (IndexOutOfBoundsException iofe) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
        }
        return c;
    }

}
