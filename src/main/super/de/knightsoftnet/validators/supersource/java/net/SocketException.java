package java.net;

import java.io.IOException;

public class SocketException extends IOException {
  public SocketException(final String message) {
    super(message);
  }

  public SocketException() {
    super();
  }
}
