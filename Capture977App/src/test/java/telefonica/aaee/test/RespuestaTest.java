/**
 * 
 */
package telefonica.aaee.test;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import telefonica.aaee.model.ResumenRegularizacion;
import telefonica.aaee.util.JPAUtil;
import telefonica.aaee.util.Utilidades;
import telefonica.aaee.webutils.PFCLogger;

/**
 * @author Usuario
 *
 */
public class RespuestaTest {
	
	private static final Logger LOGGER = PFCLogger.getLogger(RespuestaTest.class.getCanonicalName());
	
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        emf = Persistence.createEntityManagerFactory("977R");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() {
        em.close();
        emf.close();
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void test001() throws Exception {
    	
    	List<ResumenRegularizacion> list = null;
    	
    	try {
			
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			LOGGER.info("Inicio...");
			if(entityManager == null){
				LOGGER.warning("ERROR: entityManager == null");
			}else{
				LOGGER.info("OK: entityManager NOT null");
				Query q = entityManager.createNamedQuery(ResumenRegularizacion.FIND_ALL);
				LOGGER.info("Query q = entityManager.createQuery(sql);");
				
				list = (List<ResumenRegularizacion>)q.getResultList();
				LOGGER.info("List list = q.getResultList();");
				
				int count = list.size();
				
				LOGGER.info("" + count);
				
				q = entityManager.createNamedQuery(ResumenRegularizacion.FINDBY_ACUERDO);
				q.setParameter("elAcuerdo", "UB_2011_1S");
				
				list = (List<ResumenRegularizacion>)q.getResultList();
				
				for(ResumenRegularizacion fichero : list){
					LOGGER.info("" + fichero);
				}
			
				q = entityManager.createNamedQuery(ResumenRegularizacion.FINDBY_CIF);
				q.setParameter("elCIF", "Q0818001J");
				
				list = (List<ResumenRegularizacion>)q.getResultList();
				
				double importe_boe = 0.0;
				
				for(ResumenRegularizacion fichero : list){
					LOGGER.info("" + fichero.getCif() + ":" + fichero.getImporte_boe());
					importe_boe += fichero.getImporte_boe();
				}
				
				LOGGER.info("" + importe_boe);
				
//				q = entityManager.createNativeQuery("{ call UPDATE_ALL_AUX_TABLES() }");
//				Object obj = q.getSingleResult();
//				LOGGER.info("obj:" + obj);

				q = entityManager.createNativeQuery("{ call 977R_GET_ACUERDOS() }");
				List<String> acuerdos = q.getResultList();
				for(String acuerdo : acuerdos){
					LOGGER.info("" + acuerdo);
				}
				
				String res = Utilidades.copiaCondiciones("COL_LHOSP_2011_2T", "COL_LHOSP_2011_1T");
				
				LOGGER.info("Copia condiciones: " + res);
				
				

			}
			LOGGER.info("...fin!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	

    }

    @Test
    public void test002() throws Exception {
    	
    	String acuerdoAct = "UB_2012_01";
    	String acuerdoAnt = "UB_2011";
    	String res = Utilidades.copiaCondiciones(acuerdoAct, acuerdoAnt);
    	
    	System.out.println(res);
    	assert(!"".equals(res));
    }

}
