/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.security.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import example.app.security.client.BootGeodeSecurityClientApplication;

/**
 * A Spring {@link RestController} used by {@link BootGeodeSecurityClientApplication}.
 *
 * @author Patrick Johnson
 * @see org.springframework.web.bind.annotation.RestController
 * @since 1.4.0
 */
// tag::class[]
@RestController
public class SecurityController {

	@Autowired
	private Environment environment;

	@GetMapping("/message")
	public String getMessage() {
		return String.format("I'm using SSL with this Keystore: %s",
			this.environment.getProperty("spring.data.gemfire.security.ssl.keystore"));
	}
}
// end::class[]
