package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the puesto database table.
 * 
 */
@Entity
@Table(name="puesto")
@NamedQuery(name="Puesto.findAll", query="SELECT p FROM Puesto p")
public class Puesto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int puesId;
	private String puesDescripcion;
	private String puesEstado;
	private String puesNombre;
	//private List<ExperienciaLaboral> experienciaLaborals;
	private List<OrganizacionPuesto> organizacionPuestos;
	private NivelPuesto nivelPuesto;

	public Puesto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pues_id")
	public int getPuesId() {
		return this.puesId;
	}

	public void setPuesId(int puesId) {
		this.puesId = puesId;
	}


	@Column(name="pues_descripcion")
	public String getPuesDescripcion() {
		return this.puesDescripcion;
	}

	public void setPuesDescripcion(String puesDescripcion) {
		this.puesDescripcion = puesDescripcion;
	}


	@Column(name="pues_estado")
	public String getPuesEstado() {
		return this.puesEstado;
	}

	public void setPuesEstado(String puesEstado) {
		this.puesEstado = puesEstado;
	}


	@Column(name="pues_nombre")
	public String getPuesNombre() {
		return this.puesNombre;
	}

	public void setPuesNombre(String puesNombre) {
		this.puesNombre = puesNombre;
	}


//	//bi-directional many-to-one association to ExperienciaLaboral
//	@OneToMany(mappedBy="puesto")
//	public List<ExperienciaLaboral> getExperienciaLaborals() {
//		return this.experienciaLaborals;
//	}
//
//	public void setExperienciaLaborals(List<ExperienciaLaboral> experienciaLaborals) {
//		this.experienciaLaborals = experienciaLaborals;
//	}
//
//	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
//		getExperienciaLaborals().add(experienciaLaboral);
//		experienciaLaboral.setPuesto(this);
//
//		return experienciaLaboral;
//	}
//
//	public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
//		getExperienciaLaborals().remove(experienciaLaboral);
//		experienciaLaboral.setPuesto(null);
//
//		return experienciaLaboral;
//	}


	//bi-directional many-to-one association to OrganizacionPuesto
	@OneToMany(mappedBy="puesto")
	public List<OrganizacionPuesto> getOrganizacionPuestos() {
		return this.organizacionPuestos;
	}

	public void setOrganizacionPuestos(List<OrganizacionPuesto> organizacionPuestos) {
		this.organizacionPuestos = organizacionPuestos;
	}

	public OrganizacionPuesto addOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getOrganizacionPuestos().add(organizacionPuesto);
		organizacionPuesto.setPuesto(this);

		return organizacionPuesto;
	}

	public OrganizacionPuesto removeOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getOrganizacionPuestos().remove(organizacionPuesto);
		organizacionPuesto.setPuesto(null);

		return organizacionPuesto;
	}


	//bi-directional many-to-one association to NivelPuesto
	@ManyToOne
	@JoinColumn(name="fk_nipu_id")
	public NivelPuesto getNivelPuesto() {
		return this.nivelPuesto;
	}

	public void setNivelPuesto(NivelPuesto nivelPuesto) {
		this.nivelPuesto = nivelPuesto;
	}

}