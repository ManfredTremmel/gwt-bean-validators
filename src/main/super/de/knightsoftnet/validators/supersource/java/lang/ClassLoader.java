package java.lang;

import com.google.gwt.core.client.JavaScriptObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * This stripped down ClassLoader class is simply here to give us cross-platform support for code
 * that might need a valid classloader.
 *
 * <p>
 * xapi-gwt-reflect does call into the one and only system classloader, to define mappings of
 * java-names to runtime classes, in order to enable Class.forName() and ClassLoader.loadClass();
 * </p>
 *
 * @author "James X. Nelson (james@wetheinter.net)"
 *
 */
public class ClassLoader {

  // The parent class loader for delegation
  private final ClassLoader parent;

  // A JSO with all known classes;
  // We access this value via reflection in ConstPool#extractClasses
  private final JavaScriptObject classes = JavaScriptObject.createObject(); // NOPMD

  /**
   * Creates a new class loader using the specified parent class loader for delegation.
   *
   * @param parent The parent class loader
   */
  protected ClassLoader(final ClassLoader parent) {
    this.parent = parent;
  }

  /**
   * Creates a new class loader using the <tt>ClassLoader</tt> returned by the method
   * {@link #getSystemClassLoader() <tt>getSystemClassLoader()</tt>} as the parent class loader.
   */
  protected ClassLoader() {
    this(getSystemClassLoader());
  }

  // -- Class --

  /**
   * Loads the class with the specified <a href="#name">binary name</a>. This method searches for
   * classes in the same manner as the {@link #loadClass(String, boolean)} method. It is invoked by
   * the Java virtual machine to resolve class references. Invoking this method is equivalent to
   * invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
   * false)</tt>}.
   * </p>
   *
   * @param name The <a href="#name">binary name</a> of the class
   *
   * @return The resulting <tt>Class</tt> object
   *
   * @throws ClassNotFoundException If the class was not found
   */
  public Class<?> loadClass(final String name) throws ClassNotFoundException {
    return this.loadClass(name, false);
  }

  /**
   * Loads the class with the specified <a href="#name">binary name</a>. The default implementation
   * of this method searches for classes in the following order:
   *
   * <ol>
   * <li>
   * <p>
   * Invoke {@link #findLoadedClass(String)} to check if the class has already been loaded.
   * </p>
   * </li>
   *
   * <li>
   * <p>
   * Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method on the parent class loader. If
   * the parent is <tt>null</tt> the class loader built-in to the virtual machine is used, instead.
   * </p>
   * </li>
   *
   * <li>
   * <p>
   * Invoke the {@link #findClass(String)} method to find the class.
   * </p>
   * </li>
   *
   * </ol>
   *
   * <p>
   * If the class was found using the above steps, and the <tt>resolve</tt> flag is true, this
   * method will then invoke the {@link #resolveClass(Class)} method on the resulting <tt>Class</tt>
   * object.
   * </p>
   *
   * <p>
   * Subclasses of <tt>ClassLoader</tt> are encouraged to override {@link #findClass(String)},
   * rather than this method.
   * </p>
   *
   * @param name The <a href="#name">binary name</a> of the class
   *
   * @param resolve If <tt>true</tt> then resolve the class
   *
   * @return The resulting <tt>Class</tt> object
   *
   * @throws ClassNotFoundException If the class could not be found
   */
  protected Class<?> loadClass(final String name, final boolean resolve)
      throws ClassNotFoundException {
    // First, check if the class has already been loaded
    Class<?> clazz = null; // TODO: reimplement this goodness findLoadedClass(name);
    if (clazz == null) {
      try {
        if (this.parent == null) {
          // clazz = findBootstrapClassOrNull(name);
        } else {
          clazz = this.parent.loadClass(name, false);
        }
      } catch (final ClassNotFoundException e) { // NOPMD
        // ClassNotFoundException thrown if class not found
        // from the non-null parent class loader
      }
      if (clazz == null) {
        // If still not found, then invoke findClass in order
        // to find the class.
        clazz = this.findClass(name);
      }
    }
    // if (resolve) {
    // resolveClass(c);
    // }
    return clazz;
  }

  // This method is invoked by the virtual machine to load a class.
  private Class<?> loadClassInternal(final String name) throws ClassNotFoundException {
    return this.loadClass(name);
  }

