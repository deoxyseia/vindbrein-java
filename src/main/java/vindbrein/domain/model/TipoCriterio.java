package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_criterio database table.
 * 
 */
@Entity
@Table(name="tipo_criterio")
@NamedQuery(name="TipoCriterio.findAll", query="SELECT t FROM TipoCriterio t")
public class TipoCriterio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ticrId;
	private String ticrCode;
	private String ticrDescripcion;
	private List<CriterioSeleccion> criterioSeleccions;

	public TipoCriterio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticr_id")
	public int getTicrId() {
		return this.ticrId;
	}

	public void setTicrId(int ticrId) {
		this.ticrId = ticrId;
	}


	@Column(name="ticr_code")
	public String getTicrCode() {
		return this.ticrCode;
	}

	public void setTicrCode(String ticrCode) {
		this.ticrCode = ticrCode;
	}


	@Column(name="ticr_descripcion")
	public String getTicrDescripcion() {
		return this.ticrDescripcion;
	}

	public void setTicrDescripcion(String ticrDescripcion) {
		this.ticrDescripcion = ticrDescripcion;
	}


	//bi-directional many-to-one association to CriterioSeleccion
	@OneToMany(mappedBy="tipoCriterio")
	public List<CriterioSeleccion> getCriterioSeleccions() {
		return this.criterioSeleccions;
	}

	public void setCriterioSeleccions(List<CriterioSeleccion> criterioSeleccions) {
		this.criterioSeleccions = criterioSeleccions;
	}

	public CriterioSeleccion addCriterioSeleccion(CriterioSeleccion criterioSeleccion) {
		getCriterioSeleccions().add(criterioSeleccion);
		criterioSeleccion.setTipoCriterio(this);

		return criterioSeleccion;
	}

	public CriterioSeleccion removeCriterioSeleccion(CriterioSeleccion criterioSeleccion) {
		getCriterioSeleccions().remove(criterioSeleccion);
		criterioSeleccion.setTipoCriterio(null);

		return criterioSeleccion;
	}

}