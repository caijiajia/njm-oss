package com.njm.oss.common.util;

import java.util.HashSet;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class Pinyin4j {

	// 将汉字转换为全拼  
    public static String getPingYin(String src) {  
  
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
          
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches(  
                        "[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                    t4 += t2[0];  
                } else  
                    t4 += java.lang.Character.toString(t1[i]);  
            }  
            // System.out.println(t4);  
            return t4;  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
        return t4;  
    }  
    
    // 返回中文的首字母  
    // chenxingxing 20170104
    public static String[] getPinYinHeadChar(String str) {
    	char[] t1 = str.toCharArray();
    	String[] ping = null;
    	HashSet<String> zm = new HashSet<String>();
    	HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
    	for(int i=0;i<t1.length;i++){
    		if(java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")){
    			ping = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
    			for(int j=0;j<ping.length;j++)
    				zm.add(ping[j].substring(0, 1).toUpperCase());
    			break;
    		}
    	}
    	if(zm.size()<1)
    		return null;
    	else
    		return zm.toArray(new String[]{"1"}); 
    }
    
    // 将字符串转移为ASCII码  
    public static String getCnASCII(String cnStr) {  
        StringBuffer strBuf = new StringBuffer();  
        byte[] bGBK = cnStr.getBytes();  
        for (int i = 0; i < bGBK.length; i++) {  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
        }  
        return strBuf.toString();  
    }  
}
