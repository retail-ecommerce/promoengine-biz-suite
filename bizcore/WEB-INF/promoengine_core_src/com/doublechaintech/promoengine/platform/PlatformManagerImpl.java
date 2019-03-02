
package com.doublechaintech.promoengine.platform;

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
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;


import com.doublechaintech.promoengine.platform.Platform;






public class PlatformManagerImpl extends CustomPromoengineCheckerManager implements PlatformManager {
	
	private static final String SERVICE_TYPE = "Platform";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}
	
	

 	protected Platform savePlatform(PromoengineUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(PromoengineUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(PromoengineUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(PromoengineUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(PromoengineUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = userContext.getDAOGroup().getPlatformDAO().present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPlatformDAO().alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(PromoengineUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(PromoengineUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPlatformDAO().save(platform, tokens);
 	}
 	protected Platform loadPlatform(PromoengineUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return userContext.getDAOGroup().getPlatformDAO().load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addApplyCondition","addApplyCondition","addApplyCondition/"+platform.getId()+"/","applyConditionList","primary");
		addAction(userContext, platform, tokens,"platform.removeApplyCondition","removeApplyCondition","removeApplyCondition/"+platform.getId()+"/","applyConditionList","primary");
		addAction(userContext, platform, tokens,"platform.updateApplyCondition","updateApplyCondition","updateApplyCondition/"+platform.getId()+"/","applyConditionList","primary");
		addAction(userContext, platform, tokens,"platform.copyApplyConditionFrom","copyApplyConditionFrom","copyApplyConditionFrom/"+platform.getId()+"/","applyConditionList","primary");
		addAction(userContext, platform, tokens,"platform.addActionType","addActionType","addActionType/"+platform.getId()+"/","actionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.removeActionType","removeActionType","removeActionType/"+platform.getId()+"/","actionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.updateActionType","updateActionType","updateActionType/"+platform.getId()+"/","actionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.copyActionTypeFrom","copyActionTypeFrom","copyActionTypeFrom/"+platform.getId()+"/","actionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.addPromotionLevel","addPromotionLevel","addPromotionLevel/"+platform.getId()+"/","promotionLevelList","primary");
		addAction(userContext, platform, tokens,"platform.removePromotionLevel","removePromotionLevel","removePromotionLevel/"+platform.getId()+"/","promotionLevelList","primary");
		addAction(userContext, platform, tokens,"platform.updatePromotionLevel","updatePromotionLevel","updatePromotionLevel/"+platform.getId()+"/","promotionLevelList","primary");
		addAction(userContext, platform, tokens,"platform.copyPromotionLevelFrom","copyPromotionLevelFrom","copyPromotionLevelFrom/"+platform.getId()+"/","promotionLevelList","primary");
		addAction(userContext, platform, tokens,"platform.addPromotion","addPromotion","addPromotion/"+platform.getId()+"/","promotionList","primary");
		addAction(userContext, platform, tokens,"platform.removePromotion","removePromotion","removePromotion/"+platform.getId()+"/","promotionList","primary");
		addAction(userContext, platform, tokens,"platform.updatePromotion","updatePromotion","updatePromotion/"+platform.getId()+"/","promotionList","primary");
		addAction(userContext, platform, tokens,"platform.copyPromotionFrom","copyPromotionFrom","copyPromotionFrom/"+platform.getId()+"/","promotionList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Platform createPlatform(PromoengineUserContext userContext,String name, String introduction, String currentVersion) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPlatform(name);
		userContext.getChecker().checkIntroductionOfPlatform(introduction);
		userContext.getChecker().checkCurrentVersionOfPlatform(currentVersion);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setIntroduction(introduction);
		platform.setCurrentVersion(currentVersion);

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;

		
	}
	protected Platform createNewPlatform() 
	{
		
		return new Platform();		
	}
	
	protected void checkParamsForUpdatingPlatform(PromoengineUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPlatform(parseString(newValueExpr));
		}
		if(Platform.INTRODUCTION_PROPERTY.equals(property)){
			userContext.getChecker().checkIntroductionOfPlatform(parseString(newValueExpr));
		}
		if(Platform.CURRENT_VERSION_PROPERTY.equals(property)){
			userContext.getChecker().checkCurrentVersionOfPlatform(parseString(newValueExpr));
		}
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
		
	}
	
	
	
	public Platform clone(PromoengineUserContext userContext, String fromPlatformId) throws Exception{
		
		return userContext.getDAOGroup().getPlatformDAO().clone(fromPlatformId, this.allTokens());
	}
	
	public Platform internalSavePlatform(PromoengineUserContext userContext, Platform platform) throws Exception 
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(PromoengineUserContext userContext, Platform platform, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			
			platform = savePlatform(userContext, platform, options);
			return platform;
			
		}

	}
	
	public Platform updatePlatform(PromoengineUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	
	public Platform updatePlatformProperty(PromoengineUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortApplyConditionListWith("id","desc")
		.sortActionTypeListWith("id","desc")
		.sortPromotionLevelListWith("id","desc")
		.sortPromotionListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		userContext.getDAOGroup().getPlatformDAO().delete(platformId, platformVersion);
	}
	
	public Platform forgetByAll(PromoengineUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);		
	}
	protected Platform forgetByAllInternal(PromoengineUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		return userContext.getDAOGroup().getPlatformDAO().disconnectFromAll(platformId, platformVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPlatformDAO().deleteAll();
	}


	
	
	
	
	

	protected void checkParamsForAddingApplyCondition(PromoengineUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfApplyCondition(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addApplyCondition(PromoengineUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingApplyCondition(userContext,platformId,name,tokensExpr);
		
		ApplyCondition applyCondition = createApplyCondition(userContext,name);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addApplyCondition( applyCondition );		
			platform = savePlatform(userContext, platform, tokens().withApplyConditionList().done());
			
			userContext.getManagerGroup().getApplyConditionManager().onNewInstanceCreated(userContext, applyCondition);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingApplyConditionProperties(PromoengineUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfApplyCondition(id);
		
		userContext.getChecker().checkNameOfApplyCondition( name);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateApplyConditionProperties(PromoengineUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingApplyConditionProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withApplyConditionListList()
				.searchApplyConditionListWith(ApplyCondition.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getApplyConditionList().isEmpty()){
			throw new PlatformManagerException("ApplyCondition is NOT FOUND with id: '"+id+"'");
		}
		
		ApplyCondition item = platformToUpdate.getApplyConditionList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingApplyCondition(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withApplyConditionList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ApplyCondition createApplyCondition(PromoengineUserContext userContext, String name) throws Exception{

		ApplyCondition applyCondition = new ApplyCondition();
		
		
		applyCondition.setName(name);		
		applyCondition.setLastUpdateTime(userContext.now());
	
		
		return applyCondition;
	
		
	}
	
	protected ApplyCondition createIndexedApplyCondition(String id, int version){

		ApplyCondition applyCondition = new ApplyCondition();
		applyCondition.setId(id);
		applyCondition.setVersion(version);
		return applyCondition;			
		
	}
	
	protected void checkParamsForRemovingApplyConditionList(PromoengineUserContext userContext, String platformId, 
			String applyConditionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String applyConditionId: applyConditionIds){
			userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeApplyConditionList(PromoengineUserContext userContext, String platformId, 
			String applyConditionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingApplyConditionList(userContext, platformId,  applyConditionIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveApplyConditionList(platform, applyConditionIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withApplyConditionList().done());
				deleteRelationListInGraph(userContext, platform.getApplyConditionList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingApplyCondition(PromoengineUserContext userContext, String platformId, 
		String applyConditionId, int applyConditionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkVersionOfApplyCondition(applyConditionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeApplyCondition(PromoengineUserContext userContext, String platformId, 
		String applyConditionId, int applyConditionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingApplyCondition(userContext,platformId, applyConditionId, applyConditionVersion,tokensExpr);
		
		ApplyCondition applyCondition = createIndexedApplyCondition(applyConditionId, applyConditionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeApplyCondition( applyCondition );		
			platform = savePlatform(userContext, platform, tokens().withApplyConditionList().done());
			deleteRelationInGraph(userContext, applyCondition);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingApplyCondition(PromoengineUserContext userContext, String platformId, 
		String applyConditionId, int applyConditionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkVersionOfApplyCondition(applyConditionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyApplyConditionFrom(PromoengineUserContext userContext, String platformId, 
		String applyConditionId, int applyConditionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingApplyCondition(userContext,platformId, applyConditionId, applyConditionVersion,tokensExpr);
		
		ApplyCondition applyCondition = createIndexedApplyCondition(applyConditionId, applyConditionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			applyCondition.updateLastUpdateTime(userContext.now());
			
			platform.copyApplyConditionFrom( applyCondition );		
			platform = savePlatform(userContext, platform, tokens().withApplyConditionList().done());
			
			userContext.getManagerGroup().getApplyConditionManager().onNewInstanceCreated(userContext, (ApplyCondition)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingApplyCondition(PromoengineUserContext userContext, String platformId, String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfApplyCondition(applyConditionId);
		userContext.getChecker().checkVersionOfApplyCondition(applyConditionVersion);
		

		if(ApplyCondition.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfApplyCondition(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateApplyCondition(PromoengineUserContext userContext, String platformId, String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingApplyCondition(userContext, platformId, applyConditionId, applyConditionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withApplyConditionList().searchApplyConditionListWith(ApplyCondition.ID_PROPERTY, "eq", applyConditionId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeApplyCondition( applyCondition );	
			//make changes to AcceleraterAccount.
			ApplyCondition applyConditionIndex = createIndexedApplyCondition(applyConditionId, applyConditionVersion);
		
			ApplyCondition applyCondition = platform.findTheApplyCondition(applyConditionIndex);
			if(applyCondition == null){
				throw new PlatformManagerException(applyCondition+" is NOT FOUND" );
			}
			
			applyCondition.changeProperty(property, newValueExpr);
			applyCondition.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withApplyConditionList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingActionType(PromoengineUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfActionType(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addActionType(PromoengineUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingActionType(userContext,platformId,name,tokensExpr);
		
		ActionType actionType = createActionType(userContext,name);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addActionType( actionType );		
			platform = savePlatform(userContext, platform, tokens().withActionTypeList().done());
			
			userContext.getManagerGroup().getActionTypeManager().onNewInstanceCreated(userContext, actionType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingActionTypeProperties(PromoengineUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfActionType(id);
		
		userContext.getChecker().checkNameOfActionType( name);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateActionTypeProperties(PromoengineUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingActionTypeProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withActionTypeListList()
				.searchActionTypeListWith(ActionType.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getActionTypeList().isEmpty()){
			throw new PlatformManagerException("ActionType is NOT FOUND with id: '"+id+"'");
		}
		
		ActionType item = platformToUpdate.getActionTypeList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingActionType(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withActionTypeList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ActionType createActionType(PromoengineUserContext userContext, String name) throws Exception{

		ActionType actionType = new ActionType();
		
		
		actionType.setName(name);		
		actionType.setLastUpdateTime(userContext.now());
	
		
		return actionType;
	
		
	}
	
	protected ActionType createIndexedActionType(String id, int version){

		ActionType actionType = new ActionType();
		actionType.setId(id);
		actionType.setVersion(version);
		return actionType;			
		
	}
	
	protected void checkParamsForRemovingActionTypeList(PromoengineUserContext userContext, String platformId, 
			String actionTypeIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String actionTypeId: actionTypeIds){
			userContext.getChecker().checkIdOfActionType(actionTypeId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeActionTypeList(PromoengineUserContext userContext, String platformId, 
			String actionTypeIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingActionTypeList(userContext, platformId,  actionTypeIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveActionTypeList(platform, actionTypeIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withActionTypeList().done());
				deleteRelationListInGraph(userContext, platform.getActionTypeList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingActionType(PromoengineUserContext userContext, String platformId, 
		String actionTypeId, int actionTypeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkVersionOfActionType(actionTypeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeActionType(PromoengineUserContext userContext, String platformId, 
		String actionTypeId, int actionTypeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingActionType(userContext,platformId, actionTypeId, actionTypeVersion,tokensExpr);
		
		ActionType actionType = createIndexedActionType(actionTypeId, actionTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeActionType( actionType );		
			platform = savePlatform(userContext, platform, tokens().withActionTypeList().done());
			deleteRelationInGraph(userContext, actionType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingActionType(PromoengineUserContext userContext, String platformId, 
		String actionTypeId, int actionTypeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkVersionOfActionType(actionTypeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyActionTypeFrom(PromoengineUserContext userContext, String platformId, 
		String actionTypeId, int actionTypeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingActionType(userContext,platformId, actionTypeId, actionTypeVersion,tokensExpr);
		
		ActionType actionType = createIndexedActionType(actionTypeId, actionTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			actionType.updateLastUpdateTime(userContext.now());
			
			platform.copyActionTypeFrom( actionType );		
			platform = savePlatform(userContext, platform, tokens().withActionTypeList().done());
			
			userContext.getManagerGroup().getActionTypeManager().onNewInstanceCreated(userContext, (ActionType)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingActionType(PromoengineUserContext userContext, String platformId, String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfActionType(actionTypeId);
		userContext.getChecker().checkVersionOfActionType(actionTypeVersion);
		

		if(ActionType.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfActionType(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateActionType(PromoengineUserContext userContext, String platformId, String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingActionType(userContext, platformId, actionTypeId, actionTypeVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withActionTypeList().searchActionTypeListWith(ActionType.ID_PROPERTY, "eq", actionTypeId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeActionType( actionType );	
			//make changes to AcceleraterAccount.
			ActionType actionTypeIndex = createIndexedActionType(actionTypeId, actionTypeVersion);
		
			ActionType actionType = platform.findTheActionType(actionTypeIndex);
			if(actionType == null){
				throw new PlatformManagerException(actionType+" is NOT FOUND" );
			}
			
			actionType.changeProperty(property, newValueExpr);
			actionType.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withActionTypeList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingPromotionLevel(PromoengineUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfPromotionLevel(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addPromotionLevel(PromoengineUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPromotionLevel(userContext,platformId,name,tokensExpr);
		
		PromotionLevel promotionLevel = createPromotionLevel(userContext,name);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addPromotionLevel( promotionLevel );		
			platform = savePlatform(userContext, platform, tokens().withPromotionLevelList().done());
			
			userContext.getManagerGroup().getPromotionLevelManager().onNewInstanceCreated(userContext, promotionLevel);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPromotionLevelProperties(PromoengineUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPromotionLevel(id);
		
		userContext.getChecker().checkNameOfPromotionLevel( name);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updatePromotionLevelProperties(PromoengineUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPromotionLevelProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPromotionLevelListList()
				.searchPromotionLevelListWith(PromotionLevel.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getPromotionLevelList().isEmpty()){
			throw new PlatformManagerException("PromotionLevel is NOT FOUND with id: '"+id+"'");
		}
		
		PromotionLevel item = platformToUpdate.getPromotionLevelList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingPromotionLevel(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withPromotionLevelList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PromotionLevel createPromotionLevel(PromoengineUserContext userContext, String name) throws Exception{

		PromotionLevel promotionLevel = new PromotionLevel();
		
		
		promotionLevel.setName(name);		
		promotionLevel.setLastUpdateTime(userContext.now());
	
		
		return promotionLevel;
	
		
	}
	
	protected PromotionLevel createIndexedPromotionLevel(String id, int version){

		PromotionLevel promotionLevel = new PromotionLevel();
		promotionLevel.setId(id);
		promotionLevel.setVersion(version);
		return promotionLevel;			
		
	}
	
	protected void checkParamsForRemovingPromotionLevelList(PromoengineUserContext userContext, String platformId, 
			String promotionLevelIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String promotionLevelId: promotionLevelIds){
			userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removePromotionLevelList(PromoengineUserContext userContext, String platformId, 
			String promotionLevelIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPromotionLevelList(userContext, platformId,  promotionLevelIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemovePromotionLevelList(platform, promotionLevelIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withPromotionLevelList().done());
				deleteRelationListInGraph(userContext, platform.getPromotionLevelList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPromotionLevel(PromoengineUserContext userContext, String platformId, 
		String promotionLevelId, int promotionLevelVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkVersionOfPromotionLevel(promotionLevelVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removePromotionLevel(PromoengineUserContext userContext, String platformId, 
		String promotionLevelId, int promotionLevelVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPromotionLevel(userContext,platformId, promotionLevelId, promotionLevelVersion,tokensExpr);
		
		PromotionLevel promotionLevel = createIndexedPromotionLevel(promotionLevelId, promotionLevelVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removePromotionLevel( promotionLevel );		
			platform = savePlatform(userContext, platform, tokens().withPromotionLevelList().done());
			deleteRelationInGraph(userContext, promotionLevel);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPromotionLevel(PromoengineUserContext userContext, String platformId, 
		String promotionLevelId, int promotionLevelVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkVersionOfPromotionLevel(promotionLevelVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyPromotionLevelFrom(PromoengineUserContext userContext, String platformId, 
		String promotionLevelId, int promotionLevelVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPromotionLevel(userContext,platformId, promotionLevelId, promotionLevelVersion,tokensExpr);
		
		PromotionLevel promotionLevel = createIndexedPromotionLevel(promotionLevelId, promotionLevelVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			promotionLevel.updateLastUpdateTime(userContext.now());
			
			platform.copyPromotionLevelFrom( promotionLevel );		
			platform = savePlatform(userContext, platform, tokens().withPromotionLevelList().done());
			
			userContext.getManagerGroup().getPromotionLevelManager().onNewInstanceCreated(userContext, (PromotionLevel)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPromotionLevel(PromoengineUserContext userContext, String platformId, String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkVersionOfPromotionLevel(promotionLevelVersion);
		

		if(PromotionLevel.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotionLevel(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updatePromotionLevel(PromoengineUserContext userContext, String platformId, String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPromotionLevel(userContext, platformId, promotionLevelId, promotionLevelVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPromotionLevelList().searchPromotionLevelListWith(PromotionLevel.ID_PROPERTY, "eq", promotionLevelId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removePromotionLevel( promotionLevel );	
			//make changes to AcceleraterAccount.
			PromotionLevel promotionLevelIndex = createIndexedPromotionLevel(promotionLevelId, promotionLevelVersion);
		
			PromotionLevel promotionLevel = platform.findThePromotionLevel(promotionLevelIndex);
			if(promotionLevel == null){
				throw new PlatformManagerException(promotionLevel+" is NOT FOUND" );
			}
			
			promotionLevel.changeProperty(property, newValueExpr);
			promotionLevel.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withPromotionLevelList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingPromotion(PromoengineUserContext userContext, String platformId, String name, DateTime validAfter, DateTime expireTime,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfPromotion(name);
		
		userContext.getChecker().checkValidAfterOfPromotion(validAfter);
		
		userContext.getChecker().checkExpireTimeOfPromotion(expireTime);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addPromotion(PromoengineUserContext userContext, String platformId, String name, DateTime validAfter, DateTime expireTime, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPromotion(userContext,platformId,name, validAfter, expireTime,tokensExpr);
		
		Promotion promotion = createPromotion(userContext,name, validAfter, expireTime);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addPromotion( promotion );		
			platform = savePlatform(userContext, platform, tokens().withPromotionList().done());
			
			userContext.getManagerGroup().getPromotionManager().onNewInstanceCreated(userContext, promotion);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPromotionProperties(PromoengineUserContext userContext, String platformId,String id,String name,DateTime validAfter,DateTime expireTime,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPromotion(id);
		
		userContext.getChecker().checkNameOfPromotion( name);
		userContext.getChecker().checkValidAfterOfPromotion( validAfter);
		userContext.getChecker().checkExpireTimeOfPromotion( expireTime);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updatePromotionProperties(PromoengineUserContext userContext, String platformId, String id,String name,DateTime validAfter,DateTime expireTime, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPromotionProperties(userContext,platformId,id,name,validAfter,expireTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPromotionListList()
				.searchPromotionListWith(Promotion.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getPromotionList().isEmpty()){
			throw new PlatformManagerException("Promotion is NOT FOUND with id: '"+id+"'");
		}
		
		Promotion item = platformToUpdate.getPromotionList().first();
		
		item.updateName( name );
		item.updateValidAfter( validAfter );
		item.updateExpireTime( expireTime );

		
		//checkParamsForAddingPromotion(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withPromotionList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Promotion createPromotion(PromoengineUserContext userContext, String name, DateTime validAfter, DateTime expireTime) throws Exception{

		Promotion promotion = new Promotion();
		
		
		promotion.setName(name);		
		promotion.setValidAfter(validAfter);		
		promotion.setExpireTime(expireTime);		
		promotion.setLastUpdateTime(userContext.now());
	
		
		return promotion;
	
		
	}
	
	protected Promotion createIndexedPromotion(String id, int version){

		Promotion promotion = new Promotion();
		promotion.setId(id);
		promotion.setVersion(version);
		return promotion;			
		
	}
	
	protected void checkParamsForRemovingPromotionList(PromoengineUserContext userContext, String platformId, 
			String promotionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String promotionId: promotionIds){
			userContext.getChecker().checkIdOfPromotion(promotionId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removePromotionList(PromoengineUserContext userContext, String platformId, 
			String promotionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPromotionList(userContext, platformId,  promotionIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemovePromotionList(platform, promotionIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withPromotionList().done());
				deleteRelationListInGraph(userContext, platform.getPromotionList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPromotion(PromoengineUserContext userContext, String platformId, 
		String promotionId, int promotionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkVersionOfPromotion(promotionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removePromotion(PromoengineUserContext userContext, String platformId, 
		String promotionId, int promotionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPromotion(userContext,platformId, promotionId, promotionVersion,tokensExpr);
		
		Promotion promotion = createIndexedPromotion(promotionId, promotionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removePromotion( promotion );		
			platform = savePlatform(userContext, platform, tokens().withPromotionList().done());
			deleteRelationInGraph(userContext, promotion);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPromotion(PromoengineUserContext userContext, String platformId, 
		String promotionId, int promotionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkVersionOfPromotion(promotionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyPromotionFrom(PromoengineUserContext userContext, String platformId, 
		String promotionId, int promotionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPromotion(userContext,platformId, promotionId, promotionVersion,tokensExpr);
		
		Promotion promotion = createIndexedPromotion(promotionId, promotionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			promotion.updateLastUpdateTime(userContext.now());
			
			platform.copyPromotionFrom( promotion );		
			platform = savePlatform(userContext, platform, tokens().withPromotionList().done());
			
			userContext.getManagerGroup().getPromotionManager().onNewInstanceCreated(userContext, (Promotion)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPromotion(PromoengineUserContext userContext, String platformId, String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPromotion(promotionId);
		userContext.getChecker().checkVersionOfPromotion(promotionVersion);
		

		if(Promotion.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotion(parseString(newValueExpr));
		}
		
		if(Promotion.VALID_AFTER_PROPERTY.equals(property)){
			userContext.getChecker().checkValidAfterOfPromotion(parseTimestamp(newValueExpr));
		}
		
		if(Promotion.EXPIRE_TIME_PROPERTY.equals(property)){
			userContext.getChecker().checkExpireTimeOfPromotion(parseTimestamp(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updatePromotion(PromoengineUserContext userContext, String platformId, String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPromotion(userContext, platformId, promotionId, promotionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPromotionList().searchPromotionListWith(Promotion.ID_PROPERTY, "eq", promotionId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removePromotion( promotion );	
			//make changes to AcceleraterAccount.
			Promotion promotionIndex = createIndexedPromotion(promotionId, promotionVersion);
		
			Promotion promotion = platform.findThePromotion(promotionIndex);
			if(promotion == null){
				throw new PlatformManagerException(promotion+" is NOT FOUND" );
			}
			
			promotion.changeProperty(property, newValueExpr);
			promotion.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withPromotionList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(PromoengineUserContext userContext, Platform newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


