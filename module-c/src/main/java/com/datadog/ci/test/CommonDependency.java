package com.datadog.ci.test;

public class CommonDependency {

   {
       System.out.println("Initializing common dependency");
   }

   public static final CommonDependency INSTANCE = new CommonDependency();

}
