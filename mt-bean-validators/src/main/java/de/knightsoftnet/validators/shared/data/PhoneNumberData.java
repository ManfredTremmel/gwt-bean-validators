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
    countryCode = pcountryCode;
    areaCode = pareaCode;
    lineNumber = plineNumber;
    extension = pextension;
  }

  /**
   * constructor initializing fields.
   *
   * @param pphoneNumber phone number data
   */
  public PhoneNumberData(final PhoneNumberInterface pphoneNumber) {
    super();
    countryCode = pphoneNumber.getCountryCode();
    areaCode = pphoneNumber.getAreaCode();
    lineNumber = pphoneNumber.getLineNumber();
    extension = pphoneNumber.getExtension();
    if (pphoneNumber instanceof PhoneNumberExtendedInterface) {
      countryName = ((PhoneNumberExtendedInterface) pphoneNumber).getCountryName();
      areaName = ((PhoneNumberExtendedInterface) pphoneNumber).getAreaName();
    }
    if (pphoneNumber instanceof ValidationInterface) {
      valid = ((ValidationInterface) pphoneNumber).isValid();
    }
  }


  @Override
  public final String getCountryCode() {
    return countryCode;
  }

  @Override
  public final void setCountryCode(final String pcountryCode) {
    countryCode = pcountryCode;
  }

  @Override
  public final String getCountryName() {
    return countryName;
  }

  @Override
  public final void setCountryName(final String pcountryName) {
    countryName = pcountryName;
  }

  @Override
  public final String getAreaCode() {
    return areaCode;
  }

  @Override
  public final void setAreaCode(final String pareaCode) {
    areaCode = pareaCode;
  }

  @Override
  public final String getAreaName() {
    return areaName;
  }

  @Override
  public final void setAreaName(final String pareaName) {
    areaName = pareaName;
  }

  @Override
  public final String getLineNumber() {
    return lineNumber;
  }

  @Override
  public final void setLineNumber(final String plineNumber) {
    lineNumber = plineNumber;
  }

  @Override
  public final String getExtension() {
    return extension;
  }

  @Override
  public final void setExtension(final String pextension) {
    extension = pextension;
  }

  @Override
  public final boolean isValid() {
    return valid;
  }

  @Override
  public final void setValid(final boolean pvalid) {
    valid = pvalid;
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryCode, areaCode, lineNumber, extension);
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
    return StringUtils.equals(countryCode, other.countryCode)
        && StringUtils.equals(areaCode, other.areaCode)
        && StringUtils.equals(lineNumber, other.lineNumber)
        && StringUtils.equals(extension, other.extension);
  }

  @Override
  public String toString() {
    return "PhoneNumberData [countryCode=" + countryCode + ", areaCode=" + areaCode
        + ", phoneNumber=" + lineNumber + ", extension=" + extension + "]";
  }
}
