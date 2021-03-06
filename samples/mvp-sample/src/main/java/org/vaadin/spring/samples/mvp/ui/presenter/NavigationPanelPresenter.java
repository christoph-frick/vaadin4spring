/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.spring.samples.mvp.ui.presenter;

import java.util.List;

import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;
import org.vaadin.spring.samples.mvp.ui.component.nav.NavElement;
import org.vaadin.spring.samples.mvp.ui.component.nav.NavElementFactory;
import org.vaadin.spring.samples.mvp.ui.view.NavigationPanelView;

/**
 * Presenter responsible for setting model data on the {@link NavigationPanelView}.
 * It employs a factory to construct model data in the form of <code>List&lt;NavElement&gt;</code>.
 * Model data is retrieved from <code>navElements.json</code>.
 * @author Chris Phillipson (fastnsilver@gmail.com)
 *
 */
@VaadinPresenter(viewName = NavigationPanelView.NAME)
public class NavigationPanelPresenter extends Presenter<NavigationPanelView> {

    private static final String NAV_ELEMENTS_FILE = "navElements.json";

    @EventBusListenerMethod(filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {
        NavElementFactory factory = new NavElementFactory();
        getEventBus().publish(this, factory.getNavElements(NAV_ELEMENTS_FILE));
    }

    @EventBusListenerMethod
    public void onSetData(List<NavElement> navElements) {
        getView().setData(navElements);
    }

}
