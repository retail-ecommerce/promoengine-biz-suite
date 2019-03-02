package com.doublechaintech.promoengine;

import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.promoengine.platform.Platform;
import com.doublechaintech.promoengine.platform.PlatformDAO;
import com.doublechaintech.promoengine.platform.PlatformTokens;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.applycondition.ApplyConditionDAO;
import com.doublechaintech.promoengine.applycondition.ApplyConditionTokens;
import com.doublechaintech.promoengine.actiontype.ActionType;
import com.doublechaintech.promoengine.actiontype.ActionTypeDAO;
import com.doublechaintech.promoengine.actiontype.ActionTypeTokens;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevelDAO;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevelTokens;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.promotion.PromotionDAO;
import com.doublechaintech.promoengine.promotion.PromotionTokens;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRule;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleDAO;
import com.doublechaintech.promoengine.targetuserrule.TargetUserRuleTokens;
import com.doublechaintech.promoengine.targetaction.TargetAction;
import com.doublechaintech.promoengine.targetaction.TargetActionDAO;
import com.doublechaintech.promoengine.targetaction.TargetActionTokens;
import com.doublechaintech.promoengine.promotionoffer.PromotionOffer;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferDAO;
import com.doublechaintech.promoengine.promotionoffer.PromotionOfferTokens;
import com.doublechaintech.promoengine.userdomain.UserDomain;
import com.doublechaintech.promoengine.userdomain.UserDomainDAO;
import com.doublechaintech.promoengine.userdomain.UserDomainTokens;
import com.doublechaintech.promoengine.userwhitelist.UserWhiteList;
import com.doublechaintech.promoengine.userwhitelist.UserWhiteListDAO;
import com.doublechaintech.promoengine.userwhitelist.UserWhiteListTokens;
import com.doublechaintech.promoengine.secuser.SecUser;
import com.doublechaintech.promoengine.secuser.SecUserDAO;
import com.doublechaintech.promoengine.secuser.SecUserTokens;
import com.doublechaintech.promoengine.secuserblocking.SecUserBlocking;
import com.doublechaintech.promoengine.secuserblocking.SecUserBlockingDAO;
import com.doublechaintech.promoengine.secuserblocking.SecUserBlockingTokens;
import com.doublechaintech.promoengine.userapp.UserApp;
import com.doublechaintech.promoengine.userapp.UserAppDAO;
import com.doublechaintech.promoengine.userapp.UserAppTokens;
import com.doublechaintech.promoengine.listaccess.ListAccess;
import com.doublechaintech.promoengine.listaccess.ListAccessDAO;
import com.doublechaintech.promoengine.listaccess.ListAccessTokens;
import com.doublechaintech.promoengine.objectaccess.ObjectAccess;
import com.doublechaintech.promoengine.objectaccess.ObjectAccessDAO;
import com.doublechaintech.promoengine.objectaccess.ObjectAccessTokens;
import com.doublechaintech.promoengine.loginhistory.LoginHistory;
import com.doublechaintech.promoengine.loginhistory.LoginHistoryDAO;
import com.doublechaintech.promoengine.loginhistory.LoginHistoryTokens;
import com.doublechaintech.promoengine.genericform.GenericForm;
import com.doublechaintech.promoengine.genericform.GenericFormDAO;
import com.doublechaintech.promoengine.genericform.GenericFormTokens;
import com.doublechaintech.promoengine.formmessage.FormMessage;
import com.doublechaintech.promoengine.formmessage.FormMessageDAO;
import com.doublechaintech.promoengine.formmessage.FormMessageTokens;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessage;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessageDAO;
import com.doublechaintech.promoengine.formfieldmessage.FormFieldMessageTokens;
import com.doublechaintech.promoengine.formfield.FormField;
import com.doublechaintech.promoengine.formfield.FormFieldDAO;
import com.doublechaintech.promoengine.formfield.FormFieldTokens;
import com.doublechaintech.promoengine.formaction.FormAction;
import com.doublechaintech.promoengine.formaction.FormActionDAO;
import com.doublechaintech.promoengine.formaction.FormActionTokens;

public class DAOGroup {

	protected PlatformDAO platformDAO;

	protected ApplyConditionDAO applyConditionDAO;

	protected ActionTypeDAO actionTypeDAO;

	protected PromotionLevelDAO promotionLevelDAO;

	protected PromotionDAO promotionDAO;

	protected TargetUserRuleDAO targetUserRuleDAO;

	protected TargetActionDAO targetActionDAO;

	protected PromotionOfferDAO promotionOfferDAO;

	protected UserDomainDAO userDomainDAO;

	protected UserWhiteListDAO userWhiteListDAO;

	protected SecUserDAO secUserDAO;

