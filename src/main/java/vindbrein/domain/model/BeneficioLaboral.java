package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the beneficio_laboral database table.
 * 
 */
@Entity
@Table(name="beneficio_laboral")
@NamedQuery(name="BeneficioLaboral.findAll", query="SELECT b FROM BeneficioLaboral b")
public class BeneficioLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	private int beneId;
	private String beneCode;
	private String beneDescripcion;
	private String beneName;
	private List<PuestoLaboral> puestoLaborals;
	private List<PreferenciaPostulante> preferenciaPostulantes;

	public BeneficioLaboral() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bene_id")
	public int getBeneId() {
		return this.beneId;
	}

	public void setBeneId(int beneId) {
		this.beneId = beneId;
	}


	@Column(name="bene_code")
	public String getBeneCode() {
		return this.beneCode;
	}

	public void setBeneCode(String beneCode) {
		this.beneCode = beneCode;
	}


	@Column(name="bene_descripcion")
	public String getBeneDescripcion() {
		return this.beneDescripcion;
	}

	public void setBeneDescripcion(String beneDescripcion) {
		this.beneDescripcion = beneDescripcion;
	}


	@Column(name="bene_name")
	public String getBeneName() {
		return this.beneName;
	}

	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}


	//bi-directional many-to-many association to PuestoLaboral
	@ManyToMany(mappedBy="beneficioLaborals")
	public List<PuestoLaboral> getPuestoLaborals() {
		return this.puestoLaborals;
	}

	public void setPuestoLaborals(List<PuestoLaboral> puestoLaborals) {
		this.puestoLaborals = puestoLaborals;
	}


	//bi-directional many-to-many association to PreferenciaPostulante
	@ManyToMany(mappedBy="beneficioLaborals")
	public List<PreferenciaPostulante> getPreferenciaPostulantes() {
		return this.preferenciaPostulantes;
	}

	public void setPreferenciaPostulantes(List<PreferenciaPostulante> preferenciaPostulantes) {
		this.preferenciaPostulantes = preferenciaPostulantes;
	}

}