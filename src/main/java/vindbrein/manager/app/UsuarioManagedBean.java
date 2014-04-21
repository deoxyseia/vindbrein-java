package vindbrein.manager.app;

import java.io.IOException;
import java.io.Serializable;

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

	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioService usuarioService;

	private String username;
	private String password;

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
		
		String o = getOldPassword();
		String p = getPassword();
		
		if(getOldPassword().equals(getPassword())){
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
		
		Usuario usuario = getUsuarioService().getUsuarioByUsername(getUsername());
		usuario.setUsuaPassword(getNewPassword());
		
		getUsuarioService().updateUsuario(usuario);
		
		Util.lanzarMensaje("INFO", "GLOBAL","Se ha cambiado la contraseña correctamente");
	}
	
	public void cancelarCambioContrasenia(){
		logger.info("Cambio de contraseña cancelado");
		
		oldPassword = "";
		newPassword = "";
		newPasswordReload = "";
	}

	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getUsername() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		return user.getUsername();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		return user.getPassword();
	}

	public void setPassword(String password) {
		this.password = password;
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
