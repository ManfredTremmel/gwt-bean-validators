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

import org.hibernate.validator.constraints.ISBN;

/**
 * test bean with isbn number.
 */
public class HibernateIsbn13TestBean {

  /** isbn. */
  @ISBN(type = ISBN.Type.ISBN_13)
  private final String isbn;

  /**
   * constructor initializing fields.
   *
   * @param pisbn isbn to set
   */
  public HibernateIsbn13TestBean(final String pisbn) {
    super();
    this.isbn = pisbn;
  }

  public final String getIsbn() {
    return this.isbn;
  }

  @Override
  public String toString() {
    return "HibernateIsbn13TestBean [isbn=" + this.isbn + "]";
  }
}
