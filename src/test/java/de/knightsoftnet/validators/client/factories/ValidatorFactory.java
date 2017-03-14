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

package de.knightsoftnet.validators.client.factories;

import de.knightsoftnet.validators.client.AbstractGwtValidatorFactory;
import de.knightsoftnet.validators.client.GwtValidation;
import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;
import de.knightsoftnet.validators.shared.beans.AgeLimitTestBean;
import de.knightsoftnet.validators.shared.beans.AlternateSizeTestBean;
import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;
import de.knightsoftnet.validators.shared.beans.BicTestBean;
import de.knightsoftnet.validators.shared.beans.BicValueTestBean;
import de.knightsoftnet.validators.shared.beans.BicWithSpacesTestBean;
import de.knightsoftnet.validators.shared.beans.CreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.beans.EmailTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.GlnTestBean;
import de.knightsoftnet.validators.shared.beans.Gtin13TestBean;
import de.knightsoftnet.validators.shared.beans.Gtin8TestBean;
import de.knightsoftnet.validators.shared.beans.GtinTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateAssertFalseTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateAssertTrueTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateCnpjTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateCpfTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateCreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateDecimalMinMaxTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateDigitsTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateEan13TestBean;
import de.knightsoftnet.validators.shared.beans.HibernateEan8TestBean;
import de.knightsoftnet.validators.shared.beans.HibernateEmailTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateFutureTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateLengthTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateMinMaxTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateNipTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateNotBlankTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateNotNullTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateNullTestBean;
import de.knightsoftnet.validators.shared.beans.HibernatePastTestBean;
import de.knightsoftnet.validators.shared.beans.HibernatePatternTestBean;
import de.knightsoftnet.validators.shared.beans.HibernatePeselTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateRegonTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateSizeArrayTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateSizeCollectionTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateSizeTestBean;
import de.knightsoftnet.validators.shared.beans.HibernateUrlTestBean;
import de.knightsoftnet.validators.shared.beans.IbanFormatedTestBean;
import de.knightsoftnet.validators.shared.beans.IbanTestBean;
import de.knightsoftnet.validators.shared.beans.IbanWithSpacesTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn10FormatedTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn10TestBean;
import de.knightsoftnet.validators.shared.beans.Isbn10WithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn13FormatedTestBean;
import de.knightsoftnet.validators.shared.beans.Isbn13TestBean;
import de.knightsoftnet.validators.shared.beans.Isbn13WithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.beans.IsbnFormatedTestBean;
import de.knightsoftnet.validators.shared.beans.IsbnTestBean;
import de.knightsoftnet.validators.shared.beans.IsbnWithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.beans.IsinTestBean;
import de.knightsoftnet.validators.shared.beans.LevenshteinDistanceTestBean;
import de.knightsoftnet.validators.shared.beans.LimitCharsetTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeBiggerDateTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeBiggerIntegerTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeBiggerOrEqualDateTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeBiggerOrEqualIntegerTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeEqualTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeSmallerDateTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeSmallerIntegerTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeSmallerOrEqualDateTestBean;
import de.knightsoftnet.validators.shared.beans.MustBeSmallerOrEqualIntegerTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.PasswordTestBean;
import de.knightsoftnet.validators.shared.beans.PhoneNumberTestBean;
import de.knightsoftnet.validators.shared.beans.PhoneNumberValueTestBean;
import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.beans.RegExTestBean;
import de.knightsoftnet.validators.shared.beans.SizeWithoutSeparatorsTestBean;
import de.knightsoftnet.validators.shared.beans.UrlTestBean;
import de.knightsoftnet.validators.shared.beans.VatIdTestBean;

import com.google.gwt.core.client.GWT;

import javax.validation.Validator;

/**
 * The <code>ValidatorFactory</code> class is used for client side validation by annotation. All
 * beans which should be checked by bean validators have to be added to @GwtValidation annotation.
 *
 * @author Manfred Tremmel
 *
 */
public class ValidatorFactory extends AbstractGwtValidatorFactory {

  /**
   * Validator marker for the Validation Sample project. Only the classes and groups listed in the
   * {@link GwtValidation} annotation can be validated.
   */
  @GwtValidation(value = {AgeLimitTestBean.class, AlternateSizeTestBean.class,
      BankCountryTestBean.class, BicTestBean.class, BicValueTestBean.class,
      BicWithSpacesTestBean.class, CreditCardNumberTestBean.class, EmailTestBean.class,
      EmptyIfOtherIsEmptyTestBean.class, EmptyIfOtherIsNotEmptyTestBean.class, GlnTestBean.class,
      Gtin13TestBean.class, Gtin8TestBean.class, GtinTestBean.class, IbanFormatedTestBean.class,
      IbanTestBean.class, IbanWithSpacesTestBean.class, Isbn10FormatedTestBean.class,
      Isbn10TestBean.class, Isbn10WithSeparatorsTestBean.class, Isbn13FormatedTestBean.class,
      Isbn13TestBean.class, Isbn13WithSeparatorsTestBean.class, IsbnFormatedTestBean.class,
      IsbnTestBean.class, IsbnWithSeparatorsTestBean.class, IsinTestBean.class,
      LimitCharsetTestBean.class, MustBeEqualTestBean.class, MustBeBiggerDateTestBean.class,
      MustBeBiggerIntegerTestBean.class, MustBeBiggerOrEqualDateTestBean.class,
      MustBeBiggerOrEqualIntegerTestBean.class, MustBeSmallerDateTestBean.class,
      MustBeSmallerIntegerTestBean.class, MustBeSmallerOrEqualDateTestBean.class,
      MustBeSmallerOrEqualIntegerTestBean.class, NotEmptyAlternateIfOtherHasValueTestBean.class,
      NotEmptyAlternateIfOtherIsEmptyTestBean.class,
      NotEmptyAlternateIfOtherIsNotEmptyTestBean.class, NotEmptyIfOtherHasValueTestBean.class,
      NotEmptyIfOtherIsEmptyTestBean.class, NotEmptyIfOtherIsNotEmptyTestBean.class,
      PasswordTestBean.class, PhoneNumberTestBean.class, PhoneNumberValueTestBean.class,
      PostalCodeTestBean.class, RegExTestBean.class, SizeWithoutSeparatorsTestBean.class,
      UrlTestBean.class, VatIdTestBean.class, LevenshteinDistanceTestBean.class,

      HibernateAssertFalseTestBean.class, HibernateAssertTrueTestBean.class,
      HibernateDecimalMinMaxTestBean.class, HibernateDigitsTestBean.class,
      HibernateEmailTestBean.class, HibernateNotNullTestBean.class, HibernateNullTestBean.class,
      HibernatePatternTestBean.class, HibernateMinMaxTestBean.class, HibernateSizeTestBean.class,
      HibernateSizeArrayTestBean.class, HibernateSizeCollectionTestBean.class,
      HibernateFutureTestBean.class, HibernatePastTestBean.class, HibernateNotBlankTestBean.class,
      HibernateNotEmptyTestBean.class, HibernateEan8TestBean.class, HibernateEan13TestBean.class,
      HibernateLengthTestBean.class, HibernateCreditCardNumberTestBean.class,
      HibernateUrlTestBean.class, HibernateCnpjTestBean.class, HibernateCpfTestBean.class,
      HibernateNipTestBean.class, HibernatePeselTestBean.class, HibernateRegonTestBean.class})
  public interface GwtValidator extends Validator {
  }

  @Override
  public final AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }
}
