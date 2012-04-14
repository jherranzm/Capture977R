/**
 *
 */
package telefonica.aaee.dao.factory;

import java.util.logging.Logger;

import telefonica.aaee.dao.objects.IDB977RDAO;
import telefonica.aaee.webutils.AAEEModPlanas;
import telefonica.aaee.webutils.PFCLogger;

/**
 * @author t130796
 *
 */
public abstract class DB977RDAOFactory {

	final private static Logger LOGGER = PFCLogger.getLogger("telefonica.aaee.dao.factory.DB977RDAOFactory");

	/**
	 * método que devuelve la factoria en función del tipo de 
	 * base de datos utilizada.
	 * 
	 * @param databaseType
	 * @return
	 */
	public static DB977RDAOFactory getDAOFactory(final String databaseType){
		
		DB977RDAOFactory ret = null;
		if(databaseType.equals(AAEEModPlanas.DRIVER_MANAGER_MYSQL)){
			LOGGER.info(AAEEModPlanas.DRIVER_MANAGER_MYSQL);
			ret = new DriverManagerMySQLDB977RDAOFactory();
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public abstract IDB977RDAO getDBIdb977rdao();


}
