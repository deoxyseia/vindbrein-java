package vindbrein.manager.postulante;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.service.app.RecommenderService;

@Controller
@Scope("session")
public class RecomendarOfertaManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(RecomendarOfertaManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OfertaLaboral> ofertasRecomendadas;
	
	@Autowired
	@Qualifier("recommenderServiceImpl")
	private RecommenderService recommenderService;
	
			
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		
	}
	
	public void recomendarOfertasLaborales(){
		
	}
	
	//getters and setters

	public ArrayList<OfertaLaboral> getOfertasRecomendadas() {
		return ofertasRecomendadas;
	}

	public void setOfertasRecomendadas(ArrayList<OfertaLaboral> ofertasRecomendadas) {
		this.ofertasRecomendadas = ofertasRecomendadas;
	}	
}
