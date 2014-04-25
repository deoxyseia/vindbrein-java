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
	private String puesNombre;
	private TipoPuesto tipoPuesto;
	private List<Sucursal> sucursals;

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


	@Column(name="pues_nombre")
	public String getPuesNombre() {
		return this.puesNombre;
	}

	public void setPuesNombre(String puesNombre) {
		this.puesNombre = puesNombre;
	}


	//bi-directional many-to-one association to TipoPuesto
	@ManyToOne
	@JoinColumn(name="fk_tipu_id")
	public TipoPuesto getTipoPuesto() {
		return this.tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}


	//bi-directional many-to-many association to Sucursal
	@ManyToMany(mappedBy="puestos")
	public List<Sucursal> getSucursals() {
		return this.sucursals;
	}

	public void setSucursals(List<Sucursal> sucursals) {
		this.sucursals = sucursals;
	}

}