package java.net;

import java.io.IOException;

public class UnknownHostException extends IOException {
  private static final long serialVersionUID = -8591190820764269618L;

  public UnknownHostException(final String message) {
    super(message);
  }

  public UnknownHostException() {
    super();
  }
}
