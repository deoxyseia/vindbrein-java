package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the postulante_curso database table.
 * 
 */
@Entity
@Table(name="postulante_curso")
@NamedQuery(name="PostulanteCurso.findAll", query="SELECT p FROM PostulanteCurso p")
public class PostulanteCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	private PostulanteCursoPK id;
	private int nota;
	private Curso curso;
	private Postulante postulante;

	public PostulanteCurso() {
	}


	@EmbeddedId
	public PostulanteCursoPK getId() {
		return this.id;
	}

	public void setId(PostulanteCursoPK id) {
		this.id = id;
	}


	public int getNota() {
		return this.nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}


	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="fk_curs_id",insertable=false, updatable=false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id",insertable=false, updatable=false)
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

}