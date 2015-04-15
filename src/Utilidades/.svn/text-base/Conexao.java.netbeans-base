package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * Classe que faz a conexão no banco de dados. Contém a Url de conexão, o
 * usuário e a senha do banco de dados. Ela herda da classe JdbcConnectionSource
 * 
 * @author thiago
 * @see JdbcConnectionSource
 */
public class Conexao {

    public static String USUARIO = "root";
    public static String SENHA = "123456";
    public static String PORTA = "3306";
    public static String NOME_BANCO = "sistotal";
    public static String SGBD = "mysql";
    public static String SERVIDOR = "localhost";
    public static String CONECTOR = "jdbc";
    public static String DRIVER_MYSQL_JDBC = "com.mysql.jdbc.Driver";
    private EntityManager em;

    public Conexao(EntityManager em) {
        this.em = em;
    }

    public void insert(Object obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public void update(Object obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public void delete(Object obj) {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_MYSQL_JDBC);
        return DriverManager.getConnection(getUrl(), USUARIO, SENHA);
    }

    public static void closeConnection(Connection conn,
            Statement stmt, ResultSet rs) {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getUrl() {
        return CONECTOR + ":" + SGBD + "://" + SERVIDOR + ":" + PORTA + "/" + NOME_BANCO;
    }
}
