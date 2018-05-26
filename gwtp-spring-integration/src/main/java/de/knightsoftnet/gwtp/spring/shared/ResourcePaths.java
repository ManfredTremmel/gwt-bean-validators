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

package de.knightsoftnet.gwtp.spring.shared;

public class ResourcePaths {
  public static final String XSRF_COOKIE = "XSRF-TOKEN";
  public static final String XSRF_HEADER = "X-XSRF-TOKEN";

  public static final String API_BASE_DIR = "/api/rest";

  public class User {
    public static final String ROOT = API_BASE_DIR + "/user";
    public static final String LOGIN = "/login";
  }

  public class PhoneNumber {
    public static final String ROOT = API_BASE_DIR + "/phonenumberservice";
    public static final String PARSE_PHONE_NUMBER = "/parsePhoneNumber";
    public static final String PARSE_WITH_POS = "/parsewithpos";
    public static final String PARSE_AND_FORMAT = "/parseandformat";
    public static final String FORMAT_E123 = "/formate123";
    public static final String FORMAT_E123_WITH_POS = "/formate123withpos";
    public static final String FORMAT_E123_INTERNATIONAL = "/formate123international";
    public static final String FORMAT_E123_INTERNATIONAL_WITH_POS =
        "/formate123internationalwithpos";
    public static final String FORMAT_E123_NATIONAL = "/formate123national";
    public static final String FORMAT_E123_NATIONAL_WITH_POS = "/formate123nationalwithpos";
    public static final String FORMAT_DIN5008 = "/formatdin5008";
    public static final String FORMAT_DIN5008_WITH_POS = "/formatdin5008withpos";
    public static final String FORMAT_DIN5008_INTERNATIONAL = "/formatdin5008international";
    public static final String FORMAT_DIN5008_INTERNATIONAL_WITH_POS =
        "/formatdin5008internationalwithpos";
    public static final String FORMAT_DIN5008_NATIONAL = "/formatdin5008national";
    public static final String FORMAT_DIN5008_NATIONAL_WITH_POS = "/formatdin5008nationalwithpos";
    public static final String FORMAT_RFC3966 = "/formatrfc3966";
    public static final String FORMAT_RFC3966_WITH_POS = "/formatrfc3966withpos";
    public static final String FORMAT_MS = "/formatms";
    public static final String FORMAT_MS_WITH_POS = "/formatmswithpos";
    public static final String FORMAT_URL = "/formaturl";
    public static final String FORMAT_URL_WITH_POS = "/formaturlwithpos";
    public static final String FORMAT_COMMON = "/formatcommon";
    public static final String FORMAT_COMMON_WITH_POS = "/formatcommonwithpos";
    public static final String FORMAT_COMMON_INTERNATIONAL = "/formatcommoninternational";
    public static final String FORMAT_COMMON_INTERNATIONAL_WITH_POS =
        "/formatcommoninternationalwithpos";
    public static final String FORMAT_COMMON_NATIONAL = "/formatcommonnational";
    public static final String FORMAT_COMMON_NATIONAL_WITH_POS = "/formatcommonnationalwithpos";
    public static final String GET_SUGGESTIONS = "/getsuggestions";
    public static final String VALIDATE = "/validate";
  }
}
