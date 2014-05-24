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

import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.EstadoCivil;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.Puesto;
import vindbrein.domain.model.Reclutador;
import vindbrein.domain.model.TipoHorario;
import vindbrein.service.OfertaLaboralService;
import vindbrein.service.ReclutadorService;

@Controller
@Scope("session")
public class OfertaLaboralManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(OfertaLaboralManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private OfertaLaboral newOfertaLaboral;
	
	private Reclutador reclutador;
	private Organizacion organizacion;
	
	private ArrayList<OfertaLaboral> ofertasLaborales;
	
	@Autowired
	@Qualifier("reclutadorServiceImpl")
	private ReclutadorService reclutadorService;
	
	@Autowired
	@Qualifier("ofertaLaboralServiceImpl")
	private OfertaLaboralService ofertaLaboralService;
	
	@PostConstruct
	public void init() {		
		iniciarReclutador();
		
		iniciarDatosMaestros();
	}
	
	private void iniciarReclutador(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		reclutador = reclutadorService.getReclutadorByCorreo(user.getUsername());		
	}
	
	private void iniciarDatosMaestros(){
		organizacion = reclutador.getOrganizacion();
		
		ofertasLaborales = ofertaLaboralService.getOfertasLaboralesByOrganizacion(organizacion);
	}
	
	private void recargarOfertaLaboral(){
		
		
	}
	
	private void reiniciarNewOfertaLaboral(){
		newOfertaLaboral = new OfertaLaboral();
		
		newOfertaLaboral.setOrganizacionPuesto(new OrganizacionPuesto());
		newOfertaLaboral.getOrganizacionPuesto().setOrganizacion(organizacion);
		newOfertaLaboral.getOrganizacionPuesto().setPuesto(new Puesto());
		newOfertaLaboral.setTipoHorario(new TipoHorario());
		newOfertaLaboral.setDistrito(new Distrito());
		newOfertaLaboral.setEstadoCivil(new EstadoCivil());
		
	}

	public Reclutador getReclutador() {
		return reclutador;
	}

	public void setReclutador(Reclutador reclutador) {
		this.reclutador = reclutador;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboral> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}

	public OfertaLaboral getNewOfertaLaboral() {
		return newOfertaLaboral;
	}

	public void setNewOfertaLaboral(OfertaLaboral newOfertaLaboral) {
		this.newOfertaLaboral = newOfertaLaboral;
	}
}
