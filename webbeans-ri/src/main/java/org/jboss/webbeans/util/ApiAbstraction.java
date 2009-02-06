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

package org.jboss.webbeans.util;

import java.lang.annotation.Annotation;

import org.jboss.webbeans.introspector.AnnotatedAnnotation;
import org.jboss.webbeans.introspector.AnnotatedClass;
import org.jboss.webbeans.introspector.jlr.AnnotatedAnnotationImpl;
import org.jboss.webbeans.introspector.jlr.AnnotatedClassImpl;
import org.jboss.webbeans.resources.spi.ResourceLoader;
import org.jboss.webbeans.resources.spi.ResourceLoadingException;

/**
 * A base class for utility classes that represent annotations, classes etc
 * 
 * @author Pete Muir
 */
public class ApiAbstraction
{
   
   private static final AnnotatedAnnotation<DummyAnnotation> DUMMY_ANNOTATION = AnnotatedAnnotationImpl.of(DummyAnnotation.class);
   
   private static final AnnotatedClass<Dummy> DUMMY_CLASS = AnnotatedClassImpl.of(Dummy.class);
   
   private ResourceLoader resourceLoader;

   /**
    * "Not found" annotation
    */
   public @interface DummyAnnotation
   {
   }

   /**
    * "Not found" class
    */
   public interface Dummy
   {
   }
   

   /**
    * Constructor
    * 
    * @param resourceLoader The root resource loaderS
    */
   public ApiAbstraction(ResourceLoader resourceLoader)
   {
      this.resourceLoader = resourceLoader;
   }

   /**
    * Initializes an annotation class
    * 
    * @param name The name of the annotation class
    * @return The instance of the annotation. Returns a dummy if the class was
    *         not found
    */
   @SuppressWarnings("unchecked")
   protected AnnotatedAnnotation<?> annotationTypeForName(String name)
   {
      try
      {
         return AnnotatedAnnotationImpl.of((Class<? extends Annotation>) resourceLoader.classForName(name));
      }
      catch (ResourceLoadingException cnfe)
      {
         return DUMMY_ANNOTATION;
      }
   }

   /**
    * Initializes a type
    * 
    * @param name The name of the class
    * @return The instance of the class. Returns a dummy if the class was not
    *         found.
    */
   @SuppressWarnings("unchecked")
   protected AnnotatedClass<?> classForName(String name)
   {
      try
      {
         return AnnotatedClassImpl.of(resourceLoader.classForName(name));
      }
      catch (ResourceLoadingException cnfe)
      {
         return DUMMY_CLASS;
      }
   }

}
