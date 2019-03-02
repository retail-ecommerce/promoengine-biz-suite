
package com.doublechaintech.promoengine.actiontype;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class ActionTypeManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public ActionTypeManagerException(String string) {
		super(string);
	}
	public ActionTypeManagerException(Message message) {
		super(message);
	}
	public ActionTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


