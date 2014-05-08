package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the postulante_idioma database table.
 * 
 */
@Entity
@Table(name="postulante_idioma")
@NamedQuery(name="PostulanteIdioma.findAll", query="SELECT p FROM PostulanteIdioma p")
public class PostulanteIdioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private PostulanteIdiomaPK id;
	private Idioma idioma;
	private Postulante postulante;
	private NivelIdioma nivelIdioma;

	public PostulanteIdioma() {
	}


	@EmbeddedId
	public PostulanteIdiomaPK getId() {
		return this.id;
	}

	public void setId(PostulanteIdiomaPK id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Idioma
	@ManyToOne
	@JoinColumn(name="fk_idio_id",insertable=false, updatable=false)
	public Idioma getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
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


	//bi-directional many-to-one association to NivelIdioma
	@ManyToOne
	@JoinColumn(name="fk_niid_id",insertable=false, updatable=false)
	public NivelIdioma getNivelIdioma() {
		return this.nivelIdioma;
	}

	public void setNivelIdioma(NivelIdioma nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
	}

}