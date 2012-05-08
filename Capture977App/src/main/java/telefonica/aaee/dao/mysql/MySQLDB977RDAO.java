/**
 *
 */
package telefonica.aaee.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;

import telefonica.aaee.dao.factory.DriverManagerMySQLDB977RDAOFactory;
import telefonica.aaee.dao.objects.IDB977RDAO;
import telefonica.aaee.webutils.AAEEModPlanas;
import telefonica.aaee.webutils.PFCLogger;

/**
 * @author t130796
 * 
 */
public class MySQLDB977RDAO implements IDB977RDAO {

	private Logger logger = PFCLogger.getLogger(this.getClass()
			.getCanonicalName());

	private void closeAll(Connection con, PreparedStatement preparedStatement,
			ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			logger.severe(AAEEModPlanas.SEP_VERTICAL + "ERROR:SQLException:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public String callStoredProcBorrarDatosAcuerdo(String acuerdo) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String ret = "";
		try {
			con = DriverManagerMySQLDB977RDAOFactory.getConnection();
			String proc = "{ call BorrarDatosAcuerdo(?, ?) }";
			CallableStatement cs = con.prepareCall(proc);
			
			cs.setString(1, acuerdo);
			cs.registerOutParameter(2, Types.VARCHAR);
			
			cs.execute();
			
			ret = cs.getString(2);
			
		} catch (SQLException e) {
			logger.severe(AAEEModPlanas.SEP_VERTICAL + "ERROR:SQLException:"
					+ e.getMessage());
			ret = "SQLException:" + e.getMessage();
		} catch (Exception e) {
			logger.severe(AAEEModPlanas.SEP_VERTICAL + "ERROR:Exception:"
					+ e.getMessage());
			ret = "Exception:" + e.getMessage();
		} finally {
			closeAll(con, preparedStatement, resultSet);
		}

		return ret;
	}

}
