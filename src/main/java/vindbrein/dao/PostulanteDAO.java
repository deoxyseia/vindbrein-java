package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Postulante;

@Repository
public class PostulanteDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public void addPostulante(Postulante postulante) {
		getSessionFactory().getCurrentSession().save(postulante);		
	}

	public void updatePostulante(Postulante postulante) {
		getSessionFactory().getCurrentSession().update(postulante);		
	}

	public void deletePostulante(Postulante postulante) {
		getSessionFactory().getCurrentSession().delete(postulante);		
	}

	public Postulante getPostulanteById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Postulante where postId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Postulante)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Postulante> getPostulantes() {
		ArrayList<Postulante> list = (ArrayList<Postulante>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Postulante order by postNombres asc")
				.list();
		return list;
	}
	
	public Postulante getPostulanteByCorreo(String correo) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Postulante p where p.usuario.usuaCorreo=?")
				.setParameter(0, correo).list();

		if (list.size() != 0) {
			return (Postulante) list.get(0);
			
		} else {
			return null;
		}
	}
	
	///por mientras
	public Postulante getAlgunPostulante() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Postulante").list();
		
		if(list.size()!=0){
			return (Postulante)list.get(0);
		} else {
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
