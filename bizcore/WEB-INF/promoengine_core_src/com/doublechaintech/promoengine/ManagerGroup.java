package com.doublechaintech.promoengine;


import com.doublechaintech.promoengine.platform.PlatformManager;

import com.doublechaintech.promoengine.applycondition.ApplyConditionManager;

import com.doublechaintech.promoengine.actiontype.ActionTypeManager;

import com.doublechaintech.promoengine.promotionlevel.PromotionLevelManager;

import com.doublechaintech.promoengine.promotion.PromotionManager;

import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleManager;

import com.doublechaintech.promoengine.targetaction.TargetActionManager;

import com.doublechaintech.promoengine.promotionoffer.PromotionOfferManager;

import com.doublechaintech.promoengine.userdomain.UserDomainManager;

import com.doublechaintech.promoengine.userwhitelist.UserWhiteListManager;

import com.doublechaintech.promoengine.secuser.SecUserManager;

import com.doublechaintech.promoengine.secuserblocking.SecUserBlockingManager;

import com.doublechaintech.promoengine.userapp.UserAppManager;

import com.doublechaintech.promoengine.listaccess.ListAccessManager;

import com.doublechaintech.promoengine.objectaccess.ObjectAccessManager;

import com.doublechaintech.promoengine.loginhistory.LoginHistoryManager;

import com.doublechaintech.promoengine.genericform.GenericFormManager;

import com.doublechaintech.promoengine.formmessage.FormMessageManager;

import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessageManager;

import com.doublechaintech.promoengine.formfield.FormFieldManager;

import com.doublechaintech.promoengine.formaction.FormActionManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected ApplyConditionManager applyConditionManager;

	protected ActionTypeManager actionTypeManager;

	protected PromotionLevelManager promotionLevelManager;

	protected PromotionManager promotionManager;

	protected TargetUserRuleManager targetUserRuleManager;

	protected TargetActionManager targetActionManager;

	protected PromotionOfferManager promotionOfferManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected SecUserBlockingManager secUserBlockingManager;

	protected UserAppManager userAppManager;

	protected ListAccessManager listAccessManager;

	protected ObjectAccessManager objectAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected GenericFormManager genericFormManager;

	protected FormMessageManager formMessageManager;

	protected FormFieldMessageManager formFieldMessageManager;

	protected FormFieldManager formFieldManager;

	protected FormActionManager formActionManager;

	

	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public ApplyConditionManager getApplyConditionManager(){
		return this.applyConditionManager;
	}
	public void setApplyConditionManager(ApplyConditionManager manager){
		this.applyConditionManager = manager;
	}


	public ActionTypeManager getActionTypeManager(){
		return this.actionTypeManager;
	}
	public void setActionTypeManager(ActionTypeManager manager){
		this.actionTypeManager = manager;
	}


	public PromotionLevelManager getPromotionLevelManager(){
		return this.promotionLevelManager;
	}
	public void setPromotionLevelManager(PromotionLevelManager manager){
		this.promotionLevelManager = manager;
	}


	public PromotionManager getPromotionManager(){
		return this.promotionManager;
	}
	public void setPromotionManager(PromotionManager manager){
		this.promotionManager = manager;
	}


	public TargetUserRuleManager getTargetUserRuleManager(){
		return this.targetUserRuleManager;
	}
	public void setTargetUserRuleManager(TargetUserRuleManager manager){
		this.targetUserRuleManager = manager;
	}


	public TargetActionManager getTargetActionManager(){
		return this.targetActionManager;
	}
	public void setTargetActionManager(TargetActionManager manager){
		this.targetActionManager = manager;
	}


	public PromotionOfferManager getPromotionOfferManager(){
		return this.promotionOfferManager;
	}
	public void setPromotionOfferManager(PromotionOfferManager manager){
		this.promotionOfferManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public SecUserBlockingManager getSecUserBlockingManager(){
		return this.secUserBlockingManager;
	}
	public void setSecUserBlockingManager(SecUserBlockingManager manager){
		this.secUserBlockingManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public ObjectAccessManager getObjectAccessManager(){
		return this.objectAccessManager;
	}
	public void setObjectAccessManager(ObjectAccessManager manager){
		this.objectAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public GenericFormManager getGenericFormManager(){
		return this.genericFormManager;
	}
	public void setGenericFormManager(GenericFormManager manager){
		this.genericFormManager = manager;
	}


	public FormMessageManager getFormMessageManager(){
		return this.formMessageManager;
	}
	public void setFormMessageManager(FormMessageManager manager){
		this.formMessageManager = manager;
	}


	public FormFieldMessageManager getFormFieldMessageManager(){
		return this.formFieldMessageManager;
	}
	public void setFormFieldMessageManager(FormFieldMessageManager manager){
		this.formFieldMessageManager = manager;
	}


	public FormFieldManager getFormFieldManager(){
		return this.formFieldManager;
	}
	public void setFormFieldManager(FormFieldManager manager){
		this.formFieldManager = manager;
	}


	public FormActionManager getFormActionManager(){
		return this.formActionManager;
	}
	public void setFormActionManager(FormActionManager manager){
		this.formActionManager = manager;
	}


}









