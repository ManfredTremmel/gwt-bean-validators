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

package de.knightsoftnet.validators.client.decorators;

import com.google.gwt.uibinder.client.UiConstructor;

/**
 * This is a Decorator which in changes style sheets and shows error messages when validation fails.
 *
 * <h3>Use in UiBinder Templates</h3>
 * <p>
 * The decorator may have exactly one Widget added though an <code>&lt;e:widget&gt;</code> child
 * tag.
 * </p>
 * <p>
 * For example:
 * </p>
 *
 * <pre>
 * &#064;UiField
 * UniversalDecoratorWithLabel&lt;String&gt; name;
 * </pre>
 *
 * <pre>
 * &lt;e:UniversalDecoratorWithLabel ui:field='name'&gt;
 * &lt;e:widget&gt;
 * &lt;g:TextBox /&gt;
 * &lt;/e:widget&gt;
 * &lt;e:label&gt;
 * &lt;g:Label&gt;Text&lt;/g:Label&gt;
 * &lt;/e:label&gt;
 * &lt;/e:UniversalDecoratorWithLabel&gt;
 * </pre>
 *
 * @param <T> the type of data being edited
 */

public class UniversalDecoratorWithLabel<T> extends AbstractDecoratorWithLabel<T> {

  /**
   * Constructs a ValueBoxEditorDecorator.
   *
   * @param errorLocation location of the error text
   */
  @UiConstructor
  public UniversalDecoratorWithLabel(final PanelLocationEnum errorLocation) {
    super(errorLocation);
  }
}
