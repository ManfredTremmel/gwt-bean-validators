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

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

import org.apache.commons.lang3.StringUtils;

/**
 * render long value to text without separators.
 *
 * @author Manfred Tremmel
 */
public class LongRendererWithoutSeparator extends AbstractRenderer<Long> {

  private static volatile LongRendererWithoutSeparator instanceRenderer = null;

  /**
   * returns the instance.
   *
   * @return CurrencyDoubleRenderer
   */
  public static final Renderer<Long> instance() { // NOPMD it's thread save!
    if (LongRendererWithoutSeparator.instanceRenderer == null) {
      synchronized (LongRendererWithoutSeparator.class) {
        if (LongRendererWithoutSeparator.instanceRenderer == null) {
          LongRendererWithoutSeparator.instanceRenderer = new LongRendererWithoutSeparator();
        }
      }
    }
    return LongRendererWithoutSeparator.instanceRenderer;
  }

  @Override
  public String render(final Long pobject) {
    if (pobject == null) {
      return StringUtils.EMPTY;
    }
    return NumberFormat.getFormat("###0").format(pobject);
  }
}
