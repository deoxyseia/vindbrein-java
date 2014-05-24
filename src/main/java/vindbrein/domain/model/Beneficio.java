package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the beneficio database table.
 * 
 */
@Entity
@Table(name="beneficio")
@NamedQuery(name="Beneficio.findAll", query="SELECT b FROM Beneficio b")
public class Beneficio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int beneId;
	private String beneCode;
	private String beneDescripcion;
	private String beneNombre;
	private List<OfertaBeneficio> ofertaBeneficios;
	private List<PostulanteBeneficio> postulanteBeneficios;

	public Beneficio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bene_id")
	public int getBeneId() {
		return this.beneId;
	}

	public void setBeneId(int beneId) {
		this.beneId = beneId;
	}


	@Column(name="bene_code")
	public String getBeneCode() {
		return this.beneCode;
	}

	public void setBeneCode(String beneCode) {
		this.beneCode = beneCode;
	}


	@Column(name="bene_descripcion")
	public String getBeneDescripcion() {
		return this.beneDescripcion;
	}

	public void setBeneDescripcion(String beneDescripcion) {
		this.beneDescripcion = beneDescripcion;
	}


	@Column(name="bene_nombre")
	public String getBeneNombre() {
		return this.beneNombre;
	}

	public void setBeneNombre(String beneNombre) {
		this.beneNombre = beneNombre;
	}


	//bi-directional many-to-one association to OfertaBeneficio
	@OneToMany(mappedBy="beneficio")
	public List<OfertaBeneficio> getOfertaBeneficios() {
		return this.ofertaBeneficios;
	}

	public void setOfertaBeneficios(List<OfertaBeneficio> ofertaBeneficios) {
		this.ofertaBeneficios = ofertaBeneficios;
	}

	public OfertaBeneficio addOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getOfertaBeneficios().add(ofertaBeneficio);
		ofertaBeneficio.setBeneficio(this);

		return ofertaBeneficio;
	}

	public OfertaBeneficio removeOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getOfertaBeneficios().remove(ofertaBeneficio);
		ofertaBeneficio.setBeneficio(null);

		return ofertaBeneficio;
	}


	//bi-directional many-to-one association to PostulanteBeneficio
	@OneToMany(mappedBy="beneficio")
	public List<PostulanteBeneficio> getPostulanteBeneficios() {
		return this.postulanteBeneficios;
	}

	public void setPostulanteBeneficios(List<PostulanteBeneficio> postulanteBeneficios) {
		this.postulanteBeneficios = postulanteBeneficios;
	}

	public PostulanteBeneficio addPostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getPostulanteBeneficios().add(postulanteBeneficio);
		postulanteBeneficio.setBeneficio(this);

		return postulanteBeneficio;
	}

	public PostulanteBeneficio removePostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getPostulanteBeneficios().remove(postulanteBeneficio);
		postulanteBeneficio.setBeneficio(null);

		return postulanteBeneficio;
	}

}