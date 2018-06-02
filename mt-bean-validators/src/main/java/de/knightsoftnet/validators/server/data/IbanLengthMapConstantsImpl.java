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

package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.IbanLengthDefinition;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IbanLengthMapConstantsImpl implements IbanLengthMapSharedConstants {
  private final Map<String, IbanLengthDefinition> ibanLengthMap;

  /**
   * constructor initializing map.
   *
   * @param pmap map to put
   */
  public IbanLengthMapConstantsImpl(final Map<String, String> pmap) {
    // this.ibanLengthMap = pmap.entrySet().stream().collect(Collectors.toMap( //
    // entry -> entry.getKey(), //
    // entry -> new IbanLengthDefinition(entry.getValue())));
    ibanLengthMap = new HashMap<>(pmap.size());
    for (final Entry<String, String> entry : pmap.entrySet()) {
      ibanLengthMap.put(entry.getKey(), new IbanLengthDefinition(entry.getValue()));
    }
  }

  @Override
  public Map<String, IbanLengthDefinition> ibanLengths() {
    return ibanLengthMap;
  }
}
