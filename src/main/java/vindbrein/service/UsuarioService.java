package vindbrein.service;

import java.util.List;

import vindbrein.domain.model.Usuario;



public interface UsuarioService {
	
public void addUsuario(Usuario usuario);
	
	public void updateUsuario(Usuario usuario);

	public void deleteUsuario(Usuario usuario);
	
	public Usuario getUsuarioById(int id);
	
	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioByUsername(String user);

}
