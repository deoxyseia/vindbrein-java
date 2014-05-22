package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.IdiomaDAO;
import vindbrein.dao.NivelIdiomaDAO;
import vindbrein.dao.PostulanteIdiomaDAO;
import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.PostulanteIdioma;
import vindbrein.service.IdiomaService;

@Service
@Transactional(readOnly = true)
public class IdiomaServiceImpl implements IdiomaService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	IdiomaDAO idiomaDAO;
	
	@Autowired	
	NivelIdiomaDAO nivelIdiomaDAO;
	
	@Autowired	
	PostulanteIdiomaDAO postulanteIdiomaDAO;
	
	@Transactional(readOnly = false)
	public void addIdioma(Idioma idioma) {
		getIdiomaDAO().addIdioma(idioma);		
	}
	
	@Transactional(readOnly = false)
	public void updateIdioma(Idioma idioma) {
		getIdiomaDAO().updateIdioma(idioma);		
	}
	
	@Transactional(readOnly = false)
	public void deleteIdioma(Idioma idioma) {
		getIdiomaDAO().deleteIdioma(idioma);		
	}

	public Idioma getIdiomaById(int id) {
		return getIdiomaDAO().getIdiomaById(id);
	}

	public ArrayList<Idioma> getIdiomas() {
		return getIdiomaDAO().getIdiomas();
	}
	
	public void addNivelIdioma(NivelIdioma nivelIdioma) {
		getNivelIdiomaDAO().addNivelIdioma(nivelIdioma);		
	}

	public void updateNivelIdioma(NivelIdioma nivelIdioma) {
		getNivelIdiomaDAO().updateNivelIdioma(nivelIdioma);		
	}

	public void deleteNivelIdioma(NivelIdioma nivelIdioma) {
		getNivelIdiomaDAO().deleteNivelIdioma(nivelIdioma);
		
	}

	public Idioma getNivelIdiomaById(int id) {
		return getNivelIdiomaById(id);
	}

	public ArrayList<NivelIdioma> getNivelesIdioma() {
		return getNivelIdiomaDAO().getNivelesIdioma();
	}	
	
	@Transactional(readOnly = false)
	public void addIdiomaToPostulante(PostulanteIdioma postulanteIdioma) {
		
		postulanteIdioma.getId().setFkIdioId(postulanteIdioma.getIdioma().getIdioId());
		postulanteIdioma.getId().setFkNiidId(postulanteIdioma.getNivelIdioma().getNiidId());
		postulanteIdioma.getId().setFkPostId(postulanteIdioma.getPostulante().getPostId());
		
		getPostulanteIdiomaDAO().addPostulanteIdioma(postulanteIdioma);		
	}
	
	@Transactional(readOnly = false)
	public void updateIdiomaToPostulante(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomaDAO().updatePostulanteIdioma(postulanteIdioma);		
	}
	
	@Transactional(readOnly = false)
	public void deleteIdiomaToPostulante(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomaDAO().deletePostulanteIdioma(postulanteIdioma);		
	}	
		
	//getters and setters
	
	public IdiomaDAO getIdiomaDAO() {
		return idiomaDAO;
	}

	public void setIdiomaDAO(IdiomaDAO idiomaDAO) {
		this.idiomaDAO = idiomaDAO;
	}

	public PostulanteIdiomaDAO getPostulanteIdiomaDAO() {
		return postulanteIdiomaDAO;
	}

	public void setPostulanteIdiomaDAO(PostulanteIdiomaDAO postulanteIdiomaDAO) {
		this.postulanteIdiomaDAO = postulanteIdiomaDAO;
	}

	public NivelIdiomaDAO getNivelIdiomaDAO() {
		return nivelIdiomaDAO;
	}

	public void setNivelIdiomaDAO(NivelIdiomaDAO nivelIdiomaDAO) {
		this.nivelIdiomaDAO = nivelIdiomaDAO;
	}	
}
