
package com.doublechaintech.promoengine.targetuserrule;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.applycondition.ApplyConditionDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;


public interface TargetUserRuleDAO{

	
	public TargetUserRule load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<TargetUserRule> targetUserRuleList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public TargetUserRule present(TargetUserRule targetUserRule,Map<String,Object> options) throws Exception;
	public TargetUserRule clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public TargetUserRule save(TargetUserRule targetUserRule,Map<String,Object> options);
	public SmartList<TargetUserRule> saveTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options);
	public SmartList<TargetUserRule> removeTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options);
	public SmartList<TargetUserRule> findTargetUserRuleWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTargetUserRuleWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTargetUserRuleWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String targetUserRuleId, int version) throws Exception;
	public TargetUserRule disconnectFromAll(String targetUserRuleId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<TargetUserRule> queryList(String sql, Object ... parmeters);
 
 	public SmartList<TargetUserRule> findTargetUserRuleByApplyCondition(String applyConditionId, Map<String,Object> options);
 	public int countTargetUserRuleByApplyCondition(String applyConditionId, Map<String,Object> options);
 	public Map<String, Integer> countTargetUserRuleByApplyConditionIds(String[] ids, Map<String,Object> options);
 	public SmartList<TargetUserRule> findTargetUserRuleByApplyCondition(String applyConditionId, int start, int count, Map<String,Object> options);
 	public void analyzeTargetUserRuleByApplyCondition(SmartList<TargetUserRule> resultList, String applyConditionId, Map<String,Object> options);

 
  
 	public SmartList<TargetUserRule> findTargetUserRuleByPromotion(String promotionId, Map<String,Object> options);
 	public int countTargetUserRuleByPromotion(String promotionId, Map<String,Object> options);
 	public Map<String, Integer> countTargetUserRuleByPromotionIds(String[] ids, Map<String,Object> options);
 	public SmartList<TargetUserRule> findTargetUserRuleByPromotion(String promotionId, int start, int count, Map<String,Object> options);
 	public void analyzeTargetUserRuleByPromotion(SmartList<TargetUserRule> resultList, String promotionId, Map<String,Object> options);

 
 }


