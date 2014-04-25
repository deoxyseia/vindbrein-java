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
	private List<PostulanteConocimiento> postulanteConocimientos;
	private List<PuestoPrefConocim> puestoPrefConocims;

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


	//bi-directional many-to-one association to PuestoPrefConocim
	@OneToMany(mappedBy="nivelConocimiento")
	public List<PuestoPrefConocim> getPuestoPrefConocims() {
		return this.puestoPrefConocims;
	}

	public void setPuestoPrefConocims(List<PuestoPrefConocim> puestoPrefConocims) {
		this.puestoPrefConocims = puestoPrefConocims;
	}

	public PuestoPrefConocim addPuestoPrefConocim(PuestoPrefConocim puestoPrefConocim) {
		getPuestoPrefConocims().add(puestoPrefConocim);
		puestoPrefConocim.setNivelConocimiento(this);

		return puestoPrefConocim;
	}

	public PuestoPrefConocim removePuestoPrefConocim(PuestoPrefConocim puestoPrefConocim) {
		getPuestoPrefConocims().remove(puestoPrefConocim);
		puestoPrefConocim.setNivelConocimiento(null);

		return puestoPrefConocim;
	}

}