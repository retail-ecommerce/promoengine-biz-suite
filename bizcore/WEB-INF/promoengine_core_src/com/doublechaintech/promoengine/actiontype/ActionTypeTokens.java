
package com.doublechaintech.promoengine.actiontype;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class ActionTypeTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="actionType";
	
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
	protected ActionTypeTokens(){
		//ensure not initialized outside the class
	}
	public  static  ActionTypeTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ActionTypeTokens tokens = new ActionTypeTokens(options);
		return tokens;
		
	}
	protected ActionTypeTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ActionTypeTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ActionTypeTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ActionTypeTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ActionTypeTokens start(){
		return new ActionTypeTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ActionTypeTokens allTokens(){
		
		return start()
			.withPlatform()
			.withTargetActionList();
	
	}
	public static ActionTypeTokens withoutListsTokens(){
		
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
	
	public ActionTypeTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ActionTypeTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TARGET_ACTION_LIST = "targetActionList";
	public String getTargetActionList(){
		return TARGET_ACTION_LIST;
	}
	public ActionTypeTokens withTargetActionList(){		
		addSimpleOptions(TARGET_ACTION_LIST);
		return this;
	}
	public ActionTypeTokens analyzeTargetActionList(){		
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
	public ActionTypeTokens extractMoreFromTargetActionList(String idsSeperatedWithComma){		
		addSimpleOptions(TARGET_ACTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int targetActionListSortCounter = 0;
	public ActionTypeTokens sortTargetActionListWith(String field, String descOrAsc){		
		addSortMoreOptions(TARGET_ACTION_LIST,targetActionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int targetActionListSearchCounter = 0;
	public ActionTypeTokens searchTargetActionListWith(String field, String verb, String value){		
		addSearchMoreOptions(TARGET_ACTION_LIST,targetActionListSearchCounter++, field, verb, value);
		return this;
	}
	
	public ActionTypeTokens searchAllTextOfTargetActionList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(TARGET_ACTION_LIST,targetActionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ActionTypeTokens rowsPerPageOfTargetActionList(int rowsPerPage){		
		addSimpleOptions(TARGET_ACTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ActionTypeTokens currentPageNumberOfTargetActionList(int currentPageNumber){		
		addSimpleOptions(TARGET_ACTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ActionTypeTokens retainColumnsOfTargetActionList(String[] columns){		
		addSimpleOptions(TARGET_ACTION_LIST+"RetainColumns",columns);
		return this;
	}
	public ActionTypeTokens excludeColumnsOfTargetActionList(String[] columns){		
		addSimpleOptions(TARGET_ACTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ActionTypeTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTargetActionList(verb, value);	
		return this;
	}
}

