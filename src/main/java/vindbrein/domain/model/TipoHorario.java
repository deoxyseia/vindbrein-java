package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_horario database table.
 * 
 */
@Entity
@Table(name="tipo_horario")
@NamedQuery(name="TipoHorario.findAll", query="SELECT t FROM TipoHorario t")
public class TipoHorario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tihoId;
	private String tihoCode;
	private String tihoDescripcion;
	private String tihoNombre;
	private List<Postulante> postulantes;
	private List<OfertaLaboral> ofertaLaborals;

	public TipoHorario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tiho_id")
	public int getTihoId() {
		return this.tihoId;
	}

	public void setTihoId(int tihoId) {
		this.tihoId = tihoId;
	}


	@Column(name="tiho_code")
	public String getTihoCode() {
		return this.tihoCode;
	}

	public void setTihoCode(String tihoCode) {
		this.tihoCode = tihoCode;
	}


	@Column(name="tiho_descripcion")
	public String getTihoDescripcion() {
		return this.tihoDescripcion;
	}

	public void setTihoDescripcion(String tihoDescripcion) {
		this.tihoDescripcion = tihoDescripcion;
	}


	@Column(name="tiho_nombre")
	public String getTihoNombre() {
		return this.tihoNombre;
	}

	public void setTihoNombre(String tihoNombre) {
		this.tihoNombre = tihoNombre;
	}


	//bi-directional many-to-one association to Postulante
	@OneToMany(mappedBy="tipoHorario")
	public List<Postulante> getPostulantes() {
		return this.postulantes;
	}

	public void setPostulantes(List<Postulante> postulantes) {
		this.postulantes = postulantes;
	}

	public Postulante addPostulante(Postulante postulante) {
		getPostulantes().add(postulante);
		postulante.setTipoHorario(this);

		return postulante;
	}

	public Postulante removePostulante(Postulante postulante) {
		getPostulantes().remove(postulante);
		postulante.setTipoHorario(null);

		return postulante;
	}


	//bi-directional many-to-one association to OfertaLaboral
	@OneToMany(mappedBy="tipoHorario")
	public List<OfertaLaboral> getOfertaLaborals() {
		return this.ofertaLaborals;
	}

	public void setOfertaLaborals(List<OfertaLaboral> ofertaLaborals) {
		this.ofertaLaborals = ofertaLaborals;
	}

	public OfertaLaboral addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().add(ofertaLaboral);
		ofertaLaboral.setTipoHorario(this);

		return ofertaLaboral;
	}

	public OfertaLaboral removeOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().remove(ofertaLaboral);
		ofertaLaboral.setTipoHorario(null);

		return ofertaLaboral;
	}

}