	protected SecUserBlockingDAO secUserBlockingDAO;

	protected UserAppDAO userAppDAO;

	protected ListAccessDAO listAccessDAO;

	protected ObjectAccessDAO objectAccessDAO;

	protected LoginHistoryDAO loginHistoryDAO;

	protected GenericFormDAO genericFormDAO;

	protected FormMessageDAO formMessageDAO;

	protected FormFieldMessageDAO formFieldMessageDAO;

	protected FormFieldDAO formFieldDAO;

	protected FormActionDAO formActionDAO;

	

	public PlatformDAO getPlatformDAO(){
		return this.platformDAO;
	}
	public void setPlatformDAO(PlatformDAO dao){
		this.platformDAO = dao;
	}


	public ApplyConditionDAO getApplyConditionDAO(){
		return this.applyConditionDAO;
	}
	public void setApplyConditionDAO(ApplyConditionDAO dao){
		this.applyConditionDAO = dao;
	}


	public ActionTypeDAO getActionTypeDAO(){
		return this.actionTypeDAO;
	}
	public void setActionTypeDAO(ActionTypeDAO dao){
		this.actionTypeDAO = dao;
	}


	public PromotionLevelDAO getPromotionLevelDAO(){
		return this.promotionLevelDAO;
	}
	public void setPromotionLevelDAO(PromotionLevelDAO dao){
		this.promotionLevelDAO = dao;
	}


	public PromotionDAO getPromotionDAO(){
		return this.promotionDAO;
	}
	public void setPromotionDAO(PromotionDAO dao){
		this.promotionDAO = dao;
	}


	public TargetUserRuleDAO getTargetUserRuleDAO(){
		return this.targetUserRuleDAO;
	}
	public void setTargetUserRuleDAO(TargetUserRuleDAO dao){
		this.targetUserRuleDAO = dao;
	}


	public TargetActionDAO getTargetActionDAO(){
		return this.targetActionDAO;
	}
	public void setTargetActionDAO(TargetActionDAO dao){
		this.targetActionDAO = dao;
	}


	public PromotionOfferDAO getPromotionOfferDAO(){
		return this.promotionOfferDAO;
	}
	public void setPromotionOfferDAO(PromotionOfferDAO dao){
		this.promotionOfferDAO = dao;
	}


	public UserDomainDAO getUserDomainDAO(){
		return this.userDomainDAO;
	}
	public void setUserDomainDAO(UserDomainDAO dao){
		this.userDomainDAO = dao;
	}


	public UserWhiteListDAO getUserWhiteListDAO(){
		return this.userWhiteListDAO;
	}
	public void setUserWhiteListDAO(UserWhiteListDAO dao){
		this.userWhiteListDAO = dao;
	}


	public SecUserDAO getSecUserDAO(){
		return this.secUserDAO;
	}
	public void setSecUserDAO(SecUserDAO dao){
		this.secUserDAO = dao;
	}


	public SecUserBlockingDAO getSecUserBlockingDAO(){
		return this.secUserBlockingDAO;
	}
	public void setSecUserBlockingDAO(SecUserBlockingDAO dao){
		this.secUserBlockingDAO = dao;
	}


	public UserAppDAO getUserAppDAO(){
		return this.userAppDAO;
	}
	public void setUserAppDAO(UserAppDAO dao){
		this.userAppDAO = dao;
	}


	public ListAccessDAO getListAccessDAO(){
		return this.listAccessDAO;
	}
	public void setListAccessDAO(ListAccessDAO dao){
		this.listAccessDAO = dao;
	}


	public ObjectAccessDAO getObjectAccessDAO(){
		return this.objectAccessDAO;
	}
	public void setObjectAccessDAO(ObjectAccessDAO dao){
		this.objectAccessDAO = dao;
	}


	public LoginHistoryDAO getLoginHistoryDAO(){
		return this.loginHistoryDAO;
	}
	public void setLoginHistoryDAO(LoginHistoryDAO dao){
		this.loginHistoryDAO = dao;
	}


	public GenericFormDAO getGenericFormDAO(){
		return this.genericFormDAO;
	}
	public void setGenericFormDAO(GenericFormDAO dao){
		this.genericFormDAO = dao;
	}


	public FormMessageDAO getFormMessageDAO(){
		return this.formMessageDAO;
	}
	public void setFormMessageDAO(FormMessageDAO dao){
		this.formMessageDAO = dao;
	}


	public FormFieldMessageDAO getFormFieldMessageDAO(){
		return this.formFieldMessageDAO;
	}
	public void setFormFieldMessageDAO(FormFieldMessageDAO dao){
		this.formFieldMessageDAO = dao;
	}


