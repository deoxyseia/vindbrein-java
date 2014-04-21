package vindbrein.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="Usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int usuaId;
	private String usuaEstado;
	private String usuaPassword;
	private String usuaUser;

	public Usuario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idUsuario")
	public int getUsuaId() {
		return this.usuaId;
	}

	public void setUsuaId(int usuaId) {
		this.usuaId = usuaId;
	}


	@Column(name="flag_activo")
	public String getUsuaEstado() {
		return this.usuaEstado;
	}

	public void setUsuaEstado(String usuaEstado) {
		this.usuaEstado = usuaEstado;
	}


	@Column(name="user_password")
	public String getUsuaPassword() {
		return this.usuaPassword;
	}

	public void setUsuaPassword(String usuaPassword) {
		this.usuaPassword = usuaPassword;
	}


	@Column(name="user_name")
	public String getUsuaUser() {
		return this.usuaUser;
	}

	public void setUsuaUser(String usuaUser) {
		this.usuaUser = usuaUser;
	}
}