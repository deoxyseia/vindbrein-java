package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Reclutador;

@Repository
public class ReclutadorDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addReclutador(Reclutador reclutador) {
		getSessionFactory().getCurrentSession().save(reclutador);		
	}

	public void updateReclutador(Reclutador reclutador) {
		getSessionFactory().getCurrentSession().update(reclutador);		
	}

	public void deleteReclutador(Reclutador reclutador) {
		getSessionFactory().getCurrentSession().delete(reclutador);		
	}

	public Reclutador getReclutadorById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Reclutador where reclId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Reclutador)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Reclutador> getReclutadores() {
		ArrayList<Reclutador> list = (ArrayList<Reclutador>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Reclutador order by reclApellidoPaterno asc")
				.list();
		return list;
	}
	
	public Reclutador getReclutadorByCorreo(String correo) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Reclutador p where p.usuario.usuaCorreo=?")
				.setParameter(0, correo).list();

		if (list.size() != 0) {
			return (Reclutador) list.get(0);
			
		} else {
			return null;
		}
	}
}
