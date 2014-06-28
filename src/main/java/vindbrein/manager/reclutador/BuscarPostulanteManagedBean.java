package vindbrein.manager.reclutador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.service.PostulanteService;
import vindbrein.service.app.CoreService;
import vindbrein.util.Util;

@Controller
@Scope("session")
public class BuscarPostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(BuscarPostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("postulanteServiceImpl")
	PostulanteService postulanteService;
	
	@Autowired
	@Qualifier("coreServiceImpl")
	CoreService coreService;
	
	private ArrayList<Postulante> postulantes;
	private ArrayList<Postulante> postulantesEncontrados;
	private String cadenaBusqueda;
	private Postulante selectedPostulante;
	private OfertaLaboral selectedOfertaLaboral;
	
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		logger.info("Iniciando datos maestros");
		
		postulantes = postulanteService.getPostulantesCompletos();
		
		postulantesEncontrados = postulantes;
	}
	
	public List<String> autocompletarBusqueda(String query){
		boolean tomado= false;
		List<String> encontrados = new ArrayList<String>();
		
		for (int i = 0; i < postulantes.size(); i++) {
			List<PostulanteConocimiento> postulanteConocimientos = postulantes.get(i).getPostulanteConocimientos();
			
			for (int j = 0; j < postulanteConocimientos.size(); j++) {
				if(postulanteConocimientos.get(j).getConocimiento().getConoNombre().toLowerCase().contains(query.toLowerCase())){
					for (int k = 0; k < encontrados.size(); k++) {
						if(encontrados.get(k).equalsIgnoreCase(postulanteConocimientos.get(j).getConocimiento().getConoNombre())){
							tomado = true;
							break;
						}
					}
					
					if(!tomado){
						encontrados.add(postulanteConocimientos.get(j).getConocimiento().getConoNombre());
						tomado = false;
					}					
				}
			}
		}
		
		return encontrados;
	}
	
	public void realizarBusqueda(){
		logger.info("realizando busqueda");
		
		if(cadenaBusqueda.equals("")){
			postulantesEncontrados = postulantes;
		}else{
			postulantesEncontrados = new ArrayList<Postulante>();
			
			for (int i = 0; i < postulantes.size(); i++) {
				
				List<PostulanteConocimiento> postulanteConocimientos = postulantes.get(i).getPostulanteConocimientos();
				
				for (int j = 0; j < postulanteConocimientos.size(); j++) {
					if(postulanteConocimientos.get(j).getConocimiento().getConoNombre().equalsIgnoreCase(cadenaBusqueda)){
						postulantesEncontrados.add(postulantes.get(i));
						break;
					}
				}
			}
		}	
		
		Util.lanzarMensaje("INFO", "GLOBAL", "Se ha realizado exitosamente la búsqueda");
	}
	
	public void reclutarPostulante(){
		logger.info("reclutando postulante");
		
		coreService.reclutarPostulante(selectedOfertaLaboral, selectedPostulante);		
	}
	
	
	//getters and setters
	
	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}

	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}

	public ArrayList<Postulante> getPostulantesEncontrados() {
		return postulantesEncontrados;
	}

	public void setPostulantesEncontrados(
			ArrayList<Postulante> postulantesEncontrados) {
		this.postulantesEncontrados = postulantesEncontrados;
	}

	public Postulante getSelectedPostulante() {
		return selectedPostulante;
	}

	public void setSelectedPostulante(Postulante selectedPostulante) {
		selectedPostulante = postulanteService.getPostulanteCompletoByPostulante(selectedPostulante);
		
		this.selectedPostulante = selectedPostulante;
	}

	public OfertaLaboral getSelectedOfertaLaboral() {
		return selectedOfertaLaboral;
	}

	public void setSelectedOfertaLaboral(OfertaLaboral selectedOfertaLaboral) {
		this.selectedOfertaLaboral = selectedOfertaLaboral;
	}		
}
