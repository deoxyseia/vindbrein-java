package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


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
	private byte sucuPrincipal;
	private String sucuTelefono;
	private Distrito distrito;
	private Organizacion organizacion;

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


	@Column(name="sucu_principal")
	public byte getSucuPrincipal() {
		return this.sucuPrincipal;
	}

	public void setSucuPrincipal(byte sucuPrincipal) {
		this.sucuPrincipal = sucuPrincipal;
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
	@JoinColumn(name="fk_orga_id")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}