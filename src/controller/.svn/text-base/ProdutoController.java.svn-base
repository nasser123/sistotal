/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Produto;

/**
 *
 * @author Produção
 */
public class ProdutoController {

    public void gravar(EntityManager em, Produto p) {
        new Conexao(em).insert(p);
    }

    public void alterar(EntityManager em, Produto p) {
        new Conexao(em).update(p);
    }

    public void excluir(EntityManager em, Produto p) {
        new Conexao(em).delete(p);
    }

    public Produto getProdutoByCodBarras(javax.persistence.Query query, EntityManager em, String codBarras) {
        Produto p = null;
        String cb = codBarras;
        List<Produto> resultado;

        if (!cb.equals("") ) {
            query = em.createNamedQuery("Produto.findByCodigobarras");
            query.setParameter("codigobarras", cb);
            resultado = query.getResultList();

            try {
                p = resultado.get(0);
            } catch (IndexOutOfBoundsException | NullPointerException  iofe) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado");
            }


        }
        return p;
    }
}
