package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.ActividadAcademica;
import vindbrein.domain.model.Postulante;

@Repository
public class ActividadAcademicaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addActividadAcademica(ActividadAcademica actividadAcademica) {
		getSessionFactory().getCurrentSession().save(actividadAcademica);		
	}

	public void updateActividadAcademica(ActividadAcademica actividadAcademica) {
		getSessionFactory().getCurrentSession().update(actividadAcademica);		
	}

	public void deleteActividadAcademica(ActividadAcademica actividadAcademica) {
		getSessionFactory().getCurrentSession().delete(actividadAcademica);		
	}
	
	
	//sin probar
	public ActividadAcademica getActividadAcademicaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from ActividadAcademica where depaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (ActividadAcademica)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<ActividadAcademica> getActividadesAcademicas() {
		ArrayList<ActividadAcademica> list = (ArrayList<ActividadAcademica>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from ActividadAcademica order by depaNombre asc")
				.list();
		return list;
	}	
	
	public ArrayList<ActividadAcademica> getActividadesAcademicasByPostulante(Postulante postulante) {
		ArrayList<ActividadAcademica> list = (ArrayList<ActividadAcademica>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from ActividadAcademica where postulante.postId=? order by acacFechaInicio asc")
				.setParameter(0, postulante.getPostId()).list();
		
		return list;
	}
}
