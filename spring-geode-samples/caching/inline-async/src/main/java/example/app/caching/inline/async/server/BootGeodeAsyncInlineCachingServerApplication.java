/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;

import example.app.caching.inline.async.config.AsyncInlineCachingConfiguration;
import example.app.caching.inline.async.config.AsyncInlineCachingRegionConfiguration;

/**
 * {@link SpringBootApplication} class implementing the server-side of the golf tournament management application.
 *
 * @author John Blum
 * @see java.time.Duration
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.boot.builder.SpringApplicationBuilder
 * @see org.springframework.context.annotation.Import
 * @see org.springframework.context.annotation.Profile
 * @see org.springframework.data.gemfire.config.annotation.CacheServerApplication
 * @see org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions
 * @see example.app.caching.inline.async.config.AsyncInlineCachingConfiguration
 * @see example.app.caching.inline.async.client.model.Golfer
 * @since 1.4.0
 */
@SpringBootApplication
@Profile("server")
public class BootGeodeAsyncInlineCachingServerApplication {

	private static final String APPLICATION_NAME = "GolfServerApplication";

	public static void main(String[] args) {

		new SpringApplicationBuilder(BootGeodeAsyncInlineCachingServerApplication.class)
			.web(WebApplicationType.NONE)
			.build()
			.run(args);
	}

	@CacheServerApplication(name = APPLICATION_NAME)
	@Import({ AsyncInlineCachingConfiguration.class, AsyncInlineCachingRegionConfiguration.class })
	@SuppressWarnings("unused")
	static class GeodeConfiguration { }

}
