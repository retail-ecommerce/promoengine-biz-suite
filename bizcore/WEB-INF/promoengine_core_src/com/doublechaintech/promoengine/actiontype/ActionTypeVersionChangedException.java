
package com.doublechaintech.promoengine.actiontype;
import com.doublechaintech.promoengine.EntityNotFoundException;

public class ActionTypeVersionChangedException extends ActionTypeManagerException {
	private static final long serialVersionUID = 1L;
	public ActionTypeVersionChangedException(String string) {
		super(string);
	}


}


