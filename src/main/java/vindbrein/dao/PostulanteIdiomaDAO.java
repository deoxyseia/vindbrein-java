package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteIdioma;

@Repository
public class PostulanteIdiomaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addPostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getSessionFactory().getCurrentSession().save(postulanteIdioma);		
	}

	public void updatePostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getSessionFactory().getCurrentSession().update(postulanteIdioma);		
	}

	public void deletePostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getSessionFactory().getCurrentSession().delete(postulanteIdioma);		
	}

	//sin probar
	public PostulanteIdioma getPostulanteIdiomaById(Idioma idioma, NivelIdioma nivelIdioma, Postulante postulante) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from PostulanteIdioma where idioma.idioId=? and nivelIdioma.niidId=? and postulante.postId=?");
		
		query.setParameter(0, idioma.getIdioId());
		query.setParameter(1, nivelIdioma.getNiidId());
		query.setParameter(2, postulante.getPostId());
		
		List<PostulanteIdioma> list = (ArrayList<PostulanteIdioma>)query.list();		        
		
		if(list.size()!=0){
			return (PostulanteIdioma)list.get(0);
		} else {
			return null;
		}
	}	
	
	public ArrayList<PostulanteIdioma> getPostulanteIdiomas() {
		ArrayList<PostulanteIdioma> list = (ArrayList<PostulanteIdioma>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from PostulanteIdioma order by idioma.idioNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<PostulanteIdioma> getPostulanteIdiomasByPostulante(Postulante postulante) {
		ArrayList<PostulanteIdioma> list = (ArrayList<PostulanteIdioma>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from PostulanteIdioma where postulante.postId=? order by idioma.idioNombre asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
