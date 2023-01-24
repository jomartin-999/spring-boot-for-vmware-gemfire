/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.geode.cache.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import example.app.crm.model.Customer;
import example.app.crm.repo.CustomerRepository;

/**
 * Integration Tests for the Customer Service application.
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.apache.geode.cache.Region
 * @see org.springframework.boot.test.context.SpringBootTest
 * @see org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport
 * @see org.springframework.test.annotation.DirtiesContext
 * @see org.springframework.test.context.ActiveProfiles
 * @see org.springframework.test.context.junit4.SpringRunner
 * @since 1.1.0
 */
// tag::class[]
@ActiveProfiles("debug")
@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
	"spring.boot.data.gemfire.security.ssl.environment.post-processor.enabled=false"
})
@SuppressWarnings("unused")
public class CustomerServiceApplicationIntegrationTests extends IntegrationTestsSupport {

	@Autowired
	private CustomerRepository customerRepository;

	@Resource(name = "Customers")
	private Region<Long, Customer> customers;

	@Before
	public void setup() {

		assertThat(this.customerRepository).isNotNull();
		assertThat(this.customers).isNotNull();
		assertThat(this.customers.getName()).isEqualTo("Customers");
		assertThat(this.customers).hasSize(1);

		Customer jonDoe = this.customerRepository.findByNameLike("%Doe");

		assertThat(jonDoe).isNotNull();
		assertThat(jonDoe.getName()).isEqualTo("Jon Doe");
	}

	@Test
	public void putAndGetCustomerIsSuccessful() {

		Customer expectedJaneDoe = Customer.newCustomer(2L, "Jane Doe");

		expectedJaneDoe = this.customerRepository.save(expectedJaneDoe);

		Customer actualJaneDoe = this.customerRepository.findByNameLike("Jane%");

		assertThat(actualJaneDoe).isNotNull();
		assertThat(actualJaneDoe).isEqualTo(expectedJaneDoe);
	}
}
// end::class[]
