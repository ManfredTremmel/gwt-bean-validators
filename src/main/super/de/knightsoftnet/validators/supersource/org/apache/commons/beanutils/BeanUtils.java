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

package org.apache.commons.beanutils;

import de.knightsoftnet.validators.client.GwtReflectGetterFactoryInterface;
import de.knightsoftnet.validators.client.GwtReflectGetterInterface;

import com.google.gwt.core.shared.GWT;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * client side replacement of BeanUtils (poor man edition), it only implements getProperty.
 *
 * @author Manfred Tremmel
 */
public class BeanUtils {

  /**
   * <p>
   * Return the value of the specified property of the specified bean, no matter which property
   * reference format is used, as a String.
   * </p>
   *
   * <p>
   * For more details see <code>BeanUtilsBean</code>.
   * </p>
   *
   * @param pbean Bean whose property is to be extracted
   * @param pname Possibly indexed and/or nested name of the property to be extracted
   * @return The property's value, converted to a String
   *
   * @exception IllegalAccessException if the caller does not have access to the property accessor
   *            method
   * @exception InvocationTargetException if the property accessor method throws an exception
   * @exception NoSuchMethodException if an accessor method for this property cannot be found
   */
  public static String getProperty(final Object pbean, final String pname)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

    if (pbean == null) {
      throw new NoSuchMethodException("A null object has no getters");
    }
    if (pname == null) {
      throw new NoSuchMethodException("No method to get property for null");
    }

    final GwtReflectGetterFactoryInterface reflectGetterFactory =
        GWT.create(GwtReflectGetterFactoryInterface.class);
    final GwtReflectGetterInterface reflectGetter = reflectGetterFactory.getGwtReflectGetter();

    final int posPoint = pname.indexOf('.');
    try {
      if (posPoint >= 0) {
        final Object subObject = reflectGetter.getProperty(pbean, pname.substring(0, posPoint));
        if (subObject == null) {
          throw new NestedNullException(
              "Null property value for '" + pname + "' on bean class '" + pbean.getClass() + "'");
        }
        return getProperty(subObject, pname.substring(posPoint + 1));
      }
      return Objects.toString(reflectGetter.getProperty(pbean, pname), null);
    } catch (final ReflectiveOperationException e) {
      throw new InvocationTargetException(e);
    }
  }
}
