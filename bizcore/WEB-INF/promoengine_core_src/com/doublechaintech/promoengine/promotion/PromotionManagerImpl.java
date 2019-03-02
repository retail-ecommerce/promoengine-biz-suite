
package com.doublechaintech.promoengine.promotion;

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
import com.doublechaintech.promoengine.targetaction.TargetAction;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

import com.doublechaintech.promoengine.platform.CandidatePlatform;

import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;






public class PromotionManagerImpl extends CustomPromoengineCheckerManager implements PromotionManager {
	
	private static final String SERVICE_TYPE = "Promotion";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PromotionManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PromotionManagerException(message);

	}
	
	

 	protected Promotion savePromotion(PromoengineUserContext userContext, Promotion promotion, String [] tokensExpr) throws Exception{	
 		//return getPromotionDAO().save(promotion, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePromotion(userContext, promotion, tokens);
 	}
 	
 	protected Promotion savePromotionDetail(PromoengineUserContext userContext, Promotion promotion) throws Exception{	

 		
 		return savePromotion(userContext, promotion, allTokens());
 	}
 	
 	public Promotion loadPromotion(PromoengineUserContext userContext, String promotionId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Promotion promotion = loadPromotion( userContext, promotionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotion, tokens);
 	}
 	
 	
 	 public Promotion searchPromotion(PromoengineUserContext userContext, String promotionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Promotion promotion = loadPromotion( userContext, promotionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotion, tokens);
 	}
 	
 	

 	protected Promotion present(PromoengineUserContext userContext, Promotion promotion, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,promotion,tokens);
		
		
		Promotion  promotionToPresent = userContext.getDAOGroup().getPromotionDAO().present(promotion, tokens);
		
		List<BaseEntity> entityListToNaming = promotionToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPromotionDAO().alias(entityListToNaming);
		
		return  promotionToPresent;
		
		
	}
 
 	
 	
 	public Promotion loadPromotionDetail(PromoengineUserContext userContext, String promotionId) throws Exception{	
 		Promotion promotion = loadPromotion( userContext, promotionId, allTokens());
 		return present(userContext,promotion, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String promotionId) throws Exception{	
 		Promotion promotion = loadPromotion( userContext, promotionId, viewTokens());
 		return present(userContext,promotion, allTokens());
		
 	}
 	protected Promotion savePromotion(PromoengineUserContext userContext, Promotion promotion, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPromotionDAO().save(promotion, tokens);
 	}
 	protected Promotion loadPromotion(PromoengineUserContext userContext, String promotionId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionManagerException.class);

 
 		return userContext.getDAOGroup().getPromotionDAO().load(promotionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, Promotion promotion, Map<String, Object> tokens){
		super.addActions(userContext, promotion, tokens);
		
		addAction(userContext, promotion, tokens,"@create","createPromotion","createPromotion/","main","primary");
		addAction(userContext, promotion, tokens,"@update","updatePromotion","updatePromotion/"+promotion.getId()+"/","main","primary");
		addAction(userContext, promotion, tokens,"@copy","clonePromotion","clonePromotion/"+promotion.getId()+"/","main","primary");
		
		addAction(userContext, promotion, tokens,"promotion.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+promotion.getId()+"/","main","primary");
		addAction(userContext, promotion, tokens,"promotion.addTargetUserRule","addTargetUserRule","addTargetUserRule/"+promotion.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, promotion, tokens,"promotion.removeTargetUserRule","removeTargetUserRule","removeTargetUserRule/"+promotion.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, promotion, tokens,"promotion.updateTargetUserRule","updateTargetUserRule","updateTargetUserRule/"+promotion.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, promotion, tokens,"promotion.copyTargetUserRuleFrom","copyTargetUserRuleFrom","copyTargetUserRuleFrom/"+promotion.getId()+"/","targetUserRuleList","primary");
		addAction(userContext, promotion, tokens,"promotion.addTargetAction","addTargetAction","addTargetAction/"+promotion.getId()+"/","targetActionList","primary");
		addAction(userContext, promotion, tokens,"promotion.removeTargetAction","removeTargetAction","removeTargetAction/"+promotion.getId()+"/","targetActionList","primary");
		addAction(userContext, promotion, tokens,"promotion.updateTargetAction","updateTargetAction","updateTargetAction/"+promotion.getId()+"/","targetActionList","primary");
		addAction(userContext, promotion, tokens,"promotion.copyTargetActionFrom","copyTargetActionFrom","copyTargetActionFrom/"+promotion.getId()+"/","targetActionList","primary");
		addAction(userContext, promotion, tokens,"promotion.addPromotionOffer","addPromotionOffer","addPromotionOffer/"+promotion.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotion, tokens,"promotion.removePromotionOffer","removePromotionOffer","removePromotionOffer/"+promotion.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotion, tokens,"promotion.updatePromotionOffer","updatePromotionOffer","updatePromotionOffer/"+promotion.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotion, tokens,"promotion.copyPromotionOfferFrom","copyPromotionOfferFrom","copyPromotionOfferFrom/"+promotion.getId()+"/","promotionOfferList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, Promotion promotion, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Promotion createPromotion(PromoengineUserContext userContext,String name, DateTime validAfter, DateTime expireTime, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPromotion(name);
		userContext.getChecker().checkValidAfterOfPromotion(validAfter);
		userContext.getChecker().checkExpireTimeOfPromotion(expireTime);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);


		Promotion promotion=createNewPromotion();	

		promotion.setName(name);
		promotion.setValidAfter(validAfter);
		promotion.setExpireTime(expireTime);
		promotion.setLastUpdateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		promotion.setPlatform(platform);
		
		

		promotion = savePromotion(userContext, promotion, emptyOptions());
		
		onNewInstanceCreated(userContext, promotion);
		return promotion;

		
	}
	protected Promotion createNewPromotion() 
	{
		
		return new Promotion();		
	}
	
	protected void checkParamsForUpdatingPromotion(PromoengineUserContext userContext,String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkVersionOfPromotion( promotionVersion);
		

		if(Promotion.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotion(parseString(newValueExpr));
		}
		if(Promotion.VALID_AFTER_PROPERTY.equals(property)){
			userContext.getChecker().checkValidAfterOfPromotion(parseTimestamp(newValueExpr));
		}
		if(Promotion.EXPIRE_TIME_PROPERTY.equals(property)){
			userContext.getChecker().checkExpireTimeOfPromotion(parseTimestamp(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
		
	}
	
	
	
	public Promotion clone(PromoengineUserContext userContext, String fromPromotionId) throws Exception{
		
		return userContext.getDAOGroup().getPromotionDAO().clone(fromPromotionId, this.allTokens());
	}
	
	public Promotion internalSavePromotion(PromoengineUserContext userContext, Promotion promotion) throws Exception 
	{
		return internalSavePromotion(userContext, promotion, allTokens());

	}
	public Promotion internalSavePromotion(PromoengineUserContext userContext, Promotion promotion, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPromotion(userContext, promotionId, promotionVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(promotion){ 
			//will be good when the promotion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Promotion.
			
			
			promotion = savePromotion(userContext, promotion, options);
			return promotion;
			
		}

	}
	
	public Promotion updatePromotion(PromoengineUserContext userContext,String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotion(userContext, promotionId, promotionVersion, property, newValueExpr, tokensExpr);
		
		
		
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		if(promotion.getVersion() != promotionVersion){
			String message = "The target version("+promotion.getVersion()+") is not equals to version("+promotionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotion){ 
			//will be good when the promotion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Promotion.
			promotion.updateLastUpdateTime(userContext.now());
			promotion.changeProperty(property, newValueExpr);
			promotion = savePromotion(userContext, promotion, tokens().done());
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
			//return savePromotion(userContext, promotion, tokens().done());
		}

	}
	
	public Promotion updatePromotionProperty(PromoengineUserContext userContext,String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotion(userContext, promotionId, promotionVersion, property, newValueExpr, tokensExpr);
		
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		if(promotion.getVersion() != promotionVersion){
			String message = "The target version("+promotion.getVersion()+") is not equals to version("+promotionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotion){ 
			//will be good when the promotion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Promotion.
			
			promotion.changeProperty(property, newValueExpr);
			promotion.updateLastUpdateTime(userContext.now());
			promotion = savePromotion(userContext, promotion, tokens().done());
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
			//return savePromotion(userContext, promotion, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PromotionTokens tokens(){
		return PromotionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PromotionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTargetUserRuleListWith("id","desc")
		.sortTargetActionListWith("id","desc")
		.sortPromotionOfferListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PromotionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(PromoengineUserContext userContext, String promotionId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPromotion(promotionId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
 		
 	}
 	public Promotion transferToAnotherPlatform(PromoengineUserContext userContext, String promotionId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, promotionId,anotherPlatformId);
 
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());	
		synchronized(promotion){
			//will be good when the promotion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			promotion.updatePlatform(platform);		
			promotion = savePromotion(userContext, promotion, emptyOptions());
			
			return present(userContext,promotion, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForPromotion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(PromoengineUserContext userContext, String promotionId, int promotionVersion) throws Exception {
		//deleteInternal(userContext, promotionId, promotionVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String promotionId, int promotionVersion) throws Exception{
			
		userContext.getDAOGroup().getPromotionDAO().delete(promotionId, promotionVersion);
	}
	
	public Promotion forgetByAll(PromoengineUserContext userContext, String promotionId, int promotionVersion) throws Exception {
		return forgetByAllInternal(userContext, promotionId, promotionVersion);		
	}
	protected Promotion forgetByAllInternal(PromoengineUserContext userContext,
			String promotionId, int promotionVersion) throws Exception{
			
		return userContext.getDAOGroup().getPromotionDAO().disconnectFromAll(promotionId, promotionVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PromotionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPromotionDAO().deleteAll();
	}


	//disconnect Promotion with apply_condition in TargetUserRule
	protected Promotion breakWithTargetUserRuleByApplyCondition(PromoengineUserContext userContext, String promotionId, String applyConditionId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());

			synchronized(promotion){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPromotionDAO().planToRemoveTargetUserRuleListWithApplyCondition(promotion, applyConditionId, this.emptyOptions());

				promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
				return promotion;
			}
	}
	//disconnect Promotion with action in TargetAction
	protected Promotion breakWithTargetActionByAction(PromoengineUserContext userContext, String promotionId, String actionId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());

			synchronized(promotion){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPromotionDAO().planToRemoveTargetActionListWithAction(promotion, actionId, this.emptyOptions());

				promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
				return promotion;
			}
	}
	//disconnect Promotion with promotion_level in PromotionOffer
	protected Promotion breakWithPromotionOfferByPromotionLevel(PromoengineUserContext userContext, String promotionId, String promotionLevelId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());

			synchronized(promotion){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPromotionDAO().planToRemovePromotionOfferListWithPromotionLevel(promotion, promotionLevelId, this.emptyOptions());

				promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
				return promotion;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingTargetUserRule(PromoengineUserContext userContext, String promotionId, String name, String applyConditionId, String parameter,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPromotion(promotionId);

		
		userContext.getChecker().checkNameOfTargetUserRule(name);
		
		userContext.getChecker().checkApplyConditionIdOfTargetUserRule(applyConditionId);
		
		userContext.getChecker().checkParameterOfTargetUserRule(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);

	
	}
	public  Promotion addTargetUserRule(PromoengineUserContext userContext, String promotionId, String name, String applyConditionId, String parameter, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTargetUserRule(userContext,promotionId,name, applyConditionId, parameter,tokensExpr);
		
		TargetUserRule targetUserRule = createTargetUserRule(userContext,name, applyConditionId, parameter);
		
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.addTargetUserRule( targetUserRule );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
			
			userContext.getManagerGroup().getTargetUserRuleManager().onNewInstanceCreated(userContext, targetUserRule);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTargetUserRuleProperties(PromoengineUserContext userContext, String promotionId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfTargetUserRule(id);
		
		userContext.getChecker().checkNameOfTargetUserRule( name);
		userContext.getChecker().checkParameterOfTargetUserRule( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion updateTargetUserRuleProperties(PromoengineUserContext userContext, String promotionId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTargetUserRuleProperties(userContext,promotionId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTargetUserRuleListList()
				.searchTargetUserRuleListWith(TargetUserRule.ID_PROPERTY, "is", id).done();
		
		Promotion promotionToUpdate = loadPromotion(userContext, promotionId, options);
		
		if(promotionToUpdate.getTargetUserRuleList().isEmpty()){
			throw new PromotionManagerException("TargetUserRule is NOT FOUND with id: '"+id+"'");
		}
		
		TargetUserRule item = promotionToUpdate.getTargetUserRuleList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingTargetUserRule(userContext,promotionId,name, code, used,tokensExpr);
		Promotion promotion = savePromotion(userContext, promotionToUpdate, tokens().withTargetUserRuleList().done());
		synchronized(promotion){ 
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected TargetUserRule createTargetUserRule(PromoengineUserContext userContext, String name, String applyConditionId, String parameter) throws Exception{

		TargetUserRule targetUserRule = new TargetUserRule();
		
		
		targetUserRule.setName(name);		
		ApplyCondition  applyCondition = new ApplyCondition();
		applyCondition.setId(applyConditionId);		
		targetUserRule.setApplyCondition(applyCondition);		
		targetUserRule.setParameter(parameter);		
		targetUserRule.setLastUpdateTime(userContext.now());
	
		
		return targetUserRule;
	
		
	}
	
	protected TargetUserRule createIndexedTargetUserRule(String id, int version){

		TargetUserRule targetUserRule = new TargetUserRule();
		targetUserRule.setId(id);
		targetUserRule.setVersion(version);
		return targetUserRule;			
		
	}
	
	protected void checkParamsForRemovingTargetUserRuleList(PromoengineUserContext userContext, String promotionId, 
			String targetUserRuleIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		for(String targetUserRuleId: targetUserRuleIds){
			userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion removeTargetUserRuleList(PromoengineUserContext userContext, String promotionId, 
			String targetUserRuleIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTargetUserRuleList(userContext, promotionId,  targetUserRuleIds, tokensExpr);
			
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
			synchronized(promotion){ 
				//Will be good when the promotion loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPromotionDAO().planToRemoveTargetUserRuleList(promotion, targetUserRuleIds, allTokens());
				promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
				deleteRelationListInGraph(userContext, promotion.getTargetUserRuleList());
				return present(userContext,promotion, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTargetUserRule(PromoengineUserContext userContext, String promotionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion removeTargetUserRule(PromoengineUserContext userContext, String promotionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTargetUserRule(userContext,promotionId, targetUserRuleId, targetUserRuleVersion,tokensExpr);
		
		TargetUserRule targetUserRule = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.removeTargetUserRule( targetUserRule );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
			deleteRelationInGraph(userContext, targetUserRule);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTargetUserRule(PromoengineUserContext userContext, String promotionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion copyTargetUserRuleFrom(PromoengineUserContext userContext, String promotionId, 
		String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTargetUserRule(userContext,promotionId, targetUserRuleId, targetUserRuleVersion,tokensExpr);
		
		TargetUserRule targetUserRule = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			targetUserRule.updateLastUpdateTime(userContext.now());
			
			promotion.copyTargetUserRuleFrom( targetUserRule );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
			
			userContext.getManagerGroup().getTargetUserRuleManager().onNewInstanceCreated(userContext, (TargetUserRule)promotion.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTargetUserRule(PromoengineUserContext userContext, String promotionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfTargetUserRule(targetUserRuleId);
		userContext.getChecker().checkVersionOfTargetUserRule(targetUserRuleVersion);
		

		if(TargetUserRule.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetUserRule(parseString(newValueExpr));
		}
		
		if(TargetUserRule.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetUserRule(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	
	public  Promotion updateTargetUserRule(PromoengineUserContext userContext, String promotionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTargetUserRule(userContext, promotionId, targetUserRuleId, targetUserRuleVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTargetUserRuleList().searchTargetUserRuleListWith(TargetUserRule.ID_PROPERTY, "eq", targetUserRuleId).done();
		
		
		
		Promotion promotion = loadPromotion(userContext, promotionId, loadTokens);
		
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//promotion.removeTargetUserRule( targetUserRule );	
			//make changes to AcceleraterAccount.
			TargetUserRule targetUserRuleIndex = createIndexedTargetUserRule(targetUserRuleId, targetUserRuleVersion);
		
			TargetUserRule targetUserRule = promotion.findTheTargetUserRule(targetUserRuleIndex);
			if(targetUserRule == null){
				throw new PromotionManagerException(targetUserRule+" is NOT FOUND" );
			}
			
			targetUserRule.changeProperty(property, newValueExpr);
			targetUserRule.updateLastUpdateTime(userContext.now());
			promotion = savePromotion(userContext, promotion, tokens().withTargetUserRuleList().done());
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingTargetAction(PromoengineUserContext userContext, String promotionId, String name, String actionId, String parameter,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPromotion(promotionId);

		
		userContext.getChecker().checkNameOfTargetAction(name);
		
		userContext.getChecker().checkActionIdOfTargetAction(actionId);
		
		userContext.getChecker().checkParameterOfTargetAction(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);

	
	}
	public  Promotion addTargetAction(PromoengineUserContext userContext, String promotionId, String name, String actionId, String parameter, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTargetAction(userContext,promotionId,name, actionId, parameter,tokensExpr);
		
		TargetAction targetAction = createTargetAction(userContext,name, actionId, parameter);
		
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.addTargetAction( targetAction );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
			
			userContext.getManagerGroup().getTargetActionManager().onNewInstanceCreated(userContext, targetAction);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTargetActionProperties(PromoengineUserContext userContext, String promotionId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfTargetAction(id);
		
		userContext.getChecker().checkNameOfTargetAction( name);
		userContext.getChecker().checkParameterOfTargetAction( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion updateTargetActionProperties(PromoengineUserContext userContext, String promotionId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTargetActionProperties(userContext,promotionId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTargetActionListList()
				.searchTargetActionListWith(TargetAction.ID_PROPERTY, "is", id).done();
		
		Promotion promotionToUpdate = loadPromotion(userContext, promotionId, options);
		
		if(promotionToUpdate.getTargetActionList().isEmpty()){
			throw new PromotionManagerException("TargetAction is NOT FOUND with id: '"+id+"'");
		}
		
		TargetAction item = promotionToUpdate.getTargetActionList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingTargetAction(userContext,promotionId,name, code, used,tokensExpr);
		Promotion promotion = savePromotion(userContext, promotionToUpdate, tokens().withTargetActionList().done());
		synchronized(promotion){ 
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected TargetAction createTargetAction(PromoengineUserContext userContext, String name, String actionId, String parameter) throws Exception{

		TargetAction targetAction = new TargetAction();
		
		
		targetAction.setName(name);		
		ActionType  action = new ActionType();
		action.setId(actionId);		
		targetAction.setAction(action);		
		targetAction.setParameter(parameter);		
		targetAction.setLastUpdateTime(userContext.now());
	
		
		return targetAction;
	
		
	}
	
	protected TargetAction createIndexedTargetAction(String id, int version){

		TargetAction targetAction = new TargetAction();
		targetAction.setId(id);
		targetAction.setVersion(version);
		return targetAction;			
		
	}
	
	protected void checkParamsForRemovingTargetActionList(PromoengineUserContext userContext, String promotionId, 
			String targetActionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		for(String targetActionId: targetActionIds){
			userContext.getChecker().checkIdOfTargetAction(targetActionId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion removeTargetActionList(PromoengineUserContext userContext, String promotionId, 
			String targetActionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTargetActionList(userContext, promotionId,  targetActionIds, tokensExpr);
			
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
			synchronized(promotion){ 
				//Will be good when the promotion loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPromotionDAO().planToRemoveTargetActionList(promotion, targetActionIds, allTokens());
				promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
				deleteRelationListInGraph(userContext, promotion.getTargetActionList());
				return present(userContext,promotion, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTargetAction(PromoengineUserContext userContext, String promotionId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion removeTargetAction(PromoengineUserContext userContext, String promotionId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTargetAction(userContext,promotionId, targetActionId, targetActionVersion,tokensExpr);
		
		TargetAction targetAction = createIndexedTargetAction(targetActionId, targetActionVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.removeTargetAction( targetAction );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
			deleteRelationInGraph(userContext, targetAction);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTargetAction(PromoengineUserContext userContext, String promotionId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion copyTargetActionFrom(PromoengineUserContext userContext, String promotionId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTargetAction(userContext,promotionId, targetActionId, targetActionVersion,tokensExpr);
		
		TargetAction targetAction = createIndexedTargetAction(targetActionId, targetActionVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			targetAction.updateLastUpdateTime(userContext.now());
			
			promotion.copyTargetActionFrom( targetAction );		
			promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
			
			userContext.getManagerGroup().getTargetActionManager().onNewInstanceCreated(userContext, (TargetAction)promotion.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTargetAction(PromoengineUserContext userContext, String promotionId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		

		if(TargetAction.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetAction(parseString(newValueExpr));
		}
		
		if(TargetAction.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetAction(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	
	public  Promotion updateTargetAction(PromoengineUserContext userContext, String promotionId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTargetAction(userContext, promotionId, targetActionId, targetActionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTargetActionList().searchTargetActionListWith(TargetAction.ID_PROPERTY, "eq", targetActionId).done();
		
		
		
		Promotion promotion = loadPromotion(userContext, promotionId, loadTokens);
		
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//promotion.removeTargetAction( targetAction );	
			//make changes to AcceleraterAccount.
			TargetAction targetActionIndex = createIndexedTargetAction(targetActionId, targetActionVersion);
		
			TargetAction targetAction = promotion.findTheTargetAction(targetActionIndex);
			if(targetAction == null){
				throw new PromotionManagerException(targetAction+" is NOT FOUND" );
			}
			
			targetAction.changeProperty(property, newValueExpr);
			targetAction.updateLastUpdateTime(userContext.now());
			promotion = savePromotion(userContext, promotion, tokens().withTargetActionList().done());
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingPromotionOffer(PromoengineUserContext userContext, String promotionId, String name, String promotionLevelId, String parameter,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPromotion(promotionId);

		
		userContext.getChecker().checkNameOfPromotionOffer(name);
		
		userContext.getChecker().checkPromotionLevelIdOfPromotionOffer(promotionLevelId);
		
		userContext.getChecker().checkParameterOfPromotionOffer(parameter);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);

	
	}
	public  Promotion addPromotionOffer(PromoengineUserContext userContext, String promotionId, String name, String promotionLevelId, String parameter, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPromotionOffer(userContext,promotionId,name, promotionLevelId, parameter,tokensExpr);
		
		PromotionOffer promotionOffer = createPromotionOffer(userContext,name, promotionLevelId, parameter);
		
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.addPromotionOffer( promotionOffer );		
			promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
			
			userContext.getManagerGroup().getPromotionOfferManager().onNewInstanceCreated(userContext, promotionOffer);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPromotionOfferProperties(PromoengineUserContext userContext, String promotionId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfPromotionOffer(id);
		
		userContext.getChecker().checkNameOfPromotionOffer( name);
		userContext.getChecker().checkParameterOfPromotionOffer( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion updatePromotionOfferProperties(PromoengineUserContext userContext, String promotionId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPromotionOfferProperties(userContext,promotionId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPromotionOfferListList()
				.searchPromotionOfferListWith(PromotionOffer.ID_PROPERTY, "is", id).done();
		
		Promotion promotionToUpdate = loadPromotion(userContext, promotionId, options);
		
		if(promotionToUpdate.getPromotionOfferList().isEmpty()){
			throw new PromotionManagerException("PromotionOffer is NOT FOUND with id: '"+id+"'");
		}
		
		PromotionOffer item = promotionToUpdate.getPromotionOfferList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingPromotionOffer(userContext,promotionId,name, code, used,tokensExpr);
		Promotion promotion = savePromotion(userContext, promotionToUpdate, tokens().withPromotionOfferList().done());
		synchronized(promotion){ 
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PromotionOffer createPromotionOffer(PromoengineUserContext userContext, String name, String promotionLevelId, String parameter) throws Exception{

		PromotionOffer promotionOffer = new PromotionOffer();
		
		
		promotionOffer.setName(name);		
		PromotionLevel  promotionLevel = new PromotionLevel();
		promotionLevel.setId(promotionLevelId);		
		promotionOffer.setPromotionLevel(promotionLevel);		
		promotionOffer.setParameter(parameter);
	
		
		return promotionOffer;
	
		
	}
	
	protected PromotionOffer createIndexedPromotionOffer(String id, int version){

		PromotionOffer promotionOffer = new PromotionOffer();
		promotionOffer.setId(id);
		promotionOffer.setVersion(version);
		return promotionOffer;			
		
	}
	
	protected void checkParamsForRemovingPromotionOfferList(PromoengineUserContext userContext, String promotionId, 
			String promotionOfferIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		for(String promotionOfferId: promotionOfferIds){
			userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
		
	}
	public  Promotion removePromotionOfferList(PromoengineUserContext userContext, String promotionId, 
			String promotionOfferIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPromotionOfferList(userContext, promotionId,  promotionOfferIds, tokensExpr);
			
			
			Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
			synchronized(promotion){ 
				//Will be good when the promotion loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPromotionDAO().planToRemovePromotionOfferList(promotion, promotionOfferIds, allTokens());
				promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
				deleteRelationListInGraph(userContext, promotion.getPromotionOfferList());
				return present(userContext,promotion, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPromotionOffer(PromoengineUserContext userContext, String promotionId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion removePromotionOffer(PromoengineUserContext userContext, String promotionId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPromotionOffer(userContext,promotionId, promotionOfferId, promotionOfferVersion,tokensExpr);
		
		PromotionOffer promotionOffer = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotion.removePromotionOffer( promotionOffer );		
			promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
			deleteRelationInGraph(userContext, promotionOffer);
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPromotionOffer(PromoengineUserContext userContext, String promotionId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotion( promotionId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	public  Promotion copyPromotionOfferFrom(PromoengineUserContext userContext, String promotionId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPromotionOffer(userContext,promotionId, promotionOfferId, promotionOfferVersion,tokensExpr);
		
		PromotionOffer promotionOffer = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		Promotion promotion = loadPromotion(userContext, promotionId, allTokens());
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			promotion.copyPromotionOfferFrom( promotionOffer );		
			promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
			
			userContext.getManagerGroup().getPromotionOfferManager().onNewInstanceCreated(userContext, (PromotionOffer)promotion.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPromotionOffer(PromoengineUserContext userContext, String promotionId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		

		if(PromotionOffer.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotionOffer(parseString(newValueExpr));
		}
		
		if(PromotionOffer.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfPromotionOffer(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionManagerException.class);
	
	}
	
	public  Promotion updatePromotionOffer(PromoengineUserContext userContext, String promotionId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPromotionOffer(userContext, promotionId, promotionOfferId, promotionOfferVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPromotionOfferList().searchPromotionOfferListWith(PromotionOffer.ID_PROPERTY, "eq", promotionOfferId).done();
		
		
		
		Promotion promotion = loadPromotion(userContext, promotionId, loadTokens);
		
		synchronized(promotion){ 
			//Will be good when the promotion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//promotion.removePromotionOffer( promotionOffer );	
			//make changes to AcceleraterAccount.
			PromotionOffer promotionOfferIndex = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		
			PromotionOffer promotionOffer = promotion.findThePromotionOffer(promotionOfferIndex);
			if(promotionOffer == null){
				throw new PromotionManagerException(promotionOffer+" is NOT FOUND" );
			}
			
			promotionOffer.changeProperty(property, newValueExpr);
			
			promotion = savePromotion(userContext, promotion, tokens().withPromotionOfferList().done());
			return present(userContext,promotion, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(PromoengineUserContext userContext, Promotion newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


