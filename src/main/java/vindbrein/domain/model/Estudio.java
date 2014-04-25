package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estudio database table.
 * 
 */
@Entity
@Table(name="estudio")
@NamedQuery(name="Estudio.findAll", query="SELECT e FROM Estudio e")
public class Estudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int estuId;
	private String estudNombre;
	private List<ActividadAcademica> actividadAcademicas;
	private List<Curso> cursos;
	private CentroEstudio centroEstudio;
	private Grado grado;
	private List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals;

	public Estudio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="estu_id")
	public int getEstuId() {
		return this.estuId;
	}

	public void setEstuId(int estuId) {
		this.estuId = estuId;
	}


	@Column(name="estud_nombre")
	public String getEstudNombre() {
		return this.estudNombre;
	}

	public void setEstudNombre(String estudNombre) {
		this.estudNombre = estudNombre;
	}


	//bi-directional many-to-one association to ActividadAcademica
	@OneToMany(mappedBy="estudio")
	public List<ActividadAcademica> getActividadAcademicas() {
		return this.actividadAcademicas;
	}

	public void setActividadAcademicas(List<ActividadAcademica> actividadAcademicas) {
		this.actividadAcademicas = actividadAcademicas;
	}

	public ActividadAcademica addActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicas().add(actividadAcademica);
		actividadAcademica.setEstudio(this);

		return actividadAcademica;
	}

	public ActividadAcademica removeActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicas().remove(actividadAcademica);
		actividadAcademica.setEstudio(null);

		return actividadAcademica;
	}


	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="estudio")
	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setEstudio(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setEstudio(null);

		return curso;
	}


	//bi-directional many-to-one association to CentroEstudio
	@ManyToOne
	@JoinColumn(name="fk_cees_id")
	public CentroEstudio getCentroEstudio() {
		return this.centroEstudio;
	}

	public void setCentroEstudio(CentroEstudio centroEstudio) {
		this.centroEstudio = centroEstudio;
	}


	//bi-directional many-to-one association to Grado
	@ManyToOne
	@JoinColumn(name="fk_grad_id")
	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}


	//bi-directional many-to-many association to PreferenciaPuestoLaboral
	@ManyToMany(mappedBy="estudios")
	public List<PreferenciaPuestoLaboral> getPreferenciaPuestoLaborals() {
		return this.preferenciaPuestoLaborals;
	}

	public void setPreferenciaPuestoLaborals(List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals) {
		this.preferenciaPuestoLaborals = preferenciaPuestoLaborals;
	}

}