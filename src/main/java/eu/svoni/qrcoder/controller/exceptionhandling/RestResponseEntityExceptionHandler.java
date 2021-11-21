package eu.svoni.qrcoder.controller.exceptionhandling;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper om;

    public RestResponseEntityExceptionHandler(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest request)
        throws JsonProcessingException {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if ( ex instanceof IllegalArgumentException ) status = HttpStatus.UNPROCESSABLE_ENTITY;
        if ( ex instanceof EntityNotFoundException) status = HttpStatus.NOT_FOUND;

        ExceptionResponseBody erb = ExceptionResponseBody.builder()
                                                         .path(((ServletWebRequest)request).getRequest().getRequestURI())
                                                         .method(((ServletWebRequest)request).getRequest().getMethod())
                                                         .message(ex.getMessage())
                                                         .build();

        return handleExceptionInternal(ex,
                                       om.writeValueAsString(erb),
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

}

