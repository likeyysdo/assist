package com.assist.util;

import java.util.ArrayList;
import java.util.List;

import com.assist.intertaceresultbean.AccessTokenResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
     * 解析Json数据
     * 
     * @param jsonString Json数据字符串
     */
    public static String ParseJson(String jsonString) {

        // 以employee为例解析，map类似
        JSONObject jb = JSONObject.fromObject(jsonString);
        return (String) jb.get("access_token");
//        JSONArray ja = jb.getJSONArray("access_token");
//
//        List<AccessTokenResult> atrList = new ArrayList<AccessTokenResult>();
//
//        AccessTokenResult atr = new AccessTokenResult();
//        
//        atr.setAccess_token(ja.getJSONObject(0).getString("access_token"));
//        atr.setExpires_in(ja.getJSONObject(0).getString("expires_in"));
//        atrList.add(atr);
//
//        System.out.println("\n将Json数据转换为Employee对象：");
//
//        AccessTokenResult atr2 = atrList.get(0);
////            System.out.println("access_token: " + atr.getAccess_token() + " expires_in: "
////                    + atr.getExpires_in() );
//        return atr2.getAccess_token();
        }

    }
