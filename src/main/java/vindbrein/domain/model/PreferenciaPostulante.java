package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the preferencia_postulante database table.
 * 
 */
@Entity
@Table(name="preferencia_postulante")
@NamedQuery(name="PreferenciaPostulante.findAll", query="SELECT p FROM PreferenciaPostulante p")
public class PreferenciaPostulante implements Serializable {
	private static final long serialVersionUID = 1L;
	private int prpoId;
	private int prpoSalario;
	private List<BeneficioLaboral> beneficioLaborals;
	private Postulante postulante;
	private TipoHorario tipoHorario;
	private TipoPuesto tipoPuesto;

	public PreferenciaPostulante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prpo_id")
	public int getPrpoId() {
		return this.prpoId;
	}

	public void setPrpoId(int prpoId) {
		this.prpoId = prpoId;
	}


	@Column(name="prpo_salario")
	public int getPrpoSalario() {
		return this.prpoSalario;
	}

	public void setPrpoSalario(int prpoSalario) {
		this.prpoSalario = prpoSalario;
	}


	//bi-directional many-to-many association to BeneficioLaboral
	@ManyToMany
	@JoinTable(
		name="postulante_pref_benef"
		, joinColumns={
			@JoinColumn(name="fk_pres_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_bene_id")
			}
		)
	public List<BeneficioLaboral> getBeneficioLaborals() {
		return this.beneficioLaborals;
	}

	public void setBeneficioLaborals(List<BeneficioLaboral> beneficioLaborals) {
		this.beneficioLaborals = beneficioLaborals;
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


	//bi-directional many-to-one association to TipoHorario
	@ManyToOne
	@JoinColumn(name="fk_tiho_id")
	public TipoHorario getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}


	//bi-directional many-to-one association to TipoPuesto
	@ManyToOne
	@JoinColumn(name="fk_tipu_id")
	public TipoPuesto getTipoPuesto() {
		return this.tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}

}