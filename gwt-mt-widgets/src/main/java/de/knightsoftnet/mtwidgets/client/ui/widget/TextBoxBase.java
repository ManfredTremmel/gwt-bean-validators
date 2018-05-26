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

package de.knightsoftnet.mtwidgets.client.ui.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.text.shared.testing.PassthroughParser;
import com.google.gwt.text.shared.testing.PassthroughRenderer;

import org.apache.commons.lang3.StringUtils;

/**
 * Abstract base class for most text entry widgets.
 *
 * <p>
 * The names of the static members of {@link TextBoxBase}, as well as simple alignment names
 * (<code>left</code>, <code>center</code>, <code>right</code>, <code>justify</code>), can be used
 * as values for a <code>textAlignment</code> attribute.
 * </p>
 *
 * <p>
 * For example,
 * </p>
 *
 * <pre>
 * &lt;g:TextBox textAlignment='ALIGN_RIGHT'/&gt;
 * &lt;g:TextBox textAlignment='right'/&gt;
 * </pre>
 */
public class TextBoxBase extends ValueBoxBaseWithEditorErrors<String> {

  /**
   * Creates a text box that wraps the given browser element handle. This is only used by
   * subclasses.
   *
   * @param elem the browser element to wrap
   */
  protected TextBoxBase(final Element elem) {
    super(elem, PassthroughRenderer.instance(), PassthroughParser.instance());
  }

  /**
   * Overridden to return "" from an empty text box.
   */
  @Override
  public String getValue() {
    return StringUtils.defaultString(super.getValue());
  }
}
