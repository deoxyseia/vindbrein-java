package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Postulante;

public interface PostulanteService {
	
	public void addPostulante(Postulante Postulante);

	public void updatePostulante(Postulante Postulante);

	public void deletePostulante(Postulante Postulante);

	public Postulante getPostulanteById(int id);

	public ArrayList<Postulante> getPostulantes();
	
	public Postulante getPostulanteByUsername(String username);
	
	public Postulante getPostulanteCompletoByPostulante(Postulante postulante);

}
