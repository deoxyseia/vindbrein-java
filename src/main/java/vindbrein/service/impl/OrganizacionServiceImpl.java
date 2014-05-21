package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.OrganizacionDAO;
import vindbrein.domain.model.Organizacion;
import vindbrein.service.OrganizacionService;

@Service
@Transactional(readOnly = true)
public class OrganizacionServiceImpl implements OrganizacionService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	OrganizacionDAO organizacionDAO;
	
	@Transactional(readOnly = false)
	public void addOrganizacion(Organizacion organizacion) {
		getOrganizacionDAO().addOrganizacion(organizacion);		
	}
	
	@Transactional(readOnly = false)
	public void updateOrganizacion(Organizacion organizacion) {
		getOrganizacionDAO().updateOrganizacion(organizacion);		
	}
	
	@Transactional(readOnly = false)
	public void deleteOrganizacion(Organizacion organizacion) {
		getOrganizacionDAO().deleteOrganizacion(organizacion);		
	}

	public Organizacion getOrganizacionById(int id) {
		return getOrganizacionDAO().getOrganizacionById(id);
	}

	public ArrayList<Organizacion> getOrganizaciones() {
		return getOrganizacionDAO().getOrganizaciones();
	}	
	
	//getters and setters
	
	public OrganizacionDAO getOrganizacionDAO() {
		return organizacionDAO;
	}

	public void setOrganizacionDAO(OrganizacionDAO organizacionDAO) {
		this.organizacionDAO = organizacionDAO;
	}	
}
