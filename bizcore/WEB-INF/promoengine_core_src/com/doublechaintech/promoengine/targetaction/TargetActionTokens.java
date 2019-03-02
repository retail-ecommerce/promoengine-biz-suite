
package com.doublechaintech.promoengine.targetaction;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class TargetActionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="targetAction";
	
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
	protected TargetActionTokens(){
		//ensure not initialized outside the class
	}
	public  static  TargetActionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		TargetActionTokens tokens = new TargetActionTokens(options);
		return tokens;
		
	}
	protected TargetActionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public TargetActionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static TargetActionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected TargetActionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static TargetActionTokens start(){
		return new TargetActionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static TargetActionTokens allTokens(){
		
		return start()
			.withAction()
			.withPromotion();
	
	}
	public static TargetActionTokens withoutListsTokens(){
		
		return start()
			.withAction()
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
	
	public TargetActionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ACTION = "action";
	public String getAction(){
		return ACTION;
	}
	public TargetActionTokens withAction(){		
		addSimpleOptions(ACTION);
		return this;
	}
	
	
	protected static final String PROMOTION = "promotion";
	public String getPromotion(){
		return PROMOTION;
	}
	public TargetActionTokens withPromotion(){		
		addSimpleOptions(PROMOTION);
		return this;
	}
	
	
	
	public  TargetActionTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

