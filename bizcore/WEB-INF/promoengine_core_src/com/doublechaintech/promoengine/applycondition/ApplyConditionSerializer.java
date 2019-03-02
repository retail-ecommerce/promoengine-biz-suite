package com.doublechaintech.promoengine.applycondition;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.promoengine.PromoengineObjectPlainCustomSerializer;
public class ApplyConditionSerializer extends PromoengineObjectPlainCustomSerializer<ApplyCondition>{

	@Override
	public void serialize(ApplyCondition applyCondition, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, applyCondition, provider);
		
	}
}


