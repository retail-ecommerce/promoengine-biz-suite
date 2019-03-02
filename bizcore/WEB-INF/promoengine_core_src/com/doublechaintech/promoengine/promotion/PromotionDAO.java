
package com.doublechaintech.promoengine.promotion;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetaction.TargetActionDAO;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleDAO;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferDAO;


public interface PromotionDAO{

	
	public Promotion load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Promotion> promotionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Promotion present(Promotion promotion,Map<String,Object> options) throws Exception;
	public Promotion clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Promotion save(Promotion promotion,Map<String,Object> options);
	public SmartList<Promotion> savePromotionList(SmartList<Promotion> promotionList,Map<String,Object> options);
	public SmartList<Promotion> removePromotionList(SmartList<Promotion> promotionList,Map<String,Object> options);
	public SmartList<Promotion> findPromotionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPromotionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPromotionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String promotionId, int version) throws Exception;
	public Promotion disconnectFromAll(String promotionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TargetUserRuleDAO getTargetUserRuleDAO();
		
	public TargetActionDAO getTargetActionDAO();
		
	public PromotionOfferDAO getPromotionOfferDAO();
		
	
 	public SmartList<Promotion> requestCandidatePromotionForTargetUserRule(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Promotion> requestCandidatePromotionForTargetAction(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Promotion> requestCandidatePromotionForPromotionOffer(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Promotion planToRemoveTargetUserRuleList(Promotion promotion, String targetUserRuleIds[], Map<String,Object> options)throws Exception;


	//disconnect Promotion with apply_condition in TargetUserRule
	public Promotion planToRemoveTargetUserRuleListWithApplyCondition(Promotion promotion, String applyConditionId, Map<String,Object> options)throws Exception;
	public int countTargetUserRuleListWithApplyCondition(String promotionId, String applyConditionId, Map<String,Object> options)throws Exception;
	
	public Promotion planToRemoveTargetActionList(Promotion promotion, String targetActionIds[], Map<String,Object> options)throws Exception;


	//disconnect Promotion with action in TargetAction
	public Promotion planToRemoveTargetActionListWithAction(Promotion promotion, String actionId, Map<String,Object> options)throws Exception;
	public int countTargetActionListWithAction(String promotionId, String actionId, Map<String,Object> options)throws Exception;
	
	public Promotion planToRemovePromotionOfferList(Promotion promotion, String promotionOfferIds[], Map<String,Object> options)throws Exception;


	//disconnect Promotion with promotion_level in PromotionOffer
	public Promotion planToRemovePromotionOfferListWithPromotionLevel(Promotion promotion, String promotionLevelId, Map<String,Object> options)throws Exception;
	public int countPromotionOfferListWithPromotionLevel(String promotionId, String promotionLevelId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Promotion> queryList(String sql, Object ... parmeters);
 
 	public SmartList<Promotion> findPromotionByPlatform(String platformId, Map<String,Object> options);
 	public int countPromotionByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countPromotionByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Promotion> findPromotionByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzePromotionByPlatform(SmartList<Promotion> resultList, String platformId, Map<String,Object> options);

 
 }


