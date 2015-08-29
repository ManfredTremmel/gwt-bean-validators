package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;

import java.util.Map;

public class PostalCodesMapConstantsImpl implements PostalCodesMapSharedConstants {
  private final Map<String, String> postalCodesMap;

  public PostalCodesMapConstantsImpl(final Map<String, String> pmap) {
    this.postalCodesMap = pmap;
  }

  @Override
  public Map<String, String> postalCodes() {
    return this.postalCodesMap;
  }

}
