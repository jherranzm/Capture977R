/**
 *
 */
package telefonica.aaee.webutils;

import java.util.Properties;

/**
 * @author t130796
 * 
 */
public final class AAEEModPlanas {

    public static final String MYSQL = "MySQL";
    public static final String DRIVER_MANAGER_MYSQL = "DriverManagerMySQL";
    public static final String NL = "\n";
    public static final String BR = "<br/>";
    public static final String IE = "IE";
    public static final String ENC_UTF8 = "UTF-8";
    public static final String ENC_ISO88591 = "ISO-8859-1";
    public static final String SEPARADOR = "* * * * * * * * * * * * * * * * * * * * * *";
    public static final String SEP_VERTICAL = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static boolean tratarMixtos = false;
    public static Properties sqlStatements = new Properties();
    public static Properties messages = new Properties();

    public static final String[] JERARQUIA = { "Comercial", "Jefe Ventas",
	    "Jefe Área", "Gerente", "Dir.Ventas", "Dir.General",
	    "Comité de Precios" };
    public static final String[] JERARQUIA2 = { "Comerci", "Jefe_V", "Jefe_A",
	    "Gerente", "Dir_Ven", "Dir_Gen", "ComiteP" };

    public static final String[] CAUSAS_BAJA = { "",
	    "Disconformidad del cliente con la Plana",
	    "Cambio a otros Acuerdos Especiales", "Baja Tráfico", "Impago",
	    "Cambio CIF/NIF", "Otros" };
    
    public static String uploadDir = "D:/server";
    public static int maxFileSizeFile = 16777216; // 16MBytes
    public static int maxFileSizeMemory = 8192; // 8KBytes


    public static String servidorCorreo = "192.168.113.1"; // Por defecto
    
    public static String realPath = "D:/server"; // Path donde está alojada la aplicación
}
