package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conocimiento database table.
 * 
 */
@Entity
@Table(name="conocimiento")
@NamedQuery(name="Conocimiento.findAll", query="SELECT c FROM Conocimiento c")
public class Conocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int conoId;
	private String conoCode;
	private String conoDescripcion;
	private String conoEstado;
	private String conoNombre;
	private SubcategoriaConocimiento subcategoriaConocimiento;
	private List<PostulanteConocimiento> postulanteConocimientos;
	private List<OfertaConocimiento> ofertaConocimientos;

	public Conocimiento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cono_id")
	public int getConoId() {
		return this.conoId;
	}

	public void setConoId(int conoId) {
		this.conoId = conoId;
	}


	@Column(name="cono_code")
	public String getConoCode() {
		return this.conoCode;
	}

	public void setConoCode(String conoCode) {
		this.conoCode = conoCode;
	}


	@Column(name="cono_descripcion")
	public String getConoDescripcion() {
		return this.conoDescripcion;
	}

	public void setConoDescripcion(String conoDescripcion) {
		this.conoDescripcion = conoDescripcion;
	}


	@Column(name="cono_estado")
	public String getConoEstado() {
		return this.conoEstado;
	}

	public void setConoEstado(String conoEstado) {
		this.conoEstado = conoEstado;
	}


	@Column(name="cono_nombre")
	public String getConoNombre() {
		return this.conoNombre;
	}

	public void setConoNombre(String conoNombre) {
		this.conoNombre = conoNombre;
	}


	//bi-directional many-to-one association to SubcategoriaConocimiento
	@ManyToOne
	@JoinColumn(name="fk_suco_id")
	public SubcategoriaConocimiento getSubcategoriaConocimiento() {
		return this.subcategoriaConocimiento;
	}

	public void setSubcategoriaConocimiento(SubcategoriaConocimiento subcategoriaConocimiento) {
		this.subcategoriaConocimiento = subcategoriaConocimiento;
	}


	//bi-directional many-to-one association to PostulanteConocimiento
	@OneToMany(mappedBy="conocimiento")
	public List<PostulanteConocimiento> getPostulanteConocimientos() {
		return this.postulanteConocimientos;
	}

	public void setPostulanteConocimientos(List<PostulanteConocimiento> postulanteConocimientos) {
		this.postulanteConocimientos = postulanteConocimientos;
	}

	public PostulanteConocimiento addPostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().add(postulanteConocimiento);
		postulanteConocimiento.setConocimiento(this);

		return postulanteConocimiento;
	}

	public PostulanteConocimiento removePostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().remove(postulanteConocimiento);
		postulanteConocimiento.setConocimiento(null);

		return postulanteConocimiento;
	}


	//bi-directional many-to-one association to OfertaConocimiento
	@OneToMany(mappedBy="conocimiento")
	public List<OfertaConocimiento> getOfertaConocimientos() {
		return this.ofertaConocimientos;
	}

	public void setOfertaConocimientos(List<OfertaConocimiento> ofertaConocimientos) {
		this.ofertaConocimientos = ofertaConocimientos;
	}

	public OfertaConocimiento addOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().add(ofertaConocimiento);
		ofertaConocimiento.setConocimiento(this);

		return ofertaConocimiento;
	}

	public OfertaConocimiento removeOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().remove(ofertaConocimiento);
		ofertaConocimiento.setConocimiento(null);

		return ofertaConocimiento;
	}

}