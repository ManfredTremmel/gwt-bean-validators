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

import de.knightsoftnet.validators.shared.LevenshteinDistance;

@LevenshteinDistance(field1 = "user", field2 = "password", minDistance = 2)
public class LevenshteinDistanceTestBean {

  private final String user;

  private final String password;

  /**
   * constructor initializing fields.
   *
   * @param puser user to set
   * @param ppassword password to set
   */
  public LevenshteinDistanceTestBean(final String puser, final String ppassword) {
    super();
    this.user = puser;
    this.password = ppassword;
  }

  public String getUser() {
    return this.user;
  }

  public String getPassword() {
    return this.password;
  }

  @Override
  public String toString() {
    return "LevenshteinDistanceTestBean [user=" + this.user + ", password=" + this.password + "]";
  }
}
