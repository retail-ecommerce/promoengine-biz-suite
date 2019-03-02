
package com.doublechaintech.promoengine.promotionoffer;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class PromotionOfferManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public PromotionOfferManagerException(String string) {
		super(string);
	}
	public PromotionOfferManagerException(Message message) {
		super(message);
	}
	public PromotionOfferManagerException(List<Message> messageList) {
		super(messageList);
	}

}


