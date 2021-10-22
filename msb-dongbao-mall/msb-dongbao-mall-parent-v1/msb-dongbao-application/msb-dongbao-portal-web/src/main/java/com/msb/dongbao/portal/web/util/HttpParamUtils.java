package com.msb.dongbao.portal.web.util;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/5/26
 */
@Slf4j
public class HttpParamUtils {
	/**
	 * 获取所有参数，包括 url和body
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static SortedMap<String,String> getAllParams(HttpServletRequest request) throws IOException {

		// 获取 url上的参数
		Map<String, String> urlParams = getUrlParams(request);
		System.out.println("url 参数："+urlParams);

		// 获取 body上的参数
		Map<String, String> bodyParams = getBodyParams(request);

		// 总的参数的map
		SortedMap<String , String> allMap = new TreeMap<>();
		for (Map.Entry entry : urlParams.entrySet()){
			allMap.put((String) entry.getKey() , (String)entry.getValue());
		}
		for (Map.Entry entry : bodyParams.entrySet()){
			allMap.put((String) entry.getKey() , (String)entry.getValue());
		}

		log.info("所有的参数："+allMap);

		return allMap;
	}

	/**
	 * 获取body中的参数
	 * 为空的判断 ，同学自己加
	 */
	private static Map<String,String> getBodyParams(HttpServletRequest request) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream())) ;

		StringBuilder sb = new StringBuilder();
		// 读取 流
		String s = "";
		while ((s=reader.readLine())!=null){
			sb.append(s);
		}

		// 转map
		Map map = JSONObject.parseObject(sb.toString(), Map.class);

		System.out.println("body参数："+map);
		return map;

	}

	/**
	 * 获取 url中的参数  /asdf/asd?a=b&b=c
	 * 为空的判断 ，同学自己加
	 * @param request
	 * @return
	 */
	private static Map<String,String> getUrlParams(HttpServletRequest request){

		String queryParam = "";

		try {
			if (!StringUtils.isBlank(request.getQueryString())){
				queryParam = URLDecoder.decode(request.getQueryString(),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Map<String,String> result = new HashMap<>();
		String[] split = queryParam.split("&");
		for (String s: split){
			int i = s.indexOf("=");
			result.put(s.substring(0,i),s.substring(i+1));
		}

		System.out.println("url 参数："+result);
		return result;

	}
}