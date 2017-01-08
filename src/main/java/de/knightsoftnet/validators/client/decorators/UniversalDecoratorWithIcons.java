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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * This is a Decorator which in changes style sheets and shows error messages when validation fails.
 *
 * <p>
 * <h3>Use in UiBinder Templates</h3>
 * </p>
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
 * UniversalDecoratorWithIcons&lt;String&gt; name;
 * </pre>
 *
 * <pre>
 * &lt;e:UniversalDecoratorWithIcons ui:field='name'&gt;
 * &lt;e:widget&gt;
 * &lt;g:TextBox /&gt;
 * &lt;/e:widget&gt;
 * &lt;/e:UniversalDecoratorWithIcons&gt;
 * </pre>
 *
 * @param <T> the type of data being edited
 */

public class UniversalDecoratorWithIcons<T> extends AbstractDecorator<T> {

  /**
   * A ClientBundle that provides images and style sheets for the decorator.
   */
  public interface ExtendedResources extends Resources {

    @DataResource.MimeType("image/svg+xml")
    @Source("valid.svg")
    DataResource resValidImage();

    @DataResource.MimeType("image/svg+xml")
    @Source("error.svg")
    DataResource resErrorImage();

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Override
    @Source("EditorDecoratorWithIcons.gss")
    DecoratorStyle decoratorStyle();
  }

  /**
   * the default resources.
   */
  private static ExtendedResources extendedResource;

  /**
   * Constructs a ValueBoxEditorDecorator.
   *
   * @param errorLocation location of the error text
   */
  @UiConstructor
  public UniversalDecoratorWithIcons(final ErrorPanelLocationEnum errorLocation) {
    super(errorLocation, getExtendedResources());
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static Resources getExtendedResources() {
    if (extendedResource == null) { // NOPMD needn't be thread save on client side
      extendedResource = GWT.create(ExtendedResources.class);
    }
    return extendedResource;
  }
}
