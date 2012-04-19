/**
 * 
 */
package telefonica.aaee.capture977;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * @author t130796
 * 
 */
public class Split977 {


	final private static String SQL_QUERIES = "/Capture977R.SQLQueries.xml";
	final private static String CRLF = "\r\n";
	final private static String TAB = "\t";
	final private static String CODIFICACION_FICHERO_ORIGEN = "ISO-8859-1";
	
	private int[] posiciones = { 25, 1, 15, 4, 3, 1, 3 };

	private Hashtable<String, TipoRegistro> registros = new Hashtable<String, TipoRegistro>();
	private Hashtable<String, File> filesOut = new Hashtable<String, File>();
	private Hashtable<String, BufferedWriter> bwOut = new Hashtable<String, BufferedWriter>();
	private Hashtable<String, String> codigoRegistroExistente = new Hashtable<String, String>();
	private Hashtable<String, Vector<String>> camposPorTabla = new Hashtable<String, Vector<String>>();

	/*
	 * private int[][] pos_000000 = { {6,8,8,12,12,12,20,20,20,4},
	 * {40,18,70,30,20,5,8,70}, {2,65,40,40,7,40}, {12,5,7,8,5},
	 * {56,30,8,8,1,10} };
	 */
	private String fechaFactura = "";
	private String nombreFicheroOriginal = "";
	private String cifActual = "";
	/** Es el valor del CIF del registro 100000 */
	private String acuerdo = "";
	private String[] ficheros = {};
	
	private boolean borrarTablas = false;
	private boolean borrarAcuerdo = true;
	private boolean detalleLlamadas = false;
	private boolean detalleLlamadasRI = false;

	/**
	 * 
	 */
	private String dbHost = "";
	private String dbName = "";
	private String dbUser = "";
	private String dbPass = "";

	private String directorio = null;

	private Properties sqlStatements = null;
	
