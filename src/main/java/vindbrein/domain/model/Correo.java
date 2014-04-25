package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the correo database table.
 * 
 */
@Entity
@Table(name="correo")
@NamedQuery(name="Correo.findAll", query="SELECT c FROM Correo c")
public class Correo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int corrId;
	private String corrName;
	private Postulante postulante;

	public Correo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="corr_id")
	public int getCorrId() {
		return this.corrId;
	}

	public void setCorrId(int corrId) {
		this.corrId = corrId;
	}


	@Column(name="corr_name")
	public String getCorrName() {
		return this.corrName;
	}

	public void setCorrName(String corrName) {
		this.corrName = corrName;
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