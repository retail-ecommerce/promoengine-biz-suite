
package com.doublechaintech.promoengine.formfield;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.promoengine.PromoengineUserContext;
import com.doublechaintech.promoengine.BaseEntity;
import com.doublechaintech.promoengine.SmartList;

public interface FormFieldManager{

		

	public FormField createFormField(PromoengineUserContext userContext, String label, String localeKey, String parameterName, String type, String formId, String placeholder, String defaultValue, String description, String fieldGroup, String minimumValue, String maximumValue, boolean required, boolean disabled, boolean customRendering, String candidateValues, String suggestValues) throws Exception;	
	public FormField updateFormField(PromoengineUserContext userContext,String formFieldId, int formFieldVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormField loadFormField(PromoengineUserContext userContext, String formFieldId, String [] tokensExpr) throws Exception;
	public FormField internalSaveFormField(PromoengineUserContext userContext, FormField formField) throws Exception;
	public FormField internalSaveFormField(PromoengineUserContext userContext, FormField formField,Map<String,Object>option) throws Exception;
	
	public FormField transferToAnotherForm(PromoengineUserContext userContext, String formFieldId, String anotherFormId)  throws Exception;
 

	public void delete(PromoengineUserContext userContext, String formFieldId, int version) throws Exception;
	public int deleteAll(PromoengineUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(PromoengineUserContext userContext, FormField newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


