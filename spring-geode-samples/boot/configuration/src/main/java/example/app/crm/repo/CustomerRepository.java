/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm.repo;

import org.springframework.data.repository.CrudRepository;

import example.app.crm.model.Customer;

/**
 * Data Access Object (DAO) used to perform basic CRUD and simple query operations on {@link Customer} objects
 * store in Apache Geode
 *
 * @author John Blum
 * @see java.lang.Long
 * @see org.springframework.data.repository.CrudRepository
 * @see example.app.crm.model.Customer
 * @since 1.0.0
 */
// tag::class[]
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByNameLike(String name);

}
// end::class[]
