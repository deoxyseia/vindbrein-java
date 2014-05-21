package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sector database table.
 * 
 */
@Entity
@Table(name="sector")
@NamedQuery(name="Sector.findAll", query="SELECT s FROM Sector s")
public class Sector implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sectId;
	private String sectCode;
	private String sectDescripcion;
	private String sectNombre;
	private List<Organizacion> organizacions;
	private List<Postulante> postulantes;

	public Sector() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sect_id")
	public int getSectId() {
		return this.sectId;
	}

	public void setSectId(int sectId) {
		this.sectId = sectId;
	}


	@Column(name="sect_code")
	public String getSectCode() {
		return this.sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}


	@Column(name="sect_descripcion")
	public String getSectDescripcion() {
		return this.sectDescripcion;
	}

	public void setSectDescripcion(String sectDescripcion) {
		this.sectDescripcion = sectDescripcion;
	}


	@Column(name="sect_nombre")
	public String getSectNombre() {
		return this.sectNombre;
	}

	public void setSectNombre(String sectNombre) {
		this.sectNombre = sectNombre;
	}


	//bi-directional many-to-one association to Organizacion
	@OneToMany(mappedBy="sector")
	public List<Organizacion> getOrganizacions() {
		return this.organizacions;
	}

	public void setOrganizacions(List<Organizacion> organizacions) {
		this.organizacions = organizacions;
	}

	public Organizacion addOrganizacion(Organizacion organizacion) {
		getOrganizacions().add(organizacion);
		organizacion.setSector(this);

		return organizacion;
	}

	public Organizacion removeOrganizacion(Organizacion organizacion) {
		getOrganizacions().remove(organizacion);
		organizacion.setSector(null);

		return organizacion;
	}


	//bi-directional many-to-one association to Postulante
	@OneToMany(mappedBy="sector")
	public List<Postulante> getPostulantes() {
		return this.postulantes;
	}

	public void setPostulantes(List<Postulante> postulantes) {
		this.postulantes = postulantes;
	}

	public Postulante addPostulante(Postulante postulante) {
		getPostulantes().add(postulante);
		postulante.setSector(this);

		return postulante;
	}

	public Postulante removePostulante(Postulante postulante) {
		getPostulantes().remove(postulante);
		postulante.setSector(null);

		return postulante;
	}

}