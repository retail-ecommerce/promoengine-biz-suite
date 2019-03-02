package com.doublechaintech.promoengine.promotionlevel;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class PromotionLevelForm extends BaseForm {
	
	
	public PromotionLevelForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PromotionLevelForm promotionLevelIdField(String parameterName, String initValue){
		FormField field = idFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm promotionLevelIdField(String initValue){
		return promotionLevelIdField("promotionLevelId",initValue);
	}
	public PromotionLevelForm promotionLevelIdField(){
		return promotionLevelIdField("promotionLevelId","");
	}


	public PromotionLevelForm nameField(String parameterName, String initValue){
		FormField field = nameFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PromotionLevelForm nameField(){
		return nameField("name","");
	}


	public PromotionLevelForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public PromotionLevelForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public PromotionLevelForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PromotionLevelForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public PromotionLevelForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionLevelForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public PromotionLevelForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public PromotionLevelForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionLevelForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public PromotionLevelForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public PromotionLevelForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionLevelForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public PromotionLevelForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public PromotionLevelForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionLevelForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public PromotionLevelForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public PromotionLevelForm promotionOfferIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  idFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm promotionOfferIdFieldForPromotionOffer(String initValue){
		return promotionOfferIdFieldForPromotionOffer("promotionOfferId",initValue);
	}
	public PromotionLevelForm promotionOfferIdFieldForPromotionOffer(){
		return promotionOfferIdFieldForPromotionOffer("promotionOfferId","");
	}


	public PromotionLevelForm nameFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  nameFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm nameFieldForPromotionOffer(String initValue){
		return nameFieldForPromotionOffer("name",initValue);
	}
	public PromotionLevelForm nameFieldForPromotionOffer(){
		return nameFieldForPromotionOffer("name","");
	}


	public PromotionLevelForm promotionLevelIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  promotionLevelIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm promotionLevelIdFieldForPromotionOffer(String initValue){
		return promotionLevelIdFieldForPromotionOffer("promotionLevelId",initValue);
	}
	public PromotionLevelForm promotionLevelIdFieldForPromotionOffer(){
		return promotionLevelIdFieldForPromotionOffer("promotionLevelId","");
	}


	public PromotionLevelForm parameterFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  parameterFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm parameterFieldForPromotionOffer(String initValue){
		return parameterFieldForPromotionOffer("parameter",initValue);
	}
	public PromotionLevelForm parameterFieldForPromotionOffer(){
		return parameterFieldForPromotionOffer("parameter","");
	}


	public PromotionLevelForm promotionIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  promotionIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionLevelForm promotionIdFieldForPromotionOffer(String initValue){
		return promotionIdFieldForPromotionOffer("promotionId",initValue);
	}
	public PromotionLevelForm promotionIdFieldForPromotionOffer(){
		return promotionIdFieldForPromotionOffer("promotionId","");
	}

	

	
 	public PromotionLevelForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/promotionLevelId/");
		this.addFormAction(action);
		return this;
	}

 

	public PromotionLevelForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


