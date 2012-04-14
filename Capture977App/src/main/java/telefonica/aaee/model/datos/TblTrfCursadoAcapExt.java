package telefonica.aaee.model.datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


/**
 * The persistent class for the tbl_trf_cursado_acap_ext database table.
 * 
 */
@Entity
@Table(name="tbl_trf_cursado_acap_ext")
@XmlRootElement
public class TblTrfCursadoAcapExt 

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

	@Column(name="CIF_REASIGNADO")
	private String cifReasignado;

	@Column(name="CODIGO_DESCUENTO_1")
	private String codigoDescuento1;

	@Column(name="CODIGO_DESCUENTO_2")
	private String codigoDescuento2;

	@Column(name="CODIGO_DESCUENTO_3")
	private String codigoDescuento3;

	@Column(name="CODIGO_DESCUENTO_4")
	private String codigoDescuento4;

	@Column(name="CODIGO_DESCUENTO_5")
	private String codigoDescuento5;

	@Column(name="CODIGO_TRAFICO")
	private String codigoTrafico;

	private String CUC_Cliente;

	@Column(name="DESC_AMBITO_DE_TRAFICO")
	private String descAmbitoDeTrafico;

	@Column(name="DESC_CODIGO_DESCUENTO_1")
	private String descCodigoDescuento1;

	@Column(name="DESC_CODIGO_DESCUENTO_2")
	private String descCodigoDescuento2;

	@Column(name="DESC_CODIGO_DESCUENTO_3")
	private String descCodigoDescuento3;

	@Column(name="DESC_CODIGO_DESCUENTO_4")
	private String descCodigoDescuento4;

	@Column(name="DESC_CODIGO_DESCUENTO_5")
	private String descCodigoDescuento5;

	@Column(name="DESC_CODIGO_TRAFICO")
	private String descCodigoTrafico;

	@Column(name="DESC_DETALLE")
	private String descDetalle;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="DESCUENTOS_BOE")
	private double descuentosBoe;

	@Column(name="EST_LLAMADA")
	private double estLlamada;

	@Column(name="FECHA_FACTURA")
	private String fechaFactura;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_FIN_PERIODO")
	private Date fechaFinPeriodo;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_INICIO_PERIODO")
	private Date fechaInicioPeriodo;

	private String fichero;

	@Column(name="IMPORTE_BOE")
	private double importeBoe;

	@Column(name="IMPORTE_DESCUENTO_1")
	private double importeDescuento1;

	@Column(name="IMPORTE_DESCUENTO_2")
	private double importeDescuento2;

	@Column(name="IMPORTE_DESCUENTO_3")
	private double importeDescuento3;

	@Column(name="IMPORTE_DESCUENTO_4")
	private double importeDescuento4;

	@Column(name="IMPORTE_DESCUENTO_5")
	private double importeDescuento5;

	@Column(name="IMPORTE_ESTANDAR_PRODUCTO")
	private double importeEstandarProducto;

	@Column(name="IMPORTE_VALOR_ANADIDO")
	private double importeValorAnadido;

	@Column(name="INDICADOR_DE_DETALLE")
	private String indicadorDeDetalle;

	@Column(name="MINUTOS_DURACION_SUJETA_DESCUEN_1")
	private double minutosDuracionSujetaDescuen1;

	@Column(name="MINUTOS_DURACION_SUJETA_DESCUEN_2")
	private double minutosDuracionSujetaDescuen2;

	@Column(name="MINUTOS_DURACION_SUJETA_DESCUEN_3")
	private double minutosDuracionSujetaDescuen3;

	@Column(name="MINUTOS_DURACION_SUJETA_DESCUEN_4")
	private double minutosDuracionSujetaDescuen4;

	@Column(name="MINUTOS_DURACION_SUJETA_DESCUEN_5")
	private double minutosDuracionSujetaDescuen5;

	@Column(name="MINUTOS_TOTAL")
	private double minutosTotal;

	private String multiconexion;

	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;

	@Column(name="NOMBRE_CLIENTE_REASIGNADO")
	private String nombreClienteReasignado;

	@Column(name="NUMERO_COMERCIAL")
	private String numeroComercial;

	@Column(name="NUMERO_COMERCIAL_ASOCIADO")
	private String numeroComercialAsociado;

	@Column(name="NUMERO_DE_LLAMADAS")
	private int numeroDeLlamadas;

	@Column(name="PORCENTAJE_DESCUENTO")
	private double porcentajeDescuento;

	@Column(name="PRECIO_ACUERDO")
	private double precioAcuerdo;

	@Column(name="PRECIO_ESPECIAL")
	private String precioEspecial;

	@Column(name="PRECIO_POR_MINUTO")
	private double precioPorMinuto;

	@Column(name="SUPRA_CIF")
	private String supraCif;

	private String SUPRA_Tipo_Doc;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	@Column(name="TIPO_DESCUENTO")
	private String tipoDescuento;

	private String tipo_Doc;

	@Column(name="TIPO_FACTURACION")
	private String tipoFacturacion;

	private String traficoNovacom;

    public TblTrfCursadoAcapExt() {
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

	public String getCifReasignado() {
		return this.cifReasignado;
	}

	public void setCifReasignado(String cifReasignado) {
		this.cifReasignado = cifReasignado;
	}

	public String getCodigoDescuento1() {
		return this.codigoDescuento1;
	}

	public void setCodigoDescuento1(String codigoDescuento1) {
		this.codigoDescuento1 = codigoDescuento1;
	}

	public String getCodigoDescuento2() {
		return this.codigoDescuento2;
	}

	public void setCodigoDescuento2(String codigoDescuento2) {
		this.codigoDescuento2 = codigoDescuento2;
	}

	public String getCodigoDescuento3() {
		return this.codigoDescuento3;
	}

	public void setCodigoDescuento3(String codigoDescuento3) {
		this.codigoDescuento3 = codigoDescuento3;
	}

	public String getCodigoDescuento4() {
		return this.codigoDescuento4;
	}

	public void setCodigoDescuento4(String codigoDescuento4) {
		this.codigoDescuento4 = codigoDescuento4;
	}

	public String getCodigoDescuento5() {
		return this.codigoDescuento5;
	}

	public void setCodigoDescuento5(String codigoDescuento5) {
		this.codigoDescuento5 = codigoDescuento5;
	}

	public String getCodigoTrafico() {
		return this.codigoTrafico;
	}

	public void setCodigoTrafico(String codigoTrafico) {
		this.codigoTrafico = codigoTrafico;
	}

	public String getCUC_Cliente() {
		return this.CUC_Cliente;
	}

	public void setCUC_Cliente(String CUC_Cliente) {
		this.CUC_Cliente = CUC_Cliente;
	}

	public String getDescAmbitoDeTrafico() {
		return this.descAmbitoDeTrafico;
	}

	public void setDescAmbitoDeTrafico(String descAmbitoDeTrafico) {
		this.descAmbitoDeTrafico = descAmbitoDeTrafico;
	}

	public String getDescCodigoDescuento1() {
		return this.descCodigoDescuento1;
	}

	public void setDescCodigoDescuento1(String descCodigoDescuento1) {
		this.descCodigoDescuento1 = descCodigoDescuento1;
	}

	public String getDescCodigoDescuento2() {
		return this.descCodigoDescuento2;
	}

	public void setDescCodigoDescuento2(String descCodigoDescuento2) {
		this.descCodigoDescuento2 = descCodigoDescuento2;
	}

	public String getDescCodigoDescuento3() {
		return this.descCodigoDescuento3;
	}

	public void setDescCodigoDescuento3(String descCodigoDescuento3) {
		this.descCodigoDescuento3 = descCodigoDescuento3;
	}

	public String getDescCodigoDescuento4() {
		return this.descCodigoDescuento4;
	}

	public void setDescCodigoDescuento4(String descCodigoDescuento4) {
		this.descCodigoDescuento4 = descCodigoDescuento4;
	}

	public String getDescCodigoDescuento5() {
		return this.descCodigoDescuento5;
	}

	public void setDescCodigoDescuento5(String descCodigoDescuento5) {
		this.descCodigoDescuento5 = descCodigoDescuento5;
	}

	public String getDescCodigoTrafico() {
		return this.descCodigoTrafico;
	}

	public void setDescCodigoTrafico(String descCodigoTrafico) {
		this.descCodigoTrafico = descCodigoTrafico;
	}

	public String getDescDetalle() {
		return this.descDetalle;
	}

	public void setDescDetalle(String descDetalle) {
		this.descDetalle = descDetalle;
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

	public double getEstLlamada() {
		return this.estLlamada;
	}

	public void setEstLlamada(double estLlamada) {
		this.estLlamada = estLlamada;
	}

	public String getFechaFactura() {
		return this.fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Date getFechaFinPeriodo() {
		return this.fechaFinPeriodo;
	}

	public void setFechaFinPeriodo(Date fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}

	public Date getFechaInicioPeriodo() {
		return this.fechaInicioPeriodo;
	}

	public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
		this.fechaInicioPeriodo = fechaInicioPeriodo;
	}

	public String getFichero() {
		return this.fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public double getImporteBoe() {
		return this.importeBoe;
	}

	public void setImporteBoe(double importeBoe) {
		this.importeBoe = importeBoe;
	}

	public double getImporteDescuento1() {
		return this.importeDescuento1;
	}

	public void setImporteDescuento1(double importeDescuento1) {
		this.importeDescuento1 = importeDescuento1;
	}

	public double getImporteDescuento2() {
		return this.importeDescuento2;
	}

	public void setImporteDescuento2(double importeDescuento2) {
		this.importeDescuento2 = importeDescuento2;
	}

	public double getImporteDescuento3() {
		return this.importeDescuento3;
	}

	public void setImporteDescuento3(double importeDescuento3) {
		this.importeDescuento3 = importeDescuento3;
	}

	public double getImporteDescuento4() {
		return this.importeDescuento4;
	}

	public void setImporteDescuento4(double importeDescuento4) {
		this.importeDescuento4 = importeDescuento4;
	}

	public double getImporteDescuento5() {
		return this.importeDescuento5;
	}

	public void setImporteDescuento5(double importeDescuento5) {
		this.importeDescuento5 = importeDescuento5;
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

	public String getIndicadorDeDetalle() {
		return this.indicadorDeDetalle;
	}

	public void setIndicadorDeDetalle(String indicadorDeDetalle) {
		this.indicadorDeDetalle = indicadorDeDetalle;
	}

	public double getMinutosDuracionSujetaDescuen1() {
		return this.minutosDuracionSujetaDescuen1;
	}

	public void setMinutosDuracionSujetaDescuen1(double minutosDuracionSujetaDescuen1) {
		this.minutosDuracionSujetaDescuen1 = minutosDuracionSujetaDescuen1;
	}

	public double getMinutosDuracionSujetaDescuen2() {
		return this.minutosDuracionSujetaDescuen2;
	}

	public void setMinutosDuracionSujetaDescuen2(double minutosDuracionSujetaDescuen2) {
		this.minutosDuracionSujetaDescuen2 = minutosDuracionSujetaDescuen2;
	}

	public double getMinutosDuracionSujetaDescuen3() {
		return this.minutosDuracionSujetaDescuen3;
	}

	public void setMinutosDuracionSujetaDescuen3(double minutosDuracionSujetaDescuen3) {
		this.minutosDuracionSujetaDescuen3 = minutosDuracionSujetaDescuen3;
	}

	public double getMinutosDuracionSujetaDescuen4() {
		return this.minutosDuracionSujetaDescuen4;
	}

	public void setMinutosDuracionSujetaDescuen4(double minutosDuracionSujetaDescuen4) {
		this.minutosDuracionSujetaDescuen4 = minutosDuracionSujetaDescuen4;
	}

	public double getMinutosDuracionSujetaDescuen5() {
		return this.minutosDuracionSujetaDescuen5;
	}

	public void setMinutosDuracionSujetaDescuen5(double minutosDuracionSujetaDescuen5) {
		this.minutosDuracionSujetaDescuen5 = minutosDuracionSujetaDescuen5;
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

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreClienteReasignado() {
		return this.nombreClienteReasignado;
	}

	public void setNombreClienteReasignado(String nombreClienteReasignado) {
		this.nombreClienteReasignado = nombreClienteReasignado;
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

	public int getNumeroDeLlamadas() {
		return this.numeroDeLlamadas;
	}

	public void setNumeroDeLlamadas(int numeroDeLlamadas) {
		this.numeroDeLlamadas = numeroDeLlamadas;
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

	public String getSupraCif() {
		return this.supraCif;
	}

	public void setSupraCif(String supraCif) {
		this.supraCif = supraCif;
	}

	public String getSUPRA_Tipo_Doc() {
		return this.SUPRA_Tipo_Doc;
	}

	public void setSUPRA_Tipo_Doc(String SUPRA_Tipo_Doc) {
		this.SUPRA_Tipo_Doc = SUPRA_Tipo_Doc;
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

	public String getTraficoNovacom() {
		return this.traficoNovacom;
	}

	public void setTraficoNovacom(String traficoNovacom) {
		this.traficoNovacom = traficoNovacom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}