package vindbrein.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Beneficio;

@Repository
public class BeneficioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addBeneficio(Beneficio beneficio) {
		getSessionFactory().getCurrentSession().save(beneficio);		
	}

	public void updateBeneficio(Beneficio beneficio) {
		getSessionFactory().getCurrentSession().update(beneficio);		
	}

	public void deleteBeneficio(Beneficio beneficio) {
		getSessionFactory().getCurrentSession().delete(beneficio);		
	}

	public Beneficio getBeneficioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Beneficio where beneId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Beneficio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Beneficio> getBeneficios() {
		ArrayList<Beneficio> list = (ArrayList<Beneficio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from BeneficioLaboral order by beneNombre asc")
				.list();
		return list;
	}	
}
