package telefonica.aaee.model.datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tbl_trafico_internacional_detallado database table.
 * 
 */
@Entity
@Table(name="tbl_trafico_internacional_detallado")
@XmlRootElement

public class TblTraficoInternacionalDetallado 

	extends telefonica.aaee.model.GenericXML

	implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String acuerdo;

	@Column(name="AGRUPACION_FACTURABLE")
	private String agrupacionFacturable;

	@Column(name="AMBITO_DE_TRAFICO")
	private String ambitoDeTrafico;

	@Column(name="BOE_CON_DESCUENTOS")
	private double boeConDescuentos;

	private String cif;

	private String CIF_CLIENTE_Clave;

	@Column(name="CODIGO_DESCUENTO")
	private String codigoDescuento;

	@Column(name="CODIGO_PERSONALIZADO")
	private String codigoPersonalizado;

	@Column(name="CODIGO_TRAFICO")
	private String codigoTrafico;

	private String cuc;

	@Column(name="DESC_AMBITO_DE_TRAFICO")
	private String descAmbitoDeTrafico;

	@Column(name="DESC_CODIGO_TRAFICO")
	private String descCodigoTrafico;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="DESCUENTOS_BOE")
	private double descuentosBoe;

	private int destino;

	private String duracion;

	@Column(name="DURACION_HORAS")
	private String duracionHoras;

	@Column(name="DURACION_MINUTOS")
	private String duracionMinutos;

	@Column(name="DURACION_SEGUNDOS")
	private String duracionSegundos;

	@Column(name="EST_LLAMADA")
	private double estLlamada;

	@Column(name="fecha_carga")
	private Timestamp fechaCarga;

	@Column(name="FECHA_FACTURA")
	private String fechaFactura;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_LLAMADA")
	private Date fechaLlamada;

	private String fichero;

	@Column(name="GRUPO_DE_GASTO")
	private String grupoDeGasto;

	@Column(name="HORA_INICIO")
	private Time horaInicio;

	@Column(name="IMPORTE_BOE")
	private double importeBoe;

	@Column(name="IMPORTE_DESCUENTO")
	private double importeDescuento;

	@Column(name="IMPORTE_ESTANDAR_PRODUCTO")
	private double importeEstandarProducto;

	@Column(name="IMPORTE_VALOR_ANADIDO")
	private double importeValorAnadido;

	@Column(name="LONGITUD_REGISTRO")
	private int longitudRegistro;

	@Column(name="MINUTOS_TOTAL")
	private double minutosTotal;

	private String multiconexion;

	@Column(name="N_SOLICITADO_LLAMANTE")
	private String nSolicitadoLlamante;

	private String nivel;

	private String nombre;

	@Column(name="nombre_pais_por_prefijo")
	private String nombrePaisPorPrefijo;

	@Column(name="NUMERO_COMERCIAL")
	private String numeroComercial;

	@Column(name="NUMERO_COMERCIAL_ASOCIADO")
	private String numeroComercialAsociado;

	@Column(name="NUMERO_LLAMADO")
	private String numeroLlamado;

	private int operador;

	private String pais;

	@Column(name="pais_por_prefijo")
	private String paisPorPrefijo;

	@Column(name="PORCENTAJE_DESCUENTO")
	private double porcentajeDescuento;

	@Column(name="PRECIO_ACUERDO")
	private double precioAcuerdo;

	@Column(name="PRECIO_ESPECIAL")
	private String precioEspecial;

	@Column(name="PRECIO_POR_MINUTO")
	private double precioPorMinuto;

	private String tarifa;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	@Column(name="TIPO_DESCUENTO")
	private String tipoDescuento;

	@Column(name="tipo_doc")
	private String tipoDoc;

	private String zona;

    public TblTraficoInternacionalDetallado() {
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

	public String getAgrupacionFacturable() {
		return this.agrupacionFacturable;
	}

	public void setAgrupacionFacturable(String agrupacionFacturable) {
		this.agrupacionFacturable = agrupacionFacturable;
	}

	public String getAmbitoDeTrafico() {
		return this.ambitoDeTrafico;
	}

	public void setAmbitoDeTrafico(String ambitoDeTrafico) {
		this.ambitoDeTrafico = ambitoDeTrafico;
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

	public String getCIF_CLIENTE_Clave() {
		return this.CIF_CLIENTE_Clave;
	}

	public void setCIF_CLIENTE_Clave(String CIF_CLIENTE_Clave) {
		this.CIF_CLIENTE_Clave = CIF_CLIENTE_Clave;
	}

	public String getCodigoDescuento() {
		return this.codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}

	public String getCodigoPersonalizado() {
		return this.codigoPersonalizado;
	}

	public void setCodigoPersonalizado(String codigoPersonalizado) {
		this.codigoPersonalizado = codigoPersonalizado;
	}

	public String getCodigoTrafico() {
		return this.codigoTrafico;
	}

	public void setCodigoTrafico(String codigoTrafico) {
		this.codigoTrafico = codigoTrafico;
	}

	public String getCuc() {
		return this.cuc;
	}

	public void setCuc(String cuc) {
		this.cuc = cuc;
	}

	public String getDescAmbitoDeTrafico() {
		return this.descAmbitoDeTrafico;
	}

	public void setDescAmbitoDeTrafico(String descAmbitoDeTrafico) {
		this.descAmbitoDeTrafico = descAmbitoDeTrafico;
	}

	public String getDescCodigoTrafico() {
		return this.descCodigoTrafico;
	}

	public void setDescCodigoTrafico(String descCodigoTrafico) {
		this.descCodigoTrafico = descCodigoTrafico;
	}

	public String getDescTipoDeServicio() {
		return this.descTipoDeServicio;
	}

	public void setDescTipoDeServicio(String descTipoDeServicio) {
		this.descTipoDeServicio = descTipoDeServicio;
	}

	public double getDescuentosBoe() {
		return this.descuentosBoe;
	}

	public void setDescuentosBoe(double descuentosBoe) {
		this.descuentosBoe = descuentosBoe;
	}

	public int getDestino() {
		return this.destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public String getDuracion() {
		return this.duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getDuracionHoras() {
		return this.duracionHoras;
	}

	public void setDuracionHoras(String duracionHoras) {
		this.duracionHoras = duracionHoras;
	}

	public String getDuracionMinutos() {
		return this.duracionMinutos;
	}

	public void setDuracionMinutos(String duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getDuracionSegundos() {
		return this.duracionSegundos;
	}

	public void setDuracionSegundos(String duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}

	public double getEstLlamada() {
		return this.estLlamada;
	}

	public void setEstLlamada(double estLlamada) {
		this.estLlamada = estLlamada;
	}

	public Timestamp getFechaCarga() {
		return this.fechaCarga;
	}

	public void setFechaCarga(Timestamp fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getFechaFactura() {
		return this.fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Date getFechaLlamada() {
		return this.fechaLlamada;
	}

	public void setFechaLlamada(Date fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	public String getFichero() {
		return this.fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getGrupoDeGasto() {
		return this.grupoDeGasto;
	}

	public void setGrupoDeGasto(String grupoDeGasto) {
		this.grupoDeGasto = grupoDeGasto;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public double getImporteBoe() {
		return this.importeBoe;
	}

	public void setImporteBoe(double importeBoe) {
		this.importeBoe = importeBoe;
	}

	public double getImporteDescuento() {
		return this.importeDescuento;
	}

	public void setImporteDescuento(double importeDescuento) {
		this.importeDescuento = importeDescuento;
	}

	public double getImporteEstandarProducto() {
		return this.importeEstandarProducto;
	}

	public void setImporteEstandarProducto(double importeEstandarProducto) {
		this.importeEstandarProducto = importeEstandarProducto;
	}

	public double getImporteValorAnadido() {
		return this.importeValorAnadido;
	}

	public void setImporteValorAnadido(double importeValorAnadido) {
		this.importeValorAnadido = importeValorAnadido;
	}

	public int getLongitudRegistro() {
		return this.longitudRegistro;
	}

	public void setLongitudRegistro(int longitudRegistro) {
		this.longitudRegistro = longitudRegistro;
	}

	public double getMinutosTotal() {
		return this.minutosTotal;
	}

	public void setMinutosTotal(double minutosTotal) {
		this.minutosTotal = minutosTotal;
	}

	public String getMulticonexion() {
		return this.multiconexion;
	}

	public void setMulticonexion(String multiconexion) {
		this.multiconexion = multiconexion;
	}

	public String getNSolicitadoLlamante() {
		return this.nSolicitadoLlamante;
	}

	public void setNSolicitadoLlamante(String nSolicitadoLlamante) {
		this.nSolicitadoLlamante = nSolicitadoLlamante;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombrePaisPorPrefijo() {
		return this.nombrePaisPorPrefijo;
	}

	public void setNombrePaisPorPrefijo(String nombrePaisPorPrefijo) {
		this.nombrePaisPorPrefijo = nombrePaisPorPrefijo;
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

	public String getNumeroLlamado() {
		return this.numeroLlamado;
	}

	public void setNumeroLlamado(String numeroLlamado) {
		this.numeroLlamado = numeroLlamado;
	}

	public int getOperador() {
		return this.operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPaisPorPrefijo() {
		return this.paisPorPrefijo;
	}

	public void setPaisPorPrefijo(String paisPorPrefijo) {
		this.paisPorPrefijo = paisPorPrefijo;
	}

	public double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public double getPrecioAcuerdo() {
		return this.precioAcuerdo;
	}

	public void setPrecioAcuerdo(double precioAcuerdo) {
		this.precioAcuerdo = precioAcuerdo;
	}

	public String getPrecioEspecial() {
		return this.precioEspecial;
	}

	public void setPrecioEspecial(String precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public double getPrecioPorMinuto() {
		return this.precioPorMinuto;
	}

	public void setPrecioPorMinuto(double precioPorMinuto) {
		this.precioPorMinuto = precioPorMinuto;
	}

	public String getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public String getTipoDeServicio() {
		return this.tipoDeServicio;
	}

	public void setTipoDeServicio(String tipoDeServicio) {
		this.tipoDeServicio = tipoDeServicio;
	}

	public String getTipoDescuento() {
		return this.tipoDescuento;
	}

	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}