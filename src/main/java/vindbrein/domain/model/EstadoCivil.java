package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado_civil database table.
 * 
 */
@Entity
@Table(name="estado_civil")
@NamedQuery(name="EstadoCivil.findAll", query="SELECT e FROM EstadoCivil e")
public class EstadoCivil implements Serializable {
	private static final long serialVersionUID = 1L;
	private int esciId;
	private String esciCode;
	private String esciDescripcion;
	private List<PuestoLaboral> puestoLaborals;
	private List<Postulante> postulantes;

	public EstadoCivil() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="esci_id")
	public int getEsciId() {
		return this.esciId;
	}

	public void setEsciId(int esciId) {
		this.esciId = esciId;
	}


	@Column(name="esci_code")
	public String getEsciCode() {
		return this.esciCode;
	}

	public void setEsciCode(String esciCode) {
		this.esciCode = esciCode;
	}


	@Column(name="esci_descripcion")
	public String getEsciDescripcion() {
		return this.esciDescripcion;
	}

	public void setEsciDescripcion(String esciDescripcion) {
		this.esciDescripcion = esciDescripcion;
	}


	//bi-directional many-to-one association to PuestoLaboral
	@OneToMany(mappedBy="estadoCivil")
	public List<PuestoLaboral> getPuestoLaborals() {
		return this.puestoLaborals;
	}

	public void setPuestoLaborals(List<PuestoLaboral> puestoLaborals) {
		this.puestoLaborals = puestoLaborals;
	}

	public PuestoLaboral addPuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().add(puestoLaboral);
		puestoLaboral.setEstadoCivil(this);

		return puestoLaboral;
	}

	public PuestoLaboral removePuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().remove(puestoLaboral);
		puestoLaboral.setEstadoCivil(null);

		return puestoLaboral;
	}


	//bi-directional many-to-one association to Postulante
	@OneToMany(mappedBy="estadoCivil")
	public List<Postulante> getPostulantes() {
		return this.postulantes;
	}

	public void setPostulantes(List<Postulante> postulantes) {
		this.postulantes = postulantes;
	}

	public Postulante addPostulante(Postulante postulante) {
		getPostulantes().add(postulante);
		postulante.setEstadoCivil(this);

		return postulante;
	}

	public Postulante removePostulante(Postulante postulante) {
		getPostulantes().remove(postulante);
		postulante.setEstadoCivil(null);

		return postulante;
	}

}