package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int usuaId;
	private String usuaContrasenia;
	private String usuaCorreo;
	private byte usuaFlagActivo;
	private Postulante postulante;
	private Reclutador reclutador;

	public Usuario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usua_id")
	public int getUsuaId() {
		return this.usuaId;
	}

	public void setUsuaId(int usuaId) {
		this.usuaId = usuaId;
	}


	@Column(name="usua_contrasenia")
	public String getUsuaContrasenia() {
		return this.usuaContrasenia;
	}

	public void setUsuaContrasenia(String usuaContrasenia) {
		this.usuaContrasenia = usuaContrasenia;
	}


	@Column(name="usua_correo")
	public String getUsuaCorreo() {
		return this.usuaCorreo;
	}

	public void setUsuaCorreo(String usuaCorreo) {
		this.usuaCorreo = usuaCorreo;
	}


	@Column(name="usua_flag_activo")
	public byte getUsuaFlagActivo() {
		return this.usuaFlagActivo;
	}

	public void setUsuaFlagActivo(byte usuaFlagActivo) {
		this.usuaFlagActivo = usuaFlagActivo;
	}


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id")
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}


	//bi-directional many-to-one association to Reclutador
	@ManyToOne
	@JoinColumn(name="fk_recl_id")
	public Reclutador getReclutador() {
		return this.reclutador;
	}

	public void setReclutador(Reclutador reclutador) {
		this.reclutador = reclutador;
	}

}