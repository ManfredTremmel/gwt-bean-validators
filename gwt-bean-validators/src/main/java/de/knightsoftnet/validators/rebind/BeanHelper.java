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

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JRawType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.thirdparty.guava.common.base.Function;

import org.apache.commons.lang3.StringUtils;

import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.PropertyDescriptor;

/**
 * A simple struct for the various values associated with a Bean that can be validated.
 */
public final class BeanHelper {

  public static final Function<BeanHelper, Class<?>> TO_CLAZZ =
      helper -> helper == null ? null : helper.getClazz();

  private final BeanDescriptor beanDescriptor;

  private final JClassType jclass;

  private final Class<?> clazz;

  /**
   * Shouldn't be created directly; instead use BeanHelperCache.
   */
  BeanHelper(final JClassType pjclass, final Class<?> pclazz,
      final BeanDescriptor pbeanDescriptor) {
    beanDescriptor = pbeanDescriptor;
    jclass = pjclass;
    clazz = pclazz;
  }

  /**
   * get association type.
   *
   * @param ppropertyDescriptor property description
   * @param puseField use field
   * @return JClassType
   */
  public JClassType getAssociationType(final PropertyDescriptor ppropertyDescriptor,
      final boolean puseField) {
    final JType type = getElementType(ppropertyDescriptor, puseField);
    if (type == null) {
      return null;
    }
    final JArrayType jarray = type.isArray();
    if (jarray != null) {
      return jarray.getComponentType().isClassOrInterface();
    }
    final JParameterizedType jptype = type.isParameterized();
    JClassType[] typeArgs;
    if (jptype == null) {
      final JRawType jrtype = type.isRawType();
      typeArgs = jrtype.getGenericType().getTypeParameters();
    } else {
      typeArgs = jptype.getTypeArgs();
    }
    // it is either a Iterable or a Map use the last type arg.
    return typeArgs[typeArgs.length - 1].isClassOrInterface();
  }

  public BeanDescriptor getBeanDescriptor() {
    return beanDescriptor;
  }

  /*
   * The server-side validator needs an actual class.
   */
  public Class<?> getClazz() {
    return clazz;
  }

  public String getFullyQualifiedValidatorName() {
    return getPackage() + "." + getValidatorName();
  }

  public JClassType getJClass() {
    return jclass;
  }

  public String getPackage() {
    return jclass.getPackage().getName();
  }

  public String getTypeCanonicalName() {
    return jclass.getQualifiedSourceName();
  }

  public String getValidatorInstanceName() {
    return getFullyQualifiedValidatorName() + ".INSTANCE";
  }

  public String getValidatorName() {
    return makeJavaSafe("_" + jclass.getName() + "Validator");
  }

  @Override
  public String toString() {
    return getTypeCanonicalName();
  }

  JType getElementType(final PropertyDescriptor ppropertyDescriptor, final boolean puseField) {
    if (puseField) {
      final JField field = findRecursiveField(jclass, ppropertyDescriptor.getPropertyName());
      if (field == null) {
        return null;
      }
      return field.getType();
    } else {
      final JMethod method =
          findRecursiveMethod(jclass, GwtSpecificValidatorCreator.asGetter(ppropertyDescriptor),
              GwtSpecificValidatorCreator.NO_ARGS);
      if (method == null) {
        return null;
      }
      return method.getReturnType();
    }
  }

  private JMethod findRecursiveMethod(final JClassType pjclass, final String pasGetter,
      final JType[] pnoArgs) {
    if (pjclass == null || pasGetter == null) {
      return null;
    }
    final JMethod method = pjclass.findMethod(pasGetter, pnoArgs);
    if (method == null) {
      return findRecursiveMethod(pjclass.getSuperclass(), pasGetter, pnoArgs);
    }
    return method;
  }

  private JField findRecursiveField(final JClassType pjclass, final String ppropertyName) {
    if (pjclass == null || ppropertyName == null) {
      return null;
    }
    final JField field = pjclass.findField(ppropertyName);
    if (field == null) {
      return findRecursiveField(pjclass.getSuperclass(), ppropertyName);
    }
    return field;
  }

  boolean hasField(final PropertyDescriptor ppropertyDescriptor) {
    final JField field = jclass.findField(ppropertyDescriptor.getPropertyName());
    return field != null;
  }

  boolean hasGetter(final PropertyDescriptor ppropertyDescriptor) {
    final JType[] paramTypes = new JType[] {};
    try {
      jclass.getMethod(GwtSpecificValidatorCreator.asGetter(ppropertyDescriptor), paramTypes);
      return true;
    } catch (final NotFoundException e) {
      return false;
    }
  }

  private String makeJavaSafe(final String in) {
    return StringUtils.replaceChars(in, '.', '_');
  }
}
