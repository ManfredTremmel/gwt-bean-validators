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

import de.knightsoftnet.validators.client.AbstractGwtReflectGetterFactory;
import de.knightsoftnet.validators.client.GwtReflectGetterInterface;
import de.knightsoftnet.validators.client.GwtValidation;
import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.beans.LevenshteinDistanceTestBean;
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
import de.knightsoftnet.validators.shared.beans.PhoneNumberValueTestBean;
import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.beans.TaxNumberTestBean;
import de.knightsoftnet.validators.shared.beans.TinTestBean;
import de.knightsoftnet.validators.shared.beans.VatIdTestBean;

import com.google.gwt.core.client.GWT;

/**
 * The <code>ReflectGetterFactory</code> class is used for client side reflector replacement of the
 * getters. All beans which should need reflection like access to the getters, have to be added
 * to @GwtValidation annotation.
 *
 * @author Manfred Tremmel
 *
 */
public class ReflectGetterFactory extends AbstractGwtReflectGetterFactory {

  /**
   * Validator marker for the Reflector Sample project. Only the classes and groups listed in the
   * {@link GwtValidation} annotation can be reflected.
   */
  @GwtValidation(value = {BankCountryTestBean.class, EmptyIfOtherIsEmptyTestBean.class,
      EmptyIfOtherIsNotEmptyTestBean.class, MustBeEqualTestBean.class,
      MustBeBiggerDateTestBean.class, MustBeBiggerIntegerTestBean.class,
      MustBeBiggerOrEqualDateTestBean.class, MustBeBiggerOrEqualIntegerTestBean.class,
      MustBeSmallerDateTestBean.class, MustBeSmallerIntegerTestBean.class,
      MustBeSmallerOrEqualDateTestBean.class, MustBeSmallerOrEqualIntegerTestBean.class,
      NotEmptyAlternateIfOtherHasValueTestBean.class, NotEmptyAlternateIfOtherIsEmptyTestBean.class,
      NotEmptyAlternateIfOtherIsNotEmptyTestBean.class, NotEmptyIfOtherHasValueTestBean.class,
      NotEmptyIfOtherIsEmptyTestBean.class, NotEmptyIfOtherIsNotEmptyTestBean.class,
      PhoneNumberValueTestBean.class, PostalCodeTestBean.class, TaxNumberTestBean.class,
      TinTestBean.class, VatIdTestBean.class, LevenshteinDistanceTestBean.class})
  public interface GwtGetReflector extends GwtReflectGetterInterface {
  }

  @Override
  public GwtReflectGetterInterface createGwtReflectGetter() {
    return GWT.create(GwtGetReflector.class);
  }
}
