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

import de.knightsoftnet.validators.shared.AgeLimitCheck;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * age limit check test.
 *
 * @author Manfred Tremmel
 *
 */
public class AgeLimitCheckTest extends AbstractValidationTest<AgeLimitCheckTest.AgeLimitTestBean> {

  /**
   * the limit we test with.
   */
  private static final int AGE_LIMIT = 18;

  /**
   * age limit test bean containing only birthday.
   */
  public class AgeLimitTestBean {

    /** birthday. */
    @AgeLimitCheck(minYears = AGE_LIMIT)
    private final Date birthday;

    /**
     * constructor filling fields.
     *
     * @param pbirthday birthday to set
     */
    public AgeLimitTestBean(final Date pbirthday) {
      super();
      this.birthday = pbirthday;
    }

    public final Date getBirthday() {
      return this.birthday;
    }
  }

  /**
   * empty value is allowed.
   */
  @Test
  public final void testEmptyAgeIsAllowed() {
    super.validationTest(new AgeLimitTestBean(null), true, null);
  }

  /**
   * correct ages are allowed.
   */
  @Test
  public final void testCorrectAlternateSizesAreAllowed() {
    super.validationTest(new AgeLimitTestBean(DateUtils.addYears(new Date(), 0 - AGE_LIMIT - 1)),
        true, null);
    super.validationTest(new AgeLimitTestBean(DateUtils.addYears(new Date(), 0 - AGE_LIMIT)), true,
        null);
  }

  /**
   * wrong ages are not allowed.
   */
  @Test
  public final void testWrongAlternateSizeAreWrong() {
    super.validationTest(new AgeLimitTestBean(new Date()), false,
        "de.knightsoftnet.validators.shared.impl.AgeLimitCheckValidator");
    super.validationTest(
        new AgeLimitTestBean(DateUtils.addDays(DateUtils.addYears(new Date(), 0 - AGE_LIMIT), 1)),
        false, "de.knightsoftnet.validators.shared.impl.AgeLimitCheckValidator");
  }
}