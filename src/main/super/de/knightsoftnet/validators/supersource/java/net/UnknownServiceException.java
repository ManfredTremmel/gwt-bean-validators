package java.net;

import java.io.IOException;

public class UnknownServiceException extends IOException {

  private static final long serialVersionUID = -4169033248853639508L;

  public UnknownServiceException() {
    super();
  }

  public UnknownServiceException(final String message) {
    super(message);
  }
}
