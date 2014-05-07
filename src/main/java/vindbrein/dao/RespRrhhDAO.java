package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.RespRrhh;

@Repository
public class RespRrhhDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addRespRrhh(RespRrhh respRrhh) {
		getSessionFactory().getCurrentSession().save(respRrhh);		
	}

	public void updateRespRrhh(RespRrhh respRrhh) {
		getSessionFactory().getCurrentSession().update(respRrhh);		
	}

	public void deleteRespRrhh(RespRrhh respRrhh) {
		getSessionFactory().getCurrentSession().delete(respRrhh);		
	}

	public RespRrhh getRespRrhhById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from RespRrhh where rerrId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (RespRrhh)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<RespRrhh> getRespRrhhs() {
		ArrayList<RespRrhh> list = (ArrayList<RespRrhh>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from RespRrhh order by rerrNombres asc")
				.list();
		return list;
	}	
}
