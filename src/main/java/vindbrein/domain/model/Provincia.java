package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
@Table(name="provincia")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int provId;
	private String provNombre;
	private List<Distrito> distritos;
	private Departamento departamento;

	public Provincia() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prov_id")
	public int getProvId() {
		return this.provId;
	}

	public void setProvId(int provId) {
		this.provId = provId;
	}


	@Column(name="prov_nombre")
	public String getProvNombre() {
		return this.provNombre;
	}

	public void setProvNombre(String provNombre) {
		this.provNombre = provNombre;
	}


	//bi-directional many-to-one association to Distrito
	@OneToMany(mappedBy="provincia")
	public List<Distrito> getDistritos() {
		return this.distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public Distrito addDistrito(Distrito distrito) {
		getDistritos().add(distrito);
		distrito.setProvincia(this);

		return distrito;
	}

	public Distrito removeDistrito(Distrito distrito) {
		getDistritos().remove(distrito);
		distrito.setProvincia(null);

		return distrito;
	}


	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="fk_depa_id")
	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}