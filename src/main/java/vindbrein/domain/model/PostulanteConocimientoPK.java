package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the postulante_conocimiento database table.
 * 
 */
@Embeddable
public class PostulanteConocimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkPostId;
	private int fkConoId;
	private int fkNicoId;

	public PostulanteConocimientoPK() {
	}

	@Column(name="fk_post_id", insertable=false, updatable=false)
	public int getFkPostId() {
		return this.fkPostId;
	}
	public void setFkPostId(int fkPostId) {
		this.fkPostId = fkPostId;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostulanteConocimientoPK)) {
			return false;
		}
		PostulanteConocimientoPK castOther = (PostulanteConocimientoPK)other;
		return 
			(this.fkPostId == castOther.fkPostId)
			&& (this.fkConoId == castOther.fkConoId)
			&& (this.fkNicoId == castOther.fkNicoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkPostId;
		hash = hash * prime + this.fkConoId;
		hash = hash * prime + this.fkNicoId;
		
		return hash;
	}
}