/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ConnectionFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                if (!existeCliente(c)) {
                    entity.getTransaction().begin();
                    entity.persist(c);
                    entity.getTransaction().commit();
                } else {
                    JOptionPane.showMessageDialog(null, "Já existe cliente com esse nome.");
                    return false;
                }
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
                if (!existeCliente(c)) {
                    entity.getTransaction().begin();
                    entity.merge(c);
                    entity.getTransaction().commit();
                } else {
                    JOptionPane.showMessageDialog(null, "Já existe cliente com esse nome.");
                    return false;
                }
                JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean excluir(Object cliente) throws SQLException {
        if (cliente instanceof Cliente) {
            Cliente c = (Cliente) cliente;
            if (c.getOrdemServicoList().isEmpty()) {
                entity.getTransaction().begin();
                entity.remove(c);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não pode ser excluido.");
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Cliente pesquisarPorId(Integer id) throws SQLException {
        Cliente cli = new Cliente();
        Integer idCliente = id;
        Query query = entity.createNamedQuery("Cliente.findByIdcliente");
        query.setParameter("idcliente", idCliente);
        if (!query.getResultList().isEmpty()) {
            cli = (Cliente) query.getResultList().get(0);
            entity.getTransaction().begin();
            entity.refresh(cli);
            entity.getTransaction().commit();
            return cli;
        }
        return null;
    }

    @Override
    public List<? extends Object> pesquisarTodos() throws SQLException {
        List<Cliente> clienteList = new ArrayList();
        Query query = entity.createNamedQuery("Cliente.findAll");
        if (!query.getResultList().isEmpty()) {
            clienteList = query.getResultList();
            return clienteList;
        }
        return null;

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

    private boolean existeCliente(Cliente cliente) {
        List<Cliente> clienteList = new ArrayList();
        Query query = entity.createNamedQuery("Cliente.findByNome");
        query.setParameter("nome", cliente.getNome());
        if (!query.getResultList().isEmpty()) {
            if (query.getResultList().size() == 1) {
                Cliente c = (Cliente) query.getSingleResult();
                if (c.getNome().equalsIgnoreCase(cliente.getNome())) {
                    return false;
                } else {
                    return true;
                }
            }
            return true;
        }
        return false;

    }

}
