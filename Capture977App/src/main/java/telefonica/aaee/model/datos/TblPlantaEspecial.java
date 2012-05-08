package telefonica.aaee.model.datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tbl_planta_especial database table.
 * 
 */
@Entity
@Table(name="tbl_planta_especial")
@XmlRootElement

public class TblPlantaEspecial 
	extends telefonica.aaee.model.GenericXML

	implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	private String acuerdo;

	@Column(name="AGRUPACION_FACTURABLE")
	private String agrupacionFacturable;

	private String cif;

	@Column(name="CODIGO_POSTAL")
	private int codigoPostal;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="DOMICILIO_TERMINAL_A")
	private String domicilioTerminalA;

	@Column(name="DOMICILIO_TERMINAL_B")
	private String domicilioTerminalB;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;

	private String fichero;

	@Column(name="importe_acuerdo")
	private BigDecimal importeAcuerdo;

	private String multiconexion;

	private String nombre;

	@Column(name="NUMERO_COMERCIAL")
	private String numeroComercial;

	@Column(name="NUMERO_COMERCIAL_ASOCIADO")
	private String numeroComercialAsociado;

	@Column(name="POBLACION_TERMINAL_A")
	private String poblacionTerminalA;

	@Column(name="POBLACION_TERMINAL_B")
	private String poblacionTerminalB;

	@Column(name="precio_especial")
	private String precioEspecial;

	private String provincia;

	@Column(name="SITUACION_ABONO")
	private String situacionAbono;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	@Column(name="tipo_doc")
	private String tipoDoc;

	@Column(name="TIPO_FACTURACION")
	private String tipoFacturacion;

    public TblPlantaEspecial() {
    }

	public String getAcuerdo() {
		return this.acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getAgrupacionFacturable() {
		return this.agrupacionFacturable;
	}

	public void setAgrupacionFacturable(String agrupacionFacturable) {
		this.agrupacionFacturable = agrupacionFacturable;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDescTipoDeServicio() {
		return this.descTipoDeServicio;
	}

	public void setDescTipoDeServicio(String descTipoDeServicio) {
		this.descTipoDeServicio = descTipoDeServicio;
	}

	public String getDomicilioTerminalA() {
		return this.domicilioTerminalA;
	}

	public void setDomicilioTerminalA(String domicilioTerminalA) {
		this.domicilioTerminalA = domicilioTerminalA;
	}

	public String getDomicilioTerminalB() {
		return this.domicilioTerminalB;
	}

	public void setDomicilioTerminalB(String domicilioTerminalB) {
		this.domicilioTerminalB = domicilioTerminalB;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getFichero() {
		return this.fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public BigDecimal getImporteAcuerdo() {
		return this.importeAcuerdo;
	}

	public void setImporteAcuerdo(BigDecimal importeAcuerdo) {
		this.importeAcuerdo = importeAcuerdo;
	}

	public String getMulticonexion() {
		return this.multiconexion;
	}

	public void setMulticonexion(String multiconexion) {
		this.multiconexion = multiconexion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroComercial() {
		return this.numeroComercial;
	}

	public void setNumeroComercial(String numeroComercial) {
		this.numeroComercial = numeroComercial;
	}

	public String getNumeroComercialAsociado() {
		return this.numeroComercialAsociado;
	}

	public void setNumeroComercialAsociado(String numeroComercialAsociado) {
		this.numeroComercialAsociado = numeroComercialAsociado;
	}

	public String getPoblacionTerminalA() {
		return this.poblacionTerminalA;
	}

	public void setPoblacionTerminalA(String poblacionTerminalA) {
		this.poblacionTerminalA = poblacionTerminalA;
	}

	public String getPoblacionTerminalB() {
		return this.poblacionTerminalB;
	}

	public void setPoblacionTerminalB(String poblacionTerminalB) {
		this.poblacionTerminalB = poblacionTerminalB;
	}

	public String getPrecioEspecial() {
		return this.precioEspecial;
	}

	public void setPrecioEspecial(String precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSituacionAbono() {
		return this.situacionAbono;
	}

	public void setSituacionAbono(String situacionAbono) {
		this.situacionAbono = situacionAbono;
	}

	public String getTipoDeServicio() {
		return this.tipoDeServicio;
	}

	public void setTipoDeServicio(String tipoDeServicio) {
		this.tipoDeServicio = tipoDeServicio;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTipoFacturacion() {
		return this.tipoFacturacion;
	}

	public void setTipoFacturacion(String tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}