package vindbrein.manager.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.DimensionOrganizacion;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Provincia;
import vindbrein.domain.model.RespRrhh;
import vindbrein.domain.model.Sector;
import vindbrein.domain.model.Sucursal;
import vindbrein.domain.model.Usuario;
import vindbrein.service.DepartamentoService;
import vindbrein.service.DimensionOrganizacionService;
import vindbrein.service.DistritoService;
import vindbrein.service.ProvinciaService;
import vindbrein.service.SectorService;
import vindbrein.service.UsuarioService;
import vindbrein.util.Util;

@Controller
@Scope("session")
public class UsuarioManagedBean implements Serializable {
	
	private static final Logger logger = Logger
			.getLogger(UsuarioManagedBean.class);

	private static final long serialVersionUID = 1L;
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordReload;
	
	//datos para registro
	private Usuario usuarioRespRrhh;		
	private Usuario usuarioPostulante;
	
	//datos maestros
	private ArrayList<Sector> sectores;	
	private ArrayList<DimensionOrganizacion> dimensionesOrganizacion;
	private ArrayList<Departamento> departamentos;
	private ArrayList<Provincia> provincias;
	private ArrayList<Distrito> distritos;
	
	private Departamento selectedDepartamento;
	private Provincia selectedProvincia;
	private Distrito selectedDistrito;
		
	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("sectorServiceImpl")
	SectorService sectorService;
	
	@Autowired
	@Qualifier("dimensionOrganizacionServiceImpl")
	DimensionOrganizacionService dimensionOrganizacionService;
	
	@Autowired
	@Qualifier("departamentoServiceImpl")
	DepartamentoService departamentoService;
	
	@Autowired
	@Qualifier("provinciaServiceImpl")
	ProvinciaService provinciaService;
	
	@Autowired
	@Qualifier("distritoServiceImpl")
	DistritoService distritoService;
	
	@PostConstruct
	public void init() {
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();
		
		iniciarDatosUsuarioPostulante();
		
		iniciarDatosUsuarioOrganizacion();
	}
	
	public void iniciarDatosUsuarioPostulante(){
		usuarioPostulante = new Usuario();
		usuarioPostulante.setPostulante(new Postulante());			
	}
	
	public void iniciarDatosUsuarioOrganizacion(){
		Sucursal sucursal = new Sucursal();
		sucursal.setOrganizacion(new Organizacion());
		sucursal.getOrganizacion().setDimensionOrganizacion(new DimensionOrganizacion());
		sucursal.getOrganizacion().setSector(new Sector());
		sucursal.setDistrito(new Distrito());
		
		usuarioRespRrhh = new Usuario();
		usuarioRespRrhh.setRespRrhh(new RespRrhh());
		usuarioRespRrhh.getRespRrhh().setSucursal(sucursal);				
		
		//otros datos necesario
		sectores = getSectorService().getSectores();		
		dimensionesOrganizacion = getDimensionOrganizacionService().getDimensionesOrganizacion();
		departamentos = getDepartamentoService().getDepartamentos();
	}
		
	public void verifiedSaveUsuarioPostulante(){
		boolean verificado = false;
		
		if(getNewPassword().equals(getNewPasswordReload())){
			getUsuarioPostulante().setUsuaContrasenia(getNewPassword());
			verificado = true;
		}else{
			Util.lanzarMensaje("ERROR", "GLOBAL", "Las contraseñas no coinciden, vuelva a probar");
		}
		
		Util.lanzarVariableAInterfaz("verificado", verificado);
	}
	
	public void saveUsuarioPostulante(){
		logger.info("Agregando nuevo usuario de postulante");				
		
		getUsuarioService().addUsuarioPostulante(usuarioPostulante);
				
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha creado un nuevo usuario");	
	}
	
