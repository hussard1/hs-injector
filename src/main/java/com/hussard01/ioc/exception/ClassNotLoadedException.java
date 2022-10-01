package com.hussard01.ioc.exception;

public class ClassNotLoadedException extends RuntimeException {

  public ClassNotLoadedException(final String message) {
    super(message);
  }

  public ClassNotLoadedException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
