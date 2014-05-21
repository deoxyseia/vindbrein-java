package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta_idioma database table.
 * 
 */
@Entity
@Table(name="oferta_idioma")
@NamedQuery(name="OfertaIdioma.findAll", query="SELECT o FROM OfertaIdioma o")
public class OfertaIdioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaIdiomaPK id;
	private Idioma idioma;
	private NivelIdioma nivelIdioma;
	private OfertaLaboral ofertaLaboral;

	public OfertaIdioma() {
	}


	@EmbeddedId
	public OfertaIdiomaPK getId() {
		return this.id;
	}

	public void setId(OfertaIdiomaPK id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Idioma
	@ManyToOne
	@JoinColumn(name="fk_idio_id", insertable=false, updatable=false)
	public Idioma getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}


	//bi-directional many-to-one association to NivelIdioma
	@ManyToOne
	@JoinColumn(name="fk_niid_id", insertable=false, updatable=false)
	public NivelIdioma getNivelIdioma() {
		return this.nivelIdioma;
	}

	public void setNivelIdioma(NivelIdioma nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
	}


	//bi-directional many-to-one association to OfertaLaboral
	@ManyToOne
	@JoinColumn(name="fk_ofla_id", insertable=false, updatable=false)
	public OfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}

	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}

}