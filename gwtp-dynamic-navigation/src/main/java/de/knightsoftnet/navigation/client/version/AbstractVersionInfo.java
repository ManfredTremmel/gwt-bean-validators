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

package de.knightsoftnet.navigation.client.version;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * The <code>AbstractVersionInfo</code> class provides a view version informations.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractVersionInfo implements VersionInfoInterface {
  /**
   * version date format.
   */
  private final DateTimeFormat versionDateFormat;

  /**
   * date format to display in dialog.
   */
  private final DateTimeFormat dateFormatDisplay;

  /**
   * default constructor.
   */
  public AbstractVersionInfo() {
    super();
    this.versionDateFormat = DateTimeFormat.getFormat("yyyyMMdd-HHmm");
    this.dateFormatDisplay = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
  }

  /**
   * parse and format a date string.
   *
   * @param pversionDate string with date in versionDateFormat
   * @return the same date formated as dateFormatDisplay
   */
  protected final String parseAndFormatDate(final String pversionDate) {
    Date date;
    if (StringUtils.isEmpty(pversionDate)) {
      date = new Date();
    } else {
      try {
        date = this.versionDateFormat.parse(pversionDate);
      } catch (final IllegalArgumentException e) {
        date = new Date();
      }
    }
    return this.dateFormatDisplay.format(date);
  }
}
