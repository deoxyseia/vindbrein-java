package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the residencia database table.
 * 
 */
@Entity
@Table(name="residencia")
@NamedQuery(name="Residencia.findAll", query="SELECT r FROM Residencia r")
public class Residencia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int resiId;
	private String resiDireccion;
	private byte resiEstado;
	private String resiTelefono;
	private Distrito distrito;
	private Postulante postulante;

	public Residencia() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resi_id")
	public int getResiId() {
		return this.resiId;
	}

	public void setResiId(int resiId) {
		this.resiId = resiId;
	}


	@Column(name="resi_direccion")
	public String getResiDireccion() {
		return this.resiDireccion;
	}

	public void setResiDireccion(String resiDireccion) {
		this.resiDireccion = resiDireccion;
	}


	@Column(name="resi_estado")
	public byte getResiEstado() {
		return this.resiEstado;
	}

	public void setResiEstado(byte resiEstado) {
		this.resiEstado = resiEstado;
	}


	@Column(name="resi_telefono")
	public String getResiTelefono() {
		return this.resiTelefono;
	}

	public void setResiTelefono(String resiTelefono) {
		this.resiTelefono = resiTelefono;
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


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_estu_id")
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

}