	public FormFieldDAO getFormFieldDAO(){
		return this.formFieldDAO;
	}
	public void setFormFieldDAO(FormFieldDAO dao){
		this.formFieldDAO = dao;
	}


	public FormActionDAO getFormActionDAO(){
		return this.formActionDAO;
	}
	public void setFormActionDAO(FormActionDAO dao){
		this.formActionDAO = dao;
	}


	private interface BasicLoader{
	    BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception;
	    BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception;
	    BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception;
	}
	private static Map<String, BasicLoader> internalLoaderMap;
	static {
		internalLoaderMap = new HashMap<String, BasicLoader>();

		internalLoaderMap.put("Platform", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPlatformDAO().load(id, PlatformTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().present((Platform)data, tokens);
			}
		});

		internalLoaderMap.put("ApplyCondition", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getApplyConditionDAO().load(id, ApplyConditionTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getApplyConditionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getApplyConditionDAO().present((ApplyCondition)data, tokens);
			}
		});

		internalLoaderMap.put("ActionType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getActionTypeDAO().load(id, ActionTypeTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getActionTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getActionTypeDAO().present((ActionType)data, tokens);
			}
		});

		internalLoaderMap.put("PromotionLevel", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPromotionLevelDAO().load(id, PromotionLevelTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionLevelDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionLevelDAO().present((PromotionLevel)data, tokens);
			}
		});

		internalLoaderMap.put("Promotion", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPromotionDAO().load(id, PromotionTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionDAO().present((Promotion)data, tokens);
			}
		});

		internalLoaderMap.put("TargetUserRule", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getTargetUserRuleDAO().load(id, TargetUserRuleTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTargetUserRuleDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTargetUserRuleDAO().present((TargetUserRule)data, tokens);
			}
		});

		internalLoaderMap.put("TargetAction", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getTargetActionDAO().load(id, TargetActionTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTargetActionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTargetActionDAO().present((TargetAction)data, tokens);
			}
		});

		internalLoaderMap.put("PromotionOffer", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPromotionOfferDAO().load(id, PromotionOfferTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionOfferDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPromotionOfferDAO().present((PromotionOffer)data, tokens);
			}
		});

		internalLoaderMap.put("UserDomain", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, UserDomainTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().present((UserDomain)data, tokens);
			}
		});

		internalLoaderMap.put("UserWhiteList", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, UserWhiteListTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().present((UserWhiteList)data, tokens);
			}
		});

		internalLoaderMap.put("SecUser", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSecUserDAO().load(id, SecUserTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().present((SecUser)data, tokens);
			}
		});

		internalLoaderMap.put("SecUserBlocking", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSecUserBlockingDAO().load(id, SecUserBlockingTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserBlockingDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserBlockingDAO().present((SecUserBlocking)data, tokens);
			}
		});

		internalLoaderMap.put("UserApp", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserAppDAO().load(id, UserAppTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().present((UserApp)data, tokens);
			}
		});

		internalLoaderMap.put("ListAccess", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getListAccessDAO().load(id, ListAccessTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().present((ListAccess)data, tokens);
			}
		});

		internalLoaderMap.put("ObjectAccess", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getObjectAccessDAO().load(id, ObjectAccessTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getObjectAccessDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getObjectAccessDAO().present((ObjectAccess)data, tokens);
			}
		});

		internalLoaderMap.put("LoginHistory", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, LoginHistoryTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().present((LoginHistory)data, tokens);
			}
		});

		internalLoaderMap.put("GenericForm", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getGenericFormDAO().load(id, GenericFormTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getGenericFormDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getGenericFormDAO().present((GenericForm)data, tokens);
			}
		});

		internalLoaderMap.put("FormMessage", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormMessageDAO().load(id, FormMessageTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormMessageDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormMessageDAO().present((FormMessage)data, tokens);
			}
		});

		internalLoaderMap.put("FormFieldMessage", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormFieldMessageDAO().load(id, FormFieldMessageTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldMessageDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldMessageDAO().present((FormFieldMessage)data, tokens);
			}
		});

		internalLoaderMap.put("FormField", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormFieldDAO().load(id, FormFieldTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldDAO().present((FormField)data, tokens);
			}
		});

		internalLoaderMap.put("FormAction", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormActionDAO().load(id, FormActionTokens.withoutLists());
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormActionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormActionDAO().present((FormAction)data, tokens);
			}
		});

	}
	public BaseEntity loadBasicData(String type, String id){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicData(this, id);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public BaseEntity loadBasicDataWithTokens(String type, String id, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicDataWithToken(this, id, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public BaseEntity present(BaseEntity data, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(data.getInternalType());
	    if (loader == null || data == null) {
	    	return null;
	    }
	    try{
	    	return loader.present(this, data, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
}

