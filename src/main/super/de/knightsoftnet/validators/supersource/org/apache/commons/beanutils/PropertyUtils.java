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

import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;

import com.google.gwt.core.shared.GwtIncompatible;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import javax.validation.Validation;

/**
 * client side replacement of PropertyUtils (poor man edition), it only implements getProperty.
 *
 * @author Manfred Tremmel
 */
public class PropertyUtils {

  /**
   * <p>
   * Return the value of the specified property of the specified bean, no matter which property
   * reference format is used, with no type conversions.
   * </p>
   *
   * <p>
   * For more details see <code>PropertyUtilsBean</code>.
   * </p>
   *
   * @param pbean Bean whose property is to be extracted
   * @param pname Possibly indexed and/or nested name of the property to be extracted
   * @return the property value
   *
   * @exception IllegalAccessException if the caller does not have access to the property accessor
   *            method
   * @exception IllegalArgumentException if <code>bean</code> or <code>name</code> is null
   * @exception InvocationTargetException if the property accessor method throws an exception
   * @exception NoSuchMethodException if an accessor method for this propety cannot be found
   * @see PropertyUtilsBean#getProperty
   */
  public static Object getProperty(final Object pbean, final String pname)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

    if (pbean == null) {
      throw new NoSuchMethodException("A null object has no getters");
    }
    if (pname == null) {
      throw new NoSuchMethodException("No method to get property for null");
    }

    final int posPoint = pname.indexOf('.');
    try {
      final AbstractGwtValidator validator =
          (AbstractGwtValidator) Validation.buildDefaultValidatorFactory().getValidator();
      if (posPoint >= 0) {
        final Object subObject = validator.getProperty(pbean, pname.substring(0, posPoint));
        if (subObject == null) {
          throw new NestedNullException(
              "Null property value for '" + pname + "' on bean class '" + pbean.getClass() + "'");
        }
        return getProperty(subObject, pname.substring(posPoint + 1));
      }
      return validator.getProperty(pbean, pname);
    } catch (final ReflectiveOperationException e) {
      throw new InvocationTargetException(e);
    }
  }

  /**
   * <p>
   * Retrieve the property descriptors for the specified class, introspecting and caching them the
   * first time a particular bean class is encountered.
   * </p>
   *
   * <p>
   * For more details see <code>PropertyUtilsBean</code>.
   * </p>
   *
   * @param beanClass Bean class for which property descriptors are requested
   * @return the property descriptors
   * @exception IllegalArgumentException if <code>beanClass</code> is null
   * @see PropertyUtilsBean#getPropertyDescriptors(Class)
   */
  @GwtIncompatible("incompatible method")
  public static PropertyDescriptor[] getPropertyDescriptors(final Class<?> beanClass) {
    return PropertyUtilsBean.getInstance().getPropertyDescriptors(beanClass);
  }
}
