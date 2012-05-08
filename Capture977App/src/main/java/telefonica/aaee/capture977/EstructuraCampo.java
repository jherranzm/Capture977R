/**
 * 
 */
package telefonica.aaee.capture977;

/**
 * @author t130796
 *
 */
public class EstructuraCampo {

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

	/**
	 * @return the nombreCampo
	 */
	public String getNombreCampo() {
		return nombreCampo;
	}

	/**
	 * @param nombreCampo the nombreCampo to set
	 */
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	/**
	 * @return the tipoCampo
	 */
	public String getTipoCampo() {
		return tipoCampo;
	}

	/**
	 * @param tipoCampo the tipoCampo to set
	 */
	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	/**
	 * @return the formatoCampo
	 */
	public String getFormatoCampo() {
		return formatoCampo;
	}

	/**
	 * @param formatoCampo the formatoCampo to set
	 */
	public void setFormatoCampo(String formatoCampo) {
		this.formatoCampo = formatoCampo;
	}

	/**
	 * @return the posicionCampo
	 */
	public String getPosicionCampo() {
		return posicionCampo;
	}

	/**
	 * @param posicionCampo the posicionCampo to set
	 */
	public void setPosicionCampo(String posicionCampo) {
		this.posicionCampo = posicionCampo;
	}

	/**
	 * @return the longitudCampo
	 */
	public String getLongitudCampo() {
		return longitudCampo;
	}

	/**
	 * @param longitudCampo the longitudCampo to set
	 */
	public void setLongitudCampo(String longitudCampo) {
		this.longitudCampo = longitudCampo;
	}

	/**
	 * @return the repetidoCampo
	 */
	public String getRepetidoCampo() {
		return repetidoCampo;
	}

	/**
	 * @param repetidoCampo the repetidoCampo to set
	 */
	public void setRepetidoCampo(String repetidoCampo) {
		this.repetidoCampo = repetidoCampo;
	}

	/**
	 * @return the auxCampo
	 */
	public String getAuxCampo() {
		return auxCampo;
	}

	/**
	 * @param auxCampo the auxCampo to set
	 */
	public void setAuxCampo(String auxCampo) {
		this.auxCampo = auxCampo;
	}

}
