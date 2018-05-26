# gwtp-spring-integration
Basic functionality to integrate gwtp with spring.

It includes

* authentication integration with spring security
* user session handling
* validation handling and report to client
* rest callback implementations handling errors from server
* expose some interfaces to client

Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwtp-spring-integration</artifactId>
      <version>0.55.0</version>
    </dependency>
```

GWT Integration
---------------

You have to inherit GwtpSpringIntegration into your project .gwt.xml file:

```
<inherits name="de.knightsoftnet.gwtp.spring.GwtpSpringIntegration" />
```
