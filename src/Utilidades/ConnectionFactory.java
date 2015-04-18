package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.ini4j.Profile.Section;

/**
 *
 * @author Usuario
 */
public class ConnectionFactory {

    private static Connection connection;
    private static Connection connectionNoDatabase;
    private static EntityManager entityManager;

    public static Connection getConnection() {
        if (connection == null) {
            Section config = ConfigurationFactory.getConfiguration();
            
            try {
                //aqui colocamos os dados de acesso ao banco
                if (ConfigurationFactory.DATABASE.equalsIgnoreCase("mysql")) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    connection = DriverManager.getConnection("jdbc:mysql://" + ConfigurationFactory.DBHOST + ":" + ConfigurationFactory.DBPORT + "/" + ConfigurationFactory.DATABASE + "?autoReconnect=true&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8",
                            ConfigurationFactory.DBUSER, ConfigurationFactory.DBPASSWORD);
                }
            } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    public static Connection getConnectionWithNoDatabase() {
        if (connectionNoDatabase == null) {
            Section config = ConfigurationFactory.getConfiguration();
            try {
                //aqui colocamos os dados de acesso ao banco
                if (ConfigurationFactory.DATABASE.equalsIgnoreCase("mysql")) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    connectionNoDatabase = DriverManager.getConnection("jdbc:mysql://" + ConfigurationFactory.DBHOST + ":3306/?autoReconnect=true&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8",
                            ConfigurationFactory.DBUSER, ConfigurationFactory.DBPASSWORD);
                }
            } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connectionNoDatabase;
    }

    public static EntityManager getEntityManager()  {
        Section config = ConfigurationFactory.getConfiguration();
        Map prop = new HashMap();
        if (entityManager == null) {
            EntityManagerFactory emf;
            prop.put("javax.persistence.jdbc.url", "jdbc:mysql://" + ConfigurationFactory.DBHOST + ":" + ConfigurationFactory.DBPORT + "/" + ConfigurationFactory.DATABASE);
            prop.put("javax.persistence.jdbc.password", ConfigurationFactory.DBPASSWORD);
            prop.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            prop.put("javax.persistence.jdbc.user", ConfigurationFactory.DBUSER);
            emf = Persistence.createEntityManagerFactory("SistotalPU", prop);
            try {
                entityManager = emf.createEntityManager();
            } catch (Exception e) {
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Sem acesso ao banco de dados");
            }

            return entityManager;
        } else {

            return entityManager;
        }
    }

}
