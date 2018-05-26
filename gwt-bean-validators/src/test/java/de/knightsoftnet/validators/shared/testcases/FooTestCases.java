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

import de.knightsoftnet.validators.shared.beans.Bar;
import de.knightsoftnet.validators.shared.beans.Foo;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for foo test. https://github.com/gwtproject/gwt/issues/7263
 *
 * @author Manfred Tremmel
 *
 */
public class FooTestCases {

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Foo> getCorrectTestBeans() {
    final List<Foo> correctCases = new ArrayList<>();
    final Foo foo = new Foo();
    foo.setOne("one");
    foo.setTwo("two");

    final Bar bar = new Bar();
    bar.setThree("three");
    bar.setFour("four");
    foo.setBar(bar);

    final Bar bar1 = new Bar();
    bar1.setThree("three1");
    bar1.setFour("four1");
    foo.getBarList().add(bar1);

    correctCases.add(foo);
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Foo> getWrongTestBeans() {
    final List<Foo> wrongCases = new ArrayList<>();
    final Foo foo = new Foo();

    final Bar bar = new Bar();
    foo.setBar(bar);

    final Bar bar1 = new Bar();
    foo.getBarList().add(bar1);
    wrongCases.add(foo);
    return wrongCases;
  }
}
