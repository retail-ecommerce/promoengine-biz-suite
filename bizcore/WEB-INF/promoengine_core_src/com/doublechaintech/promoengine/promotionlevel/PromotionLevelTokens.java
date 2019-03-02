
package com.doublechaintech.promoengine.promotionlevel;
import com.doublechaintech.promoengine.CommonTokens;
import java.util.Map;
public class PromotionLevelTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="promotionLevel";
	
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
	protected PromotionLevelTokens(){
		//ensure not initialized outside the class
	}
	public  static  PromotionLevelTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PromotionLevelTokens tokens = new PromotionLevelTokens(options);
		return tokens;
		
	}
	protected PromotionLevelTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PromotionLevelTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PromotionLevelTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PromotionLevelTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PromotionLevelTokens start(){
		return new PromotionLevelTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PromotionLevelTokens allTokens(){
		
		return start()
			.withPlatform()
			.withPromotionOfferList();
	
	}
	public static PromotionLevelTokens withoutListsTokens(){
		
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
	
	public PromotionLevelTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public PromotionLevelTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String PROMOTION_OFFER_LIST = "promotionOfferList";
	public String getPromotionOfferList(){
		return PROMOTION_OFFER_LIST;
	}
	public PromotionLevelTokens withPromotionOfferList(){		
		addSimpleOptions(PROMOTION_OFFER_LIST);
		return this;
	}
	public PromotionLevelTokens analyzePromotionOfferList(){		
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
	public PromotionLevelTokens extractMoreFromPromotionOfferList(String idsSeperatedWithComma){		
		addSimpleOptions(PROMOTION_OFFER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int promotionOfferListSortCounter = 0;
	public PromotionLevelTokens sortPromotionOfferListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSortCounter++, field, descOrAsc);
		return this;
	}
	private int promotionOfferListSearchCounter = 0;
	public PromotionLevelTokens searchPromotionOfferListWith(String field, String verb, String value){		
		addSearchMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PromotionLevelTokens searchAllTextOfPromotionOfferList(String verb, String value){	
		String field = "id|name|parameter";
		addSearchMoreOptions(PROMOTION_OFFER_LIST,promotionOfferListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PromotionLevelTokens rowsPerPageOfPromotionOfferList(int rowsPerPage){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PromotionLevelTokens currentPageNumberOfPromotionOfferList(int currentPageNumber){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PromotionLevelTokens retainColumnsOfPromotionOfferList(String[] columns){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"RetainColumns",columns);
		return this;
	}
	public PromotionLevelTokens excludeColumnsOfPromotionOfferList(String[] columns){		
		addSimpleOptions(PROMOTION_OFFER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PromotionLevelTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfPromotionOfferList(verb, value);	
		return this;
	}
}

