package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oferta_idioma database table.
 * 
 */
@Embeddable
public class OfertaIdiomaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkOflaId;
	private int fkIdioId;
	private int fkNiidId;

	public OfertaIdiomaPK() {
	}

	@Column(name="fk_ofla_id", insertable=false, updatable=false)
	public int getFkOflaId() {
		return this.fkOflaId;
	}
	public void setFkOflaId(int fkOflaId) {
		this.fkOflaId = fkOflaId;
	}

	@Column(name="fk_idio_id", insertable=false, updatable=false)
	public int getFkIdioId() {
		return this.fkIdioId;
	}
	public void setFkIdioId(int fkIdioId) {
		this.fkIdioId = fkIdioId;
	}

	@Column(name="fk_niid_id", insertable=false, updatable=false)
	public int getFkNiidId() {
		return this.fkNiidId;
	}
	public void setFkNiidId(int fkNiidId) {
		this.fkNiidId = fkNiidId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OfertaIdiomaPK)) {
			return false;
		}
		OfertaIdiomaPK castOther = (OfertaIdiomaPK)other;
		return 
			(this.fkOflaId == castOther.fkOflaId)
			&& (this.fkIdioId == castOther.fkIdioId)
			&& (this.fkNiidId == castOther.fkNiidId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkOflaId;
		hash = hash * prime + this.fkIdioId;
		hash = hash * prime + this.fkNiidId;
		
		return hash;
	}
}