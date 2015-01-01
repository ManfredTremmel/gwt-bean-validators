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

import de.knightsoftnet.validators.shared.beans.AgeLimitTestBean;
import de.knightsoftnet.validators.shared.beans.AlternateSizeTestBean;
import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;
import de.knightsoftnet.validators.shared.beans.BicTestBean;
import de.knightsoftnet.validators.shared.beans.CreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.beans.EmailTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.GlnTestBean;
import de.knightsoftnet.validators.shared.beans.Gtin13TestBean;
import de.knightsoftnet.validators.shared.beans.Gtin8TestBean;
import de.knightsoftnet.validators.shared.beans.GtinTestBean;
import de.knightsoftnet.validators.shared.beans.IbanFormatedTestBean;
import de.knightsoftnet.validators.shared.beans.IbanTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn10FormatedTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn10TestBean;
import de.knightsoftnet.validators.shared.beans.Isbn13FormatedTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn13TestBean;
import de.knightsoftnet.validators.shared.beans.IsbnFormatedTestBean;
import de.knightsoftnet.validators.shared.beans.IsbnTestBean;
import de.knightsoftnet.validators.shared.beans.IsinTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.PasswordTestBean;
import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.beans.RegularExpressionTestBean;
import de.knightsoftnet.validators.shared.beans.UrlTestBean;
import de.knightsoftnet.validators.shared.beans.VatIdTestBean;

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
