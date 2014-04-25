package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dimension_organizacion database table.
 * 
 */
@Entity
@Table(name="dimension_organizacion")
@NamedQuery(name="DimensionOrganizacion.findAll", query="SELECT d FROM DimensionOrganizacion d")
public class DimensionOrganizacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int diorId;
	private String diorCode;
	private String diorDescripcion;
	private List<Organizacion> organizacions;

	public DimensionOrganizacion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dior_id")
	public int getDiorId() {
		return this.diorId;
	}

	public void setDiorId(int diorId) {
		this.diorId = diorId;
	}


	@Column(name="dior_code")
	public String getDiorCode() {
		return this.diorCode;
	}

	public void setDiorCode(String diorCode) {
		this.diorCode = diorCode;
	}


	@Column(name="dior_descripcion")
	public String getDiorDescripcion() {
		return this.diorDescripcion;
	}

	public void setDiorDescripcion(String diorDescripcion) {
		this.diorDescripcion = diorDescripcion;
	}


	//bi-directional many-to-one association to Organizacion
	@OneToMany(mappedBy="dimensionOrganizacion")
	public List<Organizacion> getOrganizacions() {
		return this.organizacions;
	}

	public void setOrganizacions(List<Organizacion> organizacions) {
		this.organizacions = organizacions;
	}

	public Organizacion addOrganizacion(Organizacion organizacion) {
		getOrganizacions().add(organizacion);
		organizacion.setDimensionOrganizacion(this);

		return organizacion;
	}

	public Organizacion removeOrganizacion(Organizacion organizacion) {
		getOrganizacions().remove(organizacion);
		organizacion.setDimensionOrganizacion(null);

		return organizacion;
	}

}