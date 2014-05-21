package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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
	private BigDecimal pocuNota;
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


	@Column(name="pocu_nota")
	public BigDecimal getPocuNota() {
		return this.pocuNota;
	}

	public void setPocuNota(BigDecimal pocuNota) {
		this.pocuNota = pocuNota;
	}


	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="fk_curs_id", insertable=false, updatable=false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

}