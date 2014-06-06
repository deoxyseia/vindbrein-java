package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.OfertaBeneficioDAO;
import vindbrein.dao.OfertaCentroEstudioDAO;
import vindbrein.dao.OfertaConocimientoDAO;
import vindbrein.dao.OfertaEstudioDAO;
import vindbrein.dao.OfertaIdiomaDAO;
import vindbrein.dao.OfertaLaboralDAO;
import vindbrein.dao.OrganizacionPuestoDAO;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaCentroEstudio;
import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.OfertaEstudio;
import vindbrein.domain.model.OfertaIdioma;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;
import vindbrein.service.OfertaLaboralService;

@Service
@Transactional(readOnly = true)
public class OfertaLaboralServiceImpl implements OfertaLaboralService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired	
	private OfertaLaboralDAO ofertaLaboralDAO;
	
	@Autowired	
	private OrganizacionPuestoDAO organizacionPuestoDAO;
	
	@Autowired	
	private OfertaBeneficioDAO ofertaBeneficioDAO;
	
	@Autowired	
	private OfertaIdiomaDAO ofertaIdiomaDAO;
	
	@Autowired	
	private OfertaConocimientoDAO ofertaConocimientoDAO;
	
	@Autowired	
	private OfertaEstudioDAO ofertaEstudioDAO;
	
	@Autowired	
	private OfertaCentroEstudioDAO ofertaCentroEstudioDAO;
	
	@Transactional(readOnly = false)
	public void addOfertaLaboral(OfertaLaboral ofertaLaboral) {	
					
	}
	
	@Transactional(readOnly = false)
	public void updateOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ofertaLaboralDAO.updateOfertaLaboral(ofertaLaboral);		
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdateOfertaLaboral(OfertaLaboral ofertaLaboral) {
		// obtiene el puesto de la organizacion y lo inserta en la oferta

		ofertaLaboral.getOrganizacionPuesto().getId().setFkPuesId(ofertaLaboral.getOrganizacionPuesto().getPuesto().getPuesId());
		ofertaLaboral.getOrganizacionPuesto().getId().setFkOrgaId(ofertaLaboral.getOrganizacionPuesto().getOrganizacion().getOrgaId());

		// guarda la oferta laboral
		ofertaLaboralDAO.saveOrUpdateOfertaLaboral(ofertaLaboral);	
		
		//beneficio 
		for (OfertaBeneficio ofertaBeneficio : ofertaBeneficioDAO.getOfertaBeneficiosByOfertaLaboral(ofertaLaboral)) {
			ofertaBeneficioDAO.deleteOfertaBeneficio(ofertaBeneficio);
		}

		for (OfertaBeneficio ofertaBeneficio : ofertaLaboral.getOfertaBeneficios()) {
			ofertaBeneficio.getId().setFkBeneId(ofertaBeneficio.getBeneficio().getBeneId());
			ofertaBeneficio.getId().setFkOflaId(ofertaBeneficio.getOfertaLaboral().getOflaId());

			ofertaBeneficioDAO.addOfertaBeneficio(ofertaBeneficio);
		}
		
		//idioma
		for (OfertaIdioma ofertaIdioma : ofertaIdiomaDAO.getOfertaIdiomasByOfertaLaboral(ofertaLaboral)) {
			ofertaIdiomaDAO.deleteOfertaIdioma(ofertaIdioma);
		}
		
		for (OfertaIdioma ofertaIdioma : ofertaLaboral.getOfertaIdiomas()) {
			ofertaIdioma.getId().setFkIdioId(ofertaIdioma.getIdioma().getIdioId());
			ofertaIdioma.getId().setFkNiidId(ofertaIdioma.getNivelIdioma().getNiidId());
			ofertaIdioma.getId().setFkOflaId(ofertaIdioma.getOfertaLaboral().getOflaId());
			
			ofertaIdiomaDAO.addOfertaIdioma(ofertaIdioma);
		}
		
		//conocimiento
		for (OfertaConocimiento ofertaConocimiento : ofertaConocimientoDAO.getOfertaConocimientosByOfertaLaboral(ofertaLaboral)) {
			ofertaConocimientoDAO.deleteOfertaConocimiento(ofertaConocimiento);
		}
		
		for (OfertaConocimiento ofertaConocimiento : ofertaLaboral.getOfertaConocimientos()) {
			ofertaConocimiento.getId().setFkConoId(ofertaConocimiento.getConocimiento().getConoId());
			ofertaConocimiento.getId().setFkNicoId(ofertaConocimiento.getNivelConocimiento().getNicoId());
			ofertaConocimiento.getId().setFkOflaId(ofertaConocimiento.getOfertaLaboral().getOflaId());
			
			ofertaConocimientoDAO.addOfertaConocimiento(ofertaConocimiento);
		}
		
		//estudio genérico
		for (OfertaEstudio ofertaEstudio : ofertaEstudioDAO.getOfertaEstudiosByOfertaLaboral(ofertaLaboral)) {
			ofertaEstudioDAO.deleteOfertaEstudio(ofertaEstudio);
		}
		
		for (OfertaEstudio ofertaEstudio : ofertaLaboral.getOfertaEstudios()) {
			ofertaEstudio.getId().setFkEsgeId(ofertaEstudio.getEstudioGenerico().getEsgeId());
			ofertaEstudio.getId().setFkOflaId(ofertaEstudio.getOfertaLaboral().getOflaId());
			
			ofertaEstudioDAO.addOfertaEstudio(ofertaEstudio);
		}
		
		//centro de estudio
		for (OfertaCentroEstudio ofertaCentroEstudio : ofertaCentroEstudioDAO.getOfertaCentroEstudiosByOfertaLaboral(ofertaLaboral)) {
			ofertaCentroEstudioDAO.deleteOfertaCentroEstudio(ofertaCentroEstudio);
		}
		
		for (OfertaCentroEstudio ofertaCentroEstudio : ofertaLaboral.getOfertaCentroEstudios()) {
			ofertaCentroEstudio.getId().setFkCeesId(ofertaCentroEstudio.getCentroEstudio().getCeesId());
			ofertaCentroEstudio.getId().setFkOflaId(ofertaCentroEstudio.getOfertaLaboral().getOflaId());;
			
			ofertaCentroEstudioDAO.addOfertaCentroEstudio(ofertaCentroEstudio);
		}
	}
	
	@Transactional(readOnly = false)
	public void deleteOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ofertaLaboralDAO.deleteOfertaLaboral(ofertaLaboral);		
	}

	public OfertaLaboral getOfertaLaboralById(int id) {
		return ofertaLaboralDAO.getOfertaLaboralById(id);
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertaLaboralDAO.getOfertasLaborales();
	}	
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesByOrganizacion(Organizacion organizacion){
		return ofertaLaboralDAO.getOfertasLaboralesByOrganizacion(organizacion);
	}	
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesCompletas(){
		ArrayList<OfertaLaboral> ofertas = getOfertasLaborales();
		
		for (int i = 0; i < ofertas.size(); i++) {
			ofertas.get(i).setOfertaBeneficios(ofertaBeneficioDAO.getOfertaBeneficiosByOfertaLaboral(ofertas.get(i)));
			ofertas.get(i).setOfertaCentroEstudios(ofertaCentroEstudioDAO.getOfertaCentroEstudiosByOfertaLaboral(ofertas.get(i)));
			ofertas.get(i).setOfertaConocimientos(ofertaConocimientoDAO.getOfertaConocimientosByOfertaLaboral(ofertas.get(i)));
			ofertas.get(i).setOfertaEstudios(ofertaEstudioDAO.getOfertaEstudiosByOfertaLaboral(ofertas.get(i)));
			ofertas.get(i).setOfertaIdiomas(ofertaIdiomaDAO.getOfertaIdiomasByOfertaLaboral(ofertas.get(i)));
		}
		
		return ofertas;
	}
	
	public OfertaLaboral getOfertaLaboralCompleta(OfertaLaboral ofertaLaboral){
		ofertaLaboral.setOfertaBeneficios(ofertaBeneficioDAO.getOfertaBeneficiosByOfertaLaboral(ofertaLaboral));
		ofertaLaboral.setOfertaCentroEstudios(ofertaCentroEstudioDAO.getOfertaCentroEstudiosByOfertaLaboral(ofertaLaboral));
		ofertaLaboral.setOfertaConocimientos(ofertaConocimientoDAO.getOfertaConocimientosByOfertaLaboral(ofertaLaboral));
		ofertaLaboral.setOfertaEstudios(ofertaEstudioDAO.getOfertaEstudiosByOfertaLaboral(ofertaLaboral));
		ofertaLaboral.setOfertaIdiomas(ofertaIdiomaDAO.getOfertaIdiomasByOfertaLaboral(ofertaLaboral));
		
		return ofertaLaboral;
	}
}
