
package com.doublechaintech.promoengine.actiontype;

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

import com.doublechaintech.promoengine.platform.CandidatePlatform;

import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;






public class ActionTypeManagerImpl extends CustomPromoengineCheckerManager implements ActionTypeManager {
	
	private static final String SERVICE_TYPE = "ActionType";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ActionTypeManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ActionTypeManagerException(message);

	}
	
	

 	protected ActionType saveActionType(PromoengineUserContext userContext, ActionType actionType, String [] tokensExpr) throws Exception{	
 		//return getActionTypeDAO().save(actionType, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveActionType(userContext, actionType, tokens);
 	}
 	
 	protected ActionType saveActionTypeDetail(PromoengineUserContext userContext, ActionType actionType) throws Exception{	

 		
 		return saveActionType(userContext, actionType, allTokens());
 	}
 	
 	public ActionType loadActionType(PromoengineUserContext userContext, String actionTypeId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().throwExceptionIfHasErrors( ActionTypeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ActionType actionType = loadActionType( userContext, actionTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,actionType, tokens);
 	}
 	
 	
 	 public ActionType searchActionType(PromoengineUserContext userContext, String actionTypeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().throwExceptionIfHasErrors( ActionTypeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ActionType actionType = loadActionType( userContext, actionTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,actionType, tokens);
 	}
 	
 	

 	protected ActionType present(PromoengineUserContext userContext, ActionType actionType, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,actionType,tokens);
		
		
		ActionType  actionTypeToPresent = userContext.getDAOGroup().getActionTypeDAO().present(actionType, tokens);
		
		List<BaseEntity> entityListToNaming = actionTypeToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getActionTypeDAO().alias(entityListToNaming);
		
		return  actionTypeToPresent;
		
		
	}
 
 	
 	
 	public ActionType loadActionTypeDetail(PromoengineUserContext userContext, String actionTypeId) throws Exception{	
 		ActionType actionType = loadActionType( userContext, actionTypeId, allTokens());
 		return present(userContext,actionType, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String actionTypeId) throws Exception{	
 		ActionType actionType = loadActionType( userContext, actionTypeId, viewTokens());
 		return present(userContext,actionType, allTokens());
		
 	}
 	protected ActionType saveActionType(PromoengineUserContext userContext, ActionType actionType, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getActionTypeDAO().save(actionType, tokens);
 	}
 	protected ActionType loadActionType(PromoengineUserContext userContext, String actionTypeId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().throwExceptionIfHasErrors( ActionTypeManagerException.class);

 
 		return userContext.getDAOGroup().getActionTypeDAO().load(actionTypeId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, ActionType actionType, Map<String, Object> tokens){
		super.addActions(userContext, actionType, tokens);
		
		addAction(userContext, actionType, tokens,"@create","createActionType","createActionType/","main","primary");
		addAction(userContext, actionType, tokens,"@update","updateActionType","updateActionType/"+actionType.getId()+"/","main","primary");
		addAction(userContext, actionType, tokens,"@copy","cloneActionType","cloneActionType/"+actionType.getId()+"/","main","primary");
		
		addAction(userContext, actionType, tokens,"action_type.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+actionType.getId()+"/","main","primary");
		addAction(userContext, actionType, tokens,"action_type.addTargetAction","addTargetAction","addTargetAction/"+actionType.getId()+"/","targetActionList","primary");
		addAction(userContext, actionType, tokens,"action_type.removeTargetAction","removeTargetAction","removeTargetAction/"+actionType.getId()+"/","targetActionList","primary");
		addAction(userContext, actionType, tokens,"action_type.updateTargetAction","updateTargetAction","updateTargetAction/"+actionType.getId()+"/","targetActionList","primary");
		addAction(userContext, actionType, tokens,"action_type.copyTargetActionFrom","copyTargetActionFrom","copyTargetActionFrom/"+actionType.getId()+"/","targetActionList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, ActionType actionType, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public ActionType createActionType(PromoengineUserContext userContext,String name, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfActionType(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);


		ActionType actionType=createNewActionType();	

		actionType.setName(name);
		actionType.setLastUpdateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		actionType.setPlatform(platform);
		
		

		actionType = saveActionType(userContext, actionType, emptyOptions());
		
		onNewInstanceCreated(userContext, actionType);
		return actionType;

		
	}
	protected ActionType createNewActionType() 
	{
		
		return new ActionType();		
	}
	
	protected void checkParamsForUpdatingActionType(PromoengineUserContext userContext,String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkVersionOfActionType( actionTypeVersion);
		

		if(ActionType.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfActionType(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
	
		
	}
	
	
	
	public ActionType clone(PromoengineUserContext userContext, String fromActionTypeId) throws Exception{
		
		return userContext.getDAOGroup().getActionTypeDAO().clone(fromActionTypeId, this.allTokens());
	}
	
	public ActionType internalSaveActionType(PromoengineUserContext userContext, ActionType actionType) throws Exception 
	{
		return internalSaveActionType(userContext, actionType, allTokens());

	}
	public ActionType internalSaveActionType(PromoengineUserContext userContext, ActionType actionType, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingActionType(userContext, actionTypeId, actionTypeVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(actionType){ 
			//will be good when the actionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ActionType.
			
			
			actionType = saveActionType(userContext, actionType, options);
			return actionType;
			
		}

	}
	
	public ActionType updateActionType(PromoengineUserContext userContext,String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingActionType(userContext, actionTypeId, actionTypeVersion, property, newValueExpr, tokensExpr);
		
		
		
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
		if(actionType.getVersion() != actionTypeVersion){
			String message = "The target version("+actionType.getVersion()+") is not equals to version("+actionTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(actionType){ 
			//will be good when the actionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ActionType.
			actionType.updateLastUpdateTime(userContext.now());
			actionType.changeProperty(property, newValueExpr);
			actionType = saveActionType(userContext, actionType, tokens().done());
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
			//return saveActionType(userContext, actionType, tokens().done());
		}

	}
	
	public ActionType updateActionTypeProperty(PromoengineUserContext userContext,String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingActionType(userContext, actionTypeId, actionTypeVersion, property, newValueExpr, tokensExpr);
		
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
		if(actionType.getVersion() != actionTypeVersion){
			String message = "The target version("+actionType.getVersion()+") is not equals to version("+actionTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(actionType){ 
			//will be good when the actionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ActionType.
			
			actionType.changeProperty(property, newValueExpr);
			actionType.updateLastUpdateTime(userContext.now());
			actionType = saveActionType(userContext, actionType, tokens().done());
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
			//return saveActionType(userContext, actionType, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ActionTypeTokens tokens(){
		return ActionTypeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ActionTypeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTargetActionListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ActionTypeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(PromoengineUserContext userContext, String actionTypeId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfActionType(actionTypeId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
 		
 	}
 	public ActionType transferToAnotherPlatform(PromoengineUserContext userContext, String actionTypeId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, actionTypeId,anotherPlatformId);
 
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());	
		synchronized(actionType){
			//will be good when the actionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			actionType.updatePlatform(platform);		
			actionType = saveActionType(userContext, actionType, emptyOptions());
			
			return present(userContext,actionType, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForActionType(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(PromoengineUserContext userContext, String actionTypeId, int actionTypeVersion) throws Exception {
		//deleteInternal(userContext, actionTypeId, actionTypeVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String actionTypeId, int actionTypeVersion) throws Exception{
			
		userContext.getDAOGroup().getActionTypeDAO().delete(actionTypeId, actionTypeVersion);
	}
	
	public ActionType forgetByAll(PromoengineUserContext userContext, String actionTypeId, int actionTypeVersion) throws Exception {
		return forgetByAllInternal(userContext, actionTypeId, actionTypeVersion);		
	}
	protected ActionType forgetByAllInternal(PromoengineUserContext userContext,
			String actionTypeId, int actionTypeVersion) throws Exception{
			
		return userContext.getDAOGroup().getActionTypeDAO().disconnectFromAll(actionTypeId, actionTypeVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ActionTypeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getActionTypeDAO().deleteAll();
	}


	//disconnect ActionType with promotion in TargetAction
	protected ActionType breakWithTargetActionByPromotion(PromoengineUserContext userContext, String actionTypeId, String promotionId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());

			synchronized(actionType){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getActionTypeDAO().planToRemoveTargetActionListWithPromotion(actionType, promotionId, this.emptyOptions());

				actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
				return actionType;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingTargetAction(PromoengineUserContext userContext, String actionTypeId, String name, String parameter, String promotionId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfActionType(actionTypeId);

		
		userContext.getChecker().checkNameOfTargetAction(name);
		
		userContext.getChecker().checkParameterOfTargetAction(parameter);
		
		userContext.getChecker().checkPromotionIdOfTargetAction(promotionId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);

	
	}
	public  ActionType addTargetAction(PromoengineUserContext userContext, String actionTypeId, String name, String parameter, String promotionId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTargetAction(userContext,actionTypeId,name, parameter, promotionId,tokensExpr);
		
		TargetAction targetAction = createTargetAction(userContext,name, parameter, promotionId);
		
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
		synchronized(actionType){ 
			//Will be good when the actionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			actionType.addTargetAction( targetAction );		
			actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
			
			userContext.getManagerGroup().getTargetActionManager().onNewInstanceCreated(userContext, targetAction);
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTargetActionProperties(PromoengineUserContext userContext, String actionTypeId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkIdOfTargetAction(id);
		
		userContext.getChecker().checkNameOfTargetAction( name);
		userContext.getChecker().checkParameterOfTargetAction( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
		
	}
	public  ActionType updateTargetActionProperties(PromoengineUserContext userContext, String actionTypeId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTargetActionProperties(userContext,actionTypeId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTargetActionListList()
				.searchTargetActionListWith(TargetAction.ID_PROPERTY, "is", id).done();
		
		ActionType actionTypeToUpdate = loadActionType(userContext, actionTypeId, options);
		
		if(actionTypeToUpdate.getTargetActionList().isEmpty()){
			throw new ActionTypeManagerException("TargetAction is NOT FOUND with id: '"+id+"'");
		}
		
		TargetAction item = actionTypeToUpdate.getTargetActionList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingTargetAction(userContext,actionTypeId,name, code, used,tokensExpr);
		ActionType actionType = saveActionType(userContext, actionTypeToUpdate, tokens().withTargetActionList().done());
		synchronized(actionType){ 
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected TargetAction createTargetAction(PromoengineUserContext userContext, String name, String parameter, String promotionId) throws Exception{

		TargetAction targetAction = new TargetAction();
		
		
		targetAction.setName(name);		
		targetAction.setParameter(parameter);		
		targetAction.setLastUpdateTime(userContext.now());		
		Promotion  promotion = new Promotion();
		promotion.setId(promotionId);		
		targetAction.setPromotion(promotion);
	
		
		return targetAction;
	
		
	}
	
	protected TargetAction createIndexedTargetAction(String id, int version){

		TargetAction targetAction = new TargetAction();
		targetAction.setId(id);
		targetAction.setVersion(version);
		return targetAction;			
		
	}
	
	protected void checkParamsForRemovingTargetActionList(PromoengineUserContext userContext, String actionTypeId, 
			String targetActionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		for(String targetActionId: targetActionIds){
			userContext.getChecker().checkIdOfTargetAction(targetActionId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
		
	}
	public  ActionType removeTargetActionList(PromoengineUserContext userContext, String actionTypeId, 
			String targetActionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTargetActionList(userContext, actionTypeId,  targetActionIds, tokensExpr);
			
			
			ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
			synchronized(actionType){ 
				//Will be good when the actionType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getActionTypeDAO().planToRemoveTargetActionList(actionType, targetActionIds, allTokens());
				actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
				deleteRelationListInGraph(userContext, actionType.getTargetActionList());
				return present(userContext,actionType, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTargetAction(PromoengineUserContext userContext, String actionTypeId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfActionType( actionTypeId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
	
	}
	public  ActionType removeTargetAction(PromoengineUserContext userContext, String actionTypeId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTargetAction(userContext,actionTypeId, targetActionId, targetActionVersion,tokensExpr);
		
		TargetAction targetAction = createIndexedTargetAction(targetActionId, targetActionVersion);
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
		synchronized(actionType){ 
			//Will be good when the actionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			actionType.removeTargetAction( targetAction );		
			actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
			deleteRelationInGraph(userContext, targetAction);
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTargetAction(PromoengineUserContext userContext, String actionTypeId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfActionType( actionTypeId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
	
	}
	public  ActionType copyTargetActionFrom(PromoengineUserContext userContext, String actionTypeId, 
		String targetActionId, int targetActionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTargetAction(userContext,actionTypeId, targetActionId, targetActionVersion,tokensExpr);
		
		TargetAction targetAction = createIndexedTargetAction(targetActionId, targetActionVersion);
		ActionType actionType = loadActionType(userContext, actionTypeId, allTokens());
		synchronized(actionType){ 
			//Will be good when the actionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			targetAction.updateLastUpdateTime(userContext.now());
			
			actionType.copyTargetActionFrom( targetAction );		
			actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
			
			userContext.getManagerGroup().getTargetActionManager().onNewInstanceCreated(userContext, (TargetAction)actionType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTargetAction(PromoengineUserContext userContext, String actionTypeId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkIdOfTargetAction(targetActionId);
		userContext.getChecker().checkVersionOfTargetAction(targetActionVersion);
		

		if(TargetAction.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTargetAction(parseString(newValueExpr));
		}
		
		if(TargetAction.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfTargetAction(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ActionTypeManagerException.class);
	
	}
	
	public  ActionType updateTargetAction(PromoengineUserContext userContext, String actionTypeId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTargetAction(userContext, actionTypeId, targetActionId, targetActionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTargetActionList().searchTargetActionListWith(TargetAction.ID_PROPERTY, "eq", targetActionId).done();
		
		
		
		ActionType actionType = loadActionType(userContext, actionTypeId, loadTokens);
		
		synchronized(actionType){ 
			//Will be good when the actionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//actionType.removeTargetAction( targetAction );	
			//make changes to AcceleraterAccount.
			TargetAction targetActionIndex = createIndexedTargetAction(targetActionId, targetActionVersion);
		
			TargetAction targetAction = actionType.findTheTargetAction(targetActionIndex);
			if(targetAction == null){
				throw new ActionTypeManagerException(targetAction+" is NOT FOUND" );
			}
			
			targetAction.changeProperty(property, newValueExpr);
			targetAction.updateLastUpdateTime(userContext.now());
			actionType = saveActionType(userContext, actionType, tokens().withTargetActionList().done());
			return present(userContext,actionType, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(PromoengineUserContext userContext, ActionType newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


