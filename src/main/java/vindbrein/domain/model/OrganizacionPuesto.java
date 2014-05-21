package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the organizacion_puesto database table.
 * 
 */
@Entity
@Table(name="organizacion_puesto")
@NamedQuery(name="OrganizacionPuesto.findAll", query="SELECT o FROM OrganizacionPuesto o")
public class OrganizacionPuesto implements Serializable {
	private static final long serialVersionUID = 1L;
	private OrganizacionPuestoPK id;
	private String orpuEstado;
	private List<ExperienciaLaboral> experienciaLaborals;
	private List<OfertaLaboral> ofertaLaborals;
	private Puesto puesto;
	private Organizacion organizacion;

	public OrganizacionPuesto() {
	}


	@EmbeddedId
	public OrganizacionPuestoPK getId() {
		return this.id;
	}

	public void setId(OrganizacionPuestoPK id) {
		this.id = id;
	}


	@Column(name="orpu_estado")
	public String getOrpuEstado() {
		return this.orpuEstado;
	}

	public void setOrpuEstado(String orpuEstado) {
		this.orpuEstado = orpuEstado;
	}


	//bi-directional many-to-one association to ExperienciaLaboral
	@OneToMany(mappedBy="organizacionPuesto")
	public List<ExperienciaLaboral> getExperienciaLaborals() {
		return this.experienciaLaborals;
	}

	public void setExperienciaLaborals(List<ExperienciaLaboral> experienciaLaborals) {
		this.experienciaLaborals = experienciaLaborals;
	}

	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().add(experienciaLaboral);
		experienciaLaboral.setOrganizacionPuesto(this);

		return experienciaLaboral;
	}

	public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().remove(experienciaLaboral);
		experienciaLaboral.setOrganizacionPuesto(null);

		return experienciaLaboral;
	}


	//bi-directional many-to-one association to OfertaLaboral
	@OneToMany(mappedBy="organizacionPuesto")
	public List<OfertaLaboral> getOfertaLaborals() {
		return this.ofertaLaborals;
	}

	public void setOfertaLaborals(List<OfertaLaboral> ofertaLaborals) {
		this.ofertaLaborals = ofertaLaborals;
	}

	public OfertaLaboral addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().add(ofertaLaboral);
		ofertaLaboral.setOrganizacionPuesto(this);

		return ofertaLaboral;
	}

	public OfertaLaboral removeOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaborals().remove(ofertaLaboral);
		ofertaLaboral.setOrganizacionPuesto(null);

		return ofertaLaboral;
	}


	//bi-directional many-to-one association to Puesto
	@ManyToOne
	@JoinColumn(name="fk_pues_id", insertable=false, updatable=false)
	public Puesto getPuesto() {
		return this.puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}


	//bi-directional many-to-one association to Organizacion
	@ManyToOne
	@JoinColumn(name="fk_orga_id", insertable=false, updatable=false)
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}