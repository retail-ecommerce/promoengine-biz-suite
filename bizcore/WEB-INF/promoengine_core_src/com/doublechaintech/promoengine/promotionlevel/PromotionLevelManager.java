
package com.doublechaintech.promoengine.promotionlevel;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface PromotionLevelManager{

		

	public PromotionLevel createPromotionLevel(PromoengineUserContext userContext, String name, String platformId) throws Exception;	
	public PromotionLevel updatePromotionLevel(PromoengineUserContext userContext,String promotionLevelId, int promotionLevelVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PromotionLevel loadPromotionLevel(PromoengineUserContext userContext, String promotionLevelId, String [] tokensExpr) throws Exception;
	public PromotionLevel internalSavePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel) throws Exception;
	public PromotionLevel internalSavePromotionLevel(PromoengineUserContext userContext, PromotionLevel promotionLevel,Map<String,Object>option) throws Exception;
	
	public PromotionLevel transferToAnotherPlatform(PromoengineUserContext userContext, String promotionLevelId, String anotherPlatformId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String promotionLevelId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, PromotionLevel newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  PromotionOfferManager getPromotionOfferManager(PromoengineUserContext userContext, String promotionLevelId, String name, String parameter, String promotionId ,String [] tokensExpr)  throws Exception;
	
	public  PromotionLevel addPromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String name, String parameter, String promotionId , String [] tokensExpr)  throws Exception;
	public  PromotionLevel removePromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String promotionOfferId, int promotionOfferVersion,String [] tokensExpr)  throws Exception;
	public  PromotionLevel updatePromotionOffer(PromoengineUserContext userContext, String promotionLevelId, String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


