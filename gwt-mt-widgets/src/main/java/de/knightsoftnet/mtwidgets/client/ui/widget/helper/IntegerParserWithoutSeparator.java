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
import com.google.gwt.text.shared.Parser;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Objects;

/**
 * parse text to integer value.
 *
 * @author Manfred Tremmel
 */
public class IntegerParserWithoutSeparator implements Parser<Integer> {

  private static volatile IntegerParserWithoutSeparator instanceParser = null;

  /**
   * returns the instance.
   *
   * @return IntegerParserWithoutSeparator
   */
  public static final Parser<Integer> instance() { // NOPMD it's thread save!
    if (IntegerParserWithoutSeparator.instanceParser == null) {
      synchronized (IntegerParserWithoutSeparator.class) {
        if (IntegerParserWithoutSeparator.instanceParser == null) {
          IntegerParserWithoutSeparator.instanceParser = new IntegerParserWithoutSeparator();
        }
      }
    }
    return IntegerParserWithoutSeparator.instanceParser;
  }

  @Override
  public final Integer parse(final CharSequence pobject) throws ParseException {
    if (StringUtils.isEmpty(Objects.toString(pobject))) {
      return null;
    }

    try {
      return Integer.valueOf((int) NumberFormat.getFormat("###0").parse(pobject.toString()));
    } catch (final NumberFormatException e) {
      throw new ParseException(e.getMessage(), 0); // NOPMD, we needn't a stack trace
    }
  }
}
