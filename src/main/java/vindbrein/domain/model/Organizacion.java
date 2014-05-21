package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
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
	private String orgaEstado;
	private String orgaRazonSocial;
	private BigInteger orgaRuc;
	private String orgaWeb;
	private DimensionOrganizacion dimensionOrganizacion;
	private Organizacion organizacion;
	private List<Organizacion> organizacions;
	private Sector sector;
	private List<Sucursal> sucursales;
	private List<OrganizacionPuesto> organizacionPuestos;
	private List<Reclutador> reclutadors;

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


	@Column(name="orga_estado")
	public String getOrgaEstado() {
		return this.orgaEstado;
	}

	public void setOrgaEstado(String orgaEstado) {
		this.orgaEstado = orgaEstado;
	}


	@Column(name="orga_razon_social")
	public String getOrgaRazonSocial() {
		return this.orgaRazonSocial;
	}

	public void setOrgaRazonSocial(String orgaRazonSocial) {
		this.orgaRazonSocial = orgaRazonSocial;
	}


	@Column(name="orga_ruc")
	public BigInteger getOrgaRuc() {
		return this.orgaRuc;
	}

	public void setOrgaRuc(BigInteger orgaRuc) {
		this.orgaRuc = orgaRuc;
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
	@JoinColumn(name="fk_orga_id_padre")
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
	public List<Sucursal> getSucursales() {
		return this.sucursales;
	}

	public void setSucursales(List<Sucursal> sucursals) {
		this.sucursales = sucursals;
	}

	public Sucursal addSucursal(Sucursal sucursal) {
		getSucursales().add(sucursal);
		sucursal.setOrganizacion(this);

		return sucursal;
	}

	public Sucursal removeSucursal(Sucursal sucursal) {
		getSucursales().remove(sucursal);
		sucursal.setOrganizacion(null);

		return sucursal;
	}


	//bi-directional many-to-one association to OrganizacionPuesto
	@OneToMany(mappedBy="organizacion")
	public List<OrganizacionPuesto> getOrganizacionPuestos() {
		return this.organizacionPuestos;
	}

	public void setOrganizacionPuestos(List<OrganizacionPuesto> organizacionPuestos) {
		this.organizacionPuestos = organizacionPuestos;
	}

	public OrganizacionPuesto addOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getOrganizacionPuestos().add(organizacionPuesto);
		organizacionPuesto.setOrganizacion(this);

		return organizacionPuesto;
	}

	public OrganizacionPuesto removeOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getOrganizacionPuestos().remove(organizacionPuesto);
		organizacionPuesto.setOrganizacion(null);

		return organizacionPuesto;
	}


	//bi-directional many-to-one association to Reclutador
	@OneToMany(mappedBy="organizacion")
	public List<Reclutador> getReclutadors() {
		return this.reclutadors;
	}

	public void setReclutadors(List<Reclutador> reclutadors) {
		this.reclutadors = reclutadors;
	}

	public Reclutador addReclutador(Reclutador reclutador) {
		getReclutadors().add(reclutador);
		reclutador.setOrganizacion(this);

		return reclutador;
	}

	public Reclutador removeReclutador(Reclutador reclutador) {
		getReclutadors().remove(reclutador);
		reclutador.setOrganizacion(null);

		return reclutador;
	}

}