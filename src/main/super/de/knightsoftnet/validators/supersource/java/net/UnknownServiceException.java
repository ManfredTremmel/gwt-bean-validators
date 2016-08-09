package java.net;

import java.io.IOException;

public class UnknownServiceException extends IOException {

  public UnknownServiceException() {
    super();
  }

  public UnknownServiceException(final String message) {
    super(message);
  }
}
