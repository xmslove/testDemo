package com.bus.validate;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.bus.validate.match.AbstractMatchValidate;
import com.bus.validate.match.MatchMaxLengthValidate;
import com.bus.validate.match.MatchMaxValueValidate;
import com.bus.validate.match.MatchMinLengthValidate;
import com.bus.validate.match.MatchMinValueValidate;
import com.bus.validate.match.MatchNotNullValidate;
import com.bus.validate.match.MatchRangeLengthValidate;
import com.bus.validate.match.MatchRangeValueValidate;
import com.bus.validate.match.MatchRegexpValidate;
import com.bus.validate.rule.MaxLengthValidate;
import com.bus.validate.rule.MaxValueValidate;
import com.bus.validate.rule.MinLengthValidate;
import com.bus.validate.rule.MinValueValidate;
import com.bus.validate.rule.NotNullValidate;
import com.bus.validate.rule.RangeLengthValidate;
import com.bus.validate.rule.RangeValueValidate;
import com.bus.validate.rule.RegexpValidate;


public class ValidateRulePool {

	private static final Map<Class<? extends Annotation>, AbstractMatchValidate<? extends Annotation>> matchValidatePool = new HashMap<>();

	static {
		
		mount(MaxLengthValidate.class, new MatchMaxLengthValidate());
		mount(MaxValueValidate.class, new MatchMaxValueValidate());
		mount(MinLengthValidate.class, new MatchMinLengthValidate());
		mount(MinValueValidate.class, new MatchMinValueValidate());
		mount(NotNullValidate.class, new MatchNotNullValidate());
		mount(RangeLengthValidate.class, new MatchRangeLengthValidate());
		mount(RangeValueValidate.class, new MatchRangeValueValidate());
		mount(RegexpValidate.class, new MatchRegexpValidate());

	}
	private ValidateRulePool() {
		
	}
	
	public static void mount(Class<? extends Annotation> alias, AbstractMatchValidate<? extends Annotation> handler) {
		
		matchValidatePool.put(alias, handler);
		
	}
	
	public static AbstractMatchValidate<? extends Annotation> get(Class<? extends Annotation> alias) {

		return matchValidatePool.get(alias);
	}
}
