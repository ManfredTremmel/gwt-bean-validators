package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.VatIdMapConstants;

import java.util.Map;

public class VatIdMapConstantsImpl implements VatIdMapConstants {
  private final Map<String, String> vatIdMap;

  public VatIdMapConstantsImpl(final Map<String, String> pmap) {
    this.vatIdMap = pmap;
  }

  @Override
  public Map<String, String> vatIds() {
    return this.vatIdMap;
  }
}
