
package com.doublechaintech.promoengine.promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.promoengine.platform.Platform;
import com.doublechaintech.promoengine.targetaction.TargetAction;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

@JsonSerialize(using = PromotionSerializer.class)
public class Promotion extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String VALID_AFTER_PROPERTY           = "validAfter"        ;
	public static final String EXPIRE_TIME_PROPERTY           = "expireTime"        ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TARGET_USER_RULE_LIST                    = "targetUserRuleList";
	public static final String TARGET_ACTION_LIST                       = "targetActionList"  ;
	public static final String PROMOTION_OFFER_LIST                     = "promotionOfferList";

	public static final String INTERNAL_TYPE="Promotion";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		DateTime            	mValidAfter         ;
	protected		DateTime            	mExpireTime         ;
	protected		DateTime            	mLastUpdateTime     ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<TargetUserRule>	mTargetUserRuleList ;
	protected		SmartList<TargetAction>	mTargetActionList   ;
	protected		SmartList<PromotionOffer>	mPromotionOfferList ;
	
		
	public 	Promotion(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	Promotion(String name, DateTime validAfter, DateTime expireTime, DateTime lastUpdateTime, Platform platform)
	{
		setName(name);
		setValidAfter(validAfter);
		setExpireTime(expireTime);
		setLastUpdateTime(lastUpdateTime);
		setPlatform(platform);

		this.mTargetUserRuleList = new SmartList<TargetUserRule>();
		this.mTargetActionList = new SmartList<TargetAction>();
		this.mPromotionOfferList = new SmartList<PromotionOffer>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(VALID_AFTER_PROPERTY.equals(property)){
			changeValidAfterProperty(newValueExpr);
		}
		if(EXPIRE_TIME_PROPERTY.equals(property)){
			changeExpireTimeProperty(newValueExpr);
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			changeLastUpdateTimeProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeValidAfterProperty(String newValueExpr){
		DateTime oldValue = getValidAfter();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateValidAfter(newValue);
		this.onChangeProperty(VALID_AFTER_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeExpireTimeProperty(String newValueExpr){
		DateTime oldValue = getExpireTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateExpireTime(newValue);
		this.onChangeProperty(EXPIRE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeLastUpdateTimeProperty(String newValueExpr){
		DateTime oldValue = getLastUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLastUpdateTime(newValue);
		this.onChangeProperty(LAST_UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public Promotion updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public Promotion updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setValidAfter(DateTime validAfter){
		this.mValidAfter = validAfter;;
	}
	public DateTime getValidAfter(){
		return this.mValidAfter;
	}
	public Promotion updateValidAfter(DateTime validAfter){
		this.mValidAfter = validAfter;;
		this.changed = true;
		return this;
	}
	public void mergeValidAfter(DateTime validAfter){
		setValidAfter(validAfter);
	}
	
	
	public void setExpireTime(DateTime expireTime){
		this.mExpireTime = expireTime;;
	}
	public DateTime getExpireTime(){
		return this.mExpireTime;
	}
	public Promotion updateExpireTime(DateTime expireTime){
		this.mExpireTime = expireTime;;
		this.changed = true;
		return this;
	}
	public void mergeExpireTime(DateTime expireTime){
		setExpireTime(expireTime);
	}
	
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public Promotion updateLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
		this.changed = true;
		return this;
	}
	public void mergeLastUpdateTime(DateTime lastUpdateTime){
		setLastUpdateTime(lastUpdateTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Promotion updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Promotion updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<TargetUserRule> getTargetUserRuleList(){
		if(this.mTargetUserRuleList == null){
			this.mTargetUserRuleList = new SmartList<TargetUserRule>();
			this.mTargetUserRuleList.setListInternalName (TARGET_USER_RULE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mTargetUserRuleList;	
	}
	public  void setTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList){
		for( TargetUserRule targetUserRule:targetUserRuleList){
			targetUserRule.setPromotion(this);
		}

		this.mTargetUserRuleList = targetUserRuleList;
		this.mTargetUserRuleList.setListInternalName (TARGET_USER_RULE_LIST );
		
	}
	
	public  void addTargetUserRule(TargetUserRule targetUserRule){
		targetUserRule.setPromotion(this);
		getTargetUserRuleList().add(targetUserRule);
	}
	public  void addTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList){
		for( TargetUserRule targetUserRule:targetUserRuleList){
			targetUserRule.setPromotion(this);
		}
		getTargetUserRuleList().addAll(targetUserRuleList);
	}
	public  void mergeTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList){
		if(targetUserRuleList==null){
			return;
		}
		if(targetUserRuleList.isEmpty()){
			return;
		}
		addTargetUserRuleList( targetUserRuleList );
		
	}
	public  TargetUserRule removeTargetUserRule(TargetUserRule targetUserRuleIndex){
		
		int index = getTargetUserRuleList().indexOf(targetUserRuleIndex);
        if(index < 0){
        	String message = "TargetUserRule("+targetUserRuleIndex.getId()+") with version='"+targetUserRuleIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        TargetUserRule targetUserRule = getTargetUserRuleList().get(index);        
        // targetUserRule.clearPromotion(); //disconnect with Promotion
        targetUserRule.clearFromAll(); //disconnect with Promotion
		
		boolean result = getTargetUserRuleList().planToRemove(targetUserRule);
        if(!result){
        	String message = "TargetUserRule("+targetUserRuleIndex.getId()+") with version='"+targetUserRuleIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return targetUserRule;
        
	
	}
	//断舍离
	public  void breakWithTargetUserRule(TargetUserRule targetUserRule){
		
		if(targetUserRule == null){
			return;
		}
		targetUserRule.setPromotion(null);
		//getTargetUserRuleList().remove();
	
	}
	
	public  boolean hasTargetUserRule(TargetUserRule targetUserRule){
	
		return getTargetUserRuleList().contains(targetUserRule);
  
	}
	
	public void copyTargetUserRuleFrom(TargetUserRule targetUserRule) {

		TargetUserRule targetUserRuleInList = findTheTargetUserRule(targetUserRule);
		TargetUserRule newTargetUserRule = new TargetUserRule();
		targetUserRuleInList.copyTo(newTargetUserRule);
		newTargetUserRule.setVersion(0);//will trigger copy
		getTargetUserRuleList().add(newTargetUserRule);
		addItemToFlexiableObject(COPIED_CHILD, newTargetUserRule);
	}
	
	public  TargetUserRule findTheTargetUserRule(TargetUserRule targetUserRule){
		
		int index =  getTargetUserRuleList().indexOf(targetUserRule);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "TargetUserRule("+targetUserRule.getId()+") with version='"+targetUserRule.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTargetUserRuleList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTargetUserRuleList(){
		getTargetUserRuleList().clear();
	}
	
	
	


	public  SmartList<TargetAction> getTargetActionList(){
		if(this.mTargetActionList == null){
			this.mTargetActionList = new SmartList<TargetAction>();
			this.mTargetActionList.setListInternalName (TARGET_ACTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mTargetActionList;	
	}
	public  void setTargetActionList(SmartList<TargetAction> targetActionList){
		for( TargetAction targetAction:targetActionList){
			targetAction.setPromotion(this);
		}

		this.mTargetActionList = targetActionList;
		this.mTargetActionList.setListInternalName (TARGET_ACTION_LIST );
		
	}
	
	public  void addTargetAction(TargetAction targetAction){
		targetAction.setPromotion(this);
		getTargetActionList().add(targetAction);
	}
	public  void addTargetActionList(SmartList<TargetAction> targetActionList){
		for( TargetAction targetAction:targetActionList){
			targetAction.setPromotion(this);
		}
		getTargetActionList().addAll(targetActionList);
	}
	public  void mergeTargetActionList(SmartList<TargetAction> targetActionList){
		if(targetActionList==null){
			return;
		}
		if(targetActionList.isEmpty()){
			return;
		}
		addTargetActionList( targetActionList );
		
	}
	public  TargetAction removeTargetAction(TargetAction targetActionIndex){
		
		int index = getTargetActionList().indexOf(targetActionIndex);
        if(index < 0){
        	String message = "TargetAction("+targetActionIndex.getId()+") with version='"+targetActionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        TargetAction targetAction = getTargetActionList().get(index);        
        // targetAction.clearPromotion(); //disconnect with Promotion
        targetAction.clearFromAll(); //disconnect with Promotion
		
		boolean result = getTargetActionList().planToRemove(targetAction);
        if(!result){
        	String message = "TargetAction("+targetActionIndex.getId()+") with version='"+targetActionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return targetAction;
        
	
	}
	//断舍离
	public  void breakWithTargetAction(TargetAction targetAction){
		
		if(targetAction == null){
			return;
		}
		targetAction.setPromotion(null);
		//getTargetActionList().remove();
	
	}
	
	public  boolean hasTargetAction(TargetAction targetAction){
	
		return getTargetActionList().contains(targetAction);
  
	}
	
	public void copyTargetActionFrom(TargetAction targetAction) {

		TargetAction targetActionInList = findTheTargetAction(targetAction);
		TargetAction newTargetAction = new TargetAction();
		targetActionInList.copyTo(newTargetAction);
		newTargetAction.setVersion(0);//will trigger copy
		getTargetActionList().add(newTargetAction);
		addItemToFlexiableObject(COPIED_CHILD, newTargetAction);
	}
	
	public  TargetAction findTheTargetAction(TargetAction targetAction){
		
		int index =  getTargetActionList().indexOf(targetAction);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "TargetAction("+targetAction.getId()+") with version='"+targetAction.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTargetActionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTargetActionList(){
		getTargetActionList().clear();
	}
	
	
	


	public  SmartList<PromotionOffer> getPromotionOfferList(){
		if(this.mPromotionOfferList == null){
			this.mPromotionOfferList = new SmartList<PromotionOffer>();
			this.mPromotionOfferList.setListInternalName (PROMOTION_OFFER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mPromotionOfferList;	
	}
	public  void setPromotionOfferList(SmartList<PromotionOffer> promotionOfferList){
		for( PromotionOffer promotionOffer:promotionOfferList){
			promotionOffer.setPromotion(this);
		}

		this.mPromotionOfferList = promotionOfferList;
		this.mPromotionOfferList.setListInternalName (PROMOTION_OFFER_LIST );
		
	}
	
	public  void addPromotionOffer(PromotionOffer promotionOffer){
		promotionOffer.setPromotion(this);
		getPromotionOfferList().add(promotionOffer);
	}
	public  void addPromotionOfferList(SmartList<PromotionOffer> promotionOfferList){
		for( PromotionOffer promotionOffer:promotionOfferList){
			promotionOffer.setPromotion(this);
		}
		getPromotionOfferList().addAll(promotionOfferList);
	}
	public  void mergePromotionOfferList(SmartList<PromotionOffer> promotionOfferList){
		if(promotionOfferList==null){
			return;
		}
		if(promotionOfferList.isEmpty()){
			return;
		}
		addPromotionOfferList( promotionOfferList );
		
	}
	public  PromotionOffer removePromotionOffer(PromotionOffer promotionOfferIndex){
		
		int index = getPromotionOfferList().indexOf(promotionOfferIndex);
        if(index < 0){
        	String message = "PromotionOffer("+promotionOfferIndex.getId()+") with version='"+promotionOfferIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PromotionOffer promotionOffer = getPromotionOfferList().get(index);        
        // promotionOffer.clearPromotion(); //disconnect with Promotion
        promotionOffer.clearFromAll(); //disconnect with Promotion
		
		boolean result = getPromotionOfferList().planToRemove(promotionOffer);
        if(!result){
        	String message = "PromotionOffer("+promotionOfferIndex.getId()+") with version='"+promotionOfferIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return promotionOffer;
        
	
	}
	//断舍离
	public  void breakWithPromotionOffer(PromotionOffer promotionOffer){
		
		if(promotionOffer == null){
			return;
		}
		promotionOffer.setPromotion(null);
		//getPromotionOfferList().remove();
	
	}
	
	public  boolean hasPromotionOffer(PromotionOffer promotionOffer){
	
		return getPromotionOfferList().contains(promotionOffer);
  
	}
	
	public void copyPromotionOfferFrom(PromotionOffer promotionOffer) {

		PromotionOffer promotionOfferInList = findThePromotionOffer(promotionOffer);
		PromotionOffer newPromotionOffer = new PromotionOffer();
		promotionOfferInList.copyTo(newPromotionOffer);
		newPromotionOffer.setVersion(0);//will trigger copy
		getPromotionOfferList().add(newPromotionOffer);
		addItemToFlexiableObject(COPIED_CHILD, newPromotionOffer);
	}
	
	public  PromotionOffer findThePromotionOffer(PromotionOffer promotionOffer){
		
		int index =  getPromotionOfferList().indexOf(promotionOffer);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PromotionOffer("+promotionOffer.getId()+") with version='"+promotionOffer.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPromotionOfferList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPromotionOfferList(){
		getPromotionOfferList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getTargetUserRuleList(), internalType);
		collectFromList(this, entityList, getTargetActionList(), internalType);
		collectFromList(this, entityList, getPromotionOfferList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTargetUserRuleList());
		listOfList.add( getTargetActionList());
		listOfList.add( getPromotionOfferList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, VALID_AFTER_PROPERTY, getValidAfter());
		appendKeyValuePair(result, EXPIRE_TIME_PROPERTY, getExpireTime());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TARGET_USER_RULE_LIST, getTargetUserRuleList());
		if(!getTargetUserRuleList().isEmpty()){
			appendKeyValuePair(result, "targetUserRuleCount", getTargetUserRuleList().getTotalCount());
			appendKeyValuePair(result, "targetUserRuleCurrentPageNumber", getTargetUserRuleList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, TARGET_ACTION_LIST, getTargetActionList());
		if(!getTargetActionList().isEmpty()){
			appendKeyValuePair(result, "targetActionCount", getTargetActionList().getTotalCount());
			appendKeyValuePair(result, "targetActionCurrentPageNumber", getTargetActionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PROMOTION_OFFER_LIST, getPromotionOfferList());
		if(!getPromotionOfferList().isEmpty()){
			appendKeyValuePair(result, "promotionOfferCount", getPromotionOfferList().getTotalCount());
			appendKeyValuePair(result, "promotionOfferCurrentPageNumber", getPromotionOfferList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Promotion){
		
		
			Promotion dest =(Promotion)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setValidAfter(getValidAfter());
			dest.setExpireTime(getExpireTime());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTargetUserRuleList(getTargetUserRuleList());
			dest.setTargetActionList(getTargetActionList());
			dest.setPromotionOfferList(getPromotionOfferList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Promotion){
		
			
			Promotion dest =(Promotion)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeValidAfter(getValidAfter());
			dest.mergeExpireTime(getExpireTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTargetUserRuleList(getTargetUserRuleList());
			dest.mergeTargetActionList(getTargetActionList());
			dest.mergePromotionOfferList(getPromotionOfferList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Promotion{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tvalidAfter='"+getValidAfter()+"';");
		stringBuilder.append("\texpireTime='"+getExpireTime()+"';");
		stringBuilder.append("\tlastUpdateTime='"+getLastUpdateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

