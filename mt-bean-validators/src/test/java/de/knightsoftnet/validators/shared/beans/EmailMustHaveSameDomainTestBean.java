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

import de.knightsoftnet.validators.shared.EmailMustHaveSameDomain;

@EmailMustHaveSameDomain(field1 = "email1", field2 = "email2")
public class EmailMustHaveSameDomainTestBean {

  private final String email1;

  private final String email2;

  /**
   * constructor initializing fields.
   *
   * @param pemail1 first eMail
   * @param pemail2 second eMail
   */
  public EmailMustHaveSameDomainTestBean(final String pemail1, final String pemail2) {
    super();
    this.email1 = pemail1;
    this.email2 = pemail2;
  }

  public final String getEmail1() {
    return this.email1;
  }

  public final String getEmail2() {
    return this.email2;
  }

  @Override
  public String toString() {
    return "EmailMustHaveSameDomainTestBean [email1=" + this.email1 + ", email2=" + this.email2
        + "]";
  }
}
