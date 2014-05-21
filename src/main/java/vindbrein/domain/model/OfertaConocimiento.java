package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta_conocimiento database table.
 * 
 */
@Entity
@Table(name="oferta_conocimiento")
@NamedQuery(name="OfertaConocimiento.findAll", query="SELECT o FROM OfertaConocimiento o")
public class OfertaConocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaConocimientoPK id;
	private Conocimiento conocimiento;
	private NivelConocimiento nivelConocimiento;
	private OfertaLaboral ofertaLaboral;

	public OfertaConocimiento() {
	}


	@EmbeddedId
	public OfertaConocimientoPK getId() {
		return this.id;
	}

	public void setId(OfertaConocimientoPK id) {
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