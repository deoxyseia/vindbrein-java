package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Correo;

@Repository
public class CorreoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public void addCorreo(Correo correo) {
		getSessionFactory().getCurrentSession().save(correo);		
	}

	public void updateCorreo(Correo correo) {
		getSessionFactory().getCurrentSession().update(correo);		
	}

	public void deleteCorreo(Correo correo) {
		getSessionFactory().getCurrentSession().delete(correo);		
	}

	public Correo getCorreoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Correo where bancId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Correo)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Correo> getCorreos() {
		ArrayList<Correo> list = (ArrayList<Correo>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Correo order by correoName asc")
				.list();
		return list;
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
