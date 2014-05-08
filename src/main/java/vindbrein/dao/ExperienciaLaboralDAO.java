package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.ExperienciaLaboral;
import vindbrein.domain.model.Postulante;

@Repository
public class ExperienciaLaboralDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getSessionFactory().getCurrentSession().save(experienciaLaboral);		
	}

	public void updateExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getSessionFactory().getCurrentSession().update(experienciaLaboral);		
	}

	public void deleteExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getSessionFactory().getCurrentSession().delete(experienciaLaboral);		
	}

	public ExperienciaLaboral getExperienciaLaboralById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from ExperienciaLaboral where exlaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (ExperienciaLaboral)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<ExperienciaLaboral> getExperienciasLaborales() {
		ArrayList<ExperienciaLaboral> list = (ArrayList<ExperienciaLaboral>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from ExperienciaLaboral order by exlaFechaInicio asc")
				.list();
		return list;
	}	
	
	public ArrayList<ExperienciaLaboral> getExperienciasLaboralesByPostulante(Postulante postulante) {
		ArrayList<ExperienciaLaboral> list = (ArrayList<ExperienciaLaboral>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from ExperienciaLaboral where postulante.postId=? order by exlaFechaInicio asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
