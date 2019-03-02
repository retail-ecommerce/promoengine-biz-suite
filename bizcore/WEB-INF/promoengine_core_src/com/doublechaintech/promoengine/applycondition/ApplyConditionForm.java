package com.doublechaintech.promoengine.applycondition;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class ApplyConditionForm extends BaseForm {
	
	
	public ApplyConditionForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public ApplyConditionForm applyConditionIdField(String parameterName, String initValue){
		FormField field = idFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm applyConditionIdField(String initValue){
		return applyConditionIdField("applyConditionId",initValue);
	}
	public ApplyConditionForm applyConditionIdField(){
		return applyConditionIdField("applyConditionId","");
	}


	public ApplyConditionForm nameField(String parameterName, String initValue){
		FormField field = nameFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public ApplyConditionForm nameField(){
		return nameField("name","");
	}


	public ApplyConditionForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public ApplyConditionForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public ApplyConditionForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromApplyCondition(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public ApplyConditionForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public ApplyConditionForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ApplyConditionForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public ApplyConditionForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public ApplyConditionForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ApplyConditionForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public ApplyConditionForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public ApplyConditionForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ApplyConditionForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public ApplyConditionForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public ApplyConditionForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ApplyConditionForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public ApplyConditionForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public ApplyConditionForm targetUserRuleIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  idFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm targetUserRuleIdFieldForTargetUserRule(String initValue){
		return targetUserRuleIdFieldForTargetUserRule("targetUserRuleId",initValue);
	}
	public ApplyConditionForm targetUserRuleIdFieldForTargetUserRule(){
		return targetUserRuleIdFieldForTargetUserRule("targetUserRuleId","");
	}


	public ApplyConditionForm nameFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  nameFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm nameFieldForTargetUserRule(String initValue){
		return nameFieldForTargetUserRule("name",initValue);
	}
	public ApplyConditionForm nameFieldForTargetUserRule(){
		return nameFieldForTargetUserRule("name","");
	}


	public ApplyConditionForm applyConditionIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  applyConditionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm applyConditionIdFieldForTargetUserRule(String initValue){
		return applyConditionIdFieldForTargetUserRule("applyConditionId",initValue);
	}
	public ApplyConditionForm applyConditionIdFieldForTargetUserRule(){
		return applyConditionIdFieldForTargetUserRule("applyConditionId","");
	}


	public ApplyConditionForm parameterFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  parameterFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm parameterFieldForTargetUserRule(String initValue){
		return parameterFieldForTargetUserRule("parameter",initValue);
	}
	public ApplyConditionForm parameterFieldForTargetUserRule(){
		return parameterFieldForTargetUserRule("parameter","");
	}


	public ApplyConditionForm lastUpdateTimeFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm lastUpdateTimeFieldForTargetUserRule(String initValue){
		return lastUpdateTimeFieldForTargetUserRule("lastUpdateTime",initValue);
	}
	public ApplyConditionForm lastUpdateTimeFieldForTargetUserRule(){
		return lastUpdateTimeFieldForTargetUserRule("lastUpdateTime","");
	}


	public ApplyConditionForm promotionIdFieldForTargetUserRule(String parameterName, String initValue){
		FormField field =  promotionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ApplyConditionForm promotionIdFieldForTargetUserRule(String initValue){
		return promotionIdFieldForTargetUserRule("promotionId",initValue);
	}
	public ApplyConditionForm promotionIdFieldForTargetUserRule(){
		return promotionIdFieldForTargetUserRule("promotionId","");
	}

	

	
 	public ApplyConditionForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/applyConditionId/");
		this.addFormAction(action);
		return this;
	}

 

	public ApplyConditionForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


