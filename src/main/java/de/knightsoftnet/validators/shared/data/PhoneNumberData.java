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

import java.io.Serializable;

/**
 * phone number data.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberData
    implements Serializable, PhoneNumberExtendedInterface, ValidationInterface {
  private static final long serialVersionUID = -5715038613377873088L;

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


  @Override
  public String getCountryCode() {
    return this.countryCode;
  }

  @Override
  public void setCountryCode(final String pcountryCode) {
    this.countryCode = pcountryCode;
  }

  @Override
  public String getCountryName() {
    return this.countryName;
  }

  @Override
  public void setCountryName(final String pcountryName) {
    this.countryName = pcountryName;
  }

  @Override
  public String getAreaCode() {
    return this.areaCode;
  }

  @Override
  public void setAreaCode(final String pareaCode) {
    this.areaCode = pareaCode;
  }

  @Override
  public String getAreaName() {
    return this.areaName;
  }

  @Override
  public void setAreaName(final String pareaName) {
    this.areaName = pareaName;
  }

  @Override
  public String getLineNumber() {
    return this.lineNumber;
  }

  @Override
  public void setLineNumber(final String plineNumber) {
    this.lineNumber = plineNumber;
  }

  @Override
  public String getExtension() {
    return this.extension;
  }

  @Override
  public void setExtension(final String pextension) {
    this.extension = pextension;
  }

  @Override
  public boolean isValid() {
    return this.valid;
  }

  @Override
  public void setValid(final boolean pvalid) {
    this.valid = pvalid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.countryCode == null ? 0 : this.countryCode.hashCode());
    result = prime * result + (this.areaCode == null ? 0 : this.areaCode.hashCode());
    result = prime * result + (this.lineNumber == null ? 0 : this.lineNumber.hashCode());
    result = prime * result + (this.extension == null ? 0 : this.extension.hashCode());
    return result;
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
