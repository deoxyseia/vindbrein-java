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
	private String niidNombre;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<OfertaIdioma> ofertaIdiomas;

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


	@Column(name="niid_nombre")
	public String getNiidNombre() {
		return this.niidNombre;
	}

	public void setNiidNombre(String niidNombre) {
		this.niidNombre = niidNombre;
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


	//bi-directional many-to-one association to OfertaIdioma
	@OneToMany(mappedBy="nivelIdioma")
	public List<OfertaIdioma> getOfertaIdiomas() {
		return this.ofertaIdiomas;
	}

	public void setOfertaIdiomas(List<OfertaIdioma> ofertaIdiomas) {
		this.ofertaIdiomas = ofertaIdiomas;
	}

	public OfertaIdioma addOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().add(ofertaIdioma);
		ofertaIdioma.setNivelIdioma(this);

		return ofertaIdioma;
	}

	public OfertaIdioma removeOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().remove(ofertaIdioma);
		ofertaIdioma.setNivelIdioma(null);

		return ofertaIdioma;
	}

}