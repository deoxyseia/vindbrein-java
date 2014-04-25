package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the puesto_pref_idioma database table.
 * 
 */
@Embeddable
public class PuestoPrefIdiomaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPrpuId;
	private int fkIdioId;

	public PuestoPrefIdiomaPK() {
	}

	@Column(name="fk_prpu_id", insertable=false, updatable=false)
	public int getFkPrpuId() {
		return this.fkPrpuId;
	}
	public void setFkPrpuId(int fkPrpuId) {
		this.fkPrpuId = fkPrpuId;
	}

	@Column(name="fk_idio_id", insertable=false, updatable=false)
	public int getFkIdioId() {
		return this.fkIdioId;
	}
	public void setFkIdioId(int fkIdioId) {
		this.fkIdioId = fkIdioId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PuestoPrefIdiomaPK)) {
			return false;
		}
		PuestoPrefIdiomaPK castOther = (PuestoPrefIdiomaPK)other;
		return 
			(this.fkPrpuId == castOther.fkPrpuId)
			&& (this.fkIdioId == castOther.fkIdioId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPrpuId;
		hash = hash * prime + this.fkIdioId;
		
		return hash;
	}
}