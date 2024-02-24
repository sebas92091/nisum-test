package com.technical.exercise.users.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseExceptionHandler {
  static final String MESSAGE = "message";

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = MESSAGE;
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(PasswordValidationException.class)
  public Map<String, String> handleInvalidPasswordExceptions(PasswordValidationException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = MESSAGE;
    String errorMessage = ex.getMessage();
    errors.put(fieldName, errorMessage);
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  public Map<String, String> handleInvalidPasswordExceptions(EmailAlreadyRegisteredException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = MESSAGE;
    String errorMessage = ex.getMessage();
    errors.put(fieldName, errorMessage);
    return errors;
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingRequestHeaderException.class)
  public Map<String, String> handleInvalidPasswordExceptions(MissingRequestHeaderException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = MESSAGE;
    String errorMessage = ex.getMessage();
    errors.put(fieldName, errorMessage);
    return errors;
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ResourceNotFoundException.class)
  public Map<String, String> handleInvalidPasswordExceptions(ResourceNotFoundException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = MESSAGE;
    String errorMessage = ex.getMessage();
    errors.put(fieldName, errorMessage);
    return errors;
  }
}
