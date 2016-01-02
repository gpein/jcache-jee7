JCache JEE 7
========

The project is under license [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0).


Main goal
------------

This jar module is made to be embbeded as a Java EE 7 (Java EE 6 as well) Web application library.

It enables support for JSR 107 a.k.a "JCache" based on Hazelcast implementation provider.
This JSR is the future of Java EE caching API currently targeted in Java EE 8 release.

Using this module in a Web app will automatically expose a REST API to help dealing with Cache Managing features.
 
This module relies on others Java EE API : CDI, JAX-RS and JSP

Quick start
------------

First step is adding maven dependency into your WEB-INF\lib (for a WAR packaging)

```xml 
<dependency>
    <groupId>io.github.gpein</groupId>
    <artifactId>jcache-jee7</artifactId>
    <version>1.0.0</version>
</dependency>
```

Then enable JCache interceptors in your beans.xml CDI descriptor

```xml 
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
		http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
       bean-discovery-mode="all">
       
    <interceptors>
        <class>org.jsr107.ri.annotations.cdi.CacheResultInterceptor</class>
        <class>org.jsr107.ri.annotations.cdi.CacheRemoveEntryInterceptor</class>
        <class>org.jsr107.ri.annotations.cdi.CacheRemoveAllInterceptor</class>
        <class>org.jsr107.ri.annotations.cdi.CachePutInterceptor</class>
    </interceptors>
</beans>
```

Your are now able to use JSR 107 API !
For more information, please check the GitHub repository : https://github.com/jsr107/jsr107spec

Hazelcast manual can be found here : http://docs.hazelcast.org/docs/3.5/manual/html-single

Releases
------

* 1.0.0 : initial release including JSR 107  support and REST API for handling cache statistics

Next features
------

* Basic UI to interact directly with REST API
