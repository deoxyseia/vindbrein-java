package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.DimensionOrganizacion;

public interface DimensionOrganizacionService {
	
	public void addDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public void updateDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public void deleteDimensionOrganizacion(DimensionOrganizacion DimensionOrganizacion);

	public DimensionOrganizacion getDimensionOrganizacionById(int id);

	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion();

}
