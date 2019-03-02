
package com.doublechaintech.promoengine.targetaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.promotion.Promotion;
import com.doublechaintech.promoengine.actiontype.ActionType;

public class TargetActionMapper extends BaseRowMapper<TargetAction>{
	
	protected TargetAction internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		TargetAction targetAction = getTargetAction();		
		 		
 		setId(targetAction, rs, rowNumber); 		
 		setName(targetAction, rs, rowNumber); 		
 		setAction(targetAction, rs, rowNumber); 		
 		setParameter(targetAction, rs, rowNumber); 		
 		setLastUpdateTime(targetAction, rs, rowNumber); 		
 		setPromotion(targetAction, rs, rowNumber); 		
 		setVersion(targetAction, rs, rowNumber);

		return targetAction;
	}
	
	protected TargetAction getTargetAction(){
		return new TargetAction();
	}		
		
	protected void setId(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(TargetActionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetAction.setId(id);
	}
		
	protected void setName(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(TargetActionTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetAction.setName(name);
	}
		 		
 	protected void setAction(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
 		String actionTypeId = rs.getString(TargetActionTable.COLUMN_ACTION);
 		if( actionTypeId == null){
 			return;
 		}
 		if( actionTypeId.isEmpty()){
 			return;
 		}
 		ActionType actionType = targetAction.getAction();
 		if( actionType != null ){
 			//if the root object 'targetAction' already have the property, just set the id for it;
 			actionType.setId(actionTypeId);
 			
 			return;
 		}
 		targetAction.setAction(createEmptyAction(actionTypeId));
 	}
 	
	protected void setParameter(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String parameter = rs.getString(TargetActionTable.COLUMN_PARAMETER);
		if(parameter == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetAction.setParameter(parameter);
	}
		
	protected void setLastUpdateTime(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(TargetActionTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetAction.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPromotion(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
 		String promotionId = rs.getString(TargetActionTable.COLUMN_PROMOTION);
 		if( promotionId == null){
 			return;
 		}
 		if( promotionId.isEmpty()){
 			return;
 		}
 		Promotion promotion = targetAction.getPromotion();
 		if( promotion != null ){
 			//if the root object 'targetAction' already have the property, just set the id for it;
 			promotion.setId(promotionId);
 			
 			return;
 		}
 		targetAction.setPromotion(createEmptyPromotion(promotionId));
 	}
 	
	protected void setVersion(TargetAction targetAction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(TargetActionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		targetAction.setVersion(version);
	}
		
		

 	protected ActionType  createEmptyAction(String actionTypeId){
 		ActionType actionType = new ActionType();
 		actionType.setId(actionTypeId);
 		actionType.setVersion(Integer.MAX_VALUE);
 		return actionType;
 	}
 	
 	protected Promotion  createEmptyPromotion(String promotionId){
 		Promotion promotion = new Promotion();
 		promotion.setId(promotionId);
 		promotion.setVersion(Integer.MAX_VALUE);
 		return promotion;
 	}
 	
}


