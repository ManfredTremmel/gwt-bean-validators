package de.knightsoftnet.validators.client.rest.path;

import de.knightsoftnet.validators.shared.ResourcePaths;

import com.google.gwt.core.client.GWT;

import org.apache.commons.lang3.StringUtils;

public class PathDefinitionImpl implements PathDefinitionInterface {

  @Override
  public String getRestBasePath() {
    return StringUtils.removeEnd(
        StringUtils.removeEnd(GWT.getModuleBaseURL(), "/") + ResourcePaths.API_BASE_DIR, "/");
  }
}
