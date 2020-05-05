package com.godric.cms.common.dto;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author Godric
 */
@Getter
public class ResultMessage<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
    private Integer count;

    public static final int SUCCESS_CODE = 201;
    public static final int REFUSE_CODE = 203;
    public static final int FAIL_CODE = 205;

    public static ResultMessage<Void> success() {
        ResultMessage<Void> resultMessage = new ResultMessage<>();
        resultMessage.code = SUCCESS_CODE;
        return resultMessage;
    }

    public static ResultMessage<Void> success(String message) {
        ResultMessage<Void> resultMessage = new ResultMessage<>();
        resultMessage.code = SUCCESS_CODE;
        resultMessage.message = message;
        return resultMessage;
    }

    public static<T> ResultMessage<T> success(T data) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = SUCCESS_CODE;
        resultMessage.data = data;
        return resultMessage;
    }

    public static<T> ResultMessage<T> success(T data, Integer count) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = SUCCESS_CODE;
        resultMessage.data = data;
        resultMessage.count = count;
        return resultMessage;
    }

    public static<T> ResultMessage<T> success(String message, T data) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = SUCCESS_CODE;
        resultMessage.message = message;
        resultMessage.data = data;
        return resultMessage;
    }

    public static<T> ResultMessage<T> refuse() {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = REFUSE_CODE;
        return resultMessage;
    }

    public static<T> ResultMessage<T> refuse(String message) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = REFUSE_CODE;
        resultMessage.message = message;
        return resultMessage;
    }

    public static ResultMessage<Void> fail() {
        ResultMessage<Void> resultMessage = new ResultMessage<>();
        resultMessage.code = FAIL_CODE;
        return resultMessage;
    }

    public static<T> ResultMessage<T> fail(String message) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.code = FAIL_CODE;
        resultMessage.message = message;
        return resultMessage;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE == this.code;
    }

}
