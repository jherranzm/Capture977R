/**
 * 
 */
package telefonica.aaee.capture977;

/**
 * @author t130796
 *
 */
public class CamposBloque {

	private String nombreCampo = "";
	private String tipoCampo = "";
	private String formatoCampo = "";
	private String posicionCampo = "";
	private String longitudCampo = "";
	private String repetidoCampo = "";
	private String auxCampo = "";
	
	public String toString(){
		return "[" + nombreCampo + ";" + tipoCampo + ";" + formatoCampo + ";" + posicionCampo + ";" + longitudCampo + ";" + tipoCampo + ";" + repetidoCampo + ";" + auxCampo + "]";
	}

}
