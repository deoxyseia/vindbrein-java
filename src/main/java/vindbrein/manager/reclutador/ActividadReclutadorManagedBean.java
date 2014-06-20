package vindbrein.manager.reclutador;

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
import vindbrein.domain.model.Reclutador;
import vindbrein.service.MatchResultService;
import vindbrein.service.ReclutadorService;

@Controller
@Scope("request")
public class ActividadReclutadorManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(ActividadReclutadorManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("matchResultServiceImpl")
	MatchResultService matchResultService;
	
	@Autowired
	@Qualifier("reclutadorServiceImpl")
	ReclutadorService reclutadorService;
	
	
	ArrayList<MatchResult> matchResults;
		
		
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	public void iniciarDatosMaestros(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Reclutador reclutador = reclutadorService.getReclutadorByCorreo(user.getUsername());
		
		matchResults = matchResultService.getMatchResultsByOrganizacion(reclutador.getOrganizacion());
	}

	public ArrayList<MatchResult> getMatchResults() {
		return matchResults;
	}

	public void setMatchResults(ArrayList<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}	
}
