package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ActividadAcademicaDAO;
import vindbrein.dao.ExperienciaLaboralDAO;
import vindbrein.dao.OfertaBeneficioDAO;
import vindbrein.dao.PostulanteBeneficioDAO;
import vindbrein.dao.PostulanteConocimientoDAO;
import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.PostulanteIdiomaDAO;
import vindbrein.dao.ResidenciaDAO;
import vindbrein.dao.TelefonoDAO;
import vindbrein.dao.app.CoreDAO;
import vindbrein.dao.document.PostulantPreferenceDAO;
import vindbrein.dao.document.PostulantSelfDescriptionDAO;
import vindbrein.domain.document.PostulantPreference;
import vindbrein.domain.document.PostulantSelfDescription;
import vindbrein.domain.model.ActividadAcademica;
import vindbrein.domain.model.DimensionOrganizacion;
import vindbrein.domain.model.EstadoCivil;
import vindbrein.domain.model.ExperienciaLaboral;
import vindbrein.domain.model.NivelPuesto;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteBeneficio;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.domain.model.PostulanteIdioma;
import vindbrein.domain.model.Residencia;
import vindbrein.domain.model.Sector;
import vindbrein.domain.model.Telefono;
import vindbrein.domain.model.TipoHorario;
import vindbrein.service.ExperienciaLaboralService;
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
	
	@Autowired
	@Qualifier(("experienciaLaboralServiceImpl"))
	ExperienciaLaboralService experienciaLaboralService;
		
	@Autowired	
	CoreDAO coreDAO;
	
	
	
	@Transactional(readOnly = false)
	public void addPostulante(Postulante postulante) {
		getPostulanteDAO().addPostulante(postulante);		
	}	
		
	@Transactional(readOnly = false)
	public void updatePostulante(Postulante postulante){
		
		if(postulante.getEstadoCivil().getEsciId() == 0){
			postulante.setEstadoCivil(null);
		}
		
		if(postulante.getTipoHorario().getTihoId() == 0){
			postulante.setTipoHorario(null);
		}
		
		if(postulante.getNivelPuesto().getNipuId() == 0){
			postulante.setNivelPuesto(null);
		}
		
		if(postulante.getDimensionOrganizacion().getDiorId() == 0){
			postulante.setDimensionOrganizacion(null);
		}
		
		if(postulante.getSector().getSectId() == 0){
			postulante.setSector(null);
		}
				
		postulanteDAO.updatePostulante(postulante);
		
		// beneficio
		for (PostulanteBeneficio postulanteBeneficio : postulanteBeneficioDAO.getPostulanteBeneficioByPostulante(postulante)) {
			postulanteBeneficioDAO.deletePostulanteBeneficio(postulanteBeneficio);
		}

		for (PostulanteBeneficio postulanteBeneficio : postulante.getPostulanteBeneficios()) {
			postulanteBeneficio.getId().setFkBeneId(postulanteBeneficio.getBeneficio().getBeneId());
			postulanteBeneficio.getId().setFkPostId(postulanteBeneficio.getPostulante().getPostId());
			
			postulanteBeneficioDAO.addPostulanteBeneficio(postulanteBeneficio);
		}
		
		//telefono
		for (Telefono telefono : telefonoDAO.getTelefonosByPostulante(postulante)) {
			telefonoDAO.deleteTelefono(telefono);
		}
		
		for (Telefono telefono : postulante.getTelefonos()) {
			telefono.setPostulante(postulante);
			telefonoDAO.addTelefono(telefono);
		}
		
		//residencia
		for (Residencia residencia : residenciaDAO.getResidenciasByPostulante(postulante)) {
			residenciaDAO.deleteResidencia(residencia);
		}

		for (Residencia residencia : postulante.getResidencias()) {
			residencia.setPostulante(postulante);
			residenciaDAO.addResidencia(residencia);
		}
		
		//conocimientos
		for (PostulanteConocimiento postulanteConocimiento : postulanteConocimientoDAO.getPostulanteConocimientoByPostulante(postulante)) {
			postulanteConocimientoDAO.deletePostulanteConocimiento(postulanteConocimiento);
		}

		for (PostulanteConocimiento postulanteConocimiento : postulante.getPostulanteConocimientos()) {
			postulanteConocimiento.getId().setFkConoId(postulanteConocimiento.getConocimiento().getConoId());
			postulanteConocimiento.getId().setFkPostId(postulanteConocimiento.getPostulante().getPostId());
			postulanteConocimiento.getId().setFkNicoId(postulanteConocimiento.getNivelConocimiento().getNicoId());
			
			postulanteConocimientoDAO.addPostulanteConocimiento(postulanteConocimiento);
		}
		
		//experiencia laboral
		for (ExperienciaLaboral experienciaLaboral : experienciaLaboralDAO.getExperienciasLaboralesByPostulante(postulante)) {
			experienciaLaboralDAO.deleteExperienciaLaboral(experienciaLaboral);
		}

		for (ExperienciaLaboral experienciaLaboral : postulante.getExperienciasLaborales()) {
			experienciaLaboralService.addExperienciaLaboral(experienciaLaboral);
		}
		
		//actividad academica
		for (ActividadAcademica actividadAcademica : actividadAcademicaDAO.getActividadesAcademicasByPostulante(postulante)) {
			actividadAcademicaDAO.deleteActividadAcademica(actividadAcademica);
		}

		for (ActividadAcademica actividadAcademica : postulante.getActividadesAcademicas()) {
			actividadAcademicaDAO.addActividadAcademica(actividadAcademica);
		}
		
		//actividad academica
		for (PostulanteIdioma postulanteIdioma : postulanteIdiomaDAO.getPostulanteIdiomasByPostulante(postulante)) {
			postulanteIdiomaDAO.deletePostulanteIdioma(postulanteIdioma);
		}

		for (PostulanteIdioma postulanteIdioma : postulante.getPostulanteIdiomas()) {
			postulanteIdioma.getId().setFkIdioId(postulanteIdioma.getIdioma().getIdioId());
			postulanteIdioma.getId().setFkNiidId(postulanteIdioma.getNivelIdioma().getNiidId());
			postulanteIdioma.getId().setFkPostId(postulanteIdioma.getPostulante().getPostId());
			
			postulanteIdiomaDAO.addPostulanteIdioma(postulanteIdioma);;
		}
				
		//actualizando la autodescripcion
		PostulantSelfDescription postulantSelfDescription = postulantSelfDescriptionDAO
				.getPostulantSelfDescriptionById(postulante.getPostIdS());
		
		postulantSelfDescription.setValues(coreDAO.getVectorPostulantSelfDescription(postulante));
		
		postulantSelfDescriptionDAO.updatePostulantSelfDescription(postulantSelfDescription);
		
		//actualizando las preferencias
		PostulantPreference postulantPreference = postulantPreferenceDAO
				.getPostulantPreferenceById(postulante.getPostIdP());
		
		postulantPreference.setValues(coreDAO.getVectorPostulantPreference(postulante));
		
		postulantPreferenceDAO.updatePostulantPreference(postulantPreference);
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
	
	public ArrayList<Postulante> getPostulantesCompletos(){
		
		ArrayList<Postulante> postulantes = postulanteDAO.getPostulantes();
		
		for (int i = 0; i < postulantes.size(); i++) {
			postulantes.get(i).setActividadesAcademicas(actividadAcademicaDAO.getActividadesAcademicasByPostulante(postulantes.get(i)));
			postulantes.get(i).setExperienciasLaborales(experienciaLaboralDAO.getExperienciasLaboralesByPostulante(postulantes.get(i)));
			postulantes.get(i).setPostulanteBeneficios(postulanteBeneficioDAO.getPostulanteBeneficioByPostulante(postulantes.get(i)));
			postulantes.get(i).setPostulanteConocimientos(postulanteConocimientoDAO.getPostulanteConocimientoByPostulante(postulantes.get(i)));
			postulantes.get(i).setPostulanteIdiomas(postulanteIdiomaDAO.getPostulanteIdiomasByPostulante(postulantes.get(i)));
			postulantes.get(i).setResidencias(residenciaDAO.getResidenciasByPostulante(postulantes.get(i)));
			postulantes.get(i).setTelefonos(telefonoDAO.getTelefonosByPostulante(postulantes.get(i)));
		}
		
		return postulantes;
		
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
