package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the postulante_beneficio database table.
 * 
 */
@Embeddable
public class PostulanteBeneficioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkBeneId;
	private int fkPostId;

	public PostulanteBeneficioPK() {
	}

	@Column(name="fk_bene_id", insertable=false, updatable=false)
	public int getFkBeneId() {
		return this.fkBeneId;
	}
	public void setFkBeneId(int fkBeneId) {
		this.fkBeneId = fkBeneId;
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
		if (!(other instanceof PostulanteBeneficioPK)) {
			return false;
		}
		PostulanteBeneficioPK castOther = (PostulanteBeneficioPK)other;
		return 
			(this.fkBeneId == castOther.fkBeneId)
			&& (this.fkPostId == castOther.fkPostId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkBeneId;
		hash = hash * prime + this.fkPostId;
		
		return hash;
	}
}