package com.msb.dongbao.ums.service.impl;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.entity.response.UserMemberLoginResponse;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import com.msb.dongbao.ums.service.UmsMemberService;
import com.msb.msbdongbaocommonutil.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
@Service
public class UmsMemberServiceImpl  implements UmsMemberService {

	@Autowired
	UmsMemberMapper umsMemberMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberREgisterParamDTO){

		UmsMember umsMember = new UmsMember();
		BeanUtils.copyProperties(umsMemberREgisterParamDTO,umsMember);

		String encode = passwordEncoder.encode(umsMemberREgisterParamDTO.getPassword());

		umsMember.setPassword(encode);

		umsMemberMapper.insert(umsMember);
		return ResultWrapper.getSuccessBuilder().build();
	}

	@Override
	public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {

		UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
		if(null !=  umsMember){
			String passwordDb = umsMember.getPassword();

			if(!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(),passwordDb)){
				return ResultWrapper.getFailBuilder().code(StateCodeEnum.PASSWORD_ERROR.getCode()).msg(StateCodeEnum.PASSWORD_ERROR.getMsg()).build();
			}

		}else{
			return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_EMPTY.getCode()).msg(StateCodeEnum.USER_EMPTY.getMsg()).build();
		}

		String token = JwtUtil.createToken(umsMember.getId()+"");

		UserMemberLoginResponse userMemberLoginResponse = new UserMemberLoginResponse();
		userMemberLoginResponse.setToken(token);

		umsMember.setPassword("");
		userMemberLoginResponse.setUmsMember(umsMember);
		return ResultWrapper.getSuccessBuilder().data(userMemberLoginResponse).build();
	}

	@Override
	public ResultWrapper edit(UmsMember umsMember) {

		umsMemberMapper.updateById(umsMember);
		return ResultWrapper.getSuccessBuilder().data(umsMember).build();
	}
}
