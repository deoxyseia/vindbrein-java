package vindbrein.service.app;

import java.util.ArrayList;

import vindbrein.domain.app.Profile;
import vindbrein.domain.app.Result;


public interface RecommenderService {
	
	public ArrayList<Result> recommenderContentBased(Profile profile, Profile[] alternatives, int sizeResults);
	
	public ArrayList<Result> recommenderCollaborativeBased(Profile profile, Profile[] alternatives, int sizeResults);
	
	public ArrayList<Result> recommenderReciprocityBased(Profile profile, Profile[] alternatives, int sizeResults);
}
