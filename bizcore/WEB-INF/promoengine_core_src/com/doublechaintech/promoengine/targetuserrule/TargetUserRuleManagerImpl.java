
package com.doublechaintech.promoengine.targetuserrule;

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

import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;

import com.doublechaintech.promoengine.applycondition.CandidateApplyCondition;
import com.doublechaintech.promoengine.promotion.CandidatePromotion;







public class TargetUserRuleManagerImpl extends CustomPromoengineCheckerManager implements TargetUserRuleManager {
	
	private static final String SERVICE_TYPE = "TargetUserRule";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws TargetUserRuleManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new TargetUserRuleManagerException(message);

	}
	
	

 	protected TargetUserRule saveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule, String [] tokensExpr) throws Exception{	
 		//return getTargetUserRuleDAO().save(targetUserRule, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveTargetUserRule(userContext, targetUserRule, tokens);
 	}
 	
 	protected TargetUserRule saveTargetUserRuleDetail(PromoengineUserContext userContext, TargetUserRule targetUserRule) throws Exception{	

 		
 		return saveTargetUserRule(userContext, targetUserRule, allTokens());
 	}
 	
 	public TargetUserRule loadTargetUserRule(PromoengineUserContext userContext, String targetUserRuleId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetUserRuleManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		TargetUserRule targetUserRule = loadTargetUserRule( userContext, targetUserRuleId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,targetUserRule, tokens);
 	}
 	
 	
 	 public TargetUserRule searchTargetUserRule(PromoengineUserContext userContext, String targetUserRuleId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetUserRuleManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		TargetUserRule targetUserRule = loadTargetUserRule( userContext, targetUserRuleId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,targetUserRule, tokens);
 	}
 	
 	

 	protected TargetUserRule present(PromoengineUserContext userContext, TargetUserRule targetUserRule, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,targetUserRule,tokens);
		
		
		TargetUserRule  targetUserRuleToPresent = userContext.getDAOGroup().getTargetUserRuleDAO().present(targetUserRule, tokens);
		
		List<BaseEntity> entityListToNaming = targetUserRuleToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getTargetUserRuleDAO().alias(entityListToNaming);
		
		return  targetUserRuleToPresent;
		
		
	}
 
 	
 	
 	public TargetUserRule loadTargetUserRuleDetail(PromoengineUserContext userContext, String targetUserRuleId) throws Exception{	
 		TargetUserRule targetUserRule = loadTargetUserRule( userContext, targetUserRuleId, allTokens());
 		return present(userContext,targetUserRule, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String targetUserRuleId) throws Exception{	
 		TargetUserRule targetUserRule = loadTargetUserRule( userContext, targetUserRuleId, viewTokens());
 		return present(userContext,targetUserRule, allTokens());
		
 	}
 	protected TargetUserRule saveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getTargetUserRuleDAO().save(targetUserRule, tokens);
 	}
 	protected TargetUserRule loadTargetUserRule(PromoengineUserContext userContext, String targetUserRuleId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().throwExceptionIfHasErrors( TargetUserRuleManagerException.class);

 
 		return userContext.getDAOGroup().getTargetUserRuleDAO().load(targetUserRuleId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, TargetUserRule targetUserRule, Map<String, Object> tokens){
		super.addActions(userContext, targetUserRule, tokens);
		
		addAction(userContext, targetUserRule, tokens,"@create","createTargetUserRule","createTargetUserRule/","main","primary");
		addAction(userContext, targetUserRule, tokens,"@update","updateTargetUserRule","updateTargetUserRule/"+targetUserRule.getId()+"/","main","primary");
		addAction(userContext, targetUserRule, tokens,"@copy","cloneTargetUserRule","cloneTargetUserRule/"+targetUserRule.getId()+"/","main","primary");
		
		addAction(userContext, targetUserRule, tokens,"target_user_rule.transfer_to_apply_condition","transferToAnotherApplyCondition","transferToAnotherApplyCondition/"+targetUserRule.getId()+"/","main","primary");
		addAction(userContext, targetUserRule, tokens,"target_user_rule.transfer_to_promotion","transferToAnotherPromotion","transferToAnotherPromotion/"+targetUserRule.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, TargetUserRule targetUserRule, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public TargetUserRule createTargetUserRule(PromoengineUserContext userContext,String name, String applyConditionId, String parameter, String promotionId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfTargetUserRule(name);
		userContext.getChecker().checkParameterOfTargetUserRule(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(TargetUserRuleManagerException.class);


		TargetUserRule targetUserRule=createNewTargetUserRule();	

		targetUserRule.setName(name);
			
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId,emptyOptions());
		targetUserRule.setApplyCondition(applyCondition);
		
		
		targetUserRule.setParameter(parameter);
		targetUserRule.setLastUpdateTime(userContext.now());
			
		Promotion promotion = loadPromotion(userContext, promotionId,emptyOptions());
		targetUserRule.setPromotion(promotion);
		
		

		targetUserRule = saveTargetUserRule(userContext, targetUserRule, emptyOptions());
		
		onNewInstanceCreated(userContext, targetUserRule);
		return targetUserRule;

		
	}
	protected TargetUserRule createNewTargetUserRule() 
	{
		
		return new TargetUserRule();		
	}
	
	protected void checkParamsForUpdatingTargetUserRule(PromoengineUserContext userContext,String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule( targetUserRuleVersion);
		

		if(TargetUserRule.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetUserRule(parseString(newValueExpr));
		}		

		
		if(TargetUserRule.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetUserRule(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(TargetUserRuleManagerException.class);
	
		
	}
	
	
	
	public TargetUserRule clone(PromoengineUserContext userContext, String fromTargetUserRuleId) throws Exception{
		
		return userContext.getDAOGroup().getTargetUserRuleDAO().clone(fromTargetUserRuleId, this.allTokens());
	}
	
	public TargetUserRule internalSaveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule) throws Exception 
	{
		return internalSaveTargetUserRule(userContext, targetUserRule, allTokens());

	}
	public TargetUserRule internalSaveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingTargetUserRule(userContext, targetUserRuleId, targetUserRuleVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(targetUserRule){ 
			//will be good when the targetUserRule loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetUserRule.
			
			
			targetUserRule = saveTargetUserRule(userContext, targetUserRule, options);
			return targetUserRule;
			
		}

	}
	
	public TargetUserRule updateTargetUserRule(PromoengineUserContext userContext,String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingTargetUserRule(userContext, targetUserRuleId, targetUserRuleVersion, property, newValueExpr, tokensExpr);
		
		
		
		TargetUserRule targetUserRule = loadTargetUserRule(userContext, targetUserRuleId, allTokens());
		if(targetUserRule.getVersion() != targetUserRuleVersion){
			String message = "The target version("+targetUserRule.getVersion()+") is not equals to version("+targetUserRuleVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(targetUserRule){ 
			//will be good when the targetUserRule loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetUserRule.
			targetUserRule.updateLastUpdateTime(userContext.now());
			targetUserRule.changeProperty(property, newValueExpr);
			targetUserRule = saveTargetUserRule(userContext, targetUserRule, tokens().done());
			return present(userContext,targetUserRule, mergedAllTokens(tokensExpr));
			//return saveTargetUserRule(userContext, targetUserRule, tokens().done());
		}

	}
	
	public TargetUserRule updateTargetUserRuleProperty(PromoengineUserContext userContext,String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingTargetUserRule(userContext, targetUserRuleId, targetUserRuleVersion, property, newValueExpr, tokensExpr);
		
		TargetUserRule targetUserRule = loadTargetUserRule(userContext, targetUserRuleId, allTokens());
		if(targetUserRule.getVersion() != targetUserRuleVersion){
			String message = "The target version("+targetUserRule.getVersion()+") is not equals to version("+targetUserRuleVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(targetUserRule){ 
			//will be good when the targetUserRule loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TargetUserRule.
			
			targetUserRule.changeProperty(property, newValueExpr);
			targetUserRule.updateLastUpdateTime(userContext.now());
			targetUserRule = saveTargetUserRule(userContext, targetUserRule, tokens().done());
			return present(userContext,targetUserRule, mergedAllTokens(tokensExpr));
			//return saveTargetUserRule(userContext, targetUserRule, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected TargetUserRuleTokens tokens(){
		return TargetUserRuleTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return TargetUserRuleTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return TargetUserRuleTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherApplyCondition(PromoengineUserContext userContext, String targetUserRuleId, String anotherApplyConditionId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
 		userContext.getChecker().checkIdOfApplyCondition(anotherApplyConditionId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(TargetUserRuleManagerException.class);
 		
 	}
 	public TargetUserRule transferToAnotherApplyCondition(PromoengineUserContext userContext, String targetUserRuleId, String anotherApplyConditionId) throws Exception
 	{
 		checkParamsForTransferingAnotherApplyCondition(userContext, targetUserRuleId,anotherApplyConditionId);
 
		TargetUserRule targetUserRule = loadTargetUserRule(userContext, targetUserRuleId, allTokens());	
		synchronized(targetUserRule){
			//will be good when the targetUserRule loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ApplyCondition applyCondition = loadApplyCondition(userContext, anotherApplyConditionId, emptyOptions());		
			targetUserRule.updateApplyCondition(applyCondition);		
			targetUserRule = saveTargetUserRule(userContext, targetUserRule, emptyOptions());
			
			return present(userContext,targetUserRule, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateApplyCondition requestCandidateApplyCondition(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateApplyCondition result = new CandidateApplyCondition();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ApplyCondition> candidateList = userContext.getDAOGroup().getApplyConditionDAO().requestCandidateApplyConditionForTargetUserRule(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherPromotion(PromoengineUserContext userContext, String targetUserRuleId, String anotherPromotionId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
 		userContext.getChecker().checkIdOfPromotion(anotherPromotionId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(TargetUserRuleManagerException.class);
 		
 	}
 	public TargetUserRule transferToAnotherPromotion(PromoengineUserContext userContext, String targetUserRuleId, String anotherPromotionId) throws Exception
 	{
 		checkParamsForTransferingAnotherPromotion(userContext, targetUserRuleId,anotherPromotionId);
 
		TargetUserRule targetUserRule = loadTargetUserRule(userContext, targetUserRuleId, allTokens());	
		synchronized(targetUserRule){
			//will be good when the targetUserRule loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Promotion promotion = loadPromotion(userContext, anotherPromotionId, emptyOptions());		
			targetUserRule.updatePromotion(promotion);		
			targetUserRule = saveTargetUserRule(userContext, targetUserRule, emptyOptions());
			
			return present(userContext,targetUserRule, allTokens());
			
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
		SmartList<Promotion> candidateList = userContext.getDAOGroup().getPromotionDAO().requestCandidatePromotionForTargetUserRule(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected ApplyCondition loadApplyCondition(PromoengineUserContext userContext, String newApplyConditionId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getApplyConditionDAO().load(newApplyConditionId, options);
 	}
 	
 	
 	
	
	 	
 	protected Promotion loadPromotion(PromoengineUserContext userContext, String newPromotionId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPromotionDAO().load(newPromotionId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String targetUserRuleId, int targetUserRuleVersion) throws Exception {
		//deleteInternal(userContext, targetUserRuleId, targetUserRuleVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String targetUserRuleId, int targetUserRuleVersion) throws Exception{
			
		userContext.getDAOGroup().getTargetUserRuleDAO().delete(targetUserRuleId, targetUserRuleVersion);
	}
	
	public TargetUserRule forgetByAll(PromoengineUserContext userContext, String targetUserRuleId, int targetUserRuleVersion) throws Exception {
		return forgetByAllInternal(userContext, targetUserRuleId, targetUserRuleVersion);		
	}
	protected TargetUserRule forgetByAllInternal(PromoengineUserContext userContext,
			String targetUserRuleId, int targetUserRuleVersion) throws Exception{
			
		return userContext.getDAOGroup().getTargetUserRuleDAO().disconnectFromAll(targetUserRuleId, targetUserRuleVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new TargetUserRuleManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getTargetUserRuleDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(PromoengineUserContext userContext, TargetUserRule newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


