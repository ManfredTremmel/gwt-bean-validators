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

import com.google.gwt.core.shared.GWT;

import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.valkyrie.gwt.bean.client.BeanManager;
import org.valkyrie.gwt.bean.client.ReflectedBean;
import org.valkyrie.gwt.bean.client.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * client side replacement of BeanUtils (poor man edition), it only implements getProperty.
 *
 * @author Manfred Tremmel
 */
@SuppressWarnings("deprecation")
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
   * @param bean Bean whose property is to be extracted
   * @param name Possibly indexed and/or nested name of the property to be extracted
   * @return The property's value, converted to a String
   *
   * @exception IllegalAccessException if the caller does not have access to the property accessor
   *            method
   * @exception InvocationTargetException if the property accessor method throws an exception
   * @exception NoSuchMethodException if an accessor method for this property cannot be found
   */
  public static String getProperty(final Object bean, final String name)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

    if (bean == null) {
      throw new NoSuchMethodException("A null object has no getters");
    }
    if (name == null) {
      throw new NoSuchMethodException("No method to get property for null");
    }
    if (bean instanceof ReflectedBean) {
      try {
        final BeanManager<ReflectedBean> manager = GWT.create(bean.getClass());
        manager.setBean((ReflectedBean) bean);
        final int posPoint = name.indexOf('.');
        if (posPoint >= 0) {
          final Object subObject = manager.get(name);
          if (subObject == null) {
            return null;
          } else {
            return getProperty(subObject, name.substring(posPoint + 1));
          }
        }
        return Objects.toString(manager.get(name), null);
      } catch (final ReflectionException e) {
        throw new InvocationTargetException(e);
      }
    } else if (bean instanceof HasGetFieldByName) {
      return Objects.toString(((HasGetFieldByName) bean).getFieldByName(name), null);
    } else {
      throw new IllegalAccessException(bean.getClass().toString() + " must implement ReflectedBean"
          + " or HasGetFieldByName to make access work on client side");
    }
  }
}
