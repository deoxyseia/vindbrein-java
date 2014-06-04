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
import vindbrein.service.OfertaLaboralService;

@Controller
@Scope("session")
public class BuscarOfertaManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(BuscarOfertaManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("ofertaLaboralServiceImpl")
	OfertaLaboralService ofertaLaboralService;
	
	
	private ArrayList<OfertaLaboral> ofertasLaborales;
		
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		logger.info("Iniciando datos maestros");
		
		ofertasLaborales = ofertaLaboralService.getOfertasLaborales();		
	}
	
	//getters and setters

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboral> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}	
}
