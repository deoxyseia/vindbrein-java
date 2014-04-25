package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the match_result database table.
 * 
 */
@Embeddable
public class MatchResultPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPulaId;
	private int fkPostId;

	public MatchResultPK() {
	}

	@Column(name="fk_pula_id", insertable=false, updatable=false)
	public int getFkPulaId() {
		return this.fkPulaId;
	}
	public void setFkPulaId(int fkPulaId) {
		this.fkPulaId = fkPulaId;
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
		if (!(other instanceof MatchResultPK)) {
			return false;
		}
		MatchResultPK castOther = (MatchResultPK)other;
		return 
			(this.fkPulaId == castOther.fkPulaId)
			&& (this.fkPostId == castOther.fkPostId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPulaId;
		hash = hash * prime + this.fkPostId;
		
		return hash;
	}
}