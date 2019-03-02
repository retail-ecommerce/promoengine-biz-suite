package com.doublechaintech.promoengine.actiontype;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class ActionTypeForm extends BaseForm {
	
	
	public ActionTypeForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public ActionTypeForm actionTypeIdField(String parameterName, String initValue){
		FormField field = idFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm actionTypeIdField(String initValue){
		return actionTypeIdField("actionTypeId",initValue);
	}
	public ActionTypeForm actionTypeIdField(){
		return actionTypeIdField("actionTypeId","");
	}


	public ActionTypeForm nameField(String parameterName, String initValue){
		FormField field = nameFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public ActionTypeForm nameField(){
		return nameField("name","");
	}


	public ActionTypeForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public ActionTypeForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public ActionTypeForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromActionType(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public ActionTypeForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public ActionTypeForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ActionTypeForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public ActionTypeForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public ActionTypeForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ActionTypeForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public ActionTypeForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public ActionTypeForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ActionTypeForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public ActionTypeForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public ActionTypeForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ActionTypeForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public ActionTypeForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public ActionTypeForm targetActionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  idFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm targetActionIdFieldForTargetAction(String initValue){
		return targetActionIdFieldForTargetAction("targetActionId",initValue);
	}
	public ActionTypeForm targetActionIdFieldForTargetAction(){
		return targetActionIdFieldForTargetAction("targetActionId","");
	}


	public ActionTypeForm nameFieldForTargetAction(String parameterName, String initValue){
		FormField field =  nameFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm nameFieldForTargetAction(String initValue){
		return nameFieldForTargetAction("name",initValue);
	}
	public ActionTypeForm nameFieldForTargetAction(){
		return nameFieldForTargetAction("name","");
	}


	public ActionTypeForm actionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  actionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm actionIdFieldForTargetAction(String initValue){
		return actionIdFieldForTargetAction("actionId",initValue);
	}
	public ActionTypeForm actionIdFieldForTargetAction(){
		return actionIdFieldForTargetAction("actionId","");
	}


	public ActionTypeForm parameterFieldForTargetAction(String parameterName, String initValue){
		FormField field =  parameterFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm parameterFieldForTargetAction(String initValue){
		return parameterFieldForTargetAction("parameter",initValue);
	}
	public ActionTypeForm parameterFieldForTargetAction(){
		return parameterFieldForTargetAction("parameter","");
	}


	public ActionTypeForm lastUpdateTimeFieldForTargetAction(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm lastUpdateTimeFieldForTargetAction(String initValue){
		return lastUpdateTimeFieldForTargetAction("lastUpdateTime",initValue);
	}
	public ActionTypeForm lastUpdateTimeFieldForTargetAction(){
		return lastUpdateTimeFieldForTargetAction("lastUpdateTime","");
	}


	public ActionTypeForm promotionIdFieldForTargetAction(String parameterName, String initValue){
		FormField field =  promotionIdFromTargetAction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ActionTypeForm promotionIdFieldForTargetAction(String initValue){
		return promotionIdFieldForTargetAction("promotionId",initValue);
	}
	public ActionTypeForm promotionIdFieldForTargetAction(){
		return promotionIdFieldForTargetAction("promotionId","");
	}

	

	
 	public ActionTypeForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/actionTypeId/");
		this.addFormAction(action);
		return this;
	}

 

	public ActionTypeForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


