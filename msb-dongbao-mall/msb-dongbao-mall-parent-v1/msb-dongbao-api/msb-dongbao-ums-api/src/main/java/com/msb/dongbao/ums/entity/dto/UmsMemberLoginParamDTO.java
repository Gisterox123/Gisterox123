package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UmsMemberLoginParamDTO {
    private String username;
    private String password;
}