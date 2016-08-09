package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("checkstyle:abbreviationaswordinname")
public abstract class URLConnection { // NOPMD

  /**
   * The URL represents the remote object on the World Wide Web to which this connection is opened.
   * <p>
   * The value of this field can be accessed by the {@code getURL} method.
   * </p>
   * <p>
   * The default value of this variable is the value of the URL argument in the
   * {@code URLConnection} constructor.
   * </p>
   *
   * @see java.net.URLConnection#getURL()
   * @see java.net.URLConnection#url
   */
  protected URL url;

  /**
   * This variable is set by the {@code setDoInput} method. Its value is returned by the
   * {@code getDoInput} method.
   * <p>
   * A URL connection can be used for input and/or output. Setting the {@code doInput} flag to
   * {@code true} indicates that the application intends to read data from the URL connection.
   * </p>
   * <p>
   * The default value of this field is {@code true}.
   * </p>
   *
   * @see java.net.URLConnection#getDoInput()
   * @see java.net.URLConnection#setDoInput(boolean)
   */
  protected boolean doInput = true;

  /**
   * This variable is set by the {@code setDoOutput} method. Its value is returned by the
   * {@code getDoOutput} method.
   * <p>
   * A URL connection can be used for input and/or output. Setting the {@code doOutput} flag to
   * {@code true} indicates that the application intends to write data to the URL connection.
   * </p>
   * <p>
   * The default value of this field is {@code false}.
   * </p>
   *
   * @see java.net.URLConnection#getDoOutput()
   * @see java.net.URLConnection#setDoOutput(boolean)
   */
  protected boolean doOutput = false;

  private static boolean defaultAllowUserInteraction = false;

  /**
   * If {@code true}, this {@code URL} is being examined in a context in which it makes sense to
   * allow user interactions such as popping up an authentication dialog. If {@code false}, then no
   * user interaction is allowed.
   * <p>
   * The value of this field can be set by the {@code setAllowUserInteraction} method. Its value is
   * returned by the {@code getAllowUserInteraction} method. Its default value is the value of the
   * argument in the last invocation of the {@code setDefaultAllowUserInteraction} method.
   * </p>
   *
   * @see java.net.URLConnection#getAllowUserInteraction()
   * @see java.net.URLConnection#setAllowUserInteraction(boolean)
   * @see java.net.URLConnection#setDefaultAllowUserInteraction(boolean)
   */
  protected boolean allowUserInteraction = defaultAllowUserInteraction;

  private boolean defaultUseCaches = true;

  /**
   * If {@code true}, the protocol is allowed to use caching whenever it can. If {@code false}, the
   * protocol must always try to get a fresh copy of the object.
   * <p>
   * This field is set by the {@code setUseCaches} method. Its value is returned by the
   * {@code getUseCaches} method.
   * </p>
   * <p>
   * Its default value is the value given in the last invocation of the {@code setDefaultUseCaches}
   * method.
   * </p>
   *
   * @see java.net.URLConnection#setUseCaches(boolean)
   * @see java.net.URLConnection#getUseCaches()
   * @see java.net.URLConnection#setDefaultUseCaches(boolean)
   */
  protected boolean useCaches = this.defaultUseCaches;

  /**
   * Some protocols support skipping the fetching of the object unless the object has been modified
   * more recently than a certain time.
   * <p>
   * A nonzero value gives a time as the number of milliseconds since January 1, 1970, GMT. The
   * object is fetched only if it has been modified more recently than that time.
   * </p>
   * <p>
   * This variable is set by the {@code setIfModifiedSince} method. Its value is returned by the
   * {@code getIfModifiedSince} method.
   * </p>
   * <p>
   * The default value of this field is {@code 0}, indicating that the fetching must always occur.
   * </p>
   *
   * @see java.net.URLConnection#getIfModifiedSince()
   * @see java.net.URLConnection#setIfModifiedSince(long)
   */
  protected long ifModifiedSince = 0;

