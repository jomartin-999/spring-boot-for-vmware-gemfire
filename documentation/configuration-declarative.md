---
Title: Declarative Configuration
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

The primary purpose of any software development framework is to help you
be productive as quickly and as easily as possible and to do so in a
reliable manner.

As application developers, we want a framework to provide constructs
that are both intuitive and familiar so that their behaviors are
predictable. This provided convenience not only helps you hit the ground
running in the right direction sooner but increases your focus on the
application domain so that you can better understand the problem you are
trying to solve in the first place. Once the problem domain is well
understood, you are more apt to make informed decisions about the
design, which leads to better outcomes, faster.

This is exactly what Spring Boot’s auto-configuration provides for you.
It enables features, functionality, services and supporting
infrastructure for Spring applications in a loosely integrated way by
using conventions (such as the classpath) that ultimately help you keep
your attention and focus on solving the problem at hand and not on the
plumbing.

For example, if you are building a web application, you can include the
`org.springframework.boot:spring-boot-starter-web` dependency on your
application classpath. Not only does Spring Boot enable you to build
Spring Web MVC Controllers appropriate to your application UC (your
responsibility), but it also bootstraps your web application in an
embedded Servlet container on startup (Spring Boot’s responsibility).

This saves you from having to handle many low-level, repetitive, and
tedious development tasks that are error-prone and easy to get wrong
when you are trying to solve problems. You need not care how the
plumbing works until you need to customize something. And, when you do,
you are better informed and prepared to do so.

It is also equally essential that frameworks, such as Spring Boot, get
out of the way quickly when application requirements diverge from the
provided defaults. This is the beautiful and powerful thing about Spring
Boot and why it is second to none in its class.

Still, auto-configuration does not solve every problem all the time.
Therefore, you need to use declarative configuration in some cases,
whether expressed as bean definitions, in properties, or by some other
means. This is so that frameworks do not leave things to chance,
especially when things are ambiguous. The framework gives you choice.

Keeping our goals in mind, this chapter:

- Refers you to the [spring-data-gemfire-name] annotations covered by [spring-boot-gemfire-name]’s
  auto-configuration.

- Lists all [spring-data-gemfire-name] annotations not covered by [spring-boot-gemfire-name]’s auto-configuration.

- Covers the [spring-boot-gemfire-name], [spring-session-gemfire-name] and [spring-data-gemfire-name] annotations that you must explicitly
  declare and that provide the most value and productivity when getting
  started with [vmware-gemfire-name] in Spring \[Boot\] applications.

<p class="note"><strong>Note:</strong>
[spring-data-gemfire-name] refers to https://spring.io/projects/spring-data-gemfire[[spring-data-gemfire-name]]. [spring-session-gemfire-name] refers to
[[spring-session-gemfire-name]](https://spring.io/projects/spring-session-data-geode). [spring-boot-gemfire-name] refers to [spring-session-gemfire-name] (this project).
</p>

The list of [spring-data-gemfire-name] annotations covered by [spring-boot-gemfire-name]’s
auto-configuration is discussed in detail in the [Appendix: Auto-configuration vs.
Annotation-based configuration](./configuration-annotations.html).

To be absolutely clear about which [spring-data-gemfire-name] annotations we are referring to,
we mean the [spring-data-gemfire-name] annotations in the
[`org.springframework.data.gemfire.config.annotation`]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/config/annotation/package-summary.html)
package.

In subsequent sections, we also cover which annotations are added by
[spring-boot-gemfire-name].

### Auto-configuration

We explained auto-configuration in detail in the
[Auto-configuration](./configuration-auto.html) chapter.

### Annotations Not Covered by Auto-configuration

The following [spring-data-gemfire-name] annotations are not implicitly applied by [spring-boot-gemfire-name]’s
auto-configuration:

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

- `@EnableGemFireMockObjects`

- `@EnableHttpService`

- `@EnableIndexing`

- `@EnableOffHeap`

- `@EnableLocator`

- `@EnableManager`

