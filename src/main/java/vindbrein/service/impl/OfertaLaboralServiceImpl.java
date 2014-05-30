package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.OfertaBeneficioDAO;
import vindbrein.dao.OfertaLaboralDAO;
import vindbrein.dao.OrganizacionPuestoDAO;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;
import vindbrein.service.OfertaLaboralService;

@Service
@Transactional(readOnly = true)
public class OfertaLaboralServiceImpl implements OfertaLaboralService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	OfertaLaboralDAO ofertaLaboralDAO;
	
	@Autowired	
	OrganizacionPuestoDAO organizacionPuestoDAO;
	
	@Autowired	
	OfertaBeneficioDAO ofertaBeneficioDAO;
	
	@Transactional(readOnly = false)
	public void addOfertaLaboral(OfertaLaboral ofertaLaboral) {	
				
		if(ofertaLaboral.getDistrito().getDistId() == 0){
			ofertaLaboral.setDistrito(null);
		}
		
		if(ofertaLaboral.getTipoHorario().getTihoId() == 0){
			ofertaLaboral.setTipoHorario(null);
		}
		
		if(ofertaLaboral.getEstadoCivil().getEsciId() == 0){
			ofertaLaboral.setEstadoCivil(null);
		}
		
		ofertaLaboral.setOrganizacionPuesto(getOrganizacionPuestoDAO().getOrganizacionPuestoById(
				ofertaLaboral.getOrganizacionPuesto()
					.getOrganizacion(),
				ofertaLaboral.getOrganizacionPuesto()
					.getPuesto()));
		
		getOfertaLaboralDAO().addOfertaLaboral(ofertaLaboral);
		
		
		
		for (OfertaBeneficio ofertaBeneficio : ofertaLaboral.getOfertaBeneficios()) {
			ofertaBeneficio.getId().setFkBeneId(ofertaBeneficio.getBeneficio().getBeneId());
			ofertaBeneficio.getId().setFkOflaId(ofertaBeneficio.getOfertaLaboral().getOflaId());
			
			getOfertaBeneficioDAO().addOfertaBeneficio(ofertaBeneficio);
		}				
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

	public OrganizacionPuestoDAO getOrganizacionPuestoDAO() {
		return organizacionPuestoDAO;
	}

	public void setOrganizacionPuestoDAO(OrganizacionPuestoDAO organizacionPuestoDAO) {
		this.organizacionPuestoDAO = organizacionPuestoDAO;
	}

	public OfertaBeneficioDAO getOfertaBeneficioDAO() {
		return ofertaBeneficioDAO;
	}

	public void setOfertaBeneficioDAO(OfertaBeneficioDAO ofertaBeneficioDAO) {
		this.ofertaBeneficioDAO = ofertaBeneficioDAO;
	}	
}
