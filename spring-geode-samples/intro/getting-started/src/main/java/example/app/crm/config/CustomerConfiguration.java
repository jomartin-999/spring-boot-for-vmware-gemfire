/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;

import example.app.crm.model.Customer;

/**
 * Spring {@link Configuration} class used to configure and initialize Apache Geode for the CRM application
 * beyond Spring Boot {@literal auto-configuration}.
 *
 * @author John Blum
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions
 * @see org.springframework.geode.config.annotation.EnableClusterAware
 * @since 1.2.0
 */
// tag::class[]
@Configuration
@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
public class CustomerConfiguration {

}
// end::class[]
