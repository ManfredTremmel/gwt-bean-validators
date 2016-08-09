package java.net;

import java.io.IOException;

public class UnknownHostException extends IOException {
  public UnknownHostException(final String message) {
    super(message);
  }

  public UnknownHostException() {
    super();
  }
}
