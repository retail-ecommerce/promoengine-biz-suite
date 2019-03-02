
package com.doublechaintech.promoengine.platform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.applycondition.ApplyConditionDAO;
import com.doublechaintech.promoengine.actiontype.ActionTypeDAO;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevelDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;


public interface PlatformDAO{

	
	public Platform load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Platform> platformList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Platform present(Platform platform,Map<String,Object> options) throws Exception;
	public Platform clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Platform save(Platform platform,Map<String,Object> options);
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String platformId, int version) throws Exception;
	public Platform disconnectFromAll(String platformId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ApplyConditionDAO getApplyConditionDAO();
		
	public ActionTypeDAO getActionTypeDAO();
		
	public PromotionLevelDAO getPromotionLevelDAO();
		
	public PromotionDAO getPromotionDAO();
		
	
 	public SmartList<Platform> requestCandidatePlatformForApplyCondition(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForActionType(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForPromotionLevel(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForPromotion(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Platform planToRemoveApplyConditionList(Platform platform, String applyConditionIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveActionTypeList(Platform platform, String actionTypeIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemovePromotionLevelList(Platform platform, String promotionLevelIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemovePromotionList(Platform platform, String promotionIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<Platform> queryList(String sql, Object ... parmeters);
}


