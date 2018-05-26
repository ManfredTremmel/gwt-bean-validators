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

package de.knightsoftnet.mtwidgets.client.ui.widget.helper;

import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationMessageElement;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.impl.ListValidationEditor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * editor to show a list of items entries.
 *
 * @author Manfred Tremmel
 *
 * @param <D> type of item data to edit
 * @param <V> type of view of the single items
 */
public abstract class AbstractListEditor<D, V extends AbstractListItemView<D>> extends FlowPanel
    implements IsEditor<ListValidationEditor<D, V>>, HasEditorErrors<List<D>>,
    HasValueChangeHandlers<List<D>>, HasValidationMessageElement {

  private HTMLPanel validationMessageElement;


  public void removeEntry(final int ppos) {
    this.asEditor().getList().remove(ppos);
    ValueChangeEvent.fire(this, this.asEditor().getList());
  }

  public void addNewEntry() {
    this.asEditor().getList().add(this.createData());
    ValueChangeEvent.fire(this, this.asEditor().getList());
  }

  /**
   * create new instance of a data element.
   *
   * @return data element
   */
  protected abstract D createData();

  /**
   * set parent editor driver.
   *
   * @param pparentDriver BeanValidationEditorDriver to set
   */
  public final void setParentDriver(final BeanValidationEditorDriver<?, ?> pparentDriver) {
    this.asEditor().setParentDriver(pparentDriver);
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final Set<String> messages = new HashSet<>();
    for (final EditorError error : perrors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
    if (messages.isEmpty()) {
      if (this.validationMessageElement != null) {
        this.validationMessageElement.getElement().setInnerText(StringUtils.EMPTY);
      }
    } else {
      if (this.validationMessageElement == null) {
        GWT.log(ErrorMessageFormater.messagesToString(messages));
      } else {
        this.validationMessageElement.getElement()
            .setInnerSafeHtml(ErrorMessageFormater.messagesToList(messages));
      }
    }
  }

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    this.validationMessageElement = pelement;
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return this.validationMessageElement;
  }

  /**
   * Checks if a error belongs to this widget.
   *
   * @param perror editor error to check
   * @return true if the error belongs to this widget
   */
  protected boolean editorErrorMatches(final EditorError perror) {
    return perror != null && perror.getEditor() != null
        && (this.equals(perror.getEditor()) || perror.getEditor().equals(this.asEditor()));
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<List<D>> phandler) {
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }
}
