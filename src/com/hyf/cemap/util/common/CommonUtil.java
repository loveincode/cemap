package com.hyf.cemap.util.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 
*  @author  huyifan
*  @ClassName  CommonUtil  
*  @date  2016年12月20日 下午1:01:29
 */
public class CommonUtil {
	

    /**
     * 生成UUID(去除‘-’)
     * @return
     */
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toString();
    }
    
    /**
     * 
    * @Title: CommonUtil.java
    * @Description: MD5加密
    * @date 2016年3月12日 下午1:24:59
    * @return String
     */
    @SuppressWarnings("unchecked")
    public static String MD5Encoder(String pwd){
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
    	md5.setEncodeHashAsBase64(false);
		String newPwd = md5.encodePassword(pwd, null);
		map.put("111", "111");
		return newPwd;
    }
    static {
    	System.out.println("this is static part");
    }
    
    public CommonUtil(){
    	System.out.println("this is construct");
    }
    public static final Map map = new HashMap<String, String>();
    
    //test
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
    	map.put("222", "33");
		System.out.println(MD5Encoder("csstsari"));
		CommonUtil cu = new CommonUtil();
		cu.MD5Encoder("dd");
		map.put("44", "444");
		String str = "";
    	Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry<String, String> entry = it.next();
    		str += "["+entry.getKey()+":"+entry.getValue()+"]";
    	}
		System.out.println(str);
	}

}
