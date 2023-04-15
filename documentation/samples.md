---
Title: Samples
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



> 
>
> <table>
> <colgroup>
> <col style="width: 50%" />
> <col style="width: 50%" />
> </colgroup>
> <tbody>
> <tr class="odd">
> <td class="icon">
> Note
> </td>
> <td class="content">You are viewing Samples for [spring-boot-gemfire-name] version 1.27.</td>
> </tr>
> </tbody>
> </table>
>
> 





This section contains working examples that show how to use [spring-boot-gemfire-name]  effectively.





Some examples focus on specific use cases (such as (HTTP) session state
caching), while other examples show how [spring-boot-gemfire-name] works under the hood, to
give you a better understanding of what is actually happening and how to
debug problems with your Spring Boot [vmware-gemfire-name]
applications.



<table class="tableblock frame-all grid-all stretch">
<caption>Table 1. Example Spring Boot applications using
[vmware-gemfire-name]</caption>
<colgroup>
<col style="width: 33%" />
<col style="width: 33%" />
<col style="width: 33%" />
</colgroup>
<thead>
<tr class="header">
<th class="tableblock halign-left valign-top">Guide</th>
<th class="tableblock halign-left valign-top">Description</th>
<th class="tableblock halign-left valign-top">Source</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td class="tableblock halign-left valign-top"><p><a
href="guides/getting-started.html">Getting Started with [spring-boot-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to get
started quickly, easily, and reliably building [vmware-gemfire-name]
powered applications with Spring Boot.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Getting Started](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/intro/getting-started)</p></td>
</tr>
<tr class="even">
<td class="tableblock halign-left valign-top"><p><a
href="guides/boot-configuration.html">Spring Boot Auto-Configuration for
[vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains what
auto-configuration is provided by [spring-boot-gemfire-name] and what the auto-configuration
does.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Spring Boot Auto-Configuration](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/boot/configuration)</p></td>
</tr>
<tr class="odd">
<td class="tableblock halign-left valign-top"><p><a
href="guides/boot-actuator.html">Spring Boot Actuator for
[vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to use
Spring Boot Actuator for [vmware-gemfire-name] and how it
works.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Spring Boot Actuator](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/boot/actuator)</p></td>
</tr>
<tr class="even">
<td class="tableblock halign-left valign-top"><p><a
href="guides/boot-security.html">Spring Boot Security for
[vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to
configure auth and TLS with SSL when you use [vmware-gemfire-name] in
your Spring Boot applications.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Spring Boot Security](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/boot/security)</p></td>
</tr>
<tr class="odd">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-look-aside.html">Look-Aside Caching with Spring’s
Cache Abstraction and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring’s Cache Abstraction with [vmware-gemfire-name] as the
caching provider for look-aside caching.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Look-Aside Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/look-aside)</p></td>
</tr>
<tr class="even">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-inline.html">Inline Caching with Spring’s Cache
Abstraction and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring’s Cache Abstraction with [vmware-gemfire-name] as the
caching provider for inline caching. This sample builds on the
look-aside caching sample.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Inline Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/inline)</p></td>
</tr>
<tr class="odd">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-inline-async.html">Asynchronous Inline Caching with
Spring’s Cache Abstraction and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring’s Cache Abstraction with [vmware-gemfire-name] as the
caching provider for asynchronous inline caching. This sample builds on
the look-aside and inline caching samples.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Asynchronous Inline Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/inline-async)</p></td>
</tr>
<tr class="even">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-near.html">Near Caching with Spring’s Cache
Abstraction and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring’s Cache Abstraction with [vmware-gemfire-name] as the
caching provider for near caching. This sample builds on the look-aside
caching sample.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Near Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/near)</p></td>
</tr>
<tr class="odd">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-multi-site.html">Multi-Site Caching with Spring’s
Cache Abstraction and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring’s Cache Abstraction with [vmware-gemfire-name] as the
caching provider for multi-site caching. This sample builds on the
look-aside caching sample.</p></td>
<td
class="tableblock halign-left valign-top"><p> [Multi-Site Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/multi-site)</p></td>
</tr>
<tr class="even">
<td class="tableblock halign-left valign-top"><p><a
href="guides/caching-http-session.html">HTTP Session Caching with Spring
Session and [vmware-gemfire-name]</a></p></td>
<td class="tableblock halign-left valign-top"><p>Explains how to enable
and use Spring Session with [vmware-gemfire-name] to manage HTTP
session state.</p></td>
<td
class="tableblock halign-left valign-top"><p> [HTTP Session Caching](https://github.com/gemfire/spring-boot-for-vmware-gemfire/tree/9.15-2.7/spring-gemfire-samples/caching/http-session)</p></td>
</tr>
</tbody>
</table>

Table 1. Example Spring Boot applications using [vmware-gemfire-name]







<div id="footer">

<div id="footer-text">



