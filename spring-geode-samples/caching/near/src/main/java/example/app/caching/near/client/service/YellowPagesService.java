/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.near.client.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import example.app.caching.near.client.model.Person;
import example.app.caching.near.client.service.support.AbstractCacheableService;
import example.app.caching.near.client.service.support.EmailGenerator;
import example.app.caching.near.client.service.support.PhoneNumberGenerator;

/**
 * Spring {@link Service} class implementing the Yellow Pages.
 *
 * @author John Blum
 * @see org.springframework.cache.annotation.Cacheable
 * @see org.springframework.cache.annotation.CachePut
 * @see org.springframework.cache.annotation.CacheEvict
 * @see org.springframework.stereotype.Service
 * @see example.app.caching.near.client.model.Person
 * @see example.app.caching.near.client.service.support.AbstractCacheableService
 * @see example.app.caching.near.client.service.support.EmailGenerator
 * @see example.app.caching.near.client.service.support.PhoneNumberGenerator
 * @since 1.1.0
 */
// tag::class[]
@Service
public class YellowPagesService extends AbstractCacheableService {

	@Cacheable("YellowPages")
	public Person find(String name) {

		this.cacheMiss.set(true);

		Person person = Person.newPerson(name)
			.withEmail(EmailGenerator.generate(name, null))
			.withPhoneNumber(PhoneNumberGenerator.generate(null));

		simulateLatency();

		return person;
	}

	@CachePut(cacheNames = "YellowPages", key = "#person.name")
	public Person save(Person person, String email, String phoneNumber) {

		if (StringUtils.hasText(email)) {
			person.withEmail(email);
		}

		if (StringUtils.hasText(phoneNumber)) {
			person.withPhoneNumber(phoneNumber);
		}

		return person;
	}

	@CacheEvict(cacheNames = "YellowPages")
	public boolean evict(String name) {
		return true;
	}
}
// end::class[]
