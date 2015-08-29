/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.server.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * The <code>UTF8Control</code> class is a Control to read UTF-8 properties files.
 *
 * @author Manfred Tremmel
 */
public class Utf8Control extends Control {

  @Override
  public final ResourceBundle newBundle(final String pbaseName, final Locale plocale,
      final String pformat, final ClassLoader ploader, final boolean preload)
      throws IllegalAccessException, InstantiationException, IOException {
    // The below is a copy of the default implementation.
    final String bundleName = this.toBundleName(pbaseName, plocale);
    final String resourceName = this.toResourceName(bundleName, "properties");
    ResourceBundle bundle = null;
    InputStream stream = null;
    if (preload) {
      final URL url = ploader.getResource(resourceName);
      if (url != null) {
        final URLConnection connection = url.openConnection();
        if (connection != null) {
          connection.setUseCaches(false);
          stream = connection.getInputStream();
        }
      }
    } else {
      stream = ploader.getResourceAsStream(resourceName);
    }
    if (stream != null) {
      try {
        // Only this line is changed to make it to read
        // properties files as UTF-8.
        bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
      } finally {
        stream.close();
      }
    }
    return bundle;
  }
}
