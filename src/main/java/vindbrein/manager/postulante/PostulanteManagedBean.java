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

import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.domain.model.PostulanteConocimientoPK;
import vindbrein.domain.model.Provincia;
import vindbrein.domain.model.Residencia;
import vindbrein.domain.model.Telefono;
import vindbrein.service.ConocimientoService;
import vindbrein.service.DepartamentoService;
import vindbrein.service.DistritoService;
import vindbrein.service.PostulanteService;
import vindbrein.service.ProvinciaService;
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
	DepartamentoService departamentoService;
	
	@Autowired
	@Qualifier("provinciaServiceImpl")
	ProvinciaService provinciaService;
	
	@Autowired
	@Qualifier("distritoServiceImpl")
	DistritoService distritoService;
	
	@Autowired
	@Qualifier("conocimientoServiceImpl")
	ConocimientoService conocimientoService;
	
	// datos maestros	
	private ArrayList<Departamento> departamentos;
	private ArrayList<Provincia> provincias;
	private ArrayList<Distrito> distritos;
	private ArrayList<Conocimiento> conocimientos;

	private Departamento selectedDepartamento;
	private Provincia selectedProvincia;
	private Distrito selectedDistrito;
	private Conocimiento selectedConocimiento;
		
	@PostConstruct
	public void init() {		
		cargarPostulante();	
		
		iniciarDatosMaestros();
			
	}
	
	private void cargarPostulante(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		postulante = getPostulanteService().getPostulanteByUsername(user.getUsername());
		postulante = getPostulanteService().getPostulanteCompletoByPostulante(postulante);
	}
	
	private void iniciarDatosMaestros(){
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();
		selectedConocimiento = new Conocimiento();
		
		departamentos = getDepartamentoService().getDepartamentos();
		conocimientos = getConocimientoService().getConocimientos();
		
		reiniciarNewTelefono();
		reiniciarNewResidencia();
		reiniciarNewPostulanteConocimiento();
	}
	
	public void chargeProvincias(){
		logger.info("Cargando pronvincias");
		
		provincias = getProvinciaService().getProvinciasByDepartamento(selectedDepartamento);
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
		newResidencia.setResiEstado((byte)1);
		
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
	}
	
	public void savePostulanteConocimiento(){		
		newPostulanteConocimiento.setConocimiento(selectedConocimiento);
		
		if(newPostulanteConocimiento.getId().getFkConoId()==0 &&
				newPostulanteConocimiento.getId().getFkPostId()==0){
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

	public ArrayList<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(ArrayList<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public Conocimiento getSelectedConocimiento() {
		return selectedConocimiento;
	}

	public void setSelectedConocimiento(Conocimiento selectedConocimiento) {
		this.selectedConocimiento = selectedConocimiento;
	}

	public PostulanteConocimiento getNewPostulanteConocimiento() {
		return newPostulanteConocimiento;
	}

	public void setNewPostulanteConocimiento(
			PostulanteConocimiento newPostulanteConocimiento) {
		this.newPostulanteConocimiento = newPostulanteConocimiento;
	}
	
	
}
