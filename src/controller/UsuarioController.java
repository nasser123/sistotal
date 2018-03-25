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

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Usuario;
import Utilidades.ConnectionFactory;
import Utilidades.Senhas;
import Utilidades.Variaveis;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 *
 * @author Nasser
 */
public class UsuarioController {

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    public static EntityManager entity;

    public UsuarioController() {
        this.entity = ConnectionFactory.getEntityManager();
    }

    public boolean inserir(Object usuario) throws SQLException {
        if (usuario instanceof Usuario) {
            Usuario u = (Usuario) usuario;
            if (!existeUsuario(u.getUsername()) && dadosValidos(u)) {
                entity.getTransaction().begin();
                entity.persist(u);
                entity.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso.");
                return true;
            }
        }
        return false;
    }

    private boolean dadosValidos(Usuario u) {
        if (u.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo 'nome' não pode ser vazio");
            return false;
        } else if (u.getSenha().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo 'senha' não pode ser vazio");
            return false;
        } else if (u.getUsername().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo 'username' não pode ser vazio");
            return false;
        }
        return true;

    }

    public boolean inserir(String nome, String email, String username, char[] senha) throws SQLException {
        Usuario u = new Usuario(nome, email, username);

        if (!existeUsuario(u.getUsername())) {
            u.setEmail(email);

            if (!entity.getTransaction().isActive()) {
                entity.getTransaction().begin();
            }
            entity.persist(u);
            entity.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verifique os dados digitados");
        return false;
    }

    public boolean excluir(Object usuario) throws SQLException {
        if (usuario instanceof Usuario) {
            Usuario u = (Usuario) usuario;
            if (!entity.getTransaction().isActive()) {
                entity.getTransaction().begin();
                entity.remove(u);
                try {
                    entity.getTransaction().commit();
                } catch (javax.persistence.RollbackException | org.eclipse.persistence.exceptions.DatabaseException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel excluir o usuário" + '\n' + ex.getMessage(), "Exclusão de Usuario", 0);
                    return false;
                }
                
                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso.");
                return true;
            }
        }

        return false;
    }

    private boolean existeUsuario(String usuario) {
        Usuario u = null;
        List usuarios = entity.createNamedQuery("Usuario.findByUsername").setParameter("username", usuario).getResultList();
        if (!usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Já existe cadastro com esse usuario.");
            return true;
        }
        return false;
    }

    public Usuario pesquisarPorId(int id) throws SQLException {
        Usuario u = null;
        List usuarios = entity.createNamedQuery("Usuario.findByIdusuario").setParameter("idusuario", id).getResultList();
        if (!usuarios.isEmpty()) {
            u = (Usuario) usuarios.get(0);
            return u;
        }
        return null;
    }

    private List<Usuario> getUsuarioBanco(String usuario) {
        return entity.createNamedQuery("Usuario.findByUsername").setParameter("username", usuario).getResultList();

    }

    public Usuario pesquisarPorUsuario(String usuario) throws SQLException {
        Usuario u = null;
        List usuarios = entity.createNamedQuery("Usuario.findByUsuario").setParameter("usuario", usuario).getResultList();
        if (!usuarios.isEmpty()) {
            u = (Usuario) usuarios.get(0);
            return u;
        }
        JOptionPane.showMessageDialog(null, "Usuário digitado não existe.");
        return null;
    }

    public boolean alterar(Object objeto, boolean mensagem) throws SQLException {
        if (objeto instanceof Usuario) {
            Usuario u = (Usuario) objeto;
            if (dadosValidos(u)) {
                if (!entity.getTransaction().isActive()) {
                    entity.getTransaction().begin();
                }
                entity.merge(u);

                entity.getTransaction().commit();
                if (mensagem) {
                    JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso.");
                }
                return true;
            }
        }
        return false;
    }

    public List<? extends Object> pesquisarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<? extends Object> pesquisarTodosOrdenadoPor(String criterioOrdenamento) throws SQLException {
        Query query = entity.createNativeQuery("Select * from Usuario order by " + criterioOrdenamento, Usuario.class
        );
        List usuarios = query.getResultList();

        if (!usuarios.isEmpty()) {
            return usuarios;
        }

        return null;
    }

    /*
     */
    public boolean verificaLogin(String usuario, String senha) throws SQLException, NoSuchAlgorithmException {
        if (senha != null) {
//            transforma a senha texto em md5
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            String s = new BigInteger(1, m.digest()).toString(16);

            List<Usuario> usuarios = getUsuarioBanco(usuario);
            boolean verifica = false;
            if (usuarios != null) {
                for (Usuario usuario2 : usuarios) {
                    if (usuario2 != null) {
                        if (s.equals(usuario2.getSenha())) {
                            verifica = true;
                            Variaveis.setUsuario(usuario2);
                            break;
                        }
                    }
                }
            }
            return verifica;
        }
        return false;

    }

    public Usuario pesquisarPorId(Integer id) throws SQLException {
        Usuario usu = new Usuario();
        Integer idUsuario = id;
        Query query = entity.createNamedQuery("Usuario.findByIdcliente");
        query.setParameter("idusuario", idUsuario);
        if (!query.getResultList().isEmpty()) {
            usu = (Usuario) query.getResultList().get(0);
            entity.getTransaction().begin();
            entity.refresh(usu);
            entity.getTransaction().commit();
            return usu;
        }
        return null;
    }
}
