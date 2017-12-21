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

import de.knightsoftnet.validators.client.impl.AbstractGwtSpecificValidator;
import de.knightsoftnet.validators.client.impl.ConstraintDescriptorImpl;
import de.knightsoftnet.validators.client.impl.ConstraintOrigin;
import de.knightsoftnet.validators.client.impl.Group;
import de.knightsoftnet.validators.client.impl.GroupChain;
import de.knightsoftnet.validators.client.impl.GroupChainGenerator;
import de.knightsoftnet.validators.client.impl.GwtBeanDescriptor;
import de.knightsoftnet.validators.client.impl.GwtBeanDescriptorImpl;
import de.knightsoftnet.validators.client.impl.GwtValidationContext;
import de.knightsoftnet.validators.client.impl.PropertyDescriptorImpl;
import de.knightsoftnet.validators.client.impl.metadata.BeanMetadata;
import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.UnsafeNativeLong;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.dev.jjs.ast.JBooleanLiteral;
import com.google.gwt.dev.jjs.ast.JCharLiteral;
import com.google.gwt.dev.jjs.ast.JDoubleLiteral;
import com.google.gwt.dev.jjs.ast.JFloatLiteral;
import com.google.gwt.dev.jjs.ast.JIntLiteral;
import com.google.gwt.dev.jjs.ast.JLongLiteral;
import com.google.gwt.thirdparty.guava.common.base.Function;
import com.google.gwt.thirdparty.guava.common.base.Functions;
import com.google.gwt.thirdparty.guava.common.base.Joiner;
import com.google.gwt.thirdparty.guava.common.base.Predicate;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableSet;
import com.google.gwt.thirdparty.guava.common.collect.Iterables;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.google.gwt.thirdparty.guava.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.Sets;
import com.google.gwt.thirdparty.guava.common.primitives.Primitives;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.Path.Node;
import javax.validation.Payload;
import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

/**
 * Creates a {@link de.knightsoftnet.validators.client.impl.GwtSpecificValidator}.
 * <p>
 * This class is not thread safe.
 * </p>
 */
@SuppressWarnings("checkstyle:linelength")
public final class GwtSpecificValidatorCreator extends AbstractCreator {
  private enum Stage {
    OBJECT, PROPERTY, VALUE
  }

  static final JType[] NO_ARGS = new JType[] {};

  private static final String DEFAULT_VIOLATION_VAR = "violations";

  private static final Annotation[] NO_ANNOTATIONS = new Annotation[] {};

  private static final Function<java.beans.PropertyDescriptor, String> PROPERTY_DESCRIPTOR_TO_NAME =
      pd -> pd == null ? null : pd.getName();

  private static final Function<Object, String> TO_LITERAL =
      input -> input == null ? null : asLiteral(input);

  public static String asGetter(final PropertyDescriptor propertyDescriptor) {
    return "get" + StringUtils.capitalize(propertyDescriptor.getPropertyName());
  }

  /**
   * Returns the literal value of an object that is suitable for inclusion in Java Source code.
   *
   * <p>
   * Supports all types that {@link Annotation} value can have.
   * </p>
   *
   *
   * @throws IllegalArgumentException if the type of the object does not have a java literal form.
   */
  public static String asLiteral(final Object value) throws IllegalArgumentException {
    final Class<?> clazz = value.getClass();

    if (clazz.isArray()) {
      final StringBuilder sb = new StringBuilder();
      final Object[] array = (Object[]) value;

      sb.append("new " + clazz.getComponentType().getCanonicalName() + "[] {");
      boolean first = true;
      for (final Object object : array) {
        if (first) {
          first = false;
        } else {
          sb.append(',');
        }
        sb.append(asLiteral(object));
      }
      sb.append('}');
      return sb.toString();
    }

    if (value instanceof Boolean) {
      return JBooleanLiteral.get(((Boolean) value).booleanValue()).toSource();
    } else if (value instanceof Byte) {
      return JIntLiteral.get(((Byte) value).byteValue()).toSource();
    } else if (value instanceof Character) {
      return JCharLiteral.get(((Character) value).charValue()).toSource();
    } else if (value instanceof Class<?>) {
      return ((Class<?>) (Class<?>) value).getCanonicalName() + ".class";
    } else if (value instanceof Double) {
      return JDoubleLiteral.get(((Double) value).doubleValue()).toSource();
    } else if (value instanceof Enum) {
      return value.getClass().getCanonicalName() + "." + ((Enum<?>) value).name();
    } else if (value instanceof Float) {
      return JFloatLiteral.get(((Float) value).floatValue()).toSource();
    } else if (value instanceof Integer) {
      return JIntLiteral.get(((Integer) value).intValue()).toSource();
    } else if (value instanceof Long) {
      return JLongLiteral.get(((Long) value).longValue()).toSource();
    } else if (value instanceof String) {
      return '"' + Generator.escape((String) value) + '"';
    } else {
      // TODO(nchalko) handle Annotation types
      throw new IllegalArgumentException(
          value.getClass() + " can not be represented as a Java Literal.");
    }
  }

  /**
   * check if elementClass is iterable.
   *
   * @param elementClass class to check
   * @return true if iterable, otherwise false
   */
  public static boolean isIterableOrMap(final Class<?> elementClass) {
    // TODO(nchalko) handle iterables everywhere this is called.
    return elementClass.isArray() || Iterable.class.isAssignableFrom(elementClass)
        || Map.class.isAssignableFrom(elementClass);
  }

  /**
   * Finds the type that a constraint validator will check.
   *
   * <p>
   * This type comes from the first parameter of the isValid() method on the constraint validator.
   * However, this is a bit tricky because ConstraintValidator has a parameterized type. When using
   * Java reflection, we will see multiple isValid() methods, including one that checks
   * java.lang.Object.
   * </p>
   *
   * <p>
   * Strategy: for now, assume there are at most two isValid() methods. If there are two, assume one
   * of them has a type that is assignable from the other. (Most likely, one of them will be
   * java.lang.Object.)
   * </p>
   *
   * @throws IllegalStateException if there isn't any isValid() method or there are more than two.
   */
  static <T extends Annotation> Class<?> getTypeOfConstraintValidator(
      final Class<? extends ConstraintValidator<T, ?>> constraintClass) {

    int candidateCount = 0;
    Class<?> result = null;
    for (final Method method : constraintClass.getMethods()) {
      if (method.getName().equals("isValid") && method.getParameterTypes().length == 2
          && method.getReturnType().isAssignableFrom(Boolean.TYPE)) {
        final Class<?> firstArgType = method.getParameterTypes()[0];
        if (result == null || result.isAssignableFrom(firstArgType)) {
          result = firstArgType;
        }
        candidateCount++;
      }
    }

    if (candidateCount == 0) {
      throw new IllegalStateException("ConstraintValidators must have a isValid method");
    } else if (candidateCount > 2) {
      throw new IllegalStateException(
          "ConstraintValidators must have no more than two isValid methods");
    }

    return result;
  }

  // Visible for testing
  static <A extends Annotation> ImmutableSet<Class<? extends ConstraintValidator<A, ?>>> //
      getValidatorForType(final Class<?> ptype,
          final List<Class<? extends ConstraintValidator<A, ?>>> constraintValidatorClasses) {
    final Class<?> type = Primitives.wrap(ptype);
    final Map<Class<?>, Class<? extends ConstraintValidator<A, ?>>> map = Maps.newHashMap();
    for (final Class<? extends ConstraintValidator<A, ?>> conClass : constraintValidatorClasses) {
      final Class<?> aType = Primitives.wrap(getTypeOfConstraintValidator(conClass));
      if (aType.isAssignableFrom(type)) {
        map.put(aType, conClass);
      }
    }
    // TODO(nchalko) implement per spec
    // Handle Arrays and Generics

    final Set<Class<?>> best = Util.findBestMatches(type, map.keySet());

    final Predicate<Class<?>> inBest = key -> best.contains(key);
    return ImmutableSet.copyOf(Maps.filterKeys(map, inBest).values());
  }

