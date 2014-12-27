package de.knightsoftnet.validators.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.validation.client.AbstractValidationMessageResolver;
import com.google.gwt.validation.client.UserValidationMessagesResolver;

/**
 * The <code>CustomValidationMessagesResolver</code> is a replacement of the google validation
 * message resolver, this enables us to add own localized messages to the annotations.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 */
public class CustomValidationMessagesResolver extends AbstractValidationMessageResolver implements
    UserValidationMessagesResolver {

  /**
   * default constructor.
   */
  public CustomValidationMessagesResolver() {
    super((ConstantsWithLookup) GWT.create(ValidationMessages.class));
  }
}
