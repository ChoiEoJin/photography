package com.collabo.photography.common.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageUtil {
	Logger log = Logger.getLogger(this.getClass());
	
	ReloadableResourceBundleMessageSource messageSource;
	
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return messageSource;
	}

	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.messageSource = reloadableResourceBundleMessageSource;
	}

	public String getMessage(String key) {
		log.debug("key=[" + key + "]");
		return messageSource.getMessage(key, new String[]{}, getLocale());
	}
	
	public String getMessage(String key, String param) {
		log.debug("key=[" + key + "], param=[" + param + "]");
		return messageSource.getMessage(key, new String[]{param}, getLocale());
	}
	public String getMessage(String key, String[] params) {
		log.debug("key=[" + key + "]");
		return messageSource.getMessage(key, params, getLocale());
	}
	
    public Locale getLocale() {
    	log.debug("getLocale()==>"+LocaleContextHolder.getLocale());
        return LocaleContextHolder.getLocale();
    }
}
