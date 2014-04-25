package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the experiencia_laboral database table.
 * 
 */
@Entity
@Table(name="experiencia_laboral")
@NamedQuery(name="ExperienciaLaboral.findAll", query="SELECT e FROM ExperienciaLaboral e")
public class ExperienciaLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	private int exlaId;
	private String exlaFechaFinal;
	private String exlaFechaInicio;
	private int exlaTiempoMeses;
	private TipoPuesto tipoPuesto;
	private Postulante postulante;

	public ExperienciaLaboral() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="exla_id")
	public int getExlaId() {
		return this.exlaId;
	}

	public void setExlaId(int exlaId) {
		this.exlaId = exlaId;
	}


	@Column(name="exla_fecha_final")
	public String getExlaFechaFinal() {
		return this.exlaFechaFinal;
	}

	public void setExlaFechaFinal(String exlaFechaFinal) {
		this.exlaFechaFinal = exlaFechaFinal;
	}


	@Column(name="exla_fecha_inicio")
	public String getExlaFechaInicio() {
		return this.exlaFechaInicio;
	}

	public void setExlaFechaInicio(String exlaFechaInicio) {
		this.exlaFechaInicio = exlaFechaInicio;
	}


	@Column(name="exla_tiempo_meses")
	public int getExlaTiempoMeses() {
		return this.exlaTiempoMeses;
	}

	public void setExlaTiempoMeses(int exlaTiempoMeses) {
		this.exlaTiempoMeses = exlaTiempoMeses;
	}


	//bi-directional many-to-one association to TipoPuesto
	@ManyToOne
	@JoinColumn(name="fk_tipu_id")
	public TipoPuesto getTipoPuesto() {
		return this.tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id")
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

}