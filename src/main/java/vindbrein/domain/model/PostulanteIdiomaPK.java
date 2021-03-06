package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the postulante_idioma database table.
 * 
 */
@Embeddable
public class PostulanteIdiomaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkIdioId;
	private int fkPostId;
	private int fkNiidId;

	public PostulanteIdiomaPK() {
	}

	@Column(name="fk_idio_id", insertable=false, updatable=false)
	public int getFkIdioId() {
		return this.fkIdioId;
	}
	public void setFkIdioId(int fkIdioId) {
		this.fkIdioId = fkIdioId;
	}

	@Column(name="fk_post_id", insertable=false, updatable=false)
	public int getFkPostId() {
		return this.fkPostId;
	}
	public void setFkPostId(int fkPostId) {
		this.fkPostId = fkPostId;
	}

	@Column(name="fk_niid_id", insertable=false, updatable=false)
	public int getFkNiidId() {
		return this.fkNiidId;
	}
	public void setFkNiidId(int fkNiidId) {
		this.fkNiidId = fkNiidId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostulanteIdiomaPK)) {
			return false;
		}
		PostulanteIdiomaPK castOther = (PostulanteIdiomaPK)other;
		return 
			(this.fkIdioId == castOther.fkIdioId)
			&& (this.fkPostId == castOther.fkPostId)
			&& (this.fkNiidId == castOther.fkNiidId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkIdioId;
		hash = hash * prime + this.fkPostId;
		hash = hash * prime + this.fkNiidId;
		
		return hash;
	}
}