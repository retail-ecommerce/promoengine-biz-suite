
package com.doublechaintech.promoengine.targetuserrule;
//import com.doublechaintech.promoengine.EntityNotFoundException;
import com.doublechaintech.promoengine.PromoengineException;
import com.doublechaintech.promoengine.Message;
import java.util.List;

public class TargetUserRuleManagerException extends PromoengineException {
	private static final long serialVersionUID = 1L;
	public TargetUserRuleManagerException(String string) {
		super(string);
	}
	public TargetUserRuleManagerException(Message message) {
		super(message);
	}
	public TargetUserRuleManagerException(List<Message> messageList) {
		super(messageList);
	}

}


