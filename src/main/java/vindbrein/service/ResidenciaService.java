package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Residencia;

public interface ResidenciaService {
	
	public void addResidencia(Residencia Residencia);

	public void updateResidencia(Residencia Residencia);

	public void deleteResidencia(Residencia Residencia);

	public Residencia getResidenciaById(int id);

	public ArrayList<Residencia> getResidencias();

}
