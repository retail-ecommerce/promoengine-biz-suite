
package com.doublechaintech.promoengine;
import java.util.HashMap;
import java.util.Map;

public class CustomRelation extends BaseRelation{

	protected void prepareRelation()
	{
		super.prepareRelation();
		//Uncomment to make any change to the relation type
		//replaceGenericRelation("ApplyCondition"                        , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("ActionType"                            , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("PromotionLevel"                        , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Promotion"                             , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("TargetUserRule"                        , BaseRelation.TRUST_CHAIN_ALL, "applyCondition");
		//replaceGenericRelation("TargetUserRule"                        , BaseRelation.TRUST_CHAIN_ALL, "promotion");
		//replaceGenericRelation("TargetAction"                          , BaseRelation.TRUST_CHAIN_ALL, "action");
		//replaceGenericRelation("TargetAction"                          , BaseRelation.TRUST_CHAIN_ALL, "promotion");
		//replaceGenericRelation("PromotionOffer"                        , BaseRelation.TRUST_CHAIN_ALL, "promotionLevel");
		//replaceGenericRelation("PromotionOffer"                        , BaseRelation.TRUST_CHAIN_ALL, "promotion");
		//replaceGenericRelation("UserWhiteList"                         , BaseRelation.TRUST_CHAIN_ALL, "domain");
		//replaceGenericRelation("SecUser"                               , BaseRelation.TRUST_CHAIN_ALL, "domain");
		//replaceGenericRelation("UserApp"                               , BaseRelation.TRUST_CHAIN_ALL, "secUser");
		//replaceGenericRelation("ListAccess"                            , BaseRelation.TRUST_CHAIN_ALL, "app");
		//replaceGenericRelation("ObjectAccess"                          , BaseRelation.TRUST_CHAIN_ALL, "app");
		//replaceGenericRelation("LoginHistory"                          , BaseRelation.TRUST_CHAIN_ALL, "secUser");
		//replaceGenericRelation("FormMessage"                           , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormFieldMessage"                      , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormField"                             , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormAction"                            , BaseRelation.TRUST_CHAIN_ALL, "form");

	}
	
	protected void prepareRelationIndex()
	{
		super.prepareRelationIndex();
		/*
		
		Note: you could delete some of the possible relations if you do not want it.
		Just uncomment the definition line and replaceRelationIndex line to replace existing one.
		
		*/
		//String [] applyConditionRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("ApplyCondition",applyConditionRelatedObjectNames);

		//String [] actionTypeRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("ActionType",actionTypeRelatedObjectNames);

		//String [] promotionLevelRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("PromotionLevel",promotionLevelRelatedObjectNames);

		//String [] promotionRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("Promotion",promotionRelatedObjectNames);

		//String [] targetUserRuleRelatedObjectNames = {"apply_condition:ApplyCondition","promotion:Promotion"};
		//replaceRelationIndex("TargetUserRule",targetUserRuleRelatedObjectNames);

		//String [] targetActionRelatedObjectNames = {"action:ActionType","promotion:Promotion"};
		//replaceRelationIndex("TargetAction",targetActionRelatedObjectNames);

		//String [] promotionOfferRelatedObjectNames = {"promotion_level:PromotionLevel","promotion:Promotion"};
		//replaceRelationIndex("PromotionOffer",promotionOfferRelatedObjectNames);

		//String [] userWhiteListRelatedObjectNames = {"domain:UserDomain"};
		//replaceRelationIndex("UserWhiteList",userWhiteListRelatedObjectNames);

		//String [] secUserRelatedObjectNames = {"domain:UserDomain"};
		//replaceRelationIndex("SecUser",secUserRelatedObjectNames);

		//String [] userAppRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("UserApp",userAppRelatedObjectNames);

		//String [] listAccessRelatedObjectNames = {"app:UserApp"};
		//replaceRelationIndex("ListAccess",listAccessRelatedObjectNames);

		//String [] objectAccessRelatedObjectNames = {"app:UserApp"};
		//replaceRelationIndex("ObjectAccess",objectAccessRelatedObjectNames);

		//String [] loginHistoryRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("LoginHistory",loginHistoryRelatedObjectNames);

		//String [] formMessageRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormMessage",formMessageRelatedObjectNames);

		//String [] formFieldMessageRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormFieldMessage",formFieldMessageRelatedObjectNames);

		//String [] formFieldRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormField",formFieldRelatedObjectNames);

		//String [] formActionRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormAction",formActionRelatedObjectNames);

		
		
	
	}
	
	
	@Override
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{

		String relation = super.getRelation(fromType, fromId, targetField, targetId);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}

}