  /**
   * If {@code false}, this connection object has not created a communications link to the specified
   * URL. If {@code true}, the communications link has been established.
   */
  protected boolean connected = false;

  /**
   * @since 1.5
   */
  private int connectTimeout;
  private int readTimeout;

  /**
   * Opens a communications link to the resource referenced by this URL, if such a connection has
   * not already been established.
   * <p>
   * If the {@code connect} method is called when the connection has already been opened (indicated
   * by the {@code connected} field having the value {@code true}), the call is ignored.
   * </p>
   * <p>
   * URLConnection objects go through two phases: first they are created, then they are connected.
   * After being created, and before being connected, various options can be specified (e.g.,
   * doInput and UseCaches). After connecting, it is an error to try to set them. Operations that
   * depend on being connected, like getContentLength, will implicitly perform the connection, if
   * necessary.
   * </p>
   *
   * @throws SocketTimeoutException if the timeout expires before the connection can be established
   * @exception IOException if an I/O error occurs while opening the connection.
   * @see java.net.URLConnection#connected
   * @see #getConnectTimeout()
   * @see #setConnectTimeout(int)
   */
  public abstract void connect() throws IOException;

  /**
   * Sets a specified timeout value, in milliseconds, to be used when opening a communications link
   * to the resource referenced by this URLConnection. If the timeout expires before the connection
   * can be established, a java.net.SocketTimeoutException is raised. A timeout of zero is
   * interpreted as an infinite timeout.
   *
   * <p>
   * Some non-standard implementation of this method may ignore the specified timeout. To see the
   * connect timeout set, please call getConnectTimeout().
   * </p>
   *
   * @param timeout an {@code int} that specifies the connect timeout value in milliseconds
   * @throws IllegalArgumentException if the timeout parameter is negative
   *
   * @see #getConnectTimeout()
   * @see #connect()
   * @since 1.5
   */
  public void setConnectTimeout(final int timeout) {
    if (timeout < 0) {
      throw new IllegalArgumentException("timeout can not be negative");
    }
    this.connectTimeout = timeout;
  }

  /**
   * Returns setting for connect timeout.
   * <p>
   * 0 return implies that the option is disabled (i.e., timeout of infinity).
   * </p>
   *
   * @return an {@code int} that indicates the connect timeout value in milliseconds
   * @see #setConnectTimeout(int)
   * @see #connect()
   * @since 1.5
   */
  public int getConnectTimeout() {
    return this.connectTimeout;
  }

  /**
   * Sets the read timeout to a specified timeout, in milliseconds. A non-zero value specifies the
   * timeout when reading from Input stream when a connection is established to a resource. If the
   * timeout expires before there is data available for read, a java.net.SocketTimeoutException is
   * raised. A timeout of zero is interpreted as an infinite timeout.
   *
   * <p>
   * Some non-standard implementation of this method ignores the specified timeout. To see the read
   * timeout set, please call getReadTimeout().
   * </p>
   *
   * @param timeout an {@code int} that specifies the timeout value to be used in milliseconds
   * @throws IllegalArgumentException if the timeout parameter is negative
   *
   * @see #getReadTimeout()
   * @see InputStream#read()
   * @since 1.5
   */
  public void setReadTimeout(final int timeout) {
    if (timeout < 0) {
      throw new IllegalArgumentException("timeout can not be negative");
    }
    this.readTimeout = timeout;
  }

  /**
   * Returns setting for read timeout. 0 return implies that the option is disabled (i.e., timeout
   * of infinity).
   *
   * @return an {@code int} that indicates the read timeout value in milliseconds
   *
   * @see #setReadTimeout(int)
   * @see InputStream#read()
   * @since 1.5
   */
  public int getReadTimeout() {
    return this.readTimeout;
  }


