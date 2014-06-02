package vindbrein.domain.app;

import java.math.BigDecimal;

public class Result {
	private Profile profile;
	private BigDecimal score;
	
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}	
}
