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

package de.knightsoftnet.validators.client.handlers;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.HasValue;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory Class to get Handlers.
 *
 * @author Manfred Tremmel
 */
public class HandlerFactory {

  private static final Map<String, RegExKeyPressHandler> REG_EX_KEY_PRESS_HANDLER_MAP =
      new HashMap<String, RegExKeyPressHandler>();

  private static volatile UpperAsciiKeyPressHandler upperAsciiKeyPressHandler = null;
  private static volatile NumericAndUpperAsciiKeyPressHandler numericAndUpperAsciiKeyPressHandler =
      null;
  private static volatile IbanKeyPressHandler ibanKeyPressHandler = null;
  private static volatile IbanKeyUpHandler ibanKeyUpHandler = null;
  private static volatile Isbn10KeyPressHandler isbn10KeyPressHandler = null;
  private static volatile Isbn10KeyUpHandler isbn10KeyUpHandler = null;
  private static volatile Isbn13KeyPressHandler isbn13KeyPressHandler = null;
  private static volatile Isbn13KeyUpHandler isbn13KeyUpHandler = null;
  private static volatile IsbnKeyPressHandler isbnKeyPressHandler = null;
  private static volatile IsbnKeyUpHandler isbnKeyUpHandler = null;
  private static volatile NumericKeyPressHandler numericKeyPressHandler = null;
  private static volatile CurrencyKeyPressHandler currencyKeyPressHandler = null;
  private static volatile PercentKeyPressHandler percentKeyPressHandler = null;
  private static volatile PhoneNumberKeyPressHandler phoneNumberKeyPressHandler = null;
  private static volatile DecimalKeyPressHandler decimalKeyPressHandler = null;

  /**
   * get a upper case key press handler.
   *
   * @return UpperAsciiKeyPressHandler
   */
  public static final KeyPressHandler getUpperAsciiKeyPressHandler() {
    if (upperAsciiKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (UpperAsciiKeyPressHandler.class) {
        if (upperAsciiKeyPressHandler == null) {
          upperAsciiKeyPressHandler = new UpperAsciiKeyPressHandler();
        }
      }
    }
    return upperAsciiKeyPressHandler;
  }

  /**
   * get a numeric and upper case key press handler.
   *
   * @return NumericAndUpperAsciiKeyPressHandler
   */
  public static final KeyPressHandler getNumericAndUpperAsciiKeyPressHandler() {
    if (numericAndUpperAsciiKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (NumericAndUpperAsciiKeyPressHandler.class) {
        if (numericAndUpperAsciiKeyPressHandler == null) {
          numericAndUpperAsciiKeyPressHandler = new NumericAndUpperAsciiKeyPressHandler();
        }
      }
    }
    return numericAndUpperAsciiKeyPressHandler;
  }

  /**
   * get a iban key press handler.
   *
   * @return IbanKeyPressHandler
   */
  public static final KeyPressHandler getIbanKeyPressHandler() {
    if (ibanKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (IbanKeyPressHandler.class) {
        if (ibanKeyPressHandler == null) {
          ibanKeyPressHandler = new IbanKeyPressHandler();
        }
      }
    }
    return ibanKeyPressHandler;
  }

  /**
   * get a iban key up handler.
   *
   * @return IbanKeyUpHandler
   */
  public static final KeyUpHandler getIbanKeyUpHandler() {
    if (ibanKeyUpHandler == null) { // NOPMD it's thread save!
      synchronized (IbanKeyUpHandler.class) {
        if (ibanKeyUpHandler == null) {
          ibanKeyUpHandler = new IbanKeyUpHandler();
        }
      }
    }
    return ibanKeyUpHandler;
  }

  /**
   * get a isbn 10 key press handler.
   *
   * @return Isbn10KeyPressHandler
   */
  public static final KeyPressHandler getIsbn10KeyPressHandler() {
    if (isbn10KeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (Isbn10KeyPressHandler.class) {
        if (isbn10KeyPressHandler == null) {
          isbn10KeyPressHandler = new Isbn10KeyPressHandler();
        }
      }
    }
    return isbn10KeyPressHandler;
  }

  /**
   * get a isbn 10 key up handler.
   *
   * @return Isbn10KeyUpHandler
   */
  public static final KeyUpHandler getIsbn10KeyUpHandler() {
    if (isbn10KeyUpHandler == null) { // NOPMD it's thread save!
      synchronized (Isbn10KeyUpHandler.class) {
        if (isbn10KeyUpHandler == null) {
          isbn10KeyUpHandler = new Isbn10KeyUpHandler();
        }
      }
    }
    return isbn10KeyUpHandler;
  }

  /**
   * get a isbn 13 key press handler.
   *
   * @return Isbn13KeyPressHandler
   */
  public static final KeyPressHandler getIsbn13KeyPressHandler() {
    if (isbn13KeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (Isbn13KeyPressHandler.class) {
        if (isbn13KeyPressHandler == null) {
          isbn13KeyPressHandler = new Isbn13KeyPressHandler();
        }
      }
    }
    return isbn13KeyPressHandler;
  }

  /**
   * get a isbn 13 key up handler.
   *
   * @return Isbn13KeyUpHandler
   */
  public static final KeyUpHandler getIsbn13KeyUpHandler() {
    if (isbn13KeyUpHandler == null) { // NOPMD it's thread save!
      synchronized (Isbn13KeyUpHandler.class) {
        if (isbn13KeyUpHandler == null) {
          isbn13KeyUpHandler = new Isbn13KeyUpHandler();
        }
      }
    }
    return isbn13KeyUpHandler;
  }

