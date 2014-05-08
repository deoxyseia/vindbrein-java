package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Residencia;

@Repository
public class ResidenciaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addResidencia(Residencia residencia) {
		getSessionFactory().getCurrentSession().save(residencia);		
	}

	public void updateResidencia(Residencia residencia) {
		getSessionFactory().getCurrentSession().update(residencia);		
	}

	public void deleteResidencia(Residencia residencia) {
		getSessionFactory().getCurrentSession().delete(residencia);		
	}

	public Residencia getResidenciaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Residencia where resiId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Residencia)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Residencia> getResidencias() {
		ArrayList<Residencia> list = (ArrayList<Residencia>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Residencia order by resiDireccion asc")
				.list();
		return list;
	}	
	
	public ArrayList<Residencia> getResidenciasByPostulante(Postulante postulante) {
		ArrayList<Residencia> list = (ArrayList<Residencia>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Residencia where postulante.postId=? order by resiDireccion asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
