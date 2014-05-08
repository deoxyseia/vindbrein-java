package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the puesto_pref_estudio database table.
 * 
 */
@Embeddable
public class PuestoPrefEstudioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPrpuId;
	private int fkEsgeId;

	public PuestoPrefEstudioPK() {
	}

	@Column(name="fk_prpu_id", insertable=false, updatable=false)
	public int getFkPrpuId() {
		return this.fkPrpuId;
	}
	public void setFkPrpuId(int fkPrpuId) {
		this.fkPrpuId = fkPrpuId;
	}

	@Column(name="fk_esge_id", insertable=false, updatable=false)
	public int getFkEsgeId() {
		return this.fkEsgeId;
	}
	public void setFkEsgeId(int fkEsgeId) {
		this.fkEsgeId = fkEsgeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PuestoPrefEstudioPK)) {
			return false;
		}
		PuestoPrefEstudioPK castOther = (PuestoPrefEstudioPK)other;
		return 
			(this.fkPrpuId == castOther.fkPrpuId)
			&& (this.fkEsgeId == castOther.fkEsgeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPrpuId;
		hash = hash * prime + this.fkEsgeId;
		
		return hash;
	}
}