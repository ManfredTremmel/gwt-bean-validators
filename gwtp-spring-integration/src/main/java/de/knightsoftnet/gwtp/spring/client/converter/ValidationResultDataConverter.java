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

package de.knightsoftnet.gwtp.spring.client.converter;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.convert.converter.Converter;

import javax.validation.ConstraintViolation;

import java.util.Collections;
import java.util.stream.Collectors;

import de.knightsoftnet.gwtp.spring.shared.data.ValidationResultInterface;

/**
 * convert ValidationResultData from server to a ArrayList&lt;ConstraintViolation&lt;?&gt;&gt; which
 * can be handled by gwt.
 *
 * @author Manfred Tremmel
 *
 * @param <E> validation type
 */
public class ValidationResultDataConverter<E>
    implements Converter<ValidationResultInterface, Iterable<ConstraintViolation<?>>> {

  @Override
  public Iterable<ConstraintViolation<?>> convert(final ValidationResultInterface psource) {
    return this.convert(psource, null);
  }

  /**
   * convert ValidationResultData from server to a ArrayList&lt;ConstraintViolation&lt;?&gt;&gt;
   * which can be handled by gwt.
   *
   * @param psource ValidationResultData to convert
   * @param pbean the validated bean (which is not transfered back to client)
   * @return ArrayList&lt;ConstraintViolation&lt;?&gt;&gt;
   */
  @SuppressWarnings("unchecked")
  public Iterable<ConstraintViolation<?>> convert(final ValidationResultInterface psource,
      final E pbean) {
    if (psource == null) {
      return null;
    }
    return psource.getValidationErrorSet().stream()
        .map(violation -> ConstraintViolationImpl.forBeanValidation( //
            null, //
            Collections.emptyMap(), //
            Collections.emptyMap(), //
            violation.getMessage(), //
            (Class<E>) (pbean == null ? null : pbean.getClass()), //
            pbean, //
            null, //
            null, //
            PathImpl.createPathFromString(violation.getPropertyPath()), //
            null, //
            null, //
            null))
        .collect(Collectors.toList());
  }
}
