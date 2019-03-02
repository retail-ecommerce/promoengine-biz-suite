package com.doublechaintech.promoengine.promotion;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class PromotionForm extends BaseForm {
	
	
	public PromotionForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PromotionForm promotionIdField(String parameterName, String initValue){
		FormField field = idFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionIdField(String initValue){
		return promotionIdField("promotionId",initValue);
	}
	public PromotionForm promotionIdField(){
		return promotionIdField("promotionId","");
	}


	public PromotionForm nameField(String parameterName, String initValue){
		FormField field = nameFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PromotionForm nameField(){
		return nameField("name","");
	}


	public PromotionForm validAfterField(String parameterName, String initValue){
		FormField field = validAfterFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm validAfterField(String initValue){
		return validAfterField("validAfter",initValue);
	}
	public PromotionForm validAfterField(){
		return validAfterField("validAfter","");
	}


	public PromotionForm expireTimeField(String parameterName, String initValue){
		FormField field = expireTimeFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm expireTimeField(String initValue){
		return expireTimeField("expireTime",initValue);
	}
	public PromotionForm expireTimeField(){
		return expireTimeField("expireTime","");
	}


	public PromotionForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public PromotionForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public PromotionForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PromotionForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public PromotionForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public PromotionForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public PromotionForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public PromotionForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public PromotionForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public PromotionForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public PromotionForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PromotionForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public PromotionForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public PromotionForm targetUserRuleIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  idFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm targetUserRuleIdFieldForTargetUserRule(String initValue){
		return targetUserRuleIdFieldForTargetUserRule("targetUserRuleId",initValue);
	}
	public PromotionForm targetUserRuleIdFieldForTargetUserRule(){
		return targetUserRuleIdFieldForTargetUserRule("targetUserRuleId","");
	}


	public PromotionForm nameFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  nameFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm nameFieldForTargetUserRule(String initValue){
		return nameFieldForTargetUserRule("name",initValue);
	}
	public PromotionForm nameFieldForTargetUserRule(){
		return nameFieldForTargetUserRule("name","");
	}


	public PromotionForm applyConditionIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  applyConditionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm applyConditionIdFieldForTargetUserRule(String initValue){
		return applyConditionIdFieldForTargetUserRule("applyConditionId",initValue);
	}
	public PromotionForm applyConditionIdFieldForTargetUserRule(){
		return applyConditionIdFieldForTargetUserRule("applyConditionId","");
	}


	public PromotionForm parameterFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  parameterFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm parameterFieldForTargetUserRule(String initValue){
		return parameterFieldForTargetUserRule("parameter",initValue);
	}
	public PromotionForm parameterFieldForTargetUserRule(){
		return parameterFieldForTargetUserRule("parameter","");
	}


	public PromotionForm lastUpdateTimeFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm lastUpdateTimeFieldForTargetUserRule(String initValue){
		return lastUpdateTimeFieldForTargetUserRule("lastUpdateTime",initValue);
	}
	public PromotionForm lastUpdateTimeFieldForTargetUserRule(){
		return lastUpdateTimeFieldForTargetUserRule("lastUpdateTime","");
	}


	public PromotionForm promotionIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  promotionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionIdFieldForTargetUserRule(String initValue){
		return promotionIdFieldForTargetUserRule("promotionId",initValue);
	}
	public PromotionForm promotionIdFieldForTargetUserRule(){
		return promotionIdFieldForTargetUserRule("promotionId","");
	}


	public PromotionForm targetActionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  idFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm targetActionIdFieldForTargetAction(String initValue){
		return targetActionIdFieldForTargetAction("targetActionId",initValue);
	}
	public PromotionForm targetActionIdFieldForTargetAction(){
		return targetActionIdFieldForTargetAction("targetActionId","");
	}


	public PromotionForm nameFieldForTargetAction(String parameterName, String initValue){
		FormField field =  nameFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm nameFieldForTargetAction(String initValue){
		return nameFieldForTargetAction("name",initValue);
	}
	public PromotionForm nameFieldForTargetAction(){
		return nameFieldForTargetAction("name","");
	}


	public PromotionForm actionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  actionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm actionIdFieldForTargetAction(String initValue){
		return actionIdFieldForTargetAction("actionId",initValue);
	}
	public PromotionForm actionIdFieldForTargetAction(){
		return actionIdFieldForTargetAction("actionId","");
	}


	public PromotionForm parameterFieldForTargetAction(String parameterName, String initValue){
		FormField field =  parameterFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm parameterFieldForTargetAction(String initValue){
		return parameterFieldForTargetAction("parameter",initValue);
	}
	public PromotionForm parameterFieldForTargetAction(){
		return parameterFieldForTargetAction("parameter","");
	}


	public PromotionForm lastUpdateTimeFieldForTargetAction(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm lastUpdateTimeFieldForTargetAction(String initValue){
		return lastUpdateTimeFieldForTargetAction("lastUpdateTime",initValue);
	}
	public PromotionForm lastUpdateTimeFieldForTargetAction(){
		return lastUpdateTimeFieldForTargetAction("lastUpdateTime","");
	}


	public PromotionForm promotionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  promotionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionIdFieldForTargetAction(String initValue){
		return promotionIdFieldForTargetAction("promotionId",initValue);
	}
	public PromotionForm promotionIdFieldForTargetAction(){
		return promotionIdFieldForTargetAction("promotionId","");
	}


	public PromotionForm promotionOfferIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  idFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionOfferIdFieldForPromotionOffer(String initValue){
		return promotionOfferIdFieldForPromotionOffer("promotionOfferId",initValue);
	}
	public PromotionForm promotionOfferIdFieldForPromotionOffer(){
		return promotionOfferIdFieldForPromotionOffer("promotionOfferId","");
	}


	public PromotionForm nameFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  nameFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm nameFieldForPromotionOffer(String initValue){
		return nameFieldForPromotionOffer("name",initValue);
	}
	public PromotionForm nameFieldForPromotionOffer(){
		return nameFieldForPromotionOffer("name","");
	}


	public PromotionForm promotionLevelIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  promotionLevelIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionLevelIdFieldForPromotionOffer(String initValue){
		return promotionLevelIdFieldForPromotionOffer("promotionLevelId",initValue);
	}
	public PromotionForm promotionLevelIdFieldForPromotionOffer(){
		return promotionLevelIdFieldForPromotionOffer("promotionLevelId","");
	}


	public PromotionForm parameterFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  parameterFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm parameterFieldForPromotionOffer(String initValue){
		return parameterFieldForPromotionOffer("parameter",initValue);
	}
	public PromotionForm parameterFieldForPromotionOffer(){
		return parameterFieldForPromotionOffer("parameter","");
	}


	public PromotionForm promotionIdFieldForPromotionOffer(String parameterName, String initValue){
		FormField field =  promotionIdFromPromotionOffer(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PromotionForm promotionIdFieldForPromotionOffer(String initValue){
		return promotionIdFieldForPromotionOffer("promotionId",initValue);
	}
	public PromotionForm promotionIdFieldForPromotionOffer(){
		return promotionIdFieldForPromotionOffer("promotionId","");
	}

	

	
 	public PromotionForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/promotionId/");
		this.addFormAction(action);
		return this;
	}

 

	public PromotionForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


