package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Telefono;

@Repository
public class TelefonoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addTelefono(Telefono telefono) {
		getSessionFactory().getCurrentSession().save(telefono);		
	}

	public void updateTelefono(Telefono telefono) {
		getSessionFactory().getCurrentSession().update(telefono);		
	}

	public void deleteTelefono(Telefono telefono) {
		getSessionFactory().getCurrentSession().delete(telefono);		
	}

	public Telefono getTelefonoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Telefono where depaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Telefono)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Telefono> getTelefonos() {
		ArrayList<Telefono> list = (ArrayList<Telefono>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Telefono order by depaNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<Telefono> getTelefonosByPostulante(Postulante postulante) {
		ArrayList<Telefono> list = (ArrayList<Telefono>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Telefono where postulante.postId=? order by postulante.postId asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
