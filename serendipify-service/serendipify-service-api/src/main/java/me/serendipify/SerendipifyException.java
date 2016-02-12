package me.serendipify;

public class SerendipifyException extends Exception {

  public SerendipifyException(String detailMessage) {
    super(detailMessage);
  }

  public SerendipifyException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }
}
