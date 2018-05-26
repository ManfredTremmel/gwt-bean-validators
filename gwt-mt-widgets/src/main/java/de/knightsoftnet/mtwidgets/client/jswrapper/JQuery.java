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

package de.knightsoftnet.mtwidgets.client.jswrapper;

import elemental.html.InputElement;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * JQuery wrapper.
 *
 * @author Manfred Tremmel
 *
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "checkstyle:methodname"})
@JsType(isNative = true)
public abstract class JQuery { // NOPMD
  @JsMethod(namespace = JsPackage.GLOBAL)
  public static native JQuery $(String selector); // NOPMD

  /**
   * Get the computed style properties for the first element in the set of matched elements.
   *
   * @param propertyName A CSS property.
   * @return css class as string
   */
  public native String css(String... propertyName);

  /**
   * Set one or more CSS properties for the set of matched elements.
   *
   * @param propertyName A CSS property.
   * @param value A value to set for the property.
   * @return self {@link JQuery}
   */
  public native JQuery css(String propertyName, String value);


  /**
   * Get the value of an attribute for the first element in the set of matched elements.
   *
   * @param attr The name of the attribute to get.
   * @return attribute as object
   */
  public native Object attr(String attr);

  /**
   * Set one or more attributes for the set of matched elements.
   *
   * @param attr The name of the attribute to set.
   * @param value A value to set for the attribute.
   * @return self {@link JQuery}
   */
  public native JQuery attr(String attr, Object value);

  /**
   * Remove an attribute from each element in the set of matched elements.
   *
   * @param attributeName An attribute to remove; as of version 1.7, it can be a space-separated
   *        list of attributes.
   * @return self {@link JQuery}
   */
  public native JQuery removeAttr(String attributeName);

  /**
   * Get the current value of the first element in the set of matched elements.
   *
   * @return value as object
   */
  public native Object val();

  /**
   * Set the value of each element in the set of matched elements.
   *
   * @param value A string of text corresponding to the value of each matched element to set as
   *        selected/checked.
   * @return self {@link JQuery}
   */
  public native JQuery val(String value);

  /**
   * update polyfill usage of the element.
   *
   * @return self {@link JQuery}
   */
  public native JQuery updatePolyfill();

  /**
   * get shadow element for polyfilled widget.
   *
   * @return InputElement
   */
  @SuppressWarnings("unusable-by-js")
  public native InputElement getShadowElement();
}
