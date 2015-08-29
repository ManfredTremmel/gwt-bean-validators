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
public class CreateClass {

  private static final String PROPERTY_PACKAGE = "de.knightsoftnet.validators.client.data.";

  /**
   * Instantiates a class via deferred binding.
   *
   * <p>
   * The argument to {@link #create(Class)}&#160;<i>must</i> be a class literal because the
   * Production Mode compiler must be able to statically determine the requested type at
   * compile-time. This can be tricky because using a {@link Class} variable may appear to work
   * correctly in Development Mode.
   * </p>
   *
   * @param pclassLiteral a class literal specifying the base class to be instantiated
   * @return the new instance, which must be cast to the requested class
   */
  @SuppressWarnings("unchecked")
  public static <T> T create(final Class<?> pclassLiteral) {
    if (pclassLiteral.equals(de.knightsoftnet.validators.shared.data.BicMapConstants.class)) {
      return (T) new BicMapConstantsImpl(readMapFromProperties("BicMapConstants", "bics"));
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.IbanLengthMapConstants.class)) {
      return (T) new IbanLengthMapConstantsImpl(readMapFromProperties("IbanLengthMapConstants",
          "ibanLengths"));
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.PostalCodesMapConstants.class)) {
      return (T) new PostalCodesMapConstantsImpl(readMapFromProperties("PostalCodesMapConstants",
          "postalCodes"));
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.VatIdMapConstants.class)) {
      return (T) new VatIdMapConstantsImpl(readMapFromProperties("VatIdMapConstants", "vatIds"));
    }
    return null;
  }

  private static Map<String, String> readMapFromProperties(final String pmapName,
      final String pmapRoot) {
    final ResourceBundle bundle =
        ResourceBundle.getBundle(PROPERTY_PACKAGE + pmapName, Locale.ROOT, new Utf8Control());
    final Map<String, String> map = new HashMap<>();
    final String mapNames = StringUtils.defaultString(bundle.getString(pmapRoot));
    for (final String key : mapNames.split(",")) {
      map.put(key, bundle.getString(key));
    }
    return map;
  }
}
