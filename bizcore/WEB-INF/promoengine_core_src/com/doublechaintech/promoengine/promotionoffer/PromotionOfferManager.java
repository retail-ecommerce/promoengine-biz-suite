
package com.doublechaintech.promoengine.promotionoffer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface PromotionOfferManager{

		

	public PromotionOffer createPromotionOffer(PromoengineUserContext userContext, String name, String promotionLevelId, String parameter, String promotionId) throws Exception;	
	public PromotionOffer updatePromotionOffer(PromoengineUserContext userContext,String promotionOfferId, int promotionOfferVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PromotionOffer loadPromotionOffer(PromoengineUserContext userContext, String promotionOfferId, String [] tokensExpr) throws Exception;
	public PromotionOffer internalSavePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer) throws Exception;
	public PromotionOffer internalSavePromotionOffer(PromoengineUserContext userContext, PromotionOffer promotionOffer,Map<String,Object>option) throws Exception;
	
	public PromotionOffer transferToAnotherPromotionLevel(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionLevelId)  throws Exception;
 	public PromotionOffer transferToAnotherPromotion(PromoengineUserContext userContext, String promotionOfferId, String anotherPromotionId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String promotionOfferId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, PromotionOffer newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


