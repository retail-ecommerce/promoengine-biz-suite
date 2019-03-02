
package com.doublechaintech.promoengine.targetaction;
import com.doublechaintech.promoengine.AccessKey;


public class TargetActionTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	static final String TABLE_NAME="target_action_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_ACTION = "action";
	static final String COLUMN_PARAMETER = "parameter";
	static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";
	static final String COLUMN_PROMOTION = "promotion";
	static final String COLUMN_VERSION = "version";
 
	static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_ACTION, COLUMN_PARAMETER, COLUMN_LAST_UPDATE_TIME, COLUMN_PROMOTION, 
		COLUMN_VERSION};
	static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_ACTION, COLUMN_PARAMETER, COLUMN_LAST_UPDATE_TIME, COLUMN_PROMOTION
		};
	
	
}


