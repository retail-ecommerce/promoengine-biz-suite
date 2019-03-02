
package com.doublechaintech.promoengine.formfieldmessage;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class FormFieldMessageManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public FormFieldMessageManagerException(String string) {
		super(string);
	}
	public FormFieldMessageManagerException(Message message) {
		super(message);
	}
	public FormFieldMessageManagerException(List<Message> messageList) {
		super(messageList);
	}

}


