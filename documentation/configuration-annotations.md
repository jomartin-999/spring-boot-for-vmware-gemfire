---
Title: Auto-configuration vs. Annotation-based configuration
---

<!-- 
 Copyright (c) VMware, Inc. 2022. All rights reserved.
 Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 agreements. See the NOTICE file distributed with this work for additional information regarding
 copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance with the License. You may obtain a
 copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software distributed under the License
 is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 or implied. See the License for the specific language governing permissions and limitations under
 the License.
-->

The question most often asked is, “What [spring-data-gemfire-name] annotations can I use, or must I use, when
developing [vmware-gemfire-name] applications with Spring Boot?”

This section answers this question and more.

See the complementary sample, [Spring Boot Auto-configuration for
[vmware-gemfire-name]](guides/boot-configuration.html), which shows the
auto-configuration provided by [spring-boot-gemfire-name] in
action.

### Background

To help answer this question, you must start by reviewing the complete
collection of available [spring-data-gemfire-name]
annotations. These annotations are provided in the
[`org.springframework.data.gemfire.config.annotation`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/data/gemfire/config/annotation/package-summary.html)
package. Most of the essential annotations begin with `@Enable…​`, except
for the base annotations: `@ClientCacheApplication`,
`@PeerCacheApplication` and `@CacheServerApplication`.

By extension, [spring-boot-gemfire-name] builds on [spring-data-gemfire-name]’s
annotation-based configuration model to implement auto-configuration and
apply Spring Boot’s core concepts, such as “convention over
configuration”, letting [vmware-gemfire-name] applications be built with
Spring Boot reliably, quickly, and easily.

[spring-data-gemfire-name] provides this annotation-based configuration model to, first and
foremost, give application developers “choice” when building Spring
applications with [vmware-gemfire-name]. [spring-data-gemfire-name] makes no assumptions about
what application developers are trying to create and fails fast anytime
the configuration is ambiguous, giving users immediate feedback.

Second, [spring-data-gemfire-name]’s annotations were meant to get application developers up
and running quickly and reliably with ease. [spring-data-gemfire-name] accomplishes this by
applying sensible defaults so that application developers need not know,
or even have to learn, all the intricate configuration details and
tooling provided by [vmware-gemfire-name] to accomplish simple tasks, such
as building a prototype.

So, [spring-data-gemfire-name] is all about “choice” and [spring-boot-gemfire-name] is all about “convention”.
Together these frameworks provide application developers with
convenience and ease to move quickly and reliably.

