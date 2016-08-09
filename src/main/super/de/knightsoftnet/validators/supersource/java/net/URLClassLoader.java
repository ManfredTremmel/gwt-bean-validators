package java.net;

/**
 * This stripped down URLClassLoader class is simply here to give us cross-platform support for code
 * that might need a valid classloader.
 *
 * <p>
 * If support is ever needed, we can implement a generator which will set the URLs of any
 * URLClassLoader to the jars and source paths used to compile GWT.
 * </p>
 *
 * @author "James X. Nelson (james@wetheinter.net)"
 *
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class URLClassLoader extends ClassLoader {

  private URL[] urls;

  public URLClassLoader(final URL[] urls, final ClassLoader parent) {
    super(parent);
    this.urls = urls;
  }

  public URLClassLoader(final URL[] urls) {
    this(urls, ClassLoader.getSystemClassLoader());
  }

  // Included here so attempts at reflection succeed
  protected void addURL(final URL url) {
    final URL[] newUrls = new URL[this.urls.length + 1];
    System.arraycopy(url, 0, newUrls, 0, this.urls.length);
    newUrls[this.urls.length] = url;
    this.urls = newUrls;
  }

  public URL[] getURLs() {
    return this.urls;
  }

  public static URLClassLoader newInstance(final URL[] urls, final ClassLoader parent) {
    return new URLClassLoader(urls, parent);
  }

  public static URLClassLoader newInstance(final URL[] urls) {
    return new URLClassLoader(urls);
  }

}
