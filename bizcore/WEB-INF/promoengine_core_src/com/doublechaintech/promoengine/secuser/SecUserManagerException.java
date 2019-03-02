
package com.doublechaintech.promoengine.secuser;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class SecUserManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public SecUserManagerException(String string) {
		super(string);
	}
	public SecUserManagerException(Message message) {
		super(message);
	}
	public SecUserManagerException(List<Message> messageList) {
		super(messageList);
	}

}


