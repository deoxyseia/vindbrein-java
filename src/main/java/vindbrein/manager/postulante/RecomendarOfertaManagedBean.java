package vindbrein.manager.postulante;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.service.PostulanteService;
import vindbrein.service.app.RecommenderService;
import vindbrein.util.RecommenderType;

@Controller
@Scope("session")
public class RecomendarOfertaManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(RecomendarOfertaManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private int tipoRecomendacion;
	
	private Postulante postulante;
	
	private ArrayList<OfertaLaboral> ofertasRecomendadas;
	
	@Autowired
	@Qualifier("recommenderServiceImpl")
	private RecommenderService recommenderService;
	
	@Autowired
	@Qualifier("postulanteServiceImpl")
	private PostulanteService postulanteService;
	
			
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		postulante = postulanteService.getPostulanteByCorreo(user.getUsername());
	}
	
	public void recomendarOfertasLaborales(){	
		
		switch (tipoRecomendacion) {
		case 1:
			ofertasRecomendadas = recommenderService.recomendarOfertasLaboralesToPostulante(postulante, 2, RecommenderType.CONTENT_BASED);
			break;
		case 2:
			ofertasRecomendadas = recommenderService.recomendarOfertasLaboralesToPostulante(postulante, 2, RecommenderType.COLLABORATIVE_BASED);
			break;
		case 3:
			ofertasRecomendadas = recommenderService.recomendarOfertasLaboralesToPostulante(postulante, 2, RecommenderType.RECIPROCITY_BASED);
			break;
		case 4:
			System.out.println("en proceso");
			break;	

		default:
			System.out.println("que pasó!!!");
			break;
		}
	}
	
	//getters and setters

	public ArrayList<OfertaLaboral> getOfertasRecomendadas() {
		return ofertasRecomendadas;
	}

	public void setOfertasRecomendadas(ArrayList<OfertaLaboral> ofertasRecomendadas) {
		this.ofertasRecomendadas = ofertasRecomendadas;
	}

	public int getTipoRecomendacion() {
		return tipoRecomendacion;
	}

	public void setTipoRecomendacion(int tipoRecomendacion) {
		this.tipoRecomendacion = tipoRecomendacion;
	}		
}
