package de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader;

import elemental.client.Browser;
import elemental.dom.NodeList;
import elemental.html.LinkElement;

import org.apache.commons.lang3.StringUtils;

public class CssResources {

  private static final String TAG_TYPE = "link";
  private static final String SCRIPT_TYPE = "text/css";
  private static final String REL_TYPE = "stylesheet";

  /**
   * add css script to header.
   *
   * @param scriptname style sheet file to add to header
   */
  public static void addToHeader(final String scriptname) {
    if (!CssResources.isInHeader(scriptname)) {
      final LinkElement styleLinkElement = Browser.getDocument().createLinkElement();
      styleLinkElement.setRel(CssResources.REL_TYPE);
      styleLinkElement.setType(CssResources.SCRIPT_TYPE);
      styleLinkElement.setHref(scriptname);
      Browser.getDocument().getHead().appendChild(styleLinkElement);
    }
  }

  /**
   * check if script is already in the header.
   *
   * @return true if it exists
   */
  public static boolean isInHeader(final String scriptname) {
    final NodeList linkList =
        Browser.getDocument().getHead().getElementsByTagName(CssResources.TAG_TYPE);
    for (int i = 0; i < linkList.getLength(); i++) {
      final LinkElement linkTag = (LinkElement) linkList.item(i);
      if (StringUtils.equals(linkTag.getType(), CssResources.SCRIPT_TYPE)
          && StringUtils.equals(linkTag.getRel(), CssResources.REL_TYPE)
          && StringUtils.contains(linkTag.getHref(), scriptname)) {
        return true;
      }
    }
    return false;
  }
}
