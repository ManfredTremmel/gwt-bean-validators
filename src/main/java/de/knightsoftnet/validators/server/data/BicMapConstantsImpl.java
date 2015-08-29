package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.BicMapConstants;

import java.util.Map;

public class BicMapConstantsImpl implements BicMapConstants {
  private final Map<String, String> bicMap;

  public BicMapConstantsImpl(final Map<String, String> pmap) {
    this.bicMap = pmap;
  }

  @Override
  public Map<String, String> bics() {
    return this.bicMap;
  }

}
