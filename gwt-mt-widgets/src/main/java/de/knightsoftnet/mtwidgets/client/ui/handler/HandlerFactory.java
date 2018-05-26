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

package de.knightsoftnet.mtwidgets.client.ui.handler;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.TakesValue;

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
      new HashMap<>();

  private static volatile UpperAsciiKeyPressHandler upperAsciiKeyPressHandler = null;
  private static volatile NumericAndUpperAsciiKeyPressHandler numericAndUpperAsciiKeyPressHandler =
      null;
  private static volatile NumericKeyPressHandler numericKeyPressHandler = null;
  private static volatile NumericWithSeparatorsKeyPressHandler numericWsKeyPressHandler = null;

  private static volatile CurrencyKeyPressHandler currencyKeyPressHandler = null;
  private static volatile PercentKeyPressHandler percentKeyPressHandler = null;
  private static volatile PhoneNumberKeyPressHandler phoneNumberKeyPressHandler = null;
  private static volatile DecimalKeyPressHandler decimalKeyPressHandler = null;

  private static volatile FilterReplaceAndFormatKeyPressHandler<String> filReplFormStrKeyPrH = null;
  private static volatile FormatKeyUpHandler<String> formatStrKeyUpHandler = null;

  /**
   * get a upper case key press handler.
   *
   * @return UpperAsciiKeyPressHandler
   */
  public static final KeyPressHandler getUpperAsciiKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.upperAsciiKeyPressHandler == null) {
      synchronized (UpperAsciiKeyPressHandler.class) {
        if (HandlerFactory.upperAsciiKeyPressHandler == null) {
          HandlerFactory.upperAsciiKeyPressHandler = new UpperAsciiKeyPressHandler();
        }
      }
    }
    return HandlerFactory.upperAsciiKeyPressHandler;
  }

  /**
   * get a numeric and upper case key press handler.
   *
   * @return NumericAndUpperAsciiKeyPressHandler
   */
  public static final KeyPressHandler getNumericAndUpperAsciiKeyPressHandler() { // NOPMD
    if (HandlerFactory.numericAndUpperAsciiKeyPressHandler == null) {
      synchronized (NumericAndUpperAsciiKeyPressHandler.class) {
        if (HandlerFactory.numericAndUpperAsciiKeyPressHandler == null) {
          HandlerFactory.numericAndUpperAsciiKeyPressHandler =
              new NumericAndUpperAsciiKeyPressHandler();
        }
      }
    }
    return HandlerFactory.numericAndUpperAsciiKeyPressHandler;
  }

  /**
   * get a numeric key press handler.
   *
   * @return NumericKeyPressHandler
   */
  public static final KeyPressHandler getNumericKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.numericKeyPressHandler == null) {
      synchronized (NumericKeyPressHandler.class) {
        if (HandlerFactory.numericKeyPressHandler == null) {
          HandlerFactory.numericKeyPressHandler = new NumericKeyPressHandler();
        }
      }
    }
    return HandlerFactory.numericKeyPressHandler;
  }

  /**
   * get a numeric with separators key press handler.
   *
   * @return NumericWithSeparatorsKeyPressHandler
   */
  public static final KeyPressHandler getNumericWithSeparatorsKeyPressHandler() { // NOPMD
    if (HandlerFactory.numericWsKeyPressHandler == null) {
      synchronized (NumericWithSeparatorsKeyPressHandler.class) {
        if (HandlerFactory.numericWsKeyPressHandler == null) {
          HandlerFactory.numericWsKeyPressHandler = new NumericWithSeparatorsKeyPressHandler();
        }
      }
    }
    return HandlerFactory.numericWsKeyPressHandler;
  }

  /**
   * get a currency key press handler.
   *
   * @return CurrencyKeyPressHandler
   */
  public static final KeyPressHandler getCurrencyKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.currencyKeyPressHandler == null) {
      synchronized (CurrencyKeyPressHandler.class) {
        if (HandlerFactory.currencyKeyPressHandler == null) {
          HandlerFactory.currencyKeyPressHandler = new CurrencyKeyPressHandler();

        }
      }
    }
    return HandlerFactory.currencyKeyPressHandler;
  }

  /**
   * get a percent key press handler.
   *
   * @return PercentKeyPressHandler
   */
  public static final KeyPressHandler getPercentKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.percentKeyPressHandler == null) {
      synchronized (PercentKeyPressHandler.class) {
        if (HandlerFactory.percentKeyPressHandler == null) {
          HandlerFactory.percentKeyPressHandler = new PercentKeyPressHandler();
        }
      }
    }
    return HandlerFactory.percentKeyPressHandler;
  }

  /**
   * get a phone number key press handler.
   *
   * @return PhoneNumberKeyPressHandler
   */
  public static final KeyPressHandler getPhoneNumberKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.phoneNumberKeyPressHandler == null) {
      synchronized (PhoneNumberKeyPressHandler.class) {
        if (HandlerFactory.phoneNumberKeyPressHandler == null) {
          HandlerFactory.phoneNumberKeyPressHandler = new PhoneNumberKeyPressHandler();
        }
      }
    }
    return HandlerFactory.phoneNumberKeyPressHandler;
  }

  /**
   * get a decimal key press handler.
   *
   * @return DecimalKeyPressHandler
   */
  public static final KeyPressHandler getDecimalKeyPressHandler() { // NOPMD it's thread save!
    if (HandlerFactory.decimalKeyPressHandler == null) {
      synchronized (DecimalKeyPressHandler.class) {
        if (HandlerFactory.decimalKeyPressHandler == null) {
          HandlerFactory.decimalKeyPressHandler = new DecimalKeyPressHandler();
        }
      }
    }
    return HandlerFactory.decimalKeyPressHandler;
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
    RegExKeyPressHandler result = HandlerFactory.REG_EX_KEY_PRESS_HANDLER_MAP.get(pregEx);
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
      final TakesValue<?> pcountryCodeField) {
    return new PostalCodeKeyPressHandler(pcountryCodeField);
  }


  /**
   * get a filter replace and format String key press handler.
   *
   * @return FilterReplaceAndFormatKeyPressHandler&lt;String&gt;
   */
  public static final KeyPressHandler getFilterReplAndFormatStrKeyPressHandler() { // NOPMD
    if (HandlerFactory.filReplFormStrKeyPrH == null) {
      synchronized (DecimalKeyPressHandler.class) {
        if (HandlerFactory.filReplFormStrKeyPrH == null) {
          HandlerFactory.filReplFormStrKeyPrH = new FilterReplaceAndFormatKeyPressHandler<>();
        }
      }
    }
    return HandlerFactory.filReplFormStrKeyPrH;
  }

  /**
   * get a format key up handler.
   *
   * @return FormatKeyUpHandler&lt;String&gt;
   */
  public static final KeyUpHandler getFormatStrKeyUpHandler() { // NOPMD it's thread save!
    if (HandlerFactory.formatStrKeyUpHandler == null) {
      synchronized (DecimalKeyPressHandler.class) {
        if (HandlerFactory.formatStrKeyUpHandler == null) {
          HandlerFactory.formatStrKeyUpHandler = new FormatKeyUpHandler<>();
        }
      }
    }
    return HandlerFactory.formatStrKeyUpHandler;
  }

  /**
   * get a key press handler which allows characters for tax number of a referenced country.
   *
   * @param pcountryCodeField reference to country code field
   * @return key press handler
   */
  public static final KeyPressHandler getTaxNumberKeyPressHandler(
      final TakesValue<?> pcountryCodeField) {
    return new TaxNumberKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a key press handler which allows characters for tin of a referenced country.
   *
   * @param pcountryCodeField reference to country code field
   * @return key press handler
   */
  public static final KeyPressHandler getTinKeyPressHandler(final TakesValue<?> pcountryCodeField) {
    return new TinKeyPressHandler(pcountryCodeField);
  }

  /**
   * get a key press handler which allows characters for vat id of a referenced country.
   *
   * @param pcountryCodeField reference to country code field
   * @return key press handler
   */
  public static final KeyPressHandler getVatIdKeyPressHandler(
      final TakesValue<?> pcountryCodeField) {
    return new VatIdKeyPressHandler(pcountryCodeField);
  }
}
