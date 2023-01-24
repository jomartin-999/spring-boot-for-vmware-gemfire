/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.near.client.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * {@link Person} models a person (human being).
 *
 * @author John Blum
 * @since 1.1.0
 */
// tag::class[]
@Getter
@EqualsAndHashCode(of = "name")
@ToString(of = { "name", "email", "phoneNumber" })
@RequiredArgsConstructor(staticName = "newPerson")
public class Person {

	@NonNull
	private String name;

	private String email;
	private String phoneNumber;

	public Person withEmail(String email) {
		this.email = email;
		return this;
	}

	public Person withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
}
// end::class[]
