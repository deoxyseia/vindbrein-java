package vindbrein.service.app.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.MatchResultDAO;
import vindbrein.dao.OfertaLaboralDAO;
import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.app.CoreDAO;
import vindbrein.dao.document.OfferHistoricalDAO;
import vindbrein.dao.document.OfferPreferenceDAO;
import vindbrein.dao.document.OfferSelfDescriptionDAO;
import vindbrein.dao.document.PostulantHistoricalDAO;
import vindbrein.dao.document.PostulantPreferenceDAO;
import vindbrein.dao.document.PostulantSelfDescriptionDAO;
import vindbrein.domain.app.Profile;
import vindbrein.domain.app.Result;
import vindbrein.domain.document.OfferHistorical;
import vindbrein.domain.document.OfferPreference;
import vindbrein.domain.document.OfferSelfDescription;
import vindbrein.domain.document.PostulantHistorical;
import vindbrein.domain.document.PostulantPreference;
import vindbrein.domain.document.PostulantSelfDescription;
import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.service.OfertaLaboralService;
import vindbrein.service.PostulanteService;
import vindbrein.service.app.RecommenderService;
import vindbrein.util.RecommenderType;

@Service
@Transactional(readOnly = true)
public class RecommenderServiceImpl implements RecommenderService, Serializable {
	
	@Autowired
	CoreDAO coreDAO;
	
	@Autowired
	PostulantSelfDescriptionDAO postulantSelfDescriptionDAO;
	
	@Autowired
	PostulantPreferenceDAO postulantPreferenceDAO;
	
	@Autowired
	PostulantHistoricalDAO postulantHistoricalDAO;
	
	@Autowired
	OfferSelfDescriptionDAO offerSelfDescriptionDAO;
	
	@Autowired
	OfferPreferenceDAO offerPreferenceDAO;
	
	@Autowired
	OfferHistoricalDAO offerHistoricalDAO;
	
	@Autowired
	OfertaLaboralDAO ofertaLaboralDAO;
	
	@Autowired
	PostulanteDAO postulanteDAO;
	
	@Autowired
	MatchResultDAO matchResultDAO;
	
	@Autowired
	@Qualifier("ofertaLaboralServiceImpl")
	OfertaLaboralService ofertaLaboralService;
	
	@Autowired
	@Qualifier("postulanteServiceImpl")
	PostulanteService postulanteService;
	
	
	private static final long serialVersionUID = 1L;
	
	
	public ArrayList<OfertaLaboral> recomendarOfertasLaboralesToPostulante(Postulante postulante, int size, RecommenderType recommenderType){		
		ArrayList<OfertaLaboral> results = new ArrayList<OfertaLaboral>();
		
		//////////////////postulante
		
		PostulantPreference postulantPreference = postulantPreferenceDAO.getPostulantPreferenceById(postulante.getPostIdP());
		PostulantSelfDescription postulantSelfDescription = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(postulante.getPostIdS());
		
		Profile profile = new Profile();
		
		profile.setId(postulante.getPostId());
		profile.setRecNumber(matchResultDAO.getNumberRecomendationPostulant(postulante));
		profile.setVecSelfDescription(convertLinkedHashMapToArray(postulantSelfDescription.getValues()));
		profile.setVecPreference(convertLinkedHashMapToArray(postulantPreference.getValues()));
				
		//busco perfil histórico o lo cambio o creo uno de 0s
		if(postulante.getPostIdH() == null){
			OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getAlgunaOfertaLaboral();
			
			OfferSelfDescription offerSelfDescription = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ofertaLaboral.getOflaIdS());
			profile.setVecHistorical(convertLinkedHashMapToArray(offerSelfDescription.getValues()));
			
			for (int i = 0; i < profile.getVecHistorical().length; i++) {
				profile.getVecHistorical()[i] = new BigDecimal(0);
			}
		}else{
			
			PostulantHistorical postulantHistorical = postulantHistoricalDAO.getPostulantHistoricalById(postulante.getPostIdH());
			
			profile.setVecHistorical(convertLinkedHashMapToArray(postulantHistorical.getValues()));
		}
		
		/////////////ofertas laborales
		
		ArrayList<OfertaLaboral> ofertas = ofertaLaboralDAO.getOfertasLaborales();
		
		Profile[] alternativasOfertasLaborales = new Profile[ofertas.size()];
		
		OfferPreference offerPreference;
		OfferSelfDescription offerSelfDescription;
		
		for (int i = 0; i < ofertas.size(); i++) {
			Profile alternativa = new Profile();		
			
			offerPreference = offerPreferenceDAO.getOfferPreferenceById(ofertas.get(i).getOflaIdP());
			offerSelfDescription = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ofertas.get(i).getOflaIdS());
			
