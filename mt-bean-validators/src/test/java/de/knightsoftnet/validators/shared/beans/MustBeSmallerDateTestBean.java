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

import de.knightsoftnet.validators.shared.MustBeSmaller;

import java.util.Date;

@MustBeSmaller(field1 = "from", field2 = "to")
public class MustBeSmallerDateTestBean {

  private final Date from;

  private final Date to;

  /**
   * constructor initializing fields.
   *
   * @param pfrom from entry
   * @param pto to entry
   */
  public MustBeSmallerDateTestBean(final Date pfrom, final Date pto) {
    super();
    this.from = pfrom;
    this.to = pto;
  }

  public final Date getFrom() {
    return this.from;
  }

  public final Date getTo() {
    return this.to;
  }

  @Override
  public String toString() {
    return "MustBeSmallerDateTestBean [from=" + this.from + ", to=" + this.to + "]";
  }
}
