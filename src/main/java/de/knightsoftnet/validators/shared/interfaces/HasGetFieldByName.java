package de.knightsoftnet.validators.shared.interfaces;

/**
 * interface for beans which provides a get field by name method.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public interface HasGetFieldByName {

  /**
   * get a field by name.
   * 
   * @param pname name of the field
   * @return value of the field.
   */
  Object getFieldByName(String pname);
}
