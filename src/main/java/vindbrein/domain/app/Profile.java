package vindbrein.domain.app;

import java.math.BigDecimal;

public class Profile {
	private int id;
	private BigDecimal[] vecSelfDescription;
	private BigDecimal[] vecPreference;
	private BigDecimal[] vecHistorical;
	private int recNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal[] getVecSelfDescription() {
		return vecSelfDescription;
	}
	public void setVecSelfDescription(BigDecimal[] vecSelfDescription) {
		this.vecSelfDescription = vecSelfDescription;
	}
	public BigDecimal[] getVecPreference() {
		return vecPreference;
	}
	public void setVecPreference(BigDecimal[] vecPreference) {
		this.vecPreference = vecPreference;
	}
	public BigDecimal[] getVecHistorical() {
		return vecHistorical;
	}
	public void setVecHistorical(BigDecimal[] vecHistorical) {
		this.vecHistorical = vecHistorical;
	}
	public int getRecNumber() {
		return recNumber;
	}
	public void setRecNumber(int recNumber) {
		this.recNumber = recNumber;
	}	
}
