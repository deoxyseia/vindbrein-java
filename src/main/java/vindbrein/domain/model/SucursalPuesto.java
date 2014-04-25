package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sucursal_puesto database table.
 * 
 */
@Entity
@Table(name="sucursal_puesto")
@NamedQuery(name="SucursalPuesto.findAll", query="SELECT s FROM SucursalPuesto s")
public class SucursalPuesto implements Serializable {
	private static final long serialVersionUID = 1L;
	private SucursalPuestoPK id;
	private List<PuestoLaboral> puestoLaborals;

	public SucursalPuesto() {
	}


	@EmbeddedId
	public SucursalPuestoPK getId() {
		return this.id;
	}

	public void setId(SucursalPuestoPK id) {
		this.id = id;
	}


	//bi-directional many-to-one association to PuestoLaboral
	@OneToMany(mappedBy="sucursalPuesto")
	public List<PuestoLaboral> getPuestoLaborals() {
		return this.puestoLaborals;
	}

	public void setPuestoLaborals(List<PuestoLaboral> puestoLaborals) {
		this.puestoLaborals = puestoLaborals;
	}

	public PuestoLaboral addPuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().add(puestoLaboral);
		puestoLaboral.setSucursalPuesto(this);

		return puestoLaboral;
	}

	public PuestoLaboral removePuestoLaboral(PuestoLaboral puestoLaboral) {
		getPuestoLaborals().remove(puestoLaboral);
		puestoLaboral.setSucursalPuesto(null);

		return puestoLaboral;
	}

}