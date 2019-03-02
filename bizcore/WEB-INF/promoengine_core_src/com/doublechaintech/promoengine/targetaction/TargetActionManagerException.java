
package com.doublechaintech.promoengine.targetaction;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class TargetActionManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public TargetActionManagerException(String string) {
		super(string);
	}
	public TargetActionManagerException(Message message) {
		super(message);
	}
	public TargetActionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


