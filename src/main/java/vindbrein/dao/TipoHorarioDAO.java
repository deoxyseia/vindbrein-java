package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.TipoHorario;

@Repository
public class TipoHorarioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addTipoHorario(TipoHorario tipoHorario) {
		getSessionFactory().getCurrentSession().save(tipoHorario);		
	}

	public void updateTipoHorario(TipoHorario tipoHorario) {
		getSessionFactory().getCurrentSession().update(tipoHorario);		
	}

	public void deleteTipoHorario(TipoHorario tipoHorario) {
		getSessionFactory().getCurrentSession().delete(tipoHorario);		
	}

	public TipoHorario getTipoHorarioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from TipoHorario where tihoId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (TipoHorario)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<TipoHorario> getTiposHorario() {
		ArrayList<TipoHorario> list = (ArrayList<TipoHorario>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from TipoHorario order by tihoNombre asc")
				.list();
		return list;
	}	
}
