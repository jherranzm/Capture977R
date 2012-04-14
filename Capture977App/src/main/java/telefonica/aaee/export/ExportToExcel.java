/**
 *
 */
package telefonica.aaee.export;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import telefonica.aaee.webutils.PFCLogger;

/**
 * @author José Luis Herranz Martín
 *
 * 05/02/2009
 *
 */
public class ExportToExcel {

	//private String[][] datos;
    private String realPath = null;
    private String fullFile = null;
    private String file = null;
    private String fileIn = null;
    private FileOutputStream fileOut = null;
    private Logger logger = PFCLogger.getLogger(this.getClass().getCanonicalName());

    private String xlsFileOrig = "PeticionesPorDia.xls";
    
    private final HSSFWorkbook wbOutput = new HSSFWorkbook();
    private final HSSFCellStyle headerStyle = wbOutput.createCellStyle();
    private final HSSFCellStyle cellStyle = wbOutput.createCellStyle();
    private final HSSFFont font = wbOutput.createFont();

   
    


    /**
	 * Constructor
	 *
	 */
	public ExportToExcel() {
		super();
	}


    /**
	 * Constructor
     * @param path
	 *
	 */
	public ExportToExcel(String path) {
		super();
		setRealPath(path);
		setFileIn(getRealPath() + xlsFileOrig);
	}

    /**
	 * Constructor
     * @param path
     * @param template
	 *
	 */
	public ExportToExcel(String path, String template) {
		super();
		setRealPath(path);
		setFileIn(getRealPath() + template);
	}
	
