
package com.doublechaintech.promoengine.promotionoffer;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.BaseEntity;


import com.doublechaintech.promoengine.Message;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;

import com.doublechaintech.promoengine.PromoengineUserContext;
//import com.doublechaintech.promoengine.BaseManagerImpl;
import com.doublechaintech.promoengine.PromoengineCheckerManager;
import com.doublechaintech.promoengine.CustomPromoengineCheckerManager;

import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.promotion.Promotion;

import com.doublechaintech.promoengine.promotionlevel.CandidatePromotionLevel;
import com.doublechaintech.promoengine.promotion.CandidatePromotion;







public class PromotionOfferManagerImpl extends CustomPromoengineCheckerManager implements PromotionOfferManager {
	
	private static final String SERVICE_TYPE = "PromotionOffer";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PromotionOfferManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PromotionOfferManagerException(message);

	}
	
	

 	protected PromotionOffer savePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer, String [] tokensExpr) throws Exception{	
 		//return getPromotionOfferDAO().save(promotionOffer, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePromotionOffer(userContext, promotionOffer, tokens);
 	}
 	
 	protected PromotionOffer savePromotionOfferDetail(PromoengineUserContext userContext, PromotionOffer promotionOffer) throws Exception{	

 		
 		return savePromotionOffer(userContext, promotionOffer, allTokens());
 	}
 	
 	public PromotionOffer loadPromotionOffer(PromoengineUserContext userContext, String promotionOfferId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionOfferManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		PromotionOffer promotionOffer = loadPromotionOffer( userContext, promotionOfferId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotionOffer, tokens);
 	}
 	
 	
 	 public PromotionOffer searchPromotionOffer(PromoengineUserContext userContext, String promotionOfferId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionOfferManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		PromotionOffer promotionOffer = loadPromotionOffer( userContext, promotionOfferId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotionOffer, tokens);
 	}
 	
 	

 	protected PromotionOffer present(PromoengineUserContext userContext, PromotionOffer promotionOffer, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,promotionOffer,tokens);
		
		
		PromotionOffer  promotionOfferToPresent = userContext.getDAOGroup().getPromotionOfferDAO().present(promotionOffer, tokens);
		
		List<BaseEntity> entityListToNaming = promotionOfferToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPromotionOfferDAO().alias(entityListToNaming);
		
		return  promotionOfferToPresent;
		
		
	}
 
 	
 	
 	public PromotionOffer loadPromotionOfferDetail(PromoengineUserContext userContext, String promotionOfferId) throws Exception{	
 		PromotionOffer promotionOffer = loadPromotionOffer( userContext, promotionOfferId, allTokens());
 		return present(userContext,promotionOffer, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String promotionOfferId) throws Exception{	
 		PromotionOffer promotionOffer = loadPromotionOffer( userContext, promotionOfferId, viewTokens());
 		return present(userContext,promotionOffer, allTokens());
		
 	}
 	protected PromotionOffer savePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPromotionOfferDAO().save(promotionOffer, tokens);
 	}
 	protected PromotionOffer loadPromotionOffer(PromoengineUserContext userContext, String promotionOfferId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionOfferManagerException.class);

 
 		return userContext.getDAOGroup().getPromotionOfferDAO().load(promotionOfferId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, PromotionOffer promotionOffer, Map<String, Object> tokens){
		super.addActions(userContext, promotionOffer, tokens);
		
		addAction(userContext, promotionOffer, tokens,"@create","createPromotionOffer","createPromotionOffer/","main","primary");
		addAction(userContext, promotionOffer, tokens,"@update","updatePromotionOffer","updatePromotionOffer/"+promotionOffer.getId()+"/","main","primary");
		addAction(userContext, promotionOffer, tokens,"@copy","clonePromotionOffer","clonePromotionOffer/"+promotionOffer.getId()+"/","main","primary");
		
		addAction(userContext, promotionOffer, tokens,"promotion_offer.transfer_to_promotion_level","transferToAnotherPromotionLevel","transferToAnotherPromotionLevel/"+promotionOffer.getId()+"/","main","primary");
		addAction(userContext, promotionOffer, tokens,"promotion_offer.transfer_to_promotion","transferToAnotherPromotion","transferToAnotherPromotion/"+promotionOffer.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, PromotionOffer promotionOffer, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public PromotionOffer createPromotionOffer(PromoengineUserContext userContext,String name, String promotionLevelId, String parameter, String promotionId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPromotionOffer(name);
		userContext.getChecker().checkParameterOfPromotionOffer(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionOfferManagerException.class);


		PromotionOffer promotionOffer=createNewPromotionOffer();	

		promotionOffer.setName(name);
			
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId,emptyOptions());
		promotionOffer.setPromotionLevel(promotionLevel);
		
		
		promotionOffer.setParameter(parameter);
			
		Promotion promotion = loadPromotion(userContext, promotionId,emptyOptions());
		promotionOffer.setPromotion(promotion);
		
		

		promotionOffer = savePromotionOffer(userContext, promotionOffer, emptyOptions());
		
		onNewInstanceCreated(userContext, promotionOffer);
		return promotionOffer;

		
	}
	protected PromotionOffer createNewPromotionOffer() 
	{
		
		return new PromotionOffer();		
	}
	
	protected void checkParamsForUpdatingPromotionOffer(PromoengineUserContext userContext,String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer( promotionOfferVersion);
		

		if(PromotionOffer.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotionOffer(parseString(newValueExpr));
		}		

		
		if(PromotionOffer.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfPromotionOffer(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionOfferManagerException.class);
	
		
	}
	
	
	
	public PromotionOffer clone(PromoengineUserContext userContext, String fromPromotionOfferId) throws Exception{
		
		return userContext.getDAOGroup().getPromotionOfferDAO().clone(fromPromotionOfferId, this.allTokens());
	}
	
	public PromotionOffer internalSavePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer) throws Exception 
	{
		return internalSavePromotionOffer(userContext, promotionOffer, allTokens());

	}
	public PromotionOffer internalSavePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPromotionOffer(userContext, promotionOfferId, promotionOfferVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(promotionOffer){ 
			//will be good when the promotionOffer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionOffer.
			
			
			promotionOffer = savePromotionOffer(userContext, promotionOffer, options);
			return promotionOffer;
			
		}

	}
	
	public PromotionOffer updatePromotionOffer(PromoengineUserContext userContext,String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotionOffer(userContext, promotionOfferId, promotionOfferVersion, property, newValueExpr, tokensExpr);
		
		
		
		PromotionOffer promotionOffer = loadPromotionOffer(userContext, promotionOfferId, allTokens());
		if(promotionOffer.getVersion() != promotionOfferVersion){
			String message = "The target version("+promotionOffer.getVersion()+") is not equals to version("+promotionOfferVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotionOffer){ 
			//will be good when the promotionOffer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionOffer.
			
			promotionOffer.changeProperty(property, newValueExpr);
			promotionOffer = savePromotionOffer(userContext, promotionOffer, tokens().done());
			return present(userContext,promotionOffer, mergedAllTokens(tokensExpr));
			//return savePromotionOffer(userContext, promotionOffer, tokens().done());
		}

	}
	
	public PromotionOffer updatePromotionOfferProperty(PromoengineUserContext userContext,String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotionOffer(userContext, promotionOfferId, promotionOfferVersion, property, newValueExpr, tokensExpr);
		
		PromotionOffer promotionOffer = loadPromotionOffer(userContext, promotionOfferId, allTokens());
		if(promotionOffer.getVersion() != promotionOfferVersion){
			String message = "The target version("+promotionOffer.getVersion()+") is not equals to version("+promotionOfferVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotionOffer){ 
			//will be good when the promotionOffer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionOffer.
			
			promotionOffer.changeProperty(property, newValueExpr);
			
			promotionOffer = savePromotionOffer(userContext, promotionOffer, tokens().done());
			return present(userContext,promotionOffer, mergedAllTokens(tokensExpr));
			//return savePromotionOffer(userContext, promotionOffer, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PromotionOfferTokens tokens(){
		return PromotionOfferTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PromotionOfferTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PromotionOfferTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPromotionLevel(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionLevelId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
 		userContext.getChecker().checkIdOfPromotionLevel(anotherPromotionLevelId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PromotionOfferManagerException.class);
 		
 	}
 	public PromotionOffer transferToAnotherPromotionLevel(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionLevelId) throws Exception
 	{
 		checkParamsForTransferingAnotherPromotionLevel(userContext, promotionOfferId,anotherPromotionLevelId);
 
		PromotionOffer promotionOffer = loadPromotionOffer(userContext, promotionOfferId, allTokens());	
		synchronized(promotionOffer){
			//will be good when the promotionOffer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			PromotionLevel promotionLevel = loadPromotionLevel(userContext, anotherPromotionLevelId, emptyOptions());		
			promotionOffer.updatePromotionLevel(promotionLevel);		
			promotionOffer = savePromotionOffer(userContext, promotionOffer, emptyOptions());
			
			return present(userContext,promotionOffer, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePromotionLevel requestCandidatePromotionLevel(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePromotionLevel result = new CandidatePromotionLevel();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<PromotionLevel> candidateList = userContext.getDAOGroup().getPromotionLevelDAO().requestCandidatePromotionLevelForPromotionOffer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherPromotion(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
 		userContext.getChecker().checkIdOfPromotion(anotherPromotionId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PromotionOfferManagerException.class);
 		
 	}
 	public PromotionOffer transferToAnotherPromotion(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionId) throws Exception
 	{
 		checkParamsForTransferingAnotherPromotion(userContext, promotionOfferId,anotherPromotionId);
 
		PromotionOffer promotionOffer = loadPromotionOffer(userContext, promotionOfferId, allTokens());	
		synchronized(promotionOffer){
			//will be good when the promotionOffer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Promotion promotion = loadPromotion(userContext, anotherPromotionId, emptyOptions());		
			promotionOffer.updatePromotion(promotion);		
			promotionOffer = savePromotionOffer(userContext, promotionOffer, emptyOptions());
			
			return present(userContext,promotionOffer, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePromotion requestCandidatePromotion(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePromotion result = new CandidatePromotion();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Promotion> candidateList = userContext.getDAOGroup().getPromotionDAO().requestCandidatePromotionForPromotionOffer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected PromotionLevel loadPromotionLevel(PromoengineUserContext userContext, String newPromotionLevelId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPromotionLevelDAO().load(newPromotionLevelId, options);
 	}
 	
 	
 	
	
	 	
 	protected Promotion loadPromotion(PromoengineUserContext userContext, String newPromotionId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPromotionDAO().load(newPromotionId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String promotionOfferId, int promotionOfferVersion) throws Exception {
		//deleteInternal(userContext, promotionOfferId, promotionOfferVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String promotionOfferId, int promotionOfferVersion) throws Exception{
			
		userContext.getDAOGroup().getPromotionOfferDAO().delete(promotionOfferId, promotionOfferVersion);
	}
	
	public PromotionOffer forgetByAll(PromoengineUserContext userContext, String promotionOfferId, int promotionOfferVersion) throws Exception {
		return forgetByAllInternal(userContext, promotionOfferId, promotionOfferVersion);		
	}
	protected PromotionOffer forgetByAllInternal(PromoengineUserContext userContext,
			String promotionOfferId, int promotionOfferVersion) throws Exception{
			
		return userContext.getDAOGroup().getPromotionOfferDAO().disconnectFromAll(promotionOfferId, promotionOfferVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PromotionOfferManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPromotionOfferDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(PromoengineUserContext userContext, PromotionOffer newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


