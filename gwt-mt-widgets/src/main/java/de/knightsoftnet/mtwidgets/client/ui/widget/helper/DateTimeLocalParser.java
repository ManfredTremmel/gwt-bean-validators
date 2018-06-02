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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.shared.Parser;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * parse text to date value.
 *
 * @author Manfred Tremmel
 */
public class DateTimeLocalParser implements Parser<Date> {

  private static volatile DateTimeLocalParser instanceParser = null;
  private final DateTimeFormat dateTimeFormat;

  /**
   * returns the instance.
   *
   * @return Parser
   */
  public static final Parser<Date> instance() { // NOPMD it's thread save!
    if (DateTimeLocalParser.instanceParser == null) {
      synchronized (DateTimeLocalParser.class) {
        if (DateTimeLocalParser.instanceParser == null) {
          DateTimeLocalParser.instanceParser = new DateTimeLocalParser("yyyy-MM-dd'T'HH:mm:ss");
        }
      }
    }
    return DateTimeLocalParser.instanceParser;
  }

  public DateTimeLocalParser(final String pformat) {
    super();
    dateTimeFormat = DateTimeFormat.getFormat(pformat);
  }

  @Override
  public final Date parse(final CharSequence pobject) throws ParseException {
    if (StringUtils.isEmpty(Objects.toString(pobject))) {
      return null;
    }

    try {
      if (StringUtils.countMatches(pobject, ':') == 2) {
        return dateTimeFormat.parse(Objects.toString(pobject));
      }
      return dateTimeFormat.parse(Objects.toString(pobject) + ":00"); // NOPMD
    } catch (final IllegalArgumentException e) {
      throw new ParseException(e.getMessage(), 0); // NOPMD, we needn't a stack trace
    }
  }
}
