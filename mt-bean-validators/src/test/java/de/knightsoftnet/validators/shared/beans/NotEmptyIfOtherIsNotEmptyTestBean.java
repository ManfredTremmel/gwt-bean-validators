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

import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsNotEmpty;

@NotEmptyIfOtherIsNotEmpty(field = "street", fieldCompare = "streetNumber")
public class NotEmptyIfOtherIsNotEmptyTestBean {

  private final String street;

  private final String streetNumber;

  /**
   * constructor initializing fields.
   *
   * @param pstreet street name
   * @param pstreetNumber street number
   */
  public NotEmptyIfOtherIsNotEmptyTestBean(final String pstreet, final String pstreetNumber) {
    super();
    this.street = pstreet;
    this.streetNumber = pstreetNumber;
  }

  public String getStreet() {
    return this.street;
  }

  public String getStreetNumber() {
    return this.streetNumber;
  }

  @Override
  public String toString() {
    return "NotEmptyIfOtherIsNotEmptyTestBean [street=" + this.street + ", streetNumber="
        + this.streetNumber + "]";
  }
}
