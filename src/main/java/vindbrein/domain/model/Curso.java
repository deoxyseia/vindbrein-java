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
	private Estudio estudio;
	private List<PostulanteCurso> postulanteCursos;
	private List<PuestoPrefCurso> puestoPrefCursos;

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


	//bi-directional many-to-one association to PuestoPrefCurso
	@OneToMany(mappedBy="curso")
	public List<PuestoPrefCurso> getPuestoPrefCursos() {
		return this.puestoPrefCursos;
	}

	public void setPuestoPrefCursos(List<PuestoPrefCurso> puestoPrefCursos) {
		this.puestoPrefCursos = puestoPrefCursos;
	}

	public PuestoPrefCurso addPuestoPrefCurso(PuestoPrefCurso puestoPrefCurso) {
		getPuestoPrefCursos().add(puestoPrefCurso);
		puestoPrefCurso.setCurso(this);

		return puestoPrefCurso;
	}

	public PuestoPrefCurso removePuestoPrefCurso(PuestoPrefCurso puestoPrefCurso) {
		getPuestoPrefCursos().remove(puestoPrefCurso);
		puestoPrefCurso.setCurso(null);

		return puestoPrefCurso;
	}

}