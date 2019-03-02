
package com.doublechaintech.promoengine.applycondition;

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
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;

@JsonSerialize(using = ApplyConditionSerializer.class)
public class ApplyCondition extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TARGET_USER_RULE_LIST                    = "targetUserRuleList";

	public static final String INTERNAL_TYPE="ApplyCondition";
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
	protected		DateTime            	mLastUpdateTime     ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<TargetUserRule>	mTargetUserRuleList ;
	
		
	public 	ApplyCondition(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	ApplyCondition(String name, DateTime lastUpdateTime, Platform platform)
	{
		setName(name);
		setLastUpdateTime(lastUpdateTime);
		setPlatform(platform);

		this.mTargetUserRuleList = new SmartList<TargetUserRule>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
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
	public ApplyCondition updateId(String id){
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
	public ApplyCondition updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public ApplyCondition updateLastUpdateTime(DateTime lastUpdateTime){
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
	public ApplyCondition updatePlatform(Platform platform){
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
	public ApplyCondition updateVersion(int version){
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
			targetUserRule.setApplyCondition(this);
		}

		this.mTargetUserRuleList = targetUserRuleList;
		this.mTargetUserRuleList.setListInternalName (TARGET_USER_RULE_LIST );
		
	}
	
	public  void addTargetUserRule(TargetUserRule targetUserRule){
		targetUserRule.setApplyCondition(this);
		getTargetUserRuleList().add(targetUserRule);
	}
	public  void addTargetUserRuleList(SmartList<TargetUserRule> targetUserRuleList){
		for( TargetUserRule targetUserRule:targetUserRuleList){
			targetUserRule.setApplyCondition(this);
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
        // targetUserRule.clearApplyCondition(); //disconnect with ApplyCondition
        targetUserRule.clearFromAll(); //disconnect with ApplyCondition
		
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
		targetUserRule.setApplyCondition(null);
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
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getTargetUserRuleList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTargetUserRuleList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TARGET_USER_RULE_LIST, getTargetUserRuleList());
		if(!getTargetUserRuleList().isEmpty()){
			appendKeyValuePair(result, "targetUserRuleCount", getTargetUserRuleList().getTotalCount());
			appendKeyValuePair(result, "targetUserRuleCurrentPageNumber", getTargetUserRuleList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ApplyCondition){
		
		
			ApplyCondition dest =(ApplyCondition)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTargetUserRuleList(getTargetUserRuleList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ApplyCondition){
		
			
			ApplyCondition dest =(ApplyCondition)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTargetUserRuleList(getTargetUserRuleList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ApplyCondition{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
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