	public boolean writeFile( String fileName){
		boolean ret = false;
		
		try {
			FileOutputStream stream = new FileOutputStream(fileName);
			wbOutput.write(stream);
			
		} catch (IOException e) {
			System.out.println("Error al grabar el fichero ***" + fileName + "***");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	/**
	 * 
	 * @param nombreTab
	 * @param crs
	 * @return
	 */
	public boolean export(String nombreTab, CachedRowSet crs){
		
		/**
		 * Se presupone que el libro Excel EXISTE y que está abierto, y se dejará abierto
		 */
		boolean ret = false;
		
		try {
			
			// añadimos una pestaña
			HSSFSheet sheet = wbOutput.createSheet(nombreTab);
			// generamos los estilos
			crearEstilos(headerStyle, font);

			//logger.info("Fichero y cabeceras creadas!");
			
			// bloqueamos la primera fila
			sheet.createFreezePane( 0, 1, 0, 1 );
			
			// rellenamos la pestaña
			if(crs != null){

				ret = fillExcelSheet(crs, sheet, headerStyle, cellStyle);

			}else{
				logger.severe("El ResultSet está vacío!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}


	/**
	 * @param crs
	 * @return true or false
	 */
	public boolean export( CachedRowSet crs){

		boolean ret = false;

        try {
			final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
			final String sDate = formatter.format(new Date());
			setFile("PeticionesPorDia_" + sDate + ".xls");
			setFullFile(getRealPath() + getFile());

			logger.info("Fichero temporal:"+getFullFile());

			fileOut = new FileOutputStream(getFullFile());

			POIFSFileSystem doc = new POIFSFileSystem(new FileInputStream(getFileIn()));
			final HSSFWorkbook wbOutput = new HSSFWorkbook(doc);
			//final HSSFSheet sheet1 = wbOutput.getSheet("PeticionesPorDia");
			final HSSFSheet sheet1 = wbOutput.getSheetAt(0);
			//final HSSFSheet sheet2 = wbOutput.createSheet(sheetname);

			crearEstilos(headerStyle, font);

			logger.info("Fichero y cabeceras creadas!");
			
			sheet1.createFreezePane( 0, 1, 0, 1 );

			if(crs != null){

				ret = fillExcelSheet(crs, sheet1, headerStyle, cellStyle);

			}else{
				logger.severe("El ResultSet está vacío!");
			}

			wbOutput.write(fileOut);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

        return ret;

	}


	/**
	 * @param headerStyle
	 * @param font
	 */
	private void crearEstilos(final HSSFCellStyle headerStyle,
			final HSSFFont font) {
		font.setColor( HSSFColor.WHITE.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		headerStyle.setFillBackgroundColor( HSSFColor.BLUE.index);
		headerStyle.setFillForegroundColor( HSSFColor.BLUE.index);
		headerStyle.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setWrapText(true);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN ) ;
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN ) ;
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN ) ;
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN ) ;
		headerStyle.setFont(font);
	}


	/**
	 * @param crs
	 * @param sheet1
	 * @param headerStyle
	 * @param cellStyle
	 * @return
	 * @throws SQLException
	 */
	private boolean fillExcelSheet(CachedRowSet crs, 
			final HSSFSheet sheet1,
			final HSSFCellStyle headerStyle, 
			final HSSFCellStyle cellStyle)
			throws SQLException {
		boolean ret;
		ResultSetMetaData rsmd = (RowSetMetaData)crs.getMetaData(); //

		int numColumnas = rsmd.getColumnCount();
		
		int _f = 0; // fila inicial
		HSSFRow row = sheet1.createRow(_f);
		for(int _c = 1; _c<=numColumnas; _c++){
			HSSFCell cell =  row.createCell((_c-1)); // poi.3.6
		    cell.setCellStyle(headerStyle);
		    cell.setCellValue(rsmd.getColumnName(_c));
		    /**
		    logger.info("Column: " + _c + ":" + rsmd.getColumnName(_c));
		    logger.info("" + cell.getStringCellValue());
		    logger.info("Row:" +row.getRowNum()+"\tCell:" + cell.getColumnIndex());
		    */
		}
		_f++;

		// Recorremos las filas
		while(crs.next()){
		    row = sheet1.createRow(_f);

		    // Recorremos las columnas
			for(int _c = 1; _c<=numColumnas; _c++){
				//final HSSFCell cell =  row.createCell((short)(_c-1));
				HSSFCell cell =  row.createCell((_c-1)); //poi.3.6
				
				//logger.info("Tipo de Columna:" + rsmd.getColumnType(_c) + ":" + rsmd.getColumnName(_c));

		        if(rsmd.getColumnType(_c) == java.sql.Types.INTEGER){
		        	int value = crs.getInt(_c);
		            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#0"));
		        	cell.setCellValue(value);
		            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

		        }else if(rsmd.getColumnType(_c) == java.sql.Types.BIGINT){
		        	long value = crs.getLong(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00"));
		            cell.setCellValue(value);
		            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

		        }else if(rsmd.getColumnType(_c) == java.sql.Types.FLOAT){
		        	float value = crs.getFloat(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00"));
		            cell.setCellValue(value);
		            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

		        }else if(rsmd.getColumnType(_c) == java.sql.Types.DOUBLE){
		        	float value = crs.getFloat(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00"));
		            cell.setCellValue(value);
		            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

		        }else if(rsmd.getColumnType(_c) == java.sql.Types.VARCHAR){
		        	String value = crs.getString(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
		            if (value != null) cell.setCellValue(value);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);

		        }else if(rsmd.getColumnType(_c) == java.sql.Types.CHAR){
		        	String value = crs.getString(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
		            if (value != null) cell.setCellValue(value);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		            
		        }else if(rsmd.getColumnName(_c) == "TEXT"){
		        	String value = crs.getString(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
		            if (value != null) cell.setCellValue(value);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		            
		        }else if(
		        		(rsmd.getColumnType(_c) == java.sql.Types.DATE) ||
		        		(rsmd.getColumnName(_c) == "DATETIME") ||
		        		(rsmd.getColumnType(_c) == java.sql.Types.TIMESTAMP) // 93
		        		){

		        	Date value = crs.getDate(_c);
		        	//cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("dd-MM-yyyy"));
		        	cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy/MM/dd"));
		            final SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
		            if (value != null) cell.setCellValue(sdf.format( value ));
		        }else{
		        	String value = crs.getString(_c);
					cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
		            if (value != null) cell.setCellValue(value);
		            
		            logger.info("OJO!:" + rsmd.getColumnType(_c) + ":" + value);
		            
		        }
		        cell.setCellStyle(cellStyle);
			}
			_f++;
		}

		ret = true;
		return ret;
	}




	/**
	 * @return the realPath
	 */
	public String getRealPath() {
		return realPath;
	}




	/**
	 * @param realPath the realPath to set
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}




	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}


	/**
	 * @return the file
	 */
	public String getFullFile() {
		return fullFile;
	}



	/**
	 * @param file the file to set
	 */
	public void setFullFile(String file) {
		this.fullFile = file;
	}




	/**
	 * @return the fileIn
	 */
	public String getFileIn() {
		return fileIn;
	}




	/**
	 * @param fileIn the fileIn to set
	 */
	public void setFileIn(String fileIn) {
		this.fileIn = fileIn;
	}

}

