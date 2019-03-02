
package com.doublechaintech.promoengine.targetaction;

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

import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;

import com.doublechaintech.promoengine.promotion.CandidatePromotion;
import com.doublechaintech.promoengine.actiontype.CandidateActionType;







public class TargetActionManagerImpl extends CustomPromoengineCheckerManager implements TargetActionManager {
	
	private static final String SERVICE_TYPE = "TargetAction";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws TargetActionManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new TargetActionManagerException(message);

	}
	
	

 	protected TargetAction saveTargetAction(PromoengineUserContext userContext, TargetAction targetAction, String [] tokensExpr) throws Exception{	
 		//return getTargetActionDAO().save(targetAction, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveTargetAction(userContext, targetAction, tokens);
 	}
 	
 	protected TargetAction saveTargetActionDetail(PromoengineUserContext userContext, TargetAction targetAction) throws Exception{	

 		
 		return saveTargetAction(userContext, targetAction, allTokens());
 	}
 	
 	public TargetAction loadTargetAction(PromoengineUserContext userContext, String targetActionId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetActionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		TargetAction targetAction = loadTargetAction( userContext, targetActionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,targetAction, tokens);
 	}
 	
 	
 	 public TargetAction searchTargetAction(PromoengineUserContext userContext, String targetActionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetActionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		TargetAction targetAction = loadTargetAction( userContext, targetActionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,targetAction, tokens);
 	}
 	
 	

 	protected TargetAction present(PromoengineUserContext userContext, TargetAction targetAction, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,targetAction,tokens);
		
		
		TargetAction  targetActionToPresent = userContext.getDAOGroup().getTargetActionDAO().present(targetAction, tokens);
		
		List<BaseEntity> entityListToNaming = targetActionToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getTargetActionDAO().alias(entityListToNaming);
		
		return  targetActionToPresent;
		
		
	}
 
 	
 	
 	public TargetAction loadTargetActionDetail(PromoengineUserContext userContext, String targetActionId) throws Exception{	
 		TargetAction targetAction = loadTargetAction( userContext, targetActionId, allTokens());
 		return present(userContext,targetAction, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String targetActionId) throws Exception{	
 		TargetAction targetAction = loadTargetAction( userContext, targetActionId, viewTokens());
 		return present(userContext,targetAction, allTokens());
		
 	}
 	protected TargetAction saveTargetAction(PromoengineUserContext userContext, TargetAction targetAction, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getTargetActionDAO().save(targetAction, tokens);
 	}
 	protected TargetAction loadTargetAction(PromoengineUserContext userContext, String targetActionId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetActionManagerException.class);

 
 		return userContext.getDAOGroup().getTargetActionDAO().load(targetActionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, TargetAction targetAction, Map<String, Object> tokens){
		super.addActions(userContext, targetAction, tokens);
		
		addAction(userContext, targetAction, tokens,"@create","createTargetAction","createTargetAction/","main","primary");
		addAction(userContext, targetAction, tokens,"@update","updateTargetAction","updateTargetAction/"+targetAction.getId()+"/","main","primary");
		addAction(userContext, targetAction, tokens,"@copy","cloneTargetAction","cloneTargetAction/"+targetAction.getId()+"/","main","primary");
		
		addAction(userContext, targetAction, tokens,"target_action.transfer_to_action","transferToAnotherAction","transferToAnotherAction/"+targetAction.getId()+"/","main","primary");
		addAction(userContext, targetAction, tokens,"target_action.transfer_to_promotion","transferToAnotherPromotion","transferToAnotherPromotion/"+targetAction.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, TargetAction targetAction, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public TargetAction createTargetAction(PromoengineUserContext userContext,String name, String actionId, String parameter, String promotionId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfTargetAction(name);
		userContext.getChecker().checkParameterOfTargetAction(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(TargetActionManagerException.class);


		TargetAction targetAction=createNewTargetAction();	

		targetAction.setName(name);
			
		ActionType action = loadActionType(userContext, actionId,emptyOptions());
		targetAction.setAction(action);
		
		
		targetAction.setParameter(parameter);
		targetAction.setLastUpdateTime(userContext.now());
			
		Promotion promotion = loadPromotion(userContext, promotionId,emptyOptions());
		targetAction.setPromotion(promotion);
		
		

		targetAction = saveTargetAction(userContext, targetAction, emptyOptions());
		
		onNewInstanceCreated(userContext, targetAction);
		return targetAction;

		
	}
	protected TargetAction createNewTargetAction() 
	{
		
		return new TargetAction();		
	}
	
	protected void checkParamsForUpdatingTargetAction(PromoengineUserContext userContext,String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction( targetActionVersion);
		

		if(TargetAction.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetAction(parseString(newValueExpr));
		}		

		
		if(TargetAction.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetAction(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(TargetActionManagerException.class);
	
		
	}
	
	
	
	public TargetAction clone(PromoengineUserContext userContext, String fromTargetActionId) throws Exception{
		
		return userContext.getDAOGroup().getTargetActionDAO().clone(fromTargetActionId, this.allTokens());
	}
	
	public TargetAction internalSaveTargetAction(PromoengineUserContext userContext, TargetAction targetAction) throws Exception 
	{
		return internalSaveTargetAction(userContext, targetAction, allTokens());

	}
	public TargetAction internalSaveTargetAction(PromoengineUserContext userContext, TargetAction targetAction, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingTargetAction(userContext, targetActionId, targetActionVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(targetAction){ 
			//will be good when the targetAction loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetAction.
			
			
			targetAction = saveTargetAction(userContext, targetAction, options);
			return targetAction;
			
		}

	}
	
	public TargetAction updateTargetAction(PromoengineUserContext userContext,String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingTargetAction(userContext, targetActionId, targetActionVersion, property, newValueExpr, tokensExpr);
		
		
		
		TargetAction targetAction = loadTargetAction(userContext, targetActionId, allTokens());
		if(targetAction.getVersion() != targetActionVersion){
			String message = "The target version("+targetAction.getVersion()+") is not equals to version("+targetActionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(targetAction){ 
			//will be good when the targetAction loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetAction.
			targetAction.updateLastUpdateTime(userContext.now());
			targetAction.changeProperty(property, newValueExpr);
			targetAction = saveTargetAction(userContext, targetAction, tokens().done());
			return present(userContext,targetAction, mergedAllTokens(tokensExpr));
			//return saveTargetAction(userContext, targetAction, tokens().done());
		}

	}
	
	public TargetAction updateTargetActionProperty(PromoengineUserContext userContext,String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingTargetAction(userContext, targetActionId, targetActionVersion, property, newValueExpr, tokensExpr);
		
		TargetAction targetAction = loadTargetAction(userContext, targetActionId, allTokens());
		if(targetAction.getVersion() != targetActionVersion){
			String message = "The target version("+targetAction.getVersion()+") is not equals to version("+targetActionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(targetAction){ 
			//will be good when the targetAction loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetAction.
			
			targetAction.changeProperty(property, newValueExpr);
			targetAction.updateLastUpdateTime(userContext.now());
			targetAction = saveTargetAction(userContext, targetAction, tokens().done());
			return present(userContext,targetAction, mergedAllTokens(tokensExpr));
			//return saveTargetAction(userContext, targetAction, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected TargetActionTokens tokens(){
		return TargetActionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return TargetActionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return TargetActionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherAction(PromoengineUserContext userContext, String targetActionId, String anotherActionId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfTargetAction(targetActionId);
 		userContext.getChecker().checkIdOfActionType(anotherActionId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(TargetActionManagerException.class);
 		
 	}
 	public TargetAction transferToAnotherAction(PromoengineUserContext userContext, String targetActionId, String anotherActionId) throws Exception
 	{
 		checkParamsForTransferingAnotherAction(userContext, targetActionId,anotherActionId);
 
		TargetAction targetAction = loadTargetAction(userContext, targetActionId, allTokens());	
		synchronized(targetAction){
			//will be good when the targetAction loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ActionType action = loadActionType(userContext, anotherActionId, emptyOptions());		
			targetAction.updateAction(action);		
			targetAction = saveTargetAction(userContext, targetAction, emptyOptions());
			
			return present(userContext,targetAction, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateActionType requestCandidateAction(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateActionType result = new CandidateActionType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ActionType> candidateList = userContext.getDAOGroup().getActionTypeDAO().requestCandidateActionTypeForTargetAction(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherPromotion(PromoengineUserContext userContext, String targetActionId, String anotherPromotionId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfTargetAction(targetActionId);
 		userContext.getChecker().checkIdOfPromotion(anotherPromotionId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(TargetActionManagerException.class);
 		
 	}
 	public TargetAction transferToAnotherPromotion(PromoengineUserContext userContext, String targetActionId, String anotherPromotionId) throws Exception
 	{
 		checkParamsForTransferingAnotherPromotion(userContext, targetActionId,anotherPromotionId);
 
		TargetAction targetAction = loadTargetAction(userContext, targetActionId, allTokens());	
		synchronized(targetAction){
			//will be good when the targetAction loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Promotion promotion = loadPromotion(userContext, anotherPromotionId, emptyOptions());		
			targetAction.updatePromotion(promotion);		
			targetAction = saveTargetAction(userContext, targetAction, emptyOptions());
			
			return present(userContext,targetAction, allTokens());
			
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
		SmartList<Promotion> candidateList = userContext.getDAOGroup().getPromotionDAO().requestCandidatePromotionForTargetAction(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected ActionType loadActionType(PromoengineUserContext userContext, String newActionId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getActionTypeDAO().load(newActionId, options);
 	}
 	
 	
 	
	
	 	
 	protected Promotion loadPromotion(PromoengineUserContext userContext, String newPromotionId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPromotionDAO().load(newPromotionId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String targetActionId, int targetActionVersion) throws Exception {
		//deleteInternal(userContext, targetActionId, targetActionVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String targetActionId, int targetActionVersion) throws Exception{
			
		userContext.getDAOGroup().getTargetActionDAO().delete(targetActionId, targetActionVersion);
	}
	
	public TargetAction forgetByAll(PromoengineUserContext userContext, String targetActionId, int targetActionVersion) throws Exception {
		return forgetByAllInternal(userContext, targetActionId, targetActionVersion);		
	}
	protected TargetAction forgetByAllInternal(PromoengineUserContext userContext,
			String targetActionId, int targetActionVersion) throws Exception{
			
		return userContext.getDAOGroup().getTargetActionDAO().disconnectFromAll(targetActionId, targetActionVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new TargetActionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getTargetActionDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(PromoengineUserContext userContext, TargetAction newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


