package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estudio_generico database table.
 * 
 */
@Entity
@Table(name="estudio_generico")
@NamedQuery(name="EstudioGenerico.findAll", query="SELECT e FROM EstudioGenerico e")
public class EstudioGenerico implements Serializable {
	private static final long serialVersionUID = 1L;
	private int esgeId;
	private String esgeCode;
	private String esgeDescripcion;
	private String esgeNombre;
	private List<Estudio> estudios;
	private Grado grado;
	private List<PuestoPrefEstudio> puestoPrefEstudios;

	public EstudioGenerico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="esge_id")
	public int getEsgeId() {
		return this.esgeId;
	}

	public void setEsgeId(int esgeId) {
		this.esgeId = esgeId;
	}


	@Column(name="esge_code")
	public String getEsgeCode() {
		return this.esgeCode;
	}

	public void setEsgeCode(String esgeCode) {
		this.esgeCode = esgeCode;
	}


	@Column(name="esge_descripcion")
	public String getEsgeDescripcion() {
		return this.esgeDescripcion;
	}

	public void setEsgeDescripcion(String esgeDescripcion) {
		this.esgeDescripcion = esgeDescripcion;
	}


	@Column(name="esge_nombre")
	public String getEsgeNombre() {
		return this.esgeNombre;
	}

	public void setEsgeNombre(String esgeNombre) {
		this.esgeNombre = esgeNombre;
	}


	//bi-directional many-to-one association to Estudio
	@OneToMany(mappedBy="estudioGenerico")
	public List<Estudio> getEstudios() {
		return this.estudios;
	}

	public void setEstudios(List<Estudio> estudios) {
		this.estudios = estudios;
	}

	public Estudio addEstudio(Estudio estudio) {
		getEstudios().add(estudio);
		estudio.setEstudioGenerico(this);

		return estudio;
	}

	public Estudio removeEstudio(Estudio estudio) {
		getEstudios().remove(estudio);
		estudio.setEstudioGenerico(null);

		return estudio;
	}


	//bi-directional many-to-one association to Grado
	@ManyToOne
	@JoinColumn(name="fk_grad_id")
	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}


	//bi-directional many-to-one association to PuestoPrefEstudio
	@OneToMany(mappedBy="estudioGenerico")
	public List<PuestoPrefEstudio> getPuestoPrefEstudios() {
		return this.puestoPrefEstudios;
	}

	public void setPuestoPrefEstudios(List<PuestoPrefEstudio> puestoPrefEstudios) {
		this.puestoPrefEstudios = puestoPrefEstudios;
	}

	public PuestoPrefEstudio addPuestoPrefEstudio(PuestoPrefEstudio puestoPrefEstudio) {
		getPuestoPrefEstudios().add(puestoPrefEstudio);
		puestoPrefEstudio.setEstudioGenerico(this);

		return puestoPrefEstudio;
	}

	public PuestoPrefEstudio removePuestoPrefEstudio(PuestoPrefEstudio puestoPrefEstudio) {
		getPuestoPrefEstudios().remove(puestoPrefEstudio);
		puestoPrefEstudio.setEstudioGenerico(null);

		return puestoPrefEstudio;
	}

}