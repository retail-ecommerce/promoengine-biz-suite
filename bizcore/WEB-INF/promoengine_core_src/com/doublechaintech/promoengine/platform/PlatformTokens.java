
package com.doublechaintech.promoengine.platform;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	public  static  PlatformTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PlatformTokens tokens = new PlatformTokens(options);
		return tokens;
		
	}
	protected PlatformTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withApplyConditionList()
			.withActionTypeList()
			.withPromotionLevelList()
			.withPromotionList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public PlatformTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String APPLY_CONDITION_LIST = "applyConditionList";
	public String getApplyConditionList(){
		return APPLY_CONDITION_LIST;
	}
	public PlatformTokens withApplyConditionList(){		
		addSimpleOptions(APPLY_CONDITION_LIST);
		return this;
	}
	public PlatformTokens analyzeApplyConditionList(){		
		addSimpleOptions(APPLY_CONDITION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeApplyConditionListEnabled(){		
		
		if(checkOptions(this.options(), APPLY_CONDITION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromApplyConditionList(String idsSeperatedWithComma){		
		addSimpleOptions(APPLY_CONDITION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int applyConditionListSortCounter = 0;
	public PlatformTokens sortApplyConditionListWith(String field, String descOrAsc){		
		addSortMoreOptions(APPLY_CONDITION_LIST,applyConditionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int applyConditionListSearchCounter = 0;
	public PlatformTokens searchApplyConditionListWith(String field, String verb, String value){		
		addSearchMoreOptions(APPLY_CONDITION_LIST,applyConditionListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfApplyConditionList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(APPLY_CONDITION_LIST,applyConditionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfApplyConditionList(int rowsPerPage){		
		addSimpleOptions(APPLY_CONDITION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfApplyConditionList(int currentPageNumber){		
		addSimpleOptions(APPLY_CONDITION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfApplyConditionList(String[] columns){		
		addSimpleOptions(APPLY_CONDITION_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfApplyConditionList(String[] columns){		
		addSimpleOptions(APPLY_CONDITION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String ACTION_TYPE_LIST = "actionTypeList";
	public String getActionTypeList(){
		return ACTION_TYPE_LIST;
	}
	public PlatformTokens withActionTypeList(){		
		addSimpleOptions(ACTION_TYPE_LIST);
		return this;
	}
	public PlatformTokens analyzeActionTypeList(){		
		addSimpleOptions(ACTION_TYPE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeActionTypeListEnabled(){		
		
		if(checkOptions(this.options(), ACTION_TYPE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromActionTypeList(String idsSeperatedWithComma){		
		addSimpleOptions(ACTION_TYPE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int actionTypeListSortCounter = 0;
	public PlatformTokens sortActionTypeListWith(String field, String descOrAsc){		
		addSortMoreOptions(ACTION_TYPE_LIST,actionTypeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int actionTypeListSearchCounter = 0;
	public PlatformTokens searchActionTypeListWith(String field, String verb, String value){		
		addSearchMoreOptions(ACTION_TYPE_LIST,actionTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfActionTypeList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(ACTION_TYPE_LIST,actionTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfActionTypeList(int rowsPerPage){		
		addSimpleOptions(ACTION_TYPE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfActionTypeList(int currentPageNumber){		
		addSimpleOptions(ACTION_TYPE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfActionTypeList(String[] columns){		
		addSimpleOptions(ACTION_TYPE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfActionTypeList(String[] columns){		
		addSimpleOptions(ACTION_TYPE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PROMOTION_LEVEL_LIST = "promotionLevelList";
	public String getPromotionLevelList(){
		return PROMOTION_LEVEL_LIST;
	}
	public PlatformTokens withPromotionLevelList(){		
		addSimpleOptions(PROMOTION_LEVEL_LIST);
		return this;
	}
	public PlatformTokens analyzePromotionLevelList(){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+".anaylze");
		return this;
	}
	public boolean analyzePromotionLevelListEnabled(){		
		
		if(checkOptions(this.options(), PROMOTION_LEVEL_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromPromotionLevelList(String idsSeperatedWithComma){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int promotionLevelListSortCounter = 0;
	public PlatformTokens sortPromotionLevelListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROMOTION_LEVEL_LIST,promotionLevelListSortCounter++, field, descOrAsc);
		return this;
	}
	private int promotionLevelListSearchCounter = 0;
	public PlatformTokens searchPromotionLevelListWith(String field, String verb, String value){		
		addSearchMoreOptions(PROMOTION_LEVEL_LIST,promotionLevelListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfPromotionLevelList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(PROMOTION_LEVEL_LIST,promotionLevelListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfPromotionLevelList(int rowsPerPage){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfPromotionLevelList(int currentPageNumber){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfPromotionLevelList(String[] columns){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfPromotionLevelList(String[] columns){		
		addSimpleOptions(PROMOTION_LEVEL_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PROMOTION_LIST = "promotionList";
	public String getPromotionList(){
		return PROMOTION_LIST;
	}
	public PlatformTokens withPromotionList(){		
		addSimpleOptions(PROMOTION_LIST);
		return this;
	}
	public PlatformTokens analyzePromotionList(){		
		addSimpleOptions(PROMOTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzePromotionListEnabled(){		
		
		if(checkOptions(this.options(), PROMOTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromPromotionList(String idsSeperatedWithComma){		
		addSimpleOptions(PROMOTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int promotionListSortCounter = 0;
	public PlatformTokens sortPromotionListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROMOTION_LIST,promotionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int promotionListSearchCounter = 0;
	public PlatformTokens searchPromotionListWith(String field, String verb, String value){		
		addSearchMoreOptions(PROMOTION_LIST,promotionListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfPromotionList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(PROMOTION_LIST,promotionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfPromotionList(int rowsPerPage){		
		addSimpleOptions(PROMOTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfPromotionList(int currentPageNumber){		
		addSimpleOptions(PROMOTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfPromotionList(String[] columns){		
		addSimpleOptions(PROMOTION_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfPromotionList(String[] columns){		
		addSimpleOptions(PROMOTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfApplyConditionList(verb, value);	
		searchAllTextOfActionTypeList(verb, value);	
		searchAllTextOfPromotionLevelList(verb, value);	
		searchAllTextOfPromotionList(verb, value);	
		return this;
	}
}

