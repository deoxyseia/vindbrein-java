package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the puesto_pref_idioma database table.
 * 
 */
@Entity
@Table(name="puesto_pref_idioma")
@NamedQuery(name="PuestoPrefIdioma.findAll", query="SELECT p FROM PuestoPrefIdioma p")
public class PuestoPrefIdioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private PuestoPrefIdiomaPK id;
	private PreferenciaPuestoLaboral preferenciaPuestoLaboral;
	private Idioma idioma;
	private NivelIdioma nivelIdioma;

	public PuestoPrefIdioma() {
	}


	@EmbeddedId
	public PuestoPrefIdiomaPK getId() {
		return this.id;
	}

	public void setId(PuestoPrefIdiomaPK id) {
		this.id = id;
	}


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@ManyToOne
	@JoinColumn(name="fk_prpu_id",insertable=false, updatable=false)
	public PreferenciaPuestoLaboral getPreferenciaPuestoLaboral() {
		return this.preferenciaPuestoLaboral;
	}

	public void setPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		this.preferenciaPuestoLaboral = preferenciaPuestoLaboral;
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


	//bi-directional many-to-one association to NivelIdioma
	@ManyToOne
	@JoinColumn(name="fk_niid_id")
	public NivelIdioma getNivelIdioma() {
		return this.nivelIdioma;
	}

	public void setNivelIdioma(NivelIdioma nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
	}

}