  /**
   * Sets the general request property. If a property with the key already exists, overwrite its
   * value with the new value.
   *
   * <p>
   * NOTE: HTTP requires all request properties which can legally have multiple instances with the
   * same key to use a comma-separated list syntax which enables multiple properties to be appended
   * into a single property.
   * </p>
   *
   * @param key the keyword by which the request is known (e.g., "{@code Accept}").
   * @param value the value associated with it.
   * @throws IllegalStateException if already connected
   * @throws NullPointerException if key is <CODE>null</CODE>
   * @see #getRequestProperty(java.lang.String)
   */
  public void setRequestProperty(final String key, final String value) {
    throw new UnsupportedOperationException();
  }

  /**
   * Adds a general request property specified by a key-value pair. This method will not overwrite
   * existing values associated with the same key.
   *
   * @param key the keyword by which the request is known (e.g., "{@code Accept}").
   * @param value the value associated with it.
   * @throws IllegalStateException if already connected
   * @throws NullPointerException if key is null
   * @see #getRequestProperties()
   * @since 1.4
   */
  public void addRequestProperty(final String key, final String value) {
    throw new UnsupportedOperationException();
  }


  /**
   * Returns the value of the named general request property for this connection.
   *
   * @param key the keyword by which the request is known (e.g., "Accept").
   * @return the value of the named general request property for this connection. If key is null,
   *         then null is returned.
   * @throws IllegalStateException if already connected
   * @see #setRequestProperty(java.lang.String, java.lang.String)
   */
  public String getRequestProperty(final String key) {
    throw new UnsupportedOperationException();
  }

  /**
   * Constructs a URL connection to the specified URL. A connection to the object referenced by the
   * URL is not created.
   *
   * @param url the specified URL.
   */
  protected URLConnection(final URL url) {
    this.url = url;
  }

  /**
   * Returns the value of this {@code URLConnection}'s {@code URL} field.
   *
   * @return the value of this {@code URLConnection}'s {@code URL} field.
   * @see java.net.URLConnection#url
   */
  public URL getURL() {
    return this.url;
  }

  /**
   * Returns the value of the {@code content-length} header field.
   * <P>
   * <B>Note</B>: {@link #getContentLengthLong() getContentLengthLong()} should be preferred over
   * this method, since it returns a {@code long} instead and is therefore more portable.
   * </P>
   *
   * @return the content length of the resource that this connection's URL references, {@code -1} if
   *         the content length is not known, or if the content length is greater than
   *         Integer.MAX_VALUE.
   */
  public int getContentLength() {
    final long l = this.getContentLengthLong();
    if (l > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) l;
  }

  /**
   * Returns the value of the {@code content-length} header field as a long.
   *
   * @return the content length of the resource that this connection's URL references, or {@code -1}
   *         if the content length is not known.
   * @since 7.0
   */
  public long getContentLengthLong() {
    return this.getHeaderFieldLong("content-length", -1);
  }

  /**
   * Returns the value of the {@code content-type} header field.
   *
   * @return the content type of the resource that the URL references, or {@code null} if not known.
   * @see java.net.URLConnection#getHeaderField(java.lang.String)
   */
  public String getContentType() {
    return this.getHeaderField("content-type");
  }

  /**
   * Returns the value of the {@code content-encoding} header field.
   *
   * @return the content encoding of the resource that the URL references, or {@code null} if not
   *         known.
   * @see java.net.URLConnection#getHeaderField(java.lang.String)
   */
  public String getContentEncoding() {
    return this.getHeaderField("content-encoding");
  }

  /**
   * Returns the value of the {@code expires} header field.
   *
   * @return the expiration date of the resource that this URL references, or 0 if not known. The
   *         value is the number of milliseconds since January 1, 1970 GMT.
   * @see java.net.URLConnection#getHeaderField(java.lang.String)
   */
  public long getExpiration() {
    return this.getHeaderFieldDate("expires", 0);
  }

  /**
   * Returns the value of the {@code date} header field.
   *
   * @return the sending date of the resource that the URL references, or {@code 0} if not known.
   *         The value returned is the number of milliseconds since January 1, 1970 GMT.
   * @see java.net.URLConnection#getHeaderField(java.lang.String)
   */
  public long getDate() {
    return this.getHeaderFieldDate("date", 0);
  }

