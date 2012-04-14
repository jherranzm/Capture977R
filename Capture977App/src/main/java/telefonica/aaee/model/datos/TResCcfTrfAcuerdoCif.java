package telefonica.aaee.model.datos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the t_res_ccf_trf_acuerdo_cif database table.
 * 
 */
@Entity
@Table(name="t_res_ccf_trf_acuerdo_cif")

@XmlRootElement(name="regularizacion")
public class TResCcfTrfAcuerdoCif 

	extends telefonica.aaee.model.GenericXML
	implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String acuerdo;

	@Column(name="BOE_CON_DESCUENTOS")
	private double boeConDescuentos;

	private String cif;

	private String concepto;

	@Column(name="DESCUENTOS_BOE")
	private double descuentosBoe;

	@Column(name="fecha_carga")
	@Temporal(TemporalType.DATE)
	private java.util.Date fechaCarga;

	@Column(name="IMPORTE_BOE")
	private double importeBoe;

	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;

	@Column(name="PRECIO_ACUERDO")
	private double precioAcuerdo;

	private String tipo_Doc;

	@Column(name="tipo_facturacion")
	private String tipoFacturacion;

    public TResCcfTrfAcuerdoCif() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcuerdo() {
		return this.acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public double getBoeConDescuentos() {
		return this.boeConDescuentos;
	}

	public void setBoeConDescuentos(double boeConDescuentos) {
		this.boeConDescuentos = boeConDescuentos;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getDescuentosBoe() {
		return this.descuentosBoe;
	}

	public void setDescuentosBoe(double descuentosBoe) {
		this.descuentosBoe = descuentosBoe;
	}

	
	public java.util.Date getFechaCarga() {
		return this.fechaCarga;
	}

	public void setFechaCarga(Timestamp fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public double getImporteBoe() {
		return this.importeBoe;
	}

	public void setImporteBoe(double importeBoe) {
		this.importeBoe = importeBoe;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public double getPrecioAcuerdo() {
		return this.precioAcuerdo;
	}

	public void setPrecioAcuerdo(double precioAcuerdo) {
		this.precioAcuerdo = precioAcuerdo;
	}

	public String getTipo_Doc() {
		return this.tipo_Doc;
	}

	public void setTipo_Doc(String tipo_Doc) {
		this.tipo_Doc = tipo_Doc;
	}

	public String getTipoFacturacion() {
		return this.tipoFacturacion;
	}

	public void setTipoFacturacion(String tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TResCcfTrfAcuerdoCif [id=" + id + ",\\n acuerdo=" + acuerdo + ",\\n boeConDescuentos="
				+ boeConDescuentos + ",\\n cif=" + cif + ",\\n concepto=" + concepto + ",\\n descuentosBoe="
				+ descuentosBoe + ",\\n fechaCarga=" + fechaCarga + ",\\n importeBoe=" + importeBoe
				+ ",\\n nombreCliente=" + nombreCliente + ",\\n precioAcuerdo=" + precioAcuerdo + ",\\n tipo_Doc="
				+ tipo_Doc + ",\\n tipoFacturacion=" + tipoFacturacion + "]";
	}
	
	
	
	

}