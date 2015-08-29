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

import de.knightsoftnet.validators.client.data.BicMapConstants;
import de.knightsoftnet.validators.client.data.IbanLengthMapConstants;
import de.knightsoftnet.validators.client.data.PostalCodesMapConstants;
import de.knightsoftnet.validators.client.data.VatIdMapConstants;
import de.knightsoftnet.validators.shared.data.BicMapSharedConstants;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;
import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;
import de.knightsoftnet.validators.shared.data.VatIdMapSharedConstants;

import com.google.gwt.core.client.GWT;

/**
 * Read gwt constants from properties file on client side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateClass {

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
  public static <T> T create(final Class<?> pclassLiteral) {
    if (pclassLiteral.equals(BicMapSharedConstants.class)) {
      return GWT.create(BicMapConstants.class);
    } else if (pclassLiteral.equals(IbanLengthMapSharedConstants.class)) {
      return GWT.create(IbanLengthMapConstants.class);
    } else if (pclassLiteral.equals(PostalCodesMapSharedConstants.class)) {
      return GWT.create(PostalCodesMapConstants.class);
    } else if (pclassLiteral.equals(VatIdMapSharedConstants.class)) {
      return GWT.create(VatIdMapConstants.class);
    }
    return null;
  }
}
