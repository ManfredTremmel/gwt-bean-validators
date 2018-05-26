/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.mtwidgets.client.ui.widget;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

/**
 * Image lazy loading is based on the GWT {@link Image} and provides all the functionality inside.
 * The difference is, image urls are put into the image tag when image is in the viewport so no
 * images are loaded by the browser, which are not visible.
 *
 * @author Manfred Tremmel
 *
 */
public class ImageLazyLoading extends Image {

  private SafeUri parkedUri;
  private HandlerRegistration scrollHandler;
  private HandlerRegistration resizeHandler;

  public ImageLazyLoading() {
    super();
  }

  public ImageLazyLoading(final Element pelement) {
    super(pelement);
  }

  public ImageLazyLoading(final ImageResource presource) {
    super(presource);
  }

  public ImageLazyLoading(final SafeUri purl, final int pleft, final int ptop, final int pwidth,
      final int pheight) {
    super(purl, pleft, ptop, pwidth, pheight);
  }

  public ImageLazyLoading(final SafeUri purl) {
    super(purl);
  }

  public ImageLazyLoading(final String purl, final int pleft, final int ptop, final int pwidth,
      final int pheight) {
    super(purl, pleft, ptop, pwidth, pheight);
  }

  public ImageLazyLoading(final String purl) {
    super(purl);
  }

  @Override
  public void setUrl(final SafeUri purl) {
    Scheduler.get().scheduleDeferred(() -> this.setImageUrl(purl));
  }

  private void setImageUrl(final SafeUri purl) {
    if (this.isInViewPort()) {
      super.setUrl(purl);
      this.parkedUri = null;
      if (this.scrollHandler != null) {
        this.scrollHandler.removeHandler();
      }
      if (this.resizeHandler != null) {
        this.resizeHandler.removeHandler();
      }
    } else {
      this.parkedUri = purl;
      if (this.scrollHandler == null) {
        this.scrollHandler = Window.addWindowScrollHandler(pScrollEvent -> {
          if (this.isInViewPort()) {
            super.setUrl(this.parkedUri);
          }
        });
      }
      if (this.resizeHandler == null) {
        this.resizeHandler = Window.addResizeHandler(pResizeEvent -> {
          if (this.isInViewPort()) {
            super.setUrl(this.parkedUri);
          }
        });
      }
    }
  }

  private boolean isInViewPort() {
    final int pageTop = Window.getScrollTop();
    final int pageBottom = pageTop + Window.getClientHeight();
    final int elementTop = this.asWidget().getElement().getAbsoluteTop();
    final int elementBottom = elementTop + this.asWidget().getElement().getClientHeight();
    return elementTop <= pageBottom && elementBottom >= pageTop;
  }
}
