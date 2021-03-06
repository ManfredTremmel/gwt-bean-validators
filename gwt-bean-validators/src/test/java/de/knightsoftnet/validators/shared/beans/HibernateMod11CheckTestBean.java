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

package de.knightsoftnet.validators.shared.beans;

import org.hibernate.validator.constraints.Mod11Check;
import org.hibernate.validator.constraints.Mod11Check.ProcessingDirection;

public class HibernateMod11CheckTestBean {

  @Mod11Check(startIndex = 0, endIndex = Integer.MAX_VALUE, checkDigitIndex = -1,
      ignoreNonDigitCharacters = true, treatCheck10As = '0', treatCheck11As = '0',
      processingDirection = ProcessingDirection.RIGHT_TO_LEFT)
  private final String value;

  public HibernateMod11CheckTestBean(final String pvalue) {
    super();
    this.value = pvalue;
  }

  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "HibernateMod11CheckTestBean [value=" + this.value + "]";
  }
}
