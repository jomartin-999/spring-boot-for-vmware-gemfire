/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.session.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.geode.config.annotation.EnableClusterAware;

/**
 * {@link SpringBootApplication} to demo HTTP Session state caching.
 *
 * @author John Blum
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.geode.config.annotation.EnableClusterAware
 * @since 1.1.0
 */
//tag::class[]
@SpringBootApplication
@EnableClusterAware
public class BootGeodeHttpSessionCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootGeodeHttpSessionCachingApplication.class, args);
	}
}
//end::class[]
