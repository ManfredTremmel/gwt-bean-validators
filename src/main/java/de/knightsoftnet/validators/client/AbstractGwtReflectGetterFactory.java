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

package de.knightsoftnet.validators.client;

/**
 * Abstract GetterFactory that delegates to a GWT generated class which enables reflection like
 * access to the getters of the beans.
 *
 * <p>
 * Extend this class create and implement createGwtReflectGetter
 * </p>
 *
 * <pre>
 * public class MyReflectGetterFactory extends AbstractGwtReflectGetterFactory {
 *   &#064;GwtValidation(value = {Pojo.class, Other.class})
 *   public static interface GwtReflectGetters extends GwtReflectGetterInterface {
 *   }
 * 
 *   public GwtReflectGetterInterface createGwtReflectGetter() {
 *     return GWT.create(GwtReflectGetters.class));
 *   }
 * }
 * 
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * </pre>
 *
 * <p>
 * Then add a line like this to your Gwt Module config (gwt.xml) file.
 * </p>
 *
 * <pre>
 * &lt;replace-with class="com.example.MyReflectGetterFactory">
 *   &lt;when-type-is class="de.knightsoftnet.validators.client.AbstractGwtReflectGetterFactory"/>
 * &lt;/replace-with>
 * </pre>
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractGwtReflectGetterFactory implements GwtReflectGetterFactoryInterface {

  private GwtReflectGetterInterface gwtReflectGetter = null;

  @Override
  public abstract GwtReflectGetterInterface createGwtReflectGetter();

  @Override
  public final GwtReflectGetterInterface getGwtReflectGetter() {
    if (this.gwtReflectGetter == null) {
      this.gwtReflectGetter = this.createGwtReflectGetter();
    }
    return this.gwtReflectGetter;
  }
}
