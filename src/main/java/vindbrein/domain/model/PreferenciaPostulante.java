package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the preferencia_postulante database table.
 * 
 */
@Entity
@Table(name="preferencia_postulante")
@NamedQuery(name="PreferenciaPostulante.findAll", query="SELECT p FROM PreferenciaPostulante p")
public class PreferenciaPostulante implements Serializable {
	private static final long serialVersionUID = 1L;
	private int prpoId;
	private int prpoSalario;
	private TipoPuesto tipoPuesto;
	private TipoHorario tipoHorario;
	private Postulante postulante;
	private Sector sector;
	private DimensionOrganizacion dimensionOrganizacion;

	public PreferenciaPostulante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prpo_id")
	public int getPrpoId() {
		return this.prpoId;
	}

	public void setPrpoId(int prpoId) {
		this.prpoId = prpoId;
	}


	@Column(name="prpo_salario")
	public int getPrpoSalario() {
		return this.prpoSalario;
	}

	public void setPrpoSalario(int prpoSalario) {
		this.prpoSalario = prpoSalario;
	}


	//bi-directional many-to-one association to TipoPuesto
	@ManyToOne
	@JoinColumn(name="fk_tipu_id")
	public TipoPuesto getTipoPuesto() {
		return this.tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}


	//bi-directional many-to-one association to TipoHorario
	@ManyToOne
	@JoinColumn(name="fk_tiho_id")
	public TipoHorario getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id")
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}


	//bi-directional many-to-one association to Sector
	@ManyToOne
	@JoinColumn(name="fk_sect_id")
	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}


	//bi-directional many-to-one association to DimensionOrganizacion
	@ManyToOne
	@JoinColumn(name="fk_dior_id")
	public DimensionOrganizacion getDimensionOrganizacion() {
		return this.dimensionOrganizacion;
	}

	public void setDimensionOrganizacion(DimensionOrganizacion dimensionOrganizacion) {
		this.dimensionOrganizacion = dimensionOrganizacion;
	}

}