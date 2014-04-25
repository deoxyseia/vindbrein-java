package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefono database table.
 * 
 */
@Entity
@Table(name="telefono")
@NamedQuery(name="Telefono.findAll", query="SELECT t FROM Telefono t")
public class Telefono implements Serializable {
	private static final long serialVersionUID = 1L;
	private int teleId;
	private String teleDescripcion;
	private String teleNum;
	private Postulante postulante;

	public Telefono() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tele_id")
	public int getTeleId() {
		return this.teleId;
	}

	public void setTeleId(int teleId) {
		this.teleId = teleId;
	}


	@Column(name="tele_descripcion")
	public String getTeleDescripcion() {
		return this.teleDescripcion;
	}

	public void setTeleDescripcion(String teleDescripcion) {
		this.teleDescripcion = teleDescripcion;
	}


	@Column(name="tele_num")
	public String getTeleNum() {
		return this.teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
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

}