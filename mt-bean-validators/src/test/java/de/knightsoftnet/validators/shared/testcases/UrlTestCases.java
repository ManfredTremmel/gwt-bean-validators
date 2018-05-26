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

package de.knightsoftnet.validators.shared.testcases;

import de.knightsoftnet.validators.shared.beans.UrlTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for url test.
 *
 * @author Manfred Tremmel
 *
 */
public class UrlTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final UrlTestBean getEmptyTestBean() {
    return new UrlTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<UrlTestBean> getCorrectTestBeans() {
    final List<UrlTestBean> correctCases = new ArrayList<UrlTestBean>();
    correctCases.add(new UrlTestBean("http://www.google.com"));
    correctCases.add(new UrlTestBean("http://www.google.com/"));
    correctCases.add(new UrlTestBean("http://tech.yahoo.com/rc/desktops/102;_ylt="
        + "Ao8yevQHlZ4On0O3ZJGXLEQFLZA5"));
    correctCases.add(new UrlTestBean("https://blah.gov/blah-blah.as"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<UrlTestBean> getWrongTestBeans() {
    final List<UrlTestBean> wrongCases = new ArrayList<UrlTestBean>();
    wrongCases.add(new UrlTestBean("tz:\\temp\\ fi*le?na:m<e>.doc"));
    wrongCases.add(new UrlTestBean("http://www.blah&quot;blah.com/I have spaces!"));
    wrongCases.add(new UrlTestBean("(http://www.krumedia.com)"));
    return wrongCases;
  }
}
