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

package de.knightsoftnet.validators.shared.util;

import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.testcases.IbanUtilTestCases;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map.Entry;

public class IbanUtilTest {

  /**
   * test formating iban.
   */
  @Test
  public void testIbanFormat() {
    Assert.assertNull("iban format should be null", IbanUtil.ibanFormatWithPos(null));
    for (final Entry<String, String> entry : IbanUtilTestCases.getFormatCases().entrySet()) {
      Assert.assertEquals("iban format failed", entry.getKey(),
          IbanUtil.ibanFormat(entry.getValue()));
    }
  }

  /**
   * test formating iban with position.
   */
  @Test
  public void testIbanFormatWithPos() {
    Assert.assertNull("iban format should be null", IbanUtil.ibanFormatWithPos(null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> entry : IbanUtilTestCases
        .getFormatWithPosCases().entrySet()) {
      Assert.assertEquals("iban format failed", entry.getKey(),
          IbanUtil.ibanFormatWithPos(entry.getValue()));
    }
  }

  /**
   * test compressing iban.
   */
  @Test
  public void testIbanCompress() {
    Assert.assertNull("iban compression should be null", IbanUtil.ibanCompress(null));
    for (final Entry<String, String> entry : IbanUtilTestCases.getCompressCases().entrySet()) {
      Assert.assertEquals("iban compress failed", entry.getKey(),
          IbanUtil.ibanCompress(entry.getValue()));
    }
  }
}
