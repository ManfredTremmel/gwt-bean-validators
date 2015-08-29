package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.IbanLengthMapConstants;

import java.util.Map;

public class IbanLengthMapConstantsImpl implements IbanLengthMapConstants {
  private final Map<String, String> ibanLengthMap;

  public IbanLengthMapConstantsImpl(final Map<String, String> pmap) {
    this.ibanLengthMap = pmap;
  }

  @Override
  public Map<String, String> ibanLengths() {
    return this.ibanLengthMap;
  }
}
