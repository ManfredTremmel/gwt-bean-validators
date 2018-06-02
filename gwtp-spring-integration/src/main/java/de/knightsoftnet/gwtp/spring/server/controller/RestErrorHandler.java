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

package de.knightsoftnet.gwtp.spring.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

import de.knightsoftnet.gwtp.spring.shared.data.ValidationDto;
import de.knightsoftnet.gwtp.spring.shared.data.ValidationResultData;
import de.knightsoftnet.gwtp.spring.shared.data.ValidationResultInterface;

/**
 * This error handler handles validation errors.
 *
 * @author Manfred Tremmel
 */
@ControllerAdvice
public class RestErrorHandler {

  /**
   * handle validation errors.
   *
   * @param pexception exception which is thrown when data is invalid.
   * @return ValidationResultData with validation errors
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationResultInterface processValidationError(
      final MethodArgumentNotValidException pexception) {
    final BindingResult result = pexception.getBindingResult();
    final List<FieldError> fieldErrors = result.getFieldErrors();

    return processFieldErrors(fieldErrors);
  }

  private ValidationResultInterface processFieldErrors(final List<FieldError> pfieldErrors) {
    final ValidationResultInterface validationResult = new ValidationResultData();

    validationResult.getValidationErrorSet()
        .addAll(pfieldErrors.stream().map(
            fieldError -> new ValidationDto(fieldError.getDefaultMessage(), fieldError.getField()))
            .collect(Collectors.toList()));

    return validationResult;
  }
}
