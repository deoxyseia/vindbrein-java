package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.PostulanteIdioma;

public interface IdiomaService {
	
	public void addIdioma(Idioma idioma);

	public void updateIdioma(Idioma idioma);

	public void deleteIdioma(Idioma idioma);

	public Idioma getIdiomaById(int id);

	public ArrayList<Idioma> getIdiomas();	
	
	public void addNivelIdioma(NivelIdioma nivelIdioma);

	public void updateNivelIdioma(NivelIdioma nivelIdioma);

	public void deleteNivelIdioma(NivelIdioma nivelIdioma);

	public Idioma getNivelIdiomaById(int id);

	public ArrayList<NivelIdioma> getNivelesIdioma();
	
	public void addIdiomaToPostulante(PostulanteIdioma postulanteIdioma);

	public void updateIdiomaToPostulante(PostulanteIdioma postulanteIdioma);

	public void deleteIdiomaToPostulante(PostulanteIdioma postulanteIdioma);	
}
