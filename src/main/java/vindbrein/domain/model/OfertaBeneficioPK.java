package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_beneficio database table.
 * 
 */
@Embeddable
public class OfertaBeneficioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkBeneId;
	private int fkOflaId;

	public OfertaBeneficioPK() {
	}

	@Column(name="fk_bene_id", insertable=false, updatable=false)
	public int getFkBeneId() {
		return this.fkBeneId;
	}
	public void setFkBeneId(int fkBeneId) {
		this.fkBeneId = fkBeneId;
	}

	@Column(name="fk_ofla_id", insertable=false, updatable=false)
	public int getFkOflaId() {
		return this.fkOflaId;
	}
	public void setFkOflaId(int fkOflaId) {
		this.fkOflaId = fkOflaId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OfertaBeneficioPK)) {
			return false;
		}
		OfertaBeneficioPK castOther = (OfertaBeneficioPK)other;
		return 
			(this.fkBeneId == castOther.fkBeneId)
			&& (this.fkOflaId == castOther.fkOflaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkBeneId;
		hash = hash * prime + this.fkOflaId;
		
		return hash;
	}
}