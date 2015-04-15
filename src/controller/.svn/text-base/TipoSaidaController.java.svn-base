/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Produto;
import model.TipoSaida;

/**
 *
 * @author Produção
 */
public class TipoSaidaController {

    public void gravar(EntityManager em, TipoSaida ts) {
        new Conexao(em).insert(ts);
    }

    public void alterar(EntityManager em, TipoSaida ts) {
        new Conexao(em).update(ts);
    }

    public void excluir(EntityManager em, TipoSaida ts) {
        new Conexao(em).delete(ts);
    }

    public TipoSaida getTipoSaidaByIdTipoSaida(javax.persistence.Query query, EntityManager em, Integer idTipoSaida) {
        TipoSaida ts = null;
        Integer codigo = idTipoSaida;
        List<TipoSaida> resultado;

        query = em.createNamedQuery("TipoSaida.findByIdtipoSaida");
        query.setParameter("idtipoSaida", codigo);
        resultado = query.getResultList();

        try {
            ts = resultado.get(0);
        } catch (IndexOutOfBoundsException iofe) {
            JOptionPane.showMessageDialog(null, "Tipo de saída não encontrado");
        }
        return ts;
    }
}
