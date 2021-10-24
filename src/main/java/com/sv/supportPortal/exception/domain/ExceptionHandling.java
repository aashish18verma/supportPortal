package com.sv.supportPortal.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.io.IOContext;
import com.sv.supportPortal.constant.SecurityConstant;
import com.sv.supportPortal.domain.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.processing.SupportedOptions;
import javax.persistence.NoResultException;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;


@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
 public final Logger LOGGER = LoggerFactory.getLogger(getClass());

 public static final String ACCOUNT_LOCKED ="Your account has been locked, please contact administration";
    public static final String METHOD_IS_NOT_ALLOWED ="This request is not allowed on this endpoint. please  send a '%s' request";
    public static final String INTERNAL_SERVER_ERROR_MSG ="Error occurred while processing request";
    public static final String INCORRECT_CREDENTIALS ="username / password incorrect. please try again";
    public static final String ACCOUNT_DISABLED ="Your account has been disabled, please contact administrator";
    public static final String ERROR_PROCCESSING_FILE ="An error occurred while proccessing file";
    public static final String NOT_ENOUGH_PERMISSION ="You do not have enough  perimission";
    public static final String ERROR_PATH = "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST,ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentials(){
        return createHttpResponse(HttpStatus.BAD_REQUEST,INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException(){
        return createHttpResponse(HttpStatus.FORBIDDEN,NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedEception(){
        return createHttpResponse(HttpStatus.UNAUTHORIZED,ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException ex){
        return createHttpResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistException ex){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage().toUpperCase());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(UsernameExistException ex){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailNotFoundException ex){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage().toUpperCase());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException ex){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage().toUpperCase());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        HttpMethod supportedMethod = Objects.requireNonNull(ex.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

   /* @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(NoHandlerFoundException ex){
        return createHttpResponse(HttpStatus.BAD_REQUEST, "THIS PAGE NOT FOUND......");
    }*/

    @ExceptionHandler(NotAnImageFileException.class)
    public ResponseEntity<HttpResponse> notAnImageFileException(NotAnImageFileException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception ex){
        LOGGER.error(ex.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR ,INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException ex){
        LOGGER.error(ex.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND ,ex.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> ioException(IOException ex){
        LOGGER.error(ex.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR ,ERROR_PROCCESSING_FILE);
    }




    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message),httpStatus);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404(){
        return createHttpResponse(HttpStatus.NOT_FOUND ,"There is no mapping for this url");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
