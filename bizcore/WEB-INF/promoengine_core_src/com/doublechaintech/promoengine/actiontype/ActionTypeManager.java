
package com.doublechaintech.promoengine.actiontype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface ActionTypeManager{

		

	public ActionType createActionType(PromoengineUserContext userContext, String name, String platformId) throws Exception;	
	public ActionType updateActionType(PromoengineUserContext userContext,String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ActionType loadActionType(PromoengineUserContext userContext, String actionTypeId, String [] tokensExpr) throws Exception;
	public ActionType internalSaveActionType(PromoengineUserContext userContext, ActionType actionType) throws Exception;
	public ActionType internalSaveActionType(PromoengineUserContext userContext, ActionType actionType,Map<String,Object>option) throws Exception;
	
	public ActionType transferToAnotherPlatform(PromoengineUserContext userContext, String actionTypeId, String anotherPlatformId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String actionTypeId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, ActionType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TargetActionManager getTargetActionManager(PromoengineUserContext userContext, String actionTypeId, String name, String parameter, String promotionId ,String [] tokensExpr)  throws Exception;
	
	public  ActionType addTargetAction(PromoengineUserContext userContext, String actionTypeId, String name, String parameter, String promotionId , String [] tokensExpr)  throws Exception;
	public  ActionType removeTargetAction(PromoengineUserContext userContext, String actionTypeId, String targetActionId, int targetActionVersion,String [] tokensExpr)  throws Exception;
	public  ActionType updateTargetAction(PromoengineUserContext userContext, String actionTypeId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


