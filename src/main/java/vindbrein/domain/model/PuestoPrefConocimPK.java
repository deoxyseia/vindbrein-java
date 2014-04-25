package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the puesto_pref_conocim database table.
 * 
 */
@Embeddable
public class PuestoPrefConocimPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPrpuId;
	private int fkConoId;

	public PuestoPrefConocimPK() {
	}

	@Column(name="fk_prpu_id", insertable=false, updatable=false)
	public int getFkPrpuId() {
		return this.fkPrpuId;
	}
	public void setFkPrpuId(int fkPrpuId) {
		this.fkPrpuId = fkPrpuId;
	}

	@Column(name="fk_cono_id", insertable=false, updatable=false)
	public int getFkConoId() {
		return this.fkConoId;
	}
	public void setFkConoId(int fkConoId) {
		this.fkConoId = fkConoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PuestoPrefConocimPK)) {
			return false;
		}
		PuestoPrefConocimPK castOther = (PuestoPrefConocimPK)other;
		return 
			(this.fkPrpuId == castOther.fkPrpuId)
			&& (this.fkConoId == castOther.fkConoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPrpuId;
		hash = hash * prime + this.fkConoId;
		
		return hash;
	}
}