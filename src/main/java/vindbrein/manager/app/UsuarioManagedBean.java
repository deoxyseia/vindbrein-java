package vindbrein.manager.app;

import java.io.IOException;
import java.io.Serializable;

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

import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Reclutador;
import vindbrein.domain.model.Usuario;
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
	private Usuario usuarioReclutador;		
	private Usuario usuarioPostulante;
		
		
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
		
	@PostConstruct
	public void init() {	
		
		reiniciarDatosUsuarioPostulante();
		
		reiniciarDatosUsuarioOrganizacion();
	}
	
	public void reiniciarDatosUsuarioPostulante(){
		usuarioPostulante = new Usuario();
		usuarioPostulante.setUsuaFlagActivo((byte)1);
		usuarioPostulante.setPostulante(new Postulante());			
	}
	
	public void reiniciarDatosUsuarioOrganizacion(){
		Organizacion organizacion = new Organizacion();
				
		organizacion.setOrgaEstado("V");
		
		usuarioReclutador = new Usuario();
		usuarioReclutador.setUsuaFlagActivo((byte)1);
		usuarioReclutador.setReclutador(new Reclutador());
		usuarioReclutador.getReclutador().setReclFlagMaestro((byte)1);
		usuarioReclutador.getReclutador().setOrganizacion(organizacion);	
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
		
		usuarioService.addUsuarioPostulante(usuarioPostulante);
				
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha creado un nuevo usuario");	
	}
	
	public void verifiedSaveUsuarioOrganizacion(){
		boolean verificado = false;
		
		if(getNewPassword().equals(getNewPasswordReload())){
			getUsuarioReclutador().setUsuaContrasenia(getNewPassword());
			verificado = true;
		}else{
			Util.lanzarMensaje("ERROR", "GLOBAL", "Las contraseñas no coinciden, vuelva a probar");
		}
		
		Util.lanzarVariableAInterfaz("verificado", verificado);
	}
	
	public void saveUsuarioOrganizacion(){
		logger.info("Agregando nuevo usuario reclutador");	
				
		usuarioService.addUsuarioOrganizacion(usuarioReclutador);
				
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha creado una nueva organización");	
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
		
		Usuario usuario = usuarioService.getUsuarioByUsername(user.getUsername());
		usuario.setUsuaContrasenia(getNewPassword());
		
		usuarioService.updateUsuario(usuario);
		
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha cambiado la contraseña correctamente");
	}
	
	public void cancelarCambioContrasenia(){
		logger.info("Cambio de contraseña cancelado");
		
		oldPassword = "";
		newPassword = "";
		newPasswordReload = "";
	}
	
	//getters and setters	
	public Usuario getUsuarioReclutador() {
		return usuarioReclutador;
	}

	public void setUsuarioReclutador(Usuario usuarioReclutador) {
		this.usuarioReclutador = usuarioReclutador;
	}

	public void setUsuarioPostulante(Usuario usuarioPostulante) {
		this.usuarioPostulante = usuarioPostulante;
	}
	
	public Usuario getUsuarioPostulante(){
		return usuarioPostulante;
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
}
