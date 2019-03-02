
package com.doublechaintech.promoengine.promotionlevel;

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
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

import com.doublechaintech.promoengine.platform.CandidatePlatform;

import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.promotion.Promotion;






public class PromotionLevelManagerImpl extends CustomPromoengineCheckerManager implements PromotionLevelManager {
	
	private static final String SERVICE_TYPE = "PromotionLevel";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PromotionLevelManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PromotionLevelManagerException(message);

	}
	
	

 	protected PromotionLevel savePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel, String [] tokensExpr) throws Exception{	
 		//return getPromotionLevelDAO().save(promotionLevel, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePromotionLevel(userContext, promotionLevel, tokens);
 	}
 	
 	protected PromotionLevel savePromotionLevelDetail(PromoengineUserContext userContext, PromotionLevel promotionLevel) throws Exception{	

 		
 		return savePromotionLevel(userContext, promotionLevel, allTokens());
 	}
 	
 	public PromotionLevel loadPromotionLevel(PromoengineUserContext userContext, String promotionLevelId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionLevelManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		PromotionLevel promotionLevel = loadPromotionLevel( userContext, promotionLevelId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotionLevel, tokens);
 	}
 	
 	
 	 public PromotionLevel searchPromotionLevel(PromoengineUserContext userContext, String promotionLevelId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionLevelManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		PromotionLevel promotionLevel = loadPromotionLevel( userContext, promotionLevelId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,promotionLevel, tokens);
 	}
 	
 	

 	protected PromotionLevel present(PromoengineUserContext userContext, PromotionLevel promotionLevel, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,promotionLevel,tokens);
		
		
		PromotionLevel  promotionLevelToPresent = userContext.getDAOGroup().getPromotionLevelDAO().present(promotionLevel, tokens);
		
		List<BaseEntity> entityListToNaming = promotionLevelToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPromotionLevelDAO().alias(entityListToNaming);
		
		return  promotionLevelToPresent;
		
		
	}
 
 	
 	
 	public PromotionLevel loadPromotionLevelDetail(PromoengineUserContext userContext, String promotionLevelId) throws Exception{	
 		PromotionLevel promotionLevel = loadPromotionLevel( userContext, promotionLevelId, allTokens());
 		return present(userContext,promotionLevel, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String promotionLevelId) throws Exception{	
 		PromotionLevel promotionLevel = loadPromotionLevel( userContext, promotionLevelId, viewTokens());
 		return present(userContext,promotionLevel, allTokens());
		
 	}
 	protected PromotionLevel savePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPromotionLevelDAO().save(promotionLevel, tokens);
 	}
 	protected PromotionLevel loadPromotionLevel(PromoengineUserContext userContext, String promotionLevelId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().throwExceptionIfHasErrors( PromotionLevelManagerException.class);

 
 		return userContext.getDAOGroup().getPromotionLevelDAO().load(promotionLevelId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, PromotionLevel promotionLevel, Map<String, Object> tokens){
		super.addActions(userContext, promotionLevel, tokens);
		
		addAction(userContext, promotionLevel, tokens,"@create","createPromotionLevel","createPromotionLevel/","main","primary");
		addAction(userContext, promotionLevel, tokens,"@update","updatePromotionLevel","updatePromotionLevel/"+promotionLevel.getId()+"/","main","primary");
		addAction(userContext, promotionLevel, tokens,"@copy","clonePromotionLevel","clonePromotionLevel/"+promotionLevel.getId()+"/","main","primary");
		
		addAction(userContext, promotionLevel, tokens,"promotion_level.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+promotionLevel.getId()+"/","main","primary");
		addAction(userContext, promotionLevel, tokens,"promotion_level.addPromotionOffer","addPromotionOffer","addPromotionOffer/"+promotionLevel.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotionLevel, tokens,"promotion_level.removePromotionOffer","removePromotionOffer","removePromotionOffer/"+promotionLevel.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotionLevel, tokens,"promotion_level.updatePromotionOffer","updatePromotionOffer","updatePromotionOffer/"+promotionLevel.getId()+"/","promotionOfferList","primary");
		addAction(userContext, promotionLevel, tokens,"promotion_level.copyPromotionOfferFrom","copyPromotionOfferFrom","copyPromotionOfferFrom/"+promotionLevel.getId()+"/","promotionOfferList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, PromotionLevel promotionLevel, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public PromotionLevel createPromotionLevel(PromoengineUserContext userContext,String name, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPromotionLevel(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);


		PromotionLevel promotionLevel=createNewPromotionLevel();	

		promotionLevel.setName(name);
		promotionLevel.setLastUpdateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		promotionLevel.setPlatform(platform);
		
		

		promotionLevel = savePromotionLevel(userContext, promotionLevel, emptyOptions());
		
		onNewInstanceCreated(userContext, promotionLevel);
		return promotionLevel;

		
	}
	protected PromotionLevel createNewPromotionLevel() 
	{
		
		return new PromotionLevel();		
	}
	
	protected void checkParamsForUpdatingPromotionLevel(PromoengineUserContext userContext,String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkVersionOfPromotionLevel( promotionLevelVersion);
		

		if(PromotionLevel.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotionLevel(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
	
		
	}
	
	
	
	public PromotionLevel clone(PromoengineUserContext userContext, String fromPromotionLevelId) throws Exception{
		
		return userContext.getDAOGroup().getPromotionLevelDAO().clone(fromPromotionLevelId, this.allTokens());
	}
	
	public PromotionLevel internalSavePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel) throws Exception 
	{
		return internalSavePromotionLevel(userContext, promotionLevel, allTokens());

	}
	public PromotionLevel internalSavePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPromotionLevel(userContext, promotionLevelId, promotionLevelVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(promotionLevel){ 
			//will be good when the promotionLevel loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionLevel.
			
			
			promotionLevel = savePromotionLevel(userContext, promotionLevel, options);
			return promotionLevel;
			
		}

	}
	
	public PromotionLevel updatePromotionLevel(PromoengineUserContext userContext,String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotionLevel(userContext, promotionLevelId, promotionLevelVersion, property, newValueExpr, tokensExpr);
		
		
		
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
		if(promotionLevel.getVersion() != promotionLevelVersion){
			String message = "The target version("+promotionLevel.getVersion()+") is not equals to version("+promotionLevelVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotionLevel){ 
			//will be good when the promotionLevel loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionLevel.
			promotionLevel.updateLastUpdateTime(userContext.now());
			promotionLevel.changeProperty(property, newValueExpr);
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().done());
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
			//return savePromotionLevel(userContext, promotionLevel, tokens().done());
		}

	}
	
	public PromotionLevel updatePromotionLevelProperty(PromoengineUserContext userContext,String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPromotionLevel(userContext, promotionLevelId, promotionLevelVersion, property, newValueExpr, tokensExpr);
		
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
		if(promotionLevel.getVersion() != promotionLevelVersion){
			String message = "The target version("+promotionLevel.getVersion()+") is not equals to version("+promotionLevelVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(promotionLevel){ 
			//will be good when the promotionLevel loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PromotionLevel.
			
			promotionLevel.changeProperty(property, newValueExpr);
			promotionLevel.updateLastUpdateTime(userContext.now());
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().done());
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
			//return savePromotionLevel(userContext, promotionLevel, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PromotionLevelTokens tokens(){
		return PromotionLevelTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PromotionLevelTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortPromotionOfferListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PromotionLevelTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(PromoengineUserContext userContext, String promotionLevelId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
 		
 	}
 	public PromotionLevel transferToAnotherPlatform(PromoengineUserContext userContext, String promotionLevelId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, promotionLevelId,anotherPlatformId);
 
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());	
		synchronized(promotionLevel){
			//will be good when the promotionLevel loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			promotionLevel.updatePlatform(platform);		
			promotionLevel = savePromotionLevel(userContext, promotionLevel, emptyOptions());
			
			return present(userContext,promotionLevel, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForPromotionLevel(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(PromoengineUserContext userContext, String promotionLevelId, int promotionLevelVersion) throws Exception {
		//deleteInternal(userContext, promotionLevelId, promotionLevelVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String promotionLevelId, int promotionLevelVersion) throws Exception{
			
		userContext.getDAOGroup().getPromotionLevelDAO().delete(promotionLevelId, promotionLevelVersion);
	}
	
	public PromotionLevel forgetByAll(PromoengineUserContext userContext, String promotionLevelId, int promotionLevelVersion) throws Exception {
		return forgetByAllInternal(userContext, promotionLevelId, promotionLevelVersion);		
	}
	protected PromotionLevel forgetByAllInternal(PromoengineUserContext userContext,
			String promotionLevelId, int promotionLevelVersion) throws Exception{
			
		return userContext.getDAOGroup().getPromotionLevelDAO().disconnectFromAll(promotionLevelId, promotionLevelVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PromotionLevelManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPromotionLevelDAO().deleteAll();
	}


	//disconnect PromotionLevel with promotion in PromotionOffer
	protected PromotionLevel breakWithPromotionOfferByPromotion(PromoengineUserContext userContext, String promotionLevelId, String promotionId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());

			synchronized(promotionLevel){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPromotionLevelDAO().planToRemovePromotionOfferListWithPromotion(promotionLevel, promotionId, this.emptyOptions());

				promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
				return promotionLevel;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String name, String parameter, String promotionId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);

		
		userContext.getChecker().checkNameOfPromotionOffer(name);
		
		userContext.getChecker().checkParameterOfPromotionOffer(parameter);
		
		userContext.getChecker().checkPromotionIdOfPromotionOffer(promotionId);
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);

	
	}
	public  PromotionLevel addPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String name, String parameter, String promotionId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPromotionOffer(userContext,promotionLevelId,name, parameter, promotionId,tokensExpr);
		
		PromotionOffer promotionOffer = createPromotionOffer(userContext,name, parameter, promotionId);
		
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
		synchronized(promotionLevel){ 
			//Will be good when the promotionLevel loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotionLevel.addPromotionOffer( promotionOffer );		
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
			
			userContext.getManagerGroup().getPromotionOfferManager().onNewInstanceCreated(userContext, promotionOffer);
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPromotionOfferProperties(PromoengineUserContext userContext, String promotionLevelId,String id,String name,String parameter,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkIdOfPromotionOffer(id);
		
		userContext.getChecker().checkNameOfPromotionOffer( name);
		userContext.getChecker().checkParameterOfPromotionOffer( parameter);

		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
		
	}
	public  PromotionLevel updatePromotionOfferProperties(PromoengineUserContext userContext, String promotionLevelId, String id,String name,String parameter, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPromotionOfferProperties(userContext,promotionLevelId,id,name,parameter,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPromotionOfferListList()
				.searchPromotionOfferListWith(PromotionOffer.ID_PROPERTY, "is", id).done();
		
		PromotionLevel promotionLevelToUpdate = loadPromotionLevel(userContext, promotionLevelId, options);
		
		if(promotionLevelToUpdate.getPromotionOfferList().isEmpty()){
			throw new PromotionLevelManagerException("PromotionOffer is NOT FOUND with id: '"+id+"'");
		}
		
		PromotionOffer item = promotionLevelToUpdate.getPromotionOfferList().first();
		
		item.updateName( name );
		item.updateParameter( parameter );

		
		//checkParamsForAddingPromotionOffer(userContext,promotionLevelId,name, code, used,tokensExpr);
		PromotionLevel promotionLevel = savePromotionLevel(userContext, promotionLevelToUpdate, tokens().withPromotionOfferList().done());
		synchronized(promotionLevel){ 
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PromotionOffer createPromotionOffer(PromoengineUserContext userContext, String name, String parameter, String promotionId) throws Exception{

		PromotionOffer promotionOffer = new PromotionOffer();
		
		
		promotionOffer.setName(name);		
		promotionOffer.setParameter(parameter);		
		Promotion  promotion = new Promotion();
		promotion.setId(promotionId);		
		promotionOffer.setPromotion(promotion);
	
		
		return promotionOffer;
	
		
	}
	
	protected PromotionOffer createIndexedPromotionOffer(String id, int version){

		PromotionOffer promotionOffer = new PromotionOffer();
		promotionOffer.setId(id);
		promotionOffer.setVersion(version);
		return promotionOffer;			
		
	}
	
	protected void checkParamsForRemovingPromotionOfferList(PromoengineUserContext userContext, String promotionLevelId, 
			String promotionOfferIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		for(String promotionOfferId: promotionOfferIds){
			userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
		
	}
	public  PromotionLevel removePromotionOfferList(PromoengineUserContext userContext, String promotionLevelId, 
			String promotionOfferIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPromotionOfferList(userContext, promotionLevelId,  promotionOfferIds, tokensExpr);
			
			
			PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
			synchronized(promotionLevel){ 
				//Will be good when the promotionLevel loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPromotionLevelDAO().planToRemovePromotionOfferList(promotionLevel, promotionOfferIds, allTokens());
				promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
				deleteRelationListInGraph(userContext, promotionLevel.getPromotionOfferList());
				return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotionLevel( promotionLevelId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
	
	}
	public  PromotionLevel removePromotionOffer(PromoengineUserContext userContext, String promotionLevelId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPromotionOffer(userContext,promotionLevelId, promotionOfferId, promotionOfferVersion,tokensExpr);
		
		PromotionOffer promotionOffer = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
		synchronized(promotionLevel){ 
			//Will be good when the promotionLevel loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			promotionLevel.removePromotionOffer( promotionOffer );		
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
			deleteRelationInGraph(userContext, promotionOffer);
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPromotionLevel( promotionLevelId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
	
	}
	public  PromotionLevel copyPromotionOfferFrom(PromoengineUserContext userContext, String promotionLevelId, 
		String promotionOfferId, int promotionOfferVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPromotionOffer(userContext,promotionLevelId, promotionOfferId, promotionOfferVersion,tokensExpr);
		
		PromotionOffer promotionOffer = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, allTokens());
		synchronized(promotionLevel){ 
			//Will be good when the promotionLevel loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			promotionLevel.copyPromotionOfferFrom( promotionOffer );		
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
			
			userContext.getManagerGroup().getPromotionOfferManager().onNewInstanceCreated(userContext, (PromotionOffer)promotionLevel.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPromotionLevel(promotionLevelId);
		userContext.getChecker().checkIdOfPromotionOffer(promotionOfferId);
		userContext.getChecker().checkVersionOfPromotionOffer(promotionOfferVersion);
		

		if(PromotionOffer.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPromotionOffer(parseString(newValueExpr));
		}
		
		if(PromotionOffer.PARAMETER_PROPERTY.equals(property)){
			userContext.getChecker().checkParameterOfPromotionOffer(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PromotionLevelManagerException.class);
	
	}
	
	public  PromotionLevel updatePromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPromotionOffer(userContext, promotionLevelId, promotionOfferId, promotionOfferVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPromotionOfferList().searchPromotionOfferListWith(PromotionOffer.ID_PROPERTY, "eq", promotionOfferId).done();
		
		
		
		PromotionLevel promotionLevel = loadPromotionLevel(userContext, promotionLevelId, loadTokens);
		
		synchronized(promotionLevel){ 
			//Will be good when the promotionLevel loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//promotionLevel.removePromotionOffer( promotionOffer );	
			//make changes to AcceleraterAccount.
			PromotionOffer promotionOfferIndex = createIndexedPromotionOffer(promotionOfferId, promotionOfferVersion);
		
			PromotionOffer promotionOffer = promotionLevel.findThePromotionOffer(promotionOfferIndex);
			if(promotionOffer == null){
				throw new PromotionLevelManagerException(promotionOffer+" is NOT FOUND" );
			}
			
			promotionOffer.changeProperty(property, newValueExpr);
			
			promotionLevel = savePromotionLevel(userContext, promotionLevel, tokens().withPromotionOfferList().done());
			return present(userContext,promotionLevel, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(PromoengineUserContext userContext, PromotionLevel newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


