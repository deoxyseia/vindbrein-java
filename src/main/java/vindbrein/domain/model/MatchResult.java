package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


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
	private String mareFlagRecomendado;
	private String mareFlagSeleccionado;
	private PuestoLaboral puestoLaboral;
	private Postulante postulante;

	public MatchResult() {
	}


	@EmbeddedId
	public MatchResultPK getId() {
		return this.id;
	}

	public void setId(MatchResultPK id) {
		this.id = id;
	}


	@Column(name="mare_flag_recomendado")
	public String getMareFlagRecomendado() {
		return this.mareFlagRecomendado;
	}

	public void setMareFlagRecomendado(String mareFlagRecomendado) {
		this.mareFlagRecomendado = mareFlagRecomendado;
	}


	@Column(name="mare_flag_seleccionado")
	public String getMareFlagSeleccionado() {
		return this.mareFlagSeleccionado;
	}

	public void setMareFlagSeleccionado(String mareFlagSeleccionado) {
		this.mareFlagSeleccionado = mareFlagSeleccionado;
	}


	//bi-directional many-to-one association to PuestoLaboral
	@ManyToOne
	@JoinColumn(name="fk_pula_id",insertable=false, updatable=false)
	public PuestoLaboral getPuestoLaboral() {
		return this.puestoLaboral;
	}

	public void setPuestoLaboral(PuestoLaboral puestoLaboral) {
		this.puestoLaboral = puestoLaboral;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id",insertable=false, updatable=false)
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

}