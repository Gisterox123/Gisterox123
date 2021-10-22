package com.msb.dongbao.portal.web.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证码vo 对象
 * @author 晁鹏飞
 */
@Data
public class VerificationVO implements Serializable {


    /**
     *  滑块图
     */
    private String slidingImage;

    /**
     * 原图
     */
    private String originalImage;

    /**
     *  宽
     */
    private Integer xWidth;

    /**
     *  高
     */
    private Integer yHeight;

}