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

import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * suggest oracle of phone number suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberOracle<T extends AbstractPhoneNumberItemSuggest>
    extends SuggestOracle {

  /**
   * class type.
   */
  protected final Class<T> type;

  /**
   * default limit suggests.
   */
  protected static final int LIMIT_DEFAULT = 20;

  /**
   * constructor initializing with suggestion type.
   *
   * @param ptype class type
   */
  public AbstractPhoneNumberOracle(final Class<T> ptype) {
    super();
    this.type = ptype;
  }

  @Override
  public final boolean isDisplayStringHTML() {
    return true;
  }

  @Override
  public abstract void requestSuggestions(final Request prequest, final Callback pcallback);

  protected int getLimit(final Request prequest) {
    final int limit;
    if (prequest.getLimit() > 0) {
      limit = prequest.getLimit();
    } else {
      limit = AbstractPhoneNumberOracle.LIMIT_DEFAULT;
    }
    return limit;
  }

  /**
   * <code>createInstance</code> creates a new Instance.
   *
   * @param pentry phone number data to create instance for
   * @return T
   */
  protected abstract T createInstance(final PhoneNumberData pentry);

  /**
   * <code>createInstance</code> creates a new Instance.
   *
   * @param pentry entry to get suggestion for
   * @return true if suggest call should be done
   */
  protected abstract boolean needSuggest(final String pentry);
}
