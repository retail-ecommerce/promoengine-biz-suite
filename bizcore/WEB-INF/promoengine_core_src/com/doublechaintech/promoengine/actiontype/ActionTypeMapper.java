
package com.doublechaintech.promoengine.actiontype;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.platform.Platform;

public class ActionTypeMapper extends BaseRowMapper<ActionType>{
	
	protected ActionType internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ActionType actionType = getActionType();		
		 		
 		setId(actionType, rs, rowNumber); 		
 		setName(actionType, rs, rowNumber); 		
 		setLastUpdateTime(actionType, rs, rowNumber); 		
 		setPlatform(actionType, rs, rowNumber); 		
 		setVersion(actionType, rs, rowNumber);

		return actionType;
	}
	
	protected ActionType getActionType(){
		return new ActionType();
	}		
		
	protected void setId(ActionType actionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(ActionTypeTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		actionType.setId(id);
	}
		
	protected void setName(ActionType actionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(ActionTypeTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		actionType.setName(name);
	}
		
	protected void setLastUpdateTime(ActionType actionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(ActionTypeTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		actionType.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPlatform(ActionType actionType, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(ActionTypeTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = actionType.getPlatform();
 		if( platform != null ){
 			//if the root object 'actionType' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		actionType.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(ActionType actionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(ActionTypeTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		actionType.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


