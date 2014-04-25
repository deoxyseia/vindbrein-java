package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@Table(name="departamento")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int depaId;
	private String depaNombre;
	private List<Provincia> provincias;

	public Departamento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="depa_id")
	public int getDepaId() {
		return this.depaId;
	}

	public void setDepaId(int depaId) {
		this.depaId = depaId;
	}


	@Column(name="depa_nombre")
	public String getDepaNombre() {
		return this.depaNombre;
	}

	public void setDepaNombre(String depaNombre) {
		this.depaNombre = depaNombre;
	}


	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="departamento")
	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public Provincia addProvincia(Provincia provincia) {
		getProvincias().add(provincia);
		provincia.setDepartamento(this);

		return provincia;
	}

	public Provincia removeProvincia(Provincia provincia) {
		getProvincias().remove(provincia);
		provincia.setDepartamento(null);

		return provincia;
	}

}