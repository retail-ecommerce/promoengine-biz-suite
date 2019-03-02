
package com.doublechaintech.promoengine.applycondition;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.platform.Platform;

public class ApplyConditionMapper extends BaseRowMapper<ApplyCondition>{
	
	protected ApplyCondition internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ApplyCondition applyCondition = getApplyCondition();		
		 		
 		setId(applyCondition, rs, rowNumber); 		
 		setName(applyCondition, rs, rowNumber); 		
 		setLastUpdateTime(applyCondition, rs, rowNumber); 		
 		setPlatform(applyCondition, rs, rowNumber); 		
 		setVersion(applyCondition, rs, rowNumber);

		return applyCondition;
	}
	
	protected ApplyCondition getApplyCondition(){
		return new ApplyCondition();
	}		
		
	protected void setId(ApplyCondition applyCondition, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(ApplyConditionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		applyCondition.setId(id);
	}
		
	protected void setName(ApplyCondition applyCondition, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(ApplyConditionTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		applyCondition.setName(name);
	}
		
	protected void setLastUpdateTime(ApplyCondition applyCondition, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(ApplyConditionTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		applyCondition.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPlatform(ApplyCondition applyCondition, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(ApplyConditionTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = applyCondition.getPlatform();
 		if( platform != null ){
 			//if the root object 'applyCondition' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		applyCondition.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(ApplyCondition applyCondition, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(ApplyConditionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		applyCondition.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


