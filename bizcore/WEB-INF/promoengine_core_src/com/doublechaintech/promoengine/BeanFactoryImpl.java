
package com.doublechaintech.promoengine;
import java.util.Map;

import com.doublechaintech.promoengine.platform.Platform;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.actiontype.ActionType;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;
import com.doublechaintech.promoengine.targetaction.TargetAction;
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;
import com.doublechaintech.promoengine.userdomain.UserDomain;
import com.doublechaintech.promoengine.userwhitelist.UserWhiteList;
import com.doublechaintech.promoengine.secuser.SecUser;
import com.doublechaintech.promoengine.secuserblocking.SecUserBlocking;
import com.doublechaintech.promoengine.userapp.UserApp;
import com.doublechaintech.promoengine.listaccess.ListAccess;
import com.doublechaintech.promoengine.objectaccess.ObjectAccess;
import com.doublechaintech.promoengine.loginhistory.LoginHistory;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formaction.FormAction;

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public ApplyCondition createApplyCondition(Map<String,Object> options){
		return new ApplyCondition();
	}


	public ActionType createActionType(Map<String,Object> options){
		return new ActionType();
	}


	public PromotionLevel createPromotionLevel(Map<String,Object> options){
		return new PromotionLevel();
	}


	public Promotion createPromotion(Map<String,Object> options){
		return new Promotion();
	}


	public TargetUserRule createTargetUserRule(Map<String,Object> options){
		return new TargetUserRule();
	}


	public TargetAction createTargetAction(Map<String,Object> options){
		return new TargetAction();
	}


	public PromotionOffer createPromotionOffer(Map<String,Object> options){
		return new PromotionOffer();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public SecUserBlocking createSecUserBlocking(Map<String,Object> options){
		return new SecUserBlocking();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public ObjectAccess createObjectAccess(Map<String,Object> options){
		return new ObjectAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public GenericForm createGenericForm(Map<String,Object> options){
		return new GenericForm();
	}


	public FormMessage createFormMessage(Map<String,Object> options){
		return new FormMessage();
	}


	public FormFieldMessage createFormFieldMessage(Map<String,Object> options){
		return new FormFieldMessage();
	}


	public FormField createFormField(Map<String,Object> options){
		return new FormField();
	}


	public FormAction createFormAction(Map<String,Object> options){
		return new FormAction();
	}





}










