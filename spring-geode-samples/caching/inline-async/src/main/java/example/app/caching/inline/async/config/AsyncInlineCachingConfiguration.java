/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.geode.cache.AsyncInlineCachingRegionConfigurer;

import example.app.caching.inline.async.client.model.Golfer;
import example.app.caching.inline.async.client.repo.GolferRepository;

/**
 * Spring {@link Configuration} class used to configure {@literal Async Inline Caching}.
 *
 * @author John Blum
 * @see java.time.Duration
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Profile
 * @see org.springframework.geode.cache.AsyncInlineCachingRegionConfigurer
 * @see example.app.caching.inline.async.client.model.Golfer
 * @see example.app.caching.inline.async.client.repo.GolferRepository
 * @since 1.4.0
 */
// tag::class[]
@Configuration
@SuppressWarnings("unused")
public class AsyncInlineCachingConfiguration {

	protected static final String GOLFERS_REGION_NAME = "Golfers";

	// tag::queue-batch-size[]
	@Bean
	@Profile("queue-batch-size")
	AsyncInlineCachingRegionConfigurer<Golfer, String> batchSizeAsyncInlineCachingConfigurer(
			@Value("${spring.geode.sample.async-inline-caching.queue.batch-size:25}") int queueBatchSize,
			GolferRepository golferRepository) {

		return AsyncInlineCachingRegionConfigurer.create(golferRepository, GOLFERS_REGION_NAME)
			.withQueueBatchConflationEnabled()
			.withQueueBatchSize(queueBatchSize)
			.withQueueBatchTimeInterval(Duration.ofMinutes(15))
			.withQueueDispatcherThreadCount(1);
	}
	// end::queue-batch-size[]

	// tag::queue-batch-time-interval[]
	@Bean
	@Profile("queue-batch-time-interval")
	AsyncInlineCachingRegionConfigurer<Golfer, String> batchTimeIntervalAsyncInlineCachingConfigurer(
			@Value("${spring.geode.sample.async-inline-caching.queue.batch-time-interval-ms:5000}") int queueBatchTimeIntervalMilliseconds,
			GolferRepository golferRepository) {

		return AsyncInlineCachingRegionConfigurer.create(golferRepository, GOLFERS_REGION_NAME)
			.withQueueBatchSize(1000000)
			.withQueueBatchTimeInterval(Duration.ofMillis(queueBatchTimeIntervalMilliseconds))
			.withQueueDispatcherThreadCount(1);
	}
	// end::queue-batch-time-interval[]
}
// end::class[]
