# gwtp-dynamic-navigation
A view helper classes to generate a dynamic navigation in a gwtp project. The navigation changes when user changes (login/logout). It can highlight the entry of the selected view, even if you use the back button or reload the page.

With version 0.55.1 the spring integration stuff was moved into [gwtp-spring-integration](https://github.com/ManfredTremmel/gwtp-spring-integration) package, to solve failed imports replace `de.knightsoftnet.navigation.*` and `de.knightsoftnet.validators.*` with `de.knightsoftnet.gwtp.spring.*`.

Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwtp-dynamic-navigation</artifactId>
      <version>0.55.1</version>
    </dependency>
```

GWT Integration
---------------

You have to inherit GwtBeanValidators into your project .gwt.xml file:

```
<inherits name="de.knightsoftnet.navigation.GwtpDynamicNavigation" />
```

Next you need implementations of the interfaces User (you can extend MinimumUser if you want), Session (you can extend AbstractSession) and NavigationStructure (you should extend AbstractNavigationStructure) and bind it to the interfaces.

```
    this.bind(User.class).to(MyUser.class).in(Singleton.class);
    this.bind(Session.class).to(MySession.class).in(Singleton.class);
    this.bind(NavigationStructure.class).to(MyNavigationStructure.class).in(Singleton.class);
```

The session should contain all the data you need from the server, especially the user data which is included in the User implementation. In the NavigationStructure now you can define the navigation structure. Each entry can be secured by a gatekeeper, folders can be defined to structure the navigation.

Next you Include the navigation presenter and view. A view with navigation tree is included, but other implementations can be done without a problem:

```
    this.bindPresenter(NavigationPresenter.class, NavigationPresenter.MyView.class,
        TreeNavigationView.class, NavigationPresenter.MyProxy.class);
```

Last you bind the presenter to a slot.

As example take a look at my [gwt bean validators example](https://github.com/ManfredTremmel/gwt-bean-validators-example) Project on github.
