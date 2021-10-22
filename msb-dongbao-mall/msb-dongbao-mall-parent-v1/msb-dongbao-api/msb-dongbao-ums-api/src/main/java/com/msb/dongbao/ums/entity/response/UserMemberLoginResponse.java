package com.msb.dongbao.ums.entity.response;

import com.msb.dongbao.ums.entity.UmsMember;
import lombok.Data;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/1/19
 */
@Data
public class UserMemberLoginResponse {

	private String token;

	private UmsMember umsMember;

}
