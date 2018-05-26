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

package de.knightsoftnet.gwtp.spring.client.rest.helper;

import com.google.gwt.editor.client.Editor;
import com.gwtplatform.mvp.client.View;

import javax.validation.ConstraintViolation;

/**
 * editor interface with default functionality used in forms.
 *
 * @author Manfred Tremmel
 *
 * @param <P> presenter type
 * @param <F> editable data type
 */
public interface EditorWithErrorHandling<P, F> extends View, Editor<F>, HasShowMessage {
  /**
   * set a reference to the presenter/activity.
   *
   * @param ppresenter reference to set
   */
  void setPresenter(P ppresenter);

  /**
   * fill the form with data.
   *
   * @param pformData data to fill into the form
   */
  void fillForm(F pformData);

  /**
   * set focus on first widget.
   */
  void setFocusOnFirstWidget();

  /**
   * display validation errors.
   *
   * @param pvalidationErrorSet list of violations
   */
  void setConstraintViolations(Iterable<ConstraintViolation<?>> pvalidationErrorSet);
}