- `@EnableMemcachedServer`

- `@EnablePool(s)`

- `@EnableStatistics`

- `@UseGemFireProperties`

<p class="note"><strong>Note:</strong>
This content was also covered in 
[Explicit Annotations](./appendix.html#geode-autoconfiguration-annotations-explicit).
</p>

One reason [spring-boot-gemfire-name] does not provide auto-configuration for several of the
annotations is because the annotations are server-specific:

- `@EnableCacheServer(s)`

- `@EnableGatewayReceiver`

- `@EnableGatewaySender(s)`.

- `@EnableHttpService`

- `@EnableLocator`

- `@EnableManager`

- `@EnableMemcachedServer`

Also, we [already stated](clientcache-applications.html) that [spring-boot-gemfire-name] is
opinionated about providing a `ClientCache` instance.

Other annotations are driven by need, including:

- `@EnableAutoRegionLookup` and `@EnableBeanFactoryLocator`: Really
  useful only when mixing configuration metadata formats, such as Spring
  config with [vmware-gemfire-name] `cache.xml`. This is usually the
  case only if you have legacy `cache.xml` config to begin with.
  Otherwise, you should not use these annotations.

- `@EnableCompression`: Requires the Snappy Compression Library to be on
  your application classpath.

- `@EnableDiskStore(s)` Used only for overflow and persistence.

- `@EnableOffHeap`: Enables data to be stored in main memory, which is
  useful only when your application data (that is, objects stored in
 [vmware-gemfire-name]) are generally uniform in size.

- `@EnableGemFireAsLastResource`: Needed only in the context of JTA
  Transactions.

- `@EnableStatistics`: Useful if you need runtime metrics. However,
  enabling statistics gathering does consume considerable system
  resources (CPU & Memory).

Still other annotations require more careful planning:

- `@EnableEviction`

- `@EnableExpiration`

- `@EnableIndexing`

One annotation is used exclusively for unit testing:

- `@EnableGemFireMockObjects`

The bottom-line is that a framework should not auto-configure every
possible feature, especially when the features consume additional system
resources or require more careful planning (as determined by the use
case).

However, all of these annotations are available for the application
developer to use when needed.

### Productivity Annotations

This section calls out the annotations we believe to be most beneficial
for your application development purposes when using
[vmware-gemfire-name] in Spring \[Boot\] applications.

#### `@EnableClusterAware` 

The `@EnableClusterAware` annotation is arguably the most powerful and
valuable annotation.

Example 1. Declaring `@EnableClusterAware`

``` highlight
@SpringBootApplication
@EnableClusterAware
class SpringBootGemFireClientCacheApplication {  }
```

When you annotate your main `@SpringBootApplication` class with
`@EnableClusterAware`, your Spring Boot, [vmware-gemfire-name]
`ClientCache` application is able to seamlessly switch between
client/server and local-only topologies with no code or configuration
changes, regardless of the runtime environment (such as local/standalone
versus cloud-managed environments).

When a cluster of [vmware-gemfire-name] servers is detected, the client
application sends and receives data to and from the
[vmware-gemfire-name] cluster. If a cluster is not available, the
client automatically switches to storing data locally on the client by
using `LOCAL` Regions.

Additionally, the `@EnableClusterAware` annotation is meta-annotated
with [spring-data-gemfire-name]’s
[`@EnableClusterConfiguration`]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/config/annotation/EnableClusterConfiguration.html).
annotation.

The `@EnableClusterConfiguration` annotation lets configuration metadata
defined on the client (such as Region and Index definitions, as needed
by the application based on requirements and use cases) be sent to the
cluster of servers. If those schema objects are not already present,
they are created by the servers in the cluster in such a way that the
servers remember the configuration on restart as well as provide the
configuration to new servers that join the cluster when it is scaled
out. This feature is careful not to stomp on any existing Region or
Index objects already defined on the servers, particularly since you may
already have critical data stored in the Regions.

