package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteBeneficio;
import vindbrein.domain.model.PostulanteIdioma;

@Repository
public class PostulanteBeneficioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addPostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getSessionFactory().getCurrentSession().save(postulanteBeneficio);		
	}

	public void updatePostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getSessionFactory().getCurrentSession().update(postulanteBeneficio);		
	}

	public void deletePostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getSessionFactory().getCurrentSession().delete(postulanteBeneficio);		
	}	
	
	public PostulanteBeneficio getPostulanteBeneficioById(Postulante postulante, Beneficio beneficio) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from PostulanteBeneficio where postulante.postId=? and beneficio.beneId=?");
		
		query.setParameter(0, postulante.getPostId());
		query.setParameter(1, beneficio.getBeneId());
		
		List<PostulanteBeneficio> list = (ArrayList<PostulanteBeneficio>)query.list();        
		
		if(list.size()!=0){
			return (PostulanteBeneficio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<PostulanteBeneficio> getPostulanteBeneficios() {
		ArrayList<PostulanteBeneficio> list = (ArrayList<PostulanteBeneficio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from PostulanteBeneficio order by beneficio.beneNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<PostulanteBeneficio> getPostulanteBeneficioByPostulante(Postulante postulante) {
		ArrayList<PostulanteBeneficio> list = (ArrayList<PostulanteBeneficio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from PostulanteBeneficio where postulante.postId=? order by beneficio.beneNombre asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
