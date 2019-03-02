
package com.doublechaintech.promoengine.loginhistory;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface LoginHistoryManager{

		

	public LoginHistory createLoginHistory(PromoengineUserContext userContext, String fromIp, String description, String secUserId) throws Exception;	
	public LoginHistory updateLoginHistory(PromoengineUserContext userContext,String loginHistoryId, int loginHistoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public LoginHistory loadLoginHistory(PromoengineUserContext userContext, String loginHistoryId, String [] tokensExpr) throws Exception;
	public LoginHistory internalSaveLoginHistory(PromoengineUserContext userContext, LoginHistory loginHistory) throws Exception;
	public LoginHistory internalSaveLoginHistory(PromoengineUserContext userContext, LoginHistory loginHistory,Map<String,Object>option) throws Exception;
	
	public LoginHistory transferToAnotherSecUser(PromoengineUserContext userContext, String loginHistoryId, String anotherSecUserId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String loginHistoryId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, LoginHistory newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


