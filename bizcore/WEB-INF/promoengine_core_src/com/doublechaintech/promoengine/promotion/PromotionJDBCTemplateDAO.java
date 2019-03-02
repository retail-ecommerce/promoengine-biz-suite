
package com.doublechaintech.promoengine.promotion;

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
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.targetaction.TargetActionDAO;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleDAO;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PromotionJDBCTemplateDAO extends PromoengineNamingServiceDAO implements PromotionDAO{
 
 	
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
 	
			
		
	
  	private  PromotionOfferDAO  promotionOfferDAO;
 	public void setPromotionOfferDAO(PromotionOfferDAO pPromotionOfferDAO){
 	
 		if(pPromotionOfferDAO == null){
 			throw new IllegalStateException("Do not try to set promotionOfferDAO to null.");
 		}
	 	this.promotionOfferDAO = pPromotionOfferDAO;
 	}
 	public PromotionOfferDAO getPromotionOfferDAO(){
 		if(this.promotionOfferDAO == null){
 			throw new IllegalStateException("The promotionOfferDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.promotionOfferDAO;
 	}	
 	
			
		

	
	/*
	protected Promotion load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPromotion(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Promotion load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPromotion(PromotionTable.withId(id), options);
	}
	
	
	
	public Promotion save(Promotion promotion,Map<String,Object> options){
		
		String methodName="save(Promotion promotion,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(promotion, methodName, "promotion");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPromotion(promotion,options);
	}
	public Promotion clone(String promotionId, Map<String,Object> options) throws Exception{
	
		return clone(PromotionTable.withId(promotionId),options);
	}
	
	protected Promotion clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String promotionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Promotion newPromotion = loadInternalPromotion(accessKey, options);
		newPromotion.setVersion(0);
		
		
 		
 		if(isSaveTargetUserRuleListEnabled(options)){
 			for(TargetUserRule item: newPromotion.getTargetUserRuleList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveTargetActionListEnabled(options)){
 			for(TargetAction item: newPromotion.getTargetActionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePromotionOfferListEnabled(options)){
 			for(PromotionOffer item: newPromotion.getPromotionOfferList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPromotion(newPromotion,options);
		
		return newPromotion;
	}
	
	
	
	

	protected void throwIfHasException(String promotionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PromotionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PromotionNotFoundException(
					"The " + this.getTableName() + "(" + promotionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String promotionId, int version) throws Exception{
	
		String methodName="delete(String promotionId, int version)";
		assertMethodArgumentNotNull(promotionId, methodName, "promotionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{promotionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(promotionId,version);
		}
		
	
	}
	
	
	
	
	

	public Promotion disconnectFromAll(String promotionId, int version) throws Exception{
	
		
		Promotion promotion = loadInternalPromotion(PromotionTable.withId(promotionId), emptyOptions());
		promotion.clearFromAll();
		this.savePromotion(promotion);
		return promotion;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PromotionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "promotion";
	}
	@Override
	protected String getBeanName() {
		
		return "promotion";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PromotionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PromotionTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PromotionTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTargetUserRuleListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PromotionTokens.TARGET_USER_RULE_LIST);
 	}
 	protected boolean isAnalyzeTargetUserRuleListEnabled(Map<String,Object> options){		 		
 		return PromotionTokens.of(options).analyzeTargetUserRuleListEnabled();
 	}
	
	protected boolean isSaveTargetUserRuleListEnabled(Map<String,Object> options){
		return checkOptions(options, PromotionTokens.TARGET_USER_RULE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractTargetActionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PromotionTokens.TARGET_ACTION_LIST);
 	}
 	protected boolean isAnalyzeTargetActionListEnabled(Map<String,Object> options){		 		
 		return PromotionTokens.of(options).analyzeTargetActionListEnabled();
 	}
	
	protected boolean isSaveTargetActionListEnabled(Map<String,Object> options){
		return checkOptions(options, PromotionTokens.TARGET_ACTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPromotionOfferListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PromotionTokens.PROMOTION_OFFER_LIST);
 	}
 	protected boolean isAnalyzePromotionOfferListEnabled(Map<String,Object> options){		 		
 		return PromotionTokens.of(options).analyzePromotionOfferListEnabled();
 	}
	
	protected boolean isSavePromotionOfferListEnabled(Map<String,Object> options){
		return checkOptions(options, PromotionTokens.PROMOTION_OFFER_LIST);
		
 	}
 	
		

	

	protected PromotionMapper getPromotionMapper(){
		return new PromotionMapper();
	}

	
	
	protected Promotion extractPromotion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Promotion promotion = loadSingleObject(accessKey, getPromotionMapper());
			return promotion;
		}catch(EmptyResultDataAccessException e){
			throw new PromotionNotFoundException("Promotion("+accessKey+") is not found!");
		}

	}

	
	

	protected Promotion loadInternalPromotion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Promotion promotion = extractPromotion(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(promotion, loadOptions);
 		}
 
		
		if(isExtractTargetUserRuleListEnabled(loadOptions)){
	 		extractTargetUserRuleList(promotion, loadOptions);
 		}	
 		if(isAnalyzeTargetUserRuleListEnabled(loadOptions)){
	 		analyzeTargetUserRuleList(promotion, loadOptions);
 		}
 		
		
		if(isExtractTargetActionListEnabled(loadOptions)){
	 		extractTargetActionList(promotion, loadOptions);
 		}	
 		if(isAnalyzeTargetActionListEnabled(loadOptions)){
	 		analyzeTargetActionList(promotion, loadOptions);
 		}
 		
		
		if(isExtractPromotionOfferListEnabled(loadOptions)){
	 		extractPromotionOfferList(promotion, loadOptions);
 		}	
 		if(isAnalyzePromotionOfferListEnabled(loadOptions)){
	 		analyzePromotionOfferList(promotion, loadOptions);
 		}
 		
		
		return promotion;
		
	}

	 

 	protected Promotion extractPlatform(Promotion promotion, Map<String,Object> options) throws Exception{

		if(promotion.getPlatform() == null){
			return promotion;
		}
		String platformId = promotion.getPlatform().getId();
		if( platformId == null){
			return promotion;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			promotion.setPlatform(platform);
		}
		
 		
 		return promotion;
 	}
 		
 
		
	protected void enhanceTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Promotion extractTargetUserRuleList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<TargetUserRule> targetUserRuleList = getTargetUserRuleDAO().findTargetUserRuleByPromotion(promotion.getId(),options);
		if(targetUserRuleList != null){
			enhanceTargetUserRuleList(targetUserRuleList,options);
			promotion.setTargetUserRuleList(targetUserRuleList);
		}
		
		return promotion;
	
	}	
	
	protected Promotion analyzeTargetUserRuleList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();
		if(targetUserRuleList != null){
			getTargetUserRuleDAO().analyzeTargetUserRuleByPromotion(targetUserRuleList, promotion.getId(), options);
			
		}
		
		return promotion;
	
	}	
	
		
	protected void enhanceTargetActionList(SmartList<TargetAction> targetActionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Promotion extractTargetActionList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<TargetAction> targetActionList = getTargetActionDAO().findTargetActionByPromotion(promotion.getId(),options);
		if(targetActionList != null){
			enhanceTargetActionList(targetActionList,options);
			promotion.setTargetActionList(targetActionList);
		}
		
		return promotion;
	
	}	
	
	protected Promotion analyzeTargetActionList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();
		if(targetActionList != null){
			getTargetActionDAO().analyzeTargetActionByPromotion(targetActionList, promotion.getId(), options);
			
		}
		
		return promotion;
	
	}	
	
		
	protected void enhancePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Promotion extractPromotionOfferList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<PromotionOffer> promotionOfferList = getPromotionOfferDAO().findPromotionOfferByPromotion(promotion.getId(),options);
		if(promotionOfferList != null){
			enhancePromotionOfferList(promotionOfferList,options);
			promotion.setPromotionOfferList(promotionOfferList);
		}
		
		return promotion;
	
	}	
	
	protected Promotion analyzePromotionOfferList(Promotion promotion, Map<String,Object> options){
		
		
		if(promotion == null){
			return null;
		}
		if(promotion.getId() == null){
			return promotion;
		}

		
		
		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();
		if(promotionOfferList != null){
			getPromotionOfferDAO().analyzePromotionOfferByPromotion(promotionOfferList, promotion.getId(), options);
			
		}
		
		return promotion;
	
	}	
	
		
		
  	
 	public SmartList<Promotion> findPromotionByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Promotion> resultList = queryWith(PromotionTable.COLUMN_PLATFORM, platformId, options, getPromotionMapper());
		// analyzePromotionByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Promotion> findPromotionByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Promotion> resultList =  queryWithRange(PromotionTable.COLUMN_PLATFORM, platformId, options, getPromotionMapper(), start, count);
 		//analyzePromotionByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzePromotionByPlatform(SmartList<Promotion> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Promotion.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem validAfterStatsItem = new StatsItem();
		//Promotion.VALID_AFTER_PROPERTY
		validAfterStatsItem.setDisplayName("Promotion");
		validAfterStatsItem.setInternalName(formatKeyForDateLine(Promotion.VALID_AFTER_PROPERTY));
		validAfterStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Promotion.VALID_AFTER_PROPERTY),filterKey,emptyOptions));
		info.addItem(validAfterStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPromotionByPlatform(String platformId,Map<String,Object> options){

 		return countWith(PromotionTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countPromotionByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PromotionTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Promotion savePromotion(Promotion  promotion){
		
		if(!promotion.isChanged()){
			return promotion;
		}
		
		
		String SQL=this.getSavePromotionSQL(promotion);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePromotionParameters(promotion);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		promotion.incVersion();
		return promotion;
	
	}
	public SmartList<Promotion> savePromotionList(SmartList<Promotion> promotionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPromotionList(promotionList);
		
		batchPromotionCreate((List<Promotion>)lists[CREATE_LIST_INDEX]);
		
		batchPromotionUpdate((List<Promotion>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Promotion promotion:promotionList){
			if(promotion.isChanged()){
				promotion.incVersion();
			}
			
		
		}
		
		
		return promotionList;
	}

	public SmartList<Promotion> removePromotionList(SmartList<Promotion> promotionList,Map<String,Object> options){
		
		
		super.removeList(promotionList, options);
		
		return promotionList;
		
		
	}
	
	protected List<Object[]> preparePromotionBatchCreateArgs(List<Promotion> promotionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Promotion promotion:promotionList ){
			Object [] parameters = preparePromotionCreateParameters(promotion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePromotionBatchUpdateArgs(List<Promotion> promotionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Promotion promotion:promotionList ){
			if(!promotion.isChanged()){
				continue;
			}
			Object [] parameters = preparePromotionUpdateParameters(promotion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPromotionCreate(List<Promotion> promotionList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePromotionBatchCreateArgs(promotionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPromotionUpdate(List<Promotion> promotionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePromotionBatchUpdateArgs(promotionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPromotionList(List<Promotion> promotionList){
		
		List<Promotion> promotionCreateList=new ArrayList<Promotion>();
		List<Promotion> promotionUpdateList=new ArrayList<Promotion>();
		
		for(Promotion promotion: promotionList){
			if(isUpdateRequest(promotion)){
				promotionUpdateList.add( promotion);
				continue;
			}
			promotionCreateList.add(promotion);
		}
		
		return new Object[]{promotionCreateList,promotionUpdateList};
	}
	
	protected boolean isUpdateRequest(Promotion promotion){
 		return promotion.getVersion() > 0;
 	}
 	protected String getSavePromotionSQL(Promotion promotion){
 		if(isUpdateRequest(promotion)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePromotionParameters(Promotion promotion){
 		if(isUpdateRequest(promotion) ){
 			return preparePromotionUpdateParameters(promotion);
 		}
 		return preparePromotionCreateParameters(promotion);
 	}
 	protected Object[] preparePromotionUpdateParameters(Promotion promotion){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = promotion.getName();
 		parameters[1] = promotion.getValidAfter();
 		parameters[2] = promotion.getExpireTime();
 		parameters[3] = promotion.getLastUpdateTime(); 	
 		if(promotion.getPlatform() != null){
 			parameters[4] = promotion.getPlatform().getId();
 		}
 		
 		parameters[5] = promotion.nextVersion();
 		parameters[6] = promotion.getId();
 		parameters[7] = promotion.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePromotionCreateParameters(Promotion promotion){
		Object[] parameters = new Object[6];
		String newPromotionId=getNextId();
		promotion.setId(newPromotionId);
		parameters[0] =  promotion.getId();
 
 		parameters[1] = promotion.getName();
 		parameters[2] = promotion.getValidAfter();
 		parameters[3] = promotion.getExpireTime();
 		parameters[4] = promotion.getLastUpdateTime(); 	
 		if(promotion.getPlatform() != null){
 			parameters[5] = promotion.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Promotion saveInternalPromotion(Promotion promotion, Map<String,Object> options){
		
		savePromotion(promotion);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(promotion, options);
 		}
 
		
		if(isSaveTargetUserRuleListEnabled(options)){
	 		saveTargetUserRuleList(promotion, options);
	 		//removeTargetUserRuleList(promotion, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveTargetActionListEnabled(options)){
	 		saveTargetActionList(promotion, options);
	 		//removeTargetActionList(promotion, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePromotionOfferListEnabled(options)){
	 		savePromotionOfferList(promotion, options);
	 		//removePromotionOfferList(promotion, options);
	 		//Not delete the record
	 		
 		}		
		
		return promotion;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Promotion savePlatform(Promotion promotion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(promotion.getPlatform() == null){
 			return promotion;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(promotion.getPlatform(),options);
 		return promotion;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Promotion planToRemoveTargetUserRuleList(Promotion promotion, String targetUserRuleIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.PROMOTION_PROPERTY, promotion.getId());
		key.put(TargetUserRule.ID_PROPERTY, targetUserRuleIds);
		
		SmartList<TargetUserRule> externalTargetUserRuleList = getTargetUserRuleDAO().
				findTargetUserRuleWithKey(key, options);
		if(externalTargetUserRuleList == null){
			return promotion;
		}
		if(externalTargetUserRuleList.isEmpty()){
			return promotion;
		}
		
		for(TargetUserRule targetUserRule: externalTargetUserRuleList){

			targetUserRule.clearFromAll();
		}
		
		
		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();		
		targetUserRuleList.addAllToRemoveList(externalTargetUserRuleList);
		return promotion;	
	
	}


	//disconnect Promotion with apply_condition in TargetUserRule
	public Promotion planToRemoveTargetUserRuleListWithApplyCondition(Promotion promotion, String applyConditionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.PROMOTION_PROPERTY, promotion.getId());
		key.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyConditionId);
		
		SmartList<TargetUserRule> externalTargetUserRuleList = getTargetUserRuleDAO().
				findTargetUserRuleWithKey(key, options);
		if(externalTargetUserRuleList == null){
			return promotion;
		}
		if(externalTargetUserRuleList.isEmpty()){
			return promotion;
		}
		
		for(TargetUserRule targetUserRule: externalTargetUserRuleList){
			targetUserRule.clearApplyCondition();
			targetUserRule.clearPromotion();
			
		}
		
		
		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();		
		targetUserRuleList.addAllToRemoveList(externalTargetUserRuleList);
		return promotion;
	}
	
	public int countTargetUserRuleListWithApplyCondition(String promotionId, String applyConditionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetUserRule.PROMOTION_PROPERTY, promotionId);
		key.put(TargetUserRule.APPLY_CONDITION_PROPERTY, applyConditionId);
		
		int count = getTargetUserRuleDAO().countTargetUserRuleWithKey(key, options);
		return count;
	}
	
	public Promotion planToRemoveTargetActionList(Promotion promotion, String targetActionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.PROMOTION_PROPERTY, promotion.getId());
		key.put(TargetAction.ID_PROPERTY, targetActionIds);
		
		SmartList<TargetAction> externalTargetActionList = getTargetActionDAO().
				findTargetActionWithKey(key, options);
		if(externalTargetActionList == null){
			return promotion;
		}
		if(externalTargetActionList.isEmpty()){
			return promotion;
		}
		
		for(TargetAction targetAction: externalTargetActionList){

			targetAction.clearFromAll();
		}
		
		
		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();		
		targetActionList.addAllToRemoveList(externalTargetActionList);
		return promotion;	
	
	}


	//disconnect Promotion with action in TargetAction
	public Promotion planToRemoveTargetActionListWithAction(Promotion promotion, String actionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.PROMOTION_PROPERTY, promotion.getId());
		key.put(TargetAction.ACTION_PROPERTY, actionId);
		
		SmartList<TargetAction> externalTargetActionList = getTargetActionDAO().
				findTargetActionWithKey(key, options);
		if(externalTargetActionList == null){
			return promotion;
		}
		if(externalTargetActionList.isEmpty()){
			return promotion;
		}
		
		for(TargetAction targetAction: externalTargetActionList){
			targetAction.clearAction();
			targetAction.clearPromotion();
			
		}
		
		
		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();		
		targetActionList.addAllToRemoveList(externalTargetActionList);
		return promotion;
	}
	
	public int countTargetActionListWithAction(String promotionId, String actionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(TargetAction.PROMOTION_PROPERTY, promotionId);
		key.put(TargetAction.ACTION_PROPERTY, actionId);
		
		int count = getTargetActionDAO().countTargetActionWithKey(key, options);
		return count;
	}
	
	public Promotion planToRemovePromotionOfferList(Promotion promotion, String promotionOfferIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_PROPERTY, promotion.getId());
		key.put(PromotionOffer.ID_PROPERTY, promotionOfferIds);
		
		SmartList<PromotionOffer> externalPromotionOfferList = getPromotionOfferDAO().
				findPromotionOfferWithKey(key, options);
		if(externalPromotionOfferList == null){
			return promotion;
		}
		if(externalPromotionOfferList.isEmpty()){
			return promotion;
		}
		
		for(PromotionOffer promotionOffer: externalPromotionOfferList){

			promotionOffer.clearFromAll();
		}
		
		
		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();		
		promotionOfferList.addAllToRemoveList(externalPromotionOfferList);
		return promotion;	
	
	}


	//disconnect Promotion with promotion_level in PromotionOffer
	public Promotion planToRemovePromotionOfferListWithPromotionLevel(Promotion promotion, String promotionLevelId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_PROPERTY, promotion.getId());
		key.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevelId);
		
		SmartList<PromotionOffer> externalPromotionOfferList = getPromotionOfferDAO().
				findPromotionOfferWithKey(key, options);
		if(externalPromotionOfferList == null){
			return promotion;
		}
		if(externalPromotionOfferList.isEmpty()){
			return promotion;
		}
		
		for(PromotionOffer promotionOffer: externalPromotionOfferList){
			promotionOffer.clearPromotionLevel();
			promotionOffer.clearPromotion();
			
		}
		
		
		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();		
		promotionOfferList.addAllToRemoveList(externalPromotionOfferList);
		return promotion;
	}
	
	public int countPromotionOfferListWithPromotionLevel(String promotionId, String promotionLevelId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_PROPERTY, promotionId);
		key.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevelId);
		
		int count = getPromotionOfferDAO().countPromotionOfferWithKey(key, options);
		return count;
	}
	

		
	protected Promotion saveTargetUserRuleList(Promotion promotion, Map<String,Object> options){
		
		
		
		
		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();
		if(targetUserRuleList == null){
			//null list means nothing
			return promotion;
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
		
		
		return promotion;
	
	}
	
	protected Promotion removeTargetUserRuleList(Promotion promotion, Map<String,Object> options){
	
	
		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();
		if(targetUserRuleList == null){
			return promotion;
		}	
	
		SmartList<TargetUserRule> toRemoveTargetUserRuleList = targetUserRuleList.getToRemoveList();
		
		if(toRemoveTargetUserRuleList == null){
			return promotion;
		}
		if(toRemoveTargetUserRuleList.isEmpty()){
			return promotion;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTargetUserRuleDAO().removeTargetUserRuleList(toRemoveTargetUserRuleList,options);
		
		return promotion;
	
	}
	
	

 	
 	
	
	
	
		
	protected Promotion saveTargetActionList(Promotion promotion, Map<String,Object> options){
		
		
		
		
		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();
		if(targetActionList == null){
			//null list means nothing
			return promotion;
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
		
		
		return promotion;
	
	}
	
	protected Promotion removeTargetActionList(Promotion promotion, Map<String,Object> options){
	
	
		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();
		if(targetActionList == null){
			return promotion;
		}	
	
		SmartList<TargetAction> toRemoveTargetActionList = targetActionList.getToRemoveList();
		
		if(toRemoveTargetActionList == null){
			return promotion;
		}
		if(toRemoveTargetActionList.isEmpty()){
			return promotion;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTargetActionDAO().removeTargetActionList(toRemoveTargetActionList,options);
		
		return promotion;
	
	}
	
	

 	
 	
	
	
	
		
	protected Promotion savePromotionOfferList(Promotion promotion, Map<String,Object> options){
		
		
		
		
		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();
		if(promotionOfferList == null){
			//null list means nothing
			return promotion;
		}
		SmartList<PromotionOffer> mergedUpdatePromotionOfferList = new SmartList<PromotionOffer>();
		
		
		mergedUpdatePromotionOfferList.addAll(promotionOfferList); 
		if(promotionOfferList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePromotionOfferList.addAll(promotionOfferList.getToRemoveList());
			promotionOfferList.removeAll(promotionOfferList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPromotionOfferDAO().savePromotionOfferList(mergedUpdatePromotionOfferList,options);
		
		if(promotionOfferList.getToRemoveList() != null){
			promotionOfferList.removeAll(promotionOfferList.getToRemoveList());
		}
		
		
		return promotion;
	
	}
	
	protected Promotion removePromotionOfferList(Promotion promotion, Map<String,Object> options){
	
	
		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();
		if(promotionOfferList == null){
			return promotion;
		}	
	
		SmartList<PromotionOffer> toRemovePromotionOfferList = promotionOfferList.getToRemoveList();
		
		if(toRemovePromotionOfferList == null){
			return promotion;
		}
		if(toRemovePromotionOfferList.isEmpty()){
			return promotion;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPromotionOfferDAO().removePromotionOfferList(toRemovePromotionOfferList,options);
		
		return promotion;
	
	}
	
	

 	
 	
	
	
	
		

	public Promotion present(Promotion promotion,Map<String, Object> options){
	
		presentTargetUserRuleList(promotion,options);
		presentTargetActionList(promotion,options);
		presentPromotionOfferList(promotion,options);

		return promotion;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Promotion presentTargetUserRuleList(
			Promotion promotion,
			Map<String, Object> options) {

		SmartList<TargetUserRule> targetUserRuleList = promotion.getTargetUserRuleList();		
				SmartList<TargetUserRule> newList= presentSubList(promotion.getId(),
				targetUserRuleList,
				options,
				getTargetUserRuleDAO()::countTargetUserRuleByPromotion,
				getTargetUserRuleDAO()::findTargetUserRuleByPromotion
				);

		
		promotion.setTargetUserRuleList(newList);
		

		return promotion;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Promotion presentTargetActionList(
			Promotion promotion,
			Map<String, Object> options) {

		SmartList<TargetAction> targetActionList = promotion.getTargetActionList();		
				SmartList<TargetAction> newList= presentSubList(promotion.getId(),
				targetActionList,
				options,
				getTargetActionDAO()::countTargetActionByPromotion,
				getTargetActionDAO()::findTargetActionByPromotion
				);

		
		promotion.setTargetActionList(newList);
		

		return promotion;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Promotion presentPromotionOfferList(
			Promotion promotion,
			Map<String, Object> options) {

		SmartList<PromotionOffer> promotionOfferList = promotion.getPromotionOfferList();		
				SmartList<PromotionOffer> newList= presentSubList(promotion.getId(),
				promotionOfferList,
				options,
				getPromotionOfferDAO()::countPromotionOfferByPromotion,
				getPromotionOfferDAO()::findPromotionOfferByPromotion
				);

		
		promotion.setPromotionOfferList(newList);
		

		return promotion;
	}			
		

	
    public SmartList<Promotion> requestCandidatePromotionForTargetUserRule(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PromotionTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPromotionMapper());
    }
		
    public SmartList<Promotion> requestCandidatePromotionForTargetAction(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PromotionTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPromotionMapper());
    }
		
    public SmartList<Promotion> requestCandidatePromotionForPromotionOffer(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PromotionTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPromotionMapper());
    }
		

	protected String getTableName(){
		return PromotionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Promotion> promotionList) {		
		this.enhanceListInternal(promotionList, this.getPromotionMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Promotion> promotionList = ownerEntity.collectRefsWithType(Promotion.INTERNAL_TYPE);
		this.enhanceList(promotionList);
		
	}
	
	@Override
	public SmartList<Promotion> findPromotionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPromotionMapper());

	}
	@Override
	public int countPromotionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPromotionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Promotion> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPromotionMapper());
	}
}


