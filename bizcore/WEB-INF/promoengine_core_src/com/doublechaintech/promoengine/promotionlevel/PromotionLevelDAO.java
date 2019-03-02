
package com.doublechaintech.promoengine.promotionlevel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferDAO;


public interface PromotionLevelDAO{

	
	public PromotionLevel load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PromotionLevel> promotionLevelList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PromotionLevel present(PromotionLevel promotionLevel,Map<String,Object> options) throws Exception;
	public PromotionLevel clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PromotionLevel save(PromotionLevel promotionLevel,Map<String,Object> options);
	public SmartList<PromotionLevel> savePromotionLevelList(SmartList<PromotionLevel> promotionLevelList,Map<String,Object> options);
	public SmartList<PromotionLevel> removePromotionLevelList(SmartList<PromotionLevel> promotionLevelList,Map<String,Object> options);
	public SmartList<PromotionLevel> findPromotionLevelWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPromotionLevelWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPromotionLevelWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String promotionLevelId, int version) throws Exception;
	public PromotionLevel disconnectFromAll(String promotionLevelId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public PromotionOfferDAO getPromotionOfferDAO();
		
	
 	public SmartList<PromotionLevel> requestCandidatePromotionLevelForPromotionOffer(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public PromotionLevel planToRemovePromotionOfferList(PromotionLevel promotionLevel, String promotionOfferIds[], Map<String,Object> options)throws Exception;


	//disconnect PromotionLevel with promotion in PromotionOffer
	public PromotionLevel planToRemovePromotionOfferListWithPromotion(PromotionLevel promotionLevel, String promotionId, Map<String,Object> options)throws Exception;
	public int countPromotionOfferListWithPromotion(String promotionLevelId, String promotionId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<PromotionLevel> queryList(String sql, Object ... parmeters);
 
 	public SmartList<PromotionLevel> findPromotionLevelByPlatform(String platformId, Map<String,Object> options);
 	public int countPromotionLevelByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countPromotionLevelByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<PromotionLevel> findPromotionLevelByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzePromotionLevelByPlatform(SmartList<PromotionLevel> resultList, String platformId, Map<String,Object> options);

 
 }


