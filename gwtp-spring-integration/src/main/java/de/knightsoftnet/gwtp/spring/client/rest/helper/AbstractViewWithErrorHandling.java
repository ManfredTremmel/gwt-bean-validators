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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.validation.ConstraintViolation;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import elemental.html.InputElement;

/**
 * abstract editor implementation with default functionality used in forms.
 *
 * @author Manfred Tremmel
 *
 * @param <P> presenter type
 * @param <F> editable data type
 */
public abstract class AbstractViewWithErrorHandling<P, F> extends ViewImpl
    implements EditorWithErrorHandling<P, F> {

  protected P presenter;

  protected final BeanValidationEditorDriver<F, //
      AbstractViewWithErrorHandling<P, F>> driver;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   */
  @SuppressWarnings("unchecked")
  public AbstractViewWithErrorHandling(final BeanValidationEditorDriver<F, //
      ? extends AbstractViewWithErrorHandling<P, F>> pdriver) {
    super();
    this.driver = (BeanValidationEditorDriver<F, AbstractViewWithErrorHandling<P, F>>) pdriver;
  }

  @Override
  public void setPresenter(final P ppresenter) {
    this.presenter = ppresenter;
  }

  public final P getPresenter() {
    return this.presenter;
  }

  @Override
  public void fillForm(final F pformData) {
    this.driver.edit(pformData);
  }

  @Override
  public abstract void showMessage(String pmessage);

  @Override
  public void setFocusOnFirstWidget() {
    // implementation sets focus on first input element with autofocus attribute
    final NodeList<Element> elements = Document.get().getElementsByTagName("input");
    for (int i = 0; i < elements.getLength(); i++) {
      final InputElement element = elements.getItem(i).cast();
      if (element.isAutofocus()) {
        element.focus();
        break;
      }
    }
  }

  @Override
  public void setConstraintViolations(final Iterable<ConstraintViolation<?>> pvalidationErrorSet) {
    this.driver.setConstraintViolations(pvalidationErrorSet);
  }
}
