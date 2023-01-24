/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.lookaside;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * {@link SpringBootApplication} to configure and bootstrap the example application using the
 * {@literal Look-Aside Caching pattern}, and specifically Spring's Cache Abstraction along with
 * Apache Geode as the caching provider.
 *
 * @author John Blum
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.boot.builder.SpringApplicationBuilder
 * @since 1.0.0
 */
// tag::class[]
@SpringBootApplication
public class BootGeodeLookAsideCachingApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(BootGeodeLookAsideCachingApplication.class)
			.web(WebApplicationType.SERVLET)
			.build()
			.run(args);
	}
}
// end::class[]
