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

import de.knightsoftnet.validators.shared.testcases.IbanUtilTestCases;
import de.knightsoftnet.validators.shared.util.IbanUtil;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.Map.Entry;

public class GwtTstIbanUtil extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "de.knightsoftnet.validators.GwtBeanValidatorsJUnit";
  }

  /**
   * test formating iban.
   */
  public void testIbanFormat() {
    assertNull("iban format should be null", IbanUtil.ibanFormat(null));
    for (final Entry<String, String> entry : IbanUtilTestCases.getFormatCases().entrySet()) {
      assertEquals("iban format failed", entry.getKey(), IbanUtil.ibanFormat(entry.getValue()));
    }
  }

  /**
   * test compressing iban.
   */
  public void testIbanCompress() {
    assertNull("iban compression should be null", IbanUtil.ibanCompress(null));
    for (final Entry<String, String> entry : IbanUtilTestCases.getCompressCases().entrySet()) {
      assertEquals("iban compress failed", entry.getKey(), IbanUtil.ibanCompress(entry.getValue()));
    }
  }
}
