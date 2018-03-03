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
import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;
import de.knightsoftnet.validators.client.impl.GwtBeanDescriptor;
import de.knightsoftnet.validators.client.impl.GwtSpecificValidator;
import de.knightsoftnet.validators.client.impl.GwtValidationContext;
import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.groups.Default;
import javax.validation.metadata.BeanDescriptor;

/**
 * Creates the validator for the given input class.
 */
public final class ValidatorCreator extends AbstractCreator {

  /**
   * The beans to validate in source declaration order.
   */
  private final ImmutableList<BeanHelper> beansToValidate;
  private final GwtValidation gwtValidation;

  /**
   * constructor.
   */
  public ValidatorCreator(final JClassType validatorType, //
      final GwtValidation gwtValidation, //
      final TreeLogger logger, //
      final GeneratorContext context, final BeanHelperCache cache)
      throws UnableToCompleteException {
    super(context, logger, validatorType, cache);
    this.gwtValidation = gwtValidation;

    final List<BeanHelper> temp = Lists.newArrayList();
    for (final Class<?> clazz : gwtValidation.value()) {
      final BeanHelper helper = this.createBeanHelper(clazz);
      temp.add(helper);
    }
    this.beansToValidate = Util.sortMostSpecificFirst(temp, BeanHelper.TO_CLAZZ);
  }

  @Override
  protected void compose(final ClassSourceFileComposerFactory composerFactory) {
    this.addImports(composerFactory, GWT.class, GwtBeanDescriptor.class, GwtSpecificValidator.class,
        GwtValidationContext.class, ValidationGroupsMetadata.class, Set.class, HashSet.class,
        Map.class, HashMap.class, Default.class, ConstraintViolation.class, BeanDescriptor.class);
    composerFactory.setSuperclass(AbstractGwtValidator.class.getCanonicalName());
    composerFactory.addImplementedInterface(this.validatorType.getQualifiedSourceName());
  }

  @Override
  protected void writeClassBody(final SourceWriter sourceWriter) {
    this.writeConstructor(sourceWriter);
    sourceWriter.println();
    this.writeCreateValidationGroupsMetadata(sourceWriter);
    sourceWriter.println();
    this.writeValidate(sourceWriter);
    sourceWriter.println();
    this.writeValidateProperty(sourceWriter);
    sourceWriter.println();
    this.writeValidateValue(sourceWriter);
    sourceWriter.println();
    this.writeGetConstraintsForClass(sourceWriter);
    sourceWriter.println();
    this.writeGwtValidate(sourceWriter);
  }

  private void writeConstructor(final SourceWriter sw) {
    // public MyValidator() {
    sw.println("public " + this.getSimpleName() + "() {");
    sw.indent();

    // super(createValidationGroupsMetadata());
    sw.println("super(createValidationGroupsMetadata());");

    sw.outdent();
    sw.println("}");
  }

  private void writeContext(final SourceWriter sw, final BeanHelper bean, final String objectName) {
    // GwtValidationContext<MyBean> context = new GwtValidationContext<MyBean>(
    sw.print(GwtValidationContext.class.getSimpleName());
    sw.print("<T> context = new ");
    sw.print(GwtValidationContext.class.getSimpleName());
    sw.println("<T>(");
    sw.indent();
    sw.indent();

    // (Class<T>) MyBean.class,
    sw.print("(Class<T>) ");
    sw.println(bean.getTypeCanonicalName() + ".class, ");

    // object,
    sw.println(objectName + ", ");

    // MyBeanValidator.INSTANCE.getConstraints(getValidationGroupsMetadata()),
    sw.print(bean.getFullyQualifiedValidatorName());
    sw.println(".INSTANCE.getConstraints(getValidationGroupsMetadata()), ");

    // getMessageInterpolator(),
    sw.println("getMessageInterpolator(), ");

    // getTraversableResolver(),
    sw.println("getTraversableResolver(), ");

    // this);
    sw.println("this);");
    sw.outdent();
    sw.outdent();
  }