To learn more about the motivation behind [spring-data-gemfire-name]’s annotation-based
configuration model, see the
[Reference Documentation](https://docs.spring.io/spring-data/gemfire/docs/current/reference/html/#bootstrap-annotation-config-introduction/#bootstrap-annotation-config-introduction).

### Conventions

Currently, [spring-boot-gemfire-name] provides auto-configuration for the following features:

- `ClientCache`

- Caching with Spring’s Cache Abstraction

- Continuous Query

- Function Execution and Implementation

- Logging

- PDX

- `GemfireTemplate`

- Spring Data Repositories

- Security (Client/server auth and SSL)

- Spring Session

This means the following [spring-data-gemfire-name] annotations are not required to use the
features above:

- `@ClientCacheApplication`

- `@EnableGemfireCaching` (or by using Spring Framework’s
  `@EnableCaching` annotation)

- `@EnableContinuousQueries`

- `@EnableGemfireFunctionExecutions`

- `@EnableGemfireFunctions`

- `@EnableLogging`

- `@EnablePdx`

- `@EnableGemfireRepositories`

- `@EnableSecurity`

- `@EnableSsl`

- `@EnableGemFireHttpSession`

Since [spring-boot-gemfire-name] auto-configures these features for you, the above annotations
are not strictly required. Typically, you would only declare one of
these annotations when you want to “override” Spring Boot’s conventions,
as expressed in auto-configuration, and “customize” the behavior of the
feature.

### <a id='auto-configuration-annotations-overriding'></a>Overriding

In this section, we cover a few examples to make the behavior when
overriding more apparent.

#### Caches

By default, [spring-boot-gemfire-name] provides you with a `ClientCache` instance. [spring-boot-gemfire-name]
accomplishes this by annotating an auto-configuration class with
`@ClientCacheApplication` internally.

By convention, we assume most application developers' are developing
Spring Boot applications by using [vmware-gemfire-name] as “client”
applications in [vmware-gemfire-name]'s client/server topology. This is
especially true as users migrate their applications to a managed cloud
environment.

Still, you can “override” the default settings (convention) and declare
your Spring applications to be actual peer `Cache` members (nodes) of a
[vmware-gemfire-name] cluster, instead:

Example 1. Spring Boot, [vmware-gemfire-name] Peer `Cache` Application


``` highlight
@SpringBootApplication
@CacheServerApplication
class SpringBootApacheGeodePeerCacheServerApplication {  }
```

By declaring the `@CacheServerApplication` annotation, you effectively
override the [spring-boot-gemfire-name] default. Therefore, [spring-boot-gemfire-name] does not provide you with a
`ClientCache` instance by default, because you have informed [spring-boot-gemfire-name] of
exactly what you want: a peer `Cache` instance hosting an embedded
`CacheServer` that allows client connections.

However, you then might ask, “Well, how do I customize the `ClientCache`
instance when developing client applications without explicitly
declaring the `@ClientCacheApplication` annotation?”

First, you can “customize” the `ClientCache` instance by explicitly
declaring the `@ClientCacheApplication` annotation in your Spring Boot
application configuration and setting specific attributes as needed.
However, you should be aware that, by explicitly declaring this
annotation, (or, by default, any of the other auto-configured
annotations), you assume all the responsibility that comes with it,
since you have effectively overridden the auto-configuration. One
example of this is security, which we touch on more later.

The most ideal way to “customize” the configuration of any feature is by
way of the well-known and documented
[properties](#geode-configuration-metadata), specified in Spring Boot
`application.properties` (the “convention”), or by using a
[Configurer`](https://docs.spring.io/spring-data/gemfire/docs/current/reference/html/#bootstrap-annotation-config-introduction/#bootstrap-annotation-config-configurers).

See the [Reference Guide](#geode-clientcache-applications) for more
detail.

#### Security

As with the `@ClientCacheApplication` annotation, the `@EnableSecurity`
annotation is not strictly required, unless you want to override and
customize the defaults.

Outside a managed environment, the only security configuration required
is specifying a username and password. You do this by using the
well-known and documented [spring-data-gemfire-name] username and password properties in Spring
Boot `application.properties`:

Example 2. Required Security Properties in a Non-Manage Envionment


``` highlight
spring.data.gemfire.security.username=MyUser
spring.data.gemfire.security.password=Secret
```

You need not explicitly declare the `@EnableSecurity` annotation just to
specify security configuration (such as username and password).

Inside a managed environment, such as the Tanzu Application Service
when using [vmware-gemfire-name], [spring-boot-gemfire-name] is able to introspect the
environment and configure security (auth) completely without the need to
specify any configuration, usernames and passwords, or otherwise. This
is due, in part, because TAS supplies the security details in the VCAP
environment when the application is deployed to TAS and bound to
services (such as [vmware-gemfire-name]).

So, in short, you need not explicitly declare the `@EnableSecurity`
annotation (or `@ClientCacheApplication`).

However, if you do explicitly declare the `@ClientCacheApplication` or
`@EnableSecurity` annotations, you are now responsible for this
configuration, and [spring-boot-gemfire-name]’s auto-configuration no longer applies.

While explicitly declaring `@EnableSecurity` makes more sense when
“overriding” the [spring-boot-gemfire-name] security auto-configuration, explicitly declaring
the `@ClientCacheApplication` annotation most likely makes less sense
with regard to its impact on security configuration.

This is entirely due to the internals of [vmware-gemfire-name], because,
in certain cases (such as security), not even Spring is able to
completely shield you from the nuances of [vmware-gemfire-name]'s
configuration. No framework can.

You must configure both auth and SSL before the cache instance (whether
a `ClientCache` or a peer `Cache`) is created. This is because security
is enabled and configured during the “construction” of the cache. Also,,
the cache pulls the configuration from JVM System properties that must
be set before the cache is constructed.

Structuring the “exact” order of the auto-configuration classes provided
by [spring-boot-gemfire-name] when the classes are triggered, is no small feat. Therefore, it
should come as no surprise to learn that the security auto-configuration
classes in [spring-boot-gemfire-name] must be triggered before the `ClientCache`
auto-configuration class, which is why a `ClientCache` instance cannot
“auto” authenticate properly in PCC when the `@ClientCacheApplication`
is explicitly declared without some assistance. In other words you must
also explicitly declare the `@EnableSecurity` annotation in this case,
since you overrode the auto-configuration of the cache, and implicitly
security, as well.

Again, this is due to the way security (auth) and SSL metadata must be
supplied to [vmware-gemfire-name] on startup.

See the [Reference Guide](#geode-security) for more details.


### Extension

Most of the time, many of the other auto-configured annotations for CQ,
Functions, PDX, Repositories, and so on need not ever be declared
explicitly.

Many of these features are enabled automatically by having [spring-boot-gemfire-name] or other
libraries (such as Spring Session) on the application classpath or are
enabled based on other annotations applied to beans in the Spring
`ApplicationContext`.

We review a few examples in the following sections.

#### Caching

It is rarely, if ever, necessary to explicitly declare either the Spring
Framework’s `@EnableCaching` or the [spring-data-gemfire-name]-specific `@EnableGemfireCaching`
annotation in Spring configuration when you use [spring-boot-gemfire-name]. [spring-boot-gemfire-name] automatically
enables caching and configures the [spring-data-gemfire-name] `GemfireCacheManager` for you.

You need only focus on which application service components are
appropriate for caching:

Example 3. Service Caching


``` highlight
@Service
class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Cacheable("CustomersByName")
    public Customer findBy(String name) {
        return customerRepository.findByName(name);
    }
}
```

You need to create [vmware-gemfire-name] Regions that back the caches
declared in your application service components (`CustomersByName` in
the preceding example) by using Spring’s caching annotations (such as
`@Cacheable`), or alternatively, JSR-107 JCache annotations (such as
`@CacheResult`).

You can do that by defining each Region explicitly or, more
conveniently, you can use the following approach:

Example 4. Configuring Caches (Regions)


``` highlight
@SpringBootApplication
@EnableCachingDefinedRegions
class Application {  }
```

`@EnableCachingDefinedRegions` is optional, provided for convenience,
and complementary to caching when used rather than being necessary.

See the [Reference Guide](#geode-caching-provider) for more detail.

#### Continuous Query

It is rarely, if ever, necessary to explicitly declare the [spring-data-gemfire-name]
`@EnableContinuousQueries` annotation. Instead, you should focus on
defining your application queries and worry less about the plumbing.

Consider the following example:

Example 5. Defining Queries for CQ


``` highlight
@Component
public class TemperatureMonitor extends AbstractTemperatureEventPublisher {

    @ContinuousQuery(name = "BoilingTemperatureMonitor",
        query = "SELECT * FROM /TemperatureReadings WHERE temperature.measurement >= 212.0")
    public void boilingTemperatureReadings(CqEvent event) {
        publish(event, temperatureReading -> new BoilingTemperatureEvent(this, temperatureReading));
    }

    @ContinuousQuery(name = "FreezingTemperatureMonitor",
        query = "SELECT * FROM /TemperatureReadings WHERE temperature.measurement <= 32.0")
    public void freezingTemperatureReadings(CqEvent event) {
        publish(event, temperatureReading -> new FreezingTemperatureEvent(this, temperatureReading));
    }
}
```

[vmware-gemfire-name] CQ applies only to clients.

See the [Reference Guide](#geode-continuous-query) for more detail.

#### Functions

You rarely, if ever, need to explicitly declare either the
`@EnableGemfireFunctionExecutions` or `@EnableGemfireFunctions`
annotations. [spring-boot-gemfire-name] provides auto-configuration for both Function
implementations and executions.

You need to define the implementation:

Example 6. Function Implementation


``` highlight
@Component
class GeodeFunctions {

    @GemfireFunction
    Object exampleFunction(Object arg) {
        // ...
    }
}
```

Then you need to define the execution:

Example 7. Function Execution


``` highlight
@OnRegion(region = "Example")
interface GeodeFunctionExecutions {

    Object exampleFunction(Object arg);

}
```

[spring-boot-gemfire-name] automatically finds, configures, and registers Function
implementations (POJOs) in [vmware-gemfire-name] as proper `Functions` and
creates execution proxies for the interfaces, which can then be injected
into application service components to invoke the registered `Functions`
without needing to explicitly declare the enabling annotations. The
application Function implementations (POJOs) and executions (interfaces)
should exist below the `@SpringBootApplication` annotated main class.

See the [Reference Guide](#geode-functions) for more detail.

#### PDX

You rarely, if ever, need to explicitly declare the `@EnablePdx`
annotation, since [spring-boot-gemfire-name] auto-configures PDX by default. [spring-boot-gemfire-name] also
automatically configures the [spring-data-gemfire-name] `MappingPdxSerializer` as the default
`PdxSerializer`.

It is easy to customize the PDX configuration by setting the appropriate
[properties](#geode-configuration-metadata) (search for “PDX”) in Spring
Boot `application.properties`.

See the [Reference Guide](#geode-data-serialization) for more detail.

#### Spring Data Repositories

You rarely, if ever, need to explicitly declare the
`@EnableGemfireRepositories` annotation, since [spring-boot-gemfire-name] auto-configures
Spring Data (SD) Repositories by default.

You need only define your Repositories:

Example 8. Customer’s Repository


``` highlight
interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByName(String name);

}
```

[spring-boot-gemfire-name] finds the Repository interfaces defined in your application,
proxies them, and registers them as beans in the Spring
`ApplicationContext`. The Repositories can be injected into other
application service components.

It is sometimes convenient to use the `@EnableEntityDefinedRegions`
along with Spring Data Repositories to identify the entities used by
your application and define the Regions used by the Spring Data
Repository infrastructure to persist the entity’s state. The
`@EnableEntityDefinedRegions` annotation is optional, provided for
convenience, and complementary to the `@EnableGemfireRepositories`
annotation.

See the [Reference Guide](#geode-repositories) for more detail.


### Explicit Configuration

Most of the other annotations provided in [spring-data-gemfire-name] are focused on particular
application concerns or enable certain [vmware-gemfire-name] features,
rather than being a necessity, including:

- `@EnableAutoRegionLookup`

- `@EnableBeanFactoryLocator`

- `@EnableCacheServer(s)`

- `@EnableCachingDefinedRegions`

- `@EnableClusterConfiguration`

- `@EnableClusterDefinedRegions`

- `@EnableCompression`

- `@EnableDiskStore(s)`

- `@EnableEntityDefinedRegions`

- `@EnableEviction`

- `@EnableExpiration`

- `@EnableGatewayReceiver`

- `@EnableGatewaySender(s)`

- `@EnableGemFireAsLastResource`

- `@EnableHttpService`

- `@EnableIndexing`

- `@EnableOffHeap`

- `@EnableLocator`

- `@EnableManager`

- `@EnableMemcachedServer`

- `@EnablePool(s)`

- `@EnableStatistics`

- `@UseGemFireProperties`

None of these annotations are necessary and none are auto-configured by
[spring-boot-gemfire-name]. They are at your disposal when and if you need them. This also
means that none of these annotations are in conflict with any [spring-boot-gemfire-name]
auto-configuration.

### Summary

In conclusion, you need to understand where [spring-data-gemfire-name] ends and [spring-boot-gemfire-name] begins. It
all begins with the auto-configuration provided by [spring-boot-gemfire-name].

If a feature or function is not covered by [spring-boot-gemfire-name]’s auto-configuration,
you are responsible for enabling and configuring the feature
appropriately, as needed by your application.

In other cases, you might also want to explicitly declare a
complimentary annotation (such as `@EnableEntityDefinedRegions`) for
convenience, since [spring-boot-gemfire-name] provides no convention or opinion.

In all remaining cases, it boils down to understanding how
[vmware-gemfire-name] works under the hood. While we go to great lengths
to shield you from as many details as possible, it is not feasible or
practical to address all matters, such as cache creation and security.

