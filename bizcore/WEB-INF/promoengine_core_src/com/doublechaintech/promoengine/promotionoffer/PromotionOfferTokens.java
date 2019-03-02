
package com.doublechaintech.promoengine.promotionoffer;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class PromotionOfferTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="promotionOffer";
	
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
	protected PromotionOfferTokens(){
		//ensure not initialized outside the class
	}
	public  static  PromotionOfferTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PromotionOfferTokens tokens = new PromotionOfferTokens(options);
		return tokens;
		
	}
	protected PromotionOfferTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PromotionOfferTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PromotionOfferTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PromotionOfferTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PromotionOfferTokens start(){
		return new PromotionOfferTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PromotionOfferTokens allTokens(){
		
		return start()
			.withPromotionLevel()
			.withPromotion();
	
	}
	public static PromotionOfferTokens withoutListsTokens(){
		
		return start()
			.withPromotionLevel()
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
	
	public PromotionOfferTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PROMOTIONLEVEL = "promotionLevel";
	public String getPromotionLevel(){
		return PROMOTIONLEVEL;
	}
	public PromotionOfferTokens withPromotionLevel(){		
		addSimpleOptions(PROMOTIONLEVEL);
		return this;
	}
	
	
	protected static final String PROMOTION = "promotion";
	public String getPromotion(){
		return PROMOTION;
	}
	public PromotionOfferTokens withPromotion(){		
		addSimpleOptions(PROMOTION);
		return this;
	}
	
	
	
	public  PromotionOfferTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

