package telefonica.aaee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Table(name="t000000")
@XmlRootElement
public class FicherosCargados  
implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	private String fichero;
	private String FECHA_FACTURA;
	private String CIF_CLIENTE_Clave;
	private String ACUERDO;
	
	@Temporal(TemporalType.DATE)
    private Date fecha_carga;
	
	
	
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getFECHA_FACTURA() {
		return FECHA_FACTURA;
	}

	public void setFECHA_FACTURA(String fECHA_FACTURA) {
		FECHA_FACTURA = fECHA_FACTURA;
	}

	public String getCIF_CLIENTE_Clave() {
		return CIF_CLIENTE_Clave;
	}

	public void setCIF_CLIENTE_Clave(String cIF_CLIENTE_Clave) {
		CIF_CLIENTE_Clave = cIF_CLIENTE_Clave;
	}

	public String getACUERDO() {
		return ACUERDO;
	}

	public void setACUERDO(String aCUERDO) {
		ACUERDO = aCUERDO;
	}

	public Date getFecha_carga() {
		return fecha_carga;
	}

	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	
}
