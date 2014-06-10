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
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
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
				
		
		if(postulante.getPostIdH() == null){
			OfertaLaboral ofertaLaboral = ofertaLaboralDAO.getOfertaLaboralById(1);
			
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
				Postulante ps = postulanteDAO.getPostulanteById(5);
				
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
				OfertaLaboral ol = ofertaLaboralDAO.getOfertaLaboralById(1);
				
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
				results.add(ofertaLaboralDAO.getOfertaLaboralById(resultados.get(i).getProfile().getId()));
				System.out.println("ID oferta: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			
			break;
		case COLLABORATIVE_BASED:
			resultados = recommenderCollaborativeBased(profile, alternativasPostulantes, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				Postulante post = postulanteDAO.getPostulanteById(resultados.get(i).getProfile().getId());
				
				results.add(matchResultDAO.getLastMatchResultByPostulant(post).getOfertaLaboral());
				
				System.out.println("ID postulante: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
				System.out.println("ID oferta: "+matchResultDAO.getLastMatchResultByPostulant(post).getOfertaLaboral().getOflaId());				
			}
			
			break;
		case RECIPROCITY_BASED:
			resultados = recommenderReciprocityBased(profile, alternativasOfertasLaborales, size);
			
			for (int i = 0; i < resultados.size(); i++) {
				results.add(ofertaLaboralDAO.getOfertaLaboralById(resultados.get(i).getProfile().getId()));
				System.out.println("ID oferta: "+resultados.get(i).getProfile().getId());
				System.out.println("Score: "+resultados.get(i).getScore());
			}
			break;
		case FUSION_BASED:	
			System.out.println("En proceso");		
		}
		
		return results;
	}

	
	public ArrayList<Result> recommenderContentBased(Profile profile, Profile[] alternatives, int sizeResults){
		ArrayList<Result> results = new ArrayList<Result>();		
		Result nowResult;
		BigDecimal similarityMin = new BigDecimal(0);
		BigDecimal similarity = new BigDecimal(0);
		
		System.out.println("MAKING RECOMMENDATION BASED-CONTENT");
		
		for (int i = 0; i < alternatives.length; i++) {
			similarity = cosineSimilarity(profile.getVecPreference(), alternatives[i].getVecSelfDescription());
			
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
			//orden de mayor a menor
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
			similarity = cosineSimilarity(profile.getVecPreference(), alternatives[i].getVecPreference())
					.add(cosineSimilarity(profile.getVecHistorical(), alternatives[i].getVecHistorical()));
			
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
			//orden de mayor a menor
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
		
		//ordenamiento descendente
		
		Comparator<Result> comparator = new Comparator<Result>() {
			//orden de mayor a menor
			public int compare(Result o1, Result o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		};
		
		Collections.sort(results, comparator);

		return results;
		
	}
	
	private BigDecimal[] convertLinkedHashMapToArray(LinkedHashMap<String, Integer> map){
		int size = map.size();
		
		BigDecimal[] vector = new BigDecimal[size];
		
		int i = 0;
		
		for (Entry<String, Integer> value : map.entrySet()) {
			vector[i] = new BigDecimal(value.getValue());
			i++;
		}
		
		return vector;
	}
	
	//Intern methods	
	private BigDecimal cosineSimilarity(BigDecimal[] vecA, BigDecimal[] vecB){
		BigDecimal valor = scalarProduct(vecA, vecB).divide(vecMagnitude(vecA).multiply(vecMagnitude(vecB)),5,RoundingMode.HALF_UP);
				
		System.out.println("cosineSimilarity");
		System.out.println(valor);
		
		return valor;
	}
	
	
	//validado
	private BigDecimal scalarProduct(BigDecimal[] vecA, BigDecimal[] vecB){
		BigDecimal product = new BigDecimal(0);
		
		if(vecA.length == vecB.length){
			for (int i = 0; i < vecA.length; i++) {
				product = product.add(vecA[i].multiply(vecB[i]));
			}
		}else{
			throw new IllegalArgumentException("La longitud de los parametros no es la misma");
		}		
		
		System.out.println("scalarProduct");
		System.out.println(product);
		return product;		
	}
	
	//validado
	private BigDecimal vecMagnitude(BigDecimal[] vec){
		BigDecimal sum = new BigDecimal(0);
		
		for (int i = 0; i < vec.length; i++) {
			sum = sum.add(vec[i].multiply(vec[i]));
		}		
		
		System.out.println("vecMagnitude");
		System.out.println(BigDecimal.valueOf(Math.sqrt(sum.doubleValue())));
		
		return BigDecimal.valueOf(Math.sqrt(sum.doubleValue()));
		
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
		
		if(set.size() >0){
			for (int i = 0; i < set.size(); i++) {
				int number = set.get(i).getRecNumber();
				
				if(number == 0){
					pivote = pivote.add((new BigDecimal(1)).divide(new BigDecimal(0.1), 5, RoundingMode.HALF_UP)
							.multiply(reciprocity(profile, set.get(i))));
				}else{
					pivote = pivote.add((new BigDecimal(1)).divide(new BigDecimal(set.get(i).getRecNumber()), 5, RoundingMode.HALF_UP)
							.multiply(reciprocity(profile, set.get(i))));
				}
				
			}
			
			result = pivote.divide(new BigDecimal(set.size()));
			pivote = new BigDecimal(0);
			
		}
		
		if (set.size() >= 2) {
			combs = k_combinations(set, 2);
			
			for (int i = 0; i < combs.size(); i++) {
				pivote = pivote.subtract(cosineSimilarity(combs.get(i).get(0).getVecSelfDescription(), 
															combs.get(i).get(1).getVecSelfDescription()));
			}
			
			pivote = pivote.divide(new BigDecimal(combs.size()));
			
			result = result.add(pivote);
		}
		
		System.out.println("Resultado de evalFunction");
		System.out.println(result);
		
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
			ArrayList<Profile> head = (ArrayList<Profile>)set.subList(i, i+1);
			
			ArrayList<ArrayList<Profile>> tailcombs = k_combinations((ArrayList<Profile>)set.subList(i, set.size()), k-1);
			
			for (int j = 0; j < tailcombs.size(); j++) {
				head.addAll(tailcombs.get(j));
				
				combs.add(head);
			}
		}
		
		return combs;
		
	}	
	
	private BigDecimal reciprocity(Profile profileA, Profile profileB){
		BigDecimal result = cosineSimilarity(profileA.getVecPreference(), profileB.getVecSelfDescription())
				.multiply(cosineSimilarity(profileB.getVecPreference(), profileA.getVecSelfDescription()));
		
		return result;
	}	
}
