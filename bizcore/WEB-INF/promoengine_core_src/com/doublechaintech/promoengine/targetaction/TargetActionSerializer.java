package com.doublechaintech.promoengine.targetaction;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.promoengine.PromoengineObjectPlainCustomSerializer;
public class TargetActionSerializer extends PromoengineObjectPlainCustomSerializer<TargetAction>{

	@Override
	public void serialize(TargetAction targetAction, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, targetAction, provider);
		
	}
}


