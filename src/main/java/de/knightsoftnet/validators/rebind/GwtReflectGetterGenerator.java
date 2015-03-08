package de.knightsoftnet.validators.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.validation.client.GwtValidation;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * this class generates a simple class to get properties of all validated classes by name.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtReflectGetterGenerator extends Generator {

  @Override
  public final String generate(final TreeLogger plogger, final GeneratorContext pcontext,
      final String ptypeName) throws UnableToCompleteException {
    try {
      plogger.log(TreeLogger.DEBUG, "Start generating for " + ptypeName + ".");

      final JClassType classType = pcontext.getTypeOracle().getType(ptypeName);
      final TypeOracle typeOracle = pcontext.getTypeOracle();
      assert typeOracle != null;
      final JClassType reflectorType = this.findType(plogger, typeOracle, ptypeName);

      // here you would retrieve the metadata based on typeName for this class
      final SourceWriter src = this.getSourceWriter(classType, pcontext, plogger);

      // generator is called more then once in a build, with second call, we don't get
      // a writer and needn't generate the class once again
      if (src != null) {
        final Class<?>[] classes = this.getBeans(plogger, reflectorType);

        src.println("@Override");
        src.println("public final Object getProperty(final Object pbean, final String pname)"
            + " throws NoSuchMethodException, ReflectiveOperationException {");

        src.println("  if (pbean == null) {");
        src.println("    throw new NoSuchMethodException(\"A null object has no getters\");");
        src.println("  }");
        src.println("  if (pname == null) {");
        src.println("    throw new NoSuchMethodException(\"No method to get property for null\");");
        src.println("  }");
        src.println("");

        for (final Class<?> clazz : classes) {
          final String className = clazz.getName();
          plogger.log(TreeLogger.DEBUG, "Generating getter reflections for class " + className);

          // Describe the bean properties
          final PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(clazz);

          src.println("  if (pbean.getClass() == " + className + ".class) {");
          src.println("    switch (pname) {");

          // for all getters generate a case and return entry
          for (final PropertyDescriptor property : properties) {

            final Method readMethod = property.getReadMethod();
            final String name = property.getName();
            if (readMethod == null) {
              continue; // If the property cannot be read
            }
            plogger.log(TreeLogger.DEBUG, "Add getter for property " + name);

            // Invoke the getter on the bean
            src.println("      case \"" + name + "\":");
            src.println("        return ((" + className + ") pbean)." + readMethod.getName()
                + "();");
          }

          src.println("      default:");
          src.println("        throw new NoSuchMethodException(\"Class " + className
              + " has no getter for porperty \" + pname);");
          src.println("    }");
          src.println("  }");
        }
        src.println("  throw new ReflectiveOperationException(\"Class \" + "
            + "pbean.getClass().getName() + \" is not reflected\");");
        src.println("}");

        plogger.log(TreeLogger.DEBUG, "End of generating reached");

        src.commit(plogger);
      }
      return this.getClassPackage(classType) + "." + this.getClassName(classType);
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
  private SourceWriter getSourceWriter(final JClassType pclassType,
      final GeneratorContext pcontext, final TreeLogger plogger) {
    final String packageName = this.getClassPackage(pclassType);
    final String simpleName = this.getClassName(pclassType);
    final ClassSourceFileComposerFactory composer =
        new ClassSourceFileComposerFactory(packageName, simpleName);
    composer
        .addImplementedInterface("de.knightsoftnet.validators.client.GwtReflectGetterInterface");

    final PrintWriter printWriter = pcontext.tryCreate(plogger, packageName, simpleName);
    if (printWriter == null) {
      return null;
    }
    return composer.createSourceWriter(pcontext, printWriter);
  }

  /**
   * get class package in which we generate class.
   *
   * @param pclassType JClassType of the original initiator
   * @return class path
   */
  private String getClassPackage(final JClassType pclassType) {
    return pclassType.getPackage().getName();
  }

  /**
   * get class name of the generated class, we use original name and add a "Generated".
   *
   * @param pclassType JClassType of the original initiator
   * @return class name
   */
  private String getClassName(final JClassType pclassType) {
    return pclassType.getSimpleSourceName() + "Generated";
  }

  /**
   * lets find the type of the class.
   *
   * @param plogger logger
   * @param ptypeOracle type oracle
   * @param ptypeName type name
   * @return class type
   * @throws UnableToCompleteException if type can't be detected
   */
  private JClassType findType(final TreeLogger plogger, final TypeOracle ptypeOracle,
      final String ptypeName) throws UnableToCompleteException {
    final JClassType result = ptypeOracle.findType(ptypeName);
    if (result == null) {
      plogger.log(TreeLogger.ERROR, "Unable to find metadata for type '" + ptypeName + "'", null);
      throw new UnableToCompleteException();
    }
    return result;
  }

  /**
   * get the list of the annotated beans.
   *
   * @param plogger logger
   * @param pvalidatorType class type
   * @return list of classes
   * @throws UnableToCompleteException it's thrown, if no classes can be found
   */
  private Class<?>[] getBeans(final TreeLogger plogger, final JClassType pvalidatorType)
      throws UnableToCompleteException {
    final String typeName = pvalidatorType.getName();

    final GwtValidation gwtValidation =
        pvalidatorType.findAnnotationInTypeHierarchy(GwtValidation.class);

    if (gwtValidation == null) {
      plogger.log(TreeLogger.ERROR,
          typeName + " must be anntotated with " + GwtValidation.class.getCanonicalName(), null);
      throw new UnableToCompleteException();
    }

    if (gwtValidation.value().length == 0) {
      plogger.log(TreeLogger.ERROR, "The @" + GwtValidation.class.getSimpleName() + "  of "
          + typeName + "must specify at least one bean type to validate.", null);
      throw new UnableToCompleteException();
    }

    return gwtValidation.value();
  }
}
