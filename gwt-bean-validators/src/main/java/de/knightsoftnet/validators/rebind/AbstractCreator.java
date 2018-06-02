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

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.user.rebind.AbstractSourceCreator;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;

/**
 * Abstract Class for Creating source files.
 * <p>
 * This class is not thread safe.
 * </p>
 */
public abstract class AbstractCreator extends AbstractSourceCreator {

  final GeneratorContext context;

  final TreeLogger logger;

  final JClassType validatorType;

  final BeanHelperCache cache;

  AbstractCreator(final GeneratorContext context, final TreeLogger logger,
      final JClassType validatorType, final BeanHelperCache cache) {
    super();
    this.context = context;
    this.logger = branch(logger, "Creating " + validatorType);
    this.validatorType = validatorType;
    this.cache = cache;
  }

  /**
   * create logger.
   *
   * @return qualified name
   * @throws UnableToCompleteException if fails
   */
  public final String create() throws UnableToCompleteException {
    final SourceWriter sourceWriter = getSourceWriter(logger, context);
    if (sourceWriter != null) {
      writeClassBody(sourceWriter);
      sourceWriter.commit(logger);
    }
    return getQualifiedName();
  }

  protected void addImports(final ClassSourceFileComposerFactory composerFactory,
      final Class<?>... imports) {
    for (final Class<?> imp : imports) {
      composerFactory.addImport(imp.getCanonicalName());
    }
  }

  protected abstract void compose(ClassSourceFileComposerFactory composerFactory);

  protected BeanHelper createBeanHelper(final Class<?> clazz) throws UnableToCompleteException {
    return cache.createHelper(clazz, logger, context);
  }

  protected BeanHelper createBeanHelper(final JClassType jtype) throws UnableToCompleteException {
    return cache.createHelper(jtype, logger, context);
  }

  protected final String getPackage() {
    final JPackage serviceIntfPkg = validatorType.getPackage();
    return serviceIntfPkg == null ? "" : serviceIntfPkg.getName();
  }

  protected String getSimpleName() {
    final int length = getPackage().length();
    final String rawName =
        validatorType.getQualifiedSourceName().substring(length == 0 ? 0 : length + 1);
    return rawName.replace('.', '_') + "Impl";
  }

  protected abstract void writeClassBody(SourceWriter sourceWriter)
      throws UnableToCompleteException;

  private String getQualifiedName() {
    final String packageName = getPackage();
    return (StringUtils.isEmpty(packageName) ? StringUtils.EMPTY : packageName + ".")
        + getSimpleName();
  }

  private SourceWriter getSourceWriter(final TreeLogger logger, final GeneratorContext ctx) {
    final String packageName = getPackage();
    final String simpleName = getSimpleName();
    final PrintWriter printWriter = ctx.tryCreate(logger, packageName, simpleName);
    if (printWriter == null) {
      return null;
    }

    final ClassSourceFileComposerFactory composerFactory =
        new ClassSourceFileComposerFactory(packageName, simpleName);
    compose(composerFactory);
    return composerFactory.createSourceWriter(ctx, printWriter);
  }
}