The primary motivation for the `@EnableClusterAware` annotation is to
let you switch environments with minimal effort. It is a common
development practice to debug and test your application locally (in your
IDE) and then push up to a production-like (staging) environment for
more rigorous integration testing.

By default, the configuration metadata is sent to the cluster by using a
non-secure HTTP connection. However, you can configure HTTPS, change the
host and port, and configure the data management policy used by the
servers when creating Regions.

See the section in the [spring-data-gemfire-name] reference documentation
on
[Configuring Cluster Configuration Push]([spring-data-gemfire-docs]/#bootstrap-annotation-config-cluster) for more details.

##### @EnableClusterAware, strictMatch

The `strictMatch` attribute has been added to the `@EnableClusterAware`
annotation to enable fail-fast behavior. `strictMatch` is set to `false`
by default.

Essentially, when you set `strictMatch` to `true`, your Spring Boot,
[vmware-gemfire-name] `ClientCache` application requires an
[vmware-gemfire-name] cluster to exist. That is, the application
requires a client/server topology to operate, and the application should
fail to start if a cluster is not present. The application should not
startup in a local-only capacity.

When `strictMatch` is set to `true` and an [vmware-gemfire-name]
cluster is not available, your Spring Boot, [vmware-gemfire-name]
`ClientCache` application fails to start with a
`ClusterNotFoundException`. The application does not attempt to start in
a local-only capacity.

You can explicitly set the `strictMatch` attribute programmatically by
using the `@EnableClusterAware` annotation:

Example 2. Set `@EnableClusterAware.strictMatch`

``` highlight
@SpringBootApplication
@EnableClusterAware(strictMatch = true)
class SpringBootGemFireClientCacheApplication {  }
```

Alternatively, you can set `strictMatch` attribute by using the
corresponding property in Spring Boot `application.properties`:

Example 3. Set `strictMatch` using a property

``` highlight
# Spring Boot application.properties

spring.boot.data.gemfire.cluster.condition.match.strict=true
```

This is convenient when you need to apply this configuration setting
conditionally, based on a Spring profile.

When you adjust the log level of the
`org.springframework.geode.config.annotation.ClusterAwareConfiguration`
logger to `INFO`, you get more details from the `@EnableClusterAware`
functionality when applying the logic to determine the presence of an
[vmware-gemfire-name] cluster, such as which explicitly or implicitly
configured connections were successful.

The following example shows typical output:

Example 4. `@EnableClusterAware` INFO log output

``` highlight
2021-01-20 14:02:28,740  INFO fig.annotation.ClusterAwareConfiguration: 476 - Failed to connect to localhost[40404]
2021-01-20 14:02:28,745  INFO fig.annotation.ClusterAwareConfiguration: 476 - Failed to connect to localhost[10334]
2021-01-20 14:02:28,746  INFO fig.annotation.ClusterAwareConfiguration: 470 - Successfully connected to localhost[57649]
2021-01-20 14:02:28,746  INFO fig.annotation.ClusterAwareConfiguration: 576 - Cluster was found; Auto-configuration made [1] successful connection(s);
2021-01-20 14:02:28,746  INFO fig.annotation.ClusterAwareConfiguration: 586 - Spring Boot application is running in a client/server topology, using a standalone [vmware-gemfire-name]-based cluster
```

<p class="note"><strong>Note:</strong>
An attempt is always made to connect to
<code>localhost</code> on the default <code>Locator</code> port,
<code>10334</code>, and the default <code>CacheServer</code> port,
<code>40404</code>.
</p>

You can force a successful match by setting the
<code>spring.boot.data.gemfire.cluster.condition.match</code> property
to <code>true</code> in Spring Boot <code>application.properties</code>.
This is sometimes useful for testing purposes.

#### `@EnableCachingDefinedRegions`, `@EnableClusterDefinedRegions` and `@EnableEntityDefinedRegions` 

These annotations are used to create Regions in the cache to manage your
application data.

You can create Regions by using Java configuration and the Spring API as
follows:

Example 5. Creating a Region with Spring JavaConfig

``` highlight
@Configuration
class GemFireConfiguration {

    @Bean("Customers")
    ClientRegionFactoryBean<Long, Customer> customersRegion(GemFireCache cache) {

        ClientRegionFactoryBean<Long, Customer> customers =
            new ClientRegionFactoryBean<>();

        customers.setCache(cache);
        customers.setShortcut(ClientRegionShortcut.PROXY);

        return customers;
    }
}
```

You can do the same in XML:

Example 6. Creating a client Region using Spring XML

``` highlight
<gfe:client-region id="Customers" shorcut="PROXY"/>
```

However, using the provided annotations is far easier, especially during
development, when the complete Region configuration may be unknown and
you want only to create a Region to persist your application data and
move on.

##### `@EnableCachingDefinedRegions`

The `@EnableCachingDefinedRegions` annotation is used when you have
application components registered in the Spring container that are
annotated with Spring or JSR-107 JCache
[spring-framework-docs]/integration.html#cache-jsr-107\[annotations\].

Caches that are identified by name in the caching annotations are used
to create Regions that hold the data you want cached.

Consider the following example:

Example 7. Defining Regions based on Spring or JSR-107 JCache
Annotations

``` highlight
@Service
class CustomerService {

    @Cacheable(cacheNames = "CustomersByAccountNumber", key = "#account.number")
    public Customer findBy(Account account) {
        // ...
    }
}
```

Further consider the following example, in which the main
`@SpringBootApplication` class is annotated with
`@EnableCachingDefinedRegions`:

Example 8. Using `@EnableCachingDefinedRegions`

``` highlight
@SpringBootApplication
@EnableCachingDefinedRegions
class SpringBootGemFireClientCacheApplication {  }
```

With this setup, [spring-boot-gemfire-name] would create a client `PROXY` Region (or
`PARTITION_REGION` if your application were a peer member of the
[vmware-gemfire-name] cluster) with a name of
“CustomersByAccountNumber”, as though you created the Region by using
either the Java configuration or XML approaches shown earlier.

You can use the `clientRegionShortcut` or `serverRegionShortcut`
attribute to change the data management policy of the Regions created on
the client or servers, respectively.

For client Regions, you can also set the `poolName` attribute to assign
a specific `Pool` of connections to be used by the client `*PROXY`
Regions to send data to the cluster.

##### `@EnableEntityDefinedRegions`

As with `@EnableCachingDefinedRegions`, `@EnableEntityDefinedRegions`
lets you create Regions based on the entity classes you have defined in
your application domain model.

For instance, consider an entity class annotated with [spring-data-gemfire-name]’s
[spring-data-gemfire-javadoc]/org/springframework/data/gemfire/mapping/annotation/Region.html\[`@Region`\]
mapping annotation:

Example 9. Customer entity class annotated with `@Region`

``` highlight
@Region("Customers")
class Customer {

    @Id
    private Long id;

    @Indexed
    private String name;

}
```

For this class, [spring-boot-gemfire-name] creates Regions from the name specified in the
`@Region` mapping annotation on the entity class. In this case, the
`Customer` application-defined entity class results in the creation of a
Region named “Customers” when the main `@SpringBootApplication` class is
annotated with `@EnableEntityDefinedRegions`:

Example 10. Using `@EnableEntityDefinedRegions`

``` highlight
@SpringBootApplication
@EnableEntityDefinedRegions(basePackageClasses = Customer.class,
    clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
class SpringBootGemFireClientCacheApplication {  }
```

As with the `@EnableCachingDefinedRegions` annotation, you can set the
client and server Region data management policy by using the
`clientRegionShortcut` and `serverRegionShortcut` attributes,
respectively, and set a dedicated `Pool` of connections used by client
Regions with the `poolName` attribute.

However, unlike the `@EnableCachingDefinedRegions` annotation, you must
specify either the `basePackage` attribute or the type-safe
`basePackageClasses` attribute (recommended) when you use the
`@EnableEntityDefinedRegions` annotation.

Part of the reason for this is that `@EnableEntityDefinedRegions`
performs a component scan for the entity classes defined by your
application. The component scan loads each class to inspect the
annotation metadata for that class. This is not unlike the JPA entity
scan when working with JPA providers, such as Hibernate.

Therefore, it is customary to limit the scope of the scan. Otherwise,
you end up potentially loading many classes unnecessarily. After all,
the JVM uses dynamic linking to load classes only when needed.

Both the `basePackages` and `basePackageClasses` attributes accept an
array of values. With `basePackageClasses`, you need only refer to a
single class type in that package and every class in that package as
well as classes in the sub-packages are scanned to determine if the
class type represents an entity. A class type is an entity if it is
annotated with the `@Region` mapping annotation. Otherwise, it is not
considered to be an entity.

For example, suppose you had the following structure:

Example 11. Entity Scan

``` highlight
- example.app.crm.model
 |- Customer.class
 |- NonEntity.class
 |- contact
   |- Address.class
   |- PhoneNumber.class
   |- AnotherNonEntity.class
- example.app.accounts.model
 |- Account.class
...
..
.
```

Then you could configure the `@EnableEntityDefinedRegions` as follows:

Example 12. Targeting with `@EnableEntityDefinedRegions`

``` highlight
@SpringBootApplication
@EnableEntityDefinedRegions(basePackageClasses = { NonEntity.class, Account.class } )
class SpringBootGemFireClientCacheApplication {  }
```

If `Customer`, `Address`, `PhoneNumber` and `Account` were all entity
classes properly annotated with `@Region`, the component scan would pick
up all these classes and create Regions for them. The `NonEntity` class
serves only as a marker in this case, to point to where (that is, which
package) the scan should begin.

Additionally, the `@EnableEntityDefinedRegions` annotation provides
include and exclude filters, the same as the core Spring Frameworks
`@ComponentScan` annotation.

See the [spring-data-gemfire-name] reference documentation on
[Configuring Regions]([spring-data-gemfire-docs]/#bootstrap-annotation-config-regions) for more details.

##### `@EnableClusterDefinedRegions`

Sometimes, it is ideal or even necessary to pull configuration from the
cluster (rather than push configuration to the cluster). That is, you
want the Regions defined on the servers to be created on the client and
used by your application.

To do so, annotate your main `@SpringBootApplication` class with
`@EnableClusterDefinedRegions`:

Example 13. Using `@EnableClusterDefinedRegions`

``` highlight
@SpringBootApplication
@EnableClusterDefinedRegions
class SpringBootGemFireClientCacheApplication {  }
```

Every Region that exists on the servers in the [vmware-gemfire-name]
cluster will have a corresponding `PROXY` Region defined and created on
the client as a bean in your Spring Boot application.

If the cluster of servers defines a Region called “ServerRegion”, you
can inject a client `PROXY` Region with the same name (“ServerRegion”)
into your Spring Boot application:

Example 14. Using a server-side Region on the client

``` highlight
@Component
class SomeApplicationComponent {

    @Resource(name = "ServerRegion")
    private Region<Integer, EntityType> serverRegion;

    public void someMethod() {

        EntityType entity = new EntityTypeImpl(...);

        this.serverRegion.put(1, entity);

        // ...
    }
}
```

[spring-boot-gemfire-name] auto-configures a `GemfireTemplate` for the “ServerRegion” Region
(see
[RegionTemplateAutoConfiguration](configuration-auto.html#regiontemplateautoconfiguration),
so a better way to interact with the client `PROXY` Region that
corresponds to the “ServerRegion” Region on the server is to inject the
template:

Example 15. Using a server-side Region on the client with a template

``` highlight
@Component
class SomeApplicationComponent {

    @Autowired
    @Qualifier("serverRegionTemplate")
    private GemfireTemplate serverRegionTemplate;

    public void someMethod() {

        EntityType entity = new EntityTypeImpl(...);

        this.serverRegionTemplate.put(1, entity);

        //...
    }
}
```

See the [spring-data-gemfire-name] reference documentation on
[Configuring Cluster-defined Regions]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-cluster-defined) for more details.

#### `@EnableIndexing` 

You can also use the `@EnableIndexing` annotation — but only when you
use `@EnableEntityDefinedRegions`. This is because `@EnableIndexing`
requires the entities to be scanned and analyzed for mapping metadata
(defined on the class type of the entity). This includes annotations
such as the Spring Data Commons `@Id` annotation and the annotations
provided by [spring-data-gemfire-name], such as `@Indexed`.

The `@Id` annotation identifies the (primary) key of the entity. The
`@Indexed` annotation defines OQL indexes on object fields, which can be
used in the predicates of your OQL queries.


You may have noticed that the `Customer` entity class’s `name` field was
annotated with `@Indexed`.

Consider the following listing:

Example 16. Customer entity class with `@Indexed` annotated `name` field

``` highlight
@Region("Customers")
class Customer {

    @Id
    private Long id;

    @Indexed
    private String name;

}
```

As a result, when our main `@SpringBootApplication` class is annotated
with `@EnableIndexing`, an [vmware-gemfire-name] OQL Index for the
`Customer.name` field is created, allowing OQL queries on customers by
name to use this Index:

Example 17. Using `@EnableIndexing`

``` highlight
@SpringBootApplication
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
@EnableIndexing
class SpringBootGemFireClientCacheApplication {  }
```

<p class="note"><strong>Note:</strong>
Keep in mind that OQL Indexes are not persistent
between restarts (that is, [vmware-gemfire-name] maintains Indexes in
memory only). An OQL Index is always rebuilt when the node is
restarted.
</p>

When you combine `@EnableIndexing` with either
`@EnableClusterConfiguration` or `@EnableClusterAware`, the Index
definitions are pushed to the server-side Regions where OQL queries are
generally executed.

See the [spring-data-gemfire-name] reference documentation on
[Configuring Indexes]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-indexes) for more details.

#### `@EnableExpiration` 

It is often useful to define both eviction and expiration policies,
particularly with a system like [vmware-gemfire-name], because it
primarily keeps data in memory (on the JVM Heap). Your data volume size
may far exceed the amount of available JVM Heap memory, and keeping too
much data on the JVM Heap can cause Garbage Collection (GC) issues.

You can enable off-heap (or main memory usage)
capabilities by declaring [spring-data-gemfire-name]’s <code>@EnableOffHeap</code> annotation.
See the [spring-data-gemfire-name] reference documentation on
[Configuring Off-Heap Memory]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-off-heap) for more details.

Defining eviction and expiration policies lets you limit what is kept in
memory and for how long.

While
[configuring eviction]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-eviction\) is easy with [spring-data-gemfire-name], we particularly want to call out expiration
since
[configuring expiration]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-expiration\) has special support in [spring-data-gemfire-name].

