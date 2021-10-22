package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/4/9
 */
@RestController
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {

	@GetMapping("/generator")
	@TokenCheck(required = false)
	public void generatorCode(HttpServletRequest request, HttpServletResponse response) {

		HappyCaptcha.require(request,response)
				.style(CaptchaStyle.ANIM)
				.type(CaptchaType.ARITHMETIC_ZH)
				.build().finish();


	}


	@GetMapping("/verify")
	@TokenCheck(required = false)
	public String verify(String verifyCode, HttpServletRequest request) {

		Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);
		if (aBoolean){
			HappyCaptcha.remove(request);
			return "通过";
		}

		return "不通过";
	}
}
