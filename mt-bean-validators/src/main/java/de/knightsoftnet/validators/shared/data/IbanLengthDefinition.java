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

package de.knightsoftnet.validators.shared.data;

import org.apache.commons.lang3.StringUtils;

public class IbanLengthDefinition {
  private int length;
  private int bankNumberStart;
  private int bankNumberEnd;
  private int accountNumberStart;
  private int accountNumberEnd;

  /**
   * default constructor.
   */
  public IbanLengthDefinition() {
    super();
  }

  /**
   * constructor parsing value from string.
   *
   * @param pvalueToParse semicolon separated string with values
   */
  public IbanLengthDefinition(final String pvalueToParse) {
    super();
    final String[] splittedValue = StringUtils.split(pvalueToParse, ';');
    if (splittedValue != null) {
      if (splittedValue.length >= 5) {
        accountNumberEnd = Integer.parseInt(splittedValue[4]);
      }
      if (splittedValue.length >= 4) {
        accountNumberStart = Integer.parseInt(splittedValue[3]);
      }
      if (splittedValue.length >= 3) {
        bankNumberEnd = Integer.parseInt(splittedValue[2]);
      }
      if (splittedValue.length >= 2) {
        bankNumberStart = Integer.parseInt(splittedValue[1]);
      }
      if (splittedValue.length >= 1) {
        length = Integer.parseInt(splittedValue[0]);
      }
    }
  }

  public final int getLength() {
    return length;
  }

  public final void setLength(final int plength) {
    length = plength;
  }

  public final int getBankNumberStart() {
    return bankNumberStart;
  }

  public final void setBankNumberStart(final int pbankNumberStart) {
    bankNumberStart = pbankNumberStart;
  }

  public final int getBankNumberEnd() {
    return bankNumberEnd;
  }

  public final void setBankNumberEnd(final int pbankNumberEnd) {
    bankNumberEnd = pbankNumberEnd;
  }

  public final int getAccountNumberStart() {
    return accountNumberStart;
  }

  public final void setAccountNumberStart(final int paccountNumberStart) {
    accountNumberStart = paccountNumberStart;
  }

  public final int getAccountNumberEnd() {
    return accountNumberEnd;
  }

  public final void setAccountNumberEnd(final int paccountNumberEnd) {
    accountNumberEnd = paccountNumberEnd;
  }

}
