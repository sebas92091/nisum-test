package com.technical.exercise.users.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
  public EmailAlreadyRegisteredException() {
    super("Email Already Registered");
  }

}
