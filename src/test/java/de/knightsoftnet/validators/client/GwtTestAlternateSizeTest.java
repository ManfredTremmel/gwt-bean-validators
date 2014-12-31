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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.AlternateSizeTestBean;

import org.junit.Test;

public class GwtTestAlternateSizeTest extends AbstractValidationTest<AlternateSizeTestBean> {


  /**
   * empty value is allowed.
   */
  public final void testEmptyAlternateSizeIsAllowed() {
    super.validationTest(new AlternateSizeTestBean(null), true, null);
  }

  /**
   * correct sizes are allowed.
   */
  @Test
  public final void testCorrectAlternateSizesAreAllowed() {
    for (final String value : new String[] {"abcABCou!3", "3251202537", "3453136446",
        "4035600210708", "9783453136441"}) {
      super.validationTest(new AlternateSizeTestBean(value), true, null);
    }
  }

  /**
   * wrong sizes are not allowed.
   */
  public final void testWrongAlternateSizeAreWrong() {
    for (final String value : new String[] {"308770192", "32512253", "34531365468", "403560821070",
        "978345313654"}) {
      super.validationTest(new AlternateSizeTestBean(value), false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }
}
