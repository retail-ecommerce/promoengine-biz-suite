package com.doublechaintech.promoengine.promotionoffer;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class PromotionOfferForm extends BaseForm {
	
	
	public PromotionOfferForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PromotionOfferForm promotionOfferIdField(String parameterName, String initValue){
		FormField field = idFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionOfferForm promotionOfferIdField(String initValue){
		return promotionOfferIdField("promotionOfferId",initValue);
	}
	public PromotionOfferForm promotionOfferIdField(){
		return promotionOfferIdField("promotionOfferId","");
	}


	public PromotionOfferForm nameField(String parameterName, String initValue){
		FormField field = nameFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionOfferForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PromotionOfferForm nameField(){
		return nameField("name","");
	}


	public PromotionOfferForm promotionLevelIdField(String parameterName, String initValue){
		FormField field = promotionLevelIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionOfferForm promotionLevelIdField(String initValue){
		return promotionLevelIdField("promotionLevelId",initValue);
	}
	public PromotionOfferForm promotionLevelIdField(){
		return promotionLevelIdField("promotionLevelId","");
	}


	public PromotionOfferForm parameterField(String parameterName, String initValue){
		FormField field = parameterFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionOfferForm parameterField(String initValue){
		return parameterField("parameter",initValue);
	}
	public PromotionOfferForm parameterField(){
		return parameterField("parameter","");
	}


	public PromotionOfferForm promotionIdField(String parameterName, String initValue){
		FormField field = promotionIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionOfferForm promotionIdField(String initValue){
		return promotionIdField("promotionId",initValue);
	}
	public PromotionOfferForm promotionIdField(){
		return promotionIdField("promotionId","");
	}

	
	


	public PromotionOfferForm promotionLevelIdFieldOfPromotionLevel(String parameterName, String initValue){
		FormField field =  idFromPromotionLevel(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm promotionLevelIdFieldOfPromotionLevel(String initValue){
		return promotionLevelIdFieldOfPromotionLevel("promotionLevelId",initValue);
	}
	public PromotionOfferForm promotionLevelIdFieldOfPromotionLevel(){
		return promotionLevelIdFieldOfPromotionLevel("promotionLevelId","");
	}


	public PromotionOfferForm nameFieldOfPromotionLevel(String parameterName, String initValue){
		FormField field =  nameFromPromotionLevel(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm nameFieldOfPromotionLevel(String initValue){
		return nameFieldOfPromotionLevel("name",initValue);
	}
	public PromotionOfferForm nameFieldOfPromotionLevel(){
		return nameFieldOfPromotionLevel("name","");
	}


	public PromotionOfferForm lastUpdateTimeFieldOfPromotionLevel(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotionLevel(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm lastUpdateTimeFieldOfPromotionLevel(String initValue){
		return lastUpdateTimeFieldOfPromotionLevel("lastUpdateTime",initValue);
	}
	public PromotionOfferForm lastUpdateTimeFieldOfPromotionLevel(){
		return lastUpdateTimeFieldOfPromotionLevel("lastUpdateTime","");
	}


	public PromotionOfferForm platformIdFieldOfPromotionLevel(String parameterName, String initValue){
		FormField field =  platformIdFromPromotionLevel(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm platformIdFieldOfPromotionLevel(String initValue){
		return platformIdFieldOfPromotionLevel("platformId",initValue);
	}
	public PromotionOfferForm platformIdFieldOfPromotionLevel(){
		return platformIdFieldOfPromotionLevel("platformId","");
	}


	public PromotionOfferForm promotionIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  idFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm promotionIdFieldOfPromotion(String initValue){
		return promotionIdFieldOfPromotion("promotionId",initValue);
	}
	public PromotionOfferForm promotionIdFieldOfPromotion(){
		return promotionIdFieldOfPromotion("promotionId","");
	}


	public PromotionOfferForm nameFieldOfPromotion(String parameterName, String initValue){
		FormField field =  nameFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm nameFieldOfPromotion(String initValue){
		return nameFieldOfPromotion("name",initValue);
	}
	public PromotionOfferForm nameFieldOfPromotion(){
		return nameFieldOfPromotion("name","");
	}


	public PromotionOfferForm validAfterFieldOfPromotion(String parameterName, String initValue){
		FormField field =  validAfterFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm validAfterFieldOfPromotion(String initValue){
		return validAfterFieldOfPromotion("validAfter",initValue);
	}
	public PromotionOfferForm validAfterFieldOfPromotion(){
		return validAfterFieldOfPromotion("validAfter","");
	}


	public PromotionOfferForm expireTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  expireTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm expireTimeFieldOfPromotion(String initValue){
		return expireTimeFieldOfPromotion("expireTime",initValue);
	}
	public PromotionOfferForm expireTimeFieldOfPromotion(){
		return expireTimeFieldOfPromotion("expireTime","");
	}


	public PromotionOfferForm lastUpdateTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm lastUpdateTimeFieldOfPromotion(String initValue){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime",initValue);
	}
	public PromotionOfferForm lastUpdateTimeFieldOfPromotion(){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime","");
	}


	public PromotionOfferForm platformIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  platformIdFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionOfferForm platformIdFieldOfPromotion(String initValue){
		return platformIdFieldOfPromotion("platformId",initValue);
	}
	public PromotionOfferForm platformIdFieldOfPromotion(){
		return platformIdFieldOfPromotion("platformId","");
	}

	


	

	
 	public PromotionOfferForm transferToAnotherPromotionLevelAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPromotionLevel/promotionOfferId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public PromotionOfferForm transferToAnotherPromotionAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPromotion/promotionOfferId/");
		this.addFormAction(action);
		return this;
	}

 

	public PromotionOfferForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


