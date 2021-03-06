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
package org.vaadin.spring.sidebar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.sidebar.FontAwesomeIconProvider;
import org.vaadin.spring.sidebar.LocalizedThemeIconProvider;
import org.vaadin.spring.sidebar.SideBar;
import org.vaadin.spring.sidebar.SideBarUtils;
import org.vaadin.spring.sidebar.ThemeIconProvider;

/**
 * Spring configuration for the {@link org.vaadin.spring.sidebar.SideBar} and its dependencies.
 *
 * @author Petter Holmström (petter@vaadin.com)
 * @see org.vaadin.spring.sidebar.annotation.EnableSideBar
 */
@Configuration
public class SideBarConfiguration {

    @Autowired
    I18N i18n;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired(required = false)
    SideBar.SectionComponentFactory sectionComponentFactory;
    @Autowired(required = false)
    SideBar.ItemComponentFactory itemComponentFactory;

    @Bean
    @VaadinUIScope
    SideBar sideBar() {
        return new SideBar(sideBarUtils(), sectionComponentFactory, itemComponentFactory);
    }

    @Bean
    SideBarUtils sideBarUtils() {
        return new SideBarUtils(applicationContext, i18n);
    }

    @Bean
    ThemeIconProvider themeIconProvider() {
        return new ThemeIconProvider();
    }

    @Bean
    LocalizedThemeIconProvider localizedThemeIconProvider() {
        return new LocalizedThemeIconProvider(i18n);
    }

    @Bean
    FontAwesomeIconProvider fontAwesomeIconProvider() {
        return new FontAwesomeIconProvider();
    }
}
