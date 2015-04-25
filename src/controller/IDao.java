package controller;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface comum a todas as classes desse pacote
 *
 * @author thiago
 */
public interface IDao {

    /**
     * Insere uma entidade no banco de dados.
     * Retorna a própria entidade com seu identificador.
     * Pode disparar um {@link SQLException}
     * 
     * @param objeto
     * @return a entidade com seu identificador
     * teste git
     */
    public boolean inserir(Object objeto) throws SQLException;

    
    public boolean alterar(Object objeto) throws SQLException;

    public boolean excluir(Object objeto) throws SQLException;

    public Object pesquisarPorId(Integer id) throws SQLException;

    public List<? extends Object> pesquisarTodos() throws SQLException;

    public List<? extends Object> pesquisarTodosOrdenadoPor(String criterioOrdenamento) throws SQLException;
}
