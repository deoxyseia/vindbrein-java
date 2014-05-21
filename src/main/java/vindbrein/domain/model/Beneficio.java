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
	private List<OfertaLaboral> ofertaLaborals;
	private PostulanteBeneficio postulanteBeneficio;

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


	//bi-directional many-to-many association to OfertaLaboral
	@ManyToMany
	@JoinTable(
		name="oferta_beneficio"
		, joinColumns={
			@JoinColumn(name="fk_bene_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_ofla_id")
			}
		)
	public List<OfertaLaboral> getOfertaLaborals() {
		return this.ofertaLaborals;
	}

	public void setOfertaLaborals(List<OfertaLaboral> ofertaLaborals) {
		this.ofertaLaborals = ofertaLaborals;
	}


	//bi-directional one-to-one association to PostulanteBeneficio
	@OneToOne(mappedBy="beneficio")
	public PostulanteBeneficio getPostulanteBeneficio() {
		return this.postulanteBeneficio;
	}

	public void setPostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		this.postulanteBeneficio = postulanteBeneficio;
	}

}