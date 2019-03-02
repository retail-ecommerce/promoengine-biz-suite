
package com.doublechaintech.promoengine.applycondition;

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


import com.doublechaintech.promoengine.platform.Platform;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;

import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class ApplyConditionJDBCTemplateDAO extends PromoengineNamingServiceDAO implements ApplyConditionDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  TargetUserRuleDAO  targetUserRuleDAO;
 	public void setTargetUserRuleDAO(TargetUserRuleDAO pTargetUserRuleDAO){
 	
 		if(pTargetUserRuleDAO == null){
 			throw new IllegalStateException("Do not try to set targetUserRuleDAO to null.");
 		}
	 	this.targetUserRuleDAO = pTargetUserRuleDAO;
 	}
 	public TargetUserRuleDAO getTargetUserRuleDAO(){
 		if(this.targetUserRuleDAO == null){
 			throw new IllegalStateException("The targetUserRuleDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.targetUserRuleDAO;
 	}	
 	
			
		

	
	/*
	protected ApplyCondition load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalApplyCondition(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ApplyCondition load(String id,Map<String,Object> options) throws Exception{
		return loadInternalApplyCondition(ApplyConditionTable.withId(id), options);
	}
	
	
	
	public ApplyCondition save(ApplyCondition applyCondition,Map<String,Object> options){
		
		String methodName="save(ApplyCondition applyCondition,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(applyCondition, methodName, "applyCondition");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalApplyCondition(applyCondition,options);
	}
	public ApplyCondition clone(String applyConditionId, Map<String,Object> options) throws Exception{
	
		return clone(ApplyConditionTable.withId(applyConditionId),options);
	}
	
	protected ApplyCondition clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String applyConditionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ApplyCondition newApplyCondition = loadInternalApplyCondition(accessKey, options);
		newApplyCondition.setVersion(0);
		
		
 		
 		if(isSaveTargetUserRuleListEnabled(options)){
 			for(TargetUserRule item: newApplyCondition.getTargetUserRuleList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalApplyCondition(newApplyCondition,options);
		
		return newApplyCondition;
	}
	
	
	
	

	protected void throwIfHasException(String applyConditionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ApplyConditionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ApplyConditionNotFoundException(
					"The " + this.getTableName() + "(" + applyConditionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String applyConditionId, int version) throws Exception{
	
		String methodName="delete(String applyConditionId, int version)";
		assertMethodArgumentNotNull(applyConditionId, methodName, "applyConditionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{applyConditionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(applyConditionId,version);
		}
		
	
	}
	
	
	
	
	

	public ApplyCondition disconnectFromAll(String applyConditionId, int version) throws Exception{
	
		
		ApplyCondition applyCondition = loadInternalApplyCondition(ApplyConditionTable.withId(applyConditionId), emptyOptions());
		applyCondition.clearFromAll();
		this.saveApplyCondition(applyCondition);
		return applyCondition;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ApplyConditionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "apply_condition";
	}
	@Override
	protected String getBeanName() {
		
		return "applyCondition";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ApplyConditionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ApplyConditionTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ApplyConditionTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTargetUserRuleListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ApplyConditionTokens.TARGET_USER_RULE_LIST);
 	}
 	protected boolean isAnalyzeTargetUserRuleListEnabled(Map<String,Object> options){		 		
 		return ApplyConditionTokens.of(options).analyzeTargetUserRuleListEnabled();
 	}
	
	protected boolean isSaveTargetUserRuleListEnabled(Map<String,Object> options){
		return checkOptions(options, ApplyConditionTokens.TARGET_USER_RULE_LIST);
		
 	}
 	
		

	

	protected ApplyConditionMapper getApplyConditionMapper(){
		return new ApplyConditionMapper();
	}

	
	
	protected ApplyCondition extractApplyCondition(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ApplyCondition applyCondition = loadSingleObject(accessKey, getApplyConditionMapper());
			return applyCondition;
		}catch(EmptyResultDataAccessException e){
			throw new ApplyConditionNotFoundException("ApplyCondition("+accessKey+") is not found!");
		}

	}

	
	

	protected ApplyCondition loadInternalApplyCondition(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ApplyCondition applyCondition = extractApplyCondition(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(applyCondition, loadOptions);
 		}
 
		
		if(isExtractTargetUserRuleListEnabled(loadOptions)){
	 		extractTargetUserRuleList(applyCondition, loadOptions);
 		}	
 		if(isAnalyzeTargetUserRuleListEnabled(loadOptions)){
	 		analyzeTargetUserRuleList(applyCondition, loadOptions);
 		}
 		
		
		return applyCondition;
		
	}

	 

 	protected ApplyCondition extractPlatform(ApplyCondition applyCondition, Map<String,Object> options) throws Exception{

		if(applyCondition.getPlatform() == null){
			return applyCondition;
		}
		String platformId = applyCondition.getPlatform().getId();
		if( platformId == null){
			return applyCondition;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			applyCondition.setPlatform(platform);
		}
		
 		
 		return applyCondition;
 	}
 		
 
		
	protected void enhanceTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ApplyCondition extractTargetUserRuleList(ApplyCondition applyCondition, Map<String,Object> options){
		
		
		if(applyCondition == null){
			return null;
		}
		if(applyCondition.getId() == null){
			return applyCondition;
		}

		
		
		SmartList<TargetUserRule> targetUserRuleList = getTargetUserRuleDAO().findTargetUserRuleByApplyCondition(applyCondition.getId(),options);
		if(targetUserRuleList != null){
			enhanceTargetUserRuleList(targetUserRuleList,options);
			applyCondition.setTargetUserRuleList(targetUserRuleList);
		}
		
		return applyCondition;
	
	}	
	
	protected ApplyCondition analyzeTargetUserRuleList(ApplyCondition applyCondition, Map<String,Object> options){
		
		
		if(applyCondition == null){
			return null;
		}
		if(applyCondition.getId() == null){
			return applyCondition;
		}

		
		
		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();
		if(targetUserRuleList != null){
			getTargetUserRuleDAO().analyzeTargetUserRuleByApplyCondition(targetUserRuleList, applyCondition.getId(), options);
			
		}
		
		return applyCondition;
	
	}	
	
		
		
  	
 	public SmartList<ApplyCondition> findApplyConditionByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<ApplyCondition> resultList = queryWith(ApplyConditionTable.COLUMN_PLATFORM, platformId, options, getApplyConditionMapper());
		// analyzeApplyConditionByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ApplyCondition> findApplyConditionByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ApplyCondition> resultList =  queryWithRange(ApplyConditionTable.COLUMN_PLATFORM, platformId, options, getApplyConditionMapper(), start, count);
 		//analyzeApplyConditionByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeApplyConditionByPlatform(SmartList<ApplyCondition> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ApplyCondition.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//ApplyCondition.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Apply Condition");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(ApplyCondition.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ApplyCondition.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countApplyConditionByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ApplyConditionTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countApplyConditionByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ApplyConditionTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected ApplyCondition saveApplyCondition(ApplyCondition  applyCondition){
		
		if(!applyCondition.isChanged()){
			return applyCondition;
		}
		
		
		String SQL=this.getSaveApplyConditionSQL(applyCondition);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveApplyConditionParameters(applyCondition);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		applyCondition.incVersion();
		return applyCondition;
	
	}
	public SmartList<ApplyCondition> saveApplyConditionList(SmartList<ApplyCondition> applyConditionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitApplyConditionList(applyConditionList);
		
		batchApplyConditionCreate((List<ApplyCondition>)lists[CREATE_LIST_INDEX]);
		
		batchApplyConditionUpdate((List<ApplyCondition>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ApplyCondition applyCondition:applyConditionList){
			if(applyCondition.isChanged()){
				applyCondition.incVersion();
			}
			
		
		}
		
		
		return applyConditionList;
	}

	public SmartList<ApplyCondition> removeApplyConditionList(SmartList<ApplyCondition> applyConditionList,Map<String,Object> options){
		
		
		super.removeList(applyConditionList, options);
		
		return applyConditionList;
		
		
	}
	
	protected List<Object[]> prepareApplyConditionBatchCreateArgs(List<ApplyCondition> applyConditionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ApplyCondition applyCondition:applyConditionList ){
			Object [] parameters = prepareApplyConditionCreateParameters(applyCondition);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareApplyConditionBatchUpdateArgs(List<ApplyCondition> applyConditionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ApplyCondition applyCondition:applyConditionList ){
			if(!applyCondition.isChanged()){
				continue;
			}
			Object [] parameters = prepareApplyConditionUpdateParameters(applyCondition);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchApplyConditionCreate(List<ApplyCondition> applyConditionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareApplyConditionBatchCreateArgs(applyConditionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchApplyConditionUpdate(List<ApplyCondition> applyConditionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareApplyConditionBatchUpdateArgs(applyConditionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitApplyConditionList(List<ApplyCondition> applyConditionList){
		
		List<ApplyCondition> applyConditionCreateList=new ArrayList<ApplyCondition>();
		List<ApplyCondition> applyConditionUpdateList=new ArrayList<ApplyCondition>();
		
		for(ApplyCondition applyCondition: applyConditionList){
			if(isUpdateRequest(applyCondition)){
				applyConditionUpdateList.add( applyCondition);
				continue;
			}
			applyConditionCreateList.add(applyCondition);
		}
		
		return new Object[]{applyConditionCreateList,applyConditionUpdateList};
	}
	
	protected boolean isUpdateRequest(ApplyCondition applyCondition){
 		return applyCondition.getVersion() > 0;
 	}
 	protected String getSaveApplyConditionSQL(ApplyCondition applyCondition){
 		if(isUpdateRequest(applyCondition)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveApplyConditionParameters(ApplyCondition applyCondition){
 		if(isUpdateRequest(applyCondition) ){
 			return prepareApplyConditionUpdateParameters(applyCondition);
 		}
 		return prepareApplyConditionCreateParameters(applyCondition);
 	}
 	protected Object[] prepareApplyConditionUpdateParameters(ApplyCondition applyCondition){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = applyCondition.getName();
 		parameters[1] = applyCondition.getLastUpdateTime(); 	
 		if(applyCondition.getPlatform() != null){
 			parameters[2] = applyCondition.getPlatform().getId();
 		}
 		
 		parameters[3] = applyCondition.nextVersion();
 		parameters[4] = applyCondition.getId();
 		parameters[5] = applyCondition.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareApplyConditionCreateParameters(ApplyCondition applyCondition){
		Object[] parameters = new Object[4];
		String newApplyConditionId=getNextId();
		applyCondition.setId(newApplyConditionId);
		parameters[0] =  applyCondition.getId();
 
 		parameters[1] = applyCondition.getName();
 		parameters[2] = applyCondition.getLastUpdateTime(); 	
 		if(applyCondition.getPlatform() != null){
 			parameters[3] = applyCondition.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ApplyCondition saveInternalApplyCondition(ApplyCondition applyCondition, Map<String,Object> options){
		
		saveApplyCondition(applyCondition);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(applyCondition, options);
 		}
 
		
		if(isSaveTargetUserRuleListEnabled(options)){
	 		saveTargetUserRuleList(applyCondition, options);
	 		//removeTargetUserRuleList(applyCondition, options);
	 		//Not delete the record
	 		
 		}		
		
		return applyCondition;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ApplyCondition savePlatform(ApplyCondition applyCondition, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(applyCondition.getPlatform() == null){
 			return applyCondition;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(applyCondition.getPlatform(),options);
 		return applyCondition;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ApplyCondition planToRemoveTargetUserRuleList(ApplyCondition applyCondition, String targetUserRuleIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyCondition.getId());
		key.put(TargetUserRule.ID_PROPERTY, targetUserRuleIds);
		
		SmartList<TargetUserRule> externalTargetUserRuleList = getTargetUserRuleDAO().
				findTargetUserRuleWithKey(key, options);
		if(externalTargetUserRuleList == null){
			return applyCondition;
		}
		if(externalTargetUserRuleList.isEmpty()){
			return applyCondition;
		}
		
		for(TargetUserRule targetUserRule: externalTargetUserRuleList){

			targetUserRule.clearFromAll();
		}
		
		
		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();		
		targetUserRuleList.addAllToRemoveList(externalTargetUserRuleList);
		return applyCondition;	
	
	}


	//disconnect ApplyCondition with promotion in TargetUserRule
	public ApplyCondition planToRemoveTargetUserRuleListWithPromotion(ApplyCondition applyCondition, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyCondition.getId());
		key.put(TargetUserRule.PROMOTION_PROPERTY, promotionId);
		
		SmartList<TargetUserRule> externalTargetUserRuleList = getTargetUserRuleDAO().
				findTargetUserRuleWithKey(key, options);
		if(externalTargetUserRuleList == null){
			return applyCondition;
		}
		if(externalTargetUserRuleList.isEmpty()){
			return applyCondition;
		}
		
		for(TargetUserRule targetUserRule: externalTargetUserRuleList){
			targetUserRule.clearPromotion();
			targetUserRule.clearApplyCondition();
			
		}
		
		
		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();		
		targetUserRuleList.addAllToRemoveList(externalTargetUserRuleList);
		return applyCondition;
	}
	
	public int countTargetUserRuleListWithPromotion(String applyConditionId, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyConditionId);
		key.put(TargetUserRule.PROMOTION_PROPERTY, promotionId);
		
		int count = getTargetUserRuleDAO().countTargetUserRuleWithKey(key, options);
		return count;
	}
	

		
	protected ApplyCondition saveTargetUserRuleList(ApplyCondition applyCondition, Map<String,Object> options){
		
		
		
		
		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();
		if(targetUserRuleList == null){
			//null list means nothing
			return applyCondition;
		}
		SmartList<TargetUserRule> mergedUpdateTargetUserRuleList = new SmartList<TargetUserRule>();
		
		
		mergedUpdateTargetUserRuleList.addAll(targetUserRuleList); 
		if(targetUserRuleList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTargetUserRuleList.addAll(targetUserRuleList.getToRemoveList());
			targetUserRuleList.removeAll(targetUserRuleList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTargetUserRuleDAO().saveTargetUserRuleList(mergedUpdateTargetUserRuleList,options);
		
		if(targetUserRuleList.getToRemoveList() != null){
			targetUserRuleList.removeAll(targetUserRuleList.getToRemoveList());
		}
		
		
		return applyCondition;
	
	}
	
	protected ApplyCondition removeTargetUserRuleList(ApplyCondition applyCondition, Map<String,Object> options){
	
	
		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();
		if(targetUserRuleList == null){
			return applyCondition;
		}	
	
		SmartList<TargetUserRule> toRemoveTargetUserRuleList = targetUserRuleList.getToRemoveList();
		
		if(toRemoveTargetUserRuleList == null){
			return applyCondition;
		}
		if(toRemoveTargetUserRuleList.isEmpty()){
			return applyCondition;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTargetUserRuleDAO().removeTargetUserRuleList(toRemoveTargetUserRuleList,options);
		
		return applyCondition;
	
	}
	
	

 	
 	
	
	
	
		

	public ApplyCondition present(ApplyCondition applyCondition,Map<String, Object> options){
	
		presentTargetUserRuleList(applyCondition,options);

		return applyCondition;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ApplyCondition presentTargetUserRuleList(
			ApplyCondition applyCondition,
			Map<String, Object> options) {

		SmartList<TargetUserRule> targetUserRuleList = applyCondition.getTargetUserRuleList();		
				SmartList<TargetUserRule> newList= presentSubList(applyCondition.getId(),
				targetUserRuleList,
				options,
				getTargetUserRuleDAO()::countTargetUserRuleByApplyCondition,
				getTargetUserRuleDAO()::findTargetUserRuleByApplyCondition
				);

		
		applyCondition.setTargetUserRuleList(newList);
		

		return applyCondition;
	}			
		

	
    public SmartList<ApplyCondition> requestCandidateApplyConditionForTargetUserRule(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ApplyConditionTable.COLUMN_NAME, filterKey, pageNo, pageSize, getApplyConditionMapper());
    }
		

	protected String getTableName(){
		return ApplyConditionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ApplyCondition> applyConditionList) {		
		this.enhanceListInternal(applyConditionList, this.getApplyConditionMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<ApplyCondition> applyConditionList = ownerEntity.collectRefsWithType(ApplyCondition.INTERNAL_TYPE);
		this.enhanceList(applyConditionList);
		
	}
	
	@Override
	public SmartList<ApplyCondition> findApplyConditionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getApplyConditionMapper());

	}
	@Override
	public int countApplyConditionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countApplyConditionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ApplyCondition> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getApplyConditionMapper());
	}
}


