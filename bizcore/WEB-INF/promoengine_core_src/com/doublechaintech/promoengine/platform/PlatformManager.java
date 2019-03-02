
package com.doublechaintech.promoengine.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface PlatformManager{

		

	public Platform createPlatform(PromoengineUserContext userContext, String name, String introduction, String currentVersion) throws Exception;	
	public Platform updatePlatform(PromoengineUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(PromoengineUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(PromoengineUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(PromoengineUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;
	


	public void delete(PromoengineUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ApplyConditionManager getApplyConditionManager(PromoengineUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addApplyCondition(PromoengineUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeApplyCondition(PromoengineUserContext userContext, String platformId, String applyConditionId, int applyConditionVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateApplyCondition(PromoengineUserContext userContext, String platformId, String applyConditionId, int applyConditionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ActionTypeManager getActionTypeManager(PromoengineUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addActionType(PromoengineUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeActionType(PromoengineUserContext userContext, String platformId, String actionTypeId, int actionTypeVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateActionType(PromoengineUserContext userContext, String platformId, String actionTypeId, int actionTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PromotionLevelManager getPromotionLevelManager(PromoengineUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addPromotionLevel(PromoengineUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removePromotionLevel(PromoengineUserContext userContext, String platformId, String promotionLevelId, int promotionLevelVersion,String [] tokensExpr)  throws Exception;
	public  Platform updatePromotionLevel(PromoengineUserContext userContext, String platformId, String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PromotionManager getPromotionManager(PromoengineUserContext userContext, String platformId, String name, DateTime validAfter, DateTime expireTime ,String [] tokensExpr)  throws Exception;
	
	public  Platform addPromotion(PromoengineUserContext userContext, String platformId, String name, DateTime validAfter, DateTime expireTime , String [] tokensExpr)  throws Exception;
	public  Platform removePromotion(PromoengineUserContext userContext, String platformId, String promotionId, int promotionVersion,String [] tokensExpr)  throws Exception;
	public  Platform updatePromotion(PromoengineUserContext userContext, String platformId, String promotionId, int promotionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


