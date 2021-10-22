package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.baomidou.kaptcha.Kaptcha;
import com.msb.dongbao.portal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/4/12
 */
@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {

	@Autowired
	private Kaptcha kaptcha;



	@GetMapping("/generator")
	public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
		kaptcha.render();
	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {

		Boolean aBoolean = kaptcha.validate(verifyCode, 10);
		if (aBoolean) {
			return "通过";
		}
		return "不通过";
	}

	@Autowired
	MyGoogleKaptcha myGoogleKaptcha;

	@GetMapping("/generator-my")
	public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response) {
		myGoogleKaptcha.render();
	}

	@GetMapping("/verify-my")
	public String verifyMy(String verifyCode, HttpServletRequest request) {

		Boolean aBoolean = myGoogleKaptcha.validate(verifyCode, 10);
		if (aBoolean) {
			return "通过";
		}
		return "不通过";
	}
}
