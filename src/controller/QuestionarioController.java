/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ConnectionFactory;
import Utilidades.Validadores;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import model.Questionario;

/**
 *
 * @author Nasser
 */
public class QuestionarioController implements IDao {

    private EntityManager entity;

    public QuestionarioController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    @Override
    public boolean inserir(Object questionario) throws SQLException {
        if (questionario instanceof Questionario) {
            Questionario que = (Questionario) questionario;
//            os = setaValoresNulos(os);
            if (ehValido(que)) {
                entity.getTransaction().begin();
                entity.persist(que);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Questionario salvo com sucesso.");
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean alterar(Object questionario, boolean mensagem) throws SQLException {
        if (questionario instanceof Questionario) {
            Questionario que = (Questionario) questionario;
//            if (!existeUsuario(c.getUsuario()) && dadosValidos(c)) {
            if (ehValido(que)) {
                entity.getTransaction().begin();
                entity.merge(que);
                entity.getTransaction().commit();
                if (mensagem) {
                    JOptionPane.showMessageDialog(null, "OQuestionario salvo com sucesso.");
                }
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
    public Questionario pesquisarPorId(Integer id) throws SQLException {
        Questionario que = new Questionario();
        Integer idQuestionario = id;
        Query query = entity.createNamedQuery("Questionario.findByIdquestionario");
        query.setParameter("idquestionario", idQuestionario);
        if (!query.getResultList().isEmpty()) {
            que = (Questionario) query.getResultList().get(0);
            entity.getTransaction().begin();
            entity.refresh(que);
            entity.getTransaction().commit();
            return que;
        }
        return null;
    }

    public Questionario pesquisarPorId(String id) throws SQLException {

        if (Validadores.verificaNr(id)) {
            Integer idOrdem = Integer.parseInt(id);
            Questionario que = new Questionario();
            Query query = entity.createNamedQuery("Questionario.findByIdquestionario");
            query.setParameter("idquestionario", idOrdem);
            if (!query.getResultList().isEmpty()) {
                que = (Questionario) query.getResultList().get(0);
                entity.getTransaction().begin();
                entity.refresh(que);
                entity.getTransaction().commit();
                return que;
            }
        }
        return null;
    }

    @Override
    public List<? extends Object> pesquisarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> pesquisarTodosOrdenadoPor(String criterioOrdenamento) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean ehValido(Questionario questionario) {
        if (questionario.getClienteIdcliente() != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente");
            return false;
        }
    }

    

   

//    public Cliente getClienteByIdCliente(javax.persistence.Query query, EntityManager em, Integer idCodigo) {
//        Cliente c = null;
//        Integer codigo = idCodigo;
//        List<Cliente> resultado;
//
//        query = em.createNamedQuery("Cliente.findByIdcliente");
//        query.setParameter("idcliente", codigo);
//        resultado = query.getResultList();
//
//        try {
//            c = resultado.get(0);
//        } catch (IndexOutOfBoundsException iofe) {
//            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
//        }
//        return c;
//    }
}
