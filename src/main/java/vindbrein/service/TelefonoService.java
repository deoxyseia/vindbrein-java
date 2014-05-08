package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Telefono;

public interface TelefonoService {
	
	public void addTelefono(Telefono Telefono);

	public void updateTelefono(Telefono Telefono);

	public void deleteTelefono(Telefono Telefono);

	public Telefono getTelefonoById(int id);

	public ArrayList<Telefono> getTelefonos();

}
