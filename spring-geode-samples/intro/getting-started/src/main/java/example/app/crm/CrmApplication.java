/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import example.app.crm.model.Customer;
import example.app.crm.repo.CustomerRepository;

/**
 * {@link SpringBootApplication Spring Boot application} implementing a Customer Relationship Management service (CRM).
 *
 * @author John Blum
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @since 1.2.0
 */
@SuppressWarnings("unused")
// tag::class[]
@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	// tag::runner[]
	@Bean
	ApplicationRunner runner(CustomerRepository customerRepository) {

		return args -> {

			assertThat(customerRepository.count()).isEqualTo(0);

			Customer jonDoe = Customer.newCustomer(1L, "JonDoe");

			System.err.printf("Saving Customer [%s]...%n", jonDoe);

			jonDoe = customerRepository.save(jonDoe);

			assertThat(jonDoe).isNotNull();
			assertThat(jonDoe.getId()).isEqualTo(1L);
			assertThat(jonDoe.getName()).isEqualTo("JonDoe");
			assertThat(customerRepository.count()).isEqualTo(1);

			System.err.println("Querying for Customer [SELECT * FROM /Customers WHERE name LIKE '%Doe']...");

			Customer queriedJonDoe = customerRepository.findByNameLike("%Doe");

			assertThat(queriedJonDoe).isEqualTo(jonDoe);

			System.err.printf("Customer was [%s]%n", queriedJonDoe);
		};
	}
	// end::runner[]
}
// end::class[]
