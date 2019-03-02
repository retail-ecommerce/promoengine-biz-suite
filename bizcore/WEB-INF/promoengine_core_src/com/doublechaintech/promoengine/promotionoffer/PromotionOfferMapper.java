
package com.doublechaintech.promoengine.promotionoffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.promoengine.BaseRowMapper;
import com.doublechaintech.promoengine.promotionlevel.PromotionLevel;
import com.doublechaintech.promoengine.promotion.Promotion;

public class PromotionOfferMapper extends BaseRowMapper<PromotionOffer>{
	
	protected PromotionOffer internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		PromotionOffer promotionOffer = getPromotionOffer();		
		 		
 		setId(promotionOffer, rs, rowNumber); 		
 		setName(promotionOffer, rs, rowNumber); 		
 		setPromotionLevel(promotionOffer, rs, rowNumber); 		
 		setParameter(promotionOffer, rs, rowNumber); 		
 		setPromotion(promotionOffer, rs, rowNumber); 		
 		setVersion(promotionOffer, rs, rowNumber);

		return promotionOffer;
	}
	
	protected PromotionOffer getPromotionOffer(){
		return new PromotionOffer();
	}		
		
	protected void setId(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(PromotionOfferTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionOffer.setId(id);
	}
		
	protected void setName(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(PromotionOfferTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionOffer.setName(name);
	}
		 		
 	protected void setPromotionLevel(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
 		String promotionLevelId = rs.getString(PromotionOfferTable.COLUMN_PROMOTION_LEVEL);
 		if( promotionLevelId == null){
 			return;
 		}
 		if( promotionLevelId.isEmpty()){
 			return;
 		}
 		PromotionLevel promotionLevel = promotionOffer.getPromotionLevel();
 		if( promotionLevel != null ){
 			//if the root object 'promotionOffer' already have the property, just set the id for it;
 			promotionLevel.setId(promotionLevelId);
 			
 			return;
 		}
 		promotionOffer.setPromotionLevel(createEmptyPromotionLevel(promotionLevelId));
 	}
 	
	protected void setParameter(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String parameter = rs.getString(PromotionOfferTable.COLUMN_PARAMETER);
		if(parameter == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionOffer.setParameter(parameter);
	}
		 		
 	protected void setPromotion(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
 		String promotionId = rs.getString(PromotionOfferTable.COLUMN_PROMOTION);
 		if( promotionId == null){
 			return;
 		}
 		if( promotionId.isEmpty()){
 			return;
 		}
 		Promotion promotion = promotionOffer.getPromotion();
 		if( promotion != null ){
 			//if the root object 'promotionOffer' already have the property, just set the id for it;
 			promotion.setId(promotionId);
 			
 			return;
 		}
 		promotionOffer.setPromotion(createEmptyPromotion(promotionId));
 	}
 	
	protected void setVersion(PromotionOffer promotionOffer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(PromotionOfferTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		promotionOffer.setVersion(version);
	}
		
		

 	protected PromotionLevel  createEmptyPromotionLevel(String promotionLevelId){
 		PromotionLevel promotionLevel = new PromotionLevel();
 		promotionLevel.setId(promotionLevelId);
 		promotionLevel.setVersion(Integer.MAX_VALUE);
 		return promotionLevel;
 	}
 	
 	protected Promotion  createEmptyPromotion(String promotionId){
 		Promotion promotion = new Promotion();
 		promotion.setId(promotionId);
 		promotion.setVersion(Integer.MAX_VALUE);
 		return promotion;
 	}
 	
}


