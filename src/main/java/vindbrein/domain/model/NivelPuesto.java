package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_puesto database table.
 * 
 */
@Entity
@Table(name="nivel_puesto")
@NamedQuery(name="NivelPuesto.findAll", query="SELECT n FROM NivelPuesto n")
public class NivelPuesto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nipuId;
	private String nipuCode;
	private String nipuDescripcion;
	private String nipuNombre;
	private List<Postulante> postulantes;
	private List<Puesto> puestos;

	public NivelPuesto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nipu_id")
	public int getNipuId() {
		return this.nipuId;
	}

	public void setNipuId(int nipuId) {
		this.nipuId = nipuId;
	}


	@Column(name="nipu_code")
	public String getNipuCode() {
		return this.nipuCode;
	}

	public void setNipuCode(String nipuCode) {
		this.nipuCode = nipuCode;
	}


	@Column(name="nipu_descripcion")
	public String getNipuDescripcion() {
		return this.nipuDescripcion;
	}

	public void setNipuDescripcion(String nipuDescripcion) {
		this.nipuDescripcion = nipuDescripcion;
	}


	@Column(name="nipu_nombre")
	public String getNipuNombre() {
		return this.nipuNombre;
	}

	public void setNipuNombre(String nipuNombre) {
		this.nipuNombre = nipuNombre;
	}


	//bi-directional many-to-one association to Postulante
	@OneToMany(mappedBy="nivelPuesto")
	public List<Postulante> getPostulantes() {
		return this.postulantes;
	}

	public void setPostulantes(List<Postulante> postulantes) {
		this.postulantes = postulantes;
	}

	public Postulante addPostulante(Postulante postulante) {
		getPostulantes().add(postulante);
		postulante.setNivelPuesto(this);

		return postulante;
	}

	public Postulante removePostulante(Postulante postulante) {
		getPostulantes().remove(postulante);
		postulante.setNivelPuesto(null);

		return postulante;
	}


	//bi-directional many-to-one association to Puesto
	@OneToMany(mappedBy="nivelPuesto")
	public List<Puesto> getPuestos() {
		return this.puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

	public Puesto addPuesto(Puesto puesto) {
		getPuestos().add(puesto);
		puesto.setNivelPuesto(this);

		return puesto;
	}

	public Puesto removePuesto(Puesto puesto) {
		getPuestos().remove(puesto);
		puesto.setNivelPuesto(null);

		return puesto;
	}

}