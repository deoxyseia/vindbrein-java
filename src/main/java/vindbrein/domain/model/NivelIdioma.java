package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_idioma database table.
 * 
 */
@Entity
@Table(name="nivel_idioma")
@NamedQuery(name="NivelIdioma.findAll", query="SELECT n FROM NivelIdioma n")
public class NivelIdioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private int niidId;
	private String niidCode;
	private String niidDescripcion;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<PuestoPrefIdioma> puestoPrefIdiomas;

	public NivelIdioma() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="niid_id")
	public int getNiidId() {
		return this.niidId;
	}

	public void setNiidId(int niidId) {
		this.niidId = niidId;
	}


	@Column(name="niid_code")
	public String getNiidCode() {
		return this.niidCode;
	}

	public void setNiidCode(String niidCode) {
		this.niidCode = niidCode;
	}


	@Column(name="niid_descripcion")
	public String getNiidDescripcion() {
		return this.niidDescripcion;
	}

	public void setNiidDescripcion(String niidDescripcion) {
		this.niidDescripcion = niidDescripcion;
	}


	//bi-directional many-to-one association to PostulanteIdioma
	@OneToMany(mappedBy="nivelIdioma")
	public List<PostulanteIdioma> getPostulanteIdiomas() {
		return this.postulanteIdiomas;
	}

	public void setPostulanteIdiomas(List<PostulanteIdioma> postulanteIdiomas) {
		this.postulanteIdiomas = postulanteIdiomas;
	}

	public PostulanteIdioma addPostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().add(postulanteIdioma);
		postulanteIdioma.setNivelIdioma(this);

		return postulanteIdioma;
	}

	public PostulanteIdioma removePostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().remove(postulanteIdioma);
		postulanteIdioma.setNivelIdioma(null);

		return postulanteIdioma;
	}


	//bi-directional many-to-one association to PuestoPrefIdioma
	@OneToMany(mappedBy="nivelIdioma")
	public List<PuestoPrefIdioma> getPuestoPrefIdiomas() {
		return this.puestoPrefIdiomas;
	}

	public void setPuestoPrefIdiomas(List<PuestoPrefIdioma> puestoPrefIdiomas) {
		this.puestoPrefIdiomas = puestoPrefIdiomas;
	}

	public PuestoPrefIdioma addPuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().add(puestoPrefIdioma);
		puestoPrefIdioma.setNivelIdioma(this);

		return puestoPrefIdioma;
	}

	public PuestoPrefIdioma removePuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().remove(puestoPrefIdioma);
		puestoPrefIdioma.setNivelIdioma(null);

		return puestoPrefIdioma;
	}

}