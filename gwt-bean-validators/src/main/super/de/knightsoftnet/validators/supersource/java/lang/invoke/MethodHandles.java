package java.lang.invoke;

/**
 * method handlers dummy implementation for GWT.
 * 
 * @author Manfred Tremmel
 *
 */
public class MethodHandles {

  private MethodHandles() {} // do not instantiate

  public static Lookup lookup() {
    return new Lookup();
  }

  public static final class Lookup {
  }
}
