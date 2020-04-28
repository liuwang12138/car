package com.godric.cms.controller;

import com.godric.cms.common.dto.ResultMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author Godric
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultMessage<Void> error(Exception e) {
        e.printStackTrace();
        return ResultMessage.fail("调用过程发生未知异常！" + e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultMessage<Void> error(NullPointerException e) {
        e.printStackTrace();
        return ResultMessage.fail("调用过程发生空指针异常！");
    }


    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultMessage<Void> error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return ResultMessage.fail("调用过程发生数组下标越界！");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultMessage<Void> error(ConstraintViolationException e) {
        return ResultMessage.fail(e.getMessage());
    }

}
