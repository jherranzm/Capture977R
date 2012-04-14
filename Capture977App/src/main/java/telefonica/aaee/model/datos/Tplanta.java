package telefonica.aaee.model.datos;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the tplanta database table.
 * 
 */
@Entity
@Table(name="tplanta")
@XmlRootElement(name="planta")
public class Tplanta 
	extends telefonica.aaee.model.GenericXML
	implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	private String acuerdo;

	@Column(name="AGRUPACION_FACTURABLE")
	private String agrupacionFacturable;

	@Column(name="CENTRAL_DESTINO")
	private int centralDestino;

	@Column(name="CENTRAL_ORIGEN")
	private int centralOrigen;

	@Column(name="cliente_cif")
	private String clienteCif;

	@Column(name="cliente_nombre")
	private String clienteNombre;

	@Column(name="cliente_tipo_doc")
	private String clienteTipoDoc;

	@Column(name="CODIGO_POSTAL")
	private int codigoPostal;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="DISTANCIA_EN_KM")
	private int distanciaEnKm;

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

	private String multiconexion;

	@Column(name="NUMERO_COMERCIAL")
	private String numeroComercial;

	@Column(name="NUMERO_COMERCIAL_ASOCIADO")
	private String numeroComercialAsociado;

	@Column(name="POBLACION_TERMINAL_A")
	private String poblacionTerminalA;

	@Column(name="POBLACION_TERMINAL_B")
	private String poblacionTerminalB;

	private String provincia;

	@Column(name="SITUACION_ABONO")
	private String situacionAbono;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	@Column(name="TIPO_FACTURACION")
	private String tipoFacturacion;

    public Tplanta() {
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

	public int getCentralDestino() {
		return this.centralDestino;
	}

	public void setCentralDestino(int centralDestino) {
		this.centralDestino = centralDestino;
	}

	public int getCentralOrigen() {
		return this.centralOrigen;
	}

	public void setCentralOrigen(int centralOrigen) {
		this.centralOrigen = centralOrigen;
	}

	public String getClienteCif() {
		return this.clienteCif;
	}

	public void setClienteCif(String clienteCif) {
		this.clienteCif = clienteCif;
	}

	public String getClienteNombre() {
		return this.clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getClienteTipoDoc() {
		return this.clienteTipoDoc;
	}

	public void setClienteTipoDoc(String clienteTipoDoc) {
		this.clienteTipoDoc = clienteTipoDoc;
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

	public int getDistanciaEnKm() {
		return this.distanciaEnKm;
	}

	public void setDistanciaEnKm(int distanciaEnKm) {
		this.distanciaEnKm = distanciaEnKm;
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

	public String getMulticonexion() {
		return this.multiconexion;
	}

	public void setMulticonexion(String multiconexion) {
		this.multiconexion = multiconexion;
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