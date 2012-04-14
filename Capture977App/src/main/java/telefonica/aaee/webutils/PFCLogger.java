/**
 *
 */
package telefonica.aaee.webutils;

import java.util.logging.Logger;
//import org.apache.log4j.Logger;

/**
 * @author José Luis Herranz Martín
 *
 */
public class PFCLogger extends Logger {
//public class PFCLogger {

	/**
	 * @param name
	 * @param resourceBundleName
	 */

	/*
	public PFCLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}
	*/

	public PFCLogger(String name) {
		super(name, null);
		//Logger logger = Logger.getLogger(name);
	}

	public static Logger getLogger(String str){

		Logger logger = null;

		try {
	        // Create an appending file handler
//	        boolean append = true;
//	        FileHandler handler = new FileHandler("PFC.xml", append);

	        // Add to the desired logger
	        logger = Logger.getLogger(str);
//	        logger.addHandler(handler);
	    } catch (Exception e) {
	    }

	    return logger;
	}



}
