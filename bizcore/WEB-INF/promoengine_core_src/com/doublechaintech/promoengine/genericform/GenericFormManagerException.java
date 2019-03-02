
package com.doublechaintech.promoengine.genericform;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class GenericFormManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public GenericFormManagerException(String string) {
		super(string);
	}
	public GenericFormManagerException(Message message) {
		super(message);
	}
	public GenericFormManagerException(List<Message> messageList) {
		super(messageList);
	}

}


