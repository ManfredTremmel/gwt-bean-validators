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

package de.knightsoftnet.validators.shared.beans;

import de.knightsoftnet.validators.shared.MustBeEqual;
import de.knightsoftnet.validators.shared.MustNotBeEqual;

@MustNotBeEqual(field1 = "passwordOld", field2 = "passwordNew")
@MustBeEqual(field1 = "passwordNew", field2 = "passwordNewRepeat")
public class MustBeEqualTestBean {

  private final String passwordOld;

  private final String passwordNew;

  private final String passwordNewRepeat;

  /**
   * constructor initializing fields.
   *
   * @param ppasswordOld old password
   * @param ppasswordNew new password
   * @param ppasswordNewRepeat new password repeated
   */
  public MustBeEqualTestBean(final String ppasswordOld, final String ppasswordNew,
      final String ppasswordNewRepeat) {
    super();
    this.passwordOld = ppasswordOld;
    this.passwordNew = ppasswordNew;
    this.passwordNewRepeat = ppasswordNewRepeat;
  }

  public final String getPasswordOld() {
    return this.passwordOld;
  }

  public final String getPasswordNew() {
    return this.passwordNew;
  }

  public final String getPasswordNewRepeat() {
    return this.passwordNewRepeat;
  }

  @Override
  public String toString() {
    return "MustBeEqualTestBean [passwordOld=" + this.passwordOld + ", passwordNew="
        + this.passwordNew + ", passwordNewRepeat=" + this.passwordNewRepeat + "]";
  }
}