  /**
   * Gets the best {@link ConstraintValidator}.
   *
   * <p>
   * The ConstraintValidator chosen to validate a declared type {@code targetType} is the one where
   * the type supported by the ConstraintValidator is a supertype of {@code targetType} and where
   * there is no other ConstraintValidator whose supported type is a supertype of {@code type} and
   * not a supertype of the chosen ConstraintValidator supported type.
   * </p>
   *
   * @param constraint the constraint to find ConstraintValidators for.
   * @param targetType The type to find a ConstraintValidator for.
   * @return ConstraintValidator
   *
   * @throws UnexpectedTypeException if there is not exactly one maximally specific constraint
   *         validator for targetType.
   */
  private static <A extends Annotation> Class<? extends ConstraintValidator<A, ?>> //
      getValidatorForType(final ConstraintDescriptor<A> constraint, final Class<?> targetType)
          throws UnexpectedTypeException {
    final List<Class<? extends ConstraintValidator<A, ?>>> constraintValidatorClasses =
        constraint.getConstraintValidatorClasses();
    if (constraintValidatorClasses.isEmpty()) {
      throw new UnexpectedTypeException(
          "No ConstraintValidator found for  " + constraint.getAnnotation());
    }
    final ImmutableSet<Class<? extends ConstraintValidator<A, ?>>> best =
        getValidatorForType(targetType, constraintValidatorClasses);
    if (best.isEmpty()) {
      throw new UnexpectedTypeException(
          "No " + constraint.getAnnotation() + " ConstraintValidator for type " + targetType);
    }
    if (best.size() > 1) {
      throw new UnexpectedTypeException("More than one maximally specific "
          + constraint.getAnnotation() + " ConstraintValidator for type " + targetType + ", found "
          + Ordering.usingToString().sortedCopy(best));
    }
    return Iterables.get(best, 0);
  }

  private static ConstraintOrigin convertConstraintOriginEnum(
      final org.hibernate.validator.internal.metadata.core.ConstraintOrigin definedOn) {
    switch (definedOn) {
      case DEFINED_IN_HIERARCHY:
        return ConstraintOrigin.DEFINED_IN_HIERARCHY;
      case DEFINED_LOCALLY:
        return ConstraintOrigin.DEFINED_LOCALLY;
      default:
        throw new IllegalArgumentException("Unable to convert: unknown ConstraintOrigin value");
    }
  }

  private final BeanHelper beanHelper;

  private final Set<BeanHelper> beansToValidate = Sets.newHashSet();

  private final JClassType beanType;

  private final Set<JField> fieldsToWrap = Sets.newHashSet();

  private final Set<JMethod> gettersToWrap = Sets.newHashSet();

  private final Set<Class<?>> validGroups;

  private final Map<ConstraintDescriptor<?>, Boolean> validConstraintsMap = Maps.newHashMap();

  /**
   * constructor.
   */
  public GwtSpecificValidatorCreator(final JClassType validatorType, final JClassType beanType,
      final BeanHelper beanHelper, final TreeLogger logger, final GeneratorContext context,
      final BeanHelperCache cache, final Class<?>[] validGroupsFromAnnotation) {
    super(context, logger, validatorType, cache);
    this.beanType = beanType;
    this.beanHelper = beanHelper;

    final Set<Class<?>> tempValidGroups = Sets.newHashSet(validGroupsFromAnnotation);
    tempValidGroups.add(Default.class);
    this.validGroups = Collections.unmodifiableSet(tempValidGroups);
  }

  @Override
  protected void compose(final ClassSourceFileComposerFactory composerFactory) {
    this.addImports(composerFactory, Annotation.class, ConstraintViolation.class, GWT.class,
        ValidationGroupsMetadata.class, Group.class, GroupChain.class, PathImpl.class, Node.class,
        GroupChainGenerator.class, GwtBeanDescriptor.class, BeanMetadata.class,
        GwtValidationContext.class, ArrayList.class, HashSet.class, IllegalArgumentException.class,
        Set.class, Collection.class, Iterator.class, List.class, ValidationException.class);
    composerFactory.setSuperclass(AbstractGwtSpecificValidator.class.getCanonicalName() + "<"
        + this.beanType.getQualifiedSourceName() + ">");
    composerFactory.addImplementedInterface(this.validatorType.getName());
  }

  @Override
  protected void writeClassBody(final SourceWriter sw) throws UnableToCompleteException {
    this.writeFields(sw);
    sw.println();
    this.writeValidateClassGroups(sw);
    sw.println();
    this.writeExpandDefaultAndValidateClassGroups(sw);
    sw.println();
    this.writeExpandDefaultAndValidatePropertyGroups(sw);
    sw.println();
    this.writeExpandDefaultAndValidateValueGroups(sw);
    sw.println();
    this.writeValidatePropertyGroups(sw);
    sw.println();
    this.writeValidateValueGroups(sw);
    sw.println();
    this.writeGetBeanMetadata(sw);
    sw.println();
    this.writeGetDescriptor(sw);
    sw.println();
    this.writePropertyValidators(sw);
    sw.println();
    this.writeValidateAllNonInheritedProperties(sw);
    sw.println();

    // Write the wrappers after we know which are needed
    this.writeWrappers(sw);
    sw.println();
  }

  protected void writeUnsafeNativeLongIfNeeded(final SourceWriter sw, final JType jtype) {
    if (JPrimitiveType.LONG.equals(jtype)) {
      // @com.google.gwt.core.client.UnsafeNativeLong
      sw.print("@");
      sw.println(UnsafeNativeLong.class.getCanonicalName());
    }
  }

  private boolean areConstraintDescriptorGroupsValid(
      final ConstraintDescriptor<?> constraintDescriptor) {
    if (this.validConstraintsMap.containsKey(constraintDescriptor)) {
      return this.validConstraintsMap.get(constraintDescriptor);
    } else {
      final boolean areValid = this.checkGroups(constraintDescriptor.getGroups());
      // cache result
      this.validConstraintsMap.put(constraintDescriptor, areValid);
      return areValid;
    }
  }

  private <T> T[] asArray(final Collection<?> collection, final T[] array) {
    if (collection == null) {
      return null;
    }
    return collection.toArray(array);
  }

  private boolean checkGroups(final Set<Class<?>> groups) {
    for (final Class<?> group : groups) {
      if (this.validGroups.contains(group)) {
        return true;
      }
    }
    return false;
  }

  private String constraintDescriptorVar(final String name, final int count) {
    return name + "_c" + count;
  }

  private Annotation getAnnotation(final PropertyDescriptor ppropertyDescription,
      final boolean useField, final Class<? extends Annotation> expectedAnnotationClass) {
    Annotation annotation = null;
    if (useField) {
      final JField field = this.beanType.findField(ppropertyDescription.getPropertyName());
      if (field.getEnclosingType().equals(this.beanType)) {
        annotation = field.getAnnotation(expectedAnnotationClass);
      }
    } else {
      final JMethod method = this.beanType.findMethod(asGetter(ppropertyDescription), NO_ARGS);
      if (method.getEnclosingType().equals(this.beanType)) {
        annotation = method.getAnnotation(expectedAnnotationClass);
      }
    }
    return annotation;
  }

  private Annotation[] getAnnotations(final PropertyDescriptor ppropertyDescription,
      final boolean useField) {
    final Class<?> clazz = this.beanHelper.getClazz();
    if (useField) {
      try {
        final Field field = clazz.getDeclaredField(ppropertyDescription.getPropertyName());
        return field.getAnnotations();
      } catch (final NoSuchFieldException ignore) { // NOPMD
        // Expected Case
      }
    } else {
      try {
        final Method method = clazz.getMethod(asGetter(ppropertyDescription));
        return method.getAnnotations();
      } catch (final NoSuchMethodException ignore) { // NOPMD
        // Expected Case
      }
    }
    return NO_ANNOTATIONS;
  }

  private String getQualifiedSourceNonPrimitiveType(final JType elementType) {
    final JPrimitiveType primitive = elementType.isPrimitive();
    return primitive == null ? elementType.getQualifiedSourceName()
        : primitive.getQualifiedBoxedSourceName();
  }

  private boolean hasMatchingAnnotation(final Annotation expectedAnnotation,
      final Annotation[] annotations) throws UnableToCompleteException {
    // See spec section 2.2. Applying multiple constraints of the same type
    for (final Annotation annotation : annotations) {
      // annotations not annotated by @Constraint
      if (annotation.annotationType().getAnnotation(Constraint.class) == null) {
        try {
          // value element has a return type of an array of constraint
          // annotations
          final Method valueMethod = annotation.annotationType().getMethod("value");
          final Class<?> valueType = valueMethod.getReturnType();
          if (valueType.isArray()
              && Annotation.class.isAssignableFrom(valueType.getComponentType())) {
            if (Modifier.isAbstract(valueMethod.getModifiers())) {
              // handle edge case where interface is marked "abstract"
              valueMethod.setAccessible(true);
            }
            final Annotation[] valueAnnotions = (Annotation[]) valueMethod.invoke(annotation);
            for (final Annotation annotation2 : valueAnnotions) {
              if (expectedAnnotation.equals(annotation2)) {
                return true;
              }
            }
          }
        } catch (final NoSuchMethodException ignore) { // NOPMD
          // Expected Case.
        } catch (final Exception e) {
          throw error(this.logger, e);
        }
      }
    }
    return false;
  }

