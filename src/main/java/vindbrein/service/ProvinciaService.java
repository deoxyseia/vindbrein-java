package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Provincia;

public interface ProvinciaService {
	
	public void addProvincia(Provincia Provincia);

	public void updateProvincia(Provincia Provincia);

	public void deleteProvincia(Provincia Provincia);

	public Provincia getProvinciaById(int id);

	public ArrayList<Provincia> getProvincias();
	
	public ArrayList<Provincia> getProvinciasByDepartamento(Departamento departamento);

}
