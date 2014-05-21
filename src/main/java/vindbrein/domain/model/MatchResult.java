package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the match_result database table.
 * 
 */
@Entity
@Table(name="match_result")
@NamedQuery(name="MatchResult.findAll", query="SELECT m FROM MatchResult m")
public class MatchResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private MatchResultPK id;
	private Date mareFecha;
	private byte mareFlagRecomendado;
	private byte mareFlagSeleccionado;
	private byte mareFlagVisitado;
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;

	public MatchResult() {
	}


	@EmbeddedId
	public MatchResultPK getId() {
		return this.id;
	}

	public void setId(MatchResultPK id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="mare_fecha")
	public Date getMareFecha() {
		return this.mareFecha;
	}

	public void setMareFecha(Date mareFecha) {
		this.mareFecha = mareFecha;
	}


	@Column(name="mare_flag_recomendado")
	public byte getMareFlagRecomendado() {
		return this.mareFlagRecomendado;
	}

	public void setMareFlagRecomendado(byte mareFlagRecomendado) {
		this.mareFlagRecomendado = mareFlagRecomendado;
	}


	@Column(name="mare_flag_seleccionado")
	public byte getMareFlagSeleccionado() {
		return this.mareFlagSeleccionado;
	}

	public void setMareFlagSeleccionado(byte mareFlagSeleccionado) {
		this.mareFlagSeleccionado = mareFlagSeleccionado;
	}


	@Column(name="mare_flag_visitado")
	public byte getMareFlagVisitado() {
		return this.mareFlagVisitado;
	}

	public void setMareFlagVisitado(byte mareFlagVisitado) {
		this.mareFlagVisitado = mareFlagVisitado;
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