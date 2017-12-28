/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ConnectionFactory;
import Utilidades.Datas;
import Utilidades.Validadores;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.OrdemServico;

/**
 *
 * @author Nasser
 */
public class OrdemServicoController implements IDao {

    private EntityManager entity;

    public OrdemServicoController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    @Override
    public boolean inserir(Object ordemServico) throws SQLException {
        if (ordemServico instanceof OrdemServico) {
            OrdemServico os = (OrdemServico) ordemServico;
//            os = setaValoresNulos(os);
            if (ehValido(os)) {
                entity.getTransaction().begin();
                entity.persist(os);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Ordem de Serviço salva com sucesso.");
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean alterar(Object ordemServico, boolean mensagem) throws SQLException {
        if (ordemServico instanceof OrdemServico) {
            OrdemServico os = (OrdemServico) ordemServico;
//            if (!existeUsuario(c.getUsuario()) && dadosValidos(c)) {
            if (ehValido(os)) {
                entity.getTransaction().begin();
                entity.merge(os);
                entity.getTransaction().commit();
                if (mensagem) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço salva com sucesso.");
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
    public OrdemServico pesquisarPorId(Integer id) throws SQLException {
        OrdemServico os = new OrdemServico();
        Integer idOrdem = id;
        Query query = entity.createNamedQuery("OrdemServico.findByIdordemServico");
        query.setParameter("idordemServico", idOrdem);
        if (!query.getResultList().isEmpty()) {
            os = (OrdemServico) query.getResultList().get(0);
            entity.getTransaction().begin();
            entity.refresh(os);
            entity.getTransaction().commit();
            return os;
        }
        return null;
    }

    public OrdemServico pesquisarPorId(String id) throws SQLException {

        if (Validadores.verificaNr(id)) {
            Integer idOrdem = Integer.parseInt(id);
            OrdemServico os = new OrdemServico();
            Query query = entity.createNamedQuery("OrdemServico.findByIdordemServico");
            query.setParameter("idordemServico", idOrdem);
            if (!query.getResultList().isEmpty()) {
                os = (OrdemServico) query.getResultList().get(0);
                entity.getTransaction().begin();
                entity.refresh(os);
                entity.getTransaction().commit();
                return os;
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

    private boolean ehValido(OrdemServico ordemServico) {
        if (ordemServico.getIdcliente() != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente");
            return false;
        }
    }

    public List<OrdemServico> pesquisarTelaInicial(String auxSql, String auxSqlPago) throws SQLException {

        entity.getEntityManagerFactory().getCache().evictAll();
        entity.clear();

        Query query = entity.createNativeQuery("Select idordem_servico from ordem_servico where idsituacao_os in (" + auxSql + ") and pago in (" + auxSqlPago + ") order by idordem_servico desc");
        List<OrdemServico> osList = new ArrayList();
        OrdemServico osTemp = new OrdemServico();
        if (!query.getResultList().isEmpty()) {
            System.out.println("Inicio: " + Datas.getTime(Datas.getCurrentTime()));
            List<Integer> idList = query.getResultList();
            entity.getTransaction().begin();
            for (int i = 0; i < idList.size(); i++) {
                osTemp = entity.find(OrdemServico.class, idList.get(i));
                osList.add(osTemp);
            }
            entity.getTransaction().commit();
            System.out.println("Fim: " + Datas.getTime(Datas.getCurrentTime()));
            return osList;
        }
        return null;

    }

    public List<OrdemServico> pesquisarTelaInicial(String auxSql) throws SQLException {
        Query query = entity.createNativeQuery("Select * from ordemservico where idsituacao_os in (" + auxSql + ")");

        List<OrdemServico> osList = query.getResultList();
        return osList;

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
