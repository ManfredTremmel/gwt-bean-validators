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

package de.knightsoftnet.navigation.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;
import java.util.ResourceBundle;

/**
 * this class creates a implementation of the VersionInfoInterface with data from maven's pom.xml.
 *
 * @author Manfred Tremmel
 *
 */
public class VersionInfoGenerator extends Generator {

  @Override
  public final String generate(final TreeLogger plogger, final GeneratorContext pcontext,
      final String ptypeName) throws UnableToCompleteException {
    try {
      final JClassType classType = pcontext.getTypeOracle().getType(ptypeName);

      // Here you would retrieve the metadata based on typeName for this class
      final SourceWriter src = this.getSourceWriter(classType, pcontext, plogger);

      if (src != null) {
        final ResourceBundle bundle = ResourceBundle.getBundle("Version");
        final String version = bundle.getString("application.version");
        final String buildTimeString = bundle.getString("build.timestamp");
        final String copyright = bundle.getString("application.copyright");
        final String author = bundle.getString("application.author");

        src.println("@Override");
        src.println("public final SafeHtml getCopyrightText() {");
        src.println("  return SafeHtmlUtils.fromString(\"© " + copyright + "\");");
        src.println("}");

        src.println("@Override");
        src.println("public final SafeHtml getVersionNumber() {");
        src.println("  return SafeHtmlUtils.fromString(\"" + version + "\");");
        src.println("}");

        src.println("@Override");
        src.println("public final SafeHtml getVersionDate() {");
        src.println("  return SafeHtmlUtils.fromString(this.parseAndFormatDate(\"" + buildTimeString
            + "\"));");
        src.println("}");

        src.println("@Override");
        src.println("public final SafeHtml getAuthor() {");
        src.println("  return SafeHtmlUtils.fromString(\"" + author + "\");");
        src.println("}");

        src.commit(plogger);

        System.out.println("Generating for: " + ptypeName);
      }
      return ptypeName + "Generated";
    } catch (final NotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * get source writer for the generator.
   *
   * @param pclassType class type
   * @param pcontext generator context
   * @param plogger tree logger
   * @return SourceWriter to write the generated sources to
   */
  public final SourceWriter getSourceWriter(final JClassType pclassType,
      final GeneratorContext pcontext, final TreeLogger plogger) {
    final String packageName = pclassType.getPackage().getName();
    final String simpleName = pclassType.getSimpleSourceName() + "Generated";
    final ClassSourceFileComposerFactory composer =
        new ClassSourceFileComposerFactory(packageName, simpleName);
    composer.setSuperclass(pclassType.getName());

    // Need to add whatever imports your generated class needs.
    composer.addImport("com.google.gwt.safehtml.shared.SafeHtml");
    composer.addImport("com.google.gwt.safehtml.shared.SafeHtmlUtils");

    final PrintWriter printWriter = pcontext.tryCreate(plogger, packageName, simpleName);
    if (printWriter == null) {
      return null;
    }
    return composer.createSourceWriter(pcontext, printWriter);
  }
}
