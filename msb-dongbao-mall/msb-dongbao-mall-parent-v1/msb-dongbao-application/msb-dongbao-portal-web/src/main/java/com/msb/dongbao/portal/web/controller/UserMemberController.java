package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2020/12/29
 */
@RestController
@RequestMapping("/user-member")
public class UserMemberController {

	@Autowired
	UmsMemberService umsMemberService;

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}

	/**
	 * 获取验证码
	 * @return
	 */
	@GetMapping("/get-captcha")
	public String getCaptcha(){

		return "获取验证码";
	}

	/**
	 * 注册：传入验证码信息
	 * @param umsMemberRegisterParamDTO
	 * @return
	 */
	@PostMapping("/register")
	public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){

		return umsMemberService.register(umsMemberRegisterParamDTO);
	}


	@PostMapping("/login")
	public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO){
		return umsMemberService.login(umsMemberLoginParamDTO);
	}

	@PostMapping("/edit")
	@TokenCheck
	public ResultWrapper edit(@RequestBody UmsMember umsMember){
		System.out.println("edit");
		return umsMemberService.edit(umsMember);
	}

}