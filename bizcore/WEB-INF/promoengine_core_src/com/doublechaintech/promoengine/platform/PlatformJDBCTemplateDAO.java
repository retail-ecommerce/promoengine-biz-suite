
package com.doublechaintech.promoengine.platform;

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


import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;

import com.doublechaintech.promoengine.applycondition.ApplyConditionDAO;
import com.doublechaintech.promoengine.actiontype.ActionTypeDAO;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevelDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PlatformJDBCTemplateDAO extends PromoengineNamingServiceDAO implements PlatformDAO{


			
		
	
  	private  ApplyConditionDAO  applyConditionDAO;
 	public void setApplyConditionDAO(ApplyConditionDAO pApplyConditionDAO){
 	
 		if(pApplyConditionDAO == null){
 			throw new IllegalStateException("Do not try to set applyConditionDAO to null.");
 		}
	 	this.applyConditionDAO = pApplyConditionDAO;
 	}
 	public ApplyConditionDAO getApplyConditionDAO(){
 		if(this.applyConditionDAO == null){
 			throw new IllegalStateException("The applyConditionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.applyConditionDAO;
 	}	
 	
			
		
	
  	private  ActionTypeDAO  actionTypeDAO;
 	public void setActionTypeDAO(ActionTypeDAO pActionTypeDAO){
 	
 		if(pActionTypeDAO == null){
 			throw new IllegalStateException("Do not try to set actionTypeDAO to null.");
 		}
	 	this.actionTypeDAO = pActionTypeDAO;
 	}
 	public ActionTypeDAO getActionTypeDAO(){
 		if(this.actionTypeDAO == null){
 			throw new IllegalStateException("The actionTypeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.actionTypeDAO;
 	}	
 	
			
		
	
  	private  PromotionLevelDAO  promotionLevelDAO;
 	public void setPromotionLevelDAO(PromotionLevelDAO pPromotionLevelDAO){
 	
 		if(pPromotionLevelDAO == null){
 			throw new IllegalStateException("Do not try to set promotionLevelDAO to null.");
 		}
	 	this.promotionLevelDAO = pPromotionLevelDAO;
 	}
 	public PromotionLevelDAO getPromotionLevelDAO(){
 		if(this.promotionLevelDAO == null){
 			throw new IllegalStateException("The promotionLevelDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.promotionLevelDAO;
 	}	
 	
			
		
	
  	private  PromotionDAO  promotionDAO;
 	public void setPromotionDAO(PromotionDAO pPromotionDAO){
 	
 		if(pPromotionDAO == null){
 			throw new IllegalStateException("Do not try to set promotionDAO to null.");
 		}
	 	this.promotionDAO = pPromotionDAO;
 	}
 	public PromotionDAO getPromotionDAO(){
 		if(this.promotionDAO == null){
 			throw new IllegalStateException("The promotionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.promotionDAO;
 	}	
 	
			
		

	
	/*
	protected Platform load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Platform load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(PlatformTable.withId(id), options);
	}
	
	
	
	public Platform save(Platform platform,Map<String,Object> options){
		
		String methodName="save(Platform platform,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(platform, methodName, "platform");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPlatform(platform,options);
	}
	public Platform clone(String platformId, Map<String,Object> options) throws Exception{
	
		return clone(PlatformTable.withId(platformId),options);
	}
	
	protected Platform clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String platformId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Platform newPlatform = loadInternalPlatform(accessKey, options);
		newPlatform.setVersion(0);
		
		
 		
 		if(isSaveApplyConditionListEnabled(options)){
 			for(ApplyCondition item: newPlatform.getApplyConditionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveActionTypeListEnabled(options)){
 			for(ActionType item: newPlatform.getActionTypeList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePromotionLevelListEnabled(options)){
 			for(PromotionLevel item: newPlatform.getPromotionLevelList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePromotionListEnabled(options)){
 			for(Promotion item: newPlatform.getPromotionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPlatform(newPlatform,options);
		
		return newPlatform;
	}
	
	
	
	

	protected void throwIfHasException(String platformId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PlatformVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PlatformNotFoundException(
					"The " + this.getTableName() + "(" + platformId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String platformId, int version) throws Exception{
	
		String methodName="delete(String platformId, int version)";
		assertMethodArgumentNotNull(platformId, methodName, "platformId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{platformId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(platformId,version);
		}
		
	
	}
	
	
	
	
	

	public Platform disconnectFromAll(String platformId, int version) throws Exception{
	
		
		Platform platform = loadInternalPlatform(PlatformTable.withId(platformId), emptyOptions());
		platform.clearFromAll();
		this.savePlatform(platform);
		return platform;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PlatformTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "platform";
	}
	@Override
	protected String getBeanName() {
		
		return "platform";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PlatformTokens.checkOptions(options, optionToCheck);
	
	}


		
	
	protected boolean isExtractApplyConditionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.APPLY_CONDITION_LIST);
 	}
 	protected boolean isAnalyzeApplyConditionListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeApplyConditionListEnabled();
 	}
	
	protected boolean isSaveApplyConditionListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.APPLY_CONDITION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractActionTypeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.ACTION_TYPE_LIST);
 	}
 	protected boolean isAnalyzeActionTypeListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeActionTypeListEnabled();
 	}
	
	protected boolean isSaveActionTypeListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.ACTION_TYPE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPromotionLevelListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PROMOTION_LEVEL_LIST);
 	}
 	protected boolean isAnalyzePromotionLevelListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzePromotionLevelListEnabled();
 	}
	
	protected boolean isSavePromotionLevelListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PROMOTION_LEVEL_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPromotionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PROMOTION_LIST);
 	}
 	protected boolean isAnalyzePromotionListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzePromotionListEnabled();
 	}
	
	protected boolean isSavePromotionListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PROMOTION_LIST);
		
 	}
 	
		

	

	protected PlatformMapper getPlatformMapper(){
		return new PlatformMapper();
	}

	
	
	protected Platform extractPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Platform platform = loadSingleObject(accessKey, getPlatformMapper());
			return platform;
		}catch(EmptyResultDataAccessException e){
			throw new PlatformNotFoundException("Platform("+accessKey+") is not found!");
		}

	}

	
	

	protected Platform loadInternalPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Platform platform = extractPlatform(accessKey, loadOptions);

		
		if(isExtractApplyConditionListEnabled(loadOptions)){
	 		extractApplyConditionList(platform, loadOptions);
 		}	
 		if(isAnalyzeApplyConditionListEnabled(loadOptions)){
	 		analyzeApplyConditionList(platform, loadOptions);
 		}
 		
		
		if(isExtractActionTypeListEnabled(loadOptions)){
	 		extractActionTypeList(platform, loadOptions);
 		}	
 		if(isAnalyzeActionTypeListEnabled(loadOptions)){
	 		analyzeActionTypeList(platform, loadOptions);
 		}
 		
		
		if(isExtractPromotionLevelListEnabled(loadOptions)){
	 		extractPromotionLevelList(platform, loadOptions);
 		}	
 		if(isAnalyzePromotionLevelListEnabled(loadOptions)){
	 		analyzePromotionLevelList(platform, loadOptions);
 		}
 		
		
		if(isExtractPromotionListEnabled(loadOptions)){
	 		extractPromotionList(platform, loadOptions);
 		}	
 		if(isAnalyzePromotionListEnabled(loadOptions)){
	 		analyzePromotionList(platform, loadOptions);
 		}
 		
		
		return platform;
		
	}

	
		
	protected void enhanceApplyConditionList(SmartList<ApplyCondition> applyConditionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractApplyConditionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ApplyCondition> applyConditionList = getApplyConditionDAO().findApplyConditionByPlatform(platform.getId(),options);
		if(applyConditionList != null){
			enhanceApplyConditionList(applyConditionList,options);
			platform.setApplyConditionList(applyConditionList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeApplyConditionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ApplyCondition> applyConditionList = platform.getApplyConditionList();
		if(applyConditionList != null){
			getApplyConditionDAO().analyzeApplyConditionByPlatform(applyConditionList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceActionTypeList(SmartList<ActionType> actionTypeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractActionTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ActionType> actionTypeList = getActionTypeDAO().findActionTypeByPlatform(platform.getId(),options);
		if(actionTypeList != null){
			enhanceActionTypeList(actionTypeList,options);
			platform.setActionTypeList(actionTypeList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeActionTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ActionType> actionTypeList = platform.getActionTypeList();
		if(actionTypeList != null){
			getActionTypeDAO().analyzeActionTypeByPlatform(actionTypeList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhancePromotionLevelList(SmartList<PromotionLevel> promotionLevelList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractPromotionLevelList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<PromotionLevel> promotionLevelList = getPromotionLevelDAO().findPromotionLevelByPlatform(platform.getId(),options);
		if(promotionLevelList != null){
			enhancePromotionLevelList(promotionLevelList,options);
			platform.setPromotionLevelList(promotionLevelList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzePromotionLevelList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<PromotionLevel> promotionLevelList = platform.getPromotionLevelList();
		if(promotionLevelList != null){
			getPromotionLevelDAO().analyzePromotionLevelByPlatform(promotionLevelList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhancePromotionList(SmartList<Promotion> promotionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractPromotionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Promotion> promotionList = getPromotionDAO().findPromotionByPlatform(platform.getId(),options);
		if(promotionList != null){
			enhancePromotionList(promotionList,options);
			platform.setPromotionList(promotionList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzePromotionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Promotion> promotionList = platform.getPromotionList();
		if(promotionList != null){
			getPromotionDAO().analyzePromotionByPlatform(promotionList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
		
 	
		
		
		

	

	protected Platform savePlatform(Platform  platform){
		
		if(!platform.isChanged()){
			return platform;
		}
		
		
		String SQL=this.getSavePlatformSQL(platform);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePlatformParameters(platform);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		platform.incVersion();
		return platform;
	
	}
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPlatformList(platformList);
		
		batchPlatformCreate((List<Platform>)lists[CREATE_LIST_INDEX]);
		
		batchPlatformUpdate((List<Platform>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Platform platform:platformList){
			if(platform.isChanged()){
				platform.incVersion();
			}
			
		
		}
		
		
		return platformList;
	}

	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		
		
		super.removeList(platformList, options);
		
		return platformList;
		
		
	}
	
	protected List<Object[]> preparePlatformBatchCreateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			Object [] parameters = preparePlatformCreateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePlatformBatchUpdateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			if(!platform.isChanged()){
				continue;
			}
			Object [] parameters = preparePlatformUpdateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPlatformCreate(List<Platform> platformList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePlatformBatchCreateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPlatformUpdate(List<Platform> platformList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePlatformBatchUpdateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPlatformList(List<Platform> platformList){
		
		List<Platform> platformCreateList=new ArrayList<Platform>();
		List<Platform> platformUpdateList=new ArrayList<Platform>();
		
		for(Platform platform: platformList){
			if(isUpdateRequest(platform)){
				platformUpdateList.add( platform);
				continue;
			}
			platformCreateList.add(platform);
		}
		
		return new Object[]{platformCreateList,platformUpdateList};
	}
	
	protected boolean isUpdateRequest(Platform platform){
 		return platform.getVersion() > 0;
 	}
 	protected String getSavePlatformSQL(Platform platform){
 		if(isUpdateRequest(platform)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePlatformParameters(Platform platform){
 		if(isUpdateRequest(platform) ){
 			return preparePlatformUpdateParameters(platform);
 		}
 		return preparePlatformCreateParameters(platform);
 	}
 	protected Object[] preparePlatformUpdateParameters(Platform platform){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = platform.getName();
 		parameters[1] = platform.getIntroduction();
 		parameters[2] = platform.getCurrentVersion();		
 		parameters[3] = platform.nextVersion();
 		parameters[4] = platform.getId();
 		parameters[5] = platform.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePlatformCreateParameters(Platform platform){
		Object[] parameters = new Object[4];
		String newPlatformId=getNextId();
		platform.setId(newPlatformId);
		parameters[0] =  platform.getId();
 
 		parameters[1] = platform.getName();
 		parameters[2] = platform.getIntroduction();
 		parameters[3] = platform.getCurrentVersion();		
 				
 		return parameters;
 	}
 	
	protected Platform saveInternalPlatform(Platform platform, Map<String,Object> options){
		
		savePlatform(platform);

		
		if(isSaveApplyConditionListEnabled(options)){
	 		saveApplyConditionList(platform, options);
	 		//removeApplyConditionList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveActionTypeListEnabled(options)){
	 		saveActionTypeList(platform, options);
	 		//removeActionTypeList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePromotionLevelListEnabled(options)){
	 		savePromotionLevelList(platform, options);
	 		//removePromotionLevelList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePromotionListEnabled(options)){
	 		savePromotionList(platform, options);
	 		//removePromotionList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		return platform;
		
	}
	
	
	
	//======================================================================================
	

	
	public Platform planToRemoveApplyConditionList(Platform platform, String applyConditionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ApplyCondition.PLATFORM_PROPERTY, platform.getId());
		key.put(ApplyCondition.ID_PROPERTY, applyConditionIds);
		
		SmartList<ApplyCondition> externalApplyConditionList = getApplyConditionDAO().
				findApplyConditionWithKey(key, options);
		if(externalApplyConditionList == null){
			return platform;
		}
		if(externalApplyConditionList.isEmpty()){
			return platform;
		}
		
		for(ApplyCondition applyCondition: externalApplyConditionList){

			applyCondition.clearFromAll();
		}
		
		
		SmartList<ApplyCondition> applyConditionList = platform.getApplyConditionList();		
		applyConditionList.addAllToRemoveList(externalApplyConditionList);
		return platform;	
	
	}


	public Platform planToRemoveActionTypeList(Platform platform, String actionTypeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ActionType.PLATFORM_PROPERTY, platform.getId());
		key.put(ActionType.ID_PROPERTY, actionTypeIds);
		
		SmartList<ActionType> externalActionTypeList = getActionTypeDAO().
				findActionTypeWithKey(key, options);
		if(externalActionTypeList == null){
			return platform;
		}
		if(externalActionTypeList.isEmpty()){
			return platform;
		}
		
		for(ActionType actionType: externalActionTypeList){

			actionType.clearFromAll();
		}
		
		
		SmartList<ActionType> actionTypeList = platform.getActionTypeList();		
		actionTypeList.addAllToRemoveList(externalActionTypeList);
		return platform;	
	
	}


	public Platform planToRemovePromotionLevelList(Platform platform, String promotionLevelIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionLevel.PLATFORM_PROPERTY, platform.getId());
		key.put(PromotionLevel.ID_PROPERTY, promotionLevelIds);
		
		SmartList<PromotionLevel> externalPromotionLevelList = getPromotionLevelDAO().
				findPromotionLevelWithKey(key, options);
		if(externalPromotionLevelList == null){
			return platform;
		}
		if(externalPromotionLevelList.isEmpty()){
			return platform;
		}
		
		for(PromotionLevel promotionLevel: externalPromotionLevelList){

			promotionLevel.clearFromAll();
		}
		
		
		SmartList<PromotionLevel> promotionLevelList = platform.getPromotionLevelList();		
		promotionLevelList.addAllToRemoveList(externalPromotionLevelList);
		return platform;	
	
	}


	public Platform planToRemovePromotionList(Platform platform, String promotionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Promotion.PLATFORM_PROPERTY, platform.getId());
		key.put(Promotion.ID_PROPERTY, promotionIds);
		
		SmartList<Promotion> externalPromotionList = getPromotionDAO().
				findPromotionWithKey(key, options);
		if(externalPromotionList == null){
			return platform;
		}
		if(externalPromotionList.isEmpty()){
			return platform;
		}
		
		for(Promotion promotion: externalPromotionList){

			promotion.clearFromAll();
		}
		
		
		SmartList<Promotion> promotionList = platform.getPromotionList();		
		promotionList.addAllToRemoveList(externalPromotionList);
		return platform;	
	
	}



		
	protected Platform saveApplyConditionList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ApplyCondition> applyConditionList = platform.getApplyConditionList();
		if(applyConditionList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ApplyCondition> mergedUpdateApplyConditionList = new SmartList<ApplyCondition>();
		
		
		mergedUpdateApplyConditionList.addAll(applyConditionList); 
		if(applyConditionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateApplyConditionList.addAll(applyConditionList.getToRemoveList());
			applyConditionList.removeAll(applyConditionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getApplyConditionDAO().saveApplyConditionList(mergedUpdateApplyConditionList,options);
		
		if(applyConditionList.getToRemoveList() != null){
			applyConditionList.removeAll(applyConditionList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeApplyConditionList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ApplyCondition> applyConditionList = platform.getApplyConditionList();
		if(applyConditionList == null){
			return platform;
		}	
	
		SmartList<ApplyCondition> toRemoveApplyConditionList = applyConditionList.getToRemoveList();
		
		if(toRemoveApplyConditionList == null){
			return platform;
		}
		if(toRemoveApplyConditionList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getApplyConditionDAO().removeApplyConditionList(toRemoveApplyConditionList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveActionTypeList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ActionType> actionTypeList = platform.getActionTypeList();
		if(actionTypeList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ActionType> mergedUpdateActionTypeList = new SmartList<ActionType>();
		
		
		mergedUpdateActionTypeList.addAll(actionTypeList); 
		if(actionTypeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateActionTypeList.addAll(actionTypeList.getToRemoveList());
			actionTypeList.removeAll(actionTypeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getActionTypeDAO().saveActionTypeList(mergedUpdateActionTypeList,options);
		
		if(actionTypeList.getToRemoveList() != null){
			actionTypeList.removeAll(actionTypeList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeActionTypeList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ActionType> actionTypeList = platform.getActionTypeList();
		if(actionTypeList == null){
			return platform;
		}	
	
		SmartList<ActionType> toRemoveActionTypeList = actionTypeList.getToRemoveList();
		
		if(toRemoveActionTypeList == null){
			return platform;
		}
		if(toRemoveActionTypeList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getActionTypeDAO().removeActionTypeList(toRemoveActionTypeList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform savePromotionLevelList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<PromotionLevel> promotionLevelList = platform.getPromotionLevelList();
		if(promotionLevelList == null){
			//null list means nothing
			return platform;
		}
		SmartList<PromotionLevel> mergedUpdatePromotionLevelList = new SmartList<PromotionLevel>();
		
		
		mergedUpdatePromotionLevelList.addAll(promotionLevelList); 
		if(promotionLevelList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePromotionLevelList.addAll(promotionLevelList.getToRemoveList());
			promotionLevelList.removeAll(promotionLevelList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPromotionLevelDAO().savePromotionLevelList(mergedUpdatePromotionLevelList,options);
		
		if(promotionLevelList.getToRemoveList() != null){
			promotionLevelList.removeAll(promotionLevelList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removePromotionLevelList(Platform platform, Map<String,Object> options){
	
	
		SmartList<PromotionLevel> promotionLevelList = platform.getPromotionLevelList();
		if(promotionLevelList == null){
			return platform;
		}	
	
		SmartList<PromotionLevel> toRemovePromotionLevelList = promotionLevelList.getToRemoveList();
		
		if(toRemovePromotionLevelList == null){
			return platform;
		}
		if(toRemovePromotionLevelList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPromotionLevelDAO().removePromotionLevelList(toRemovePromotionLevelList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform savePromotionList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Promotion> promotionList = platform.getPromotionList();
		if(promotionList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Promotion> mergedUpdatePromotionList = new SmartList<Promotion>();
		
		
		mergedUpdatePromotionList.addAll(promotionList); 
		if(promotionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePromotionList.addAll(promotionList.getToRemoveList());
			promotionList.removeAll(promotionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPromotionDAO().savePromotionList(mergedUpdatePromotionList,options);
		
		if(promotionList.getToRemoveList() != null){
			promotionList.removeAll(promotionList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removePromotionList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Promotion> promotionList = platform.getPromotionList();
		if(promotionList == null){
			return platform;
		}	
	
		SmartList<Promotion> toRemovePromotionList = promotionList.getToRemoveList();
		
		if(toRemovePromotionList == null){
			return platform;
		}
		if(toRemovePromotionList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPromotionDAO().removePromotionList(toRemovePromotionList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		

	public Platform present(Platform platform,Map<String, Object> options){
	
		presentApplyConditionList(platform,options);
		presentActionTypeList(platform,options);
		presentPromotionLevelList(platform,options);
		presentPromotionList(platform,options);

		return platform;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentApplyConditionList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ApplyCondition> applyConditionList = platform.getApplyConditionList();		
				SmartList<ApplyCondition> newList= presentSubList(platform.getId(),
				applyConditionList,
				options,
				getApplyConditionDAO()::countApplyConditionByPlatform,
				getApplyConditionDAO()::findApplyConditionByPlatform
				);

		
		platform.setApplyConditionList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentActionTypeList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ActionType> actionTypeList = platform.getActionTypeList();		
				SmartList<ActionType> newList= presentSubList(platform.getId(),
				actionTypeList,
				options,
				getActionTypeDAO()::countActionTypeByPlatform,
				getActionTypeDAO()::findActionTypeByPlatform
				);

		
		platform.setActionTypeList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentPromotionLevelList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<PromotionLevel> promotionLevelList = platform.getPromotionLevelList();		
				SmartList<PromotionLevel> newList= presentSubList(platform.getId(),
				promotionLevelList,
				options,
				getPromotionLevelDAO()::countPromotionLevelByPlatform,
				getPromotionLevelDAO()::findPromotionLevelByPlatform
				);

		
		platform.setPromotionLevelList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentPromotionList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Promotion> promotionList = platform.getPromotionList();		
				SmartList<Promotion> newList= presentSubList(platform.getId(),
				promotionList,
				options,
				getPromotionDAO()::countPromotionByPlatform,
				getPromotionDAO()::findPromotionByPlatform
				);

		
		platform.setPromotionList(newList);
		

		return platform;
	}			
		

	
    public SmartList<Platform> requestCandidatePlatformForApplyCondition(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForActionType(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForPromotionLevel(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForPromotion(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		

	protected String getTableName(){
		return PlatformTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Platform> platformList) {		
		this.enhanceListInternal(platformList, this.getPlatformMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Platform> platformList = ownerEntity.collectRefsWithType(Platform.INTERNAL_TYPE);
		this.enhanceList(platformList);
		
	}
	
	@Override
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPlatformMapper());

	}
	@Override
	public int countPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Platform> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPlatformMapper());
	}
}


