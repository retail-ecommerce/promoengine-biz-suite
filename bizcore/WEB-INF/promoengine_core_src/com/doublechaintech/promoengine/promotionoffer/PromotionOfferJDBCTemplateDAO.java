
package com.doublechaintech.promoengine.promotionoffer;

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
import com.doublechaintech.promoengine.promotion.Promotion;

import com.doublechaintech.promoengine.promotionlevel.PromotionLevelDAO;
import com.doublechaintech.promoengine.promotion.PromotionDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PromotionOfferJDBCTemplateDAO extends PromoengineNamingServiceDAO implements PromotionOfferDAO{
 
 	
 	private  PromotionLevelDAO  promotionLevelDAO;
 	public void setPromotionLevelDAO(PromotionLevelDAO promotionLevelDAO){
	 	this.promotionLevelDAO = promotionLevelDAO;
 	}
 	public PromotionLevelDAO getPromotionLevelDAO(){
	 	return this.promotionLevelDAO;
 	}
 
 	
 	private  PromotionDAO  promotionDAO;
 	public void setPromotionDAO(PromotionDAO promotionDAO){
	 	this.promotionDAO = promotionDAO;
 	}
 	public PromotionDAO getPromotionDAO(){
	 	return this.promotionDAO;
 	}


			
		

	
	/*
	protected PromotionOffer load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPromotionOffer(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public PromotionOffer load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPromotionOffer(PromotionOfferTable.withId(id), options);
	}
	
	
	
	public PromotionOffer save(PromotionOffer promotionOffer,Map<String,Object> options){
		
		String methodName="save(PromotionOffer promotionOffer,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(promotionOffer, methodName, "promotionOffer");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPromotionOffer(promotionOffer,options);
	}
	public PromotionOffer clone(String promotionOfferId, Map<String,Object> options) throws Exception{
	
		return clone(PromotionOfferTable.withId(promotionOfferId),options);
	}
	
	protected PromotionOffer clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String promotionOfferId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		PromotionOffer newPromotionOffer = loadInternalPromotionOffer(accessKey, options);
		newPromotionOffer.setVersion(0);
		
		

		
		saveInternalPromotionOffer(newPromotionOffer,options);
		
		return newPromotionOffer;
	}
	
	
	
	

	protected void throwIfHasException(String promotionOfferId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PromotionOfferVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PromotionOfferNotFoundException(
					"The " + this.getTableName() + "(" + promotionOfferId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String promotionOfferId, int version) throws Exception{
	
		String methodName="delete(String promotionOfferId, int version)";
		assertMethodArgumentNotNull(promotionOfferId, methodName, "promotionOfferId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{promotionOfferId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(promotionOfferId,version);
		}
		
	
	}
	
	
	
	
	

	public PromotionOffer disconnectFromAll(String promotionOfferId, int version) throws Exception{
	
		
		PromotionOffer promotionOffer = loadInternalPromotionOffer(PromotionOfferTable.withId(promotionOfferId), emptyOptions());
		promotionOffer.clearFromAll();
		this.savePromotionOffer(promotionOffer);
		return promotionOffer;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PromotionOfferTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "promotion_offer";
	}
	@Override
	protected String getBeanName() {
		
		return "promotionOffer";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PromotionOfferTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPromotionLevelEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PromotionOfferTokens.PROMOTIONLEVEL);
 	}

 	protected boolean isSavePromotionLevelEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PromotionOfferTokens.PROMOTIONLEVEL);
 	}
 	

 	
  

 	protected boolean isExtractPromotionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PromotionOfferTokens.PROMOTION);
 	}

 	protected boolean isSavePromotionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PromotionOfferTokens.PROMOTION);
 	}
 	

 	
 
		

	

	protected PromotionOfferMapper getPromotionOfferMapper(){
		return new PromotionOfferMapper();
	}

	
	
	protected PromotionOffer extractPromotionOffer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			PromotionOffer promotionOffer = loadSingleObject(accessKey, getPromotionOfferMapper());
			return promotionOffer;
		}catch(EmptyResultDataAccessException e){
			throw new PromotionOfferNotFoundException("PromotionOffer("+accessKey+") is not found!");
		}

	}

	
	

	protected PromotionOffer loadInternalPromotionOffer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		PromotionOffer promotionOffer = extractPromotionOffer(accessKey, loadOptions);
 	
 		if(isExtractPromotionLevelEnabled(loadOptions)){
	 		extractPromotionLevel(promotionOffer, loadOptions);
 		}
  	
 		if(isExtractPromotionEnabled(loadOptions)){
	 		extractPromotion(promotionOffer, loadOptions);
 		}
 
		
		return promotionOffer;
		
	}

	 

 	protected PromotionOffer extractPromotionLevel(PromotionOffer promotionOffer, Map<String,Object> options) throws Exception{

		if(promotionOffer.getPromotionLevel() == null){
			return promotionOffer;
		}
		String promotionLevelId = promotionOffer.getPromotionLevel().getId();
		if( promotionLevelId == null){
			return promotionOffer;
		}
		PromotionLevel promotionLevel = getPromotionLevelDAO().load(promotionLevelId,options);
		if(promotionLevel != null){
			promotionOffer.setPromotionLevel(promotionLevel);
		}
		
 		
 		return promotionOffer;
 	}
 		
  

 	protected PromotionOffer extractPromotion(PromotionOffer promotionOffer, Map<String,Object> options) throws Exception{

		if(promotionOffer.getPromotion() == null){
			return promotionOffer;
		}
		String promotionId = promotionOffer.getPromotion().getId();
		if( promotionId == null){
			return promotionOffer;
		}
		Promotion promotion = getPromotionDAO().load(promotionId,options);
		if(promotion != null){
			promotionOffer.setPromotion(promotion);
		}
		
 		
 		return promotionOffer;
 	}
 		
 
		
		
  	
 	public SmartList<PromotionOffer> findPromotionOfferByPromotionLevel(String promotionLevelId,Map<String,Object> options){
 	
  		SmartList<PromotionOffer> resultList = queryWith(PromotionOfferTable.COLUMN_PROMOTION_LEVEL, promotionLevelId, options, getPromotionOfferMapper());
		// analyzePromotionOfferByPromotionLevel(resultList, promotionLevelId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PromotionOffer> findPromotionOfferByPromotionLevel(String promotionLevelId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PromotionOffer> resultList =  queryWithRange(PromotionOfferTable.COLUMN_PROMOTION_LEVEL, promotionLevelId, options, getPromotionOfferMapper(), start, count);
 		//analyzePromotionOfferByPromotionLevel(resultList, promotionLevelId, options);
 		return resultList;
 		
 	}
 	public void analyzePromotionOfferByPromotionLevel(SmartList<PromotionOffer> resultList, String promotionLevelId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PromotionOffer.PROMOTION_LEVEL_PROPERTY, promotionLevelId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPromotionOfferByPromotionLevel(String promotionLevelId,Map<String,Object> options){

 		return countWith(PromotionOfferTable.COLUMN_PROMOTION_LEVEL, promotionLevelId, options);
 	}
 	@Override
	public Map<String, Integer> countPromotionOfferByPromotionLevelIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PromotionOfferTable.COLUMN_PROMOTION_LEVEL, ids, options);
	}
 	
  	
 	public SmartList<PromotionOffer> findPromotionOfferByPromotion(String promotionId,Map<String,Object> options){
 	
  		SmartList<PromotionOffer> resultList = queryWith(PromotionOfferTable.COLUMN_PROMOTION, promotionId, options, getPromotionOfferMapper());
		// analyzePromotionOfferByPromotion(resultList, promotionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PromotionOffer> findPromotionOfferByPromotion(String promotionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PromotionOffer> resultList =  queryWithRange(PromotionOfferTable.COLUMN_PROMOTION, promotionId, options, getPromotionOfferMapper(), start, count);
 		//analyzePromotionOfferByPromotion(resultList, promotionId, options);
 		return resultList;
 		
 	}
 	public void analyzePromotionOfferByPromotion(SmartList<PromotionOffer> resultList, String promotionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PromotionOffer.PROMOTION_PROPERTY, promotionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPromotionOfferByPromotion(String promotionId,Map<String,Object> options){

 		return countWith(PromotionOfferTable.COLUMN_PROMOTION, promotionId, options);
 	}
 	@Override
	public Map<String, Integer> countPromotionOfferByPromotionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PromotionOfferTable.COLUMN_PROMOTION, ids, options);
	}
 	
 	
		
		
		

	

	protected PromotionOffer savePromotionOffer(PromotionOffer  promotionOffer){
		
		if(!promotionOffer.isChanged()){
			return promotionOffer;
		}
		
		
		String SQL=this.getSavePromotionOfferSQL(promotionOffer);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePromotionOfferParameters(promotionOffer);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		promotionOffer.incVersion();
		return promotionOffer;
	
	}
	public SmartList<PromotionOffer> savePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPromotionOfferList(promotionOfferList);
		
		batchPromotionOfferCreate((List<PromotionOffer>)lists[CREATE_LIST_INDEX]);
		
		batchPromotionOfferUpdate((List<PromotionOffer>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(PromotionOffer promotionOffer:promotionOfferList){
			if(promotionOffer.isChanged()){
				promotionOffer.incVersion();
			}
			
		
		}
		
		
		return promotionOfferList;
	}

	public SmartList<PromotionOffer> removePromotionOfferList(SmartList<PromotionOffer> promotionOfferList,Map<String,Object> options){
		
		
		super.removeList(promotionOfferList, options);
		
		return promotionOfferList;
		
		
	}
	
	protected List<Object[]> preparePromotionOfferBatchCreateArgs(List<PromotionOffer> promotionOfferList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PromotionOffer promotionOffer:promotionOfferList ){
			Object [] parameters = preparePromotionOfferCreateParameters(promotionOffer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePromotionOfferBatchUpdateArgs(List<PromotionOffer> promotionOfferList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PromotionOffer promotionOffer:promotionOfferList ){
			if(!promotionOffer.isChanged()){
				continue;
			}
			Object [] parameters = preparePromotionOfferUpdateParameters(promotionOffer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPromotionOfferCreate(List<PromotionOffer> promotionOfferList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePromotionOfferBatchCreateArgs(promotionOfferList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPromotionOfferUpdate(List<PromotionOffer> promotionOfferList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePromotionOfferBatchUpdateArgs(promotionOfferList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPromotionOfferList(List<PromotionOffer> promotionOfferList){
		
		List<PromotionOffer> promotionOfferCreateList=new ArrayList<PromotionOffer>();
		List<PromotionOffer> promotionOfferUpdateList=new ArrayList<PromotionOffer>();
		
		for(PromotionOffer promotionOffer: promotionOfferList){
			if(isUpdateRequest(promotionOffer)){
				promotionOfferUpdateList.add( promotionOffer);
				continue;
			}
			promotionOfferCreateList.add(promotionOffer);
		}
		
		return new Object[]{promotionOfferCreateList,promotionOfferUpdateList};
	}
	
	protected boolean isUpdateRequest(PromotionOffer promotionOffer){
 		return promotionOffer.getVersion() > 0;
 	}
 	protected String getSavePromotionOfferSQL(PromotionOffer promotionOffer){
 		if(isUpdateRequest(promotionOffer)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePromotionOfferParameters(PromotionOffer promotionOffer){
 		if(isUpdateRequest(promotionOffer) ){
 			return preparePromotionOfferUpdateParameters(promotionOffer);
 		}
 		return preparePromotionOfferCreateParameters(promotionOffer);
 	}
 	protected Object[] preparePromotionOfferUpdateParameters(PromotionOffer promotionOffer){
 		Object[] parameters = new Object[7];
 
 		parameters[0] = promotionOffer.getName(); 	
 		if(promotionOffer.getPromotionLevel() != null){
 			parameters[1] = promotionOffer.getPromotionLevel().getId();
 		}
 
 		parameters[2] = promotionOffer.getParameter(); 	
 		if(promotionOffer.getPromotion() != null){
 			parameters[3] = promotionOffer.getPromotion().getId();
 		}
 		
 		parameters[4] = promotionOffer.nextVersion();
 		parameters[5] = promotionOffer.getId();
 		parameters[6] = promotionOffer.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePromotionOfferCreateParameters(PromotionOffer promotionOffer){
		Object[] parameters = new Object[5];
		String newPromotionOfferId=getNextId();
		promotionOffer.setId(newPromotionOfferId);
		parameters[0] =  promotionOffer.getId();
 
 		parameters[1] = promotionOffer.getName(); 	
 		if(promotionOffer.getPromotionLevel() != null){
 			parameters[2] = promotionOffer.getPromotionLevel().getId();
 		
 		}
 		
 		parameters[3] = promotionOffer.getParameter(); 	
 		if(promotionOffer.getPromotion() != null){
 			parameters[4] = promotionOffer.getPromotion().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected PromotionOffer saveInternalPromotionOffer(PromotionOffer promotionOffer, Map<String,Object> options){
		
		savePromotionOffer(promotionOffer);
 	
 		if(isSavePromotionLevelEnabled(options)){
	 		savePromotionLevel(promotionOffer, options);
 		}
  	
 		if(isSavePromotionEnabled(options)){
	 		savePromotion(promotionOffer, options);
 		}
 
		
		return promotionOffer;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected PromotionOffer savePromotionLevel(PromotionOffer promotionOffer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(promotionOffer.getPromotionLevel() == null){
 			return promotionOffer;//do nothing when it is null
 		}
 		
 		getPromotionLevelDAO().save(promotionOffer.getPromotionLevel(),options);
 		return promotionOffer;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected PromotionOffer savePromotion(PromotionOffer promotionOffer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(promotionOffer.getPromotion() == null){
 			return promotionOffer;//do nothing when it is null
 		}
 		
 		getPromotionDAO().save(promotionOffer.getPromotion(),options);
 		return promotionOffer;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public PromotionOffer present(PromotionOffer promotionOffer,Map<String, Object> options){
	

		return promotionOffer;
	
	}
		

	

	protected String getTableName(){
		return PromotionOfferTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<PromotionOffer> promotionOfferList) {		
		this.enhanceListInternal(promotionOfferList, this.getPromotionOfferMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<PromotionOffer> promotionOfferList = ownerEntity.collectRefsWithType(PromotionOffer.INTERNAL_TYPE);
		this.enhanceList(promotionOfferList);
		
	}
	
	@Override
	public SmartList<PromotionOffer> findPromotionOfferWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPromotionOfferMapper());

	}
	@Override
	public int countPromotionOfferWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPromotionOfferWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<PromotionOffer> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPromotionOfferMapper());
	}
}


