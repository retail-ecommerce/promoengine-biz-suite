
package com.doublechaintech.promoengine.targetuserrule;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface TargetUserRuleManager{

		

	public TargetUserRule createTargetUserRule(PromoengineUserContext userContext, String name, String applyConditionId, String parameter, String promotionId) throws Exception;	
	public TargetUserRule updateTargetUserRule(PromoengineUserContext userContext,String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public TargetUserRule loadTargetUserRule(PromoengineUserContext userContext, String targetUserRuleId, String [] tokensExpr) throws Exception;
	public TargetUserRule internalSaveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule) throws Exception;
	public TargetUserRule internalSaveTargetUserRule(PromoengineUserContext userContext, TargetUserRule targetUserRule,Map<String,Object>option) throws Exception;
	
	public TargetUserRule transferToAnotherApplyCondition(PromoengineUserContext userContext, String targetUserRuleId, String anotherApplyConditionId)  throws Exception;
 	public TargetUserRule transferToAnotherPromotion(PromoengineUserContext userContext, String targetUserRuleId, String anotherPromotionId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String targetUserRuleId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, TargetUserRule newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


