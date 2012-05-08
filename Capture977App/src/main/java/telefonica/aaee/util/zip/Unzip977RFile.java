package telefonica.aaee.util.zip;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;

public class Unzip977RFile {
	
	final private static Logger LOGGER = Logger.getLogger(Unzip977RFile.class.getCanonicalName());
	
	final private static String  extensiones = "ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic";
	
	/**
	 * 
	 * @param zipFileName, fichero de entrada
	 * @param outPathName, directorio de salida de los ficherso descomprimidos
	 * 
	 * @return true si no ha habido error; false, si ha habido un error.
	 */
    public String unzip(String zipFileName, String outPathName) {
    	
    	String ret = "";
    	String extension = "   ";

        try {
            ZipFile zipFile = new ZipFile(zipFileName);

            //TODO: Comprobar que funciona...
            if(!outPathName.isEmpty()){
                if(!outPathName.endsWith("//")){
                	outPathName = outPathName.concat("//");
                }
            }
            
            

            for(ZipEntry zipEntry :  Collections.list(zipFile.entries())){
            	String fileName = zipEntry.getName();
                LOGGER.info("Descomprimiendo: " + fileName);
                
                extension = StringUtils.right(fileName, 3).toLowerCase();
                
                if(extensiones.contains(extension)){
                    BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));

                    int size;
                    byte[] buffer = new byte[2048];

                    ret = outPathName.concat(zipEntry.getName());
                    FileOutputStream fos = new FileOutputStream(ret);
                    BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                    while ((size = bis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, size);
                    }

                    bos.flush();
                    bos.close();
                    fos.close();

                    bis.close();
                }else{
                	LOGGER.severe("El fichero a descomprimir " + fileName + " no tiene la extensión válida!" );
                }

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return ret;
    }
}

