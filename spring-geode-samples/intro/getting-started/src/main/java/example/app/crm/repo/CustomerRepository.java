/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm.repo;

import org.springframework.data.repository.CrudRepository;

import example.app.crm.model.Customer;

/**
 * Spring Data {@link CrudRepository} or Data Access Object (DAO) implementing basic CRUD and simple Query data access
 * operations on {@link Customer} objects.
 *
 * @author John Blum
 * @see org.springframework.data.repository.CrudRepository
 * @see example.app.crm.model.Customer
 * @since 1.2.0
 */
// tag::class[]
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByNameLike(String name);

}
// end::class[]
