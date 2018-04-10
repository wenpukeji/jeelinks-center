package com.wenpu.jeelinks.common.utils;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import com.wenpu.jeelinks.common.utils.StringUtils;
public class RequestParameterUtil {

	/***
     * 根据参数名获取Integer类型的参数值
     * @param request 请求体
     * @param paramName 参数名称
     * @param defaultValue 默认值
     * @return
     */
    public static Integer getInteger(HttpServletRequest request,String paramName,Integer defaultValue){
        String v = StringUtils.trim(request.getParameter(paramName));
        if(StringUtils.isNotBlank(v) && NumberUtils.isNumber(v.trim())){
            return Integer.valueOf(v.trim());
        }
        return defaultValue;
    }

    /***
     * 根据参数名称获取Long类型的参数值
     * @param request 请求体
     * @param paramName 参数名称
     * @param defaultValue 默认值
     * @return
     */
    public static Long getLong(HttpServletRequest request,String paramName,Long defaultValue){
        String v = StringUtils.trim(request.getParameter(paramName));
        if(StringUtils.isNotBlank(v) && NumberUtils.isNumber(v.trim())){
            return Long.valueOf(v.trim());
        }
        return defaultValue;
    }

    /***
     * 根据参数名称获取String类型的参数值
     * @param request 请求体
     * @param paramName 参数名称
     * @param defaultValue  默认值
     * @return
     */
    public static String getString(HttpServletRequest request,String paramName,String defaultValue){
        String v = StringUtils.trim(request.getParameter(paramName));
        if(StringUtils.isNotBlank(v)){
            return v;
        }
        return defaultValue;
    }

    /***
     * 根据参数名称获取BigDecimal类型的参数值
     * @param request 请求体
     * @param paramName 参数名称
     * @param defaultValue  默认值
     * @return
     */
    public static BigDecimal getBigDecimal(HttpServletRequest request,String paramName,BigDecimal defaultValue){
        String v = request.getParameter(paramName);
        if(StringUtils.isNotBlank(v) && NumberUtils.isNumber(v)){
            return new BigDecimal(v);
        }
        return defaultValue;
    }

    /***
     * 根据参数名称获取Float类型的参数值
     * @param request 请求体
     * @param paramName 参数名称
     * @param defaultValue  默认值
     * @return
     */
    public static Float getFloat(HttpServletRequest request,String paramName,Float defaultValue){
        String v = request.getParameter(paramName);
        if(StringUtils.isNotBlank(v) && NumberUtils.isNumber(v)){
            return Float.valueOf(v.trim());
        }
        return defaultValue;
    }
	
}
