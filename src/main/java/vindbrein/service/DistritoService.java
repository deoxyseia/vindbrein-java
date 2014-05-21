package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Provincia;

public interface DistritoService {
	
	public void addDistrito(Distrito distrito);

	public void updateDistrito(Distrito distrito);

	public void deleteDistrito(Distrito distrito);

	public Distrito getDistritoById(int id);

	public ArrayList<Distrito> getDistritos();
	
	public ArrayList<Distrito> getDistritosByProvincia(Provincia provincia);

}
