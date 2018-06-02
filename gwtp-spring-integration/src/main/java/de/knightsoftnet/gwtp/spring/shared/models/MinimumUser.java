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

package de.knightsoftnet.gwtp.spring.shared.models;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * The <code>UserData</code> class implements contains the data of a user.
 *
 * @author Manfred Tremmel
 */
public class MinimumUser implements User, Serializable {

  private static final long serialVersionUID = -6675680956690135567L;

  /**
   * login name of the user.
   */
  private String userName;

  /**
   * default constructor.
   */
  public MinimumUser() {
    this(null);
  }

  /**
   * constructor initializing key field.
   *
   * @param puserName user to set
   */
  public MinimumUser(final String puserName) {
    super();
    setUserName(puserName);
  }

  @Override
  public final String getUserName() {
    return userName;
  }

  @Override
  public final void setUserName(final String puserName) {
    userName = puserName;
  }

  @Override
  public boolean isLoggedIn() {
    return StringUtils.isNotEmpty(userName);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(userName);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final MinimumUser other = (MinimumUser) obj;
    return StringUtils.equals(userName, other.userName);
  }
}