  /**
   * get a isbn key press handler.
   *
   * @return IsbnKeyPressHandler
   */
  public static final KeyPressHandler getIsbnKeyPressHandler() {
    if (isbnKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (IsbnKeyPressHandler.class) {
        if (isbnKeyPressHandler == null) {
          isbnKeyPressHandler = new IsbnKeyPressHandler();
        }
      }
    }
    return isbnKeyPressHandler;
  }

  /**
   * get a isbn key up handler.
   *
   * @return IsbnKeyUpHandler
   */
  public static final KeyUpHandler getIsbnKeyUpHandler() {
    if (isbnKeyUpHandler == null) { // NOPMD it's thread save!
      synchronized (IsbnKeyUpHandler.class) {
        if (isbnKeyUpHandler == null) {
          isbnKeyUpHandler = new IsbnKeyUpHandler();
        }
      }
    }
    return isbnKeyUpHandler;
  }

  /**
   * get a numeric key press handler.
   *
   * @return NumericKeyPressHandler
   */
  public static final KeyPressHandler getNumericKeyPressHandler() {
    if (numericKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (NumericKeyPressHandler.class) {
        if (numericKeyPressHandler == null) {
          numericKeyPressHandler = new NumericKeyPressHandler();
        }
      }
    }
    return numericKeyPressHandler;
  }

  /**
   * get a currency key press handler.
   *
   * @return CurrencyKeyPressHandler
   */
  public static final KeyPressHandler getCurrencyKeyPressHandler() {
    if (currencyKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (CurrencyKeyPressHandler.class) {
        if (currencyKeyPressHandler == null) {
          currencyKeyPressHandler = new CurrencyKeyPressHandler();

        }
      }
    }
    return currencyKeyPressHandler;
  }

  /**
   * get a percent key press handler.
   *
   * @return PercentKeyPressHandler
   */
  public static final KeyPressHandler getPercentKeyPressHandler() {
    if (percentKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (PercentKeyPressHandler.class) {
        if (percentKeyPressHandler == null) {
          percentKeyPressHandler = new PercentKeyPressHandler();
        }
      }
    }
    return percentKeyPressHandler;
  }

  /**
   * get a phone number key press handler.
   *
   * @return PhoneNumberKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberKeyPressHandler() {
    if (phoneNumberKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (PhoneNumberKeyPressHandler.class) {
        if (phoneNumberKeyPressHandler == null) {
          phoneNumberKeyPressHandler = new PhoneNumberKeyPressHandler();
        }
      }
    }
    return phoneNumberKeyPressHandler;
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberCommonInternationalKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberCommonInternationalKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberCommonInternationalKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberCommonInternationalKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberCommonInternationalKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberCommonInternationalKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberCommonKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberCommonKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberCommonKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberCommonKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberCommonKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberCommonKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberDin5008InternationalKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberDin5008InternationalKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberDin5008InternationalKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberDin5008InternationalKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberDin5008InternationalKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberDin5008InternationalKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberDin5008KeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberDin5008KeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberDin5008KeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberDin5008KeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberDin5008KeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberDin5008KeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberE123InternationalKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberE123InternationalKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberE123InternationalKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberE123InternationalKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberE123InternationalKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberE123InternationalKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberE123KeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberE123KeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberE123KeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberE123KeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberE123KeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberE123KeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberMsKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberMsKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberMsKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberMsKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberMsKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberMsKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a phone number key press handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberUriKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberUriKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberUriKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a phone number key up handler.
   *
   * @param pcountryCodeField reference to country code field
   * @return PhoneNumberUriKeyUpHandler
   */
  public static final KeyUpHandler getPhoneNumberUriKeyUpHandler(
      final HasValue<?> pcountryCodeField) {
    return new PhoneNumberUriKeyUpHandler(pcountryCodeField);
  }

  /**
   * get a decimal key press handler.
   *
   * @return DecimalKeyPressHandler
   */
  public static final KeyPressHandler getDecimalKeyPressHandler() {
    if (decimalKeyPressHandler == null) { // NOPMD it's thread save!
      synchronized (DecimalKeyPressHandler.class) {
        if (decimalKeyPressHandler == null) {
          decimalKeyPressHandler = new DecimalKeyPressHandler();
        }
      }
    }
    return decimalKeyPressHandler;
  }

  /**
   * get a key press handler which allows all characters which could match a reg ex.
   *
   * @param pregEx to check
   * @return key press handler
   */
  public static final KeyPressHandler getRegExKeyPressHandler(final String pregEx) {
    if (StringUtils.isEmpty(pregEx)) {
      return null;
    }
    RegExKeyPressHandler result = REG_EX_KEY_PRESS_HANDLER_MAP.get(pregEx);
    if (result == null) {
      result = new RegExKeyPressHandler(pregEx);
    }
    return result;
  }

  /**
   * get a key press handler which allows characters for postal codes of a referenced country.
   *
   * @param pcountryCodeField reference to country code field
   * @return key press handler
   */
  public static final KeyPressHandler getPostalCodeKeyPressHandler(
      final HasValue<?> pcountryCodeField) {
    return new PostalCodeKeyPressHandler(pcountryCodeField);
  }
}
