package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	private Date exlaFechaFinal;
	private Date exlaFechaInicio;
	private int exlaTiempoMeses;
	private Postulante postulante;
	private Puesto puesto;
	private OrganizacionPuesto organizacionPuesto;

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


	@Temporal(TemporalType.DATE)
	@Column(name="exla_fecha_final")
	public Date getExlaFechaFinal() {
		return this.exlaFechaFinal;
	}

	public void setExlaFechaFinal(Date exlaFechaFinal) {
		this.exlaFechaFinal = exlaFechaFinal;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="exla_fecha_inicio")
	public Date getExlaFechaInicio() {
		return this.exlaFechaInicio;
	}

	public void setExlaFechaInicio(Date exlaFechaInicio) {
		this.exlaFechaInicio = exlaFechaInicio;
	}


	@Column(name="exla_tiempo_meses")
	public int getExlaTiempoMeses() {
		return this.exlaTiempoMeses;
	}

	public void setExlaTiempoMeses(int exlaTiempoMeses) {
		this.exlaTiempoMeses = exlaTiempoMeses;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id", insertable=false, updatable=false)
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}


	//bi-directional many-to-one association to Puesto
	@ManyToOne
	@JoinColumn(name="fk_pues_id", insertable=false, updatable=false)
	public Puesto getPuesto() {
		return this.puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}


	//bi-directional many-to-one association to OrganizacionPuesto
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fk_orga_id", referencedColumnName="fk_orga_id"),
		@JoinColumn(name="fk_pues_id", referencedColumnName="fk_pues_id")
		})
	public OrganizacionPuesto getOrganizacionPuesto() {
		return this.organizacionPuesto;
	}

	public void setOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		this.organizacionPuesto = organizacionPuesto;
	}

}