	public void verifiedSaveUsuarioOrganizacion(){
		boolean verificado = false;
		
		if(getNewPassword().equals(getNewPasswordReload())){
			getUsuarioRespRrhh().setUsuaContrasenia(getNewPassword());
			verificado = true;
		}else{
			Util.lanzarMensaje("ERROR", "GLOBAL", "Las contraseñas no coinciden, vuelva a probar");
		}
		
		Util.lanzarVariableAInterfaz("verificado", verificado);
	}
	
	public void saveUsuarioOrganizacion(){
		logger.info("Agregando nuevo usuario de postulante");	
		
		usuarioRespRrhh.getRespRrhh().getSucursal().setDistrito(selectedDistrito);
		
		getUsuarioService().addUsuarioOrganizacion(usuarioRespRrhh);
				
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha creado un nuevo usuario");	
	}
	
	public void chargeProvincias(){
		logger.info("Cargando pronvincias");
		
		provincias = getProvinciaService().getProvinciasByDepartamento(selectedDepartamento);
	}
	
	public void chargeDistritos(){
		logger.info("Cargando distritos");
		
		distritos = getDistritoService().getDistritosByProvincia(selectedProvincia);
	}

	public void login() throws IOException, ServletException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(),
				(ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void verificarCambioContrasenia(){
		logger.info("Verificando la factibilidad de cambio de contraseña");
		
		boolean verificado = false;
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
				
		if(getOldPassword().equals(user.getPassword())){
			if(getNewPassword().equals(getNewPasswordReload())){
				if(getNewPassword().equals(getOldPassword())){
					Util.lanzarMensaje("ERROR", "LOCAL", "la nueva contraseña no puede ser igual a la actual");
				}else{
					verificado = true;
				}					
			}else{
				Util.lanzarMensaje("ERROR", "LOCAL", "Las contraseñas no coinciden");
			}
		}else{
			Util.lanzarMensaje("ERROR", "LOCAL", "La contraseña actual es incorrecta");
		}
		
		Util.lanzarVariableAInterfaz("verificado", verificado);	
	}
	
	public void cambiarContrasenia(){
		logger.info("Cambiando contraseña");
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Usuario usuario = getUsuarioService().getUsuarioByUsername(user.getUsername());
		usuario.setUsuaContrasenia(getNewPassword());
		
		getUsuarioService().updateUsuario(usuario);
		
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha cambiado la contraseña correctamente");
	}
	
	public void cancelarCambioContrasenia(){
		logger.info("Cambio de contraseña cancelado");
		
		oldPassword = "";
		newPassword = "";
		newPasswordReload = "";
	}
	
	//getters and setters		
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public Usuario getUsuarioRespRrhh() {
		return usuarioRespRrhh;
	}

	public void setUsuarioRespRrhh(Usuario usuarioRespRrhh) {
		this.usuarioRespRrhh = usuarioRespRrhh;
	}	

	public Usuario getUsuarioPostulante() {
		return usuarioPostulante;
	}

	public void setUsuarioPostulante(Usuario usuarioPostulante) {
		this.usuarioPostulante = usuarioPostulante;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}	
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordReload() {
		return newPasswordReload;
	}

	public void setNewPasswordReload(String newPasswordReload) {
		this.newPasswordReload = newPasswordReload;
	}	

	public ArrayList<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(ArrayList<Sector> sectores) {
		this.sectores = sectores;
	}

	public SectorService getSectorService() {
		return sectorService;
	}

	public void setSectorService(SectorService sectorService) {
		this.sectorService = sectorService;
	}

	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion() {
		return dimensionesOrganizacion;
	}

	public void setDimensionesOrganizacion(
			ArrayList<DimensionOrganizacion> dimensionesOrganizacion) {
		this.dimensionesOrganizacion = dimensionesOrganizacion;
	}

	public DimensionOrganizacionService getDimensionOrganizacionService() {
		return dimensionOrganizacionService;
	}

	public void setDimensionOrganizacionService(
			DimensionOrganizacionService dimensionOrganizacionService) {
		this.dimensionOrganizacionService = dimensionOrganizacionService;
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
}
