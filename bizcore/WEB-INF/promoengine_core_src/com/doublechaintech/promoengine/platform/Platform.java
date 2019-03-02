
package com.doublechaintech.promoengine.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;
import com.doublechaintech.promoengine.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;

@JsonSerialize(using = PlatformSerializer.class)
public class Platform extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String INTRODUCTION_PROPERTY          = "introduction"      ;
	public static final String CURRENT_VERSION_PROPERTY       = "currentVersion"    ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String APPLY_CONDITION_LIST                     = "applyConditionList";
	public static final String ACTION_TYPE_LIST                         = "actionTypeList"    ;
	public static final String PROMOTION_LEVEL_LIST                     = "promotionLevelList";
	public static final String PROMOTION_LIST                           = "promotionList"     ;

	public static final String INTERNAL_TYPE="Platform";
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
	protected		String              	mIntroduction       ;
	protected		String              	mCurrentVersion     ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<ApplyCondition>	mApplyConditionList ;
	protected		SmartList<ActionType>	mActionTypeList     ;
	protected		SmartList<PromotionLevel>	mPromotionLevelList ;
	protected		SmartList<Promotion>	mPromotionList      ;
	
		
	public 	Platform(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){

		this.changed = true;
	}
	
	public 	Platform(String name, String introduction, String currentVersion)
	{
		setName(name);
		setIntroduction(introduction);
		setCurrentVersion(currentVersion);

		this.mApplyConditionList = new SmartList<ApplyCondition>();
		this.mActionTypeList = new SmartList<ActionType>();
		this.mPromotionLevelList = new SmartList<PromotionLevel>();
		this.mPromotionList = new SmartList<Promotion>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(INTRODUCTION_PROPERTY.equals(property)){
			changeIntroductionProperty(newValueExpr);
		}
		if(CURRENT_VERSION_PROPERTY.equals(property)){
			changeCurrentVersionProperty(newValueExpr);
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
			
			
			
	protected void changeIntroductionProperty(String newValueExpr){
		String oldValue = getIntroduction();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateIntroduction(newValue);
		this.onChangeProperty(INTRODUCTION_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeCurrentVersionProperty(String newValueExpr){
		String oldValue = getCurrentVersion();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCurrentVersion(newValue);
		this.onChangeProperty(CURRENT_VERSION_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public Platform updateId(String id){
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
	public Platform updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setIntroduction(String introduction){
		this.mIntroduction = trimString(introduction);;
	}
	public String getIntroduction(){
		return this.mIntroduction;
	}
	public Platform updateIntroduction(String introduction){
		this.mIntroduction = trimString(introduction);;
		this.changed = true;
		return this;
	}
	public void mergeIntroduction(String introduction){
		if(introduction != null) { setIntroduction(introduction);}
	}
	
	
	public void setCurrentVersion(String currentVersion){
		this.mCurrentVersion = trimString(currentVersion);;
	}
	public String getCurrentVersion(){
		return this.mCurrentVersion;
	}
	public Platform updateCurrentVersion(String currentVersion){
		this.mCurrentVersion = trimString(currentVersion);;
		this.changed = true;
		return this;
	}
	public void mergeCurrentVersion(String currentVersion){
		if(currentVersion != null) { setCurrentVersion(currentVersion);}
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Platform updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<ApplyCondition> getApplyConditionList(){
		if(this.mApplyConditionList == null){
			this.mApplyConditionList = new SmartList<ApplyCondition>();
			this.mApplyConditionList.setListInternalName (APPLY_CONDITION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mApplyConditionList;	
	}
	public  void setApplyConditionList(SmartList<ApplyCondition> applyConditionList){
		for( ApplyCondition applyCondition:applyConditionList){
			applyCondition.setPlatform(this);
		}

		this.mApplyConditionList = applyConditionList;
		this.mApplyConditionList.setListInternalName (APPLY_CONDITION_LIST );
		
	}
	
	public  void addApplyCondition(ApplyCondition applyCondition){
		applyCondition.setPlatform(this);
		getApplyConditionList().add(applyCondition);
	}
	public  void addApplyConditionList(SmartList<ApplyCondition> applyConditionList){
		for( ApplyCondition applyCondition:applyConditionList){
			applyCondition.setPlatform(this);
		}
		getApplyConditionList().addAll(applyConditionList);
	}
	public  void mergeApplyConditionList(SmartList<ApplyCondition> applyConditionList){
		if(applyConditionList==null){
			return;
		}
		if(applyConditionList.isEmpty()){
			return;
		}
		addApplyConditionList( applyConditionList );
		
	}
	public  ApplyCondition removeApplyCondition(ApplyCondition applyConditionIndex){
		
		int index = getApplyConditionList().indexOf(applyConditionIndex);
        if(index < 0){
        	String message = "ApplyCondition("+applyConditionIndex.getId()+") with version='"+applyConditionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ApplyCondition applyCondition = getApplyConditionList().get(index);        
        // applyCondition.clearPlatform(); //disconnect with Platform
        applyCondition.clearFromAll(); //disconnect with Platform
		
		boolean result = getApplyConditionList().planToRemove(applyCondition);
        if(!result){
        	String message = "ApplyCondition("+applyConditionIndex.getId()+") with version='"+applyConditionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return applyCondition;
        
	
	}
	//断舍离
	public  void breakWithApplyCondition(ApplyCondition applyCondition){
		
		if(applyCondition == null){
			return;
		}
		applyCondition.setPlatform(null);
		//getApplyConditionList().remove();
	
	}
	
	public  boolean hasApplyCondition(ApplyCondition applyCondition){
	
		return getApplyConditionList().contains(applyCondition);
  
	}
	
	public void copyApplyConditionFrom(ApplyCondition applyCondition) {

		ApplyCondition applyConditionInList = findTheApplyCondition(applyCondition);
		ApplyCondition newApplyCondition = new ApplyCondition();
		applyConditionInList.copyTo(newApplyCondition);
		newApplyCondition.setVersion(0);//will trigger copy
		getApplyConditionList().add(newApplyCondition);
		addItemToFlexiableObject(COPIED_CHILD, newApplyCondition);
	}
	
	public  ApplyCondition findTheApplyCondition(ApplyCondition applyCondition){
		
		int index =  getApplyConditionList().indexOf(applyCondition);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ApplyCondition("+applyCondition.getId()+") with version='"+applyCondition.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getApplyConditionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpApplyConditionList(){
		getApplyConditionList().clear();
	}
	
	
	


	public  SmartList<ActionType> getActionTypeList(){
		if(this.mActionTypeList == null){
			this.mActionTypeList = new SmartList<ActionType>();
			this.mActionTypeList.setListInternalName (ACTION_TYPE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mActionTypeList;	
	}
	public  void setActionTypeList(SmartList<ActionType> actionTypeList){
		for( ActionType actionType:actionTypeList){
			actionType.setPlatform(this);
		}

		this.mActionTypeList = actionTypeList;
		this.mActionTypeList.setListInternalName (ACTION_TYPE_LIST );
		
	}
	
	public  void addActionType(ActionType actionType){
		actionType.setPlatform(this);
		getActionTypeList().add(actionType);
	}
	public  void addActionTypeList(SmartList<ActionType> actionTypeList){
		for( ActionType actionType:actionTypeList){
			actionType.setPlatform(this);
		}
		getActionTypeList().addAll(actionTypeList);
	}
	public  void mergeActionTypeList(SmartList<ActionType> actionTypeList){
		if(actionTypeList==null){
			return;
		}
		if(actionTypeList.isEmpty()){
			return;
		}
		addActionTypeList( actionTypeList );
		
	}
	public  ActionType removeActionType(ActionType actionTypeIndex){
		
		int index = getActionTypeList().indexOf(actionTypeIndex);
        if(index < 0){
        	String message = "ActionType("+actionTypeIndex.getId()+") with version='"+actionTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ActionType actionType = getActionTypeList().get(index);        
        // actionType.clearPlatform(); //disconnect with Platform
        actionType.clearFromAll(); //disconnect with Platform
		
		boolean result = getActionTypeList().planToRemove(actionType);
        if(!result){
        	String message = "ActionType("+actionTypeIndex.getId()+") with version='"+actionTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return actionType;
        
	
	}
	//断舍离
	public  void breakWithActionType(ActionType actionType){
		
		if(actionType == null){
			return;
		}
		actionType.setPlatform(null);
		//getActionTypeList().remove();
	
	}
	
	public  boolean hasActionType(ActionType actionType){
	
		return getActionTypeList().contains(actionType);
  
	}
	
	public void copyActionTypeFrom(ActionType actionType) {

		ActionType actionTypeInList = findTheActionType(actionType);
		ActionType newActionType = new ActionType();
		actionTypeInList.copyTo(newActionType);
		newActionType.setVersion(0);//will trigger copy
		getActionTypeList().add(newActionType);
		addItemToFlexiableObject(COPIED_CHILD, newActionType);
	}
	
	public  ActionType findTheActionType(ActionType actionType){
		
		int index =  getActionTypeList().indexOf(actionType);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ActionType("+actionType.getId()+") with version='"+actionType.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getActionTypeList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpActionTypeList(){
		getActionTypeList().clear();
	}
	
	
	


	public  SmartList<PromotionLevel> getPromotionLevelList(){
		if(this.mPromotionLevelList == null){
			this.mPromotionLevelList = new SmartList<PromotionLevel>();
			this.mPromotionLevelList.setListInternalName (PROMOTION_LEVEL_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mPromotionLevelList;	
	}
	public  void setPromotionLevelList(SmartList<PromotionLevel> promotionLevelList){
		for( PromotionLevel promotionLevel:promotionLevelList){
			promotionLevel.setPlatform(this);
		}

		this.mPromotionLevelList = promotionLevelList;
		this.mPromotionLevelList.setListInternalName (PROMOTION_LEVEL_LIST );
		
	}
	
	public  void addPromotionLevel(PromotionLevel promotionLevel){
		promotionLevel.setPlatform(this);
		getPromotionLevelList().add(promotionLevel);
	}
	public  void addPromotionLevelList(SmartList<PromotionLevel> promotionLevelList){
		for( PromotionLevel promotionLevel:promotionLevelList){
			promotionLevel.setPlatform(this);
		}
		getPromotionLevelList().addAll(promotionLevelList);
	}
	public  void mergePromotionLevelList(SmartList<PromotionLevel> promotionLevelList){
		if(promotionLevelList==null){
			return;
		}
		if(promotionLevelList.isEmpty()){
			return;
		}
		addPromotionLevelList( promotionLevelList );
		
	}
	public  PromotionLevel removePromotionLevel(PromotionLevel promotionLevelIndex){
		
		int index = getPromotionLevelList().indexOf(promotionLevelIndex);
        if(index < 0){
        	String message = "PromotionLevel("+promotionLevelIndex.getId()+") with version='"+promotionLevelIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PromotionLevel promotionLevel = getPromotionLevelList().get(index);        
        // promotionLevel.clearPlatform(); //disconnect with Platform
        promotionLevel.clearFromAll(); //disconnect with Platform
		
		boolean result = getPromotionLevelList().planToRemove(promotionLevel);
        if(!result){
        	String message = "PromotionLevel("+promotionLevelIndex.getId()+") with version='"+promotionLevelIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return promotionLevel;
        
	
	}
	//断舍离
	public  void breakWithPromotionLevel(PromotionLevel promotionLevel){
		
		if(promotionLevel == null){
			return;
		}
		promotionLevel.setPlatform(null);
		//getPromotionLevelList().remove();
	
	}
	
	public  boolean hasPromotionLevel(PromotionLevel promotionLevel){
	
		return getPromotionLevelList().contains(promotionLevel);
  
	}
	
	public void copyPromotionLevelFrom(PromotionLevel promotionLevel) {

		PromotionLevel promotionLevelInList = findThePromotionLevel(promotionLevel);
		PromotionLevel newPromotionLevel = new PromotionLevel();
		promotionLevelInList.copyTo(newPromotionLevel);
		newPromotionLevel.setVersion(0);//will trigger copy
		getPromotionLevelList().add(newPromotionLevel);
		addItemToFlexiableObject(COPIED_CHILD, newPromotionLevel);
	}
	
	public  PromotionLevel findThePromotionLevel(PromotionLevel promotionLevel){
		
		int index =  getPromotionLevelList().indexOf(promotionLevel);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PromotionLevel("+promotionLevel.getId()+") with version='"+promotionLevel.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPromotionLevelList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPromotionLevelList(){
		getPromotionLevelList().clear();
	}
	
	
	


	public  SmartList<Promotion> getPromotionList(){
		if(this.mPromotionList == null){
			this.mPromotionList = new SmartList<Promotion>();
			this.mPromotionList.setListInternalName (PROMOTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mPromotionList;	
	}
	public  void setPromotionList(SmartList<Promotion> promotionList){
		for( Promotion promotion:promotionList){
			promotion.setPlatform(this);
		}

		this.mPromotionList = promotionList;
		this.mPromotionList.setListInternalName (PROMOTION_LIST );
		
	}
	
	public  void addPromotion(Promotion promotion){
		promotion.setPlatform(this);
		getPromotionList().add(promotion);
	}
	public  void addPromotionList(SmartList<Promotion> promotionList){
		for( Promotion promotion:promotionList){
			promotion.setPlatform(this);
		}
		getPromotionList().addAll(promotionList);
	}
	public  void mergePromotionList(SmartList<Promotion> promotionList){
		if(promotionList==null){
			return;
		}
		if(promotionList.isEmpty()){
			return;
		}
		addPromotionList( promotionList );
		
	}
	public  Promotion removePromotion(Promotion promotionIndex){
		
		int index = getPromotionList().indexOf(promotionIndex);
        if(index < 0){
        	String message = "Promotion("+promotionIndex.getId()+") with version='"+promotionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Promotion promotion = getPromotionList().get(index);        
        // promotion.clearPlatform(); //disconnect with Platform
        promotion.clearFromAll(); //disconnect with Platform
		
		boolean result = getPromotionList().planToRemove(promotion);
        if(!result){
        	String message = "Promotion("+promotionIndex.getId()+") with version='"+promotionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return promotion;
        
	
	}
	//断舍离
	public  void breakWithPromotion(Promotion promotion){
		
		if(promotion == null){
			return;
		}
		promotion.setPlatform(null);
		//getPromotionList().remove();
	
	}
	
	public  boolean hasPromotion(Promotion promotion){
	
		return getPromotionList().contains(promotion);
  
	}
	
	public void copyPromotionFrom(Promotion promotion) {

		Promotion promotionInList = findThePromotion(promotion);
		Promotion newPromotion = new Promotion();
		promotionInList.copyTo(newPromotion);
		newPromotion.setVersion(0);//will trigger copy
		getPromotionList().add(newPromotion);
		addItemToFlexiableObject(COPIED_CHILD, newPromotion);
	}
	
	public  Promotion findThePromotion(Promotion promotion){
		
		int index =  getPromotionList().indexOf(promotion);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Promotion("+promotion.getId()+") with version='"+promotion.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPromotionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPromotionList(){
		getPromotionList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){


		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getApplyConditionList(), internalType);
		collectFromList(this, entityList, getActionTypeList(), internalType);
		collectFromList(this, entityList, getPromotionLevelList(), internalType);
		collectFromList(this, entityList, getPromotionList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getApplyConditionList());
		listOfList.add( getActionTypeList());
		listOfList.add( getPromotionLevelList());
		listOfList.add( getPromotionList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, INTRODUCTION_PROPERTY, getIntroduction());
		appendKeyValuePair(result, CURRENT_VERSION_PROPERTY, getCurrentVersion());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, APPLY_CONDITION_LIST, getApplyConditionList());
		if(!getApplyConditionList().isEmpty()){
			appendKeyValuePair(result, "applyConditionCount", getApplyConditionList().getTotalCount());
			appendKeyValuePair(result, "applyConditionCurrentPageNumber", getApplyConditionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, ACTION_TYPE_LIST, getActionTypeList());
		if(!getActionTypeList().isEmpty()){
			appendKeyValuePair(result, "actionTypeCount", getActionTypeList().getTotalCount());
			appendKeyValuePair(result, "actionTypeCurrentPageNumber", getActionTypeList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PROMOTION_LEVEL_LIST, getPromotionLevelList());
		if(!getPromotionLevelList().isEmpty()){
			appendKeyValuePair(result, "promotionLevelCount", getPromotionLevelList().getTotalCount());
			appendKeyValuePair(result, "promotionLevelCurrentPageNumber", getPromotionLevelList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PROMOTION_LIST, getPromotionList());
		if(!getPromotionList().isEmpty()){
			appendKeyValuePair(result, "promotionCount", getPromotionList().getTotalCount());
			appendKeyValuePair(result, "promotionCurrentPageNumber", getPromotionList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
		
			Platform dest =(Platform)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setIntroduction(getIntroduction());
			dest.setCurrentVersion(getCurrentVersion());
			dest.setVersion(getVersion());
			dest.setApplyConditionList(getApplyConditionList());
			dest.setActionTypeList(getActionTypeList());
			dest.setPromotionLevelList(getPromotionLevelList());
			dest.setPromotionList(getPromotionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeIntroduction(getIntroduction());
			dest.mergeCurrentVersion(getCurrentVersion());
			dest.mergeVersion(getVersion());
			dest.mergeApplyConditionList(getApplyConditionList());
			dest.mergeActionTypeList(getActionTypeList());
			dest.mergePromotionLevelList(getPromotionLevelList());
			dest.mergePromotionList(getPromotionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Platform{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tintroduction='"+getIntroduction()+"';");
		stringBuilder.append("\tcurrentVersion='"+getCurrentVersion()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

