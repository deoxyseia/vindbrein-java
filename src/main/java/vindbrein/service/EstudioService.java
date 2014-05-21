package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Estudio;

public interface EstudioService {
	
	public void addEstudio(Estudio estudio);

	public void updateEstudio(Estudio estudio);

	public void deleteEstudio(Estudio estudio);

	public Estudio getEstudioById(int id);

	public ArrayList<Estudio> getEstudios();	
}
