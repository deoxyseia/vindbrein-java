package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_conocimiento database table.
 * 
 */
@Entity
@Table(name="nivel_conocimiento")
@NamedQuery(name="NivelConocimiento.findAll", query="SELECT n FROM NivelConocimiento n")
public class NivelConocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nicoId;
	private String nicoCode;
	private String nicoDescripcion;
	private String nicoNombre;
	private List<PostulanteConocimiento> postulanteConocimientos;
	private List<OfertaConocimiento> ofertaConocimientos;

	public NivelConocimiento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nico_id")
	public int getNicoId() {
		return this.nicoId;
	}

	public void setNicoId(int nicoId) {
		this.nicoId = nicoId;
	}


	@Column(name="nico_code")
	public String getNicoCode() {
		return this.nicoCode;
	}

	public void setNicoCode(String nicoCode) {
		this.nicoCode = nicoCode;
	}


	@Column(name="nico_descripcion")
	public String getNicoDescripcion() {
		return this.nicoDescripcion;
	}

	public void setNicoDescripcion(String nicoDescripcion) {
		this.nicoDescripcion = nicoDescripcion;
	}


	@Column(name="nico_nombre")
	public String getNicoNombre() {
		return this.nicoNombre;
	}

	public void setNicoNombre(String nicoNombre) {
		this.nicoNombre = nicoNombre;
	}


	//bi-directional many-to-one association to PostulanteConocimiento
	@OneToMany(mappedBy="nivelConocimiento")
	public List<PostulanteConocimiento> getPostulanteConocimientos() {
		return this.postulanteConocimientos;
	}

	public void setPostulanteConocimientos(List<PostulanteConocimiento> postulanteConocimientos) {
		this.postulanteConocimientos = postulanteConocimientos;
	}

	public PostulanteConocimiento addPostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().add(postulanteConocimiento);
		postulanteConocimiento.setNivelConocimiento(this);

		return postulanteConocimiento;
	}

	public PostulanteConocimiento removePostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().remove(postulanteConocimiento);
		postulanteConocimiento.setNivelConocimiento(null);

		return postulanteConocimiento;
	}


	//bi-directional many-to-one association to OfertaConocimiento
	@OneToMany(mappedBy="nivelConocimiento")
	public List<OfertaConocimiento> getOfertaConocimientos() {
		return this.ofertaConocimientos;
	}

	public void setOfertaConocimientos(List<OfertaConocimiento> ofertaConocimientos) {
		this.ofertaConocimientos = ofertaConocimientos;
	}

	public OfertaConocimiento addOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().add(ofertaConocimiento);
		ofertaConocimiento.setNivelConocimiento(this);

		return ofertaConocimiento;
	}

	public OfertaConocimiento removeOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().remove(ofertaConocimiento);
		ofertaConocimiento.setNivelConocimiento(null);

		return ofertaConocimiento;
	}

}