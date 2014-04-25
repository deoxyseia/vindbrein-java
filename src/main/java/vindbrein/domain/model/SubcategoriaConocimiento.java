package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subcategoria_conocimiento database table.
 * 
 */
@Entity
@Table(name="subcategoria_conocimiento")
@NamedQuery(name="SubcategoriaConocimiento.findAll", query="SELECT s FROM SubcategoriaConocimiento s")
public class SubcategoriaConocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sucoId;
	private String sucoCode;
	private String sucoDescripcion;
	private String sucoNombre;
	private List<Conocimiento> conocimientos;
	private CategoriaConocimiento categoriaConocimiento;

	public SubcategoriaConocimiento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="suco_id")
	public int getSucoId() {
		return this.sucoId;
	}

	public void setSucoId(int sucoId) {
		this.sucoId = sucoId;
	}


	@Column(name="suco_code")
	public String getSucoCode() {
		return this.sucoCode;
	}

	public void setSucoCode(String sucoCode) {
		this.sucoCode = sucoCode;
	}


	@Column(name="suco_descripcion")
	public String getSucoDescripcion() {
		return this.sucoDescripcion;
	}

	public void setSucoDescripcion(String sucoDescripcion) {
		this.sucoDescripcion = sucoDescripcion;
	}


	@Column(name="suco_nombre")
	public String getSucoNombre() {
		return this.sucoNombre;
	}

	public void setSucoNombre(String sucoNombre) {
		this.sucoNombre = sucoNombre;
	}


	//bi-directional many-to-one association to Conocimiento
	@OneToMany(mappedBy="subcategoriaConocimiento")
	public List<Conocimiento> getConocimientos() {
		return this.conocimientos;
	}

	public void setConocimientos(List<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public Conocimiento addConocimiento(Conocimiento conocimiento) {
		getConocimientos().add(conocimiento);
		conocimiento.setSubcategoriaConocimiento(this);

		return conocimiento;
	}

	public Conocimiento removeConocimiento(Conocimiento conocimiento) {
		getConocimientos().remove(conocimiento);
		conocimiento.setSubcategoriaConocimiento(null);

		return conocimiento;
	}


	//bi-directional many-to-one association to CategoriaConocimiento
	@ManyToOne
	@JoinColumn(name="fk_cate_id")
	public CategoriaConocimiento getCategoriaConocimiento() {
		return this.categoriaConocimiento;
	}

	public void setCategoriaConocimiento(CategoriaConocimiento categoriaConocimiento) {
		this.categoriaConocimiento = categoriaConocimiento;
	}

}