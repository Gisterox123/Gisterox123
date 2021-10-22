package com.msb.dongbao.portal.web.controller.api;

import com.msb.msbdongbaocommonutil.MD5Util;
import com.msb.msbdongbaocommonutil.Sha256Utils;

import java.util.*;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/5/24
 */
public class CheckUtils {

	/**
	* app secret和 appId，一一对应
	 */
	public static String appSecret = "aaa";

	// 校验 签名
	public static boolean checkSign(Map<String,String> map){
		String sign = (String) map.get("sign");
		map.remove("sign");
		// 生成sign
		String s = CheckUtils.generatorSign(map);
		if (s.equals(sign)){
			return true;
		}else {
			return false;
		}

	}

	// 根据map生成签名
	public static String generatorSign(Map<String,String> map){
		map.remove("sign");
		// 排序:
		Map<String, String> stringObjectMap = sortMapByKey(map);
		// 转格式:   name=张三&age=10,:  name,张三,age,10
		Set<Map.Entry<String, String>> entries = stringObjectMap.entrySet();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String,String> e : entries){
			sb.append(e.getKey()+","+e.getValue()).append("#");
		}


		// 组装secret  在参数的后面 添加 secret
		sb.append("secret").append(appSecret);
		// 生成签名
		return MD5Util.md5(sb.toString());
		// sha256生成 签名
//		return Sha256Utils.getSHA256(sb.toString());
	}

	public static Map<String,String> sortMapByKey(Map<String,String> map){
		// 判断一下map是否为空，自己写

		Map<String,String> sortMap = new TreeMap<>(new MyMapComparator());

		sortMap.putAll(map);

		return  sortMap;

	}

	static class MyMapComparator implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}

	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<>();
		map.put("appId","1");
		map.put("name","2");
		map.put("urlParam","3");

		Map<String, String> stringObjectMap = sortMapByKey(map);
		System.out.println(stringObjectMap);



		String s = generatorSign(map);
		// 74f0c8c14fd2869121c910601e9ea859
		System.out.println(s);
	}

}
