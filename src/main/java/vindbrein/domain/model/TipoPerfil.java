package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_perfil database table.
 * 
 */
@Entity
@Table(name="tipo_perfil")
@NamedQuery(name="TipoPerfil.findAll", query="SELECT t FROM TipoPerfil t")
public class TipoPerfil implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tipeId;
	private String tipeCode;
	private String tipeDescripcion;
	private List<CriterioSeleccion> criterioSeleccions;

	public TipoPerfil() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipe_id")
	public int getTipeId() {
		return this.tipeId;
	}

	public void setTipeId(int tipeId) {
		this.tipeId = tipeId;
	}


	@Column(name="tipe_code")
	public String getTipeCode() {
		return this.tipeCode;
	}

	public void setTipeCode(String tipeCode) {
		this.tipeCode = tipeCode;
	}


	@Column(name="tipe_descripcion")
	public String getTipeDescripcion() {
		return this.tipeDescripcion;
	}

	public void setTipeDescripcion(String tipeDescripcion) {
		this.tipeDescripcion = tipeDescripcion;
	}


	//bi-directional many-to-one association to CriterioSeleccion
	@OneToMany(mappedBy="tipoPerfil")
	public List<CriterioSeleccion> getCriterioSeleccions() {
		return this.criterioSeleccions;
	}

	public void setCriterioSeleccions(List<CriterioSeleccion> criterioSeleccions) {
		this.criterioSeleccions = criterioSeleccions;
	}

	public CriterioSeleccion addCriterioSeleccion(CriterioSeleccion criterioSeleccion) {
		getCriterioSeleccions().add(criterioSeleccion);
		criterioSeleccion.setTipoPerfil(this);

		return criterioSeleccion;
	}

	public CriterioSeleccion removeCriterioSeleccion(CriterioSeleccion criterioSeleccion) {
		getCriterioSeleccions().remove(criterioSeleccion);
		criterioSeleccion.setTipoPerfil(null);

		return criterioSeleccion;
	}

}