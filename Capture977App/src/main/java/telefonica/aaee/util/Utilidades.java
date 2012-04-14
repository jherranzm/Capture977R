package telefonica.aaee.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import telefonica.aaee.webutils.PFCLogger;

public class Utilidades {
	
	private static final String PERSISTENCE_UNIT = "977R";

	private static final String CALL_977R_ADDINCLUDE_ALL = "{ call 977R_SP_ADDINCLUDE_ALL( ? ) }";
	private static final String CALL_977R_SP_APPLY_COND_ALL = "{ call 977R_SP_APPLY_COND_ALL( ? ) }";

	private static final String CALL_977R_COPY_ESCENARIO_BY_ACUERDO = "{ call 977R_COPY_ESCENARIO_ByAcuerdo(?,?) }";

	private static final String CALL_977R_GET_ACUERDOS = "{ call 977R_GET_ACUERDOS() }";
	
	private static final String CALL_977R_DELETE_DATOS_ACUERDO = "{ call 977R_DELETE_ACUERDO( ? ) }";

	private static final Logger LOGGER = PFCLogger.getLogger(Utilidades.class.getCanonicalName());
	
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    @SuppressWarnings("unchecked")
	public static List<String> getAcuerdos(){
    	
    	List<String> acuerdos = new ArrayList<String>();
    	
    	openPersistenceUnit();
        Query q = em.createNativeQuery(CALL_977R_GET_ACUERDOS);
		acuerdos = q.getResultList();
		for(String acuerdo : acuerdos){
			LOGGER.info("" + acuerdo);
		}
        closePersistenceUnit();
        
        return acuerdos;
    }


    
    
    public static String copiaCondiciones(String acuerdoActual, String acuerdoAnterior){
    	
    	String resultado = new String();
    	
    	openPersistenceUnit();
    	Query q = em.createNativeQuery(CALL_977R_COPY_ESCENARIO_BY_ACUERDO);
    	LOGGER.info("...creada la query...");
        
        q.setParameter(1, acuerdoActual);
        q.setParameter(2, acuerdoAnterior);
        
    	LOGGER.info("...ponemos los parámetros: [" + acuerdoActual + "], [" + acuerdoAnterior + "]...");
    	LOGGER.info("Ejecutamos...");
		Object obj = q.getSingleResult();
		
		LOGGER.info("Recuperamos el obj:\n" + obj);
		resultado = obj.toString();
        closePersistenceUnit();
        
        return resultado;
    }

    public static String borrarDatosAcuerdo(String acuerdoActual){
    	
    	String resultado = new String();
    	
    	openPersistenceUnit();
        Query q = em.createNativeQuery(CALL_977R_DELETE_DATOS_ACUERDO);

        String ret = "";
        q.setParameter(1, acuerdoActual);

		Object obj = q.getSingleResult();
		ret = obj.toString();
		LOGGER.info("retorna:" + ret);
		resultado = ret;
        closePersistenceUnit();
        
        return resultado;
    }

    public static String cargaDatosCuotasTrafico(String acuerdoActual){
    	
    	String resultado = new String();
    	
    	openPersistenceUnit();
        Query q = em.createNativeQuery(CALL_977R_ADDINCLUDE_ALL);

        String ret = "";
        q.setParameter(1, acuerdoActual);
        
		Object obj = q.getSingleResult();
		ret = obj.toString();
		LOGGER.info("retorna:" + ret);
		resultado = ret;
        closePersistenceUnit();
        
        return resultado;
    }




    public static String aplicaCondiciones(String acuerdoActual){
    	
    	String resultado = new String();
    	
    	openPersistenceUnit();
        Query q = em.createNativeQuery(CALL_977R_SP_APPLY_COND_ALL);

        String ret = "";
        q.setParameter(1, acuerdoActual);
        
		Object obj = q.getSingleResult();
		ret = obj.toString();
		LOGGER.info("retorna:" + ret);
		resultado = ret;
        closePersistenceUnit();
        
        return resultado;
    }















	/**
	 * 
	 */
	private static void closePersistenceUnit() {
		em.close();
        emf.close();
	}


	/**
	 * 
	 */
	private static void openPersistenceUnit() {
    	LOGGER.info("Antes de la Persistencia...");
    	emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    	LOGGER.info("...ya tenemos la factoría...");
        em = emf.createEntityManager();
    	LOGGER.info("...ya tenemos el manejador...");
	}
    
}
