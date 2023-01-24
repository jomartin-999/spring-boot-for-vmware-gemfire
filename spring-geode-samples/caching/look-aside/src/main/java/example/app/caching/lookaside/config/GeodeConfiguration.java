/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.lookaside.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;

/**
 * Spring {@link Configuration} class used to configure Apache Geode.
 *
 * @author John Blum
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions
 * @see org.springframework.geode.config.annotation.EnableClusterAware
 * @since 1.0.0
 */
@SuppressWarnings("unused")
// tag::class[]
@Configuration
@EnableClusterAware
@EnableCachingDefinedRegions
public class GeodeConfiguration { }
// end::class[]
