package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta_beneficio database table.
 * 
 */
@Entity
@Table(name="oferta_beneficio")
@NamedQuery(name="OfertaBeneficio.findAll", query="SELECT o FROM OfertaBeneficio o")
public class OfertaBeneficio implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaBeneficioPK id;
	private byte ofbeObligatorio;
	private OfertaLaboral ofertaLaboral;
	private Beneficio beneficio;

	public OfertaBeneficio() {
	}


	@EmbeddedId
	public OfertaBeneficioPK getId() {
		return this.id;
	}

	public void setId(OfertaBeneficioPK id) {
		this.id = id;
	}


	@Column(name="ofbe_obligatorio")
	public byte getOfbeObligatorio() {
		return this.ofbeObligatorio;
	}

	public void setOfbeObligatorio(byte ofbeObligatorio) {
		this.ofbeObligatorio = ofbeObligatorio;
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


	//bi-directional many-to-one association to Beneficio
	@ManyToOne
	@JoinColumn(name="fk_bene_id", insertable=false, updatable=false)
	public Beneficio getBeneficio() {
		return this.beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

}