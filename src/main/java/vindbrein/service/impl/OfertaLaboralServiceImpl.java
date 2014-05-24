package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.OfertaLaboralDAO;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.Provincia;
import vindbrein.service.OfertaLaboralService;

@Service
@Transactional(readOnly = true)
public class OfertaLaboralServiceImpl implements OfertaLaboralService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	OfertaLaboralDAO ofertaLaboralDAO;
	
	@Transactional(readOnly = false)
	public void addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaboralDAO().addOfertaLaboral(ofertaLaboral);		
	}
	
	@Transactional(readOnly = false)
	public void updateOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaboralDAO().updateOfertaLaboral(ofertaLaboral);		
	}
	
	@Transactional(readOnly = false)
	public void deleteOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getOfertaLaboralDAO().deleteOfertaLaboral(ofertaLaboral);		
	}

	public OfertaLaboral getOfertaLaboralById(int id) {
		return getOfertaLaboralDAO().getOfertaLaboralById(id);
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return getOfertaLaboralDAO().getOfertasLaborales();
	}	
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesByOrganizacion(Organizacion organizacion){
		return getOfertaLaboralDAO().getOfertasLaboralesByOrganizacion(organizacion);
	}
	
	//getters and setters
	
	public OfertaLaboralDAO getOfertaLaboralDAO() {
		return ofertaLaboralDAO;
	}

	public void setOfertaLaboralDAO(OfertaLaboralDAO ofertaLaboralDAO) {
		this.ofertaLaboralDAO = ofertaLaboralDAO;
	}	
}
