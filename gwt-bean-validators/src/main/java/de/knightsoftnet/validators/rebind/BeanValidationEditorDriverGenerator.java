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

package de.knightsoftnet.validators.rebind;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.impl.AbstractBeanValidationEditorDelegate;
import de.knightsoftnet.validators.client.editor.impl.AbstractBeanValidationEditorDriver;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.editor.rebind.AbstractEditorDriverGenerator;
import com.google.gwt.editor.rebind.model.EditorData;
import com.google.gwt.editor.rebind.model.EditorModel;
import com.google.gwt.user.rebind.SourceWriter;

import org.apache.commons.lang3.StringUtils;

/**
 * Generates implementations of {@link BeanValidationEditorDriver}.
 */
public class BeanValidationEditorDriverGenerator extends AbstractEditorDriverGenerator {

  private final BeanHelperCache cache;

  public BeanValidationEditorDriverGenerator() {
    this(new BeanHelperCache());
  }

  public BeanValidationEditorDriverGenerator(final BeanHelperCache cache) {
    super();
    this.cache = cache;
  }

  @Override
  protected Class<?> getDriverInterfaceType() {
    return BeanValidationEditorDriver.class;
  }

  @Override
  protected Class<?> getDriverSuperclassType() {
    return AbstractBeanValidationEditorDriver.class;
  }

  @Override
  protected Class<?> getEditorDelegateType() {
    return AbstractBeanValidationEditorDelegate.class;
  }

  @Override
  protected String mutableObjectExpression(final EditorData data,
      final String sourceObjectExpression) {
    return sourceObjectExpression;
  }

  @Override
  protected void writeAdditionalContent(final TreeLogger logger, final GeneratorContext context,
      final EditorModel model, final SourceWriter sw) throws UnableToCompleteException {
    super.writeAdditionalContent(logger, context, model, sw);

    final BeanHelper beanHelper = this.cache.createHelper(model.getProxyType(), logger, context);
    // final String validatorClass =
    // StringUtils.reverse(StringUtils.replace(StringUtils.reverse(modelClass), ".", "_.", 1))
    // + "Validator";

    sw.println("@Override");
    sw.println("protected java.util.Set<javax.validation.ConstraintViolation<"
        + beanHelper.getTypeCanonicalName() + ">> validateContent(final "
        + beanHelper.getTypeCanonicalName() + " pobject, "
        + "final de.knightsoftnet.validators.client.impl.AbstractGwtValidator pvalidator) "
        + "throws java.lang.IllegalArgumentException {");
    sw.indent();
    if (StringUtils.startsWith(beanHelper.getTypeCanonicalName(), "java.")) {
      sw.println("return null;");
    } else {
      // sw.println(
      // "return pvalidator.validate((" + beanHelper.getTypeCanonicalName() + ") pobject,
      // this.validationGroups);");

      // direct call of the validator - fails because validation class is not found
      sw.println("de.knightsoftnet.validators.client.impl.GwtValidationContext<"
          + beanHelper.getTypeCanonicalName() + "> context");
      sw.indent();
      sw.println("= new de.knightsoftnet.validators.client.impl.GwtValidationContext<"
          + beanHelper.getTypeCanonicalName() + ">(");
      sw.println("(Class<" + beanHelper.getTypeCanonicalName() + ">)"
          + beanHelper.getTypeCanonicalName() + ".class,");
      sw.println("pobject,");
      sw.println(beanHelper.getValidatorInstanceName()
          + ".getConstraints(pvalidator.getValidationGroupsMetadata()),");
      sw.println("pvalidator.getMessageInterpolator(),");
      sw.println("pvalidator.getTraversableResolver(),");
      sw.println("pvalidator);");
      sw.outdent();
      sw.println("return " + beanHelper.getValidatorInstanceName() + ".validate(context, ("
          + beanHelper.getTypeCanonicalName() + ") pobject,");
      sw.indent();
      sw.println("this.validationGroups);");
      sw.outdent();
    }
    sw.outdent();
    sw.println("}");
    sw.println();
  }
}
