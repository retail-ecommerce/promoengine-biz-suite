
package com.doublechaintech.promoengine.userwhitelist;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class UserWhiteListManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public UserWhiteListManagerException(String string) {
		super(string);
	}
	public UserWhiteListManagerException(Message message) {
		super(message);
	}
	public UserWhiteListManagerException(List<Message> messageList) {
		super(messageList);
	}

}


