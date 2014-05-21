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

import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.EstadoCivil;
import vindbrein.domain.model.ExperienciaLaboral;
import vindbrein.domain.model.NivelConocimiento;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.domain.model.PostulanteConocimientoPK;
import vindbrein.domain.model.Provincia;
import vindbrein.domain.model.Puesto;
import vindbrein.domain.model.Residencia;
import vindbrein.domain.model.Telefono;
import vindbrein.service.BeneficioService;
import vindbrein.service.ConocimientoService;
import vindbrein.service.DepartamentoService;
import vindbrein.service.DistritoService;
import vindbrein.service.EstadoCivilService;
import vindbrein.service.ExperienciaLaboralService;
import vindbrein.service.OrganizacionService;
import vindbrein.service.PostulanteService;
import vindbrein.service.ProvinciaService;
import vindbrein.service.PuestoService;
import vindbrein.service.ResidenciaService;
import vindbrein.service.TelefonoService;

@Controller
@Scope("session")
public class PostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(PostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private Postulante postulante;
	
	//telefono	
	private Telefono newTelefono;
	private Residencia newResidencia;
	private PostulanteConocimiento newPostulanteConocimiento;
	private ExperienciaLaboral newExperienciaLaboral;
	
			
	@Autowired
	@Qualifier("postulanteServiceImpl")
	private PostulanteService postulanteService;
	
	@Autowired
	@Qualifier("telefonoServiceImpl")
	private TelefonoService telefonoService;
	
	@Autowired
	@Qualifier("residenciaServiceImpl")
	private ResidenciaService residenciaService;
	
	@Autowired
	@Qualifier("departamentoServiceImpl")
	private DepartamentoService departamentoService;
	
	@Autowired
	@Qualifier("provinciaServiceImpl")
	ProvinciaService provinciaService;
	
	@Autowired
	@Qualifier("distritoServiceImpl")
	DistritoService distritoService;
	
	@Autowired
	@Qualifier("conocimientoServiceImpl")
	ConocimientoService conocimientoService;
		
	@Autowired
	@Qualifier("puestoServiceImpl")
	PuestoService puestoService;
	
	@Autowired
	@Qualifier("experienciaLaboralServiceImpl")
	ExperienciaLaboralService experienciaLaboralService;
	
	@Autowired
	@Qualifier("estadoCivilServiceImpl")
	EstadoCivilService estadoCivilService;
	
	@Autowired
	@Qualifier("beneficioServiceImpl")
	BeneficioService beneficioService;
	
	@Autowired
	@Qualifier("organizacionServiceImpl")
	OrganizacionService organizacionService;
	
	// datos maestros	
	private ArrayList<Departamento> departamentos;
	private ArrayList<Provincia> provincias;
	private ArrayList<Distrito> distritos;
	private ArrayList<Conocimiento> conocimientos;
	private ArrayList<Puesto> puestos;
	private ArrayList<EstadoCivil> estadosCiviles;
	private ArrayList<Beneficio> beneficios;
	private ArrayList<NivelConocimiento> nivelesConocimiento;
	private ArrayList<Organizacion> organizaciones;

	private Departamento selectedDepartamento;
	private Provincia selectedProvincia;
	private Distrito selectedDistrito;	
	
		
	@PostConstruct
	public void init() {		
		cargarPostulante();	
		
		iniciarDatosMaestros();			
	}
	
	private void cargarPostulante(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		postulante = getPostulanteService().getPostulanteByCorreo(user.getUsername());
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
		
		if(postulante.getEstadoCivil() == null){
			postulante.setEstadoCivil(new EstadoCivil());
		}
	}
	
	private void iniciarDatosMaestros(){
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();	
		
		departamentos = getDepartamentoService().getDepartamentos();
		conocimientos = getConocimientoService().getConocimientos();
		puestos = getPuestoService().getPuestos();
		estadosCiviles = getEstadoCivilService().getEstadosCiviles();
		nivelesConocimiento = getConocimientoService().getNivelesConocimiento();
		organizaciones = getOrganizacionService().getOrganizaciones();
		
		reiniciarNewTelefono();
		reiniciarNewResidencia();
		reiniciarNewPostulanteConocimiento();
		reiniciarNewExperienciaLaboral();		
	}
	
	public void savePostulante(){
		getPostulanteService().updatePostulante(postulante);
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void cancelSavePostulante(){
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void chargeProvincias(){
		logger.info("Cargando pronvincias");
		
		provincias = getProvinciaService().getProvinciasByDepartamento(selectedDepartamento);
		distritos = null;
	}
	
	public void chargeDistritos(){
		logger.info("Cargando distritos");
		
		distritos = getDistritoService().getDistritosByProvincia(selectedProvincia);
	}
	
	//telefono
	public void reiniciarNewTelefono(){
		newTelefono = new Telefono();
	}	
	
	public void saveTelefono(){		
		newTelefono.setPostulante(postulante);
		
		if(newTelefono.getTeleId()==0){
			telefonoService.addTelefono(newTelefono);
		}else{
			telefonoService.updateTelefono(newTelefono);
		}		
		
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void deleteTelefono(){		
		if(newTelefono!= null){
			telefonoService.deleteTelefono(newTelefono);
			postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
		}	
	}
	
	//residencia
	public void reiniciarNewResidencia(){
		newResidencia = new Residencia();
		newResidencia.setResiActivo((byte)1);
		
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();
		
		provincias = null;
		distritos = null;
		
	}
	
	public void saveResidencia(){		
		newResidencia.setPostulante(postulante);
		newResidencia.setDistrito(selectedDistrito);
		
		if(newResidencia.getResiId()==0){
			residenciaService.addResidencia(newResidencia);
		}else{
			residenciaService.updateResidencia(newResidencia);
		}		
		
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void loadResidencia(){
		
		newResidencia = getResidenciaService().getResidenciaById(newResidencia.getResiId());
		
		selectedDistrito = newResidencia.getDistrito();
		selectedProvincia = newResidencia.getDistrito().getProvincia();
		selectedDepartamento = newResidencia.getDistrito().getProvincia().getDepartamento();
		
		provincias = getProvinciaService().getProvinciasByDepartamento(selectedDepartamento);
		distritos = getDistritoService().getDistritosByProvincia(selectedProvincia);
	}
	
	public void deleteResidencia(){	
		if(newResidencia != null){
			residenciaService.deleteResidencia(newResidencia);
			postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
		}		
	}
	
	//conocimiento
	public void reiniciarNewPostulanteConocimiento(){
		newPostulanteConocimiento = new PostulanteConocimiento();
		newPostulanteConocimiento.setId(new PostulanteConocimientoPK());
		
		newPostulanteConocimiento.setPostulante(postulante);	
		
		newPostulanteConocimiento.setConocimiento(new Conocimiento());
		newPostulanteConocimiento.setNivelConocimiento(new NivelConocimiento());
	}
	
	public void savePostulanteConocimiento(){		
				
		if(newPostulanteConocimiento.getId().getFkConoId()==0){
			getConocimientoService().addConocimientoToPostulante(newPostulanteConocimiento);
		}else{
			getConocimientoService().updateConocimientoToPostulante(newPostulanteConocimiento);
		}		
		
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void deletePostulanteConocimiento(){	
		if(newPostulanteConocimiento != null){
			getConocimientoService().deleteConocimientoToPostulante(newPostulanteConocimiento);
			postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
		}		
	}
	
	//experiencia laboral
	public void reiniciarNewExperienciaLaboral(){
		OrganizacionPuesto organizacionPuesto = new OrganizacionPuesto();
		organizacionPuesto.setPuesto(new Puesto());
		organizacionPuesto.setOrganizacion(new Organizacion());
		
		newExperienciaLaboral = new ExperienciaLaboral();
		newExperienciaLaboral.setPostulante(postulante);
		newExperienciaLaboral.setOrganizacionPuesto(organizacionPuesto);
	}
	
	public void saveExperienciaLaboral(){		
		if(newExperienciaLaboral.getExlaId()==0){
			getExperienciaLaboralService().addExperienciaLaboral(newExperienciaLaboral);
		}else{
			getExperienciaLaboralService().updateExperienciaLaboral(newExperienciaLaboral);			
		}
		
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	public void deleteExperienciaLaboral(){
		if(newExperienciaLaboral != null){
			getExperienciaLaboralService().deleteExperienciaLaboral(newExperienciaLaboral);
			postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
		}
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

	public Residencia getNewResidencia() {
		return newResidencia;
	}

	public void setNewResidencia(Residencia newResidencia) {
		this.newResidencia = newResidencia;
	}

	public ResidenciaService getResidenciaService() {
		return residenciaService;
	}

	public void setResidenciaService(ResidenciaService residenciaService) {
		this.residenciaService = residenciaService;
	}

	public Telefono getNewTelefono() {
		return newTelefono;
	}

	public void setNewTelefono(Telefono newTelefono) {
		this.newTelefono = newTelefono;
	}	

	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}

	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	public ProvinciaService getProvinciaService() {
		return provinciaService;
	}

	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	public DistritoService getDistritoService() {
		return distritoService;
	}

	public void setDistritoService(DistritoService distritoService) {
		this.distritoService = distritoService;
	}	

	public PuestoService getPuestoService() {
		return puestoService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}	

	public ExperienciaLaboralService getExperienciaLaboralService() {
		return experienciaLaboralService;
	}

	public void setExperienciaLaboralService(
			ExperienciaLaboralService experienciaLaboralService) {
		this.experienciaLaboralService = experienciaLaboralService;
	}

	public ArrayList<Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(ArrayList<Puesto> puestos) {
		this.puestos = puestos;
	}

	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
	}

	public ArrayList<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(ArrayList<Distrito> distritos) {
		this.distritos = distritos;
	}

	public Departamento getSelectedDepartamento() {
		return selectedDepartamento;
	}

	public void setSelectedDepartamento(Departamento selectedDepartamento) {
		this.selectedDepartamento = selectedDepartamento;
	}

	public Provincia getSelectedProvincia() {
		return selectedProvincia;
	}

	public void setSelectedProvincia(Provincia selectedProvincia) {
		this.selectedProvincia = selectedProvincia;
	}

	public Distrito getSelectedDistrito() {
		return selectedDistrito;
	}

	public void setSelectedDistrito(Distrito selectedDistrito) {
		this.selectedDistrito = selectedDistrito;
	}

	public ConocimientoService getConocimientoService() {
		return conocimientoService;
	}

	public void setConocimientoService(ConocimientoService conocimientoService) {
		this.conocimientoService = conocimientoService;
	}

	public EstadoCivilService getEstadoCivilService() {
		return estadoCivilService;
	}

	public void setEstadoCivilService(EstadoCivilService estadoCivilService) {
		this.estadoCivilService = estadoCivilService;
	}

	public ArrayList<EstadoCivil> getEstadosCiviles() {
		return estadosCiviles;
	}

	public void setEstadosCiviles(ArrayList<EstadoCivil> estadosCiviles) {
		this.estadosCiviles = estadosCiviles;
	}

	public ArrayList<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(ArrayList<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public PostulanteConocimiento getNewPostulanteConocimiento() {
		return newPostulanteConocimiento;
	}

	public void setNewPostulanteConocimiento(
			PostulanteConocimiento newPostulanteConocimiento) {
		this.newPostulanteConocimiento = newPostulanteConocimiento;
	}

	public ExperienciaLaboral getNewExperienciaLaboral() {
		return newExperienciaLaboral;
	}

	public void setNewExperienciaLaboral(ExperienciaLaboral newExperienciaLaboral) {
		this.newExperienciaLaboral = newExperienciaLaboral;
	}

	public BeneficioService getBeneficioService() {
		return beneficioService;
	}

	public void setBeneficioService(
			BeneficioService beneficioService) {
		this.beneficioService = beneficioService;
	}	

	public OrganizacionService getOrganizacionService() {
		return organizacionService;
	}

	public void setOrganizacionService(OrganizacionService organizacionService) {
		this.organizacionService = organizacionService;
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

	public ArrayList<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(ArrayList<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}		
}
