package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the postulante_conocimiento database table.
 * 
 */
@Entity
@Table(name="postulante_conocimiento")
@NamedQuery(name="PostulanteConocimiento.findAll", query="SELECT p FROM PostulanteConocimiento p")
public class PostulanteConocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private PostulanteConocimientoPK id;
	private Postulante postulante;
	private Conocimiento conocimiento;
	private NivelConocimiento nivelConocimiento;

	public PostulanteConocimiento() {
	}


	@EmbeddedId
	public PostulanteConocimientoPK getId() {
		return this.id;
	}

	public void setId(PostulanteConocimientoPK id) {
		this.id = id;
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


	//bi-directional many-to-one association to Conocimiento
	@ManyToOne
	@JoinColumn(name="fk_cono_id",insertable=false, updatable=false)
	public Conocimiento getConocimiento() {
		return this.conocimiento;
	}

	public void setConocimiento(Conocimiento conocimiento) {
		this.conocimiento = conocimiento;
	}


	//bi-directional many-to-one association to NivelConocimiento
	@ManyToOne
	@JoinColumn(name="fk_nico_id",insertable=false, updatable=false)
	public NivelConocimiento getNivelConocimiento() {
		return this.nivelConocimiento;
	}

	public void setNivelConocimiento(NivelConocimiento nivelConocimiento) {
		this.nivelConocimiento = nivelConocimiento;
	}

}