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

package de.knightsoftnet.gwtp.spring.shared.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;

/**
 * phone number data including formated values.
 *
 * @author Manfred Tremmel
 *
 */
@JsonFormat
public class PhoneNumberDataWithFormats extends PhoneNumberData {

  private String din5008National;
  private String din5008International;
  private String e123National;
  private String e123International;
  private String url;
  private String ms;
  private String commonNational;
  private String commonInternational;

  /**
   * default constructor.
   */
  public PhoneNumberDataWithFormats() {
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
  public PhoneNumberDataWithFormats(final String pcountryCode, final String pareaCode,
      final String plineNumber, final String pextension) {
    super(pcountryCode, pareaCode, plineNumber, pextension);
  }

  /**
   * constructor initializing fields.
   *
   * @param pphoneNumber phone number data
   */
  public PhoneNumberDataWithFormats(final PhoneNumberInterface pphoneNumber) {
    super(pphoneNumber);
  }

  public final String getDin5008National() {
    return din5008National;
  }

  public final void setDin5008National(final String pdin5008National) {
    din5008National = pdin5008National;
  }

  public final String getDin5008International() {
    return din5008International;
  }

  public final void setDin5008International(final String pdin5008International) {
    din5008International = pdin5008International;
  }

  public final String getE123National() {
    return e123National;
  }

  public final void setE123National(final String pe123National) {
    e123National = pe123National;
  }

  public final String getE123International() {
    return e123International;
  }

  public final void setE123International(final String pe123International) {
    e123International = pe123International;
  }

  public final String getUrl() {
    return url;
  }

  public final void setUrl(final String purl) {
    url = purl;
  }

  public final String getMs() {
    return ms;
  }

  public final void setMs(final String pms) {
    ms = pms;
  }

  public final String getCommonNational() {
    return commonNational;
  }

  public final void setCommonNational(final String pcommonNational) {
    commonNational = pcommonNational;
  }

  public final String getCommonInternational() {
    return commonInternational;
  }

  public final void setCommonInternational(final String pcommonInternational) {
    commonInternational = pcommonInternational;
  }

  @Override
  public int hashCode() {
    return Objects.hash(commonInternational, commonNational, din5008International, din5008National,
        e123International, e123National, ms, url);
  }

  @Override
  public boolean equals(final Object pobj) {
    if (this == pobj) {
      return true;
    }
    if (!super.equals(pobj)) {
      return false;
    }
    if (this.getClass() != pobj.getClass()) {
      return false;
    }
    final PhoneNumberDataWithFormats other = (PhoneNumberDataWithFormats) pobj;
    return StringUtils.equals(commonInternational, other.commonInternational)
        && StringUtils.equals(commonNational, other.commonNational)
        && StringUtils.equals(din5008International, other.din5008International)
        && StringUtils.equals(din5008National, other.din5008National)
        && StringUtils.equals(e123International, other.e123International)
        && StringUtils.equals(e123National, other.e123National) && StringUtils.equals(ms, other.ms)
        && StringUtils.equals(url, other.url);
  }
}
