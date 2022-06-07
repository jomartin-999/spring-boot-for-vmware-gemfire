/*
 * Copyright (c) VMware, Inc. 2022. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.vmware.gemfire.gradle.plugins

import org.gradle.api.Project

class DependencyConstraints {
/** By necessity, the version of those plugins used in the build-scripts are defined in the
 * buildscript {} configuration in the root project's build.gradle. */
  static Map<String, String> disparateDependencies = initExternalDependencies()

  static String get(String name) {
    return disparateDependencies.get(name)
  }


  static private Map<String, String> initExternalDependencies() {
    Map<String, String> depVersionMapping = new HashMap<>()

    depVersionMapping.put("antlrVersion", "2.7.7")
    depVersionMapping.put("findbugsVersion", "3.0.2")
    depVersionMapping.put("multithreadedtcVersion", "1.01")
    depVersionMapping.put("springBootVersion", "2.6.13")
    depVersionMapping.put("springBootDataGeodeVersion", "1.6.13")
    depVersionMapping.put("springDataGeodeTestVersion", "0.2.3-Q")
    depVersionMapping.put("springShellVersion", "1.2.0.RELEASE")
    depVersionMapping.put("testcontainersVersion", "1.17.5")
    depVersionMapping.put("gemfireVersion", "9.15.2")

    return depVersionMapping
  }

  void apply(Project project) {
    project.dependencies {

      api(platform(group: 'org.springframework.boot', name: 'spring-boot-dependencies', version: get('springBootVersion')))
      api(platform(group: 'org.testcontainers', name: 'testcontainers-bom', version: get('testcontainersVersion')))
      implementation(platform(group: 'com.vmware.gemfire', name: 'gemfire-server-bom', version: get('gemfireVersion')))

      constraints {
        api(group: 'antlr', name: 'antlr', version: get('antlrVersion'))
        api(group: 'com.google.code.findbugs', name: 'jsr305', version: get('findbugsVersion'))
        api(group: 'edu.umd.cs.mtc', name: 'multithreadedtc', version: get('multithreadedtcVersion'))
        api(group: 'org.springframework.data', name: 'spring-data-geode-test', version: get('springDataGeodeTestVersion'))
        api(group: 'org.springframework.shell', name: 'spring-shell', version: get('springShellVersion'))
        api(group: 'org.springframework.boot', name: 'spring-boot-configuration-processor', version: get('springBootVersion'))
        api(group: 'org.springframework.boot', name: 'spring-boot-autoconfigure-processor', version: get('springBootVersion'))
        api(group: 'org.springframework.security', name: 'spring-security-core', version: '5.6.9' )
        api(group: 'org.springframework.security', name: 'spring-security-web', version: '5.6.9' )
        api(group: 'org.testcontainers', name: 'testcontainers', version: get('testcontainersVersion'))
        api(group: 'org.testcontainers', name: 'cassandra', version: get('testcontainersVersion'))
        api(group: 'org.springframework.geode', name: 'spring-geode-actuator-autoconfigure', version: get('springBootDataGeodeVersion'))
      }

    }
  }
}