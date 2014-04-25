package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resp_organizacion database table.
 * 
 */
@Entity
@Table(name="resp_organizacion")
@NamedQuery(name="RespOrganizacion.findAll", query="SELECT r FROM RespOrganizacion r")
public class RespOrganizacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int reorId;
	private String reorApellidoMaterno;
	private String reorApellidoPaterno;
	private String reorMail;
	private String reorNombres;
	private String reorTelefono;
	private Organizacion organizacion;

	public RespOrganizacion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reor_id")
	public int getReorId() {
		return this.reorId;
	}

	public void setReorId(int reorId) {
		this.reorId = reorId;
	}


	@Column(name="reor_apellido_materno")
	public String getReorApellidoMaterno() {
		return this.reorApellidoMaterno;
	}

	public void setReorApellidoMaterno(String reorApellidoMaterno) {
		this.reorApellidoMaterno = reorApellidoMaterno;
	}


	@Column(name="reor_apellido_paterno")
	public String getReorApellidoPaterno() {
		return this.reorApellidoPaterno;
	}

	public void setReorApellidoPaterno(String reorApellidoPaterno) {
		this.reorApellidoPaterno = reorApellidoPaterno;
	}


	@Column(name="reor_mail")
	public String getReorMail() {
		return this.reorMail;
	}

	public void setReorMail(String reorMail) {
		this.reorMail = reorMail;
	}


	@Column(name="reor_nombres")
	public String getReorNombres() {
		return this.reorNombres;
	}

	public void setReorNombres(String reorNombres) {
		this.reorNombres = reorNombres;
	}


	@Column(name="reor_telefono")
	public String getReorTelefono() {
		return this.reorTelefono;
	}

	public void setReorTelefono(String reorTelefono) {
		this.reorTelefono = reorTelefono;
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