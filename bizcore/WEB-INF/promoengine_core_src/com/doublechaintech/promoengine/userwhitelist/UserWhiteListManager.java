
package com.doublechaintech.promoengine.userwhitelist;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface UserWhiteListManager{

		

	public UserWhiteList createUserWhiteList(PromoengineUserContext userContext, String userIdentity, String userSpecialFunctions, String domainId) throws Exception;	
	public UserWhiteList updateUserWhiteList(PromoengineUserContext userContext,String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserWhiteList loadUserWhiteList(PromoengineUserContext userContext, String userWhiteListId, String [] tokensExpr) throws Exception;
	public UserWhiteList internalSaveUserWhiteList(PromoengineUserContext userContext, UserWhiteList userWhiteList) throws Exception;
	public UserWhiteList internalSaveUserWhiteList(PromoengineUserContext userContext, UserWhiteList userWhiteList,Map<String,Object>option) throws Exception;
	
	public UserWhiteList transferToAnotherDomain(PromoengineUserContext userContext, String userWhiteListId, String anotherDomainId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String userWhiteListId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, UserWhiteList newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


