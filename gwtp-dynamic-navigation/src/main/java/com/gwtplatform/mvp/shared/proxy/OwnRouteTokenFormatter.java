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

package com.gwtplatform.mvp.shared.proxy;

import com.gwtplatform.common.shared.UrlUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeSet;

import javax.inject.Inject;

/**
 * Implementation of {@link TokenFormatter} with support for route like place names.
 * <p>
 * Instead of wiring a hierarchy of several places bound to multiple presenters this implements a
 * flat structure where every history token is bound to a single presenter.
 * </p>
 * <p>
 * Usage:
 * </p>
 * <p>
 * Replace the default binding to {@link ParameterTokenFormatter} with
 * {@link OwnRouteTokenFormatter}. In case you use GWTPs
 * {@link com.gwtplatform.mvp.client.gin.DefaultModule DefaultModule}:
 * </p>
 *
 * <pre>
 * install(new DefaultModule(DefaultPlaceManager.class, RouteTokenFormatter.class));
 * </pre>
 * <p>
 * Now all @NameToken's are treated as routes. Routes are expected to start with an '/' and can
 * contain path parameters as well as query parameters.
 * </p>
 *
 * <pre>
 * {@code @NameToken("/user/{userId}/privacy") // Token for PrivacyPresenter}
 * {@code @NameToken("/user/{userId}/privacy/profile") // Token for PrivacyProfilePresenter}
 * {@code @NameToken("/user/{userId}/privacy/photos") // Token for PrivacyPhotosPresenter}
 * </pre>
 * <p>
 * Static-parts of an route tie stronger than parameter-parts. This way following works:
 * </p>
 *
 * <pre>
 * {@code @NameToken("/{vanityId}") // Token for VanityUrlPresenter}
 * {@code @NameToken("/privacy") // Token for PrivacyPresenter}
 * </pre>
 * <p>
 * Note: For the moment this is implemented on top of the hierarchical-place API to not an big
 * structural changes prior 1.0 release.
 * </p>
 */
public class OwnRouteTokenFormatter implements TokenFormatter {
  /**
   * Helper class to store matches to routes in {@link #toPlaceRequest(String)}.
   */
  private static class RouteMatch implements Comparable<RouteMatch> {
    /**
     * Route/place-token associated to the match.
     */
    final String route;

    /**
     * Number of static matches in this route.
     */
    final int staticMatches;

    /**
     * Parsed parameters of this route.
     */
    Map<String, String> parameters;

    /**
     * Construct a new {@link RouteMatch}.
     *
     * @param route Place token associated to the match.
     * @param staticMatches Number of static matches in this route.
     * @param parameters Parsed parameters of this route.
     */
    RouteMatch(final String route, final int staticMatches, final Map<String, String> parameters) {
      this.route = route;
      this.staticMatches = staticMatches;
      this.parameters = parameters;
    }

    /**
     * Sort {@link RouteMatch}s by the amount of {@link #staticMatches}.
     */
    @Override
    public int compareTo(final RouteMatch other) {
      return Integer.valueOf(staticMatches).compareTo(other.staticMatches);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(staticMatches);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (this.getClass() != obj.getClass()) {
        return false;
      }
      final RouteMatch other = (RouteMatch) obj;
      return Objects.equals(staticMatches, other.staticMatches);
    }
  }

  /**
   * Helper class for parsing and matching place-tokens against routes.
   */
  private class RouteMatcher {
    /**
     * All matching routes of the place-token.
     * <p/>
     * Sorted in ascending order by the number of static matches.
     */
    final TreeSet<RouteMatch> allMatches;

    final String[] placeParts;

    /**
     * Parse and match the given place-token.
     *
     * @param placeToken The place-token.
     */
    RouteMatcher(final String placeToken) {
      assert placeTokenIsValid(placeToken) : "Place-token should start with a '/' or '!/'";
      assert placeToken.indexOf('?') == -1 : "No Query string expected here";

      allMatches = new TreeSet<>();
      placeParts = StringUtils.splitPreserveAllTokens(placeToken, '/');

      for (final String route : allRegisteredPlaceTokens.getAllPlaceTokens()) {
        final RouteMatch match = matchRoute(route);
        if (match != null) {
          allMatches.add(match);
        }
      }
    }

    /**
     * Check if the current place-token matches the given route and return the associated
     * {@link RouteMatch}.
     *
     * @param route The route to check.
     * @return Associated {@link RouteMatch} or <code>null</code> if the place-token doesn't match
     *         the given route.
     */
    final RouteMatch matchRoute(final String route) {
      final String[] routeParts = StringUtils.splitPreserveAllTokens(route, '/');

      if (placeParts.length != routeParts.length) {
        return null;
      }

      if (placeParts.length == 0) {
        assert routeIsEmpty(route);
        return new RouteMatch(route, 0, null);
      }

      final Map<String, String> recordedParameters = new HashMap<>();
      int staticMatches = 0;
      for (int i = 0; i < placeParts.length; i++) {
        if (placeParts[i].equals(routeParts[i])) {
          staticMatches++;
        } else if (routeParts[i].matches("\\{.*\\}")) {
          final String parameterName = routeParts[i].substring(1, routeParts[i].length() - 1);
          recordedParameters.put(parameterName, placeParts[i]);
        } else {
          return null;
        }
      }

      return new RouteMatch(route, staticMatches, recordedParameters);
    }

