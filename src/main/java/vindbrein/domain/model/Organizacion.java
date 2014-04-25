package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the organizacion database table.
 * 
 */
@Entity
@Table(name="organizacion")
@NamedQuery(name="Organizacion.findAll", query="SELECT o FROM Organizacion o")
public class Organizacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orgaId;
	private String orgaRazonSocial;
	private String orgaWeb;
	private DimensionOrganizacion dimensionOrganizacion;
	private Organizacion organizacion;
	private List<Organizacion> organizacions;
	private Sector sector;
	private List<Sucursal> sucursals;
	private List<RespOrganizacion> respOrganizacions;

	public Organizacion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orga_id")
	public int getOrgaId() {
		return this.orgaId;
	}

	public void setOrgaId(int orgaId) {
		this.orgaId = orgaId;
	}


	@Column(name="orga_razon_social")
	public String getOrgaRazonSocial() {
		return this.orgaRazonSocial;
	}

	public void setOrgaRazonSocial(String orgaRazonSocial) {
		this.orgaRazonSocial = orgaRazonSocial;
	}


	@Column(name="orga_web")
	public String getOrgaWeb() {
		return this.orgaWeb;
	}

	public void setOrgaWeb(String orgaWeb) {
		this.orgaWeb = orgaWeb;
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


	//bi-directional many-to-one association to Organizacion
	@ManyToOne
	@JoinColumn(name="fk_orga_padre")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}


	//bi-directional many-to-one association to Organizacion
	@OneToMany(mappedBy="organizacion")
	public List<Organizacion> getOrganizacions() {
		return this.organizacions;
	}

	public void setOrganizacions(List<Organizacion> organizacions) {
		this.organizacions = organizacions;
	}

	public Organizacion addOrganizacion(Organizacion organizacion) {
		getOrganizacions().add(organizacion);
		organizacion.setOrganizacion(this);

		return organizacion;
	}

	public Organizacion removeOrganizacion(Organizacion organizacion) {
		getOrganizacions().remove(organizacion);
		organizacion.setOrganizacion(null);

		return organizacion;
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


	//bi-directional many-to-one association to Sucursal
	@OneToMany(mappedBy="organizacion")
	public List<Sucursal> getSucursals() {
		return this.sucursals;
	}

	public void setSucursals(List<Sucursal> sucursals) {
		this.sucursals = sucursals;
	}

	public Sucursal addSucursal(Sucursal sucursal) {
		getSucursals().add(sucursal);
		sucursal.setOrganizacion(this);

		return sucursal;
	}

	public Sucursal removeSucursal(Sucursal sucursal) {
		getSucursals().remove(sucursal);
		sucursal.setOrganizacion(null);

		return sucursal;
	}


	//bi-directional many-to-one association to RespOrganizacion
	@OneToMany(mappedBy="organizacion")
	public List<RespOrganizacion> getRespOrganizacions() {
		return this.respOrganizacions;
	}

	public void setRespOrganizacions(List<RespOrganizacion> respOrganizacions) {
		this.respOrganizacions = respOrganizacions;
	}

	public RespOrganizacion addRespOrganizacion(RespOrganizacion respOrganizacion) {
		getRespOrganizacions().add(respOrganizacion);
		respOrganizacion.setOrganizacion(this);

		return respOrganizacion;
	}

	public RespOrganizacion removeRespOrganizacion(RespOrganizacion respOrganizacion) {
		getRespOrganizacions().remove(respOrganizacion);
		respOrganizacion.setOrganizacion(null);

		return respOrganizacion;
	}

}