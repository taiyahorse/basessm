package com.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回类实体")
public class ResultDTO<T> {
    public static final Integer SUCCESS_CODE_200 = 200;
    public static final Integer FAIL_CODE_500 = 500;
    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAIL_MESSAGE = "fail";
    /**
     * 返回状态码
     */
    @ApiModelProperty("返回状态码，200表示后台接收请求成功，不代表最终处理成功")
    private Integer code;
    /**
     * 返回信息
     */
    @ApiModelProperty("成功状态码解释")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("本次操作，处理返回的内容")
    private T data;

    private ResultDTO() {
    }

    public static <T> ResultDTO<T> success() {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(SUCCESS_CODE_200);
        resultDTO.setMessage(SUCCESS_MESSAGE);
        return resultDTO;
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> resultDTO = success();
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> success(String message, T data) {
        ResultDTO<T> resultDTO = success();
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> success(Integer code, String message, T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail() {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(FAIL_CODE_500);
        resultDTO.setMessage(FAIL_MESSAGE);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(T data) {
        ResultDTO<T> resultDTO = fail();
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(String message, T data) {
        ResultDTO<T> resultDTO = fail();
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(Integer code, String message) {
        ResultDTO<T> resultDTO = fail();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(Integer code, String message, T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
