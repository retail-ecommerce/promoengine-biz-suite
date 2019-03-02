
package com.doublechaintech.promoengine.targetuserrule;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.PromoengineNamingServiceDAO;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.AccessKey;
import com.doublechaintech.promoengine.DateKey;
import com.doublechaintech.promoengine.StatsInfo;
import com.doublechaintech.promoengine.StatsItem;

import com.doublechaintech.promoengine.MultipleAccessKey;
import com.doublechaintech.promoengine.PromoengineUserContext;


import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;

import com.doublechaintech.promoengine.applycondition.ApplyConditionDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class TargetUserRuleJDBCTemplateDAO extends PromoengineNamingServiceDAO implements TargetUserRuleDAO{
 
 	
 	private  ApplyConditionDAO  applyConditionDAO;
 	public void setApplyConditionDAO(ApplyConditionDAO applyConditionDAO){
	 	this.applyConditionDAO = applyConditionDAO;
 	}
 	public ApplyConditionDAO getApplyConditionDAO(){
	 	return this.applyConditionDAO;
 	}
 
 	
 	private  PromotionDAO  promotionDAO;
 	public void setPromotionDAO(PromotionDAO promotionDAO){
	 	this.promotionDAO = promotionDAO;
 	}
 	public PromotionDAO getPromotionDAO(){
	 	return this.promotionDAO;
 	}


			
		

	
	/*
	protected TargetUserRule load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalTargetUserRule(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public TargetUserRule load(String id,Map<String,Object> options) throws Exception{
		return loadInternalTargetUserRule(TargetUserRuleTable.withId(id), options);
	}
	
	
	
	public TargetUserRule save(TargetUserRule targetUserRule,Map<String,Object> options){
		
		String methodName="save(TargetUserRule targetUserRule,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(targetUserRule, methodName, "targetUserRule");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalTargetUserRule(targetUserRule,options);
	}
	public TargetUserRule clone(String targetUserRuleId, Map<String,Object> options) throws Exception{
	
		return clone(TargetUserRuleTable.withId(targetUserRuleId),options);
	}
	
	protected TargetUserRule clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String targetUserRuleId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		TargetUserRule newTargetUserRule = loadInternalTargetUserRule(accessKey, options);
		newTargetUserRule.setVersion(0);
		
		

		
		saveInternalTargetUserRule(newTargetUserRule,options);
		
		return newTargetUserRule;
	}
	
	
	
	

	protected void throwIfHasException(String targetUserRuleId,int version,int count) throws Exception{
		if (count == 1) {
			throw new TargetUserRuleVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new TargetUserRuleNotFoundException(
					"The " + this.getTableName() + "(" + targetUserRuleId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String targetUserRuleId, int version) throws Exception{
	
		String methodName="delete(String targetUserRuleId, int version)";
		assertMethodArgumentNotNull(targetUserRuleId, methodName, "targetUserRuleId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{targetUserRuleId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(targetUserRuleId,version);
		}
		
	
	}
	
	
	
	
	

	public TargetUserRule disconnectFromAll(String targetUserRuleId, int version) throws Exception{
	
		
		TargetUserRule targetUserRule = loadInternalTargetUserRule(TargetUserRuleTable.withId(targetUserRuleId), emptyOptions());
		targetUserRule.clearFromAll();
		this.saveTargetUserRule(targetUserRule);
		return targetUserRule;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return TargetUserRuleTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "target_user_rule";
	}
	@Override
	protected String getBeanName() {
		
		return "targetUserRule";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return TargetUserRuleTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractApplyConditionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TargetUserRuleTokens.APPLYCONDITION);
 	}

 	protected boolean isSaveApplyConditionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TargetUserRuleTokens.APPLYCONDITION);
 	}
 	

 	
  

 	protected boolean isExtractPromotionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TargetUserRuleTokens.PROMOTION);
 	}

 	protected boolean isSavePromotionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TargetUserRuleTokens.PROMOTION);
 	}
 	

 	
 
		

	

	protected TargetUserRuleMapper getTargetUserRuleMapper(){
		return new TargetUserRuleMapper();
	}

	
	
	protected TargetUserRule extractTargetUserRule(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			TargetUserRule targetUserRule = loadSingleObject(accessKey, getTargetUserRuleMapper());
			return targetUserRule;
		}catch(EmptyResultDataAccessException e){
			throw new TargetUserRuleNotFoundException("TargetUserRule("+accessKey+") is not found!");
		}

	}

	
	

	protected TargetUserRule loadInternalTargetUserRule(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		TargetUserRule targetUserRule = extractTargetUserRule(accessKey, loadOptions);
 	
 		if(isExtractApplyConditionEnabled(loadOptions)){
	 		extractApplyCondition(targetUserRule, loadOptions);
 		}
  	
 		if(isExtractPromotionEnabled(loadOptions)){
	 		extractPromotion(targetUserRule, loadOptions);
 		}
 
		
		return targetUserRule;
		
	}

	 

 	protected TargetUserRule extractApplyCondition(TargetUserRule targetUserRule, Map<String,Object> options) throws Exception{

		if(targetUserRule.getApplyCondition() == null){
			return targetUserRule;
		}
		String applyConditionId = targetUserRule.getApplyCondition().getId();
		if( applyConditionId == null){
			return targetUserRule;
		}
		ApplyCondition applyCondition = getApplyConditionDAO().load(applyConditionId,options);
		if(applyCondition != null){
			targetUserRule.setApplyCondition(applyCondition);
		}
		
 		
 		return targetUserRule;
 	}
 		
  

 	protected TargetUserRule extractPromotion(TargetUserRule targetUserRule, Map<String,Object> options) throws Exception{

		if(targetUserRule.getPromotion() == null){
			return targetUserRule;
		}
		String promotionId = targetUserRule.getPromotion().getId();
		if( promotionId == null){
			return targetUserRule;
		}
		Promotion promotion = getPromotionDAO().load(promotionId,options);
		if(promotion != null){
			targetUserRule.setPromotion(promotion);
		}
		
 		
 		return targetUserRule;
 	}
 		
 
		
		
  	
 	public SmartList<TargetUserRule> findTargetUserRuleByApplyCondition(String applyConditionId,Map<String,Object> options){
 	
  		SmartList<TargetUserRule> resultList = queryWith(TargetUserRuleTable.COLUMN_APPLY_CONDITION, applyConditionId, options, getTargetUserRuleMapper());
		// analyzeTargetUserRuleByApplyCondition(resultList, applyConditionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<TargetUserRule> findTargetUserRuleByApplyCondition(String applyConditionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<TargetUserRule> resultList =  queryWithRange(TargetUserRuleTable.COLUMN_APPLY_CONDITION, applyConditionId, options, getTargetUserRuleMapper(), start, count);
 		//analyzeTargetUserRuleByApplyCondition(resultList, applyConditionId, options);
 		return resultList;
 		
 	}
 	public void analyzeTargetUserRuleByApplyCondition(SmartList<TargetUserRule> resultList, String applyConditionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyConditionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//TargetUserRule.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Target User Rule");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(TargetUserRule.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(TargetUserRule.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTargetUserRuleByApplyCondition(String applyConditionId,Map<String,Object> options){

 		return countWith(TargetUserRuleTable.COLUMN_APPLY_CONDITION, applyConditionId, options);
 	}
 	@Override
	public Map<String, Integer> countTargetUserRuleByApplyConditionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TargetUserRuleTable.COLUMN_APPLY_CONDITION, ids, options);
	}
 	
  	
 	public SmartList<TargetUserRule> findTargetUserRuleByPromotion(String promotionId,Map<String,Object> options){
 	
  		SmartList<TargetUserRule> resultList = queryWith(TargetUserRuleTable.COLUMN_PROMOTION, promotionId, options, getTargetUserRuleMapper());
		// analyzeTargetUserRuleByPromotion(resultList, promotionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<TargetUserRule> findTargetUserRuleByPromotion(String promotionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<TargetUserRule> resultList =  queryWithRange(TargetUserRuleTable.COLUMN_PROMOTION, promotionId, options, getTargetUserRuleMapper(), start, count);
 		//analyzeTargetUserRuleByPromotion(resultList, promotionId, options);
 		return resultList;
 		
 	}
 	public void analyzeTargetUserRuleByPromotion(SmartList<TargetUserRule> resultList, String promotionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(TargetUserRule.PROMOTION_PROPERTY, promotionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//TargetUserRule.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Target User Rule");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(TargetUserRule.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(TargetUserRule.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTargetUserRuleByPromotion(String promotionId,Map<String,Object> options){

 		return countWith(TargetUserRuleTable.COLUMN_PROMOTION, promotionId, options);
 	}
 	@Override
	public Map<String, Integer> countTargetUserRuleByPromotionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TargetUserRuleTable.COLUMN_PROMOTION, ids, options);
	}
 	
 	
		
		
		

	

	protected TargetUserRule saveTargetUserRule(TargetUserRule  targetUserRule){
		
		if(!targetUserRule.isChanged()){
			return targetUserRule;
		}
		
		
		String SQL=this.getSaveTargetUserRuleSQL(targetUserRule);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveTargetUserRuleParameters(targetUserRule);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		targetUserRule.incVersion();
		return targetUserRule;
	
	}
	public SmartList<TargetUserRule> saveTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitTargetUserRuleList(targetUserRuleList);
		
		batchTargetUserRuleCreate((List<TargetUserRule>)lists[CREATE_LIST_INDEX]);
		
		batchTargetUserRuleUpdate((List<TargetUserRule>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(TargetUserRule targetUserRule:targetUserRuleList){
			if(targetUserRule.isChanged()){
				targetUserRule.incVersion();
			}
			
		
		}
		
		
		return targetUserRuleList;
	}

	public SmartList<TargetUserRule> removeTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options){
		
		
		super.removeList(targetUserRuleList, options);
		
		return targetUserRuleList;
		
		
	}
	
	protected List<Object[]> prepareTargetUserRuleBatchCreateArgs(List<TargetUserRule> targetUserRuleList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(TargetUserRule targetUserRule:targetUserRuleList ){
			Object [] parameters = prepareTargetUserRuleCreateParameters(targetUserRule);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareTargetUserRuleBatchUpdateArgs(List<TargetUserRule> targetUserRuleList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(TargetUserRule targetUserRule:targetUserRuleList ){
			if(!targetUserRule.isChanged()){
				continue;
			}
			Object [] parameters = prepareTargetUserRuleUpdateParameters(targetUserRule);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchTargetUserRuleCreate(List<TargetUserRule> targetUserRuleList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareTargetUserRuleBatchCreateArgs(targetUserRuleList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchTargetUserRuleUpdate(List<TargetUserRule> targetUserRuleList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareTargetUserRuleBatchUpdateArgs(targetUserRuleList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitTargetUserRuleList(List<TargetUserRule> targetUserRuleList){
		
		List<TargetUserRule> targetUserRuleCreateList=new ArrayList<TargetUserRule>();
		List<TargetUserRule> targetUserRuleUpdateList=new ArrayList<TargetUserRule>();
		
		for(TargetUserRule targetUserRule: targetUserRuleList){
			if(isUpdateRequest(targetUserRule)){
				targetUserRuleUpdateList.add( targetUserRule);
				continue;
			}
			targetUserRuleCreateList.add(targetUserRule);
		}
		
		return new Object[]{targetUserRuleCreateList,targetUserRuleUpdateList};
	}
	
	protected boolean isUpdateRequest(TargetUserRule targetUserRule){
 		return targetUserRule.getVersion() > 0;
 	}
 	protected String getSaveTargetUserRuleSQL(TargetUserRule targetUserRule){
 		if(isUpdateRequest(targetUserRule)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveTargetUserRuleParameters(TargetUserRule targetUserRule){
 		if(isUpdateRequest(targetUserRule) ){
 			return prepareTargetUserRuleUpdateParameters(targetUserRule);
 		}
 		return prepareTargetUserRuleCreateParameters(targetUserRule);
 	}
 	protected Object[] prepareTargetUserRuleUpdateParameters(TargetUserRule targetUserRule){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = targetUserRule.getName(); 	
 		if(targetUserRule.getApplyCondition() != null){
 			parameters[1] = targetUserRule.getApplyCondition().getId();
 		}
 
 		parameters[2] = targetUserRule.getParameter();
 		parameters[3] = targetUserRule.getLastUpdateTime(); 	
 		if(targetUserRule.getPromotion() != null){
 			parameters[4] = targetUserRule.getPromotion().getId();
 		}
 		
 		parameters[5] = targetUserRule.nextVersion();
 		parameters[6] = targetUserRule.getId();
 		parameters[7] = targetUserRule.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareTargetUserRuleCreateParameters(TargetUserRule targetUserRule){
		Object[] parameters = new Object[6];
		String newTargetUserRuleId=getNextId();
		targetUserRule.setId(newTargetUserRuleId);
		parameters[0] =  targetUserRule.getId();
 
 		parameters[1] = targetUserRule.getName(); 	
 		if(targetUserRule.getApplyCondition() != null){
 			parameters[2] = targetUserRule.getApplyCondition().getId();
 		
 		}
 		
 		parameters[3] = targetUserRule.getParameter();
 		parameters[4] = targetUserRule.getLastUpdateTime(); 	
 		if(targetUserRule.getPromotion() != null){
 			parameters[5] = targetUserRule.getPromotion().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected TargetUserRule saveInternalTargetUserRule(TargetUserRule targetUserRule, Map<String,Object> options){
		
		saveTargetUserRule(targetUserRule);
 	
 		if(isSaveApplyConditionEnabled(options)){
	 		saveApplyCondition(targetUserRule, options);
 		}
  	
 		if(isSavePromotionEnabled(options)){
	 		savePromotion(targetUserRule, options);
 		}
 
		
		return targetUserRule;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected TargetUserRule saveApplyCondition(TargetUserRule targetUserRule, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(targetUserRule.getApplyCondition() == null){
 			return targetUserRule;//do nothing when it is null
 		}
 		
 		getApplyConditionDAO().save(targetUserRule.getApplyCondition(),options);
 		return targetUserRule;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected TargetUserRule savePromotion(TargetUserRule targetUserRule, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(targetUserRule.getPromotion() == null){
 			return targetUserRule;//do nothing when it is null
 		}
 		
 		getPromotionDAO().save(targetUserRule.getPromotion(),options);
 		return targetUserRule;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public TargetUserRule present(TargetUserRule targetUserRule,Map<String, Object> options){
	

		return targetUserRule;
	
	}
		

	

	protected String getTableName(){
		return TargetUserRuleTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<TargetUserRule> targetUserRuleList) {		
		this.enhanceListInternal(targetUserRuleList, this.getTargetUserRuleMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<TargetUserRule> targetUserRuleList = ownerEntity.collectRefsWithType(TargetUserRule.INTERNAL_TYPE);
		this.enhanceList(targetUserRuleList);
		
	}
	
	@Override
	public SmartList<TargetUserRule> findTargetUserRuleWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getTargetUserRuleMapper());

	}
	@Override
	public int countTargetUserRuleWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countTargetUserRuleWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<TargetUserRule> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getTargetUserRuleMapper());
	}
}


