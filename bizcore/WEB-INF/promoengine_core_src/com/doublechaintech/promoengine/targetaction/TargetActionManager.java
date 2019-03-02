
package com.doublechaintech.promoengine.targetaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface TargetActionManager{

		

	public TargetAction createTargetAction(PromoengineUserContext userContext, String name, String actionId, String parameter, String promotionId) throws Exception;	
	public TargetAction updateTargetAction(PromoengineUserContext userContext,String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public TargetAction loadTargetAction(PromoengineUserContext userContext, String targetActionId, String [] tokensExpr) throws Exception;
	public TargetAction internalSaveTargetAction(PromoengineUserContext userContext, TargetAction targetAction) throws Exception;
	public TargetAction internalSaveTargetAction(PromoengineUserContext userContext, TargetAction targetAction,Map<String,Object>option) throws Exception;
	
	public TargetAction transferToAnotherAction(PromoengineUserContext userContext, String targetActionId, String anotherActionId)  throws Exception;
 	public TargetAction transferToAnotherPromotion(PromoengineUserContext userContext, String targetActionId, String anotherPromotionId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String targetActionId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, TargetAction newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