With [spring-data-gemfire-name], you can define the expiration policies associated with a
particular application class type on the class type itself, by using the
[`@Expiration`]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/expiration/Expiration.html),
[`@IdleTimeoutExpiration`]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/expiration/IdleTimeoutExpiration.html)
and
[`@TimeToLiveExpiration`]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/expiration/TimeToLiveExpiration.html)
annotations.

See the [vmware-gemfire-name]
[User Guide](https://docs.vmware.com/en/VMware-GemFire/9.15/gf/developing-expiration-how_expiration_works.html)
for more details on the different expiration types — that is
<em>Idle Timeout</em> (TTI) versus <em>Time-to-Live</em> (TTL).

For example, suppose we want to limit the number of `Customers`
maintained in memory for a period of time (measured in seconds) based on
the last time a `Customer` was accessed (for example, the last time a
`Customer` was read). To do so, we can define an idle timeout expiration
(TTI) policy on our `Customer` class type:

Example 18. Customer entity class with Idle Timeout Expiration (TTI)

``` highlight
@Region("Customers")
@IdleTimeoutExpiration(action = "INVALIDATE", timeout = "300")
class Customer {

    @Id
    private Long id;

    @Indexed
    private String name;

}
```

The `Customer` entry in the `Customers` Region is `invalidated` after
300 seconds (5 minutes).

To enable annotation-based expiration policies, we need to annotate our
main `@SpringBootApplication` class with `@EnableExpiration`:

Example 19. Enabling Expiration

``` highlight
@SpringBootApplication
@EnableExpiration
class SpringBootGemFireApplication {  }
```

<p class="note"><strong>Note:</strong>
Technically, this entity-class-specific
annotation-based expiration policy is implemented by using
[vmware-gemfire-name]'s
[<code>CustomExpiry</code>](https://gemfire.docs.pivotal.io/apidocs/tgf-915/index.html?org/apache/geode/cache/CustomExpiry.html)
interface.
</p>

See the [spring-data-gemfire-name] reference doccumentation for more
details on
[configuring expiration]([spring-data-gemfire-docs]/#bootstrap-annotation-config-region-expiration), along with
[annotation-based data expiration]([spring-data-gemfire-docs]/#bootstrap:region:expiration:annotation) in particular.

#### `@EnableGemFireMockObjects` (STDG)

Software testing in general and unit testing in particular are a very
important development tasks to ensure the quality of your Spring Boot
applications.

[vmware-gemfire-name] can make testing difficult in some cases,
especially when tests have to be written as integration tests to assert
the correct behavior. This can be very costly and lengthens the feedback
cycle. Fortunately, you can write unit tests as well.

Spring provides a framework for testing Spring Boot applications that
use [vmware-gemfire-name]. This is where the
[Spring Test for [vmware-gemfire-name] (STDG)](https://github.com/gemfire/spring-data-test-for-vmware-gemfire) project can help, particularly with unit
testing.

For example, if you do not care what [vmware-gemfire-name] would
actually do in certain cases and only care about the “contract”, which
is what mocking a collaborator is all about, you could effectively mock
[vmware-gemfire-name] objects to isolate the SUT, or “Subject Under
Test”, and focus on the interactions or outcomes you expect to happen.

With STDG, you need not change a bit of configuration to enable mock
objects in the unit tests for your Spring Boot applications. You need
only annotate the test class with `@EnableGemFireMockObjects`:

Example 20. Using Mock [vmware-gemfire-name] Objects

``` highlight
@RunWith(SpringRunner.class)
@SpringBootTest
class MyApplicationTestClass {

    @Test
    public void someTestCase() {
        // ...
    }

    @Configuration
    @EnableGemFireMockObjects
    static class GemFireConfiguration { }

}
```

Your Spring Boot configuration of [vmware-gemfire-name] returns mock
objects for all [vmware-gemfire-name] objects, such as Regions.

Mocking [vmware-gemfire-name] objects even works for objects created
from the productivity annotations discussed in the previous sections.

For example, consider the following Spring Boot, [vmware-gemfire-name]
`ClientCache` application class:

Example 21. Main `@SpringBootApplication` class under test

``` highlight
@SpringBootApplication
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
class SpringBootGemFireClientCacheApplication {  }
```

In the preceding example, the
`` "Customers`" Region defined by the `Customer `` entity class and
created by the `@EnableEntityDefinedRegions` annotation would be a mock
Region and not an actual Region. You can still inject the Region in your
test and assert interactions on the Region based on your application
workflows:

Example 22. Using Mock [vmware-gemfire-name] Objects

``` highlight
@RunWith(SpringRunner.class)
@SpringBootTest
class MyApplicationTestClass {

    @Resource(name = "Customers")
    private Region<Long, Customer> customers;

    @Test
    public void someTestCase() {

        Customer jonDoe = new Customer(1, "Jon Doe");

        // Use the application in some way and test the interaction on the "Customers" Region

        assertThat(this.customers).containsValue(jonDoe);

        // ...
    }
}
```

There are many more things that STDG can do for you in both unit testing
and integration testing.
