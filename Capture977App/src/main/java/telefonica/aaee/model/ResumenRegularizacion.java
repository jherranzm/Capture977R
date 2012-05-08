package telefonica.aaee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(
    		name = ResumenRegularizacion.FIND_ALL,
    		query = "SELECT a FROM ResumenRegularizacion a")
    , @NamedQuery(
    		name = ResumenRegularizacion.FINDBY_ACUERDO, 
    		query = "SELECT a FROM ResumenRegularizacion a WHERE  a.acuerdo = :elAcuerdo")
    , @NamedQuery(
    		name = ResumenRegularizacion.FINDBY_CIF, 
    	    query = "SELECT a FROM ResumenRegularizacion a WHERE  a.cif = :elCIF")
    
})

@Table(name="t_res_ccf_trf_acuerdo_cif")
@XmlRootElement
public class ResumenRegularizacion 
implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "findAllResumenRegularizacion";
	public static final String FINDBY_ACUERDO = "findAllResumenRegularizacionByAcuerdo";
	public static final String FINDBY_CIF = "findAllResumenRegularizacionByCIF";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String tipo_facturacion;
	private String acuerdo;
	private String tipo_doc;
	private String cif;
	private String nombre_cliente;
	private String concepto;

	private double importe_boe;
	private double descuentos_boe;
	private double boe_con_descuentos;
	private double precio_acuerdo;
	
    @Temporal(TemporalType.DATE)
    private Date fecha_carga;	
	
	
	
	
	
	
	
	
	public String getTipo_facturacion() {
		return tipo_facturacion;
	}
	public void setTipo_facturacion(String tipo_facturacion) {
		this.tipo_facturacion = tipo_facturacion;
	}
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	public String getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public double getImporte_boe() {
		return importe_boe;
	}
	public void setImporte_boe(double importe_boe) {
		this.importe_boe = importe_boe;
	}
	public double getDescuentos_boe() {
		return descuentos_boe;
	}
	public void setDescuentos_boe(double descuentos_boe) {
		this.descuentos_boe = descuentos_boe;
	}
	public double getBoe_con_descuentos() {
		return boe_con_descuentos;
	}
	public void setBoe_con_descuentos(double boe_con_descuentos) {
		this.boe_con_descuentos = boe_con_descuentos;
	}
	public double getPrecio_acuerdo() {
		return precio_acuerdo;
	}
	public void setPrecio_acuerdo(double precio_acuerdo) {
		this.precio_acuerdo = precio_acuerdo;
	}
	public Date getFecha_carga() {
		return fecha_carga;
	}
	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "FicherosCargados [id=" + id + ",\\n tipo_facturacion="
				+ tipo_facturacion + ",\\n acuerdo=" + acuerdo
				+ ",\\n tipo_doc=" + tipo_doc + ",\\n cif=" + cif
				+ ",\\n nombre_cliente=" + nombre_cliente + ",\\n concepto="
				+ concepto + ",\\n importe_boe=" + importe_boe
				+ ",\\n descuentos_boe=" + descuentos_boe
				+ ",\\n boe_con_descuentos=" + boe_con_descuentos
				+ ",\\n precio_acuerdo=" + precio_acuerdo + ",\\n fecha_carga="
				+ fecha_carga + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
