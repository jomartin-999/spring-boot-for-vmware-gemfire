/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import example.app.crm.model.Customer;
import example.app.crm.repo.CustomerRepository;

/**
 * Spring Web MVC {@link RestController} used to implement a crude CRM application REST interface.
 *
 * @author John Blum
 * @see org.springframework.web.bind.annotation.GetMapping
 * @see org.springframework.web.bind.annotation.PostMapping
 * @see org.springframework.web.bind.annotation.RestController
 * @see example.app.crm.model.Customer
 * @see example.app.crm.repo.CustomerRepository
 * @since 1.2.0
 */
// tag::class[]
@RestController
public class CustomerController {

	private static final String HTML = "<H1>%s</H1>";

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers")
	public Iterable<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	@PostMapping("/customers")
	public Customer save(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@GetMapping("/customers/{name}")
	public Customer findByName(@PathVariable("name") String name) {
		return this.customerRepository.findByNameLike(name);
	}

	@GetMapping("/")
	public String home() {
		return format("Customer Relationship Management");
	}

	@GetMapping("/ping")
	public String ping() {
		return format("PONG");
	}

	private String format(String value) {
		return String.format(HTML, value);
	}
}
// end::class[]
