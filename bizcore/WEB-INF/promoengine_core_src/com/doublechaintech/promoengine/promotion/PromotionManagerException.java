
package com.doublechaintech.promoengine.promotion;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class PromotionManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public PromotionManagerException(String string) {
		super(string);
	}
	public PromotionManagerException(Message message) {
		super(message);
	}
	public PromotionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


