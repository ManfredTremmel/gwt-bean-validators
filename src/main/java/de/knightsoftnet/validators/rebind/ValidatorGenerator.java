/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.rebind;

import de.knightsoftnet.validators.client.GwtValidation;
import de.knightsoftnet.validators.client.impl.GwtSpecificValidator;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

import javax.validation.Validator;

/**
 * Generates subclasses of {@link Validator} and {@link GwtSpecificValidator}. The generic validator
 * only handles the classes listed in the {@link de.knightsoftnet.validators.client.GwtValidation}
 * annotation. See {@link de.knightsoftnet.validators.client.GwtValidation} for usage.
 */
public final class ValidatorGenerator extends Generator {

  private final BeanHelperCache cache;
  private Class<?>[] validGroups;

  /**
   * constructor called by the compiler via reflection.
   */
  public ValidatorGenerator() {
    super();
    this.cache = new BeanHelperCache();
    this.validGroups = new Class<?>[] {};
  }

  /**
   * constructor called from tests.
   * 
   * @param cache bean helper cache
   * @param validGroups validation groups
   */
  public ValidatorGenerator(final BeanHelperCache cache, final Class<?>[] validGroups) {
    super();
    this.cache = cache;
    this.validGroups = validGroups;
  }

  @Override
  public String generate(final TreeLogger logger, final GeneratorContext context,
      final String typeName) throws UnableToCompleteException {
    final TypeOracle typeOracle = context.getTypeOracle();
    assert typeOracle != null;

    final JClassType validatorType = this.findType(logger, typeOracle, typeName);
    final JClassType genericType = this.findType(logger, typeOracle, Validator.class.getName());
    final JClassType gwtSpecificType =
        this.findType(logger, typeOracle, GwtSpecificValidator.class.getName());

    if (validatorType.isAssignableTo(genericType)) {
      return this.generateGenericValidator(logger, context, validatorType);
    } else if (validatorType.isAssignableTo(gwtSpecificType)) {
      return this.generateGwtSpecificValidator(logger, context, validatorType);
    } else {
      logger.log(TreeLogger.ERROR,
          "type is not a ValidatorGenerator or GwtSpecificValidatorGenerator: '" + typeName + "'",
          null);
      throw new UnableToCompleteException();
    }
  }

  private JClassType findType(final TreeLogger logger, final TypeOracle typeOracle,
      final String typeName) throws UnableToCompleteException {
    final JClassType result = typeOracle.findType(typeName);
    if (result == null) {
      logger.log(TreeLogger.ERROR, "Unable to find metadata for type '" + typeName + "'", null);
      throw new UnableToCompleteException();
    }
    return result;
  }

  private String generateGenericValidator(final TreeLogger logger, final GeneratorContext context,
      final JClassType validatorType) throws UnableToCompleteException {
    final String typeName = validatorType.getName();

    final GwtValidation gwtValidation =
        validatorType.findAnnotationInTypeHierarchy(GwtValidation.class);

    if (gwtValidation == null) {
      logger.log(TreeLogger.ERROR,
          typeName + " must be anntotated with " + GwtValidation.class.getCanonicalName(), null);
      throw new UnableToCompleteException();
    }

    if (gwtValidation.value().length == 0) {
      logger.log(TreeLogger.ERROR, "The @" + GwtValidation.class.getSimpleName() + "  of "
          + typeName + "must specify at least one bean type to validate.", null);
      throw new UnableToCompleteException();
    }

    if (gwtValidation.groups().length == 0) {
      logger.log(TreeLogger.ERROR, "The @" + GwtValidation.class.getSimpleName() + "  of "
          + typeName + "must specify at least one validation group.", null);
      throw new UnableToCompleteException();
    }

    this.validGroups = gwtValidation.groups();

    final TreeLogger validatorLogger = logger.branch(TreeLogger.DEBUG,
        "Generating Validator for  '" + validatorType.getQualifiedSourceName() + "'", null);
    final AbstractCreator creator =
        new ValidatorCreator(validatorType, gwtValidation, validatorLogger, context, this.cache);
    return creator.create();
  }

  private String generateGwtSpecificValidator(final TreeLogger logger,
      final GeneratorContext context, final JClassType validatorType)
      throws UnableToCompleteException {

    final JClassType gwtSpecificInterface = this.getGwtSpecificValidator(logger, validatorType);
    final JClassType beanType = this.getBeanType(logger, validatorType, gwtSpecificInterface);

    final BeanHelper beanHelper = this.cache.createHelper(beanType, logger, context);

    if (beanHelper == null) {
      logger.log(TreeLogger.ERROR, "Unable to create BeanHelper for " + beanType + " "
          + GwtSpecificValidator.class.getSimpleName() + ".", null);
      throw new UnableToCompleteException();
    }

    final AbstractCreator creator = new GwtSpecificValidatorCreator(validatorType, beanType,
        beanHelper, logger, context, this.cache, this.validGroups);
    return creator.create();
  }

  private JClassType getBeanType(final TreeLogger logger, final JClassType validator,
      final JClassType gwtSpecificInterface) throws UnableToCompleteException {
    if (gwtSpecificInterface instanceof JParameterizedType) {
      final JParameterizedType paramType = (JParameterizedType) gwtSpecificInterface;
      return paramType.getTypeArgs()[0];
    }
    logger.log(TreeLogger.ERROR,
        validator.getQualifiedSourceName() + " must implement "
            + GwtSpecificValidator.class.getCanonicalName() + " with a one generic parameter.",
        null);
    throw new UnableToCompleteException();
  }

  private JClassType getGwtSpecificValidator(final TreeLogger logger, final JClassType validator)
      throws UnableToCompleteException {
    for (final JClassType interfaceType : validator.getImplementedInterfaces()) {
      if (interfaceType.getQualifiedSourceName()
          .endsWith(GwtSpecificValidator.class.getCanonicalName())) {
        return interfaceType;
      }
    }
    logger.log(TreeLogger.ERROR, validator.getQualifiedSourceName() + " must implement "
        + GwtSpecificValidator.class.getCanonicalName(), null);
    throw new UnableToCompleteException();
  }
}
