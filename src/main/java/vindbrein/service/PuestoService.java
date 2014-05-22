package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.NivelPuesto;
import vindbrein.domain.model.Puesto;


public interface PuestoService {
	
	public void addPuesto(Puesto puesto);

	public void updatePuesto(Puesto puesto);

	public void deletePuesto(Puesto puesto);

	public Puesto getPuestoById(int id);

	public ArrayList<Puesto> getPuestos();
	
	public void addNivelPuesto(NivelPuesto nivelPuesto);

	public void updateNivelPuesto(NivelPuesto nivelPuesto);

	public void deleteNivelPuesto(NivelPuesto nivelPuesto);

	public NivelPuesto getNivelPuestoById(int id);

	public ArrayList<NivelPuesto> getNivelesPuesto();
}
