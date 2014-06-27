package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Postulante;

public interface PostulanteService {
	
	public void addPostulante(Postulante Postulante);

	public void updatePostulante(Postulante Postulante);

	public void deletePostulante(Postulante Postulante);
	
	public void saveOrUpdatePostulante(Postulante postulante);

	public Postulante getPostulanteById(int id);

	public ArrayList<Postulante> getPostulantes();
	
	public Postulante getPostulanteByCorreo(String correo);
	
	public Postulante getPostulanteCompletoByPostulante(Postulante postulante);
	
	public ArrayList<Postulante> getPostulantesCompletos();

}
