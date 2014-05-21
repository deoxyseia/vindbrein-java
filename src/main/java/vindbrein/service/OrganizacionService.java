package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Organizacion;

public interface OrganizacionService {
	
	public void addOrganizacion(Organizacion organizacion);

	public void updateOrganizacion(Organizacion organizacion);

	public void deleteOrganizacion(Organizacion organizacion);

	public Organizacion getOrganizacionById(int id);

	public ArrayList<Organizacion> getOrganizaciones();
}
