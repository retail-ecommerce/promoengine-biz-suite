
package com.doublechaintech.promoengine.actiontype;

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
import com.doublechaintech.promoengine.targetaction.TargetAction;

import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetaction.TargetActionDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class ActionTypeJDBCTemplateDAO extends PromoengineNamingServiceDAO implements ActionTypeDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  TargetActionDAO  targetActionDAO;
 	public void setTargetActionDAO(TargetActionDAO pTargetActionDAO){
 	
 		if(pTargetActionDAO == null){
 			throw new IllegalStateException("Do not try to set targetActionDAO to null.");
 		}
	 	this.targetActionDAO = pTargetActionDAO;
 	}
 	public TargetActionDAO getTargetActionDAO(){
 		if(this.targetActionDAO == null){
 			throw new IllegalStateException("The targetActionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.targetActionDAO;
 	}	
 	
			
		

	
	/*
	protected ActionType load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalActionType(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ActionType load(String id,Map<String,Object> options) throws Exception{
		return loadInternalActionType(ActionTypeTable.withId(id), options);
	}
	
	
	
	public ActionType save(ActionType actionType,Map<String,Object> options){
		
		String methodName="save(ActionType actionType,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(actionType, methodName, "actionType");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalActionType(actionType,options);
	}
	public ActionType clone(String actionTypeId, Map<String,Object> options) throws Exception{
	
		return clone(ActionTypeTable.withId(actionTypeId),options);
	}
	
	protected ActionType clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String actionTypeId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ActionType newActionType = loadInternalActionType(accessKey, options);
		newActionType.setVersion(0);
		
		
 		
 		if(isSaveTargetActionListEnabled(options)){
 			for(TargetAction item: newActionType.getTargetActionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalActionType(newActionType,options);
		
		return newActionType;
	}
	
	
	
	

	protected void throwIfHasException(String actionTypeId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ActionTypeVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ActionTypeNotFoundException(
					"The " + this.getTableName() + "(" + actionTypeId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String actionTypeId, int version) throws Exception{
	
		String methodName="delete(String actionTypeId, int version)";
		assertMethodArgumentNotNull(actionTypeId, methodName, "actionTypeId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{actionTypeId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(actionTypeId,version);
		}
		
	
	}
	
	
	
	
	

	public ActionType disconnectFromAll(String actionTypeId, int version) throws Exception{
	
		
		ActionType actionType = loadInternalActionType(ActionTypeTable.withId(actionTypeId), emptyOptions());
		actionType.clearFromAll();
		this.saveActionType(actionType);
		return actionType;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ActionTypeTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "action_type";
	}
	@Override
	protected String getBeanName() {
		
		return "actionType";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ActionTypeTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ActionTypeTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ActionTypeTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTargetActionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ActionTypeTokens.TARGET_ACTION_LIST);
 	}
 	protected boolean isAnalyzeTargetActionListEnabled(Map<String,Object> options){		 		
 		return ActionTypeTokens.of(options).analyzeTargetActionListEnabled();
 	}
	
	protected boolean isSaveTargetActionListEnabled(Map<String,Object> options){
		return checkOptions(options, ActionTypeTokens.TARGET_ACTION_LIST);
		
 	}
 	
		

	

	protected ActionTypeMapper getActionTypeMapper(){
		return new ActionTypeMapper();
	}

	
	
	protected ActionType extractActionType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ActionType actionType = loadSingleObject(accessKey, getActionTypeMapper());
			return actionType;
		}catch(EmptyResultDataAccessException e){
			throw new ActionTypeNotFoundException("ActionType("+accessKey+") is not found!");
		}

	}

	
	

	protected ActionType loadInternalActionType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ActionType actionType = extractActionType(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(actionType, loadOptions);
 		}
 
		
		if(isExtractTargetActionListEnabled(loadOptions)){
	 		extractTargetActionList(actionType, loadOptions);
 		}	
 		if(isAnalyzeTargetActionListEnabled(loadOptions)){
	 		analyzeTargetActionList(actionType, loadOptions);
 		}
 		
		
		return actionType;
		
	}

	 

 	protected ActionType extractPlatform(ActionType actionType, Map<String,Object> options) throws Exception{

		if(actionType.getPlatform() == null){
			return actionType;
		}
		String platformId = actionType.getPlatform().getId();
		if( platformId == null){
			return actionType;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			actionType.setPlatform(platform);
		}
		
 		
 		return actionType;
 	}
 		
 
		
	protected void enhanceTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ActionType extractTargetActionList(ActionType actionType, Map<String,Object> options){
		
		
		if(actionType == null){
			return null;
		}
		if(actionType.getId() == null){
			return actionType;
		}

		
		
		SmartList<TargetAction> targetActionList = getTargetActionDAO().findTargetActionByAction(actionType.getId(),options);
		if(targetActionList != null){
			enhanceTargetActionList(targetActionList,options);
			actionType.setTargetActionList(targetActionList);
		}
		
		return actionType;
	
	}	
	
	protected ActionType analyzeTargetActionList(ActionType actionType, Map<String,Object> options){
		
		
		if(actionType == null){
			return null;
		}
		if(actionType.getId() == null){
			return actionType;
		}

		
		
		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();
		if(targetActionList != null){
			getTargetActionDAO().analyzeTargetActionByAction(targetActionList, actionType.getId(), options);
			
		}
		
		return actionType;
	
	}	
	
		
		
  	
 	public SmartList<ActionType> findActionTypeByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<ActionType> resultList = queryWith(ActionTypeTable.COLUMN_PLATFORM, platformId, options, getActionTypeMapper());
		// analyzeActionTypeByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ActionType> findActionTypeByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ActionType> resultList =  queryWithRange(ActionTypeTable.COLUMN_PLATFORM, platformId, options, getActionTypeMapper(), start, count);
 		//analyzeActionTypeByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeActionTypeByPlatform(SmartList<ActionType> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ActionType.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//ActionType.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Action Type");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(ActionType.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ActionType.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countActionTypeByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ActionTypeTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countActionTypeByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ActionTypeTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected ActionType saveActionType(ActionType  actionType){
		
		if(!actionType.isChanged()){
			return actionType;
		}
		
		
		String SQL=this.getSaveActionTypeSQL(actionType);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveActionTypeParameters(actionType);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		actionType.incVersion();
		return actionType;
	
	}
	public SmartList<ActionType> saveActionTypeList(SmartList<ActionType> actionTypeList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitActionTypeList(actionTypeList);
		
		batchActionTypeCreate((List<ActionType>)lists[CREATE_LIST_INDEX]);
		
		batchActionTypeUpdate((List<ActionType>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ActionType actionType:actionTypeList){
			if(actionType.isChanged()){
				actionType.incVersion();
			}
			
		
		}
		
		
		return actionTypeList;
	}

	public SmartList<ActionType> removeActionTypeList(SmartList<ActionType> actionTypeList,Map<String,Object> options){
		
		
		super.removeList(actionTypeList, options);
		
		return actionTypeList;
		
		
	}
	
	protected List<Object[]> prepareActionTypeBatchCreateArgs(List<ActionType> actionTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ActionType actionType:actionTypeList ){
			Object [] parameters = prepareActionTypeCreateParameters(actionType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareActionTypeBatchUpdateArgs(List<ActionType> actionTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ActionType actionType:actionTypeList ){
			if(!actionType.isChanged()){
				continue;
			}
			Object [] parameters = prepareActionTypeUpdateParameters(actionType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchActionTypeCreate(List<ActionType> actionTypeList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareActionTypeBatchCreateArgs(actionTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchActionTypeUpdate(List<ActionType> actionTypeList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareActionTypeBatchUpdateArgs(actionTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitActionTypeList(List<ActionType> actionTypeList){
		
		List<ActionType> actionTypeCreateList=new ArrayList<ActionType>();
		List<ActionType> actionTypeUpdateList=new ArrayList<ActionType>();
		
		for(ActionType actionType: actionTypeList){
			if(isUpdateRequest(actionType)){
				actionTypeUpdateList.add( actionType);
				continue;
			}
			actionTypeCreateList.add(actionType);
		}
		
		return new Object[]{actionTypeCreateList,actionTypeUpdateList};
	}
	
	protected boolean isUpdateRequest(ActionType actionType){
 		return actionType.getVersion() > 0;
 	}
 	protected String getSaveActionTypeSQL(ActionType actionType){
 		if(isUpdateRequest(actionType)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveActionTypeParameters(ActionType actionType){
 		if(isUpdateRequest(actionType) ){
 			return prepareActionTypeUpdateParameters(actionType);
 		}
 		return prepareActionTypeCreateParameters(actionType);
 	}
 	protected Object[] prepareActionTypeUpdateParameters(ActionType actionType){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = actionType.getName();
 		parameters[1] = actionType.getLastUpdateTime(); 	
 		if(actionType.getPlatform() != null){
 			parameters[2] = actionType.getPlatform().getId();
 		}
 		
 		parameters[3] = actionType.nextVersion();
 		parameters[4] = actionType.getId();
 		parameters[5] = actionType.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareActionTypeCreateParameters(ActionType actionType){
		Object[] parameters = new Object[4];
		String newActionTypeId=getNextId();
		actionType.setId(newActionTypeId);
		parameters[0] =  actionType.getId();
 
 		parameters[1] = actionType.getName();
 		parameters[2] = actionType.getLastUpdateTime(); 	
 		if(actionType.getPlatform() != null){
 			parameters[3] = actionType.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ActionType saveInternalActionType(ActionType actionType, Map<String,Object> options){
		
		saveActionType(actionType);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(actionType, options);
 		}
 
		
		if(isSaveTargetActionListEnabled(options)){
	 		saveTargetActionList(actionType, options);
	 		//removeTargetActionList(actionType, options);
	 		//Not delete the record
	 		
 		}		
		
		return actionType;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ActionType savePlatform(ActionType actionType, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(actionType.getPlatform() == null){
 			return actionType;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(actionType.getPlatform(),options);
 		return actionType;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ActionType planToRemoveTargetActionList(ActionType actionType, String targetActionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.ACTION_PROPERTY, actionType.getId());
		key.put(TargetAction.ID_PROPERTY, targetActionIds);
		
		SmartList<TargetAction> externalTargetActionList = getTargetActionDAO().
				findTargetActionWithKey(key, options);
		if(externalTargetActionList == null){
			return actionType;
		}
		if(externalTargetActionList.isEmpty()){
			return actionType;
		}
		
		for(TargetAction targetAction: externalTargetActionList){

			targetAction.clearFromAll();
		}
		
		
		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();		
		targetActionList.addAllToRemoveList(externalTargetActionList);
		return actionType;	
	
	}


	//disconnect ActionType with promotion in TargetAction
	public ActionType planToRemoveTargetActionListWithPromotion(ActionType actionType, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.ACTION_PROPERTY, actionType.getId());
		key.put(TargetAction.PROMOTION_PROPERTY, promotionId);
		
		SmartList<TargetAction> externalTargetActionList = getTargetActionDAO().
				findTargetActionWithKey(key, options);
		if(externalTargetActionList == null){
			return actionType;
		}
		if(externalTargetActionList.isEmpty()){
			return actionType;
		}
		
		for(TargetAction targetAction: externalTargetActionList){
			targetAction.clearPromotion();
			targetAction.clearAction();
			
		}
		
		
		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();		
		targetActionList.addAllToRemoveList(externalTargetActionList);
		return actionType;
	}
	
	public int countTargetActionListWithPromotion(String actionTypeId, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.ACTION_PROPERTY, actionTypeId);
		key.put(TargetAction.PROMOTION_PROPERTY, promotionId);
		
		int count = getTargetActionDAO().countTargetActionWithKey(key, options);
		return count;
	}
	

		
	protected ActionType saveTargetActionList(ActionType actionType, Map<String,Object> options){
		
		
		
		
		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();
		if(targetActionList == null){
			//null list means nothing
			return actionType;
		}
		SmartList<TargetAction> mergedUpdateTargetActionList = new SmartList<TargetAction>();
		
		
		mergedUpdateTargetActionList.addAll(targetActionList); 
		if(targetActionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTargetActionList.addAll(targetActionList.getToRemoveList());
			targetActionList.removeAll(targetActionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTargetActionDAO().saveTargetActionList(mergedUpdateTargetActionList,options);
		
		if(targetActionList.getToRemoveList() != null){
			targetActionList.removeAll(targetActionList.getToRemoveList());
		}
		
		
		return actionType;
	
	}
	
	protected ActionType removeTargetActionList(ActionType actionType, Map<String,Object> options){
	
	
		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();
		if(targetActionList == null){
			return actionType;
		}	
	
		SmartList<TargetAction> toRemoveTargetActionList = targetActionList.getToRemoveList();
		
		if(toRemoveTargetActionList == null){
			return actionType;
		}
		if(toRemoveTargetActionList.isEmpty()){
			return actionType;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTargetActionDAO().removeTargetActionList(toRemoveTargetActionList,options);
		
		return actionType;
	
	}
	
	

 	
 	
	
	
	
		

	public ActionType present(ActionType actionType,Map<String, Object> options){
	
		presentTargetActionList(actionType,options);

		return actionType;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ActionType presentTargetActionList(
			ActionType actionType,
			Map<String, Object> options) {

		SmartList<TargetAction> targetActionList = actionType.getTargetActionList();		
				SmartList<TargetAction> newList= presentSubList(actionType.getId(),
				targetActionList,
				options,
				getTargetActionDAO()::countTargetActionByAction,
				getTargetActionDAO()::findTargetActionByAction
				);

		
		actionType.setTargetActionList(newList);
		

		return actionType;
	}			
		

	
    public SmartList<ActionType> requestCandidateActionTypeForTargetAction(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ActionTypeTable.COLUMN_NAME, filterKey, pageNo, pageSize, getActionTypeMapper());
    }
		

	protected String getTableName(){
		return ActionTypeTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ActionType> actionTypeList) {		
		this.enhanceListInternal(actionTypeList, this.getActionTypeMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<ActionType> actionTypeList = ownerEntity.collectRefsWithType(ActionType.INTERNAL_TYPE);
		this.enhanceList(actionTypeList);
		
	}
	
	@Override
	public SmartList<ActionType> findActionTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getActionTypeMapper());

	}
	@Override
	public int countActionTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countActionTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ActionType> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getActionTypeMapper());
	}
}


