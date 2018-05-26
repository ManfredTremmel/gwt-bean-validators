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

import de.knightsoftnet.mtwidgets.client.ui.widget.styling.RatingDisplayStyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.apache.commons.lang3.StringUtils;

/**
 * widget to display ratings, if you need a input field, see RatingInputWidget.
 *
 * @author Manfred Tremmel
 *
 */
public class RatingDisplayWidget extends Composite implements TakesValue<Double> {

  private final HTMLPanel panel;
  private final Resources resource;
  private Double value;
  private final int max;

  /**
   * A ClientBundle that provides images and style sheets for the decorator.
   */
  public interface Resources extends ClientBundle {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Source("styling/RatingDisplayStyle.gss")
    RatingDisplayStyle ratingDisplayStyle();
  }

  /**
   * the default resources.
   */
  private static volatile Resources defaultResource;

  /**
   * default constructor.
   *
   * @param max the number stars to display
   */
  @UiConstructor
  public RatingDisplayWidget(final int max) {
    this(max, RatingDisplayWidget.getDefaultResources());
  }

  /**
   * constructor with styling information.
   *
   * @param max the number stars to display
   * @param presource resources with styling information
   */
  public RatingDisplayWidget(final int max, final Resources presource) {
    super();
    this.max = max;
    this.resource = presource;
    this.resource.ratingDisplayStyle().ensureInjected();
    this.panel = new HTMLPanel("span", StringUtils.EMPTY);
    this.initWidget(this.panel);
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static Resources getDefaultResources() { // NOPMD it's thread save!
    if (RatingDisplayWidget.defaultResource == null) {
      synchronized (Resources.class) {
        if (RatingDisplayWidget.defaultResource == null) {
          RatingDisplayWidget.defaultResource = GWT.create(Resources.class);
        }
      }
    }
    return RatingDisplayWidget.defaultResource;
  }

  @Override
  public void setValue(final Double pvalue) {
    this.value = pvalue;
    final double rating = pvalue == null ? 0.0 : pvalue.doubleValue();
    final SafeHtmlBuilder stars = new SafeHtmlBuilder();
    for (int i = 1; i <= this.max; i++) {
      this.appendStar(stars, i, rating);
    }
    this.panel.getElement().setInnerSafeHtml(stars.toSafeHtml());
  }

  private void appendStar(final SafeHtmlBuilder stringBuilder, final int star,
      final double rating) {
    final String starClass;
    if (rating >= star - 0.25) {
      starClass = StringUtils.SPACE + this.resource.ratingDisplayStyle().full();
    } else if (rating >= star - 0.75) {
      starClass = StringUtils.SPACE + this.resource.ratingDisplayStyle().half();
    } else {
      starClass = StringUtils.EMPTY;
    }
    stringBuilder.appendHtmlConstant("<span class=\""
        + this.resource.ratingDisplayStyle().staricon() + starClass + "\">☆</span>");
  }

  @Override
  public Double getValue() {
    return this.value;
  }
}
