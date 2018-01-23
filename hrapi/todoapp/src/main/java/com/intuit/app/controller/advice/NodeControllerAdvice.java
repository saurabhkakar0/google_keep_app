package com.intuit.app.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.intuit.app.controller.NodeController;
import com.intuit.app.web.BaseAPIResponse;


@ControllerAdvice(basePackageClasses = {NodeController.class})
public class NodeControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Requested resource does not found");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseAPIResponse> handleException(Exception ex){
        BaseAPIResponse response = new BaseAPIResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setStatusMessage(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @Value("${app.version}")
    public String appVersion;

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue mappingJacksonValue, MediaType mediaType, MethodParameter methodParameter, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpServletRequest currRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        Object body = mappingJacksonValue.getValue();
        if (body != null && currRequest != null && BaseAPIResponse.class.isAssignableFrom(body.getClass())) {

            BaseAPIResponse baseResponse = (BaseAPIResponse) body;
            baseResponse.setVersion(appVersion);
        }
    }
}
