/**
 * 
 */
package telefonica.aaee.capture977;

/**
 * @author t130796
 *
 */
public class TipoRegistro {

		private String codigoRegistro = "";
		private int numBloques = 0;
		private int numCampos = 0;
		private Bloque[] bloques = new Bloque[3];
		
		
		
		
		public String toString(){
			String ret = "";
			
			ret += codigoRegistro;
			for(int j = 0; j < numBloques; j++){
				ret += "Bloque : " + j;
				ret += bloques[j].toString() + "\n";
			}
			
			return ret;
		}




		/**
		 * @return the codigoRegistro
		 */
		public String getCodigoRegistro() {
			return codigoRegistro;
		}




		/**
		 * @param codigoRegistro the codigoRegistro to set
		 */
		public void setCodigoRegistro(String codigoRegistro) {
			this.codigoRegistro = codigoRegistro;
		}




		/**
		 * @return the numBloques
		 */
		public int getNumBloques() {
			return numBloques;
		}




		/**
		 * @param numBloques the numBloques to set
		 */
		public void setNumBloques(int numBloques) {
			this.numBloques = numBloques;
		}




		/**
		 * @return the numCampos
		 */
		public int getNumCampos() {
			return numCampos;
		}




		/**
		 * @param numCampos the numCampos to set
		 */
		public void setNumCampos(int numCampos) {
			this.numCampos = numCampos;
		}




		/**
		 * @return the bloques
		 */
		public Bloque[] getBloques() {
			return bloques;
		}




		/**
		 * @param bloques the bloques to set
		 */
		public void setBloques(Bloque[] bloques) {
			this.bloques = bloques;
		}
		
}
