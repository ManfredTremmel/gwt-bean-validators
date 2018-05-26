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

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import java.util.Set;

/**
 * helper class with formating methods for validation messages.
 *
 * @author Manfred Tremmel
 *
 */
public final class ErrorMessageFormater {

  private ErrorMessageFormater() {
    super();
  }

  /**
   * build a html list out of given message set.
   *
   * @param messages set of message strings
   * @return safe html with massages as list
   */
  public static SafeHtml messagesToList(final Set<String> messages) {
    final SafeHtmlBuilder sbList = new SafeHtmlBuilder();
    sbList.appendHtmlConstant("<ul>");
    for (final String message : messages) {
      sbList.appendHtmlConstant("<li>");
      sbList.appendEscaped(message);
      sbList.appendHtmlConstant("</li>");
    }
    sbList.appendHtmlConstant("</ul>");

    return sbList.toSafeHtml();
  }

  /**
   * build a linefeed separated String with all messages out of given set.
   *
   * @param messages set of message strings
   * @return String with messages
   */
  public static String messagesToString(final Set<String> messages) {
    final SafeHtmlBuilder sb = new SafeHtmlBuilder();
    for (final String message : messages) {
      sb.appendEscaped(message);
      sb.appendEscaped("\n");
    }
    return sb.toSafeHtml().asString();
  }
}
