package vindbrein.manager.postulante;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Telefono;
import vindbrein.service.PostulanteService;
import vindbrein.service.TelefonoService;

@Controller
@Scope("session")
public class PostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(PostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private Postulante postulante;
	
	private Telefono selectedTelefono;
	private Telefono newTelefono;
	
	
		
	@Autowired
	@Qualifier("postulanteServiceImpl")
	private PostulanteService postulanteService;
	
	@Autowired
	@Qualifier("telefonoServiceImpl")
	private TelefonoService telefonoService;
		
	@PostConstruct
	public void init() {		
		cargarPostulante();	
		
		iniciarVariables();
	}
	
	private void cargarPostulante(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		postulante = getPostulanteService().getPostulanteByUsername(user.getUsername());
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	private void iniciarVariables(){
		
	}
	
	public void reiniciarNewTelefono(){
		newTelefono = new Telefono();
	}
	
	public void updateTelefono(RowEditEvent event){
		Telefono telefono = (Telefono) event.getObject();
		
		telefonoService.updateTelefono(telefono);		
	}
	
	public void saveTelefono(){		
		newTelefono.setPostulante(postulante);
		
		telefonoService.addTelefono(newTelefono);	
		
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void deleteTelefono(){				
		telefonoService.deleteTelefono(selectedTelefono);
	}		

	public Postulante getPostulante() {
		return postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

	public PostulanteService getPostulanteService() {
		return postulanteService;
	}

	public void setPostulanteService(PostulanteService postulanteService) {
		this.postulanteService = postulanteService;
	}

	public TelefonoService getTelefonoService() {
		return telefonoService;
	}

	public void setTelefonoService(TelefonoService telefonoService) {
		this.telefonoService = telefonoService;
	}

	public Telefono getSelectedTelefono() {
		return selectedTelefono;
	}

	public void setSelectedTelefono(Telefono selectedTelefono) {
		this.selectedTelefono = selectedTelefono;
	}

	public Telefono getNewTelefono() {
		return newTelefono;
	}

	public void setNewTelefono(Telefono newTelefono) {
		this.newTelefono = newTelefono;
	}		
}
