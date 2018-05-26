package de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader;

import elemental.client.Browser;
import elemental.dom.NodeList;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.ScriptElement;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsResources {

  protected static final String TAG_TYPE = "script";
  protected static final String SCRIPT_TYPE = "text/javascript";

  protected static Map<String, Boolean> initializationStarted = new HashMap<>();

  protected static Map<String, Event> rememberEvent = new HashMap<>();
  protected static Map<String, List<EventListener>> eventLisenerQueue = new HashMap<>();

  /**
   * async load of resources.
   *
   * @param function function to call on load
   */
  public static void whenReady(final String scriptname, final EventListener function) {
    List<EventListener> eventList = JsResources.eventLisenerQueue.get(scriptname);
    if (eventList == null) {
      eventList = new ArrayList<>();
      JsResources.eventLisenerQueue.put(scriptname, eventList);
    }
    eventList.add(function);
    if (BooleanUtils.isTrue(JsResources.initializationStarted.get(scriptname))
        || JsResources.isInHeader(scriptname)) {
      if (JsResources.isInitialized(scriptname)) {
        JsResources.eventLisenerQueue.get(scriptname)
            .forEach(action -> action.handleEvent(JsResources.rememberEvent.get(scriptname)));
        JsResources.eventLisenerQueue.get(scriptname).clear();
      }
      return;
    }
    JsResources.initializationStarted.put(scriptname, Boolean.TRUE);

    final ScriptElement jsScript = Browser.getDocument().createScriptElement();
    if (StringUtils.endsWith(scriptname, ".js")) {
      jsScript.setSrc(scriptname);
    } else {
      jsScript.setInnerHTML(scriptname);
    }
    jsScript.setType(JsResources.SCRIPT_TYPE);

    Browser.getDocument().getHead().appendChild(jsScript);

    jsScript.setOnload(event -> {
      JsResources.eventLisenerQueue.get(scriptname).forEach(action -> action.handleEvent(event));
      JsResources.eventLisenerQueue.get(scriptname).clear();
      JsResources.rememberEvent.put(scriptname, event);
    });
  }

  /**
   * check if script is available and initialized.
   *
   * @return true if it exists
   */
  public static boolean isInitialized(final String scriptname) {
    final boolean loaded = JsResources.rememberEvent != null || StringUtils.isEmpty(scriptname);
    return loaded || JsResources.isInHeader(scriptname);
  }

  /**
   * check if script is already in the header.
   *
   * @return true if it exists
   */
  public static boolean isInHeader(final String scriptname) {
    final NodeList scriptList =
        Browser.getDocument().getHead().getElementsByTagName(JsResources.TAG_TYPE);
    for (int i = 0; i < scriptList.getLength(); i++) {
      final ScriptElement scriptTag = (ScriptElement) scriptList.item(i);
      if (StringUtils.contains(scriptTag.getSrc(), scriptname)) {
        return true;
      }
    }
    return false;
  }
}
