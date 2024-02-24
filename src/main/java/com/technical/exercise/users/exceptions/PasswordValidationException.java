package com.technical.exercise.users.exceptions;

public class PasswordValidationException extends RuntimeException {
  public PasswordValidationException() {
    super("Invalid Password");
  }

}
