package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Grado;

public interface GradoService {
	
	public void addGrado(Grado grado);

	public void updateGrado(Grado grado);

	public void deleteGrado(Grado grado);

	public Grado getGradoById(int id);

	public ArrayList<Grado> getGrados();
	

}
