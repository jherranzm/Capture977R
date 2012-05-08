/**
 *
 */
package telefonica.aaee.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import telefonica.aaee.dao.mysql.MySQLDB977RDAO;
import telefonica.aaee.dao.objects.IDB977RDAO;
import telefonica.aaee.webutils.ConfigUtils;
import telefonica.aaee.webutils.PFCLogger;


/**
 * @author t130796
 *
 */
public class DriverManagerMySQLDB977RDAOFactory extends DB977RDAOFactory {

	final private static Logger LOGGER = PFCLogger.getLogger("telefonica.aaee.dao.factory.DB977RMySQLDAOFactory");
	
	
	public static Connection getConnection(){
		
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"
			    + ConfigUtils.configDB.getProperty("host") + "/" 
			    + ConfigUtils.configDB.getProperty("db") + "", 
			    ConfigUtils.configDB.getProperty("user"),
			    ConfigUtils.configDB.getProperty("pass")
			    );
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Problemas con la localización de la clase.");
			LOGGER.severe(e.getMessage());
			
		} catch (SQLException e) {
			LOGGER.severe("Problemas con SQLException " + e.getErrorCode() + ":" + e.getSQLState());
			LOGGER.severe(e.getMessage());
			
		} catch (Exception e) {
			LOGGER.severe("Problemas  " + e.getMessage());
			LOGGER.severe(e.getMessage());
		}
	    
	    return conn;
		
	}


	@Override
	public IDB977RDAO getDBIdb977rdao() {
		return new MySQLDB977RDAO();
	}

}
