package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_conocimiento database table.
 * 
 */
@Embeddable
public class OfertaConocimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkConoId;
	private int fkNicoId;
	private int fkOflaId;

	public OfertaConocimientoPK() {
	}

	@Column(name="fk_cono_id", insertable=false, updatable=false)
	public int getFkConoId() {
		return this.fkConoId;
	}
	public void setFkConoId(int fkConoId) {
		this.fkConoId = fkConoId;
	}

	@Column(name="fk_nico_id", insertable=false, updatable=false)
	public int getFkNicoId() {
		return this.fkNicoId;
	}
	public void setFkNicoId(int fkNicoId) {
		this.fkNicoId = fkNicoId;
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
		if (!(other instanceof OfertaConocimientoPK)) {
			return false;
		}
		OfertaConocimientoPK castOther = (OfertaConocimientoPK)other;
		return 
			(this.fkConoId == castOther.fkConoId)
			&& (this.fkNicoId == castOther.fkNicoId)
			&& (this.fkOflaId == castOther.fkOflaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkConoId;
		hash = hash * prime + this.fkNicoId;
		hash = hash * prime + this.fkOflaId;
		
		return hash;
	}
}