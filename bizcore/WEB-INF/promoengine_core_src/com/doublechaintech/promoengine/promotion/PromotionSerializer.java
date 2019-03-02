package com.doublechaintech.promoengine.promotion;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.promoengine.PromoengineObjectPlainCustomSerializer;
public class PromotionSerializer extends PromoengineObjectPlainCustomSerializer<Promotion>{

	@Override
	public void serialize(Promotion promotion, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, promotion, provider);
		
	}
}


