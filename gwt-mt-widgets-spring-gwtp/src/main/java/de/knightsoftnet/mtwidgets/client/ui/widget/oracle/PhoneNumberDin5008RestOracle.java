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

package de.knightsoftnet.mtwidgets.client.ui.widget.oracle;

import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.validators.client.services.PhoneNumberRestService;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import com.gwtplatform.dispatch.rest.client.RestDispatch;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * suggest oracle of phone number suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberDin5008RestOracle
    extends AbstractPhoneNumberRestOracle<PhoneNumberDin5008ItemSuggest> {

  /**
   * default constructor.
   */
  @Inject
  public PhoneNumberDin5008RestOracle(final RestDispatch pdispatcher,
      final PhoneNumberRestService pservice, final Session psession) {
    super(PhoneNumberDin5008ItemSuggest.class, pdispatcher, pservice, psession);
  }

  @Override
  protected PhoneNumberDin5008ItemSuggest createInstance(final PhoneNumberData pentry) {
    return new PhoneNumberDin5008ItemSuggest(pentry.getCountryCode(), pentry.getCountryName(),
        pentry.getAreaCode(), pentry.getAreaName());
  }

  @Override
  protected boolean needSuggest(final String pentry) {
    return StringUtils.isNotEmpty(pentry)
        && (pentry.charAt(0) != '0' && StringUtils.countMatches(pentry, " ") < 2
            || pentry.charAt(0) == '0' && !StringUtils.contains(pentry, ' '));
  }
}
