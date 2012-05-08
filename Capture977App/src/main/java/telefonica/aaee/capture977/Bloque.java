/**
 * 
 */
package telefonica.aaee.capture977;

/**
 * @author t130796
 *
 */
public class Bloque {

	private int numBloque = 0;
	private int numBloquePadre = 0;
	private int numEstructuras = 0;
	private EstructuraCampo[] estructuras = new EstructuraCampo[50];
	
	public String toString(){
		String ret = "[" +numBloque + ":" + numBloquePadre + ":" +numEstructuras + "]\n";
		
		for(int k=0; k<numEstructuras; k++){
			ret += estructuras[k]+"\n";
		}
		
		
		return ret;
	}

	/**
	 * @return the estructuras
	 */
	public EstructuraCampo[] getEstructuras() {
		return estructuras;
	}

	/**
	 * @param estructuras the estructuras to set
	 */
	public void setEstructuras(EstructuraCampo[] estructuras) {
		this.estructuras = estructuras;
	}

	/**
	 * @return the numBloque
	 */
	public int getNumBloque() {
		return numBloque;
	}

	/**
	 * @param numBloque the numBloque to set
	 */
	public void setNumBloque(int numBloque) {
		this.numBloque = numBloque;
	}

	/**
	 * @return the numBloquePadre
	 */
	public int getNumBloquePadre() {
		return numBloquePadre;
	}

	/**
	 * @param numBloquePadre the numBloquePadre to set
	 */
	public void setNumBloquePadre(int numBloquePadre) {
		this.numBloquePadre = numBloquePadre;
	}

	/**
	 * @return the numEstructuras
	 */
	public int getNumEstructuras() {
		return numEstructuras;
	}

	/**
	 * @param numEstructuras the numEstructuras to set
	 */
	public void setNumEstructuras(int numEstructuras) {
		this.numEstructuras = numEstructuras;
	}
}