    private boolean routeIsEmpty(final String route) {
      return "/".equals(route) || "!/".equals(route);
    }
  }

  private final UrlUtils urlUtils;
  private final PlaceTokenRegistry allRegisteredPlaceTokens;

  @Inject
  OwnRouteTokenFormatter(final UrlUtils urlUtils, final PlaceTokenRegistry tokenRegistry) {
    this.urlUtils = urlUtils;
    allRegisteredPlaceTokens = tokenRegistry;
  }

  @Override
  public String toPlaceToken(final PlaceRequest placeRequest) throws TokenFormatException {
    String placeToken = placeRequest.getNameToken();
    final StringBuilder queryStringBuilder = new StringBuilder();
    String querySeparator = StringUtils.EMPTY;

    for (final String parameterName : placeRequest.getParameterNames()) {
      final String parameterValue = placeRequest.getParameter(parameterName, null);
      if (parameterValue != null) {
        final String encodedParameterValue = urlUtils.encodeQueryString(parameterValue);

        if (placeToken.contains("/{" + parameterName + "}")) {
          // route parameter
          placeToken = placeToken.replace("{" + parameterName + "}", encodedParameterValue);
        } else {
          // query parameter
          queryStringBuilder.append(querySeparator).append(parameterName).append('=')
              .append(encodedParameterValue);
          querySeparator = "&";
        }
      }
    }

    final String queryString = queryStringBuilder.toString();
    if (!queryString.isEmpty()) {
      placeToken = placeToken + "?" + queryString; // NOPMD
    }

    return placeToken;
  }

  @Override
  public String toHistoryToken(final List<PlaceRequest> placeRequestHierarchy)
      throws TokenFormatException {
    assert !placeRequestHierarchy.isEmpty() : "Expected a place hierarchy with one or more places.";

    return toPlaceToken(placeRequestHierarchy.get(placeRequestHierarchy.size() - 1));
  }

  @Override
  public PlaceRequest toPlaceRequest(final String placeToken) throws TokenFormatException {
    /*
     * To support the native GWT history as well as HTML pushstate a slash is added when needed.
     */
    if (!placeTokenIsValid(placeToken)) {
      return toPlaceRequest("/" + placeToken);
    }

    final int split = placeToken.indexOf('?');
    final String place = (split == -1) ? placeToken : placeToken.substring(0, split);
    final String query = (split == -1) ? StringUtils.EMPTY : placeToken.substring(split + 1);

    final RouteMatcher matcher = new RouteMatcher(place);
    final RouteMatch match =
        matcher.allMatches.isEmpty() ? new RouteMatch(place, 0, null) : matcher.allMatches.last();

    match.parameters = decodeEmbeddedParams(match.parameters);
    match.parameters = parseQueryString(query, match.parameters);

    return new PlaceRequest.Builder().nameToken(match.route).with(match.parameters).build();
  }

  private Map<String, String> decodeEmbeddedParams(final Map<String, String> parameters) {
    if (parameters != null) {
      for (final Entry<String, String> entry : parameters.entrySet()) {
        entry.setValue(urlUtils.decodeQueryString(entry.getValue()));
      }
    }
    return parameters;
  }

  @Override
  public List<PlaceRequest> toPlaceRequestHierarchy(final String historyToken)
      throws TokenFormatException {
    final List<PlaceRequest> result = new ArrayList<>();
    result.add(toPlaceRequest(historyToken));

    return result;
  }

  /**
   * Parse the given query-string and store all parameters into a map.
   *
   * @param queryString The query-string.
   * @param into The map to use. If the given map is <code>null</code> a new map will be created.
   * @return A map containing all keys value pairs of the query-string.
   */
  Map<String, String> parseQueryString(final String queryString, final Map<String, String> into) {
    final Map<String, String> result = (into == null) ? new HashMap<>() : into;

    if (StringUtils.isNotEmpty(queryString)) {
      for (final String keyValuePair : queryString.split("&")) {
        final String[] keyValue = keyValuePair.split("=", 2);
        if (keyValue.length > 1) {
          result.put(keyValue[0], urlUtils.decodeQueryString(keyValue[1]));
        } else {
          result.put(keyValue[0], StringUtils.EMPTY);
        }
      }
    }

    return result;
  }

  private boolean placeTokenIsValid(final String placeToken) {
    return placeToken.startsWith("/") || placeToken.startsWith("!/"); // NOPMD
  }
}
