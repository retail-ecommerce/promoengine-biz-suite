
package com.doublechaintech.promoengine.targetaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.actiontype.ActionTypeDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;


public interface TargetActionDAO{

	
	public TargetAction load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<TargetAction> targetActionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public TargetAction present(TargetAction targetAction,Map<String,Object> options) throws Exception;
	public TargetAction clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public TargetAction save(TargetAction targetAction,Map<String,Object> options);
	public SmartList<TargetAction> saveTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options);
	public SmartList<TargetAction> removeTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options);
	public SmartList<TargetAction> findTargetActionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTargetActionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTargetActionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String targetActionId, int version) throws Exception;
	public TargetAction disconnectFromAll(String targetActionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<TargetAction> queryList(String sql, Object ... parmeters);
 
 	public SmartList<TargetAction> findTargetActionByAction(String actionTypeId, Map<String,Object> options);
 	public int countTargetActionByAction(String actionTypeId, Map<String,Object> options);
 	public Map<String, Integer> countTargetActionByActionIds(String[] ids, Map<String,Object> options);
 	public SmartList<TargetAction> findTargetActionByAction(String actionTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeTargetActionByAction(SmartList<TargetAction> resultList, String actionTypeId, Map<String,Object> options);

 
  
 	public SmartList<TargetAction> findTargetActionByPromotion(String promotionId, Map<String,Object> options);
 	public int countTargetActionByPromotion(String promotionId, Map<String,Object> options);
 	public Map<String, Integer> countTargetActionByPromotionIds(String[] ids, Map<String,Object> options);
 	public SmartList<TargetAction> findTargetActionByPromotion(String promotionId, int start, int count, Map<String,Object> options);
 	public void analyzeTargetActionByPromotion(SmartList<TargetAction> resultList, String promotionId, Map<String,Object> options);

 
 }


