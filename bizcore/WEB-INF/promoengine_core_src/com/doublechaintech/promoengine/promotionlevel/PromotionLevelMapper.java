
package com.doublechaintech.promoengine.promotionlevel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.platform.Platform;

public class PromotionLevelMapper extends BaseRowMapper<PromotionLevel>{
	
	protected PromotionLevel internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		PromotionLevel promotionLevel = getPromotionLevel();		
		 		
 		setId(promotionLevel, rs, rowNumber); 		
 		setName(promotionLevel, rs, rowNumber); 		
 		setLastUpdateTime(promotionLevel, rs, rowNumber); 		
 		setPlatform(promotionLevel, rs, rowNumber); 		
 		setVersion(promotionLevel, rs, rowNumber);

		return promotionLevel;
	}
	
	protected PromotionLevel getPromotionLevel(){
		return new PromotionLevel();
	}		
		
	protected void setId(PromotionLevel promotionLevel, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(PromotionLevelTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionLevel.setId(id);
	}
		
	protected void setName(PromotionLevel promotionLevel, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(PromotionLevelTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionLevel.setName(name);
	}
		
	protected void setLastUpdateTime(PromotionLevel promotionLevel, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(PromotionLevelTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionLevel.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPlatform(PromotionLevel promotionLevel, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(PromotionLevelTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = promotionLevel.getPlatform();
 		if( platform != null ){
 			//if the root object 'promotionLevel' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		promotionLevel.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(PromotionLevel promotionLevel, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(PromotionLevelTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionLevel.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


