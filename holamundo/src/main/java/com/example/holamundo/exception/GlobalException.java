package com.example.holamundo.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalException {

    // manejo de excepciones error de recuros no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handletNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);

    }

    // MANEJO DE EXCEPCIONES ERROR DE VALIDACION
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handletValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        String message = ex.getBindingResult()
                   .getFieldErrors()
                   .stream()
                   .map(error -> error.getField() + ": " + error.getDefaultMessage())
                   .collect(Collectors.joining("; "));
        return buildError(HttpStatus.BAD_REQUEST, message, request);
    }
    //manejo de  excepciones error de integridad de datos 
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex,
            HttpServletRequest request){
        return buildError(HttpStatus.CONFLICT, "conflicto de datos" , request);
     }

     @ExceptionHandler(Exception.class)
     public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest request){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "error interno del servidor", request );

     }
    

    private ResponseEntity<ErrorResponse> buildError(
            HttpStatus status, String message, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    
}
