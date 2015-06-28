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

/**
 * Factory Class to get Handlers.
 *
 * @author Manfred Tremmel
 */
public class HandlerFactory {

  private static UpperAsciiKeyPressHandler upperAsciiKeyPressHandler = null;
  private static NumericAndUpperAsciiKeyPressHandler numericAndUpperAsciiKeyPressHandler = null;
  private static IbanKeyPressHandler ibanKeyPressHandler = null;
  private static IbanKeyUpHandler ibanKeyUpHandler = null;
  private static NumericKeyPressHandler numericKeyPressHandler = null;
  private static CurrencyKeyPressHandler currencyKeyPressHandler = null;
  private static PercentKeyPressHandler percentKeyPressHandler = null;
  private static DecimalKeyPressHandler decimalKeyPressHandler = null;

  /**
   * get a upper case key press handler.
   *
   * @return UpperAsciiKeyPressHandler
   */
  public static final UpperAsciiKeyPressHandler getUpperAsciiKeyPressHandler() {
    if (upperAsciiKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      upperAsciiKeyPressHandler = new UpperAsciiKeyPressHandler();
    }
    return upperAsciiKeyPressHandler;
  }

  /**
   * get a numeric and upper case key press handler.
   *
   * @return NumericAndUpperAsciiKeyPressHandler
   */
  public static final NumericAndUpperAsciiKeyPressHandler getNumericAndUpperAsciiKeyPressHandler() {
    if (numericAndUpperAsciiKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      numericAndUpperAsciiKeyPressHandler = new NumericAndUpperAsciiKeyPressHandler();
    }
    return numericAndUpperAsciiKeyPressHandler;
  }

  /**
   * get a iban key press handler.
   *
   * @return IbanKeyPressHandler
   */
  public static final IbanKeyPressHandler getIbanKeyPressHandler() {
    if (ibanKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      ibanKeyPressHandler = new IbanKeyPressHandler();
    }
    return ibanKeyPressHandler;
  }

  /**
   * get a iban key up handler.
   *
   * @return IbanKeyUpHandler
   */
  public static final IbanKeyUpHandler getIbanKeyUpHandler() {
    if (ibanKeyUpHandler == null) { // NOPMD, needn't be thread save on client
      ibanKeyUpHandler = new IbanKeyUpHandler();
    }
    return ibanKeyUpHandler;
  }

  /**
   * get a numeric key press handler.
   *
   * @return NumericKeyPressHandler
   */
  public static final NumericKeyPressHandler getNumericKeyPressHandler() {
    if (numericKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      numericKeyPressHandler = new NumericKeyPressHandler();
    }
    return numericKeyPressHandler;
  }

  /**
   * get a currency key press handler.
   *
   * @return CurrencyKeyPressHandler
   */
  public static final CurrencyKeyPressHandler getCurrencyKeyPressHandler() {
    if (currencyKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      currencyKeyPressHandler = new CurrencyKeyPressHandler();
    }
    return currencyKeyPressHandler;
  }

  /**
   * get a percent key press handler.
   *
   * @return PercentKeyPressHandler
   */
  public static final PercentKeyPressHandler getPercentKeyPressHandler() {
    if (percentKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      percentKeyPressHandler = new PercentKeyPressHandler();
    }
    return percentKeyPressHandler;
  }

  /**
   * get a decimal key press handler.
   *
   * @return DecimalKeyPressHandler
   */
  public static final DecimalKeyPressHandler getDecimalKeyPressHandler() {
    if (decimalKeyPressHandler == null) { // NOPMD, needn't be thread save on client
      decimalKeyPressHandler = new DecimalKeyPressHandler();
    }
    return decimalKeyPressHandler;
  }
}
