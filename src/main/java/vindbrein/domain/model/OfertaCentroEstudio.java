package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta_centro_estudio database table.
 * 
 */
@Entity
@Table(name="oferta_centro_estudio")
@NamedQuery(name="OfertaCentroEstudio.findAll", query="SELECT o FROM OfertaCentroEstudio o")
public class OfertaCentroEstudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaCentroEstudioPK id;
	private byte ofceObligatorio;
	private CentroEstudio centroEstudio;
	private OfertaLaboral ofertaLaboral;

	public OfertaCentroEstudio() {
	}


	@EmbeddedId
	public OfertaCentroEstudioPK getId() {
		return this.id;
	}

	public void setId(OfertaCentroEstudioPK id) {
		this.id = id;
	}


	@Column(name="ofce_obligatorio")
	public byte getOfceObligatorio() {
		return this.ofceObligatorio;
	}

	public void setOfceObligatorio(byte ofceObligatorio) {
		this.ofceObligatorio = ofceObligatorio;
	}


	//bi-directional many-to-one association to CentroEstudio
	@ManyToOne
	@JoinColumn(name="fk_cees_id", insertable=false, updatable=false)
	public CentroEstudio getCentroEstudio() {
		return this.centroEstudio;
	}

	public void setCentroEstudio(CentroEstudio centroEstudio) {
		this.centroEstudio = centroEstudio;
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