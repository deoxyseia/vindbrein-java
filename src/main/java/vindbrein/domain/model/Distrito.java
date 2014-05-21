package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distrito database table.
 * 
 */
@Entity
@Table(name="distrito")
@NamedQuery(name="Distrito.findAll", query="SELECT d FROM Distrito d")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;
	private int distId;
	private String distCode;
	private String distNombre;
	private Provincia provincia;
	private List<Residencia> residencias;
	private List<Sucursal> sucursals;
	private List<OfertaLaboral> ofertaLaborals;

	public Distrito() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dist_id")
	public int getDistId() {
		return this.distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
	}


	@Column(name="dist_code")
	public String getDistCode() {
		return this.distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}


	@Column(name="dist_nombre")
	public String getDistNombre() {
		return this.distNombre;
	}

	public void setDistNombre(String distNombre) {
		this.distNombre = distNombre;
	}


	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="fk_prov_id")
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	//bi-directional many-to-one association to Residencia
	@OneToMany(mappedBy="distrito")
	public List<Residencia> getResidencias() {
		return this.residencias;
	}

	public void setResidencias(List<Residencia> residencias) {
		this.residencias = residencias;
	}

	public Residencia addResidencia(Residencia residencia) {
		getResidencias().add(residencia);
		residencia.setDistrito(this);

		return residencia;
	}

	public Residencia removeResidencia(Residencia residencia) {
		getResidencias().remove(residencia);
		residencia.setDistrito(null);

		return residencia;
	}


	//bi-directional many-to-one association to Sucursal
	@OneToMany(mappedBy="distrito")
	public List<Sucursal> getSucursals() {
		return this.sucursals;
	}

	public void setSucursals(List<Sucursal> sucursals) {
		this.sucursals = sucursals;
	}

	public Sucursal addSucursal(Sucursal sucursal) {
		getSucursals().add(sucursal);
		sucursal.setDistrito(this);

		return sucursal;
	}

	public Sucursal removeSucursal(Sucursal sucursal) {
		getSucursals().remove(sucursal);
		sucursal.setDistrito(null);

		return sucursal;
	}


	//bi-directional many-to-one association to OfertaLaboral
	@OneToMany(mappedBy="distrito")
	public List<OfertaLaboral> getOfertaLaborals() {
		return this.ofertaLaborals;
	}

	public void setOfertaLaborals(List<OfertaLaboral> ofertaLaborals) {
		this.ofertaLaborals = ofertaLaborals;
	}

	public OfertaLaboral addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().add(ofertaLaboral);
		ofertaLaboral.setDistrito(this);

		return ofertaLaboral;
	}

	public OfertaLaboral removeOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().remove(ofertaLaboral);
		ofertaLaboral.setDistrito(null);

		return ofertaLaboral;
	}

}