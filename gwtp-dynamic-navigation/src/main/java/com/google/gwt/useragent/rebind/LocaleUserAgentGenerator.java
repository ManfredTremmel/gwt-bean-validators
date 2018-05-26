package com.google.gwt.useragent.rebind;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.SelectionProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;

/**
 * Generator for {@link com.google.gwt.useragent.client.UserAgent}. Copy of Googles
 * UserAgentGenerator, with different fallback value (not null, use safari instead).
 */
public class LocaleUserAgentGenerator extends Generator {
  static final String PROPERTY_USER_AGENT = "user.agent";

  @Override
  public String generate(final TreeLogger logger, final GeneratorContext context,
      final String typeName) throws UnableToCompleteException {
    final TypeOracle typeOracle = context.getTypeOracle();

    JClassType userType;
    try {
      userType = typeOracle.getType(typeName);
    } catch (final NotFoundException e) {
      logger.log(TreeLogger.ERROR, "Unable to find metadata for type: " + typeName, e);
      throw new UnableToCompleteException(); // NOPMD
    }
    final String packageName = userType.getPackage().getName();
    String className = userType.getName();
    className = className.replace('.', '_');

    if (userType.isInterface() == null) {
      logger.log(TreeLogger.ERROR, userType.getQualifiedSourceName() + " is not an interface",
          null);
      throw new UnableToCompleteException();
    }

    final PropertyOracle propertyOracle = context.getPropertyOracle();

    String userAgentValue;
    SelectionProperty selectionProperty;
    try {
      selectionProperty =
          propertyOracle.getSelectionProperty(logger, LocaleUserAgentGenerator.PROPERTY_USER_AGENT);
      userAgentValue = selectionProperty.getCurrentValue();
    } catch (final BadPropertyValueException e) {
      logger.log(TreeLogger.ERROR,
          "Unable to find value for '" + LocaleUserAgentGenerator.PROPERTY_USER_AGENT + "'", e);
      throw new UnableToCompleteException(); // NOPMD
    }

    final String userAgentValueInitialCap = StringUtils.capitalize(userAgentValue);
    className = className + "Impl" + userAgentValueInitialCap; // NOPMD

    final ClassSourceFileComposerFactory composerFactory =
        new ClassSourceFileComposerFactory(packageName, className);
    composerFactory.addImplementedInterface(userType.getQualifiedSourceName());

    final PrintWriter pw = context.tryCreate(logger, packageName, className);
    if (pw != null) {
      final SourceWriter sw = composerFactory.createSourceWriter(context, pw);

      sw.println();
      sw.println("public native String getRuntimeValue() /*-{");
      sw.indent();
      UserAgentPropertyGenerator.writeUserAgentPropertyJavaScript(sw,
          selectionProperty.getPossibleValues(), "safari");
      sw.outdent();
      sw.println("}-*/;");
      sw.println();

      sw.println();
      sw.println("public String getCompileTimeValue() {");
      sw.indent();
      sw.println("return \"" + userAgentValue.trim() + "\";");
      sw.outdent();
      sw.println("}");

      sw.commit(logger);
    }
    return composerFactory.getCreatedClassName();
  }
}
