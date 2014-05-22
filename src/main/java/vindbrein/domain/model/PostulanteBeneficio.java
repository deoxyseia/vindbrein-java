package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the postulante_beneficio database table.
 * 
 */
@Entity
@Table(name="postulante_beneficio")
@NamedQuery(name="PostulanteBeneficio.findAll", query="SELECT p FROM PostulanteBeneficio p")
public class PostulanteBeneficio implements Serializable {
	private static final long serialVersionUID = 1L;
	private PostulanteBeneficioPK id;
	private Beneficio beneficio;
	private Postulante postulante;

	public PostulanteBeneficio() {
	}


	@EmbeddedId
	public PostulanteBeneficioPK getId() {
		return this.id;
	}

	public void setId(PostulanteBeneficioPK id) {
		this.id = id;
	}


	//bi-directional one-to-one association to Beneficio
	@OneToOne
	@JoinColumn(name="fk_bene_id", insertable=false, updatable=false)
	public Beneficio getBeneficio() {
		return this.beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
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