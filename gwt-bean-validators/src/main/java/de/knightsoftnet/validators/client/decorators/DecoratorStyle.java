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

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ImportedWithPrefix;

/**
 * Styles used by this widget.
 */
@ImportedWithPrefix("gwt-EditorDecorator")
public interface DecoratorStyle extends CssResource {
  /**
   * DecoratorStyle of the content container, when label is placed left.
   *
   * @return decoratorStyle entry
   */
  String contentContainerStyleLeft();

  /**
   * DecoratorStyle of the content container, when label is placed right.
   *
   * @return decoratorStyle entry
   */
  String contentContainerStyleRight();

  /**
   * DecoratorStyle of the content container, when label is placed top.
   *
   * @return decoratorStyle entry
   */
  String contentContainerStyleTop();

  /**
   * DecoratorStyle of the content container, when label is placed bottom.
   *
   * @return decoratorStyle entry
   */
  String contentContainerStyleBottom();

  /**
   * DecoratorStyle of the error label when it's placed left.
   *
   * @return errorLabelStyle entry
   */
  String errorLabelStyleLeft();

  /**
   * DecoratorStyle of the error label when it's placed right.
   *
   * @return errorLabelStyle entry
   */
  String errorLabelStyleRight();

  /**
   * DecoratorStyle of the error label when it's placed on top.
   *
   * @return errorLabelStyle entry
   */
  String errorLabelStyleTop();

  /**
   * DecoratorStyle of the error label when it's placed on bottom.
   *
   * @return errorLabelStyle entry
   */
  String errorLabelStyleBottom();

  /**
   * DecoratorStyle applied to input field, if check is successful.
   *
   * @return validInputStyle entry
   */
  String validInputStyle();

  /**
   * DecoratorStyle applied to input field, if check is not successful.
   *
   * @return errorInputStyle entry
   */
  String errorInputStyle();
}
