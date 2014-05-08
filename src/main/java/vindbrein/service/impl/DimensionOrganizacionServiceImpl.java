package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.DimensionOrganizacionDAO;
import vindbrein.domain.model.DimensionOrganizacion;
import vindbrein.service.DimensionOrganizacionService;

@Service
@Transactional(readOnly = true)
public class DimensionOrganizacionServiceImpl implements DimensionOrganizacionService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired	
	DimensionOrganizacionDAO dimensionOrganizacionDAO;	
	
	@Transactional(readOnly = false)
	public void addDimensionOrganizacion(
			DimensionOrganizacion dimensionOrganizacion) {
		getDimensionOrganizacionDAO().addDimensionOrganizacion(dimensionOrganizacion);		
	}
	
	@Transactional(readOnly = false)
	public void updateDimensionOrganizacion(
			DimensionOrganizacion dimensionOrganizacion) {
		getDimensionOrganizacionDAO().updateDimensionOrganizacion(dimensionOrganizacion);		
	}
	
	@Transactional(readOnly = false)
	public void deleteDimensionOrganizacion(
			DimensionOrganizacion dimensionOrganizacion) {
		getDimensionOrganizacionDAO().deleteDimensionOrganizacion(dimensionOrganizacion);
		
	}
	
	public DimensionOrganizacion getDimensionOrganizacionById(int id) {
		return getDimensionOrganizacionDAO().getDimensionOrganizacionById(id);
	}
	
	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion() {
		return getDimensionOrganizacionDAO().getDimensionesOrganizacion();
	}	
	
	//getters and setters	
	
	public DimensionOrganizacionDAO getDimensionOrganizacionDAO() {
		return dimensionOrganizacionDAO;
	}
	public void setDimensionOrganizacionDAO(
			DimensionOrganizacionDAO dimensionOrganizacionDAO) {
		this.dimensionOrganizacionDAO = dimensionOrganizacionDAO;
	}	
}
