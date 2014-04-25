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
	private String tihoName;
	private List<PuestoLaboral> puestoLaborals;
	private List<PreferenciaPostulante> preferenciaPostulantes;

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


	@Column(name="tiho_name")
	public String getTihoName() {
		return this.tihoName;
	}

	public void setTihoName(String tihoName) {
		this.tihoName = tihoName;
	}


	//bi-directional many-to-one association to PuestoLaboral
	@OneToMany(mappedBy="tipoHorario")
	public List<PuestoLaboral> getPuestoLaborals() {
		return this.puestoLaborals;
	}

	public void setPuestoLaborals(List<PuestoLaboral> puestoLaborals) {
		this.puestoLaborals = puestoLaborals;
	}

	public PuestoLaboral addPuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().add(puestoLaboral);
		puestoLaboral.setTipoHorario(this);

		return puestoLaboral;
	}

	public PuestoLaboral removePuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().remove(puestoLaboral);
		puestoLaboral.setTipoHorario(null);

		return puestoLaboral;
	}


	//bi-directional many-to-one association to PreferenciaPostulante
	@OneToMany(mappedBy="tipoHorario")
	public List<PreferenciaPostulante> getPreferenciaPostulantes() {
		return this.preferenciaPostulantes;
	}

	public void setPreferenciaPostulantes(List<PreferenciaPostulante> preferenciaPostulantes) {
		this.preferenciaPostulantes = preferenciaPostulantes;
	}

	public PreferenciaPostulante addPreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().add(preferenciaPostulante);
		preferenciaPostulante.setTipoHorario(this);

		return preferenciaPostulante;
	}

	public PreferenciaPostulante removePreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().remove(preferenciaPostulante);
		preferenciaPostulante.setTipoHorario(null);

		return preferenciaPostulante;
	}

}