package de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader;

import de.knightsoftnet.mtwidgets.client.jswrapper.Webshims;

import com.google.gwt.core.client.GWT;

import elemental.client.Browser;
import elemental.dom.NodeList;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.ScriptElement;
import elemental.json.Json;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WebshimResources {

  private static final String POLIFILLER_URL = ((JsWebshimResourceDefinitionInterface) GWT
      .create(JsWebshimResourceDefinitionInterface.class)).getWebshimPath();

  private static boolean initializationStarted = false;

  private static Event rememberEvent;
  private static List<EventListener> eventLisenerQueue = new ArrayList<>();

  /**
   * async load of resources.
   *
   * @param function function to call on load
   */
  public static void whenReady(final EventListener function) {
    WebshimResources.eventLisenerQueue.add(function);
    if (WebshimResources.initializationStarted || WebshimResources.isInHeader()) {
      if (WebshimResources.isInitialized()) {
        WebshimResources.eventLisenerQueue
            .forEach(action -> action.handleEvent(WebshimResources.rememberEvent));
        WebshimResources.eventLisenerQueue.clear();
      }
      return;
    }
    WebshimResources.initializationStarted = true;

    JQueryResources.whenReady(event -> {

      final ScriptElement webshimScript = Browser.getDocument().createScriptElement();
      webshimScript.setSrc(WebshimResources.POLIFILLER_URL);
      webshimScript.setType(JsResources.SCRIPT_TYPE);

      Browser.getDocument().getHead().appendChild(webshimScript);

      webshimScript.setOnload(event2 -> {
        Webshims.setOptions(Json.parse( //
            "{\n" //
                + "  \"forms-ext\": {\n" //
                + "    \"replaceUI\": true\n" //
                + "  }\n" //
                + "}"));
        Webshims.polyfill("forms forms-ext");
        WebshimResources.eventLisenerQueue.forEach(action -> action.handleEvent(event2));
        WebshimResources.eventLisenerQueue.clear();
        WebshimResources.rememberEvent = event2;
      });

    });
  }

  /**
   * check if script is available and initialized.
   *
   * @return true if it exists
   */
  public static boolean isInitialized() {
    final boolean loaded = WebshimResources.rememberEvent != null
        || StringUtils.isEmpty(WebshimResources.POLIFILLER_URL);
    return loaded || WebshimResources.isInHeader();
  }

  /**
   * check if script is already in the header.
   *
   * @return true if it exists
   */
  public static boolean isInHeader() {
    final NodeList scriptList = Browser.getDocument().getHead().getElementsByTagName("script");
    for (int i = 0; i < scriptList.getLength(); i++) {
      final ScriptElement scriptTag = (ScriptElement) scriptList.item(i);
      if (StringUtils.contains(scriptTag.getSrc(), WebshimResources.POLIFILLER_URL)) {
        return true;
      }
    }
    return false;
  }
}
