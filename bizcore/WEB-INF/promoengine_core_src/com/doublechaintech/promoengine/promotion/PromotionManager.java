
package com.doublechaintech.promoengine.promotion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface PromotionManager{

		

	public Promotion createPromotion(PromoengineUserContext userContext, String name, DateTime validAfter, DateTime expireTime, String platformId) throws Exception;	
	public Promotion updatePromotion(PromoengineUserContext userContext,String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Promotion loadPromotion(PromoengineUserContext userContext, String promotionId, String [] tokensExpr) throws Exception;
	public Promotion internalSavePromotion(PromoengineUserContext userContext, Promotion promotion) throws Exception;
	public Promotion internalSavePromotion(PromoengineUserContext userContext, Promotion promotion,Map<String,Object>option) throws Exception;
	
	public Promotion transferToAnotherPlatform(PromoengineUserContext userContext, String promotionId, String anotherPlatformId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String promotionId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, Promotion newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TargetUserRuleManager getTargetUserRuleManager(PromoengineUserContext userContext, String promotionId, String name, String applyConditionId, String parameter ,String [] tokensExpr)  throws Exception;
	
	public  Promotion addTargetUserRule(PromoengineUserContext userContext, String promotionId, String name, String applyConditionId, String parameter , String [] tokensExpr)  throws Exception;
	public  Promotion removeTargetUserRule(PromoengineUserContext userContext, String promotionId, String targetUserRuleId, int targetUserRuleVersion,String [] tokensExpr)  throws Exception;
	public  Promotion updateTargetUserRule(PromoengineUserContext userContext, String promotionId, String targetUserRuleId, int targetUserRuleVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  TargetActionManager getTargetActionManager(PromoengineUserContext userContext, String promotionId, String name, String actionId, String parameter ,String [] tokensExpr)  throws Exception;
	
	public  Promotion addTargetAction(PromoengineUserContext userContext, String promotionId, String name, String actionId, String parameter , String [] tokensExpr)  throws Exception;
	public  Promotion removeTargetAction(PromoengineUserContext userContext, String promotionId, String targetActionId, int targetActionVersion,String [] tokensExpr)  throws Exception;
	public  Promotion updateTargetAction(PromoengineUserContext userContext, String promotionId, String targetActionId, int targetActionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PromotionOfferManager getPromotionOfferManager(PromoengineUserContext userContext, String promotionId, String name, String promotionLevelId, String parameter ,String [] tokensExpr)  throws Exception;
	
	public  Promotion addPromotionOffer(PromoengineUserContext userContext, String promotionId, String name, String promotionLevelId, String parameter , String [] tokensExpr)  throws Exception;
	public  Promotion removePromotionOffer(PromoengineUserContext userContext, String promotionId, String promotionOfferId, int promotionOfferVersion,String [] tokensExpr)  throws Exception;
	public  Promotion updatePromotionOffer(PromoengineUserContext userContext, String promotionId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


