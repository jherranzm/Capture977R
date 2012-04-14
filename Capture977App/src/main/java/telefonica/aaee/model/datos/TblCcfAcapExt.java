package telefonica.aaee.model.datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


/**
 * The persistent class for the tbl_ccf_acap_ext database table.
 * 
 */
@Entity
@Table(name="tbl_ccf_acap_ext")
@XmlRootElement
public class TblCcfAcapExt 

	extends telefonica.aaee.model.GenericXML
	implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	private String acuerdo;

	@Column(name="AGRUPACION_FACTURABLE")
	private String agrupacionFacturable;

	private double BOE_con_descuentos;

	private String campoClave;

	private String cif;

	private String CIF_CLIENTE_Clave;

	@Column(name="CIF_REASIGNADO")
	private String cifReasignado;

	private String cliente;

	@Column(name="CODIGO_CONCEPTO")
	private String codigoConcepto;

	@Column(name="CODIGO_DESCUENTO_1")
	private String codigoDescuento1;

	@Column(name="CODIGO_DESCUENTO_2")
	private String codigoDescuento2;

	@Column(name="CODIGO_DESCUENTO_3")
	private String codigoDescuento3;

	@Column(name="CODIGO_PAQUETE")
	private String codigoPaquete;

	@Column(name="CODIGO_PERSONALIZACION")
	private String codigoPersonalizacion;

	@Column(name="CODIGO_PERSONALIZADO_1")
	private String codigoPersonalizado1;

	@Column(name="CODIGO_PERSONALIZADO_2")
	private String codigoPersonalizado2;

	@Column(name="CODIGO_PERSONALIZADO_3")
	private String codigoPersonalizado3;

	@Column(name="CODIGO_REGISTRO")
	private int codigoRegistro;

	@Column(name="CODIGO_REGISTRO_EXT")
	private String codigoRegistroExt;

	@Column(name="CONCEPTO_FACTURABLE")
	private String conceptoFacturable;

	@Column(name="DESC_CODIGO_CONCEPTO")
	private String descCodigoConcepto;

	@Column(name="DESC_CODIGO_DESCUENTO_1")
	private String descCodigoDescuento1;

	@Column(name="DESC_CODIGO_DESCUENTO_2")
	private String descCodigoDescuento2;

	@Column(name="DESC_CODIGO_DESCUENTO_3")
	private String descCodigoDescuento3;

	@Column(name="DESC_CONCEPTO_FACTURABLE")
	private String descConceptoFacturable;

	@Column(name="DESC_NIVEL_IMPOSITIVO")
	private String descNivelImpositivo;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="DESC_VALORACION")
	private String descValoracion;

	@Column(name="DESCRIPCION_PERIODO_1")
	private String descripcionPeriodo1;

	@Column(name="DESCRIPCION_PERIODO_2")
	private String descripcionPeriodo2;

	private double descuentos_BOE;

	@Column(name="FECHA_FACTURA")
	private String fechaFactura;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_FIN_PERIODO")
	private Date fechaFinPeriodo;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_INICIO_PERIODO")
	private Date fechaInicioPeriodo;

	private String fichero;

	@Column(name="GRUPO_DE_GASTO")
	private String grupoDeGasto;

	@Column(name="importe_acuerdo")
	private double importeAcuerdo;

	private double importe_BOE;

	@Column(name="IMPORTE_DESCUENTO_1")
	private double importeDescuento1;

	@Column(name="IMPORTE_DESCUENTO_2")
	private double importeDescuento2;

	@Column(name="IMPORTE_DESCUENTO_3")
	private double importeDescuento3;

	@Column(name="IMPORTE_DISTANCIA")
	private double importeDistancia;

	@Column(name="IMPORTE_ESTANDAR_PRODUCTO")
	private double importeEstandarProducto;

	@Column(name="IMPORTE_TOTAL_EQUIPO")
	private double importeTotalEquipo;

	@Column(name="IMPORTE_UNITARIO")
	private double importeUnitario;

	@Column(name="LONGITUD_REGISTRO")
	private int longitudRegistro;

	private String multiconexion;

	@Column(name="NIVEL_IMPOSITIVO")
	private String nivelImpositivo;

	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;

	@Column(name="NOMBRE_CLIENTE_REASIGNADO")
	private String nombreClienteReasignado;

	@Column(name="NUMERO_COMERCIAL")
	private String numeroComercial;

	@Column(name="NUMERO_COMERCIAL_ASOCIADO")
	private String numeroComercialAsociado;

	@Column(name="precio_acuerdo")
	private double precioAcuerdo;

	@Column(name="precio_especial")
	private String precioEspecial;

	private int secuencial;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	private String tipo_Doc;

	@Column(name="TIPO_FACTURACION")
	private String tipoFacturacion;

	@Column(name="TIPO_IMPOSITIVO")
	private double tipoImpositivo;

	@Column(name="tipo_precio_especial")
	private String tipoPrecioEspecial;

	@Column(name="TIPO_VALORACION")
	private String tipoValoracion;

	private int unidades;

	@Column(name="UNIDADES_PERIODO_1")
	private int unidadesPeriodo1;

	@Column(name="UNIDADES_PERIODO_2")
	private int unidadesPeriodo2;

    public TblCcfAcapExt() {
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

	public double getBOE_con_descuentos() {
		return this.BOE_con_descuentos;
	}

	public void setBOE_con_descuentos(double BOE_con_descuentos) {
		this.BOE_con_descuentos = BOE_con_descuentos;
	}

	public String getCampoClave() {
		return this.campoClave;
	}

	public void setCampoClave(String campoClave) {
		this.campoClave = campoClave;
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

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigoConcepto() {
		return this.codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
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

	public String getCodigoPaquete() {
		return this.codigoPaquete;
	}

	public void setCodigoPaquete(String codigoPaquete) {
		this.codigoPaquete = codigoPaquete;
	}

	public String getCodigoPersonalizacion() {
		return this.codigoPersonalizacion;
	}

	public void setCodigoPersonalizacion(String codigoPersonalizacion) {
		this.codigoPersonalizacion = codigoPersonalizacion;
	}

	public String getCodigoPersonalizado1() {
		return this.codigoPersonalizado1;
	}

	public void setCodigoPersonalizado1(String codigoPersonalizado1) {
		this.codigoPersonalizado1 = codigoPersonalizado1;
	}

	public String getCodigoPersonalizado2() {
		return this.codigoPersonalizado2;
	}

	public void setCodigoPersonalizado2(String codigoPersonalizado2) {
		this.codigoPersonalizado2 = codigoPersonalizado2;
	}

	public String getCodigoPersonalizado3() {
		return this.codigoPersonalizado3;
	}

	public void setCodigoPersonalizado3(String codigoPersonalizado3) {
		this.codigoPersonalizado3 = codigoPersonalizado3;
	}

	public int getCodigoRegistro() {
		return this.codigoRegistro;
	}

	public void setCodigoRegistro(int codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getCodigoRegistroExt() {
		return this.codigoRegistroExt;
	}

	public void setCodigoRegistroExt(String codigoRegistroExt) {
		this.codigoRegistroExt = codigoRegistroExt;
	}

	public String getConceptoFacturable() {
		return this.conceptoFacturable;
	}

	public void setConceptoFacturable(String conceptoFacturable) {
		this.conceptoFacturable = conceptoFacturable;
	}

	public String getDescCodigoConcepto() {
		return this.descCodigoConcepto;
	}

	public void setDescCodigoConcepto(String descCodigoConcepto) {
		this.descCodigoConcepto = descCodigoConcepto;
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

	public String getDescConceptoFacturable() {
		return this.descConceptoFacturable;
	}

	public void setDescConceptoFacturable(String descConceptoFacturable) {
		this.descConceptoFacturable = descConceptoFacturable;
	}

	public String getDescNivelImpositivo() {
		return this.descNivelImpositivo;
	}

	public void setDescNivelImpositivo(String descNivelImpositivo) {
		this.descNivelImpositivo = descNivelImpositivo;
	}

	public String getDescTipoDeServicio() {
		return this.descTipoDeServicio;
	}

	public void setDescTipoDeServicio(String descTipoDeServicio) {
		this.descTipoDeServicio = descTipoDeServicio;
	}

	public String getDescValoracion() {
		return this.descValoracion;
	}

	public void setDescValoracion(String descValoracion) {
		this.descValoracion = descValoracion;
	}

	public String getDescripcionPeriodo1() {
		return this.descripcionPeriodo1;
	}

	public void setDescripcionPeriodo1(String descripcionPeriodo1) {
		this.descripcionPeriodo1 = descripcionPeriodo1;
	}

	public String getDescripcionPeriodo2() {
		return this.descripcionPeriodo2;
	}

	public void setDescripcionPeriodo2(String descripcionPeriodo2) {
		this.descripcionPeriodo2 = descripcionPeriodo2;
	}

	public double getDescuentos_BOE() {
		return this.descuentos_BOE;
	}

	public void setDescuentos_BOE(double descuentos_BOE) {
		this.descuentos_BOE = descuentos_BOE;
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

	public String getGrupoDeGasto() {
		return this.grupoDeGasto;
	}

	public void setGrupoDeGasto(String grupoDeGasto) {
		this.grupoDeGasto = grupoDeGasto;
	}

	public double getImporteAcuerdo() {
		return this.importeAcuerdo;
	}

	public void setImporteAcuerdo(double importeAcuerdo) {
		this.importeAcuerdo = importeAcuerdo;
	}

	public double getImporte_BOE() {
		return this.importe_BOE;
	}

	public void setImporte_BOE(double importe_BOE) {
		this.importe_BOE = importe_BOE;
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

	public double getImporteDistancia() {
		return this.importeDistancia;
	}

	public void setImporteDistancia(double importeDistancia) {
		this.importeDistancia = importeDistancia;
	}

	public double getImporteEstandarProducto() {
		return this.importeEstandarProducto;
	}

	public void setImporteEstandarProducto(double importeEstandarProducto) {
		this.importeEstandarProducto = importeEstandarProducto;
	}

	public double getImporteTotalEquipo() {
		return this.importeTotalEquipo;
	}

	public void setImporteTotalEquipo(double importeTotalEquipo) {
		this.importeTotalEquipo = importeTotalEquipo;
	}

	public double getImporteUnitario() {
		return this.importeUnitario;
	}

	public void setImporteUnitario(double importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	public int getLongitudRegistro() {
		return this.longitudRegistro;
	}

	public void setLongitudRegistro(int longitudRegistro) {
		this.longitudRegistro = longitudRegistro;
	}

	public String getMulticonexion() {
		return this.multiconexion;
	}

	public void setMulticonexion(String multiconexion) {
		this.multiconexion = multiconexion;
	}

	public String getNivelImpositivo() {
		return this.nivelImpositivo;
	}

	public void setNivelImpositivo(String nivelImpositivo) {
		this.nivelImpositivo = nivelImpositivo;
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

	public int getSecuencial() {
		return this.secuencial;
	}

	public void setSecuencial(int secuencial) {
		this.secuencial = secuencial;
	}

	public String getTipoDeServicio() {
		return this.tipoDeServicio;
	}

	public void setTipoDeServicio(String tipoDeServicio) {
		this.tipoDeServicio = tipoDeServicio;
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

	public double getTipoImpositivo() {
		return this.tipoImpositivo;
	}

	public void setTipoImpositivo(double tipoImpositivo) {
		this.tipoImpositivo = tipoImpositivo;
	}

	public String getTipoPrecioEspecial() {
		return this.tipoPrecioEspecial;
	}

	public void setTipoPrecioEspecial(String tipoPrecioEspecial) {
		this.tipoPrecioEspecial = tipoPrecioEspecial;
	}

	public String getTipoValoracion() {
		return this.tipoValoracion;
	}

	public void setTipoValoracion(String tipoValoracion) {
		this.tipoValoracion = tipoValoracion;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getUnidadesPeriodo1() {
		return this.unidadesPeriodo1;
	}

	public void setUnidadesPeriodo1(int unidadesPeriodo1) {
		this.unidadesPeriodo1 = unidadesPeriodo1;
	}

	public int getUnidadesPeriodo2() {
		return this.unidadesPeriodo2;
	}

	public void setUnidadesPeriodo2(int unidadesPeriodo2) {
		this.unidadesPeriodo2 = unidadesPeriodo2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}














	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TblCcfAcapExt [id=" + id + ",\\n acuerdo=" + acuerdo + ",\\n agrupacionFacturable="
				+ agrupacionFacturable + ",\\n BOE_con_descuentos=" + BOE_con_descuentos + ",\\n campoClave="
				+ campoClave + ",\\n cif=" + cif + ",\\n CIF_CLIENTE_Clave=" + CIF_CLIENTE_Clave
				+ ",\\n cifReasignado=" + cifReasignado + ",\\n cliente=" + cliente + ",\\n codigoConcepto="
				+ codigoConcepto + ",\\n codigoDescuento1=" + codigoDescuento1 + ",\\n codigoDescuento2="
				+ codigoDescuento2 + ",\\n codigoDescuento3=" + codigoDescuento3 + ",\\n codigoPaquete="
				+ codigoPaquete + ",\\n codigoPersonalizacion=" + codigoPersonalizacion + ",\\n codigoPersonalizado1="
				+ codigoPersonalizado1 + ",\\n codigoPersonalizado2=" + codigoPersonalizado2
				+ ",\\n codigoPersonalizado3=" + codigoPersonalizado3 + ",\\n codigoRegistro=" + codigoRegistro
				+ ",\\n codigoRegistroExt=" + codigoRegistroExt + ",\\n conceptoFacturable=" + conceptoFacturable
				+ ",\\n descCodigoConcepto=" + descCodigoConcepto + ",\\n descCodigoDescuento1=" + descCodigoDescuento1
				+ ",\\n descCodigoDescuento2=" + descCodigoDescuento2 + ",\\n descCodigoDescuento3="
				+ descCodigoDescuento3 + ",\\n descConceptoFacturable=" + descConceptoFacturable
				+ ",\\n descNivelImpositivo=" + descNivelImpositivo + ",\\n descTipoDeServicio=" + descTipoDeServicio
				+ ",\\n descValoracion=" + descValoracion + ",\\n descripcionPeriodo1=" + descripcionPeriodo1
				+ ",\\n descripcionPeriodo2=" + descripcionPeriodo2 + ",\\n descuentos_BOE=" + descuentos_BOE
				+ ",\\n fechaFactura=" + fechaFactura + ",\\n fechaFinPeriodo=" + fechaFinPeriodo
				+ ",\\n fechaInicioPeriodo=" + fechaInicioPeriodo + ",\\n fichero=" + fichero + ",\\n grupoDeGasto="
				+ grupoDeGasto + ",\\n importeAcuerdo=" + importeAcuerdo + ",\\n importe_BOE=" + importe_BOE
				+ ",\\n importeDescuento1=" + importeDescuento1 + ",\\n importeDescuento2=" + importeDescuento2
				+ ",\\n importeDescuento3=" + importeDescuento3 + ",\\n importeDistancia=" + importeDistancia
				+ ",\\n importeEstandarProducto=" + importeEstandarProducto + ",\\n importeTotalEquipo="
				+ importeTotalEquipo + ",\\n importeUnitario=" + importeUnitario + ",\\n longitudRegistro="
				+ longitudRegistro + ",\\n multiconexion=" + multiconexion + ",\\n nivelImpositivo=" + nivelImpositivo
				+ ",\\n nombreCliente=" + nombreCliente + ",\\n nombreClienteReasignado=" + nombreClienteReasignado
				+ ",\\n numeroComercial=" + numeroComercial + ",\\n numeroComercialAsociado=" + numeroComercialAsociado
				+ ",\\n precioAcuerdo=" + precioAcuerdo + ",\\n precioEspecial=" + precioEspecial + ",\\n secuencial="
				+ secuencial + ",\\n tipoDeServicio=" + tipoDeServicio + ",\\n tipo_Doc=" + tipo_Doc
				+ ",\\n tipoFacturacion=" + tipoFacturacion + ",\\n tipoImpositivo=" + tipoImpositivo
				+ ",\\n tipoPrecioEspecial=" + tipoPrecioEspecial + ",\\n tipoValoracion=" + tipoValoracion
				+ ",\\n unidades=" + unidades + ",\\n unidadesPeriodo1=" + unidadesPeriodo1 + ",\\n unidadesPeriodo2="
				+ unidadesPeriodo2 + "]";
	}
	
	
	

}