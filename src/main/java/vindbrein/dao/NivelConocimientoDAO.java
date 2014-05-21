package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.NivelConocimiento;
import vindbrein.domain.model.Provincia;

@Repository
public class NivelConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addNivelConocimiento(NivelConocimiento nivelConocimiento) {
		getSessionFactory().getCurrentSession().save(nivelConocimiento);		
	}

	public void updateNivelConocimiento(NivelConocimiento nivelConocimiento) {
		getSessionFactory().getCurrentSession().update(nivelConocimiento);		
	}

	public void deleteNivelConocimiento(NivelConocimiento nivelConocimiento) {
		getSessionFactory().getCurrentSession().delete(nivelConocimiento);		
	}
		
	public NivelConocimiento getNivelConocimientoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from NivelConocimiento where nicoId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (NivelConocimiento)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<NivelConocimiento> getNivelesConocimiento() {
		ArrayList<NivelConocimiento> list = (ArrayList<NivelConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from NivelConocimiento order by nicoNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<NivelConocimiento> getNivelConocimientosByProvincia(Provincia provincia) {
		ArrayList list = (ArrayList<NivelConocimiento>) getSessionFactory().getCurrentSession()
				.createQuery("from NivelConocimiento d where d.provincia.provId=?")
		        .setParameter(0, provincia.getProvId()).list();
		
		return list;
	}
}
