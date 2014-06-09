package vindbrein.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;

@Repository
public class MatchResultDAO implements Serializable{
	
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
		
	public void addMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().save(matchResult);		
	}

	public void updateMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().update(matchResult);		
	}

	public void deleteMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().delete(matchResult);		
	}

	public MatchResult getMatchResultById(OfertaLaboral ofertaLaboral, Postulante postulante) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from MatchResult where ofertaLaboral.oflaId=? and postulante.postId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, postulante.getPostId());
		
		List<MatchResult> list = query.list();
		
		if(list.size()!=0){
			return (MatchResult)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<MatchResult> getMatchResults() {
		ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from MatchResult")
				.list();
		return list;
	}	
}
