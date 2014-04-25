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
	private String usuaFlagActivo;
	private String usuaNombre;
	private Postulante postulante;
	private RespRrhh respRrhh;

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


	@Column(name="usua_flag_activo")
	public String getUsuaFlagActivo() {
		return this.usuaFlagActivo;
	}

	public void setUsuaFlagActivo(String usuaFlagActivo) {
		this.usuaFlagActivo = usuaFlagActivo;
	}


	@Column(name="usua_nombre")
	public String getUsuaNombre() {
		return this.usuaNombre;
	}

	public void setUsuaNombre(String usuaNombre) {
		this.usuaNombre = usuaNombre;
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


	//bi-directional many-to-one association to RespRrhh
	@ManyToOne
	@JoinColumn(name="fk_rerr_id")
	public RespRrhh getRespRrhh() {
		return this.respRrhh;
	}

	public void setRespRrhh(RespRrhh respRrhh) {
		this.respRrhh = respRrhh;
	}

}