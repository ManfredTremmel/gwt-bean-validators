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

package de.knightsoftnet.validators.client;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.groups.Default;

/**
 * Annotates a {@code javax.validation.Validator} explicitly listing the classes that can be
 * validated in GWT.
 * <p>
 * Define the Validator you want, explicitly listing the classes and groups you want to validate.
 * </p>
 *
 * <pre>
 * &#064;GwtValidation(value = {MyBean.class, MyOther.class}, <br>
 * groups = {Default.class, OtherGroup.class})
 * public interface MyValidator extends javax.validation.Validator {
 * }
 * </pre>
 *
 * <p>
 * Create and use the validator.
 * </p>
 *
 * <pre>
 * MyValidator validator = GWT.create(MyValidator.class);
 * MyBean bean = new MyBean();
 * ...
 * Set&lt;ConstraintViolation&lt;MyBean&gt;&gt; violations = validator.validate(bean);
 * </pre>
 *
 * <p>
 * You must list all validation groups you are using (as well as groups making up a group
 * sequence)&ndash; unless you are only using the Default group, in which case you may omit the
 * "groups" field of the {@link GwtValidation} annotation.
 * </p>
 *
 * <p>
 * NOTE: Validation is done using only the Constraints found on the Classes listed in the
 * annotation. If you have
 * </p>
 *
 * <pre>
 * class MyBean {
 *   &#064;Null
 *   String getName() {
 *     return name;
 *   }
 * }
 *
 *
 * class MySubBean extends MyBean {
 *   &#064;Size(min = 5)
 *   String getName() {
 *     return super.getName();
 *   }
 * }
 * </pre>
 *
 * <p>
 * And then create your {@link javax.validation.ValidatorFactory ValidatorFactory} using
 * </p>
 *
 * <pre>
 * &#64;GwtValidation(MyBean.class, MyOther.class)}
 * </pre>
 *
 * <p>
 * but call validator with the subclass like
 * </p>
 *
 * <pre>
 * MySubBean bean = new MySubBean();
 * Set&lt;ConstraintViolation&lt;MyBean&gt;&gt; violations = validator.validate(bean);
 * </pre>
 *
 * <p>
 * The {@code Size} constraint will not be validated.
 * </p>
 *
 * <p>
 * Instead make sure you list the all BeanTypes that will be directly validated in the
 * {@link GwtValidation} annotation.
 * </p>
 *
 *
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface GwtValidation {

  /**
   * The list of Groups which can be processed by the annotated {@code Validator}. The default value
   * is {@link Default}. An empty array is illegal.
   *
   * @return array of classes
   */
  Class<?>[] groups() default {Default.class};

  /**
   * The list of Classes which can be validated by the annotated {@code Validator}.
   *
   * @return array of classes
   */
  Class<?>[] value();

  /**
   * The list of Classes which can be used a reflection getter, if empty all values are taken.
   *
   * @return array of classes
   */
  Class<?>[] reflect() default {};
}
