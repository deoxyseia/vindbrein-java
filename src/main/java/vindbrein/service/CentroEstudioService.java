package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.CentroEstudio;

public interface CentroEstudioService {
	
	public void addCentroEstudio(CentroEstudio centroEstudio);

	public void updateCentroEstudio(CentroEstudio centroEstudio);

	public void deleteCentroEstudio(CentroEstudio centroEstudio);

	public CentroEstudio getCentroEstudioById(int id);

	public ArrayList<CentroEstudio> getCentrosEstudio();
}
