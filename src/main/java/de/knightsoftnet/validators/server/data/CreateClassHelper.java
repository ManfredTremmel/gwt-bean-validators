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

import de.knightsoftnet.validators.shared.data.AbstractCreateClass;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Read gwt constants from properties file on server side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateClassHelper extends AbstractCreateClass {

  private static final String PROPERTY_PACKAGE = "de.knightsoftnet.validators.client.data.";

  protected static Map<String, String> readMapFromProperties(final String pmapName,
      final String pmapRoot) {
    return readMapFromProperties(pmapName, Locale.ROOT, pmapRoot);
  }

  protected static Map<String, String> readMapFromProperties(final String pmapName,
      final Locale plocale, final String pmapRoot) {
    final ResourceBundle bundle =
        ResourceBundle.getBundle(PROPERTY_PACKAGE + pmapName, plocale, new Utf8Control());
    final Map<String, String> map = new HashMap<>();
    final String mapNames = StringUtils.defaultString(bundle.getString(pmapRoot));
    if (StringUtils.isNotEmpty(mapNames)) {
      for (final String key : mapNames.split(",")) {
        map.put(key, bundle.getString(key));
      }
    }
    return map;
  }
}
