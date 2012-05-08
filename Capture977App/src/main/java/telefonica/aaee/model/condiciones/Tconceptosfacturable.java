package telefonica.aaee.model.condiciones;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the tconceptosfacturables database table.
 * 
 */
@Entity
@Table(name="tconceptosfacturables")
@XmlRootElement(name="conceptosfacturables")
public class Tconceptosfacturable 
	extends telefonica.aaee.model.GenericXML
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String acuerdo;

	@Column(name="CONCEPTO_FACTURABLE")
	private String conceptoFacturable;

	@Column(name="contratable_cf")
	private String contratableCf;

	@Column(name="DESC_CONCEPTO_FACTURABLE")
	private String descConceptoFacturable;

	@Column(name="DESC_TIPO_DE_SERVICIO")
	private String descTipoDeServicio;

	@Column(name="fin_periodo")
	private String finPeriodo;

	@Column(name="importe_acuerdo")
	private double importeAcuerdo;

	@Column(name="ini_periodo")
	private String iniPeriodo;

	@Column(name="precio_especial")
	private String precioEspecial;

	@Column(name="producto_servicio_cf")
	private String productoServicioCf;

	@Column(name="subtipo_cf")
	private String subtipoCf;

	@Column(name="tipo_cf")
	private String tipoCf;

	@Column(name="TIPO_DE_SERVICIO")
	private String tipoDeServicio;

	@Column(name="tipo_precio_especial")
	private String tipoPrecioEspecial;

    public Tconceptosfacturable() {
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

	public String getConceptoFacturable() {
		return this.conceptoFacturable;
	}

	public void setConceptoFacturable(String conceptoFacturable) {
		this.conceptoFacturable = conceptoFacturable;
	}

	public String getContratableCf() {
		return this.contratableCf;
	}

	public void setContratableCf(String contratableCf) {
		this.contratableCf = contratableCf;
	}

	public String getDescConceptoFacturable() {
		return this.descConceptoFacturable;
	}

	public void setDescConceptoFacturable(String descConceptoFacturable) {
		this.descConceptoFacturable = descConceptoFacturable;
	}

	public String getDescTipoDeServicio() {
		return this.descTipoDeServicio;
	}

	public void setDescTipoDeServicio(String descTipoDeServicio) {
		this.descTipoDeServicio = descTipoDeServicio;
	}

	public String getFinPeriodo() {
		return this.finPeriodo;
	}

	public void setFinPeriodo(String finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public double getImporteAcuerdo() {
		return this.importeAcuerdo;
	}

	public void setImporteAcuerdo(double importeAcuerdo) {
		this.importeAcuerdo = importeAcuerdo;
	}

	public String getIniPeriodo() {
		return this.iniPeriodo;
	}

	public void setIniPeriodo(String iniPeriodo) {
		this.iniPeriodo = iniPeriodo;
	}

	public String getPrecioEspecial() {
		return this.precioEspecial;
	}

	public void setPrecioEspecial(String precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public String getProductoServicioCf() {
		return this.productoServicioCf;
	}

	public void setProductoServicioCf(String productoServicioCf) {
		this.productoServicioCf = productoServicioCf;
	}

	public String getSubtipoCf() {
		return this.subtipoCf;
	}

	public void setSubtipoCf(String subtipoCf) {
		this.subtipoCf = subtipoCf;
	}

	public String getTipoCf() {
		return this.tipoCf;
	}

	public void setTipoCf(String tipoCf) {
		this.tipoCf = tipoCf;
	}

	public String getTipoDeServicio() {
		return this.tipoDeServicio;
	}

	public void setTipoDeServicio(String tipoDeServicio) {
		this.tipoDeServicio = tipoDeServicio;
	}

	public String getTipoPrecioEspecial() {
		return this.tipoPrecioEspecial;
	}

	public void setTipoPrecioEspecial(String tipoPrecioEspecial) {
		this.tipoPrecioEspecial = tipoPrecioEspecial;
	}

}