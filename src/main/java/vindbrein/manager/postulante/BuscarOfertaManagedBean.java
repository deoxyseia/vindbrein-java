package vindbrein.manager.postulante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.service.OfertaLaboralService;
import vindbrein.service.PostulanteService;
import vindbrein.service.app.CoreService;

@Controller
@Scope("session")
public class BuscarOfertaManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(BuscarOfertaManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("ofertaLaboralServiceImpl")
	OfertaLaboralService ofertaLaboralService;

	@Autowired
	@Qualifier("postulanteServiceImpl")
	PostulanteService postulanteService;
	
	@Autowired
	@Qualifier("coreServiceImpl")
	CoreService coreService;
	
	
	private ArrayList<OfertaLaboral> ofertasLaborales;
	private ArrayList<OfertaLaboral> ofertasEncontradas;
	
	private OfertaLaboral selectedOfertaLaboral;
	
	private String cadenaBusqueda;
		
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		logger.info("Iniciando datos maestros");
		
		ofertasLaborales = ofertaLaboralService.getOfertasLaboralesCompletas();	
		
		ofertasEncontradas = ofertasLaborales;
	}
	
	public List<String> autocompletarBusqueda(String query){
		List<String> encontrados = new ArrayList<String>();
		
		for (int i = 0; i < ofertasLaborales.size(); i++) {
			List<OfertaConocimiento> ofertaConocimientos = ofertasLaborales.get(i).getOfertaConocimientos();
			
			for (int j = 0; j < ofertaConocimientos.size(); j++) {
				if(ofertaConocimientos.get(j).getConocimiento().getConoNombre().toLowerCase().contains(query.toLowerCase())){
					encontrados.add(ofertaConocimientos.get(j).getConocimiento().getConoNombre());
				}
			}
		}
		
		return encontrados;
	}	
	
	public void realizarBusqueda(){
		logger.info("realizando busqueda");
		
		if(cadenaBusqueda.equals("")){
			ofertasEncontradas = ofertasLaborales;
		}else{
			ofertasEncontradas = new ArrayList<OfertaLaboral>();
			
			for (int i = 0; i < ofertasLaborales.size(); i++) {
				
				List<OfertaConocimiento> ofertaConocimientos = ofertasLaborales.get(i).getOfertaConocimientos();
				
				for (int j = 0; j < ofertaConocimientos.size(); j++) {
					if(ofertaConocimientos.get(j).getConocimiento().getConoNombre().equalsIgnoreCase(cadenaBusqueda)){
						ofertasEncontradas.add(ofertasLaborales.get(i));
						break;
					}
				}
			}
		}		
	}
	
	public void visitarOfertaLaboral(){
		logger.info("visitando oferta laboral");
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Postulante postulante = postulanteService.getPostulanteByCorreo(user.getUsername());
						
		coreService.visitarOfertaLaboral(selectedOfertaLaboral, postulante);
	}
	
	public void postularOfertaLaboral(){
		logger.info("postulando a oferta laboral");
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Postulante postulante = postulanteService.getPostulanteByCorreo(user.getUsername());
		
		coreService.postularOfertaLaboral(selectedOfertaLaboral, postulante);
	}
	
	//getters and setters

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboral> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}

	public ArrayList<OfertaLaboral> getOfertasEncontradas() {
		return ofertasEncontradas;
	}

	public void setOfertasEncontradas(ArrayList<OfertaLaboral> ofertasEncontradas) {
		this.ofertasEncontradas = ofertasEncontradas;
	}

	public OfertaLaboral getSelectedOfertaLaboral() {
		return selectedOfertaLaboral;
	}

	public void setSelectedOfertaLaboral(OfertaLaboral selectedOfertaLaboral) {
		selectedOfertaLaboral = ofertaLaboralService.getOfertaLaboralCompleta(selectedOfertaLaboral);
			
		this.selectedOfertaLaboral = selectedOfertaLaboral;
		
		visitarOfertaLaboral();
	}

	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}

	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}	
}
