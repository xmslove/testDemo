package com.bus.validate.match;

import com.bus.validate.exception.SmartValidateException;
import com.bus.validate.rule.NotNullValidate;


/**
 * 非空验证
 * @author xms
 *
 */
public class MatchNotNullValidate extends AbstractMatchValidate<NotNullValidate>{

	@Override
	public void validate(NotNullValidate t,
			String fieldName,
			Object value) throws SmartValidateException {
		
		String defaultMessage = "%s为必填项";

		if(value == null || value.toString().trim().length() == 0) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName)));
			
		}
		
	}
}
