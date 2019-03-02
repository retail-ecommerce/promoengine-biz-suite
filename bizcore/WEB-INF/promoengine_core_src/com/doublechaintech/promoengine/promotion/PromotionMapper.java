
package com.doublechaintech.promoengine.promotion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.platform.Platform;

public class PromotionMapper extends BaseRowMapper<Promotion>{
	
	protected Promotion internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Promotion promotion = getPromotion();		
		 		
 		setId(promotion, rs, rowNumber); 		
 		setName(promotion, rs, rowNumber); 		
 		setValidAfter(promotion, rs, rowNumber); 		
 		setExpireTime(promotion, rs, rowNumber); 		
 		setLastUpdateTime(promotion, rs, rowNumber); 		
 		setPlatform(promotion, rs, rowNumber); 		
 		setVersion(promotion, rs, rowNumber);

		return promotion;
	}
	
	protected Promotion getPromotion(){
		return new Promotion();
	}		
		
	protected void setId(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(PromotionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setId(id);
	}
		
	protected void setName(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(PromotionTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setName(name);
	}
		
	protected void setValidAfter(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date validAfter = rs.getTimestamp(PromotionTable.COLUMN_VALID_AFTER);
		if(validAfter == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setValidAfter(convertToDateTime(validAfter));
	}
		
	protected void setExpireTime(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date expireTime = rs.getTimestamp(PromotionTable.COLUMN_EXPIRE_TIME);
		if(expireTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setExpireTime(convertToDateTime(expireTime));
	}
		
	protected void setLastUpdateTime(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(PromotionTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setPlatform(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(PromotionTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = promotion.getPlatform();
 		if( platform != null ){
 			//if the root object 'promotion' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		promotion.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(Promotion promotion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(PromotionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotion.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


