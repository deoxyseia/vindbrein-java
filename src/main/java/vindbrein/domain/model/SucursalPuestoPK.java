package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sucursal_puesto database table.
 * 
 */
@Embeddable
public class SucursalPuestoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int fkSucuId;
	private int fkPuesId;

	public SucursalPuestoPK() {
	}

	@Column(name="fk_sucu_id", insertable=false, updatable=false)
	public int getFkSucuId() {
		return this.fkSucuId;
	}
	public void setFkSucuId(int fkSucuId) {
		this.fkSucuId = fkSucuId;
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
		if (!(other instanceof SucursalPuestoPK)) {
			return false;
		}
		SucursalPuestoPK castOther = (SucursalPuestoPK)other;
		return 
			(this.fkSucuId == castOther.fkSucuId)
			&& (this.fkPuesId == castOther.fkPuesId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkSucuId;
		hash = hash * prime + this.fkPuesId;
		
		return hash;
	}
}