  private boolean hasMatchingAnnotation(final ConstraintDescriptor<?> constraint)
      throws UnableToCompleteException {
    final Annotation expectedAnnotation = constraint.getAnnotation();
    final Class<? extends Annotation> expectedAnnotationClass = expectedAnnotation.annotationType();
    if (expectedAnnotation
        .equals(this.beanHelper.getClazz().getAnnotation(expectedAnnotationClass))) {
      return true;
    }

    // See spec section 2.2. Applying multiple constraints of the same type
    final Annotation[] annotations = this.beanHelper.getClazz().getAnnotations();
    return this.hasMatchingAnnotation(expectedAnnotation, annotations);
  }

  private boolean hasMatchingAnnotation(final PropertyDescriptor ppropertyDescription,
      final boolean useField, final ConstraintDescriptor<?> constraint)
      throws UnableToCompleteException {
    final Annotation expectedAnnotation = constraint.getAnnotation();
    final Class<? extends Annotation> expectedAnnotationClass = expectedAnnotation.annotationType();
    if (expectedAnnotation
        .equals(this.getAnnotation(ppropertyDescription, useField, expectedAnnotationClass))) {
      return true;
    }
    return this.hasMatchingAnnotation(expectedAnnotation,
        this.getAnnotations(ppropertyDescription, useField));
  }

  private boolean hasValid(final PropertyDescriptor ppropertyDescription, final boolean useField) {
    return this.getAnnotation(ppropertyDescription, useField, Valid.class) != null;
  }

  private boolean isPropertyConstrained(final BeanHelper helper,
      final PropertyDescriptor ppropertyDescription) {
    final Set<PropertyDescriptor> propertyDescriptors =
        helper.getBeanDescriptor().getConstrainedProperties();
    final Predicate<PropertyDescriptor> nameMatches =
        this.newPropertyNameMatches(ppropertyDescription);
    return Iterables.any(propertyDescriptors, nameMatches);
  }

  private boolean isPropertyConstrained(final PropertyDescriptor ppropertyDescription,
      final boolean useField) {
    // cascaded counts as constrained
    // we must know if the @Valid annotation is on a field or a getter
    final JClassType jClass = this.beanHelper.getJClass();
    if (useField && jClass.findField(ppropertyDescription.getPropertyName())
        .isAnnotationPresent(Valid.class)) {
      return true;
    } else if (!useField && jClass.findMethod(asGetter(ppropertyDescription), NO_ARGS)
        .isAnnotationPresent(Valid.class)) {
      return true;
    }
    // for non-cascaded properties
    for (final ConstraintDescriptor<?> constraint : ppropertyDescription
        .getConstraintDescriptors()) {
      final org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?> constraintHibernate =
          (org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?>) constraint;
      if (constraintHibernate
          .getElementType() == (useField ? ElementType.FIELD : ElementType.METHOD)) {
        return true;
      }
    }
    return false;
  }

  private Predicate<PropertyDescriptor> newPropertyNameMatches(
      final PropertyDescriptor ppropertyDescription) {
    return input -> input.getPropertyName().equals(ppropertyDescription.getPropertyName());
  }

  private String toWrapperName(final JField field) {
    return "_" + field.getName();
  }

  private String toWrapperName(final JMethod method) {
    return "_" + method.getName();
  }

  private String validateMethodFieldName(final PropertyDescriptor ppropertyDescription) {
    return "validateProperty_" + ppropertyDescription.getPropertyName();
  }

  private String validateMethodGetterName(final PropertyDescriptor ppropertyDescription) {
    return "validateProperty_get" + ppropertyDescription.getPropertyName();
  }

