
package com.doublechaintech.promoengine.promotionlevel;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class PromotionLevelManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public PromotionLevelManagerException(String string) {
		super(string);
	}
	public PromotionLevelManagerException(Message message) {
		super(message);
	}
	public PromotionLevelManagerException(List<Message> messageList) {
		super(messageList);
	}

}


