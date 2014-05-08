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
	private List<Postulante> postulantes;
	private List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals;

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


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@OneToMany(mappedBy="estadoCivil")
	public List<PreferenciaPuestoLaboral> getPreferenciaPuestoLaborals() {
		return this.preferenciaPuestoLaborals;
	}

	public void setPreferenciaPuestoLaborals(List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals) {
		this.preferenciaPuestoLaborals = preferenciaPuestoLaborals;
	}

	public PreferenciaPuestoLaboral addPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().add(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setEstadoCivil(this);

		return preferenciaPuestoLaboral;
	}

	public PreferenciaPuestoLaboral removePreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().remove(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setEstadoCivil(null);

		return preferenciaPuestoLaboral;
	}

}