package com.eastsoft.esgjyj.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 公共方法集合。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
public class Tools {
	/**
	 * 加密 druid 连接池密码。
	 * @param password 密码明文。
	 * @return 加密字符串。
	 * @throws Exception 加密时抛出异常。
	 * @see com.alibaba.druid.filter.config.ConfigTools#encrypt(String)
	 */
	public static String encryptDruidPassword(String password) throws Exception {
		password = ConfigTools.encrypt(password);
		return password;
	}
	
	/**
	 * 解密 druid 连接池密码。
	 * @param password 加密字符串。
	 * @return 解密后的密码明文。
	 * @throws Exception 解密时抛出异常。
	 * @see com.alibaba.druid.filter.config.ConfigTools#decrypt(String)
	 */
	public static String decryptDruidPassword(String password) throws Exception {
		password = ConfigTools.decrypt(password);
		return password;
	}
	
	/**
	 * 获取异常的堆栈跟踪信息。
	 * @param e 异常对象。
	 * @return 堆栈跟踪信息。
	 */
	public static String getExceptionStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
		String stackTrace = sw.toString().replaceAll("\n", "<br>&emsp;");
		return stackTrace;
	}
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if ( str == null || str.trim().equals("") )
		{
			return true;
		}
		return false;
	}
	/** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        return s;
    }
    
    public static String generateImgBase64Str(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return "";
		}
		String imgBase64Str = "data:image/jpg;base64," + Tools.base64Encode(bytes);
		return imgBase64Str;
	}
    
    public static String base64Encode(byte[] bytes) {
		String str = Base64.encodeBase64String(bytes);
		str = str.replaceAll(" ", "").replaceAll("\r\n", "");
		return str;
	}
}
