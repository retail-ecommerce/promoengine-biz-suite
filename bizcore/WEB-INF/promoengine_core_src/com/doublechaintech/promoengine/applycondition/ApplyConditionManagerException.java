
package com.doublechaintech.promoengine.applycondition;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class ApplyConditionManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public ApplyConditionManagerException(String string) {
		super(string);
	}
	public ApplyConditionManagerException(Message message) {
		super(message);
	}
	public ApplyConditionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


