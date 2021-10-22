package com.msb.dongbao.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msb.dongbao.ums.entity.UmsMember;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
@Repository
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

	UmsMember selectByName(String name);
}
