/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import example.app.caching.inline.model.Operator;
import example.app.caching.inline.model.ResultHolder;

/**
 * Spring Data {@link CrudRepository}, a.k.a. Data Access Object (DAO) used to perform basic CRUD and simple query
 * data access operations on {@link ResultHolder} objects to/from the backend data store.
 *
 * @author John Blum
 * @see org.springframework.data.repository.CrudRepository
 * @see example.app.caching.inline.model.ResultHolder
 * @since 1.1.0
 */
@SuppressWarnings("unused")
// tag::class[]
public interface CalculatorRepository
		extends CrudRepository<ResultHolder, ResultHolder.ResultKey> {

	Optional<ResultHolder> findByOperandEqualsAndOperatorEquals(Number operand, Operator operator);

}
// end::class[]
