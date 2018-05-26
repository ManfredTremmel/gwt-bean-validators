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

import java.util.Objects;

/**
 * phone number data.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberData implements PhoneNumberExtendedInterface, ValidationInterface {

  private String countryCode;
  private String countryName;
  private String areaCode;
  private String areaName;
  private String lineNumber;
  private String extension;
  private boolean valid;

  /**
   * default constructor.
   */
  public PhoneNumberData() {
    super();
  }

  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country code
   * @param pareaCode area code
   * @param plineNumber phone number
   * @param pextension extension
   */
  public PhoneNumberData(final String pcountryCode, final String pareaCode,
      final String plineNumber, final String pextension) {
    super();
    this.countryCode = pcountryCode;
    this.areaCode = pareaCode;
    this.lineNumber = plineNumber;
    this.extension = pextension;
  }

  /**
   * constructor initializing fields.
   *
   * @param pphoneNumber phone number data
   */
  public PhoneNumberData(final PhoneNumberInterface pphoneNumber) {
    super();
    this.countryCode = pphoneNumber.getCountryCode();
    this.areaCode = pphoneNumber.getAreaCode();
    this.lineNumber = pphoneNumber.getLineNumber();
    this.extension = pphoneNumber.getExtension();
    if (pphoneNumber instanceof PhoneNumberExtendedInterface) {
      this.countryName = ((PhoneNumberExtendedInterface) pphoneNumber).getCountryName();
      this.areaName = ((PhoneNumberExtendedInterface) pphoneNumber).getAreaName();
    }
    if (pphoneNumber instanceof ValidationInterface) {
      this.valid = ((ValidationInterface) pphoneNumber).isValid();
    }
  }


  @Override
  public final String getCountryCode() {
    return this.countryCode;
  }

  @Override
  public final void setCountryCode(final String pcountryCode) {
    this.countryCode = pcountryCode;
  }

  @Override
  public final String getCountryName() {
    return this.countryName;
  }

  @Override
  public final void setCountryName(final String pcountryName) {
    this.countryName = pcountryName;
  }

  @Override
  public final String getAreaCode() {
    return this.areaCode;
  }

  @Override
  public final void setAreaCode(final String pareaCode) {
    this.areaCode = pareaCode;
  }

  @Override
  public final String getAreaName() {
    return this.areaName;
  }

  @Override
  public final void setAreaName(final String pareaName) {
    this.areaName = pareaName;
  }

  @Override
  public final String getLineNumber() {
    return this.lineNumber;
  }

  @Override
  public final void setLineNumber(final String plineNumber) {
    this.lineNumber = plineNumber;
  }

  @Override
  public final String getExtension() {
    return this.extension;
  }

  @Override
  public final void setExtension(final String pextension) {
    this.extension = pextension;
  }

  @Override
  public final boolean isValid() {
    return this.valid;
  }

  @Override
  public final void setValid(final boolean pvalid) {
    this.valid = pvalid;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.countryCode, this.areaCode, this.lineNumber, this.extension);
  }

  @Override
  public boolean equals(final Object pobj) {
    if (this == pobj) {
      return true;
    }
    if (pobj == null) {
      return false;
    }
    if (this.getClass() != pobj.getClass()) {
      return false;
    }
    final PhoneNumberData other = (PhoneNumberData) pobj;
    return StringUtils.equals(this.countryCode, other.countryCode)
        && StringUtils.equals(this.areaCode, other.areaCode)
        && StringUtils.equals(this.lineNumber, other.lineNumber)
        && StringUtils.equals(this.extension, other.extension);
  }

  @Override
  public String toString() {
    return "PhoneNumberData [countryCode=" + this.countryCode + ", areaCode=" + this.areaCode
        + ", phoneNumber=" + this.lineNumber + ", extension=" + this.extension + "]";
  }
}
