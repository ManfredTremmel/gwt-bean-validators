package java.net;

import java.io.IOException;

public class SocketException extends IOException {
  private static final long serialVersionUID = 6950780022280181042L;

  public SocketException(final String message) {
    super(message);
  }

  public SocketException() {
    super();
  }
}
