package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.EstudioGenerico;

public interface EstudioGenericoService {
	
	public void addEstudioGenerico(EstudioGenerico estudioGenerico);

	public void updateEstudioGenerico(EstudioGenerico estudioGenerico);

	public void deleteEstudioGenerico(EstudioGenerico estudioGenerico);

	public EstudioGenerico getEstudioGenericoById(int id);

	public ArrayList<EstudioGenerico> getEstudiosGenericos();
}
