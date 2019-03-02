
package com.doublechaintech.promoengine.applycondition;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class ApplyConditionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="applyCondition";
	
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
	protected ApplyConditionTokens(){
		//ensure not initialized outside the class
	}
	public  static  ApplyConditionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ApplyConditionTokens tokens = new ApplyConditionTokens(options);
		return tokens;
		
	}
	protected ApplyConditionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ApplyConditionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ApplyConditionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ApplyConditionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ApplyConditionTokens start(){
		return new ApplyConditionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ApplyConditionTokens allTokens(){
		
		return start()
			.withPlatform()
			.withTargetUserRuleList();
	
	}
	public static ApplyConditionTokens withoutListsTokens(){
		
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
	
	public ApplyConditionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ApplyConditionTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TARGET_USER_RULE_LIST = "targetUserRuleList";
	public String getTargetUserRuleList(){
		return TARGET_USER_RULE_LIST;
	}
	public ApplyConditionTokens withTargetUserRuleList(){		
		addSimpleOptions(TARGET_USER_RULE_LIST);
		return this;
	}
	public ApplyConditionTokens analyzeTargetUserRuleList(){		
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
	public ApplyConditionTokens extractMoreFromTargetUserRuleList(String idsSeperatedWithComma){		
		addSimpleOptions(TARGET_USER_RULE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int targetUserRuleListSortCounter = 0;
	public ApplyConditionTokens sortTargetUserRuleListWith(String field, String descOrAsc){		
		addSortMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSortCounter++, field, descOrAsc);
		return this;
	}
	private int targetUserRuleListSearchCounter = 0;
	public ApplyConditionTokens searchTargetUserRuleListWith(String field, String verb, String value){		
		addSearchMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSearchCounter++, field, verb, value);
		return this;
	}
	
	public ApplyConditionTokens searchAllTextOfTargetUserRuleList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(TARGET_USER_RULE_LIST,targetUserRuleListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ApplyConditionTokens rowsPerPageOfTargetUserRuleList(int rowsPerPage){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ApplyConditionTokens currentPageNumberOfTargetUserRuleList(int currentPageNumber){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ApplyConditionTokens retainColumnsOfTargetUserRuleList(String[] columns){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"RetainColumns",columns);
		return this;
	}
	public ApplyConditionTokens excludeColumnsOfTargetUserRuleList(String[] columns){		
		addSimpleOptions(TARGET_USER_RULE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ApplyConditionTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTargetUserRuleList(verb, value);	
		return this;
	}
}

