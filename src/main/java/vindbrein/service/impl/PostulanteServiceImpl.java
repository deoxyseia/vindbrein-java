package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ActividadAcademicaDAO;
import vindbrein.dao.ExperienciaLaboralDAO;
import vindbrein.dao.PostulanteBeneficioDAO;
import vindbrein.dao.PostulanteConocimientoDAO;
import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.PostulanteIdiomaDAO;
import vindbrein.dao.ResidenciaDAO;
import vindbrein.dao.TelefonoDAO;
import vindbrein.dao.document.PostulantPreferenceDAO;
import vindbrein.dao.document.PostulantSelfDescriptionDAO;
import vindbrein.domain.model.Postulante;
import vindbrein.service.PostulanteService;

@Service
@Transactional(readOnly = true)
public class PostulanteServiceImpl implements PostulanteService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	PostulanteDAO postulanteDAO;
	
	@Autowired	
	TelefonoDAO telefonoDAO;
	
	@Autowired	
	PostulanteConocimientoDAO postulanteConocimientoDAO;
	
	@Autowired	
	ResidenciaDAO residenciaDAO;
	
	@Autowired	
	ActividadAcademicaDAO actividadAcademicaDAO;
	
	@Autowired	
	ExperienciaLaboralDAO experienciaLaboralDAO;
	
	@Autowired	
	PostulanteIdiomaDAO postulanteIdiomaDAO;
	
	@Autowired	
	PostulanteBeneficioDAO postulanteBeneficioDAO;
	
	@Autowired	
	PostulantSelfDescriptionDAO postulantSelfDescriptionDAO;
	
	@Autowired	
	PostulantPreferenceDAO postulantPreferenceDAO;
	
	
	
	@Transactional(readOnly = false)
	public void addPostulante(Postulante postulante) {
		getPostulanteDAO().addPostulante(postulante);		
	}
	
	@Transactional(readOnly = false)
	public void updatePostulante(Postulante postulante) {		
		getPostulanteDAO().updatePostulante(postulante);		
	}

	@Transactional(readOnly = false)
	public void deletePostulante(Postulante postulante) {
		getPostulanteDAO().deletePostulante(postulante);		
	}
	
	public Postulante getPostulanteById(int id) {
		return getPostulanteDAO().getPostulanteById(id);
	}

	public ArrayList<Postulante> getPostulantes() {
		return getPostulanteDAO().getPostulantes();
	}	
	
	public Postulante getPostulanteByCorreo(String correo){
		Postulante postulante = getPostulanteDAO().getPostulanteByCorreo(correo);
				
		return postulante;
	}
	
	public Postulante getPostulanteCompletoByPostulante(Postulante postulante){
		postulante = getPostulanteById(postulante.getPostId());
		postulante.setTelefonos(getTelefonoDAO().getTelefonosByPostulante(postulante));
		postulante.setPostulanteConocimientos(getPostulanteConocimientoDAO().getPostulanteConocimientoByPostulante(postulante));
		postulante.setResidencias(getResidenciaDAO().getResidenciasByPostulante(postulante));
		postulante.setActividadesAcademicas(getActividadAcademicaDAO().getActividadesAcademicasByPostulante(postulante));
		postulante.setExperienciasLaborales(getExperienciaLaboralDAO().getExperienciasLaboralesByPostulante(postulante));
		postulante.setPostulanteIdiomas(getPostulanteIdiomaDAO().getPostulanteIdiomasByPostulante(postulante));
		postulante.setPostulanteBeneficios(getPostulanteBeneficioDAO().getPostulanteBeneficioByPostulante(postulante));
		
		return postulante;
	}
	
	//getters and setters
	
	public PostulanteDAO getPostulanteDAO() {
		return postulanteDAO;
	}

	public void setPostulanteDAO(PostulanteDAO postulanteDAO) {
		this.postulanteDAO = postulanteDAO;
	}

	public TelefonoDAO getTelefonoDAO() {
		return telefonoDAO;
	}

	public void setTelefonoDAO(TelefonoDAO telefonoDAO) {
		this.telefonoDAO = telefonoDAO;
	}

	public PostulanteConocimientoDAO getPostulanteConocimientoDAO() {
		return postulanteConocimientoDAO;
	}

	public void setPostulanteConocimientoDAO(
			PostulanteConocimientoDAO postulanteConocimientoDAO) {
		this.postulanteConocimientoDAO = postulanteConocimientoDAO;
	}

	public ResidenciaDAO getResidenciaDAO() {
		return residenciaDAO;
	}

	public void setResidenciaDAO(ResidenciaDAO residenciaDAO) {
		this.residenciaDAO = residenciaDAO;
	}

	public ActividadAcademicaDAO getActividadAcademicaDAO() {
		return actividadAcademicaDAO;
	}

	public void setActividadAcademicaDAO(ActividadAcademicaDAO actividadAcademicaDAO) {
		this.actividadAcademicaDAO = actividadAcademicaDAO;
	}

	public ExperienciaLaboralDAO getExperienciaLaboralDAO() {
		return experienciaLaboralDAO;
	}

	public void setExperienciaLaboralDAO(ExperienciaLaboralDAO experienciaLaboralDAO) {
		this.experienciaLaboralDAO = experienciaLaboralDAO;
	}

	public PostulanteIdiomaDAO getPostulanteIdiomaDAO() {
		return postulanteIdiomaDAO;
	}

	public void setPostulanteIdiomaDAO(PostulanteIdiomaDAO postulanteIdiomaDAO) {
		this.postulanteIdiomaDAO = postulanteIdiomaDAO;
	}

	public PostulanteBeneficioDAO getPostulanteBeneficioDAO() {
		return postulanteBeneficioDAO;
	}

	public void setPostulanteBeneficioDAO(
			PostulanteBeneficioDAO postulanteBeneficioDAO) {
		this.postulanteBeneficioDAO = postulanteBeneficioDAO;
	}		
}
