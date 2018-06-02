package com.google.gwt.user.client.impl;

@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class DOMImplWebkitNew extends DOMImplStandardBase {

  @SuppressWarnings("deprecation")
  @Override 
  protected native void initEventSystem() /*-{
    // Ensure $entry for bitfull event dispatchers
    @com.google.gwt.user.client.impl.DOMImplStandard::dispatchEvent =
        $entry(@com.google.gwt.user.client.impl.DOMImplStandard::dispatchEvent(*));

    @com.google.gwt.user.client.impl.DOMImplStandard::dispatchUnhandledEvent =
        $entry(@com.google.gwt.user.client.impl.DOMImplStandard::dispatchUnhandledEvent(*));

    var foreach = @com.google.gwt.user.client.impl.EventMap::foreach(*);

    // Ensure $entry for bitless event dispatchers
    var bitlessEvents = @com.google.gwt.user.client.impl.DOMImplStandard::bitlessEventDispatchers;
    foreach(bitlessEvents, function(e, fn) { bitlessEvents[e] = $entry(fn); });

    // Ensure $entry for capture event dispatchers
    var captureEvents = @com.google.gwt.user.client.impl.DOMImplStandard::captureEventDispatchers;
    foreach(captureEvents, function(e, fn) { captureEvents[e] = $entry(fn); });

    // Add capture event listeners
    foreach(captureEvents, function(e, fn) {
        try {
            $wnd.addEventListener(e, fn, { passive: true, capture: true });
        } catch (e) {
            $wnd.addEventListener(e, fn, true);
        }
    });
  }-*/;

}
