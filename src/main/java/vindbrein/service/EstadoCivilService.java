package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.EstadoCivil;

public interface EstadoCivilService {
	
	public void addestadoCivil(EstadoCivil estadoCivil);

	public void updateestadoCivil(EstadoCivil estadoCivil);

	public void deleteestadoCivil(EstadoCivil estadoCivil);

	public EstadoCivil getEstadoCivilById(int id);

	public ArrayList<EstadoCivil> getEstadosCiviles();	
}
