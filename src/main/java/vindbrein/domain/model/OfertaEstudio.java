package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta_estudio database table.
 * 
 */
@Entity
@Table(name="oferta_estudio")
@NamedQuery(name="OfertaEstudio.findAll", query="SELECT o FROM OfertaEstudio o")
public class OfertaEstudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaEstudioPK id;
	private int ofesPorcentajeAvance;
	private EstudioGenerico estudioGenerico;
	private OfertaLaboral ofertaLaboral;

	public OfertaEstudio() {
	}


	@EmbeddedId
	public OfertaEstudioPK getId() {
		return this.id;
	}

	public void setId(OfertaEstudioPK id) {
		this.id = id;
	}


	@Column(name="ofes_porcentaje_avance")
	public int getOfesPorcentajeAvance() {
		return this.ofesPorcentajeAvance;
	}

	public void setOfesPorcentajeAvance(int ofesPorcentajeAvance) {
		this.ofesPorcentajeAvance = ofesPorcentajeAvance;
	}


	//bi-directional many-to-one association to EstudioGenerico
	@ManyToOne
	@JoinColumn(name="fk_esge_id", insertable=false, updatable=false)
	public EstudioGenerico getEstudioGenerico() {
		return this.estudioGenerico;
	}

	public void setEstudioGenerico(EstudioGenerico estudioGenerico) {
		this.estudioGenerico = estudioGenerico;
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