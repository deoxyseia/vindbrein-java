package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_puesto database table.
 * 
 */
@Entity
@Table(name="tipo_puesto")
@NamedQuery(name="TipoPuesto.findAll", query="SELECT t FROM TipoPuesto t")
public class TipoPuesto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tipuId;
	private String tipuCode;
	private String tipuDescripcion;
	private String tipuNombre;
	private List<ExperienciaLaboral> experienciaLaborals;
	private List<Puesto> puestos;
	private List<PreferenciaPostulante> preferenciaPostulantes;
	private List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals;

	public TipoPuesto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipu_id")
	public int getTipuId() {
		return this.tipuId;
	}

	public void setTipuId(int tipuId) {
		this.tipuId = tipuId;
	}


	@Column(name="tipu_code")
	public String getTipuCode() {
		return this.tipuCode;
	}

	public void setTipuCode(String tipuCode) {
		this.tipuCode = tipuCode;
	}


	@Column(name="tipu_descripcion")
	public String getTipuDescripcion() {
		return this.tipuDescripcion;
	}

	public void setTipuDescripcion(String tipuDescripcion) {
		this.tipuDescripcion = tipuDescripcion;
	}


	@Column(name="tipu_nombre")
	public String getTipuNombre() {
		return this.tipuNombre;
	}

	public void setTipuNombre(String tipuNombre) {
		this.tipuNombre = tipuNombre;
	}


	//bi-directional many-to-one association to ExperienciaLaboral
	@OneToMany(mappedBy="tipoPuesto")
	public List<ExperienciaLaboral> getExperienciaLaborals() {
		return this.experienciaLaborals;
	}

	public void setExperienciaLaborals(List<ExperienciaLaboral> experienciaLaborals) {
		this.experienciaLaborals = experienciaLaborals;
	}

	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().add(experienciaLaboral);
		experienciaLaboral.setTipoPuesto(this);

		return experienciaLaboral;
	}

	public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().remove(experienciaLaboral);
		experienciaLaboral.setTipoPuesto(null);

		return experienciaLaboral;
	}


	//bi-directional many-to-one association to Puesto
	@OneToMany(mappedBy="tipoPuesto")
	public List<Puesto> getPuestos() {
		return this.puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

	public Puesto addPuesto(Puesto puesto) {
		getPuestos().add(puesto);
		puesto.setTipoPuesto(this);

		return puesto;
	}

	public Puesto removePuesto(Puesto puesto) {
		getPuestos().remove(puesto);
		puesto.setTipoPuesto(null);

		return puesto;
	}


	//bi-directional many-to-one association to PreferenciaPostulante
	@OneToMany(mappedBy="tipoPuesto")
	public List<PreferenciaPostulante> getPreferenciaPostulantes() {
		return this.preferenciaPostulantes;
	}

	public void setPreferenciaPostulantes(List<PreferenciaPostulante> preferenciaPostulantes) {
		this.preferenciaPostulantes = preferenciaPostulantes;
	}

	public PreferenciaPostulante addPreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().add(preferenciaPostulante);
		preferenciaPostulante.setTipoPuesto(this);

		return preferenciaPostulante;
	}

	public PreferenciaPostulante removePreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().remove(preferenciaPostulante);
		preferenciaPostulante.setTipoPuesto(null);

		return preferenciaPostulante;
	}


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@OneToMany(mappedBy="tipoPuesto")
	public List<PreferenciaPuestoLaboral> getPreferenciaPuestoLaborals() {
		return this.preferenciaPuestoLaborals;
	}

	public void setPreferenciaPuestoLaborals(List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals) {
		this.preferenciaPuestoLaborals = preferenciaPuestoLaborals;
	}

	public PreferenciaPuestoLaboral addPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().add(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setTipoPuesto(this);

		return preferenciaPuestoLaboral;
	}

	public PreferenciaPuestoLaboral removePreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().remove(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setTipoPuesto(null);

		return preferenciaPuestoLaboral;
	}

}