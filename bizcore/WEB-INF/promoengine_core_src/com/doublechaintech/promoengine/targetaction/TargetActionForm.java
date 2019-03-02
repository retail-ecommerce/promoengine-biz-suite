package com.doublechaintech.promoengine.targetaction;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class TargetActionForm extends BaseForm {
	
	
	public TargetActionForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public TargetActionForm targetActionIdField(String parameterName, String initValue){
		FormField field = idFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm targetActionIdField(String initValue){
		return targetActionIdField("targetActionId",initValue);
	}
	public TargetActionForm targetActionIdField(){
		return targetActionIdField("targetActionId","");
	}


	public TargetActionForm nameField(String parameterName, String initValue){
		FormField field = nameFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public TargetActionForm nameField(){
		return nameField("name","");
	}


	public TargetActionForm actionIdField(String parameterName, String initValue){
		FormField field = actionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm actionIdField(String initValue){
		return actionIdField("actionId",initValue);
	}
	public TargetActionForm actionIdField(){
		return actionIdField("actionId","");
	}


	public TargetActionForm parameterField(String parameterName, String initValue){
		FormField field = parameterFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm parameterField(String initValue){
		return parameterField("parameter",initValue);
	}
	public TargetActionForm parameterField(){
		return parameterField("parameter","");
	}


	public TargetActionForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public TargetActionForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public TargetActionForm promotionIdField(String parameterName, String initValue){
		FormField field = promotionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetActionForm promotionIdField(String initValue){
		return promotionIdField("promotionId",initValue);
	}
	public TargetActionForm promotionIdField(){
		return promotionIdField("promotionId","");
	}

	
	


	public TargetActionForm actionTypeIdFieldOfActionType(String parameterName, String initValue){
		FormField field =  idFromActionType(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm actionTypeIdFieldOfActionType(String initValue){
		return actionTypeIdFieldOfActionType("actionTypeId",initValue);
	}
	public TargetActionForm actionTypeIdFieldOfActionType(){
		return actionTypeIdFieldOfActionType("actionTypeId","");
	}


	public TargetActionForm nameFieldOfActionType(String parameterName, String initValue){
		FormField field =  nameFromActionType(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm nameFieldOfActionType(String initValue){
		return nameFieldOfActionType("name",initValue);
	}
	public TargetActionForm nameFieldOfActionType(){
		return nameFieldOfActionType("name","");
	}


	public TargetActionForm lastUpdateTimeFieldOfActionType(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromActionType(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm lastUpdateTimeFieldOfActionType(String initValue){
		return lastUpdateTimeFieldOfActionType("lastUpdateTime",initValue);
	}
	public TargetActionForm lastUpdateTimeFieldOfActionType(){
		return lastUpdateTimeFieldOfActionType("lastUpdateTime","");
	}


	public TargetActionForm platformIdFieldOfActionType(String parameterName, String initValue){
		FormField field =  platformIdFromActionType(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm platformIdFieldOfActionType(String initValue){
		return platformIdFieldOfActionType("platformId",initValue);
	}
	public TargetActionForm platformIdFieldOfActionType(){
		return platformIdFieldOfActionType("platformId","");
	}


	public TargetActionForm promotionIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  idFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm promotionIdFieldOfPromotion(String initValue){
		return promotionIdFieldOfPromotion("promotionId",initValue);
	}
	public TargetActionForm promotionIdFieldOfPromotion(){
		return promotionIdFieldOfPromotion("promotionId","");
	}


	public TargetActionForm nameFieldOfPromotion(String parameterName, String initValue){
		FormField field =  nameFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm nameFieldOfPromotion(String initValue){
		return nameFieldOfPromotion("name",initValue);
	}
	public TargetActionForm nameFieldOfPromotion(){
		return nameFieldOfPromotion("name","");
	}


	public TargetActionForm validAfterFieldOfPromotion(String parameterName, String initValue){
		FormField field =  validAfterFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm validAfterFieldOfPromotion(String initValue){
		return validAfterFieldOfPromotion("validAfter",initValue);
	}
	public TargetActionForm validAfterFieldOfPromotion(){
		return validAfterFieldOfPromotion("validAfter","");
	}


	public TargetActionForm expireTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  expireTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm expireTimeFieldOfPromotion(String initValue){
		return expireTimeFieldOfPromotion("expireTime",initValue);
	}
	public TargetActionForm expireTimeFieldOfPromotion(){
		return expireTimeFieldOfPromotion("expireTime","");
	}


	public TargetActionForm lastUpdateTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm lastUpdateTimeFieldOfPromotion(String initValue){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime",initValue);
	}
	public TargetActionForm lastUpdateTimeFieldOfPromotion(){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime","");
	}


	public TargetActionForm platformIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  platformIdFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetActionForm platformIdFieldOfPromotion(String initValue){
		return platformIdFieldOfPromotion("platformId",initValue);
	}
	public TargetActionForm platformIdFieldOfPromotion(){
		return platformIdFieldOfPromotion("platformId","");
	}

	


	

	
 	public TargetActionForm transferToAnotherActionAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherAction/targetActionId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public TargetActionForm transferToAnotherPromotionAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPromotion/targetActionId/");
		this.addFormAction(action);
		return this;
	}

 

	public TargetActionForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


