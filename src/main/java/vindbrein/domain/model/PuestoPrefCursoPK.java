package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the puesto_pref_curso database table.
 * 
 */
@Embeddable
public class PuestoPrefCursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPrpuId;
	private int fkCursId;

	public PuestoPrefCursoPK() {
	}

	@Column(name="fk_prpu_id", insertable=false, updatable=false)
	public int getFkPrpuId() {
		return this.fkPrpuId;
	}
	public void setFkPrpuId(int fkPrpuId) {
		this.fkPrpuId = fkPrpuId;
	}

	@Column(name="fk_curs_id", insertable=false, updatable=false)
	public int getFkCursId() {
		return this.fkCursId;
	}
	public void setFkCursId(int fkCursId) {
		this.fkCursId = fkCursId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PuestoPrefCursoPK)) {
			return false;
		}
		PuestoPrefCursoPK castOther = (PuestoPrefCursoPK)other;
		return 
			(this.fkPrpuId == castOther.fkPrpuId)
			&& (this.fkCursId == castOther.fkCursId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPrpuId;
		hash = hash * prime + this.fkCursId;
		
		return hash;
	}
}