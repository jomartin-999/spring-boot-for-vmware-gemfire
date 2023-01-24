/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * Smoke Tests for {@link InitializerApplication}.
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.springframework.boot.test.context.SpringBootTest
 * @see org.springframework.context.ApplicationContext
 * @see org.springframework.data.gemfire.GemfireTemplate
 * @see org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport
 * @see org.springframework.test.context.junit4.SpringRunner
 * @see example.app.InitializerApplication
 * @since 1.2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("unused")
public class InitializerApplicationSmokeTests extends IntegrationTestsSupport {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier("exampleTemplate")
	private GemfireTemplate exampleTemplate;

	@Test
	public void applicationContextLoadsAndIsCorrectType() {

		assertThat(this.applicationContext).isNotNull();
		assertThat(this.applicationContext).isNotInstanceOf(WebApplicationContext.class);
		assertThat(this.applicationContext).isNotInstanceOf(ServletWebServerApplicationContext.class);
	}

	@Test
	public void regionDataAccessOperationsFunctionAsExpected() {

		assertThat(this.exampleTemplate).isNotNull();
		assertThat(this.exampleTemplate.getRegion()).isNotNull();
		assertThat(this.exampleTemplate.getRegion().getName()).isEqualTo("Example");
		assertThat(this.exampleTemplate.put(1, "TEST")).isNull();
		assertThat(this.exampleTemplate.<Integer, String>get(1)).isEqualTo("TEST");
	}
}
