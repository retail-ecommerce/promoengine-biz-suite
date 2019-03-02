
package com.doublechaintech.promoengine.targetaction;

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


import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;

import com.doublechaintech.promoengine.actiontype.ActionTypeDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class TargetActionJDBCTemplateDAO extends PromoengineNamingServiceDAO implements TargetActionDAO{
 
 	
 	private  ActionTypeDAO  actionTypeDAO;
 	public void setActionTypeDAO(ActionTypeDAO actionTypeDAO){
	 	this.actionTypeDAO = actionTypeDAO;
 	}
 	public ActionTypeDAO getActionTypeDAO(){
	 	return this.actionTypeDAO;
 	}
 
 	
 	private  PromotionDAO  promotionDAO;
 	public void setPromotionDAO(PromotionDAO promotionDAO){
	 	this.promotionDAO = promotionDAO;
 	}
 	public PromotionDAO getPromotionDAO(){
	 	return this.promotionDAO;
 	}


			
		

	
	/*
	protected TargetAction load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalTargetAction(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public TargetAction load(String id,Map<String,Object> options) throws Exception{
		return loadInternalTargetAction(TargetActionTable.withId(id), options);
	}
	
	
	
	public TargetAction save(TargetAction targetAction,Map<String,Object> options){
		
		String methodName="save(TargetAction targetAction,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(targetAction, methodName, "targetAction");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalTargetAction(targetAction,options);
	}
	public TargetAction clone(String targetActionId, Map<String,Object> options) throws Exception{
	
		return clone(TargetActionTable.withId(targetActionId),options);
	}
	
	protected TargetAction clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String targetActionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		TargetAction newTargetAction = loadInternalTargetAction(accessKey, options);
		newTargetAction.setVersion(0);
		
		

		
		saveInternalTargetAction(newTargetAction,options);
		
		return newTargetAction;
	}
	
	
	
	

	protected void throwIfHasException(String targetActionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new TargetActionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new TargetActionNotFoundException(
					"The " + this.getTableName() + "(" + targetActionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String targetActionId, int version) throws Exception{
	
		String methodName="delete(String targetActionId, int version)";
		assertMethodArgumentNotNull(targetActionId, methodName, "targetActionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{targetActionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(targetActionId,version);
		}
		
	
	}
	
	
	
	
	

	public TargetAction disconnectFromAll(String targetActionId, int version) throws Exception{
	
		
		TargetAction targetAction = loadInternalTargetAction(TargetActionTable.withId(targetActionId), emptyOptions());
		targetAction.clearFromAll();
		this.saveTargetAction(targetAction);
		return targetAction;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return TargetActionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "target_action";
	}
	@Override
	protected String getBeanName() {
		
		return "targetAction";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return TargetActionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractActionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TargetActionTokens.ACTION);
 	}

 	protected boolean isSaveActionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TargetActionTokens.ACTION);
 	}
 	

 	
  

 	protected boolean isExtractPromotionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TargetActionTokens.PROMOTION);
 	}

 	protected boolean isSavePromotionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TargetActionTokens.PROMOTION);
 	}
 	

 	
 
		

	

	protected TargetActionMapper getTargetActionMapper(){
		return new TargetActionMapper();
	}

	
	
	protected TargetAction extractTargetAction(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			TargetAction targetAction = loadSingleObject(accessKey, getTargetActionMapper());
			return targetAction;
		}catch(EmptyResultDataAccessException e){
			throw new TargetActionNotFoundException("TargetAction("+accessKey+") is not found!");
		}

	}

	
	

	protected TargetAction loadInternalTargetAction(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		TargetAction targetAction = extractTargetAction(accessKey, loadOptions);
 	
 		if(isExtractActionEnabled(loadOptions)){
	 		extractAction(targetAction, loadOptions);
 		}
  	
 		if(isExtractPromotionEnabled(loadOptions)){
	 		extractPromotion(targetAction, loadOptions);
 		}
 
		
		return targetAction;
		
	}

	 

 	protected TargetAction extractAction(TargetAction targetAction, Map<String,Object> options) throws Exception{

		if(targetAction.getAction() == null){
			return targetAction;
		}
		String actionId = targetAction.getAction().getId();
		if( actionId == null){
			return targetAction;
		}
		ActionType action = getActionTypeDAO().load(actionId,options);
		if(action != null){
			targetAction.setAction(action);
		}
		
 		
 		return targetAction;
 	}
 		
  

 	protected TargetAction extractPromotion(TargetAction targetAction, Map<String,Object> options) throws Exception{

		if(targetAction.getPromotion() == null){
			return targetAction;
		}
		String promotionId = targetAction.getPromotion().getId();
		if( promotionId == null){
			return targetAction;
		}
		Promotion promotion = getPromotionDAO().load(promotionId,options);
		if(promotion != null){
			targetAction.setPromotion(promotion);
		}
		
 		
 		return targetAction;
 	}
 		
 
		
		
  	
 	public SmartList<TargetAction> findTargetActionByAction(String actionTypeId,Map<String,Object> options){
 	
  		SmartList<TargetAction> resultList = queryWith(TargetActionTable.COLUMN_ACTION, actionTypeId, options, getTargetActionMapper());
		// analyzeTargetActionByAction(resultList, actionTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<TargetAction> findTargetActionByAction(String actionTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<TargetAction> resultList =  queryWithRange(TargetActionTable.COLUMN_ACTION, actionTypeId, options, getTargetActionMapper(), start, count);
 		//analyzeTargetActionByAction(resultList, actionTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeTargetActionByAction(SmartList<TargetAction> resultList, String actionTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(TargetAction.ACTION_PROPERTY, actionTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//TargetAction.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Target Action");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(TargetAction.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(TargetAction.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTargetActionByAction(String actionTypeId,Map<String,Object> options){

 		return countWith(TargetActionTable.COLUMN_ACTION, actionTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countTargetActionByActionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TargetActionTable.COLUMN_ACTION, ids, options);
	}
 	
  	
 	public SmartList<TargetAction> findTargetActionByPromotion(String promotionId,Map<String,Object> options){
 	
  		SmartList<TargetAction> resultList = queryWith(TargetActionTable.COLUMN_PROMOTION, promotionId, options, getTargetActionMapper());
		// analyzeTargetActionByPromotion(resultList, promotionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<TargetAction> findTargetActionByPromotion(String promotionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<TargetAction> resultList =  queryWithRange(TargetActionTable.COLUMN_PROMOTION, promotionId, options, getTargetActionMapper(), start, count);
 		//analyzeTargetActionByPromotion(resultList, promotionId, options);
 		return resultList;
 		
 	}
 	public void analyzeTargetActionByPromotion(SmartList<TargetAction> resultList, String promotionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(TargetAction.PROMOTION_PROPERTY, promotionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//TargetAction.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Target Action");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(TargetAction.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(TargetAction.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTargetActionByPromotion(String promotionId,Map<String,Object> options){

 		return countWith(TargetActionTable.COLUMN_PROMOTION, promotionId, options);
 	}
 	@Override
	public Map<String, Integer> countTargetActionByPromotionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TargetActionTable.COLUMN_PROMOTION, ids, options);
	}
 	
 	
		
		
		

	

	protected TargetAction saveTargetAction(TargetAction  targetAction){
		
		if(!targetAction.isChanged()){
			return targetAction;
		}
		
		
		String SQL=this.getSaveTargetActionSQL(targetAction);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveTargetActionParameters(targetAction);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		targetAction.incVersion();
		return targetAction;
	
	}
	public SmartList<TargetAction> saveTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitTargetActionList(targetActionList);
		
		batchTargetActionCreate((List<TargetAction>)lists[CREATE_LIST_INDEX]);
		
		batchTargetActionUpdate((List<TargetAction>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(TargetAction targetAction:targetActionList){
			if(targetAction.isChanged()){
				targetAction.incVersion();
			}
			
		
		}
		
		
		return targetActionList;
	}

	public SmartList<TargetAction> removeTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options){
		
		
		super.removeList(targetActionList, options);
		
		return targetActionList;
		
		
	}
	
	protected List<Object[]> prepareTargetActionBatchCreateArgs(List<TargetAction> targetActionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(TargetAction targetAction:targetActionList ){
			Object [] parameters = prepareTargetActionCreateParameters(targetAction);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareTargetActionBatchUpdateArgs(List<TargetAction> targetActionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(TargetAction targetAction:targetActionList ){
			if(!targetAction.isChanged()){
				continue;
			}
			Object [] parameters = prepareTargetActionUpdateParameters(targetAction);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchTargetActionCreate(List<TargetAction> targetActionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareTargetActionBatchCreateArgs(targetActionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchTargetActionUpdate(List<TargetAction> targetActionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareTargetActionBatchUpdateArgs(targetActionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitTargetActionList(List<TargetAction> targetActionList){
		
		List<TargetAction> targetActionCreateList=new ArrayList<TargetAction>();
		List<TargetAction> targetActionUpdateList=new ArrayList<TargetAction>();
		
		for(TargetAction targetAction: targetActionList){
			if(isUpdateRequest(targetAction)){
				targetActionUpdateList.add( targetAction);
				continue;
			}
			targetActionCreateList.add(targetAction);
		}
		
		return new Object[]{targetActionCreateList,targetActionUpdateList};
	}
	
	protected boolean isUpdateRequest(TargetAction targetAction){
 		return targetAction.getVersion() > 0;
 	}
 	protected String getSaveTargetActionSQL(TargetAction targetAction){
 		if(isUpdateRequest(targetAction)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveTargetActionParameters(TargetAction targetAction){
 		if(isUpdateRequest(targetAction) ){
 			return prepareTargetActionUpdateParameters(targetAction);
 		}
 		return prepareTargetActionCreateParameters(targetAction);
 	}
 	protected Object[] prepareTargetActionUpdateParameters(TargetAction targetAction){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = targetAction.getName(); 	
 		if(targetAction.getAction() != null){
 			parameters[1] = targetAction.getAction().getId();
 		}
 
 		parameters[2] = targetAction.getParameter();
 		parameters[3] = targetAction.getLastUpdateTime(); 	
 		if(targetAction.getPromotion() != null){
 			parameters[4] = targetAction.getPromotion().getId();
 		}
 		
 		parameters[5] = targetAction.nextVersion();
 		parameters[6] = targetAction.getId();
 		parameters[7] = targetAction.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareTargetActionCreateParameters(TargetAction targetAction){
		Object[] parameters = new Object[6];
		String newTargetActionId=getNextId();
		targetAction.setId(newTargetActionId);
		parameters[0] =  targetAction.getId();
 
 		parameters[1] = targetAction.getName(); 	
 		if(targetAction.getAction() != null){
 			parameters[2] = targetAction.getAction().getId();
 		
 		}
 		
 		parameters[3] = targetAction.getParameter();
 		parameters[4] = targetAction.getLastUpdateTime(); 	
 		if(targetAction.getPromotion() != null){
 			parameters[5] = targetAction.getPromotion().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected TargetAction saveInternalTargetAction(TargetAction targetAction, Map<String,Object> options){
		
		saveTargetAction(targetAction);
 	
 		if(isSaveActionEnabled(options)){
	 		saveAction(targetAction, options);
 		}
  	
 		if(isSavePromotionEnabled(options)){
	 		savePromotion(targetAction, options);
 		}
 
		
		return targetAction;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected TargetAction saveAction(TargetAction targetAction, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(targetAction.getAction() == null){
 			return targetAction;//do nothing when it is null
 		}
 		
 		getActionTypeDAO().save(targetAction.getAction(),options);
 		return targetAction;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected TargetAction savePromotion(TargetAction targetAction, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(targetAction.getPromotion() == null){
 			return targetAction;//do nothing when it is null
 		}
 		
 		getPromotionDAO().save(targetAction.getPromotion(),options);
 		return targetAction;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public TargetAction present(TargetAction targetAction,Map<String, Object> options){
	

		return targetAction;
	
	}
		

	

	protected String getTableName(){
		return TargetActionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<TargetAction> targetActionList) {		
		this.enhanceListInternal(targetActionList, this.getTargetActionMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<TargetAction> targetActionList = ownerEntity.collectRefsWithType(TargetAction.INTERNAL_TYPE);
		this.enhanceList(targetActionList);
		
	}
	
	@Override
	public SmartList<TargetAction> findTargetActionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getTargetActionMapper());

	}
	@Override
	public int countTargetActionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countTargetActionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<TargetAction> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getTargetActionMapper());
	}
}


