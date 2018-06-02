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

import de.knightsoftnet.validators.shared.data.ValueWithPos;

/**
 * bean with value, cursor position and default country.
 *
 * @author Manfred Tremmel
 *
 * @param <E> Type of value
 */
@JsonFormat
public class ValueWithPosAndCountry<E> extends ValueWithPos<E> {
  private String language;
  private String country;

  /**
   * default constructor.
   */
  public ValueWithPosAndCountry() {
    super();
  }

  /**
   * constructor initializing fields.
   *
   * @param pvalue value entry
   * @param ppos cursor position
   */
  public ValueWithPosAndCountry(final E pvalue, final int ppos) {
    super(pvalue, ppos);
  }

  /**
   * constructor initializing fields.
   *
   * @param pvalue value entry
   * @param ppos cursor position
   * @param pcountry default country code
   * @param planguage language to get messages
   */
  public ValueWithPosAndCountry(final E pvalue, final int ppos, final String pcountry,
      final String planguage) {
    this(pvalue, ppos);
    this.country = pcountry;
    this.language = planguage;
  }

  public final String getCountry() {
    return this.country;
  }

  public final void setCountry(final String pcountry) {
    this.country = pcountry;
  }

  public final String getLanguage() {
    return this.language;
  }

  public final void setLanguage(final String planguage) {
    this.language = planguage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Integer.valueOf(getPos()), getValue(), this.country, this.language);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    final ValueWithPosAndCountry<E> other = (ValueWithPosAndCountry<E>) obj;
    return getPos() == other.getPos() //
        && Objects.equals(getValue(), other.getValue()) //
        && StringUtils.equals(this.country, other.country) //
        && StringUtils.equals(this.language, other.language);
  }
}
