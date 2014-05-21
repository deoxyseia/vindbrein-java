package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the organizacion_puesto database table.
 * 
 */
@Embeddable
public class OrganizacionPuestoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkOrgaId;
	private int fkPuesId;

	public OrganizacionPuestoPK() {
	}

	@Column(name="fk_orga_id", insertable=false, updatable=false)
	public int getFkOrgaId() {
		return this.fkOrgaId;
	}
	public void setFkOrgaId(int fkOrgaId) {
		this.fkOrgaId = fkOrgaId;
	}

	@Column(name="fk_pues_id", insertable=false, updatable=false)
	public int getFkPuesId() {
		return this.fkPuesId;
	}
	public void setFkPuesId(int fkPuesId) {
		this.fkPuesId = fkPuesId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrganizacionPuestoPK)) {
			return false;
		}
		OrganizacionPuestoPK castOther = (OrganizacionPuestoPK)other;
		return 
			(this.fkOrgaId == castOther.fkOrgaId)
			&& (this.fkPuesId == castOther.fkPuesId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkOrgaId;
		hash = hash * prime + this.fkPuesId;
		
		return hash;
	}
}