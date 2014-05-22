package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.DimensionOrganizacion;
import vindbrein.domain.model.Organizacion;

public interface OrganizacionService {
	
	public void addOrganizacion(Organizacion organizacion);

	public void updateOrganizacion(Organizacion organizacion);

	public void deleteOrganizacion(Organizacion organizacion);

	public Organizacion getOrganizacionById(int id);

	public ArrayList<Organizacion> getOrganizaciones();
	
	public void addDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public void updateDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public void deleteDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public DimensionOrganizacion getDimensionOrganizacionById(int id);

	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion();
}
