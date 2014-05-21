package vindbrein.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the reclutador database table.
 * 
 */
@Entity
@Table(name="reclutador")
@NamedQuery(name="Reclutador.findAll", query="SELECT r FROM Reclutador r")
public class Reclutador implements Serializable {
	private static final long serialVersionUID = 1L;
	private int reclId;
	private String reclApellidoMaterno;
	private String reclApellidoPaterno;
	private byte reclFlagMaestro;
	private String reclNombres;
	private String reclTelefono;
	private Organizacion organizacion;
	private Usuario usuario;

	public Reclutador() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recl_id")
	public int getReclId() {
		return this.reclId;
	}

	public void setReclId(int reclId) {
		this.reclId = reclId;
	}


	@Column(name="recl_apellido_materno")
	public String getReclApellidoMaterno() {
		return this.reclApellidoMaterno;
	}

	public void setReclApellidoMaterno(String reclApellidoMaterno) {
		this.reclApellidoMaterno = reclApellidoMaterno;
	}


	@Column(name="recl_apellido_paterno")
	public String getReclApellidoPaterno() {
		return this.reclApellidoPaterno;
	}

	public void setReclApellidoPaterno(String reclApellidoPaterno) {
		this.reclApellidoPaterno = reclApellidoPaterno;
	}


	@Column(name="recl_flag_maestro")
	public byte getReclFlagMaestro() {
		return this.reclFlagMaestro;
	}

	public void setReclFlagMaestro(byte reclFlagMaestro) {
		this.reclFlagMaestro = reclFlagMaestro;
	}


	@Column(name="recl_nombres")
	public String getReclNombres() {
		return this.reclNombres;
	}

	public void setReclNombres(String reclNombres) {
		this.reclNombres = reclNombres;
	}


	@Column(name="recl_telefono")
	public String getReclTelefono() {
		return this.reclTelefono;
	}

	public void setReclTelefono(String reclTelefono) {
		this.reclTelefono = reclTelefono;
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


	//bi-directional many-to-one association to Usuario
	@OneToOne(mappedBy="reclutador")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}