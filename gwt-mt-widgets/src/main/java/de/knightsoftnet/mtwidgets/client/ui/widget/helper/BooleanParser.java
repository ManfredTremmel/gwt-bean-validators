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

import com.google.gwt.text.shared.Parser;

import org.apache.commons.lang3.BooleanUtils;

import java.text.ParseException;
import java.util.Objects;

/**
 * parse text to date boolean.
 *
 * @author Manfred Tremmel
 */
public class BooleanParser implements Parser<Boolean> {

  private static volatile BooleanParser instanceParser = null;

  /**
   * returns the instance.
   *
   * @return Parser
   */
  public static final Parser<Boolean> instance() { // NOPMD it's thread save!
    if (BooleanParser.instanceParser == null) {
      synchronized (BooleanParser.class) {
        if (BooleanParser.instanceParser == null) {
          BooleanParser.instanceParser = new BooleanParser();
        }
      }
    }
    return BooleanParser.instanceParser;
  }

  @Override
  public final Boolean parse(final CharSequence pobject) throws ParseException {
    return Boolean.valueOf(BooleanUtils.toBoolean(Objects.toString(pobject)));
  }
}