  /**
   * Returns the value of the {@code last-modified} header field. The result is the number of
   * milliseconds since January 1, 1970 GMT.
   *
   * @return the date the resource referenced by this {@code URLConnection} was last modified, or 0
   *         if not known.
   * @see java.net.URLConnection#getHeaderField(java.lang.String)
   */
  public long getLastModified() {
    return this.getHeaderFieldDate("last-modified", 0);
  }

  /**
   * Returns the value of the named header field.
   * <p>
   * If called on a connection that sets the same header multiple times with possibly different
   * values, only the last value is returned.
   * </p>
   *
   *
   * @param name the name of a header field.
   * @return the value of the named header field, or {@code null} if there is no such field in the
   *         header.
   */
  public String getHeaderField(final String name) {
    return null;
  }

  /**
   * Returns the value for the {@code n}<sup>th</sup> header field. It returns {@code null} if there
   * are fewer than {@code n+1}fields.
   * <p>
   * This method can be used in conjunction with the {@link #getHeaderFieldKey(int)
   * getHeaderFieldKey} method to iterate through all the headers in the message.
   * </p>
   *
   * @param number an index, where {@code n>=0}
   * @return the value of the {@code n}<sup>th</sup> header field or {@code null} if there are fewer
   *         than {@code n+1} fields
   * @see java.net.URLConnection#getHeaderFieldKey(int)
   */
  public String getHeaderField(final int number) {
    return null;
  }

  /**
   * Returns an unmodifiable Map of the header fields. The Map keys are Strings that represent the
   * response-header field names. Each Map value is an unmodifiable List of Strings that represents
   * the corresponding field values.
   *
   * @return a Map of header fields
   * @since 1.4
   */
  public Map<String, List<String>> getHeaderFields() {
    return Collections.emptyMap();
  }

  /**
   * Returns the value of the named field parsed as a number.
   * <p>
   * This form of {@code getHeaderField} exists because some connection types (e.g., {@code http-ng}
   * ) have pre-parsed headers. Classes for that connection type can override this method and
   * short-circuit the parsing.
   * </p>
   *
   * @param name the name of the header field.
   * @param pdefault the default value.
   * @return the value of the named field, parsed as an integer. The {@code Default} value is
   *         returned if the field is missing or malformed.
   */
  public int getHeaderFieldInt(final String name, final int pdefault) {
    final String value = this.getHeaderField(name);
    try {
      return Integer.parseInt(value);
    } catch (final Exception e) { // NOPMD
    }
    return pdefault;
  }

  /**
   * Returns the value of the named field parsed as a number.
   * <p>
   * This form of {@code getHeaderField} exists because some connection types (e.g., {@code http-ng}
   * ) have pre-parsed headers. Classes for that connection type can override this method and
   * short-circuit the parsing.
   * </p>
   *
   * @param name the name of the header field.
   * @param pdefault the default value.
   * @return the value of the named field, parsed as a long. The {@code Default} value is returned
   *         if the field is missing or malformed.
   * @since 7.0
   */
  public long getHeaderFieldLong(final String name, final long pdefault) {
    final String value = this.getHeaderField(name);
    try {
      return Long.parseLong(value);
    } catch (final Exception e) { // NOPMD
    }
    return pdefault;
  }

  /**
   * Returns the value of the named field parsed as date. The result is the number of milliseconds
   * since January 1, 1970 GMT represented by the named field.
   * <p>
   * This form of {@code getHeaderField} exists because some connection types (e.g., {@code http-ng}
   * ) have pre-parsed headers. Classes for that connection type can override this method and
   * short-circuit the parsing.
   * </p>
   *
   * @param name the name of the header field.
   * @param pdefault a default value.
   * @return the value of the field, parsed as a date. The value of the {@code Default} argument is
   *         returned if the field is missing or malformed.
   */
  @SuppressWarnings("deprecation")
  public long getHeaderFieldDate(final String name, final long pdefault) {
    final String value = this.getHeaderField(name);
    try {
      return Date.parse(value);
    } catch (final Exception e) { // NOPMD
    }
    return pdefault;
  }

