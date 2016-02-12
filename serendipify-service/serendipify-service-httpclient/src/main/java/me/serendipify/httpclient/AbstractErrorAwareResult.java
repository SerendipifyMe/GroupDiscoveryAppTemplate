package me.serendipify.httpclient;

public class AbstractErrorAwareResult {

  public boolean isError(String result) {
    if (null != result) {
      return !"OK".equals(result.trim());
    } else {
      return true;
    }
  }
}
