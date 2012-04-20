/**
 * 
 */
package telefonica.aaee.exceptions;


/**
 * @author Usuario
 *
 */
public class ErrorEnLaInicializacionException extends Exception {

	public ErrorEnLaInicializacionException(String mensaje) {
		System.err.println(mensaje);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