  private void writeBeanDescriptor(final SourceWriter sw) {
    final BeanDescriptor beanDescriptor = this.beanHelper.getBeanDescriptor();

    // private final GwtBeanDescriptor <MyBean> beanDescriptor =
    sw.print("private final ");
    sw.print(GwtBeanDescriptor.class.getCanonicalName());
    sw.print("<" + this.beanHelper.getTypeCanonicalName() + ">");
    sw.println(" beanDescriptor = ");
    sw.indent();
    sw.indent();

    // GwtBeanDescriptorImpl.builder(Order.class)
    sw.print(GwtBeanDescriptorImpl.class.getCanonicalName());
    sw.println(".builder(" + this.beanHelper.getTypeCanonicalName() + ".class)");
    sw.indent();
    sw.indent();

    // .setConstrained(true)
    sw.println(".setConstrained(" + beanDescriptor.isBeanConstrained() + ")");

    int count = 0;
    for (final ConstraintDescriptor<?> constraint : beanDescriptor.getConstraintDescriptors()) {
      if (this.areConstraintDescriptorGroupsValid(constraint)) {
        // .add(c0)
        sw.println(".add(" + this.constraintDescriptorVar("this", count) + ")");
        count++;
      }
    }

    // .put("myProperty", myProperty_pd)
    for (final PropertyDescriptor p : beanDescriptor.getConstrainedProperties()) {
      sw.print(".put(\"");
      sw.print(p.getPropertyName());
      sw.print("\", ");
      sw.print(p.getPropertyName());
      sw.println("_pd)");
    }

    // .setBeanMetadata(beanMetadata)
    sw.println(".setBeanMetadata(beanMetadata)");

    // .build();
    sw.println(".build();");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writeBeanMetadata(final SourceWriter sw) throws UnableToCompleteException {
    // private final BeanMetadata beanMetadata =
    sw.println("private final BeanMetadata beanMetadata =");
    sw.indent();
    sw.indent();

    // new BeanMetadata(
    sw.println("new " + BeanMetadata.class.getSimpleName() + "(");
    sw.indent();
    sw.indent();

    // <<bean class>>, <<default group seq class 1>>, <<default group seq class 2>>, ...
    final Class<?> beanClazz = this.beanHelper.getClazz();
    sw.print(asLiteral(beanClazz));
    final GroupSequence groupSeqAnnotation = beanClazz.getAnnotation(GroupSequence.class);
    final List<Class<?>> groupSequence = new ArrayList<>();
    if (groupSeqAnnotation == null) {
      groupSequence.add(beanClazz);
    } else {
      groupSequence.addAll(Arrays.asList(groupSeqAnnotation.value()));
    }
    boolean groupSequenceContainsDefault = false;
    for (final Class<?> group : groupSequence) {
      sw.println(",");
      if (group.getName().equals(beanClazz.getName())) {
        sw.print(asLiteral(Default.class));
        groupSequenceContainsDefault = true;
      } else if (group.getName().equals(Default.class.getName())) {
        throw error(this.logger, "'Default.class' cannot appear in default group sequence list.");
      } else {
        sw.print(asLiteral(group));
      }
    }
    if (!groupSequenceContainsDefault) {
      throw error(this.logger,
          beanClazz.getName() + " must be part of the redefined default group " + "sequence.");
    }

    sw.println(");");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writeClassLevelConstraintsValidation(final SourceWriter sw,
      final String groupsVarName) throws UnableToCompleteException {
    // all class level constraints
    int count = 0;
    final Class<?> clazz = this.beanHelper.getClazz();
    for (final ConstraintDescriptor<?> constraint : this.beanHelper.getBeanDescriptor()
        .getConstraintDescriptors()) {
      if (this.areConstraintDescriptorGroupsValid(constraint)) {
        if (this.hasMatchingAnnotation(constraint)) {

          if (!constraint.getConstraintValidatorClasses().isEmpty()) { // NOPMD
            final Class<? extends ConstraintValidator<? extends Annotation, ?>> validatorClass =
                getValidatorForType(constraint, clazz);

            // validate(context, violations, null, object,
            sw.print("validate(context, violations, null, object, ");

            // new MyValidtor(),
            sw.print("new ");
            sw.print(validatorClass.getCanonicalName());
            sw.print("(), "); // TODO(nchalko) use ConstraintValidatorFactory

            // this.aConstraintDescriptor, groups);
            sw.print(this.constraintDescriptorVar("this", count));
            sw.print(", ");
            sw.print(groupsVarName);
            sw.println(");");
          } else if (constraint.getComposingConstraints().isEmpty()) {
            // TODO(nchalko) What does the spec say to do here.
            this.logger.log(TreeLogger.WARN,
                "No ConstraintValidator of " + constraint + " for type " + clazz);
          }
          // TODO(nchalko) handle constraint.isReportAsSingleViolation() and
          // hasComposingConstraints
        }
        count++;
      }
    }
  }

  private void writeConstraintDescriptor(final SourceWriter sw,
      final ConstraintDescriptor<? extends Annotation> constraint, final ElementType elementType,
      final ConstraintOrigin origin, final String constraintDescripotorVar)
      throws UnableToCompleteException {
    final Class<? extends Annotation> annotationType = constraint.getAnnotation().annotationType();

    // First list all composing constraints
    int count = 0;
    for (final ConstraintDescriptor<?> composingConstraint : constraint.getComposingConstraints()) {
      this.writeConstraintDescriptor(sw, composingConstraint, elementType, origin,
          constraintDescripotorVar + "_" + count++);
    }

    // private final ConstraintDescriptorImpl<MyAnnotation> constraintDescriptor = ;
    sw.print("private final ");
    sw.print(ConstraintDescriptorImpl.class.getCanonicalName());
    sw.print("<");

    sw.print(annotationType.getCanonicalName());
    sw.print(">");

    sw.println(" " + constraintDescripotorVar + "  = ");
    sw.indent();
    sw.indent();

    // ConstraintDescriptorImpl.<MyConstraint> builder()
    sw.print(ConstraintDescriptorImpl.class.getCanonicalName());
    sw.print(".<");

    sw.print(annotationType.getCanonicalName());
    sw.println("> builder()");
    sw.indent();
    sw.indent();

    // .setAnnotation(new MyAnnotation )
    sw.println(".setAnnotation( ");
    sw.indent();
    sw.indent();
    this.writeNewAnnotation(sw, constraint);
    sw.println(")");
    sw.outdent();
    sw.outdent();

    // .setAttributes(builder()
    sw.println(".setAttributes(attributeBuilder()");
    sw.indent();

    for (final Map.Entry<String, Object> entry : constraint.getAttributes().entrySet()) {
      // .put(key, value)
      sw.print(".put(");
      final String key = entry.getKey();
      sw.print(asLiteral(key));
      sw.print(", ");
      Object value = entry.getValue();
      // Add the Default group if it is not already present
      if ("groups".equals(key) && value instanceof Class[] && ((Class[]) value).length == 0) {
        value = new Class[] {Default.class};
      }
      sw.print(asLiteral(value));
      sw.println(")");
    }

    // .build())
    sw.println(".build())");
    sw.outdent();

    // .setConstraintValidatorClasses(classes )
    sw.print(".setConstraintValidatorClasses(");
    sw.print(asLiteral(this.asArray(constraint.getConstraintValidatorClasses(), new Class[0])));
    sw.println(")");

    final int ccCount = constraint.getComposingConstraints().size();
    for (int i = 0; i < ccCount; i++) {
      // .addComposingConstraint(cX_X)
      sw.print(".addComposingConstraint(");
      sw.print(constraintDescripotorVar + "_" + i);
      sw.println(")");
    }

    // .getGroups(groups)
    sw.print(".setGroups(");
    final Set<Class<?>> groups = constraint.getGroups();
    sw.print(asLiteral(this.asArray(groups, new Class<?>[0])));
    sw.println(")");

    // .setPayload(payload)
    sw.print(".setPayload(");
    final Set<Class<? extends Payload>> payload = constraint.getPayload();
    sw.print(asLiteral(this.asArray(payload, new Class[0])));
    sw.println(")");

    // .setReportAsSingleViolation(boolean )
    sw.print(".setReportAsSingleViolation(");
    sw.print(Boolean.toString(constraint.isReportAsSingleViolation()));
    sw.println(")");

    // .setElementType(elementType)
    sw.print(".setElementType(");
    sw.print(asLiteral(elementType));
    sw.println(")");

    // .setDefinedOn(origin)
    sw.print(".setDefinedOn(");
    sw.print(asLiteral(origin));
    sw.println(")");

    // .build();
    sw.println(".build();");
    sw.outdent();
    sw.outdent();

    sw.outdent();
    sw.outdent();
    sw.println();
  }

  private void writeExpandDefaultAndValidate(final SourceWriter sw, final Stage stage)
      throws UnableToCompleteException {
    final Class<?> clazz = this.beanHelper.getClazz();

    // ArrayList<Class<?>> justGroups = new ArrayList<Class<?>>();
    sw.println("ArrayList<Class<?>> justGroups = new ArrayList<Class<?>>();");

    // for (Group g : groups) {
    sw.println("for (Group g : groups) {");
    sw.indent();
    // if (!g.isDefaultGroup() || !getBeanMetadata().defaultGroupSequenceIsRedefined()) {
    sw.println(
        "if (!g.isDefaultGroup() || !getBeanMetadata().defaultGroupSequenceIsRedefined()) {");
    sw.indent();
    // justGroups.add(g.getGroup());
    sw.println("justGroups.add(g.getGroup());");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");

    // Class<?>[] justGroupsArray = justGroups.toArray(new Class<?>[justGroups.size()]);
    sw.println("Class<?>[] justGroupsArray = justGroups.toArray(new Class<?>[justGroups.size()]);");

    switch (stage) {
      case OBJECT:
        // validateAllNonInheritedProperties(context, object, violations, justGroupsArray);
        sw.println("validateAllNonInheritedProperties(context, object, violations, "
            + "justGroupsArray);");
        this.writeClassLevelConstraintsValidation(sw, "justGroupsArray");
        break;
      case PROPERTY:
        // validatePropertyGroups(context, object, propertyName, violations, justGroupsArray);
        sw.println("validatePropertyGroups(context, object, propertyName, violations, "
            + "justGroupsArray);");
        break;
      case VALUE:
        // validateValueGroups(context, beanType, propertyName, value, violations,
        // justGroupsArray);
        sw.println("validateValueGroups(context, beanType, propertyName, value, violations, "
            + "justGroupsArray);");
        break;
      default:
        throw new IllegalStateException();
    }

    // if (getBeanMetadata().defaultGroupSequenceIsRedefined()) {
    sw.println("if (getBeanMetadata().defaultGroupSequenceIsRedefined()) {");
    sw.indent();
    // for (Class<?> g : beanMetadata.getDefaultGroupSequence()) {
    sw.println("for (Class<?> g : beanMetadata.getDefaultGroupSequence()) {");
    sw.indent();
    // int numberOfViolations = violations.size();
    sw.println("int numberOfViolations = violations.size();");

    switch (stage) {
      case OBJECT:
        // validateAllNonInheritedProperties(context, object, violations, g);
        sw.println("validateAllNonInheritedProperties(context, object, violations, g);");
        this.writeClassLevelConstraintsValidation(sw, "g");
        // validate super classes and super interfaces
        this.writeValidateInheritance(sw, clazz, Stage.OBJECT, null, false, "g");
        break;
      case PROPERTY:
        // validatePropertyGroups(context, object, propertyName, violations, g);
        sw.println("validatePropertyGroups(context, object, propertyName, violations, g);");
        break;
      case VALUE:
        // validateValueGroups(context, beanType, propertyName, value, violations, g);
        sw.println("validateValueGroups(context, beanType, propertyName, value, violations, g);");
        break;
      default:
        throw new IllegalStateException();
    }

    // if (violations.size() > numberOfViolations) {
    sw.println("if (violations.size() > numberOfViolations) {");
    sw.indent();
    // break;
    sw.println("break;");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");
    if (stage == Stage.OBJECT) {
      // else {
      sw.println("else {");
      sw.indent();

      // validate super classes and super interfaces
      this.writeValidateInheritance(sw, clazz, Stage.OBJECT, null, true, "groups");

      // }
      sw.outdent();
      sw.println("}");
    }
  }

  private void writeExpandDefaultAndValidateClassGroups(final SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidateClassGroups(
    sw.println("public <T> void expandDefaultAndValidateClassGroups(");

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    this.writeExpandDefaultAndValidate(sw, Stage.OBJECT);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeExpandDefaultAndValidatePropertyGroups(final SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidatePropertyGroups(
    sw.println("public <T> void expandDefaultAndValidatePropertyGroups(");

    // GwtValidationContext<T> context, BeanType object, String propertyName,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");
    sw.println("String propertyName,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    this.writeExpandDefaultAndValidate(sw, Stage.PROPERTY);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeExpandDefaultAndValidateValueGroups(final SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidateValueGroups(
    sw.println("public <T> void expandDefaultAndValidateValueGroups(");

    // GwtValidationContext<T> context, Class<Author> beanType, String propertyName,
    // Object value, Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println("Class<" + this.beanHelper.getTypeCanonicalName() + "> beanType,");
    sw.println("String propertyName,");
    sw.println("Object value,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    this.writeExpandDefaultAndValidate(sw, Stage.VALUE);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeFields(final SourceWriter sw) throws UnableToCompleteException {

    // Create a static array of all valid property names.
    BeanInfo beanInfo;
    try {
      beanInfo = Introspector.getBeanInfo(this.beanHelper.getClazz());
    } catch (final IntrospectionException e) {
      throw error(this.logger, e);
    }

    // private static final java.util.List<String> ALL_PROPERTY_NAMES =
    sw.println("private static final java.util.List<String> ALL_PROPERTY_NAMES = ");
    sw.indent();
    sw.indent();

    // Collections.<String>unmodifiableList(
    sw.println("java.util.Collections.<String>unmodifiableList(");
    sw.indent();
    sw.indent();

    // java.util.Arrays.<String>asList(
    sw.print("java.util.Arrays.<String>asList(");

    // "foo","bar" );
    sw.print(Joiner.on(",")
        .join(Iterables.transform(ImmutableList.copyOf(beanInfo.getPropertyDescriptors()),
            Functions.compose(TO_LITERAL, PROPERTY_DESCRIPTOR_TO_NAME))));
    sw.println("));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // Write the metadata for the bean
    this.writeBeanMetadata(sw);
    sw.println();

    // Create a variable for each constraint of each property
    for (final PropertyDescriptor p : this.beanHelper.getBeanDescriptor()
        .getConstrainedProperties()) {
      int count = 0;
      for (final ConstraintDescriptor<?> constraint : p.getConstraintDescriptors()) {
        final org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?> constraintHibernate =
            (org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?>) constraint;
        if (this.areConstraintDescriptorGroupsValid(constraint)) {
          this.writeConstraintDescriptor(sw, constraint, constraintHibernate.getElementType(),
              convertConstraintOriginEnum(constraintHibernate.getDefinedOn()),
              this.constraintDescriptorVar(p.getPropertyName(), count++));
        }
      }
      this.writePropertyDescriptor(sw, p);
      if (p.isCascaded()) {
        this.beansToValidate.add(isIterableOrMap(p.getElementClass())
            ? this.createBeanHelper(this.beanHelper.getAssociationType(p, true))
            : this.createBeanHelper(p.getElementClass()));
      }
    }

    // Create a variable for each constraint of this class.
    int count = 0;
    for (final ConstraintDescriptor<?> constraint : this.beanHelper.getBeanDescriptor()
        .getConstraintDescriptors()) {
      final org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?> constraintHibernate =
          (org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl<?>) constraint;
      if (this.areConstraintDescriptorGroupsValid(constraint)) {
        this.writeConstraintDescriptor(sw, constraint, ElementType.TYPE,
            convertConstraintOriginEnum(constraintHibernate.getDefinedOn()),
            this.constraintDescriptorVar("this", count++));
      }
    }

    // Now write the BeanDescriptor after we already have the
    // PropertyDescriptors and class constraints
    this.writeBeanDescriptor(sw);
    sw.println();
  }

  private void writeFieldWrapperMethod(final SourceWriter sw, final JField field) {
    this.writeUnsafeNativeLongIfNeeded(sw, field.getType());

    // private native fieldType _fieldName(com.example.Bean object) /*-{
    sw.print("private native ");

    sw.print(field.getType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(this.toWrapperName(field));
    sw.print("(");
    sw.print(field.getEnclosingType().getQualifiedSourceName());
    sw.println(" object) /*-{");
    sw.indent();

    // return object.@com.examples.Bean::myMethod();
    sw.print("return object.@");
    sw.print(field.getEnclosingType().getQualifiedSourceName());
    sw.print("::" + field.getName());
    sw.println(";");

    // }-*/;
    sw.outdent();
    sw.println("}-*/;");
  }

  private void writeGetBeanMetadata(final SourceWriter sw) {
    // public BeanMetadata getBeanMetadata() {
    sw.println("public BeanMetadata getBeanMetadata() {");
    sw.indent();

    // return beanMetadata;
    sw.println("return beanMetadata;");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeGetDescriptor(final SourceWriter sw) {
    // public GwtBeanDescriptor<beanType>
    // getConstraints(ValidationGroupsMetadata validationGroupsMetadata) {
    sw.print("public ");
    sw.print("GwtBeanDescriptor<" + this.beanHelper.getTypeCanonicalName() + "> ");
    sw.println("getConstraints(ValidationGroupsMetadata validationGroupsMetadata) {");
    sw.indent();

    // beanDescriptor.setValidationGroupsMetadata(validationGroupsMetadata);
    sw.println("beanDescriptor.setValidationGroupsMetadata(validationGroupsMetadata);");

    // return beanDescriptor;
    sw.println("return beanDescriptor;");

    sw.outdent();
    sw.println("}");
  }

  private void writeGetterWrapperMethod(final SourceWriter sw, final JMethod method) {
    this.writeUnsafeNativeLongIfNeeded(sw, method.getReturnType());

    // private native fieldType _getter(Bean object) /*={
    sw.print("private native ");
    sw.print(method.getReturnType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(this.toWrapperName(method));
    sw.print("(");
    sw.print(this.beanType.getName());
    sw.println(" object) /*-{");
    sw.indent();

    // return object.@com.examples.Bean::myMethod()();
    sw.print("return object.");
    sw.print(method.getJsniSignature());
    sw.println("();");

    // }-*/;
    sw.outdent();
    sw.println("}-*/;");
  }

  private void writeIfPropertyNameNotFound(final SourceWriter sw) {
    // if (!ALL_PROPERTY_NAMES.contains(propertyName)) {
    sw.println(" if (!ALL_PROPERTY_NAMES.contains(propertyName)) {");
    sw.indent();

    // throw new IllegalArgumentException(propertyName
    // +"is not a valid property of myClass");
    sw.print("throw new ");
    sw.print(IllegalArgumentException.class.getCanonicalName());
    sw.print("( propertyName +\" is not a valid property of ");
    sw.print(this.beanType.getQualifiedSourceName());
    sw.println("\");");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeNewAnnotation(final SourceWriter sw,
      final ConstraintDescriptor<? extends Annotation> constraint)
      throws UnableToCompleteException {
    final Annotation annotation = constraint.getAnnotation();
    final Class<? extends Annotation> annotationType = annotation.annotationType();

    // new MyAnnotation () {
    sw.print("new ");
    sw.print(annotationType.getCanonicalName());
    sw.println("(){");
    sw.indent();
    sw.indent();

    // public Class<? extends Annotation> annotationType() { return
    // MyAnnotation.class; }
    sw.print("public Class<? extends Annotation> annotationType() {  return ");
    sw.print(annotationType.getCanonicalName());
    sw.println(".class; }");

    for (final Method method : annotationType.getMethods()) {
      // method.isAbstract would be better
      if (method.getDeclaringClass().equals(annotation.annotationType())) {
        // public returnType method() { return value ;}
        sw.print("public ");
        sw.print(method.getReturnType().getCanonicalName()); // TODO handle
                                                             // generics
        sw.print(" ");
        sw.print(method.getName());
        sw.print("() { return ");

        try {
          final Object value = method.invoke(annotation);
          sw.print(asLiteral(value));
        } catch (final IllegalArgumentException e) {
          throw error(this.logger, e);
        } catch (final IllegalAccessException e) {
          throw error(this.logger, e);
        } catch (final InvocationTargetException e) {
          throw error(this.logger, e);
        }
        sw.println(";}");
      }
    }

    sw.outdent();
    sw.outdent();
    sw.println("}");
  }

  private void writeNewViolations(final SourceWriter sw, final String violationName) {
    // Set<ConstraintViolation<T>> violations =
    sw.print("Set<ConstraintViolation<T>> ");
    sw.print(violationName);
    sw.println(" = ");
    sw.indent();
    sw.indent();

    // new HashSet<ConstraintViolation<T>>();
    sw.println("new HashSet<ConstraintViolation<T>>();");
    sw.outdent();
    sw.outdent();
  }

  private void writePropertyDescriptor(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription) {
    // private final PropertyDescriptor myProperty_pd =
    sw.print("private final ");
    sw.print(PropertyDescriptorImpl.class.getCanonicalName());
    sw.print(" ");
    sw.print(ppropertyDescription.getPropertyName());
    sw.println("_pd =");
    sw.indent();
    sw.indent();

    // new PropertyDescriptorImpl(
    sw.println("new " + PropertyDescriptorImpl.class.getCanonicalName() + "(");
    sw.indent();
    sw.indent();

    // "myProperty",
    sw.println("\"" + ppropertyDescription.getPropertyName() + "\",");

    // MyType.class,
    sw.println(ppropertyDescription.getElementClass().getCanonicalName() + ".class,");

    // isCascaded,
    sw.print(Boolean.toString(ppropertyDescription.isCascaded()) + ",");

    // beanMetadata,
    sw.print("beanMetadata");

    // myProperty_c0,
    // myProperty_c1 );
    int count = 0;
    for (final ConstraintDescriptor<?> constraint : ppropertyDescription
        .getConstraintDescriptors()) {
      if (this.areConstraintDescriptorGroupsValid(constraint)) {
        sw.println(","); // Print the , for the previous line
        sw.print(this.constraintDescriptorVar(ppropertyDescription.getPropertyName(), count));
        count++;
      }
    }
    sw.println(");");

    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writePropertyValidators(final SourceWriter sw) throws UnableToCompleteException {
    for (final PropertyDescriptor p : this.beanHelper.getBeanDescriptor()
        .getConstrainedProperties()) {
      if (this.beanHelper.hasField(p)) {
        this.writeValidatePropertyMethod(sw, p, true);
        sw.println();
      }
      if (this.beanHelper.hasGetter(p)) {
        this.writeValidatePropertyMethod(sw, p, false);
        sw.println();
      }
    }
  }

  private void writeValidateAllNonInheritedProperties(final SourceWriter sw) {
    // private <T> void validateAllNonInheritedProperties(
    sw.println("private <T> void validateAllNonInheritedProperties(");
    sw.indent();
    sw.indent();

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Class<?>... groups) {
    sw.println("GwtValidationContext<T> context,");
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    for (final PropertyDescriptor p : this.beanHelper.getBeanDescriptor()
        .getConstrainedProperties()) {
      this.writeValidatePropertyCall(sw, p, false, true);
    }

    sw.outdent();
    sw.println("}");
  }

  private void writeValidateClassGroups(final SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validateClassGroups(
    sw.println("public <T> void validateClassGroups(");

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    // /// For each group

    // TODO(nchalko) handle the sequence in the AbstractValidator

    // See JSR 303 section 3.5
    // all reachable fields
    // all reachable getters (both) at once
    // including all reachable and cascadable associations

    sw.println("validateAllNonInheritedProperties(context, object, violations, groups);");

    // validate super classes and super interfaces
    this.writeValidateInheritance(sw, this.beanHelper.getClazz(), Stage.OBJECT, null, false,
        "groups");

    this.writeClassLevelConstraintsValidation(sw, "groups");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateConstraint(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription, final Class<?> elementClass,
      final ConstraintDescriptor<?> constraint, final String constraintDescriptorVar)
      throws UnableToCompleteException {
    this.writeValidateConstraint(sw, ppropertyDescription, elementClass, constraint,
        constraintDescriptorVar, DEFAULT_VIOLATION_VAR);
  }

  /**
   * Writes the call to actually validate a constraint, including its composite constraints.
   * <p>
   * If the constraint is annotated as {@link javax.validation.ReportAsSingleViolation
   * ReportAsSingleViolation}, then is called recursively and the {@code violationsVar} is changed
   * to match the {@code constraintDescriptorVar}.
   * </p>
   *
   * @param sw the Source Writer
   * @param ppropertyDescription the property
   * @param elementClass The class of the Element
   * @param constraint the constraint to validate.
   * @param constraintDescriptorVar the name of the constraintDescriptor variable.
   * @param violationsVar the name of the variable to hold violations
   * @throws UnableToCompleteException when validation can not be completed
   */
  private void writeValidateConstraint(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription, final Class<?> elementClass,
      final ConstraintDescriptor<?> constraint, final String constraintDescriptorVar,
      final String violationsVar) throws UnableToCompleteException {
    final boolean isComposite = !constraint.getComposingConstraints().isEmpty();
    final boolean firstReportAsSingleViolation = constraint.isReportAsSingleViolation()
        && violationsVar.equals(DEFAULT_VIOLATION_VAR) && isComposite;
    final boolean reportAsSingleViolation =
        firstReportAsSingleViolation || !violationsVar.equals(DEFAULT_VIOLATION_VAR);
    final boolean hasValidator = !constraint.getConstraintValidatorClasses().isEmpty();
    final String compositeViolationsVar = constraintDescriptorVar + "_violations";

    // Only do this the first time in a constraint composition.
    if (firstReportAsSingleViolation) {
      // Report myConstraint as Single Violation
      sw.print("// Report ");
      sw.print(constraint.getAnnotation().annotationType().getCanonicalName());
      sw.println(" as Single Violation");
      this.writeNewViolations(sw, compositeViolationsVar);
    }

    if (hasValidator) {
      Class<? extends ConstraintValidator<? extends Annotation, ?>> validatorClass;
      try {
        validatorClass = getValidatorForType(constraint, elementClass);
      } catch (final UnexpectedTypeException e) {
        throw error(this.logger, e);
      }

      if (firstReportAsSingleViolation) {
        // if (!
        sw.println("if (!");
        sw.indent();
        sw.indent();
      }

      // validate(myContext, violations object, value, new MyValidator(),
      // constraintDescriptor, groups));
      sw.print("validate(myContext, ");
      sw.print(violationsVar);
      sw.print(", object, value, ");
      sw.print("new ");
      sw.print(validatorClass.getCanonicalName());
      sw.print("(), ");
      sw.print(constraintDescriptorVar);
      sw.print(", groups)");
      if (firstReportAsSingleViolation) {
        // ) {
        sw.println(") {");
        sw.outdent();

      } else if (reportAsSingleViolation) {
        if (isComposite) {
          // ||
          sw.println(" ||");
        }
      } else {
        // ;
        sw.println(";");
      }
    } else if (!isComposite) {
      // TODO(nchalko) What does the spec say to do here.
      this.logger.log(TreeLogger.WARN, "No ConstraintValidator of " + constraint + " for "
          + ppropertyDescription.getPropertyName() + " of type " + elementClass);
    }

    if (firstReportAsSingleViolation) {
      // if (
      sw.print("if (");
      sw.indent();
      sw.indent();
    }
    int count = 0;

    for (final ConstraintDescriptor<?> compositeConstraint : constraint.getComposingConstraints()) {
      final String compositeVar = constraintDescriptorVar + "_" + count++;
      this.writeValidateConstraint(sw, ppropertyDescription, elementClass, compositeConstraint,
          compositeVar, firstReportAsSingleViolation ? compositeViolationsVar : violationsVar);
      if (reportAsSingleViolation) {
        // ||
        sw.println(" ||");
      } else {
        // ;
        sw.println(";");
      }
    }
    if (isComposite && reportAsSingleViolation) {
      // false
      sw.print("false");
    }
    if (firstReportAsSingleViolation) {
      // ) {
      sw.println(" ) {");
      sw.outdent();

      // addSingleViolation(myContext, violations, object, value,
      // constraintDescriptor);
      sw.print("addSingleViolation(myContext, violations, object, value, ");
      sw.print(constraintDescriptorVar);
      sw.println(");");

      // }
      sw.outdent();
      sw.println("}");

      if (hasValidator) {
        // }
        sw.outdent();
        sw.println("}");
      }
    }
  }

  private void writeValidateFieldCall(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription, final boolean useValue,
      final boolean honorValid) {
    final String propertyName = ppropertyDescription.getPropertyName();

    // validateProperty_<<field>>(context,
    sw.print(this.validateMethodFieldName(ppropertyDescription));
    sw.print("(context, ");
    sw.print("violations, ");

    // null, (MyType) value,
    // or
    // object, object.getLastName(),
    if (useValue) {
      sw.print("null, ");
      sw.print("(");
      sw.print(this.getQualifiedSourceNonPrimitiveType(
          this.beanHelper.getElementType(ppropertyDescription, true)));
      sw.print(") value");
    } else {
      sw.print("object, ");
      final JField field = this.beanType.getField(propertyName);
      if (field.isPublic()) {
        sw.print("object.");
        sw.print(propertyName);
      } else {
        this.fieldsToWrap.add(field);
        sw.print(this.toWrapperName(field) + "(object)");
      }
    }
    sw.print(", ");

    // honorValid, groups);
    sw.print(Boolean.toString(honorValid));
    sw.println(", groups);");
  }

  private void writeValidateGetterCall(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription, final boolean useValue,
      final boolean honorValid) {
    // validateProperty_get<<field>>(context, violations,
    sw.print(this.validateMethodGetterName(ppropertyDescription));
    sw.print("(context, ");
    sw.print("violations, ");

    // object, object.getMyProp(),
    // or
    // null, (MyType) value,
    if (useValue) {
      sw.print("null, ");
      sw.print("(");
      sw.print(this.getQualifiedSourceNonPrimitiveType(
          this.beanHelper.getElementType(ppropertyDescription, false)));
      sw.print(") value");
    } else {
      sw.print("object, ");
      final JMethod method = this.beanType.findMethod(asGetter(ppropertyDescription), NO_ARGS);
      if (method.isPublic()) {
        sw.print("object.");
        sw.print(asGetter(ppropertyDescription));
        sw.print("()");
      } else {
        this.gettersToWrap.add(method);
        sw.print(this.toWrapperName(method) + "(object)");
      }
    }
    sw.print(", ");

    // honorValid, groups);
    sw.print(Boolean.toString(honorValid));
    sw.println(", groups);");
  }

  private void writeValidateInheritance(final SourceWriter sw, final Class<?> clazz,
      final Stage stage, final PropertyDescriptor property) throws UnableToCompleteException {
    this.writeValidateInheritance(sw, clazz, stage, property, false, "groups");
  }

  private void writeValidateInheritance(final SourceWriter sw, final Class<?> clazz,
      final Stage stage, final PropertyDescriptor property,
      final boolean expandDefaultGroupSequence, final String groupsVarName)
      throws UnableToCompleteException {
    this.writeValidateInterfaces(sw, clazz, stage, property, expandDefaultGroupSequence,
        groupsVarName);
    final Class<?> superClass = clazz.getSuperclass();
    if (superClass != null) {
      this.writeValidatorCall(sw, superClass, stage, property, expandDefaultGroupSequence,
          groupsVarName);
    }
  }

  private void writeValidateInterfaces(final SourceWriter sw, final Class<?> clazz,
      final Stage stage, final PropertyDescriptor ppropertyDescription,
      final boolean expandDefaultGroupSequence, final String groupsVarName)
      throws UnableToCompleteException {
    for (final Class<?> type : clazz.getInterfaces()) {
      this.writeValidatorCall(sw, type, stage, ppropertyDescription, expandDefaultGroupSequence,
          groupsVarName);
      this.writeValidateInterfaces(sw, type, stage, ppropertyDescription,
          expandDefaultGroupSequence, groupsVarName);
    }
  }

  private void writeValidateIterable(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription) {
    // int i = 0;
    sw.println("int i = 0;");

    // for (Object instance : value) {
    sw.println("for(Object instance : value) {");
    sw.indent();

    // if(instance != null && !context.alreadyValidated(instance)) {
    sw.println(" if (instance != null  && !context.alreadyValidated(instance)) {");
    sw.indent();

    // violations.addAll(
    sw.println("violations.addAll(");
    sw.indent();
    sw.indent();

    // context.getValidator().validate(
    sw.println("context.getValidator().validate(");
    sw.indent();
    sw.indent();

    final Class<?> elementClass = ppropertyDescription.getElementClass();
    if (elementClass.isArray() || List.class.isAssignableFrom(elementClass)) {
      // context.appendIndex("myProperty",i++),
      sw.print("context.appendIndex(\"");
      sw.print(ppropertyDescription.getPropertyName());
      sw.println("\",i),");
    } else {
      // context.appendIterable("myProperty"),
      sw.print("context.appendIterable(\"");
      sw.print(ppropertyDescription.getPropertyName());
      sw.println("\"),");
    }

    // instance, groups));
    sw.println("instance, groups));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");

    // i++;
    sw.println("i++;");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateMap(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription) {
    // for (Entry<?, Type> entry : value.entrySet()) {
    sw.print("for(");
    sw.print(Entry.class.getCanonicalName());
    sw.println("<?, ?> entry : value.entrySet()) {");
    sw.indent();

    // if(entry.getValue() != null &&
    // !context.alreadyValidated(entry.getValue())) {
    sw.println(" if (entry.getValue() != null && !context.alreadyValidated(entry.getValue())) {");
    sw.indent();

    // violations.addAll(
    sw.println("violations.addAll(");
    sw.indent();
    sw.indent();

    // context.getValidator().validate(
    sw.println("context.getValidator().validate(");
    sw.indent();
    sw.indent();

    // context.appendKey("myProperty",entry.getKey()),
    sw.print("context.appendKey(\"");
    sw.print(ppropertyDescription.getPropertyName());
    sw.println("\",entry.getKey()),");

    // entry.getValue(), groups));
    sw.println("entry.getValue(), groups));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidatePropertyCall(final SourceWriter sw, final PropertyDescriptor property,
      final boolean useValue, final boolean honorValid) {
    if (useValue) {
      // boolean valueTypeMatches = false;
      sw.println("boolean valueTypeMatches = false;");
    }
    if (this.beanHelper.hasGetter(property)) {
      if (useValue) {
        // if ( value == null || value instanceof propertyType) {
        sw.print("if (value == null || value instanceof ");
        sw.print(this
            .getQualifiedSourceNonPrimitiveType(this.beanHelper.getElementType(property, false)));
        sw.println(") {");
        sw.indent();

        // valueTypeMatches = true;
        sw.println("valueTypeMatches = true;");
      }
      // validate_getMyProperty
      this.writeValidateGetterCall(sw, property, useValue, honorValid);
      if (useValue) {
        // }
        sw.outdent();
        sw.println("}");
      }
    }

    if (this.beanHelper.hasField(property)) {
      if (useValue) {
        // if ( value == null || value instanceof propertyType) {
        sw.print("if ( value == null || value instanceof ");
        sw.print(this
            .getQualifiedSourceNonPrimitiveType(this.beanHelper.getElementType(property, true)));
        sw.println(") {");
        sw.indent();

        // valueTypeMatches = true;
        sw.println("valueTypeMatches = true;");
      }
      // validate_myProperty
      this.writeValidateFieldCall(sw, property, useValue, honorValid);
      if (useValue) {
        // } else
        sw.outdent();
        sw.println("}");
      }
    }

    if (useValue && (this.beanHelper.hasGetter(property) || this.beanHelper.hasField(property))) {
      // if(!valueTypeMatches) {
      sw.println("if (!valueTypeMatches)  {");
      sw.indent();

      // throw new ValidationException(value.getClass +
      // " is not a valid type for " + propertyName);
      sw.print("throw new ValidationException");
      sw.println("(value.getClass() +\" is not a valid type for \"+ propertyName);");

      // }
      sw.outdent();
      sw.println("}");
    }
  }

  private void writeValidatePropertyGroups(final SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validatePropertyGroups(
    sw.println("public <T> void validatePropertyGroups(");

    // GwtValidationContext<T> context, BeanType object, String propertyName,
    // Set<ConstraintViolation<T>> violations, Class<?>... groups) throws ValidationException {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");
    sw.println("String propertyName,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) throws ValidationException {");
    sw.outdent();

    for (final PropertyDescriptor property : this.beanHelper.getBeanDescriptor()
        .getConstrainedProperties()) {
      // if (propertyName.equals(myPropety)) {
      sw.print("if (propertyName.equals(\"");
      sw.print(property.getPropertyName());
      sw.println("\")) {");
      sw.indent();

      this.writeValidatePropertyCall(sw, property, false, false);

      // validate all super classes and interfaces
      this.writeValidateInheritance(sw, this.beanHelper.getClazz(), Stage.PROPERTY, property);

      // }
      sw.outdent();
      sw.print("} else ");
    }

    this.writeIfPropertyNameNotFound(sw);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidatePropertyMethod(final SourceWriter sw,
      final PropertyDescriptor ppropertyDescription, final boolean useField)
      throws UnableToCompleteException {
    final Class<?> elementClass = ppropertyDescription.getElementClass();
    final JType elementType = this.beanHelper.getElementType(ppropertyDescription, useField);

    // private final <T> void validateProperty_{get}<p>(
    sw.print("private final <T> void ");
    if (useField) {
      sw.print(this.validateMethodFieldName(ppropertyDescription));
    } else {
      sw.print(this.validateMethodGetterName(ppropertyDescription));
    }
    sw.println("(");
    sw.indent();
    sw.indent();

    // final GwtValidationContext<T> context,
    sw.println("final GwtValidationContext<T> context,");

    // final Set<ConstraintViolation<T>> violations,
    sw.println("final Set<ConstraintViolation<T>> violations,");

    // BeanType object,
    sw.println(this.beanHelper.getTypeCanonicalName() + " object,");

    // final <Type> value,
    sw.print("final ");
    sw.print(elementType.getParameterizedQualifiedSourceName());
    sw.println(" value,");

    // boolean honorValid,
    sw.println("boolean honorValid,");

    // Class<?>... groups) {
    sw.println("Class<?>... groups) {");
    sw.outdent();

    // only write the checks if the property is constrained in some way
    if (this.isPropertyConstrained(ppropertyDescription, useField)) {
      // context = context.append("myProperty");
      sw.print("final GwtValidationContext<T> myContext = context.append(\"");
      sw.print(ppropertyDescription.getPropertyName());
      sw.println("\");");

      // only check this property if the TraversableResolver says we can

      // Node leafNode = myContext.getPath().getLeafNode();
      sw.println("Node leafNode = myContext.getPath().getLeafNode();");
      // PathImpl path = myContext.getPath().getPathWithoutLeafNode();
      sw.println("PathImpl path = myContext.getPath().getPathWithoutLeafNode();");
      // boolean isReachable;
      sw.println("boolean isReachable;");
      // try {
      sw.println("try {");
      sw.indent();
      // isReachable = myContext.getTraversableResolver().isReachable(object, leafNode,
      // myContext.getRootBeanClass(), path, ElementType);
      sw.println("isReachable = myContext.getTraversableResolver().isReachable(object, "
          + "leafNode, myContext.getRootBeanClass(), path, "
          + (useField ? asLiteral(ElementType.FIELD) : asLiteral(ElementType.METHOD)) + ");");
      // } catch (Exception e) {
      sw.outdent();
      sw.println("} catch (Exception e) {");
      sw.indent();
      // throw new ValidationException("TraversableResolver isReachable caused an exception", e);
      sw.println("throw new ValidationException(\"TraversableResolver isReachable caused an "
          + "exception\", e);");
      // }
      sw.outdent();
      sw.println("}");
      // if (isReachable) {
      sw.println("if (isReachable) {");
      sw.indent();

      // TODO(nchalko) move this out of here to the Validate method
      if (ppropertyDescription.isCascaded() && this.hasValid(ppropertyDescription, useField)) {

        // if (honorValid && value != null) {
        sw.println("if (honorValid && value != null) {");
        sw.indent();
        // boolean isCascadable;
        sw.println("boolean isCascadable;");
        // try {
        sw.println("try {");
        sw.indent();
        // isCascadable = myContext.getTraversableResolver().isCascadable(object, leafNode,
        // myContext.getRootBeanClass(), path, ElementType)
        sw.println("isCascadable = myContext.getTraversableResolver().isCascadable(object, "
            + "leafNode, myContext.getRootBeanClass(), path, "
            + (useField ? asLiteral(ElementType.FIELD) : asLiteral(ElementType.METHOD)) + ");");
        // } catch (Exception e) {
        sw.outdent();
        sw.println("} catch (Exception e) {");
        sw.indent();
        // throw new ValidationException("TraversableResolver isReachable caused an exception", e);
        sw.println("throw new ValidationException(\"TraversableResolver isCascadable caused an "
            + "exception\", e);");
        // }
        sw.outdent();
        sw.println("}");
        // if (isCascadable) {
        sw.println("if (isCascadable) {");
        sw.indent();

        if (isIterableOrMap(elementClass)) {
          final JClassType associationType =
              this.beanHelper.getAssociationType(ppropertyDescription, useField);
          this.createBeanHelper(associationType);
          if (Map.class.isAssignableFrom(elementClass)) {
            this.writeValidateMap(sw, ppropertyDescription);
          } else {
            this.writeValidateIterable(sw, ppropertyDescription);
          }
        } else {
          this.createBeanHelper(elementClass);

          // if (!context.alreadyValidated(value)) {
          sw.println(" if (!context.alreadyValidated(value)) {");
          sw.indent();

          // violations.addAll(myContext.getValidator().validate(context, value,
          // groups));
          sw.print("violations.addAll(");
          sw.println("myContext.getValidator().validate(myContext, value, groups));");

          // }
          sw.outdent();
          sw.println("}");
        }

        // }
        sw.outdent();
        sw.println("}");
        // }
        sw.outdent();
        sw.println("}");
      }

      // It is possible for an annotation with the exact same values to be set on
      // both the field and the getter.
      // Keep track of the ones we have used to make sure we don't duplicate.
      final Set<Object> includedAnnotations = Sets.newHashSet();
      int count = 0;
      for (final ConstraintDescriptor<?> constraint : ppropertyDescription
          .getConstraintDescriptors()) {
        if (this.areConstraintDescriptorGroupsValid(constraint)) {
          final Object annotation = constraint.getAnnotation();
          if (this.hasMatchingAnnotation(ppropertyDescription, useField, constraint)) {
            final String constraintDescriptorVar =
                this.constraintDescriptorVar(ppropertyDescription.getPropertyName(), count);
            if (includedAnnotations.contains(annotation)) {
              // The annotation has been looked at once already during this validate property call
              // so we know the field and the getter are both annotated with the same constraint.
              if (!useField) {
                this.writeValidateConstraint(sw, ppropertyDescription, elementClass, constraint,
                    constraintDescriptorVar);
              }
            } else {
              if (useField) {
                this.writeValidateConstraint(sw, ppropertyDescription, elementClass, constraint,
                    constraintDescriptorVar);
              } else {
                // The annotation hasn't been looked at twice (yet) and we are validating a getter
                // Write the call if only the getter has this constraint applied to it
                final boolean hasField = this.beanHelper.hasField(ppropertyDescription);
                if (!hasField || hasField
                    && !this.hasMatchingAnnotation(ppropertyDescription, true, constraint)) {
                  this.writeValidateConstraint(sw, ppropertyDescription, elementClass, constraint,
                      constraintDescriptorVar);
                }
              }
            }
            includedAnnotations.add(annotation);
          }
          count++;
        }
      }
      // }
      sw.outdent();
      sw.println("}");
    }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateValueGroups(final SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validateValueGroups(
    sw.println("public <T> void validateValueGroups(");

    // GwtValidationContext<T> context, Class<Author> beanType, String propertyName,
    // Object value, Set<ConstraintViolation<T>> violations, Class<?>... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println("Class<" + this.beanHelper.getTypeCanonicalName() + "> beanType,");
    sw.println("String propertyName,");
    sw.println("Object value,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    for (final PropertyDescriptor property : this.beanHelper.getBeanDescriptor()
        .getConstrainedProperties()) {
      // if (propertyName.equals(myPropety)) {
      sw.print("if (propertyName.equals(\"");
      sw.print(property.getPropertyName());
      sw.println("\")) {");
      sw.indent();

      if (!isIterableOrMap(property.getElementClass())) {
        this.writeValidatePropertyCall(sw, property, true, false);
      }

      // validate all super classes and interfaces
      this.writeValidateInheritance(sw, this.beanHelper.getClazz(), Stage.VALUE, property);

      // }
      sw.outdent();
      sw.print("} else ");
    }

    this.writeIfPropertyNameNotFound(sw);

    sw.outdent();
    sw.println("}");
  }

  /**
   * write validator call.
   * 
   * @param ppropertyDescription Only used if writing a call to validate a property - otherwise can
   *        be null.
   * @param expandDefaultGroupSequence Only used if writing a call to validate a bean.
   * @param groupsVarName The name of the variable containing the groups.
   */
  private void writeValidatorCall(final SourceWriter sw, final Class<?> type, final Stage stage,
      final PropertyDescriptor ppropertyDescription, final boolean expandDefaultGroupSequence,
      final String groupsVarName) throws UnableToCompleteException {
    if (this.cache.isClassConstrained(type) && !isIterableOrMap(type)) {
      final BeanHelper helper = this.createBeanHelper(type);
      this.beansToValidate.add(helper);
      switch (stage) {
        case OBJECT:
          // myValidator
          sw.print(helper.getValidatorInstanceName());
          if (expandDefaultGroupSequence) {
            // .expandDefaultAndValidateClassGroups(context,object,violations,groups);
            sw.println(".expandDefaultAndValidateClassGroups(context, object, violations, "
                + groupsVarName + ");");
          } else {
            // .validateClassGroups(context,object,violations,groups);
            sw.println(".validateClassGroups(context, object, violations, " + groupsVarName + ");");
          }
          break;
        case PROPERTY:
          if (this.isPropertyConstrained(helper, ppropertyDescription)) {
            // myValidator.validatePropertyGroups(context,object
            // ,propertyName, violations, groups);
            sw.print(helper.getValidatorInstanceName());
            sw.print(".validatePropertyGroups(context, object,");
            sw.println(" propertyName, violations, " + groupsVarName + ");");
          }
          break;
        case VALUE:
          if (this.isPropertyConstrained(helper, ppropertyDescription)) {
            // myValidator.validateValueGroups(context,beanType
            // ,propertyName, value, violations, groups);
            sw.print(helper.getValidatorInstanceName());
            sw.print(".validateValueGroups(context, ");
            sw.print(helper.getTypeCanonicalName());
            sw.println(".class, propertyName, value, violations, " + groupsVarName + ");");
          }
          break;
        default:
          throw new IllegalStateException();
      }
    }
  }

  private void writeWrappers(final SourceWriter sw) {
    sw.println("// Write the wrappers after we know which are needed");
    for (final JField field : this.fieldsToWrap) {
      this.writeFieldWrapperMethod(sw, field);
      sw.println();
    }

    for (final JMethod method : this.gettersToWrap) {
      this.writeGetterWrapperMethod(sw, method);
      sw.println();
    }
  }
}
