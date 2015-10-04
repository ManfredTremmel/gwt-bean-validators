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

package de.knightsoftnet.validators.shared.util;

import org.junit.Assert;
import org.junit.Test;

public class IsbnUtilTest {

  /**
   * test formating isbn10.
   */
  @Test
  public void isbn10Format() {
    Assert.assertNull(IsbnUtil.isbn10Format(null));
    Assert.assertEquals("3-80-770171-0", IsbnUtil.isbn10Format("3807701710"));
    Assert.assertEquals("3-80-770205-9", IsbnUtil.isbn10Format("3807702059"));
    Assert.assertEquals("3-80-770192-3", IsbnUtil.isbn10Format("3807701923"));
    Assert.assertEquals("3-86-640001-2", IsbnUtil.isbn10Format("3866400012"));
    Assert.assertEquals("3-80-770171-0", IsbnUtil.isbn10Format("3807701710"));
  }

  /**
   * test formating isbn13.
   */
  @Test
  public void isbn13Format() {
    Assert.assertNull(IsbnUtil.isbn13Format(null));
    Assert.assertEquals("978-3-83-621802-3", IsbnUtil.isbn13Format("9783836218023"));
    Assert.assertEquals("978-3-83-621507-7", IsbnUtil.isbn13Format("9783836215077"));
    Assert.assertEquals("978-3-89-864471-6", IsbnUtil.isbn13Format("9783898644716"));
  }

  /**
   * test formating isbn.
   */
  @Test
  public void isbnFormat() {
    Assert.assertNull(IsbnUtil.isbnFormat(null));
    Assert.assertEquals("3-80-770171-0", IsbnUtil.isbnFormat("3807701710"));
    Assert.assertEquals("3-80-770205-9", IsbnUtil.isbnFormat("3807702059"));
    Assert.assertEquals("3-80-770192-3", IsbnUtil.isbnFormat("3807701923"));
    Assert.assertEquals("3-86-640001-2", IsbnUtil.isbnFormat("3866400012"));
    Assert.assertEquals("3-80-770171-0", IsbnUtil.isbnFormat("3807701710"));
    Assert.assertEquals("978-3-83-621802-3", IsbnUtil.isbnFormat("9783836218023"));
    Assert.assertEquals("978-3-83-621507-7", IsbnUtil.isbnFormat("9783836215077"));
    Assert.assertEquals("978-3-89-864471-6", IsbnUtil.isbnFormat("9783898644716"));
  }

  /**
   * test compressing isbn.
   */
  @Test
  public void ibanCompress() {
    Assert.assertNull(IsbnUtil.isbnCompress(null));
    Assert.assertEquals("3807701710", IsbnUtil.isbnCompress("3-80-770171-0"));
    Assert.assertEquals("3807702059", IsbnUtil.isbnCompress("3-80-770205-9"));
    Assert.assertEquals("3807701923", IsbnUtil.isbnCompress("3-80-770192-3"));
    Assert.assertEquals("3866400012", IsbnUtil.isbnCompress("3-86-640001-2"));
    Assert.assertEquals("3807701710", IsbnUtil.isbnCompress("3-80-770171-0"));
    Assert.assertEquals("9783836218023", IsbnUtil.isbnCompress("978-3-83-621802-3"));
    Assert.assertEquals("9783836215077", IsbnUtil.isbnCompress("978-3-83-621507-7"));
    Assert.assertEquals("9783898644716", IsbnUtil.isbnCompress("978-3-89-864471-6"));
  }
}
