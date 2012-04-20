/**
 * 
 */
package telefonica.aaee.capture977;

import java.io.File;

import org.junit.Test;


/**
 * @author Usuario
 *
 */
public class Split977Test {
	
	@Test
	public void testUnFicheroFacturacion(){
		String path = "D:/server/Test_F5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_01");
			String[] f = {path + File.separator  + "COC02669.ENE"
					};
			sp.setFicheros(f);
			
			sp.setDetalleLlamadas(false);
			sp.setDetalleLlamadasRI(false);
			
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			
//			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorioOut(path);
			sp.execute();
			System.out.println("Se ha tardado:" + (sp.getTiempoEmpleado()/1000) + " segundos!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Test
	public void testVariosFicheroFacturacion(){
		String path = "D:/server/Test_F5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_02");
			String[] f = {path + File.separator  + "COC02669.ENE"
					, path + File.separator  + "COC02670.ENE"
					};
			sp.setFicheros(f);
			
			sp.setDetalleLlamadas(false);
			sp.setDetalleLlamadasRI(false);
			
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			
//			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorioOut(path);
			sp.execute();
			System.out.println("Se ha tardado:" + (sp.getTiempoEmpleado()/1000) + " segundos!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//CO[1].20110328.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip
	@Test
	public void testFicheroZipFacturacion(){
		String path = "D:/server/Test_F5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_03");
			String[] f = {path + File.separator + "CO[1].20110328.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
//					, path + "COC02670.ENE"
					};
			sp.setFicheros(f);
			
			sp.setDetalleLlamadas(false);
			sp.setDetalleLlamadasRI(false);
			
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			
//			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorioOut(path);
			sp.execute();
			System.out.println("Se ha tardado:" + (sp.getTiempoEmpleado()/1000) + " segundos!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void testVariosFicherosZipFacturacion(){
		String path = "D:/server/Test_F5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_07");
			String[] f = {
					path + File.separator + "CO[1].20110328.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110328.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110228.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110228.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110128.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110128.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
//					, path + "COC02670.ENE"
					};
			sp.setFicheros(f);
			
			sp.setDetalleLlamadas(false);
			sp.setDetalleLlamadasRI(false);
			
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			
//			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorioOut(path);
			sp.execute();
			System.out.println("Se ha tardado:" + (sp.getTiempoEmpleado()/1000) + " segundos!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	

	@Test
	public void testVariosFicherosZipFacturacionEnOtroDisco(){
		String path = "V:/Clientes/u/UPCNET/REGs/REG_2011/FicherosF5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_09");
			String[] f = {
					  path + File.separator + "CO[1].20110828.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110828.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110728.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110728.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110628.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110628.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110528.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110528.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110428.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110428.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110328.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110328.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110228.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110228.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
					
					, path + File.separator + "CO[1].20110128.LB61832119--012001.LB61832119.000-------_FACTEL5_977R.zip"
					, path + File.separator + "CO[1].20110128.LB61832119--102001.LB61832119.000-------_FACTEL5_977R.zip"
//					, path + "COC02670.ENE"
					};
			sp.setFicheros(f);
			
			sp.setDetalleLlamadas(false);
			sp.setDetalleLlamadasRI(false);
			
			sp.setDbHost("localhost");
			sp.setDbName("977R");
			sp.setDbUser("root");
			sp.setDbPass("illuminatti");
			
//			String path = new java.io.File(".").getCanonicalPath();
			sp.setDirectorioOut(path);
			sp.execute();
			System.out.println("Se ha tardado:" + (sp.getTiempoEmpleado()/1000) + " segundos!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test
	public void testFicherosZipFacturacionEnUnDirectorio(){
		String path = "V:/Clientes/u/UPCNET/REGs/REG_2011/FicherosF5";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_10");
			
			sp.setFicherosZipPath(path);

			if(sp.getFicheros().length > 0 ){
				sp.setDbHost("localhost");
				sp.setDbName("977R");
				sp.setDbUser("root");
				sp.setDbPass("illuminatti");
				
//				String path = new java.io.File(".").getCanonicalPath();
				sp.setDirectorioOut(path);
				sp.execute();
				System.out.println("Se ha tardado:" + sp.getTiempoEmpleado()/1000 + " segundos!");
			}else{
				System.err.println("Sin ficheros a tratar...");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testFicherosZipFacturacionEnOtroDirectorio(){
		String path = "V:/Clientes/u/UNIVERSITAT DE BARCELONA/Regularizaciones/REG_Acuerdo_2012";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("TEST_11");
			
			sp.setFicherosZipPath(path);

			if(sp.getFicheros().length > 0 ){
				sp.setDbHost("localhost");
				sp.setDbName("977R");
				sp.setDbUser("root");
				sp.setDbPass("illuminatti");
				
//				String path = new java.io.File(".").getCanonicalPath();
				sp.setDirectorioOut(path);
				sp.execute();
				System.out.println("Se ha tardado:" + sp.getTiempoEmpleado()/1000 + " segundos!");
			}else{
				System.err.println("Sin ficheros a tratar...");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testFicherosZipFacturacionURV2012(){
		String path = "V:/Clientes/u/UNIVERSITAT ROVIRA I VIRGILI/REG_2012";
		try {
			Split977 sp = new Split977();
			sp.setAcuerdo("URV_2012");
			
			sp.setFicherosZipPath(path);

			if(sp.getFicheros().length > 0 ){
				// Probando valores por defecto
				sp.setDirectorioOut(path);
				sp.execute();
				System.out.println("Se ha tardado:" + sp.getTiempoEmpleado()/1000 + " segundos!");
			}else{
				System.err.println("Sin ficheros a tratar...");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
