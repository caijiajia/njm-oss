package com.njm.oss.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhcacheUtil {
	private static Cache cache = SpringFactory.getBean("contentCache");

    public static void set(String key, Object value){
        Element element = new Element(key, value);
        cache.put(element);
    }
    
    public static Object getObject(String key){
    	Element element = cache.get(key);
        return element == null?null:element.getObjectValue();
    }
    
    public static String get(String key){
    	Element element = cache.get(key);
    	return element == null?null:(String)element.getObjectValue();
    }
    
    public static boolean remove(String key){
       return cache.remove(key);
    }

}