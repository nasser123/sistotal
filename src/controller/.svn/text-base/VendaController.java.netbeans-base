/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import javax.persistence.EntityManager;
import model.Venda;

/**
 *
 * @author Produção
 */
public class VendaController {

    public void gravar(EntityManager em, Venda v) {
        //if (v.verificaPagamento()) {
            new Conexao(em).insert(v);
     //   }

    }

    public void alterar(EntityManager em, Venda v) {
        new Conexao(em).update(v);
    }

    public void excluir(EntityManager em, Venda v) {
        new Conexao(em).delete(v);
    }
//    public Cliente getClienteByIdCliente(javax.persistence.Query query, EntityManager em, Integer idCodigo) {
//        Venda v = null;
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
