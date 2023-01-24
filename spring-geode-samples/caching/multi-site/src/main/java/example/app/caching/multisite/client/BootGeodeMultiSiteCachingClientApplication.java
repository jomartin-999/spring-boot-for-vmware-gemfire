/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.multisite.client;

import org.apache.geode.cache.client.ClientCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;

/**
 * The {@link BootGeodeMultiSiteCachingClientApplication} class is a Spring Boot, Apache Geode {@link ClientCache}
 * Web application that can be configured to connect to 1 of many Apache Geode clusters.
 *
 * @author John Blum
 * @see org.apache.geode.cache.client.ClientCache
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.config.annotation.ClientCacheApplication
 * @since 1.3.0
 */
// tag::class[]
@SpringBootApplication
@SuppressWarnings("unused")
public class BootGeodeMultiSiteCachingClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootGeodeMultiSiteCachingClientApplication.class, args);
	}

	@Configuration
	@EnableCachingDefinedRegions
	static class GeodeClientConfiguration { }

}
// end::class[]
