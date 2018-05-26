package de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader;

public class JsWebshimResourceDefinitionImpl implements JsWebshimResourceDefinitionInterface {

  @Override
  public String getWebshimPath() {
    return "//cdn.jsdelivr.net/npm/webshim@1/js-webshim/minified/polyfiller.min.js";
  }
}
