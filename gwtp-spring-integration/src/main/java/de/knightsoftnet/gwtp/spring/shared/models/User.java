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

/**
 * The <code>User</code> interface defines the minimum data of a user.
 *
 * @author Manfred Tremmel
 */
public interface User {

  /**
   * set the user name.
   *
   * @param puserName user name
   */
  void setUserName(String puserName);

  /**
   * get the user name.
   *
   * @return user name
   */
  String getUserName();

  /**
   * check if user is logged in.
   *
   * @return true if we have a logged in user
   */
  boolean isLoggedIn();
}
