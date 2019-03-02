
package com.doublechaintech.promoengine.applycondition;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface ApplyConditionManager{

		

	public ApplyCondition createApplyCondition(PromoengineUserContext userContext, String name, String platformId) throws Exception;	
	public ApplyCondition updateApplyCondition(PromoengineUserContext userContext,String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ApplyCondition loadApplyCondition(PromoengineUserContext userContext, String applyConditionId, String [] tokensExpr) throws Exception;
	public ApplyCondition internalSaveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition) throws Exception;
	public ApplyCondition internalSaveApplyCondition(PromoengineUserContext userContext, ApplyCondition applyCondition,Map<String,Object>option) throws Exception;
	
	public ApplyCondition transferToAnotherPlatform(PromoengineUserContext userContext, String applyConditionId, String anotherPlatformId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String applyConditionId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, ApplyCondition newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TargetUserRuleManager getTargetUserRuleManager(PromoengineUserContext userContext, String applyConditionId, String name, String parameter, String promotionId ,String [] tokensExpr)  throws Exception;
	
	public  ApplyCondition addTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String name, String parameter, String promotionId , String [] tokensExpr)  throws Exception;
	public  ApplyCondition removeTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr)  throws Exception;
	public  ApplyCondition updateTargetUserRule(PromoengineUserContext userContext, String applyConditionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


