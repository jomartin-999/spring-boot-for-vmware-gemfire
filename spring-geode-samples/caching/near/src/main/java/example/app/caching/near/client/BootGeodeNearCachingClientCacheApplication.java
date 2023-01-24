/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.near.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import example.app.caching.near.client.model.Person;

/**
 * Spring Boot application demonstrating Spring's Cache Abstraction with Apache Geode as the caching provider
 * for {@literal Near Caching}.
 *
 * @author John Blum
 * @see org.apache.geode.cache.Region
 * @see org.apache.geode.cache.client.Pool
 * @see org.springframework.boot.ApplicationRunner
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Bean
 * @see example.app.caching.near.client.model.Person
 * @since 1.1.0
 */
// tag::class[]
@SpringBootApplication
public class BootGeodeNearCachingClientCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootGeodeNearCachingClientCacheApplication.class, args);
	}

	// tag::application-runner[]
	@Bean
	public ApplicationRunner runner(@Qualifier("YellowPages") Region<String, Person> yellowPages) {

		return args -> {

			assertThat(yellowPages).isNotNull();
			assertThat(yellowPages.getName()).isEqualTo("YellowPages");
			assertThat(yellowPages.getInterestListRegex()).containsAnyOf(".*");
			assertThat(yellowPages.getAttributes()).isNotNull();
			assertThat(yellowPages.getAttributes().getDataPolicy()).isEqualTo(DataPolicy.NORMAL);
			assertThat(yellowPages.getAttributes().getPoolName()).isEqualTo("DEFAULT");

			Pool defaultPool = PoolManager.find("DEFAULT");

			assertThat(defaultPool).isNotNull();
			assertThat(defaultPool.getSubscriptionEnabled()).isTrue();

		};
	}
	// end::application-runner[]
}
// end::class[]
