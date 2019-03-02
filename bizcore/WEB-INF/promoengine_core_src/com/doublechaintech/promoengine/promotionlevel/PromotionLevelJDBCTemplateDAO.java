
package com.doublechaintech.promoengine.promotionlevel;

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
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PromotionLevelJDBCTemplateDAO extends PromoengineNamingServiceDAO implements PromotionLevelDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
	protected PromotionLevel load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPromotionLevel(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public PromotionLevel load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPromotionLevel(PromotionLevelTable.withId(id), options);
	}
	
	
	
	public PromotionLevel save(PromotionLevel promotionLevel,Map<String,Object> options){
		
		String methodName="save(PromotionLevel promotionLevel,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(promotionLevel, methodName, "promotionLevel");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPromotionLevel(promotionLevel,options);
	}
	public PromotionLevel clone(String promotionLevelId, Map<String,Object> options) throws Exception{
	
		return clone(PromotionLevelTable.withId(promotionLevelId),options);
	}
	
	protected PromotionLevel clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String promotionLevelId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		PromotionLevel newPromotionLevel = loadInternalPromotionLevel(accessKey, options);
		newPromotionLevel.setVersion(0);
		
		
 		
 		if(isSavePromotionOfferListEnabled(options)){
 			for(PromotionOffer item: newPromotionLevel.getPromotionOfferList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPromotionLevel(newPromotionLevel,options);
		
		return newPromotionLevel;
	}
	
	
	
	

	protected void throwIfHasException(String promotionLevelId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PromotionLevelVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PromotionLevelNotFoundException(
					"The " + this.getTableName() + "(" + promotionLevelId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String promotionLevelId, int version) throws Exception{
	
		String methodName="delete(String promotionLevelId, int version)";
		assertMethodArgumentNotNull(promotionLevelId, methodName, "promotionLevelId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{promotionLevelId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(promotionLevelId,version);
		}
		
	
	}
	
	
	
	
	

	public PromotionLevel disconnectFromAll(String promotionLevelId, int version) throws Exception{
	
		
		PromotionLevel promotionLevel = loadInternalPromotionLevel(PromotionLevelTable.withId(promotionLevelId), emptyOptions());
		promotionLevel.clearFromAll();
		this.savePromotionLevel(promotionLevel);
		return promotionLevel;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PromotionLevelTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "promotion_level";
	}
	@Override
	protected String getBeanName() {
		
		return "promotionLevel";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PromotionLevelTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PromotionLevelTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PromotionLevelTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractPromotionOfferListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PromotionLevelTokens.PROMOTION_OFFER_LIST);
 	}
 	protected boolean isAnalyzePromotionOfferListEnabled(Map<String,Object> options){		 		
 		return PromotionLevelTokens.of(options).analyzePromotionOfferListEnabled();
 	}
	
	protected boolean isSavePromotionOfferListEnabled(Map<String,Object> options){
		return checkOptions(options, PromotionLevelTokens.PROMOTION_OFFER_LIST);
		
 	}
 	
		

	

	protected PromotionLevelMapper getPromotionLevelMapper(){
		return new PromotionLevelMapper();
	}

	
	
	protected PromotionLevel extractPromotionLevel(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			PromotionLevel promotionLevel = loadSingleObject(accessKey, getPromotionLevelMapper());
			return promotionLevel;
		}catch(EmptyResultDataAccessException e){
			throw new PromotionLevelNotFoundException("PromotionLevel("+accessKey+") is not found!");
		}

	}

	
	

	protected PromotionLevel loadInternalPromotionLevel(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		PromotionLevel promotionLevel = extractPromotionLevel(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(promotionLevel, loadOptions);
 		}
 
		
		if(isExtractPromotionOfferListEnabled(loadOptions)){
	 		extractPromotionOfferList(promotionLevel, loadOptions);
 		}	
 		if(isAnalyzePromotionOfferListEnabled(loadOptions)){
	 		analyzePromotionOfferList(promotionLevel, loadOptions);
 		}
 		
		
		return promotionLevel;
		
	}

	 

 	protected PromotionLevel extractPlatform(PromotionLevel promotionLevel, Map<String,Object> options) throws Exception{

		if(promotionLevel.getPlatform() == null){
			return promotionLevel;
		}
		String platformId = promotionLevel.getPlatform().getId();
		if( platformId == null){
			return promotionLevel;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			promotionLevel.setPlatform(platform);
		}
		
 		
 		return promotionLevel;
 	}
 		
 
		
	protected void enhancePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected PromotionLevel extractPromotionOfferList(PromotionLevel promotionLevel, Map<String,Object> options){
		
		
		if(promotionLevel == null){
			return null;
		}
		if(promotionLevel.getId() == null){
			return promotionLevel;
		}

		
		
		SmartList<PromotionOffer> promotionOfferList = getPromotionOfferDAO().findPromotionOfferByPromotionLevel(promotionLevel.getId(),options);
		if(promotionOfferList != null){
			enhancePromotionOfferList(promotionOfferList,options);
			promotionLevel.setPromotionOfferList(promotionOfferList);
		}
		
		return promotionLevel;
	
	}	
	
	protected PromotionLevel analyzePromotionOfferList(PromotionLevel promotionLevel, Map<String,Object> options){
		
		
		if(promotionLevel == null){
			return null;
		}
		if(promotionLevel.getId() == null){
			return promotionLevel;
		}

		
		
		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();
		if(promotionOfferList != null){
			getPromotionOfferDAO().analyzePromotionOfferByPromotionLevel(promotionOfferList, promotionLevel.getId(), options);
			
		}
		
		return promotionLevel;
	
	}	
	
		
		
  	
 	public SmartList<PromotionLevel> findPromotionLevelByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<PromotionLevel> resultList = queryWith(PromotionLevelTable.COLUMN_PLATFORM, platformId, options, getPromotionLevelMapper());
		// analyzePromotionLevelByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PromotionLevel> findPromotionLevelByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PromotionLevel> resultList =  queryWithRange(PromotionLevelTable.COLUMN_PLATFORM, platformId, options, getPromotionLevelMapper(), start, count);
 		//analyzePromotionLevelByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzePromotionLevelByPlatform(SmartList<PromotionLevel> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PromotionLevel.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//PromotionLevel.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Promotion Level");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(PromotionLevel.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(PromotionLevel.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPromotionLevelByPlatform(String platformId,Map<String,Object> options){

 		return countWith(PromotionLevelTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countPromotionLevelByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PromotionLevelTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected PromotionLevel savePromotionLevel(PromotionLevel  promotionLevel){
		
		if(!promotionLevel.isChanged()){
			return promotionLevel;
		}
		
		
		String SQL=this.getSavePromotionLevelSQL(promotionLevel);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePromotionLevelParameters(promotionLevel);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		promotionLevel.incVersion();
		return promotionLevel;
	
	}
	public SmartList<PromotionLevel> savePromotionLevelList(SmartList<PromotionLevel> promotionLevelList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPromotionLevelList(promotionLevelList);
		
		batchPromotionLevelCreate((List<PromotionLevel>)lists[CREATE_LIST_INDEX]);
		
		batchPromotionLevelUpdate((List<PromotionLevel>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(PromotionLevel promotionLevel:promotionLevelList){
			if(promotionLevel.isChanged()){
				promotionLevel.incVersion();
			}
			
		
		}
		
		
		return promotionLevelList;
	}

	public SmartList<PromotionLevel> removePromotionLevelList(SmartList<PromotionLevel> promotionLevelList,Map<String,Object> options){
		
		
		super.removeList(promotionLevelList, options);
		
		return promotionLevelList;
		
		
	}
	
	protected List<Object[]> preparePromotionLevelBatchCreateArgs(List<PromotionLevel> promotionLevelList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PromotionLevel promotionLevel:promotionLevelList ){
			Object [] parameters = preparePromotionLevelCreateParameters(promotionLevel);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePromotionLevelBatchUpdateArgs(List<PromotionLevel> promotionLevelList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PromotionLevel promotionLevel:promotionLevelList ){
			if(!promotionLevel.isChanged()){
				continue;
			}
			Object [] parameters = preparePromotionLevelUpdateParameters(promotionLevel);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPromotionLevelCreate(List<PromotionLevel> promotionLevelList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePromotionLevelBatchCreateArgs(promotionLevelList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPromotionLevelUpdate(List<PromotionLevel> promotionLevelList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePromotionLevelBatchUpdateArgs(promotionLevelList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPromotionLevelList(List<PromotionLevel> promotionLevelList){
		
		List<PromotionLevel> promotionLevelCreateList=new ArrayList<PromotionLevel>();
		List<PromotionLevel> promotionLevelUpdateList=new ArrayList<PromotionLevel>();
		
		for(PromotionLevel promotionLevel: promotionLevelList){
			if(isUpdateRequest(promotionLevel)){
				promotionLevelUpdateList.add( promotionLevel);
				continue;
			}
			promotionLevelCreateList.add(promotionLevel);
		}
		
		return new Object[]{promotionLevelCreateList,promotionLevelUpdateList};
	}
	
	protected boolean isUpdateRequest(PromotionLevel promotionLevel){
 		return promotionLevel.getVersion() > 0;
 	}
 	protected String getSavePromotionLevelSQL(PromotionLevel promotionLevel){
 		if(isUpdateRequest(promotionLevel)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePromotionLevelParameters(PromotionLevel promotionLevel){
 		if(isUpdateRequest(promotionLevel) ){
 			return preparePromotionLevelUpdateParameters(promotionLevel);
 		}
 		return preparePromotionLevelCreateParameters(promotionLevel);
 	}
 	protected Object[] preparePromotionLevelUpdateParameters(PromotionLevel promotionLevel){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = promotionLevel.getName();
 		parameters[1] = promotionLevel.getLastUpdateTime(); 	
 		if(promotionLevel.getPlatform() != null){
 			parameters[2] = promotionLevel.getPlatform().getId();
 		}
 		
 		parameters[3] = promotionLevel.nextVersion();
 		parameters[4] = promotionLevel.getId();
 		parameters[5] = promotionLevel.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePromotionLevelCreateParameters(PromotionLevel promotionLevel){
		Object[] parameters = new Object[4];
		String newPromotionLevelId=getNextId();
		promotionLevel.setId(newPromotionLevelId);
		parameters[0] =  promotionLevel.getId();
 
 		parameters[1] = promotionLevel.getName();
 		parameters[2] = promotionLevel.getLastUpdateTime(); 	
 		if(promotionLevel.getPlatform() != null){
 			parameters[3] = promotionLevel.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected PromotionLevel saveInternalPromotionLevel(PromotionLevel promotionLevel, Map<String,Object> options){
		
		savePromotionLevel(promotionLevel);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(promotionLevel, options);
 		}
 
		
		if(isSavePromotionOfferListEnabled(options)){
	 		savePromotionOfferList(promotionLevel, options);
	 		//removePromotionOfferList(promotionLevel, options);
	 		//Not delete the record
	 		
 		}		
		
		return promotionLevel;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected PromotionLevel savePlatform(PromotionLevel promotionLevel, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(promotionLevel.getPlatform() == null){
 			return promotionLevel;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(promotionLevel.getPlatform(),options);
 		return promotionLevel;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public PromotionLevel planToRemovePromotionOfferList(PromotionLevel promotionLevel, String promotionOfferIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevel.getId());
		key.put(PromotionOffer.ID_PROPERTY, promotionOfferIds);
		
		SmartList<PromotionOffer> externalPromotionOfferList = getPromotionOfferDAO().
				findPromotionOfferWithKey(key, options);
		if(externalPromotionOfferList == null){
			return promotionLevel;
		}
		if(externalPromotionOfferList.isEmpty()){
			return promotionLevel;
		}
		
		for(PromotionOffer promotionOffer: externalPromotionOfferList){

			promotionOffer.clearFromAll();
		}
		
		
		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();		
		promotionOfferList.addAllToRemoveList(externalPromotionOfferList);
		return promotionLevel;	
	
	}


	//disconnect PromotionLevel with promotion in PromotionOffer
	public PromotionLevel planToRemovePromotionOfferListWithPromotion(PromotionLevel promotionLevel, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevel.getId());
		key.put(PromotionOffer.PROMOTION_PROPERTY, promotionId);
		
		SmartList<PromotionOffer> externalPromotionOfferList = getPromotionOfferDAO().
				findPromotionOfferWithKey(key, options);
		if(externalPromotionOfferList == null){
			return promotionLevel;
		}
		if(externalPromotionOfferList.isEmpty()){
			return promotionLevel;
		}
		
		for(PromotionOffer promotionOffer: externalPromotionOfferList){
			promotionOffer.clearPromotion();
			promotionOffer.clearPromotionLevel();
			
		}
		
		
		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();		
		promotionOfferList.addAllToRemoveList(externalPromotionOfferList);
		return promotionLevel;
	}
	
	public int countPromotionOfferListWithPromotion(String promotionLevelId, String promotionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevelId);
		key.put(PromotionOffer.PROMOTION_PROPERTY, promotionId);
		
		int count = getPromotionOfferDAO().countPromotionOfferWithKey(key, options);
		return count;
	}
	

		
	protected PromotionLevel savePromotionOfferList(PromotionLevel promotionLevel, Map<String,Object> options){
		
		
		
		
		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();
		if(promotionOfferList == null){
			//null list means nothing
			return promotionLevel;
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
		
		
		return promotionLevel;
	
	}
	
	protected PromotionLevel removePromotionOfferList(PromotionLevel promotionLevel, Map<String,Object> options){
	
	
		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();
		if(promotionOfferList == null){
			return promotionLevel;
		}	
	
		SmartList<PromotionOffer> toRemovePromotionOfferList = promotionOfferList.getToRemoveList();
		
		if(toRemovePromotionOfferList == null){
			return promotionLevel;
		}
		if(toRemovePromotionOfferList.isEmpty()){
			return promotionLevel;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPromotionOfferDAO().removePromotionOfferList(toRemovePromotionOfferList,options);
		
		return promotionLevel;
	
	}
	
	

 	
 	
	
	
	
		

	public PromotionLevel present(PromotionLevel promotionLevel,Map<String, Object> options){
	
		presentPromotionOfferList(promotionLevel,options);

		return promotionLevel;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected PromotionLevel presentPromotionOfferList(
			PromotionLevel promotionLevel,
			Map<String, Object> options) {

		SmartList<PromotionOffer> promotionOfferList = promotionLevel.getPromotionOfferList();		
				SmartList<PromotionOffer> newList= presentSubList(promotionLevel.getId(),
				promotionOfferList,
				options,
				getPromotionOfferDAO()::countPromotionOfferByPromotionLevel,
				getPromotionOfferDAO()::findPromotionOfferByPromotionLevel
				);

		
		promotionLevel.setPromotionOfferList(newList);
		

		return promotionLevel;
	}			
		

	
    public SmartList<PromotionLevel> requestCandidatePromotionLevelForPromotionOffer(PromoengineUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PromotionLevelTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPromotionLevelMapper());
    }
		

	protected String getTableName(){
		return PromotionLevelTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<PromotionLevel> promotionLevelList) {		
		this.enhanceListInternal(promotionLevelList, this.getPromotionLevelMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<PromotionLevel> promotionLevelList = ownerEntity.collectRefsWithType(PromotionLevel.INTERNAL_TYPE);
		this.enhanceList(promotionLevelList);
		
	}
	
	@Override
	public SmartList<PromotionLevel> findPromotionLevelWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPromotionLevelMapper());

	}
	@Override
	public int countPromotionLevelWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPromotionLevelWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<PromotionLevel> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPromotionLevelMapper());
	}
}


