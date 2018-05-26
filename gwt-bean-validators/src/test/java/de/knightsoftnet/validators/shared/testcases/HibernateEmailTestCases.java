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

import de.knightsoftnet.validators.shared.beans.HibernateEmailTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate email test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateEmailTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateEmailTestBean getEmptyTestBean() {
    return new HibernateEmailTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateEmailTestBean> getCorrectTestBeans() {
    final List<HibernateEmailTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateEmailTestBean("emmanuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emmanuel@hibernate"));
    correctCases.add(new HibernateEmailTestBean("emma-n_uel@hibernate"));
    correctCases.add(new HibernateEmailTestBean("emma+nuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emma=nuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emmanuel@[123.12.2.11]"));
    correctCases.add(new HibernateEmailTestBean("*@example.net"));
    correctCases.add(new HibernateEmailTestBean("fred&barny@example.com"));
    correctCases.add(new HibernateEmailTestBean("---@example.com"));
    correctCases.add(new HibernateEmailTestBean("foo-bar@example.net"));
    correctCases.add(new HibernateEmailTestBean("mailbox.sub1.sub2@this-domain"));
    correctCases.add(new HibernateEmailTestBean("prettyandsimple@example.com"));
    correctCases.add(new HibernateEmailTestBean("very.common@example.com"));
    correctCases.add(new HibernateEmailTestBean("disposable.style.email.with+symbol@example.com"));
    correctCases.add(new HibernateEmailTestBean("other.email-with-dash@example.com"));
    correctCases.add(new HibernateEmailTestBean("x@example.com"));
    correctCases.add(new HibernateEmailTestBean("\"much.more unusual\"@example.com"));
    correctCases.add(new HibernateEmailTestBean("\"very.unusual.@.unusual.com\"@example.com"));
    correctCases.add(new HibernateEmailTestBean(
        "\"very.(),:;<>[]\\\".VERY.\\\"very@\\\\ \\\"very\\\".unusual\"@strange.example.com"));
    correctCases.add(
        new HibernateEmailTestBean("\"some \".\" strange \".\" part*:; \"@strange.example.com"));
    correctCases.add(new HibernateEmailTestBean("example-indeed@strange-example.com"));
    correctCases.add(new HibernateEmailTestBean("admin@mailserver1"));
    correctCases.add(new HibernateEmailTestBean("#!$%&'*+-/=?^_`{}|~@example.org"));
    correctCases
        .add(new HibernateEmailTestBean("\"()<>[]:,;@\\\"!#$%&'-/=?^_`{}| ~.a\"@example.org"));
    correctCases.add(new HibernateEmailTestBean("\" \"@example.org"));
    correctCases.add(new HibernateEmailTestBean("example@localhost"));
    correctCases.add(new HibernateEmailTestBean("example@s.solutions"));
    correctCases.add(new HibernateEmailTestBean("user@localserver"));
    correctCases.add(new HibernateEmailTestBean("user@tt"));
    correctCases.add(new HibernateEmailTestBean("user@[IPv6:2001:DB8::1]"));

    correctCases.add(new HibernateEmailTestBean("Test^Email@example.com"));

    correctCases.add(new HibernateEmailTestBean("myname@östereich.at"));
    correctCases.add(new HibernateEmailTestBean("θσερ@εχαμπλε.ψομ"));

    correctCases.add(new HibernateEmailTestBean(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@hibernate.org"));

    // Domain part should allow up to 255
    for (int length = 1; length <= 251; length++) {
      correctCases.add(new HibernateEmailTestBean("foo@" + domainOfLength(length) + ".com"));
    }

    correctCases.add(new HibernateEmailTestBean(
        "foo@" + stringOfLength(63) + "." + stringOfLength(63) + ".com"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEmailTestBean> getWrongTestBeans() {
    final List<HibernateEmailTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateEmailTestBean("emmanuel.hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma(nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emmanuel@"));
    wrongCases.add(new HibernateEmailTestBean("emma\nnuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma@nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma@nuel@.hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("Just a string"));
    wrongCases.add(new HibernateEmailTestBean("string"));
    wrongCases.add(new HibernateEmailTestBean("me@"));
    wrongCases.add(new HibernateEmailTestBean("@example.com"));
    wrongCases.add(new HibernateEmailTestBean("me.@example.com"));
    wrongCases.add(new HibernateEmailTestBean(".me@example.com"));
    wrongCases.add(new HibernateEmailTestBean("me@example..com"));
    wrongCases.add(new HibernateEmailTestBean("me\\@example.com"));
    wrongCases.add(new HibernateEmailTestBean("Abc.example.com")); // (no @ character)

    // (only one @ is allowed outside quotation marks)
    wrongCases.add(new HibernateEmailTestBean("A@b@c@example.com"));

    // (none of the special characters in this local-part are allowed outside quotation marks)
    wrongCases.add(new HibernateEmailTestBean("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"));

    // (quoted strings must be dot separated or the only element making up the local-part)
    wrongCases.add(new HibernateEmailTestBean("just\"not\"right@example.com"));

    // (spaces, quotes, and backslashes may only exist when within quoted strings and preceded by a
    // backslash)
    wrongCases.add(new HibernateEmailTestBean("this is\"not\\allowed@example.com"));

    // (even if escaped (preceded by a backslash), spaces, quotes, and backslashes must still be
    // contained by quotes)
    wrongCases.add(new HibernateEmailTestBean("this\\ still\\\"not\\\\allowed@example.com"));

    // (double dot before @) with caveat: Gmail lets this through, Email address#Local-part the dots
    // altogether
    wrongCases.add(new HibernateEmailTestBean("john..doe@example.com"));

    wrongCases.add(new HibernateEmailTestBean("john.doe@example..com"));

    wrongCases.add(new HibernateEmailTestBean("θσερ.εχαμπλε.ψομ"));

    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@"));
    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@@"));
    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@@@"));

    // Domain part should allow up to 255
    wrongCases.add(new HibernateEmailTestBean("foo@" + domainOfLength(252) + ".com"));

    return wrongCases;
  }

  private static String stringOfLength(final int length) {
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      builder.append('a');
    }
    return builder.toString();
  }

  private static String domainOfLength(final int length) {
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      // we insert a dot from time to time to be sure each label of the domain name is at most 63
      // characters long
      if (i % 32 == 0 && i > 0 && i < length - 1) {
        builder.append('.');
      } else {
        builder.append('a');
      }
    }
    return builder.toString();
  }
}
