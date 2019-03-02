
package com.doublechaintech.promoengine.applycondition;

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

import com.doublechaintech.promoengine.platform.Platform;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;

import com.doublechaintech.promoengine.platform.CandidatePlatform;

import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;






public class ApplyConditionManagerImpl extends CustomPromoengineCheckerManager implements ApplyConditionManager {
	
	private static final String SERVICE_TYPE = "ApplyCondition";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ApplyConditionManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ApplyConditionManagerException(message);

	}
	
	

 	protected ApplyCondition saveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition, String [] tokensExpr) throws Exception{	
 		//return getApplyConditionDAO().save(applyCondition, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveApplyCondition(userContext, applyCondition, tokens);
 	}
 	
 	protected ApplyCondition saveApplyConditionDetail(PromoengineUserContext userContext, ApplyCondition applyCondition) throws Exception{	

 		
 		return saveApplyCondition(userContext, applyCondition, allTokens());
 	}
 	
 	public ApplyCondition loadApplyCondition(PromoengineUserContext userContext, String applyConditionId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().throwExceptionIfHasErrors( ApplyConditionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ApplyCondition applyCondition = loadApplyCondition( userContext, applyConditionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,applyCondition, tokens);
 	}
 	
 	
 	 public ApplyCondition searchApplyCondition(PromoengineUserContext userContext, String applyConditionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().throwExceptionIfHasErrors( ApplyConditionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ApplyCondition applyCondition = loadApplyCondition( userContext, applyConditionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,applyCondition, tokens);
 	}
 	
 	

 	protected ApplyCondition present(PromoengineUserContext userContext, ApplyCondition applyCondition, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,applyCondition,tokens);
		
		
		ApplyCondition  applyConditionToPresent = userContext.getDAOGroup().getApplyConditionDAO().present(applyCondition, tokens);
		
		List<BaseEntity> entityListToNaming = applyConditionToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getApplyConditionDAO().alias(entityListToNaming);
		
		return  applyConditionToPresent;
		
		
	}
 
 	
 	
 	public ApplyCondition loadApplyConditionDetail(PromoengineUserContext userContext, String applyConditionId) throws Exception{	
 		ApplyCondition applyCondition = loadApplyCondition( userContext, applyConditionId, allTokens());
 		return present(userContext,applyCondition, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String applyConditionId) throws Exception{	
 		ApplyCondition applyCondition = loadApplyCondition( userContext, applyConditionId, viewTokens());
 		return present(userContext,applyCondition, allTokens());
		
 	}
 	protected ApplyCondition saveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getApplyConditionDAO().save(applyCondition, tokens);
 	}
 	protected ApplyCondition loadApplyCondition(PromoengineUserContext userContext, String applyConditionId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().throwExceptionIfHasErrors( ApplyConditionManagerException.class);

 
 		return userContext.getDAOGroup().getApplyConditionDAO().load(applyConditionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, ApplyCondition applyCondition, Map<String, Object> tokens){
		super.addActions(userContext, applyCondition, tokens);
		
		addAction(userContext, applyCondition, tokens,"@create","createApplyCondition","createApplyCondition/","main","primary");
		addAction(userContext, applyCondition, tokens,"@update","updateApplyCondition","updateApplyCondition/"+applyCondition.getId()+"/","main","primary");
		addAction(userContext, applyCondition, tokens,"@copy","cloneApplyCondition","cloneApplyCondition/"+applyCondition.getId()+"/","main","primary");
		
		addAction(userContext, applyCondition, tokens,"apply_condition.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+applyCondition.getId()+"/","main","primary");
		addAction(userContext, applyCondition, tokens,"apply_condition.addTargetUserRule","addTargetUserRule","addTargetUserRule/"+applyCondition.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, applyCondition, tokens,"apply_condition.removeTargetUserRule","removeTargetUserRule","removeTargetUserRule/"+applyCondition.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, applyCondition, tokens,"apply_condition.updateTargetUserRule","updateTargetUserRule","updateTargetUserRule/"+applyCondition.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, applyCondition, tokens,"apply_condition.copyTargetUserRuleFrom","copyTargetUserRuleFrom","copyTargetUserRuleFrom/"+applyCondition.getId()+"/","targetUserRuleList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, ApplyCondition applyCondition, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public ApplyCondition createApplyCondition(PromoengineUserContext userContext,String name, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfApplyCondition(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);


		ApplyCondition applyCondition=createNewApplyCondition();	

		applyCondition.setName(name);
		applyCondition.setLastUpdateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		applyCondition.setPlatform(platform);
		
		

		applyCondition = saveApplyCondition(userContext, applyCondition, emptyOptions());
		
		onNewInstanceCreated(userContext, applyCondition);
		return applyCondition;

		
	}
	protected ApplyCondition createNewApplyCondition() 
	{
		
		return new ApplyCondition();		
	}
	
	protected void checkParamsForUpdatingApplyCondition(PromoengineUserContext userContext,String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkVersionOfApplyCondition( applyConditionVersion);
		

		if(ApplyCondition.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfApplyCondition(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
	
		
	}
	
	
	
	public ApplyCondition clone(PromoengineUserContext userContext, String fromApplyConditionId) throws Exception{
		
		return userContext.getDAOGroup().getApplyConditionDAO().clone(fromApplyConditionId, this.allTokens());
	}
	
	public ApplyCondition internalSaveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition) throws Exception 
	{
		return internalSaveApplyCondition(userContext, applyCondition, allTokens());

	}
	public ApplyCondition internalSaveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingApplyCondition(userContext, applyConditionId, applyConditionVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(applyCondition){ 
			//will be good when the applyCondition loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ApplyCondition.
			
			
			applyCondition = saveApplyCondition(userContext, applyCondition, options);
			return applyCondition;
			
		}

	}
	
	public ApplyCondition updateApplyCondition(PromoengineUserContext userContext,String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingApplyCondition(userContext, applyConditionId, applyConditionVersion, property, newValueExpr, tokensExpr);
		
		
		
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
		if(applyCondition.getVersion() != applyConditionVersion){
			String message = "The target version("+applyCondition.getVersion()+") is not equals to version("+applyConditionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(applyCondition){ 
			//will be good when the applyCondition loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ApplyCondition.
			applyCondition.updateLastUpdateTime(userContext.now());
			applyCondition.changeProperty(property, newValueExpr);
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().done());
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
			//return saveApplyCondition(userContext, applyCondition, tokens().done());
		}

	}
	
	public ApplyCondition updateApplyConditionProperty(PromoengineUserContext userContext,String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingApplyCondition(userContext, applyConditionId, applyConditionVersion, property, newValueExpr, tokensExpr);
		
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
		if(applyCondition.getVersion() != applyConditionVersion){
			String message = "The target version("+applyCondition.getVersion()+") is not equals to version("+applyConditionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(applyCondition){ 
			//will be good when the applyCondition loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ApplyCondition.
			
			applyCondition.changeProperty(property, newValueExpr);
			applyCondition.updateLastUpdateTime(userContext.now());
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().done());
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
			//return saveApplyCondition(userContext, applyCondition, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ApplyConditionTokens tokens(){
		return ApplyConditionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ApplyConditionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTargetUserRuleListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ApplyConditionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(PromoengineUserContext userContext, String applyConditionId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
 		
 	}
 	public ApplyCondition transferToAnotherPlatform(PromoengineUserContext userContext, String applyConditionId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, applyConditionId,anotherPlatformId);
 
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());	
		synchronized(applyCondition){
			//will be good when the applyCondition loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			applyCondition.updatePlatform(platform);		
			applyCondition = saveApplyCondition(userContext, applyCondition, emptyOptions());
			
			return present(userContext,applyCondition, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePlatform requestCandidatePlatform(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForApplyCondition(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Platform loadPlatform(PromoengineUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String applyConditionId, int applyConditionVersion) throws Exception {
		//deleteInternal(userContext, applyConditionId, applyConditionVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String applyConditionId, int applyConditionVersion) throws Exception{
			
		userContext.getDAOGroup().getApplyConditionDAO().delete(applyConditionId, applyConditionVersion);
	}
	
	public ApplyCondition forgetByAll(PromoengineUserContext userContext, String applyConditionId, int applyConditionVersion) throws Exception {
		return forgetByAllInternal(userContext, applyConditionId, applyConditionVersion);		
	}
	protected ApplyCondition forgetByAllInternal(PromoengineUserContext userContext,
			String applyConditionId, int applyConditionVersion) throws Exception{
			
		return userContext.getDAOGroup().getApplyConditionDAO().disconnectFromAll(applyConditionId, applyConditionVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ApplyConditionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getApplyConditionDAO().deleteAll();
	}


	//disconnect ApplyCondition with promotion in TargetUserRule
	protected ApplyCondition breakWithTargetUserRuleByPromotion(PromoengineUserContext userContext, String applyConditionId, String promotionId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());

			synchronized(applyCondition){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getApplyConditionDAO().planToRemoveTargetUserRuleListWithPromotion(applyCondition, promotionId, this.emptyOptions());

				applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
				return applyCondition;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String name, String parameter, String promotionId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);

		
		userContext.getChecker().checkNameOfTargetUserRule(name);
		
		userContext.getChecker().checkParameterOfTargetUserRule(parameter);
		
		userContext.getChecker().checkPromotionIdOfTargetUserRule(promotionId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);

	
	}
	public  ApplyCondition addTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String name, String parameter, String promotionId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTargetUserRule(userContext,applyConditionId,name, parameter, promotionId,tokensExpr);
		
		TargetUserRule targetUserRule = createTargetUserRule(userContext,name, parameter, promotionId);
		
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
		synchronized(applyCondition){ 
			//Will be good when the applyCondition loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			applyCondition.addTargetUserRule( targetUserRule );		
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
			
			userContext.getManagerGroup().getTargetUserRuleManager().onNewInstanceCreated(userContext, targetUserRule);
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTargetUserRuleProperties(PromoengineUserContext userContext, String applyConditionId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkIdOfTargetUserRule(id);
		
		userContext.getChecker().checkNameOfTargetUserRule( name);
		userContext.getChecker().checkParameterOfTargetUserRule( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
		
	}
	public  ApplyCondition updateTargetUserRuleProperties(PromoengineUserContext userContext, String applyConditionId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTargetUserRuleProperties(userContext,applyConditionId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTargetUserRuleListList()
				.searchTargetUserRuleListWith(TargetUserRule.ID_PROPERTY, "is", id).done();
		
		ApplyCondition applyConditionToUpdate = loadApplyCondition(userContext, applyConditionId, options);
		
		if(applyConditionToUpdate.getTargetUserRuleList().isEmpty()){
			throw new ApplyConditionManagerException("TargetUserRule is NOT FOUND with id: '"+id+"'");
		}
		
		TargetUserRule item = applyConditionToUpdate.getTargetUserRuleList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingTargetUserRule(userContext,applyConditionId,name, code, used,tokensExpr);
		ApplyCondition applyCondition = saveApplyCondition(userContext, applyConditionToUpdate, tokens().withTargetUserRuleList().done());
		synchronized(applyCondition){ 
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected TargetUserRule createTargetUserRule(PromoengineUserContext userContext, String name, String parameter, String promotionId) throws Exception{

		TargetUserRule targetUserRule = new TargetUserRule();
		
		
		targetUserRule.setName(name);		
		targetUserRule.setParameter(parameter);		
		targetUserRule.setLastUpdateTime(userContext.now());		
		Promotion  promotion = new Promotion();
		promotion.setId(promotionId);		
		targetUserRule.setPromotion(promotion);
	
		
		return targetUserRule;
	
		
	}
	
	protected TargetUserRule createIndexedTargetUserRule(String id, int version){

		TargetUserRule targetUserRule = new TargetUserRule();
		targetUserRule.setId(id);
		targetUserRule.setVersion(version);
		return targetUserRule;			
		
	}
	
	protected void checkParamsForRemovingTargetUserRuleList(PromoengineUserContext userContext, String applyConditionId, 
			String targetUserRuleIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		for(String targetUserRuleId: targetUserRuleIds){
			userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
		
	}
	public  ApplyCondition removeTargetUserRuleList(PromoengineUserContext userContext, String applyConditionId, 
			String targetUserRuleIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTargetUserRuleList(userContext, applyConditionId,  targetUserRuleIds, tokensExpr);
			
			
			ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
			synchronized(applyCondition){ 
				//Will be good when the applyCondition loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getApplyConditionDAO().planToRemoveTargetUserRuleList(applyCondition, targetUserRuleIds, allTokens());
				applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
				deleteRelationListInGraph(userContext, applyCondition.getTargetUserRuleList());
				return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTargetUserRule(PromoengineUserContext userContext, String applyConditionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfApplyCondition( applyConditionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
	
	}
	public  ApplyCondition removeTargetUserRule(PromoengineUserContext userContext, String applyConditionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTargetUserRule(userContext,applyConditionId, targetUserRuleId, targetUserRuleVersion,tokensExpr);
		
		TargetUserRule targetUserRule = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
		synchronized(applyCondition){ 
			//Will be good when the applyCondition loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			applyCondition.removeTargetUserRule( targetUserRule );		
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
			deleteRelationInGraph(userContext, targetUserRule);
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTargetUserRule(PromoengineUserContext userContext, String applyConditionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfApplyCondition( applyConditionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
	
	}
	public  ApplyCondition copyTargetUserRuleFrom(PromoengineUserContext userContext, String applyConditionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTargetUserRule(userContext,applyConditionId, targetUserRuleId, targetUserRuleVersion,tokensExpr);
		
		TargetUserRule targetUserRule = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, allTokens());
		synchronized(applyCondition){ 
			//Will be good when the applyCondition loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			targetUserRule.updateLastUpdateTime(userContext.now());
			
			applyCondition.copyTargetUserRuleFrom( targetUserRule );		
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
			
			userContext.getManagerGroup().getTargetUserRuleManager().onNewInstanceCreated(userContext, (TargetUserRule)applyCondition.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		

		if(TargetUserRule.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetUserRule(parseString(newValueExpr));
		}
		
		if(TargetUserRule.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetUserRule(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ApplyConditionManagerException.class);
	
	}
	
	public  ApplyCondition updateTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTargetUserRule(userContext, applyConditionId, targetUserRuleId, targetUserRuleVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTargetUserRuleList().searchTargetUserRuleListWith(TargetUserRule.ID_PROPERTY, "eq", targetUserRuleId).done();
		
		
		
		ApplyCondition applyCondition = loadApplyCondition(userContext, applyConditionId, loadTokens);
		
		synchronized(applyCondition){ 
			//Will be good when the applyCondition loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//applyCondition.removeTargetUserRule( targetUserRule );	
			//make changes to AcceleraterAccount.
			TargetUserRule targetUserRuleIndex = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		
			TargetUserRule targetUserRule = applyCondition.findTheTargetUserRule(targetUserRuleIndex);
			if(targetUserRule == null){
				throw new ApplyConditionManagerException(targetUserRule+" is NOT FOUND" );
			}
			
			targetUserRule.changeProperty(property, newValueExpr);
			targetUserRule.updateLastUpdateTime(userContext.now());
			applyCondition = saveApplyCondition(userContext, applyCondition, tokens().withTargetUserRuleList().done());
			return present(userContext,applyCondition, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(PromoengineUserContext userContext, ApplyCondition newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


