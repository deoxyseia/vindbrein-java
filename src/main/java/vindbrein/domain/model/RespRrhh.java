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
 * The persistent class for the resp_rrhh database table.
 * 
 */
@Entity
@Table(name="resp_rrhh")
@NamedQuery(name="RespRrhh.findAll", query="SELECT r FROM RespRrhh r")
public class RespRrhh implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rerrId;
	private String rerrApellidoMaterno;
	private String rerrApellidoPaterno;
	private String rerrFlagRespMaestro;
	private String rerrNombres;
	private String rerrTelefono;
	private Sucursal sucursal;
	private Usuario usuario;

	public RespRrhh() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rerr_id")
	public int getRerrId() {
		return this.rerrId;
	}

	public void setRerrId(int rerrId) {
		this.rerrId = rerrId;
	}


	@Column(name="rerr_apellido_materno")
	public String getRerrApellidoMaterno() {
		return this.rerrApellidoMaterno;
	}

	public void setRerrApellidoMaterno(String rerrApellidoMaterno) {
		this.rerrApellidoMaterno = rerrApellidoMaterno;
	}


	@Column(name="rerr_apellido_paterno")
	public String getRerrApellidoPaterno() {
		return this.rerrApellidoPaterno;
	}

	public void setRerrApellidoPaterno(String rerrApellidoPaterno) {
		this.rerrApellidoPaterno = rerrApellidoPaterno;
	}


	@Column(name="rerr_flag_resp_maestro")
	public String getRerrFlagRespMaestro() {
		return this.rerrFlagRespMaestro;
	}

	public void setRerrFlagRespMaestro(String rerrFlagRespMaestro) {
		this.rerrFlagRespMaestro = rerrFlagRespMaestro;
	}


	@Column(name="rerr_nombres")
	public String getRerrNombres() {
		return this.rerrNombres;
	}

	public void setRerrNombres(String rerrNombres) {
		this.rerrNombres = rerrNombres;
	}


	@Column(name="rerr_telefono")
	public String getRerrTelefono() {
		return this.rerrTelefono;
	}

	public void setRerrTelefono(String rerrTelefono) {
		this.rerrTelefono = rerrTelefono;
	}


	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="fk_sucu_id")
	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}


	//bi-directional many-to-one association to Usuario
	@OneToOne(mappedBy="respRrhh")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}