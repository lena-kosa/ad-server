package com.ads.adserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AdServerException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ((AdServerException) ex).getStatus(), request);
    }

    // MethodArgumentNotValidException is already handled by Spring,
    // so the method above will not work for it. Overriding this function works.
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), headers, status);
    }

}

//    to create MethodArgumentNotValidException:
//
//    BeanPropertyBindingResult result = new BeanPropertyBindingResult(null, "campaign");
//    result.addError(new ObjectError("campaign", "Campaign should contain existing products"));
//    MethodParameter parameter = new MethodParameter(this.getClass().getDeclaredMethods()[0], 0);
//    throw new MethodArgumentNotValidException(parameter, result);

