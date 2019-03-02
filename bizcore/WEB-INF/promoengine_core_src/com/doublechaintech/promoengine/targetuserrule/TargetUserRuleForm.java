package com.doublechaintech.promoengine.targetuserrule;
import com.doublechaintech.promoengine.BaseForm;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;



public class TargetUserRuleForm extends BaseForm {
	
	
	public TargetUserRuleForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public TargetUserRuleForm targetUserRuleIdField(String parameterName, String initValue){
		FormField field = idFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm targetUserRuleIdField(String initValue){
		return targetUserRuleIdField("targetUserRuleId",initValue);
	}
	public TargetUserRuleForm targetUserRuleIdField(){
		return targetUserRuleIdField("targetUserRuleId","");
	}


	public TargetUserRuleForm nameField(String parameterName, String initValue){
		FormField field = nameFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public TargetUserRuleForm nameField(){
		return nameField("name","");
	}


	public TargetUserRuleForm applyConditionIdField(String parameterName, String initValue){
		FormField field = applyConditionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm applyConditionIdField(String initValue){
		return applyConditionIdField("applyConditionId",initValue);
	}
	public TargetUserRuleForm applyConditionIdField(){
		return applyConditionIdField("applyConditionId","");
	}


	public TargetUserRuleForm parameterField(String parameterName, String initValue){
		FormField field = parameterFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm parameterField(String initValue){
		return parameterField("parameter",initValue);
	}
	public TargetUserRuleForm parameterField(){
		return parameterField("parameter","");
	}


	public TargetUserRuleForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public TargetUserRuleForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}


	public TargetUserRuleForm promotionIdField(String parameterName, String initValue){
		FormField field = promotionIdFromTargetUserRule(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TargetUserRuleForm promotionIdField(String initValue){
		return promotionIdField("promotionId",initValue);
	}
	public TargetUserRuleForm promotionIdField(){
		return promotionIdField("promotionId","");
	}

	
	


	public TargetUserRuleForm applyConditionIdFieldOfApplyCondition(String parameterName, String initValue){
		FormField field =  idFromApplyCondition(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm applyConditionIdFieldOfApplyCondition(String initValue){
		return applyConditionIdFieldOfApplyCondition("applyConditionId",initValue);
	}
	public TargetUserRuleForm applyConditionIdFieldOfApplyCondition(){
		return applyConditionIdFieldOfApplyCondition("applyConditionId","");
	}


	public TargetUserRuleForm nameFieldOfApplyCondition(String parameterName, String initValue){
		FormField field =  nameFromApplyCondition(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm nameFieldOfApplyCondition(String initValue){
		return nameFieldOfApplyCondition("name",initValue);
	}
	public TargetUserRuleForm nameFieldOfApplyCondition(){
		return nameFieldOfApplyCondition("name","");
	}


	public TargetUserRuleForm lastUpdateTimeFieldOfApplyCondition(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromApplyCondition(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm lastUpdateTimeFieldOfApplyCondition(String initValue){
		return lastUpdateTimeFieldOfApplyCondition("lastUpdateTime",initValue);
	}
	public TargetUserRuleForm lastUpdateTimeFieldOfApplyCondition(){
		return lastUpdateTimeFieldOfApplyCondition("lastUpdateTime","");
	}


	public TargetUserRuleForm platformIdFieldOfApplyCondition(String parameterName, String initValue){
		FormField field =  platformIdFromApplyCondition(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm platformIdFieldOfApplyCondition(String initValue){
		return platformIdFieldOfApplyCondition("platformId",initValue);
	}
	public TargetUserRuleForm platformIdFieldOfApplyCondition(){
		return platformIdFieldOfApplyCondition("platformId","");
	}


	public TargetUserRuleForm promotionIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  idFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm promotionIdFieldOfPromotion(String initValue){
		return promotionIdFieldOfPromotion("promotionId",initValue);
	}
	public TargetUserRuleForm promotionIdFieldOfPromotion(){
		return promotionIdFieldOfPromotion("promotionId","");
	}


	public TargetUserRuleForm nameFieldOfPromotion(String parameterName, String initValue){
		FormField field =  nameFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm nameFieldOfPromotion(String initValue){
		return nameFieldOfPromotion("name",initValue);
	}
	public TargetUserRuleForm nameFieldOfPromotion(){
		return nameFieldOfPromotion("name","");
	}


	public TargetUserRuleForm validAfterFieldOfPromotion(String parameterName, String initValue){
		FormField field =  validAfterFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm validAfterFieldOfPromotion(String initValue){
		return validAfterFieldOfPromotion("validAfter",initValue);
	}
	public TargetUserRuleForm validAfterFieldOfPromotion(){
		return validAfterFieldOfPromotion("validAfter","");
	}


	public TargetUserRuleForm expireTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  expireTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm expireTimeFieldOfPromotion(String initValue){
		return expireTimeFieldOfPromotion("expireTime",initValue);
	}
	public TargetUserRuleForm expireTimeFieldOfPromotion(){
		return expireTimeFieldOfPromotion("expireTime","");
	}


	public TargetUserRuleForm lastUpdateTimeFieldOfPromotion(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm lastUpdateTimeFieldOfPromotion(String initValue){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime",initValue);
	}
	public TargetUserRuleForm lastUpdateTimeFieldOfPromotion(){
		return lastUpdateTimeFieldOfPromotion("lastUpdateTime","");
	}


	public TargetUserRuleForm platformIdFieldOfPromotion(String parameterName, String initValue){
		FormField field =  platformIdFromPromotion(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TargetUserRuleForm platformIdFieldOfPromotion(String initValue){
		return platformIdFieldOfPromotion("platformId",initValue);
	}
	public TargetUserRuleForm platformIdFieldOfPromotion(){
		return platformIdFieldOfPromotion("platformId","");
	}

	


	

	
 	public TargetUserRuleForm transferToAnotherApplyConditionAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherApplyCondition/targetUserRuleId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public TargetUserRuleForm transferToAnotherPromotionAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPromotion/targetUserRuleId/");
		this.addFormAction(action);
		return this;
	}

 

	public TargetUserRuleForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


