package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_estudio database table.
 * 
 */
@Embeddable
public class OfertaEstudioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkEsgeId;
	private int fkOflaId;

	public OfertaEstudioPK() {
	}

	@Column(name="fk_esge_id", insertable=false, updatable=false)
	public int getFkEsgeId() {
		return this.fkEsgeId;
	}
	public void setFkEsgeId(int fkEsgeId) {
		this.fkEsgeId = fkEsgeId;
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
		if (!(other instanceof OfertaEstudioPK)) {
			return false;
		}
		OfertaEstudioPK castOther = (OfertaEstudioPK)other;
		return 
			(this.fkEsgeId == castOther.fkEsgeId)
			&& (this.fkOflaId == castOther.fkOflaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkEsgeId;
		hash = hash * prime + this.fkOflaId;
		
		return hash;
	}
}