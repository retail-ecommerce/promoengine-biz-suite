
package com.doublechaintech.promoengine.targetuserrule;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class TargetUserRuleTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="targetUserRule";
	
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
	protected TargetUserRuleTokens(){
		//ensure not initialized outside the class
	}
	public  static  TargetUserRuleTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		TargetUserRuleTokens tokens = new TargetUserRuleTokens(options);
		return tokens;
		
	}
	protected TargetUserRuleTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public TargetUserRuleTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static TargetUserRuleTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected TargetUserRuleTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static TargetUserRuleTokens start(){
		return new TargetUserRuleTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static TargetUserRuleTokens allTokens(){
		
		return start()
			.withApplyCondition()
			.withPromotion();
	
	}
	public static TargetUserRuleTokens withoutListsTokens(){
		
		return start()
			.withApplyCondition()
			.withPromotion();
	
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
	
	public TargetUserRuleTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String APPLYCONDITION = "applyCondition";
	public String getApplyCondition(){
		return APPLYCONDITION;
	}
	public TargetUserRuleTokens withApplyCondition(){		
		addSimpleOptions(APPLYCONDITION);
		return this;
	}
	
	
	protected static final String PROMOTION = "promotion";
	public String getPromotion(){
		return PROMOTION;
	}
	public TargetUserRuleTokens withPromotion(){		
		addSimpleOptions(PROMOTION);
		return this;
	}
	
	
	
	public  TargetUserRuleTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