	private long tiempoEmpleado = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Split977 sp = new Split977();
			sp.setAcuerdo(args[0]);
			String[] f = new String[args.length - 1];
			for (int k = 1; k < args.length; k++) {
				f[k - 1] = args[k];
			}
			sp.setFicheros(f);
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorio(path);
			System.out.println(sp.execute());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 */
	public String execute() {

		StringBuilder sb = new StringBuilder();

		try {
			
			String[] aProps = {
					"java.class.path"
					, "java.ext.dirs"
					, "java.library.path"
					, "path.separator"
					};
			for(String aProp : aProps){
				sb.append(aProp).append(":  [").append(System.getProperty(aProp)).append("]").append(CRLF);
			}
			sb.append("directorio:       [").append(getDirectorio()).append("]").append(CRLF);

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"
					+ getDbHost() + "/" + getDbName() + "", getDbUser(),
					getDbPass());
			conn.setAutoCommit(false);

			sqlStatements = new Properties();
			/**
			 * FileInputStream fis = new FileInputStream(
			 * "Capture977R.SQLQueries.xml"); sqlStatements.loadFromXML(fis);
			 */
			System.out.println(SQL_QUERIES);
			sb.append(SQL_QUERIES);
			InputStream XMLstream = getClass().getResourceAsStream( SQL_QUERIES);
			if (XMLstream == null) {
				throw new FileNotFoundException(
						"No se ha encontrado el fichero en el JAR: " +SQL_QUERIES);
			} else {
				System.err.println("XMLStream no es nulo...!");
				sb.append("XMLStream no es nulo...!");
			}
			sqlStatements.loadFromXML(XMLstream);

			Calendar ini = Calendar.getInstance();
			long lIni = ini.getTimeInMillis();
			sb.append(lIni);

			// acuerdo = args[0];
			sb.append("Acuerdo: ").append(acuerdo).append(CRLF);

			for (int k = 0; k < ficheros.length; k++) {
				sb.append("ficheros[").append(k).append("]: ").append(ficheros[k]).append(CRLF);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(ficheros[k])),
						CODIFICACION_FICHERO_ORIGEN));
				getEstructuraRegistros(in);
				// getEstructuraRegistros(ficheros[k]);
			}

			sb.append(createSQLCreateTables("createTables"));

			for (int k = 0; k < ficheros.length; k++) {
				sb.append("procesaFichero[" + k + "]: " + ficheros[k] + CRLF);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(ficheros[k])),
						CODIFICACION_FICHERO_ORIGEN));
				procesaFichero(in);
				// procesaFichero(ficheros[k]);
			}

			sb.append(createSQLCreateViews("incorporarDatos"));
			cerrarFicherosSalida();

			ScriptRunner runner = new ScriptRunner(conn, false, true);

			runner.runScript(new BufferedReader(new FileReader(
					"createTables.sql")));
			runner.runScript(new BufferedReader(new FileReader(
					"incorporarDatos.sql")));

			conn.close();

			Calendar fin = Calendar.getInstance();
			long lFin = fin.getTimeInMillis();
			sb.append((lFin - lIni) + CRLF);
			
			setTiempoEmpleado(lFin - lIni);
			
			sb.append("Número de tablas:" + camposPorTabla.size());
						
			
			Vector<String> v = new Vector<String>(camposPorTabla.keySet());
			Collections.sort(v);
			for (String nombreTabla : v) {
				sb.append("Nombre tabla:" + nombreTabla + CRLF);
		         Vector<String> nomCampos = camposPorTabla.get(nombreTabla);
		         for(String nomCampo : nomCampos){
		        	 sb.append( TAB + nomCampo + CRLF);
		         }

		     }
			

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			sb.append("FileNotFoundException:" + e.getMessage());
		} catch (Exception e) {
			sb.append("Exception:" + e.getMessage());
		}

		return sb.toString();

	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	private String pathComponent(String filename) {
		int i = filename.lastIndexOf(File.separator);
		return (i > -1) ? filename.substring(0, i) : filename;
	}

	/**
	 * 
	 * Genera un fichero de texto con nombre el parámetro filename 
	 * y extensión .sql
	 * 
	 * En el se incluyen los comandos que generarán dichas tablas.
	 * 
	 * Hay un atributo de la clase que indica si se ha de incluir o no
	 * la eliminación de tablas (DROP TABLE). Por defecto es FALSE.
	 * 
	 * @param fileName
	 */
	private String createSQLCreateTables(String fileName) {
		
		StringBuilder sb = new StringBuilder();

		File fileOut = null;// , fileOut2 = null;
		BufferedWriter out = null;// , out2 = null;

		String path = "";

		try {

			fileOut = new File(fileName + ".sql");
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileOut), CODIFICACION_FICHERO_ORIGEN));
			path = pathComponent(fileOut.getAbsolutePath());
			path = path.replaceAll("\\\\", "/");

			if (directorio == null)
				setDirectorio(path);

			if (borrarTablas) {
				StringBuilder SQLDropTableDefT000000 = new StringBuilder(
						"DROP TABLE IF EXISTS T000000;");
				out.write(SQLDropTableDefT000000.toString() + CRLF);
				out.write("COMMIT;" + CRLF);
				out.flush();
			}
			

			StringBuilder SQLCreateTableDefT000000 = new StringBuilder();
			SQLCreateTableDefT000000.append("CREATE TABLE IF NOT EXISTS T000000 (");
			SQLCreateTableDefT000000.append("	id int(11) NOT NULL AUTO_INCREMENT, ");
			SQLCreateTableDefT000000.append("	fichero VARCHAR(12) NOT NULL, ");
			SQLCreateTableDefT000000.append("	FECHA_FACTURA VARCHAR(8) NOT NULL, ");
			SQLCreateTableDefT000000.append("	CIF_CLIENTE_Clave VARCHAR(18) NOT NULL, ");
			SQLCreateTableDefT000000.append("	ACUERDO VARCHAR(18) NOT NULL ");
			SQLCreateTableDefT000000.append(",	PRIMARY KEY (id)");
			SQLCreateTableDefT000000.append(",	KEY (fichero, FECHA_FACTURA)");
			SQLCreateTableDefT000000.append(")	ENGINE=MyISAM;");

			out.write(SQLCreateTableDefT000000.toString() + CRLF);
			out.write("COMMIT;" + CRLF);
			out.flush();

			Set<String> sKeys = registros.keySet();
			Vector<String> vKeys = new Vector<String>(sKeys);
			Collections.sort(vKeys);

			
			for (Iterator<String> i = vKeys.iterator(); i.hasNext();) {
				String codigoRegistro = (String) i.next();

				// El registro 702010 es el detalle de llamadas...
				if (codigoRegistro.equals("702010") && !detalleLlamadas) {
					// no hacemos nada... sin detalle

				// El registro 702010 es el detalle de llamadas de RED INTELIGENTE...
				} else if (codigoRegistro.equals("702020") && !detalleLlamadasRI) {
					// no hacemos nada... sin detalle

				} else {

					TipoRegistro tr = registros.get(codigoRegistro);
					// recorremos cada Bloque

					// Se utiliza una tabla para
					Vector<String> nombresCampos = new Vector<String>();

					for (int iBloque = 0; iBloque < tr.getNumBloques(); iBloque++) {
						Bloque bloque = tr.getBloques()[iBloque];
						EstructuraCampo[] estructura = bloque.getEstructuras();

						StringBuilder SQLDropTableDef = null;
						if (borrarTablas) {
							SQLDropTableDef = new StringBuilder(
									"DROP TABLE IF EXISTS T" + codigoRegistro
											+ "_" + (iBloque + 1) + ";");
						}
						
						String nombreTabla = codigoRegistro + "_" + (iBloque + 1);

						StringBuilder SQLCreateTableDef = new StringBuilder();
						SQLCreateTableDef.append("CREATE TABLE IF NOT EXISTS T"
								+ codigoRegistro + "_" + (iBloque + 1) + " (");

						StringBuilder SQLCreateTableCamposComunes = new StringBuilder();
						SQLCreateTableCamposComunes
							.append(" fichero VARCHAR(12) NOT NULL, ")
							.append(" CampoClave VARCHAR(8) NOT NULL, ")
							.append(" FECHA_FACTURA VARCHAR(8) NOT NULL, ")
							.append(" CIF_CLIENTE_Clave VARCHAR(18) NOT NULL, ")
							.append(" ACUERDO VARCHAR(18) NOT NULL, ")
							.append(" CODIGO_REGISTRO_EXT VARCHAR(8) NOT NULL ");

						/*
						 * Utilizamos un vector para almacenar los nombres de los campos de las tablas
						 */
						Vector<String> camposEnTabla = new Vector<String>();
						camposEnTabla.clear();
						// campos comunes
						camposEnTabla.add("fichero");
						camposEnTabla.add("CampoClave");
						camposEnTabla.add("FECHA_FACTURA");
						camposEnTabla.add("CIF_CLIENTE_Clave");
						camposEnTabla.add("ACUERDO");
						camposEnTabla.add("CODIGO_REGISTRO_EXT");
						
						StringBuilder SQLCreateTableDefCampos = new StringBuilder();

						int ocurrencias = 0;
						for (int k = 0; k < bloque.getNumEstructuras(); k++) {

							/**
							 * Cambiamos los caracteres que pueden resultar complicados como nombres de campo
							 * en una tabla MySQL
							 */
							String nombreCampo = estructura[k].getNombreCampo()
									.trim().replace(" ", "_").replace("/_", "").replace(".", "");

							int longCampo = (new Integer(estructura[k].getLongitudCampo())).intValue();
							
							if (nombreCampo.startsWith("OCURR")) {

								ocurrencias++;
							} else {

								// Guardamos el campo en caso de que no esté en
								// la lista
								String _nombreCampo = "t" + (iBloque + 1) + "." + nombreCampo;
								if (!nombresCampos.contains(_nombreCampo)){
									nombresCampos.add(_nombreCampo);
									
									camposEnTabla.add(nombreCampo);
								}

								SQLCreateTableDefCampos.append(", ").append(nombreCampo);

								/**
								 * Añadimos la longitud y características en función del Tipo de Campo
								 */
								if (estructura[k].getTipoCampo().equals("N")) {
									SQLCreateTableDefCampos.append(" INT(11) DEFAULT 0 ");

								} else if (estructura[k].getTipoCampo().equals(
										"A")) {
									SQLCreateTableDefCampos.append(" VARCHAR(" + longCampo + ") DEFAULT NULL ");

								} else if (estructura[k].getTipoCampo().equals("I")) {
									// el contenido es numérico, ha de
									// considerarse como tal
									String formatoCampo = estructura[k].getFormatoCampo().trim();
									StringTokenizer st = new StringTokenizer(formatoCampo, ",");
									String m = st.nextToken();
									int im = (new Integer(m)).intValue();
									String d = st.nextToken();
									int id = (new Integer(d)).intValue();
									im += id;
									formatoCampo = im + "," + id;
									SQLCreateTableDefCampos.append(" DOUBLE(" + formatoCampo + ") DEFAULT 0 ");

								} else if (estructura[k].getTipoCampo().equals("F")) {
									SQLCreateTableDefCampos
											.append(" DATE DEFAULT NULL ");

								} else if (estructura[k].getTipoCampo().equals("D")) {
									SQLCreateTableDefCampos.append(" VARCHAR(" + longCampo + ") DEFAULT NULL ");

								} else if (estructura[k].getTipoCampo().equals("H")) {
									SQLCreateTableDefCampos
											.append(" TIME DEFAULT NULL ");

								} else {
									System.out.println(estructura[k].getTipoCampo()+ " No contemplado!");
								}// if

								/**
								 * Casos especiales en la tabla de detalle de llamadas Simulcom
								 */
								if (codigoRegistro.equals("702017")
										&& nombreCampo.equals("IMPORTE_VALOR_ANADIDO")
										&& iBloque == 0) {
									SQLCreateTableDefCampos.append(", ");
									SQLCreateTableDefCampos.append(" NUMERO_PARTICIPANTES_2 INT(11) DEFAULT 0 ");
									
									camposEnTabla.add("NUMERO_PARTICIPANTES_2");

								}

							}// if(nombreCampo.startsWith("OCURR"))

						}// for(int k = 0; k < bloque.getNumEstructuras(); k++)

						sb.append("ocurrencias:" + ocurrencias);

						if (codigoRegistro.equals("901000") && iBloque == 1) {
							SQLCreateTableDefCampos.append(", ");
							SQLCreateTableDefCampos .append(" TEXTO VARCHAR(180) ");
							
							camposEnTabla.add("TEXTO");
						}
						SQLCreateTableDef.append(SQLCreateTableCamposComunes.toString());
						SQLCreateTableDef.append(SQLCreateTableDefCampos.toString());
						SQLCreateTableDef.append(", KEY (CampoClave)");
						SQLCreateTableDef.append(")  ENGINE=MyISAM;");

						// out.write(SQLDropTableTemp.toString() + CRLF);
						if (borrarTablas) {
							out.write(SQLDropTableDef.toString() + CRLF);
						}
						// out.write(SQLCreateTableTemp + CRLF);
						out.write(SQLCreateTableDef.toString() + CRLF);

						out.write("COMMIT;" + CRLF);
						out.flush();
						
						camposPorTabla.put(nombreTabla, camposEnTabla);

					}// for(int iBloque = 0; iBloque < tr.getNumBloques();
						// iBloque++)

				}// if(codigoRegistro.equals("702010") && detalleLlamadas)

			}// for(Iterator<String> i = vKeys.iterator(); i.hasNext(); )
			out.close();
			// out2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();

	}

	/**
	 * 
	 * @param fileName
	 * @param fileName2
	 */
	private String createSQLCreateViews(String fileName2) {
		
		StringBuilder sb = new StringBuilder();

		File fileOut2 = null, fileOutIndex = null;
		BufferedWriter out2 = null, outIndex = null;

		String path = "";
		String loadDataInfile = "";
		String createIndex = "";

		try {
			fileOut2 = new File(fileName2 + ".sql");
			out2 = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(fileOut2), CODIFICACION_FICHERO_ORIGEN));
			fileOutIndex = new File("createIndex_01.sql");
			outIndex = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileOutIndex), CODIFICACION_FICHERO_ORIGEN));

			path = pathComponent(fileOut2.getAbsolutePath());
			path = path.replaceAll("\\\\", "/");

			if (directorio == null)
				setDirectorio(path);

			out2.write("DELETE FROM T000000 WHERE ");
			out2.write(" fichero = '" + nombreFicheroOriginal + "' ");
			out2.write(" and  acuerdo = '" + acuerdo + "';" + CRLF);
			out2.write("COMMIT;" + CRLF);
			out2.flush();
			
			loadDataInfile = "load data infile '"
					+ path
					+ "/000000.txt' into table T000000 "
					+ "fields terminated by ';' enclosed by '\"' "
					+ "(fichero, FECHA_FACTURA, CIF_CLIENTE_Clave, ACUERDO); ";
			out2.write("" + loadDataInfile + CRLF);
			out2.flush();

			Set<String> sKeys = codigoRegistroExistente.keySet();
			Vector<String> vKeys = new Vector<String>(sKeys);
			Collections.sort(vKeys);
			
			
			for (Iterator<String> i = vKeys.iterator(); i.hasNext();) {
				String codigoRegistro = (String) i.next();
				sb.append("codigoRegistro:[" + codigoRegistro + "]");

				/**
					 * 
					 */
				if (codigoRegistro.equals("000000")) {
					// nada
				} else if (codigoRegistro.equals("903000")) {
					// no hacemos nada...
				} else if (codigoRegistro.equals("702010") && !detalleLlamadas) {
					// no hacemos nada... sin detalle
				} else if (codigoRegistro.equals("702020")
						&& !detalleLlamadasRI) {
					// no hacemos nada... sin detalle
				} else {

					// System.out.println("codigoRegistro:[" + codigoRegistro +
					// "]");

					TipoRegistro tr = registros.get(codigoRegistro);
					// recorremos cada Bloque

					// Se utiliza una tabla para saber si se han utilizado o no el nombre de campo
					Vector<String> nombresCampos = new Vector<String>();

					for (int iBloque = 0; iBloque < tr.getNumBloques(); iBloque++) {
						Bloque bloque = tr.getBloques()[iBloque];
						EstructuraCampo[] estructura = bloque.getEstructuras();

						int ocurrencias = 0;
						for (int k = 0; k < bloque.getNumEstructuras(); k++) {

							String nombreCampo = estructura[k].getNombreCampo()
									.trim().replace(" ", "_").replace("/_", "").replace(".", "");

							if (nombreCampo.startsWith("OCURR")) {
								// SQLCreateTableTempCampos += ", " +
								// nombreCampo + "_" + ocurrencias + " " +
								// "VARCHAR(" + (longCampo+2) +
								// ") DEFAULT NULL ";
								ocurrencias++;
							} else {

								// Guardamos el campo en caso de que no esté en
								// la lista
								String _nombreCampo = "t" + (iBloque + 1) + "."
										+ nombreCampo;
								if (!nombresCampos.contains(_nombreCampo))
									nombresCampos.add(_nombreCampo);

							}// if(nombreCampo.startsWith("OCURR"))

						}// for(int k = 0; k < bloque.getNumEstructuras(); k++)
						
						sb.append("ocurrencias:" + ocurrencias);

						

						String strBloque = codigoRegistro + "_" + (iBloque + 1);

						/**
						 * String sqlInsertIntoTTablas =
						 * "insert into ttablas (acuerdo, tabla) values ('" +
						 * acuerdo + "', 't" + strBloque + "');" + CRLF;
						 * out2.write("" + sqlInsertIntoTTablas + CRLF);
						 */
						out2.write("COMMIT;" + CRLF);

						if(codigoRegistro.equals("601010") 
								|| codigoRegistro.equals("701010")){
							
							String strBloque2 = "6701010" + "_" + (iBloque + 1);
							String nombreTabla = "701010" + "_" + (iBloque + 1);
							
							String strListaCampos = getListaCampos(nombreTabla);
							
							loadDataInfile = "load data infile '"
								+ path
								+ "/"
								+ strBloque
								+ ".txt' into table T"
								+ strBloque2
								+ " fields terminated by  ';' enclosed by '\"' "
								+ strListaCampos
								+ ";"
								;
							
							sb.append(loadDataInfile);

						}else if (!(codigoRegistro.equals("901000") && iBloque == 2)) {

							String strListaCampos = getListaCampos(strBloque);
							
							loadDataInfile = "load data infile '"
									+ path
									+ "/"
									+ strBloque
									+ ".txt' into table T"
									+ strBloque
									+ " fields terminated by  ';' enclosed by '\"' "
									+ strListaCampos
									+ ";"
									;
							createIndex = "CREATE INDEX i" + strBloque
									+ " on T" + strBloque + "(campoClave);";
						}
						out2.write("" + loadDataInfile + CRLF);
						out2.write("COMMIT;" + CRLF);
						outIndex.write("" + createIndex + CRLF);
						outIndex.write("COMMIT;" + CRLF);
						out2.flush();
						outIndex.flush();

					}// for(int iBloque = 0; iBloque < tr.getNumBloques();
						// iBloque++)

					// if (borrarTablas){
					StringBuilder dropView = new StringBuilder(
							"DROP VIEW  IF EXISTS v" + codigoRegistro + ";");
					out2.write("" + dropView.toString() + CRLF);
					// }
					StringBuilder createView = new StringBuilder(
							"CREATE VIEW v" + codigoRegistro + " AS SELECT ");
					createView.append("	t1.fichero, ");
					createView.append("	t1.CampoClave, ");
					createView.append("	t1.FECHA_FACTURA , ");
					createView.append("	t1.CIF_CLIENTE_Clave, ");
					createView.append("	t1.Acuerdo, ");
					createView.append("	t1.CODIGO_REGISTRO_EXT, ");
					for (int k = 0; k < nombresCampos.size(); k++) {
						if (nombresCampos.get(k).equals("t3.TEXTO")) {
							createView.append("	t2.TEXTO");
						} else if (nombresCampos.get(k).equals("t1.CLIENTE")) {
							createView.append("	t1.CLIENTE ");
							if (!codigoRegistro.equals("100000")
									&& !codigoRegistro.equals("200000")) {
								createView.append(",");
								createView
										.append("	cuc.cliente_tipo_doc, cuc.cliente_cif, cuc.cliente_nombre ");
							}
						} else {
							createView.append(nombresCampos.get(k));
						}

						if (k < (nombresCampos.size() - 1))
							createView.append(", ");
					}
					if (codigoRegistro.equals("100000")
							|| codigoRegistro.equals("200000")) {
						createView
								.append(" FROM 977r.t")
								.append(codigoRegistro).append("_1 t1 ")
								.append(" left join 977r.t")
								.append(codigoRegistro)
								.append("_2 t2 ")
								.append("on t1.CampoClave = t2.CampoClave ")
								.append("and t1.fichero = t2.fichero ")
								.append("and t1.acuerdo = t2.acuerdo ")
								.append("and t1.fecha_factura = t2.fecha_factura ");
					} else if (codigoRegistro.equals("300000")) {
						createView
								.append(" FROM 977r.t")
								.append(codigoRegistro).append("_1 t1 ")
								.append(" left join 977r.t")
								.append(codigoRegistro)
								.append("_2 t2 ")
								.append("on t1.CampoClave = t2.CampoClave  ")
								.append("and t1.fichero = t2.fichero  ")
								.append("and t1.acuerdo = t2.acuerdo  ")
								.append("and t1.fecha_factura = t2.fecha_factura ")
								.append(" left join 977r.t")
								.append(codigoRegistro)
								.append("_3 t3 ")
								.append("on t1.CampoClave = t3.CampoClave ")
								.append("and t1.fichero = t3.fichero  ")
								.append("and t1.acuerdo = t3.acuerdo  ")
								.append("and t1.fecha_factura = t3.fecha_factura ")
								.append(" left join 977r.vCUC_CLIENTE cuc ")
								.append("on t1.cliente = cuc.cliente_cuc ");
					} else {
						createView
						.append(" FROM 977r.t")
								.append(codigoRegistro)
								.append("_1 t1 ")
								.append(" left join 977r.t")
								.append(codigoRegistro)
								.append("_2 t2 ")
								.append("on t1.CampoClave = t2.CampoClave ")
								.append("and t1.fichero = t2.fichero ")
								.append("and t1.acuerdo = t2.acuerdo ")
								.append("and t1.fecha_factura = t2.fecha_factura ")
								.append(" left join 977r.vCUC_CLIENTE cuc  ")
								.append("on t1.cliente = cuc.cliente_cuc ");
					}
					createView.append(";");
					out2.write("" + createView.toString() + CRLF);
					out2.write("COMMIT;" + CRLF);
					out2.flush();

					if (codigoRegistro.equals("200000")) {
						out2.write("drop view if exists 977r.vCUC_CLIENTE;" + CRLF);
						out2.write("COMMIT;" + CRLF);
						out2.write("create view 977r.vCUC_CLIENTE as ");
						out2.write("SELECT ");
						out2.write("  cliente as cliente_cuc, ");
						out2.write("  left(cif_cliente,1) as cliente_tipo_doc, ");
						out2.write("  right(cif_cliente,9) as cliente_cif, ");
						out2.write("  nombre_cliente as cliente_nombre ");
						out2.write("FROM 977r.v200000 v ");
						out2.write("group by cliente_cuc ");
						out2.write("order by cliente_cif;" + CRLF);
						out2.write("COMMIT;" + CRLF);

					}

				}// if(codigoRegistro.equals("702010") && detalleLlamadas)

			}// for(Iterator<String> i = vKeys.iterator(); i.hasNext(); )

			/**
			 * Procesamos las consultas que hay en el fichero
			 * Capture977R.SQLQueries.xml
			 */
			String[] comandos = { 
					"ActualizarTablas"
					
					
					// Los conceptos se inluyen en un SP que se llama 977R_ADDINCLUDE_ALL
					
					//, "SQL_Insert_Conceptos_601010"
					//, "SQL_Insert_Conceptos_701010"
					//, "SQL_Insert_Conceptos_6701010"

					//, "ActualizarTablas"
					//, "SQL_Insert_Trafico"
					//, "SQL_Insert_Trafico_Internacional" 
				};

			/**
			 * 
			 ,"SQL_Insert_Conceptos_601010" ,"SQL_Insert_Conceptos_701010"
			 */

			for (String comm : comandos) {
				sb.append("SQL:" + comm);
				String s2 = sqlStatements.getProperty(comm);
				sb.append(s2);
				String s2_res = Pattern.compile("--ACUERDO--").matcher(s2)
						.replaceAll(acuerdo);
				out2.write(s2_res);
				out2.write("COMMIT;" + CRLF);
				out2.flush();
			}

			out2.close();
			outIndex.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();

	}

	/**
	 * @param nombreTabla
	 * @return
	 */
	private String getListaCampos(String nombreTabla) {
		Vector<String> listaCampos = camposPorTabla.get(nombreTabla);
		String strListaCampos = " (";
		for(String _nombreCampo: listaCampos){
			strListaCampos += _nombreCampo + ", ";
		}
		strListaCampos = strListaCampos.substring(0, strListaCampos.length() - 2);
		strListaCampos += ") ";
		return strListaCampos;
	}

	/**
	 * 
	 * @param fileName
	 */
	private void procesaFichero(BufferedReader in) {
		try {
			int numLinea = 0;

			String line;
			line = in.readLine();
			while (true) {
				if (line == null) {
					break;
				}

				if (line.length() < 6) {
					break;
				}

				String codigoRegistro = line.substring(0, 6);
				
				System.out.println("procesaFichero:codigoRegistro:" + codigoRegistro);

				if (codigoRegistroExistente.get(codigoRegistro) == null)
					codigoRegistroExistente.put(codigoRegistro, codigoRegistro);

				String secuencial = line.substring(6, 6 + 8);
				if (codigoRegistro.equals("100000")) {
					// Extraemos el CIF
					cifActual = line.substring(129, 129 + 18);
				}
				// Recuperamos la estructura de la línea

				TipoRegistro tr = registros.get(codigoRegistro);
				if (tr == null) {
					// la primera linea es el registro 000000 y no esta
					// contemplado en los registros 903000
					if (codigoRegistro.equals("000000")) {
						tratarRegistro000000(line);
					}else{
						System.out.println("procesaFichero:codigoRegistro:" + codigoRegistro+" y TipoRegistro tr == null");
					}
				} else if (codigoRegistro.equals("901000")) {
					tratarRegistro901000(line);

				} else if (codigoRegistro.equals("702010")
						&& !isDetalleLlamadas()) {
					// no hacemos nada... sin detalle
				} else if (codigoRegistro.equals("702020")
						&& !isDetalleLlamadasRI()) {
					// no hacemos nada... sin detalle
				} else if (codigoRegistro.equals("000000")) {
					tratarRegistro000000(line);
				} else {
					// Los bloques se recorren 1 vez, a menos que el campo
					// OCURRENCIAS con longitud <>0 diga lo contrario
					int[] vecesBloque = { 1, 1, 1 };
					int posVecesBloque = 1;
					int pos = 0;
					// Recorremos los bloques del registro
					for (int iBloque = 0; iBloque < tr.getNumBloques(); iBloque++) {
						Bloque bloque = tr.getBloques()[iBloque];
						EstructuraCampo[] estructuras = bloque.getEstructuras();

						if (vecesBloque[iBloque] > 1) {
							for (int j = 0; j < vecesBloque[iBloque]; j++) {
								StringBuilder resultLine = new StringBuilder();
								resultLine
										.append("\"" + nombreFicheroOriginal
												+ "\";").append("\"")
										.append(secuencial).append("\";")
										.append("\"").append(fechaFactura)
										.append("\";").append("\"")
										.append(cifActual).append("\";")
										.append("\"").append(acuerdo)
										.append("\";").append("\"")
										.append(codigoRegistro).append("_")
										.append((iBloque + 1)).append("\";");

								// String fOutName = codigoRegistro
								// +"_"+(iBloque+1);
								String fOutName = getDirectorio() + "/"
										+ codigoRegistro + "_" + (iBloque + 1);
								BufferedWriter bwOut = getBROut(fOutName);
								for (int k = 0; k < bloque.getNumEstructuras(); k++) {
									int offset = (new Integer(
											estructuras[k].getLongitudCampo()))
											.intValue();
									if (offset > 0) {
										String tipoCampo = estructuras[k]
												.getTipoCampo();
										String formatoCampo = estructuras[k]
												.getFormatoCampo();
										String campo = "";
										/**
										 * Falla por longitud en algunos
										 * registros, por ejemplo 400000
										 */
										if (line.length() > (pos + offset)) {
											campo = line.substring(pos,
													pos += offset);
										} else {
											campo = line.substring(pos);
										}
										// System.out.println(campo);
										/**
        									 * 
        									 */
										campo = correccionCamposNumericos(
												tipoCampo, campo);
										if (tipoCampo.equals("I")) {
											Double d = extraeDouble(
													formatoCampo, campo);
											resultLine.append("" + d + ";");
										} else if (tipoCampo.equals("N")) {
											long l = extraeLong(campo);
											if (l == -999999) {
												System.out.println("ERROR: "
														+ line);
												l = 0;
											}
											resultLine.append("" + l + ";");
										} else if (tipoCampo.equals("F")) {
											if (campo.equals("00000000")) {
												campo = "25001231";
											}
											resultLine.append("" + campo + ";");
										} else {
											resultLine.append("\""
													+ ltrim(campo.trim())
													+ "\";");
										}
									} else {
										System.out.println(offset);
									}

								}
								// System.out.println(resultLine);
								bwOut.write("" + resultLine.toString() + CRLF);
								bwOut.flush();
							}

						} else {
							StringBuilder resultLine = new StringBuilder();
							resultLine
									.append("\"" + nombreFicheroOriginal
											+ "\";")
									.append("\"" + secuencial + "\";")
									.append("\"" + fechaFactura + "\";")
									.append("\"" + cifActual + "\";")
									.append("\"" + acuerdo + "\";")
									.append("\"" + codigoRegistro + "_"
											+ (iBloque + 1) + "\";");
							String fOutName = getDirectorio() + "/"
									+ codigoRegistro + "_" + (iBloque + 1);
							BufferedWriter bwOut = getBROut(fOutName);
							for (int k = 0; k < bloque.getNumEstructuras(); k++) {
								int offset = (new Integer(
										estructuras[k].getLongitudCampo()))
										.intValue();
								if (offset > 0) {
									String tipoCampo = estructuras[k]
											.getTipoCampo();
									String campo = line.substring(pos,
											pos += offset);
									String formatoCampo = estructuras[k]
											.getFormatoCampo();
									campo = correccionCamposNumericos(
											tipoCampo, campo);
									if (tipoCampo.equals("I")) {
										Double d = extraeDouble(formatoCampo,
												campo);
										resultLine.append("" + d + ";");
									} else if (tipoCampo.equals("N")) {
										long l = extraeLong(campo);
										if (l == -999999) {
											System.out
													.println("ERROR: " + line);
											l = 0;
										}
										resultLine.append("" + l + ";");
									} else if (tipoCampo.equals("F")) {
										if (campo.equals("00000000")) {
											campo = "25001231";
										}
										resultLine.append("" + campo + ";");
									} else {
										resultLine.append("\""
												+ ltrim(campo.trim()) + "\";");
									}
									if (estructuras[k].getNombreCampo()
											.substring(0, 5).equals("OCURR")
											&& offset > 0 && iBloque == 0) {
										vecesBloque[posVecesBloque] = (new Integer(
												campo)).intValue();
										posVecesBloque++;
									}
								}
							}
							// System.out.println(resultLine);
							bwOut.write("" + resultLine.toString() + CRLF);
							bwOut.flush();

						}

					}// for(int iBloque = 0; iBloque < tr.getNumBloques();
						// iBloque++){
				}// if(tr == null){

				numLinea++;
				line = in.readLine();

			}// while(true){

			in.close();
			
			System.out.println("numLinea:" + numLinea);



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fileName
	 */
	@SuppressWarnings("unused")
	private void procesaFichero(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), CODIFICACION_FICHERO_ORIGEN));

			int numLinea = 0;

			String line;
			line = in.readLine();
			while (true) {
				if (line == null) {
					break;
				}

				if (line.length() < 6) {
					break;
				}

				String codigoRegistro = line.substring(0, 6);

				if (codigoRegistroExistente.get(codigoRegistro) == null)
					codigoRegistroExistente.put(codigoRegistro, codigoRegistro);

				String secuencial = line.substring(6, 6 + 8);
				if (codigoRegistro.equals("100000")) {
					// Extraemos el CIF
					cifActual = line.substring(129, 129 + 18);
				}
				// Recuperamos la estructura de la línea

				TipoRegistro tr = registros.get(codigoRegistro);
				if (tr == null) {
					// la primera linea es el registro 000000 y no esta
					// contemplado en los registros 903000
					if (codigoRegistro.equals("000000")) {
						tratarRegistro000000(line);
					}
				} else if (codigoRegistro.equals("000000")) {
					tratarRegistro000000(line);
				} else if (codigoRegistro.equals("901000")) {
					tratarRegistro901000(line);

				} else if (codigoRegistro.equals("702010")
						&& !isDetalleLlamadas()) {
					// no hacemos nada... sin detalle
				} else if (codigoRegistro.equals("702020")
						&& !isDetalleLlamadasRI()) {
					// no hacemos nada... sin detalle
				} else {
					// Los bloques se recorren 1 vez, a menos que el campo
					// OCURRENCIAS con longitud <>0 diga lo contrario
					int[] vecesBloque = { 1, 1, 1 };
					int posVecesBloque = 1;
					int pos = 0;
					// Recorremos los bloques del registro
					for (int iBloque = 0; iBloque < tr.getNumBloques(); iBloque++) {
						Bloque bloque = tr.getBloques()[iBloque];
						EstructuraCampo[] estructuras = bloque.getEstructuras();

						if (vecesBloque[iBloque] > 1) {
							for (int j = 0; j < vecesBloque[iBloque]; j++) {
								StringBuilder resultLine = new StringBuilder();
								resultLine.append("\"" + nombreFicheroOriginal
										+ "\";");
								resultLine.append("\"" + secuencial + "\";");
								resultLine.append("\"" + fechaFactura + "\";");
								resultLine.append("\"" + cifActual + "\";");
								resultLine.append("\"" + acuerdo + "\";");
								resultLine.append("\"" + codigoRegistro + "_"
										+ (iBloque + 1) + "\";");

								// String fOutName = codigoRegistro
								// +"_"+(iBloque+1);
								String fOutName = getDirectorio() + "/"
										+ codigoRegistro + "_" + (iBloque + 1);
								BufferedWriter bwOut = getBROut(fOutName);
								for (int k = 0; k < bloque.getNumEstructuras(); k++) {
									int offset = (new Integer(
											estructuras[k].getLongitudCampo()))
											.intValue();
									if (offset > 0) {
										String tipoCampo = estructuras[k]
												.getTipoCampo();
										String formatoCampo = estructuras[k]
												.getFormatoCampo();
										String campo = "";
										/**
										 * Falla por longitud en algunos
										 * registros, por ejemplo 400000
										 */
										if (line.length() > (pos + offset)) {
											campo = line.substring(pos,
													pos += offset);
										} else {
											campo = line.substring(pos);
										}
										// System.out.println(campo);
										/**
										 * 
										 */
										campo = correccionCamposNumericos(
												tipoCampo, campo);
										if (tipoCampo.equals("I")) {
											Double d = extraeDouble(
													formatoCampo, campo);
											resultLine.append("" + d + ";");
										} else if (tipoCampo.equals("N")) {
											long l = extraeLong(campo);
											if (l == -999999) {
												System.out.println("ERROR: "
														+ line);
												l = 0;
											}
											resultLine.append("" + l + ";");
										} else if (tipoCampo.equals("F")) {
											if (campo.equals("00000000")) {
												campo = "25001231";
											}
											resultLine.append("" + campo + ";");
										} else {
											resultLine.append("\""
													+ ltrim(campo.trim())
													+ "\";");
										}
									} else {
										System.out.println(offset);
									}

								}
								// System.out.println(resultLine);
								bwOut.write("" + resultLine.toString() + CRLF);
								bwOut.flush();
							}

						} else {
							StringBuilder resultLine = new StringBuilder();
							resultLine.append("\"" + nombreFicheroOriginal
									+ "\";");
							resultLine.append("\"" + secuencial + "\";");
							resultLine.append("\"" + fechaFactura + "\";");
							resultLine.append("\"" + cifActual + "\";");
							resultLine.append("\"" + acuerdo + "\";");
							resultLine.append("\"" + codigoRegistro + "_"
									+ (iBloque + 1) + "\";");
							String fOutName = getDirectorio() + "/"
									+ codigoRegistro + "_" + (iBloque + 1);
							BufferedWriter bwOut = getBROut(fOutName);
							for (int k = 0; k < bloque.getNumEstructuras(); k++) {
								int offset = (new Integer(
										estructuras[k].getLongitudCampo()))
										.intValue();
								if (offset > 0) {
									String tipoCampo = estructuras[k]
											.getTipoCampo();
									String campo = line.substring(pos,
											pos += offset);
									String formatoCampo = estructuras[k]
											.getFormatoCampo();
									campo = correccionCamposNumericos(
											tipoCampo, campo);
									if (tipoCampo.equals("I")) {
										Double d = extraeDouble(formatoCampo,
												campo);
										resultLine.append("" + d + ";");
									} else if (tipoCampo.equals("N")) {
										long l = extraeLong(campo);
										if (l == -999999) {
											System.out
													.println("ERROR: " + line);
											l = 0;
										}
										resultLine.append("" + l + ";");
									} else if (tipoCampo.equals("F")) {
										if (campo.equals("00000000")) {
											campo = "25001231";
										}
										resultLine.append("" + campo + ";");
									} else {
										resultLine.append("\""
												+ ltrim(campo.trim()) + "\";");
									}
									if (estructuras[k].getNombreCampo()
											.substring(0, 5).equals("OCURR")
											&& offset > 0 && iBloque == 0) {
										vecesBloque[posVecesBloque] = (new Integer(
												campo)).intValue();
										posVecesBloque++;
									}
								}
							}
							// System.out.println(resultLine);
							bwOut.write("" + resultLine.toString() + CRLF);
							bwOut.flush();

						}

					}// for(int iBloque = 0; iBloque < tr.getNumBloques();
						// iBloque++){
				}// if(tr == null){

				numLinea++;
				line = in.readLine();

			}// while(true){

			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 */
	private void cerrarFicherosSalida() {
		try {
			Set<String> sKeys = bwOut.keySet();
			Vector<String> vKeys = new Vector<String>(sKeys);
			Collections.sort(vKeys);
			for (Iterator<String> i = vKeys.iterator(); i.hasNext();) {
				bwOut.get(i.next()).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tratarRegistro000000(String line) {

		try {
			// Extraemos los datos necesarios de 000000
			// La fecha
			int posicionFechaFactura = 305;
			int posicionFicheroOriginal = 577;
			int posicionCIFActual = 162;
			
			fechaFactura = line.substring(posicionFechaFactura, posicionFechaFactura + 8);
			System.out.println(fechaFactura);
			// nombre del fichero
			nombreFicheroOriginal = line.substring(posicionFicheroOriginal, posicionFicheroOriginal + 12);
			// cif actual
			String cifActual000000 = line.substring(posicionCIFActual, posicionCIFActual + 18);
			System.out.println("tratarRegistro000000:nombreFicheroOriginal:" + nombreFicheroOriginal);
			StringBuilder resultLine = new StringBuilder();
			resultLine.append("\"" + nombreFicheroOriginal + "\";");
			resultLine.append("\"" + fechaFactura + "\";");
			resultLine.append("\"" + cifActual000000 + "\";");
			resultLine.append("\"" + acuerdo + "\";");
			// File fileOut2 = new File(fileName2 + ".sql");
			// out2 = new BufferedWriter( new OutputStreamWriter(new
			// FileOutputStream(fileOut2),CODIFICACION_FICHERO_ORIGEN));
			String fOutName = getDirectorio() + "/" + "000000";
			System.out.println("tratarRegistro000000:fOutName:" + fOutName);

			BufferedWriter bwOut = getBROut(fOutName);
			bwOut.write("" + resultLine.toString() + CRLF);
			bwOut.flush();
			System.out.println("tratarRegistro000000:" + resultLine.toString());
			// bwOut.close();
		} catch (Exception e) {
			System.out.println("ERROR!!! tratarRegistro000000");
		}
	}

	/**
	 * 
	 * @param line
	 */
	private void tratarRegistro901000(String line) {

		/**
		 * El registro es ligeramente diferente del resto. Los Bloques 2 y 3 se
		 * tienen que tratar juntos
		 */
		BufferedWriter bwOut = null;
		BufferedWriter bwOut2 = null;
		try {
			int repeticionesBloque2 = 0;
			int longitudTextoBloque3 = 0;
			String codigoRegistro = line.substring(0, 6);
			String secuencial = line.substring(6, 6 + 8);
			TipoRegistro tr = registros.get(codigoRegistro);
			Bloque bloque = tr.getBloques()[0];
			EstructuraCampo[] estructuras = bloque.getEstructuras();
			StringBuilder resultLine = new StringBuilder();
			resultLine.append("\"" + nombreFicheroOriginal + "\";");
			resultLine.append("\"" + secuencial + "\";");
			resultLine.append("\"" + fechaFactura + "\";");
			resultLine.append("\"" + cifActual + "\";");
			resultLine.append("\"" + acuerdo + "\";");
			resultLine.append("\"" + codigoRegistro + "_" + (1) + "\";");
			String fOutName = getDirectorio() + "/" + codigoRegistro + "_"
					+ (1);
			bwOut = getBROut(fOutName);
			int pos = 0;
			for (int k = 0; k < bloque.getNumEstructuras(); k++) {
				int offset = (new Integer(estructuras[k].getLongitudCampo()))
						.intValue();
				if (offset > 0) {
					String nomCampo = estructuras[k].getNombreCampo();
					String tipoCampo = estructuras[k].getTipoCampo();
					String formatoCampo = estructuras[k].getFormatoCampo();
					String campo = "";
					if (line.length() > (pos + offset)) {
						campo = line.substring(pos, pos += offset);
					} else {
						campo = line.substring(pos);
					}
					if (nomCampo.trim().equals("OCURRENCIAS")) {
						repeticionesBloque2 = (new Integer(campo)).intValue();
					} else if (nomCampo.trim().equals("LONGITUD TEXTOS")) {
						longitudTextoBloque3 = (new Integer(campo)).intValue();
					}
					// System.out.println(nomCampo + ":" + campo + ":" +
					// repeticionesBloque2 + ":" + longitudTextoBloque3);
					campo = correccionCamposNumericos(tipoCampo, campo);
					if (tipoCampo.equals("I")) {
						Double d = extraeDouble(formatoCampo, campo);
						resultLine.append("" + d + ";");
					} else {
						resultLine.append("\"" + ltrim(campo.trim()) + "\";");
					}
					// System.out.println(resultLine);
				} else {
					System.out.println(offset);
				}
			}
			bwOut.write("" + resultLine.toString() + CRLF);
			bwOut.flush();
			fOutName = codigoRegistro + "_" + (2);
			bwOut2 = getBROut(fOutName);
			for (int i = 0; i < repeticionesBloque2; i++) {
				resultLine = new StringBuilder();
				resultLine.append("\"" + nombreFicheroOriginal + "\";");
				resultLine.append("\"" + secuencial + "\";");
				resultLine.append("\"" + fechaFactura + "\";");
				resultLine.append("\"" + cifActual + "\";");
				resultLine.append("\"" + acuerdo + "\";");
				resultLine.append("\"" + codigoRegistro + "_" + (2) + "\";");
				String campo = "";
				Bloque bloque2 = tr.getBloques()[1];
				estructuras = bloque2.getEstructuras();
				for (int k = 0; k < bloque2.getNumEstructuras(); k++) {
					int offset = (new Integer(estructuras[k].getLongitudCampo()))
							.intValue();
					if (offset > 0) {

						String tipoCampo = estructuras[k].getTipoCampo();
						String formatoCampo = estructuras[k].getFormatoCampo();

						if (line.length() > (pos + offset)) {
							campo = line.substring(pos, pos += offset);
						} else {
							campo = line.substring(pos);
						}

						campo = correccionCamposNumericos(tipoCampo, campo);
						if (tipoCampo.equals("I")) {
							Double d = extraeDouble(formatoCampo, campo);
							resultLine.append("" + d + ";");
						} else {
							resultLine.append("\"" + ltrim(campo.trim())
									+ "\";");
						}
					} else {
						System.out.println(offset);
					}
				}
				if (line.length() > (pos + longitudTextoBloque3)) {
					campo = line.substring(pos, pos += longitudTextoBloque3);
				} else {
					campo = line.substring(pos);
				}
				resultLine.append("\"" + ltrim(campo.trim()) + "\";");
				bwOut2.write("" + resultLine.toString() + CRLF);
				bwOut2.flush();
			}
		} catch (Exception e) {
		} finally {
		}

	}

	/**
	 * 
	 * Retorna un Buffer de escritura según el nombre pasado por parámetro
	 * 
	 * 
	 * @param fOutName
	 * @return
	 */
	private BufferedWriter getBROut(String fOutName) {

		/**
		 * 
		 * El método busca el fichero en la HashTable, si lo encuentra devuelve
		 * el Objeto sino crea uno nuevo
		 */
		File fileOut = null;
		BufferedWriter out = null;
		try {
			if (filesOut.get(fOutName) == null) {
				// creamos un nuevo fOut
				fileOut = new File(fOutName + ".txt");
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileOut),
						CODIFICACION_FICHERO_ORIGEN));
				filesOut.put(fOutName, fileOut);
				bwOut.put(fOutName, out);
				System.out.println("Fichero:" + fOutName);
			} else {
				out = bwOut.get(fOutName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return out;
	}

	/**
	 * @param fileName
	 */
	@SuppressWarnings("unused")
	private void getEstructuraRegistros(String fileName) {
		try {
			// File file = new File(fileName);
			// BufferedReader in = new BufferedReader(new FileReader(file));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(fileName)),
					CODIFICACION_FICHERO_ORIGEN));

			int numLinea = 0;

			String line;
			line = in.readLine();
			while (true) {
				if (line == null) {
					break;
				}

				if (line.startsWith("903000")) {

					TipoRegistro tr = new TipoRegistro();
					Bloque[] bloques = new Bloque[3];
					int pos = 0;
					int offset = 0;
					String line2 = line.substring(122);
					// System.out.println(numLinea+";"+line2);
					offset = 6;
					String codigoRegistro = line2.substring(pos, pos += offset);
					tr.setCodigoRegistro(codigoRegistro);
					// System.out.println("Registro:"+codigoRegistro);
					offset = 3;
					String numBloques = line2.substring(pos, pos += offset);
					int iNumBloques = Integer.parseInt(numBloques);
					tr.setNumBloques(iNumBloques);
					// System.out.println("NumBloques:"+ iNumBloques);
					offset = 3;
					String numCampos = line2.substring(pos, pos += offset);
					int iNumCampos = Integer.parseInt(numCampos);
					tr.setNumCampos(iNumCampos);
					// System.out.println("NumCampos:"+ iNumCampos );
					int[] arr = { 1 };
					for (int j = 0; j < iNumBloques; j++) {
						// Tenemos que pasar el valor de la variable pos por
						// referencia,
						// pues se modificar� en el m�todo extraeBloque
						// El truco est� en pasarlo en un array
						arr[0] = pos;
						Bloque bloque = extraeBloque(arr, line2, iNumCampos);
						pos = arr[0];
						bloques[j] = bloque;
					}// for(int j = 0; j < iNumBloques; j++)
					tr.setBloques(bloques);
					// registros.add(codigoRegistro, tr);
					registros.put(codigoRegistro, tr);

				}// if(line.startsWith("903000"))

				numLinea++;
				line = in.readLine();

			}

			in.close();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			// System.out.println(registros);
			Enumeration<String> e = (Enumeration<String>) registros.keys();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.println(key + ":" + registros.get(key));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * En los registros 903000 está la estructura de los registros de un fichero 977R.
	 * 
	 * Este método lee el fichero línea a línea y localiza las líneas que comienzan por 903000. 
	 * En ellas está la información de cómo leer el resto de registros.
	 * 
	 * @param in 
	 */
	private void getEstructuraRegistros(BufferedReader in) {
		try {

			int numLinea = 0;

			String line;
			line = in.readLine();
			while (true) {
				if (line == null) {
					break;
				}

				if (line.startsWith("903000")) {

					TipoRegistro tr = new TipoRegistro();
					Bloque[] bloques = new Bloque[3];
					int pos = 0;
					int offset = 0;
					String line2 = line.substring(122);
					// System.out.println(numLinea+";"+line2);
					offset = 6;
					String codigoRegistro = line2.substring(pos, pos += offset);
					tr.setCodigoRegistro(codigoRegistro);
					// System.out.println("Registro:"+codigoRegistro);
					offset = 3;
					String numBloques = line2.substring(pos, pos += offset);
					int iNumBloques = Integer.parseInt(numBloques);
					tr.setNumBloques(iNumBloques);
					// System.out.println("NumBloques:"+ iNumBloques);
					offset = 3;
					String numCampos = line2.substring(pos, pos += offset);
					int iNumCampos = Integer.parseInt(numCampos);
					tr.setNumCampos(iNumCampos);
					// System.out.println("NumCampos:"+ iNumCampos );
					int[] arr = { 1 };
					for (int j = 0; j < iNumBloques; j++) {
						
						/**
						 * Tenemos que pasar el valor de la variable "pos" por referencia,
						 * pues se modificará en el método "extraeBloque"
						 * 
						 * El truco está en pasarlo en un array ya que es un objeto...
						 * 
						 */
						arr[0] = pos;
						Bloque bloque = extraeBloque(arr, line2, iNumCampos);
						pos = arr[0];
						bloques[j] = bloque;
					}// for(int j = 0; j < iNumBloques; j++)
					tr.setBloques(bloques);
					// registros.add(codigoRegistro, tr);
					registros.put(codigoRegistro, tr);

				}// if(line.startsWith("903000"))

				numLinea++;
				line = in.readLine();

			}

			in.close();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			// System.out.println(registros);
			Enumeration<String> e = (Enumeration<String>) registros.keys();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.println(key + ":" + registros.get(key));
			}
			
			System.out.println("numLinea:" + numLinea);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param tipoCampo
	 * @param campo
	 * @return
	 */
	private String correccionCamposNumericos(String tipoCampo, String campo) {
		if ((tipoCampo.equals("I") || tipoCampo.equals("N"))
				&& campo.length() > 0) {
			String positivos = "{ABCDEFGHI";
			String negativos = "}JKLMNOPQR";

			String derecha = campo.substring(campo.length() - 1);
			// System.out.println("campo:" + campo + "\tderecha:" + derecha);
			if (positivos.indexOf(derecha) > -1) { // es positivo
				campo = campo.replace(derecha,
						(new Integer(positivos.indexOf(derecha))).toString());
			} else if (negativos.indexOf(derecha) > -1) { // es negativo
				campo = campo.replace(derecha,
						(new Integer(negativos.indexOf(derecha))).toString());
				campo = "-" + campo;
			}
			// System.out.println("campo:" + campo);
		}
		return campo;
	}

	/**
	 * @param pos
	 * @param line2
	 * @param iNumCampos
	 * @return
	 */
	private Bloque extraeBloque(int[] posiciones, String line2, int iNumCampos) {
		int offset;
		offset = 1;
		int pos = posiciones[0];
		String numBloque = line2.substring(pos, pos += offset);
		int iNumBloque = Integer.parseInt(numBloque);
		// System.out.println("NumBloque:"+ iNumBloque );
		offset = 1;
		String numBloquePadre = line2.substring(pos, pos += offset);
		int iNumBloquePadre = Integer.parseInt(numBloquePadre);
		// System.out.println("NumBloquePadre:"+ iNumBloquePadre );
		Bloque bloque = new Bloque();
		bloque.setNumBloque(iNumBloque);
		bloque.setNumBloquePadre(iNumBloquePadre);
		EstructuraCampo[] estructuras = new EstructuraCampo[50];
		for (int k = 0; k < iNumCampos; k++) {
			offset = 52;
			String strEstructuraCampo = line2.substring(pos, pos += offset);
			if (!strEstructuraCampo.substring(0, 6).equals("      ")) {
				// System.out.println("estructuraCampo:"+
				// splitRegistro(strEstructuraCampo) );
				EstructuraCampo ec = extraeEstructuraCampo(strEstructuraCampo);
				estructuras[k] = ec;
				// System.out.println(ec);
				bloque.setNumEstructuras(bloque.getNumEstructuras() + 1);
			}
		}
		bloque.setEstructuras(estructuras);
		System.out.println(bloque);
		posiciones[0] = pos;
		return bloque;
	}

	/**
	 * @param strEstructuraCampo
	 * @return
	 */
	private EstructuraCampo extraeEstructuraCampo(String strEstructuraCampo) {
		EstructuraCampo ec = new EstructuraCampo();
		int pos2 = 0;
		ec.setNombreCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[0]));
		ec.setTipoCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[1]));
		ec.setFormatoCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[2]));
		ec.setPosicionCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[3]));
		ec.setLongitudCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[4]));
		ec.setRepetidoCampo(strEstructuraCampo.substring(pos2,
				pos2 += posiciones[5]));
		ec.setAuxCampo(strEstructuraCampo
				.substring(pos2, pos2 += posiciones[6]));
		return ec;
	}

	/**
	 * @param formatoCampo
	 * @param campo
	 * @return double
	 */
	private double extraeDouble(String formatoCampo, String campo) {

		double d = 0.0;
		boolean negativo = false;
		if (formatoCampo.indexOf(",") > -1) { // Existe formato decimal

			// Es negativo??
			String neg = campo.substring(0, 1);
			if (neg.equals("-")) {
				negativo = true;
			}
			String entero = formatoCampo
					.substring(0, formatoCampo.indexOf(","));
			if (negativo) {
				// El tema está en los decimales NEGATIVOS, si es negativo,
				// entonces la longitud de campo era mayor

				campo = "-"
						+ campo.substring(1,
								(new Integer(entero)).intValue() + 1) + "."
						+ campo.substring((new Integer(entero)).intValue() + 1);
			} else {
				campo = campo.substring(0, (new Integer(entero)).intValue())
						+ "."
						+ campo.substring((new Integer(entero)).intValue());
			}

			try {

				d = new Double(campo);

			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("Error en la conversión de [" + campo
						+ "] a tipo DOUBLE.");
				return 0.0;
			}
		}
		return d;
	}

	/**
	 * 
	 * @param campo
	 * @return long
	 */
	private long extraeLong(String campo) {
		long l = 0;
		try {
			l = new Long(campo.trim());
		} catch (NumberFormatException e) {
			System.out.println("Error en la conversión de [" + campo
					+ "] a tipo LONG.");
			l = -999999; // error
		}
		return l;
	}

	private String ltrim(String source) {
		return source.replaceAll("^\\s+", "");
	}

	/**
	 * @return the acuerdo
	 */
	public String getAcuerdo() {
		return acuerdo;
	}

	/**
	 * @param acuerdo
	 *            the acuerdo to set
	 */
	public void setAcuerdo(String ac) {
		acuerdo = ac;
	}

	/**
	 * @return the ficheros
	 */
	public String[] getFicheros() {
		return ficheros;
	}

	/**
	 * @param ficheros
	 *            the ficheros to set
	 */
	public void setFicheros(String[] f) {
		ficheros = f;
	}

	/**
	 * @param nombreFicheroOriginal
	 *            the nombreFicheroOriginal to set
	 */
	public void setNombreFicheroOriginal(String nombreFicheroOriginal) {
		this.nombreFicheroOriginal = nombreFicheroOriginal;
	}

	/**
	 * @return the nombreFicheroOriginal
	 */
	public String getNombreFicheroOriginal() {
		return nombreFicheroOriginal;
	}

	/**
	 * @return the borrarTablas
	 */
	public boolean isBorrarTablas() {
		return borrarTablas;
	}

	/**
	 * @param borrarTablas
	 *            the borrarTablas to set
	 */
	public void setBorrarTablas(boolean borrarTablas) {
		this.borrarTablas = borrarTablas;
	}

	/**
	 * @return the dbHost
	 */
	public String getDbHost() {
		return dbHost;
	}

	/**
	 * @param dbHost
	 *            the dbHost to set
	 */
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}

	/**
	 * @param dbUser
	 *            the dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	/**
	 * @return the dbPass
	 */
	public String getDbPass() {
		return dbPass;
	}

	/**
	 * @param dbPass
	 *            the dbPass to set
	 */
	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	/**
	 * @param dbName
	 *            the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @return the directorio
	 */
	public String getDirectorio() {
		return directorio;
	}

	/**
	 * @param directorio
	 *            the directorio to set
	 */
	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	/**
	 * @param borrarAcuerdo
	 *            the borrarAcuerdo to set
	 */
	public void setBorrarAcuerdo(boolean borrarAcuerdo) {
		this.borrarAcuerdo = borrarAcuerdo;
	}

	/**
	 * @return the borrarAcuerdo
	 */
	public boolean isBorrarAcuerdo() {
		return borrarAcuerdo;
	}

	/**
	 * @param detalleLlamadas
	 *            the detalleLlamadas to set
	 */
	public void setDetalleLlamadas(boolean detalleLlamadas) {
		this.detalleLlamadas = detalleLlamadas;
	}

	/**
	 * @return the detalleLlamadas
	 */
	public boolean isDetalleLlamadas() {
		return detalleLlamadas;
	}

	/**
	 * @param detalleLlamadasRI
	 *            the detalleLlamadasRI to set
	 */
	public void setDetalleLlamadasRI(boolean detalleLlamadasRI) {
		this.detalleLlamadasRI = detalleLlamadasRI;
	}

	/**
	 * @return the detalleLlamadasRI
	 */
	public boolean isDetalleLlamadasRI() {
		return detalleLlamadasRI;
	}

	public long getTiempoEmpleado() {
		return tiempoEmpleado;
	}

	public void setTiempoEmpleado(long tiempoEmpleado) {
		this.tiempoEmpleado = tiempoEmpleado;
	}

}
