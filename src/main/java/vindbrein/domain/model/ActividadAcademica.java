package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the actividad_academica database table.
 * 
 */
@Entity
@Table(name="actividad_academica")
@NamedQuery(name="ActividadAcademica.findAll", query="SELECT a FROM ActividadAcademica a")
public class ActividadAcademica implements Serializable {
	private static final long serialVersionUID = 1L;
	private int acacId;
	private Date acacFechaFinal;
	private Date acacFechaInicio;
	private int acacPorcentajeAvance;
	private Estudio estudio;
	private Postulante postulante;

	public ActividadAcademica() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="acac_id")
	public int getAcacId() {
		return this.acacId;
	}

	public void setAcacId(int acacId) {
		this.acacId = acacId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="acac_fecha_final")
	public Date getAcacFechaFinal() {
		return this.acacFechaFinal;
	}

	public void setAcacFechaFinal(Date acacFechaFinal) {
		this.acacFechaFinal = acacFechaFinal;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="acac_fecha_inicio")
	public Date getAcacFechaInicio() {
		return this.acacFechaInicio;
	}

	public void setAcacFechaInicio(Date acacFechaInicio) {
		this.acacFechaInicio = acacFechaInicio;
	}


	@Column(name="acac_porcentaje_avance")
	public int getAcacPorcentajeAvance() {
		return this.acacPorcentajeAvance;
	}

	public void setAcacPorcentajeAvance(int acacPorcentajeAvance) {
		this.acacPorcentajeAvance = acacPorcentajeAvance;
	}


	//bi-directional many-to-one association to Estudio
	@ManyToOne
	@JoinColumn(name="fk_estu_id")
	public Estudio getEstudio() {
		return this.estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
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