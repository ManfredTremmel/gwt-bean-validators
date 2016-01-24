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

package de.knightsoftnet.validators.client.util;

import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.testcases.IsbnUtilTestCases;
import de.knightsoftnet.validators.shared.util.IsbnUtil;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.Map.Entry;

public class GwtTstIsbnUtil extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "de.knightsoftnet.validators.GwtBeanValidatorsJUnit";
  }

  /**
   * test formating isbn10 with position.
   */
  public void testIsbn10FormatWithPos() {
    assertNull("isbn format should be null", IsbnUtil.isbn10FormatWithPos(null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> entry : IsbnUtilTestCases
        .getIsbn10FormatWithPosCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(),
          IsbnUtil.isbn10FormatWithPos(entry.getValue()));
    }
  }

  /**
   * test formating isbn10.
   */
  public void testIsbn10Format() {
    assertNull("isbn format should be null", IsbnUtil.isbn10Format(null));
    for (final Entry<String, String> entry : IsbnUtilTestCases.getIsbn10FormatCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(), IsbnUtil.isbn10Format(entry.getValue()));
    }
  }

  /**
   * test formating isbn13 with position.
   */
  public void testIsbn13FormatWithPos() {
    assertNull("isbn format should be null", IsbnUtil.isbn13FormatWithPos(null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> entry : IsbnUtilTestCases
        .getIsbn13FormatWithPosCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(),
          IsbnUtil.isbn13FormatWithPos(entry.getValue()));
    }
  }

  /**
   * test formating isbn13.
   */
  public void testIsbn13Format() {
    assertNull("isbn format should be null", IsbnUtil.isbn13Format(null));
    for (final Entry<String, String> entry : IsbnUtilTestCases.getIsbn13FormatCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(), IsbnUtil.isbn13Format(entry.getValue()));
    }
  }

  /**
   * test formating isbn with position.
   */
  public void testIsbnFormatWithPos() {
    assertNull("isbn format should be null", IsbnUtil.isbnFormatWithPos(null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> entry : IsbnUtilTestCases
        .getIsbnFormatWithPosCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(),
          IsbnUtil.isbnFormatWithPos(entry.getValue()));
    }
  }

  /**
   * test formating isbn.
   */
  public void testIsbnFormat() {
    assertNull("isbn format should be null", IsbnUtil.isbnFormat(null));
    for (final Entry<String, String> entry : IsbnUtilTestCases.getIsbnFormatCases().entrySet()) {
      assertEquals("isbn format failed", entry.getKey(), IsbnUtil.isbnFormat(entry.getValue()));
    }
  }

  /**
   * test compressing isbn.
   */
  public void testIbanCompress() {
    assertNull("isbn compress should be null", IsbnUtil.isbnCompress(null));
    for (final Entry<String, String> entry : IsbnUtilTestCases.getCompressCases().entrySet()) {
      assertEquals("isbn compress failed", entry.getKey(), IsbnUtil.isbnCompress(entry.getValue()));
    }
  }
}