  /**
   * Finds the class with the specified <a href="#name">binary name</a>. This method should be
   * overridden by class loader implementations that follow the delegation model for loading
   * classes, and will be invoked by the {@link #loadClass <tt>loadClass</tt>} method after checking
   * the parent class loader for the requested class. The default implementation throws a
   * <tt>ClassNotFoundException</tt>.
   * </p>
   *
   * @param name The <a href="#name">binary name</a> of the class
   *
   * @return The resulting <tt>Class</tt> object
   *
   * @throws ClassNotFoundException If the class could not be found
   *
   * @since 1.2
   */
  protected Class<?> findClass(final String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
  }


  /**
   * Our ClassLoader doesn't actually load anything; it just holds a jso mapping from class name to
   * class object.
   */
  public final native Class<?> defineClass(String name, Class<?> cls)
  /*-{
    this.@java.lang.ClassLoader::classes[name] = cls;
    return cls;
  }-*/;

  /**
   * Finds a class with the specified <a href="#name">binary name</a>, loading it if necessary.
   *
   * <p>
   * This method loads the class through the system class loader (see
   * {@link #getSystemClassLoader()}). The <tt>Class</tt> object returned might have more than one
   * <tt>ClassLoader</tt> associated with it. Subclasses of <tt>ClassLoader</tt> need not usually
   * invoke this method, because most class loaders need to override just
   * {@link #findClass(String)}.
   * </p>
   *
   * @param name The <a href="#name">binary name</a> of the class
   *
   * @return The <tt>Class</tt> object for the specified <tt>name</tt>
   *
   * @throws ClassNotFoundException If the class could not be found
   *
   * @see #ClassLoader(ClassLoader)
   * @see #getParent()
   */
  protected final Class<?> findSystemClass(final String name) throws ClassNotFoundException {
    return getSystemClassLoader().loadClass(name);
  }

  /**
   * No-op for compatibility.
   */
  protected final void setSigners(final Class<?> pclass, final Object[] signers) {
    // do nothing
  }

  // -- Resource --
  public URL getResource(final String name) {
    // TODO return a magic url backed by an IO request
    return null;
  }


  /**
   * Unsupported.
   */
  public InputStream getResourceAsStream(final String name) {
    throw new UnsupportedOperationException();
    // URL url = getResource(name);
    // try {
    // return url != null ? url.openStream() : null;
    // } catch (IOException e) {
    // return null;
    // }
  }

  /**
   * Unsupported.
   */
  public static InputStream getSystemResourceAsStream(final String name) {
    throw new UnsupportedOperationException();
    // URL url = getSystemResource(name);
    // try {
    // return url != null ? url.openStream() : null;
    // } catch (IOException e) {
    // return null;
    // }
  }

  /**
   * Returns the parent class loader for delegation. Some implementations may use <tt>null</tt> to
   * represent the bootstrap class loader. This method will return <tt>null</tt> in such
   * implementations if this class loader's parent is the bootstrap class loader.
   *
   */
  public final ClassLoader getParent() {
    return this.parent;
  }

  private static ClassLoader cl;

  /**
   * get system class loader.
   *
   * @return ClassLoader
   */
  public static ClassLoader getSystemClassLoader() {
    if (cl == null) { // NOPMD
      cl = new ClassLoader(null);
    }
    return cl;
  }


  // Returns true if the specified class loader can be found in this class
  // loader's delegation chain.
  @SuppressWarnings("checkstyle:rightCurly")
  boolean isAncestor(final ClassLoader cl) {
    ClassLoader acl = this;
    do {
      acl = acl.parent;
      if (Objects.equals(cl, acl)) {
        return true;
      }
    } while (acl != null);
    return false;
  }

  // Returns the invoker's class loader, or null if none.
  // NOTE: This must always be invoked when there is exactly one intervening
  // frame from the core libraries on the stack between this method's
  // invocation and the desired invoker.
  static ClassLoader getCallerClassLoader() {
    return getSystemClassLoader();
  }

  // Invoked in the java.lang.Runtime class to implement load and loadLibrary.
  static void loadLibrary(final Class<?> fromClass, final String name, final boolean isAbsolute) {}
}
