package vindbrein.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.OrganizacionDAO;
import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.ReclutadorDAO;
import vindbrein.dao.SucursalDAO;
import vindbrein.dao.UsuarioDAO;
import vindbrein.domain.model.Usuario;
import vindbrein.service.UsuarioService;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	PostulanteDAO postulanteDAO;
	
	@Autowired
	OrganizacionDAO organizacionDAO;
	
	@Autowired
	SucursalDAO sucursalDAO;
	
	@Autowired
	ReclutadorDAO reclutadorDAO;
	
	
	@Transactional(readOnly = false)
	public void addUsuario(Usuario usuario) {
		getUsuarioDAO().addUsuario(usuario);
		
	}
	
	@Transactional(readOnly = false)
	public void updateUsuario(Usuario usuario) {
		getUsuarioDAO().updateUsuario(usuario);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteUsuario(Usuario usuario) {
		getUsuarioDAO().deleteUsuario(usuario);
		
	}

	public Usuario getUsuarioById(int id) {		
		return getUsuarioDAO().getUsuarioById(id);
	}

	public List<Usuario> getUsuarios() {		
		return getUsuarioDAO().getUsuarios();
	}
		
	public Usuario getUsuarioByUsername(String user){
		return getUsuarioDAO().getUsuarioByUsername(user.trim());
	}	
	
	@Transactional(readOnly = false)
	public void addUsuarioPostulante(Usuario usuario){
		getPostulanteDAO().addPostulante(usuario.getPostulante());
		getUsuarioDAO().addUsuario(usuario);
	}
	
	@Transactional(readOnly = false)
	public void addUsuarioOrganizacion(Usuario usuario){
		getOrganizacionDAO().addOrganizacion(usuario.getReclutador().getOrganizacion());
		getSucursalDAO().addSucursal(usuario.getReclutador().getOrganizacion().getSucursales().get(0));
		getReclutadorDAO().addReclutador(usuario.getReclutador());
		getUsuarioDAO().addUsuario(usuario);
	}
	
	//getters and setters

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public PostulanteDAO getPostulanteDAO() {
		return postulanteDAO;
	}

	public void setPostulanteDAO(PostulanteDAO postulanteDAO) {
		this.postulanteDAO = postulanteDAO;
	}

	public OrganizacionDAO getOrganizacionDAO() {
		return organizacionDAO;
	}

	public void setOrganizacionDAO(OrganizacionDAO organizacionDAO) {
		this.organizacionDAO = organizacionDAO;
	}

	public SucursalDAO getSucursalDAO() {
		return sucursalDAO;
	}

	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}

	public ReclutadorDAO getReclutadorDAO() {
		return reclutadorDAO;
	}

	public void setReclutadorDAO(ReclutadorDAO reclutadorDAO) {
		this.reclutadorDAO = reclutadorDAO;
	}
}
