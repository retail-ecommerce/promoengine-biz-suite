package com.doublechaintech.promoengine.targetuserrule;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.promoengine.PromoengineObjectPlainCustomSerializer;
public class TargetUserRuleSerializer extends PromoengineObjectPlainCustomSerializer<TargetUserRule>{

	@Override
	public void serialize(TargetUserRule targetUserRule, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, targetUserRule, provider);
		
	}
}


