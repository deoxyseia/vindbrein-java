package vindbrein.manager.administrador;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.Grado;
import vindbrein.service.GradoService;
import vindbrein.service.PostulanteService;



@Controller
@Scope("session")
public class GradoManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(GradoManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Grado> grados;
	
	private Grado newGrado;
	
	@Autowired
	@Qualifier("gradoServiceImpl")
	private GradoService gradoService;
	
	@PostConstruct
	public void init() {		
		grados = gradoService.getGrados();
		newGrado = new Grado();	
	}
	
	public void reiniciarNewGrado (){
		newGrado = new Grado();
	}
    
	public void saveGrado(){
		if(newGrado.getGradId()==0){
			gradoService.addGrado(newGrado);
		}else{
			gradoService.updateGrado(newGrado);
		}
		
		grados = gradoService.getGrados();		
	}
	
	public void deleteGrado(){
		gradoService.deleteGrado(newGrado);
		grados = gradoService.getGrados();	
	}
	
	
	
	public ArrayList<Grado> getGrados() {
		return grados;
	}

	public void setGrados(ArrayList<Grado> grados) {
		this.grados = grados;
	}

	public Grado getNewGrado() {
		return newGrado;
	}

	public void setNewGrado(Grado newGrado) {
		this.newGrado = newGrado;
	}

	public GradoService getGradoService() {
		return gradoService;
	}

	public void setGradoService(GradoService gradoService) {
		this.gradoService = gradoService;
	}
	
	
		
}
