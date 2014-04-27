package vindbrein.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Usuario;

@Repository
public class UsuarioDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void addUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().save(usuario);		
	}

	public void updateUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);
		
	}

	public void deleteUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);
		
	}

	public Usuario getUsuarioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where usuaId=?")
		        .setParameter(0, id).list();
		return (Usuario)list.get(0);
	}

	public List<Usuario> getUsuarios() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Usuario").list();
		return list;
	}
	
	public Usuario getUsuarioByUsername(String user){
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where usuaNombre=?")
		        .setParameter(0, user).list();
		return (Usuario)list.get(0);
	}
}
