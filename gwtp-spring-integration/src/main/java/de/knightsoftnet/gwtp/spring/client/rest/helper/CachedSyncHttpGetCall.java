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

package de.knightsoftnet.gwtp.spring.client.rest.helper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gwt.core.shared.GWT;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import elemental.client.Browser;
import elemental.xml.XMLHttpRequest;

/**
 * helper class for synchronous rest get call with result caching.
 *
 * @author Manfred Tremmel
 */
public final class CachedSyncHttpGetCall {

  /**
   * cache map.
   */
  private static final LoadingCache<String, String> CACHE =
      CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(10, TimeUnit.DAYS)
          .build(new CacheLoader<String, String>() {
            @Override
            public String load(final String pkey) {
              return CachedSyncHttpGetCall.syncRestNativeCall(pkey);
            }
          });

  /**
   * private default constructor.
   */
  private CachedSyncHttpGetCall() {
    super();
  }

  /**
   * start get rest call with given url.
   *
   * @param purl url to read
   * @return result of the call, has to be interpreted
   */
  public static String syncRestCall(final String purl) {
    try {
      return CACHE.get(purl);
    } catch (final ExecutionException e) {
      GWT.log(e.getMessage(), e);
      return null;
    }
  }

  // simple synchronous rest get call
  private static String syncRestNativeCall(final String purl) {
    final XMLHttpRequest xmlHttp = Browser.getWindow().newXMLHttpRequest();
    xmlHttp.open("GET", purl, false); // false for synchronous request
    xmlHttp.send();
    return xmlHttp.getResponseText();
  }
}
