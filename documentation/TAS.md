---
Title:VMware Tanzu Application Service
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

In most cases, when you deploy (that is, `cf push`) your Spring Boot
applications to VMware Tanzu Application Service (TAS), you bind your application to
one or more instances of the [vmware-gemfire-name] for TAS service.

In a nutshell, [[vmware-gemfire-name] for TAS](https://www.vmware.com/products/gemfire.html)
is a managed version of
[[vmware-gemfire-name]](https://www.vmware.com/products/gemfire.html) that runs in
[Tanzu Application Service](https://tanzu.vmware.com/tanzu) (TAS). When
running in or across cloud environments (such as AWS, Azure, or GCP),
[vmware-gemfire-name] for TAS with TAS offers several advantages over trying to run and
manage your own standalone [vmware-gemfire-name] clusters. It handles
many of the infrastructure-related, operational concerns so that you
need not do so.

### Running a Spring Boot application as a specific user

By default, Spring Boot applications run as a `cluster_operator`
role-based user inVMware Tanzu Application Service when the application is bound to
a [vmware-gemfire-name] for TAS service instance.

A `cluster_operator` has full system privileges (that is, authorization)
to do whatever that user wishes to involving the [vmware-gemfire-name] for TAS service instance. A
`cluster_operator` has read and write access to all the data, can modify
the schema (for example, create and destroy Regions, add and remove
Indexes, change eviction or expiration policies, and so on), start and
stop servers in the [vmware-gemfire-name] for TAS cluster, or even modify permissions.


About cluster_operator as the default user

One of the reasons why Spring Boot applications default to running as a
`cluster_operator` is to allow configuration metadata to be sent from
the client to the server. Enabling configuration metadata to be sent
from the client to the server is a useful development-time feature and
is as simple as annotating your main `@SpringBootApplication` class with
the `@EnableClusterConfiguration` annotation:

Example 1. Using `@EnableClusterConfiguration`


``` highlight
@SpringBootApplication
@EnableClusterConfiguration(useHttp = true)
class SpringBootGemFireClientCacheApplication {  }
```

With `@EnableClusterConfiguration`, Region and OQL Index configuration
metadata that is defined on the client can be sent to servers in the [vmware-gemfire-name] for TAS
cluster. [vmware-gemfire-name] requires matching Regions by name on
both the client and the servers in order for clients to send and receive
data to and from the cluster.

For example, when you declare the Region where an application entity is
persisted by using the `@Region` mapping annotation and declare the
`@EnableEntityDefinedRegions` annotation on the main
`@SpringBootApplication` class in conjunction with the
`@EnableClusterConfiguration` annotation, not only does [spring-boot-gemfire-name] create the
required client Region, but it also sends the configuration metadata for
this Region to the servers in the cluster to create the matching,
required server Region, where the data for your application entity is
managed.


However...

> 
>
> With great power comes great responsibility. - Uncle Ben
>
> 

Not all Spring Boot applications using [vmware-gemfire-name] for TAS need to change the schema or
even modify data. Rather, certain applications may need only read
access. Therefore, it is ideal to be able to configure your Spring Boot
applications to run with a different user at runtime other than the
auto-configured `cluster_operator`, by default.

A prerequisite for running a Spring Boot application in [vmware-gemfire-name] for TAS with a
specific user is to create a user with restricted permissions by using
TAS Apps Manager while provisioning the [vmware-gemfire-name] for TAS service
instance to which the Spring Boot application is bound.

Example 2. Configuring a Spring Boot application to run as a specific
user


``` highlight
# Spring Boot application.properties for TAS when using [vmware-gemfire-name] for TAS

spring.data.gemfire.security.username=guest
```

The
<code>spring.data.gemfire.security.username</code> property corresponds
directly to the [spring-data-gemfire-name] <code>@EnableSecurity</code> annotation’s
<code>securityUsername</code> attribute. See the
[Javadoc]([spring-data-gemfire-javadoc]/org/springframework/data/gemfire/config/annotation/EnableSecurity.html#securityUsername--)
for more details.

The `spring.data.gemfire.security.username` property is the same
property used by [spring-data-gemfire-name] to
configure the runtime user of your Spring Data application when you
connect to an externally managed [vmware-gemfire-name] cluster.

In this case, [spring-boot-gemfire-name] uses the configured username to look up the
authentication credentials of the user to set the username and password
used by the Spring Boot `ClientCache` application when connecting to [vmware-gemfire-name] for TAS
while running in TAS.

If the username is not valid, an `IllegalStateException` is thrown.

By using [Spring profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles), it would be a simple matter to configure the Spring Boot
application to run with a different user depending on environment.

See the [vmware-gemfire-name] for TAS documentation on
[security](https://docs.vmware.com/en/VMware-GemFire-for-Tanzu-Application-Service/1.14/gf-tas/content-security.html) for configuring
users with assigned roles and permissions.

#### Overriding Authentication Auto-configuration

It should be understood that auto-configuration for client
authentication is available only for managed environments, such as
Tanzu Application Service. When running in externally managed environments,
you must explicitly set a username and password to authenticate, as
described in
[Non-Managed Auth for Clients](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles).

To completely override the auto-configuration of client authentication,
you can set both a username and a password:

Example 3. Overriding Security Authentication Auto-configuration with
explicit username and password


``` highlight
# Spring Boot application.properties

spring.data.gemfire.security.username=MyUser
spring.data.gemfire.security.password=MyPassword
```

In this case, [spring-boot-gemfire-name]’s auto-configuration for authentication is
effectively disabled and security credentials are not extracted from the
environment.


### Targeting Specific [vmware-gemfire-name] for TAS Service Instances

It is possible to provision multiple instances of the VMware GemFire for TAS service in yourVMware Tanzu Application Service environment. You can then
bind multiple [vmware-gemfire-name] for TAS service instances to your Spring Boot application.

However, [spring-boot-gemfire-name] only
auto-configures one [vmware-gemfire-name] for TAS service instance for your Spring Boot
application. This does not mean that it is not possible to use multiple
[vmware-gemfire-name] for TAS service instances with your Spring Boot application, just that [spring-boot-gemfire-name]
only auto-configures one service instance for you.

You must select which [vmware-gemfire-name] for TAS service instance your Spring Boot application
automatically auto-configures for you when you have multiple instances
and want to target a specific [vmware-gemfire-name] for TAS service instance to use.

To do so, declare the following [spring-boot-gemfire-name] property in Spring Boot
`application.properties`:

Example 4. Spring Boot application.properties targeting a specific [vmware-gemfire-name] for TAS
service instance by name


``` highlight
# Spring Boot application.properties

spring.boot.data.gemfire.cloud.cloudfoundry.service.cloudcache.name=gemFireServiceInstanceTwo
```

The
`spring.boot.data.gemfire.cloud.cloudfoundry.service.cloudcache.name`
property tells [spring-boot-gemfire-name] which [vmware-gemfire-name] for TAS service instance to auto-configure.

If the [vmware-gemfire-name] for TAS service instance identified by the property does not exist,
[spring-boot-gemfire-name] throws an `IllegalStateException` stating the [vmware-gemfire-name] for TAS service instance
by name could not be found.

If you did not set the property and your Spring Boot application is
bound to multiple [vmware-gemfire-name] for TAS service instances, [spring-boot-gemfire-name] auto-configures the first
[vmware-gemfire-name] for TAS service instance it finds by name, alphabetically.

If you did not set the property and no [vmware-gemfire-name] for TAS service instance is found,
[spring-boot-gemfire-name] logs a warning.

### Using Multiple [vmware-gemfire-name] for TAS Service Instances

If you want to use multiple [vmware-gemfire-name] for TAS service instances with your Spring Boot
application, you need to configure multiple connection `Pools` connected
to each [vmware-gemfire-name] for TAS service instance used by your Spring Boot application.

The configuration would be similar to the following:

Example 5. Multiple [vmware-gemfire-name] for TAS Service Instance Configuration


``` highlight
@Configuration
@EnablePools(pools = {
  @EnablePool(name = "One"),
  @EnablePool(name = "Two"),
  ...,
  @EnablePool(name = "GemFireN")
})
class GemFireConfiguration {
  // ...
}
```

You would then externalize the configuration for the individually
declared `Pools` in Spring Boot `application.properties`:

Example 6. Configuring Locator-based Pool connections


``` highlight
# Spring Boot `application.properties`

spring.data.gemfire.pool.one.locators=gemFireOneHost1[port1], gemFireOneHost2[port2], ..., gemFireOneHostN[portN]

spring.data.gemfire.pool.two.locators=gemFireTwoHost1[port1], gemFireTwoHost2[port2], ..., gemFireTwoHostN[portN]
```

<p class="note"><strong>Note:</strong>
Though less common, you can also configure the
<code>Pool</code> of connections to target specific servers in the
cluster by setting the
<code>spring.data.gemfire.pool.&lt;named-pool&gt;.severs</code>
property.
</p>

Keep in mind that properties in Spring Boot
<code>application.properties</code> can refer to other properties:
<code>property=${otherProperty}</code>. This lets you further
externalize properties by using Java System properties or environment
variables.

A client Region is then assigned the Pool of connections that are used
to send data to and from the specific [vmware-gemfire-name] for TAS service instance (cluster):

Example 7. Assigning a Pool to a client Region


``` highlight
@Configuration
class GemFireConfiguration {

  @Bean("Example")
  ClientRegionFactoryBean exampleRegion(GemFireCache gemfireCache,
      @Qualifier("Two") Pool poolTwo) {

    ClientRegionFactoryBean exampleRegion = new ClientRegionFactoryBean();

    exampleRegion.setCache(gemfireCache);
    exampleRegion.setPool(poolTwo);
    exampleRegion.setShortcut(ClientRegionShortcut.PROXY);

    return exampleRegion;
  }
}
```

You can configure as many Pools and client Regions as your application
needs. Again, the `Pool` determines the [vmware-gemfire-name] for TAS service
instance and cluster in which the data for the client Region resides.

<p class="note"><strong>Note:</strong>
By default, [spring-boot-gemfire-name] configures all <code>Pools</code>
declared in a Spring Boot <code>ClientCache</code> application to
connect to and use a single [vmware-gemfire-name] for TAS service instance. This may be a targeted
[vmware-gemfire-name] for TAS service instance when you use the
<code>spring.boot.data.gemfire.cloud.cloudfoundry.service.cloudcache.name</code>
property as discussed <a
href="#cloudfoundry-cloudcache-multiinstance-using">earlier</a>.
</p>

