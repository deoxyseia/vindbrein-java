package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Provincia;

public interface DistritoService {
	
	public void addDistrito(Distrito Distrito);

	public void updateDistrito(Distrito Distrito);

	public void deleteDistrito(Distrito Distrito);

	public Distrito getDistritoById(int id);

	public ArrayList<Distrito> getDistritos();
	
	public ArrayList<Distrito> getDistritosByProvincia(Provincia provincia);

}
