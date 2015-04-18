/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ConnectionFactory;
import Utilidades.Variaveis;
import model.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Nasser
 */
public class UsuarioController implements IDao {
    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    public static EntityManager entity;

    public UsuarioController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    
    @Override
    public boolean inserir(Object usuario) throws SQLException {
        if (usuario instanceof Usuario) {
            Usuario u = (Usuario) usuario;
            if (!existeUsername(u.getUsername())) {
                entity.getTransaction().begin();
                entity.persist(u);
                entity.getTransaction().commit();

                return true;
            }
        }
        return false;
    }
    
    public boolean inserir(String nome, String email, String username, String senha) throws SQLException {
            Usuario u = new Usuario(nome, email, username, senha);
            
            if (!existeUsername(u.getUsername())) {
                entity.getTransaction().begin();
                entity.persist(u);
                entity.getTransaction().commit();
                return true;
            }
        return false;
    }

    @Override
    public boolean excluir(Object usuario) throws SQLException {
        if (usuario instanceof Usuario) {
            Usuario s = (Usuario) usuario;
            entity.getTransaction().begin();
            entity.remove(s);
            entity.getTransaction().commit();

            return true;
        }
        return false;
    }

    private boolean existeUsername(String username) {
        Usuario u = null;
        List usuarios = entity.createNamedQuery("Usuario.findByUsername").setParameter("username", username).getResultList();
        if (!usuarios.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Usuario pesquisarPorId(int id) throws SQLException {
        Usuario u = null;
        List usuarios = entity.createNamedQuery("Usuario.findByIdusuario").setParameter("idusuario", id).getResultList();
        if (!usuarios.isEmpty()) {
            u = (Usuario) usuarios.get(0);
            return u;
        }

        return null;
    }

    @Override
    public boolean alterar(Object objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> pesquisarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> pesquisarTodosOrdenadoPor(String criterioOrdenamento) throws SQLException {
        Query query = entity.createNativeQuery("Select * from Usuario order by "  + criterioOrdenamento, Usuario.class);
        List usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios;
        }
        return null;
    }

    public List<Usuario> pesquisarTodosPor(String colunaPesquisa, String atributoPesquisa) throws SQLException {
        Query query = entity.createNativeQuery("Select * from Usuario where " + colunaPesquisa + " = \"" + atributoPesquisa + "\"", Usuario.class);
        List usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios;
        }
        return null;
    }
    private List<Usuario> getUsuarioBanco(String usuario) {
        return entity.createNamedQuery("Usuario.findByUsername").setParameter("username", usuario).getResultList();

    }
    /*
     * Realiza o teste de login
     */

    public boolean verificaLogin(String username, String senha) throws NoSuchAlgorithmException {
        if (senha != null) {
//            transforma a senha texto em md5
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            String s = new BigInteger(1, m.digest()).toString(16);

            List<Usuario> usuarios = getUsuarioBanco(username);
            boolean verifica = false;
            if (usuarios != null) {
                for (Usuario usuario : usuarios) {
                    if (usuario != null) {
                        if (s.equals(usuario.getSenha())) {
                            verifica = true;
                            Variaveis.setUsuario(usuario);
                            break;
                        }
                    }
                }
            }
            return verifica;
        }
        return false;
    }
}
