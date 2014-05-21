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
	private String idioNombre;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<OfertaIdioma> ofertaIdiomas;

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


	@Column(name="idio_nombre")
	public String getIdioNombre() {
		return this.idioNombre;
	}

	public void setIdioNombre(String idioNombre) {
		this.idioNombre = idioNombre;
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


	//bi-directional many-to-one association to OfertaIdioma
	@OneToMany(mappedBy="idioma")
	public List<OfertaIdioma> getOfertaIdiomas() {
		return this.ofertaIdiomas;
	}

	public void setOfertaIdiomas(List<OfertaIdioma> ofertaIdiomas) {
		this.ofertaIdiomas = ofertaIdiomas;
	}

	public OfertaIdioma addOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().add(ofertaIdioma);
		ofertaIdioma.setIdioma(this);

		return ofertaIdioma;
	}

	public OfertaIdioma removeOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().remove(ofertaIdioma);
		ofertaIdioma.setIdioma(null);

		return ofertaIdioma;
	}

}