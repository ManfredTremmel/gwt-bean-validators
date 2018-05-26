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
import de.knightsoftnet.validators.shared.beans.BarBar;
import de.knightsoftnet.validators.shared.beans.FooFoo;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for fooFoo test. https://github.com/gwtproject/gwt/issues/7263
 *
 * @author Manfred Tremmel
 *
 */
public class FooFooTestCases {

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<FooFoo> getCorrectTestBeans() {
    final List<FooFoo> correctCases = new ArrayList<>();
    final FooFoo fooFoo = new FooFoo();
    fooFoo.setOne("one");
    fooFoo.setTwo("two");

    final Bar bar = new Bar();
    bar.setThree("three");
    bar.setFour("four");
    fooFoo.setBar(bar);

    final Bar bar1 = new Bar();
    bar1.setThree("three1");
    bar1.setFour("four1");
    fooFoo.getBarList().add(bar1);

    final BarBar barbar = new BarBar();
    barbar.setThree("three2");
    barbar.setFour("four2");
    barbar.setBarbarone("barbarone");
    fooFoo.setBarBar(barbar);

    final BarBar barbar1 = new BarBar();
    barbar1.setThree("three3");
    barbar1.setFour("four3");
    barbar1.setBarbarone("barbarone1");
    fooFoo.getBarBarList().add(barbar1);

    correctCases.add(fooFoo);
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<FooFoo> getWrongTestBeans() {
    final List<FooFoo> wrongCases = new ArrayList<>();
    final FooFoo fooFoo = new FooFoo();

    final Bar bar = new Bar();
    fooFoo.setBar(bar);

    final Bar bar1 = new Bar();
    fooFoo.getBarList().add(bar1);

    final BarBar barbar = new BarBar();
    fooFoo.setBarBar(barbar);

    final BarBar barbar1 = new BarBar();
    fooFoo.getBarBarList().add(barbar1);
    wrongCases.add(fooFoo);
    return wrongCases;
  }
}