			alternativa.setId(ofertas.get(i).getOflaId());
			alternativa.setRecNumber(matchResultDAO.getNumberRecomendationOffer(ofertas.get(i)));
			alternativa.setVecPreference(convertLinkedHashMapToArray(offerPreference.getValues()));
			alternativa.setVecSelfDescription(convertLinkedHashMapToArray(offerSelfDescription.getValues()));		
			
			if(ofertas.get(i).getOflaIdH() == null){
				Postulante ps = postulanteDAO.getAlgunPostulante();
				
				PostulantSelfDescription pSD = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(ps.getPostIdS());
				alternativa.setVecHistorical(convertLinkedHashMapToArray(pSD.getValues()));
							
				for (int j = 0; j < alternativa.getVecHistorical().length; j++) {
					alternativa.getVecHistorical()[j] = new BigDecimal(0);
				}
			}else{
				
				OfferHistorical offerHistorical = offerHistoricalDAO.getOfferHistoricalById(ofertas.get(i).getOflaIdH());
				
				alternativa.setVecHistorical(convertLinkedHashMapToArray(offerHistorical.getValues()));
			}
			
			alternativasOfertasLaborales[i] = alternativa;
		}
		
		////postulantes
		
		ArrayList<Postulante> postulantes = postulanteDAO.getPostulantes();
		
		Profile[] alternativasPostulantes = new Profile[postulantes.size()];
		
		PostulantPreference postulantP;
		PostulantSelfDescription postulantS;
		
		for (int i = 0; i < postulantes.size(); i++) {
			Profile alternativa = new Profile();		
			
			postulantP = postulantPreferenceDAO.getPostulantPreferenceById(postulantes.get(i).getPostIdP());
			postulantS = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(postulantes.get(i).getPostIdS());
			
			alternativa.setId(postulantes.get(i).getPostId());
			alternativa.setRecNumber(matchResultDAO.getNumberRecomendationPostulant(postulantes.get(i)));
			alternativa.setVecPreference(convertLinkedHashMapToArray(postulantP.getValues()));
			alternativa.setVecSelfDescription(convertLinkedHashMapToArray(postulantS.getValues()));		
			
			if(postulantes.get(i).getPostIdH() == null){
				OfertaLaboral ol = ofertaLaboralDAO.getAlgunaOfertaLaboral();
				
				OfferSelfDescription oSD = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ol.getOflaIdS());
				alternativa.setVecHistorical(convertLinkedHashMapToArray(oSD.getValues()));
							
				for (int j = 0; j < alternativa.getVecHistorical().length; j++) {
					alternativa.getVecHistorical()[j] = new BigDecimal(0);
				}
			}else{
				
				PostulantHistorical postulantHistorical = postulantHistoricalDAO.getPostulantHistoricalById(postulantes.get(i).getPostIdH());
				
				alternativa.setVecHistorical(convertLinkedHashMapToArray(postulantHistorical.getValues()));
			}
			
			alternativasPostulantes[i] = alternativa;
		}
		
				
		ArrayList<Result> resultados;		
		
		
	
		switch (recommenderType) {
		case CONTENT_BASED:
			resultados = recommenderContentBased(profile, alternativasOfertasLaborales, size);
						
			for (int i = 0; i < resultados.size(); i++) {
				OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getOfertaLaboralById(resultados.get(i).getProfile().getId());
				ofertaLaboral.setScore(resultados.get(i).getScore());
				
				results.add(ofertaLaboral);
				System.out.println("ID oferta: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			
			break;
		case COLLABORATIVE_BASED:
			resultados = recommenderCollaborativeBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				Postulante post = postulanteDAO.getPostulanteById(resultados.get(i).getProfile().getId());
				
				MatchResult matchResult = matchResultDAO.getLastMatchResultByPostulant(post);
				
				if(matchResult != null){
					OfertaLaboral ofertaLaboral = matchResult.getOfertaLaboral();
					ofertaLaboral.setScore(resultados.get(i).getScore());
					
					results.add(ofertaLaboral);
					
					System.out.println("ID postulante: "+resultados.get(i).getProfile().getId());
					System.out.println("Score: "+resultados.get(i).getScore());
					System.out.println("ID oferta: "+matchResultDAO.getLastMatchResultByPostulant(post).getOfertaLaboral().getOflaId());
				}							
			}
			
			break;
		case RECIPROCITY_BASED:
			resultados = recommenderReciprocityBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getOfertaLaboralById(resultados.get(i).getProfile().getId());
				ofertaLaboral.setScore(resultados.get(i).getScore());
				
				results.add(ofertaLaboral);
				System.out.println("ID oferta: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			break;
		case FUSION_BASED:	
			System.out.println("INICIANDO RECOMENDACION POR FUSION");
			
			ArrayList<OfertaLaboral> ofertasContent = new ArrayList<OfertaLaboral>();
			ArrayList<OfertaLaboral> ofertasCollavorative = new ArrayList<OfertaLaboral>();
			ArrayList<OfertaLaboral> ofertasReciprocity = new ArrayList<OfertaLaboral>();
			ArrayList<OfertaLaboral> ofertasTotal = new ArrayList<OfertaLaboral>();
			
			
			ArrayList<Result> resultadosContent;
			ArrayList<Result> resultadosCollaborative;
			ArrayList<Result> resultadosReciprocity;
			
			
			//basado en contenido
			
			resultadosContent = recommenderContentBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultadosContent.size(); i++) {
				OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getOfertaLaboralById(resultadosContent.get(i).getProfile().getId());
				ofertaLaboral.setScore(resultadosContent.get(i).getScore());
				
				ofertasContent.add(ofertaLaboral);
				System.out.println("ID oferta: "+resultadosContent.get(i).getProfile().getId());
				System.out.println("Score: "+resultadosContent.get(i).getScore());
			}
			
			//basado en colaboracion
			
			resultadosCollaborative = recommenderCollaborativeBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultadosCollaborative.size(); i++) {
				Postulante post = postulanteDAO.getPostulanteById(resultadosCollaborative.get(i).getProfile().getId());
				
				MatchResult matchResult = matchResultDAO.getLastMatchResultByPostulant(post);
				
				if(matchResult != null){
					OfertaLaboral ofertaLaboral = matchResult.getOfertaLaboral(); 
					ofertaLaboral.setScore(resultadosCollaborative.get(i).getScore());
					
					ofertasCollavorative.add(ofertaLaboral);
					
					System.out.println("ID postulante: "+resultadosCollaborative.get(i).getProfile().getId());
					System.out.println("Score: "+resultadosCollaborative.get(i).getScore());
					System.out.println("ID oferta: "+matchResultDAO.getLastMatchResultByPostulant(post).getOfertaLaboral().getOflaId());
				}							
			}
			
			//basado en reciprocidad
			
			resultadosReciprocity = recommenderReciprocityBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultadosReciprocity.size(); i++) {
				OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getOfertaLaboralById(resultadosReciprocity.get(i).getProfile().getId());
				ofertaLaboral.setScore(resultadosReciprocity.get(i).getScore());
				
				ofertasReciprocity.add(ofertaLaboral);
				System.out.println("ID oferta: "+resultadosReciprocity.get(i).getProfile().getId());
				System.out.println("Score: "+resultadosReciprocity.get(i).getScore());
			}
			
			
			ofertasTotal.addAll(normalizarScoreOfertas(ofertasContent, new BigDecimal(0), new BigDecimal(1)));
			ofertasTotal.addAll(normalizarScoreOfertas(ofertasCollavorative, new BigDecimal(0), new BigDecimal(2)));
			ofertasTotal.addAll(normalizarScoreOfertas(ofertasReciprocity, new BigDecimal(-1), new BigDecimal(1)));		
			
			boolean existente;
			
			for (int i = 0; i < ofertasTotal.size(); i++) {
				existente = false;
				
				for (int j = 0; j < results.size(); j++) {
					if(results.get(j).getOflaId() == ofertasTotal.get(i).getOflaId()){
						results.get(j).setScore(results.get(j).getScore().add(ofertasTotal.get(i).getScore()));
						existente = true;
					}					
				}
				
				if (!existente) {
					results.add(ofertasTotal.get(i));
				}
			}
			
			Comparator<OfertaLaboral> comparatorOferta = new Comparator<OfertaLaboral>() {
				//orden descendente
				public int compare(OfertaLaboral o1, OfertaLaboral o2) {
					return o1.getScore().compareTo(o2.getScore())*-1;
				}				
			};
			
			Collections.sort(results, comparatorOferta);
			
			int s = results.size() >= size ? size : results.size();
			
			System.out.println("sizeeeeee: "+ s);
			
			results = new ArrayList<OfertaLaboral>(results.subList(0, s));			
			
			break;
		}
		
		for (int i = 0; i < results.size(); i++) {
			results.set(i, ofertaLaboralService.getOfertaLaboralCompletaByOfertaLaboral(results.get(i)));
		}
		
		return results;
	}
	
	public ArrayList<Postulante> recomendarPostulanteToOfertaLboral(OfertaLaboral ofertaLaboral, int size, RecommenderType recommenderType){		
		ArrayList<Postulante> results = new ArrayList<Postulante>();
		
		//////////////////Oferta laboral
		
		OfferPreference offerPreference = offerPreferenceDAO.getOfferPreferenceById(ofertaLaboral.getOflaIdP());
		OfferSelfDescription offerSelfDescription = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ofertaLaboral.getOflaIdS());
		
		Profile profile = new Profile();
		
		profile.setId(ofertaLaboral.getOflaId());
		profile.setRecNumber(matchResultDAO.getNumberRecomendationOffer(ofertaLaboral));
		profile.setVecSelfDescription(convertLinkedHashMapToArray(offerSelfDescription.getValues()));
		profile.setVecPreference(convertLinkedHashMapToArray(offerPreference.getValues()));
				
		
		if(ofertaLaboral.getOflaIdH() == null){
			Postulante postulante = postulanteDAO.getAlgunPostulante();
			
			PostulantSelfDescription postulantSelfDescription = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(postulante.getPostIdS());
			profile.setVecHistorical(convertLinkedHashMapToArray(postulantSelfDescription.getValues()));
			
			for (int i = 0; i < profile.getVecHistorical().length; i++) {
				profile.getVecHistorical()[i] = new BigDecimal(0);
			}
		}else{
			
			OfferHistorical offerHistorical = offerHistoricalDAO.getOfferHistoricalById(ofertaLaboral.getOflaIdH());
			
			profile.setVecHistorical(convertLinkedHashMapToArray(offerHistorical.getValues()));
		}
		
	    ////postulantes
		
		ArrayList<Postulante> postulantes = postulanteDAO.getPostulantes();
		
		Profile[] alternativasPostulantes = new Profile[postulantes.size()];
		
		PostulantPreference postulantP;
		PostulantSelfDescription postulantS;
		
		for (int i = 0; i < postulantes.size(); i++) {
			Profile alternativa = new Profile();		
			
			postulantP = postulantPreferenceDAO.getPostulantPreferenceById(postulantes.get(i).getPostIdP());
			postulantS = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(postulantes.get(i).getPostIdS());
			
			alternativa.setId(postulantes.get(i).getPostId());
			alternativa.setRecNumber(matchResultDAO.getNumberRecomendationPostulant(postulantes.get(i)));
			alternativa.setVecPreference(convertLinkedHashMapToArray(postulantP.getValues()));
			alternativa.setVecSelfDescription(convertLinkedHashMapToArray(postulantS.getValues()));		
			
			if(postulantes.get(i).getPostIdH() == null){
				OfertaLaboral ol = ofertaLaboralDAO.getAlgunaOfertaLaboral();
				
				OfferSelfDescription oSD = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ol.getOflaIdS());
				alternativa.setVecHistorical(convertLinkedHashMapToArray(oSD.getValues()));
							
				for (int j = 0; j < alternativa.getVecHistorical().length; j++) {
					alternativa.getVecHistorical()[j] = new BigDecimal(0);
				}
			}else{
				
				PostulantHistorical postulantHistorical = postulantHistoricalDAO.getPostulantHistoricalById(postulantes.get(i).getPostIdH());
				
				alternativa.setVecHistorical(convertLinkedHashMapToArray(postulantHistorical.getValues()));
			}
			
			alternativasPostulantes[i] = alternativa;
		}
		
		/////////////ofertas laborales
		
		ArrayList<OfertaLaboral> ofertas = ofertaLaboralDAO.getOfertasLaborales();
		
		Profile[] alternativasOfertasLaborales = new Profile[ofertas.size()];
		
		OfferPreference offerP;
		OfferSelfDescription offerS;
		
		for (int i = 0; i < ofertas.size(); i++) {
			Profile alternativa = new Profile();		
			
			offerP = offerPreferenceDAO.getOfferPreferenceById(ofertas.get(i).getOflaIdP());
			offerS = offerSelfDescriptionDAO.getOfferSelfDescriptionById(ofertas.get(i).getOflaIdS());
			
			alternativa.setId(ofertas.get(i).getOflaId());
			alternativa.setRecNumber(matchResultDAO.getNumberRecomendationOffer(ofertas.get(i)));
			alternativa.setVecPreference(convertLinkedHashMapToArray(offerP.getValues()));
			alternativa.setVecSelfDescription(convertLinkedHashMapToArray(offerS.getValues()));		
			
			if(ofertas.get(i).getOflaIdH() == null){
				Postulante ps = postulanteDAO.getAlgunPostulante();
				
				PostulantSelfDescription pSD = postulantSelfDescriptionDAO.getPostulantSelfDescriptionById(ps.getPostIdS());
				alternativa.setVecHistorical(convertLinkedHashMapToArray(pSD.getValues()));
							
				for (int j = 0; j < alternativa.getVecHistorical().length; j++) {
					alternativa.getVecHistorical()[j] = new BigDecimal(0);
				}
			}else{
				
				OfferHistorical offerHistorical = offerHistoricalDAO.getOfferHistoricalById(ofertas.get(i).getOflaIdH());
				
				alternativa.setVecHistorical(convertLinkedHashMapToArray(offerHistorical.getValues()));
			}
			
			alternativasOfertasLaborales[i] = alternativa;
		}
		
		
		
				
		ArrayList<Result> resultados;		
		
		
	
		switch (recommenderType) {
		case CONTENT_BASED:
			resultados = recommenderContentBased(profile, alternativasPostulantes, size);
						
			for (int i = 0; i < resultados.size(); i++) {
				Postulante postulante = postulanteDAO.getPostulanteById(resultados.get(i).getProfile().getId());				
				postulante.setScore(resultados.get(i).getScore());
				
				results.add(postulante);
				System.out.println("ID postulante: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			
			break;
		case COLLABORATIVE_BASED:
			resultados = recommenderCollaborativeBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				OfertaLaboral oferta = ofertaLaboralDAO.getOfertaLaboralById(resultados.get(i).getProfile().getId());
				
				MatchResult matchResult = matchResultDAO.getLastMatchResultByOffer(oferta);
				
				if(matchResult != null){
					Postulante postulante = matchResult.getPostulante(); 
					postulante.setScore(resultados.get(i).getScore());
					
					results.add(postulante);
					
					System.out.println("ID oferta: "+resultados.get(i).getProfile().getId());
					System.out.println("Score: "+resultados.get(i).getScore());
					System.out.println("ID postulante: "+matchResultDAO.getLastMatchResultByOffer(oferta).getOfertaLaboral().getOflaId());					
				}								
			}
			
			break;
		case RECIPROCITY_BASED:
			resultados = recommenderReciprocityBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				Postulante postulante = postulanteDAO.getPostulanteById(resultados.get(i).getProfile().getId());
				postulante.setScore(resultados.get(i).getScore());
				
				results.add(postulante);
				System.out.println("ID postulante: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			break;
		case FUSION_BASED:	
			System.out.println("INICIANDO RECOMENDACION POR FUSION");
			
			ArrayList<Postulante> postulantesContent = new ArrayList<Postulante>();
			ArrayList<Postulante> postulantesCollavorative = new ArrayList<Postulante>();
			ArrayList<Postulante> postulantesReciprocity = new ArrayList<Postulante>();
			ArrayList<Postulante> postulantesTotal = new ArrayList<Postulante>();
			
			
			ArrayList<Result> resultadosContent;
			ArrayList<Result> resultadosCollaborative;
			ArrayList<Result> resultadosReciprocity;
			
			
			//basado en contenido
			
			resultadosContent = recommenderContentBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultadosContent.size(); i++) {
				Postulante postulante = postulanteDAO.getPostulanteById(resultadosContent.get(i).getProfile().getId());
				postulante.setScore(resultadosContent.get(i).getScore());
				
				postulantesContent.add(postulante);
				System.out.println("ID postulante: "+resultadosContent.get(i).getProfile().getId());
				System.out.println("Score: "+resultadosContent.get(i).getScore());
			}
			
			//basado en colaboracion
			
			resultadosCollaborative = recommenderCollaborativeBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultadosCollaborative.size(); i++) {
				OfertaLaboral oferta = ofertaLaboralDAO.getOfertaLaboralById(resultadosCollaborative.get(i).getProfile().getId());
				
				MatchResult matchResult = matchResultDAO.getLastMatchResultByOffer(oferta);
				
				if(matchResult != null){
					Postulante postulante = matchResult.getPostulante();
					postulante.setScore(resultadosCollaborative.get(i).getScore());
					
					postulantesCollavorative.add(postulante);
					
					System.out.println("ID oferta: "+resultadosCollaborative.get(i).getProfile().getId());
					System.out.println("Score: "+resultadosCollaborative.get(i).getScore());
					System.out.println("ID postulante: "+matchResultDAO.getLastMatchResultByPostulant(postulante).getPostulante().getPostId());
				}							
			}
			
			//basado en reciprocidad
			
			resultadosReciprocity = recommenderReciprocityBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultadosReciprocity.size(); i++) {
				Postulante postulante = postulanteDAO.getPostulanteById(resultadosReciprocity.get(i).getProfile().getId());
				postulante.setScore(resultadosReciprocity.get(i).getScore());
				
				postulantesReciprocity.add(postulante);
				System.out.println("ID postulante: "+resultadosReciprocity.get(i).getProfile().getId());
				System.out.println("Score: "+resultadosReciprocity.get(i).getScore());
			}
			
			
			postulantesTotal.addAll(normalizarScorePostulantes(postulantesContent, new BigDecimal(0), new BigDecimal(1)));
			postulantesTotal.addAll(normalizarScorePostulantes(postulantesCollavorative, new BigDecimal(0), new BigDecimal(2)));
			postulantesTotal.addAll(normalizarScorePostulantes(postulantesReciprocity, new BigDecimal(-1), new BigDecimal(1)));		
			
			boolean existente;
			
			for (int i = 0; i < postulantesTotal.size(); i++) {
				existente = false;
				
				for (int j = 0; j < results.size(); j++) {
					if(results.get(j).getPostId() == postulantesTotal.get(i).getPostId()){
						results.get(j).setScore(results.get(j).getScore().add(postulantesTotal.get(i).getScore()));
						existente = true;
					}					
				}
				
				if (!existente) {
					results.add(postulantesTotal.get(i));
				}
			}
			
			Comparator<Postulante> comparatorOferta = new Comparator<Postulante>() {
				//orden descendente
				public int compare(Postulante o1, Postulante o2) {
					return o1.getScore().compareTo(o2.getScore())*-1;
				}				
			};
			
			Collections.sort(results, comparatorOferta);
			
			int s = results.size() >= size ? size : results.size();
			
			System.out.println("sizeeeeee: "+ s);
			
			results = new ArrayList<Postulante>(results.subList(0, s));			
			
			break;
		}
		
		for (int i = 0; i < results.size(); i++) {
			results.set(i, postulanteService.getPostulanteCompletoByPostulante(results.get(i)));
		}
		
		return results;
	}
	
	public ArrayList<OfertaLaboral> normalizarScoreOfertas(ArrayList<OfertaLaboral> ofertas, BigDecimal min, BigDecimal max){
		
		for (int i = 0; i < ofertas.size(); i++) {
			ofertas.get(i).setScore((ofertas.get(i).getScore().subtract(min)).divide(max.subtract(min)));
		}
		
		return ofertas;
	}
	
	public ArrayList<Postulante> normalizarScorePostulantes(ArrayList<Postulante> postulantes, BigDecimal min, BigDecimal max){
		
		for (int i = 0; i < postulantes.size(); i++) {
			postulantes.get(i).setScore((postulantes.get(i).getScore().subtract(min)).divide(max.subtract(min)));
		}
		
		return postulantes;
	}

	
	public ArrayList<Result> recommenderContentBased(Profile profile, Profile[] alternatives, int sizeResults){
		ArrayList<Result> results = new ArrayList<Result>();		
		Result nowResult;
		BigDecimal similarityMin = new BigDecimal(0);
		BigDecimal similarity = new BigDecimal(0);
		
		System.out.println("MAKING RECOMMENDATION BASED-CONTENT");
		
		for (int i = 0; i < alternatives.length; i++) {
			try {
				similarity = cosineSimilarity(profile.getVecPreference(), alternatives[i].getVecSelfDescription());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//similarity es mayor que similarityMin
			if(similarity.compareTo(similarityMin) == 1){
				nowResult = new Result();
				
				nowResult.setProfile(alternatives[i]);
				nowResult.setScore(similarity);
				
				results.add(nowResult);
								
				Comparator<Result> comparatorInverso = new Comparator<Result>() {
					//orden inverso, el resultado es descendente
					public int compare(Result o1, Result o2) {
						return o1.getScore().compareTo(o2.getScore())*-1;
					}
				};
				
				Collections.sort(results, comparatorInverso);
				
				if(results.size() > sizeResults){
					results.remove(0);
				}
				
				similarityMin = results.get(0).getScore();				
			}			
		}
		//order ascendente
		Comparator<Result> comparator = new Comparator<Result>() {
			//orden de menor a mayor
			public int compare(Result o1, Result o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		};
		
		Collections.sort(results, comparator);

		return results;
	}
	
	public ArrayList<Result> recommenderCollaborativeBased(Profile profile, Profile[] alternatives, int sizeResults){
		ArrayList<Result> results = new ArrayList<Result>();		
		Result nowResult;
		BigDecimal similarityMin = new BigDecimal(0);
		BigDecimal similarity = new BigDecimal(0);
				
		System.out.println("MAKING RECOMMENDATION BASED-COLLABORATIVE");
		
		for (int i = 0; i < alternatives.length; i++) {
			try {
				similarity = cosineSimilarity(profile.getVecPreference(), alternatives[i].getVecPreference())
						.add(cosineSimilarity(profile.getVecHistorical(), alternatives[i].getVecHistorical()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//similarity es mayor que similarityMin
			if(similarity.compareTo(similarityMin) == 1){
				nowResult = new Result();
				
				nowResult.setProfile(alternatives[i]);
				nowResult.setScore(similarity);
				
				results.add(nowResult);
								
				Comparator<Result> comparatorInverso = new Comparator<Result>() {
					//orden inverso
					public int compare(Result o1, Result o2) {
						return o1.getScore().compareTo(o2.getScore())*-1;
					}
				};
				
				Collections.sort(results, comparatorInverso);
				
				if(results.size() > sizeResults){
					results.remove(0);
				}
				
				similarityMin = results.get(0).getScore();				
			}			
		}
		
		Comparator<Result> comparator = new Comparator<Result>() {
			//orden de ascendente
			public int compare(Result o1, Result o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		};
		
		Collections.sort(results, comparator);

		return results;
	}
	
	private ArrayList<Profile> generateArray(ArrayList<Profile> set, Profile alternative){
		ArrayList<Profile> result = new ArrayList<Profile>();
		
		for (int i = 0; i < set.size(); i++) {
			result.add(set.get(i));
		}
		
		result.add(alternative);
		
		return result;
	}
	
	public ArrayList<Result> recommenderReciprocityBased(Profile profile, Profile[] alternatives, int sizeResults){
		ArrayList<Result> results = new ArrayList<Result>();		
		ArrayList<Profile> subSet = new ArrayList<Profile>();
		Result nowResult;
		BigDecimal increase = new BigDecimal(0);
		BigDecimal maxIncrease = new BigDecimal(-1);
		int maxIndex = 0;
		
		System.out.println("MAKING RECOMMENDATION BASED-RECIPROCITY");
		
		while (alternatives.length > 0 && subSet.size() < sizeResults) {
			for (int i = 0; i < alternatives.length; i++) {
				increase = evalFunction(profile, generateArray(subSet, alternatives[i]))
						.subtract(evalFunction(profile, subSet));
				
				System.out.println("Increase");
				System.out.println(increase);
				
				
				//increase es mayor que maxIncrease
				if (increase.compareTo(maxIncrease) == 1){
					maxIncrease = increase;
					maxIndex = i;
				}
			}
			
			nowResult = new Result();
			nowResult.setProfile(alternatives[maxIndex]);
			nowResult.setScore(maxIncrease);
			
			
			//save the result
			results.add(nowResult);
			
			//Added alternative to subSet
			subSet.add(alternatives[maxIndex]);
			
			//remove alternatives
			ArrayList<Profile> pivote = new ArrayList<Profile>();
			Collections.addAll(pivote, alternatives);
			
			//por recontra probar para ver si el index se mantiene
			//Remove alternatives
			pivote.remove(maxIndex);			
			
			alternatives = new Profile[pivote.size()];
			
			pivote.toArray();
			
			for (int i = 0; i < pivote.size(); i++) {
				alternatives[i] = (Profile)pivote.get(i);
			}
			
			maxIncrease = new BigDecimal(-1);			
		}
		
		//ordenamiento ascendente
		
		Comparator<Result> comparator = new Comparator<Result>() {
			//orden de menor a mayor
			public int compare(Result o1, Result o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		};
		
		Collections.sort(results, comparator);

		return results;
		
	}
	
	
	
	//Intern methods	
	private BigDecimal[] convertLinkedHashMapToArray(LinkedHashMap<String, Integer> map){
		
		BigDecimal[] vector = new BigDecimal[map.size()];
		
		int i = 0;
		
		for (Entry<String, Integer> value : map.entrySet()) {
			vector[i] = new BigDecimal(value.getValue());
			i++;
		}
		
		return vector;
	}
	
	private BigDecimal cosineSimilarity(BigDecimal[] vecA, BigDecimal[] vecB) throws InterruptedException{
		BigDecimal result;
		
		if (vecA.length == vecB.length) {
			BigDecimal denominador = vecMagnitude(vecA).multiply(vecMagnitude(vecB));
			
			if(denominador.compareTo(new BigDecimal(0)) == 0){
				result = new BigDecimal(0);
			}else{
				result = scalarProduct(vecA, vecB).divide(denominador,5, RoundingMode.HALF_UP);
			}		
					
			System.out.println("cosineSimilarity: "+result);
			
		}else{
			throw new InterruptedException("La longitud de los vectores no es la misma");
		}				
		
		return result;
	}
	
	
	//validado
	private BigDecimal scalarProduct(BigDecimal[] vecA, BigDecimal[] vecB){
		
		BigDecimal result = new BigDecimal(0);
		
		
		for (int i = 0; i < vecA.length; i++) {
			result = result.add(vecA[i].multiply(vecB[i]));
		}		
		
		System.out.println("scalarProduct: "+result);
		
		return result;		
	}
	
	//validado
	private BigDecimal vecMagnitude(BigDecimal[] vec) throws ArithmeticException{
		BigDecimal result = new BigDecimal(0);
		BigDecimal sum = new BigDecimal(0);
		
		for (int i = 0; i < vec.length; i++) {
			sum = sum.add(vec[i].multiply(vec[i]));
		}
		
		try {
			result = BigDecimal.valueOf(Math.sqrt(sum.doubleValue()));
		} catch (Exception e) {
			throw new ArithmeticException("Error al obtener la raiz cuadrada");
		}	
		
		System.out.println("vecMagnitude :"+result);		
		
		return result;	
	}
	
	private BigDecimal evalFunction(Profile profile, ArrayList<Profile> set){
		System.out.println("Start evalFunction");
		System.out.println("profile");
		System.out.println(profile.getId());
		System.out.println("set");
		System.out.println(set.size());
		
		ArrayList<ArrayList<Profile>> combs;
		
		BigDecimal result = new BigDecimal(0);
		BigDecimal pivote = new BigDecimal(0);
		
		if(set.size() >= 1){
			for (int i = 0; i < set.size(); i++) {				
				pivote = pivote.add((new BigDecimal(1)).divide(new BigDecimal(set.get(i).getRecNumber()), 5, RoundingMode.HALF_UP)
						.multiply(reciprocity(profile, set.get(i))));				
			}
			
			result = pivote.divide(new BigDecimal(set.size()));
			pivote = new BigDecimal(0);
			
			// si el conjunto tiene 2 items o más
			if (set.size() >= 2) {
				combs = k_combinations(set, 2);
				
				for (int i = 0; i < combs.size(); i++) {
					try {
						pivote = pivote.subtract(cosineSimilarity(combs.get(i).get(0).getVecSelfDescription(), 
																	combs.get(i).get(1).getVecSelfDescription()));
					} catch (InterruptedException e) {					
						e.printStackTrace();
					}
				}
				
				pivote = pivote.divide(new BigDecimal(combs.size()));
				
				result = result.add(pivote);
			}			
		}	
		
		System.out.println("Resultado de evalFunction: "+result);
		
		return result;
	}	
	
	private ArrayList<ArrayList<Profile>> k_combinations(ArrayList<Profile> set, int k){
		
		ArrayList<ArrayList<Profile>> result;
		ArrayList<ArrayList<Profile>> combs;
		ArrayList<Profile> pivote;
		
		
		if(k > set.size() || k <= 0){
			result = new ArrayList<ArrayList<Profile>>();
			return result;
		}
		
		if(k == set.size()){
			result = new ArrayList<ArrayList<Profile>>();
			result.add(set);
			return result;
		}
		
		if(k == 1){
			combs = new ArrayList<ArrayList<Profile>>();
			
			for (int i = 0; i < set.size(); i++) {
				pivote = new ArrayList<Profile>();
				pivote.add(set.get(i));
				
				combs.add(pivote);
			}
			
			return combs;
		}
		
		combs = new ArrayList<ArrayList<Profile>>();
		
		for (int i = 0; i < set.size() - k + 1; i++) {
			ArrayList<Profile> head = new ArrayList<Profile>(set.subList(i, i+1));
			
			ArrayList<ArrayList<Profile>> tailcombs = k_combinations((ArrayList<Profile>)set.subList(i, set.size()), k-1);
			
			for (int j = 0; j < tailcombs.size(); j++) {
				head.addAll(tailcombs.get(j));
				
				combs.add(head);
			}
		}
		
		return combs;
		
	}	
	
	private BigDecimal reciprocity(Profile profileA, Profile profileB){
		
		BigDecimal result = new BigDecimal(0);
		try {
			result = cosineSimilarity(profileA.getVecPreference(), profileB.getVecSelfDescription())
					.multiply(cosineSimilarity(profileB.getVecPreference(), profileA.getVecSelfDescription()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}	
}
