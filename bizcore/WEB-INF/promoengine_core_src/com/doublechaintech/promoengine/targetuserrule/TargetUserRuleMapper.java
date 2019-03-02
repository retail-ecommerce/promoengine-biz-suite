
package com.doublechaintech.promoengine.targetuserrule;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.applycondition.ApplyCondition;
import com.doublechaintech.promoengine.promotion.Promotion;

public class TargetUserRuleMapper extends BaseRowMapper<TargetUserRule>{
	
	protected TargetUserRule internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		TargetUserRule targetUserRule = getTargetUserRule();		
		 		
 		setId(targetUserRule, rs, rowNumber); 		
 		setName(targetUserRule, rs, rowNumber); 		
 		setApplyCondition(targetUserRule, rs, rowNumber); 		
 		setParameter(targetUserRule, rs, rowNumber); 		
 		setLastUpdateTime(targetUserRule, rs, rowNumber); 		
 		setPromotion(targetUserRule, rs, rowNumber); 		
 		setVersion(targetUserRule, rs, rowNumber);

		return targetUserRule;
	}
	
	protected TargetUserRule getTargetUserRule(){
		return new TargetUserRule();
	}		
		
	protected void setId(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(TargetUserRuleTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetUserRule.setId(id);
	}
		
	protected void setName(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(TargetUserRuleTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetUserRule.setName(name);
	}
		 		
 	protected void setApplyCondition(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
 		String applyConditionId = rs.getString(TargetUserRuleTable.COLUMN_APPLY_CONDITION);
 		if( applyConditionId == null){
 			return;
 		}
 		if( applyConditionId.isEmpty()){
 			return;
 		}
 		ApplyCondition applyCondition = targetUserRule.getApplyCondition();
 		if( applyCondition != null ){
 			//if the root object 'targetUserRule' already have the property, just set the id for it;
 			applyCondition.setId(applyConditionId);
 			
 			return;
 		}
 		targetUserRule.setApplyCondition(createEmptyApplyCondition(applyConditionId));
 	}
 	
	protected void setParameter(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String parameter = rs.getString(TargetUserRuleTable.COLUMN_PARAMETER);
		if(parameter == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetUserRule.setParameter(parameter);
	}
		
	protected void setLastUpdateTime(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(TargetUserRuleTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetUserRule.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPromotion(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
 		String promotionId = rs.getString(TargetUserRuleTable.COLUMN_PROMOTION);
 		if( promotionId == null){
 			return;
 		}
 		if( promotionId.isEmpty()){
 			return;
 		}
 		Promotion promotion = targetUserRule.getPromotion();
 		if( promotion != null ){
 			//if the root object 'targetUserRule' already have the property, just set the id for it;
 			promotion.setId(promotionId);
 			
 			return;
 		}
 		targetUserRule.setPromotion(createEmptyPromotion(promotionId));
 	}
 	
	protected void setVersion(TargetUserRule targetUserRule, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(TargetUserRuleTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetUserRule.setVersion(version);
	}
		
		

 	protected ApplyCondition  createEmptyApplyCondition(String applyConditionId){
 		ApplyCondition applyCondition = new ApplyCondition();
 		applyCondition.setId(applyConditionId);
 		applyCondition.setVersion(Integer.MAX_VALUE);
 		return applyCondition;
 	}
 	
 	protected Promotion  createEmptyPromotion(String promotionId){
 		Promotion promotion = new Promotion();
 		promotion.setId(promotionId);
 		promotion.setVersion(Integer.MAX_VALUE);
 		return promotion;
 	}
 	
}


