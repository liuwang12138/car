package com.godric.cms.common.handler;

import com.godric.cms.common.dto.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Godric
 */
@Slf4j
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMessage<Void> error(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        StringBuilder errorInfo = new StringBuilder();
        if (!fieldErrors.isEmpty()) {
            errorInfo.append(fieldErrors.get(0).getField());
            for (int i=1; i<fieldErrors.size(); i++) {
                errorInfo.append(", ").append(fieldErrors.get(i).getField());
            }
        }
        errorInfo.append(" 字段参数不合法！");
        return ResultMessage.fail(errorInfo.toString());
    }

}
