package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteConocimiento;

@Repository
public class PostulanteConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addPostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getSessionFactory().getCurrentSession().save(postulanteConocimiento);		
	}

	public void updatePostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getSessionFactory().getCurrentSession().update(postulanteConocimiento);		
	}

	public void deletePostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getSessionFactory().getCurrentSession().delete(postulanteConocimiento);		
	}	

	//no verificado ni hecho de veras xD!
	public PostulanteConocimiento getPostulanteConocimientoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from PostulanteConocimiento where depaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (PostulanteConocimiento)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<PostulanteConocimiento> getPostulanteConocimientos() {
		ArrayList<PostulanteConocimiento> list = (ArrayList<PostulanteConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from PostulanteConocimiento order by conocimiento.conoNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<PostulanteConocimiento> getPostulanteConocimientoByPostulante(
			Postulante postulante) {
		ArrayList<PostulanteConocimiento> list = (ArrayList<PostulanteConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from PostulanteConocimiento where postulante.postId=?")
				.setParameter(0, postulante.getPostId()).list();

		return list;
	}
}
