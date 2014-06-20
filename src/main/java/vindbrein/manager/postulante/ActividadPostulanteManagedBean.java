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

import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.Postulante;
import vindbrein.service.MatchResultService;
import vindbrein.service.PostulanteService;

@Controller
@Scope("request")
public class ActividadPostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(ActividadPostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("matchResultServiceImpl")
	MatchResultService matchResultService;
	
	@Autowired
	@Qualifier("postulanteServiceImpl")
	PostulanteService postulanteService;
	
	ArrayList<MatchResult> matchResults;
		
		
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	public void iniciarDatosMaestros(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Postulante  postulante = postulanteService.getPostulanteByCorreo(user.getUsername());
		
		matchResults = matchResultService.getMatchResultsByPostulante(postulante);
	}

	public ArrayList<MatchResult> getMatchResults() {
		return matchResults;
	}

	public void setMatchResults(ArrayList<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}	
}
