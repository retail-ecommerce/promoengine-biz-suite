
package com.doublechaintech.promoengine.actiontype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetaction.TargetActionDAO;


public interface ActionTypeDAO{

	
	public ActionType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ActionType> actionTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ActionType present(ActionType actionType,Map<String,Object> options) throws Exception;
	public ActionType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ActionType save(ActionType actionType,Map<String,Object> options);
	public SmartList<ActionType> saveActionTypeList(SmartList<ActionType> actionTypeList,Map<String,Object> options);
	public SmartList<ActionType> removeActionTypeList(SmartList<ActionType> actionTypeList,Map<String,Object> options);
	public SmartList<ActionType> findActionTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countActionTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countActionTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String actionTypeId, int version) throws Exception;
	public ActionType disconnectFromAll(String actionTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TargetActionDAO getTargetActionDAO();
		
	
 	public SmartList<ActionType> requestCandidateActionTypeForTargetAction(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ActionType planToRemoveTargetActionList(ActionType actionType, String targetActionIds[], Map<String,Object> options)throws Exception;


	//disconnect ActionType with promotion in TargetAction
	public ActionType planToRemoveTargetActionListWithPromotion(ActionType actionType, String promotionId, Map<String,Object> options)throws Exception;
	public int countTargetActionListWithPromotion(String actionTypeId, String promotionId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ActionType> queryList(String sql, Object ... parmeters);
 
 	public SmartList<ActionType> findActionTypeByPlatform(String platformId, Map<String,Object> options);
 	public int countActionTypeByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countActionTypeByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ActionType> findActionTypeByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeActionTypeByPlatform(SmartList<ActionType> resultList, String platformId, Map<String,Object> options);

 
 }


