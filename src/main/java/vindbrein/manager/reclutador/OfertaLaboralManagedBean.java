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

import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.CentroEstudio;
import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.EstudioGenerico;
import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelConocimiento;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaBeneficioPK;
import vindbrein.domain.model.OfertaCentroEstudio;
import vindbrein.domain.model.OfertaCentroEstudioPK;
import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.OfertaConocimientoPK;
import vindbrein.domain.model.OfertaEstudio;
import vindbrein.domain.model.OfertaEstudioPK;
import vindbrein.domain.model.OfertaIdioma;
import vindbrein.domain.model.OfertaIdiomaPK;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.OrganizacionPuestoPK;
import vindbrein.domain.model.Puesto;
import vindbrein.domain.model.Reclutador;
import vindbrein.domain.model.TipoHorario;
import vindbrein.service.BeneficioService;
import vindbrein.service.CentroEstudioService;
import vindbrein.service.ConocimientoService;
import vindbrein.service.EstudioGenericoService;
import vindbrein.service.IdiomaService;
import vindbrein.service.OfertaLaboralService;
import vindbrein.service.PuestoService;
import vindbrein.service.ReclutadorService;
import vindbrein.service.TipoHorarioService;

@Controller
@Scope("session")
public class OfertaLaboralManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(OfertaLaboralManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OfertaLaboral> ofertasLaborales;
	private OfertaLaboral newOfertaLaboral;
	
	private Reclutador reclutador;
	private Organizacion organizacion;
		
	//data de manejo
	private OfertaCentroEstudio newOfertaCentroEstudio;
	private OfertaEstudio newOfertaEstudio;
	private OfertaBeneficio newOfertaBeneficio;
	private OfertaConocimiento newOfertaConocimiento;
	private OfertaIdioma newOfertaIdioma;
		
	@Autowired
	@Qualifier("reclutadorServiceImpl")
	private ReclutadorService reclutadorService;
	
	@Autowired
	@Qualifier("ofertaLaboralServiceImpl")
	private OfertaLaboralService ofertaLaboralService;
	
	@Autowired
	@Qualifier("idiomaServiceImpl")
	private IdiomaService idiomaService;
	
	@Autowired
	@Qualifier("tipoHorarioServiceImpl")
	private TipoHorarioService tipoHorarioService;
	
	@Autowired
	@Qualifier("conocimientoServiceImpl")
	private ConocimientoService conocimientoService;
	
	@Autowired
	@Qualifier("beneficioServiceImpl")
	private BeneficioService beneficioService;
	
	@Autowired
	@Qualifier("estudioGenericoServiceImpl")
	private EstudioGenericoService estudioGenericoService;
	
	@Autowired
	@Qualifier("centroEstudioServiceImpl")
	private CentroEstudioService centroEstudioService;
	
	@Autowired
	@Qualifier("puestoServiceImpl")
	private PuestoService puestoService;
	
	//data maestra
	private ArrayList<Idioma> idiomas;
	private ArrayList<NivelIdioma> nivelesIdioma;
	private ArrayList<TipoHorario> tiposHorario;	
	private ArrayList<Conocimiento> conocimientos;
	private ArrayList<NivelConocimiento> nivelesConocimiento;	
	private ArrayList<Beneficio> beneficios;
	private ArrayList<EstudioGenerico> estudiosGenericos;
	private ArrayList<CentroEstudio> centrosEstudio;
	private ArrayList<Puesto> puestos;
	
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();
	}
			
	private void iniciarDatosMaestros(){
		logger.info("iniciando datos maestros");
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		reclutador = reclutadorService.getReclutadorByCorreo(user.getUsername());
		organizacion = reclutador.getOrganizacion();
		
		ofertasLaborales = ofertaLaboralService.getOfertasLaboralesByOrganizacion(organizacion);
				
		idiomas = idiomaService.getIdiomas();
		nivelesIdioma = idiomaService.getNivelesIdioma();
		tiposHorario = tipoHorarioService.getTiposHorario();
		conocimientos = conocimientoService.getConocimientos();
		nivelesConocimiento = conocimientoService.getNivelesConocimiento();
		beneficios = beneficioService.getBeneficios();
		estudiosGenericos = estudioGenericoService.getEstudiosGenericos();
		centrosEstudio = centroEstudioService.getCentrosEstudio();	
		puestos = puestoService.getPuestos();
	}
	
	public void recargarOfertaLaboral(){
		
		
	}
	
	public void reiniciarNewOfertaLaboral(){
		logger.info("reiniciando oferta laboral");
		
		newOfertaLaboral = new OfertaLaboral();
		
		newOfertaLaboral.setOrganizacionPuesto(new OrganizacionPuesto());
		newOfertaLaboral.getOrganizacionPuesto().setOrganizacion(organizacion);
		newOfertaLaboral.getOrganizacionPuesto().setPuesto(new Puesto());
		newOfertaLaboral.getOrganizacionPuesto().setId(new OrganizacionPuestoPK());
					
		newOfertaLaboral.setOfertaCentroEstudios(new ArrayList<OfertaCentroEstudio>());
		newOfertaLaboral.setOfertaBeneficios(new ArrayList<OfertaBeneficio>());
		newOfertaLaboral.setOfertaConocimientos(new ArrayList<OfertaConocimiento>());
		newOfertaLaboral.setOfertaEstudios(new ArrayList<OfertaEstudio>());
		newOfertaLaboral.setOfertaIdiomas(new ArrayList<OfertaIdioma>());	
		
		reiniciarNewOfertaBeneficio();
		reiniciarNewOfertaCentroEstudio();
		reiniciarNewOfertaConocimiento();
		reiniciarNewOfertaEstudio();
		reiniciarNewOfertaIdioma();
	}
	
	public void saveOfertaLaboral(){
		logger.info("guardando la oferta laboral");
		
		ofertaLaboralService.saveOrUpdateOfertaLaboral(newOfertaLaboral);
	}
	
	// Oferta beneficio
	public void reiniciarNewOfertaBeneficio() {
		logger.info("reiniciando oferta beneficio");
		
		newOfertaBeneficio = new OfertaBeneficio();
		newOfertaBeneficio.setOfertaLaboral(newOfertaLaboral);
		newOfertaBeneficio.setBeneficio(new Beneficio());
		newOfertaBeneficio.setId(new OfertaBeneficioPK());
	}

	public void saveOfertaBeneficio() {
		logger.info("guardando oferta beneficio");
		
		boolean agregar = true;
		
		for (OfertaBeneficio ofertaBeneficio : newOfertaLaboral.getOfertaBeneficios()) {
			if(ofertaBeneficio.getBeneficio().getBeneId() == newOfertaBeneficio.getBeneficio().getBeneId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			newOfertaLaboral.addOfertaBeneficio(newOfertaBeneficio);			
		}				
	}

	public void deleteOfertaBeneficio() {
		logger.info("eliminando beneficio");
		
		for (OfertaBeneficio ofertaBeneficio : newOfertaLaboral.getOfertaBeneficios()) {
			if(ofertaBeneficio.getBeneficio().getBeneId() == newOfertaBeneficio.getBeneficio().getBeneId()){
				newOfertaLaboral.removeOfertaBeneficio(ofertaBeneficio);				
				break;
			}
		}
	}
	
	// Oferta Centro estudio
	public void reiniciarNewOfertaCentroEstudio() {
		newOfertaCentroEstudio = new OfertaCentroEstudio();
		newOfertaCentroEstudio.setOfertaLaboral(newOfertaLaboral);
		newOfertaCentroEstudio.setCentroEstudio(new CentroEstudio());
		newOfertaCentroEstudio.setId(new OfertaCentroEstudioPK());
	}

	public void saveOfertaCentroEstudio() {
		logger.info("guardando oferta centro de estudio");
		
		boolean agregar = true;
		
		for (OfertaCentroEstudio ofertaCentroEstudio : newOfertaLaboral.getOfertaCentroEstudios()) {
			if(ofertaCentroEstudio.getCentroEstudio().getCeesId() == newOfertaCentroEstudio.getCentroEstudio().getCeesId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			newOfertaLaboral.addOfertaCentroEstudio(newOfertaCentroEstudio);		
		}
	}

	public void deleteOfertaCentroEstudio() {
		logger.info("eliminando centro de estudio");
		
		for (OfertaCentroEstudio ofertaCentroEstudio : newOfertaLaboral.getOfertaCentroEstudios()) {
			if(ofertaCentroEstudio.getCentroEstudio().getCeesId() == newOfertaCentroEstudio.getCentroEstudio().getCeesId()){
				newOfertaLaboral.removeOfertaCentroEstudio(ofertaCentroEstudio);			
				break;
			}
		}
	}
	
	// Oferta Conocimiento
	public void reiniciarNewOfertaConocimiento() {
		newOfertaConocimiento = new OfertaConocimiento();
		newOfertaConocimiento.setOfertaLaboral(newOfertaLaboral);
		newOfertaConocimiento.setConocimiento(new Conocimiento());
		newOfertaConocimiento.setNivelConocimiento(new NivelConocimiento());
		newOfertaConocimiento.setId(new OfertaConocimientoPK());
	}

	public void saveOfertaConocimiento() {
		logger.info("guardando oferta conocimiento");
		
		boolean agregar = true;
		
		for (OfertaConocimiento ofertaConocimiento : newOfertaLaboral.getOfertaConocimientos()) {
			if(ofertaConocimiento.getConocimiento().getConoId() == newOfertaConocimiento.getConocimiento().getConoId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			newOfertaLaboral.addOfertaConocimiento(newOfertaConocimiento);		
		}
	}

	public void deleteOfertaConocimiento() {
		logger.info("eliminando Conocimiento");
		
		for (OfertaConocimiento ofertaConocimiento : newOfertaLaboral.getOfertaConocimientos()) {
			if(ofertaConocimiento.getConocimiento().getConoId() == newOfertaConocimiento.getConocimiento().getConoId()){
				newOfertaLaboral.removeOfertaConocimiento(ofertaConocimiento);		
				break;
			}
		}
	}	
	
	// Oferta Estudio
	public void reiniciarNewOfertaEstudio() {
		newOfertaEstudio = new OfertaEstudio();
		newOfertaEstudio.setOfertaLaboral(newOfertaLaboral);
		newOfertaEstudio.setEstudioGenerico(new EstudioGenerico());
		newOfertaEstudio.setId(new OfertaEstudioPK());
		
	}

	public void saveOfertaEstudio() {
		logger.info("guardando oferta estudio");
		
		boolean agregar = true;
		
		for (OfertaEstudio ofertaEstudio : newOfertaLaboral.getOfertaEstudios()) {
			if(ofertaEstudio.getEstudioGenerico().getEsgeId() == newOfertaEstudio.getEstudioGenerico().getEsgeId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			newOfertaLaboral.addOfertaEstudio(newOfertaEstudio);	
		}
	}

	public void deleteOfertaEstudio() {
		logger.info("eliminando oferta estudio");
		
		for (OfertaEstudio ofertaEstudio : newOfertaLaboral.getOfertaEstudios()) {
			if(ofertaEstudio.getEstudioGenerico().getEsgeId() == newOfertaEstudio.getEstudioGenerico().getEsgeId()){
				newOfertaLaboral.removeOfertaEstudio(ofertaEstudio);	
				break;
			}
		}
	}
	
	
	// Oferta idioma
	public void reiniciarNewOfertaIdioma() {
		newOfertaIdioma = new OfertaIdioma();
		newOfertaIdioma.setOfertaLaboral(newOfertaLaboral);
		newOfertaIdioma.setIdioma(new Idioma());
		newOfertaIdioma.setNivelIdioma(new NivelIdioma());
		newOfertaIdioma.setId(new OfertaIdiomaPK());
	}

	public void saveOfertaIdioma() {
		logger.info("guardando oferta idioma");
		
		boolean agregar = true;
		
		for (OfertaIdioma ofertaIdioma : newOfertaLaboral.getOfertaIdiomas()) {
			if(ofertaIdioma.getIdioma().getIdioId() == newOfertaIdioma.getIdioma().getIdioId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			newOfertaLaboral.addOfertaIdioma(newOfertaIdioma);		
		}
	}

	public void deleteOfertaIdioma() {
		logger.info("eliminando oferta idioma");
		
		for (OfertaIdioma ofertaIdioma : newOfertaLaboral.getOfertaIdiomas()) {
			if(ofertaIdioma.getIdioma().getIdioId() == newOfertaIdioma.getIdioma().getIdioId()){
				newOfertaLaboral.removeOfertaIdioma(ofertaIdioma);
				break;
			}
		}
	}
			
	//getters and setters

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

	public OfertaCentroEstudio getNewOfertaCentroEstudio() {
		return newOfertaCentroEstudio;
	}

	public void setNewOfertaCentroEstudio(OfertaCentroEstudio newOfertaCentroEstudio) {
		this.newOfertaCentroEstudio = newOfertaCentroEstudio;
	}

	public OfertaEstudio getNewOfertaEstudio() {
		return newOfertaEstudio;
	}

	public void setNewOfertaEstudio(OfertaEstudio newOfertaEstudio) {
		this.newOfertaEstudio = newOfertaEstudio;
	}

	public OfertaBeneficio getNewOfertaBeneficio() {
		return newOfertaBeneficio;
	}

	public void setNewOfertaBeneficio(OfertaBeneficio newOfertaBeneficio) {
		this.newOfertaBeneficio = newOfertaBeneficio;
	}

	public OfertaConocimiento getNewOfertaConocimiento() {
		return newOfertaConocimiento;
	}

	public void setNewOfertaConocimiento(OfertaConocimiento newOfertaConocimiento) {
		this.newOfertaConocimiento = newOfertaConocimiento;
	}

	public OfertaIdioma getNewOfertaIdioma() {
		return newOfertaIdioma;
	}

	public void setNewOfertaIdioma(OfertaIdioma newOfertaIdioma) {
		this.newOfertaIdioma = newOfertaIdioma;
	}

	public ArrayList<NivelIdioma> getNivelesIdioma() {
		return nivelesIdioma;
	}

	public void setNivelesIdioma(ArrayList<NivelIdioma> nivelesIdioma) {
		this.nivelesIdioma = nivelesIdioma;
	}

	public ArrayList<TipoHorario> getTiposHorario() {
		return tiposHorario;
	}

	public void setTiposHorario(ArrayList<TipoHorario> tiposHorario) {
		this.tiposHorario = tiposHorario;
	}

	public ArrayList<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(ArrayList<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public ArrayList<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(ArrayList<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public ArrayList<NivelConocimiento> getNivelesConocimiento() {
		return nivelesConocimiento;
	}

	public void setNivelesConocimiento(
			ArrayList<NivelConocimiento> nivelesConocimiento) {
		this.nivelesConocimiento = nivelesConocimiento;
	}

	public ArrayList<EstudioGenerico> getEstudiosGenericos() {
		return estudiosGenericos;
	}

	public void setEstudiosGenericos(ArrayList<EstudioGenerico> estudiosGenericos) {
		this.estudiosGenericos = estudiosGenericos;
	}

	public ArrayList<CentroEstudio> getCentrosEstudio() {
		return centrosEstudio;
	}

	public void setCentrosEstudio(ArrayList<CentroEstudio> centrosEstudio) {
		this.centrosEstudio = centrosEstudio;
	}

	public ArrayList<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(ArrayList<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public ArrayList<Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(ArrayList<Puesto> puestos) {
		this.puestos = puestos;
	}	
}
