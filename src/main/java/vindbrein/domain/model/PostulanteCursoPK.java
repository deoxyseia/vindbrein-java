package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the postulante_curso database table.
 * 
 */
@Embeddable
public class PostulanteCursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkCursId;
	private int fkPostId;

	public PostulanteCursoPK() {
	}

	@Column(name="fk_curs_id", insertable=false, updatable=false)
	public int getFkCursId() {
		return this.fkCursId;
	}
	public void setFkCursId(int fkCursId) {
		this.fkCursId = fkCursId;
	}

	@Column(name="fk_post_id", insertable=false, updatable=false)
	public int getFkPostId() {
		return this.fkPostId;
	}
	public void setFkPostId(int fkPostId) {
		this.fkPostId = fkPostId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostulanteCursoPK)) {
			return false;
		}
		PostulanteCursoPK castOther = (PostulanteCursoPK)other;
		return 
			(this.fkCursId == castOther.fkCursId)
			&& (this.fkPostId == castOther.fkPostId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkCursId;
		hash = hash * prime + this.fkPostId;
		
		return hash;
	}
}