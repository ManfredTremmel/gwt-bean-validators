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

import org.junit.Assert;
import org.junit.Test;

public class IbanUtilTest {

  /**
   * test formating iban.
   */
  @Test
  public void ibanFormat() {
    Assert.assertNull(IbanUtil.ibanFormat(null));
    Assert.assertEquals("DE16 7016 0000 0000 5554 44",
        IbanUtil.ibanFormat("DE16701600000000555444"));
    Assert.assertEquals("DE49 4306 0967 0000 0334 01",
        IbanUtil.ibanFormat("DE49430609670000033401"));
    Assert.assertEquals("AT24 2011 1822 2121 9800", IbanUtil.ibanFormat("AT242011182221219800"));
    Assert.assertEquals("CH16 0900 0000 8777 6876 6", IbanUtil.ibanFormat("CH1609000000877768766"));
    Assert.assertEquals("IT73 O050 1803 2000 0000 0125 125",
        IbanUtil.ibanFormat("IT73O0501803200000000125125"));
    Assert.assertEquals("BE51 3630 3644 5162", IbanUtil.ibanFormat("BE51363036445162"));
    Assert.assertEquals("DK62 8065 0002 0071 98", IbanUtil.ibanFormat("DK6280650002007198"));
    Assert.assertEquals("NL42 INGB 0006 3919 52", IbanUtil.ibanFormat("NL42INGB0006391952"));
    Assert.assertEquals("SE28 5000 0000 0530 4100 2965",
        IbanUtil.ibanFormat("SE2850000000053041002965"));
    Assert.assertEquals("SI56 0201 0001 1603 397", IbanUtil.ibanFormat("SI56020100011603397"));
  }

  /**
   * test compressing iban.
   */
  @Test
  public void ibanCompress() {
    Assert.assertNull(IbanUtil.ibanCompress(null));
    Assert.assertEquals("DE16701600000000555444",
        IbanUtil.ibanCompress("DE16 7016 0000 0000 5554 44"));
    Assert.assertEquals("DE49430609670000033401",
        IbanUtil.ibanCompress("DE49 4306 0967 0000 0334 01"));
    Assert.assertEquals("AT242011182221219800", IbanUtil.ibanCompress("AT24 2011 1822 2121 9800"));
    Assert.assertEquals("CH1609000000877768766",
        IbanUtil.ibanCompress("CH16 0900 0000 8777 6876 6"));
    Assert.assertEquals("IT73O0501803200000000125125",
        IbanUtil.ibanCompress("IT73 O050 1803 2000 0000 0125 125"));
    Assert.assertEquals("BE51363036445162", IbanUtil.ibanCompress("BE51 3630 3644 5162"));
    Assert.assertEquals("DK6280650002007198", IbanUtil.ibanCompress("DK62 8065 0002 0071 98"));
    Assert.assertEquals("NL42INGB0006391952", IbanUtil.ibanCompress("NL42 INGB 0006 3919 52"));
    Assert.assertEquals("SE2850000000053041002965",
        IbanUtil.ibanCompress("SE28 5000 0000 0530 4100 2965"));
    Assert.assertEquals("SI56020100011603397", IbanUtil.ibanCompress("SI56 0201 0001 1603 397"));
  }
}