  /**
   * Returns the key for the {@code n}<sup>th</sup> header field. It returns {@code null} if there
   * are fewer than {@code n+1} fields.
   *
   * @param number an index, where {@code n>=0}
   * @return the key for the {@code n}<sup>th</sup> header field, or {@code null} if there are fewer
   *         than {@code n+1} fields.
   */
  public String getHeaderFieldKey(final int number) {
    return null;
  }

  /**
   * Returns an input stream that reads from this open connection.
   *
   * <p>
   * A SocketTimeoutException can be thrown when reading from the returned input stream if the read
   * timeout expires before data is available for read.
   * </p>
   *
   * @return an input stream that reads from this open connection.
   * @exception IOException if an I/O error occurs while creating the input stream.
   * @exception UnknownServiceException if the protocol does not support input.
   * @see #setReadTimeout(int)
   * @see #getReadTimeout()
   */
  public InputStream getInputStream() throws IOException {
    throw new UnknownServiceException("protocol doesn't support input");
  }

  /**
   * Returns an output stream that writes to this connection.
   *
   * @return an output stream that writes to this connection.
   * @exception IOException if an I/O error occurs while creating the output stream.
   * @exception UnknownServiceException if the protocol does not support output.
   */
  public OutputStream getOutputStream() throws IOException {
    throw new UnknownServiceException("protocol doesn't support output");
  }

  /**
   * Returns a {@code String} representation of this URL connection.
   *
   * @return a string representation of this {@code URLConnection}.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + ":" + this.url;
  }

  /**
   * Sets the value of the {@code doInput} field for this {@code URLConnection} to the specified
   * value.
   *
   * <p>
   * A URL connection can be used for input and/or output. Set the DoInput flag to true if you
   * intend to use the URL connection for input, false if not. The default is true.
   * </p>
   *
   * @param doinput the new value.
   * @throws IllegalStateException if already connected
   * @see java.net.URLConnection#doInput
   * @see #getDoInput()
   */
  public void setDoInput(final boolean doinput) {
    if (this.connected) {
      throw new IllegalStateException("Already connected");
    }
    this.doInput = doinput;
  }

  /**
   * Returns the value of this {@code URLConnection}'s {@code doInput} flag.
   *
   * @return the value of this {@code URLConnection}'s {@code doInput} flag.
   * @see #setDoInput(boolean)
   */
  public boolean getDoInput() { // NOPMD
    return this.doInput;
  }

  /**
   * Sets the value of the {@code doOutput} field for this {@code URLConnection} to the specified
   * value.
   * <p>
   * A URL connection can be used for input and/or output. Set the DoOutput flag to true if you
   * intend to use the URL connection for output, false if not. The default is false.
   * </p>
   *
   * @param dooutput the new value.
   * @throws IllegalStateException if already connected
   * @see #getDoOutput()
   */
  public void setDoOutput(final boolean dooutput) {
    if (this.connected) {
      throw new IllegalStateException("Already connected");
    }
    this.doOutput = dooutput;
  }

  /**
   * Returns the value of this {@code URLConnection}'s {@code doOutput} flag.
   *
   * @return the value of this {@code URLConnection}'s {@code doOutput} flag.
   * @see #setDoOutput(boolean)
   */
  public boolean getDoOutput() { // NOPMD
    return this.doOutput;
  }

  /**
   * Set the value of the {@code allowUserInteraction} field of this {@code URLConnection}.
   *
   * @param allowuserinteraction the new value.
   * @throws IllegalStateException if already connected
   * @see #getAllowUserInteraction()
   */
  public void setAllowUserInteraction(final boolean allowuserinteraction) {
    if (this.connected) {
      throw new IllegalStateException("Already connected");
    }
    this.allowUserInteraction = allowuserinteraction;
  }

