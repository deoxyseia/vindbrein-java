package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the puesto_pref_conocim database table.
 * 
 */
@Entity
@Table(name="puesto_pref_conocim")
@NamedQuery(name="PuestoPrefConocim.findAll", query="SELECT p FROM PuestoPrefConocim p")
public class PuestoPrefConocim implements Serializable {
	private static final long serialVersionUID = 1L;
	private PuestoPrefConocimPK id;
	private Conocimiento conocimiento;
	private NivelConocimiento nivelConocimiento;
	private PreferenciaPuestoLaboral preferenciaPuestoLaboral;

	public PuestoPrefConocim() {
	}


	@EmbeddedId
	public PuestoPrefConocimPK getId() {
		return this.id;
	}

	public void setId(PuestoPrefConocimPK id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Conocimiento
	@ManyToOne
	@JoinColumn(name="fk_cono_id", insertable=false, updatable=false)
	public Conocimiento getConocimiento() {
		return this.conocimiento;
	}

	public void setConocimiento(Conocimiento conocimiento) {
		this.conocimiento = conocimiento;
	}


	//bi-directional many-to-one association to NivelConocimiento
	@ManyToOne
	@JoinColumn(name="fk_nico_id", insertable=false, updatable=false)
	public NivelConocimiento getNivelConocimiento() {
		return this.nivelConocimiento;
	}

	public void setNivelConocimiento(NivelConocimiento nivelConocimiento) {
		this.nivelConocimiento = nivelConocimiento;
	}


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@ManyToOne
	@JoinColumn(name="fk_prpu_id", insertable=false, updatable=false)
	public PreferenciaPuestoLaboral getPreferenciaPuestoLaboral() {
		return this.preferenciaPuestoLaboral;
	}

	public void setPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		this.preferenciaPuestoLaboral = preferenciaPuestoLaboral;
	}

}