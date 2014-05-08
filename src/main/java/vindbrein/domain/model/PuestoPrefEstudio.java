package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the puesto_pref_estudio database table.
 * 
 */
@Entity
@Table(name="puesto_pref_estudio")
@NamedQuery(name="PuestoPrefEstudio.findAll", query="SELECT p FROM PuestoPrefEstudio p")
public class PuestoPrefEstudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private PuestoPrefEstudioPK id;
	private int porcentajeAvance;
	private PreferenciaPuestoLaboral preferenciaPuestoLaboral;
	private EstudioGenerico estudioGenerico;

	public PuestoPrefEstudio() {
	}


	@EmbeddedId
	public PuestoPrefEstudioPK getId() {
		return this.id;
	}

	public void setId(PuestoPrefEstudioPK id) {
		this.id = id;
	}


	@Column(name="porcentaje_avance")
	public int getPorcentajeAvance() {
		return this.porcentajeAvance;
	}

	public void setPorcentajeAvance(int porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
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


	//bi-directional many-to-one association to EstudioGenerico
	@ManyToOne
	@JoinColumn(name="fk_esge_id",insertable=false, updatable=false)
	public EstudioGenerico getEstudioGenerico() {
		return this.estudioGenerico;
	}

	public void setEstudioGenerico(EstudioGenerico estudioGenerico) {
		this.estudioGenerico = estudioGenerico;
	}

}