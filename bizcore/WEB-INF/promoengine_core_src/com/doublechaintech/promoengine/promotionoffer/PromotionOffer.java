
package com.doublechaintech.promoengine.promotionoffer;

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
import com.doublechaintech.promoengine.promotion.Promotion;

@JsonSerialize(using = PromotionOfferSerializer.class)
public class PromotionOffer extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String PROMOTION_LEVEL_PROPERTY       = "promotionLevel"    ;
	public static final String PARAMETER_PROPERTY             = "parameter"         ;
	public static final String PROMOTION_PROPERTY             = "promotion"         ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="PromotionOffer";
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
	protected		PromotionLevel      	mPromotionLevel     ;
	protected		String              	mParameter          ;
	protected		Promotion           	mPromotion          ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	PromotionOffer(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPromotionLevel( null );
		setPromotion( null );

		this.changed = true;
	}
	
	public 	PromotionOffer(String name, PromotionLevel promotionLevel, String parameter, Promotion promotion)
	{
		setName(name);
		setPromotionLevel(promotionLevel);
		setParameter(parameter);
		setPromotion(promotion);
	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(PARAMETER_PROPERTY.equals(property)){
			changeParameterProperty(newValueExpr);
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
			
			
			
	protected void changeParameterProperty(String newValueExpr){
		String oldValue = getParameter();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateParameter(newValue);
		this.onChangeProperty(PARAMETER_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public PromotionOffer updateId(String id){
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
	public PromotionOffer updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setPromotionLevel(PromotionLevel promotionLevel){
		this.mPromotionLevel = promotionLevel;;
	}
	public PromotionLevel getPromotionLevel(){
		return this.mPromotionLevel;
	}
	public PromotionOffer updatePromotionLevel(PromotionLevel promotionLevel){
		this.mPromotionLevel = promotionLevel;;
		this.changed = true;
		return this;
	}
	public void mergePromotionLevel(PromotionLevel promotionLevel){
		if(promotionLevel != null) { setPromotionLevel(promotionLevel);}
	}
	
	
	public void clearPromotionLevel(){
		setPromotionLevel ( null );
		this.changed = true;
	}
	
	public void setParameter(String parameter){
		this.mParameter = trimString(parameter);;
	}
	public String getParameter(){
		return this.mParameter;
	}
	public PromotionOffer updateParameter(String parameter){
		this.mParameter = trimString(parameter);;
		this.changed = true;
		return this;
	}
	public void mergeParameter(String parameter){
		if(parameter != null) { setParameter(parameter);}
	}
	
	
	public void setPromotion(Promotion promotion){
		this.mPromotion = promotion;;
	}
	public Promotion getPromotion(){
		return this.mPromotion;
	}
	public PromotionOffer updatePromotion(Promotion promotion){
		this.mPromotion = promotion;;
		this.changed = true;
		return this;
	}
	public void mergePromotion(Promotion promotion){
		if(promotion != null) { setPromotion(promotion);}
	}
	
	
	public void clearPromotion(){
		setPromotion ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public PromotionOffer updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPromotionLevel(), internalType);
		addToEntityList(this, entityList, getPromotion(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, PROMOTION_LEVEL_PROPERTY, getPromotionLevel());
		appendKeyValuePair(result, PARAMETER_PROPERTY, getParameter());
		appendKeyValuePair(result, PROMOTION_PROPERTY, getPromotion());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PromotionOffer){
		
		
			PromotionOffer dest =(PromotionOffer)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setPromotionLevel(getPromotionLevel());
			dest.setParameter(getParameter());
			dest.setPromotion(getPromotion());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PromotionOffer){
		
			
			PromotionOffer dest =(PromotionOffer)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergePromotionLevel(getPromotionLevel());
			dest.mergeParameter(getParameter());
			dest.mergePromotion(getPromotion());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("PromotionOffer{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getPromotionLevel() != null ){
 			stringBuilder.append("\tpromotionLevel='PromotionLevel("+getPromotionLevel().getId()+")';");
 		}
		stringBuilder.append("\tparameter='"+getParameter()+"';");
		if(getPromotion() != null ){
 			stringBuilder.append("\tpromotion='Promotion("+getPromotion().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

