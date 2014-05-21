package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_curso database table.
 * 
 */
@Embeddable
public class OfertaCursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkOflaId;
	private int fkCursId;

	public OfertaCursoPK() {
	}

	@Column(name="fk_ofla_id", insertable=false, updatable=false)
	public int getFkOflaId() {
		return this.fkOflaId;
	}
	public void setFkOflaId(int fkOflaId) {
		this.fkOflaId = fkOflaId;
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
		if (!(other instanceof OfertaCursoPK)) {
			return false;
		}
		OfertaCursoPK castOther = (OfertaCursoPK)other;
		return 
			(this.fkOflaId == castOther.fkOflaId)
			&& (this.fkCursId == castOther.fkCursId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkOflaId;
		hash = hash * prime + this.fkCursId;
		
		return hash;
	}
}