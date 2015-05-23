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

import de.knightsoftnet.validators.shared.EmptyIfOtherHasValue;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherHasValue;

@EmptyIfOtherHasValue.List({
    @EmptyIfOtherHasValue(field = "street", fieldCompare = "type", valueCompare = "postOfficeBox"),
    @EmptyIfOtherHasValue(field = "postOfficeBox", fieldCompare = "type", valueCompare = "street")})
@NotEmptyIfOtherHasValue.List({
    @NotEmptyIfOtherHasValue(field = "street", fieldCompare = "type", valueCompare = "street"),
    @NotEmptyIfOtherHasValue(field = "postOfficeBox", fieldCompare = "type",
        valueCompare = "postOfficeBox")})
public class NotEmptyIfOtherHasValueTestBean {

  private final String type;

  private final String street;

  private final String postOfficeBox;

  /**
   * constructor initializing fields.
   *
   * @param ptype type indicator
   * @param pstreet street name
   * @param ppostOfficeBox post office box
   */
  public NotEmptyIfOtherHasValueTestBean(final String ptype, final String pstreet,
      final String ppostOfficeBox) {
    super();
    this.type = ptype;
    this.street = pstreet;
    this.postOfficeBox = ppostOfficeBox;
  }

  public String getType() {
    return this.type;
  }

  public String getStreet() {
    return this.street;
  }

  public String getPostOfficeBox() {
    return this.postOfficeBox;
  }

  @Override
  public String toString() {
    return "NotEmptyIfOtherHasValueTestBean [type=" + this.type + ", street=" + this.street
        + ", postOfficeBox=" + this.postOfficeBox + "]";
  }
}
