package com.bus.util;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author xms
 *
 */

public class ConfigUtil {

	private static final Logger LOG = Logger.getLogger(ConfigUtil.class);

	private static Properties config = null;

	/**
	 * 返回系统config.properties配置信息
	 * @param key key�?
	 * @return value�?
	 */
	/*public static String getProperty(String key) {
		if (config == null) {
			synchronized (ConfigUtil.class) {
				if (null == config) {
					try {
						Resource resource = new ClassPathResource("config.properties");
						config = PropertiesLoaderUtils.loadProperties(resource);
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}

		return config.getProperty(key);
	}*/
}
