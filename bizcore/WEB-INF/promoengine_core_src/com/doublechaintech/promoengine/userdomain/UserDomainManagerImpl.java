
package com.doublechaintech.promoengine.userdomain;

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

import com.doublechaintech.promoengine.userwhitelist.UserWhiteList;
import com.doublechaintech.promoengine.secuser.SecUser;


import com.doublechaintech.promoengine.secuserblocking.SecUserBlocking;
import com.doublechaintech.promoengine.userdomain.UserDomain;






public class UserDomainManagerImpl extends CustomPromoengineCheckerManager implements UserDomainManager {
	
	private static final String SERVICE_TYPE = "UserDomain";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws UserDomainManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new UserDomainManagerException(message);

	}
	
	

 	protected UserDomain saveUserDomain(PromoengineUserContext userContext, UserDomain userDomain, String [] tokensExpr) throws Exception{	
 		//return getUserDomainDAO().save(userDomain, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUserDomain(userContext, userDomain, tokens);
 	}
 	
 	protected UserDomain saveUserDomainDetail(PromoengineUserContext userContext, UserDomain userDomain) throws Exception{	

 		
 		return saveUserDomain(userContext, userDomain, allTokens());
 	}
 	
 	public UserDomain loadUserDomain(PromoengineUserContext userContext, String userDomainId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().throwExceptionIfHasErrors( UserDomainManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userDomain, tokens);
 	}
 	
 	
 	 public UserDomain searchUserDomain(PromoengineUserContext userContext, String userDomainId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().throwExceptionIfHasErrors( UserDomainManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userDomain, tokens);
 	}
 	
 	

 	protected UserDomain present(PromoengineUserContext userContext, UserDomain userDomain, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,userDomain,tokens);
		
		
		UserDomain  userDomainToPresent = userContext.getDAOGroup().getUserDomainDAO().present(userDomain, tokens);
		
		List<BaseEntity> entityListToNaming = userDomainToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getUserDomainDAO().alias(entityListToNaming);
		
		return  userDomainToPresent;
		
		
	}
 
 	
 	
 	public UserDomain loadUserDomainDetail(PromoengineUserContext userContext, String userDomainId) throws Exception{	
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, allTokens());
 		return present(userContext,userDomain, allTokens());
		
 	}
 	
 	public Object view(PromoengineUserContext userContext, String userDomainId) throws Exception{	
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, viewTokens());
 		return present(userContext,userDomain, allTokens());
		
 	}
 	protected UserDomain saveUserDomain(PromoengineUserContext userContext, UserDomain userDomain, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getUserDomainDAO().save(userDomain, tokens);
 	}
 	protected UserDomain loadUserDomain(PromoengineUserContext userContext, String userDomainId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().throwExceptionIfHasErrors( UserDomainManagerException.class);

 
 		return userContext.getDAOGroup().getUserDomainDAO().load(userDomainId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, UserDomain userDomain, Map<String, Object> tokens){
		super.addActions(userContext, userDomain, tokens);
		
		addAction(userContext, userDomain, tokens,"@create","createUserDomain","createUserDomain/","main","primary");
		addAction(userContext, userDomain, tokens,"@update","updateUserDomain","updateUserDomain/"+userDomain.getId()+"/","main","primary");
		addAction(userContext, userDomain, tokens,"@copy","cloneUserDomain","cloneUserDomain/"+userDomain.getId()+"/","main","primary");
		
		addAction(userContext, userDomain, tokens,"user_domain.addUserWhiteList","addUserWhiteList","addUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.removeUserWhiteList","removeUserWhiteList","removeUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.updateUserWhiteList","updateUserWhiteList","updateUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.copyUserWhiteListFrom","copyUserWhiteListFrom","copyUserWhiteListFrom/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.addSecUser","addSecUser","addSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.removeSecUser","removeSecUser","removeSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.updateSecUser","updateSecUser","updateSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.copySecUserFrom","copySecUserFrom","copySecUserFrom/"+userDomain.getId()+"/","secUserList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(PromoengineUserContext userContext, UserDomain userDomain, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public UserDomain createUserDomain(PromoengineUserContext userContext,String name) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfUserDomain(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);


		UserDomain userDomain=createNewUserDomain();	

		userDomain.setName(name);

		userDomain = saveUserDomain(userContext, userDomain, emptyOptions());
		
		onNewInstanceCreated(userContext, userDomain);
		return userDomain;

		
	}
	protected UserDomain createNewUserDomain() 
	{
		
		return new UserDomain();		
	}
	
	protected void checkParamsForUpdatingUserDomain(PromoengineUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().checkVersionOfUserDomain( userDomainVersion);
		

		if(UserDomain.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfUserDomain(parseString(newValueExpr));
		}
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
		
	}
	
	
	
	public UserDomain clone(PromoengineUserContext userContext, String fromUserDomainId) throws Exception{
		
		return userContext.getDAOGroup().getUserDomainDAO().clone(fromUserDomainId, this.allTokens());
	}
	
	public UserDomain internalSaveUserDomain(PromoengineUserContext userContext, UserDomain userDomain) throws Exception 
	{
		return internalSaveUserDomain(userContext, userDomain, allTokens());

	}
	public UserDomain internalSaveUserDomain(PromoengineUserContext userContext, UserDomain userDomain, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(userDomain){ 
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.
			
			
			userDomain = saveUserDomain(userContext, userDomain, options);
			return userDomain;
			
		}

	}
	
	public UserDomain updateUserDomain(PromoengineUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);
		
		
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		if(userDomain.getVersion() != userDomainVersion){
			String message = "The target version("+userDomain.getVersion()+") is not equals to version("+userDomainVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userDomain){ 
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.
			
			userDomain.changeProperty(property, newValueExpr);
			userDomain = saveUserDomain(userContext, userDomain, tokens().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			//return saveUserDomain(userContext, userDomain, tokens().done());
		}

	}
	
	public UserDomain updateUserDomainProperty(PromoengineUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		if(userDomain.getVersion() != userDomainVersion){
			String message = "The target version("+userDomain.getVersion()+") is not equals to version("+userDomainVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userDomain){ 
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.
			
			userDomain.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			//return saveUserDomain(userContext, userDomain, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected UserDomainTokens tokens(){
		return UserDomainTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserDomainTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortUserWhiteListListWith("id","desc")
		.sortSecUserListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserDomainTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(PromoengineUserContext userContext, String userDomainId, int userDomainVersion) throws Exception {
		//deleteInternal(userContext, userDomainId, userDomainVersion);		
	}
	protected void deleteInternal(PromoengineUserContext userContext,
			String userDomainId, int userDomainVersion) throws Exception{
			
		userContext.getDAOGroup().getUserDomainDAO().delete(userDomainId, userDomainVersion);
	}
	
	public UserDomain forgetByAll(PromoengineUserContext userContext, String userDomainId, int userDomainVersion) throws Exception {
		return forgetByAllInternal(userContext, userDomainId, userDomainVersion);		
	}
	protected UserDomain forgetByAllInternal(PromoengineUserContext userContext,
			String userDomainId, int userDomainVersion) throws Exception{
			
		return userContext.getDAOGroup().getUserDomainDAO().disconnectFromAll(userDomainId, userDomainVersion);
	}
	

	
	public int deleteAll(PromoengineUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserDomainManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(PromoengineUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getUserDomainDAO().deleteAll();
	}


	
	
	
	
	

	protected void checkParamsForAddingUserWhiteList(PromoengineUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);

		
		userContext.getChecker().checkUserIdentityOfUserWhiteList(userIdentity);
		
		userContext.getChecker().checkUserSpecialFunctionsOfUserWhiteList(userSpecialFunctions);
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);

	
	}
	public  UserDomain addUserWhiteList(PromoengineUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingUserWhiteList(userContext,userDomainId,userIdentity, userSpecialFunctions,tokensExpr);
		
		UserWhiteList userWhiteList = createUserWhiteList(userContext,userIdentity, userSpecialFunctions);
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.addUserWhiteList( userWhiteList );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			
			userContext.getManagerGroup().getUserWhiteListManager().onNewInstanceCreated(userContext, userWhiteList);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserWhiteListProperties(PromoengineUserContext userContext, String userDomainId,String id,String userIdentity,String userSpecialFunctions,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().checkIdOfUserWhiteList(id);
		
		userContext.getChecker().checkUserIdentityOfUserWhiteList( userIdentity);
		userContext.getChecker().checkUserSpecialFunctionsOfUserWhiteList( userSpecialFunctions);

		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
		
	}
	public  UserDomain updateUserWhiteListProperties(PromoengineUserContext userContext, String userDomainId, String id,String userIdentity,String userSpecialFunctions, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingUserWhiteListProperties(userContext,userDomainId,id,userIdentity,userSpecialFunctions,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserWhiteListListList()
				.searchUserWhiteListListWith(UserWhiteList.ID_PROPERTY, "is", id).done();
		
		UserDomain userDomainToUpdate = loadUserDomain(userContext, userDomainId, options);
		
		if(userDomainToUpdate.getUserWhiteListList().isEmpty()){
			throw new UserDomainManagerException("UserWhiteList is NOT FOUND with id: '"+id+"'");
		}
		
		UserWhiteList item = userDomainToUpdate.getUserWhiteListList().first();
		
		item.updateUserIdentity( userIdentity );
		item.updateUserSpecialFunctions( userSpecialFunctions );

		
		//checkParamsForAddingUserWhiteList(userContext,userDomainId,name, code, used,tokensExpr);
		UserDomain userDomain = saveUserDomain(userContext, userDomainToUpdate, tokens().withUserWhiteListList().done());
		synchronized(userDomain){ 
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected UserWhiteList createUserWhiteList(PromoengineUserContext userContext, String userIdentity, String userSpecialFunctions) throws Exception{

		UserWhiteList userWhiteList = new UserWhiteList();
		
		
		userWhiteList.setUserIdentity(userIdentity);		
		userWhiteList.setUserSpecialFunctions(userSpecialFunctions);
	
		
		return userWhiteList;
	
		
	}
	
	protected UserWhiteList createIndexedUserWhiteList(String id, int version){

		UserWhiteList userWhiteList = new UserWhiteList();
		userWhiteList.setId(id);
		userWhiteList.setVersion(version);
		return userWhiteList;			
		
	}
	
	protected void checkParamsForRemovingUserWhiteListList(PromoengineUserContext userContext, String userDomainId, 
			String userWhiteListIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		for(String userWhiteListId: userWhiteListIds){
			userContext.getChecker().checkIdOfUserWhiteList(userWhiteListId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
		
	}
	public  UserDomain removeUserWhiteListList(PromoengineUserContext userContext, String userDomainId, 
			String userWhiteListIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingUserWhiteListList(userContext, userDomainId,  userWhiteListIds, tokensExpr);
			
			
			UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
			synchronized(userDomain){ 
				//Will be good when the userDomain loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getUserDomainDAO().planToRemoveUserWhiteListList(userDomain, userWhiteListIds, allTokens());
				userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
				deleteRelationListInGraph(userContext, userDomain.getUserWhiteListList());
				return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingUserWhiteList(PromoengineUserContext userContext, String userDomainId, 
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfUserDomain( userDomainId);
		userContext.getChecker().checkIdOfUserWhiteList(userWhiteListId);
		userContext.getChecker().checkVersionOfUserWhiteList(userWhiteListVersion);
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	public  UserDomain removeUserWhiteList(PromoengineUserContext userContext, String userDomainId, 
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingUserWhiteList(userContext,userDomainId, userWhiteListId, userWhiteListVersion,tokensExpr);
		
		UserWhiteList userWhiteList = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.removeUserWhiteList( userWhiteList );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			deleteRelationInGraph(userContext, userWhiteList);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingUserWhiteList(PromoengineUserContext userContext, String userDomainId, 
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfUserDomain( userDomainId);
		userContext.getChecker().checkIdOfUserWhiteList(userWhiteListId);
		userContext.getChecker().checkVersionOfUserWhiteList(userWhiteListVersion);
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	public  UserDomain copyUserWhiteListFrom(PromoengineUserContext userContext, String userDomainId, 
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingUserWhiteList(userContext,userDomainId, userWhiteListId, userWhiteListVersion,tokensExpr);
		
		UserWhiteList userWhiteList = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			userDomain.copyUserWhiteListFrom( userWhiteList );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			
			userContext.getManagerGroup().getUserWhiteListManager().onNewInstanceCreated(userContext, (UserWhiteList)userDomain.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingUserWhiteList(PromoengineUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().checkIdOfUserWhiteList(userWhiteListId);
		userContext.getChecker().checkVersionOfUserWhiteList(userWhiteListVersion);
		

		if(UserWhiteList.USER_IDENTITY_PROPERTY.equals(property)){
			userContext.getChecker().checkUserIdentityOfUserWhiteList(parseString(newValueExpr));
		}
		
		if(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY.equals(property)){
			userContext.getChecker().checkUserSpecialFunctionsOfUserWhiteList(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	
	public  UserDomain updateUserWhiteList(PromoengineUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingUserWhiteList(userContext, userDomainId, userWhiteListId, userWhiteListVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withUserWhiteListList().searchUserWhiteListListWith(UserWhiteList.ID_PROPERTY, "eq", userWhiteListId).done();
		
		
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, loadTokens);
		
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//userDomain.removeUserWhiteList( userWhiteList );	
			//make changes to AcceleraterAccount.
			UserWhiteList userWhiteListIndex = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);
		
			UserWhiteList userWhiteList = userDomain.findTheUserWhiteList(userWhiteListIndex);
			if(userWhiteList == null){
				throw new UserDomainManagerException(userWhiteList+" is NOT FOUND" );
			}
			
			userWhiteList.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingSecUser(PromoengineUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);

		
		userContext.getChecker().checkLoginOfSecUser(login);
		
		userContext.getChecker().checkMobileOfSecUser(mobile);
		
		userContext.getChecker().checkEmailOfSecUser(email);
		
		userContext.getChecker().checkPwdOfSecUser(pwd);
		
		userContext.getChecker().checkVerificationCodeOfSecUser(verificationCode);
		
		userContext.getChecker().checkVerificationCodeExpireOfSecUser(verificationCodeExpire);
		
		userContext.getChecker().checkLastLoginTimeOfSecUser(lastLoginTime);
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);

	
	}
	public  UserDomain addSecUser(PromoengineUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSecUser(userContext,userDomainId,login, mobile, email, pwd, verificationCode, verificationCodeExpire, lastLoginTime,tokensExpr);
		
		SecUser secUser = createSecUser(userContext,login, mobile, email, pwd, verificationCode, verificationCodeExpire, lastLoginTime);
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.addSecUser( secUser );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			
			userContext.getManagerGroup().getSecUserManager().onNewInstanceCreated(userContext, secUser);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSecUserProperties(PromoengineUserContext userContext, String userDomainId,String id,String login,String mobile,String email,String pwd,int verificationCode,DateTime verificationCodeExpire,DateTime lastLoginTime,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().checkIdOfSecUser(id);
		
		userContext.getChecker().checkLoginOfSecUser( login);
		userContext.getChecker().checkMobileOfSecUser( mobile);
		userContext.getChecker().checkEmailOfSecUser( email);
		userContext.getChecker().checkPwdOfSecUser( pwd);
		userContext.getChecker().checkVerificationCodeOfSecUser( verificationCode);
		userContext.getChecker().checkVerificationCodeExpireOfSecUser( verificationCodeExpire);
		userContext.getChecker().checkLastLoginTimeOfSecUser( lastLoginTime);

		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
		
	}
	public  UserDomain updateSecUserProperties(PromoengineUserContext userContext, String userDomainId, String id,String login,String mobile,String email,String pwd,int verificationCode,DateTime verificationCodeExpire,DateTime lastLoginTime, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSecUserProperties(userContext,userDomainId,id,login,mobile,email,pwd,verificationCode,verificationCodeExpire,lastLoginTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSecUserListList()
				.searchSecUserListWith(SecUser.ID_PROPERTY, "is", id).done();
		
		UserDomain userDomainToUpdate = loadUserDomain(userContext, userDomainId, options);
		
		if(userDomainToUpdate.getSecUserList().isEmpty()){
			throw new UserDomainManagerException("SecUser is NOT FOUND with id: '"+id+"'");
		}
		
		SecUser item = userDomainToUpdate.getSecUserList().first();
		
		item.updateLogin( login );
		item.updateMobile( mobile );
		item.updateEmail( email );
		item.updatePwd( pwd );
		item.updateVerificationCode( verificationCode );
		item.updateVerificationCodeExpire( verificationCodeExpire );
		item.updateLastLoginTime( lastLoginTime );

		
		//checkParamsForAddingSecUser(userContext,userDomainId,name, code, used,tokensExpr);
		UserDomain userDomain = saveUserDomain(userContext, userDomainToUpdate, tokens().withSecUserList().done());
		synchronized(userDomain){ 
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SecUser createSecUser(PromoengineUserContext userContext, String login, String mobile, String email, String pwd, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime) throws Exception{

		SecUser secUser = new SecUser();
		
		
		secUser.setLogin(login);		
		secUser.setMobile(mobile);		
		secUser.setEmail(email);		
		secUser.setClearTextOfPwd(pwd);		
		secUser.setVerificationCode(verificationCode);		
		secUser.setVerificationCodeExpire(verificationCodeExpire);		
		secUser.setLastLoginTime(lastLoginTime);		
		secUser.setCurrentStatus("INIT");
	
		
		return secUser;
	
		
	}
	
	protected SecUser createIndexedSecUser(String id, int version){

		SecUser secUser = new SecUser();
		secUser.setId(id);
		secUser.setVersion(version);
		return secUser;			
		
	}
	
	protected void checkParamsForRemovingSecUserList(PromoengineUserContext userContext, String userDomainId, 
			String secUserIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		for(String secUserId: secUserIds){
			userContext.getChecker().checkIdOfSecUser(secUserId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
		
	}
	public  UserDomain removeSecUserList(PromoengineUserContext userContext, String userDomainId, 
			String secUserIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSecUserList(userContext, userDomainId,  secUserIds, tokensExpr);
			
			
			UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
			synchronized(userDomain){ 
				//Will be good when the userDomain loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getUserDomainDAO().planToRemoveSecUserList(userDomain, secUserIds, allTokens());
				userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
				deleteRelationListInGraph(userContext, userDomain.getSecUserList());
				return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSecUser(PromoengineUserContext userContext, String userDomainId, 
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfUserDomain( userDomainId);
		userContext.getChecker().checkIdOfSecUser(secUserId);
		userContext.getChecker().checkVersionOfSecUser(secUserVersion);
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	public  UserDomain removeSecUser(PromoengineUserContext userContext, String userDomainId, 
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSecUser(userContext,userDomainId, secUserId, secUserVersion,tokensExpr);
		
		SecUser secUser = createIndexedSecUser(secUserId, secUserVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.removeSecUser( secUser );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			deleteRelationInGraph(userContext, secUser);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSecUser(PromoengineUserContext userContext, String userDomainId, 
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfUserDomain( userDomainId);
		userContext.getChecker().checkIdOfSecUser(secUserId);
		userContext.getChecker().checkVersionOfSecUser(secUserVersion);
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	public  UserDomain copySecUserFrom(PromoengineUserContext userContext, String userDomainId, 
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSecUser(userContext,userDomainId, secUserId, secUserVersion,tokensExpr);
		
		SecUser secUser = createIndexedSecUser(secUserId, secUserVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			userDomain.copySecUserFrom( secUser );		
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			
			userContext.getManagerGroup().getSecUserManager().onNewInstanceCreated(userContext, (SecUser)userDomain.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSecUser(PromoengineUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfUserDomain(userDomainId);
		userContext.getChecker().checkIdOfSecUser(secUserId);
		userContext.getChecker().checkVersionOfSecUser(secUserVersion);
		

		if(SecUser.LOGIN_PROPERTY.equals(property)){
			userContext.getChecker().checkLoginOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.MOBILE_PROPERTY.equals(property)){
			userContext.getChecker().checkMobileOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.EMAIL_PROPERTY.equals(property)){
			userContext.getChecker().checkEmailOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.PWD_PROPERTY.equals(property)){
			userContext.getChecker().checkPwdOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.VERIFICATION_CODE_PROPERTY.equals(property)){
			userContext.getChecker().checkVerificationCodeOfSecUser(parseInt(newValueExpr));
		}
		
		if(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY.equals(property)){
			userContext.getChecker().checkVerificationCodeExpireOfSecUser(parseTimestamp(newValueExpr));
		}
		
		if(SecUser.LAST_LOGIN_TIME_PROPERTY.equals(property)){
			userContext.getChecker().checkLastLoginTimeOfSecUser(parseTimestamp(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(UserDomainManagerException.class);
	
	}
	
	public  UserDomain updateSecUser(PromoengineUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSecUser(userContext, userDomainId, secUserId, secUserVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSecUserList().searchSecUserListWith(SecUser.ID_PROPERTY, "eq", secUserId).done();
		
		
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, loadTokens);
		
		synchronized(userDomain){ 
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//userDomain.removeSecUser( secUser );	
			//make changes to AcceleraterAccount.
			SecUser secUserIndex = createIndexedSecUser(secUserId, secUserVersion);
		
			SecUser secUser = userDomain.findTheSecUser(secUserIndex);
			if(secUser == null){
				throw new UserDomainManagerException(secUser+" is NOT FOUND" );
			}
			
			secUser.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  UserDomain associateSecUserListToNewBlocking(PromoengineUserContext userContext, String userDomainId, String  secUserIds[], String who, String comments, String [] tokensExpr) throws Exception {

		
		
		Map<String, Object> options = tokens()
				.allTokens()
				.searchSecUserListWith(SecUser.ID_PROPERTY, "oneof", this.joinArray("|", secUserIds)).done();
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, options);
		
		SecUserBlocking blocking = userContext.getManagerGroup().getSecUserBlockingManager().createSecUserBlocking(userContext,  who,  comments);
		
		for(SecUser secUser: userDomain.getSecUserList()) {
			//TODO: need to check if already associated
			secUser.updateBlocking(blocking);
		}
		return this.internalSaveUserDomain(userContext, userDomain);
	}
	*/
	
	public  UserDomain associateSecUserListToBlocking(PromoengineUserContext userContext, String userDomainId, String  secUserIds[], String blockingId, String [] tokensExpr) throws Exception {

		
		
		Map<String, Object> options = tokens()
				.allTokens()
				.searchSecUserListWith(SecUser.ID_PROPERTY, "oneof", this.joinArray("|", secUserIds)).done();
		
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, options);
		
		SecUserBlocking blocking = userContext.getManagerGroup().getSecUserBlockingManager().loadSecUserBlocking(userContext,blockingId,new String[]{"none"} );
		
		for(SecUser secUser: userDomain.getSecUserList()) {
			//TODO: need to check if already associated
			secUser.updateBlocking(blocking);
		}
		return this.internalSaveUserDomain(userContext, userDomain);
	}


	public void onNewInstanceCreated(PromoengineUserContext userContext, UserDomain newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


