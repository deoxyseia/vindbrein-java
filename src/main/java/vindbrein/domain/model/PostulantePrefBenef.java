package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the postulante_pref_benef database table.
 * 
 */
@Entity
@Table(name="postulante_pref_benef")
@NamedQuery(name="PostulantePrefBenef.findAll", query="SELECT p FROM PostulantePrefBenef p")
public class PostulantePrefBenef implements Serializable {
	private static final long serialVersionUID = 1L;
	private int fkBeneId;
	private BeneficioLaboral beneficioLaboral;
	private Postulante postulante;

	public PostulantePrefBenef() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fk_bene_id")
	public int getFkBeneId() {
		return this.fkBeneId;
	}

	public void setFkBeneId(int fkBeneId) {
		this.fkBeneId = fkBeneId;
	}


	//bi-directional one-to-one association to BeneficioLaboral
	@OneToOne
	@JoinColumn(name="fk_bene_id")
	public BeneficioLaboral getBeneficioLaboral() {
		return this.beneficioLaboral;
	}

	public void setBeneficioLaboral(BeneficioLaboral beneficioLaboral) {
		this.beneficioLaboral = beneficioLaboral;
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