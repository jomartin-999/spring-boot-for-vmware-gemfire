/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.lookaside;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.Resource;

import org.apache.geode.cache.Region;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport;
import org.springframework.test.context.junit4.SpringRunner;

import example.app.caching.lookaside.service.CounterService;

/**
 * Integration Tests for the Counter Service application.
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.apache.geode.cache.Region
 * @see org.springframework.boot.test.context.SpringBootTest
 * @see org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport
 * @see org.springframework.test.context.junit4.SpringRunner
 * @see example.app.caching.lookaside.service.CounterService
 * @since 1.1.0
 */
// tag::class[]
@RunWith(SpringRunner.class)
@SpringBootTest(
	properties = { "spring.boot.data.gemfire.security.ssl.environment.post-processor.enabled=false" },
	webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@SuppressWarnings("unused")
public class BootGeodeLookAsideCachingApplicationIntegrationTests extends IntegrationTestsSupport {

	@Autowired
	private CounterService counterService;

	@Resource(name = "Counters")
	private Region<String, Long> counters;

	@Before
	public void setup() {

		assertThat(this.counterService).isNotNull();
		assertThat(this.counters).isNotNull();
		assertThat(this.counters.getName()).isEqualTo("Counters");
		assertThat(this.counters).isEmpty();
	}

	@Test
	public void counterServiceCachesCounts() {

		for (int count = 1; count < 10; count++) {
			assertThat(this.counterService.getCount("TestCounter")).isEqualTo(count);
		}

		assertThat(this.counterService.getCachedCount("TestCounter")).isEqualTo(9L);
		assertThat(this.counterService.getCachedCount("TestCounter")).isEqualTo(9L);
		assertThat(this.counterService.getCachedCount("MockCounter")).isEqualTo(1L);
		assertThat(this.counterService.getCachedCount("MockCounter")).isEqualTo(1L);
	}
}
// end::class[]
