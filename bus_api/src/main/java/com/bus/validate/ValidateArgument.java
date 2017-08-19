package com.bus.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bus.validate.rule.MaxLengthValidate;
import com.bus.validate.rule.MaxValueValidate;
import com.bus.validate.rule.MinLengthValidate;
import com.bus.validate.rule.MinValueValidate;
import com.bus.validate.rule.NotNullValidate;
import com.bus.validate.rule.RangeLengthValidate;
import com.bus.validate.rule.RangeValueValidate;
import com.bus.validate.rule.RegexpValidate;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateArgument {

	MaxLengthValidate[] maxLength() default {};
	MinLengthValidate[] minLength() default {};
	MaxValueValidate[] maxValue() default {};
	MinValueValidate[] minValue() default {};
	NotNullValidate[] notNull() default {};
	RangeLengthValidate[] rangeLength() default {};
	RangeValueValidate[] rangeValue() default {};
	RegexpValidate[] regexp() default {};
}
