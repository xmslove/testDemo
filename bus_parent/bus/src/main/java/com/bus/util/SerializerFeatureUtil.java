package com.bus.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author xms
 *
 */

public class SerializerFeatureUtil {

	public static final SerializerFeature[] FEATURES = {
			SerializerFeature.WriteMapNullValue,
			SerializerFeature.QuoteFieldNames,
			SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullBooleanAsFalse,
			SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero,
			SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.IgnoreErrorGetter
	};
}
