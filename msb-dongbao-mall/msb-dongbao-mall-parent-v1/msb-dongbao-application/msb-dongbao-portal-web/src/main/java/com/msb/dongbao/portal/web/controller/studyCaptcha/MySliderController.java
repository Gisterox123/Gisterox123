package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.portal.web.util.SliderUtil;
import com.msb.dongbao.portal.web.util.VerificationVO;
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
@RequestMapping("/my-slider")
public class MySliderController {

	@GetMapping("/generator")
	public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {

		return SliderUtil.cut();
	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {

//		Boolean aBoolean = kaptcha.validate(verifyCode, 10);
//		if (aBoolean) {
//			return "通过";
//		}
		return "不通过";
	}
}
