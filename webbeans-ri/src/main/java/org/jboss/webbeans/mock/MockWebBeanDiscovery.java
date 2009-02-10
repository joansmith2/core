/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.webbeans.mock;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import org.jboss.webbeans.bootstrap.spi.WebBeanDiscovery;

public class MockWebBeanDiscovery implements WebBeanDiscovery
{

   private Iterable<Class<?>> webBeanClasses = new HashSet<Class<?>>();

   private Iterable<URL> webBeansXmlFiles = new HashSet<URL>();

   /**
    * Simple constructor that auto discovers EJBs
    * @param webBeanClasses
    */
   public MockWebBeanDiscovery(Class<?>... webBeanClasses)
   {
      this(Arrays.asList(webBeanClasses), null);
   }
   
   public MockWebBeanDiscovery(Iterable<Class<?>> webBeanClasses, Iterable<URL> webBeansXmlFiles)
   {
      super();
      this.webBeanClasses = webBeanClasses;
      this.webBeansXmlFiles = webBeansXmlFiles;
   }

   public Iterable<Class<?>> discoverWebBeanClasses()
   {
      return webBeanClasses;
   }

   public Iterable<URL> discoverWebBeansXml()
   {
      return webBeansXmlFiles;
   }

}