  private void writeCreateValidationGroupsMetadata(final SourceWriter sw) {
    // private static ValidationGroupsMetadata createValidationGroupsMetadata() {
    sw.println("private static ValidationGroupsMetadata createValidationGroupsMetadata() {");
    sw.indent();

    // return ValidationGroupsMetadata.builder()
    sw.println("return ValidationGroupsMetadata.builder()");
    sw.indent();
    sw.indent();
    for (final Class<?> group : this.gwtValidation.groups()) {
      final GroupSequence sequenceAnnotation = group.getAnnotation(GroupSequence.class);
      Class<?>[] groups;
      if (sequenceAnnotation == null) {
        // .addGroup(<<group>>
        sw.print(".addGroup(");
        sw.print(group.getCanonicalName() + ".class");
        groups = group.getInterfaces();
      } else {
        // .addSequence(<<sequence>>
        sw.print(".addSequence(");
        sw.print(group.getCanonicalName() + ".class");
        groups = sequenceAnnotation.value();
      }
      for (final Class<?> clazz : groups) {
        // , <<group class>>
        sw.print(", ");
        sw.print(clazz.getCanonicalName() + ".class");
      }
      // )
      sw.println(")");
    }

    // .build();
    sw.println(".build();");
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeGetConstraintsForClass(final SourceWriter sw) {
    // public BeanDescriptor getConstraintsForClass(Class<?> clazz {
    sw.println("public BeanDescriptor getConstraintsForClass(Class<?> clazz) {");
    sw.indent();

    // checkNotNull(clazz, "clazz");
    sw.println("checkNotNull(clazz, \"clazz\");");

    for (final BeanHelper bean : this.beansToValidate) {
      this.writeGetConstraintsForClass(sw, bean);
    }

    this.writeThrowIllegalArgumnet(sw, "clazz.getName()");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeGetConstraintsForClass(final SourceWriter sw, final BeanHelper bean) {
    // if (clazz.eqals(MyBean.class)) {
    sw.println("if (clazz.equals(" + bean.getTypeCanonicalName() + ".class)) {");
    sw.indent();

    // return MyBeanValidator.INSTANCE.getConstraints(getValidationGroupsMetadata());
    sw.print("return ");
    sw.print(bean.getFullyQualifiedValidatorName());
    sw.println(".INSTANCE.getConstraints(getValidationGroupsMetadata());");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeGwtValidate(final SourceWriter sw) {
    // public <T> Set<ConstraintViolation<T>> validate(GwtValidationContext<T>
    // context,
    sw.print("public <T> Set<ConstraintViolation<T>> ");
    sw.println("validate(GwtValidationContext<T> context,");
    sw.indent();
    sw.indent();

    // Object object, Class<?>... groups) {
    sw.println("Object object, Class<?>... groups) {");
    sw.outdent();

    sw.println("checkNotNull(context, \"context\");");
    sw.println("checkNotNull(object, \"object\");");
    sw.println("checkNotNull(groups, \"groups\");");
    sw.println("checkGroups(groups);");

    for (final BeanHelper bean : this.beansToValidate) {
      this.writeGwtValidate(sw, bean);
    }

    // TODO(nchalko) log warning instead.
    this.writeThrowIllegalArgumnet(sw, "object.getClass().getName()");

    sw.outdent();
    sw.println("}");
  }

  private void writeGwtValidate(final SourceWriter sw, final BeanHelper bean) {
    this.writeIfInstanceofBeanType(sw, bean);
    sw.indent();

    // return PersonValidator.INSTANCE

    sw.print("return ");
    sw.println(bean.getFullyQualifiedValidatorName() + ".INSTANCE");
    sw.indent();
    sw.indent();
    // .validate(context, (<<MyBean>>) object, groups);
    sw.print(".validate(context, ");
    sw.print("(" + bean.getTypeCanonicalName() + ") object, ");
    sw.println("groups);");
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeIfInstanceofBeanType(final SourceWriter sourceWriter, final BeanHelper bean) {
    // if (object instanceof MyBean) {
    sourceWriter.print("if (object instanceof ");
    sourceWriter.print(bean.getTypeCanonicalName());
    sourceWriter.println(") {");
  }

  private void writeThrowIllegalArgumnet(final SourceWriter sourceWriter,
      final String getClassName) {
    // throw new IllegalArgumentException("MyValidator can not validate ",
    sourceWriter.print("throw new IllegalArgumentException(\"");
    sourceWriter.print(this.validatorType.getName() + " can not  validate \"");
    sourceWriter.indent();
    sourceWriter.indent();

    // + object.getClass().getName() +". "
    sourceWriter.print("+ ");
    sourceWriter.print(getClassName);
    sourceWriter.println("+ \". \"");

    // + "Valid values are {Foo.clas, Bar.class}
    sourceWriter.print("+ \"Valid types are ");
    sourceWriter.print(this.beansToValidate.toString());
    sourceWriter.println("\");");
    sourceWriter.outdent();
    sourceWriter.outdent();
  }

  private void writeValidate(final SourceWriter sw) {
    // public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>...
    // groups) {
    sw.println("public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {");
    sw.indent();

    sw.println("checkNotNull(object, \"object\");");
    sw.println("checkNotNull(groups, \"groups\");");
    sw.println("checkGroups(groups);");

    for (final BeanHelper bean : this.beansToValidate) {
      this.writeValidate(sw, bean);
    }

    this.writeThrowIllegalArgumnet(sw, "object.getClass().getName()");

    sw.outdent();
    sw.println("}");
  }

  private void writeValidate(final SourceWriter sw, final BeanHelper bean) {
    this.writeIfInstanceofBeanType(sw, bean);
    sw.indent();

    this.writeContext(sw, bean, "object");

    // return PersonValidator.INSTANCE
    sw.print("return ");
    sw.println(bean.getFullyQualifiedValidatorName() + ".INSTANCE");
    sw.indent();
    sw.indent();

    // .validate(context, (<<MyBean>>) object, groups);
    sw.print(".validate(context, ");
    sw.print("(" + bean.getTypeCanonicalName() + ") object, ");
    sw.println("groups);");
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateProperty(final SourceWriter sw) {
    sw.println("public <T> Set<ConstraintViolation<T>> validateProperty("
        + "T object,String propertyName, Class<?>... groups) {");
    sw.indent();

    sw.println("checkNotNull(object, \"object\");");
    sw.println("checkNotNull(propertyName, \"propertyName\");");
    sw.println("checkNotNull(groups, \"groups\");");
    sw.println("checkGroups(groups);");

    for (final BeanHelper bean : this.beansToValidate) {
      this.writeValidateProperty(sw, bean);
    }

    this.writeThrowIllegalArgumnet(sw, "object.getClass().getName()");

    sw.outdent();
    sw.println("}");
  }

  private void writeValidateProperty(final SourceWriter sw, final BeanHelper bean) {
    this.writeIfInstanceofBeanType(sw, bean);
    sw.indent();
    this.writeContext(sw, bean, "object");

    // return PersonValidator.INSTANCE
    sw.print("return ");
    sw.println(bean.getFullyQualifiedValidatorName() + ".INSTANCE");
    sw.indent();
    sw.indent();

    // .validateProperty(context, (MyBean) object, propertyName, groups);
    sw.print(".validateProperty(context, (");
    sw.print(bean.getTypeCanonicalName());
    sw.print(") object, propertyName, ");
    sw.println("groups);");
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateValue(final SourceWriter sw) {
    sw.println("public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, "
        + "String propertyName, Object value, Class<?>... groups) {");
    sw.indent();

    sw.println("checkNotNull(beanType, \"beanType\");");
    sw.println("checkNotNull(propertyName, \"propertyName\");");
    sw.println("checkNotNull(groups, \"groups\");");
    sw.println("checkGroups(groups);");

    for (final BeanHelper bean : this.beansToValidate) {
      this.writeValidateValue(sw, bean);
    }

    this.writeThrowIllegalArgumnet(sw, "beanType.getName()");

    sw.outdent();
    sw.println("}");
  }

  private void writeValidateValue(final SourceWriter sw, final BeanHelper bean) {
    sw.println("if (beanType.equals(" + bean.getTypeCanonicalName() + ".class)) {");
    sw.indent();
    this.writeContext(sw, bean, "null");

    // return PersonValidator.INSTANCE
    sw.print("return ");
    sw.println(bean.getFullyQualifiedValidatorName() + ".INSTANCE");
    sw.indent();
    sw.indent();

    // .validateValue(context, (Class<MyBean> beanType, propertyName, value,
    // groups);
    sw.print(".validateValue(context, (Class<");
    sw.print(bean.getTypeCanonicalName());
    sw.println(">)beanType, propertyName, value, groups);");
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");
  }
}
