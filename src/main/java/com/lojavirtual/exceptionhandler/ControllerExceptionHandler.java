package com.lojavirtual.exceptionhandler;

import com.lojavirtual.exception.AcessoNaoEncontradoException;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestController
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, ex.getLocalizedMessage());
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(METHOD_NOT_ALLOWED, "Metodo nao suportado", ex.getMessage());
        return buildResponseEntityReturnsObject(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        ErrorResponse errorResponse = new ErrorResponse(UNAUTHORIZED, "Usuario ou senha invalidos", e.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(AcessoNaoEncontradoException.class)
    protected ResponseEntity<ErrorResponse> handleAcessoNaoEncontradoException(AcessoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(PessoaException.class)
    protected ResponseEntity<ErrorResponse> handlePessoaException(PessoaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    protected ResponseEntity<ErrorResponse> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        ;
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntityReturnsObject(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
