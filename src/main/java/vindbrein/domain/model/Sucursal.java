package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sucursal database table.
 * 
 */
@Entity
@Table(name="sucursal")
@NamedQuery(name="Sucursal.findAll", query="SELECT s FROM Sucursal s")
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sucuId;
	private String sucuDireccion;
	private String sucuTelefono;
	private Distrito distrito;
	private Organizacion organizacion;
	private List<RespRrhh> respRrhhs;
	private List<Puesto> puestos;

	public Sucursal() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sucu_id")
	public int getSucuId() {
		return this.sucuId;
	}

	public void setSucuId(int sucuId) {
		this.sucuId = sucuId;
	}


	@Column(name="sucu_direccion")
	public String getSucuDireccion() {
		return this.sucuDireccion;
	}

	public void setSucuDireccion(String sucuDireccion) {
		this.sucuDireccion = sucuDireccion;
	}


	@Column(name="sucu_telefono")
	public String getSucuTelefono() {
		return this.sucuTelefono;
	}

	public void setSucuTelefono(String sucuTelefono) {
		this.sucuTelefono = sucuTelefono;
	}


	//bi-directional many-to-one association to Distrito
	@ManyToOne
	@JoinColumn(name="fk_dist_id")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}


	//bi-directional many-to-one association to Organizacion
	@ManyToOne
	@JoinColumn(name="fk_org_id")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}


	//bi-directional many-to-one association to RespRrhh
	@OneToMany(mappedBy="sucursal")
	public List<RespRrhh> getRespRrhhs() {
		return this.respRrhhs;
	}

	public void setRespRrhhs(List<RespRrhh> respRrhhs) {
		this.respRrhhs = respRrhhs;
	}

	public RespRrhh addRespRrhh(RespRrhh respRrhh) {
		getRespRrhhs().add(respRrhh);
		respRrhh.setSucursal(this);

		return respRrhh;
	}

	public RespRrhh removeRespRrhh(RespRrhh respRrhh) {
		getRespRrhhs().remove(respRrhh);
		respRrhh.setSucursal(null);

		return respRrhh;
	}


	//bi-directional many-to-many association to Puesto
	@ManyToMany
	@JoinTable(
		name="sucursal_puesto"
		, joinColumns={
			@JoinColumn(name="fk_sucu_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_pues_id")
			}
		)
	public List<Puesto> getPuestos() {
		return this.puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

}