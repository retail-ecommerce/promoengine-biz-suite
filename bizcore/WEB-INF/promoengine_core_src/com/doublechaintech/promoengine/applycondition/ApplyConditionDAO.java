
package com.doublechaintech.promoengine.applycondition;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleDAO;


public interface ApplyConditionDAO{

	
	public ApplyCondition load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ApplyCondition> applyConditionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ApplyCondition present(ApplyCondition applyCondition,Map<String,Object> options) throws Exception;
	public ApplyCondition clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ApplyCondition save(ApplyCondition applyCondition,Map<String,Object> options);
	public SmartList<ApplyCondition> saveApplyConditionList(SmartList<ApplyCondition> applyConditionList,Map<String,Object> options);
	public SmartList<ApplyCondition> removeApplyConditionList(SmartList<ApplyCondition> applyConditionList,Map<String,Object> options);
	public SmartList<ApplyCondition> findApplyConditionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countApplyConditionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countApplyConditionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String applyConditionId, int version) throws Exception;
	public ApplyCondition disconnectFromAll(String applyConditionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TargetUserRuleDAO getTargetUserRuleDAO();
		
	
 	public SmartList<ApplyCondition> requestCandidateApplyConditionForTargetUserRule(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ApplyCondition planToRemoveTargetUserRuleList(ApplyCondition applyCondition, String targetUserRuleIds[], Map<String,Object> options)throws Exception;


	//disconnect ApplyCondition with promotion in TargetUserRule
	public ApplyCondition planToRemoveTargetUserRuleListWithPromotion(ApplyCondition applyCondition, String promotionId, Map<String,Object> options)throws Exception;
	public int countTargetUserRuleListWithPromotion(String applyConditionId, String promotionId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ApplyCondition> queryList(String sql, Object ... parmeters);
 
 	public SmartList<ApplyCondition> findApplyConditionByPlatform(String platformId, Map<String,Object> options);
 	public int countApplyConditionByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countApplyConditionByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ApplyCondition> findApplyConditionByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeApplyConditionByPlatform(SmartList<ApplyCondition> resultList, String platformId, Map<String,Object> options);

 
 }