  /**
   * Returns the value of the {@code allowUserInteraction} field for this object.
   *
   * @return the value of the {@code allowUserInteraction} field for this object.
   * @see #setAllowUserInteraction(boolean)
   */
  public boolean getAllowUserInteraction() { // NOPMD
    return this.allowUserInteraction;
  }

  /**
   * Sets the default value of the {@code allowUserInteraction} field for all future
   * {@code URLConnection} objects to the specified value.
   *
   * @param defaultallowuserinteraction the new value.
   * @see #getDefaultAllowUserInteraction()
   */
  public static void setDefaultAllowUserInteraction(final boolean defaultallowuserinteraction) {
    defaultAllowUserInteraction = defaultallowuserinteraction;
  }

  /**
   * Returns the default value of the {@code allowUserInteraction} field.
   * <p>
   * Ths default is "sticky", being a part of the static state of all URLConnections. This flag
   * applies to the next, and all following URLConnections that are created.
   * </p>
   *
   * @return the default value of the {@code allowUserInteraction} field.
   * @see #setDefaultAllowUserInteraction(boolean)
   */
  public static boolean getDefaultAllowUserInteraction() { // NOPMD
    return defaultAllowUserInteraction;
  }

  /**
   * Sets the value of the {@code useCaches} field of this {@code URLConnection} to the specified
   * value.
   *
   * <p>
   * Some protocols do caching of documents. Occasionally, it is important to be able to
   * "tunnel through" and ignore the caches (e.g., the "reload" button in a browser). If the
   * UseCaches flag on a connection is true, the connection is allowed to use whatever caches it
   * can. If false, caches are to be ignored. The default value comes from DefaultUseCaches, which
   * defaults to true.
   * </p>
   *
   * @param usecaches a {@code boolean} indicating whether or not to allow caching
   * @throws IllegalStateException if already connected
   * @see #getUseCaches()
   */
  public void setUseCaches(final boolean usecaches) {
    if (this.connected) {
      throw new IllegalStateException("Already connected");
    }
    this.useCaches = usecaches;
  }

  /**
   * Returns the value of this {@code URLConnection}'s {@code useCaches} field.
   *
   * @return the value of this {@code URLConnection}'s {@code useCaches} field.
   * @see #setUseCaches(boolean)
   */
  public boolean getUseCaches() { // NOPMD
    return this.useCaches;
  }

  /**
   * Sets the value of the {@code ifModifiedSince} field of this {@code URLConnection} to the
   * specified value.
   *
   * @param ifmodifiedsince the new value.
   * @throws IllegalStateException if already connected
   * @see #getIfModifiedSince()
   */
  public void setIfModifiedSince(final long ifmodifiedsince) {
    if (this.connected) {
      throw new IllegalStateException("Already connected");
    }
    this.ifModifiedSince = ifmodifiedsince;
  }

  /**
   * Returns the value of this object's {@code ifModifiedSince} field.
   *
   * @return the value of this object's {@code ifModifiedSince} field.
   * @see #setIfModifiedSince(long)
   */
  public long getIfModifiedSince() {
    return this.ifModifiedSince;
  }

  /**
   * Returns the default value of a {@code URLConnection}'s {@code useCaches} flag.
   *
   * <p>
   * Ths default is "sticky", being a part of the static state of all URLConnections. This flag
   * applies to the next, and all following URLConnections that are created.
   * </p>
   *
   * @return the default value of a {@code URLConnection}'s {@code useCaches} flag.
   * @see #setDefaultUseCaches(boolean)
   */
  public boolean getDefaultUseCaches() { // NOPMD
    return this.defaultUseCaches;
  }

  /**
   * Sets the default value of the {@code useCaches} field to the specified value.
   *
   * @param defaultusecaches the new value.
   * @see #getDefaultUseCaches()
   */
  public void setDefaultUseCaches(final boolean defaultusecaches) {
    this.defaultUseCaches = defaultusecaches;
  }

}

