package com.doublechaintech.promoengine.platform;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class PlatformForm extends BaseForm {
	
	
	public PlatformForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PlatformForm platformIdField(String parameterName, String initValue){
		FormField field = idFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PlatformForm platformIdField(){
		return platformIdField("platformId","");
	}


	public PlatformForm nameField(String parameterName, String initValue){
		FormField field = nameFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PlatformForm nameField(){
		return nameField("name","");
	}


	public PlatformForm introductionField(String parameterName, String initValue){
		FormField field = introductionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm introductionField(String initValue){
		return introductionField("introduction",initValue);
	}
	public PlatformForm introductionField(){
		return introductionField("introduction","");
	}


	public PlatformForm currentVersionField(String parameterName, String initValue){
		FormField field = currentVersionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm currentVersionField(String initValue){
		return currentVersionField("currentVersion",initValue);
	}
	public PlatformForm currentVersionField(){
		return currentVersionField("currentVersion","");
	}

	
	

	



	public PlatformForm applyConditionIdFieldForApplyCondition(String parameterName, String initValue){
		FormField field =  idFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm applyConditionIdFieldForApplyCondition(String initValue){
		return applyConditionIdFieldForApplyCondition("applyConditionId",initValue);
	}
	public PlatformForm applyConditionIdFieldForApplyCondition(){
		return applyConditionIdFieldForApplyCondition("applyConditionId","");
	}


	public PlatformForm nameFieldForApplyCondition(String parameterName, String initValue){
		FormField field =  nameFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForApplyCondition(String initValue){
		return nameFieldForApplyCondition("name",initValue);
	}
	public PlatformForm nameFieldForApplyCondition(){
		return nameFieldForApplyCondition("name","");
	}


	public PlatformForm lastUpdateTimeFieldForApplyCondition(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm lastUpdateTimeFieldForApplyCondition(String initValue){
		return lastUpdateTimeFieldForApplyCondition("lastUpdateTime",initValue);
	}
	public PlatformForm lastUpdateTimeFieldForApplyCondition(){
		return lastUpdateTimeFieldForApplyCondition("lastUpdateTime","");
	}


	public PlatformForm platformIdFieldForApplyCondition(String parameterName, String initValue){
		FormField field =  platformIdFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForApplyCondition(String initValue){
		return platformIdFieldForApplyCondition("platformId",initValue);
	}
	public PlatformForm platformIdFieldForApplyCondition(){
		return platformIdFieldForApplyCondition("platformId","");
	}


	public PlatformForm actionTypeIdFieldForActionType(String parameterName, String initValue){
		FormField field =  idFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm actionTypeIdFieldForActionType(String initValue){
		return actionTypeIdFieldForActionType("actionTypeId",initValue);
	}
	public PlatformForm actionTypeIdFieldForActionType(){
		return actionTypeIdFieldForActionType("actionTypeId","");
	}


	public PlatformForm nameFieldForActionType(String parameterName, String initValue){
		FormField field =  nameFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForActionType(String initValue){
		return nameFieldForActionType("name",initValue);
	}
	public PlatformForm nameFieldForActionType(){
		return nameFieldForActionType("name","");
	}


	public PlatformForm lastUpdateTimeFieldForActionType(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm lastUpdateTimeFieldForActionType(String initValue){
		return lastUpdateTimeFieldForActionType("lastUpdateTime",initValue);
	}
	public PlatformForm lastUpdateTimeFieldForActionType(){
		return lastUpdateTimeFieldForActionType("lastUpdateTime","");
	}


	public PlatformForm platformIdFieldForActionType(String parameterName, String initValue){
		FormField field =  platformIdFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForActionType(String initValue){
		return platformIdFieldForActionType("platformId",initValue);
	}
	public PlatformForm platformIdFieldForActionType(){
		return platformIdFieldForActionType("platformId","");
	}


	public PlatformForm promotionLevelIdFieldForPromotionLevel(String parameterName, String initValue){
		FormField field =  idFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm promotionLevelIdFieldForPromotionLevel(String initValue){
		return promotionLevelIdFieldForPromotionLevel("promotionLevelId",initValue);
	}
	public PlatformForm promotionLevelIdFieldForPromotionLevel(){
		return promotionLevelIdFieldForPromotionLevel("promotionLevelId","");
	}


	public PlatformForm nameFieldForPromotionLevel(String parameterName, String initValue){
		FormField field =  nameFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForPromotionLevel(String initValue){
		return nameFieldForPromotionLevel("name",initValue);
	}
	public PlatformForm nameFieldForPromotionLevel(){
		return nameFieldForPromotionLevel("name","");
	}


	public PlatformForm lastUpdateTimeFieldForPromotionLevel(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm lastUpdateTimeFieldForPromotionLevel(String initValue){
		return lastUpdateTimeFieldForPromotionLevel("lastUpdateTime",initValue);
	}
	public PlatformForm lastUpdateTimeFieldForPromotionLevel(){
		return lastUpdateTimeFieldForPromotionLevel("lastUpdateTime","");
	}


	public PlatformForm platformIdFieldForPromotionLevel(String parameterName, String initValue){
		FormField field =  platformIdFromPromotionLevel(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForPromotionLevel(String initValue){
		return platformIdFieldForPromotionLevel("platformId",initValue);
	}
	public PlatformForm platformIdFieldForPromotionLevel(){
		return platformIdFieldForPromotionLevel("platformId","");
	}


	public PlatformForm promotionIdFieldForPromotion(String parameterName, String initValue){
		FormField field =  idFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm promotionIdFieldForPromotion(String initValue){
		return promotionIdFieldForPromotion("promotionId",initValue);
	}
	public PlatformForm promotionIdFieldForPromotion(){
		return promotionIdFieldForPromotion("promotionId","");
	}


	public PlatformForm nameFieldForPromotion(String parameterName, String initValue){
		FormField field =  nameFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForPromotion(String initValue){
		return nameFieldForPromotion("name",initValue);
	}
	public PlatformForm nameFieldForPromotion(){
		return nameFieldForPromotion("name","");
	}


	public PlatformForm validAfterFieldForPromotion(String parameterName, String initValue){
		FormField field =  validAfterFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm validAfterFieldForPromotion(String initValue){
		return validAfterFieldForPromotion("validAfter",initValue);
	}
	public PlatformForm validAfterFieldForPromotion(){
		return validAfterFieldForPromotion("validAfter","");
	}


	public PlatformForm expireTimeFieldForPromotion(String parameterName, String initValue){
		FormField field =  expireTimeFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm expireTimeFieldForPromotion(String initValue){
		return expireTimeFieldForPromotion("expireTime",initValue);
	}
	public PlatformForm expireTimeFieldForPromotion(){
		return expireTimeFieldForPromotion("expireTime","");
	}


	public PlatformForm lastUpdateTimeFieldForPromotion(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm lastUpdateTimeFieldForPromotion(String initValue){
		return lastUpdateTimeFieldForPromotion("lastUpdateTime",initValue);
	}
	public PlatformForm lastUpdateTimeFieldForPromotion(){
		return lastUpdateTimeFieldForPromotion("lastUpdateTime","");
	}


	public PlatformForm platformIdFieldForPromotion(String parameterName, String initValue){
		FormField field =  platformIdFromPromotion(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForPromotion(String initValue){
		return platformIdFieldForPromotion("platformId",initValue);
	}
	public PlatformForm platformIdFieldForPromotion(){
		return platformIdFieldForPromotion("platformId","");
	}

	



	public PlatformForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


