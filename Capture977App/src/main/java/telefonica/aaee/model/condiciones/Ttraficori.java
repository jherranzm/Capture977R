package telefonica.aaee.model.condiciones;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the ttraficori database table.
 * 
 */
@Entity
@XmlRootElement
public class Ttraficori 

	extends telefonica.aaee.model.GenericXML

	implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String acuerdo;

	@Column(name="AMBITO_DE_TRAFICO")
	private String ambitoDeTrafico;

	private String cif;

	@Column(name="DESC_AMBITO_DE_TRAFICO")
	private String descAmbitoDeTrafico;

	@Column(name="EST_LLAMADA")
	private double estLlamada;

	@Column(name="fin_periodo")
	private String finPeriodo;

	@Column(name="ini_periodo")
	private String iniPeriodo;

	@Column(name="PORCENTAJE_DESCUENTO")
	private double porcentajeDescuento;

	@Column(name="PRECIO_ESPECIAL")
	private String precioEspecial;

	@Column(name="PRECIO_POR_MINUTO")
	private double precioPorMinuto;

	@Column(name="TIPO_DESCUENTO")
	private String tipoDescuento;

    public Ttraficori() {
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

	public String getAmbitoDeTrafico() {
		return this.ambitoDeTrafico;
	}

	public void setAmbitoDeTrafico(String ambitoDeTrafico) {
		this.ambitoDeTrafico = ambitoDeTrafico;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDescAmbitoDeTrafico() {
		return this.descAmbitoDeTrafico;
	}

	public void setDescAmbitoDeTrafico(String descAmbitoDeTrafico) {
		this.descAmbitoDeTrafico = descAmbitoDeTrafico;
	}

	public double getEstLlamada() {
		return this.estLlamada;
	}

	public void setEstLlamada(double estLlamada) {
		this.estLlamada = estLlamada;
	}

	public String getFinPeriodo() {
		return this.finPeriodo;
	}

	public void setFinPeriodo(String finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public String getIniPeriodo() {
		return this.iniPeriodo;
	}

	public void setIniPeriodo(String iniPeriodo) {
		this.iniPeriodo = iniPeriodo;
	}

	public double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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

	public String getTipoDescuento() {
		return this.tipoDescuento;
	}

	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

}