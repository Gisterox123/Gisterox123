package com.msb.msbdongbaocommonutil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/1/19
 */
public class JwtUtil {

	private static final String secret = "asdfasdf";

	public static String createToken(String subject){

		String token = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return token;
	}

	public static String parseToken(String token){
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		String subject = body.getSubject();
		return subject;
	}

	public static void main(String[] args) throws InterruptedException {
		String name = "教育";

		String token = createToken(name);
		System.out.println("token:"+token);

		String srcStr = parseToken(token);
		System.out.println("解析出来："+srcStr);
		System.out.println("==========================");
		TimeUnit.SECONDS.sleep(4);
		srcStr = parseToken(token);
		System.out.println("解析出来："+srcStr);

	}
}
