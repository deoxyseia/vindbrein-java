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
	private String esciNombre;
	private List<Postulante> postulantes;
	private List<OfertaLaboral> ofertaLaborals;

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


	@Column(name="esci_nombre")
	public String getEsciNombre() {
		return this.esciNombre;
	}

	public void setEsciNombre(String esciNombre) {
		this.esciNombre = esciNombre;
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


	//bi-directional many-to-one association to OfertaLaboral
	@OneToMany(mappedBy="estadoCivil")
	public List<OfertaLaboral> getOfertaLaborals() {
		return this.ofertaLaborals;
	}

	public void setOfertaLaborals(List<OfertaLaboral> ofertaLaborals) {
		this.ofertaLaborals = ofertaLaborals;
	}

	public OfertaLaboral addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().add(ofertaLaboral);
		ofertaLaboral.setEstadoCivil(this);

		return ofertaLaboral;
	}

	public OfertaLaboral removeOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().remove(ofertaLaboral);
		ofertaLaboral.setEstadoCivil(null);

		return ofertaLaboral;
	}

}