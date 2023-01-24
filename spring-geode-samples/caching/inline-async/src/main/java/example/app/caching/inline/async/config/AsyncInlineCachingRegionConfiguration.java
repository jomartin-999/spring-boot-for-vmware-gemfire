/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.config;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.geode.cache.AsyncInlineCachingRegionConfigurer;

import example.app.caching.inline.async.client.model.Golfer;

/**
 * Spring {@link Configuration} class used to configure an Apache Geode cache {@link Region}
 *
 * @author John Blum
 * @see org.apache.geode.cache.GemFireCache
 * @see org.apache.geode.cache.Region
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.ReplicatedRegionFactoryBean
 * @see org.springframework.geode.cache.AsyncInlineCachingRegionConfigurer
 * @since 1.4.0
 */
@Configuration
@SuppressWarnings("unused")
public class AsyncInlineCachingRegionConfiguration {

	protected static final String GOLFERS_REGION_NAME = "Golfers";

	@Bean(GOLFERS_REGION_NAME)
	public ReplicatedRegionFactoryBean<Object, Object> golfersRegion(GemFireCache gemfireCache,
			AsyncInlineCachingRegionConfigurer<Golfer, String> asyncInlineCachingRegionConfigurer) {

		ReplicatedRegionFactoryBean<Object, Object> golfersRegion = new ReplicatedRegionFactoryBean<>();

		golfersRegion.setCache(gemfireCache);
		golfersRegion.setPersistent(false);
		golfersRegion.setRegionConfigurers(asyncInlineCachingRegionConfigurer);

		return golfersRegion;
	}

	@Bean
	@DependsOn(GOLFERS_REGION_NAME)
	public GemfireTemplate golfersTemplate(GemFireCache gemfireCache) {
		return new GemfireTemplate(gemfireCache.getRegion(GOLFERS_REGION_NAME));
	}
}
