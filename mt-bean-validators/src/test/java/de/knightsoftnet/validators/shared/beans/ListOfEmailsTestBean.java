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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ListOfEmailsTestBean {

  @Valid
  @NotNull
  @Size(min = 1)
  private final List<EmailTestBean> emailList;

  private String path;

  public ListOfEmailsTestBean() {
    super();
    this.emailList = new ArrayList<>();
  }

  public final List<EmailTestBean> getEmailList() {
    return this.emailList;
  }

  public final String getPath() {
    return this.path;
  }

  public final void setPath(final String ppath) {
    this.path = ppath;
  }

  @Override
  public String toString() {
    return "ListOfEmailsTestBean [emailList=" + this.emailList + "]";
  }
}
