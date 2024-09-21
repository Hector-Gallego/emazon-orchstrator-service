package com.resourceserver.emazonorchestratorservice.configuration.exceptionhandler;


import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;
import com.resourceserver.emazonorchestratorservice.domain.exceptions.SupplyOrchestrationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {



    @ExceptionHandler(SupplyOrchestrationException.class)
    public ResponseEntity<CustomErrorResponse> handleFieldLimitExceededException(SupplyOrchestrationException exception) {
        return buildErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, Collections.emptyList());
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException exception){
        return  buildErrorResponse(exception, HttpStatus.FORBIDDEN, Collections.emptyList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialsException(BadCredentialsException exception){
        return  buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialsException(InsufficientAuthenticationException exception){
        return  buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(RuntimeException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        List<String> errorList = exception
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ErrorMessageConstants.INVALID_FIELDS,
                errorList,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<CustomErrorResponse> buildErrorResponse(Exception exception, HttpStatus status, List<String> errors) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                status.value(),
                exception.getMessage(),
                errors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(customErrorResponse, status);
    }
}
