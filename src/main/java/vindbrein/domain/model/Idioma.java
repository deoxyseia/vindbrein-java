package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the idioma database table.
 * 
 */
@Entity
@Table(name="idioma")
@NamedQuery(name="Idioma.findAll", query="SELECT i FROM Idioma i")
public class Idioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idioId;
	private String idioCode;
	private String idioDescripcion;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<PuestoPrefIdioma> puestoPrefIdiomas;

	public Idioma() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idio_id")
	public int getIdioId() {
		return this.idioId;
	}

	public void setIdioId(int idioId) {
		this.idioId = idioId;
	}


	@Column(name="idio_code")
	public String getIdioCode() {
		return this.idioCode;
	}

	public void setIdioCode(String idioCode) {
		this.idioCode = idioCode;
	}


	@Column(name="idio_descripcion")
	public String getIdioDescripcion() {
		return this.idioDescripcion;
	}

	public void setIdioDescripcion(String idioDescripcion) {
		this.idioDescripcion = idioDescripcion;
	}


	//bi-directional many-to-one association to PostulanteIdioma
	@OneToMany(mappedBy="idioma")
	public List<PostulanteIdioma> getPostulanteIdiomas() {
		return this.postulanteIdiomas;
	}

	public void setPostulanteIdiomas(List<PostulanteIdioma> postulanteIdiomas) {
		this.postulanteIdiomas = postulanteIdiomas;
	}

	public PostulanteIdioma addPostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().add(postulanteIdioma);
		postulanteIdioma.setIdioma(this);

		return postulanteIdioma;
	}

	public PostulanteIdioma removePostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().remove(postulanteIdioma);
		postulanteIdioma.setIdioma(null);

		return postulanteIdioma;
	}


	//bi-directional many-to-one association to PuestoPrefIdioma
	@OneToMany(mappedBy="idioma")
	public List<PuestoPrefIdioma> getPuestoPrefIdiomas() {
		return this.puestoPrefIdiomas;
	}

	public void setPuestoPrefIdiomas(List<PuestoPrefIdioma> puestoPrefIdiomas) {
		this.puestoPrefIdiomas = puestoPrefIdiomas;
	}

	public PuestoPrefIdioma addPuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().add(puestoPrefIdioma);
		puestoPrefIdioma.setIdioma(this);

		return puestoPrefIdioma;
	}

	public PuestoPrefIdioma removePuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().remove(puestoPrefIdioma);
		puestoPrefIdioma.setIdioma(null);

		return puestoPrefIdioma;
	}

}