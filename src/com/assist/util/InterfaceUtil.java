package com.assist.util;

import com.assist.http.HttpRequest;

public class InterfaceUtil {
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
	public static final String ACCESS_TOKEN_PARAM="grant_type=client_credential&appid=wxebb8587d5f1c1686&secret=c5d0a5f6347bd2491b456323a77474bf";
	
	public static String getAccess_Token(){
		
		String s= HttpRequest.sendGet(ACCESS_TOKEN_URL, ACCESS_TOKEN_PARAM);
		return JsonUtil.ParseJson(s);
	}
	
	public static String getMenu(String access_token){
		String s=HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info","access_token=access_token");
		return s;
	}
}
