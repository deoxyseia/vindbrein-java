package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@Table(name="curso")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cursId;
	private String cursDescripcion;
	private String cursNombre;
	private int cursNumeroCiclo;
	private Estudio estudio;
	private List<PostulanteCurso> postulanteCursos;
	private List<OfertaCurso> ofertaCursos;

	public Curso() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="curs_id")
	public int getCursId() {
		return this.cursId;
	}

	public void setCursId(int cursId) {
		this.cursId = cursId;
	}


	@Column(name="curs_descripcion")
	public String getCursDescripcion() {
		return this.cursDescripcion;
	}

	public void setCursDescripcion(String cursDescripcion) {
		this.cursDescripcion = cursDescripcion;
	}


	@Column(name="curs_nombre")
	public String getCursNombre() {
		return this.cursNombre;
	}

	public void setCursNombre(String cursNombre) {
		this.cursNombre = cursNombre;
	}


	@Column(name="curs_numero_ciclo")
	public int getCursNumeroCiclo() {
		return this.cursNumeroCiclo;
	}

	public void setCursNumeroCiclo(int cursNumeroCiclo) {
		this.cursNumeroCiclo = cursNumeroCiclo;
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


	//bi-directional many-to-one association to PostulanteCurso
	@OneToMany(mappedBy="curso")
	public List<PostulanteCurso> getPostulanteCursos() {
		return this.postulanteCursos;
	}

	public void setPostulanteCursos(List<PostulanteCurso> postulanteCursos) {
		this.postulanteCursos = postulanteCursos;
	}

	public PostulanteCurso addPostulanteCurso(PostulanteCurso postulanteCurso) {
		getPostulanteCursos().add(postulanteCurso);
		postulanteCurso.setCurso(this);

		return postulanteCurso;
	}

	public PostulanteCurso removePostulanteCurso(PostulanteCurso postulanteCurso) {
		getPostulanteCursos().remove(postulanteCurso);
		postulanteCurso.setCurso(null);

		return postulanteCurso;
	}


	//bi-directional many-to-one association to OfertaCurso
	@OneToMany(mappedBy="curso")
	public List<OfertaCurso> getOfertaCursos() {
		return this.ofertaCursos;
	}

	public void setOfertaCursos(List<OfertaCurso> ofertaCursos) {
		this.ofertaCursos = ofertaCursos;
	}

	public OfertaCurso addOfertaCurso(OfertaCurso ofertaCurso) {
		getOfertaCursos().add(ofertaCurso);
		ofertaCurso.setCurso(this);

		return ofertaCurso;
	}

	public OfertaCurso removeOfertaCurso(OfertaCurso ofertaCurso) {
		getOfertaCursos().remove(ofertaCurso);
		ofertaCurso.setCurso(null);

		return ofertaCurso;
	}

}