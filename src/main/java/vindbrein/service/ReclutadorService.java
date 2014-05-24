package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Reclutador;

public interface ReclutadorService {
	
	public void addReclutador(Reclutador reclutador);

	public void updateReclutador(Reclutador reclutador);

	public void deleteReclutador(Reclutador reclutador);

	public Reclutador getReclutadorById(int id);

	public ArrayList<Reclutador> getReclutadores();	
	
	public Reclutador getReclutadorByCorreo(String correo);
}
