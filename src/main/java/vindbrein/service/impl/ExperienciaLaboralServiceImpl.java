package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ExperienciaLaboralDAO;
import vindbrein.dao.OrganizacionPuestoDAO;
import vindbrein.domain.model.ExperienciaLaboral;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.OrganizacionPuestoPK;
import vindbrein.service.ExperienciaLaboralService;

@Service
@Transactional(readOnly = true)
public class ExperienciaLaboralServiceImpl implements ExperienciaLaboralService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired	
	ExperienciaLaboralDAO experienciaLaboralDAO;
	
	@Autowired	
	OrganizacionPuestoDAO organizacionPuestoDAO;
		
	@Transactional(readOnly = false)
	public void addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		if (getOrganizacionPuestoDAO().getOrganizacionPuestoById(
				experienciaLaboral.getOrganizacionPuesto().getOrganizacion(),
				experienciaLaboral.getOrganizacionPuesto().getPuesto()) == null){
			
			OrganizacionPuesto organizacionPuesto = new OrganizacionPuesto();
			organizacionPuesto.setId(new OrganizacionPuestoPK());
			organizacionPuesto.setOrpuEstado("B"); //borrador 
			
			organizacionPuesto.getId().setFkOrgaId(experienciaLaboral.getOrganizacionPuesto().getOrganizacion().getOrgaId());
			organizacionPuesto.getId().setFkPuesId(experienciaLaboral.getOrganizacionPuesto().getPuesto().getPuesId());
			
			getOrganizacionPuestoDAO().addOrganizacionPuesto(organizacionPuesto);
			
			experienciaLaboral.setOrganizacionPuesto(organizacionPuesto);		
		}
			
		getExperienciaLaboralDAO().addExperienciaLaboral(experienciaLaboral);		
	}
	
	@Transactional(readOnly = false)
	public void updateExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaboralDAO().updateExperienciaLaboral(experienciaLaboral);		
	}
	
	@Transactional(readOnly = false)
	public void deleteExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaboralDAO().deleteExperienciaLaboral(experienciaLaboral);		
	}

	public ExperienciaLaboral getExperienciaLaboralById(int id) {
		return getExperienciaLaboralDAO().getExperienciaLaboralById(id);
	}

	public ArrayList<ExperienciaLaboral> getExperienciasLaborales() {
		return getExperienciaLaboralDAO().getExperienciasLaborales();
	}
	
	//getters and setters
	
	public ExperienciaLaboralDAO getExperienciaLaboralDAO() {
		return experienciaLaboralDAO;
	}

	public void setExperienciaLaboralDAO(ExperienciaLaboralDAO experienciaLaboralDAO) {
		this.experienciaLaboralDAO = experienciaLaboralDAO;
	}

	public OrganizacionPuestoDAO getOrganizacionPuestoDAO() {
		return organizacionPuestoDAO;
	}

	public void setOrganizacionPuestoDAO(OrganizacionPuestoDAO organizacionPuestoDAO) {
		this.organizacionPuestoDAO = organizacionPuestoDAO;
	}
}
