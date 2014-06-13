package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;

public interface OfertaLaboralService {
	
	public void addOfertaLaboral(OfertaLaboral ofertaLaboral);

	public void updateOfertaLaboral(OfertaLaboral ofertaLaboral);
	
	public void saveOrUpdateOfertaLaboral(OfertaLaboral ofertaLaboral);

	public void deleteOfertaLaboral(OfertaLaboral ofertaLaboral);

	public OfertaLaboral getOfertaLaboralById(int id);

	public ArrayList<OfertaLaboral> getOfertasLaborales();
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesByOrganizacion(Organizacion organizacion);
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesCompletas();
	
	public OfertaLaboral getOfertaLaboralCompleta(OfertaLaboral ofertaLaboral);
}
