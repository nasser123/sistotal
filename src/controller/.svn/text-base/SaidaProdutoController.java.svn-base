/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import model.SaidaProduto;

/**
 *
 * @author Produção
 */
public class SaidaProdutoController {

    public void gravar(EntityManager em, SaidaProduto sp) {
        new Conexao(em).insert(sp);
    }
    
    public void gravarLista (EntityManager em, List<SaidaProduto> spList){
        for(int i = 0 ; i < spList.size() ; i++){
            gravar(em, spList.get(i));
        
        }
    
    
    }

    public void alterar(EntityManager em, SaidaProduto sp) {
        new Conexao(em).update(sp);
    }

    public void excluir(EntityManager em, SaidaProduto sp) {
        new Conexao(em).delete(sp);
    }

//    public Cliente getClienteByIdCliente(javax.persistence.Query query, EntityManager em, Integer idCodigo) {
//        SaidaProduto sp = null;
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
