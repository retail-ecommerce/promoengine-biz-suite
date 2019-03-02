
package com.doublechaintech.promoengine.formmessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface FormMessageManager{

		

	public FormMessage createFormMessage(PromoengineUserContext userContext, String title, String formId, String level) throws Exception;	
	public FormMessage updateFormMessage(PromoengineUserContext userContext,String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormMessage loadFormMessage(PromoengineUserContext userContext, String formMessageId, String [] tokensExpr) throws Exception;
	public FormMessage internalSaveFormMessage(PromoengineUserContext userContext, FormMessage formMessage) throws Exception;
	public FormMessage internalSaveFormMessage(PromoengineUserContext userContext, FormMessage formMessage,Map<String,Object>option) throws Exception;
	
	public FormMessage transferToAnotherForm(PromoengineUserContext userContext, String formMessageId, String anotherFormId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String formMessageId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, FormMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


