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

package de.knightsoftnet.validators.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import de.knightsoftnet.validators.shared.AgeLimitTestBean;
import de.knightsoftnet.validators.shared.AlternateSizeTestBean;
import de.knightsoftnet.validators.shared.BankCountryTestBean;
import de.knightsoftnet.validators.shared.BicTestBean;
import de.knightsoftnet.validators.shared.CreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.EmailTestBean;
import de.knightsoftnet.validators.shared.EmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.EmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.GlnTestBean;
import de.knightsoftnet.validators.shared.Gtin13TestBean;
import de.knightsoftnet.validators.shared.Gtin8TestBean;
import de.knightsoftnet.validators.shared.GtinTestBean;
import de.knightsoftnet.validators.shared.IbanFormatedTestBean;
import de.knightsoftnet.validators.shared.IbanTestBean;
import de.knightsoftnet.validators.shared.Isbn10FormatedTestBean;
import de.knightsoftnet.validators.shared.Isbn10TestBean;
import de.knightsoftnet.validators.shared.Isbn13FormatedTestBean;
import de.knightsoftnet.validators.shared.Isbn13TestBean;
import de.knightsoftnet.validators.shared.IsbnFormatedTestBean;
import de.knightsoftnet.validators.shared.IsbnTestBean;
import de.knightsoftnet.validators.shared.IsinTestBean;
import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.PasswordTestBean;
import de.knightsoftnet.validators.shared.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.RegularExpressionTestBean;
import de.knightsoftnet.validators.shared.UrlTestBean;
import de.knightsoftnet.validators.shared.VatIdTestBean;

import javax.validation.Validator;

/**
 * The <code>ValidatorFactory</code> class is used for client side validation by annotation. FOR
 * TESTING ONLY!
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 */
public class ValidatorFactory extends AbstractGwtValidatorFactory {

  /**
   * Validator marker for the Validation Sample project. Only the classes and groups listed in the
   * {@link GwtValidation} annotation can be validated.
   */
  @GwtValidation(value = {AgeLimitTestBean.class, AlternateSizeTestBean.class,
      BankCountryTestBean.class, BicTestBean.class, CreditCardNumberTestBean.class,
      EmailTestBean.class, EmptyIfOtherIsEmptyTestBean.class, EmptyIfOtherIsNotEmptyTestBean.class,
      GlnTestBean.class, Gtin13TestBean.class, Gtin8TestBean.class, GtinTestBean.class,
      IbanFormatedTestBean.class, IbanTestBean.class, Isbn10FormatedTestBean.class,
      Isbn10TestBean.class, Isbn13FormatedTestBean.class, Isbn13TestBean.class,
      IsbnFormatedTestBean.class, IsbnTestBean.class, IsinTestBean.class,
      NotEmptyAlternateIfOtherHasValueTestBean.class,
      NotEmptyAlternateIfOtherIsEmptyTestBean.class,
      NotEmptyAlternateIfOtherIsNotEmptyTestBean.class, NotEmptyIfOtherHasValueTestBean.class,
      NotEmptyIfOtherIsEmptyTestBean.class, NotEmptyIfOtherIsNotEmptyTestBean.class,
      PasswordTestBean.class, PostalCodeTestBean.class, RegularExpressionTestBean.class,
      UrlTestBean.class, VatIdTestBean.class})
  public interface GwtValidator extends Validator {
  }

  @Override
  public final AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }

}
