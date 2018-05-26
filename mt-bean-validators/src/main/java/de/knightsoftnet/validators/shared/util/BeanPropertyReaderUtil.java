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

package de.knightsoftnet.validators.shared.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Bean Property Util, read bean property.
 *
 * @author Manfred Tremmel
 *
 */
public final class BeanPropertyReaderUtil {

  /**
   * private constructor for final utility class.
   */
  private BeanPropertyReaderUtil() {
    super();
  }

  /**
   * <p>
   * Return the value of the specified property of the specified bean, no matter which property
   * reference format is used, as a String.
   * </p>
   * <p>
   * If there is a null value in path hierarchy, exception is cached and null returned.
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
   * @see BeanUtilsBean#getProperty
   */
  public static String getNullSaveStringProperty(final Object pbean, final String pname)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    String property;
    try {
      property = BeanUtils.getProperty(pbean, pname);
    } catch (final NestedNullException pexception) {
      property = null;
    }
    return property;
  }

  /**
   * <p>
   * Return the value of the specified property of the specified bean, no matter which property
   * reference format is used, as a String.
   * </p>
   * <p>
   * If there is a null value in path hierarchy, exception is cached and null returned.
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
   * @see BeanUtilsBean#getProperty
   */
  public static Object getNullSaveProperty(final Object pbean, final String pname)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    Object property;
    try {
      property = PropertyUtils.getProperty(pbean, pname);
    } catch (final NestedNullException pexception) {
      property = null;
    }
    return property;
  }
}
