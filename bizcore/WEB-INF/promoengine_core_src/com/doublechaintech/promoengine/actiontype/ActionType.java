
package com.doublechaintech.promoengine.actiontype;

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

@JsonSerialize(using = ActionTypeSerializer.class)
public class ActionType extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TARGET_ACTION_LIST                       = "targetActionList"  ;

	public static final String INTERNAL_TYPE="ActionType";
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
	
	
	protected		SmartList<TargetAction>	mTargetActionList   ;
	
		
	public 	ActionType(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	ActionType(String name, DateTime lastUpdateTime, Platform platform)
	{
		setName(name);
		setLastUpdateTime(lastUpdateTime);
		setPlatform(platform);

		this.mTargetActionList = new SmartList<TargetAction>();	
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
	public ActionType updateId(String id){
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
	public ActionType updateName(String name){
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
	public ActionType updateLastUpdateTime(DateTime lastUpdateTime){
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
	public ActionType updatePlatform(Platform platform){
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
	public ActionType updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
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
			targetAction.setAction(this);
		}

		this.mTargetActionList = targetActionList;
		this.mTargetActionList.setListInternalName (TARGET_ACTION_LIST );
		
	}
	
	public  void addTargetAction(TargetAction targetAction){
		targetAction.setAction(this);
		getTargetActionList().add(targetAction);
	}
	public  void addTargetActionList(SmartList<TargetAction> targetActionList){
		for( TargetAction targetAction:targetActionList){
			targetAction.setAction(this);
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
        // targetAction.clearAction(); //disconnect with Action
        targetAction.clearFromAll(); //disconnect with Action
		
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
		targetAction.setAction(null);
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
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getTargetActionList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTargetActionList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TARGET_ACTION_LIST, getTargetActionList());
		if(!getTargetActionList().isEmpty()){
			appendKeyValuePair(result, "targetActionCount", getTargetActionList().getTotalCount());
			appendKeyValuePair(result, "targetActionCurrentPageNumber", getTargetActionList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ActionType){
		
		
			ActionType dest =(ActionType)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTargetActionList(getTargetActionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ActionType){
		
			
			ActionType dest =(ActionType)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTargetActionList(getTargetActionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ActionType{");
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

