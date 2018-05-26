package de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader;

import com.google.gwt.core.client.GWT;

import elemental.client.Browser;
import elemental.dom.NodeList;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.ScriptElement;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JQueryResources {

  private static final String JQUERY_URL =
      ((JsJQueryResourceDefinitionInterface) GWT.create(JsJQueryResourceDefinitionInterface.class))
          .getJQeryPath();

  private static boolean initializationStarted = false;

  private static Event rememberEvent;
  private static List<EventListener> eventLisenerQueue = new ArrayList<>();

  /**
   * async load of resources.
   *
   * @param function function to call on load
   */
  public static void whenReady(final EventListener function) {
    JQueryResources.eventLisenerQueue.add(function);
    if (JQueryResources.initializationStarted || JQueryResources.isInHeader()) {
      if (JQueryResources.isInitialized()) {
        JQueryResources.eventLisenerQueue
            .forEach(action -> action.handleEvent(JQueryResources.rememberEvent));
        JQueryResources.eventLisenerQueue.clear();
      }
      return;
    }
    JQueryResources.initializationStarted = true;

    final ScriptElement jqueryScript = Browser.getDocument().createScriptElement();
    jqueryScript.setSrc(JQueryResources.JQUERY_URL);
    jqueryScript.setType(JsResources.SCRIPT_TYPE);

    Browser.getDocument().getHead().appendChild(jqueryScript);

    jqueryScript.setOnload(event -> {
      JQueryResources.eventLisenerQueue.forEach(action -> action.handleEvent(event));
      JQueryResources.eventLisenerQueue.clear();
      JQueryResources.rememberEvent = event;
    });
  }

  /**
   * check if script is available and initialized.
   *
   * @return true if it exists
   */
  public static boolean isInitialized() {
    final boolean loaded =
        JQueryResources.rememberEvent != null || StringUtils.isEmpty(JQueryResources.JQUERY_URL);
    return loaded || JQueryResources.isInHeader();
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
      if (StringUtils.contains(scriptTag.getSrc(), JQueryResources.JQUERY_URL)) {
        return true;
      }
    }
    return false;
  }
}
