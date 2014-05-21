package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grado database table.
 * 
 */
@Entity
@Table(name="grado")
@NamedQuery(name="Grado.findAll", query="SELECT g FROM Grado g")
public class Grado implements Serializable {
	private static final long serialVersionUID = 1L;
	private int gradId;
	private String gradCode;
	private String gradDescripcion;
	private String gradNombre;
	private List<EstudioGenerico> estudioGenericos;

	public Grado() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grad_id")
	public int getGradId() {
		return this.gradId;
	}

	public void setGradId(int gradId) {
		this.gradId = gradId;
	}


	@Column(name="grad_code")
	public String getGradCode() {
		return this.gradCode;
	}

	public void setGradCode(String gradCode) {
		this.gradCode = gradCode;
	}


	@Column(name="grad_descripcion")
	public String getGradDescripcion() {
		return this.gradDescripcion;
	}

	public void setGradDescripcion(String gradDescripcion) {
		this.gradDescripcion = gradDescripcion;
	}


	@Column(name="grad_nombre")
	public String getGradNombre() {
		return this.gradNombre;
	}

	public void setGradNombre(String gradNombre) {
		this.gradNombre = gradNombre;
	}


	//bi-directional many-to-one association to EstudioGenerico
	@OneToMany(mappedBy="grado")
	public List<EstudioGenerico> getEstudioGenericos() {
		return this.estudioGenericos;
	}

	public void setEstudioGenericos(List<EstudioGenerico> estudioGenericos) {
		this.estudioGenericos = estudioGenericos;
	}

	public EstudioGenerico addEstudioGenerico(EstudioGenerico estudioGenerico) {
		getEstudioGenericos().add(estudioGenerico);
		estudioGenerico.setGrado(this);

		return estudioGenerico;
	}

	public EstudioGenerico removeEstudioGenerico(EstudioGenerico estudioGenerico) {
		getEstudioGenericos().remove(estudioGenerico);
		estudioGenerico.setGrado(null);

		return estudioGenerico;
	}

}