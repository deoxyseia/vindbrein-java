package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria_conocimiento database table.
 * 
 */
@Entity
@Table(name="categoria_conocimiento")
@NamedQuery(name="CategoriaConocimiento.findAll", query="SELECT c FROM CategoriaConocimiento c")
public class CategoriaConocimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cacoId;
	private String cacoCode;
	private String cacoDescripcion;
	private String cacoNombre;
	private List<SubcategoriaConocimiento> subcategoriaConocimientos;

	public CategoriaConocimiento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="caco_id")
	public int getCacoId() {
		return this.cacoId;
	}

	public void setCacoId(int cacoId) {
		this.cacoId = cacoId;
	}


	@Column(name="caco_code")
	public String getCacoCode() {
		return this.cacoCode;
	}

	public void setCacoCode(String cacoCode) {
		this.cacoCode = cacoCode;
	}


	@Column(name="caco_descripcion")
	public String getCacoDescripcion() {
		return this.cacoDescripcion;
	}

	public void setCacoDescripcion(String cacoDescripcion) {
		this.cacoDescripcion = cacoDescripcion;
	}


	@Column(name="caco_nombre")
	public String getCacoNombre() {
		return this.cacoNombre;
	}

	public void setCacoNombre(String cacoNombre) {
		this.cacoNombre = cacoNombre;
	}


	//bi-directional many-to-one association to SubcategoriaConocimiento
	@OneToMany(mappedBy="categoriaConocimiento")
	public List<SubcategoriaConocimiento> getSubcategoriaConocimientos() {
		return this.subcategoriaConocimientos;
	}

	public void setSubcategoriaConocimientos(List<SubcategoriaConocimiento> subcategoriaConocimientos) {
		this.subcategoriaConocimientos = subcategoriaConocimientos;
	}

	public SubcategoriaConocimiento addSubcategoriaConocimiento(SubcategoriaConocimiento subcategoriaConocimiento) {
		getSubcategoriaConocimientos().add(subcategoriaConocimiento);
		subcategoriaConocimiento.setCategoriaConocimiento(this);

		return subcategoriaConocimiento;
	}

	public SubcategoriaConocimiento removeSubcategoriaConocimiento(SubcategoriaConocimiento subcategoriaConocimiento) {
		getSubcategoriaConocimientos().remove(subcategoriaConocimiento);
		subcategoriaConocimiento.setCategoriaConocimiento(null);

		return subcategoriaConocimiento;
	}

}