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
	private List<Estudio> estudios;

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


	//bi-directional many-to-one association to Estudio
	@OneToMany(mappedBy="grado")
	public List<Estudio> getEstudios() {
		return this.estudios;
	}

	public void setEstudios(List<Estudio> estudios) {
		this.estudios = estudios;
	}

	public Estudio addEstudio(Estudio estudio) {
		getEstudios().add(estudio);
		estudio.setGrado(this);

		return estudio;
	}

	public Estudio removeEstudio(Estudio estudio) {
		getEstudios().remove(estudio);
		estudio.setGrado(null);

		return estudio;
	}

}