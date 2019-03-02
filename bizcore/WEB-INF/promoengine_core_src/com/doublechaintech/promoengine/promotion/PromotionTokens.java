
package com.doublechaintech.promoengine.promotion;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class PromotionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="promotion";
	
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
	protected PromotionTokens(){
		//ensure not initialized outside the class
	}
	public  static  PromotionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PromotionTokens tokens = new PromotionTokens(options);
		return tokens;
		
	}
	protected PromotionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PromotionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PromotionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PromotionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PromotionTokens start(){
		return new PromotionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PromotionTokens allTokens(){
		
		return start()
			.withPlatform()
			.withTargetUserRuleList()
			.withTargetActionList()
			.withPromotionOfferList();
	
	}
	public static PromotionTokens withoutListsTokens(){
		
		return start()
			.withPlatform();
	
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
	
	public PromotionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public PromotionTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TARGET_USER_RULE_LIST = "targetUserRuleList";
	public String getTargetUserRuleList(){
		return TARGET_USER_RULE_LIST;
	}
	public PromotionTokens withTargetUserRuleList(){		
		addSimpleOptions(TARGET_USER_RULE_LIST);
		return this;
	}
	public PromotionTokens analyzeTargetUserRuleList(){		
		addSimpleOptions(TARGET_USER_RULE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeTargetUserRuleListEnabled(){		
		
		if(checkOptions(this.options(), TARGET_USER_RULE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PromotionTokens extractMoreFromTargetUserRuleList(String idsSeperatedWithComma){		
		addSimpleOptions(TARGET_USER_RULE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int targetUserRuleListSortCounter = 0;
	public PromotionTokens sortTargetUserRuleListWith(String field, String descOrAsc){		
		addSortMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSortCounter++, field, descOrAsc);
		return this;
	}
	private int targetUserRuleListSearchCounter = 0;
	public PromotionTokens searchTargetUserRuleListWith(String field, String verb, String value){		
		addSearchMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PromotionTokens searchAllTextOfTargetUserRuleList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PromotionTokens rowsPerPageOfTargetUserRuleList(int rowsPerPage){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PromotionTokens currentPageNumberOfTargetUserRuleList(int currentPageNumber){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PromotionTokens retainColumnsOfTargetUserRuleList(String[] columns){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"RetainColumns",columns);
		return this;
	}
	public PromotionTokens excludeColumnsOfTargetUserRuleList(String[] columns){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String TARGET_ACTION_LIST = "targetActionList";
	public String getTargetActionList(){
		return TARGET_ACTION_LIST;
	}
	public PromotionTokens withTargetActionList(){		
		addSimpleOptions(TARGET_ACTION_LIST);
		return this;
	}
	public PromotionTokens analyzeTargetActionList(){		
		addSimpleOptions(TARGET_ACTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeTargetActionListEnabled(){		
		
		if(checkOptions(this.options(), TARGET_ACTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PromotionTokens extractMoreFromTargetActionList(String idsSeperatedWithComma){		
		addSimpleOptions(TARGET_ACTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int targetActionListSortCounter = 0;
	public PromotionTokens sortTargetActionListWith(String field, String descOrAsc){		
		addSortMoreOptions(TARGET_ACTION_LIST,targetActionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int targetActionListSearchCounter = 0;
	public PromotionTokens searchTargetActionListWith(String field, String verb, String value){		
		addSearchMoreOptions(TARGET_ACTION_LIST,targetActionListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PromotionTokens searchAllTextOfTargetActionList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(TARGET_ACTION_LIST,targetActionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PromotionTokens rowsPerPageOfTargetActionList(int rowsPerPage){		
		addSimpleOptions(TARGET_ACTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PromotionTokens currentPageNumberOfTargetActionList(int currentPageNumber){		
		addSimpleOptions(TARGET_ACTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PromotionTokens retainColumnsOfTargetActionList(String[] columns){		
		addSimpleOptions(TARGET_ACTION_LIST+"RetainColumns",columns);
		return this;
	}
	public PromotionTokens excludeColumnsOfTargetActionList(String[] columns){		
		addSimpleOptions(TARGET_ACTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PROMOTION_OFFER_LIST = "promotionOfferList";
	public String getPromotionOfferList(){
		return PROMOTION_OFFER_LIST;
	}
	public PromotionTokens withPromotionOfferList(){		
		addSimpleOptions(PROMOTION_OFFER_LIST);
		return this;
	}
	public PromotionTokens analyzePromotionOfferList(){		
		addSimpleOptions(PROMOTION_OFFER_LIST+".anaylze");
		return this;
	}
	public boolean analyzePromotionOfferListEnabled(){		
		
		if(checkOptions(this.options(), PROMOTION_OFFER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PromotionTokens extractMoreFromPromotionOfferList(String idsSeperatedWithComma){		
		addSimpleOptions(PROMOTION_OFFER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int promotionOfferListSortCounter = 0;
	public PromotionTokens sortPromotionOfferListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSortCounter++, field, descOrAsc);
		return this;
	}
	private int promotionOfferListSearchCounter = 0;
	public PromotionTokens searchPromotionOfferListWith(String field, String verb, String value){		
		addSearchMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PromotionTokens searchAllTextOfPromotionOfferList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PromotionTokens rowsPerPageOfPromotionOfferList(int rowsPerPage){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PromotionTokens currentPageNumberOfPromotionOfferList(int currentPageNumber){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PromotionTokens retainColumnsOfPromotionOfferList(String[] columns){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"RetainColumns",columns);
		return this;
	}
	public PromotionTokens excludeColumnsOfPromotionOfferList(String[] columns){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PromotionTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTargetUserRuleList(verb, value);	
		searchAllTextOfTargetActionList(verb, value);	
		searchAllTextOfPromotionOfferList(verb, value);	
		return this;
	}
}

