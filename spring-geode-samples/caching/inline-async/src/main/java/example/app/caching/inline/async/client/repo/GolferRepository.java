/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.inline.async.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import example.app.caching.inline.async.client.model.Golfer;

/**
 * Spring Data {@link JpaRepository} and Data Access Object (DAO) used to perform basic CRUD and simple SQL query
 * data access operations on {@link Golfer Golfers} stored in an RDBMS (database) with JPA (Hibernate).
 *
 * @author John Blum
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see example.app.caching.inline.async.client.model.Golfer
 * @since 1.4.0
 */
public interface GolferRepository extends JpaRepository<Golfer, String> {

}
