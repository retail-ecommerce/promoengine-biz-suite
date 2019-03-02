
package com.doublechaintech.promoengine.promotionlevel;

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
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;

@JsonSerialize(using = PromotionLevelSerializer.class)
public class PromotionLevel extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String PROMOTION_OFFER_LIST                     = "promotionOfferList";

	public static final String INTERNAL_TYPE="PromotionLevel";
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
	
	
	protected		SmartList<PromotionOffer>	mPromotionOfferList ;
	
		
	public 	PromotionLevel(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	PromotionLevel(String name, DateTime lastUpdateTime, Platform platform)
	{
		setName(name);
		setLastUpdateTime(lastUpdateTime);
		setPlatform(platform);

		this.mPromotionOfferList = new SmartList<PromotionOffer>();	
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
	public PromotionLevel updateId(String id){
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
	public PromotionLevel updateName(String name){
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
	public PromotionLevel updateLastUpdateTime(DateTime lastUpdateTime){
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
	public PromotionLevel updatePlatform(Platform platform){
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
	public PromotionLevel updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
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
			promotionOffer.setPromotionLevel(this);
		}

		this.mPromotionOfferList = promotionOfferList;
		this.mPromotionOfferList.setListInternalName (PROMOTION_OFFER_LIST );
		
	}
	
	public  void addPromotionOffer(PromotionOffer promotionOffer){
		promotionOffer.setPromotionLevel(this);
		getPromotionOfferList().add(promotionOffer);
	}
	public  void addPromotionOfferList(SmartList<PromotionOffer> promotionOfferList){
		for( PromotionOffer promotionOffer:promotionOfferList){
			promotionOffer.setPromotionLevel(this);
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
        // promotionOffer.clearPromotionLevel(); //disconnect with PromotionLevel
        promotionOffer.clearFromAll(); //disconnect with PromotionLevel
		
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
		promotionOffer.setPromotionLevel(null);
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
		collectFromList(this, entityList, getPromotionOfferList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getPromotionOfferList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, PROMOTION_OFFER_LIST, getPromotionOfferList());
		if(!getPromotionOfferList().isEmpty()){
			appendKeyValuePair(result, "promotionOfferCount", getPromotionOfferList().getTotalCount());
			appendKeyValuePair(result, "promotionOfferCurrentPageNumber", getPromotionOfferList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PromotionLevel){
		
		
			PromotionLevel dest =(PromotionLevel)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setPromotionOfferList(getPromotionOfferList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PromotionLevel){
		
			
			PromotionLevel dest =(PromotionLevel)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergePromotionOfferList(getPromotionOfferList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("PromotionLevel{");
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

