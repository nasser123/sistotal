/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import Utilidades.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.TipoSaida;

/**
 *
 * @author Produção
 */
public class TipoSaidaController {

    private EntityManager entity;
    
    public TipoSaidaController() {
        this.entity = ConnectionFactory.getEntityManager();
    }
    
    public void gravar(TipoSaida ts) {
        new Conexao(entity).insert(ts);
    }

    public void alterar(TipoSaida ts) {
        new Conexao(entity).update(ts);
    }

    public void excluir(TipoSaida ts) {
        new Conexao(entity).delete(ts);
    }

    public TipoSaida getTipoSaidaByIdTipoSaida(javax.persistence.Query query, Integer idTipoSaida) {
        TipoSaida ts = null;
        Integer codigo = idTipoSaida;
        List<TipoSaida> resultado;

        query = entity.createNamedQuery("TipoSaida.findByIdtipoSaida");
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
