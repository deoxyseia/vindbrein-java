package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_centro_estudio database table.
 * 
 */
@Embeddable
public class OfertaCentroEstudioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkCeesId;
	private int fkOflaId;

	public OfertaCentroEstudioPK() {
	}

	@Column(name="fk_cees_id", insertable=false, updatable=false)
	public int getFkCeesId() {
		return this.fkCeesId;
	}
	public void setFkCeesId(int fkCeesId) {
		this.fkCeesId = fkCeesId;
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
		if (!(other instanceof OfertaCentroEstudioPK)) {
			return false;
		}
		OfertaCentroEstudioPK castOther = (OfertaCentroEstudioPK)other;
		return 
			(this.fkCeesId == castOther.fkCeesId)
			&& (this.fkOflaId == castOther.fkOflaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkCeesId;
		hash = hash * prime + this.fkOflaId;
		
		return hash;
	}
}