
package com.doublechaintech.promoengine.promotionoffer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevelDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;


public interface PromotionOfferDAO{

	
	public PromotionOffer load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PromotionOffer> promotionOfferList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PromotionOffer present(PromotionOffer promotionOffer,Map<String,Object> options) throws Exception;
	public PromotionOffer clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PromotionOffer save(PromotionOffer promotionOffer,Map<String,Object> options);
	public SmartList<PromotionOffer> savePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options);
	public SmartList<PromotionOffer> removePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options);
	public SmartList<PromotionOffer> findPromotionOfferWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPromotionOfferWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPromotionOfferWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String promotionOfferId, int version) throws Exception;
	public PromotionOffer disconnectFromAll(String promotionOfferId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<PromotionOffer> queryList(String sql, Object ... parmeters);
 
 	public SmartList<PromotionOffer> findPromotionOfferByPromotionLevel(String promotionLevelId, Map<String,Object> options);
 	public int countPromotionOfferByPromotionLevel(String promotionLevelId, Map<String,Object> options);
 	public Map<String, Integer> countPromotionOfferByPromotionLevelIds(String[] ids, Map<String,Object> options);
 	public SmartList<PromotionOffer> findPromotionOfferByPromotionLevel(String promotionLevelId, int start, int count, Map<String,Object> options);
 	public void analyzePromotionOfferByPromotionLevel(SmartList<PromotionOffer> resultList, String promotionLevelId, Map<String,Object> options);

 
  
 	public SmartList<PromotionOffer> findPromotionOfferByPromotion(String promotionId, Map<String,Object> options);
 	public int countPromotionOfferByPromotion(String promotionId, Map<String,Object> options);
 	public Map<String, Integer> countPromotionOfferByPromotionIds(String[] ids, Map<String,Object> options);
 	public SmartList<PromotionOffer> findPromotionOfferByPromotion(String promotionId, int start, int count, Map<String,Object> options);
 	public void analyzePromotionOfferByPromotion(SmartList<PromotionOffer> resultList, String promotionId, Map<String,Object> options);

 
 }


