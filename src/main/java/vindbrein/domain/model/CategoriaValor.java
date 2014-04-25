package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria_valor database table.
 * 
 */
@Entity
@Table(name="categoria_valor")
@NamedQuery(name="CategoriaValor.findAll", query="SELECT c FROM CategoriaValor c")
public class CategoriaValor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cavaId;
	private int cavaCotaMaxima;
	private int cavaCotaMinima;
	private String cavaNrNombreColumna;
	private String cavaNrNombreTabla;
	private String cavaValorCode;
	private CriterioSeleccion criterioSeleccion;

	public CategoriaValor() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cava_id")
	public int getCavaId() {
		return this.cavaId;
	}

	public void setCavaId(int cavaId) {
		this.cavaId = cavaId;
	}


	@Column(name="cava_cota_maxima")
	public int getCavaCotaMaxima() {
		return this.cavaCotaMaxima;
	}

	public void setCavaCotaMaxima(int cavaCotaMaxima) {
		this.cavaCotaMaxima = cavaCotaMaxima;
	}


	@Column(name="cava_cota_minima")
	public int getCavaCotaMinima() {
		return this.cavaCotaMinima;
	}

	public void setCavaCotaMinima(int cavaCotaMinima) {
		this.cavaCotaMinima = cavaCotaMinima;
	}


	@Column(name="cava_nr_nombre_columna")
	public String getCavaNrNombreColumna() {
		return this.cavaNrNombreColumna;
	}

	public void setCavaNrNombreColumna(String cavaNrNombreColumna) {
		this.cavaNrNombreColumna = cavaNrNombreColumna;
	}


	@Column(name="cava_nr_nombre_tabla")
	public String getCavaNrNombreTabla() {
		return this.cavaNrNombreTabla;
	}

	public void setCavaNrNombreTabla(String cavaNrNombreTabla) {
		this.cavaNrNombreTabla = cavaNrNombreTabla;
	}


	@Column(name="cava_valor_code")
	public String getCavaValorCode() {
		return this.cavaValorCode;
	}

	public void setCavaValorCode(String cavaValorCode) {
		this.cavaValorCode = cavaValorCode;
	}


	//bi-directional many-to-one association to CriterioSeleccion
	@ManyToOne
	@JoinColumn(name="fk_crse_id")
	public CriterioSeleccion getCriterioSeleccion() {
		return this.criterioSeleccion;
	}

	public void setCriterioSeleccion(CriterioSeleccion criterioSeleccion) {
		this.criterioSeleccion = criterioSeleccion;
	}

}